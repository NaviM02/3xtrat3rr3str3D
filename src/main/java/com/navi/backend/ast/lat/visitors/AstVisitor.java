package com.navi.backend.ast.lat.visitors;

import com.navi.backend.ast.lat.declarations.*;
import com.navi.backend.ast.lat.declarations.initializers.ExpressionInitializer;
import com.navi.backend.ast.lat.declarations.initializers.StructFieldInitializer;
import com.navi.backend.ast.lat.declarations.initializers.StructInitializer;
import com.navi.backend.ast.lat.expressions.*;
import com.navi.backend.ast.lat.expressions.literals.*;
import com.navi.backend.ast.lat.global.*;
import com.navi.backend.ast.lat.statements.*;

public interface AstVisitor<R> {

    R visit(Program node);

    R visit(GlobalVariableSection node);
    R visit(LocalVariableSection node);
    R visit(FunctionDeclaration node);
    R visit(FunctionBody node);
    R visit(Parameter node);

    R visit(VariableDeclaration node);
    R visit(ArrayDeclaration node);
    R visit(ArrayInitializer node);
    R visit(StructDeclaration node);
    R visit(StructField node);

    R visit(ExpressionInitializer node);
    R visit(StructInitializer node);
    R visit(StructFieldInitializer node);

    R visit(AssignmentStatement node);
    R visit(BlockStatement node);
    R visit(BreakStatement node);
    R visit(ContinueStatement node);
    R visit(DoWhileStatement node);
    R visit(ElseIfStatement node);
    R visit(ForStatement node);
    R visit(FunctionCallStatement node);
    R visit(IfStatement node);
    R visit(IncrementStatement node);
    R visit(PrintStatement node);
    R visit(ReadStatement node);
    R visit(ReturnStatement node);
    R visit(WhileStatement node);

    R visit(ArrayAccessExpression node);
    R visit(BinaryExpression node);
    R visit(FunctionCallExpression node);
    R visit(MemberAccessExpression node);
    R visit(UnaryExpression node);
    R visit(VariableExpression node);

    R visit(BooleanLiteral node);
    R visit(CharLiteral node);
    R visit(DecimalLiteral node);
    R visit(NumberLiteral node);
    R visit(StringLiteral node);
}