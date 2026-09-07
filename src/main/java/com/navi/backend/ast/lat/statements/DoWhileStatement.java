package com.navi.backend.ast.lat.statements;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class DoWhileStatement extends Statement {
    private final BlockStatement block;
    private final Expression condition;

    public DoWhileStatement(int line, int column, BlockStatement block, Expression condition) {
        super(line, column);
        this.block = block;
        this.condition = condition;
    }

    @Override
    public List<? extends AstLatNode> getChildren() {
        return List.of(block, condition);
    }

    @Override
    public <R> R accept(AstLatVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
