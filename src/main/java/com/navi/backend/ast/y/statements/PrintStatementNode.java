package com.navi.backend.ast.y.statements;

import com.navi.backend.ast.y.expressions.ExpressionNode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PrintStatementNode extends StatementNode {

    private List<ExpressionNode> expressions;

    public PrintStatementNode() {
        this.expressions = new ArrayList<>();
    }
}