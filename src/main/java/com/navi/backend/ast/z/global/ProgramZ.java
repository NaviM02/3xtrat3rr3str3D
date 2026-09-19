package com.navi.backend.ast.z.global;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.declarations.ClassDeclaration;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class ProgramZ extends AstZNode {
    private final ClassDeclaration classDeclaration;

    public ProgramZ(int line, int column, ClassDeclaration classDeclaration) {
        super(line, column);
        this.classDeclaration = classDeclaration;
    }

    @Override
    public List<? extends AstZNode> getChildren() {
        return List.of(classDeclaration);
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
