package com.navi.backend.ast.z.expressions.literals;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.expressions.Expression;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class LiteralExpression extends Expression {
    private final Object value;

    public LiteralExpression(int line, int column, Object value) {
        super(line, column);
        this.value = value;
    }

    @Override
    public List<? extends AstZNode> getChildren() {
        return List.of();
    }

    @Override
    public String getNodeLabel() {
        return "LiteralExpression: " + value;
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
