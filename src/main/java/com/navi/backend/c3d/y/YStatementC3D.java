package com.navi.backend.c3d.y;

import com.navi.backend.ast.y.expressions.Expression;
import com.navi.backend.ast.y.statements.AssignmentOperator;
import com.navi.backend.ast.y.statements.AssignmentStatement;
import com.navi.backend.ast.y.statements.BreakStatement;
import com.navi.backend.ast.y.statements.ContinueStatement;
import com.navi.backend.ast.y.statements.DefaultCase;
import com.navi.backend.ast.y.statements.DoWhileStatement;
import com.navi.backend.ast.y.statements.ElseClause;
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
import com.navi.backend.c3d.C3DEmitter;

import java.util.ArrayDeque;
import java.util.Deque;

// emision C3D de sentencias de Y: control de flujo (pilas de break/continue),
// switch, asignaciones e incrementos
class YStatementC3D {

    private final C3DEmitter emitter;
    private final YResolverC3D resolver;
    private final AstYVisitor<String> visitor;
    private final Deque<String> breakLabels = new ArrayDeque<>();
    private final Deque<String> continueLabels = new ArrayDeque<>();

    YStatementC3D(C3DEmitter emitter, YResolverC3D resolver, AstYVisitor<String> visitor) {
        this.emitter = emitter;
        this.resolver = resolver;
        this.visitor = visitor;
    }

    String assignment(AssignmentStatement node) {
        String value = node.getValue().accept(visitor);
        if (node.getOperator() == AssignmentOperator.ASSIGN) {
            resolver.assignTo(node.getTarget(), value);
        } else {
            String op = node.getOperator() == AssignmentOperator.PLUS_ASSIGN ? "+"
                    : node.getOperator() == AssignmentOperator.MINUS_ASSIGN ? "-" : "*";
            String current = node.getTarget().accept(visitor);
            resolver.assignTo(node.getTarget(), emitter.binary(op, current, value));
        }
        return null;
    }

    String increment(IncrementStatement node) {
        String current = node.getTarget().accept(visitor);
        String fresh = emitter.binary(node.isIncrement() ? "+" : "-", current, "1");
        resolver.assignTo(node.getTarget(), fresh);
        return null;
    }

    String ifStatement(IfStatement node) {
        String cond = node.getCondition().accept(visitor);
        boolean hasElseIf = node.getElseIfClauses() != null && !node.getElseIfClauses().isEmpty();
        if (!hasElseIf && node.getElseClause() == null) {
            String thenLabel = emitter.newLabel();
            String endLabel = emitter.newLabel();
            emitter.ifGoto(cond, "==", "1", thenLabel);
            emitter.jump(endLabel);
            emitter.label(thenLabel);
            for (Statement s : node.getStatements()) s.accept(visitor);
            emitter.label(endLabel);
            return null;
        }
        String endLabel = emitter.newLabel();
        String thenLabel = emitter.newLabel();
        String current = emitter.newLabel();
        emitter.ifGoto(cond, "==", "1", thenLabel);
        emitter.jump(current);
        emitter.label(thenLabel);
        for (Statement s : node.getStatements()) s.accept(visitor);
        emitter.jump(endLabel);

        if (node.getElseIfClauses() != null) {
            for (com.navi.backend.ast.y.statements.ElseIfClause c : node.getElseIfClauses()) {
                emitter.label(current);
                String cThen = emitter.newLabel();
                current = emitter.newLabel();
                String cc = c.getCondition().accept(visitor);
                emitter.ifGoto(cc, "==", "1", cThen);
                emitter.jump(current);
                emitter.label(cThen);
                for (Statement s : c.getStatements()) s.accept(visitor);
                emitter.jump(endLabel);
            }
        }
        emitter.label(current);
        if (node.getElseClause() != null) node.getElseClause().accept(visitor);
        emitter.label(endLabel);
        return null;
    }

    String elseClause(ElseClause node) {
        for (Statement s : node.getStatements()) s.accept(visitor);
        return null;
    }

