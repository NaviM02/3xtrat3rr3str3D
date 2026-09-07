package com.navi.backend.ast.lat.expressions;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import lombok.Getter;
import java.util.List;

@Getter
public class FunctionCallExpression extends Expression {
    private final Expression callee;
    private final List<Expression> arguments;

    public FunctionCallExpression(int line, int column, Expression callee, List<Expression> arguments) {
        super(line, column);
        this.callee = callee;
        this.arguments = arguments;
    }

    @Override
    public List<? extends AstLatNode> getChildren() {
        List<AstLatNode> children = new java.util.ArrayList<>();

        children.add(callee);
        children.addAll(arguments);

        return children;
    }

    @Override
    public <R> R accept(AstLatVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
