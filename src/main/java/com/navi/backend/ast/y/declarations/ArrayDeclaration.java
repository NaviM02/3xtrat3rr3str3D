package com.navi.backend.ast.y.declarations;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.expressions.Expression;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ArrayDeclaration extends AstYNode {
    private final List<Expression> dimensions;

    public ArrayDeclaration(int line, int column, List<Expression> dimensions) {
        super(line, column);
        this.dimensions = dimensions;
    }

    @Override
    public List<? extends AstYNode> getChildren() {
        return dimensions == null ? List.of() : new ArrayList<>(dimensions);
    }

    @Override
    public String getNodeLabel() {
        return "ArrayDeclaration";
    }

    @Override
    public <R> R accept(AstYVisitor<R> visitor) {
        return visitor.visit(this);
    }
}