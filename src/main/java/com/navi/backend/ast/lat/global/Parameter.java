package com.navi.backend.ast.lat.global;

import com.navi.backend.ast.lat.AstNode;
import com.navi.backend.ast.lat.visitors.AstVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class Parameter extends AstNode {
    private final String name;
    private final String type;

    public Parameter(int line, int column, String name, String type) {
        super(line, column);
        this.name = name;
        this.type = type;
    }

    @Override
    public List<? extends AstNode> getChildren() {
        return List.of();
    }

    @Override
    public String getNodeLabel() {
        return "Parameter: " + name + " : " + type;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
