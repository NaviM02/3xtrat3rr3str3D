package com.navi.backend.lexer_parser.z;// Generated from Z.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ZParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ZVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ZParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(ZParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(ZParser.ClassDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#classMember}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassMember(ZParser.ClassMemberContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldDeclaration(ZParser.FieldDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#variableDeclaratorList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaratorList(ZParser.VariableDeclaratorListContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#variableDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarator(ZParser.VariableDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructorDeclaration(ZParser.ConstructorDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#methodDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDeclaration(ZParser.MethodDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#parameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterList(ZParser.ParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(ZParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(ZParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(ZParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#variableDeclarationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarationStatement(ZParser.VariableDeclarationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#initializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitializer(ZParser.InitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#statementOrBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementOrBlock(ZParser.StatementOrBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(ZParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#switchStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchStatement(ZParser.SwitchStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#switchCase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchCase(ZParser.SwitchCaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#defaultCase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultCase(ZParser.DefaultCaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(ZParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#forInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForInitializer(ZParser.ForInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#variableDeclarationStatementNoSemi}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarationStatementNoSemi(ZParser.VariableDeclarationStatementNoSemiContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#forUpdate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForUpdate(ZParser.ForUpdateContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(ZParser.ExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(ZParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#doWhileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoWhileStatement(ZParser.DoWhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(ZParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#breakStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStatement(ZParser.BreakStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#continueStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStatement(ZParser.ContinueStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#printStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStatement(ZParser.PrintStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#printlnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintlnStatement(ZParser.PrintlnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#readlnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadlnStatement(ZParser.ReadlnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#expressionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionStatement(ZParser.ExpressionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(ZParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#assignmentExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentExpression(ZParser.AssignmentExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#assignmentOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentOperator(ZParser.AssignmentOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#conditionalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalExpression(ZParser.ConditionalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOrExpression(ZParser.LogicalOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalAndExpression(ZParser.LogicalAndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#equalityExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpression(ZParser.EqualityExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#relationalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpression(ZParser.RelationalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#additiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpression(ZParser.AdditiveExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpression(ZParser.MultiplicativeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpression(ZParser.UnaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#postfixExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixExpression(ZParser.PostfixExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#postfixOperation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixOperation(ZParser.PostfixOperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#arrayAccess}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAccess(ZParser.ArrayAccessContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#memberAccess}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberAccess(ZParser.MemberAccessContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(ZParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpression(ZParser.PrimaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#arrayCreation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayCreation(ZParser.ArrayCreationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#arrayCreationDimensions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayCreationDimensions(ZParser.ArrayCreationDimensionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(ZParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#argumentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentList(ZParser.ArgumentListContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#arrayInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayInitializer(ZParser.ArrayInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#arrayInitializerElementList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayInitializerElementList(ZParser.ArrayInitializerElementListContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#arrayInitializerElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayInitializerElement(ZParser.ArrayInitializerElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#arrayDimensions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayDimensions(ZParser.ArrayDimensionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(ZParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#primitiveType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveType(ZParser.PrimitiveTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#referenceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReferenceType(ZParser.ReferenceTypeContext ctx);
}