package com.navi.backend.ast.y;

import com.navi.backend.ast.y.visitors.AstYVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public abstract class AstYNode {
    private final int line;
    private final int column;

    protected AstYNode(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public abstract List<? extends AstYNode> getChildren();

    public String getNodeLabel() {
        return getClass().getSimpleName();
    }

    public abstract <R> R accept(AstYVisitor<R> visitor);
}