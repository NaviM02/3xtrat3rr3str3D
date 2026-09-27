package com.navi.backend.c3d.y;

import com.navi.backend.ast.y.expressions.ArrayAccessExpression;
import com.navi.backend.ast.y.expressions.BinaryExpression;
import com.navi.backend.ast.y.expressions.BinaryOperator;
import com.navi.backend.ast.y.expressions.Expression;
import com.navi.backend.ast.y.expressions.FunctionCallExpression;
import com.navi.backend.ast.y.expressions.MemberAccessExpression;
import com.navi.backend.ast.y.expressions.ReadExpression;
import com.navi.backend.ast.y.expressions.UnaryExpression;
import com.navi.backend.ast.y.expressions.UnaryOperator;
import com.navi.backend.ast.y.expressions.VariableExpression;
import com.navi.backend.ast.y.expressions.literals.LiteralExpression;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import com.navi.backend.c3d.C3DEmitter;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.model.Symbol;
import com.navi.backend.semantic.enums.SymbolKind;
import com.navi.backend.semantic.model.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Emisión C3D de expresiones de Y: aritmética, booleanos materializados con
 * saltos, llamadas (con paso por referencia de arreglos/structs) y acceso a
 * miembros. El recorrido de los hijos lo hace el dispatcher ({@link YC3DVisitor}).
 */
class YExpressionC3D {

    private final SemanticContext context;
    private final C3DEmitter emitter;
    private final YResolverC3D resolver;
    private final AstYVisitor<String> visitor;

    YExpressionC3D(SemanticContext context, C3DEmitter emitter, YResolverC3D resolver, AstYVisitor<String> visitor) {
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
        if (node.getOperator() == UnaryOperator.NOT) return materializeNot(node.getExpression());
        if (node.getOperator() == UnaryOperator.NEGATE) return emitter.unaryNeg(node.getExpression().accept(visitor));
        String op = node.getOperator() == UnaryOperator.POST_INCREMENT ? "+" : "-";
        return postIncDec(node.getExpression(), op);
    }

    private String postIncDec(Expression target, String op) {
        String old = target.accept(visitor);
        resolver.assignTo(target, emitter.binary(op, old, "1"));
        return old;
    }

    String variable(VariableExpression node) {
        return emitter.loadVar(node.getName());
    }

    String arrayAccess(ArrayAccessExpression node) {
        return emitter.stackLoadAt(resolver.arrayElementAddr(node));
    }

    String memberAccess(MemberAccessExpression node) {
        int off = resolver.fieldOffset(context.typeOf(node.getObject()), node.getMember());
        return emitter.stackLoadAt(emitter.binary("+", resolver.structAddr(node.getObject()), String.valueOf(off)));
    }

    String functionCall(FunctionCallExpression node) {
        if (node.getFunction() instanceof VariableExpression ve) {
            List<Type> argTypes = argTypes(node.getArguments());
            Symbol fn = resolveFunction(ve.getName(), argTypes);
            boolean byRef = fn != null && fn.isReference();
            List<Type> params = fn == null ? null : fn.getSignature().getParameters();
            List<String> args = evalArgs(node.getArguments(), params, byRef);
            Type t = context.typeOf(node);
            if (t != null && t.isVoid()) {
                emitter.callVoid(ve.getName(), args);
                return null;
            }
            return emitter.call(ve.getName(), args);
        }
        return emitter.literal("0");
    }

    String read(ReadExpression node) {
        return emitter.read();
    }

    String literal(LiteralExpression node) {
        return emitter.literal(C3DEmitter.formatLiteral(node.getValue()));
    }

    // ---------------------------------------------------------------- argumentos

    /** Evalúa argumentos enviando la dirección cuando el parámetro de Y es arreglo/struct. */
    private List<String> evalArgs(List<Expression> args, List<Type> params, boolean byRef) {
        List<String> places = new ArrayList<>();
        if (args == null) return places;
        for (int i = 0; i < args.size(); i++) {
            Expression a = args.get(i);
            Type p = params != null && i < params.size() ? params.get(i) : null;
            if (byRef && p != null && (p.isArray() || p.isStruct())) places.add(addressOf(a));
            else places.add(a.accept(visitor));
        }
        return places;
    }

    private String addressOf(Expression e) {
        if (e instanceof VariableExpression v) {
            return emitter.isReference(v.getName()) ? emitter.loadVar(v.getName()) : emitter.varAddr(v.getName());
        }
        if (e instanceof ArrayAccessExpression a) return resolver.arrayElementAddr(a);
        if (e instanceof MemberAccessExpression m) return resolver.structAddr(m);
        return e.accept(visitor);
    }

    private List<Type> argTypes(List<Expression> args) {
        List<Type> types = new ArrayList<>();
        if (args != null) for (Expression a : args) {
            Type t = context.typeOf(a);
            types.add(t == null ? Type.ERROR : t);
        }
        return types;
    }

    private Symbol resolveFunction(String name, List<Type> args) {
        List<Symbol> overloads = context.getSymbolTable().resolveCallable(name);
        if (overloads != null) {
            for (Symbol fn : overloads) {
                if (fn.getKind() == SymbolKind.FUNCTION && fn.getSignature().matches(args)) {
                    return fn;
                }
            }
        }
        return null;
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
