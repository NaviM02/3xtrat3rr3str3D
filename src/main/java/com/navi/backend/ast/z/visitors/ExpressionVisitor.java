package com.navi.backend.ast.z.visitors;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.declarations.Type;
import com.navi.backend.ast.z.expressions.*;
import com.navi.backend.ast.z.expressions.literals.LiteralExpression;
import com.navi.backend.ast.z.statements.AssignmentOperator;
import com.navi.backend.lexer_parser.z.ZParser;
import com.navi.backend.lexer_parser.z.ZBaseVisitor;

import java.util.ArrayList;
import java.util.List;

public class ExpressionVisitor extends ZBaseVisitor<AstZNode> {

    @Override
    public AstZNode visitExpression(ZParser.ExpressionContext ctx) {
        return visit(ctx.assignmentExpression());
    }

    @Override
    public AstZNode visitToConditionalExpression(ZParser.ToConditionalExpressionContext ctx) {
        return visit(ctx.conditionalExpression());
    }

    @Override
    public AstZNode visitAssignmentExpressionValue(ZParser.AssignmentExpressionValueContext ctx) {
        return new AssignmentExpression(
                ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
                (Expression) visit(ctx.postfixExpression()),
                getAssignmentOperator(ctx.assignmentOperator()),
                (Expression) visit(ctx.assignmentExpression())
        );
    }

    private AssignmentOperator getAssignmentOperator(ZParser.AssignmentOperatorContext ctx) {
        if (ctx.ASSIGN() != null) return AssignmentOperator.ASSIGN;
        if (ctx.PLUS_ASSIGN() != null) return AssignmentOperator.PLUS_ASSIGN;
        if (ctx.MINUS_ASSIGN() != null) return AssignmentOperator.MINUS_ASSIGN;
        return AssignmentOperator.MULT_ASSIGN;
    }

    @Override
    public AstZNode visitLogicalOrConditionalExpression(ZParser.LogicalOrConditionalExpressionContext ctx) {
        return visit(ctx.logicalOrExpression());
    }

    @Override
    public AstZNode visitTernaryExpression(ZParser.TernaryExpressionContext ctx) {
        return new TernaryExpression(
                ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
                (Expression) visit(ctx.logicalOrExpression()),
                (Expression) visit(ctx.expression(0)),
                (Expression) visit(ctx.expression(1))
        );
    }

    @Override
    public AstZNode visitOrExpr(ZParser.OrExprContext ctx) {
        return new BinaryExpression(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
                (Expression) visit(ctx.logicalOrExpression()), BinaryOperator.OR,
                (Expression) visit(ctx.logicalAndExpression()));
    }

    @Override
    public AstZNode visitToLogicalAndExpr(ZParser.ToLogicalAndExprContext ctx) {
        return visit(ctx.logicalAndExpression());
    }

    @Override
    public AstZNode visitAndExpr(ZParser.AndExprContext ctx) {
        return new BinaryExpression(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
                (Expression) visit(ctx.logicalAndExpression()), BinaryOperator.AND,
                (Expression) visit(ctx.equalityExpression()));
    }

    @Override
    public AstZNode visitToEqualityExpr(ZParser.ToEqualityExprContext ctx) {
        return visit(ctx.equalityExpression());
    }

    @Override
    public AstZNode visitEqualExpr(ZParser.EqualExprContext ctx) {
        return binary(ctx, BinaryOperator.EQUAL, ctx.equalityExpression(), ctx.comparisonExpression());
    }

    @Override
    public AstZNode visitNotEqualExpr(ZParser.NotEqualExprContext ctx) {
        return binary(ctx, BinaryOperator.NOT_EQUAL, ctx.equalityExpression(), ctx.comparisonExpression());
    }

    @Override
    public AstZNode visitToComparisonExpr(ZParser.ToComparisonExprContext ctx) {
        return visit(ctx.comparisonExpression());
    }

    @Override
    public AstZNode visitLessExpr(ZParser.LessExprContext ctx) {
        return binary(ctx, BinaryOperator.LESS, ctx.comparisonExpression(), ctx.additiveExpression());
    }

