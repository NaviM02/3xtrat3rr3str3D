package com.navi.backend.semantic.y;

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
import com.navi.backend.semantic.Definitions;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.Type;
import com.navi.backend.semantic.TypeRules;

/**
 * Pasada semántica de Y. Valida tipos y anota expresiones con su {@link Type}.
 * Dispatcher delgado: la lógica vive en {@link YStatementChecker} (sentencias)
 * y {@link YExpressionChecker} (expresiones); este visitante conserva el
 * programa y el no-op de los nodos pasivos.
 */
public class YSemanticVisitor implements AstYVisitor<Type> {

    private final YStatementChecker statements;
    private final YExpressionChecker expressions;

    public YSemanticVisitor(SemanticContext context) {
        TypeRules rules = new TypeRules(context);
        YTypeResolver types = new YTypeResolver(context);
        Definitions defs = new Definitions(context);
        // `this` como recorrido: los checkers delegan el accept de los hijos aquí.
        this.expressions = new YExpressionChecker(context, rules, this);
        this.statements = new YStatementChecker(context, rules, types, defs, this);
    }

    public void build(ProgramY program) {
        program.accept(this);
    }

    @Override
    public Type visit(ProgramY node) {
        if (node.getFunctions() != null) {
            for (FunctionDeclaration f : node.getFunctions()) {
                f.accept(this);
            }
        }
        return null;
    }

    // ---------------------------------------------------------------- delegación

    @Override public Type visit(FunctionDeclaration node) { return statements.functionDeclaration(node); }
    @Override public Type visit(VariableDeclaration node) { return statements.variableDeclaration(node); }
    @Override public Type visit(AssignmentStatement node) { return statements.assignment(node); }
    @Override public Type visit(IncrementStatement node) { return statements.increment(node); }
    @Override public Type visit(IfStatement node) { return statements.ifStatement(node); }
    @Override public Type visit(ElseIfClause node) { return statements.elseIf(node); }
    @Override public Type visit(ElseClause node) { return statements.elseClause(node); }
    @Override public Type visit(WhileStatement node) { return statements.whileStatement(node); }
    @Override public Type visit(DoWhileStatement node) { return statements.doWhile(node); }
    @Override public Type visit(ForStatement node) { return statements.forStatement(node); }
    @Override public Type visit(SwitchStatement node) { return statements.switchStatement(node); }
    @Override public Type visit(SwitchCase node) { return statements.switchCase(node); }
    @Override public Type visit(DefaultCase node) { return statements.defaultCase(node); }
    @Override public Type visit(ReturnStatement node) { return statements.returnStatement(node); }
    @Override public Type visit(PrintStatement node) { return statements.print(node); }
    @Override public Type visit(ExpressionStatement node) { return statements.expressionStatement(node); }
    @Override public Type visit(StructureDeclaration node) { return statements.structureDeclaration(node); }

    @Override public Type visit(BinaryExpression node) { return expressions.binary(node); }
    @Override public Type visit(UnaryExpression node) { return expressions.unary(node); }
    @Override public Type visit(VariableExpression node) { return expressions.variable(node); }
    @Override public Type visit(ArrayAccessExpression node) { return expressions.arrayAccess(node); }
    @Override public Type visit(MemberAccessExpression node) { return expressions.memberAccess(node); }
    @Override public Type visit(FunctionCallExpression node) { return expressions.functionCall(node); }
    @Override public Type visit(ReadExpression node) { return expressions.read(node); }
    @Override public Type visit(LiteralExpression node) { return expressions.literal(node); }

    // ---------------------------------------------------------------- no-ops

    @Override public Type visit(ReadStatement node) { return null; }
    @Override public Type visit(BreakStatement node) { return null; }
    @Override public Type visit(ContinueStatement node) { return null; }
    @Override public Type visit(StructureField node) { return null; }
    @Override public Type visit(ArrayDeclaration node) { return null; }
    @Override public Type visit(ArrayDimensions node) { return null; }
    @Override public Type visit(ArrayParameter node) { return null; }
    @Override public Type visit(NormalParameter node) { return null; }
    @Override public Type visit(StructureParameter node) { return null; }
    @Override public Type visit(YType node) { return null; }

    @Override public Type visit(ArrayInitializer node) { return Type.ERROR; }
    @Override public Type visit(ExpressionInitializer node) { return Type.ERROR; }
    @Override public Type visit(StructureInitializer node) { return Type.ERROR; }
}
