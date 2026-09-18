package com.navi.backend.ast.z.expressions;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.visitors.AstZVisitor;

import java.util.List;

public class NullExpression extends Expression {
    public NullExpression(int line, int column) {
        super(line, column);
    }

    @Override
    public List<? extends AstZNode> getChildren() {
        return List.of();
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
