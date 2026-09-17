package com.navi.backend.ast.y.statements;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class IfStatement extends Statement {
    private final com.navi.backend.ast.y.expressions.Expression condition;
    private final List<Statement> statements;
    private final List<ElseIfClause> elseIfClauses;
    private final ElseClause elseClause;

    public IfStatement(int line, int column, com.navi.backend.ast.y.expressions.Expression condition, List<Statement> statements, List<ElseIfClause> elseIfClauses, ElseClause elseClause) {
        super(line, column);
        this.condition = condition;
        this.statements = statements;
        this.elseIfClauses = elseIfClauses;
        this.elseClause = elseClause;
    }

    @Override
    public List<? extends AstYNode> getChildren() {
        List<AstYNode> children = new ArrayList<>();
        children.add(condition);
        if (statements != null) children.addAll(statements);
        if (elseIfClauses != null) children.addAll(elseIfClauses);
        if (elseClause != null) children.add(elseClause);
        return children;
    }

    @Override
    public <R> R accept(AstYVisitor<R> visitor) {
        return visitor.visit(this);
    }
}