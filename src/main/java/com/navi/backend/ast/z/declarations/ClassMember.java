package com.navi.backend.ast.z.declarations;

import com.navi.backend.ast.z.AstZNode;

public abstract class ClassMember extends AstZNode {
    protected ClassMember(int line, int column) {
        super(line, column);
    }
}
