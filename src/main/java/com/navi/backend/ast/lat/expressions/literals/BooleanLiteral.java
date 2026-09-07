package com.navi.backend.ast.lat.expressions.literals;

import com.navi.backend.ast.lat.AstNode;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.visitors.AstVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class BooleanLiteral extends Expression {
    private final boolean value;

    public BooleanLiteral(int line, int column, boolean value) {
        super(line, column);
        this.value = value;
    }

    @Override
    public List<? extends AstNode> getChildren() {
        return List.of();
    }

    @Override
    public String getNodeLabel() {
        return "BooleanLiteral: " + value;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
