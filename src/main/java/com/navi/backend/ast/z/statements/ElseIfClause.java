package com.navi.backend.ast.z.statements;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.expressions.Expression;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class ElseIfClause extends AstZNode {
    private final Expression condition;
    private final AstZNode branch;

    public ElseIfClause(int line, int column, Expression condition, AstZNode branch) {
        super(line, column);
        this.condition = condition;
        this.branch = branch;
    }

    @Override
    public List<? extends AstZNode> getChildren() {
        return List.of(condition, branch);
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
