package com.navi.backend.ast.z.statements;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.expressions.Expression;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class ReturnStatement extends Statement {
    private final Expression expression;

    public ReturnStatement(int line, int column, Expression expression) {
        super(line, column);
        this.expression = expression;
    }

    @Override
    public List<? extends AstZNode> getChildren() {
        return expression == null ? List.of() : List.of(expression);
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
