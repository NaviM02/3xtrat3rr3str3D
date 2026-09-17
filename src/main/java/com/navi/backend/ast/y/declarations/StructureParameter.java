package com.navi.backend.ast.y.declarations;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class StructureParameter extends Parameter {
    private final String structureName;
    private final String name;

    public StructureParameter(int line, int column, String structureName, String name) {
        super(line, column);
        this.structureName = structureName;
        this.name = name;
    }

    @Override
    public List<? extends AstYNode> getChildren() {
        return List.of();
    }

    @Override
    public String getNodeLabel() {
        return "StructureParameter: " + structureName + " " + name;
    }

    @Override
    public <R> R accept(AstYVisitor<R> visitor) {
        return visitor.visit(this);
    }
}