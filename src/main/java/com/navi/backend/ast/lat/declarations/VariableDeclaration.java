package com.navi.backend.ast.lat.declarations;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.declarations.initializers.Initializer;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class VariableDeclaration extends Declaration {
    private final String name;
    private final String type;
    private final Initializer initializer;

    public VariableDeclaration(int line, int column, String name, String type, Initializer initializer) {
        super(line, column);
        this.name = name;
        this.type = type;
        this.initializer = initializer;
    }

    @Override
    public List<? extends AstLatNode> getChildren() {
        if (initializer == null) {
            return List.of();
        }

        return List.of(initializer);
    }

    @Override
    public String getNodeLabel() {
        return "VariableDeclaration: " + name + " : " + type;
    }

    @Override
    public <R> R accept(AstLatVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
