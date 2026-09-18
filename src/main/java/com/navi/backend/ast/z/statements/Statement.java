package com.navi.backend.ast.z.statements;

import com.navi.backend.ast.z.AstZNode;

public abstract class Statement extends AstZNode {
    protected Statement(int line, int column) {
        super(line, column);
    }
}
