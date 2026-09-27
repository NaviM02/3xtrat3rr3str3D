package com.navi.backend.c3d.z;

import com.navi.backend.ast.z.expressions.Expression;
import com.navi.backend.ast.z.statements.BlockStatement;
import com.navi.backend.ast.z.statements.BreakStatement;
import com.navi.backend.ast.z.statements.ContinueStatement;
import com.navi.backend.ast.z.statements.DefaultCase;
import com.navi.backend.ast.z.statements.DoWhileStatement;
import com.navi.backend.ast.z.statements.ElseClause;
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
import com.navi.backend.c3d.C3DEmitter;

import java.util.ArrayDeque;
import java.util.Deque;

// emision C3D de sentencias de Z: control de flujo (pilas de break/continue),
// switch, declaraciones locales y expresiones
class ZStatementC3D {

    private final C3DEmitter emitter;
    private final AstZVisitor<String> visitor;
    private final Deque<String> breakLabels = new ArrayDeque<>();
    private final Deque<String> continueLabels = new ArrayDeque<>();

    ZStatementC3D(C3DEmitter emitter, AstZVisitor<String> visitor) {
        this.emitter = emitter;
        this.visitor = visitor;
    }

    String block(BlockStatement node) {
        for (Statement s : node.getStatements()) s.accept(visitor);
        return null;
    }

    String variableDeclarationStatement(VariableDeclarationStatement node) {
        node.getDeclaration().accept(visitor);
        return null;
    }

    String expressionStatement(ExpressionStatement node) {
        node.getExpression().accept(visitor);
        return null;
    }

    String ifStatement(IfStatement node) {
        String cond = node.getCondition().accept(visitor);
        boolean hasElseIf = node.getElseIfClauses() != null && !node.getElseIfClauses().isEmpty();
        if (!hasElseIf && node.getElseBranch() == null) {
            String thenLabel = emitter.newLabel();
            String endLabel = emitter.newLabel();
            emitter.ifGoto(cond, "==", "1", thenLabel);
            emitter.jump(endLabel);
            emitter.label(thenLabel);
            node.getThenBranch().accept(visitor);
            emitter.label(endLabel);
            return null;
        }
        String endLabel = emitter.newLabel();
        String thenLabel = emitter.newLabel();
        String current = emitter.newLabel();
        emitter.ifGoto(cond, "==", "1", thenLabel);
        emitter.jump(current);
        emitter.label(thenLabel);
        node.getThenBranch().accept(visitor);
        emitter.jump(endLabel);

        if (node.getElseIfClauses() != null) {
            for (com.navi.backend.ast.z.statements.ElseIfClause c : node.getElseIfClauses()) {
                emitter.label(current);
                String cThen = emitter.newLabel();
                current = emitter.newLabel();
                String cc = c.getCondition().accept(visitor);
                emitter.ifGoto(cc, "==", "1", cThen);
                emitter.jump(current);
                emitter.label(cThen);
                c.getBranch().accept(visitor);
                emitter.jump(endLabel);
            }
        }
        emitter.label(current);
        if (node.getElseBranch() != null) node.getElseBranch().accept(visitor);
        emitter.label(endLabel);
        return null;
    }

    String elseClause(ElseClause node) {
        node.getBranch().accept(visitor);
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
        node.getBody().accept(visitor);
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
        node.getBody().accept(visitor);
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
        if (node.getCondition() instanceof Expression e) {
            String cond = e.accept(visitor);
            emitter.ifGoto(cond, "==", "1", bodyLabel);
            emitter.jump(endLabel);
        }
        emitter.label(bodyLabel);
        breakLabels.push(endLabel);
        continueLabels.push(contLabel);
        if (node.getBlock() != null) node.getBlock().accept(visitor);
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
        for (Expression e : node.getArguments()) emitter.print(e.accept(visitor));
        return null;
    }

    String println(PrintlnStatement node) {
        for (Expression e : node.getArguments()) emitter.print(e.accept(visitor));
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
}
