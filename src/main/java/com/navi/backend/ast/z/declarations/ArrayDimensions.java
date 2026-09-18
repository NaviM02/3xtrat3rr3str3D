package com.navi.backend.ast.z.declarations;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class ArrayDimensions extends AstZNode {
    private final int dimensions;

    public ArrayDimensions(int line, int column, int dimensions) {
        super(line, column);
        this.dimensions = dimensions;
    }

    @Override
    public List<? extends AstZNode> getChildren() {
        return List.of();
    }

    @Override
    public String getNodeLabel() {
        return "ArrayDimensions: " + dimensions;
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
