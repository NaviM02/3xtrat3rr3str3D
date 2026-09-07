package com.navi.backend.ast.y.statements;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultCaseNode extends YBlockNode {

    private BreakStatementNode breakStatement;
}