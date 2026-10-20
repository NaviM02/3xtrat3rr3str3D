package com.navi.backend.ast.z.statements;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.expressions.Expression;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

// llamada explicita super(...) al constructor del padre; solo valida como
// primera instruccion de un constructor
@Getter
public class SuperCallStatement extends Statement {
    private final List<Expression> arguments;

    public SuperCallStatement(int line, int column, List<Expression> arguments) {
        super(line, column);
        this.arguments = arguments == null ? List.of() : arguments;
    }

    @Override
    public List<? extends AstZNode> getChildren() {
        return new ArrayList<>(arguments);
    }

    @Override
    public String getNodeLabel() {
        return "SuperCallStatement";
    }

    @Override
    public <R> R accept(AstZVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
