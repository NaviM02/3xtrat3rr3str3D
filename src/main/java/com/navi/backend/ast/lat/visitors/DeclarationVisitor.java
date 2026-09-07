package com.navi.backend.ast.lat.visitors;

import com.navi.backend.ast.lat.AstNode;
import com.navi.backend.ast.lat.declarations.*;
import com.navi.backend.ast.lat.declarations.initializers.ExpressionInitializer;
import com.navi.backend.ast.lat.declarations.initializers.Initializer;
import com.navi.backend.ast.lat.declarations.initializers.StructFieldInitializer;
import com.navi.backend.ast.lat.declarations.initializers.StructInitializer;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.global.FunctionBody;
import com.navi.backend.ast.lat.global.FunctionDeclaration;
import com.navi.backend.ast.lat.global.LocalVariableSection;
import com.navi.backend.ast.lat.global.Parameter;
import com.navi.backend.lexer_parser.lat.PigLatinParser;
import com.navi.backend.ast.lat.statements.BlockStatement;
import com.navi.backend.ast.lat.statements.Statement;

import java.util.ArrayList;
import java.util.List;

public class DeclarationVisitor extends StatementVisitor {
    @Override
    public AstNode visitDeclaration(PigLatinParser.DeclarationContext ctx) {
        if (ctx.variableDeclaration() != null) {
            return visit(ctx.variableDeclaration());
        }

        if (ctx.arrayDeclaration() != null) {
            return visit(ctx.arrayDeclaration());
        }

        if (ctx.structDeclaration() != null) {
            return visit(ctx.structDeclaration());
        }

        return null;
    }

    @Override
    public AstNode visitNormalVarDeclaration(PigLatinParser.NormalVarDeclarationContext ctx) {
        Initializer initializer = null;

        if (ctx.expression() != null) {
            Expression expr = (Expression) visit(ctx.expression());
            initializer = new ExpressionInitializer(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), expr);
        } else if (ctx.structInitializer() != null) {
            initializer = (Initializer) visit(ctx.structInitializer());
        }

        return new VariableDeclaration(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.ID().getText(), ctx.type().getText(), initializer);
    }

    @Override
    public AstNode visitArrayDeclaration(PigLatinParser.ArrayDeclarationContext ctx) {
        String type = "boolean";

        if (ctx.type() != null) {
            type = ctx.type().getText();
        }

        ArrayInitializer initializer = null;

        if (ctx.arrayInitializer() != null) {
            initializer = (ArrayInitializer) visit(ctx.arrayInitializer());
        }

        return new ArrayDeclaration(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            ctx.ID().getText(),
            (Expression) visit(ctx.expression()),
            type,
            initializer
        );
    }

    @Override
    public AstNode visitArrayInitializer(PigLatinParser.ArrayInitializerContext ctx) {
        List<Expression> values = new ArrayList<>();

        for (PigLatinParser.ExpressionContext expression : ctx.values) {
            values.add((Expression) visit(expression));
        }

        return new ArrayInitializer(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), values);
    }

    @Override
    public AstNode visitExprInit(PigLatinParser.ExprInitContext ctx) {
        return new ExpressionInitializer(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), (Expression) visit(ctx.expression()));
    }

    @Override
    public AstNode visitStructInit(PigLatinParser.StructInitContext ctx) {
        return visit(ctx.structInitializer());
    }

    @Override
    public AstNode visitStructInitializer(PigLatinParser.StructInitializerContext ctx) {
        List<StructFieldInitializer> fields = new ArrayList<>();

        for (PigLatinParser.StructFieldInitializerContext field : ctx.fields) {
            fields.add((StructFieldInitializer) visit(field));
        }

        return new StructInitializer(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), fields);
    }

    @Override
    public AstNode visitStructFieldInitializer(PigLatinParser.StructFieldInitializerContext ctx) {
        return new StructFieldInitializer(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            ctx.ID().getText(),
            (Initializer) visit(ctx.initializer())
        );
    }

    @Override
    public AstNode visitStructWithSemicolon(PigLatinParser.StructWithSemicolonContext ctx) {
        List<StructField> fields = new ArrayList<>();

        for (PigLatinParser.StructFieldContext field : ctx.structField()) {
            fields.add((StructField) visit(field));
        }

        return new StructDeclaration(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.ID().getText(), fields);
    }

    @Override
    public AstNode visitStructWithComma(PigLatinParser.StructWithCommaContext ctx) {
        List<StructField> fields = new ArrayList<>();

        for (PigLatinParser.StructFieldContext field : ctx.structField()) {
            fields.add((StructField) visit(field));
        }

        return new StructDeclaration(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.ID().getText(), fields);
    }

    @Override
    public AstNode visitStructVariableField(PigLatinParser.StructVariableFieldContext ctx) {
        return new StructField(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.ID().getText(), ctx.type().getText(), false);
    }

    @Override
    public AstNode visitStructArrayField(PigLatinParser.StructArrayFieldContext ctx) {
        return new StructField(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.ID().getText(), ctx.type().getText(), true);
    }

    @Override
    public AstNode visitFunctionDeclaration(PigLatinParser.FunctionDeclarationContext ctx) {
        if (ctx.procedureDeclaration() != null) {
            return visit(ctx.procedureDeclaration());
        }

        return visit(ctx.functionWithReturn());
    }

    @Override
    public AstNode visitProcedureDeclaration(PigLatinParser.ProcedureDeclarationContext ctx) {
        List<Parameter> parameters = new ArrayList<>();

        if (ctx.parameterList() != null) {
            for (PigLatinParser.ParameterContext parameter : ctx.parameterList().parameter()) {
                parameters.add((Parameter) visit(parameter));
            }
        }

        return new FunctionDeclaration(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            ctx.ID().getText(),
            "void",
            parameters,
            (FunctionBody) visit(ctx.functionBody())
        );
    }

    @Override
    public AstNode visitFunctionWithReturn(PigLatinParser.FunctionWithReturnContext ctx) {
        List<Parameter> parameters = new ArrayList<>();

        if (ctx.parameterList() != null) {
            for (PigLatinParser.ParameterContext parameter : ctx.parameterList().parameters) {
                parameters.add((Parameter) visit(parameter));
            }
        }

        return new FunctionDeclaration(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            ctx.ID().getText(),
            ctx.type().getText(),
            parameters,
            (FunctionBody) visit(ctx.functionBody())
        );
    }

    @Override
    public AstNode visitParameter(PigLatinParser.ParameterContext ctx) {
        return new Parameter(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.ID().getText(), ctx.type().getText());
    }

    @Override
    public AstNode visitFunctionBody(PigLatinParser.FunctionBodyContext ctx) {
        LocalVariableSection localVariables = null;

        if (ctx.localVariableSection() != null) {
            localVariables = (LocalVariableSection) visit(ctx.localVariableSection());
        }

        List<Statement> statements = new ArrayList<>();

        for (PigLatinParser.StatementContext statement : ctx.statement()) {
            statements.add((Statement) visit(statement));
        }

        return new FunctionBody(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            localVariables,
            new BlockStatement(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), statements)
        );
    }

    @Override
    public AstNode visitLocalVariableSection(PigLatinParser.LocalVariableSectionContext ctx) {
        List<Declaration> declarations = new ArrayList<>();

        for (PigLatinParser.DeclarationContext declaration : ctx.declaration()) {
            declarations.add((Declaration) visit(declaration));
        }

        return new LocalVariableSection(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), declarations);
    }
}
