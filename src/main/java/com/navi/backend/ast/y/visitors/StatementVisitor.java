package com.navi.backend.ast.y.visitors;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.expressions.Expression;
import com.navi.backend.ast.y.statements.*;
import com.navi.backend.lexer_parser.y.YParser;

import java.util.ArrayList;
import java.util.List;

public class StatementVisitor extends ExpressionVisitor {

    @Override
    public AstYNode visitVariableDeclarationStmt(YParser.VariableDeclarationStmtContext ctx) {
        return visit(ctx.variableDeclaration());
    }

    @Override
    public AstYNode visitStructureDeclarationStmt(YParser.StructureDeclarationStmtContext ctx) {
        return visit(ctx.structureDeclaration());
    }

    @Override
    public AstYNode visitAssignmentStmt(YParser.AssignmentStmtContext ctx) {
        return visit(ctx.assignmentStatement());
    }

    @Override
    public AstYNode visitAssignmentStatement(YParser.AssignmentStatementContext ctx) {
        Expression left = (Expression) visit(ctx.assignableExpression());
        Expression right = (Expression) visit(ctx.expression());

        AssignmentOperator operator;

        if (ctx.assignmentOperator() instanceof YParser.AssignOperatorContext) {
            operator = AssignmentOperator.ASSIGN;
        } else if (ctx.assignmentOperator() instanceof YParser.PlusAssignOperatorContext) {
            operator = AssignmentOperator.PLUS_ASSIGN;
        } else if (ctx.assignmentOperator() instanceof YParser.MinusAssignOperatorContext) {
            operator = AssignmentOperator.MINUS_ASSIGN;
        } else {
            operator = AssignmentOperator.MULT_ASSIGN;
        }

        return new AssignmentStatement(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            left,
            operator,
            right
        );
    }

    @Override
    public AstYNode visitIncrementStmt(YParser.IncrementStmtContext ctx) {
        return visit(ctx.incrementStatement());
    }

    @Override
    public AstYNode visitIncrementStatement(YParser.IncrementStatementContext ctx) {
        Expression target = (Expression) visit(ctx.assignableExpression());
        boolean increment = ctx.INCREMENT() != null;

        return new IncrementStatement(
                ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine(),
                target,
                increment
        );
    }

    @Override
    public AstYNode visitIfStmt(YParser.IfStmtContext ctx) {
        return visit(ctx.ifStatement());
    }

