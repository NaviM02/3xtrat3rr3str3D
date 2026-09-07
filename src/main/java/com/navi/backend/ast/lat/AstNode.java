package com.navi.backend.ast.lat;

import com.navi.backend.ast.lat.visitors.AstVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public abstract class AstNode {
    private final int line;
    private final int column;

    protected AstNode(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public abstract List<? extends AstNode> getChildren();
    public String getNodeLabel() {
        return getClass().getSimpleName();
    }
    public abstract <R> R accept(AstVisitor<R> visitor);
}
