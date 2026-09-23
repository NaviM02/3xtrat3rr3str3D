package com.navi.backend.semantic.lat;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.declarations.ArrayDeclaration;
import com.navi.backend.ast.lat.declarations.ArrayInitializer;
import com.navi.backend.ast.lat.declarations.Declaration;
import com.navi.backend.ast.lat.declarations.VariableDeclaration;
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
import com.navi.backend.ast.lat.expressions.literals.BooleanLiteral;
import com.navi.backend.ast.lat.expressions.literals.CharLiteral;
import com.navi.backend.ast.lat.expressions.literals.DecimalLiteral;
import com.navi.backend.ast.lat.expressions.literals.NumberLiteral;
import com.navi.backend.ast.lat.expressions.literals.StringLiteral;
import com.navi.backend.ast.lat.global.FunctionBody;
import com.navi.backend.ast.lat.global.FunctionDeclaration;
import com.navi.backend.ast.lat.global.GlobalVariableSection;
import com.navi.backend.ast.lat.global.ImportDeclaration;
import com.navi.backend.ast.lat.global.LocalVariableSection;
import com.navi.backend.ast.lat.global.Parameter;
import com.navi.backend.ast.lat.global.Program;
import com.navi.backend.ast.lat.statements.AssignmentStatement;
import com.navi.backend.ast.lat.statements.BlockStatement;
import com.navi.backend.ast.lat.statements.BreakStatement;
import com.navi.backend.ast.lat.statements.ContinueStatement;
import com.navi.backend.ast.lat.statements.DoWhileStatement;
import com.navi.backend.ast.lat.statements.ElseIfStatement;
import com.navi.backend.ast.lat.statements.ForStatement;
import com.navi.backend.ast.lat.statements.FunctionCallStatement;
import com.navi.backend.ast.lat.statements.IfStatement;
import com.navi.backend.ast.lat.statements.IncrementStatement;
import com.navi.backend.ast.lat.statements.PrintStatement;
import com.navi.backend.ast.lat.statements.ReadStatement;
import com.navi.backend.ast.lat.statements.ReturnStatement;
import com.navi.backend.ast.lat.statements.Statement;
import com.navi.backend.ast.lat.statements.WhileStatement;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import com.navi.backend.semantic.AggregateType;
import com.navi.backend.semantic.Field;
import com.navi.backend.semantic.ScopeKind;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.Symbol;
import com.navi.backend.semantic.SymbolKind;
import com.navi.backend.semantic.Type;
import com.navi.backend.semantic.TypeCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Pasada semántica de Lat: valida tipos y anota cada expresión con su {@link Type}.
 */
public class LatSemanticVisitor implements AstLatVisitor<Type> {

    private final SemanticContext context;
    private Type currentReturnType;

    public LatSemanticVisitor(SemanticContext context) {
        this.context = context;
    }

    public void build(Program program) {
        program.accept(this);
    }

    @Override
    public Type visit(Program node) {
        if (node.getGlobalVariables() != null) {
            for (Declaration d : node.getGlobalVariables().getDeclarations()) {
                checkGlobalInitializer(d);
            }
        }
        if (node.getFunctions() != null) {
            for (FunctionDeclaration f : node.getFunctions()) {
                f.accept(this);
            }
        }
        if (node.getMainStatements() != null) {
            for (Statement s : node.getMainStatements()) {
                s.accept(this);
            }
        }
        return null;
    }

    private void checkGlobalInitializer(Declaration d) {
        if (d instanceof VariableDeclaration v) {
            Type expected = resolveType(v.getType(), v.getLine(), v.getColumn());
            if (v.getInitializer() != null) {
                checkInitializer(v.getInitializer(), expected, v.getLine(), v.getColumn());
            }
        } else if (d instanceof ArrayDeclaration a) {
            Type base = resolveType(a.getType(), a.getLine(), a.getColumn());
            if (a.getInitializer() != null) {
                for (AstLatNode el : a.getInitializer().getElements()) {
                    Type actual = el instanceof Expression e ? e.accept(this) : Type.ERROR;
                    if (!TypeCompat.canAssign(base, actual)) {
                        error(el.getLine(), el.getColumn(), "Elemento de arreglo " + actual + " no asignable a " + base);
                    }
                }
            }
        }
    }

