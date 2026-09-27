package com.navi.backend.c3d.z;

import com.navi.backend.ast.z.expressions.ArrayAccessExpression;
import com.navi.backend.ast.z.expressions.ArrayCreationExpression;
import com.navi.backend.ast.z.expressions.AssignmentExpression;
import com.navi.backend.ast.z.expressions.BinaryExpression;
import com.navi.backend.ast.z.expressions.BinaryOperator;
import com.navi.backend.ast.z.expressions.Expression;
import com.navi.backend.ast.z.expressions.ExpressionList;
import com.navi.backend.ast.z.expressions.FunctionCallExpression;
import com.navi.backend.ast.z.expressions.MemberAccessExpression;
import com.navi.backend.ast.z.expressions.NullExpression;
import com.navi.backend.ast.z.expressions.ObjectCreationExpression;
import com.navi.backend.ast.z.expressions.ReadExpression;
import com.navi.backend.ast.z.expressions.TernaryExpression;
import com.navi.backend.ast.z.expressions.UnaryExpression;
import com.navi.backend.ast.z.expressions.VariableExpression;
import com.navi.backend.ast.z.expressions.literals.LiteralExpression;
import com.navi.backend.ast.z.statements.AssignmentOperator;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import com.navi.backend.c3d.C3DEmitter;
import com.navi.backend.semantic.AggregateType;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.Symbol;
import com.navi.backend.semantic.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Emisión C3D de expresiones de Z: aritmética, booleanos materializados con
 * saltos, llamadas a métodos (con {@code this} implícito), {@code new} y
 * creación de arreglos en el heap. El recorrido de los hijos lo hace el
 * dispatcher ({@link ZC3DVisitor}).
 */
class ZExpressionC3D {

    private final SemanticContext context;
    private final C3DEmitter emitter;
    private final ZResolverC3D resolver;
    private final AstZVisitor<String> visitor;

    ZExpressionC3D(SemanticContext context, C3DEmitter emitter, ZResolverC3D resolver, AstZVisitor<String> visitor) {
        this.context = context;
        this.emitter = emitter;
        this.resolver = resolver;
        this.visitor = visitor;
    }

    String binary(BinaryExpression node) {
        switch (node.getOperator()) {
            case AND -> { return materializeAnd(node.getLeft(), node.getRight()); }
            case OR -> { return materializeOr(node.getLeft(), node.getRight()); }
            case EQUAL, NOT_EQUAL, LESS, GREATER, LESS_EQUAL, GREATER_EQUAL -> {
                String l = node.getLeft().accept(visitor);
                String r = node.getRight().accept(visitor);
                return emitter.materializeComparison(l, relop(node.getOperator()), r);
            }
            default -> {
                String l = node.getLeft().accept(visitor);
                String r = node.getRight().accept(visitor);
                return emitter.binary(arith(node.getOperator()), l, r);
            }
        }
    }

    String unary(UnaryExpression node) {
        return switch (node.getOperator()) {
            case NOT -> materializeNot(node.getExpression());
            case NEGATE -> emitter.unaryNeg(node.getExpression().accept(visitor));
            case POSITIVE -> node.getExpression().accept(visitor);
            case POST_INCREMENT -> postIncDec(node.getExpression(), "+");
            case POST_DECREMENT -> postIncDec(node.getExpression(), "-");
        };
    }

    private String postIncDec(Expression target, String op) {
        String old = target.accept(visitor);
        resolver.assignTo(target, emitter.binary(op, old, "1"));
        return old;
    }

    String assignment(AssignmentExpression node) {
        String value = node.getValue().accept(visitor);
        if (node.getOperator() == AssignmentOperator.ASSIGN) {
            resolver.assignTo(node.getTarget(), value);
        } else {
            String op = node.getOperator() == AssignmentOperator.PLUS_ASSIGN ? "+"
                    : node.getOperator() == AssignmentOperator.MINUS_ASSIGN ? "-" : "*";
            String current = node.getTarget().accept(visitor);
            resolver.assignTo(node.getTarget(), emitter.binary(op, current, value));
        }
        return value;
    }

