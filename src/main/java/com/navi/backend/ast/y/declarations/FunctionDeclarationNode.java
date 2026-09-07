package com.navi.backend.ast.y.declarations;

import com.navi.backend.ast.y.YNode;
import com.navi.backend.ast.y.statements.StatementNode;
import com.navi.backend.ast.y.types.TypeNode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FunctionDeclarationNode extends YNode {

    private String name;
    private List<ParameterNode> parameters;
    private TypeNode returnType;
    private List<StatementNode> statements;

    public FunctionDeclarationNode(String name) {
        this.name = name;
        this.parameters = new ArrayList<>();
        this.statements = new ArrayList<>();
    }
}