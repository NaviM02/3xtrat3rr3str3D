package com.navi.backend.ast.z.visitors;

import com.navi.backend.ast.z.declarations.*;
import com.navi.backend.ast.z.expressions.*;
import com.navi.backend.ast.z.expressions.literals.LiteralExpression;
import com.navi.backend.ast.z.global.ProgramZ;
import com.navi.backend.ast.z.statements.*;

public interface AstZVisitor<R> {
    R visit(ProgramZ node);
    R visit(ClassDeclaration node);
    R visit(ClassMember node);
    R visit(FieldDeclaration node);
    R visit(ConstructorDeclaration node);
    R visit(MethodDeclaration node);
    R visit(Parameter node);
    R visit(ZType node);
    R visit(ArrayDimensions node);
    R visit(VariableDeclarator node);
    R visit(VariableDeclaration node);
    R visit(Initializer node);
    R visit(ExpressionInitializer node);
    R visit(ArrayInitializer node);

    R visit(VariableDeclarationStatement node);
    R visit(BlockStatement node);
    R visit(ExpressionStatement node);
    R visit(IfStatement node);
    R visit(ElseIfClause node);
    R visit(ElseClause node);
    R visit(SwitchStatement node);
    R visit(SwitchCase node);
    R visit(DefaultCase node);
    R visit(ForStatement node);
    R visit(WhileStatement node);
    R visit(DoWhileStatement node);
    R visit(ReturnStatement node);
    R visit(BreakStatement node);
    R visit(ContinueStatement node);
    R visit(PrintStatement node);
    R visit(PrintlnStatement node);
    R visit(ReadlnStatement node);

    R visit(ArrayAccessExpression node);
    R visit(AssignmentExpression node);
    R visit(BinaryExpression node);
    R visit(FunctionCallExpression node);
    R visit(MemberAccessExpression node);
    R visit(UnaryExpression node);
    R visit(VariableExpression node);
    R visit(TernaryExpression node);
    R visit(ObjectCreationExpression node);
    R visit(ArrayCreationExpression node);
    R visit(NullExpression node);
    R visit(ReadExpression node);
    R visit(ExpressionList node);
    R visit(LiteralExpression node);
}
