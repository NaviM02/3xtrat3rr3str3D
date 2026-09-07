package com.navi.backend.ast.lat.global;

import com.navi.backend.ast.lat.AstNode;
import com.navi.backend.ast.lat.visitors.AstVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FunctionDeclaration extends AstNode {
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
    public List<? extends AstNode> getChildren() {
        List<AstNode> children = new ArrayList<>();

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
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
