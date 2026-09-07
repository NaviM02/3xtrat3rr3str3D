package com.navi.backend.ast.y.statements;

import com.navi.backend.ast.y.expressions.ExpressionNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ElseIfNode extends YBlockNode {

    private ExpressionNode condition;

    public ElseIfNode(ExpressionNode condition) {
        this.condition = condition;
    }
}