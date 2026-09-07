package com.navi.backend.ast.lat.statements;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import lombok.Getter;
import java.util.List;

@Getter
public class IfStatement extends Statement {
    private final Expression condition;
    private final BlockStatement thenBlock;
    private final List<ElseIfStatement> elseIfStatements;
    private final BlockStatement elseBlock;

    public IfStatement(int line, int column, Expression condition, BlockStatement thenBlock, List<ElseIfStatement> elseIfStatements, BlockStatement elseBlock) {
        super(line, column);
        this.condition = condition;
        this.thenBlock = thenBlock;
        this.elseIfStatements = elseIfStatements;
        this.elseBlock = elseBlock;
    }

    @Override
    public List<? extends AstLatNode> getChildren() {
        List<AstLatNode> children = new java.util.ArrayList<>();

        children.add(condition);
        children.add(thenBlock);

        if (elseIfStatements != null) {
            children.addAll(elseIfStatements);
        }

        if (elseBlock != null) {
            children.add(elseBlock);
        }

        return children;
    }

    @Override
    public <R> R accept(AstLatVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
