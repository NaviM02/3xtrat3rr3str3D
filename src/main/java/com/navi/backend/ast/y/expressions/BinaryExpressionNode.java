package com.navi.backend.ast.y.expressions;

import com.navi.backend.ast.y.operators.BinaryOperator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BinaryExpressionNode extends ExpressionNode {

    private ExpressionNode left;
    private BinaryOperator operator;
    private ExpressionNode right;

    public BinaryExpressionNode(
            ExpressionNode left,
            BinaryOperator operator,
            ExpressionNode right) {

        this.left = left;
        this.operator = operator;
        this.right = right;
    }
}