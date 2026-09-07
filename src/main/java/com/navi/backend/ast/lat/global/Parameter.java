package com.navi.backend.ast.lat.global;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class Parameter extends AstLatNode {
    private final String name;
    private final String type;

    public Parameter(int line, int column, String name, String type) {
        super(line, column);
        this.name = name;
        this.type = type;
    }

    @Override
    public List<? extends AstLatNode> getChildren() {
        return List.of();
    }

    @Override
    public String getNodeLabel() {
        return "Parameter: " + name + " : " + type;
    }

    @Override
    public <R> R accept(AstLatVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
