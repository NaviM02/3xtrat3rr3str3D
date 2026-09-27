package com.navi.backend.c3d.y;

import com.navi.backend.ast.y.declarations.ArrayDeclaration;
import com.navi.backend.ast.y.declarations.ArrayDimensions;
import com.navi.backend.ast.y.declarations.ArrayInitializer;
import com.navi.backend.ast.y.declarations.ArrayParameter;
import com.navi.backend.ast.y.declarations.ExpressionInitializer;
import com.navi.backend.ast.y.declarations.FunctionDeclaration;
import com.navi.backend.ast.y.declarations.NormalParameter;
import com.navi.backend.ast.y.declarations.StructureDeclaration;
import com.navi.backend.ast.y.declarations.StructureField;
import com.navi.backend.ast.y.declarations.StructureInitializer;
import com.navi.backend.ast.y.declarations.StructureParameter;
import com.navi.backend.ast.y.declarations.VariableDeclaration;
import com.navi.backend.ast.y.declarations.YType;
import com.navi.backend.ast.y.expressions.ArrayAccessExpression;
import com.navi.backend.ast.y.expressions.BinaryExpression;
import com.navi.backend.ast.y.expressions.FunctionCallExpression;
import com.navi.backend.ast.y.expressions.MemberAccessExpression;
import com.navi.backend.ast.y.expressions.ReadExpression;
import com.navi.backend.ast.y.expressions.UnaryExpression;
import com.navi.backend.ast.y.expressions.VariableExpression;
import com.navi.backend.ast.y.expressions.literals.LiteralExpression;
import com.navi.backend.ast.y.global.ProgramY;
import com.navi.backend.ast.y.statements.AssignmentStatement;
import com.navi.backend.ast.y.statements.BreakStatement;
import com.navi.backend.ast.y.statements.ContinueStatement;
import com.navi.backend.ast.y.statements.DefaultCase;
import com.navi.backend.ast.y.statements.DoWhileStatement;
import com.navi.backend.ast.y.statements.ElseClause;
import com.navi.backend.ast.y.statements.ElseIfClause;
import com.navi.backend.ast.y.statements.ExpressionStatement;
import com.navi.backend.ast.y.statements.ForStatement;
import com.navi.backend.ast.y.statements.IfStatement;
import com.navi.backend.ast.y.statements.IncrementStatement;
import com.navi.backend.ast.y.statements.PrintStatement;
import com.navi.backend.ast.y.statements.ReadStatement;
import com.navi.backend.ast.y.statements.ReturnStatement;
import com.navi.backend.ast.y.statements.SwitchCase;
import com.navi.backend.ast.y.statements.SwitchStatement;
import com.navi.backend.ast.y.statements.WhileStatement;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import com.navi.backend.c3d.C3DEmitter;
import com.navi.backend.semantic.SemanticContext;

// genera C3D para Y: variables y structs en stack[BP + off]
// los arreglos/structs por referencia guardan una direccion en su slot
// dispatcher delgado: la logica esta en YDeclaration/Statement/ExpressionC3D
public class YC3DVisitor implements AstYVisitor<String> {

    private final YResolverC3D resolver;
    private final YExpressionC3D expressions;
    private final YStatementC3D statements;
    private final YDeclarationC3D declarations;

    public YC3DVisitor(SemanticContext context, C3DEmitter emitter) {
        this.resolver = new YResolverC3D(context, emitter, this);
        this.expressions = new YExpressionC3D(context, emitter, resolver, this);
        this.statements = new YStatementC3D(emitter, resolver, this);
        this.declarations = new YDeclarationC3D(context, emitter, resolver, this);
    }

    public void generate(ProgramY program) {
        program.accept(this);
    }

    @Override
    public String visit(ProgramY node) {
        if (node.getFunctions() != null) for (FunctionDeclaration f : node.getFunctions()) f.accept(this);
        return null;
    }

    // ---------------------------------------------------------------- declaraciones

    @Override public String visit(FunctionDeclaration node) { return declarations.functionDeclaration(node); }
    @Override public String visit(VariableDeclaration node) { return declarations.variableDeclaration(node); }

    // ---------------------------------------------------------------- sentencias

    @Override public String visit(AssignmentStatement node) { return statements.assignment(node); }
    @Override public String visit(IncrementStatement node) { return statements.increment(node); }
    @Override public String visit(IfStatement node) { return statements.ifStatement(node); }
    @Override public String visit(ElseClause node) { return statements.elseClause(node); }
    @Override public String visit(WhileStatement node) { return statements.whileStatement(node); }
    @Override public String visit(DoWhileStatement node) { return statements.doWhile(node); }
    @Override public String visit(ForStatement node) { return statements.forStatement(node); }
    @Override public String visit(SwitchStatement node) { return statements.switchStatement(node); }
    @Override public String visit(DefaultCase node) { return statements.defaultCase(node); }
    @Override public String visit(ReturnStatement node) { return statements.returnStatement(node); }
    @Override public String visit(PrintStatement node) { return statements.print(node); }
    @Override public String visit(BreakStatement node) { return statements.breakStatement(node); }
    @Override public String visit(ContinueStatement node) { return statements.continueStatement(node); }
    @Override public String visit(ExpressionStatement node) { return statements.expressionStatement(node); }

    // ---------------------------------------------------------------- expresiones

    @Override public String visit(BinaryExpression node) { return expressions.binary(node); }
    @Override public String visit(UnaryExpression node) { return expressions.unary(node); }
    @Override public String visit(VariableExpression node) { return expressions.variable(node); }
    @Override public String visit(ArrayAccessExpression node) { return expressions.arrayAccess(node); }
    @Override public String visit(MemberAccessExpression node) { return expressions.memberAccess(node); }
    @Override public String visit(FunctionCallExpression node) { return expressions.functionCall(node); }
    @Override public String visit(ReadExpression node) { return expressions.read(node); }
    @Override public String visit(LiteralExpression node) { return expressions.literal(node); }

    // ---------------------------------------------------------------- pasivos

    @Override public String visit(StructureDeclaration node) { return null; }
    @Override public String visit(StructureField node) { return null; }
    @Override public String visit(ArrayDeclaration node) { return null; }
    @Override public String visit(ArrayDimensions node) { return null; }
    @Override public String visit(ArrayInitializer node) { return null; }
    @Override public String visit(ArrayParameter node) { return null; }
    @Override public String visit(ExpressionInitializer node) { return null; }
    @Override public String visit(NormalParameter node) { return null; }
    @Override public String visit(StructureInitializer node) { return null; }
    @Override public String visit(StructureParameter node) { return null; }
    @Override public String visit(YType node) { return null; }
    @Override public String visit(ElseIfClause node) { return null; }
    @Override public String visit(SwitchCase node) { return null; }
    @Override public String visit(ReadStatement node) { return null; }
}
