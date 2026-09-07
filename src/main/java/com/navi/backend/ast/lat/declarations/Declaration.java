package com.navi.backend.ast.lat.declarations;

import com.navi.backend.ast.lat.AstNode;

public abstract class Declaration extends AstNode {
    protected Declaration(int line, int column) {
        super(line, column);
    }
}
