package com.navi.backend.ast.z.declarations;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.expressions.Expression;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ArrayInitializer extends Initializer {
    private final List<AstZNode> elements;

    public ArrayInitializer(int line, int column, List<AstZNode> elements) {
        super(line, column);
        this.elements = elements;
    }

    @Override
    public List<? extends AstZNode> getChildren() {
        return elements == null ? List.of() : new ArrayList<>(elements);
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
