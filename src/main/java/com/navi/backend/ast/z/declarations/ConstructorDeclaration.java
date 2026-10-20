package com.navi.backend.ast.z.declarations;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.statements.BlockStatement;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import com.navi.backend.semantic.enums.AccessModifier;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ConstructorDeclaration extends ClassMember {
    private final AccessModifier accessModifier;
    private final String name;
    private final List<Parameter> parameters;
    private final BlockStatement body;

    public ConstructorDeclaration(int line, int column, boolean publicAccess, String name, List<Parameter> parameters, BlockStatement body) {
        this(line, column, publicAccess ? AccessModifier.PUBLIC : AccessModifier.DEFAULT, name, parameters, body);
    }

    public ConstructorDeclaration(int line, int column, AccessModifier accessModifier, String name,
                                  List<Parameter> parameters, BlockStatement body) {
        super(line, column);
        this.accessModifier = accessModifier == null ? AccessModifier.DEFAULT : accessModifier;
        this.name = name;
        this.parameters = parameters;
        this.body = body;
    }

    public boolean isPublicAccess() {
        return accessModifier == AccessModifier.PUBLIC;
    }

    @Override
    public List<? extends AstZNode> getChildren() {
        List<AstZNode> children = new ArrayList<>();
        if (parameters != null) children.addAll(parameters);
        children.add(body);
        return children;
    }

    @Override
    public String getNodeLabel() {
        return "ConstructorDeclaration: " + name;
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
