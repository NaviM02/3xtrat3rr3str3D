package com.navi.backend.ast.y.statements;

import com.navi.backend.ast.y.declarations.VariableDeclarationNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForVariableDeclarationNode extends ForInitializerNode {

    private VariableDeclarationNode declaration;

    public ForVariableDeclarationNode(VariableDeclarationNode declaration) {
        this.declaration = declaration;
    }
}