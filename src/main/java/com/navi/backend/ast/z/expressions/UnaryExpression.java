package com.navi.backend.ast.z.expressions;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.visitors.AstZVisitor;
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
    public List<? extends AstZNode> getChildren() {
        return List.of(expression);
    }

    @Override
    public String getNodeLabel() {
        return "UnaryExpression: " + operator;
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
