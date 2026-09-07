package com.navi.backend.ast.lat.declarations;

import com.navi.backend.ast.lat.AstNode;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.visitors.AstVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class ArrayInitializer extends AstNode {
    private final List<Expression> values;

    public ArrayInitializer(int line, int column, List<Expression> values) {
        super(line, column);
        this.values = values;
    }

    @Override
    public List<? extends AstNode> getChildren() {
        return values;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
