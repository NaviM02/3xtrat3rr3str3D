package com.navi.backend.ast.lat.declarations;

import com.navi.backend.ast.lat.AstNode;
import com.navi.backend.ast.lat.visitors.AstVisitor;
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
    public List<? extends AstNode> getChildren() {
        return fields;
    }

    @Override
    public String getNodeLabel() {
        return "StructDeclaration: " + name;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
