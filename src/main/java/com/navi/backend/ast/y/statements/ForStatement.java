package com.navi.backend.ast.y.statements;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.expressions.Expression;
import com.navi.backend.ast.y.declarations.Declaration;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ForStatement extends Statement {
    private final AstYNode initializer;
    private final Expression condition;
    private final Expression update;
    private final List<Statement> statements;

    public ForStatement(int line, int column, AstYNode initializer, Expression condition, Expression update, List<Statement> statements) {
        super(line, column);
        this.initializer = initializer;
        this.condition = condition;
        this.update = update;
        this.statements = statements;
    }

    @Override
    public List<? extends AstYNode> getChildren() {
        List<AstYNode> children = new ArrayList<>();
        if (initializer != null) children.add(initializer);
        if (condition != null) children.add(condition);
        if (update != null) children.add(update);
        if (statements != null) children.addAll(statements);
        return children;
    }

    @Override
    public <R> R accept(AstYVisitor<R> visitor) {
        return visitor.visit(this);
    }
}