    @Override
    public Type visit(FunctionDeclaration node) {
        context.getSymbolTable().enterScope(ScopeKind.FUNCTION);
        if (node.getParameters() != null) {
            for (Parameter p : node.getParameters()) {
                Type t = resolveType(p.getType(), p.getLine(), p.getColumn());
                Symbol s = new Symbol(p.getName(), SymbolKind.PARAMETER, t, null, false,
                        context.getSymbolTable().getCurrentScope(), null, p.getLine(), p.getColumn());
                if (!context.getSymbolTable().defineUnique(s)) {
                    error(p.getLine(), p.getColumn(), "Parámetro duplicado: " + p.getName());
                }
            }
        }
        currentReturnType = resolveType(node.getReturnType(), node.getLine(), node.getColumn());
        if (node.getBody() != null) {
            node.getBody().accept(this);
        }
        currentReturnType = null;
        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Type visit(FunctionBody node) {
        if (node.getLocalVariables() != null) {
            node.getLocalVariables().accept(this);
        }
        if (node.getBody() != null) {
            node.getBody().accept(this);
        }
        return null;
    }

    @Override
    public Type visit(LocalVariableSection node) {
        for (Declaration d : node.getDeclarations()) {
            d.accept(this);
        }
        return null;
    }

    @Override
    public Type visit(VariableDeclaration node) {
        Type type = resolveType(node.getType(), node.getLine(), node.getColumn());
        Symbol s = new Symbol(node.getName(), SymbolKind.VARIABLE, type, null, false,
                context.getSymbolTable().getCurrentScope(), null, node.getLine(), node.getColumn());
        if (!context.getSymbolTable().defineUnique(s)) {
            error(node.getLine(), node.getColumn(), "Variable duplicada: " + node.getName());
        }
        if (node.getInitializer() != null) {
            checkInitializer(node.getInitializer(), type, node.getLine(), node.getColumn());
        }
        return null;
    }

    @Override
    public Type visit(ArrayDeclaration node) {
        Type base = resolveType(node.getType(), node.getLine(), node.getColumn());
        int rank = node.getSizes() == null ? 0 : node.getSizes().size();
        Type type = Type.array(base, rank);
        Symbol s = new Symbol(node.getName(), SymbolKind.VARIABLE, type, null, false,
                context.getSymbolTable().getCurrentScope(), null, node.getLine(), node.getColumn());
        if (!context.getSymbolTable().defineUnique(s)) {
            error(node.getLine(), node.getColumn(), "Variable duplicada: " + node.getName());
        }
        if (node.getInitializer() != null) {
            for (AstLatNode el : node.getInitializer().getElements()) {
                Type actual = el instanceof Expression e ? e.accept(this) : Type.ERROR;
                if (!TypeCompat.canAssign(base, actual)) {
                    error(el.getLine(), el.getColumn(), "Elemento de arreglo " + actual + " no asignable a " + base);
                }
            }
        }
        return null;
    }

    // ---------------------------------------------------------------- statements

    @Override
    public Type visit(BlockStatement node) {
        context.getSymbolTable().enterScope(ScopeKind.BLOCK);
        for (Statement s : node.getStatements()) {
            s.accept(this);
        }
        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Type visit(AssignmentStatement node) {
        Type targetType = node.getTarget().accept(this);
        Type valueType = initializerType(node.getInitializer());
        if (!TypeCompat.canAssign(targetType, valueType)) {
            error(node.getLine(), node.getColumn(), "No se puede asignar " + valueType + " a " + targetType);
        }
        return null;
    }

    @Override
    public Type visit(IfStatement node) {
        requireBool(node.getCondition().accept(this), node.getLine(), node.getColumn());
        if (node.getThenBlock() != null) node.getThenBlock().accept(this);
        if (node.getElseIfStatements() != null) {
            for (ElseIfStatement e : node.getElseIfStatements()) e.accept(this);
        }
        if (node.getElseBlock() != null) node.getElseBlock().accept(this);
        return null;
    }

    @Override
    public Type visit(ElseIfStatement node) {
        requireBool(node.getCondition().accept(this), node.getLine(), node.getColumn());
        if (node.getBlock() != null) node.getBlock().accept(this);
        return null;
    }

    @Override
    public Type visit(WhileStatement node) {
        requireBool(node.getCondition().accept(this), node.getLine(), node.getColumn());
        context.getSymbolTable().enterScope(ScopeKind.LOOP);
        if (node.getBlock() != null) node.getBlock().accept(this);
        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Type visit(DoWhileStatement node) {
        context.getSymbolTable().enterScope(ScopeKind.LOOP);
        if (node.getBlock() != null) node.getBlock().accept(this);
        requireBool(node.getCondition().accept(this), node.getLine(), node.getColumn());
        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Type visit(ForStatement node) {
        context.getSymbolTable().enterScope(ScopeKind.LOOP);
        if (node.getInitializer() != null) node.getInitializer().accept(this);
        if (node.getCondition() != null) requireBool(node.getCondition().accept(this), node.getLine(), node.getColumn());
        if (node.getUpdate() != null) node.getUpdate().accept(this);
        if (node.getBlock() != null) node.getBlock().accept(this);
        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Type visit(ReturnStatement node) {
        if (node.getExpression() == null) {
            if (currentReturnType != null && !currentReturnType.isVoid()) {
                error(node.getLine(), node.getColumn(), "Se esperaba un valor de retorno de tipo " + currentReturnType);
            }
        } else {
            Type t = node.getExpression().accept(this);
            if (currentReturnType == null || currentReturnType.isVoid()) {
                error(node.getLine(), node.getColumn(), "La función no retorna valor");
            } else if (!TypeCompat.canAssign(currentReturnType, t)) {
                error(node.getLine(), node.getColumn(), "Tipo de retorno " + t + " no asignable a " + currentReturnType);
            }
        }
        return null;
    }

    @Override
    public Type visit(IncrementStatement node) {
        Type t = node.getTarget().accept(this);
        if (!TypeCompat.isNumeric(t)) {
            error(node.getLine(), node.getColumn(), "El operador ++/-- requiere un operando numérico");
        }
        return null;
    }

    @Override
    public Type visit(PrintStatement node) {
        for (Expression e : node.getExpressions()) {
            e.accept(this);
        }
        return null;
    }

    @Override
    public Type visit(ReadStatement node) {
        if (node.getTarget() != null) node.getTarget().accept(this);
        return null;
    }

    @Override
    public Type visit(FunctionCallStatement node) {
        resolveCall(node.getCallee(), node.getArguments(), node.getLine(), node.getColumn());
        return null;
    }

    @Override public Type visit(BreakStatement node) { return null; }
    @Override public Type visit(ContinueStatement node) { return null; }
    @Override public Type visit(GlobalVariableSection node) { return null; }
    @Override public Type visit(ImportDeclaration node) { return null; }

    // ---------------------------------------------------------------- expressions

    @Override
    public Type visit(BinaryExpression node) {
        Type l = node.getLeft().accept(this);
        Type r = node.getRight().accept(this);
        Type result = switch (node.getOperator()) {
            case AND, OR -> requireBoolOp(l, r, node.getLine(), node.getColumn());
            case EQUAL, NOT_EQUAL -> TypeCompat.comparable(l, r) ? Type.BOOLEAN
                    : fail(node.getLine(), node.getColumn(), "Tipos no comparables: " + l + " y " + r);
            case LESS, LESS_EQUAL, GREATER, GREATER_EQUAL -> {
                if (TypeCompat.isNumeric(l) && TypeCompat.isNumeric(r)) yield Type.BOOLEAN;
                yield fail(node.getLine(), node.getColumn(), "La comparación requiere operandos numéricos");
            }
            case ADD -> add(l, r, node.getLine(), node.getColumn());
            case SUBTRACT, MULTIPLY, DIVIDE -> numeric(l, r, node.getLine(), node.getColumn());
        };
        context.annotate(node, result);
        return result;
    }

    @Override
    public Type visit(UnaryExpression node) {
        Type t = node.getExpression().accept(this);
        Type result = switch (node.getOperator()) {
            case NOT -> t.isBoolean() ? Type.BOOLEAN
                    : fail(node.getLine(), node.getColumn(), "La negación ! requiere un booleano");
            case NEGATE -> TypeCompat.isNumeric(t) ? t
                    : fail(node.getLine(), node.getColumn(), "El operador - requiere un operando numérico");
            case POST_INCREMENT, POST_DECREMENT -> TypeCompat.isNumeric(t) ? t
                    : fail(node.getLine(), node.getColumn(), "++/-- requiere un operando numérico");
        };
        context.annotate(node, result);
        return result;
    }

    @Override
    public Type visit(VariableExpression node) {
        Symbol s = context.getSymbolTable().resolve(node.getName());
        if (s == null) {
            error(node.getLine(), node.getColumn(), "Identificador no definido: " + node.getName());
            context.annotate(node, Type.ERROR);
            return Type.ERROR;
        }
        context.annotate(node, s.getType());
        return s.getType();
    }

    @Override
    public Type visit(ArrayAccessExpression node) {
        Type arr = node.getArray().accept(this);
        Type idx = node.getIndex().accept(this);
        if (!TypeCompat.isNumeric(idx)) {
            error(node.getLine(), node.getColumn(), "El índice de un arreglo debe ser numérico");
        }
        Type result;
        if (arr.isArray()) {
            result = arr.getElementType();
        } else {
            error(node.getLine(), node.getColumn(), "Acceso por índice a un valor no-arreglo: " + arr);
            result = Type.ERROR;
        }
        context.annotate(node, result);
        return result;
    }

    @Override
    public Type visit(MemberAccessExpression node) {
        Type objType = node.getObject().accept(this);
        Type result;
        if (objType.isStruct() || objType.isClass()) {
            AggregateType agg = context.getTypeTable().resolve(objType.getName());
            Field field = agg == null ? null : agg.findField(node.getMember());
            if (field == null) {
                error(node.getLine(), node.getColumn(), "Campo '" + node.getMember() + "' no existe en " + objType);
                result = Type.ERROR;
            } else {
                result = field.getType();
            }
        } else {
            error(node.getLine(), node.getColumn(), "Acceso a miembro en un tipo no agregado: " + objType);
            result = Type.ERROR;
        }
        context.annotate(node, result);
        return result;
    }

    @Override
    public Type visit(FunctionCallExpression node) {
        Type result = resolveCall(node.getCallee(), node.getArguments(), node.getLine(), node.getColumn());
        context.annotate(node, result);
        return result;
    }

    @Override
    public Type visit(ObjectCreationExpression node) {
        List<Type> argTypes = new ArrayList<>();
        if (node.getArguments() != null) {
            for (Expression a : node.getArguments()) argTypes.add(a.accept(this));
        }
        AggregateType agg = context.getTypeTable().resolve(node.getType());
        Type result;
        if (agg == null) {
            error(node.getLine(), node.getColumn(), "Clase no definida: " + node.getType());
            result = Type.ERROR;
        } else if (!agg.isClass()) {
            error(node.getLine(), node.getColumn(), "novus requiere una clase, no una estructura: " + node.getType());
            result = Type.ERROR;
        } else {
            if (agg.findConstructor(argTypes) == null) {
                error(node.getLine(), node.getColumn(), "No existe un constructor de " + node.getType()
                        + " para los argumentos " + argTypes);
            }
            result = Type.classType(node.getType());
        }
        context.annotate(node, result);
        return result;
    }

    @Override public Type visit(BooleanLiteral node) { context.annotate(node, Type.BOOLEAN); return Type.BOOLEAN; }
    @Override public Type visit(CharLiteral node) { context.annotate(node, Type.CHAR); return Type.CHAR; }
    @Override public Type visit(DecimalLiteral node) { context.annotate(node, Type.DOUBLE); return Type.DOUBLE; }
    @Override public Type visit(NumberLiteral node) { context.annotate(node, Type.INT); return Type.INT; }
    @Override public Type visit(StringLiteral node) { context.annotate(node, Type.STRING); return Type.STRING; }

    @Override public Type visit(ArrayInitializer node) { return Type.ERROR; }
    @Override public Type visit(ExpressionInitializer node) { return Type.ERROR; }
    @Override public Type visit(StructInitializer node) { return Type.ERROR; }
    @Override public Type visit(StructFieldInitializer node) { return Type.ERROR; }
    @Override public Type visit(Parameter node) { return null; }

    // ---------------------------------------------------------------- helpers

    private Type resolveCall(Expression callee, List<Expression> args, int line, int col) {
        List<Type> argTypes = new ArrayList<>();
        if (args != null) {
            for (Expression a : args) argTypes.add(a.accept(this));
        }
        if (callee instanceof VariableExpression ve) {
            List<Symbol> overloads = context.getSymbolTable().resolveCallable(ve.getName());
            if (overloads == null) {
                error(line, col, "Función no definida: " + ve.getName());
                return Type.ERROR;
            }
            for (Symbol fn : overloads) {
                if (fn.getKind() == SymbolKind.FUNCTION && fn.getSignature().matches(argTypes)) {
                    return fn.getSignature().getReturnType();
                }
            }
            error(line, col, "No hay una función '" + ve.getName() + "' para los argumentos " + argTypes);
            return Type.ERROR;
        }
        if (callee instanceof MemberAccessExpression ma) {
            Type objType = ma.getObject().accept(this);
            return resolveMethod(objType, ma.getMember(), argTypes, line, col);
        }
        error(line, col, "Expresión no invocable");
        return Type.ERROR;
    }

    private Type resolveMethod(Type objType, String name, List<Type> argTypes, int line, int col) {
        if (!objType.isClass()) {
            error(line, col, "El tipo " + objType + " no tiene métodos");
            return Type.ERROR;
        }
        AggregateType agg = context.getTypeTable().resolve(objType.getName());
        if (agg == null) {
            error(line, col, "Clase no definida: " + objType.getName());
            return Type.ERROR;
        }
        Symbol m = agg.findMethod(name, argTypes);
        if (m == null) {
            error(line, col, "No existe el método '" + name + "' en " + objType + " para " + argTypes);
            return Type.ERROR;
        }
        return m.getSignature().getReturnType();
    }

    private Type initializerType(Initializer init) {
        if (init instanceof ExpressionInitializer ei) return ei.getExpression().accept(this);
        if (init instanceof StructInitializer si) return structLiteralType(si);
        return Type.ERROR;
    }

    private void checkInitializer(Initializer init, Type expected, int line, int col) {
        Type actual = initializerType(init);
        if (!TypeCompat.canAssign(expected, actual)) {
            error(line, col, "No se puede inicializar " + expected + " con " + actual);
        }
    }

    private Type structLiteralType(StructInitializer si) {
        // Sin tipo explícito no podemos resolver el struct; se valida por campos contra el tipo esperado.
        for (StructFieldInitializer f : si.getFields()) {
            if (f.getValue() instanceof ExpressionInitializer ei) ei.getExpression().accept(this);
        }
        return Type.ERROR;
    }

    private Type add(Type l, Type r, int line, int col) {
        if (l.isString() || r.isString()) return Type.STRING;
        return numeric(l, r, line, col);
    }

    private Type numeric(Type l, Type r, int line, int col) {
        Type promoted = TypeCompat.promoteNumeric(l, r);
        if (promoted == null) {
            return fail(line, col, "Operación aritmética con operandos no numéricos: " + l + " y " + r);
        }
        return promoted;
    }

    private Type requireBoolOp(Type l, Type r, int line, int col) {
        if (l.isBoolean() && r.isBoolean()) return Type.BOOLEAN;
        return fail(line, col, "El operador lógico requiere booleanos: " + l + " y " + r);
    }

    private void requireBool(Type t, int line, int col) {
        if (!t.isBoolean()) error(line, col, "La condición debe ser booleana, se obtuvo " + t);
    }

    private Type fail(int line, int col, String msg) {
        error(line, col, msg);
        return Type.ERROR;
    }

    private void error(int line, int col, String msg) {
        context.getErrors().report(line, col, msg);
    }

    private Type resolveType(String name, int line, int col) {
        if (name == null) return Type.VOID;
        String value = name.toLowerCase(Locale.ROOT);
        return switch (value) {
            case "numerus" -> Type.INT;
            case "decimalis" -> Type.DOUBLE;
            case "textum" -> Type.STRING;
            case "littera" -> Type.CHAR;
            case "bool", "boolean" -> Type.BOOLEAN;
            case "void" -> Type.VOID;
            default -> {
                AggregateType agg = context.getTypeTable().resolve(name);
                if (agg == null) {
                    error(line, col, "Tipo no definido: " + name);
                    yield Type.ERROR;
                }
                yield agg.isClass() ? Type.classType(name) : Type.struct(name);
            }
        };
    }
}