    String ternary(TernaryExpression node) {
        String result = emitter.newTemp();
        String cond = node.getCondition().accept(visitor);
        String thenLabel = emitter.newLabel();
        String elseLabel = emitter.newLabel();
        String endLabel = emitter.newLabel();
        emitter.ifGoto(cond, "==", "1", thenLabel);
        emitter.jump(elseLabel);
        emitter.label(thenLabel);
        emitter.assign(result, node.getThenExpression().accept(visitor));
        emitter.jump(endLabel);
        emitter.label(elseLabel);
        emitter.assign(result, node.getElseExpression().accept(visitor));
        emitter.label(endLabel);
        return result;
    }

    String variable(VariableExpression node) {
        return resolver.place(node.getName());
    }

    String arrayAccess(ArrayAccessExpression node) {
        return emitter.heapLoadAt(resolver.arrayElementAddr(node));
    }

    String memberAccess(MemberAccessExpression node) {
        String base = node.getObject().accept(visitor);
        int off = resolver.fieldIndex(context.typeOf(node.getObject()), node.getMember());
        return emitter.heapLoad(base, String.valueOf(off));
    }

    String functionCall(FunctionCallExpression node) {
        List<String> argPlaces = evalArgs(node.getArguments());
        boolean isVoid = context.typeOf(node) != null && context.typeOf(node).isVoid();
        if (node.getFunction() instanceof VariableExpression ve) {
            String label = resolveMethodLabel(resolver.currentClass().getName(), ve.getName(), node.getArguments());
            List<String> callArgs = new ArrayList<>();
            callArgs.add(resolver.thisPlace());
            callArgs.addAll(argPlaces);
            if (isVoid) {
                emitter.callVoid(label, callArgs);
                return null;
            }
            return emitter.call(label, callArgs);
        }
        if (node.getFunction() instanceof MemberAccessExpression ma) {
            String obj = ma.getObject().accept(visitor);
            Type objType = context.typeOf(ma.getObject());
            String label = resolveMethodLabel(objType == null ? "" : objType.getName(), ma.getMember(), node.getArguments());
            List<String> callArgs = new ArrayList<>();
            callArgs.add(obj);
            callArgs.addAll(argPlaces);
            if (isVoid) {
                emitter.callVoid(label, callArgs);
                return null;
            }
            return emitter.call(label, callArgs);
        }
        return emitter.literal("0");
    }

    private String resolveMethodLabel(String owner, String name, List<Expression> args) {
        AggregateType agg = context.getTypeTable().resolve(owner);
        List<Type> argTypes = argTypes(args);
        if (agg == null) return owner + "_" + name;
        Symbol m = agg.findMethod(name, argTypes);
        if (m == null) return owner + "_" + name;
        return C3DEmitter.methodLabel(owner, name, m.getSignature().getParameters());
    }

    String objectCreation(ObjectCreationExpression node) {
        List<String> argPlaces = evalArgs(node.getArguments());
        AggregateType agg = context.getTypeTable().resolve(node.getTypeName());
        List<Type> argTypes = argTypes(node.getArguments());
        String ctor = C3DEmitter.ctorLabel(node.getTypeName(), agg != null && agg.findConstructor(argTypes) != null
                ? agg.findConstructor(argTypes).getSignature().getParameters() : List.of());
        String obj = emitter.heapAlloc(String.valueOf(resolver.objectSize(node.getTypeName())));
        List<String> callArgs = new ArrayList<>();
        callArgs.add(obj);
        callArgs.addAll(argPlaces);
        emitter.callVoid(ctor, callArgs);
        return obj;
    }

    String arrayCreation(ArrayCreationExpression node) {
        List<String> dims = new ArrayList<>();
        if (node.getDimensions() != null) {
            for (Expression d : node.getDimensions()) dims.add(d.accept(visitor));
        }
        String product = "1";
        for (String d : dims) product = emitter.binary("*", product, d);
        // Cabecera: [rank][d0][d1]... y luego los datos, todo en el mismo bloque del heap.
        String total = emitter.binary("+", String.valueOf(dims.size()), product);
        String base = emitter.heapAlloc(total);
        for (int k = 0; k < dims.size(); k++) {
            emitter.heapStore(base, String.valueOf(k), dims.get(k));
        }
        return base;
    }

