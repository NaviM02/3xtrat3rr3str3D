package com.navi.backend.ast.lat.expressions;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
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
    public List<? extends AstLatNode> getChildren() {
        return List.of();
    }

    @Override
    public String getNodeLabel() {
        return "VariableExpression: " + name;
    }

    @Override
    public <R> R accept(AstLatVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
