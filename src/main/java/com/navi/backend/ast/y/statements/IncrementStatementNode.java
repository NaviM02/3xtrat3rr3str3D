package com.navi.backend.ast.y.statements;

import com.navi.backend.ast.y.expressions.AssignableExpressionNode;
import com.navi.backend.ast.y.operators.IncrementOperator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncrementStatementNode extends StatementNode {

    private AssignableExpressionNode expression;
    private IncrementOperator operator;

    public IncrementStatementNode(
            AssignableExpressionNode expression,
            IncrementOperator operator) {

        this.expression = expression;
        this.operator = operator;
    }
}