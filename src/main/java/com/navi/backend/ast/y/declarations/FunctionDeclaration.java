package com.navi.backend.ast.y.declarations;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.statements.Statement;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FunctionDeclaration extends Declaration {
    private final String name;
    private final List<Parameter> parameters;
    private final Type returnType;
    private final List<Statement> statements;

    public FunctionDeclaration(int line, int column, String name, List<Parameter> parameters, Type returnType, List<Statement> statements) {
        super(line, column);
        this.name = name;
        this.parameters = parameters;
        this.returnType = returnType;
        this.statements = statements;
    }

    @Override
    public List<? extends AstYNode> getChildren() {
        List<AstYNode> children = new ArrayList<>();
        if (parameters != null) children.addAll(parameters);
        if (returnType != null) children.add(returnType);
        if (statements != null) children.addAll(statements);
        return children;
    }

    @Override
    public String getNodeLabel() {
        return "FunctionDeclaration: " + name;
    }

    @Override
    public <R> R accept(AstYVisitor<R> visitor) {
        return visitor.visit(this);
    }
}