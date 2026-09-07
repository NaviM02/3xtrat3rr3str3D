package com.navi.backend.ast.lat.expressions;

import com.navi.backend.ast.lat.AstLatNode;
import lombok.Getter;

@Getter
public abstract class Expression extends AstLatNode {
    protected Expression(int line, int column) {
        super(line, column);
    }
}
