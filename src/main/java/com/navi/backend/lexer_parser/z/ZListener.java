package com.navi.backend.lexer_parser.z;// Generated from Z.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ZParser}.
 */
public interface ZListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ZParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(ZParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(ZParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(ZParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(ZParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#classMember}.
	 * @param ctx the parse tree
	 */
	void enterClassMember(ZParser.ClassMemberContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#classMember}.
	 * @param ctx the parse tree
	 */
	void exitClassMember(ZParser.ClassMemberContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFieldDeclaration(ZParser.FieldDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFieldDeclaration(ZParser.FieldDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#variableDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaratorList(ZParser.VariableDeclaratorListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#variableDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaratorList(ZParser.VariableDeclaratorListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#variableDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarator(ZParser.VariableDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#variableDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarator(ZParser.VariableDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterConstructorDeclaration(ZParser.ConstructorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitConstructorDeclaration(ZParser.ConstructorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclaration(ZParser.MethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclaration(ZParser.MethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void enterParameterList(ZParser.ParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void exitParameterList(ZParser.ParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(ZParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(ZParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(ZParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(ZParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(ZParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(ZParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#variableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarationStatement(ZParser.VariableDeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#variableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarationStatement(ZParser.VariableDeclarationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#initializer}.
	 * @param ctx the parse tree
	 */
	void enterInitializer(ZParser.InitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#initializer}.
	 * @param ctx the parse tree
	 */
	void exitInitializer(ZParser.InitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#statementOrBlock}.
	 * @param ctx the parse tree
	 */
	void enterStatementOrBlock(ZParser.StatementOrBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#statementOrBlock}.
	 * @param ctx the parse tree
	 */
	void exitStatementOrBlock(ZParser.StatementOrBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(ZParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(ZParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#switchStatement}.
	 * @param ctx the parse tree
	 */
	void enterSwitchStatement(ZParser.SwitchStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#switchStatement}.
	 * @param ctx the parse tree
	 */
	void exitSwitchStatement(ZParser.SwitchStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#switchCase}.
	 * @param ctx the parse tree
	 */
	void enterSwitchCase(ZParser.SwitchCaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#switchCase}.
	 * @param ctx the parse tree
	 */
	void exitSwitchCase(ZParser.SwitchCaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#defaultCase}.
	 * @param ctx the parse tree
	 */
	void enterDefaultCase(ZParser.DefaultCaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#defaultCase}.
	 * @param ctx the parse tree
	 */
	void exitDefaultCase(ZParser.DefaultCaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(ZParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(ZParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#forInitializer}.
	 * @param ctx the parse tree
	 */
	void enterForInitializer(ZParser.ForInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#forInitializer}.
	 * @param ctx the parse tree
	 */
	void exitForInitializer(ZParser.ForInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#variableDeclarationStatementNoSemi}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarationStatementNoSemi(ZParser.VariableDeclarationStatementNoSemiContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#variableDeclarationStatementNoSemi}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarationStatementNoSemi(ZParser.VariableDeclarationStatementNoSemiContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void enterForUpdate(ZParser.ForUpdateContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void exitForUpdate(ZParser.ForUpdateContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(ZParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(ZParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(ZParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(ZParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#doWhileStatement}.
	 * @param ctx the parse tree
	 */
	void enterDoWhileStatement(ZParser.DoWhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#doWhileStatement}.
	 * @param ctx the parse tree
	 */
	void exitDoWhileStatement(ZParser.DoWhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(ZParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(ZParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(ZParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(ZParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStatement(ZParser.ContinueStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStatement(ZParser.ContinueStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void enterPrintStatement(ZParser.PrintStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void exitPrintStatement(ZParser.PrintStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#printlnStatement}.
	 * @param ctx the parse tree
	 */
	void enterPrintlnStatement(ZParser.PrintlnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#printlnStatement}.
	 * @param ctx the parse tree
	 */
	void exitPrintlnStatement(ZParser.PrintlnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#readlnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReadlnStatement(ZParser.ReadlnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#readlnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReadlnStatement(ZParser.ReadlnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(ZParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(ZParser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(ZParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(ZParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentExpression(ZParser.AssignmentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentExpression(ZParser.AssignmentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentOperator(ZParser.AssignmentOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentOperator(ZParser.AssignmentOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void enterConditionalExpression(ZParser.ConditionalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void exitConditionalExpression(ZParser.ConditionalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOrExpression(ZParser.LogicalOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOrExpression(ZParser.LogicalOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAndExpression(ZParser.LogicalAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAndExpression(ZParser.LogicalAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpression(ZParser.EqualityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpression(ZParser.EqualityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpression(ZParser.RelationalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpression(ZParser.RelationalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(ZParser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(ZParser.AdditiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(ZParser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(ZParser.MultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(ZParser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(ZParser.UnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void enterPostfixExpression(ZParser.PostfixExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void exitPostfixExpression(ZParser.PostfixExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#postfixOperation}.
	 * @param ctx the parse tree
	 */
	void enterPostfixOperation(ZParser.PostfixOperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#postfixOperation}.
	 * @param ctx the parse tree
	 */
	void exitPostfixOperation(ZParser.PostfixOperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#arrayAccess}.
	 * @param ctx the parse tree
	 */
	void enterArrayAccess(ZParser.ArrayAccessContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#arrayAccess}.
	 * @param ctx the parse tree
	 */
	void exitArrayAccess(ZParser.ArrayAccessContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#memberAccess}.
	 * @param ctx the parse tree
	 */
	void enterMemberAccess(ZParser.MemberAccessContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#memberAccess}.
	 * @param ctx the parse tree
	 */
	void exitMemberAccess(ZParser.MemberAccessContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(ZParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(ZParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression(ZParser.PrimaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression(ZParser.PrimaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#arrayCreation}.
	 * @param ctx the parse tree
	 */
	void enterArrayCreation(ZParser.ArrayCreationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#arrayCreation}.
	 * @param ctx the parse tree
	 */
	void exitArrayCreation(ZParser.ArrayCreationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#arrayCreationDimensions}.
	 * @param ctx the parse tree
	 */
	void enterArrayCreationDimensions(ZParser.ArrayCreationDimensionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#arrayCreationDimensions}.
	 * @param ctx the parse tree
	 */
	void exitArrayCreationDimensions(ZParser.ArrayCreationDimensionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(ZParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(ZParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void enterArgumentList(ZParser.ArgumentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void exitArgumentList(ZParser.ArgumentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#arrayInitializer}.
	 * @param ctx the parse tree
	 */
	void enterArrayInitializer(ZParser.ArrayInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#arrayInitializer}.
	 * @param ctx the parse tree
	 */
	void exitArrayInitializer(ZParser.ArrayInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#arrayInitializerElementList}.
	 * @param ctx the parse tree
	 */
	void enterArrayInitializerElementList(ZParser.ArrayInitializerElementListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#arrayInitializerElementList}.
	 * @param ctx the parse tree
	 */
	void exitArrayInitializerElementList(ZParser.ArrayInitializerElementListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#arrayInitializerElement}.
	 * @param ctx the parse tree
	 */
	void enterArrayInitializerElement(ZParser.ArrayInitializerElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#arrayInitializerElement}.
	 * @param ctx the parse tree
	 */
	void exitArrayInitializerElement(ZParser.ArrayInitializerElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#arrayDimensions}.
	 * @param ctx the parse tree
	 */
	void enterArrayDimensions(ZParser.ArrayDimensionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#arrayDimensions}.
	 * @param ctx the parse tree
	 */
	void exitArrayDimensions(ZParser.ArrayDimensionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(ZParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(ZParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveType(ZParser.PrimitiveTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveType(ZParser.PrimitiveTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZParser#referenceType}.
	 * @param ctx the parse tree
	 */
	void enterReferenceType(ZParser.ReferenceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZParser#referenceType}.
	 * @param ctx the parse tree
	 */
	void exitReferenceType(ZParser.ReferenceTypeContext ctx);
}