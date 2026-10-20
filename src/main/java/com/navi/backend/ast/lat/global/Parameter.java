package com.navi.backend.ast.lat.global;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class Parameter extends AstLatNode {
    private final String name;
    private final String type;
    private final int rank;

    public Parameter(int line, int column, String name, String type) {
        this(line, column, name, type, 0);
    }

    public Parameter(int line, int column, String name, String type, int rank) {
        super(line, column);
        this.name = name;
        this.type = type;
        this.rank = rank;
    }

    @Override
    public List<? extends AstLatNode> getChildren() {
        return List.of();
    }

    @Override
    public String getNodeLabel() {
        return rank > 0 ? "Parameter: " + name + " : " + type + " (rank " + rank + ")"
                : "Parameter: " + name + " : " + type;
    }

    @Override
    public <R> R accept(AstLatVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
