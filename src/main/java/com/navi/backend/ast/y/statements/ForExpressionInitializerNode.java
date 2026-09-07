package com.navi.backend.ast.y.statements;

import com.navi.backend.ast.y.expressions.ExpressionNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForExpressionInitializerNode extends ForInitializerNode {

    private ExpressionNode expression;

    public ForExpressionInitializerNode(ExpressionNode expression) {
        this.expression = expression;
    }
}