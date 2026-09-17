package com.navi.backend.ast.y.statements;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.visitors.AstYVisitor;

import java.util.List;

public class BreakStatement extends Statement {
    public BreakStatement(int line, int column) {
        super(line, column);
    }

    @Override
    public List<? extends AstYNode> getChildren() {
        return List.of();
    }

    @Override
    public <R> R accept(AstYVisitor<R> visitor) {
        return visitor.visit(this);
    }
}