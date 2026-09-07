package com.navi.backend.ast.lat.visitors;

import com.navi.backend.ast.lat.AstNode;
import com.navi.backend.ast.lat.expressions.*;
import com.navi.backend.ast.lat.expressions.literals.*;
import com.navi.backend.lexer_parser.lat.PigLatinBaseVisitor;
import com.navi.backend.lexer_parser.lat.PigLatinParser;

import java.util.ArrayList;
import java.util.List;

public class ExpressionVisitor extends PigLatinBaseVisitor<AstNode> {
    @Override
    public AstNode visitNumberLiteralExpr(PigLatinParser.NumberLiteralExprContext ctx) {
        return new NumberLiteral(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), Integer.parseInt(ctx.NUMBER().getText()));
    }

    @Override
    public AstNode visitDecimalLiteralExpr(PigLatinParser.DecimalLiteralExprContext ctx) {
        return new DecimalLiteral(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), Double.parseDouble(ctx.DECIMAL().getText()));
    }

    @Override
    public AstNode visitStringLiteralExpr(PigLatinParser.StringLiteralExprContext ctx) {
        String text = ctx.STRING().getText();
        text = text.substring(1, text.length() - 1);
        return new StringLiteral(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), text);
    }

    @Override
    public AstNode visitCharLiteralExpr(PigLatinParser.CharLiteralExprContext ctx) {
        char c = ctx.CHAR().getText().charAt(1);
        return new CharLiteral(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), c);
    }

    @Override
    public AstNode visitTrueLiteralExpr(PigLatinParser.TrueLiteralExprContext ctx) {
        return new BooleanLiteral(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), true);
    }

    @Override
    public AstNode visitFalseLiteralExpr(PigLatinParser.FalseLiteralExprContext ctx) {
        return new BooleanLiteral(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), false);
    }

    @Override
    public AstNode visitVariableExpr(PigLatinParser.VariableExprContext ctx) {
        return new VariableExpression(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.ID().getText());
    }

    @Override
    public AstNode visitParenthesizedExpr(PigLatinParser.ParenthesizedExprContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public AstNode visitToLogicalAndExpr(PigLatinParser.ToLogicalAndExprContext ctx) {
        return visit(ctx.logicalAndExpression());
    }

    @Override
    public AstNode visitToEqualityExpr(PigLatinParser.ToEqualityExprContext ctx) {
        return visit(ctx.equalityExpression());
    }

    @Override
    public AstNode visitToComparisonExpr(PigLatinParser.ToComparisonExprContext ctx) {
        return visit(ctx.comparisonExpression());
    }

    @Override
    public AstNode visitToAdditiveExpr(PigLatinParser.ToAdditiveExprContext ctx) {
        return visit(ctx.additiveExpression());
    }

    @Override
    public AstNode visitToMultiplicativeExpr(PigLatinParser.ToMultiplicativeExprContext ctx) {
        return visit(ctx.multiplicativeExpression());
    }

    @Override
    public AstNode visitToUnaryExpr(PigLatinParser.ToUnaryExprContext ctx) {
        return visit(ctx.unaryExpression());
    }

    @Override
    public AstNode visitOrExpr(PigLatinParser.OrExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.logicalOrExpression()),
            BinaryOperator.OR,
            (Expression) visit(ctx.logicalAndExpression())
        );
    }

    @Override
    public AstNode visitAndExpr(PigLatinParser.AndExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.logicalAndExpression()),
            BinaryOperator.AND,
            (Expression) visit(ctx.equalityExpression())
        );
    }

    @Override
    public AstNode visitEqualExpr(PigLatinParser.EqualExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.equalityExpression()),
            BinaryOperator.EQUAL,
            (Expression) visit(ctx.comparisonExpression())
        );
    }

    @Override
    public AstNode visitNotEqualExpr(PigLatinParser.NotEqualExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.equalityExpression()),
            BinaryOperator.NOT_EQUAL,
            (Expression) visit(ctx.comparisonExpression())
        );
    }

    @Override
    public AstNode visitLessExpr(PigLatinParser.LessExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.comparisonExpression()),
            BinaryOperator.LESS,
            (Expression) visit(ctx.additiveExpression())
        );
    }

    @Override
    public AstNode visitGreaterExpr(PigLatinParser.GreaterExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.comparisonExpression()),
            BinaryOperator.GREATER,
            (Expression) visit(ctx.additiveExpression())
        );
    }

    @Override
    public AstNode visitLessEqualExpr(PigLatinParser.LessEqualExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.comparisonExpression()),
            BinaryOperator.LESS_EQUAL,
            (Expression) visit(ctx.additiveExpression())
        );
    }

    @Override
    public AstNode visitGreaterEqualExpr(PigLatinParser.GreaterEqualExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.comparisonExpression()),
            BinaryOperator.GREATER_EQUAL,
            (Expression) visit(ctx.additiveExpression())
        );
    }

    @Override
    public AstNode visitAdditionExpr(PigLatinParser.AdditionExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.additiveExpression()),
            BinaryOperator.ADD,
            (Expression) visit(ctx.multiplicativeExpression())
        );
    }

    @Override
    public AstNode visitSubtractionExpr(PigLatinParser.SubtractionExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.additiveExpression()),
            BinaryOperator.SUBTRACT,
            (Expression) visit(ctx.multiplicativeExpression())
        );
    }

    @Override
    public AstNode visitMultiplicationExpr(PigLatinParser.MultiplicationExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.multiplicativeExpression()),
            BinaryOperator.MULTIPLY,
            (Expression) visit(ctx.unaryExpression())
        );
    }

    @Override
    public AstNode visitDivisionExpr(PigLatinParser.DivisionExprContext ctx) {
        return new BinaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.multiplicativeExpression()),
            BinaryOperator.DIVIDE,
            (Expression) visit(ctx.unaryExpression())
        );
    }

    @Override
    public AstNode visitNotExpr(PigLatinParser.NotExprContext ctx) {
        return new UnaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            UnaryOperator.NOT,
            (Expression) visit(ctx.unaryExpression())
        );
    }

    @Override
    public AstNode visitNegateExpr(PigLatinParser.NegateExprContext ctx) {
        return new UnaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            UnaryOperator.NEGATE,
            (Expression) visit(ctx.unaryExpression())
        );
    }

    @Override
    public AstNode visitPostIncrementExpr(PigLatinParser.PostIncrementExprContext ctx) {
        return new UnaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            UnaryOperator.POST_INCREMENT,
            (Expression) visit(ctx.postfixExpression())
        );
    }

    @Override
    public AstNode visitPostDecrementExpr(PigLatinParser.PostDecrementExprContext ctx) {
        return new UnaryExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            UnaryOperator.POST_DECREMENT,
            (Expression) visit(ctx.postfixExpression())
        );
    }

    @Override
    public AstNode visitArrayAccessExpr(PigLatinParser.ArrayAccessExprContext ctx) {
        return new ArrayAccessExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.postfixExpression()),
            (Expression) visit(ctx.expression())
        );
    }

    @Override
    public AstNode visitMemberAccessExpr(PigLatinParser.MemberAccessExprContext ctx) {
        return new MemberAccessExpression(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.postfixExpression()),
            ctx.ID().getText()
        );
    }

    @Override
    public AstNode visitFunctionCallExpr(PigLatinParser.FunctionCallExprContext ctx) {
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
    public AstNode visitToPrimaryExpr(PigLatinParser.ToPrimaryExprContext ctx) {
        return visit(ctx.primaryExpression());
    }
}
