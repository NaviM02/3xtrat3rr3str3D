package com.navi.backend.ast.z.expressions;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.statements.AssignmentOperator;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class AssignmentExpression extends Expression {
    private final Expression target;
    private final AssignmentOperator operator;
    private final Expression value;

    public AssignmentExpression(int line, int column, Expression target, AssignmentOperator operator, Expression value) {
        super(line, column);
        this.target = target;
        this.operator = operator;
        this.value = value;
    }

    @Override
    public List<? extends AstZNode> getChildren() {
        return List.of(target, value);
    }

    @Override
    public String getNodeLabel() {
        return "AssignmentExpression: " + operator;
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
