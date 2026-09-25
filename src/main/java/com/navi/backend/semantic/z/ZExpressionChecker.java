package com.navi.backend.semantic.z;

import com.navi.backend.ast.z.expressions.ArrayAccessExpression;
import com.navi.backend.ast.z.expressions.ArrayCreationExpression;
import com.navi.backend.ast.z.expressions.AssignmentExpression;
import com.navi.backend.ast.z.expressions.BinaryExpression;
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
import com.navi.backend.semantic.AggregateType;
import com.navi.backend.semantic.Field;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.Symbol;
import com.navi.backend.semantic.Type;
import com.navi.backend.semantic.TypeCompat;
import com.navi.backend.semantic.TypeRules;

import java.util.ArrayList;
import java.util.List;

/**
 * Chequeo de expresiones de Z: resuelve el tipo de cada expresión, lo anota en
 * {@link SemanticContext} para el C3D y reporta errores vía {@link TypeRules}.
 * Mantiene la clase actual ({@code currentClass}) para resolver campos y
 * métodos sin calificar dentro de los cuerpos. El recorrido de los hijos lo
 * hace el visitante (dispatcher) que la instancia.
 */
public class ZExpressionChecker {

    private final SemanticContext context;
    private final TypeRules rules;
    private final ZTypeResolver types;
    private final AstZVisitor<Type> visitor;

    private AggregateType currentClass;

    ZExpressionChecker(SemanticContext context, TypeRules rules, ZTypeResolver types,
                       AstZVisitor<Type> visitor) {
        this.context = context;
        this.rules = rules;
        this.types = types;
        this.visitor = visitor;
    }

    void setCurrentClass(AggregateType currentClass) {
        this.currentClass = currentClass;
    }

    // ---------------------------------------------------------------- expresiones

    Type binary(BinaryExpression node) {
        Type l = node.getLeft().accept(visitor);
        Type r = node.getRight().accept(visitor);
        Type result = switch (node.getOperator()) {
            case AND, OR -> rules.boolOperands(l, r, node.getLine(), node.getColumn());
            case EQUAL, NOT_EQUAL -> rules.equality(l, r, node.getLine(), node.getColumn());
            case LESS, LESS_EQUAL, GREATER, GREATER_EQUAL ->
                    rules.relational(l, r, node.getLine(), node.getColumn());
            case ADD -> rules.addition(l, r, node.getLine(), node.getColumn());
            case SUBTRACT, MULTIPLY, DIVIDE, MODULO -> rules.arithmetic(l, r, node.getLine(), node.getColumn());
        };
        context.annotate(node, result);
        return result;
    }

    Type unary(UnaryExpression node) {
        Type t = node.getExpression().accept(visitor);
        Type result = switch (node.getOperator()) {
            case NOT -> rules.negation(t, node.getLine(), node.getColumn());
            case NEGATE, POSITIVE -> rules.unaryNumeric(t, node.getLine(), node.getColumn());
            case POST_INCREMENT, POST_DECREMENT -> rules.incrementOperand(t, node.getLine(), node.getColumn());
        };
        context.annotate(node, result);
        return result;
    }

    Type assignment(AssignmentExpression node) {
        Type target = node.getTarget().accept(visitor);
        Type value = node.getValue().accept(visitor);
        if (node.getOperator() == AssignmentOperator.ASSIGN) {
            rules.assign(target, value, node.getLine(), node.getColumn());
        } else {
            rules.compoundAssign(target, value, node.getLine(), node.getColumn());
        }
        context.annotate(node, value);
        return value;
    }

    Type ternary(TernaryExpression node) {
        rules.requireBool(node.getCondition().accept(visitor), node.getLine(), node.getColumn());
        Type t = node.getThenExpression().accept(visitor);
        Type e = node.getElseExpression().accept(visitor);
        Type result;
        if (TypeCompat.canAssign(t, e)) result = t;
        else if (TypeCompat.canAssign(e, t)) result = e;
        else {
            rules.error(node.getLine(), node.getColumn(), "Ramas del ternario incompatibles: " + t + " y " + e);
            result = Type.ERROR;
        }
        context.annotate(node, result);
        return result;
    }

    Type variable(VariableExpression node) {
        Type t = resolveVariable(node.getName(), node.getLine(), node.getColumn());
        context.annotate(node, t);
        return t;
    }

