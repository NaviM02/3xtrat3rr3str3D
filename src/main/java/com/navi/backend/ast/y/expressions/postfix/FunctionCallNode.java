package com.navi.backend.ast.y.expressions.postfix;

import com.navi.backend.ast.y.expressions.ExpressionNode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FunctionCallNode extends PostfixOperationNode {

    private List<ExpressionNode> arguments;

    public FunctionCallNode() {
        this.arguments = new ArrayList<>();
    }
}