package com.navi.backend.ast.y.declarations;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class StructureDeclaration extends Declaration {
    private final String name;
    private final List<StructureField> fields;

    public StructureDeclaration(int line, int column, String name, List<StructureField> fields) {
        super(line, column);
        this.name = name;
        this.fields = fields;
    }

    @Override
    public List<? extends AstYNode> getChildren() {
        return fields == null ? List.of() : new ArrayList<>(fields);
    }

    @Override
    public String getNodeLabel() {
        return "StructureDeclaration: " + name;
    }

    @Override
    public <R> R accept(AstYVisitor<R> visitor) {
        return visitor.visit(this);
    }
}