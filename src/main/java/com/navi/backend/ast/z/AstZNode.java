package com.navi.backend.ast.z;

import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public abstract class AstZNode {
    private final int line;
    private final int column;

    protected AstZNode(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public abstract List<? extends AstZNode> getChildren();

    public String getNodeLabel() {
        return getClass().getSimpleName();
    }

    public abstract <R> R accept(AstZVisitor<R> visitor);
}
