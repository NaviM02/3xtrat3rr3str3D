package com.navi.backend.ast.lat.declarations.initializers;

import com.navi.backend.ast.lat.AstNode;

public abstract class Initializer extends AstNode {
    public Initializer(int line, int column) {
        super(line, column);
    }
}