    @Override
    public AstZNode visitGreaterExpr(ZParser.GreaterExprContext ctx) {
        return binary(ctx, BinaryOperator.GREATER, ctx.comparisonExpression(), ctx.additiveExpression());
    }

    @Override
    public AstZNode visitLessEqualExpr(ZParser.LessEqualExprContext ctx) {
        return binary(ctx, BinaryOperator.LESS_EQUAL, ctx.comparisonExpression(), ctx.additiveExpression());
    }

    @Override
    public AstZNode visitGreaterEqualExpr(ZParser.GreaterEqualExprContext ctx) {
        return binary(ctx, BinaryOperator.GREATER_EQUAL, ctx.comparisonExpression(), ctx.additiveExpression());
    }

    @Override
    public AstZNode visitToAdditiveExpr(ZParser.ToAdditiveExprContext ctx) {
        return visit(ctx.additiveExpression());
    }

    @Override
    public AstZNode visitAdditionExpr(ZParser.AdditionExprContext ctx) {
        return binary(ctx, BinaryOperator.ADD, ctx.additiveExpression(), ctx.multiplicativeExpression());
    }

    @Override
    public AstZNode visitSubtractionExpr(ZParser.SubtractionExprContext ctx) {
        return binary(ctx, BinaryOperator.SUBTRACT, ctx.additiveExpression(), ctx.multiplicativeExpression());
    }

    @Override
    public AstZNode visitToMultiplicativeExpr(ZParser.ToMultiplicativeExprContext ctx) {
        return visit(ctx.multiplicativeExpression());
    }

    @Override
    public AstZNode visitMultiplicationExpr(ZParser.MultiplicationExprContext ctx) {
        return binary(ctx, BinaryOperator.MULTIPLY, ctx.multiplicativeExpression(), ctx.unaryExpression());
    }

    @Override
    public AstZNode visitDivisionExpr(ZParser.DivisionExprContext ctx) {
        return binary(ctx, BinaryOperator.DIVIDE, ctx.multiplicativeExpression(), ctx.unaryExpression());
    }

    @Override
    public AstZNode visitModuloExpr(ZParser.ModuloExprContext ctx) {
        return binary(ctx, BinaryOperator.MODULO, ctx.multiplicativeExpression(), ctx.unaryExpression());
    }

    @Override
    public AstZNode visitToUnaryExpr(ZParser.ToUnaryExprContext ctx) {
        return visit(ctx.unaryExpression());
    }

    private AstZNode binary(
            org.antlr.v4.runtime.ParserRuleContext ctx, BinaryOperator operator,
            org.antlr.v4.runtime.ParserRuleContext left, org.antlr.v4.runtime.ParserRuleContext right) {
        return new BinaryExpression(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
                (Expression) visit(left), operator, (Expression) visit(right));
    }

    @Override
    public AstZNode visitNotExpr(ZParser.NotExprContext ctx) {
        return unary(ctx, UnaryOperator.NOT, ctx.unaryExpression());
    }

    @Override
    public AstZNode visitNegateExpr(ZParser.NegateExprContext ctx) {
        return unary(ctx, UnaryOperator.NEGATE, ctx.unaryExpression());
    }

    @Override
    public AstZNode visitPositiveExpr(ZParser.PositiveExprContext ctx) {
        return unary(ctx, UnaryOperator.POSITIVE, ctx.unaryExpression());
    }

    @Override
    public AstZNode visitToPostfixExpr(ZParser.ToPostfixExprContext ctx) {
        return visit(ctx.postfixExpression());
    }

