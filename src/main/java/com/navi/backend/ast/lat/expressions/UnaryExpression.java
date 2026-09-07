package com.navi.backend.ast.lat.expressions;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class UnaryExpression extends Expression {
    private final UnaryOperator operator;
    private final Expression expression;

    public UnaryExpression(int line, int column, UnaryOperator operator, Expression expression) {
        super(line, column);
        this.operator = operator;
        this.expression = expression;
    }

    @Override
    public List<? extends AstLatNode> getChildren() {
        return List.of(expression);
    }

    @Override
    public <R> R accept(AstLatVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
