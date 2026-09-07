package com.navi.backend.ast.y.declarations;

import com.navi.backend.ast.y.YNode;
import com.navi.backend.ast.y.expressions.ExpressionNode;
import com.navi.backend.ast.y.types.TypeNode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class VariableDeclarationNode extends YNode {

    private TypeNode type;
    private String name;
    private List<ExpressionNode> arrayDimensions;
    private InitializerNode initializer;

    public VariableDeclarationNode(TypeNode type, String name) {
        this.type = type;
        this.name = name;
        this.arrayDimensions = new ArrayList<>();
    }
}