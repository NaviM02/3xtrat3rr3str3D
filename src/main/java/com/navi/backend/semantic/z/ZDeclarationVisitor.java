package com.navi.backend.semantic.z;

import com.navi.backend.ast.z.declarations.ArrayDimensions;
import com.navi.backend.ast.z.declarations.ArrayInitializer;
import com.navi.backend.ast.z.declarations.ClassDeclaration;
import com.navi.backend.ast.z.declarations.ClassMember;
import com.navi.backend.ast.z.declarations.ConstructorDeclaration;
import com.navi.backend.ast.z.declarations.ExpressionInitializer;
import com.navi.backend.ast.z.declarations.FieldDeclaration;
import com.navi.backend.ast.z.declarations.Initializer;
import com.navi.backend.ast.z.declarations.MethodDeclaration;
import com.navi.backend.ast.z.declarations.Parameter;
import com.navi.backend.ast.z.declarations.VariableDeclaration;
import com.navi.backend.ast.z.declarations.VariableDeclarator;
import com.navi.backend.ast.z.declarations.ZType;
import com.navi.backend.ast.z.expressions.ArrayAccessExpression;
import com.navi.backend.ast.z.expressions.ArrayCreationExpression;
import com.navi.backend.ast.z.expressions.AssignmentExpression;
import com.navi.backend.ast.z.expressions.BinaryExpression;
import com.navi.backend.ast.z.expressions.ExpressionList;
import com.navi.backend.ast.z.expressions.FunctionCallExpression;
import com.navi.backend.ast.z.expressions.MemberAccessExpression;
import com.navi.backend.ast.z.expressions.NullExpression;
import com.navi.backend.ast.z.expressions.ObjectCreationExpression;
import com.navi.backend.ast.z.expressions.ReadExpression;
import com.navi.backend.ast.z.expressions.TernaryExpression;
import com.navi.backend.ast.z.expressions.UnaryExpression;
import com.navi.backend.ast.z.expressions.VariableExpression;
import com.navi.backend.ast.z.expressions.literals.LiteralExpression;
import com.navi.backend.ast.z.global.ProgramZ;
import com.navi.backend.ast.z.statements.BlockStatement;
import com.navi.backend.ast.z.statements.BreakStatement;
import com.navi.backend.ast.z.statements.ContinueStatement;
import com.navi.backend.ast.z.statements.DefaultCase;
import com.navi.backend.ast.z.statements.DoWhileStatement;
import com.navi.backend.ast.z.statements.ElseClause;
import com.navi.backend.ast.z.statements.ElseIfClause;
import com.navi.backend.ast.z.statements.ExpressionStatement;
import com.navi.backend.ast.z.statements.ForStatement;
import com.navi.backend.ast.z.statements.IfStatement;
import com.navi.backend.ast.z.statements.PrintStatement;
import com.navi.backend.ast.z.statements.PrintlnStatement;
import com.navi.backend.ast.z.statements.ReadlnStatement;
import com.navi.backend.ast.z.statements.ReturnStatement;
import com.navi.backend.ast.z.statements.SwitchCase;
import com.navi.backend.ast.z.statements.SwitchStatement;
import com.navi.backend.ast.z.statements.VariableDeclarationStatement;
import com.navi.backend.ast.z.statements.WhileStatement;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import com.navi.backend.semantic.AggregateType;
import com.navi.backend.semantic.Definitions;
import com.navi.backend.semantic.Field;
import com.navi.backend.semantic.FunctionSignature;
import com.navi.backend.semantic.Scope;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.Symbol;
import com.navi.backend.semantic.SymbolKind;
import com.navi.backend.semantic.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Pasada de declaraciones de Z. Un archivo Z es una sola clase: registra su
 * layout ({@code AggregateType}) y sus miembros (campos, métodos y constructores,
 * con sobrecarga).
 */
public class ZDeclarationVisitor implements AstZVisitor<Void> {

    private final SemanticContext context;
    private final ZTypeResolver types;
    private final Definitions defs;

    public ZDeclarationVisitor(SemanticContext context) {
        this.context = context;
        this.types = new ZTypeResolver(context);
        this.defs = new Definitions(context);
    }

    public void build(ProgramZ program) {
        program.accept(this);
    }

    @Override
    public Void visit(ProgramZ node) {
        if (node.getClassDeclaration() != null) {
            node.getClassDeclaration().accept(this);
        }
        return null;
    }

