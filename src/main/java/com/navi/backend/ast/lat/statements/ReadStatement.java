package com.navi.backend.ast.lat.statements;

import com.navi.backend.ast.lat.AstNode;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.visitors.AstVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class ReadStatement extends Statement {
    private final Expression target;

    public ReadStatement(int line, int column, Expression target) {
        super(line, column);
        this.target = target;
    }

    @Override
    public List<? extends AstNode> getChildren() {
        return List.of(target);
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
