package com.navi.backend.ast.y.declarations;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class VariableDeclaration extends Declaration {
    private final YType type;
    private final String name;
    private final ArrayDeclaration arrayDeclaration;
    private final Initializer initializer;

    public VariableDeclaration(int line, int column, YType type, String name, ArrayDeclaration arrayDeclaration, Initializer initializer) {
        super(line, column);
        this.type = type;
        this.name = name;
        this.arrayDeclaration = arrayDeclaration;
        this.initializer = initializer;
    }

    @Override
    public List<? extends AstYNode> getChildren() {
        List<AstYNode> children = new java.util.ArrayList<>();
        children.add(type);
        if (arrayDeclaration != null) children.add(arrayDeclaration);
        if (initializer != null) children.add(initializer);
        return children;
    }
    @Override
    public String getNodeLabel() {
        return "VariableDeclaration: " + name;
    }

    @Override
    public <R> R accept(AstYVisitor<R> visitor) {
        return visitor.visit(this);
    }
}