package com.navi.backend.ast.z.declarations;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ClassDeclaration extends AstZNode {
    private final String name;
    private final String superClassName;
    private final List<ClassMember> members;

    public ClassDeclaration(int line, int column, String name, List<ClassMember> members) {
        this(line, column, name, null, members);
    }

    public ClassDeclaration(int line, int column, String name, String superClassName, List<ClassMember> members) {
        super(line, column);
        this.name = name;
        this.superClassName = superClassName;
        this.members = members;
    }

    @Override
    public List<? extends AstZNode> getChildren() {
        return members == null ? List.of() : new ArrayList<>(members);
    }

    @Override
    public String getNodeLabel() {
        return "ClassDeclaration: " + name;
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
