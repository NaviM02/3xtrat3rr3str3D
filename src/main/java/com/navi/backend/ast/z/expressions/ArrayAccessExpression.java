package com.navi.backend.ast.z.expressions;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.visitors.AstZVisitor;
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
    public List<? extends AstZNode> getChildren() {
        return List.of(array, index);
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
