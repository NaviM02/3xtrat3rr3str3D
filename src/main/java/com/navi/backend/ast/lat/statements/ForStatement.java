package com.navi.backend.ast.lat.statements;

import com.navi.backend.ast.lat.AstNode;
import com.navi.backend.ast.lat.declarations.VariableDeclaration;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.visitors.AstVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class ForStatement extends Statement {
    private final VariableDeclaration initializer;
    private final Expression condition;
    private final Expression update;
    private final BlockStatement block;

    public ForStatement(int line, int column, VariableDeclaration initializer, Expression condition, Expression update, BlockStatement block) {
        super(line, column);
        this.initializer = initializer;
        this.condition = condition;
        this.update = update;
        this.block = block;
    }

    @Override
    public List<? extends AstNode> getChildren() {
        return List.of(initializer, condition, update, block);
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
