package com.navi.backend.ast.lat.expressions.literals;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class NumberLiteral extends Expression {
    private final int value;

    public NumberLiteral(int line, int column, int value) {
        super(line, column);
        this.value = value;
    }

    @Override
    public List<? extends AstLatNode> getChildren() {
        return List.of();
    }

    @Override
    public String getNodeLabel() {
        return "NumberLiteral: " + value;
    }

    @Override
    public <R> R accept(AstLatVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
