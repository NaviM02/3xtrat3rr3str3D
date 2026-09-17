package com.navi.backend.ast.y.statements;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.expressions.Expression;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DoWhileStatement extends Statement {
    private final List<Statement> statements;
    private final Expression condition;

    public DoWhileStatement(int line, int column, List<Statement> statements, Expression condition) {
        super(line, column);
        this.statements = statements;
        this.condition = condition;
    }

    @Override
    public List<? extends AstYNode> getChildren() {
        List<AstYNode> children = new ArrayList<>();
        if (statements != null) children.addAll(statements);
        children.add(condition);
        return children;
    }

    @Override
    public <R> R accept(AstYVisitor<R> visitor) {
        return visitor.visit(this);
    }
}