    @Override
    public Void visit(ClassDeclaration node) {
        Scope global = context.getSymbolTable().getGlobalScope();
        AggregateType agg = new AggregateType(node.getName(), true, global);
        if (!context.getTypeTable().register(agg)) {
            context.getErrors().report(node.getLine(), node.getColumn(), "Clase duplicada: " + node.getName());
        }
        if (node.getMembers() != null) {
            for (ClassMember member : node.getMembers()) {
                if (member instanceof FieldDeclaration f) registerField(agg, f);
                else if (member instanceof MethodDeclaration m) registerMethod(agg, m);
                else if (member instanceof ConstructorDeclaration c) registerConstructor(agg, c);
            }
        }
        return null;
    }

    private void registerField(AggregateType agg, FieldDeclaration node) {
        Type type = types.resolve(node.getType());
        for (VariableDeclarator v : node.getVariables()) {
            agg.addField(new Field(v.getName(), type));
        }
    }

    private void registerMethod(AggregateType agg, MethodDeclaration node) {
        Type returnType = node.getReturnType() == null ? Type.VOID : types.resolve(node.getReturnType());
        List<Type> params = resolveParams(node.getParameters());
        Symbol s = new Symbol(node.getName(), SymbolKind.METHOD, returnType,
                new FunctionSignature(params, returnType), false, agg.getMemberScope(), agg,
                node.getLine(), node.getColumn());
        defs.callableIn(agg.getMemberScope(), s);
    }

    private void registerConstructor(AggregateType agg, ConstructorDeclaration node) {
        Type classType = Type.classType(agg.getName());
        List<Type> params = resolveParams(node.getParameters());
        Symbol s = new Symbol(node.getName(), SymbolKind.CONSTRUCTOR, classType,
                new FunctionSignature(params, classType), false, agg.getMemberScope(), agg,
                node.getLine(), node.getColumn());
        defs.callableIn(agg.getMemberScope(), s);
    }

    private List<Type> resolveParams(List<Parameter> parameters) {
        List<Type> paramTypes = new ArrayList<>();
        if (parameters != null) {
            for (Parameter p : parameters) paramTypes.add(types.resolve(p.getType()));
        }
        return paramTypes;
    }

    @Override public Void visit(ClassMember node) { return null; }
    @Override public Void visit(FieldDeclaration node) { return null; }
    @Override public Void visit(MethodDeclaration node) { return null; }
    @Override public Void visit(ConstructorDeclaration node) { return null; }
    @Override public Void visit(Parameter node) { return null; }
    @Override public Void visit(ZType node) { return null; }
    @Override public Void visit(ArrayDimensions node) { return null; }
    @Override public Void visit(VariableDeclarator node) { return null; }
    @Override public Void visit(VariableDeclaration node) { return null; }
    @Override public Void visit(Initializer node) { return null; }
    @Override public Void visit(ExpressionInitializer node) { return null; }
    @Override public Void visit(ArrayInitializer node) { return null; }

    @Override public Void visit(VariableDeclarationStatement node) { return null; }
    @Override public Void visit(BlockStatement node) { return null; }
    @Override public Void visit(ExpressionStatement node) { return null; }
    @Override public Void visit(IfStatement node) { return null; }
    @Override public Void visit(ElseIfClause node) { return null; }
    @Override public Void visit(ElseClause node) { return null; }
    @Override public Void visit(SwitchStatement node) { return null; }
    @Override public Void visit(SwitchCase node) { return null; }
    @Override public Void visit(DefaultCase node) { return null; }
    @Override public Void visit(ForStatement node) { return null; }
    @Override public Void visit(WhileStatement node) { return null; }
    @Override public Void visit(DoWhileStatement node) { return null; }
    @Override public Void visit(ReturnStatement node) { return null; }
    @Override public Void visit(BreakStatement node) { return null; }
    @Override public Void visit(ContinueStatement node) { return null; }
    @Override public Void visit(PrintStatement node) { return null; }
    @Override public Void visit(PrintlnStatement node) { return null; }
    @Override public Void visit(ReadlnStatement node) { return null; }

    @Override public Void visit(ArrayAccessExpression node) { return null; }
    @Override public Void visit(AssignmentExpression node) { return null; }
    @Override public Void visit(BinaryExpression node) { return null; }
    @Override public Void visit(FunctionCallExpression node) { return null; }
    @Override public Void visit(MemberAccessExpression node) { return null; }
    @Override public Void visit(UnaryExpression node) { return null; }
    @Override public Void visit(VariableExpression node) { return null; }
    @Override public Void visit(TernaryExpression node) { return null; }
    @Override public Void visit(ObjectCreationExpression node) { return null; }
    @Override public Void visit(ArrayCreationExpression node) { return null; }
    @Override public Void visit(NullExpression node) { return null; }
    @Override public Void visit(ReadExpression node) { return null; }
    @Override public Void visit(ExpressionList node) { return null; }
    @Override public Void visit(LiteralExpression node) { return null; }
}
