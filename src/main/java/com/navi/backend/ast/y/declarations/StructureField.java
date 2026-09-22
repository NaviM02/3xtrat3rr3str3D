package com.navi.backend.ast.y.declarations;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class StructureField extends Declaration {
    private final YType type;
    private final String name;
    private final ArrayDimensions arrayDimensions;

    public StructureField(int line, int column, YType type, String name, ArrayDimensions arrayDimensions) {
        super(line, column);
        this.type = type;
        this.name = name;
        this.arrayDimensions = arrayDimensions;
    }

    @Override
    public List<? extends AstYNode> getChildren() {
        if (arrayDimensions != null) return List.of(type, arrayDimensions);
        return List.of(type);
    }

    @Override
    public String getNodeLabel() {
        return "StructureField: " + name;
    }

    @Override
    public <R> R accept(AstYVisitor<R> visitor) {
        return visitor.visit(this);
    }
}