package com.navi.backend.ast.y.declarations;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StructureParameterNode extends ParameterNode {

    private String structureType;
    private String name;

    public StructureParameterNode(String structureType, String name) {
        this.structureType = structureType;
        this.name = name;
    }
}