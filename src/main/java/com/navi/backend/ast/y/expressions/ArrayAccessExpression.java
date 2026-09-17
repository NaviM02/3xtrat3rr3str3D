package com.navi.backend.ast.y.expressions;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class ArrayAccessExpression extends Expression {
    private final Expression array;
    private final Expression index;

    public ArrayAccessExpression(
            int line,
            int column,
            Expression array,
            Expression index
    ) {
        super(line, column);
        this.array = array;
        this.index = index;
    }

    @Override
    public List<? extends AstYNode> getChildren() {
        return List.of(array, index);
    }

    @Override
    public <R> R accept(AstYVisitor<R> visitor) {
        return visitor.visit(this);
    }
}