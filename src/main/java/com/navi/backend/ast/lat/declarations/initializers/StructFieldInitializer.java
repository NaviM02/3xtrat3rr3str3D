package com.navi.backend.ast.lat.declarations.initializers;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class StructFieldInitializer extends AstLatNode {
    private final String name;
    private final Initializer value;

    public StructFieldInitializer(int line, int column, String name, Initializer value) {
        super(line, column);
        this.name = name;
        this.value = value;
    }

    @Override
    public List<? extends AstLatNode> getChildren() {
        return List.of(value);
    }

    @Override
    public String getNodeLabel() {
        return "StructFieldInitializer: " + name;
    }

    @Override
    public <R> R accept(AstLatVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
