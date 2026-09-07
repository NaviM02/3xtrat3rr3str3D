package com.navi.backend.ast.y.expressions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdentifierExpressionNode extends ExpressionNode {

    private String name;

    public IdentifierExpressionNode(String name) {
        this.name = name;
    }
}