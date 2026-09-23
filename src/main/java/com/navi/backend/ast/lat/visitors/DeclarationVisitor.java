package com.navi.backend.ast.lat.visitors;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.declarations.*;
import com.navi.backend.ast.lat.declarations.initializers.ExpressionInitializer;
import com.navi.backend.ast.lat.declarations.initializers.Initializer;
import com.navi.backend.ast.lat.declarations.initializers.StructFieldInitializer;
import com.navi.backend.ast.lat.declarations.initializers.StructInitializer;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.expressions.ObjectCreationExpression;
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
    public AstLatNode visitDeclaration(PigLatinParser.DeclarationContext ctx) {
        if (ctx.variableDeclaration() != null) {
            return visit(ctx.variableDeclaration());
        }

        if (ctx.arrayDeclaration() != null) {
            return visit(ctx.arrayDeclaration());
        }

        return null;
    }

    @Override
    public AstLatNode visitNormalVarDeclaration(PigLatinParser.NormalVarDeclarationContext ctx) {
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
    public AstLatNode visitNewObjectDeclaration(PigLatinParser.NewObjectDeclarationContext ctx) {
        List<Expression> arguments = new ArrayList<>();

        if (ctx.functionArguments().argumentList() != null) {
            for (PigLatinParser.ExpressionContext expr : ctx.functionArguments().argumentList().expression()) {
                arguments.add((Expression) visit(expr));
            }
        }

        ObjectCreationExpression objectCreation = new ObjectCreationExpression(
                ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine(),
                ctx.objectType.getText(),
                arguments
        );

        ExpressionInitializer initializer = new ExpressionInitializer(
                ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine(),
                objectCreation
        );

        return new VariableDeclaration(
                ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine(),
                ctx.variableName.getText(),
                ctx.objectType.getText(),
                initializer
        );
    }

    @Override
    public AstLatNode visitArrayDeclaration(PigLatinParser.ArrayDeclarationContext ctx) {
        String type = "boolean";

        if (ctx.type() != null) {
            type = ctx.type().getText();
        }

        ArrayInitializer initializer = null;

        if (ctx.arrayInitializer() != null) {
            initializer = (ArrayInitializer) visit(ctx.arrayInitializer());
        }

        List<Expression> sizes = new ArrayList<>();

        for (PigLatinParser.ExpressionContext sizeExpr : ctx.expression()) {
            sizes.add((Expression) visit(sizeExpr));
        }

        return new ArrayDeclaration(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            ctx.ID().getText(),
            sizes,
            type,
            initializer
        );
    }

    @Override
    public AstLatNode visitArrayInitializer(PigLatinParser.ArrayInitializerContext ctx) {
        List<AstLatNode> elements = new ArrayList<>();

        if (ctx.arrayInitializerElementList() != null) {
            for (PigLatinParser.ArrayInitializerElementContext element : ctx.arrayInitializerElementList().arrayInitializerElement()) {
                elements.add(visit(element));
            }
        }

        return new ArrayInitializer(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), elements);
    }

    @Override
    public AstLatNode visitArrayExprElement(PigLatinParser.ArrayExprElementContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public AstLatNode visitNestedArrayElement(PigLatinParser.NestedArrayElementContext ctx) {
        return visit(ctx.arrayInitializer());
    }

    @Override
    public AstLatNode visitExprInit(PigLatinParser.ExprInitContext ctx) {
        return new ExpressionInitializer(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), (Expression) visit(ctx.expression()));
    }

    @Override
    public AstLatNode visitStructInit(PigLatinParser.StructInitContext ctx) {
        return visit(ctx.structInitializer());
    }

    @Override
    public AstLatNode visitStructInitializer(PigLatinParser.StructInitializerContext ctx) {
        List<StructFieldInitializer> fields = new ArrayList<>();

        for (PigLatinParser.StructFieldInitializerContext field : ctx.fields) {
            fields.add((StructFieldInitializer) visit(field));
        }

        return new StructInitializer(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), fields);
    }

    @Override
    public AstLatNode visitStructFieldInitializer(PigLatinParser.StructFieldInitializerContext ctx) {
        return new StructFieldInitializer(
            ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
            ctx.ID().getText(),
            (Initializer) visit(ctx.initializer())
        );
    }

    @Override
    public AstLatNode visitFunctionDeclaration(PigLatinParser.FunctionDeclarationContext ctx) {
        if (ctx.procedureDeclaration() != null) {
            return visit(ctx.procedureDeclaration());
        }

        return visit(ctx.functionWithReturn());
    }

    @Override
    public AstLatNode visitProcedureDeclaration(PigLatinParser.ProcedureDeclarationContext ctx) {
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
    public AstLatNode visitFunctionWithReturn(PigLatinParser.FunctionWithReturnContext ctx) {
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
    public AstLatNode visitParameter(PigLatinParser.ParameterContext ctx) {
        return new Parameter(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.ID().getText(), ctx.type().getText());
    }

    @Override
    public AstLatNode visitFunctionBody(PigLatinParser.FunctionBodyContext ctx) {
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
    public AstLatNode visitLocalVariableSection(PigLatinParser.LocalVariableSectionContext ctx) {
        List<Declaration> declarations = new ArrayList<>();

        for (PigLatinParser.DeclarationContext declaration : ctx.declaration()) {
            declarations.add((Declaration) visit(declaration));
        }

        return new LocalVariableSection(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), declarations);
    }
}
