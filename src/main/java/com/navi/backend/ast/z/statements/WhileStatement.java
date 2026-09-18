package com.navi.backend.ast.z.statements;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.expressions.Expression;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class WhileStatement extends Statement {
    private final Expression condition;
    private final AstZNode body;

    public WhileStatement(int line, int column, Expression condition, AstZNode body) {
        super(line, column);
        this.condition = condition;
        this.body = body;
    }

    @Override
    public List<? extends AstZNode> getChildren() {
        return List.of(condition, body);
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
