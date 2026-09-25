package com.navi.backend.semantic.y;

import com.navi.backend.ast.y.expressions.ArrayAccessExpression;
import com.navi.backend.ast.y.expressions.BinaryExpression;
import com.navi.backend.ast.y.expressions.Expression;
import com.navi.backend.ast.y.expressions.FunctionCallExpression;
import com.navi.backend.ast.y.expressions.MemberAccessExpression;
import com.navi.backend.ast.y.expressions.ReadExpression;
import com.navi.backend.ast.y.expressions.UnaryExpression;
import com.navi.backend.ast.y.expressions.VariableExpression;
import com.navi.backend.ast.y.expressions.literals.LiteralExpression;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.Symbol;
import com.navi.backend.semantic.SymbolKind;
import com.navi.backend.semantic.Type;
import com.navi.backend.semantic.TypeRules;

import java.util.ArrayList;
import java.util.List;

/**
 * Chequeo de expresiones de Y: resuelve el tipo de cada expresión, lo anota en
 * {@link SemanticContext} para el C3D y reporta errores vía {@link TypeRules}.
 * El recorrido de los hijos lo hace el visitante (dispatcher) que la instancia.
 */
public class YExpressionChecker {

    private final SemanticContext context;
    private final TypeRules rules;
    private final AstYVisitor<Type> visitor;

    YExpressionChecker(SemanticContext context, TypeRules rules, AstYVisitor<Type> visitor) {
        this.context = context;
        this.rules = rules;
        this.visitor = visitor;
    }

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
            case NEGATE -> rules.unaryNumeric(t, node.getLine(), node.getColumn());
            case POST_INCREMENT, POST_DECREMENT -> rules.incrementOperand(t, node.getLine(), node.getColumn());
        };
        context.annotate(node, result);
        return result;
    }

    Type variable(VariableExpression node) {
        Symbol s = context.getSymbolTable().resolve(node.getName());
        if (s == null) {
            rules.error(node.getLine(), node.getColumn(), "Identificador no definido: " + node.getName());
            context.annotate(node, Type.ERROR);
            return Type.ERROR;
        }
        context.annotate(node, s.getType());
        return s.getType();
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
            List<Symbol> overloads = context.getSymbolTable().resolveCallable(ve.getName());
            if (overloads == null) {
                rules.error(node.getLine(), node.getColumn(), "Función no definida: " + ve.getName());
            } else {
                Symbol match = null;
                for (Symbol fn : overloads) {
                    if (fn.getKind() == SymbolKind.FUNCTION && fn.getSignature().matches(argTypes)) {
                        match = fn;
                        break;
                    }
                }
                if (match == null) {
                    rules.error(node.getLine(), node.getColumn(),
                            "No hay una función '" + ve.getName() + "' para los argumentos " + argTypes);
                } else {
                    result = match.getSignature().getReturnType();
                }
            }
        } else {
            rules.error(node.getLine(), node.getColumn(), "Expresión no invocable");
        }
        context.annotate(node, result);
        return result;
    }

    Type read(ReadExpression node) {
        context.annotate(node, Type.STRING);
        return Type.STRING;
    }

    Type literal(LiteralExpression node) {
        Type t = rules.literalType(node.getValue());
        context.annotate(node, t);
        return t;
    }
}
