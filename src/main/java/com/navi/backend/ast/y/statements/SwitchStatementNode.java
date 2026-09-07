package com.navi.backend.ast.y.statements;

import com.navi.backend.ast.y.expressions.ExpressionNode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SwitchStatementNode extends StatementNode {

    private ExpressionNode expression;
    private List<SwitchCaseNode> cases;
    private DefaultCaseNode defaultCase;

    public SwitchStatementNode(ExpressionNode expression) {
        this.expression = expression;
        this.cases = new ArrayList<>();
    }
}