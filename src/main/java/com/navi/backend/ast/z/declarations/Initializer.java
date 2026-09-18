package com.navi.backend.ast.z.declarations;

import com.navi.backend.ast.z.AstZNode;

public abstract class Initializer extends AstZNode {
    protected Initializer(int line, int column) {
        super(line, column);
    }
}
