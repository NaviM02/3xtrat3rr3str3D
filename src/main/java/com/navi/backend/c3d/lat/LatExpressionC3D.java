package com.navi.backend.c3d.lat;

import com.navi.backend.ast.lat.expressions.ArrayAccessExpression;
import com.navi.backend.ast.lat.expressions.BinaryExpression;
import com.navi.backend.ast.lat.expressions.BinaryOperator;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.expressions.FunctionCallExpression;
import com.navi.backend.ast.lat.expressions.MemberAccessExpression;
import com.navi.backend.ast.lat.expressions.NullExpression;
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
import com.navi.backend.c3d.MethodDispatchC3D;
import com.navi.backend.semantic.model.AggregateType;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.model.Symbol;
import com.navi.backend.semantic.enums.SymbolKind;
import com.navi.backend.semantic.model.Type;
import com.navi.backend.semantic.rules.TypeCompat;

import java.util.ArrayList;
import java.util.List;

// emision C3D de expresiones de Lat: aritmetica, booleanos con saltos,
// llamadas y acceso a miembros
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
                Type cmp = TypeCompat.promoteNumeric(context.typeOf(node.getLeft()), context.typeOf(node.getRight()));
                return emitter.materializeComparison(l, relop(node.getOperator()), r, cmp);
            }
            default -> {
                String l = node.getLeft().accept(visitor);
                String r = node.getRight().accept(visitor);
                return emitter.binary(arith(node.getOperator()), l, r, context.typeOf(node));
            }
        }
    }

    String unary(UnaryExpression node) {
        return switch (node.getOperator()) {
            case NOT -> materializeNot(node.getExpression());
            case NEGATE -> emitter.unaryNeg(node.getExpression().accept(visitor), context.typeOf(node));
            case POST_INCREMENT -> postIncDec(node.getExpression(), "+");
            case POST_DECREMENT -> postIncDec(node.getExpression(), "-");
        };
    }

    private String postIncDec(Expression target, String op) {
        String old = target.accept(visitor);
        resolver.assignTo(target, emitter.binary(op, old, "1", context.typeOf(target)));
        return old;
    }

    String variable(VariableExpression node) {
        return emitter.loadVar(node.getName());
    }

    String arrayAccess(ArrayAccessExpression node) {
        return emitter.stackLoadAt(resolver.arrayElementAddr(node), context.typeOf(node));
    }

    String memberAccess(MemberAccessExpression node) {
        Type owner = context.typeOf(node.getObject());
        if (owner != null && owner.isStruct()) {
            int off = resolver.fieldOffset(owner, node.getMember());
            return emitter.stackLoadAt(emitter.binary("+", resolver.structAddr(node.getObject()), String.valueOf(off)), context.typeOf(node));
        }
        String obj = node.getObject().accept(visitor);
        int off = resolver.fieldIndex(owner, node.getMember());
        return emitter.heapLoad(obj, String.valueOf(off), context.typeOf(node));
    }

    String functionCall(FunctionCallExpression node) {
        Type t = context.typeOf(node);
        return emitCall(node.getCallee(), node.getArguments(), t != null && t.isVoid(), t);
    }

    String objectCreation(ObjectCreationExpression node) {
        List<String> args = evalArgs(node.getArguments(), null, false);
        AggregateType agg = context.getTypeTable().resolve(node.getType());
        List<Type> argTypes = argTypes(node.getArguments());
        Symbol constructor = agg == null ? null : agg.findConstructor(argTypes, context.getTypeTable());
        String ctor = constructor != null
                ? C3DEmitter.ctorLabel(node.getType(), constructor.getSignature().getParameters())
                : node.getType() + "_init";
        String obj = emitter.heapAlloc(String.valueOf(resolver.objectSize(node.getType())));
        if (agg != null) emitter.heapStore(obj, "0", String.valueOf(agg.getTypeId()));
        List<String> callArgs = new ArrayList<>();
        callArgs.add(obj);
        callArgs.addAll(args);
        emitter.callVoid(ctor, callArgs);
        return obj;
    }

    String nullExpression(NullExpression node) {
        return emitter.literal("null");
    }

    String booleanLiteral(BooleanLiteral node) { return emitter.literal(node.isValue() ? "1" : "0"); }
    String charLiteral(CharLiteral node) { return emitter.literal("'" + node.getValue() + "'"); }
    String decimalLiteral(DecimalLiteral node) { return emitter.literal(String.valueOf(node.getValue())); }
    String numberLiteral(NumberLiteral node) { return emitter.literal(String.valueOf(node.getValue())); }
    String stringLiteral(StringLiteral node) { return emitter.literal("\"" + node.getValue() + "\""); }

    // ---------------------------------------------------------------- llamadas

    String emitCall(Expression callee, List<Expression> args, boolean isVoid, Type resultType) {
        if (callee instanceof VariableExpression ve) {
            Symbol fn = resolveFunction(ve.getName(), argTypes(args));
            boolean byRef = fn != null && fn.isReference();
            List<Type> params = fn == null ? null : fn.getSignature().getParameters();
            List<String> argPlaces = evalArgs(args, params, byRef);
            if (isVoid) {
                emitter.callVoid(ve.getName(), argPlaces);
                return null;
            }
            return emitter.call(ve.getName(), argPlaces, resultType);
        }
        if (callee instanceof MemberAccessExpression ma) {
            String objPlace = ma.getObject().accept(visitor);
            List<String> argPlaces = evalArgs(args, null, false);
            Type objType = context.typeOf(ma.getObject());
            AggregateType staticClass = objType == null || !objType.isClass()
                    ? null : context.getTypeTable().resolve(objType.getName());
            String result = MethodDispatchC3D.call(context, emitter, staticClass, ma.getMember(),
                    argTypes(args), objPlace, argPlaces, isVoid);
            return result == null && isVoid ? null : result;
        }
        return emitter.literal("0");
    }

    // evalua argumentos: los arreglos se pasan por direccion (los multi-D agregan
    // sus dimensiones dims[1..N-1]); los structs de Y van por referencia; el resto por valor
    private List<String> evalArgs(List<Expression> args, List<Type> params, boolean byRef) {
        List<String> places = new ArrayList<>();
        if (args == null) return places;
        for (int i = 0; i < args.size(); i++) {
            Expression a = args.get(i);
            Type p = params != null && i < params.size() ? params.get(i) : null;
            if (p != null && p.isArray()) {
                places.add(resolver.addressOf(a));
                if (p.getDimensions() > 1) {
                    List<Integer> dims = resolver.arrayDimsOf(a);
                    for (int k = 1; k < p.getDimensions(); k++) {
                        int d = dims != null && k < dims.size() ? dims.get(k) : 1;
                        places.add(emitter.literal(String.valueOf(d)));
                    }
                }
            } else if (byRef && p != null && p.isStruct()) {
                places.add(resolver.addressOf(a));
            } else {
                String place = a.accept(visitor);
                places.add(p == null ? place : emitter.coerceTo(p, place));
            }
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
