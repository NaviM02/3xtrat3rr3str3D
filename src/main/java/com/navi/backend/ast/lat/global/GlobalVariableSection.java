package com.navi.backend.ast.lat.global;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.declarations.Declaration;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class GlobalVariableSection extends AstLatNode {
    private final List<Declaration> declarations;

    public GlobalVariableSection(int line, int column, List<Declaration> declarations) {
        super(line, column);
        this.declarations = declarations;
    }

    @Override
    public List<? extends AstLatNode> getChildren() {
        return declarations;
    }

    @Override
    public <R> R accept(AstLatVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
