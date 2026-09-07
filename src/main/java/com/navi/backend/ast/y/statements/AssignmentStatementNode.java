package com.navi.backend.ast.y.statements;

import com.navi.backend.ast.y.expressions.AssignableExpressionNode;
import com.navi.backend.ast.y.expressions.ExpressionNode;
import com.navi.backend.ast.y.operators.AssignmentOperator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignmentStatementNode extends StatementNode {

    private AssignableExpressionNode target;
    private AssignmentOperator operator;
    private ExpressionNode expression;

    public AssignmentStatementNode(
            AssignableExpressionNode target,
            AssignmentOperator operator,
            ExpressionNode expression) {

        this.target = target;
        this.operator = operator;
        this.expression = expression;
    }
}