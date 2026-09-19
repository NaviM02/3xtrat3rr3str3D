package com.navi.backend.ast.lat.expressions;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ObjectCreationExpression extends Expression {
    private final String type;
    private final List<Expression> arguments;

    public ObjectCreationExpression(int line, int column, String type, List<Expression> arguments) {
        super(line, column);
        this.type = type;
        this.arguments = arguments;
    }

    @Override
    public List<? extends AstLatNode> getChildren() {
        if (arguments == null) {
            return List.of();
        }

        return new ArrayList<>(arguments);
    }

    @Override
    public String getNodeLabel() {
        return "ObjectCreationExpression: " + type;
    }

    @Override
    public <R> R accept(AstLatVisitor<R> visitor) {
        return visitor.visit(this);
    }
}