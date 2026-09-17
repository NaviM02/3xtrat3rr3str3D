package com.navi.backend.ast.y.statements;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.expressions.Expression;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ElseIfClause extends AstYNode {
    private final Expression condition;
    private final List<Statement> statements;

    public ElseIfClause(int line, int column, Expression condition, List<Statement> statements) {
        super(line, column);
        this.condition = condition;
        this.statements = statements;
    }

    @Override
    public List<? extends AstYNode> getChildren() {
        List<AstYNode> children = new ArrayList<>();
        children.add(condition);
        if (statements != null) children.addAll(statements);
        return children;
    }

    @Override
    public <R> R accept(AstYVisitor<R> visitor) {
        return visitor.visit(this);
    }
}