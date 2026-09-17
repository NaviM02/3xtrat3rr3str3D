package com.navi.backend.ast.y.statements;

import com.navi.backend.ast.y.AstYNode;

public abstract class Statement extends AstYNode {

    protected Statement(int line, int column) {
        super(line, column);
    }
}