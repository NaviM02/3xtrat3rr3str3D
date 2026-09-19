package com.navi.backend.ast.z.visitors;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.declarations.ClassDeclaration;
import com.navi.backend.ast.z.global.ProgramZ;
import com.navi.backend.lexer_parser.z.ZParser;

public class ProgramVisitor extends DeclarationVisitor {
    @Override
    public AstZNode visitProgram(ZParser.ProgramContext ctx) {
        ClassDeclaration classDeclaration = (ClassDeclaration) visit(ctx.classDeclaration());
        return new ProgramZ(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), classDeclaration);
    }
}
