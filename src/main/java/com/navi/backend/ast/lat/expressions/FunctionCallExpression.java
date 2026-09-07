package com.navi.backend.ast.lat.expressions;

import com.navi.backend.ast.lat.AstNode;
import com.navi.backend.ast.lat.visitors.AstVisitor;
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
    public List<? extends AstNode> getChildren() {
        List<AstNode> children = new java.util.ArrayList<>();

        children.add(callee);
        children.addAll(arguments);

        return children;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
