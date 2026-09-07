package com.navi.backend.ast.lat.declarations.initializers;

import com.navi.backend.ast.lat.AstNode;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.visitors.AstVisitor;
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
    public List<? extends AstNode> getChildren() {
        return List.of(expression);
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
