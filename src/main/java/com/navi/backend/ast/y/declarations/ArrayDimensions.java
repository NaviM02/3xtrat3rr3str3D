package com.navi.backend.ast.y.declarations;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class ArrayDimensions extends AstYNode {
    private final List<Integer> dimensions;

    public ArrayDimensions(int line, int column, List<Integer> dimensions) {
        super(line, column);
        this.dimensions = dimensions;
    }

    @Override
    public List<? extends AstYNode> getChildren() {
        return List.of();
    }

    @Override
    public String getNodeLabel() {
        return "ArrayDimensions: " + dimensions;
    }

    @Override
    public <R> R accept(AstYVisitor<R> visitor) {
        return visitor.visit(this);
    }
}