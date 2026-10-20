package com.navi.backend.ast.lat.expressions;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;

import java.util.List;

public class NullExpression extends Expression {

    public NullExpression(int line, int column) {
        super(line, column);
    }

    @Override
    public List<? extends AstLatNode> getChildren() {
        return List.of();
    }

    @Override
    public String getNodeLabel() {
        return "NullExpression";
    }

    @Override
    public <R> R accept(AstLatVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
