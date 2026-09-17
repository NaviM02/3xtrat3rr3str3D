package com.navi.backend.ast.y.visitors;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.expressions.*;
import com.navi.backend.ast.y.expressions.literals.LiteralExpression;
import com.navi.backend.lexer_parser.y.YParserBaseVisitor;
import com.navi.backend.lexer_parser.y.YParser;

import java.util.ArrayList;
import java.util.List;

public class ExpressionVisitor extends YParserBaseVisitor<AstYNode> {

    @Override
    public AstYNode visitIntegerLiteralExpr(YParser.IntegerLiteralExprContext ctx) {
        return new LiteralExpression(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            Integer.parseInt(ctx.INTEGER_LITERAL().getText())
        );
    }

    @Override
    public AstYNode visitFloatLiteralExpr(YParser.FloatLiteralExprContext ctx) {
        return new LiteralExpression(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            Double.parseDouble(ctx.FLOAT_LITERAL().getText())
        );
    }

    @Override
    public AstYNode visitCharLiteralExpr(YParser.CharLiteralExprContext ctx) {
        String text = ctx.CHAR_LITERAL().getText();

        return new LiteralExpression(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            text.substring(1, text.length() - 1).charAt(0)
        );
    }

    @Override
    public AstYNode visitStringLiteralExpr(YParser.StringLiteralExprContext ctx) {
        String text = ctx.STRING_LITERAL().getText();

        return new LiteralExpression(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            text.substring(1, text.length() - 1)
        );
    }

    @Override
    public AstYNode visitTrueLiteralExpr(YParser.TrueLiteralExprContext ctx) {
        return new LiteralExpression(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            true
        );
    }

    @Override
    public AstYNode visitFalseLiteralExpr(YParser.FalseLiteralExprContext ctx) {
        return new LiteralExpression(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            false
        );
    }

    @Override
    public AstYNode visitVariableExpr(YParser.VariableExprContext ctx) {
        return new VariableExpression(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.ID().getText()
        );
    }

    @Override
    public AstYNode visitReadExpr(YParser.ReadExprContext ctx) {
        return new ReadExpression(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine()
        );
    }

