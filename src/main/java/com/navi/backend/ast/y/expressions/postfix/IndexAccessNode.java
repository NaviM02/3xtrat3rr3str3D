package com.navi.backend.ast.y.expressions.postfix;

import com.navi.backend.ast.y.expressions.ExpressionNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndexAccessNode extends PostfixOperationNode {

    private ExpressionNode index;

    public IndexAccessNode(ExpressionNode index) {
        this.index = index;
    }
}