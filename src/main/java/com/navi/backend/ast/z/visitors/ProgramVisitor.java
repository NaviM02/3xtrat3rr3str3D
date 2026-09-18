package com.navi.backend.ast.z.visitors;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.declarations.ClassDeclaration;
import com.navi.backend.ast.z.global.Program;
import com.navi.backend.lexer_parser.z.ZParser;

public class ProgramVisitor extends DeclarationVisitor {
    @Override
    public AstZNode visitProgram(ZParser.ProgramContext ctx) {
        ClassDeclaration classDeclaration = (ClassDeclaration) visit(ctx.classDeclaration());
        return new Program(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), classDeclaration);
    }
}
