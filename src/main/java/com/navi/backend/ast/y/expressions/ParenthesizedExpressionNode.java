package com.navi.backend.ast.y.expressions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParenthesizedExpressionNode extends ExpressionNode {

    private ExpressionNode expression;

    public ParenthesizedExpressionNode(ExpressionNode expression) {
        this.expression = expression;
    }
}