    String nullExpression(NullExpression node) {
        return emitter.literal("null");
    }

    String read(ReadExpression node) {
        return emitter.read();
    }

    String expressionList(ExpressionList node) {
        String last = null;
        for (Expression e : node.getExpressions()) last = e.accept(visitor);
        return last;
    }

    String literal(LiteralExpression node) {
        return emitter.literal(C3DEmitter.formatLiteral(node.getValue()));
    }

    // ---------------------------------------------------------------- argumentos

    private List<String> evalArgs(List<Expression> args) {
        List<String> places = new ArrayList<>();
        if (args != null) for (Expression a : args) places.add(a.accept(visitor));
        return places;
    }

    private List<Type> argTypes(List<Expression> args) {
        List<Type> types = new ArrayList<>();
        if (args != null) for (Expression a : args) {
            Type t = context.typeOf(a);
            types.add(t == null ? Type.ERROR : t);
        }
        return types;
    }

    // ---------------------------------------------------------------- booleanos

    private String materializeAnd(Expression left, Expression right) {
        String result = emitter.newTemp();
        String lFalse = emitter.newLabel();
        String lTrue = emitter.newLabel();
        String lCheck = emitter.newLabel();
        String lEnd = emitter.newLabel();
        String l = left.accept(visitor);
        emitter.ifGoto(l, "==", "1", lCheck);
        emitter.jump(lFalse);
        emitter.label(lCheck);
        String r = right.accept(visitor);
        emitter.ifGoto(r, "==", "1", lTrue);
        emitter.jump(lFalse);
        emitter.label(lTrue);
        emitter.assign(result, "1");
        emitter.jump(lEnd);
        emitter.label(lFalse);
        emitter.assign(result, "0");
        emitter.label(lEnd);
        return result;
    }

    private String materializeOr(Expression left, Expression right) {
        String result = emitter.newTemp();
        String lTrue = emitter.newLabel();
        String lFalse = emitter.newLabel();
        String lCheck = emitter.newLabel();
        String lEnd = emitter.newLabel();
        String l = left.accept(visitor);
        emitter.ifGoto(l, "==", "1", lTrue);
        emitter.jump(lCheck);
        emitter.label(lCheck);
        String r = right.accept(visitor);
        emitter.ifGoto(r, "==", "1", lTrue);
        emitter.jump(lFalse);
        emitter.label(lTrue);
        emitter.assign(result, "1");
        emitter.jump(lEnd);
        emitter.label(lFalse);
        emitter.assign(result, "0");
        emitter.label(lEnd);
        return result;
    }

    private String materializeNot(Expression operand) {
        String result = emitter.newTemp();
        String lTrue = emitter.newLabel();
        String lFalse = emitter.newLabel();
        String lEnd = emitter.newLabel();
        String v = operand.accept(visitor);
        emitter.ifGoto(v, "==", "0", lTrue);
        emitter.jump(lFalse);
        emitter.label(lTrue);
        emitter.assign(result, "1");
        emitter.jump(lEnd);
        emitter.label(lFalse);
        emitter.assign(result, "0");
        emitter.label(lEnd);
        return result;
    }

    // ---------------------------------------------------------------- operadores

    private String relop(BinaryOperator op) {
        return switch (op) {
            case EQUAL -> "==";
            case NOT_EQUAL -> "!=";
            case LESS -> "<";
            case GREATER -> ">";
            case LESS_EQUAL -> "<=";
            case GREATER_EQUAL -> ">=";
            default -> "?";
        };
    }

    private String arith(BinaryOperator op) {
        return switch (op) {
            case ADD -> "+";
            case SUBTRACT -> "-";
            case MULTIPLY -> "*";
            case DIVIDE -> "/";
            case MODULO -> "%";
            default -> "?";
        };
    }
}
