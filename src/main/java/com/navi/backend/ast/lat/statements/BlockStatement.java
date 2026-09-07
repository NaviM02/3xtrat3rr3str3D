package com.navi.backend.ast.lat.statements;

import com.navi.backend.ast.lat.AstNode;
import com.navi.backend.ast.lat.visitors.AstVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class BlockStatement extends Statement {
    private final List<Statement> statements;

    public BlockStatement(int line, int column, List<Statement> statements) {
        super(line, column);
        this.statements = statements;
    }

    @Override
    public List<? extends AstNode> getChildren() {
        return statements;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
