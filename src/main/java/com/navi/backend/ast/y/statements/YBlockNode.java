package com.navi.backend.ast.y.statements;

import com.navi.backend.ast.y.YNode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class YBlockNode extends YNode {

    private List<StatementNode> statements;

    protected YBlockNode() {
        this.statements = new ArrayList<>();
    }
}