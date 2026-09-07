package com.navi.backend.ast.lat.statements;

import com.navi.backend.ast.lat.AstNode;
import com.navi.backend.ast.lat.declarations.initializers.Initializer;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.visitors.AstVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class AssignmentStatement extends Statement {
    private final Expression target;
    private final Initializer initializer;

    public AssignmentStatement(int line, int column, Expression target, Initializer initializer) {
        super(line, column);
        this.target = target;
        this.initializer = initializer;
    }

    @Override
    public List<? extends AstNode> getChildren() {
        return List.of(target, initializer);
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
