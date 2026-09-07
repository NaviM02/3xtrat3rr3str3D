package com.navi.backend.lexer_parser.y;// Generated from YParser.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link YParser}.
 */
public interface YParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link YParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(YParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(YParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#structuresSection}.
	 * @param ctx the parse tree
	 */
	void enterStructuresSection(YParser.StructuresSectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#structuresSection}.
	 * @param ctx the parse tree
	 */
	void exitStructuresSection(YParser.StructuresSectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#functionsSection}.
	 * @param ctx the parse tree
	 */
	void enterFunctionsSection(YParser.FunctionsSectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#functionsSection}.
	 * @param ctx the parse tree
	 */
	void exitFunctionsSection(YParser.FunctionsSectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#structureDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterStructureDeclaration(YParser.StructureDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#structureDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitStructureDeclaration(YParser.StructureDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#structureField}.
	 * @param ctx the parse tree
	 */
	void enterStructureField(YParser.StructureFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#structureField}.
	 * @param ctx the parse tree
	 */
	void exitStructureField(YParser.StructureFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#arrayDimensions}.
	 * @param ctx the parse tree
	 */
	void enterArrayDimensions(YParser.ArrayDimensionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#arrayDimensions}.
	 * @param ctx the parse tree
	 */
	void exitArrayDimensions(YParser.ArrayDimensionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclaration(YParser.FunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclaration(YParser.FunctionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#returnType}.
	 * @param ctx the parse tree
	 */
	void enterReturnType(YParser.ReturnTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#returnType}.
	 * @param ctx the parse tree
	 */
	void exitReturnType(YParser.ReturnTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void enterParameterList(YParser.ParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void exitParameterList(YParser.ParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(YParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(YParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#arrayParameter}.
	 * @param ctx the parse tree
	 */
	void enterArrayParameter(YParser.ArrayParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#arrayParameter}.
	 * @param ctx the parse tree
	 */
	void exitArrayParameter(YParser.ArrayParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#structureParameter}.
	 * @param ctx the parse tree
	 */
	void enterStructureParameter(YParser.StructureParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#structureParameter}.
	 * @param ctx the parse tree
	 */
	void exitStructureParameter(YParser.StructureParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(YParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(YParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaration(YParser.VariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaration(YParser.VariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#arrayDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterArrayDeclaration(YParser.ArrayDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#arrayDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitArrayDeclaration(YParser.ArrayDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#initializer}.
	 * @param ctx the parse tree
	 */
	void enterInitializer(YParser.InitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#initializer}.
	 * @param ctx the parse tree
	 */
	void exitInitializer(YParser.InitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#arrayInitializer}.
	 * @param ctx the parse tree
	 */
	void enterArrayInitializer(YParser.ArrayInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#arrayInitializer}.
	 * @param ctx the parse tree
	 */
	void exitArrayInitializer(YParser.ArrayInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#structureInitializer}.
	 * @param ctx the parse tree
	 */
	void enterStructureInitializer(YParser.StructureInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#structureInitializer}.
	 * @param ctx the parse tree
	 */
	void exitStructureInitializer(YParser.StructureInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(YParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(YParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentStatement(YParser.AssignmentStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentStatement(YParser.AssignmentStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentOperator(YParser.AssignmentOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentOperator(YParser.AssignmentOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#incrementStatement}.
	 * @param ctx the parse tree
	 */
	void enterIncrementStatement(YParser.IncrementStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#incrementStatement}.
	 * @param ctx the parse tree
	 */
	void exitIncrementStatement(YParser.IncrementStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#assignableExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignableExpression(YParser.AssignableExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#assignableExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignableExpression(YParser.AssignableExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(YParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(YParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#elseIfClause}.
	 * @param ctx the parse tree
	 */
	void enterElseIfClause(YParser.ElseIfClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#elseIfClause}.
	 * @param ctx the parse tree
	 */
	void exitElseIfClause(YParser.ElseIfClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#elseClause}.
	 * @param ctx the parse tree
	 */
	void enterElseClause(YParser.ElseClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#elseClause}.
	 * @param ctx the parse tree
	 */
	void exitElseClause(YParser.ElseClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#switchStatement}.
	 * @param ctx the parse tree
	 */
	void enterSwitchStatement(YParser.SwitchStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#switchStatement}.
	 * @param ctx the parse tree
	 */
	void exitSwitchStatement(YParser.SwitchStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#switchCase}.
	 * @param ctx the parse tree
	 */
	void enterSwitchCase(YParser.SwitchCaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#switchCase}.
	 * @param ctx the parse tree
	 */
	void exitSwitchCase(YParser.SwitchCaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#defaultCase}.
	 * @param ctx the parse tree
	 */
	void enterDefaultCase(YParser.DefaultCaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#defaultCase}.
	 * @param ctx the parse tree
	 */
	void exitDefaultCase(YParser.DefaultCaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(YParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(YParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#forInitializer}.
	 * @param ctx the parse tree
	 */
	void enterForInitializer(YParser.ForInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#forInitializer}.
	 * @param ctx the parse tree
	 */
	void exitForInitializer(YParser.ForInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#variableDeclarationWithoutNewline}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarationWithoutNewline(YParser.VariableDeclarationWithoutNewlineContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#variableDeclarationWithoutNewline}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarationWithoutNewline(YParser.VariableDeclarationWithoutNewlineContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void enterForUpdate(YParser.ForUpdateContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void exitForUpdate(YParser.ForUpdateContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(YParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(YParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#doWhileStatement}.
	 * @param ctx the parse tree
	 */
	void enterDoWhileStatement(YParser.DoWhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#doWhileStatement}.
	 * @param ctx the parse tree
	 */
	void exitDoWhileStatement(YParser.DoWhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(YParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(YParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStatement(YParser.ContinueStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStatement(YParser.ContinueStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(YParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(YParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void enterPrintStatement(YParser.PrintStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void exitPrintStatement(YParser.PrintStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#readStatement}.
	 * @param ctx the parse tree
	 */
	void enterReadStatement(YParser.ReadStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#readStatement}.
	 * @param ctx the parse tree
	 */
	void exitReadStatement(YParser.ReadStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(YParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(YParser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(YParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(YParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOrExpression(YParser.LogicalOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOrExpression(YParser.LogicalOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAndExpression(YParser.LogicalAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAndExpression(YParser.LogicalAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpression(YParser.EqualityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpression(YParser.EqualityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void enterComparisonExpression(YParser.ComparisonExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void exitComparisonExpression(YParser.ComparisonExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(YParser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(YParser.AdditiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(YParser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(YParser.MultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(YParser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(YParser.UnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void enterPostfixExpression(YParser.PostfixExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void exitPostfixExpression(YParser.PostfixExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#postfixOperation}.
	 * @param ctx the parse tree
	 */
	void enterPostfixOperation(YParser.PostfixOperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#postfixOperation}.
	 * @param ctx the parse tree
	 */
	void exitPostfixOperation(YParser.PostfixOperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression(YParser.PrimaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression(YParser.PrimaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void enterArgumentList(YParser.ArgumentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void exitArgumentList(YParser.ArgumentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(YParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(YParser.TypeContext ctx);
}