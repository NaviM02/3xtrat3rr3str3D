package com.navi.backend.ast.y.statements;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.expressions.Expression;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PrintStatement extends Statement {
    private final List<Expression> expressions;

    public PrintStatement(int line, int column, List<Expression> expressions) {
        super(line, column);
        this.expressions = expressions;
    }

    @Override
    public List<? extends AstYNode> getChildren() {
        return expressions == null ? List.of() : new ArrayList<>(expressions);
    }

    @Override
    public <R> R accept(AstYVisitor<R> visitor) {
        return visitor.visit(this);
    }
}