package com.navi.backend.ast.z.statements;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DefaultCase extends AstZNode {
    private final List<Statement> statements;

    public DefaultCase(int line, int column, List<Statement> statements) {
        super(line, column);
        this.statements = statements;
    }

    @Override
    public List<? extends AstZNode> getChildren() {
        return statements == null ? List.of() : new ArrayList<>(statements);
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
