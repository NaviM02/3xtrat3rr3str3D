package com.navi.backend.ast.lat.expressions;

import com.navi.backend.ast.lat.AstNode;
import com.navi.backend.ast.lat.visitors.AstVisitor;
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
    public List<? extends AstNode> getChildren() {
        return List.of(expression);
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
