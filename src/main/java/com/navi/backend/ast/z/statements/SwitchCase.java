package com.navi.backend.ast.z.statements;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.expressions.Expression;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SwitchCase extends AstZNode {
    private final Expression expression;
    private final List<Statement> statements;

    public SwitchCase(int line, int column, Expression expression, List<Statement> statements) {
        super(line, column);
        this.expression = expression;
        this.statements = statements;
    }

    @Override
    public List<? extends AstZNode> getChildren() {
        List<AstZNode> children = new ArrayList<>();
        children.add(expression);
        if (statements != null) children.addAll(statements);
        return children;
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
