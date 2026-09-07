package com.navi.backend.ast.y.declarations;

import com.navi.backend.ast.y.YNode;
import com.navi.backend.ast.y.types.TypeNode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class StructureFieldNode extends YNode {

    private TypeNode type;
    private String name;
    private List<Integer> arrayDimensions;

    public StructureFieldNode(TypeNode type, String name) {
        this.type = type;
        this.name = name;
        this.arrayDimensions = new ArrayList<>();
    }
}