    String whileStatement(WhileStatement node) {
        String startLabel = emitter.newLabel();
        String bodyLabel = emitter.newLabel();
        String endLabel = emitter.newLabel();
        emitter.label(startLabel);
        String cond = node.getCondition().accept(visitor);
        emitter.ifGoto(cond, "==", "1", bodyLabel);
        emitter.jump(endLabel);
        emitter.label(bodyLabel);
        breakLabels.push(endLabel);
        continueLabels.push(startLabel);
        for (Statement s : node.getStatements()) s.accept(visitor);
        continueLabels.pop();
        breakLabels.pop();
        emitter.jump(startLabel);
        emitter.label(endLabel);
        return null;
    }

    String doWhile(DoWhileStatement node) {
        String startLabel = emitter.newLabel();
        String contLabel = emitter.newLabel();
        String endLabel = emitter.newLabel();
        emitter.label(startLabel);
        breakLabels.push(endLabel);
        continueLabels.push(contLabel);
        for (Statement s : node.getStatements()) s.accept(visitor);
        continueLabels.pop();
        breakLabels.pop();
        emitter.label(contLabel);
        String cond = node.getCondition().accept(visitor);
        emitter.ifGoto(cond, "==", "1", startLabel);
        emitter.jump(endLabel);
        emitter.label(endLabel);
        return null;
    }

    String forStatement(ForStatement node) {
        String startLabel = emitter.newLabel();
        String bodyLabel = emitter.newLabel();
        String contLabel = emitter.newLabel();
        String endLabel = emitter.newLabel();
        if (node.getInitializer() != null) node.getInitializer().accept(visitor);
        emitter.label(startLabel);
        if (node.getCondition() != null) {
            String cond = node.getCondition().accept(visitor);
            emitter.ifGoto(cond, "==", "1", bodyLabel);
            emitter.jump(endLabel);
        }
        emitter.label(bodyLabel);
        breakLabels.push(endLabel);
        continueLabels.push(contLabel);
        for (Statement s : node.getStatements()) s.accept(visitor);
        continueLabels.pop();
        breakLabels.pop();
        emitter.label(contLabel);
        if (node.getUpdate() != null) node.getUpdate().accept(visitor);
        emitter.jump(startLabel);
        emitter.label(endLabel);
        return null;
    }

    String switchStatement(SwitchStatement node) {
        String endLabel = emitter.newLabel();
        String value = node.getExpression().accept(visitor);
        breakLabels.push(endLabel);
        String current = null;
        if (node.getCases() != null) {
            for (SwitchCase c : node.getCases()) {
                if (current != null) emitter.label(current);
                String caseLabel = emitter.newLabel();
                current = emitter.newLabel();
                String caseValue = c.getExpression().accept(visitor);
                emitter.ifGoto(value, "==", caseValue, caseLabel);
                emitter.jump(current);
                emitter.label(caseLabel);
                for (Statement s : c.getStatements()) s.accept(visitor);
                emitter.jump(endLabel);
            }
        }
        if (current != null) emitter.label(current);
        if (node.getDefaultCase() != null) node.getDefaultCase().accept(visitor);
        emitter.label(endLabel);
        breakLabels.pop();
        return null;
    }

    String defaultCase(DefaultCase node) {
        for (Statement s : node.getStatements()) s.accept(visitor);
        return null;
    }

    String returnStatement(ReturnStatement node) {
        if (node.getExpression() != null) emitter.returnValue(node.getExpression().accept(visitor));
        else emitter.returnVoid();
        return null;
    }

    String print(PrintStatement node) {
        for (Expression e : node.getExpressions()) emitter.print(e.accept(visitor));
        return null;
    }

    String breakStatement(BreakStatement node) {
        if (!breakLabels.isEmpty()) emitter.jump(breakLabels.peek());
        return null;
    }

    String continueStatement(ContinueStatement node) {
        if (!continueLabels.isEmpty()) emitter.jump(continueLabels.peek());
        return null;
    }

    String expressionStatement(ExpressionStatement node) {
        node.getExpression().accept(visitor);
        return null;
    }
}
