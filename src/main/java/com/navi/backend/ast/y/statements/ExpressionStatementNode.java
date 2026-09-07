package com.navi.backend.ast.y.statements;

import com.navi.backend.ast.y.expressions.ExpressionNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpressionStatementNode extends StatementNode {

    private ExpressionNode expression;

    public ExpressionStatementNode(ExpressionNode expression) {
        this.expression = expression;
    }
}