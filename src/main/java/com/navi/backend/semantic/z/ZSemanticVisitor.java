package com.navi.backend.semantic.z;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.declarations.ArrayDimensions;
import com.navi.backend.ast.z.declarations.ArrayInitializer;
import com.navi.backend.ast.z.declarations.ClassDeclaration;
import com.navi.backend.ast.z.declarations.ClassMember;
import com.navi.backend.ast.z.declarations.ConstructorDeclaration;
import com.navi.backend.ast.z.declarations.ExpressionInitializer;
import com.navi.backend.ast.z.declarations.FieldDeclaration;
import com.navi.backend.ast.z.declarations.Initializer;
import com.navi.backend.ast.z.declarations.MethodDeclaration;
import com.navi.backend.ast.z.declarations.Parameter;
import com.navi.backend.ast.z.declarations.VariableDeclaration;
import com.navi.backend.ast.z.declarations.VariableDeclarator;
import com.navi.backend.ast.z.declarations.ZType;
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
import com.navi.backend.ast.z.global.ProgramZ;
import com.navi.backend.ast.z.statements.AssignmentOperator;
import com.navi.backend.ast.z.statements.BlockStatement;
import com.navi.backend.ast.z.statements.BreakStatement;
import com.navi.backend.ast.z.statements.ContinueStatement;
import com.navi.backend.ast.z.statements.DefaultCase;
import com.navi.backend.ast.z.statements.DoWhileStatement;
import com.navi.backend.ast.z.statements.ElseClause;
import com.navi.backend.ast.z.statements.ElseIfClause;
import com.navi.backend.ast.z.statements.ExpressionStatement;
import com.navi.backend.ast.z.statements.ForStatement;
import com.navi.backend.ast.z.statements.IfStatement;
import com.navi.backend.ast.z.statements.PrintStatement;
import com.navi.backend.ast.z.statements.PrintlnStatement;
import com.navi.backend.ast.z.statements.ReadlnStatement;
import com.navi.backend.ast.z.statements.ReturnStatement;
import com.navi.backend.ast.z.statements.Statement;
import com.navi.backend.ast.z.statements.SwitchCase;
import com.navi.backend.ast.z.statements.SwitchStatement;
import com.navi.backend.ast.z.statements.VariableDeclarationStatement;
import com.navi.backend.ast.z.statements.WhileStatement;
import com.navi.backend.ast.z.visitors.AstZVisitor;
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

/**
 * Pasada semántica de Z. Valida tipos y anota expresiones con su {@link Type}.
 * Mantiene la clase actual ({@code currentClass}) para resolver campos/métodos
 * sin calificar dentro de los cuerpos.
 */
public class ZSemanticVisitor implements AstZVisitor<Type> {

    private final SemanticContext context;
    private AggregateType currentClass;
    private Type currentReturnType;

    public ZSemanticVisitor(SemanticContext context) {
        this.context = context;
    }

    public void build(ProgramZ program) {
        program.accept(this);
    }

    @Override
    public Type visit(ProgramZ node) {
        if (node.getClassDeclaration() != null) {
            node.getClassDeclaration().accept(this);
        }
        return null;
    }

    @Override
    public Type visit(ClassDeclaration node) {
        currentClass = context.getTypeTable().resolve(node.getName());
        if (node.getMembers() != null) {
            for (ClassMember member : node.getMembers()) {
                member.accept(this);
            }
        }
        currentClass = null;
        return null;
    }

    @Override
    public Type visit(FieldDeclaration node) {
        Type type = resolveType(node.getType());
        for (VariableDeclarator v : node.getVariables()) {
            if (v.getInitializer() != null) {
                checkInitializer(v.getInitializer(), type, v.getLine(), v.getColumn());
            }
        }
        return null;
    }

