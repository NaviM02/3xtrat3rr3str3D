package com.navi.backend.ast.z.declarations;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class VariableDeclaration extends AstZNode {
    private final Type type;
    private final List<VariableDeclarator> variables;

    public VariableDeclaration(int line, int column, Type type, List<VariableDeclarator> variables) {
        super(line, column);
        this.type = type;
        this.variables = variables;
    }

    @Override
    public List<? extends AstZNode> getChildren() {
        List<AstZNode> children = new ArrayList<>();
        children.add(type);
        if (variables != null) children.addAll(variables);
        return children;
    }

    @Override
    public String getNodeLabel() {
        return "VariableDeclaration";
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
