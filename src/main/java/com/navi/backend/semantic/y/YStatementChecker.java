package com.navi.backend.semantic.y;

import com.navi.backend.ast.y.declarations.ArrayInitializer;
import com.navi.backend.ast.y.declarations.ArrayParameter;
import com.navi.backend.ast.y.declarations.ExpressionInitializer;
import com.navi.backend.ast.y.declarations.FunctionDeclaration;
import com.navi.backend.ast.y.declarations.Initializer;
import com.navi.backend.ast.y.declarations.NormalParameter;
import com.navi.backend.ast.y.declarations.Parameter;
import com.navi.backend.ast.y.declarations.StructureInitializer;
import com.navi.backend.ast.y.declarations.StructureParameter;
import com.navi.backend.ast.y.declarations.VariableDeclaration;
import com.navi.backend.ast.y.expressions.Expression;
import com.navi.backend.ast.y.statements.AssignmentStatement;
import com.navi.backend.ast.y.statements.AssignmentOperator;
import com.navi.backend.ast.y.statements.DefaultCase;
import com.navi.backend.ast.y.statements.DoWhileStatement;
import com.navi.backend.ast.y.statements.ElseClause;
import com.navi.backend.ast.y.statements.ElseIfClause;
import com.navi.backend.ast.y.statements.ExpressionStatement;
import com.navi.backend.ast.y.statements.ForStatement;
import com.navi.backend.ast.y.statements.IfStatement;
import com.navi.backend.ast.y.statements.IncrementStatement;
import com.navi.backend.ast.y.statements.PrintStatement;
import com.navi.backend.ast.y.statements.ReturnStatement;
import com.navi.backend.ast.y.statements.Statement;
import com.navi.backend.ast.y.statements.SwitchCase;
import com.navi.backend.ast.y.statements.SwitchStatement;
import com.navi.backend.ast.y.statements.WhileStatement;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import com.navi.backend.semantic.AggregateType;
import com.navi.backend.semantic.Definitions;
import com.navi.backend.semantic.Field;
import com.navi.backend.semantic.ScopeKind;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.Type;
import com.navi.backend.semantic.TypeCompat;
import com.navi.backend.semantic.TypeRules;

import java.util.List;

/**
 * Chequeo de sentencias de Y: ámbitos, símbolos y control de flujo.
 * Mantiene el estado de la función actual ({@code currentReturnType});
 * el recorrido de los hijos lo hace el visitante (dispatcher) que la instancia.
 */
public class YStatementChecker {

    private final SemanticContext context;
    private final TypeRules rules;
    private final YTypeResolver types;
    private final Definitions defs;
    private final AstYVisitor<Type> visitor;

    private Type currentReturnType;

    YStatementChecker(SemanticContext context, TypeRules rules, YTypeResolver types,
                      Definitions defs, AstYVisitor<Type> visitor) {
        this.context = context;
        this.rules = rules;
        this.types = types;
        this.defs = defs;
        this.visitor = visitor;
    }

    // ---------------------------------------------------------------- funciones

