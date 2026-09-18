package com.navi.backend.ast.z.statements;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.expressions.Expression;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class IfStatement extends Statement {
    private final Expression condition;
    private final AstZNode thenBranch;
    private final List<ElseIfClause> elseIfClauses;
    private final AstZNode elseBranch;

    public IfStatement(int line, int column, Expression condition, AstZNode thenBranch, List<ElseIfClause> elseIfClauses, AstZNode elseBranch) {
        super(line, column);
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseIfClauses = elseIfClauses;
        this.elseBranch = elseBranch;
    }

    @Override
    public List<? extends AstZNode> getChildren() {
        List<AstZNode> children = new ArrayList<>();
        children.add(condition);
        children.add(thenBranch);
        if (elseIfClauses != null) children.addAll(elseIfClauses);
        if (elseBranch != null) children.add(elseBranch);
        return children;
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
