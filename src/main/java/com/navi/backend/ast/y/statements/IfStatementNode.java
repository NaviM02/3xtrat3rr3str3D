package com.navi.backend.ast.y.statements;

import com.navi.backend.ast.y.expressions.ExpressionNode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class IfStatementNode extends YBlockNode {

    private ExpressionNode condition;
    private List<ElseIfNode> elseIfClauses;
    private ElseNode elseClause;

    public IfStatementNode(ExpressionNode condition) {
        this.condition = condition;
        this.elseIfClauses = new ArrayList<>();
    }
}