package com.navi.backend.ast.z.expressions;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.visitors.AstZVisitor;
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
    public List<? extends AstZNode> getChildren() {
        return List.of();
    }

    @Override
    public String getNodeLabel() {
        return "VariableExpression: " + name;
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
