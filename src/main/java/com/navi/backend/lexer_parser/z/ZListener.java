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
	 * Enter a parse tree produced by the {@code FieldMember}
	 * labeled alternative in {@link ZParser#classMember}.
	 * @param ctx the parse tree
	 */
	void enterFieldMember(ZParser.FieldMemberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FieldMember}
	 * labeled alternative in {@link ZParser#classMember}.
	 * @param ctx the parse tree
	 */
	void exitFieldMember(ZParser.FieldMemberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConstructorMember}
	 * labeled alternative in {@link ZParser#classMember}.
	 * @param ctx the parse tree
	 */
	void enterConstructorMember(ZParser.ConstructorMemberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConstructorMember}
	 * labeled alternative in {@link ZParser#classMember}.
	 * @param ctx the parse tree
	 */
	void exitConstructorMember(ZParser.ConstructorMemberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MethodMember}
	 * labeled alternative in {@link ZParser#classMember}.
	 * @param ctx the parse tree
	 */
	void enterMethodMember(ZParser.MethodMemberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MethodMember}
	 * labeled alternative in {@link ZParser#classMember}.
	 * @param ctx the parse tree
	 */
	void exitMethodMember(ZParser.MethodMemberContext ctx);
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
	 * Enter a parse tree produced by the {@code TypedMethodDeclaration}
	 * labeled alternative in {@link ZParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterTypedMethodDeclaration(ZParser.TypedMethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypedMethodDeclaration}
	 * labeled alternative in {@link ZParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitTypedMethodDeclaration(ZParser.TypedMethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VoidMethodDeclaration}
	 * labeled alternative in {@link ZParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVoidMethodDeclaration(ZParser.VoidMethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VoidMethodDeclaration}
	 * labeled alternative in {@link ZParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVoidMethodDeclaration(ZParser.VoidMethodDeclarationContext ctx);
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
	 * Enter a parse tree produced by the {@code ExpressionInitializerValue}
	 * labeled alternative in {@link ZParser#initializer}.
	 * @param ctx the parse tree
	 */
	void enterExpressionInitializerValue(ZParser.ExpressionInitializerValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionInitializerValue}
	 * labeled alternative in {@link ZParser#initializer}.
	 * @param ctx the parse tree
	 */
	void exitExpressionInitializerValue(ZParser.ExpressionInitializerValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayInitializerValue}
	 * labeled alternative in {@link ZParser#initializer}.
	 * @param ctx the parse tree
	 */
	void enterArrayInitializerValue(ZParser.ArrayInitializerValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayInitializerValue}
	 * labeled alternative in {@link ZParser#initializer}.
	 * @param ctx the parse tree
	 */
	void exitArrayInitializerValue(ZParser.ArrayInitializerValueContext ctx);
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
	 * Enter a parse tree produced by the {@code ToConditionalExpression}
	 * labeled alternative in {@link ZParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void enterToConditionalExpression(ZParser.ToConditionalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ToConditionalExpression}
	 * labeled alternative in {@link ZParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void exitToConditionalExpression(ZParser.ToConditionalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignmentExpressionValue}
	 * labeled alternative in {@link ZParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentExpressionValue(ZParser.AssignmentExpressionValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignmentExpressionValue}
	 * labeled alternative in {@link ZParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentExpressionValue(ZParser.AssignmentExpressionValueContext ctx);
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
	 * Enter a parse tree produced by the {@code LogicalOrConditionalExpression}
	 * labeled alternative in {@link ZParser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOrConditionalExpression(ZParser.LogicalOrConditionalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LogicalOrConditionalExpression}
	 * labeled alternative in {@link ZParser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOrConditionalExpression(ZParser.LogicalOrConditionalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TernaryExpression}
	 * labeled alternative in {@link ZParser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void enterTernaryExpression(ZParser.TernaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TernaryExpression}
	 * labeled alternative in {@link ZParser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void exitTernaryExpression(ZParser.TernaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ToLogicalAndExpr}
	 * labeled alternative in {@link ZParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterToLogicalAndExpr(ZParser.ToLogicalAndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ToLogicalAndExpr}
	 * labeled alternative in {@link ZParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitToLogicalAndExpr(ZParser.ToLogicalAndExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OrExpr}
	 * labeled alternative in {@link ZParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(ZParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OrExpr}
	 * labeled alternative in {@link ZParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(ZParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AndExpr}
	 * labeled alternative in {@link ZParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(ZParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AndExpr}
	 * labeled alternative in {@link ZParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(ZParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ToEqualityExpr}
	 * labeled alternative in {@link ZParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void enterToEqualityExpr(ZParser.ToEqualityExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ToEqualityExpr}
	 * labeled alternative in {@link ZParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void exitToEqualityExpr(ZParser.ToEqualityExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EqualExpr}
	 * labeled alternative in {@link ZParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void enterEqualExpr(ZParser.EqualExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EqualExpr}
	 * labeled alternative in {@link ZParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void exitEqualExpr(ZParser.EqualExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ToComparisonExpr}
	 * labeled alternative in {@link ZParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void enterToComparisonExpr(ZParser.ToComparisonExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ToComparisonExpr}
	 * labeled alternative in {@link ZParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void exitToComparisonExpr(ZParser.ToComparisonExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NotEqualExpr}
	 * labeled alternative in {@link ZParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void enterNotEqualExpr(ZParser.NotEqualExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NotEqualExpr}
	 * labeled alternative in {@link ZParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void exitNotEqualExpr(ZParser.NotEqualExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ToAdditiveExpr}
	 * labeled alternative in {@link ZParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void enterToAdditiveExpr(ZParser.ToAdditiveExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ToAdditiveExpr}
	 * labeled alternative in {@link ZParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void exitToAdditiveExpr(ZParser.ToAdditiveExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GreaterExpr}
	 * labeled alternative in {@link ZParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void enterGreaterExpr(ZParser.GreaterExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GreaterExpr}
	 * labeled alternative in {@link ZParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void exitGreaterExpr(ZParser.GreaterExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GreaterEqualExpr}
	 * labeled alternative in {@link ZParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void enterGreaterEqualExpr(ZParser.GreaterEqualExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GreaterEqualExpr}
	 * labeled alternative in {@link ZParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void exitGreaterEqualExpr(ZParser.GreaterEqualExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LessExpr}
	 * labeled alternative in {@link ZParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void enterLessExpr(ZParser.LessExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LessExpr}
	 * labeled alternative in {@link ZParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void exitLessExpr(ZParser.LessExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LessEqualExpr}
	 * labeled alternative in {@link ZParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void enterLessEqualExpr(ZParser.LessEqualExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LessEqualExpr}
	 * labeled alternative in {@link ZParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void exitLessEqualExpr(ZParser.LessEqualExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ToMultiplicativeExpr}
	 * labeled alternative in {@link ZParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterToMultiplicativeExpr(ZParser.ToMultiplicativeExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ToMultiplicativeExpr}
	 * labeled alternative in {@link ZParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitToMultiplicativeExpr(ZParser.ToMultiplicativeExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AdditionExpr}
	 * labeled alternative in {@link ZParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterAdditionExpr(ZParser.AdditionExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AdditionExpr}
	 * labeled alternative in {@link ZParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitAdditionExpr(ZParser.AdditionExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SubtractionExpr}
	 * labeled alternative in {@link ZParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterSubtractionExpr(ZParser.SubtractionExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SubtractionExpr}
	 * labeled alternative in {@link ZParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitSubtractionExpr(ZParser.SubtractionExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ModuloExpr}
	 * labeled alternative in {@link ZParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterModuloExpr(ZParser.ModuloExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ModuloExpr}
	 * labeled alternative in {@link ZParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitModuloExpr(ZParser.ModuloExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ToUnaryExpr}
	 * labeled alternative in {@link ZParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterToUnaryExpr(ZParser.ToUnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ToUnaryExpr}
	 * labeled alternative in {@link ZParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitToUnaryExpr(ZParser.ToUnaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DivisionExpr}
	 * labeled alternative in {@link ZParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterDivisionExpr(ZParser.DivisionExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DivisionExpr}
	 * labeled alternative in {@link ZParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitDivisionExpr(ZParser.DivisionExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultiplicationExpr}
	 * labeled alternative in {@link ZParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicationExpr(ZParser.MultiplicationExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultiplicationExpr}
	 * labeled alternative in {@link ZParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicationExpr(ZParser.MultiplicationExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link ZParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpr(ZParser.NotExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link ZParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpr(ZParser.NotExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NegateExpr}
	 * labeled alternative in {@link ZParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterNegateExpr(ZParser.NegateExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NegateExpr}
	 * labeled alternative in {@link ZParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitNegateExpr(ZParser.NegateExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PositiveExpr}
	 * labeled alternative in {@link ZParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPositiveExpr(ZParser.PositiveExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PositiveExpr}
	 * labeled alternative in {@link ZParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPositiveExpr(ZParser.PositiveExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ToPostfixExpr}
	 * labeled alternative in {@link ZParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterToPostfixExpr(ZParser.ToPostfixExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ToPostfixExpr}
	 * labeled alternative in {@link ZParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitToPostfixExpr(ZParser.ToPostfixExprContext ctx);
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
	 * Enter a parse tree produced by the {@code ArrayAccessOp}
	 * labeled alternative in {@link ZParser#postfixOperation}.
	 * @param ctx the parse tree
	 */
	void enterArrayAccessOp(ZParser.ArrayAccessOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayAccessOp}
	 * labeled alternative in {@link ZParser#postfixOperation}.
	 * @param ctx the parse tree
	 */
	void exitArrayAccessOp(ZParser.ArrayAccessOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MemberAccessOp}
	 * labeled alternative in {@link ZParser#postfixOperation}.
	 * @param ctx the parse tree
	 */
	void enterMemberAccessOp(ZParser.MemberAccessOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MemberAccessOp}
	 * labeled alternative in {@link ZParser#postfixOperation}.
	 * @param ctx the parse tree
	 */
	void exitMemberAccessOp(ZParser.MemberAccessOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunctionCallOp}
	 * labeled alternative in {@link ZParser#postfixOperation}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallOp(ZParser.FunctionCallOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunctionCallOp}
	 * labeled alternative in {@link ZParser#postfixOperation}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallOp(ZParser.FunctionCallOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostIncrementOp}
	 * labeled alternative in {@link ZParser#postfixOperation}.
	 * @param ctx the parse tree
	 */
	void enterPostIncrementOp(ZParser.PostIncrementOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostIncrementOp}
	 * labeled alternative in {@link ZParser#postfixOperation}.
	 * @param ctx the parse tree
	 */
	void exitPostIncrementOp(ZParser.PostIncrementOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostDecrementOp}
	 * labeled alternative in {@link ZParser#postfixOperation}.
	 * @param ctx the parse tree
	 */
	void enterPostDecrementOp(ZParser.PostDecrementOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostDecrementOp}
	 * labeled alternative in {@link ZParser#postfixOperation}.
	 * @param ctx the parse tree
	 */
	void exitPostDecrementOp(ZParser.PostDecrementOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LiteralExpr}
	 * labeled alternative in {@link ZParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterLiteralExpr(ZParser.LiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LiteralExpr}
	 * labeled alternative in {@link ZParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitLiteralExpr(ZParser.LiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VariableExpr}
	 * labeled alternative in {@link ZParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterVariableExpr(ZParser.VariableExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VariableExpr}
	 * labeled alternative in {@link ZParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitVariableExpr(ZParser.VariableExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ObjCreationExpr}
	 * labeled alternative in {@link ZParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterObjCreationExpr(ZParser.ObjCreationExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ObjCreationExpr}
	 * labeled alternative in {@link ZParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitObjCreationExpr(ZParser.ObjCreationExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayCreationExpr}
	 * labeled alternative in {@link ZParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterArrayCreationExpr(ZParser.ArrayCreationExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayCreationExpr}
	 * labeled alternative in {@link ZParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitArrayCreationExpr(ZParser.ArrayCreationExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NullExpr}
	 * labeled alternative in {@link ZParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterNullExpr(ZParser.NullExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NullExpr}
	 * labeled alternative in {@link ZParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitNullExpr(ZParser.NullExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReadExpr}
	 * labeled alternative in {@link ZParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterReadExpr(ZParser.ReadExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReadExpr}
	 * labeled alternative in {@link ZParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitReadExpr(ZParser.ReadExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParenthesizedExpr}
	 * labeled alternative in {@link ZParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedExpr(ZParser.ParenthesizedExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenthesizedExpr}
	 * labeled alternative in {@link ZParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedExpr(ZParser.ParenthesizedExprContext ctx);
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
	 * Enter a parse tree produced by the {@code IntegerLiteralExpr}
	 * labeled alternative in {@link ZParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterIntegerLiteralExpr(ZParser.IntegerLiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IntegerLiteralExpr}
	 * labeled alternative in {@link ZParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitIntegerLiteralExpr(ZParser.IntegerLiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FloatLiteralExpr}
	 * labeled alternative in {@link ZParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterFloatLiteralExpr(ZParser.FloatLiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FloatLiteralExpr}
	 * labeled alternative in {@link ZParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitFloatLiteralExpr(ZParser.FloatLiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CharLiteralExpr}
	 * labeled alternative in {@link ZParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterCharLiteralExpr(ZParser.CharLiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CharLiteralExpr}
	 * labeled alternative in {@link ZParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitCharLiteralExpr(ZParser.CharLiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringLiteralExpr}
	 * labeled alternative in {@link ZParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteralExpr(ZParser.StringLiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringLiteralExpr}
	 * labeled alternative in {@link ZParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteralExpr(ZParser.StringLiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TrueLiteralExpr}
	 * labeled alternative in {@link ZParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterTrueLiteralExpr(ZParser.TrueLiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TrueLiteralExpr}
	 * labeled alternative in {@link ZParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitTrueLiteralExpr(ZParser.TrueLiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FalseLiteralExpr}
	 * labeled alternative in {@link ZParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterFalseLiteralExpr(ZParser.FalseLiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FalseLiteralExpr}
	 * labeled alternative in {@link ZParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitFalseLiteralExpr(ZParser.FalseLiteralExprContext ctx);
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