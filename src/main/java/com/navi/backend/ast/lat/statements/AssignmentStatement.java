package com.navi.backend.ast.lat.statements;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.declarations.initializers.Initializer;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
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
    public List<? extends AstLatNode> getChildren() {
        return List.of(target, initializer);
    }

    @Override
    public <R> R accept(AstLatVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
