package com.navi.backend.ast.y.expressions.literals;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.expressions.Expression;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class LiteralExpression extends Expression {
    private final Object value;

    public LiteralExpression(int line, int column, Object value) {
        super(line, column);
        this.value = value;
    }

    @Override
    public List<? extends AstYNode> getChildren() {
        return List.of();
    }

    @Override
    public String getNodeLabel() {
        return "LiteralExpression: " + value;
    }

    @Override
    public <R> R accept(AstYVisitor<R> visitor) {
        return visitor.visit(this);
    }
}