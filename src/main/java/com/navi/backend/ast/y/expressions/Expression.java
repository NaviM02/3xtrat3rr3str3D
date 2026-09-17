package com.navi.backend.ast.y.expressions;

import com.navi.backend.ast.y.AstYNode;

public abstract class Expression extends AstYNode {

    protected Expression(int line, int column) {
        super(line, column);
    }
}