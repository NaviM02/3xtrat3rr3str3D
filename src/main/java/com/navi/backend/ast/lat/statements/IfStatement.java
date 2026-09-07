package com.navi.backend.ast.lat.statements;

import com.navi.backend.ast.lat.AstNode;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.visitors.AstVisitor;
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
    public List<? extends AstNode> getChildren() {
        List<AstNode> children = new java.util.ArrayList<>();

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
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
