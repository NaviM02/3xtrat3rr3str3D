package com.navi.backend.ast.y.statements;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DefaultCase extends AstYNode {
    private final List<Statement> statements;
    private final BreakStatement breakStatement;

    public DefaultCase(int line, int column, List<Statement> statements, BreakStatement breakStatement) {
        super(line, column);
        this.statements = statements;
        this.breakStatement = breakStatement;
    }

    @Override
    public List<? extends AstYNode> getChildren() {
        List<AstYNode> children = new ArrayList<>();
        if (statements != null) children.addAll(statements);
        children.add(breakStatement);
        return children;
    }

    @Override
    public <R> R accept(AstYVisitor<R> visitor) {
        return visitor.visit(this);
    }
}