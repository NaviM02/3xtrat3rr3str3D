package com.navi.backend.ast.y.statements;

import com.navi.backend.ast.y.expressions.ExpressionNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForStatementNode extends YBlockNode {

    private ForInitializerNode initializer;
    private ExpressionNode condition;
    private ExpressionNode update;
}