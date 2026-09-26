package com.navi.backend.semantic.lat;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.declarations.ArrayDeclaration;
import com.navi.backend.ast.lat.declarations.ArrayInitializer;
import com.navi.backend.ast.lat.declarations.Declaration;
import com.navi.backend.ast.lat.declarations.VariableDeclaration;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.global.FunctionBody;
import com.navi.backend.ast.lat.global.FunctionDeclaration;
import com.navi.backend.ast.lat.global.LocalVariableSection;
import com.navi.backend.ast.lat.global.Parameter;
import com.navi.backend.ast.lat.statements.AssignmentStatement;
import com.navi.backend.ast.lat.statements.BlockStatement;
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
import com.navi.backend.semantic.Definitions;
import com.navi.backend.semantic.ScopeKind;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.Type;
import com.navi.backend.semantic.TypeRules;

/**
 * Chequeo de sentencias de Lat: ámbitos, símbolos y control de flujo.
 * Mantiene el estado de la función actual ({@code currentReturnType});
 * el recorrido de los hijos lo hace el visitante (dispatcher) que la instancia.
 */
public class LatStatementChecker {

    private final SemanticContext context;
    private final TypeRules rules;
    private final LatTypeResolver types;
    private final Definitions defs;
    private final LatExpressionChecker expressions;
    private final AstLatVisitor<Type> visitor;

    private Type currentReturnType;

    LatStatementChecker(SemanticContext context, TypeRules rules, LatTypeResolver types, Definitions defs, LatExpressionChecker expressions, AstLatVisitor<Type> visitor) {
        this.context = context;
        this.rules = rules;
        this.types = types;
        this.defs = defs;
        this.expressions = expressions;
        this.visitor = visitor;
    }

    // ---------------------------------------------------------------- funciones

    Type functionDeclaration(FunctionDeclaration node) {
        context.getSymbolTable().enterScope(ScopeKind.FUNCTION);
        if (node.getParameters() != null) {
            for (Parameter p : node.getParameters()) {
                Type t = types.resolve(p.getType(), p.getLine(), p.getColumn());
                defs.parameter(p, p.getName(), t, false, p.getLine(), p.getColumn());
            }
        }
        currentReturnType = types.resolve(node.getReturnType(), node.getLine(), node.getColumn());
        if (node.getBody() != null) {
            node.getBody().accept(visitor);
        }
        currentReturnType = null;
        context.getSymbolTable().exitScope();
        return null;
    }

    Type functionBody(FunctionBody node) {
        if (node.getLocalVariables() != null) {
            node.getLocalVariables().accept(visitor);
        }
        if (node.getBody() != null) {
            node.getBody().accept(visitor);
        }
        return null;
    }

    Type localVariableSection(LocalVariableSection node) {
        for (Declaration d : node.getDeclarations()) {
            d.accept(visitor);
        }
        return null;
    }

    Type variableDeclaration(VariableDeclaration node) {
        Type type = types.resolve(node.getType(), node.getLine(), node.getColumn());
        defs.variable(node, node.getName(), type, node.getLine(), node.getColumn());
        if (node.getInitializer() != null) {
            expressions.checkInitializer(node.getInitializer(), type, node.getLine(), node.getColumn());
        }
        return null;
    }

    Type arrayDeclaration(ArrayDeclaration node) {
        Type base = types.resolve(node.getType(), node.getLine(), node.getColumn());
        int rank = node.getSizes() == null ? 0 : node.getSizes().size();
        Type arrayType = rank > 0 ? Type.array(base, rank) : base;
        defs.variable(node, node.getName(), arrayType, node.getLine(), node.getColumn());
        if (node.getInitializer() != null) {
            checkArrayInitializer(node.getInitializer(), arrayType, node.getLine(), node.getColumn());
        }
        return null;
    }

    /**
     * Valida un inicializador de arreglo descendiendo en los literales anidados:
     * cada nivel consume una dimensión ({@code int[][]} -> {@code int[]} -> {@code int}).
     */
    void checkArrayInitializer(ArrayInitializer init, Type expected, int line, int col) {
        Type element = rules.arrayElementType(expected);
        for (AstLatNode el : init.getElements()) {
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

    // ---------------------------------------------------------------- statements

    Type block(BlockStatement node) {
        context.getSymbolTable().enterScope(ScopeKind.BLOCK);
        for (Statement s : node.getStatements()) {
            s.accept(visitor);
        }
        context.getSymbolTable().exitScope();
        return null;
    }

    Type assignment(AssignmentStatement node) {
        Type targetType = node.getTarget().accept(visitor);
        Type valueType = expressions.initializerType(node.getInitializer());
        rules.assign(targetType, valueType, node.getLine(), node.getColumn());
        return null;
    }

    Type ifStatement(IfStatement node) {
        rules.requireBool(node.getCondition().accept(visitor), node.getLine(), node.getColumn());
        if (node.getThenBlock() != null) node.getThenBlock().accept(visitor);
        if (node.getElseIfStatements() != null) {
            for (ElseIfStatement e : node.getElseIfStatements()) e.accept(visitor);
        }
        if (node.getElseBlock() != null) node.getElseBlock().accept(visitor);
        return null;
    }

    Type elseIf(ElseIfStatement node) {
        rules.requireBool(node.getCondition().accept(visitor), node.getLine(), node.getColumn());
        if (node.getBlock() != null) node.getBlock().accept(visitor);
        return null;
    }

    Type whileStatement(WhileStatement node) {
        rules.requireBool(node.getCondition().accept(visitor), node.getLine(), node.getColumn());
        context.getSymbolTable().enterScope(ScopeKind.LOOP);
        if (node.getBlock() != null) node.getBlock().accept(visitor);
        context.getSymbolTable().exitScope();
        return null;
    }

    Type doWhile(DoWhileStatement node) {
        context.getSymbolTable().enterScope(ScopeKind.LOOP);
        if (node.getBlock() != null) node.getBlock().accept(visitor);
        rules.requireBool(node.getCondition().accept(visitor), node.getLine(), node.getColumn());
        context.getSymbolTable().exitScope();
        return null;
    }

    Type forStatement(ForStatement node) {
        context.getSymbolTable().enterScope(ScopeKind.LOOP);
        if (node.getInitializer() != null) node.getInitializer().accept(visitor);
        if (node.getCondition() != null) rules.requireBool(node.getCondition().accept(visitor), node.getLine(), node.getColumn());
        if (node.getUpdate() != null) node.getUpdate().accept(visitor);
        if (node.getBlock() != null) node.getBlock().accept(visitor);
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

    Type increment(IncrementStatement node) {
        Type t = node.getTarget().accept(visitor);
        rules.incrementOperand(t, node.getLine(), node.getColumn());
        return null;
    }

    Type print(PrintStatement node) {
        for (Expression e : node.getExpressions()) {
            e.accept(visitor);
        }
        return null;
    }

    Type read(ReadStatement node) {
        if (node.getTarget() != null) node.getTarget().accept(visitor);
        return null;
    }

    Type callStatement(FunctionCallStatement node) {
        expressions.resolveCall(node.getCallee(), node.getArguments(), node.getLine(), node.getColumn());
        return null;
    }
}
