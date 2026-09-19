package com.navi.backend.ast.y.global;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.declarations.FunctionDeclaration;
import com.navi.backend.ast.y.declarations.StructureDeclaration;
import com.navi.backend.ast.y.visitors.AstYVisitor;

import java.util.ArrayList;
import java.util.List;

public class ProgramY extends AstYNode {

    private final List<StructureDeclaration> structures;
    private final List<FunctionDeclaration> functions;

    public ProgramY(int line, int column, List<StructureDeclaration> structures, List<FunctionDeclaration> functions) {
        super(line, column);
        this.structures = structures;
        this.functions = functions;
    }

    @Override
    public List<? extends AstYNode> getChildren() {
        List<AstYNode> children = new ArrayList<>();

        if (structures != null) {
            children.addAll(structures);
        }

        if (functions != null) {
            children.addAll(functions);
        }

        return children;
    }

    @Override
    public <R> R accept(AstYVisitor<R> visitor) {
        return visitor.visit(this);
    }
}