package com.navi.backend.ast.y.visitors;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.declarations.FunctionDeclaration;
import com.navi.backend.ast.y.declarations.StructureDeclaration;
import com.navi.backend.ast.y.global.Program;
import com.navi.backend.lexer_parser.y.YParser;

import java.util.ArrayList;
import java.util.List;

public class ProgramVisitor extends DeclarationVisitor {

    @Override
    public AstYNode visitProgram(YParser.ProgramContext ctx) {
        List<StructureDeclaration> structures = new ArrayList<>();
        List<FunctionDeclaration> functions = new ArrayList<>();

        if (ctx.structuresSection() != null) {
            for (YParser.StructureDeclarationContext structureCtx : ctx.structuresSection().structureDeclaration()) {
                structures.add((StructureDeclaration) visit(structureCtx));
            }
        }

        if (ctx.functionsSection() != null) {
            for (YParser.FunctionDeclarationContext functionCtx : ctx.functionsSection().functionDeclaration()) {
                functions.add((FunctionDeclaration) visit(functionCtx));
            }
        }

        return new Program(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            structures,
            functions
        );
    }
}