package com.navi.backend.semantic.y;

import com.navi.backend.ast.y.declarations.ArrayDeclaration;
import com.navi.backend.ast.y.declarations.ArrayDimensions;
import com.navi.backend.ast.y.declarations.ArrayInitializer;
import com.navi.backend.ast.y.declarations.ArrayParameter;
import com.navi.backend.ast.y.declarations.ExpressionInitializer;
import com.navi.backend.ast.y.declarations.FunctionDeclaration;
import com.navi.backend.ast.y.declarations.Initializer;
import com.navi.backend.ast.y.declarations.NormalParameter;
import com.navi.backend.ast.y.declarations.Parameter;
import com.navi.backend.ast.y.declarations.StructureDeclaration;
import com.navi.backend.ast.y.declarations.StructureField;
import com.navi.backend.ast.y.declarations.StructureInitializer;
import com.navi.backend.ast.y.declarations.StructureParameter;
import com.navi.backend.ast.y.declarations.VariableDeclaration;
import com.navi.backend.ast.y.declarations.YType;
import com.navi.backend.ast.y.expressions.ArrayAccessExpression;
import com.navi.backend.ast.y.expressions.BinaryExpression;
import com.navi.backend.ast.y.expressions.Expression;
import com.navi.backend.ast.y.expressions.FunctionCallExpression;
import com.navi.backend.ast.y.expressions.MemberAccessExpression;
import com.navi.backend.ast.y.expressions.ReadExpression;
import com.navi.backend.ast.y.expressions.UnaryExpression;
import com.navi.backend.ast.y.expressions.VariableExpression;
import com.navi.backend.ast.y.expressions.literals.LiteralExpression;
import com.navi.backend.ast.y.global.ProgramY;
import com.navi.backend.ast.y.statements.AssignmentStatement;
import com.navi.backend.ast.y.statements.AssignmentOperator;
import com.navi.backend.ast.y.statements.BreakStatement;
import com.navi.backend.ast.y.statements.ContinueStatement;
import com.navi.backend.ast.y.statements.DefaultCase;
import com.navi.backend.ast.y.statements.DoWhileStatement;
import com.navi.backend.ast.y.statements.ElseClause;
import com.navi.backend.ast.y.statements.ElseIfClause;
import com.navi.backend.ast.y.statements.ExpressionStatement;
import com.navi.backend.ast.y.statements.ForStatement;
import com.navi.backend.ast.y.statements.IfStatement;
import com.navi.backend.ast.y.statements.IncrementStatement;
import com.navi.backend.ast.y.statements.PrintStatement;
import com.navi.backend.ast.y.statements.ReadStatement;
import com.navi.backend.ast.y.statements.ReturnStatement;
import com.navi.backend.ast.y.statements.Statement;
import com.navi.backend.ast.y.statements.SwitchCase;
import com.navi.backend.ast.y.statements.SwitchStatement;
import com.navi.backend.ast.y.statements.WhileStatement;
import com.navi.backend.ast.y.visitors.AstYVisitor;
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
 * Pasada semántica de Y. Valida tipos y anota expresiones con su {@link Type}.
 */
public class YSemanticVisitor implements AstYVisitor<Type> {

    private final SemanticContext context;
    private Type currentReturnType;

    public YSemanticVisitor(SemanticContext context) {
        this.context = context;
    }

    public void build(ProgramY program) {
        program.accept(this);
    }

    @Override
    public Type visit(ProgramY node) {
        if (node.getFunctions() != null) {
            for (FunctionDeclaration f : node.getFunctions()) {
                f.accept(this);
            }
        }
        return null;
    }

    @Override
    public Type visit(FunctionDeclaration node) {
        context.getSymbolTable().enterScope(ScopeKind.FUNCTION);
        if (node.getParameters() != null) {
            for (Parameter p : node.getParameters()) {
                defineParam(p);
            }
        }
        currentReturnType = node.getReturnType() == null ? Type.VOID : resolveType(node.getReturnType());
        if (node.getStatements() != null) {
            for (Statement s : node.getStatements()) s.accept(this);
        }
        currentReturnType = null;
        context.getSymbolTable().exitScope();
        return null;
    }

    private void defineParam(Parameter p) {
        Type type;
        boolean reference = false;
        String name;
        if (p instanceof NormalParameter n) {
            type = resolveType(n.getType());
            name = n.getName();
        } else if (p instanceof ArrayParameter a) {
            type = Type.array(resolveType(a.getType()), 1);
            reference = true;
            name = a.getName();
        } else if (p instanceof StructureParameter s) {
            AggregateType agg = context.getTypeTable().resolve(s.getStructureName());
            if (agg == null) {
                context.getErrors().report(s.getLine(), s.getColumn(), "Estructura no definida: " + s.getStructureName());
                type = Type.ERROR;
            } else {
                type = Type.struct(s.getStructureName());
            }
            reference = true;
            name = s.getName();
        } else {
            type = Type.ERROR;
            name = "";
        }
        Symbol sym = new Symbol(name, SymbolKind.PARAMETER, type, null, reference,
                context.getSymbolTable().getCurrentScope(), null, p.getLine(), p.getColumn());
        if (!context.getSymbolTable().defineUnique(sym)) {
            context.getErrors().report(p.getLine(), p.getColumn(), "Parámetro duplicado: " + name);
        }
    }

