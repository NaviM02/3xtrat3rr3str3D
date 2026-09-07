package com.navi.backend.ast.lat.declarations.initializers;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class StructInitializer extends Initializer {
    private final List<StructFieldInitializer> fields;

    public StructInitializer(int line, int column, List<StructFieldInitializer> fields) {
        super(line, column);
        this.fields = fields;
    }

    @Override
    public List<? extends AstLatNode> getChildren() {
        return fields;
    }

    @Override
    public <R> R accept(AstLatVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
