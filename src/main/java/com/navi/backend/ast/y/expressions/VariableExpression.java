package com.navi.backend.ast.y.expressions;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class VariableExpression extends Expression {
    private final String name;

    public VariableExpression(int line, int column, String name) {
        super(line, column);
        this.name = name;
    }

    @Override
    public List<? extends AstYNode> getChildren() {
        return List.of();
    }

    @Override
    public String getNodeLabel() {
        return "VariableExpression: " + name;
    }

    @Override
    public <R> R accept(AstYVisitor<R> visitor) {
        return visitor.visit(this);
    }
}