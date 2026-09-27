package com.navi.backend.c3d.lat;

import com.navi.backend.ast.lat.expressions.ArrayAccessExpression;
import com.navi.backend.ast.lat.expressions.BinaryExpression;
import com.navi.backend.ast.lat.expressions.BinaryOperator;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.expressions.FunctionCallExpression;
import com.navi.backend.ast.lat.expressions.MemberAccessExpression;
import com.navi.backend.ast.lat.expressions.ObjectCreationExpression;
import com.navi.backend.ast.lat.expressions.UnaryExpression;
import com.navi.backend.ast.lat.expressions.VariableExpression;
import com.navi.backend.ast.lat.expressions.literals.BooleanLiteral;
import com.navi.backend.ast.lat.expressions.literals.CharLiteral;
import com.navi.backend.ast.lat.expressions.literals.DecimalLiteral;
import com.navi.backend.ast.lat.expressions.literals.NumberLiteral;
import com.navi.backend.ast.lat.expressions.literals.StringLiteral;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import com.navi.backend.c3d.C3DEmitter;
import com.navi.backend.semantic.model.AggregateType;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.model.Symbol;
import com.navi.backend.semantic.enums.SymbolKind;
import com.navi.backend.semantic.model.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Emisión C3D de expresiones de Lat: aritmética, booleanos materializados con
 * saltos, llamadas y acceso a miembros. El recorrido de los hijos lo hace el
 * dispatcher ({@link LatC3DVisitor}).
 */
class LatExpressionC3D {

    private final SemanticContext context;
    private final C3DEmitter emitter;
    private final LatC3DResolver resolver;
    private final AstLatVisitor<String> visitor;

    LatExpressionC3D(SemanticContext context, C3DEmitter emitter, LatC3DResolver resolver, AstLatVisitor<String> visitor) {
        this.context = context;
        this.emitter = emitter;
        this.resolver = resolver;
        this.visitor = visitor;
    }

    // ---------------------------------------------------------------- expresiones

    String binary(BinaryExpression node) {
        switch (node.getOperator()) {
            case AND -> { return materializeAnd(node.getLeft(), node.getRight()); }
            case OR -> { return materializeOr(node.getLeft(), node.getRight()); }
            case EQUAL, NOT_EQUAL, LESS, LESS_EQUAL, GREATER, GREATER_EQUAL -> {
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
            case POST_INCREMENT -> postIncDec(node.getExpression(), "+");
            case POST_DECREMENT -> postIncDec(node.getExpression(), "-");
        };
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
        Type owner = context.typeOf(node.getObject());
        if (owner != null && owner.isStruct()) {
            int off = resolver.fieldOffset(owner, node.getMember());
            return emitter.stackLoadAt(emitter.binary("+", resolver.structAddr(node.getObject()), String.valueOf(off)));
        }
        String obj = node.getObject().accept(visitor);
        int off = resolver.fieldIndex(owner, node.getMember());
        return emitter.heapLoad(obj, String.valueOf(off));
    }

    String functionCall(FunctionCallExpression node) {
        Type t = context.typeOf(node);
        return emitCall(node.getCallee(), node.getArguments(), t != null && t.isVoid());
    }

    String objectCreation(ObjectCreationExpression node) {
        List<String> args = evalArgs(node.getArguments(), null, false);
        AggregateType agg = context.getTypeTable().resolve(node.getType());
        List<Type> argTypes = argTypes(node.getArguments());
        String ctor = agg != null && agg.findConstructor(argTypes) != null
                ? C3DEmitter.ctorLabel(node.getType(), agg.findConstructor(argTypes).getSignature().getParameters())
                : node.getType() + "_init";
        String obj = emitter.heapAlloc(String.valueOf(resolver.objectSize(node.getType())));
        List<String> callArgs = new ArrayList<>();
        callArgs.add(obj);
        callArgs.addAll(args);
        emitter.callVoid(ctor, callArgs);
        return obj;
    }

    String booleanLiteral(BooleanLiteral node) { return emitter.literal(node.isValue() ? "1" : "0"); }
    String charLiteral(CharLiteral node) { return emitter.literal("'" + node.getValue() + "'"); }
    String decimalLiteral(DecimalLiteral node) { return emitter.literal(String.valueOf(node.getValue())); }
    String numberLiteral(NumberLiteral node) { return emitter.literal(String.valueOf(node.getValue())); }
    String stringLiteral(StringLiteral node) { return emitter.literal("\"" + node.getValue() + "\""); }

    // ---------------------------------------------------------------- llamadas

    String emitCall(Expression callee, List<Expression> args, boolean isVoid) {
        if (callee instanceof VariableExpression ve) {
            Symbol fn = resolveFunction(ve.getName(), argTypes(args));
            boolean byRef = fn != null && fn.isReference();
            List<Type> params = fn == null ? null : fn.getSignature().getParameters();
            List<String> argPlaces = evalArgs(args, params, byRef);
            if (isVoid) {
                emitter.callVoid(ve.getName(), argPlaces);
                return null;
            }
            return emitter.call(ve.getName(), argPlaces);
        }
        if (callee instanceof MemberAccessExpression ma) {
            List<String> argPlaces = evalArgs(args, null, false);
            String objPlace = ma.getObject().accept(visitor);
            Type objType = context.typeOf(ma.getObject());
            String label = methodLabel(objType, ma.getMember(), argTypes(args));
            List<String> callArgs = new ArrayList<>();
            callArgs.add(objPlace);
            callArgs.addAll(argPlaces);
            if (isVoid) {
                emitter.callVoid(label, callArgs);
                return null;
            }
            return emitter.call(label, callArgs);
        }
        return emitter.literal("0");
    }

    /**
     * Evalúa los argumentos. Si el callee es una función de Y (parámetros por
     * referencia), los argumentos arreglo/struct se pasan como dirección; para el
     * resto (primitivos, funciones de Lat, objetos de Z) se pasa el valor.
     */
    private List<String> evalArgs(List<Expression> args, List<Type> params, boolean byRef) {
        List<String> places = new ArrayList<>();
        if (args == null) return places;
        for (int i = 0; i < args.size(); i++) {
            Expression a = args.get(i);
            Type p = params != null && i < params.size() ? params.get(i) : null;
            if (byRef && p != null && (p.isArray() || p.isStruct())) places.add(resolver.addressOf(a));
            else places.add(a.accept(visitor));
        }
        return places;
    }

    private Symbol resolveFunction(String name, List<Type> argTypes) {
        List<Symbol> overloads = context.getSymbolTable().resolveCallable(name);
        if (overloads != null) {
            for (Symbol fn : overloads) {
                if (fn.getKind() == SymbolKind.FUNCTION && fn.getSignature().matches(argTypes)) {
                    return fn;
                }
            }
        }
        return null;
    }

    private String methodLabel(Type objType, String name, List<Type> argTypes) {
        if (objType == null || !objType.isClass()) return "unknown_" + name;
        AggregateType agg = context.getTypeTable().resolve(objType.getName());
        if (agg == null) return objType.getName() + "_" + name;
        Symbol m = agg.findMethod(name, argTypes);
        if (m == null) return objType.getName() + "_" + name;
        return C3DEmitter.methodLabel(objType.getName(), name, m.getSignature().getParameters());
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
            case LESS_EQUAL -> "<=";
            case GREATER -> ">";
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
            default -> "?";
        };
    }
}
