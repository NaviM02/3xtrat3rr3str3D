package com.navi.backend.ast.y.declarations;

import com.navi.backend.ast.y.YNode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class StructureDeclarationNode extends YNode {

    private String name;
    private List<StructureFieldNode> fields;

    public StructureDeclarationNode(String name) {
        this.name = name;
        this.fields = new ArrayList<>();
    }
}