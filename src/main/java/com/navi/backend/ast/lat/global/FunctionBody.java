package com.navi.backend.ast.lat.global;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.statements.BlockStatement;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FunctionBody extends AstLatNode {
    private final LocalVariableSection localVariables;
    private final BlockStatement body;

    public FunctionBody(int line, int column, LocalVariableSection localVariables, BlockStatement body) {
        super(line, column);
        this.localVariables = localVariables;
        this.body = body;
    }

    @Override
    public List<? extends AstLatNode> getChildren() {
        List<AstLatNode> children = new ArrayList<>();

        if (localVariables != null) {
            children.add(localVariables);
        }

        if (body != null) {
            children.add(body);
        }

        return children;
    }

    @Override
    public <R> R accept(AstLatVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