    @Override
    public Type visit(VariableDeclaration node) {
        Type base = resolveType(node.getType());
        int rank = node.getArrayDeclaration() == null ? 0 : node.getArrayDeclaration().getDimensions().size();
        Type type = rank > 0 ? Type.array(base, rank) : base;
        Symbol s = new Symbol(node.getName(), SymbolKind.VARIABLE, type, null, false,
                context.getSymbolTable().getCurrentScope(), null, node.getLine(), node.getColumn());
        if (!context.getSymbolTable().defineUnique(s)) {
            context.getErrors().report(node.getLine(), node.getColumn(), "Variable duplicada: " + node.getName());
        }
        if (node.getInitializer() != null) {
            checkInitializer(node.getInitializer(), type, node.getLine(), node.getColumn());
        }
        return null;
    }

    @Override
    public Type visit(AssignmentStatement node) {
        Type target = node.getTarget().accept(this);
        Type value = node.getValue().accept(this);
        if (node.getOperator() == AssignmentOperator.ASSIGN) {
            if (!TypeCompat.canAssign(target, value)) {
                context.getErrors().report(node.getLine(), node.getColumn(),
                        "No se puede asignar " + value + " a " + target);
            }
        } else {
            if (!TypeCompat.isNumeric(target) || !TypeCompat.isNumeric(value)) {
                context.getErrors().report(node.getLine(), node.getColumn(),
                        "La asignación compuesta requiere operandos numéricos");
            }
        }
        return null;
    }

    @Override
    public Type visit(IncrementStatement node) {
        Type t = node.getTarget().accept(this);
        if (!TypeCompat.isNumeric(t)) {
            context.getErrors().report(node.getLine(), node.getColumn(), "++/-- requiere un operando numérico");
        }
        return null;
    }

    @Override
    public Type visit(IfStatement node) {
        requireBool(node.getCondition().accept(this), node.getLine(), node.getColumn());
        context.getSymbolTable().enterScope(ScopeKind.BLOCK);
        for (Statement s : node.getStatements()) s.accept(this);
        context.getSymbolTable().exitScope();
        if (node.getElseIfClauses() != null) {
            for (ElseIfClause c : node.getElseIfClauses()) c.accept(this);
        }
        if (node.getElseClause() != null) node.getElseClause().accept(this);
        return null;
    }

    @Override
    public Type visit(ElseIfClause node) {
        requireBool(node.getCondition().accept(this), node.getLine(), node.getColumn());
        context.getSymbolTable().enterScope(ScopeKind.BLOCK);
        for (Statement s : node.getStatements()) s.accept(this);
        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Type visit(ElseClause node) {
        context.getSymbolTable().enterScope(ScopeKind.BLOCK);
        for (Statement s : node.getStatements()) s.accept(this);
        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Type visit(WhileStatement node) {
        requireBool(node.getCondition().accept(this), node.getLine(), node.getColumn());
        context.getSymbolTable().enterScope(ScopeKind.LOOP);
        for (Statement s : node.getStatements()) s.accept(this);
        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Type visit(DoWhileStatement node) {
        context.getSymbolTable().enterScope(ScopeKind.LOOP);
        for (Statement s : node.getStatements()) s.accept(this);
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
        for (Statement s : node.getStatements()) s.accept(this);
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
        context.getSymbolTable().enterScope(ScopeKind.BLOCK);
        for (Statement s : node.getStatements()) s.accept(this);
        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Type visit(DefaultCase node) {
        context.getSymbolTable().enterScope(ScopeKind.BLOCK);
        for (Statement s : node.getStatements()) s.accept(this);
        context.getSymbolTable().exitScope();
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
                context.getErrors().report(node.getLine(), node.getColumn(), "La función no retorna valor");
            } else if (!TypeCompat.canAssign(currentReturnType, t)) {
                context.getErrors().report(node.getLine(), node.getColumn(),
                        "Tipo de retorno " + t + " no asignable a " + currentReturnType);
            }
        }
        return null;
    }

    @Override
    public Type visit(PrintStatement node) {
        for (Expression e : node.getExpressions()) e.accept(this);
        return null;
    }

    @Override public Type visit(ReadStatement node) { return null; }
    @Override public Type visit(BreakStatement node) { return null; }
    @Override public Type visit(ContinueStatement node) { return null; }
    @Override public Type visit(ExpressionStatement node) { node.getExpression().accept(this); return null; }

    @Override public Type visit(StructureDeclaration node) { return null; }
    @Override public Type visit(StructureField node) { return null; }
    @Override public Type visit(ArrayDeclaration node) { return null; }
    @Override public Type visit(ArrayDimensions node) { return null; }
    @Override public Type visit(ArrayParameter node) { return null; }
    @Override public Type visit(NormalParameter node) { return null; }
    @Override public Type visit(StructureParameter node) { return null; }
    @Override public Type visit(YType node) { return null; }

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
            case NEGATE -> TypeCompat.isNumeric(t) ? t
                    : fail(node.getLine(), node.getColumn(), "- requiere un operando numérico");
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
            context.getErrors().report(node.getLine(), node.getColumn(), "Identificador no definido: " + node.getName());
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
        if (objType.isStruct()) {
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
                    "Acceso a miembro en un tipo no-estructura: " + objType);
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
            List<Symbol> overloads = context.getSymbolTable().resolveCallable(ve.getName());
            if (overloads == null) {
                context.getErrors().report(node.getLine(), node.getColumn(), "Función no definida: " + ve.getName());
            } else {
                Symbol match = null;
                for (Symbol fn : overloads) {
                    if (fn.getKind() == SymbolKind.FUNCTION && fn.getSignature().matches(argTypes)) {
                        match = fn;
                        break;
                    }
                }
                if (match == null) {
                    context.getErrors().report(node.getLine(), node.getColumn(),
                            "No hay una función '" + ve.getName() + "' para los argumentos " + argTypes);
                } else {
                    result = match.getSignature().getReturnType();
                }
            }
        } else {
            context.getErrors().report(node.getLine(), node.getColumn(), "Expresión no invocable");
        }
        context.annotate(node, result);
        return result;
    }

