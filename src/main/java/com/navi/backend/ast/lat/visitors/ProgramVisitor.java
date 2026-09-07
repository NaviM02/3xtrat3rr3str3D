package com.navi.backend.ast.lat.visitors;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.global.FunctionDeclaration;
import com.navi.backend.ast.lat.global.GlobalVariableSection;
import com.navi.backend.ast.lat.global.Program;
import com.navi.backend.ast.lat.declarations.Declaration;
import com.navi.backend.lexer_parser.lat.PigLatinParser;
import com.navi.backend.ast.lat.statements.Statement;

import java.util.ArrayList;
import java.util.List;

public class ProgramVisitor extends DeclarationVisitor {
    @Override
    public AstLatNode visitProgram(PigLatinParser.ProgramContext ctx) {
        GlobalVariableSection globalVariables = null;
        List<FunctionDeclaration> functions = new ArrayList<>();
        List<Statement> mainStatements = new ArrayList<>();

        if (ctx.globalVariablesSection() != null) {
            globalVariables = (GlobalVariableSection) visit(ctx.globalVariablesSection());
        }

        if (ctx.functionSection() != null) {
            for (PigLatinParser.FunctionDeclarationContext function : ctx.functionSection().functionDeclaration()) {
                functions.add((FunctionDeclaration) visit(function));
            }
        }

        for (PigLatinParser.StatementContext statement : ctx.mainSection().statement()) {
            mainStatements.add((Statement) visit(statement));
        }

        return new Program(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), globalVariables, functions, mainStatements);
    }

    @Override
    public AstLatNode visitGlobalVariablesSection(PigLatinParser.GlobalVariablesSectionContext ctx) {
        List<Declaration> declarations = new ArrayList<>();

        for (PigLatinParser.DeclarationContext declaration : ctx.declaration()) {
            declarations.add((Declaration) visit(declaration));
        }

        return new GlobalVariableSection(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), declarations);
    }
}
