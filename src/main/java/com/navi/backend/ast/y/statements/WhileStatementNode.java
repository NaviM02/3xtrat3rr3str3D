package com.navi.backend.ast.y.statements;

import com.navi.backend.ast.y.expressions.ExpressionNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WhileStatementNode extends YBlockNode {

    private ExpressionNode condition;

    public WhileStatementNode(ExpressionNode condition) {
        this.condition = condition;
    }
}