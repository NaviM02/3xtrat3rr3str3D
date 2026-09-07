package com.navi.backend.lexer_parser.y;// Generated from YParser.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link YParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface YParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link YParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(YParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#structuresSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructuresSection(YParser.StructuresSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#functionsSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionsSection(YParser.FunctionsSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#structureDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructureDeclaration(YParser.StructureDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#structureField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructureField(YParser.StructureFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#arrayDimensions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayDimensions(YParser.ArrayDimensionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#functionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclaration(YParser.FunctionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#returnType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnType(YParser.ReturnTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#parameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterList(YParser.ParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(YParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#arrayParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayParameter(YParser.ArrayParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#structureParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructureParameter(YParser.StructureParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(YParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(YParser.VariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#arrayDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayDeclaration(YParser.ArrayDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#initializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitializer(YParser.InitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#arrayInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayInitializer(YParser.ArrayInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#structureInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructureInitializer(YParser.StructureInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(YParser.ExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#assignmentStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStatement(YParser.AssignmentStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#assignmentOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentOperator(YParser.AssignmentOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#incrementStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIncrementStatement(YParser.IncrementStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#assignableExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignableExpression(YParser.AssignableExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(YParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#elseIfClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseIfClause(YParser.ElseIfClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#elseClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseClause(YParser.ElseClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#switchStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchStatement(YParser.SwitchStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#switchCase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchCase(YParser.SwitchCaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#defaultCase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultCase(YParser.DefaultCaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(YParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#forInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForInitializer(YParser.ForInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#variableDeclarationWithoutNewline}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarationWithoutNewline(YParser.VariableDeclarationWithoutNewlineContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#forUpdate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForUpdate(YParser.ForUpdateContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(YParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#doWhileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoWhileStatement(YParser.DoWhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#breakStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStatement(YParser.BreakStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#continueStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStatement(YParser.ContinueStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(YParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#printStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStatement(YParser.PrintStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#readStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadStatement(YParser.ReadStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#expressionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionStatement(YParser.ExpressionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(YParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOrExpression(YParser.LogicalOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalAndExpression(YParser.LogicalAndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#equalityExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpression(YParser.EqualityExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#comparisonExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonExpression(YParser.ComparisonExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#additiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpression(YParser.AdditiveExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpression(YParser.MultiplicativeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpression(YParser.UnaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#postfixExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixExpression(YParser.PostfixExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#postfixOperation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixOperation(YParser.PostfixOperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpression(YParser.PrimaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#argumentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentList(YParser.ArgumentListContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(YParser.TypeContext ctx);
}