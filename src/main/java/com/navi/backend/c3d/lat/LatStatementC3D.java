package com.navi.backend.c3d.lat;

import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.expressions.UnaryOperator;
import com.navi.backend.ast.lat.declarations.initializers.StructInitializer;
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
import com.navi.backend.c3d.C3DEmitter;
import com.navi.backend.semantic.SemanticContext;

import java.util.ArrayDeque;
import java.util.Deque;

// emision C3D de sentencias de Lat: control de flujo (pilas de break/continue),
// asignaciones, incrementos, impresion y lectura
class LatStatementC3D {

    private final C3DEmitter emitter;
    private final SemanticContext context;
    private final LatC3DResolver resolver;
    private final LatExpressionC3D expressions;
    private final AstLatVisitor<String> visitor;
    private final Deque<String> breakLabels = new ArrayDeque<>();
    private final Deque<String> continueLabels = new ArrayDeque<>();

    LatStatementC3D(C3DEmitter emitter, SemanticContext context, LatC3DResolver resolver, LatExpressionC3D expressions, AstLatVisitor<String> visitor) {
        this.emitter = emitter;
        this.context = context;
        this.resolver = resolver;
        this.expressions = expressions;
        this.visitor = visitor;
    }

    String block(BlockStatement node) {
        for (Statement s : node.getStatements()) s.accept(visitor);
        return null;
    }

    String assignment(AssignmentStatement node) {
        if (node.getInitializer() instanceof StructInitializer si) {
            resolver.emitStructInitializer(si, node.getTarget(), context.typeOf(node.getTarget()));
            return null;
        }
        String value = resolver.initializerValue(node.getInitializer());
        if (value != null) resolver.assignTo(node.getTarget(), value);
        return null;
    }

    String ifStatement(IfStatement node) {
        String cond = node.getCondition().accept(visitor);
        boolean hasElseIf = node.getElseIfStatements() != null && !node.getElseIfStatements().isEmpty();
        if (!hasElseIf && node.getElseBlock() == null) {
            String thenLabel = emitter.newLabel();
            String endLabel = emitter.newLabel();
            emitter.ifGoto(cond, "==", "1", thenLabel);
            emitter.jump(endLabel);
            emitter.label(thenLabel);
            node.getThenBlock().accept(visitor);
            emitter.label(endLabel);
            return null;
        }
        String endLabel = emitter.newLabel();
        String thenLabel = emitter.newLabel();
        String current = emitter.newLabel();
        emitter.ifGoto(cond, "==", "1", thenLabel);
        emitter.jump(current);
        emitter.label(thenLabel);
        node.getThenBlock().accept(visitor);
        emitter.jump(endLabel);

        if (node.getElseIfStatements() != null) {
            for (ElseIfStatement e : node.getElseIfStatements()) {
                emitter.label(current);
                String eThen = emitter.newLabel();
                current = emitter.newLabel();
                String c = e.getCondition().accept(visitor);
                emitter.ifGoto(c, "==", "1", eThen);
                emitter.jump(current);
                emitter.label(eThen);
                e.getBlock().accept(visitor);
                emitter.jump(endLabel);
            }
        }
        emitter.label(current);
        if (node.getElseBlock() != null) node.getElseBlock().accept(visitor);
        emitter.label(endLabel);
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
        node.getBlock().accept(visitor);
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
        node.getBlock().accept(visitor);
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
        if (node.getBlock() != null) node.getBlock().accept(visitor);
        continueLabels.pop();
        breakLabels.pop();
        emitter.label(contLabel);
        if (node.getUpdate() != null) node.getUpdate().accept(visitor);
        emitter.jump(startLabel);
        emitter.label(endLabel);
        return null;
    }

    String returnStatement(ReturnStatement node) {
        if (node.getExpression() != null) emitter.returnValue(node.getExpression().accept(visitor));
        else emitter.returnVoid();
        return null;
    }

    String increment(IncrementStatement node) {
        String old = node.getTarget().accept(visitor);
        String op = node.getOperator() == UnaryOperator.POST_INCREMENT ? "+" : "-";
        resolver.assignTo(node.getTarget(), emitter.binary(op, old, "1"));
        return null;
    }

    String print(PrintStatement node) {
        for (Expression e : node.getExpressions()) emitter.print(e.accept(visitor));
        return null;
    }

    String read(ReadStatement node) {
        if (node.getTarget() != null) resolver.assignTo(node.getTarget(), emitter.read());
        else emitter.readDiscard();
        return null;
    }

    String callStatement(FunctionCallStatement node) {
        expressions.emitCall(node.getCallee(), node.getArguments(), true);
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
