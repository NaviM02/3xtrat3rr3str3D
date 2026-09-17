package com.navi.backend.ast.y.statements;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.expressions.Expression;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class IncrementStatement extends Statement {
    private final Expression target;
    private final boolean increment;

    public IncrementStatement(int line, int column, Expression target, boolean increment) {
        super(line, column);
        this.target = target;
        this.increment = increment;
    }

    @Override
    public List<? extends AstYNode> getChildren() {
        return List.of(target);
    }

    @Override
    public String getNodeLabel() {
        return increment ? "IncrementStatement: ++" : "IncrementStatement: --";
    }

    @Override
    public <R> R accept(AstYVisitor<R> visitor) {
        return visitor.visit(this);
    }
}