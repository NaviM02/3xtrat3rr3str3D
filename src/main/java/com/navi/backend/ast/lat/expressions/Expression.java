package com.navi.backend.ast.lat.expressions;

import com.navi.backend.ast.lat.AstNode;
import lombok.Getter;

@Getter
public abstract class Expression extends AstNode {
    protected Expression(int line, int column) {
        super(line, column);
    }
}
