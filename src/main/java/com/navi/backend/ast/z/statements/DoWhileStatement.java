package com.navi.backend.ast.z.statements;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.expressions.Expression;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class DoWhileStatement extends Statement {
    private final AstZNode body;
    private final Expression condition;

    public DoWhileStatement(int line, int column, AstZNode body, Expression condition) {
        super(line, column);
        this.body = body;
        this.condition = condition;
    }

    @Override
    public List<? extends AstZNode> getChildren() {
        return List.of(body, condition);
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
