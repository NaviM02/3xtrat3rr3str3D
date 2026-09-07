package com.navi.backend.ast.lat.statements;

import com.navi.backend.ast.lat.AstNode;

public abstract class Statement extends AstNode {
    protected Statement(int line, int column) {
        super(line, column);
    }
}
