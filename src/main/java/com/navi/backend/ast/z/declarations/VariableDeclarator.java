package com.navi.backend.ast.z.declarations;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class VariableDeclarator extends AstZNode {
    private final String name;
    private final Initializer initializer;

    public VariableDeclarator(int line, int column, String name, Initializer initializer) {
        super(line, column);
        this.name = name;
        this.initializer = initializer;
    }

    @Override
    public List<? extends AstZNode> getChildren() {
        return initializer == null ? List.of() : List.of(initializer);
    }

    @Override
    public String getNodeLabel() {
        return "VariableDeclarator: " + name;
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
