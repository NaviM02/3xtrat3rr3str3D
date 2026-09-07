package com.navi.backend.ast.lat.declarations;

import com.navi.backend.ast.lat.AstNode;
import com.navi.backend.ast.lat.declarations.initializers.Initializer;
import com.navi.backend.ast.lat.visitors.AstVisitor;
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
    public List<? extends AstNode> getChildren() {
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
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
