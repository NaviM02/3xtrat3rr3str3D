package com.navi.backend.ast.z.statements;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.expressions.Expression;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ExpressionStatement extends Statement {
    private final Expression expression;

    public ExpressionStatement(int line, int column, Expression expression) {
        super(line, column);
        this.expression = expression;
    }

    @Override
    public List<? extends AstZNode> getChildren() {
        List<AstZNode> children = new ArrayList<>();
        if (expression != null) children.add(expression);
        return children;
    }

    @Override
    public String getNodeLabel() {
        return "ExpressionStatement";
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