    Type arrayAccess(ArrayAccessExpression node) {
        Type arr = node.getArray().accept(visitor);
        Type idx = node.getIndex().accept(visitor);
        Type result = rules.arrayElement(arr, idx, node.getLine(), node.getColumn());
        context.annotate(node, result);
        return result;
    }

    Type memberAccess(MemberAccessExpression node) {
        Type objType = node.getObject().accept(visitor);
        Type result = rules.memberOf(objType, node.getMember(), node.getLine(), node.getColumn());
        context.annotate(node, result);
        return result;
    }

    Type functionCall(FunctionCallExpression node) {
        List<Type> argTypes = new ArrayList<>();
        if (node.getArguments() != null) {
            for (Expression a : node.getArguments()) argTypes.add(a.accept(visitor));
        }
        Type result = Type.ERROR;
        if (node.getFunction() instanceof VariableExpression ve) {
            Symbol m = currentClass == null ? null : currentClass.findMethod(ve.getName(), argTypes);
            if (m == null) {
                rules.error(node.getLine(), node.getColumn(),
                        "Método no definido: '" + ve.getName() + "' para " + argTypes);
            } else {
                result = m.getSignature().getReturnType();
            }
        } else if (node.getFunction() instanceof MemberAccessExpression ma) {
            Type objType = ma.getObject().accept(visitor);
            if (!objType.isClass()) {
                rules.error(node.getLine(), node.getColumn(), "El tipo " + objType + " no tiene métodos");
            } else {
                AggregateType agg = context.getTypeTable().resolve(objType.getName());
                Symbol m = agg == null ? null : agg.findMethod(ma.getMember(), argTypes);
                if (m == null) {
                    rules.error(node.getLine(), node.getColumn(),
                            "Método '" + ma.getMember() + "' no definido en " + objType + " para " + argTypes);
                } else {
                    result = m.getSignature().getReturnType();
                }
            }
        } else {
            rules.error(node.getLine(), node.getColumn(), "Expresión no invocable");
        }
        context.annotate(node, result);
        return result;
    }

    Type objectCreation(ObjectCreationExpression node) {
        List<Type> argTypes = new ArrayList<>();
        if (node.getArguments() != null) {
            for (Expression a : node.getArguments()) argTypes.add(a.accept(visitor));
        }
        AggregateType agg = context.getTypeTable().resolve(node.getTypeName());
        Type result;
        if (agg == null) {
            rules.error(node.getLine(), node.getColumn(), "Clase no definida: " + node.getTypeName());
            result = Type.ERROR;
        } else {
            if (agg.findConstructor(argTypes) == null) {
                rules.error(node.getLine(), node.getColumn(),
                        "No existe un constructor de " + node.getTypeName() + " para " + argTypes);
            }
            result = Type.classType(node.getTypeName());
        }
        context.annotate(node, result);
        return result;
    }

    Type arrayCreation(ArrayCreationExpression node) {
        Type base = types.resolve(node.getType());
        int rank = node.getDimensions() == null ? 0 : node.getDimensions().size();
        Type result = rank > 0 ? Type.array(base, rank) : base;
        if (node.getDimensions() != null) {
            for (Expression d : node.getDimensions()) d.accept(visitor);
        }
        context.annotate(node, result);
        return result;
    }

    Type nullExpression(NullExpression node) {
        context.annotate(node, Type.NULL);
        return Type.NULL;
    }

    Type read(ReadExpression node) {
        context.annotate(node, Type.STRING);
        return Type.STRING;
    }

    Type expressionList(ExpressionList node) {
        Type last = Type.ERROR;
        for (Expression e : node.getExpressions()) last = e.accept(visitor);
        return last;
    }

    Type literal(LiteralExpression node) {
        Type t = rules.literalType(node.getValue());
        context.annotate(node, t);
        return t;
    }

    // ---------------------------------------------------------------- helpers

    private Type resolveVariable(String name, int line, int col) {
        Symbol s = context.getSymbolTable().resolve(name);
        if (s != null) return s.getType();
        if (currentClass != null) {
            Field f = currentClass.findField(name);
            if (f != null) return f.getType();
        }
        rules.error(line, col, "Identificador no definido: " + name);
        return Type.ERROR;
    }
}
