package com.navi.backend.ast.y.declarations;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ArrayInitializer extends Initializer {
    private final List<AstYNode> elements;

    public ArrayInitializer(int line, int column, List<AstYNode> elements) {
        super(line, column);
        this.elements = elements;
    }

    @Override
    public List<? extends AstYNode> getChildren() {
        return elements == null ? List.of() : new ArrayList<>(elements);
    }

    @Override
    public <R> R accept(AstYVisitor<R> visitor) {
        return visitor.visit(this);
    }
}