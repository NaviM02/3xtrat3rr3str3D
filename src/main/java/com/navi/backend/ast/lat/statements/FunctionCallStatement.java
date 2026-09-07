package com.navi.backend.ast.lat.statements;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FunctionCallStatement extends Statement {
    private final Expression callee;
    private final List<Expression> arguments;

    public FunctionCallStatement(int line, int column, Expression callee, List<Expression> arguments) {
        super(line, column);
        this.callee = callee;
        this.arguments = arguments;
    }

    @Override
    public List<? extends AstLatNode> getChildren() {
        List<AstLatNode> children = new ArrayList<>();
        children.add(callee);
        children.addAll(arguments);
        return children;
    }

    @Override
    public String getNodeLabel() {
        return "FunctionCallStatement";
    }

    @Override
    public <R> R accept(AstLatVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
