package com.navi.backend.ast.lat.declarations;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class StructField extends AstLatNode {
    private final String name;
    private final String type;
    private final boolean isArray;

    public StructField(int line, int column, String name, String type, boolean isArray) {
        super(line, column);
        this.name = name;
        this.type = type;
        this.isArray = isArray;
    }

    @Override
    public List<? extends AstLatNode> getChildren() {
        return List.of();
    }

    @Override
    public String getNodeLabel() {
        return "StructField: " + name + " : " + type;
    }

    @Override
    public <R> R accept(AstLatVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