    @Override
    public AstYNode visitIfStatement(YParser.IfStatementContext ctx) {
        List<Statement> statements = new ArrayList<>();

        for (YParser.StatementContext statement : ctx.statement()) {
            statements.add((Statement) visit(statement));
        }

        List<ElseIfClause> elseIfClauses = new ArrayList<>();

        for (YParser.ElseIfClauseContext elseIf : ctx.elseIfClause()) {
            elseIfClauses.add((ElseIfClause) visit(elseIf));
        }

        ElseClause elseClause = null;

        if (ctx.elseClause() != null) {
            elseClause = (ElseClause) visit(ctx.elseClause());
        }

        return new IfStatement(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.expression()),
            statements,
            elseIfClauses,
            elseClause
        );
    }

    @Override
    public AstYNode visitElseIfClause(YParser.ElseIfClauseContext ctx) {
        List<Statement> statements = new ArrayList<>();

        for (YParser.StatementContext statement : ctx.statement()) {
            statements.add((Statement) visit(statement));
        }

        return new ElseIfClause(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.expression()),
            statements
        );
    }

    @Override
    public AstYNode visitElseClause(YParser.ElseClauseContext ctx) {
        List<Statement> statements = new ArrayList<>();

        for (YParser.StatementContext statement : ctx.statement()) {
            statements.add((Statement) visit(statement));
        }

        return new ElseClause(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            statements
        );
    }

    @Override
    public AstYNode visitSwitchStmt(YParser.SwitchStmtContext ctx) {
        return visit(ctx.switchStatement());
    }

    @Override
    public AstYNode visitSwitchStatement(YParser.SwitchStatementContext ctx) {
        List<SwitchCase> cases = new ArrayList<>();

        for (YParser.SwitchCaseContext caseCtx : ctx.switchCase()) {
            cases.add((SwitchCase) visit(caseCtx));
        }

        DefaultCase defaultCase = null;

        if (ctx.defaultCase() != null) {
            defaultCase = (DefaultCase) visit(ctx.defaultCase());
        }

        return new SwitchStatement(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.expression()),
            cases,
            defaultCase
        );
    }

    @Override
    public AstYNode visitSwitchCase(YParser.SwitchCaseContext ctx) {
        List<Statement> statements = new ArrayList<>();

        for (YParser.StatementContext statement : ctx.statement()) {
            statements.add((Statement) visit(statement));
        }

        BreakStatement breakStatement = (BreakStatement) visit(ctx.breakStatement());

        return new SwitchCase(
                ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine(),
                (Expression) visit(ctx.expression()),
                statements,
                breakStatement
        );
    }

    @Override
    public AstYNode visitDefaultCase(YParser.DefaultCaseContext ctx) {
        List<Statement> statements = new ArrayList<>();

        for (YParser.StatementContext statement : ctx.statement()) {
            statements.add((Statement) visit(statement));
        }

        BreakStatement breakStatement = (BreakStatement) visit(ctx.breakStatement());

        return new DefaultCase(
                ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine(),
                statements,
                breakStatement
        );
    }

    @Override
    public AstYNode visitForStmt(YParser.ForStmtContext ctx) {
        return visit(ctx.forStatement());
    }

    @Override
    public AstYNode visitForStatement(YParser.ForStatementContext ctx) {
        AstYNode initializer = null;

        if (ctx.forInitializer() != null) {
            initializer = visit(ctx.forInitializer());
        }

        Expression condition = null;

        if (ctx.expression() != null) {
            condition = (Expression) visit(ctx.expression());
        }

        Expression update = null;

        if (ctx.forUpdate() != null) {
            update = (Expression) visit(ctx.forUpdate());
        }

        List<Statement> statements = new ArrayList<>();

        for (YParser.StatementContext statement : ctx.statement()) {
            statements.add((Statement) visit(statement));
        }

        return new ForStatement(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            initializer,
            condition,
            update,
            statements
        );
    }

    @Override
    public AstYNode visitForInitializer(YParser.ForInitializerContext ctx) {
        if (ctx.variableDeclarationCore() != null) {
            return visit(ctx.variableDeclarationCore());
        }

        return visit(ctx.expression());
    }

    @Override
    public AstYNode visitWhileStmt(YParser.WhileStmtContext ctx) {
        return visit(ctx.whileStatement());
    }

    @Override
    public AstYNode visitWhileStatement(YParser.WhileStatementContext ctx) {
        List<Statement> statements = new ArrayList<>();

        for (YParser.StatementContext statement : ctx.statement()) {
            statements.add((Statement) visit(statement));
        }

        return new WhileStatement(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.expression()),
            statements
        );
    }

    @Override
    public AstYNode visitDoWhileStmt(YParser.DoWhileStmtContext ctx) {
        return visit(ctx.doWhileStatement());
    }

    @Override
    public AstYNode visitDoWhileStatement(YParser.DoWhileStatementContext ctx) {
        List<Statement> statements = new ArrayList<>();

        for (YParser.StatementContext statement : ctx.statement()) {
            statements.add((Statement) visit(statement));
        }

        return new DoWhileStatement(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            statements,
            (Expression) visit(ctx.expression())
        );
    }

    @Override
    public AstYNode visitBreakStmt(YParser.BreakStmtContext ctx) {
        return visit(ctx.breakStatement());
    }

    @Override
    public AstYNode visitBreakStatement(YParser.BreakStatementContext ctx) {
        return new BreakStatement(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine()
        );
    }

    @Override
    public AstYNode visitContinueStmt(YParser.ContinueStmtContext ctx) {
        return visit(ctx.continueStatement());
    }

    @Override
    public AstYNode visitContinueStatement(YParser.ContinueStatementContext ctx) {
        return new ContinueStatement(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine()
        );
    }

    @Override
    public AstYNode visitReturnStmt(YParser.ReturnStmtContext ctx) {
        return visit(ctx.returnStatement());
    }

    @Override
    public AstYNode visitReturnStatement(YParser.ReturnStatementContext ctx) {
        Expression expression = null;

        if (ctx.expression() != null) {
            expression = (Expression) visit(ctx.expression());
        }

        return new ReturnStatement(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            expression
        );
    }

    @Override
    public AstYNode visitPrintStmt(YParser.PrintStmtContext ctx) {
        return visit(ctx.printStatement());
    }

    @Override
    public AstYNode visitPrintStatement(YParser.PrintStatementContext ctx) {
        List<Expression> expressions = new ArrayList<>();

        if (ctx.expressionList() != null) {
            for (YParser.ExpressionContext expression : ctx.expressionList().expression()) {
                expressions.add((Expression) visit(expression));
            }
        }

        return new PrintStatement(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            expressions
        );
    }

    @Override
    public AstYNode visitReadStmt(YParser.ReadStmtContext ctx) {
        return visit(ctx.readStatement());
    }

    @Override
    public AstYNode visitReadStatement(YParser.ReadStatementContext ctx) {
        return new ReadStatement(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine()
        );
    }

    @Override
    public AstYNode visitExpressionStmt(YParser.ExpressionStmtContext ctx) {
        return new ExpressionStatement(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.expressionStatement())
        );
    }
}