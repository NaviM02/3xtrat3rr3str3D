package com.navi.backend.ast.lat.global;

import com.navi.backend.ast.lat.AstNode;
import com.navi.backend.ast.lat.statements.Statement;
import com.navi.backend.ast.lat.visitors.AstVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Program extends AstNode {
    private final GlobalVariableSection globalVariables;
    private final List<FunctionDeclaration> functions;
    private final List<Statement> mainStatements;

    public Program(int line, int column, GlobalVariableSection globalVariables, List<FunctionDeclaration> functions, List<Statement> mainStatements) {
        super(line, column);
        this.globalVariables = globalVariables;
        this.functions = functions;
        this.mainStatements = mainStatements;
    }

    @Override
    public List<? extends AstNode> getChildren() {
        List<AstNode> children = new ArrayList<>();

        if (globalVariables != null) {
            children.add(globalVariables);
        }

        if (functions != null) {
            children.addAll(functions);
        }

        if (mainStatements != null) {
            children.addAll(mainStatements);
        }

        return children;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
