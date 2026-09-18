package com.navi.backend.ast.z.statements;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.expressions.Expression;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PrintStatement extends Statement {
    private final List<Expression> arguments;

    public PrintStatement(int line, int column, List<Expression> arguments) {
        super(line, column);
        this.arguments = arguments;
    }

    @Override
    public List<? extends AstZNode> getChildren() {
        return arguments == null ? List.of() : new ArrayList<>(arguments);
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
