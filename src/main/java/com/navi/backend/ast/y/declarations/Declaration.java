package com.navi.backend.ast.y.declarations;

import com.navi.backend.ast.y.statements.Statement;

public abstract class Declaration extends Statement {

    protected Declaration(int line, int column) {
        super(line, column);
    }
}