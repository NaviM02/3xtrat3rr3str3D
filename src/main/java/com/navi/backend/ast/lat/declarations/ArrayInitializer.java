package com.navi.backend.ast.lat.declarations;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class ArrayInitializer extends AstLatNode {
    private final List<Expression> values;

    public ArrayInitializer(int line, int column, List<Expression> values) {
        super(line, column);
        this.values = values;
    }

    @Override
    public List<? extends AstLatNode> getChildren() {
        return values;
    }

    @Override
    public <R> R accept(AstLatVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
