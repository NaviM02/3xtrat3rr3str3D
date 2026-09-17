package com.navi.backend.ast.y.declarations;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.expressions.Expression;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class ExpressionInitializer extends Initializer {
    private final Expression expression;

    public ExpressionInitializer(int line, int column, Expression expression) {
        super(line, column);
        this.expression = expression;
    }

    @Override
    public List<? extends AstYNode> getChildren() {
        return List.of(expression);
    }

    @Override
    public <R> R accept(AstYVisitor<R> visitor) {
        return visitor.visit(this);
    }
}