package com.navi.backend.ast.z.declarations;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

@Getter
public class Parameter extends AstZNode {
    private final ZType type;
    private final String name;

    public Parameter(int line, int column, ZType type, String name) {
        super(line, column);
        this.type = type;
        this.name = name;
    }

    @Override
    public java.util.List<? extends AstZNode> getChildren() {
        return java.util.List.of(type);
    }

    @Override
    public String getNodeLabel() {
        return "Parameter: " + name;
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
