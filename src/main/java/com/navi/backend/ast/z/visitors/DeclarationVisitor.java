package com.navi.backend.ast.z.visitors;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.declarations.*;
import com.navi.backend.ast.z.expressions.*;
import com.navi.backend.ast.z.statements.*;
import com.navi.backend.lexer_parser.z.ZParser;

import java.util.ArrayList;
import java.util.List;

public class DeclarationVisitor extends StatementVisitor {

    @Override
    public AstZNode visitClassDeclaration(ZParser.ClassDeclarationContext ctx) {
        List<ClassMember> members = new ArrayList<>();
        for (ZParser.ClassMemberContext member : ctx.classMember()) members.add((ClassMember) visit(member));
        return new ClassDeclaration(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.ID().getText(), members);
    }

    @Override
    public AstZNode visitFieldMember(ZParser.FieldMemberContext ctx) {
        return visit(ctx.fieldDeclaration());
    }

    @Override
    public AstZNode visitConstructorMember(ZParser.ConstructorMemberContext ctx) {
        return visit(ctx.constructorDeclaration());
    }

    @Override
    public AstZNode visitMethodMember(ZParser.MethodMemberContext ctx) {
        return visit(ctx.methodDeclaration());
    }

    @Override
    public AstZNode visitFieldDeclaration(ZParser.FieldDeclarationContext ctx) {
        return new FieldDeclaration(
                ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
                ctx.PUBLIC() != null, (ZType) visit(ctx.type()), visitVariableDeclarators(ctx.variableDeclaratorList()));
    }

    @Override
    public AstZNode visitVariableDeclarationStatement(ZParser.VariableDeclarationStatementContext ctx) {
        return new VariableDeclarationStatement(
                ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
                visitVariableDeclaration(ctx.type(), ctx.variableDeclaratorList()));
    }

    private VariableDeclaration visitVariableDeclaration(ZParser.TypeContext typeCtx, ZParser.VariableDeclaratorListContext listCtx) {
        return new VariableDeclaration(typeCtx.getStart().getLine(), typeCtx.getStart().getCharPositionInLine(),
                (ZType) visit(typeCtx), visitVariableDeclarators(listCtx));
    }

    private List<VariableDeclarator> visitVariableDeclarators(ZParser.VariableDeclaratorListContext ctx) {
        List<VariableDeclarator> variables = new ArrayList<>();
        for (ZParser.VariableDeclaratorContext d : ctx.variableDeclarator())
            variables.add((VariableDeclarator) visit(d));
        return variables;
    }

    @Override
    public AstZNode visitVariableDeclarationStatementNoSemi(ZParser.VariableDeclarationStatementNoSemiContext ctx) {
        return visitVariableDeclaration(ctx.type(), ctx.variableDeclaratorList());
    }

    @Override
    public AstZNode visitVariableDeclarator(ZParser.VariableDeclaratorContext ctx) {
        Initializer initializer = ctx.initializer() != null ? (Initializer) visit(ctx.initializer()) : null;
        return new VariableDeclarator(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.ID().getText(), initializer);
    }

    @Override
    public AstZNode visitExpressionInitializerValue(ZParser.ExpressionInitializerValueContext ctx) {
        return new ExpressionInitializer(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), (Expression) visit(ctx.expression()));
    }

    @Override
    public AstZNode visitArrayInitializerValue(ZParser.ArrayInitializerValueContext ctx) {
        return visit(ctx.arrayInitializer());
    }

    @Override
    public AstZNode visitArrayInitializer(ZParser.ArrayInitializerContext ctx) {
        List<AstZNode> elements = new ArrayList<>();
        if (ctx.arrayInitializerElementList() != null) {
            for (ZParser.ArrayInitializerElementContext e : ctx.arrayInitializerElementList().arrayInitializerElement())
                elements.add(visit(e));
        }
        return new ArrayInitializer(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), elements);
    }

    @Override
    public AstZNode visitArrayInitializerElement(ZParser.ArrayInitializerElementContext ctx) {
        if (ctx.expression() != null) return visit(ctx.expression());
        return visit(ctx.arrayInitializer());
    }

    @Override
    public AstZNode visitConstructorDeclaration(ZParser.ConstructorDeclarationContext ctx) {
        return new ConstructorDeclaration(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
                ctx.PUBLIC() != null, ctx.ID().getText(), visitParameters(ctx.parameterList()), (BlockStatement) visit(ctx.block()));
    }

    @Override
    public AstZNode visitTypedMethodDeclaration(ZParser.TypedMethodDeclarationContext ctx) {
        return new MethodDeclaration(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
                ctx.PUBLIC() != null, (ZType) visit(ctx.type()), ctx.ID().getText(), visitParameters(ctx.parameterList()), (BlockStatement) visit(ctx.block()));
    }

    @Override
    public AstZNode visitVoidMethodDeclaration(ZParser.VoidMethodDeclarationContext ctx) {
        return new MethodDeclaration(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
                ctx.PUBLIC() != null, null, ctx.ID().getText(), visitParameters(ctx.parameterList()), (BlockStatement) visit(ctx.block()));
    }

    private List<Parameter> visitParameters(ZParser.ParameterListContext ctx) {
        List<Parameter> parameters = new ArrayList<>();
        if (ctx != null) for (ZParser.ParameterContext p : ctx.parameter()) parameters.add((Parameter) visit(p));
        return parameters;
    }

    @Override
    public AstZNode visitParameter(ZParser.ParameterContext ctx) {
        return new Parameter(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), (ZType) visit(ctx.type()), ctx.ID().getText());
    }

    @Override
    public AstZNode visitType(ZParser.TypeContext ctx) {
        String name;
        if (ctx.primitiveType() != null) name = ctx.primitiveType().getText();
        else name = ctx.referenceType().getText();
        ArrayDimensions dimensions = ctx.arrayDimensions() != null ? (ArrayDimensions) visit(ctx.arrayDimensions()) : null;
        return new ZType(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), name, dimensions);
    }

    @Override
    public AstZNode visitArrayDimensions(ZParser.ArrayDimensionsContext ctx) {
        return new ArrayDimensions(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ctx.LBRACK().size());
    }
}
