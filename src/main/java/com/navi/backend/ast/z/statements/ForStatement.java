package com.navi.backend.ast.z.statements;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ForStatement extends Statement {
    private final AstZNode initializer;
    private final AstZNode condition;
    private final AstZNode update;
    private final BlockStatement block;

    public ForStatement(int line, int column, AstZNode initializer, AstZNode condition, AstZNode update, BlockStatement block) {
        super(line, column);
        this.initializer = initializer;
        this.condition = condition;
        this.update = update;
        this.block = block;
    }

    @Override
    public List<? extends AstZNode> getChildren() {
        List<AstZNode> children = new ArrayList<>();

        if (initializer != null) children.add(initializer);
        if (condition != null) children.add(condition);
        if (update != null) children.add(update);
        if (block != null) children.add(block);

        return children;
    }

    @Override
    public String getNodeLabel() {
        return "ForStatement";
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}