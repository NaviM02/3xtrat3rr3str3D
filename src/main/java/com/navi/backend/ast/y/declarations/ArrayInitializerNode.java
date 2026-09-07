package com.navi.backend.ast.y.declarations;

import com.navi.backend.ast.y.expressions.ExpressionNode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ArrayInitializerNode extends InitializerNode {

    private List<ExpressionNode> expressions;

    public ArrayInitializerNode() {
        this.expressions = new ArrayList<>();
    }
}