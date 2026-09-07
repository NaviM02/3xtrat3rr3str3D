package com.navi.backend.ast.y;

import com.navi.backend.ast.y.declarations.FunctionDeclarationNode;
import com.navi.backend.ast.y.declarations.StructureDeclarationNode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AstY extends YNode {

    private List<StructureDeclarationNode> structures;
    private List<FunctionDeclarationNode> functions;

    public AstY() {
        this.structures = new ArrayList<>();
        this.functions = new ArrayList<>();
    }
}