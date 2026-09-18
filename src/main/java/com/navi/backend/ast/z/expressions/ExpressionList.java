package com.navi.backend.ast.z.expressions;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ExpressionList extends AstZNode {

    private final List<Expression> expressions;

    public ExpressionList(int line, int column, List<Expression> expressions) {
        super(line, column);
        this.expressions = expressions;
    }

    @Override
    public List<? extends AstZNode> getChildren() {
        return new ArrayList<>(expressions);
    }

    @Override
    public String getNodeLabel() {
        return "ExpressionList";
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}