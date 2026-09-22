package com.navi.backend.ast.z.declarations;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.statements.BlockStatement;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MethodDeclaration extends ClassMember {
    private final boolean publicAccess;
    private final ZType returnType;
    private final String name;
    private final List<Parameter> parameters;
    private final BlockStatement body;

    public MethodDeclaration(int line, int column, boolean publicAccess, ZType returnType, String name, List<Parameter> parameters, BlockStatement body) {
        super(line, column);
        this.publicAccess = publicAccess;
        this.returnType = returnType;
        this.name = name;
        this.parameters = parameters;
        this.body = body;
    }

    @Override
    public List<? extends AstZNode> getChildren() {
        List<AstZNode> children = new ArrayList<>();
        if (returnType != null) children.add(returnType);
        if (parameters != null) children.addAll(parameters);
        children.add(body);
        return children;
    }

    @Override
    public String getNodeLabel() {
        return "MethodDeclaration: " + name;
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
