package com.navi.backend.ast.y.expressions.postfix;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberAccessNode extends PostfixOperationNode {

    private String member;

    public MemberAccessNode(String member) {
        this.member = member;
    }
}