package com.navi.backend.semantic.z;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.declarations.ArrayInitializer;
import com.navi.backend.ast.z.declarations.ConstructorDeclaration;
import com.navi.backend.ast.z.declarations.ExpressionInitializer;
import com.navi.backend.ast.z.declarations.FieldDeclaration;
import com.navi.backend.ast.z.declarations.Initializer;
import com.navi.backend.ast.z.declarations.MethodDeclaration;
import com.navi.backend.ast.z.declarations.Parameter;
import com.navi.backend.ast.z.declarations.VariableDeclaration;
import com.navi.backend.ast.z.declarations.VariableDeclarator;
import com.navi.backend.ast.z.expressions.Expression;
import com.navi.backend.ast.z.statements.BlockStatement;
import com.navi.backend.ast.z.statements.DefaultCase;
import com.navi.backend.ast.z.statements.DoWhileStatement;
import com.navi.backend.ast.z.statements.ElseClause;
import com.navi.backend.ast.z.statements.ElseIfClause;
import com.navi.backend.ast.z.statements.ExpressionStatement;
import com.navi.backend.ast.z.statements.ForStatement;
import com.navi.backend.ast.z.statements.IfStatement;
import com.navi.backend.ast.z.statements.PrintStatement;
import com.navi.backend.ast.z.statements.PrintlnStatement;
import com.navi.backend.ast.z.statements.ReturnStatement;
import com.navi.backend.ast.z.statements.Statement;
import com.navi.backend.ast.z.statements.SwitchCase;
import com.navi.backend.ast.z.statements.SwitchStatement;
import com.navi.backend.ast.z.statements.VariableDeclarationStatement;
import com.navi.backend.ast.z.statements.WhileStatement;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import com.navi.backend.semantic.Definitions;
import com.navi.backend.semantic.ScopeKind;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.Type;
import com.navi.backend.semantic.TypeCompat;
import com.navi.backend.semantic.TypeRules;

import java.util.List;

/**
 * Chequeo de sentencias y miembros de Z: ámbitos, símbolos y control de flujo.
 * Mantiene el estado de la función actual ({@code currentReturnType});
 * el recorrido de los hijos lo hace el visitante (dispatcher) que la instancia.
 */
public class ZStatementChecker {

    private final SemanticContext context;
    private final TypeRules rules;
    private final ZTypeResolver types;
    private final Definitions defs;
    private final AstZVisitor<Type> visitor;

    private Type currentReturnType;

    ZStatementChecker(SemanticContext context, TypeRules rules, ZTypeResolver types,
                      Definitions defs, AstZVisitor<Type> visitor) {
        this.context = context;
        this.rules = rules;
        this.types = types;
        this.defs = defs;
        this.visitor = visitor;
    }

    // ---------------------------------------------------------------- miembros de clase

    Type fieldDeclaration(FieldDeclaration node) {
        Type type = types.resolve(node.getType());
        for (VariableDeclarator v : node.getVariables()) {
            if (v.getInitializer() != null) {
                checkInitializer(v.getInitializer(), type, v.getLine(), v.getColumn());
            }
        }
        return null;
    }

    Type methodDeclaration(MethodDeclaration node) {
        context.getSymbolTable().enterScope(ScopeKind.FUNCTION);
        defineParams(node.getParameters());
        currentReturnType = node.getReturnType() == null ? Type.VOID : types.resolve(node.getReturnType());
        node.getBody().accept(visitor);
        currentReturnType = null;
        context.getSymbolTable().exitScope();
        return null;
    }

    Type constructorDeclaration(ConstructorDeclaration node) {
        context.getSymbolTable().enterScope(ScopeKind.FUNCTION);
        defineParams(node.getParameters());
        currentReturnType = Type.VOID;
        node.getBody().accept(visitor);
        currentReturnType = null;
        context.getSymbolTable().exitScope();
        return null;
    }

    private void defineParams(List<Parameter> parameters) {
        if (parameters == null) return;
        for (Parameter p : parameters) {
            Type t = types.resolve(p.getType());
            defs.parameter(p, p.getName(), t, false, p.getLine(), p.getColumn());
        }
    }

    // ---------------------------------------------------------------- statements

    Type block(BlockStatement node) {
        context.getSymbolTable().enterScope(ScopeKind.BLOCK);
        for (Statement s : node.getStatements()) s.accept(visitor);
        context.getSymbolTable().exitScope();
        return null;
    }

