package com.navi.backend.ast.y.expressions;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class UnaryExpression extends Expression {
    private final UnaryOperator operator;
    private final Expression expression;

    public UnaryExpression(
            int line,
            int column,
            UnaryOperator operator,
            Expression expression
    ) {
        super(line, column);
        this.operator = operator;
        this.expression = expression;
    }

    @Override
    public List<? extends AstYNode> getChildren() {
        return List.of(expression);
    }

    @Override
    public String getNodeLabel() {
        return "UnaryExpression: " + operator;
    }

    @Override
    public <R> R accept(AstYVisitor<R> visitor) {
        return visitor.visit(this);
    }
}