package com.navi.backend.ast.lat.statements;

import com.navi.backend.ast.lat.AstNode;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.visitors.AstVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class WhileStatement extends Statement {
    private final Expression condition;
    private final BlockStatement block;

    public WhileStatement(int line, int column, Expression condition, BlockStatement block) {
        super(line, column);
        this.condition = condition;
        this.block = block;
    }

    @Override
    public List<? extends AstNode> getChildren() {
        return List.of(condition, block);
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
