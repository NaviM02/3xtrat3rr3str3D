package com.navi.backend.semantic.lat;

import com.navi.backend.ast.lat.declarations.ArrayDeclaration;
import com.navi.backend.ast.lat.declarations.ArrayInitializer;
import com.navi.backend.ast.lat.declarations.Declaration;
import com.navi.backend.ast.lat.declarations.VariableDeclaration;
import com.navi.backend.ast.lat.declarations.initializers.ExpressionInitializer;
import com.navi.backend.ast.lat.declarations.initializers.StructFieldInitializer;
import com.navi.backend.ast.lat.declarations.initializers.StructInitializer;
import com.navi.backend.ast.lat.expressions.ArrayAccessExpression;
import com.navi.backend.ast.lat.expressions.BinaryExpression;
import com.navi.backend.ast.lat.expressions.FunctionCallExpression;
import com.navi.backend.ast.lat.expressions.MemberAccessExpression;
import com.navi.backend.ast.lat.expressions.ObjectCreationExpression;
import com.navi.backend.ast.lat.expressions.UnaryExpression;
import com.navi.backend.ast.lat.expressions.VariableExpression;
import com.navi.backend.ast.lat.expressions.literals.BooleanLiteral;
import com.navi.backend.ast.lat.expressions.literals.CharLiteral;
import com.navi.backend.ast.lat.expressions.literals.DecimalLiteral;
import com.navi.backend.ast.lat.expressions.literals.NumberLiteral;
import com.navi.backend.ast.lat.expressions.literals.StringLiteral;
import com.navi.backend.ast.lat.global.FunctionBody;
import com.navi.backend.ast.lat.global.FunctionDeclaration;
import com.navi.backend.ast.lat.global.GlobalVariableSection;
import com.navi.backend.ast.lat.global.ImportDeclaration;
import com.navi.backend.ast.lat.global.LocalVariableSection;
import com.navi.backend.ast.lat.global.Parameter;
import com.navi.backend.ast.lat.global.Program;
import com.navi.backend.ast.lat.statements.AssignmentStatement;
import com.navi.backend.ast.lat.statements.BlockStatement;
import com.navi.backend.ast.lat.statements.BreakStatement;
import com.navi.backend.ast.lat.statements.ContinueStatement;
import com.navi.backend.ast.lat.statements.DoWhileStatement;
import com.navi.backend.ast.lat.statements.ElseIfStatement;
import com.navi.backend.ast.lat.statements.ForStatement;
import com.navi.backend.ast.lat.statements.FunctionCallStatement;
import com.navi.backend.ast.lat.statements.IfStatement;
import com.navi.backend.ast.lat.statements.IncrementStatement;
import com.navi.backend.ast.lat.statements.PrintStatement;
import com.navi.backend.ast.lat.statements.ReadStatement;
import com.navi.backend.ast.lat.statements.ReturnStatement;
import com.navi.backend.ast.lat.statements.WhileStatement;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import com.navi.backend.semantic.Definitions;
import com.navi.backend.semantic.FunctionSignature;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.Symbol;
import com.navi.backend.semantic.SymbolKind;
import com.navi.backend.semantic.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Pasada de declaraciones de Lat. Registra variables globales y signatures de
 * funciones en el scope global. Los imports ya fueron procesados por el orquestador.
 */
public class LatDeclarationVisitor implements AstLatVisitor<Void> {

    private final SemanticContext context;
    private final LatTypeResolver types;
    private final Definitions defs;

    public LatDeclarationVisitor(SemanticContext context) {
        this.context = context;
        this.types = new LatTypeResolver(context);
        this.defs = new Definitions(context);
    }

    public void build(Program program) {
        program.accept(this);
    }

    @Override
    public Void visit(Program node) {
        if (node.getGlobalVariables() != null) {
            node.getGlobalVariables().accept(this);
        }
        if (node.getFunctions() != null) {
            for (FunctionDeclaration function : node.getFunctions()) {
                function.accept(this);
            }
        }
        return null;
    }

    @Override
    public Void visit(ImportDeclaration node) {
        return null;
    }

    @Override
    public Void visit(GlobalVariableSection node) {
        for (Declaration declaration : node.getDeclarations()) {
            declaration.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(FunctionDeclaration node) {
        Type returnType = types.resolve(node.getReturnType(), node.getLine(), node.getColumn());
        List<Type> paramTypes = new ArrayList<>();
        if (node.getParameters() != null) {
            for (Parameter p : node.getParameters()) {
                paramTypes.add(types.resolve(p.getType(), p.getLine(), p.getColumn()));
            }
        }
        Symbol fn = new Symbol(node.getName(), SymbolKind.FUNCTION, returnType,
                new FunctionSignature(paramTypes, returnType), false,
                context.getSymbolTable().getGlobalScope(), null, node.getLine(), node.getColumn());
        defs.callable(fn);
        return null;
    }

    @Override
    public Void visit(VariableDeclaration node) {
        Type type = types.resolve(node.getType(), node.getLine(), node.getColumn());
        defs.variable(node, node.getName(), type, node.getLine(), node.getColumn());
        return null;
    }

    @Override
    public Void visit(ArrayDeclaration node) {
        Type base = types.resolve(node.getType(), node.getLine(), node.getColumn());
        int rank = node.getSizes() == null ? 0 : node.getSizes().size();
        defs.variable(node, node.getName(), Type.array(base, rank), node.getLine(), node.getColumn());
        return null;
    }

    @Override public Void visit(LocalVariableSection node) { return null; }
    @Override public Void visit(FunctionBody node) { return null; }
    @Override public Void visit(Parameter node) { return null; }
    @Override public Void visit(ArrayInitializer node) { return null; }
    @Override public Void visit(ExpressionInitializer node) { return null; }
    @Override public Void visit(StructInitializer node) { return null; }
    @Override public Void visit(StructFieldInitializer node) { return null; }

    @Override public Void visit(AssignmentStatement node) { return null; }
    @Override public Void visit(BlockStatement node) { return null; }
    @Override public Void visit(BreakStatement node) { return null; }
    @Override public Void visit(ContinueStatement node) { return null; }
    @Override public Void visit(DoWhileStatement node) { return null; }
    @Override public Void visit(ElseIfStatement node) { return null; }
    @Override public Void visit(ForStatement node) { return null; }
    @Override public Void visit(FunctionCallStatement node) { return null; }
    @Override public Void visit(IfStatement node) { return null; }
    @Override public Void visit(IncrementStatement node) { return null; }
    @Override public Void visit(PrintStatement node) { return null; }
    @Override public Void visit(ReadStatement node) { return null; }
    @Override public Void visit(ReturnStatement node) { return null; }
    @Override public Void visit(WhileStatement node) { return null; }

    @Override public Void visit(ArrayAccessExpression node) { return null; }
    @Override public Void visit(BinaryExpression node) { return null; }
    @Override public Void visit(FunctionCallExpression node) { return null; }
    @Override public Void visit(MemberAccessExpression node) { return null; }
    @Override public Void visit(ObjectCreationExpression node) { return null; }
    @Override public Void visit(UnaryExpression node) { return null; }
    @Override public Void visit(VariableExpression node) { return null; }

    @Override public Void visit(BooleanLiteral node) { return null; }
    @Override public Void visit(CharLiteral node) { return null; }
    @Override public Void visit(DecimalLiteral node) { return null; }
    @Override public Void visit(NumberLiteral node) { return null; }
    @Override public Void visit(StringLiteral node) { return null; }
}
