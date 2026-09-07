package com.navi.backend.ast.y.expressions;

import com.navi.backend.ast.y.operators.UnaryOperator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnaryExpressionNode extends ExpressionNode {

    private UnaryOperator operator;
    private ExpressionNode expression;

    public UnaryExpressionNode(
            UnaryOperator operator,
            ExpressionNode expression) {

        this.operator = operator;
        this.expression = expression;
    }
}