    @Override
    public AstYNode visitParenthesizedExpr(YParser.ParenthesizedExprContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public AstYNode visitToLogicalAndExpr(YParser.ToLogicalAndExprContext ctx) {
        return visit(ctx.logicalAndExpression());
    }

    @Override
    public AstYNode visitToEqualityExpr(YParser.ToEqualityExprContext ctx) {
        return visit(ctx.equalityExpression());
    }

    @Override
    public AstYNode visitToComparisonExpr(YParser.ToComparisonExprContext ctx) {
        return visit(ctx.comparisonExpression());
    }

    @Override
    public AstYNode visitToAdditiveExpr(YParser.ToAdditiveExprContext ctx) {
        return visit(ctx.additiveExpression());
    }

    @Override
    public AstYNode visitToMultiplicativeExpr(YParser.ToMultiplicativeExprContext ctx) {
        return visit(ctx.multiplicativeExpression());
    }

    @Override
    public AstYNode visitToUnaryExpr(YParser.ToUnaryExprContext ctx) {
        return visit(ctx.unaryExpression());
    }

    @Override
    public AstYNode visitToPostfixExpr(YParser.ToPostfixExprContext ctx) {
        return visit(ctx.postfixExpression());
    }

    @Override
    public AstYNode visitOrExpr(YParser.OrExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.logicalOrExpression()),
            BinaryOperator.OR,
            (Expression) visit(ctx.logicalAndExpression())
        );
    }

    @Override
    public AstYNode visitAndExpr(YParser.AndExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.logicalAndExpression()),
            BinaryOperator.AND,
            (Expression) visit(ctx.equalityExpression())
        );
    }

    @Override
    public AstYNode visitEqualExpr(YParser.EqualExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.equalityExpression()),
            BinaryOperator.EQUAL,
            (Expression) visit(ctx.comparisonExpression())
        );
    }

    @Override
    public AstYNode visitNotEqualExpr(YParser.NotEqualExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.equalityExpression()),
            BinaryOperator.NOT_EQUAL,
            (Expression) visit(ctx.comparisonExpression())
        );
    }

    @Override
    public AstYNode visitLessExpr(YParser.LessExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.comparisonExpression()),
            BinaryOperator.LESS,
            (Expression) visit(ctx.additiveExpression())
        );
    }

    @Override
    public AstYNode visitGreaterExpr(YParser.GreaterExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.comparisonExpression()),
            BinaryOperator.GREATER,
            (Expression) visit(ctx.additiveExpression())
        );
    }

    @Override
    public AstYNode visitLessEqualExpr(YParser.LessEqualExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.comparisonExpression()),
            BinaryOperator.LESS_EQUAL,
            (Expression) visit(ctx.additiveExpression())
        );
    }

    @Override
    public AstYNode visitGreaterEqualExpr(YParser.GreaterEqualExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.comparisonExpression()),
            BinaryOperator.GREATER_EQUAL,
            (Expression) visit(ctx.additiveExpression())
        );
    }

    @Override
    public AstYNode visitAdditionExpr(YParser.AdditionExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.additiveExpression()),
            BinaryOperator.ADD,
            (Expression) visit(ctx.multiplicativeExpression())
        );
    }

    @Override
    public AstYNode visitSubtractionExpr(YParser.SubtractionExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.additiveExpression()),
            BinaryOperator.SUBTRACT,
            (Expression) visit(ctx.multiplicativeExpression())
        );
    }

    @Override
    public AstYNode visitMultiplicationExpr(YParser.MultiplicationExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.multiplicativeExpression()),
            BinaryOperator.MULTIPLY,
            (Expression) visit(ctx.unaryExpression())
        );
    }

    @Override
    public AstYNode visitDivisionExpr(YParser.DivisionExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.multiplicativeExpression()),
            BinaryOperator.DIVIDE,
            (Expression) visit(ctx.unaryExpression())
        );
    }

    @Override
    public AstYNode visitModuloExpr(YParser.ModuloExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.multiplicativeExpression()),
            BinaryOperator.MODULO,
            (Expression) visit(ctx.unaryExpression())
        );
    }

    @Override
    public AstYNode visitNotExpr(YParser.NotExprContext ctx) {
        return new UnaryExpression(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            UnaryOperator.NOT,
            (Expression) visit(ctx.unaryExpression())
        );
    }

    @Override
    public AstYNode visitNegateExpr(YParser.NegateExprContext ctx) {
        return new UnaryExpression(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            UnaryOperator.NEGATE,
            (Expression) visit(ctx.unaryExpression())
        );
    }

    @Override
    public AstYNode visitPostfixExpression(YParser.PostfixExpressionContext ctx) {
        Expression expression = (Expression) visit(ctx.primaryExpression());

        for (YParser.PostfixOperationContext operation : ctx.postfixOperation()) {
            expression = (Expression) visitPostfixOperation(operation, expression);
        }

        return expression;
    }

    private AstYNode visitPostfixOperation(
        YParser.PostfixOperationContext ctx,
        Expression expression
    ) {
        if (ctx instanceof YParser.ArrayAccessOpContext op) {
            return new ArrayAccessExpression(
                op.getStart().getLine(),
                op.getStart().getCharPositionInLine(),
                expression,
                (Expression) visit(op.expression())
            );
        }

        if (ctx instanceof YParser.MemberAccessOpContext op) {
            return new MemberAccessExpression(
                op.getStart().getLine(),
                op.getStart().getCharPositionInLine(),
                expression,
                op.ID().getText()
            );
        }

        if (ctx instanceof YParser.FunctionCallOpContext op) {
            List<Expression> arguments = new ArrayList<>();

            if (op.argumentList() != null) {
                for (YParser.ExpressionContext expr : op.argumentList().expression()) {
                    arguments.add((Expression) visit(expr));
                }
            }

            return new FunctionCallExpression(
                op.getStart().getLine(),
                op.getStart().getCharPositionInLine(),
                expression,
                arguments
            );
        }

        if (ctx instanceof YParser.PostIncrementOpContext op) {
            return new UnaryExpression(
                op.getStart().getLine(),
                op.getStart().getCharPositionInLine(),
                UnaryOperator.POST_INCREMENT,
                expression
            );
        }

        if (ctx instanceof YParser.PostDecrementOpContext op) {
            return new UnaryExpression(
                op.getStart().getLine(),
                op.getStart().getCharPositionInLine(),
                UnaryOperator.POST_DECREMENT,
                expression
            );
        }

        return expression;
    }
}