package com.navi.backend.ast.y.visitors;

import com.navi.backend.ast.y.declarations.*;
import com.navi.backend.ast.y.expressions.*;
import com.navi.backend.ast.y.expressions.literals.LiteralExpression;
import com.navi.backend.ast.y.global.ProgramY;
import com.navi.backend.ast.y.statements.*;

public interface AstYVisitor<R> {
    // program
    R visit(ProgramY node);
    // declarations
    R visit(ArrayDeclaration node);
    R visit(ArrayDimensions node);
    R visit(ArrayInitializer node);
    R visit(ArrayParameter node);
    R visit(ExpressionInitializer node);
    R visit(FunctionDeclaration node);
    R visit(NormalParameter node);
    R visit(StructureDeclaration node);
    R visit(StructureField node);
    R visit(StructureInitializer node);
    R visit(StructureParameter node);
    R visit(Type node);
    R visit(VariableDeclaration node);
    // statements
    R visit(AssignmentStatement node);
    R visit(BreakStatement node);
    R visit(ContinueStatement node);
    R visit(DefaultCase node);
    R visit(DoWhileStatement node);
    R visit(ElseClause node);
    R visit(ElseIfClause node);
    R visit(ExpressionStatement node);
    R visit(ForStatement node);
    R visit(IfStatement node);
    R visit(IncrementStatement node);
    R visit(PrintStatement node);
    R visit(ReadStatement node);
    R visit(ReturnStatement node);
    R visit(SwitchCase node);
    R visit(SwitchStatement node);
    R visit(WhileStatement node);
    // expressions
    R visit(ArrayAccessExpression node);
    R visit(BinaryExpression node);
    R visit(FunctionCallExpression node);
    R visit(MemberAccessExpression node);
    R visit(ReadExpression node);
    R visit(UnaryExpression node);
    R visit(VariableExpression node);
    R visit(LiteralExpression node);
}