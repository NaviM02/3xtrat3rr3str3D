package com.navi.backend.ast.lat.declarations.initializers;

import com.navi.backend.ast.lat.AstNode;
import com.navi.backend.ast.lat.visitors.AstVisitor;
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
    public List<? extends AstNode> getChildren() {
        return fields;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