    @Override
    public Type visit(ReadExpression node) {
        context.annotate(node, Type.STRING);
        return Type.STRING;
    }

    @Override
    public Type visit(LiteralExpression node) {
        Type t = literalType(node.getValue());
        context.annotate(node, t);
        return t;
    }

    @Override public Type visit(ArrayInitializer node) { return Type.ERROR; }
    @Override public Type visit(ExpressionInitializer node) { return Type.ERROR; }
    @Override public Type visit(StructureInitializer node) { return Type.ERROR; }

    // ---------------------------------------------------------------- helpers

    private void checkInitializer(Initializer init, Type expected, int line, int col) {
        if (init instanceof ExpressionInitializer ei) {
            Type actual = ei.getExpression().accept(this);
            if (!TypeCompat.canAssign(expected, actual)) {
                context.getErrors().report(line, col, "No se puede inicializar " + expected + " con " + actual);
            }
        } else if (init instanceof ArrayInitializer ai) {
            Type base = expected.isArray() ? expected.getElementType() : Type.ERROR;
            for (var el : ai.getElements()) {
                Type actual = el instanceof Expression e ? e.accept(this) : Type.ERROR;
                if (!TypeCompat.canAssign(base, actual)) {
                    context.getErrors().report(line, col, "Elemento " + actual + " no asignable a " + base);
                }
            }
        } else if (init instanceof StructureInitializer si) {
            checkStructureLiteral(si.getExpressions(), expected, line, col);
        }
    }

    private void checkStructureLiteral(List<Expression> exprs, Type expected, int line, int col) {
        if (!expected.isStruct()) {
            context.getErrors().report(line, col, "Literal de estructura para un tipo no-estructura: " + expected);
            return;
        }
        AggregateType agg = context.getTypeTable().resolve(expected.getName());
        if (agg == null) return;
        List<Field> fields = agg.getFields();
        if (exprs.size() > fields.size()) {
            context.getErrors().report(line, col, "Demasiados valores para " + expected);
        }
        for (int i = 0; i < Math.min(exprs.size(), fields.size()); i++) {
            Type actual = exprs.get(i).accept(this);
            if (!TypeCompat.canAssign(fields.get(i).getType(), actual)) {
                context.getErrors().report(line, col,
                        "Campo '" + fields.get(i).getName() + "' espera " + fields.get(i).getType() + " pero recibe " + actual);
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

    private Type resolveType(YType node) {
        String value = node.getName().toLowerCase(Locale.ROOT);
        return switch (value) {
            case "entero" -> Type.INT;
            case "flotante" -> Type.DOUBLE;
            case "cadena" -> Type.STRING;
            case "caracter" -> Type.CHAR;
            case "bool" -> Type.BOOLEAN;
            default -> {
                AggregateType agg = context.getTypeTable().resolve(node.getName());
                if (agg == null) {
                    context.getErrors().report(node.getLine(), node.getColumn(), "Tipo no definido: " + node.getName());
                    yield Type.ERROR;
                }
                yield Type.struct(node.getName());
            }
        };
    }
}
