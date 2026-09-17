package com.navi.backend.ast.y.declarations;

import com.navi.backend.ast.y.AstYNode;

public abstract class Initializer extends AstYNode {
    protected Initializer(int line, int column) {
        super(line, column);
    }
}