package com.navi.backend.ast.y.expressions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LiteralExpressionNode extends ExpressionNode {

    private Object value;

    public LiteralExpressionNode(Object value) {
        this.value = value;
    }
}