package com.navi.backend.ast.lat.visitors;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.declarations.VariableDeclaration;
import com.navi.backend.ast.lat.declarations.initializers.ExpressionInitializer;
import com.navi.backend.ast.lat.declarations.initializers.Initializer;
import com.navi.backend.ast.lat.expressions.*;
import com.navi.backend.ast.lat.statements.*;
import com.navi.backend.lexer_parser.lat.PigLatinParser;

import java.util.ArrayList;
import java.util.List;

public class StatementVisitor extends ExpressionVisitor {
    @Override
    public AstLatNode visitAssignmentStmt(PigLatinParser.AssignmentStmtContext ctx) {
        return visit(ctx.assignment());
    }

    @Override
    public AstLatNode visitAssignment(PigLatinParser.AssignmentContext ctx) {
        Expression left = (Expression) visit(ctx.postfixExpression());
        Initializer right = null;

        if (ctx.expression() != null) {
            Expression expr = (Expression) visit(ctx.expression());
            right = new ExpressionInitializer(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), expr);
        } else if (ctx.structInitializer() != null) {
            right = (Initializer) visit(ctx.structInitializer());
        }

        return new AssignmentStatement(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), left, right);
    }

    @Override
    public AstLatNode visitBlock(PigLatinParser.BlockContext ctx) {
        List<Statement> statements = new ArrayList<>();
        for (PigLatinParser.StatementContext statement : ctx.statement()) {
            statements.add((Statement) visit(statement));
        }

        return new BlockStatement(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), statements);
    }

    @Override
    public AstLatNode visitIncrementStmt(PigLatinParser.IncrementStmtContext ctx) {
        return visit(ctx.incrementStatement());
    }

    @Override
    public AstLatNode visitIncrementStatement(PigLatinParser.IncrementStatementContext ctx) {
        Expression target = (Expression) visit(ctx.incrementableExpression());
        if (ctx.PLUSPLUS() != null) {
            return new IncrementStatement(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), target, UnaryOperator.POST_INCREMENT);
        }
        return new IncrementStatement(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), target, UnaryOperator.POST_DECREMENT);
    }

    @Override
    public AstLatNode visitIncrementVariable(PigLatinParser.IncrementVariableContext ctx) {
        return new VariableExpression(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.ID().getText());
    }

    @Override
    public AstLatNode visitIncrementArrayAccess(PigLatinParser.IncrementArrayAccessContext ctx) {
        return new ArrayAccessExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.postfixExpression()),
            (Expression) visit(ctx.expression())
        );
    }

    @Override
    public AstLatNode visitIncrementMemberAccess(PigLatinParser.IncrementMemberAccessContext ctx) {
        return new MemberAccessExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.postfixExpression()),
            ctx.ID().getText()
        );
    }

    @Override
    public AstLatNode visitFunctionCallStatementStmt(PigLatinParser.FunctionCallStatementStmtContext ctx) {
        return visit(ctx.functionCallStatement());
    }

    @Override
    public AstLatNode visitFunctionCallStatement(PigLatinParser.FunctionCallStatementContext ctx) {
        Expression callee = (Expression) visit(ctx.callableExpression());

        List<Expression> arguments = new ArrayList<>();
        if (ctx.functionArguments().argumentList() != null) {
            for (PigLatinParser.ExpressionContext exprCtx : ctx.functionArguments().argumentList().arguments) {
                arguments.add((Expression) visit(exprCtx));
            }
        }

        return new FunctionCallStatement(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            callee,
            arguments
        );
    }

    @Override
    public AstLatNode visitCallVariable(PigLatinParser.CallVariableContext ctx) {
        return new VariableExpression(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.ID().getText()
        );
    }

    @Override
    public AstLatNode visitCallArrayAccess(PigLatinParser.CallArrayAccessContext ctx) {
        return new ArrayAccessExpression(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.postfixExpression()),
            (Expression) visit(ctx.expression())
        );
    }

    @Override
    public AstLatNode visitCallMemberAccess(PigLatinParser.CallMemberAccessContext ctx) {
        return new MemberAccessExpression(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.postfixExpression()),
            ctx.ID().getText()
        );
    }


    @Override
    public AstLatNode visitIfStmt(PigLatinParser.IfStmtContext ctx) {
        return visit(ctx.ifStatement());
    }

    @Override
    public AstLatNode visitIfStatement(PigLatinParser.IfStatementContext ctx) {
        List<ElseIfStatement> elseIfs = new ArrayList<>();

        for (PigLatinParser.ElseIfStatementContext elseIf : ctx.elseIfStatement()) {
            elseIfs.add((ElseIfStatement) visit(elseIf));
        }

        BlockStatement elseBlock = null;

        if (ctx.elseStatement() != null) {
            elseBlock = (BlockStatement) visit(ctx.elseStatement());
        }

        return new IfStatement(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.expression()),
            (BlockStatement) visit(ctx.block()),
            elseIfs,
            elseBlock
        );
    }

    @Override
    public AstLatNode visitElseIfStatement(PigLatinParser.ElseIfStatementContext ctx) {
        return new ElseIfStatement(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.expression()),
            (BlockStatement) visit(ctx.block())
        );
    }

    @Override
    public AstLatNode visitElseStatement(PigLatinParser.ElseStatementContext ctx) {
        return visit(ctx.block());
    }

    @Override
    public AstLatNode visitWhileStmt(PigLatinParser.WhileStmtContext ctx) {
        return visit(ctx.whileStatement());
    }

    @Override
    public AstLatNode visitWhileStatement(PigLatinParser.WhileStatementContext ctx) {
        return new WhileStatement(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.expression()),
            (BlockStatement) visit(ctx.block())
        );
    }

    @Override
    public AstLatNode visitDoWhileStmt(PigLatinParser.DoWhileStmtContext ctx) {
        return visit(ctx.doWhileStatement());
    }

    @Override
    public AstLatNode visitDoWhileStatement(PigLatinParser.DoWhileStatementContext ctx) {
        return new DoWhileStatement(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (BlockStatement) visit(ctx.block()),
            (Expression) visit(ctx.expression())
        );
    }

    @Override
    public AstLatNode visitForStmt(PigLatinParser.ForStmtContext ctx) {
        return visit(ctx.forStatement());
    }

    @Override
    public AstLatNode visitForStatement(PigLatinParser.ForStatementContext ctx) {
        return new ForStatement(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (VariableDeclaration) visit(ctx.variableDeclaration()),
            (Expression) visit(ctx.expression(0)),
            (Expression) visit(ctx.expression(1)),
            (BlockStatement) visit(ctx.block())
        );
    }

    @Override
    public AstLatNode visitReturnStmt(PigLatinParser.ReturnStmtContext ctx) {
        return new ReturnStatement(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.returnStatement().expression())
        );
    }

    @Override
    public AstLatNode visitBreakStmt(PigLatinParser.BreakStmtContext ctx) {
        return new BreakStatement(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
    }

    @Override
    public AstLatNode visitContinueStmt(PigLatinParser.ContinueStmtContext ctx) {
        return new ContinueStatement(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
    }

    @Override
    public AstLatNode visitPrintStatement(PigLatinParser.PrintStatementContext ctx) {
        List<Expression> expressions = new ArrayList<>();

        for (PigLatinParser.ExpressionContext expr : ctx.expressions) {
            expressions.add((Expression) visit(expr));
        }

        return new PrintStatement(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), expressions);
    }

    @Override
    public AstLatNode visitReadStatement(PigLatinParser.ReadStatementContext ctx) {
        Expression target = null;

        if (ctx.postfixExpression() != null) {
            target = (Expression) visit(ctx.postfixExpression());
        }

        return new ReadStatement(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), target);
    }
}