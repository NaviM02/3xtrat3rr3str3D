package com.navi.backend.ast.y.declarations;

import com.navi.backend.ast.y.AstYNode;

public abstract class Parameter extends AstYNode {
    protected Parameter(int line, int column) {
        super(line, column);
    }
}