package com.navi.backend.ast.y.expressions;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class BinaryExpression extends Expression {
    private final Expression left;
    private final BinaryOperator operator;
    private final Expression right;

    public BinaryExpression(
            int line,
            int column,
            Expression left,
            BinaryOperator operator,
            Expression right
    ) {
        super(line, column);
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public List<? extends AstYNode> getChildren() {
        return List.of(left, right);
    }

    @Override
    public String getNodeLabel() {
        return "BinaryExpression: " + operator;
    }

    @Override
    public <R> R accept(AstYVisitor<R> visitor) {
        return visitor.visit(this);
    }
}