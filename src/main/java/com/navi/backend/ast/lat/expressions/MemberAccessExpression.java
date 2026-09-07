package com.navi.backend.ast.lat.expressions;

import com.navi.backend.ast.lat.AstNode;
import com.navi.backend.ast.lat.visitors.AstVisitor;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberAccessExpression extends Expression {
    private final Expression object;
    private final String member;

    public MemberAccessExpression(int line, int column, Expression object, String member) {
        super(line, column);
        this.object = object;
        this.member = member;
    }

    @Override
    public List<? extends AstNode> getChildren() {
        return List.of(object);
    }

    @Override
    public String getNodeLabel() {
        return "MemberAccessExpression: " + member;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
