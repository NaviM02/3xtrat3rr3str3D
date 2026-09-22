package com.navi.backend.ast.z.declarations;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FieldDeclaration extends ClassMember {
    private final boolean publicAccess;
    private final ZType type;
    private final List<VariableDeclarator> variables;

    public FieldDeclaration(int line, int column, boolean publicAccess, ZType type, List<VariableDeclarator> variables) {
        super(line, column);
        this.publicAccess = publicAccess;
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
        return "FieldDeclaration";
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