    private AstZNode unary(org.antlr.v4.runtime.ParserRuleContext ctx, UnaryOperator operator, org.antlr.v4.runtime.ParserRuleContext expression) {
        return new UnaryExpression(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), operator, (Expression) visit(expression));
    }

    @Override
    public AstZNode visitPostfixExpression(ZParser.PostfixExpressionContext ctx) {
        Expression expression = (Expression) visit(ctx.primaryExpression());
        for (ZParser.PostfixOperationContext operation : ctx.postfixOperation()) {
            expression = (Expression) visitPostfixOperation(operation, expression);
        }
        return expression;
    }

    private AstZNode visitPostfixOperation(ZParser.PostfixOperationContext ctx, Expression expression) {
        if (ctx instanceof ZParser.ArrayAccessOpContext op)
            return new ArrayAccessExpression(op.getStart().getLine(), op.getStart().getCharPositionInLine(), expression, (Expression) visit(op.expression()));
        if (ctx instanceof ZParser.MemberAccessOpContext op)
            return new MemberAccessExpression(op.getStart().getLine(), op.getStart().getCharPositionInLine(), expression, op.ID().getText());
        if (ctx instanceof ZParser.FunctionCallOpContext op) {
            return new FunctionCallExpression(op.getStart().getLine(), op.getStart().getCharPositionInLine(), expression, arguments(op.argumentList()));
        }
        if (ctx instanceof ZParser.PostIncrementOpContext op)
            return new UnaryExpression(op.getStart().getLine(), op.getStart().getCharPositionInLine(), UnaryOperator.POST_INCREMENT, expression);
        if (ctx instanceof ZParser.PostDecrementOpContext op)
            return new UnaryExpression(op.getStart().getLine(), op.getStart().getCharPositionInLine(), UnaryOperator.POST_DECREMENT, expression);
        return expression;
    }

    private List<Expression> arguments(ZParser.ArgumentListContext ctx) {
        List<Expression> result = new ArrayList<>();
        if (ctx != null) for (ZParser.ExpressionContext expression : ctx.expression())
            result.add((Expression) visit(expression));
        return result;
    }

    @Override
    public AstZNode visitLiteralExpr(ZParser.LiteralExprContext ctx) {
        return visit(ctx.literal());
    }

    @Override
    public AstZNode visitIntegerLiteralExpr(ZParser.IntegerLiteralExprContext ctx) {
        return literal(ctx, Integer.parseInt(ctx.getText()));
    }

    @Override
    public AstZNode visitFloatLiteralExpr(ZParser.FloatLiteralExprContext ctx) {
        return literal(ctx, Double.parseDouble(ctx.getText()));
    }

    @Override
    public AstZNode visitCharLiteralExpr(ZParser.CharLiteralExprContext ctx) {
        return literal(ctx, ctx.getText());
    }

    @Override
    public AstZNode visitStringLiteralExpr(ZParser.StringLiteralExprContext ctx) {
        return literal(ctx, ctx.getText());
    }

    @Override
    public AstZNode visitTrueLiteralExpr(ZParser.TrueLiteralExprContext ctx) {
        return literal(ctx, true);
    }

    @Override
    public AstZNode visitFalseLiteralExpr(ZParser.FalseLiteralExprContext ctx) {
        return literal(ctx, false);
    }

    private AstZNode literal(org.antlr.v4.runtime.ParserRuleContext ctx, Object value) {
        return new LiteralExpression(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), value);
    }

    @Override
    public AstZNode visitVariableExpr(ZParser.VariableExprContext ctx) {
        return new VariableExpression(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.ID().getText());
    }

    @Override
    public AstZNode visitReadExpr(ZParser.ReadExprContext ctx) {
        return new ReadExpression(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
    }

    @Override
    public AstZNode visitParenthesizedExpr(ZParser.ParenthesizedExprContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public AstZNode visitObjCreationExpr(ZParser.ObjCreationExprContext ctx) {
        return new ObjectCreationExpression(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
                ctx.ID().getText(), arguments(ctx.argumentList()));
    }

    @Override
    public AstZNode visitArrayCreationExpr(ZParser.ArrayCreationExprContext ctx) {
        return visit(ctx.arrayCreation());
    }

    @Override
    public AstZNode visitNullExpr(ZParser.NullExprContext ctx) {
        return new NullExpression(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
    }

    @Override
    public AstZNode visitArrayCreation(ZParser.ArrayCreationContext ctx) {
        String typeName = ctx.primitiveType() != null ? ctx.primitiveType().getText() : ctx.ID().getText();
        Type type = new Type(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), typeName, null);
        List<Expression> dimensions = new ArrayList<>();
        for (ZParser.ExpressionContext e : ctx.arrayCreationDimensions().expression())
            dimensions.add((Expression) visit(e));
        return new ArrayCreationExpression(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), type, dimensions);
    }
}
