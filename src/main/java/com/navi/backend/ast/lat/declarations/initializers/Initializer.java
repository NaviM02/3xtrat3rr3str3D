package com.navi.backend.ast.lat.declarations.initializers;

import com.navi.backend.ast.lat.AstLatNode;

public abstract class Initializer extends AstLatNode {
    public Initializer(int line, int column) {
        super(line, column);
    }
}
