package com.navi.backend.ast.z.statements;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class ElseClause extends AstZNode {
    private final AstZNode branch;

    public ElseClause(int line, int column, AstZNode branch) {
        super(line, column);
        this.branch = branch;
    }

    @Override
    public List<? extends AstZNode> getChildren() {
        return List.of(branch);
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
