package com.navi.backend.ast.y.expressions;

import com.navi.backend.ast.y.expressions.postfix.PostfixOperationNode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostfixExpressionNode extends ExpressionNode {

    private ExpressionNode primary;
    private List<PostfixOperationNode> operations;

    public PostfixExpressionNode(ExpressionNode primary) {
        this.primary = primary;
        this.operations = new ArrayList<>();
    }
}