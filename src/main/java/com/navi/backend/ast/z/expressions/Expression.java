package com.navi.backend.ast.z.expressions;

import com.navi.backend.ast.z.AstZNode;

public abstract class Expression extends AstZNode {
    protected Expression(int line, int column) {
        super(line, column);
    }
}
