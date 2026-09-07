package com.navi.backend.ast.lat.declarations;

import com.navi.backend.ast.lat.AstNode;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.visitors.AstVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class ArrayDeclaration extends Declaration {
    private final String name;
    private final Expression size;
    private final String type;
    private final ArrayInitializer initializer;

    public ArrayDeclaration(int line, int column, String name, Expression size, String type, ArrayInitializer initializer) {
        super(line, column);
        this.name = name;
        this.size = size;
        this.type = type;
        this.initializer = initializer;
    }

    @Override
    public List<? extends AstNode> getChildren() {
        List<AstNode> children = new java.util.ArrayList<>();

        if (size != null) {
            children.add(size);
        }

        if (initializer != null) {
            children.add(initializer);
        }

        return children;
    }

    @Override
    public String getNodeLabel() {
        return "ArrayDeclaration: " + name + " : " + type;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