    @Override
    public Type visit(MethodDeclaration node) {
        context.getSymbolTable().enterScope(ScopeKind.FUNCTION);
        defineParams(node.getParameters());
        currentReturnType = node.getReturnType() == null ? Type.VOID : resolveType(node.getReturnType());
        node.getBody().accept(this);
        currentReturnType = null;
        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Type visit(ConstructorDeclaration node) {
        context.getSymbolTable().enterScope(ScopeKind.FUNCTION);
        defineParams(node.getParameters());
        currentReturnType = Type.VOID;
        node.getBody().accept(this);
        currentReturnType = null;
        context.getSymbolTable().exitScope();
        return null;
    }

    private void defineParams(List<Parameter> parameters) {
        if (parameters == null) return;
        for (Parameter p : parameters) {
            Type t = resolveType(p.getType());
            Symbol s = new Symbol(p.getName(), SymbolKind.PARAMETER, t, null, false,
                    context.getSymbolTable().getCurrentScope(), null, p.getLine(), p.getColumn());
            if (!context.getSymbolTable().defineUnique(s)) {
                context.getErrors().report(p.getLine(), p.getColumn(), "Parámetro duplicado: " + p.getName());
            }
            context.bindSymbol(p, s);
        }
    }

    // ---------------------------------------------------------------- statements

    @Override
    public Type visit(BlockStatement node) {
        context.getSymbolTable().enterScope(ScopeKind.BLOCK);
        for (Statement s : node.getStatements()) s.accept(this);
        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Type visit(VariableDeclarationStatement node) {
        node.getDeclaration().accept(this);
        return null;
    }

    @Override
    public Type visit(VariableDeclaration node) {
        Type type = resolveType(node.getType());
        for (VariableDeclarator v : node.getVariables()) {
            Symbol s = new Symbol(v.getName(), SymbolKind.VARIABLE, type, null, false,
                    context.getSymbolTable().getCurrentScope(), null, v.getLine(), v.getColumn());
            if (!context.getSymbolTable().defineUnique(s)) {
                context.getErrors().report(v.getLine(), v.getColumn(), "Variable duplicada: " + v.getName());
            }
            context.bindSymbol(v, s);
            if (v.getInitializer() != null) {
                checkInitializer(v.getInitializer(), type, v.getLine(), v.getColumn());
            }
        }
        return null;
    }

    @Override
    public Type visit(ExpressionStatement node) {
        node.getExpression().accept(this);
        return null;
    }

    @Override
    public Type visit(IfStatement node) {
        requireBool(node.getCondition().accept(this), node.getLine(), node.getColumn());
        node.getThenBranch().accept(this);
        if (node.getElseIfClauses() != null) for (ElseIfClause c : node.getElseIfClauses()) c.accept(this);
        if (node.getElseBranch() != null) node.getElseBranch().accept(this);
        return null;
    }

    @Override
    public Type visit(ElseIfClause node) {
        requireBool(node.getCondition().accept(this), node.getLine(), node.getColumn());
        node.getBranch().accept(this);
        return null;
    }

    @Override
    public Type visit(ElseClause node) {
        node.getBranch().accept(this);
        return null;
    }

    @Override
    public Type visit(WhileStatement node) {
        requireBool(node.getCondition().accept(this), node.getLine(), node.getColumn());
        context.getSymbolTable().enterScope(ScopeKind.LOOP);
        node.getBody().accept(this);
        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Type visit(DoWhileStatement node) {
        context.getSymbolTable().enterScope(ScopeKind.LOOP);
        node.getBody().accept(this);
        requireBool(node.getCondition().accept(this), node.getLine(), node.getColumn());
        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Type visit(ForStatement node) {
        context.getSymbolTable().enterScope(ScopeKind.LOOP);
        if (node.getInitializer() != null) node.getInitializer().accept(this);
        if (node.getCondition() != null) {
            Type c = node.getCondition() instanceof Expression e ? e.accept(this) : Type.ERROR;
            requireBool(c, node.getLine(), node.getColumn());
        }
        if (node.getUpdate() != null) node.getUpdate().accept(this);
        if (node.getBlock() != null) node.getBlock().accept(this);
        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Type visit(SwitchStatement node) {
        node.getExpression().accept(this);
        if (node.getCases() != null) for (SwitchCase c : node.getCases()) c.accept(this);
        if (node.getDefaultCase() != null) node.getDefaultCase().accept(this);
        return null;
    }

    @Override
    public Type visit(SwitchCase node) {
        node.getExpression().accept(this);
        for (Statement s : node.getStatements()) s.accept(this);
        return null;
    }

    @Override
    public Type visit(DefaultCase node) {
        for (Statement s : node.getStatements()) s.accept(this);
        return null;
    }

    @Override
    public Type visit(ReturnStatement node) {
        if (node.getExpression() == null) {
            if (currentReturnType != null && !currentReturnType.isVoid()) {
                context.getErrors().report(node.getLine(), node.getColumn(),
                        "Se esperaba un valor de retorno de tipo " + currentReturnType);
            }
        } else {
            Type t = node.getExpression().accept(this);
            if (currentReturnType == null || currentReturnType.isVoid()) {
                context.getErrors().report(node.getLine(), node.getColumn(), "El método no retorna valor");
            } else if (!TypeCompat.canAssign(currentReturnType, t)) {
                context.getErrors().report(node.getLine(), node.getColumn(),
                        "Tipo de retorno " + t + " no asignable a " + currentReturnType);
            }
        }
        return null;
    }

    @Override public Type visit(BreakStatement node) { return null; }
    @Override public Type visit(ContinueStatement node) { return null; }
    @Override public Type visit(PrintStatement node) { for (Expression e : node.getArguments()) e.accept(this); return null; }
    @Override public Type visit(PrintlnStatement node) { for (Expression e : node.getArguments()) e.accept(this); return null; }
    @Override public Type visit(ReadlnStatement node) { return null; }

    @Override public Type visit(ClassMember node) { return null; }
    @Override public Type visit(Parameter node) { return null; }
    @Override public Type visit(ZType node) { return null; }
    @Override public Type visit(ArrayDimensions node) { return null; }
    @Override public Type visit(VariableDeclarator node) { return null; }
    @Override public Type visit(Initializer node) { return null; }
    @Override public Type visit(ExpressionInitializer node) { return null; }
    @Override public Type visit(ArrayInitializer node) { return null; }

    // ---------------------------------------------------------------- expresiones

    @Override
    public Type visit(BinaryExpression node) {
        Type l = node.getLeft().accept(this);
        Type r = node.getRight().accept(this);
        Type result = switch (node.getOperator()) {
            case AND, OR -> boolOp(l, r, node.getLine(), node.getColumn());
            case EQUAL, NOT_EQUAL -> TypeCompat.comparable(l, r) ? Type.BOOLEAN
                    : fail(node.getLine(), node.getColumn(), "Tipos no comparables: " + l + " y " + r);
            case LESS, LESS_EQUAL, GREATER, GREATER_EQUAL -> numericCmp(l, r, node.getLine(), node.getColumn());
            case ADD -> add(l, r, node.getLine(), node.getColumn());
            case SUBTRACT, MULTIPLY, DIVIDE, MODULO -> numeric(l, r, node.getLine(), node.getColumn());
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
            case NEGATE, POSITIVE -> TypeCompat.isNumeric(t) ? t
                    : fail(node.getLine(), node.getColumn(), "Operador unario requiere un operando numérico");
            case POST_INCREMENT, POST_DECREMENT -> TypeCompat.isNumeric(t) ? t
                    : fail(node.getLine(), node.getColumn(), "++/-- requiere un operando numérico");
        };
        context.annotate(node, result);
        return result;
    }

    @Override
    public Type visit(AssignmentExpression node) {
        Type target = node.getTarget().accept(this);
        Type value = node.getValue().accept(this);
        if (node.getOperator() == AssignmentOperator.ASSIGN) {
            if (!TypeCompat.canAssign(target, value)) {
                context.getErrors().report(node.getLine(), node.getColumn(),
                        "No se puede asignar " + value + " a " + target);
            }
        } else if (!TypeCompat.isNumeric(target) || !TypeCompat.isNumeric(value)) {
            context.getErrors().report(node.getLine(), node.getColumn(),
                    "La asignación compuesta requiere operandos numéricos");
        }
        context.annotate(node, value);
        return value;
    }

    @Override
    public Type visit(TernaryExpression node) {
        requireBool(node.getCondition().accept(this), node.getLine(), node.getColumn());
        Type t = node.getThenExpression().accept(this);
        Type e = node.getElseExpression().accept(this);
        Type result;
        if (TypeCompat.canAssign(t, e)) result = t;
        else if (TypeCompat.canAssign(e, t)) result = e;
        else {
            context.getErrors().report(node.getLine(), node.getColumn(), "Ramas del ternario incompatibles: " + t + " y " + e);
            result = Type.ERROR;
        }
        context.annotate(node, result);
        return result;
    }

    @Override
    public Type visit(VariableExpression node) {
        Type t = resolveVariable(node.getName(), node.getLine(), node.getColumn());
        context.annotate(node, t);
        return t;
    }

    @Override
    public Type visit(ArrayAccessExpression node) {
        Type arr = node.getArray().accept(this);
        Type idx = node.getIndex().accept(this);
        if (!TypeCompat.isNumeric(idx)) {
            context.getErrors().report(node.getLine(), node.getColumn(), "El índice de un arreglo debe ser numérico");
        }
        Type result;
        if (arr.isArray()) {
            result = arr.getElementType();
        } else {
            context.getErrors().report(node.getLine(), node.getColumn(), "Acceso por índice a un no-arreglo: " + arr);
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
                context.getErrors().report(node.getLine(), node.getColumn(),
                        "Campo '" + node.getMember() + "' no existe en " + objType);
                result = Type.ERROR;
            } else {
                result = field.getType();
            }
        } else {
            context.getErrors().report(node.getLine(), node.getColumn(),
                    "Acceso a miembro en un tipo no agregado: " + objType);
            result = Type.ERROR;
        }
        context.annotate(node, result);
        return result;
    }

    @Override
    public Type visit(FunctionCallExpression node) {
        List<Type> argTypes = new ArrayList<>();
        if (node.getArguments() != null) {
            for (Expression a : node.getArguments()) argTypes.add(a.accept(this));
        }
        Type result = Type.ERROR;
        if (node.getFunction() instanceof VariableExpression ve) {
            Symbol m = currentClass == null ? null : currentClass.findMethod(ve.getName(), argTypes);
            if (m == null) {
                context.getErrors().report(node.getLine(), node.getColumn(),
                        "Método no definido: '" + ve.getName() + "' para " + argTypes);
            } else {
                result = m.getSignature().getReturnType();
            }
        } else if (node.getFunction() instanceof MemberAccessExpression ma) {
            Type objType = ma.getObject().accept(this);
            if (!objType.isClass()) {
                context.getErrors().report(node.getLine(), node.getColumn(), "El tipo " + objType + " no tiene métodos");
            } else {
                AggregateType agg = context.getTypeTable().resolve(objType.getName());
                Symbol m = agg == null ? null : agg.findMethod(ma.getMember(), argTypes);
                if (m == null) {
                    context.getErrors().report(node.getLine(), node.getColumn(),
                            "Método '" + ma.getMember() + "' no definido en " + objType + " para " + argTypes);
                } else {
                    result = m.getSignature().getReturnType();
                }
            }
        } else {
            context.getErrors().report(node.getLine(), node.getColumn(), "Expresión no invocable");
        }
        context.annotate(node, result);
        return result;
    }

    @Override
    public Type visit(ObjectCreationExpression node) {
        List<Type> argTypes = new ArrayList<>();
        if (node.getArguments() != null) {
            for (Expression a : node.getArguments()) argTypes.add(a.accept(this));
        }
        AggregateType agg = context.getTypeTable().resolve(node.getTypeName());
        Type result;
        if (agg == null) {
            context.getErrors().report(node.getLine(), node.getColumn(), "Clase no definida: " + node.getTypeName());
            result = Type.ERROR;
        } else {
            if (agg.findConstructor(argTypes) == null) {
                context.getErrors().report(node.getLine(), node.getColumn(),
                        "No existe un constructor de " + node.getTypeName() + " para " + argTypes);
            }
            result = Type.classType(node.getTypeName());
        }
        context.annotate(node, result);
        return result;
    }

    @Override
    public Type visit(ArrayCreationExpression node) {
        Type base = resolveType(node.getType());
        int rank = node.getDimensions() == null ? 0 : node.getDimensions().size();
        Type result = rank > 0 ? Type.array(base, rank) : base;
        if (node.getDimensions() != null) {
            for (Expression d : node.getDimensions()) d.accept(this);
        }
        context.annotate(node, result);
        return result;
    }

    @Override
    public Type visit(NullExpression node) {
        context.annotate(node, Type.NULL);
        return Type.NULL;
    }

    @Override
    public Type visit(ReadExpression node) {
        context.annotate(node, Type.STRING);
        return Type.STRING;
    }

    @Override
    public Type visit(ExpressionList node) {
        Type last = Type.ERROR;
        for (Expression e : node.getExpressions()) last = e.accept(this);
        return last;
    }

    @Override
    public Type visit(LiteralExpression node) {
        Type t = literalType(node.getValue());
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
        context.getErrors().report(line, col, "Identificador no definido: " + name);
        return Type.ERROR;
    }

    private void checkInitializer(Initializer init, Type expected, int line, int col) {
        if (init instanceof ExpressionInitializer ei) {
            Type actual = ei.getExpression().accept(this);
            if (!TypeCompat.canAssign(expected, actual)) {
                context.getErrors().report(line, col, "No se puede inicializar " + expected + " con " + actual);
            }
        } else if (init instanceof ArrayInitializer ai) {
            Type base = expected.isArray() ? expected.getElementType() : Type.ERROR;
            for (AstZNode el : ai.getElements()) {
                Type actual = el instanceof Expression e ? e.accept(this) : Type.ERROR;
                if (!TypeCompat.canAssign(base, actual)) {
                    context.getErrors().report(line, col, "Elemento " + actual + " no asignable a " + base);
                }
            }
        }
    }

    private Type literalType(Object value) {
        if (value instanceof Integer) return Type.INT;
        if (value instanceof Double || value instanceof Float) return Type.DOUBLE;
        if (value instanceof Character) return Type.CHAR;
        if (value instanceof String) return Type.STRING;
        if (value instanceof Boolean) return Type.BOOLEAN;
        return Type.ERROR;
    }

    private Type boolOp(Type l, Type r, int line, int col) {
        if (l.isBoolean() && r.isBoolean()) return Type.BOOLEAN;
        return fail(line, col, "El operador lógico requiere booleanos: " + l + " y " + r);
    }

    private Type numericCmp(Type l, Type r, int line, int col) {
        if (TypeCompat.isNumeric(l) && TypeCompat.isNumeric(r)) return Type.BOOLEAN;
        return fail(line, col, "La comparación requiere operandos numéricos");
    }

    private Type add(Type l, Type r, int line, int col) {
        if (l.isString() || r.isString()) return Type.STRING;
        return numeric(l, r, line, col);
    }

    private Type numeric(Type l, Type r, int line, int col) {
        Type promoted = TypeCompat.promoteNumeric(l, r);
        if (promoted == null) return fail(line, col, "Operación aritmética con operandos no numéricos: " + l + " y " + r);
        return promoted;
    }

    private void requireBool(Type t, int line, int col) {
        if (!t.isBoolean()) context.getErrors().report(line, col, "La condición debe ser booleana, se obtuvo " + t);
    }

    private Type fail(int line, int col, String msg) {
        context.getErrors().report(line, col, msg);
        return Type.ERROR;
    }

    private Type resolveType(ZType node) {
        String name = node.getName();
        Type base = switch (name) {
            case "int" -> Type.INT;
            case "double" -> Type.DOUBLE;
            case "char" -> Type.CHAR;
            case "boolean" -> Type.BOOLEAN;
            case "String" -> Type.STRING;
            case "void" -> Type.VOID;
            default -> {
                AggregateType agg = context.getTypeTable().resolve(name);
                if (agg == null) {
                    context.getErrors().report(node.getLine(), node.getColumn(), "Tipo no definido: " + name);
                    yield Type.ERROR;
                }
                yield Type.classType(name);
            }
        };
        if (node.getArrayDimensions() != null && node.getArrayDimensions().getDimensions() > 0) {
            return Type.array(base, node.getArrayDimensions().getDimensions());
        }
        return base;
    }
}
