package com.navi.backend.ast.y.statements;

import com.navi.backend.ast.y.expressions.ExpressionNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoWhileStatementNode extends YBlockNode {

    private ExpressionNode condition;

    public DoWhileStatementNode(ExpressionNode condition) {
        this.condition = condition;
    }
}