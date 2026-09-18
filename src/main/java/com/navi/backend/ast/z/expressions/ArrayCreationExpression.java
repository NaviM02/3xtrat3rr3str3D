package com.navi.backend.ast.z.expressions;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.declarations.Type;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ArrayCreationExpression extends Expression {
    private final Type type;
    private final List<Expression> dimensions;

    public ArrayCreationExpression(int line, int column, Type type, List<Expression> dimensions) {
        super(line, column);
        this.type = type;
        this.dimensions = dimensions;
    }

    @Override
    public List<? extends AstZNode> getChildren() {
        List<AstZNode> children = new ArrayList<>();
        children.add(type);
        if (dimensions != null) children.addAll(dimensions);
        return children;
    }

    @Override
    public String getNodeLabel() {
        return "ArrayCreationExpression";
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
