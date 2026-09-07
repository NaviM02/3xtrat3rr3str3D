package com.navi.backend.ast.lat.statements;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
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
    public List<? extends AstLatNode> getChildren() {
        return List.of(target);
    }

    @Override
    public <R> R accept(AstLatVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
