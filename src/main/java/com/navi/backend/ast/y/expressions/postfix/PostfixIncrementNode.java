package com.navi.backend.ast.y.expressions.postfix;

import com.navi.backend.ast.y.operators.IncrementOperator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostfixIncrementNode extends PostfixOperationNode {

    private IncrementOperator operator;

    public PostfixIncrementNode(IncrementOperator operator) {
        this.operator = operator;
    }
}