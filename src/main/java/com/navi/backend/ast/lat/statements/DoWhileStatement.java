package com.navi.backend.ast.lat.statements;

import com.navi.backend.ast.lat.AstNode;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.visitors.AstVisitor;
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
    public List<? extends AstNode> getChildren() {
        return List.of(block, condition);
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
