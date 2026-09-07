package com.navi.backend.ast.lat.global;

import com.navi.backend.ast.lat.AstNode;
import com.navi.backend.ast.lat.statements.BlockStatement;
import com.navi.backend.ast.lat.visitors.AstVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FunctionBody extends AstNode {
    private final LocalVariableSection localVariables;
    private final BlockStatement body;

    public FunctionBody(int line, int column, LocalVariableSection localVariables, BlockStatement body) {
        super(line, column);
        this.localVariables = localVariables;
        this.body = body;
    }

    @Override
    public List<? extends AstNode> getChildren() {
        List<AstNode> children = new ArrayList<>();

        if (localVariables != null) {
            children.add(localVariables);
        }

        if (body != null) {
            children.add(body);
        }

        return children;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
