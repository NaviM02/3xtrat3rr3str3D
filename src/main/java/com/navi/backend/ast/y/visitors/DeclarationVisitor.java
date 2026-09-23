package com.navi.backend.ast.y.visitors;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.declarations.*;
import com.navi.backend.ast.y.expressions.Expression;
import com.navi.backend.ast.y.statements.Statement;
import com.navi.backend.lexer_parser.y.YParser;

import java.util.ArrayList;
import java.util.List;

public class DeclarationVisitor extends StatementVisitor {

    @Override
    public AstYNode visitVariableDeclaration(YParser.VariableDeclarationContext ctx) {
        return visit(ctx.variableDeclarationCore());
    }

    @Override
    public AstYNode visitVariableDeclarationCore(YParser.VariableDeclarationCoreContext ctx) {
        YType type = (YType) visit(ctx.type());
        String name = ctx.ID().getText();

        ArrayDeclaration arrayDeclaration = null;
        if (ctx.arrayDeclaration() != null) {
            arrayDeclaration = (ArrayDeclaration) visit(ctx.arrayDeclaration());
        }

        Initializer initializer = null;
        if (ctx.initializer() != null) {
            initializer = (Initializer) visit(ctx.initializer());
        }

        return new VariableDeclaration(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), type, name, arrayDeclaration, initializer);
    }

    @Override
    public AstYNode visitStructureDeclaration(YParser.StructureDeclarationContext ctx) {
        List<StructureField> fields = new ArrayList<>();
        for (YParser.StructureFieldContext fieldCtx : ctx.structureField()) {
            fields.add((StructureField) visit(fieldCtx));
        }
        return new StructureDeclaration(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.ID().getText(), fields);
    }

    @Override
    public AstYNode visitStructureField(YParser.StructureFieldContext ctx) {
        YType type = (YType) visit(ctx.type());
        ArrayDimensions dimensions = ctx.arrayDimensions() != null ? (ArrayDimensions) visit(ctx.arrayDimensions()) : null;
        return new StructureField(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), type, ctx.ID().getText(), dimensions);
    }

    @Override
    public AstYNode visitArrayDimensions(YParser.ArrayDimensionsContext ctx) {
        List<Integer> dimensions = new ArrayList<>();
        for (var token : ctx.INTEGER_LITERAL()) {
            dimensions.add(Integer.parseInt(token.getText()));
        }
        return new ArrayDimensions(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), dimensions);
    }

    @Override
    public AstYNode visitFunctionDeclaration(YParser.FunctionDeclarationContext ctx) {
        List<Parameter> parameters = new ArrayList<>();
        if (ctx.parameterList() != null) {
            for (YParser.ParameterContext parameterCtx : ctx.parameterList().parameter()) {
                parameters.add((Parameter) visit(parameterCtx));
            }
        }

        YType returnType = ctx.returnType() != null ? (YType) visit(ctx.returnType()) : null;

        List<Statement> statements = new ArrayList<>();

        for (YParser.StatementContext statementCtx : ctx.statement()) {
            statements.add((Statement) visit(statementCtx)); // error aca!!!!
        }

        return new FunctionDeclaration(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.ID().getText(),
            parameters,
            returnType,
            statements
        );
    }

    @Override
    public AstYNode visitNormalParameter(YParser.NormalParameterContext ctx) {
        return new NormalParameter(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (YType) visit(ctx.type()),
            ctx.ID().getText()
        );
    }

    @Override
    public AstYNode visitArrayParameterParam(YParser.ArrayParameterParamContext ctx) {
        YParser.ArrayParameterContext parameter = ctx.arrayParameter();

        return new ArrayParameter(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (YType) visit(parameter.type()),
            parameter.ID().getText()
        );
    }

    @Override
    public AstYNode visitStructureParameterParam(YParser.StructureParameterParamContext ctx) {
        YParser.StructureParameterContext parameter = ctx.structureParameter();

        return new StructureParameter(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            parameter.ID(0).getText(),
            parameter.ID(1).getText()
        );
    }

    @Override
    public AstYNode visitReturnType(YParser.ReturnTypeContext ctx) {
        return visit(ctx.type());
    }

    @Override
    public AstYNode visitArrayDeclaration(YParser.ArrayDeclarationContext ctx) {
        List<Expression> dimensions = new ArrayList<>();
        for (YParser.ExpressionContext expressionCtx : ctx.expression()) {
            dimensions.add((Expression) visit(expressionCtx));
        }
        return new ArrayDeclaration(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), dimensions);
    }

    @Override
    public AstYNode visitExpressionInitializer(YParser.ExpressionInitializerContext ctx) {
        return new ExpressionInitializer(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (Expression) visit(ctx.expression())
        );
    }

    @Override
    public AstYNode visitArrayInitializerValue(YParser.ArrayInitializerValueContext ctx) {
        return visit(ctx.arrayInitializer());
    }

    @Override
    public AstYNode visitStructureInitializerValue(YParser.StructureInitializerValueContext ctx) {
        return visit(ctx.structureInitializer());
    }

    @Override
    public AstYNode visitArrayInitializer(YParser.ArrayInitializerContext ctx) {
        List<AstYNode> elements = new ArrayList<>();
        if (ctx.arrayInitializerElementList() != null) {
            for (YParser.ArrayInitializerElementContext elementCtx : ctx.arrayInitializerElementList().arrayInitializerElement()) {
                elements.add(visit(elementCtx));
            }
        }
        return new ArrayInitializer(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), elements);
    }

    @Override
    public AstYNode visitArrayExprElement(YParser.ArrayExprElementContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public AstYNode visitNestedArrayElement(YParser.NestedArrayElementContext ctx) {
        return visit(ctx.arrayInitializer());
    }

    @Override
    public AstYNode visitStructureInitializer(YParser.StructureInitializerContext ctx) {
        List<Expression> expressions = new ArrayList<>();
        if (ctx.expressionList() != null) {
            for (YParser.ExpressionContext expressionCtx : ctx.expressionList().expression()) {
                expressions.add((Expression) visit(expressionCtx));
            }
        }
        return new StructureInitializer(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), expressions);
    }

    @Override
    public AstYNode visitIntegerType(YParser.IntegerTypeContext ctx) {
        return new YType(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.getText());
    }

    @Override
    public AstYNode visitFloatType(YParser.FloatTypeContext ctx) {
        return new YType(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.getText());
    }

    @Override
    public AstYNode visitCharacterType(YParser.CharacterTypeContext ctx) {
        return new YType(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.getText());
    }

    @Override
    public AstYNode visitBooleanType(YParser.BooleanTypeContext ctx) {
        return new YType(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.getText());
    }

    @Override
    public AstYNode visitStringType(YParser.StringTypeContext ctx) {
        return new YType(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.getText());
    }

    @Override
    public AstYNode visitStructureType(YParser.StructureTypeContext ctx) {
        return new YType(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.ID().getText());
    }
}