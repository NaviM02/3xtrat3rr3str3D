package com.navi.backend.ast.lat.statements;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class ElseIfStatement extends AstLatNode {
    private final Expression condition;
    private final BlockStatement block;

    public ElseIfStatement(int line, int column, Expression condition, BlockStatement block) {
        super(line, column);
        this.condition = condition;
        this.block = block;
    }

    @Override
    public List<? extends AstLatNode> getChildren() {
        return List.of(condition, block);
    }

    @Override
    public <R> R accept(AstLatVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
