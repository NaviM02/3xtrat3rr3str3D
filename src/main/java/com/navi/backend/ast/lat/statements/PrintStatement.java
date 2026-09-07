package com.navi.backend.ast.lat.statements;

import com.navi.backend.ast.lat.AstNode;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.visitors.AstVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class PrintStatement extends Statement {
    private final List<Expression> expressions;

    public PrintStatement(int line, int column, List<Expression> expressions) {
        super(line, column);
        this.expressions = expressions;
    }

    @Override
    public List<? extends AstNode> getChildren() {
        return expressions;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