    Type functionDeclaration(FunctionDeclaration node) {
        context.getSymbolTable().enterScope(ScopeKind.FUNCTION);
        if (node.getParameters() != null) {
            for (Parameter p : node.getParameters()) {
                defineParam(p);
            }
        }
        currentReturnType = node.getReturnType() == null ? Type.VOID : types.resolve(node.getReturnType());
        if (node.getStatements() != null) {
            for (Statement s : node.getStatements()) s.accept(visitor);
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
            type = types.resolve(n.getType());
            name = n.getName();
        } else if (p instanceof ArrayParameter a) {
            type = Type.array(types.resolve(a.getType()), 1);
            reference = true;
            name = a.getName();
        } else if (p instanceof StructureParameter s) {
            AggregateType agg = context.getTypeTable().resolve(s.getStructureName());
            if (agg == null) {
                rules.error(s.getLine(), s.getColumn(), "Estructura no definida: " + s.getStructureName());
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
        defs.parameter(p, name, type, reference, p.getLine(), p.getColumn());
    }

    Type variableDeclaration(VariableDeclaration node) {
        Type base = types.resolve(node.getType());
        int rank = node.getArrayDeclaration() == null ? 0 : node.getArrayDeclaration().getDimensions().size();
        Type type = rank > 0 ? Type.array(base, rank) : base;
        defs.variable(node, node.getName(), type, node.getLine(), node.getColumn());
        if (node.getInitializer() != null) {
            checkInitializer(node.getInitializer(), type, node.getLine(), node.getColumn());
        }
        return null;
    }

    // ---------------------------------------------------------------- statements

    Type assignment(AssignmentStatement node) {
        Type target = node.getTarget().accept(visitor);
        Type value = node.getValue().accept(visitor);
        if (node.getOperator() == AssignmentOperator.ASSIGN) {
            rules.assign(target, value, node.getLine(), node.getColumn());
        } else {
            rules.compoundAssign(target, value, node.getLine(), node.getColumn());
        }
        return null;
    }

    Type increment(IncrementStatement node) {
        Type t = node.getTarget().accept(visitor);
        rules.incrementOperand(t, node.getLine(), node.getColumn());
        return null;
    }

    Type ifStatement(IfStatement node) {
        rules.requireBool(node.getCondition().accept(visitor), node.getLine(), node.getColumn());
        context.getSymbolTable().enterScope(ScopeKind.BLOCK);
        for (Statement s : node.getStatements()) s.accept(visitor);
        context.getSymbolTable().exitScope();
        if (node.getElseIfClauses() != null) {
            for (ElseIfClause c : node.getElseIfClauses()) c.accept(visitor);
        }
        if (node.getElseClause() != null) node.getElseClause().accept(visitor);
        return null;
    }

    Type elseIf(ElseIfClause node) {
        rules.requireBool(node.getCondition().accept(visitor), node.getLine(), node.getColumn());
        context.getSymbolTable().enterScope(ScopeKind.BLOCK);
        for (Statement s : node.getStatements()) s.accept(visitor);
        context.getSymbolTable().exitScope();
        return null;
    }

    Type elseClause(ElseClause node) {
        context.getSymbolTable().enterScope(ScopeKind.BLOCK);
        for (Statement s : node.getStatements()) s.accept(visitor);
        context.getSymbolTable().exitScope();
        return null;
    }

    Type whileStatement(WhileStatement node) {
        rules.requireBool(node.getCondition().accept(visitor), node.getLine(), node.getColumn());
        context.getSymbolTable().enterScope(ScopeKind.LOOP);
        for (Statement s : node.getStatements()) s.accept(visitor);
        context.getSymbolTable().exitScope();
        return null;
    }

    Type doWhile(DoWhileStatement node) {
        context.getSymbolTable().enterScope(ScopeKind.LOOP);
        for (Statement s : node.getStatements()) s.accept(visitor);
        rules.requireBool(node.getCondition().accept(visitor), node.getLine(), node.getColumn());
        context.getSymbolTable().exitScope();
        return null;
    }

    Type forStatement(ForStatement node) {
        context.getSymbolTable().enterScope(ScopeKind.LOOP);
        if (node.getInitializer() != null) node.getInitializer().accept(visitor);
        if (node.getCondition() != null) rules.requireBool(node.getCondition().accept(visitor), node.getLine(), node.getColumn());
        if (node.getUpdate() != null) node.getUpdate().accept(visitor);
        for (Statement s : node.getStatements()) s.accept(visitor);
        context.getSymbolTable().exitScope();
        return null;
    }

    Type switchStatement(SwitchStatement node) {
        node.getExpression().accept(visitor);
        if (node.getCases() != null) for (SwitchCase c : node.getCases()) c.accept(visitor);
        if (node.getDefaultCase() != null) node.getDefaultCase().accept(visitor);
        return null;
    }

    Type switchCase(SwitchCase node) {
        node.getExpression().accept(visitor);
        context.getSymbolTable().enterScope(ScopeKind.BLOCK);
        for (Statement s : node.getStatements()) s.accept(visitor);
        context.getSymbolTable().exitScope();
        return null;
    }

    Type defaultCase(DefaultCase node) {
        context.getSymbolTable().enterScope(ScopeKind.BLOCK);
        for (Statement s : node.getStatements()) s.accept(visitor);
        context.getSymbolTable().exitScope();
        return null;
    }

    Type returnStatement(ReturnStatement node) {
        Expression expr = node.getExpression();
        Type actual = expr == null ? null : expr.accept(visitor);
        rules.checkReturn(currentReturnType, expr != null, actual, node.getLine(), node.getColumn(),
                "La función no retorna valor");
        return null;
    }

    Type print(PrintStatement node) {
        for (Expression e : node.getExpressions()) e.accept(visitor);
        return null;
    }

    Type expressionStatement(ExpressionStatement node) {
        node.getExpression().accept(visitor);
        return null;
    }

    // ---------------------------------------------------------------- inicializadores

    private void checkInitializer(Initializer init, Type expected, int line, int col) {
        if (init instanceof ExpressionInitializer ei) {
            Type actual = ei.getExpression().accept(visitor);
            if (!TypeCompat.canAssign(expected, actual)) {
                rules.error(line, col, "No se puede inicializar " + expected + " con " + actual);
            }
        } else if (init instanceof ArrayInitializer ai) {
            Type base = expected.isArray() ? expected.getElementType() : Type.ERROR;
            for (var el : ai.getElements()) {
                Type actual = el instanceof Expression e ? e.accept(visitor) : Type.ERROR;
                rules.checkElement(base, actual, line, col);
            }
        } else if (init instanceof StructureInitializer si) {
            checkStructureLiteral(si.getExpressions(), expected, line, col);
        }
    }

    private void checkStructureLiteral(List<Expression> exprs, Type expected, int line, int col) {
        if (!expected.isStruct()) {
            rules.error(line, col, "Literal de estructura para un tipo no-estructura: " + expected);
            return;
        }
        AggregateType agg = context.getTypeTable().resolve(expected.getName());
        if (agg == null) return;
        List<Field> fields = agg.getFields();
        if (exprs.size() > fields.size()) {
            rules.error(line, col, "Demasiados valores para " + expected);
        }
        for (int i = 0; i < Math.min(exprs.size(), fields.size()); i++) {
            Type actual = exprs.get(i).accept(visitor);
            if (!TypeCompat.canAssign(fields.get(i).getType(), actual)) {
                rules.error(line, col,
                        "Campo '" + fields.get(i).getName() + "' espera " + fields.get(i).getType() + " pero recibe " + actual);
            }
        }
    }
}
