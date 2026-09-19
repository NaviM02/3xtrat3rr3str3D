package com.navi.backend.ast.lat.global;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class ImportDeclaration extends AstLatNode {
    private final String path;

    public ImportDeclaration(int line, int column, String path) {
        super(line, column);
        this.path = path;
    }

    @Override
    public List<? extends AstLatNode> getChildren() {
        return List.of();
    }

    @Override
    public String getNodeLabel() {
        return "ImportDeclaration: " + path;
    }

    @Override
    public <R> R accept(AstLatVisitor<R> visitor) {
        return visitor.visit(this);
    }
}