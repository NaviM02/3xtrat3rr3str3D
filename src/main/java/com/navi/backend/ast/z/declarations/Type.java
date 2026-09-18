package com.navi.backend.ast.z.declarations;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Type extends AstZNode {
    private final String name;
    private final ArrayDimensions arrayDimensions;

    public Type(int line, int column, String name, ArrayDimensions arrayDimensions) {
        super(line, column);
        this.name = name;
        this.arrayDimensions = arrayDimensions;
    }

    @Override
    public List<? extends AstZNode> getChildren() {
        if (arrayDimensions == null) return List.of();
        return List.of(arrayDimensions);
    }

    @Override
    public String getNodeLabel() {
        return "Type: " + name;
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
