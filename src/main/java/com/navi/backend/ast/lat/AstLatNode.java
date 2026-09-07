package com.navi.backend.ast.lat;

import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public abstract class AstLatNode {
    private final int line;
    private final int column;

    protected AstLatNode(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public abstract List<? extends AstLatNode> getChildren();
    public String getNodeLabel() {
        return getClass().getSimpleName();
    }
    public abstract <R> R accept(AstLatVisitor<R> visitor);
}
