package com.navi.backend.ast.lat.declarations;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class StructDeclaration extends Declaration {
    private final String name;
    private final List<StructField> fields;

    public StructDeclaration(int line, int column, String name, List<StructField> fields) {
        super(line, column);
        this.name = name;
        this.fields = fields;
    }

    @Override
    public List<? extends AstLatNode> getChildren() {
        return fields;
    }

    @Override
    public String getNodeLabel() {
        return "StructDeclaration: " + name;
    }

    @Override
    public <R> R accept(AstLatVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
