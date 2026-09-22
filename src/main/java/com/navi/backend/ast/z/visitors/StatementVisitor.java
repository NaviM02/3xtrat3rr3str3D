package com.navi.backend.ast.z.visitors;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.declarations.*;
import com.navi.backend.ast.z.expressions.*;
import com.navi.backend.ast.z.statements.*;
import com.navi.backend.lexer_parser.z.ZParser;

import java.util.ArrayList;
import java.util.List;

public class StatementVisitor extends ExpressionVisitor {

    @Override
    public AstZNode visitBlock(ZParser.BlockContext ctx) {
        List<Statement> statements = new ArrayList<>();
        for (ZParser.StatementContext statement : ctx.statement()) statements.add((Statement) visit(statement));
        return new BlockStatement(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), statements);
    }

    @Override
    public AstZNode visitVariableDeclarationStatement(ZParser.VariableDeclarationStatementContext ctx) {
        return visitVariableDeclarationCore(ctx.type(), ctx.variableDeclaratorList());
    }

    private AstZNode visitVariableDeclarationCore(ZParser.TypeContext typeCtx, ZParser.VariableDeclaratorListContext listCtx) {
        return new VariableDeclaration(
                typeCtx.getStart().getLine(), typeCtx.getStart().getCharPositionInLine(),
                (ZType) visit(typeCtx),
                visitVariableDeclarators(listCtx)
        );
    }

    private List<VariableDeclarator> visitVariableDeclarators(ZParser.VariableDeclaratorListContext ctx) {
        List<VariableDeclarator> variables = new ArrayList<>();
        for (ZParser.VariableDeclaratorContext declarator : ctx.variableDeclarator())
            variables.add((VariableDeclarator) visit(declarator));
        return variables;
    }

    @Override
    public AstZNode visitExpressionStatement(ZParser.ExpressionStatementContext ctx) {
        return new ExpressionStatement(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), (Expression) visit(ctx.expression()));
    }

    @Override
    public AstZNode visitIfStatement(ZParser.IfStatementContext ctx) {
        List<ElseIfClause> elseIfs = new ArrayList<>();
        List<ZParser.ExpressionContext> expressions = ctx.expression();
        List<ZParser.StatementOrBlockContext> branches = ctx.statementOrBlock();

        AstZNode thenBranch = visit(branches.get(0));

        for (int i = 1; i < expressions.size(); i++) {
            elseIfs.add(new ElseIfClause(
                    expressions.get(i).getStart().getLine(),
                    expressions.get(i).getStart().getCharPositionInLine(),
                    (Expression) visit(expressions.get(i)),
                    visit(branches.get(i))
            ));
        }

        AstZNode elseBranch = null;
        if (branches.size() > expressions.size()) {
            elseBranch = visit(branches.get(branches.size() - 1));
        }

        return new IfStatement(
                ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine(),
                (Expression) visit(expressions.get(0)),
                thenBranch,
                elseIfs,
                elseBranch
        );
    }

    @Override
    public AstZNode visitSwitchStatement(ZParser.SwitchStatementContext ctx) {
        List<SwitchCase> cases = new ArrayList<>();
        for (ZParser.SwitchCaseContext c : ctx.switchCase()) cases.add((SwitchCase) visit(c));
        DefaultCase defaultCase = ctx.defaultCase() != null ? (DefaultCase) visit(ctx.defaultCase()) : null;
        return new SwitchStatement(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
                (Expression) visit(ctx.expression()), cases, defaultCase);
    }

    @Override
    public AstZNode visitSwitchCase(ZParser.SwitchCaseContext ctx) {
        List<Statement> statements = new ArrayList<>();
        for (ZParser.StatementContext s : ctx.statement()) statements.add((Statement) visit(s));
        return new SwitchCase(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
                (Expression) visit(ctx.expression()), statements);
    }

    @Override
    public AstZNode visitDefaultCase(ZParser.DefaultCaseContext ctx) {
        List<Statement> statements = new ArrayList<>();
        for (ZParser.StatementContext s : ctx.statement()) statements.add((Statement) visit(s));
        return new DefaultCase(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), statements);
    }

    @Override
    public AstZNode visitForStatement(ZParser.ForStatementContext ctx) {
        AstZNode initializer = ctx.forInitializer() != null ? visit(ctx.forInitializer()) : null;
        Expression condition = ctx.expression() != null ? (Expression) visit(ctx.expression()) : null;
        ExpressionList update = ctx.forUpdate() != null ? (ExpressionList) visit(ctx.forUpdate()) : null;

        BlockStatement block = (BlockStatement) visit(ctx.block());

        return new ForStatement(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), initializer, condition, update, block);
    }

    @Override
    public AstZNode visitForInitializer(ZParser.ForInitializerContext ctx) {
        return ctx.variableDeclarationStatementNoSemi() != null ? visit(ctx.variableDeclarationStatementNoSemi()) : visit(ctx.expressionList());
    }

    @Override
    public AstZNode visitVariableDeclarationStatementNoSemi(ZParser.VariableDeclarationStatementNoSemiContext ctx) {
        return visitVariableDeclarationCore(ctx.type(), ctx.variableDeclaratorList());
    }

    @Override
    public AstZNode visitExpressionList(ZParser.ExpressionListContext ctx) {
        List<Expression> expressions = new ArrayList<>();
        for (ZParser.ExpressionContext expression : ctx.expression()) {
            expressions.add((Expression) visit(expression));
        }
        return new ExpressionList(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), expressions);
    }

    @Override
    public AstZNode visitWhileStatement(ZParser.WhileStatementContext ctx) {
        return new WhileStatement(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
                (Expression) visit(ctx.expression()), visit(ctx.block()));
    }

    @Override
    public AstZNode visitDoWhileStatement(ZParser.DoWhileStatementContext ctx) {
        return new DoWhileStatement(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
                visit(ctx.block()), (Expression) visit(ctx.expression()));
    }

    @Override
    public AstZNode visitReturnStatement(ZParser.ReturnStatementContext ctx) {
        return new ReturnStatement(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
                ctx.expression() != null ? (Expression) visit(ctx.expression()) : null);
    }

    @Override
    public AstZNode visitBreakStatement(ZParser.BreakStatementContext ctx) {
        return new BreakStatement(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
    }

    @Override
    public AstZNode visitContinueStatement(ZParser.ContinueStatementContext ctx) {
        return new ContinueStatement(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
    }

    private List<Expression> visitArguments(ZParser.ArgumentListContext ctx) {
        List<Expression> result = new ArrayList<>();
        if (ctx != null) for (ZParser.ExpressionContext e : ctx.expression()) result.add((Expression) visit(e));
        return result;
    }

    @Override
    public AstZNode visitPrintStatement(ZParser.PrintStatementContext ctx) {
        return new PrintStatement(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), visitArguments(ctx.argumentList()));
    }

    @Override
    public AstZNode visitPrintlnStatement(ZParser.PrintlnStatementContext ctx) {
        return new PrintlnStatement(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), visitArguments(ctx.argumentList()));
    }

    @Override
    public AstZNode visitReadlnStatement(ZParser.ReadlnStatementContext ctx) {
        return new ReadlnStatement(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
    }
}
