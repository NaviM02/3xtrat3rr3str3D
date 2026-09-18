package com.navi.backend.ast.z.statements;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.declarations.VariableDeclaration;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class VariableDeclarationStatement extends Statement {
    private final VariableDeclaration declaration;

    public VariableDeclarationStatement(int line, int column, VariableDeclaration declaration) {
        super(line, column);
        this.declaration = declaration;
    }

    @Override
    public List<? extends AstZNode> getChildren() {
        List<AstZNode> children = new ArrayList<>();
        if (declaration != null) children.add(declaration);
        return children;
    }

    @Override
    public String getNodeLabel() {
        return "VariableDeclarationStatement";
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
