package com.navi.backend.ast.y.types;

import com.navi.backend.ast.y.YNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeNode extends YNode {

    private String name;

    public TypeNode(String name) {
        this.name = name;
    }
}