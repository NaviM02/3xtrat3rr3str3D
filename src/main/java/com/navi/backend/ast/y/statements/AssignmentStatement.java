package com.navi.backend.ast.y.statements;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.expressions.BinaryOperator;
import com.navi.backend.ast.y.expressions.Expression;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class AssignmentStatement extends Statement {
    private final Expression target;
    private final AssignmentOperator operator;
    private final Expression value;

    public AssignmentStatement(int line, int column, Expression target, AssignmentOperator operator, Expression value) {
        super(line, column);
        this.target = target;
        this.operator = operator;
        this.value = value;
    }

    @Override
    public List<? extends AstYNode> getChildren() {
        return List.of(target, value);
    }

    @Override
    public String getNodeLabel() {
        return "AssignmentStatement: " + operator;
    }

    @Override
    public <R> R accept(AstYVisitor<R> visitor) {
        return visitor.visit(this);
    }
}