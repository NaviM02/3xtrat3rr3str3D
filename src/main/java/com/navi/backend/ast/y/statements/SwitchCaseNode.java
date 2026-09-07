package com.navi.backend.ast.y.statements;

import com.navi.backend.ast.y.expressions.ExpressionNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SwitchCaseNode extends YBlockNode {

    private ExpressionNode expression;
    private BreakStatementNode breakStatement;

    public SwitchCaseNode(ExpressionNode expression) {
        this.expression = expression;
    }
}