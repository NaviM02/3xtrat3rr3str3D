package com.navi.backend.ast.lat.expressions.literals;

import com.navi.backend.ast.lat.AstNode;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.visitors.AstVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class DecimalLiteral extends Expression {
    private final double value;

    public DecimalLiteral(int line, int column, double value) {
        super(line, column);
        this.value = value;
    }

    @Override
    public List<? extends AstNode> getChildren() {
        return List.of();
    }

    @Override
    public String getNodeLabel() {
        return "DecimalLiteral: " + value;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
