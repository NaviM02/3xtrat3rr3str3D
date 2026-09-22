package com.navi.backend.semantic;

import com.navi.backend.ast.lat.global.Program;
import com.navi.backend.ast.y.global.ProgramY;
import com.navi.backend.ast.z.global.ProgramZ;
import com.navi.backend.semantic.lat.LatDeclarationVisitor;
import com.navi.backend.semantic.y.YDeclarationVisitor;
import com.navi.backend.semantic.z.ZDeclarationVisitor;

public class SemanticAnalyzer {

    private final SemanticContext context;

    public SemanticAnalyzer(SemanticContext context) {
        this.context = context;
    }

    public void analyzeLat(Program program) {
        LatDeclarationVisitor declarations = new LatDeclarationVisitor(context);
        program.accept(declarations);
        /*
        LatSemanticVisitor semantic = new LatSemanticVisitor(context);
        program.accept(semantic);*/
    }

    public void analyzeY(ProgramY program) {
        YDeclarationVisitor declarations = new YDeclarationVisitor(context);
        program.accept(declarations);
        /*
        YSemanticVisitor semantic = new YSemanticVisitor(context);
        program.accept(semantic);*/
    }

    public void analyzeZ(ProgramZ program) {
        ZDeclarationVisitor declarations = new ZDeclarationVisitor(context);
        program.accept(declarations);
        /*
        ZSemanticVisitor semantic = new ZSemanticVisitor(context);
        program.accept(semantic);*/
    }
}