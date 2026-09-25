package com.navi.backend.semantic.lat;

import com.navi.backend.ast.lat.declarations.initializers.ExpressionInitializer;
import com.navi.backend.ast.lat.declarations.initializers.Initializer;
import com.navi.backend.ast.lat.declarations.initializers.StructFieldInitializer;
import com.navi.backend.ast.lat.declarations.initializers.StructInitializer;
import com.navi.backend.ast.lat.expressions.ArrayAccessExpression;
import com.navi.backend.ast.lat.expressions.BinaryExpression;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.expressions.FunctionCallExpression;
import com.navi.backend.ast.lat.expressions.MemberAccessExpression;
import com.navi.backend.ast.lat.expressions.ObjectCreationExpression;
import com.navi.backend.ast.lat.expressions.UnaryExpression;
import com.navi.backend.ast.lat.expressions.VariableExpression;
import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import com.navi.backend.semantic.AggregateType;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.Symbol;
import com.navi.backend.semantic.SymbolKind;
import com.navi.backend.semantic.Type;
import com.navi.backend.semantic.TypeCompat;
import com.navi.backend.semantic.TypeRules;

import java.util.ArrayList;
import java.util.List;

/**
 * Chequeo de expresiones de Lat: resuelve el tipo de cada expresión, lo anota en
 * {@link SemanticContext} para el C3D y reporta errores vía {@link TypeRules}.
 * El recorrido de los hijos lo hace el visitante (dispatcher) que la instancia,
 * porque es el único que implementa {@link AstLatVisitor}.
 */
public class LatExpressionChecker {

    private final SemanticContext context;
    private final TypeRules rules;
    private final AstLatVisitor<Type> visitor;

    LatExpressionChecker(SemanticContext context, TypeRules rules, AstLatVisitor<Type> visitor) {
        this.context = context;
        this.rules = rules;
        this.visitor = visitor;
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
            case SUBTRACT, MULTIPLY, DIVIDE -> rules.arithmetic(l, r, node.getLine(), node.getColumn());
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
        Type result = resolveCall(node.getCallee(), node.getArguments(), node.getLine(), node.getColumn());
        context.annotate(node, result);
        return result;
    }

    Type objectCreation(ObjectCreationExpression node) {
        List<Type> argTypes = new ArrayList<>();
        if (node.getArguments() != null) {
            for (Expression a : node.getArguments()) argTypes.add(a.accept(visitor));
        }
        AggregateType agg = context.getTypeTable().resolve(node.getType());
        Type result;
        if (agg == null) {
            rules.error(node.getLine(), node.getColumn(), "Clase no definida: " + node.getType());
            result = Type.ERROR;
        } else if (!agg.isClass()) {
            rules.error(node.getLine(), node.getColumn(),
                    "novus requiere una clase, no una estructura: " + node.getType());
            result = Type.ERROR;
        } else {
            if (agg.findConstructor(argTypes) == null) {
                rules.error(node.getLine(), node.getColumn(), "No existe un constructor de " + node.getType()
                        + " para los argumentos " + argTypes);
            }
            result = Type.classType(node.getType());
        }
        context.annotate(node, result);
        return result;
    }

    /** Literal de tipo fijo: anota y devuelve el tipo. */
    Type constant(AstLatNode node, Type type) {
        context.annotate(node, type);
        return type;
    }

    // ---------------------------------------------------------------- llamadas

    Type resolveCall(Expression callee, List<Expression> args, int line, int col) {
        List<Type> argTypes = new ArrayList<>();
        if (args != null) {
            for (Expression a : args) argTypes.add(a.accept(visitor));
        }
        if (callee instanceof VariableExpression ve) {
            List<Symbol> overloads = context.getSymbolTable().resolveCallable(ve.getName());
            if (overloads == null) {
                rules.error(line, col, "Función no definida: " + ve.getName());
                return Type.ERROR;
            }
            for (Symbol fn : overloads) {
                if (fn.getKind() == SymbolKind.FUNCTION && fn.getSignature().matches(argTypes)) {
                    return fn.getSignature().getReturnType();
                }
            }
            rules.error(line, col, "No hay una función '" + ve.getName() + "' para los argumentos " + argTypes);
            return Type.ERROR;
        }
        if (callee instanceof MemberAccessExpression ma) {
            Type objType = ma.getObject().accept(visitor);
            return resolveMethod(objType, ma.getMember(), argTypes, line, col);
        }
        rules.error(line, col, "Expresión no invocable");
        return Type.ERROR;
    }

    private Type resolveMethod(Type objType, String name, List<Type> argTypes, int line, int col) {
        if (!objType.isClass()) {
            rules.error(line, col, "El tipo " + objType + " no tiene métodos");
            return Type.ERROR;
        }
        AggregateType agg = context.getTypeTable().resolve(objType.getName());
        if (agg == null) {
            rules.error(line, col, "Clase no definida: " + objType.getName());
            return Type.ERROR;
        }
        Symbol m = agg.findMethod(name, argTypes);
        if (m == null) {
            rules.error(line, col, "No existe el método '" + name + "' en " + objType + " para " + argTypes);
            return Type.ERROR;
        }
        return m.getSignature().getReturnType();
    }

    // ---------------------------------------------------------------- inicializadores

    Type initializerType(Initializer init) {
        if (init instanceof ExpressionInitializer ei) return ei.getExpression().accept(visitor);
        if (init instanceof StructInitializer si) return structLiteralType(si);
        return Type.ERROR;
    }

    void checkInitializer(Initializer init, Type expected, int line, int col) {
        Type actual = initializerType(init);
        if (!TypeCompat.canAssign(expected, actual)) {
            rules.error(line, col, "No se puede inicializar " + expected + " con " + actual);
        }
    }

    private Type structLiteralType(StructInitializer si) {
        // Sin tipo explícito no podemos resolver el struct; se valida por campos contra el tipo esperado.
        for (StructFieldInitializer f : si.getFields()) {
            if (f.getValue() instanceof ExpressionInitializer ei) ei.getExpression().accept(visitor);
        }
        return Type.ERROR;
    }
}
