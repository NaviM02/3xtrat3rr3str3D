package com.navi.backend.ast.lat.visitors;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.expressions.*;
import com.navi.backend.ast.lat.expressions.literals.*;
import com.navi.backend.lexer_parser.lat.PigLatinBaseVisitor;
import com.navi.backend.lexer_parser.lat.PigLatinParser;

import java.util.ArrayList;
import java.util.List;

public class ExpressionVisitor extends PigLatinBaseVisitor<AstLatNode> {
    @Override
    public AstLatNode visitNumberLiteralExpr(PigLatinParser.NumberLiteralExprContext ctx) {
        return new NumberLiteral(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), Integer.parseInt(ctx.NUMBER().getText()));
    }

    @Override
    public AstLatNode visitDecimalLiteralExpr(PigLatinParser.DecimalLiteralExprContext ctx) {
        return new DecimalLiteral(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), Double.parseDouble(ctx.DECIMAL().getText()));
    }

    @Override
    public AstLatNode visitStringLiteralExpr(PigLatinParser.StringLiteralExprContext ctx) {
        String text = ctx.STRING().getText();
        text = text.substring(1, text.length() - 1);
        return new StringLiteral(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), text);
    }

    @Override
    public AstLatNode visitCharLiteralExpr(PigLatinParser.CharLiteralExprContext ctx) {
        char c = ctx.CHAR().getText().charAt(1);
        return new CharLiteral(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), c);
    }

    @Override
    public AstLatNode visitTrueLiteralExpr(PigLatinParser.TrueLiteralExprContext ctx) {
        return new BooleanLiteral(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), true);
    }

    @Override
    public AstLatNode visitFalseLiteralExpr(PigLatinParser.FalseLiteralExprContext ctx) {
        return new BooleanLiteral(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), false);
    }

    @Override
    public AstLatNode visitVariableExpr(PigLatinParser.VariableExprContext ctx) {
        return new VariableExpression(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.ID().getText());
    }

    @Override
    public AstLatNode visitParenthesizedExpr(PigLatinParser.ParenthesizedExprContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public AstLatNode visitToLogicalAndExpr(PigLatinParser.ToLogicalAndExprContext ctx) {
        return visit(ctx.logicalAndExpression());
    }

    @Override
    public AstLatNode visitToEqualityExpr(PigLatinParser.ToEqualityExprContext ctx) {
        return visit(ctx.equalityExpression());
    }

    @Override
    public AstLatNode visitToComparisonExpr(PigLatinParser.ToComparisonExprContext ctx) {
        return visit(ctx.comparisonExpression());
    }

    @Override
    public AstLatNode visitToAdditiveExpr(PigLatinParser.ToAdditiveExprContext ctx) {
        return visit(ctx.additiveExpression());
    }

    @Override
    public AstLatNode visitToMultiplicativeExpr(PigLatinParser.ToMultiplicativeExprContext ctx) {
        return visit(ctx.multiplicativeExpression());
    }

    @Override
    public AstLatNode visitToUnaryExpr(PigLatinParser.ToUnaryExprContext ctx) {
        return visit(ctx.unaryExpression());
    }

    @Override
    public AstLatNode visitOrExpr(PigLatinParser.OrExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.logicalOrExpression()),
            BinaryOperator.OR,
            (Expression) visit(ctx.logicalAndExpression())
        );
    }

    @Override
    public AstLatNode visitAndExpr(PigLatinParser.AndExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.logicalAndExpression()),
            BinaryOperator.AND,
            (Expression) visit(ctx.equalityExpression())
        );
    }

    @Override
    public AstLatNode visitEqualExpr(PigLatinParser.EqualExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.equalityExpression()),
            BinaryOperator.EQUAL,
            (Expression) visit(ctx.comparisonExpression())
        );
    }

    @Override
    public AstLatNode visitNotEqualExpr(PigLatinParser.NotEqualExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.equalityExpression()),
            BinaryOperator.NOT_EQUAL,
            (Expression) visit(ctx.comparisonExpression())
        );
    }

    @Override
    public AstLatNode visitLessExpr(PigLatinParser.LessExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.comparisonExpression()),
            BinaryOperator.LESS,
            (Expression) visit(ctx.additiveExpression())
        );
    }

    @Override
    public AstLatNode visitGreaterExpr(PigLatinParser.GreaterExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.comparisonExpression()),
            BinaryOperator.GREATER,
            (Expression) visit(ctx.additiveExpression())
        );
    }

    @Override
    public AstLatNode visitLessEqualExpr(PigLatinParser.LessEqualExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.comparisonExpression()),
            BinaryOperator.LESS_EQUAL,
            (Expression) visit(ctx.additiveExpression())
        );
    }

    @Override
    public AstLatNode visitGreaterEqualExpr(PigLatinParser.GreaterEqualExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.comparisonExpression()),
            BinaryOperator.GREATER_EQUAL,
            (Expression) visit(ctx.additiveExpression())
        );
    }

    @Override
    public AstLatNode visitAdditionExpr(PigLatinParser.AdditionExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.additiveExpression()),
            BinaryOperator.ADD,
            (Expression) visit(ctx.multiplicativeExpression())
        );
    }

    @Override
    public AstLatNode visitSubtractionExpr(PigLatinParser.SubtractionExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.additiveExpression()),
            BinaryOperator.SUBTRACT,
            (Expression) visit(ctx.multiplicativeExpression())
        );
    }

    @Override
    public AstLatNode visitMultiplicationExpr(PigLatinParser.MultiplicationExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.multiplicativeExpression()),
            BinaryOperator.MULTIPLY,
            (Expression) visit(ctx.unaryExpression())
        );
    }

    @Override
    public AstLatNode visitDivisionExpr(PigLatinParser.DivisionExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.multiplicativeExpression()),
            BinaryOperator.DIVIDE,
            (Expression) visit(ctx.unaryExpression())
        );
    }

    @Override
    public AstLatNode visitNotExpr(PigLatinParser.NotExprContext ctx) {
        return new UnaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            UnaryOperator.NOT,
            (Expression) visit(ctx.unaryExpression())
        );
    }

    @Override
    public AstLatNode visitNegateExpr(PigLatinParser.NegateExprContext ctx) {
        return new UnaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            UnaryOperator.NEGATE,
            (Expression) visit(ctx.unaryExpression())
        );
    }

    @Override
    public AstLatNode visitPostIncrementExpr(PigLatinParser.PostIncrementExprContext ctx) {
        return new UnaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            UnaryOperator.POST_INCREMENT,
            (Expression) visit(ctx.postfixExpression())
        );
    }

    @Override
    public AstLatNode visitPostDecrementExpr(PigLatinParser.PostDecrementExprContext ctx) {
        return new UnaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            UnaryOperator.POST_DECREMENT,
            (Expression) visit(ctx.postfixExpression())
        );
    }

    @Override
    public AstLatNode visitArrayAccessExpr(PigLatinParser.ArrayAccessExprContext ctx) {
        return new ArrayAccessExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.postfixExpression()),
            (Expression) visit(ctx.expression())
        );
    }

    @Override
    public AstLatNode visitMemberAccessExpr(PigLatinParser.MemberAccessExprContext ctx) {
        return new MemberAccessExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.postfixExpression()),
            ctx.ID().getText()
        );
    }

    @Override
    public AstLatNode visitFunctionCallExpr(PigLatinParser.FunctionCallExprContext ctx) {
        List<Expression> arguments = new ArrayList<>();

        if (ctx.functionArguments().argumentList() != null) {
            for (PigLatinParser.ExpressionContext expr : ctx.functionArguments().argumentList().expression()) {
                arguments.add((Expression) visit(expr));
            }
        }

        return new FunctionCallExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.postfixExpression()),
            arguments
        );
    }

    @Override
    public AstLatNode visitToPrimaryExpr(PigLatinParser.ToPrimaryExprContext ctx) {
        return visit(ctx.primaryExpression());
    }
}
