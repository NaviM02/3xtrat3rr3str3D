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
import com.navi.backend.ast.lat.expressions.UnaryOperator;
import com.navi.backend.ast.lat.expressions.VariableExpression;
import com.navi.backend.ast.lat.expressions.literals.NumberLiteral;
import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import com.navi.backend.semantic.model.AggregateType;
import com.navi.backend.semantic.model.Field;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.model.Symbol;
import com.navi.backend.semantic.enums.SymbolKind;
import com.navi.backend.semantic.model.Type;
import com.navi.backend.semantic.rules.TypeCompat;
import com.navi.backend.semantic.rules.TypeRules;

import java.util.ArrayList;
import java.util.List;

// chequea expresiones de Lat, anota su tipo y reporta errores; los hijos los recorre el visitor
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
        checkArrayBounds(node);
        Type result = rules.arrayElement(arr, idx, node.getLine(), node.getColumn());
        context.annotate(node, result);
        return result;
    }

    // valida el indice si es constante y se conocen los tamaños del arreglo
    private void checkArrayBounds(ArrayAccessExpression node) {
        List<Integer> sizes = sizesOf(node.getArray());

        if (sizes == null || sizes.isEmpty()) return;

        Integer index = constantIndex(node.getIndex());

        if (index == null) return;

        rules.checkIndex(sizes.get(0), index, node.getLine(), node.getColumn());
    }

    // tamaños conocidos del arreglo (variable o miembro); null si no se saben
    private List<Integer> sizesOf(Expression array) {
        if (array instanceof VariableExpression v) {
            Symbol s = context.getSymbolTable().resolve(v.getName());
            return s == null || s.getArraySizes().isEmpty() ? null : s.getArraySizes();
        }
        if (array instanceof ArrayAccessExpression a) {
            List<Integer> parent = sizesOf(a.getArray());
            return parent != null && parent.size() > 1 ? parent.subList(1, parent.size()) : null;
        }
        if (array instanceof MemberAccessExpression m) {
            Type owner = context.typeOf(m.getObject());
            if (owner != null && owner.isAggregate()) {
                AggregateType agg = context.getTypeTable().resolve(owner.getName());
                Field field = agg == null ? null : agg.findField(m.getMember());
                if (field != null && !field.getArrayDims().isEmpty()) return field.getArrayDims();
            }
            return null;
        }
        return null;
    }

    private Integer constantIndex(Expression e) {
        if (e instanceof NumberLiteral n) return n.getValue();
        if (e instanceof UnaryExpression u && u.getOperator() == UnaryOperator.NEGATE
                && u.getExpression() instanceof NumberLiteral n) {
            return -n.getValue();
        }
        return null;
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

    // literal de tipo fijo: lo anota y devuelve el tipo
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
        // literal de struct: soporte parcial en C3D (solo campos escalares);
        // se acepta si el tipo esperado es struct para no bloquear la compilacion
        if (init instanceof StructInitializer si) {
            if (!expected.isStruct()) {
                rules.error(line, col, "No se puede inicializar " + expected + " con un literal de estructura");
                return;
            }
            AggregateType agg = context.getTypeTable().resolve(expected.getName());
            if (agg == null) {
                rules.error(line, col, "Estructura no definida: " + expected.getName());
                return;
            }
            checkStructLiteral(si, expected, agg, line, col);
            return;
        }

        Type actual = initializerType(init);
        if (!TypeCompat.canAssign(expected, actual)) {
            rules.error(line, col, "No se puede inicializar " + expected + " con " + actual);
        }
    }

    // valida un literal de struct campo a campo; el campo puede venir por
    // nombre (campo: valor) o por posicion (segun orden de declaracion)
    private void checkStructLiteral(StructInitializer si, Type expected, AggregateType agg, int line, int col) {
        if (si.getFields().size() > agg.getFields().size()) {
            rules.error(line, col, "El literal de " + expected.getName() + " tiene demasiados campos ("
                    + si.getFields().size() + " para " + agg.getFields().size() + ")");
        }

        int index = 0;
        for (StructFieldInitializer f : si.getFields()) {
            Field declared;
            if (f.getName() != null) {
                declared = agg.findField(f.getName());
                if (declared == null) {
                    rules.error(f.getLine(), f.getColumn(), "El campo '" + f.getName()
                            + "' no existe en " + expected.getName());
                }
            } else {
                declared = index < agg.getFields().size() ? agg.getFields().get(index) : null;
            }
            index++;

            Initializer value = f.getValue();
            if (value instanceof StructInitializer nested) {
                Type nestedType = declared == null ? null : declared.getType();
                if (nestedType != null && nestedType.isStruct()) {
                    AggregateType nestedAgg = context.getTypeTable().resolve(nestedType.getName());
                    if (nestedAgg != null) {
                        checkStructLiteral(nested, nestedType, nestedAgg, f.getLine(), f.getColumn());
                    }
                }
                continue;
            }
            if (value instanceof ExpressionInitializer ei) {
                Type actual = ei.getExpression().accept(visitor);
                Type fieldType = declared == null ? null : declared.getType();
                if (fieldType != null && !fieldType.isArray() && !fieldType.isAggregate()
                        && !TypeCompat.canAssign(fieldType, actual)) {
                    rules.error(f.getLine(), f.getColumn(), "No se puede inicializar el campo '" + declared.getName()
                            + "' (" + fieldType + ") con " + actual);
                }
            }
        }
    }

    private Type structLiteralType(StructInitializer si) {
        // sin tipo explicito no se resuelve el struct; se valida contra el tipo esperado
        for (StructFieldInitializer f : si.getFields()) {
            if (f.getValue() instanceof ExpressionInitializer ei) ei.getExpression().accept(visitor);
        }
        return Type.ERROR;
    }
}
