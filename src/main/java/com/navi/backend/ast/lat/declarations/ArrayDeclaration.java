package com.navi.backend.ast.lat.declarations;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
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
    public List<? extends AstLatNode> getChildren() {
        List<AstLatNode> children = new java.util.ArrayList<>();

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
    public <R> R accept(AstLatVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
