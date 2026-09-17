package com.navi.backend.ast.y.expressions;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FunctionCallExpression extends Expression {
    private final Expression function;
    private final List<Expression> arguments;

    public FunctionCallExpression(
            int line,
            int column,
            Expression function,
            List<Expression> arguments
    ) {
        super(line, column);
        this.function = function;
        this.arguments = arguments;
    }

    @Override
    public List<? extends AstYNode> getChildren() {
        List<AstYNode> children = new ArrayList<>();
        children.add(function);

        if (arguments != null) {
            children.addAll(arguments);
        }

        return children;
    }

    @Override
    public <R> R accept(AstYVisitor<R> visitor) {
        return visitor.visit(this);
    }
}