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
import com.navi.backend.ast.z.statements.BlockStatement;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import com.navi.backend.semantic.Definitions;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.Type;
import com.navi.backend.semantic.TypeRules;

/**
 * Pasada semántica de Z. Valida tipos y anota expresiones con su {@link Type}.
 * Dispatcher delgado: la lógica vive en {@link ZStatementChecker} (sentencias
 * y miembros) y {@link ZExpressionChecker} (expresiones, incluye la clase actual
 * para resolver miembros sin calificar); este visitante conserva el programa,
 * la clase actual y el no-op de los nodos pasivos.
 */
public class ZSemanticVisitor implements AstZVisitor<Type> {

    private final SemanticContext context;
    private final ZStatementChecker statements;
    private final ZExpressionChecker expressions;

    public ZSemanticVisitor(SemanticContext context) {
        this.context = context;
        TypeRules rules = new TypeRules(context);
        ZTypeResolver types = new ZTypeResolver(context);
        Definitions defs = new Definitions(context);
        // `this` como recorrido: los checkers delegan el accept de los hijos aquí.
        this.expressions = new ZExpressionChecker(context, rules, types, this);
        this.statements = new ZStatementChecker(context, rules, types, defs, this);
    }

    public void build(ProgramZ program) {
        program.accept(this);
    }

    @Override
    public Type visit(ProgramZ node) {
        if (node.getClassDeclaration() != null) {
            node.getClassDeclaration().accept(this);
        }
        return null;
    }

    @Override
    public Type visit(ClassDeclaration node) {
        expressions.setCurrentClass(context.getTypeTable().resolve(node.getName()));
        if (node.getMembers() != null) {
            for (ClassMember member : node.getMembers()) {
                member.accept(this);
            }
        }
        expressions.setCurrentClass(null);
        return null;
    }

    // ---------------------------------------------------------------- delegación

    @Override public Type visit(FieldDeclaration node) { return statements.fieldDeclaration(node); }
    @Override public Type visit(MethodDeclaration node) { return statements.methodDeclaration(node); }
    @Override public Type visit(ConstructorDeclaration node) { return statements.constructorDeclaration(node); }

    @Override public Type visit(BlockStatement node) { return statements.block(node); }
    @Override public Type visit(VariableDeclarationStatement node) { return statements.variableDeclarationStatement(node); }
    @Override public Type visit(VariableDeclaration node) { return statements.variableDeclaration(node); }
    @Override public Type visit(ExpressionStatement node) { return statements.expressionStatement(node); }
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
    @Override public Type visit(PrintlnStatement node) { return statements.println(node); }

    @Override public Type visit(BinaryExpression node) { return expressions.binary(node); }
    @Override public Type visit(UnaryExpression node) { return expressions.unary(node); }
    @Override public Type visit(AssignmentExpression node) { return expressions.assignment(node); }
    @Override public Type visit(TernaryExpression node) { return expressions.ternary(node); }
    @Override public Type visit(VariableExpression node) { return expressions.variable(node); }
    @Override public Type visit(ArrayAccessExpression node) { return expressions.arrayAccess(node); }
    @Override public Type visit(MemberAccessExpression node) { return expressions.memberAccess(node); }
    @Override public Type visit(FunctionCallExpression node) { return expressions.functionCall(node); }
    @Override public Type visit(ObjectCreationExpression node) { return expressions.objectCreation(node); }
    @Override public Type visit(ArrayCreationExpression node) { return expressions.arrayCreation(node); }
    @Override public Type visit(NullExpression node) { return expressions.nullExpression(node); }
    @Override public Type visit(ReadExpression node) { return expressions.read(node); }
    @Override public Type visit(ExpressionList node) { return expressions.expressionList(node); }
    @Override public Type visit(LiteralExpression node) { return expressions.literal(node); }

    // ---------------------------------------------------------------- no-ops

    @Override public Type visit(BreakStatement node) { return null; }
    @Override public Type visit(ContinueStatement node) { return null; }
    @Override public Type visit(ReadlnStatement node) { return null; }
    @Override public Type visit(ClassMember node) { return null; }
    @Override public Type visit(Parameter node) { return null; }
    @Override public Type visit(ZType node) { return null; }
    @Override public Type visit(ArrayDimensions node) { return null; }
    @Override public Type visit(VariableDeclarator node) { return null; }
    @Override public Type visit(Initializer node) { return null; }
    @Override public Type visit(ExpressionInitializer node) { return null; }
    @Override public Type visit(ArrayInitializer node) { return null; }
}
