package com.navi.backend.ast.lat.statements;

import com.navi.backend.ast.lat.AstLatNode;

public abstract class Statement extends AstLatNode {
    protected Statement(int line, int column) {
        super(line, column);
    }
}
