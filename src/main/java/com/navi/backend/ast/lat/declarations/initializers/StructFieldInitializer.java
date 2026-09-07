package com.navi.backend.ast.lat.declarations.initializers;

import com.navi.backend.ast.lat.AstNode;
import com.navi.backend.ast.lat.visitors.AstVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class StructFieldInitializer extends AstNode {
    private final String name;
    private final Initializer value;

    public StructFieldInitializer(int line, int column, String name, Initializer value) {
        super(line, column);
        this.name = name;
        this.value = value;
    }

    @Override
    public List<? extends AstNode> getChildren() {
        return List.of(value);
    }

    @Override
    public String getNodeLabel() {
        return "StructFieldInitializer: " + name;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
