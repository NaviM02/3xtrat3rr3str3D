package com.navi.backend.ast.y.statements;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.expressions.Expression;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SwitchStatement extends Statement {
    private final Expression expression;
    private final List<SwitchCase> cases;
    private final DefaultCase defaultCase;

    public SwitchStatement(int line, int column, Expression expression, List<SwitchCase> cases, DefaultCase defaultCase) {
        super(line, column);
        this.expression = expression;
        this.cases = cases;
        this.defaultCase = defaultCase;
    }

    @Override
    public List<? extends AstYNode> getChildren() {
        List<AstYNode> children = new ArrayList<>();
        children.add(expression);
        if (cases != null) children.addAll(cases);
        if (defaultCase != null) children.add(defaultCase);
        return children;
    }

    @Override
    public <R> R accept(AstYVisitor<R> visitor) {
        return visitor.visit(this);
    }
}