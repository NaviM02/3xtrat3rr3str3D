package com.navi.backend.ast.lat.declarations;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class ArrayInitializer extends AstLatNode {
    private final List<AstLatNode> elements;

    public ArrayInitializer(int line, int column, List<AstLatNode> elements) {
        super(line, column);
        this.elements = elements;
    }

    @Override
    public List<? extends AstLatNode> getChildren() {
        return elements;
    }

    @Override
    public <R> R accept(AstLatVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
