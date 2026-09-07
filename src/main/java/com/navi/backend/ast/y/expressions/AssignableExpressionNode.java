package com.navi.backend.ast.y.expressions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignableExpressionNode extends ExpressionNode {

    private PostfixExpressionNode expression;

    public AssignableExpressionNode(PostfixExpressionNode expression) {
        this.expression = expression;
    }
}