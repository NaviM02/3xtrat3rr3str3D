package com.navi.backend.ast.z.expressions;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class TernaryExpression extends Expression {
    private final Expression condition;
    private final Expression thenExpression;
    private final Expression elseExpression;

    public TernaryExpression(int line, int column, Expression condition, Expression thenExpression, Expression elseExpression) {
        super(line, column);
        this.condition = condition;
        this.thenExpression = thenExpression;
        this.elseExpression = elseExpression;
    }

    @Override
    public List<? extends AstZNode> getChildren() {
        return List.of(condition, thenExpression, elseExpression);
    }

    @Override
    public String getNodeLabel() {
        return "TernaryExpression";
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