    Type variableDeclarationStatement(VariableDeclarationStatement node) {
        node.getDeclaration().accept(visitor);
        return null;
    }

    Type variableDeclaration(VariableDeclaration node) {
        Type type = types.resolve(node.getType());
        for (VariableDeclarator v : node.getVariables()) {
            defs.variable(v, v.getName(), type, v.getLine(), v.getColumn());
            if (v.getInitializer() != null) {
                checkInitializer(v.getInitializer(), type, v.getLine(), v.getColumn());
            }
        }
        return null;
    }

    Type expressionStatement(ExpressionStatement node) {
        node.getExpression().accept(visitor);
        return null;
    }

    Type ifStatement(IfStatement node) {
        rules.requireBool(node.getCondition().accept(visitor), node.getLine(), node.getColumn());
        node.getThenBranch().accept(visitor);
        if (node.getElseIfClauses() != null) for (ElseIfClause c : node.getElseIfClauses()) c.accept(visitor);
        if (node.getElseBranch() != null) node.getElseBranch().accept(visitor);
        return null;
    }

    Type elseIf(ElseIfClause node) {
        rules.requireBool(node.getCondition().accept(visitor), node.getLine(), node.getColumn());
        node.getBranch().accept(visitor);
        return null;
    }

    Type elseClause(ElseClause node) {
        node.getBranch().accept(visitor);
        return null;
    }

    Type whileStatement(WhileStatement node) {
        rules.requireBool(node.getCondition().accept(visitor), node.getLine(), node.getColumn());
        context.getSymbolTable().enterScope(ScopeKind.LOOP);
        node.getBody().accept(visitor);
        context.getSymbolTable().exitScope();
        return null;
    }

    Type doWhile(DoWhileStatement node) {
        context.getSymbolTable().enterScope(ScopeKind.LOOP);
        node.getBody().accept(visitor);
        rules.requireBool(node.getCondition().accept(visitor), node.getLine(), node.getColumn());
        context.getSymbolTable().exitScope();
        return null;
    }

    Type forStatement(ForStatement node) {
        context.getSymbolTable().enterScope(ScopeKind.LOOP);
        if (node.getInitializer() != null) node.getInitializer().accept(visitor);
        if (node.getCondition() != null) {
            Type c = node.getCondition() instanceof Expression e ? e.accept(visitor) : Type.ERROR;
            rules.requireBool(c, node.getLine(), node.getColumn());
        }
        if (node.getUpdate() != null) node.getUpdate().accept(visitor);
        if (node.getBlock() != null) node.getBlock().accept(visitor);
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
        for (Statement s : node.getStatements()) s.accept(visitor);
        return null;
    }

    Type defaultCase(DefaultCase node) {
        for (Statement s : node.getStatements()) s.accept(visitor);
        return null;
    }

    Type returnStatement(ReturnStatement node) {
        Expression expr = node.getExpression();
        Type actual = expr == null ? null : expr.accept(visitor);
        rules.checkReturn(currentReturnType, expr != null, actual, node.getLine(), node.getColumn(),
                "El método no retorna valor");
        return null;
    }

    Type print(PrintStatement node) {
        for (Expression e : node.getArguments()) e.accept(visitor);
        return null;
    }

    Type println(PrintlnStatement node) {
        for (Expression e : node.getArguments()) e.accept(visitor);
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
            checkArrayInitializer(ai, expected, line, col);
        }
    }

    /**
     * Valida un inicializador de arreglo descendiendo en los literales anidados:
     * cada nivel consume una dimensión ({@code int[][]} -> {@code int[]} -> {@code int}).
     */
    private void checkArrayInitializer(ArrayInitializer ai, Type expected, int line, int col) {
        Type element = rules.arrayElementType(expected);
        for (AstZNode el : ai.getElements()) {
            if (el instanceof ArrayInitializer nested) {
                checkArrayInitializer(nested, element, el.getLine(), el.getColumn());
            } else if (el instanceof Expression e) {
                Type actual = e.accept(visitor);
                rules.checkElement(element, actual, el.getLine(), el.getColumn());
            } else {
                rules.checkElement(element, Type.ERROR, el.getLine(), el.getColumn());
            }
        }
    }
}
