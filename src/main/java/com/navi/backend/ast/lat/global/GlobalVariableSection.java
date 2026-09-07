package com.navi.backend.ast.lat.global;

import com.navi.backend.ast.lat.AstNode;
import com.navi.backend.ast.lat.declarations.Declaration;
import com.navi.backend.ast.lat.visitors.AstVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class GlobalVariableSection extends AstNode {
    private final List<Declaration> declarations;

    public GlobalVariableSection(int line, int column, List<Declaration> declarations) {
        super(line, column);
        this.declarations = declarations;
    }

    @Override
    public List<? extends AstNode> getChildren() {
        return declarations;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
