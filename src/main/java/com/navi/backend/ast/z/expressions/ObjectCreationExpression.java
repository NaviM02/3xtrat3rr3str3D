package com.navi.backend.ast.z.expressions;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ObjectCreationExpression extends Expression {
    private final String typeName;
    private final List<Expression> arguments;

    public ObjectCreationExpression(int line, int column, String typeName, List<Expression> arguments) {
        super(line, column);
        this.typeName = typeName;
        this.arguments = arguments;
    }

    @Override
    public List<? extends AstZNode> getChildren() {
        return arguments == null ? List.of() : new ArrayList<>(arguments);
    }

    @Override
    public String getNodeLabel() {
        return "ObjectCreationExpression: " + typeName;
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
