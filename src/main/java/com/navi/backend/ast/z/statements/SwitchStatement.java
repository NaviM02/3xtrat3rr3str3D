package com.navi.backend.ast.z.statements;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.expressions.Expression;
import com.navi.backend.ast.z.visitors.AstZVisitor;
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
    public List<? extends AstZNode> getChildren() {
        List<AstZNode> children = new ArrayList<>();
        children.add(expression);
        if (cases != null) children.addAll(cases);
        if (defaultCase != null) children.add(defaultCase);
        return children;
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
