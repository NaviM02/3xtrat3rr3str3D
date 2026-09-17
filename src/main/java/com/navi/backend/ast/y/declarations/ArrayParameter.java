package com.navi.backend.ast.y.declarations;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class ArrayParameter extends Parameter {
    private final Type type;
    private final String name;

    public ArrayParameter(int line, int column, Type type, String name) {
        super(line, column);
        this.type = type;
        this.name = name;
    }

    @Override
    public List<? extends AstYNode> getChildren() {
        return List.of(type);
    }

    @Override
    public String getNodeLabel() {
        return "ArrayParameter: " + name;
    }

    @Override
    public <R> R accept(AstYVisitor<R> visitor) {
        return visitor.visit(this);
    }
}