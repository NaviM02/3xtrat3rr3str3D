package com.navi.backend.ast.y.declarations;

import com.navi.backend.ast.y.expressions.ExpressionNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpressionInitializerNode extends InitializerNode {

    private ExpressionNode expression;

    public ExpressionInitializerNode(ExpressionNode expression) {
        this.expression = expression;
    }
}