package com.navi.backend.ast.lat.global;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.statements.Statement;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Program extends AstLatNode {
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
    public List<? extends AstLatNode> getChildren() {
        List<AstLatNode> children = new ArrayList<>();

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
    public <R> R accept(AstLatVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
