package com.navi.backend.c3d.z;

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
import com.navi.backend.c3d.C3DEmitter;
import com.navi.backend.semantic.SemanticContext;

/**
 * Genera C3D para Z siguiendo los apuntes de clase. Locales/parámetros viven en
 * el stack ({@code stack[BP + off]}); los objetos y arreglos viven en el heap
 * ({@code t = HP; HP = HP + size} y {@code heap[base + off]}). El receptor
 * {@code this} es el parámetro 0 del marco. Booleanos materializados con saltos.
 *
 * <p>Dispatcher delgado: la lógica vive en {@link ZDeclarationC3D},
 * {@link ZStatementC3D} y {@link ZExpressionC3D}; {@link ZResolverC3D}
 * centraliza direcciones, layout y asignación.</p>
 */
public class ZC3DVisitor implements AstZVisitor<String> {

    private final ZExpressionC3D expressions;
    private final ZStatementC3D statements;
    private final ZDeclarationC3D declarations;

    public ZC3DVisitor(SemanticContext context, C3DEmitter emitter) {
        ZResolverC3D resolver = new ZResolverC3D(context, emitter, this);
        this.expressions = new ZExpressionC3D(context, emitter, resolver, this);
        this.statements = new ZStatementC3D(emitter, this);
        this.declarations = new ZDeclarationC3D(context, emitter, resolver, this);
    }

    public void generate(ProgramZ program) {
        program.accept(this);
    }

    @Override
    public String visit(ProgramZ node) {
        if (node.getClassDeclaration() != null) node.getClassDeclaration().accept(this);
        return null;
    }

    // ---------------------------------------------------------------- declaraciones

    @Override public String visit(ClassDeclaration node) { return declarations.classDeclaration(node); }
    @Override public String visit(FieldDeclaration node) { return declarations.fieldDeclaration(node); }
    @Override public String visit(MethodDeclaration node) { return declarations.methodDeclaration(node); }
    @Override public String visit(ConstructorDeclaration node) { return declarations.constructorDeclaration(node); }
    @Override public String visit(VariableDeclaration node) { return declarations.variableDeclaration(node); }

    // ---------------------------------------------------------------- sentencias

    @Override public String visit(BlockStatement node) { return statements.block(node); }
    @Override public String visit(VariableDeclarationStatement node) { return statements.variableDeclarationStatement(node); }
    @Override public String visit(ExpressionStatement node) { return statements.expressionStatement(node); }
    @Override public String visit(IfStatement node) { return statements.ifStatement(node); }
    @Override public String visit(ElseClause node) { return statements.elseClause(node); }
    @Override public String visit(WhileStatement node) { return statements.whileStatement(node); }
    @Override public String visit(DoWhileStatement node) { return statements.doWhile(node); }
    @Override public String visit(ForStatement node) { return statements.forStatement(node); }
    @Override public String visit(SwitchStatement node) { return statements.switchStatement(node); }
    @Override public String visit(DefaultCase node) { return statements.defaultCase(node); }
    @Override public String visit(ReturnStatement node) { return statements.returnStatement(node); }
    @Override public String visit(PrintStatement node) { return statements.print(node); }
    @Override public String visit(PrintlnStatement node) { return statements.println(node); }
    @Override public String visit(BreakStatement node) { return statements.breakStatement(node); }
    @Override public String visit(ContinueStatement node) { return statements.continueStatement(node); }

    // ---------------------------------------------------------------- expresiones

    @Override public String visit(BinaryExpression node) { return expressions.binary(node); }
    @Override public String visit(UnaryExpression node) { return expressions.unary(node); }
    @Override public String visit(AssignmentExpression node) { return expressions.assignment(node); }
    @Override public String visit(TernaryExpression node) { return expressions.ternary(node); }
    @Override public String visit(VariableExpression node) { return expressions.variable(node); }
    @Override public String visit(ArrayAccessExpression node) { return expressions.arrayAccess(node); }
    @Override public String visit(MemberAccessExpression node) { return expressions.memberAccess(node); }
    @Override public String visit(FunctionCallExpression node) { return expressions.functionCall(node); }
    @Override public String visit(ObjectCreationExpression node) { return expressions.objectCreation(node); }
    @Override public String visit(ArrayCreationExpression node) { return expressions.arrayCreation(node); }
    @Override public String visit(NullExpression node) { return expressions.nullExpression(node); }
    @Override public String visit(ReadExpression node) { return expressions.read(node); }
    @Override public String visit(ExpressionList node) { return expressions.expressionList(node); }
    @Override public String visit(LiteralExpression node) { return expressions.literal(node); }

    // ---------------------------------------------------------------- pasivos

    @Override public String visit(ClassMember node) { return null; }
    @Override public String visit(Parameter node) { return null; }
    @Override public String visit(ZType node) { return null; }
    @Override public String visit(ArrayDimensions node) { return null; }
    @Override public String visit(VariableDeclarator node) { return null; }
    @Override public String visit(Initializer node) { return null; }
    @Override public String visit(ExpressionInitializer node) { return null; }
    @Override public String visit(ArrayInitializer node) { return null; }
    @Override public String visit(ElseIfClause node) { return null; }
    @Override public String visit(SwitchCase node) { return null; }
    @Override public String visit(ReadlnStatement node) { return null; }
}
