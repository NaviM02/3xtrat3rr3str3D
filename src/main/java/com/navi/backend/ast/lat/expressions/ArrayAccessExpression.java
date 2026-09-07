package com.navi.backend.ast.lat.expressions;

import com.navi.backend.ast.lat.AstNode;
import com.navi.backend.ast.lat.visitors.AstVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class ArrayAccessExpression extends Expression {
    private final Expression array;
    private final Expression index;

    public ArrayAccessExpression(int line, int column, Expression array, Expression index) {
        super(line, column);
        this.array = array;
        this.index = index;
    }

    @Override
    public List<? extends AstNode> getChildren() {
        return List.of(array, index);
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
