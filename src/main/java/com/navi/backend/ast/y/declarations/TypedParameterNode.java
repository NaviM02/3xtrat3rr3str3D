package com.navi.backend.ast.y.declarations;

import com.navi.backend.ast.y.types.TypeNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypedParameterNode extends ParameterNode {

    private TypeNode type;
    private String name;

    public TypedParameterNode(TypeNode type, String name) {
        this.type = type;
        this.name = name;
    }
}