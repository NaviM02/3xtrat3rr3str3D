package com.navi.backend.ast.lat.declarations;

import com.navi.backend.ast.lat.AstLatNode;

public abstract class Declaration extends AstLatNode {
    protected Declaration(int line, int column) {
        super(line, column);
    }
}
