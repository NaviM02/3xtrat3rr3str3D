package com.navi.backend.ast.lat.global;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FunctionDeclaration extends AstLatNode {
    private final String name;
    private final String returnType;
    private final List<Parameter> parameters;
    private final FunctionBody body;

    public FunctionDeclaration(int line, int column, String name, String returnType, List<Parameter> parameters, FunctionBody body) {
        super(line, column);
        this.name = name;
        this.returnType = returnType;
        this.parameters = parameters;
        this.body = body;
    }

    @Override
    public List<? extends AstLatNode> getChildren() {
        List<AstLatNode> children = new ArrayList<>();

        if (parameters != null) {
            children.addAll(parameters);
        }

        if (body != null) {
            children.add(body);
        }

        return children;
    }

    @Override
    public String getNodeLabel() {
        return "FunctionDeclaration: " + returnType + " " + name;
    }

    @Override
    public <R> R accept(AstLatVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
