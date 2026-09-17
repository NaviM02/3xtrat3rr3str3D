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
	 * Enter a parse tree produced by the {@code ArrayParameterParam}
	 * labeled alternative in {@link YParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterArrayParameterParam(YParser.ArrayParameterParamContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayParameterParam}
	 * labeled alternative in {@link YParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitArrayParameterParam(YParser.ArrayParameterParamContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StructureParameterParam}
	 * labeled alternative in {@link YParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterStructureParameterParam(YParser.StructureParameterParamContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StructureParameterParam}
	 * labeled alternative in {@link YParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitStructureParameterParam(YParser.StructureParameterParamContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NormalParameter}
	 * labeled alternative in {@link YParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterNormalParameter(YParser.NormalParameterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NormalParameter}
	 * labeled alternative in {@link YParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitNormalParameter(YParser.NormalParameterContext ctx);
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
	 * Enter a parse tree produced by the {@code VariableDeclarationStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarationStmt(YParser.VariableDeclarationStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VariableDeclarationStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarationStmt(YParser.VariableDeclarationStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StructureDeclarationStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStructureDeclarationStmt(YParser.StructureDeclarationStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StructureDeclarationStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStructureDeclarationStmt(YParser.StructureDeclarationStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignmentStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentStmt(YParser.AssignmentStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignmentStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentStmt(YParser.AssignmentStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IncrementStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIncrementStmt(YParser.IncrementStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IncrementStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIncrementStmt(YParser.IncrementStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(YParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(YParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SwitchStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterSwitchStmt(YParser.SwitchStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SwitchStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitSwitchStmt(YParser.SwitchStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ForStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterForStmt(YParser.ForStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ForStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitForStmt(YParser.ForStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WhileStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(YParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WhileStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(YParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DoWhileStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterDoWhileStmt(YParser.DoWhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DoWhileStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitDoWhileStmt(YParser.DoWhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BreakStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStmt(YParser.BreakStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BreakStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStmt(YParser.BreakStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ContinueStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStmt(YParser.ContinueStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ContinueStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStmt(YParser.ContinueStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReturnStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(YParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReturnStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(YParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrintStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterPrintStmt(YParser.PrintStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrintStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitPrintStmt(YParser.PrintStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReadStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReadStmt(YParser.ReadStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReadStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReadStmt(YParser.ReadStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStmt(YParser.ExpressionStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStmt(YParser.ExpressionStmtContext ctx);
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
	 * Enter a parse tree produced by {@link YParser#variableDeclarationCore}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarationCore(YParser.VariableDeclarationCoreContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#variableDeclarationCore}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarationCore(YParser.VariableDeclarationCoreContext ctx);
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
	 * Enter a parse tree produced by the {@code ExpressionInitializer}
	 * labeled alternative in {@link YParser#initializer}.
	 * @param ctx the parse tree
	 */
	void enterExpressionInitializer(YParser.ExpressionInitializerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionInitializer}
	 * labeled alternative in {@link YParser#initializer}.
	 * @param ctx the parse tree
	 */
	void exitExpressionInitializer(YParser.ExpressionInitializerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayInitializerValue}
	 * labeled alternative in {@link YParser#initializer}.
	 * @param ctx the parse tree
	 */
	void enterArrayInitializerValue(YParser.ArrayInitializerValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayInitializerValue}
	 * labeled alternative in {@link YParser#initializer}.
	 * @param ctx the parse tree
	 */
	void exitArrayInitializerValue(YParser.ArrayInitializerValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StructureInitializerValue}
	 * labeled alternative in {@link YParser#initializer}.
	 * @param ctx the parse tree
	 */
	void enterStructureInitializerValue(YParser.StructureInitializerValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StructureInitializerValue}
	 * labeled alternative in {@link YParser#initializer}.
	 * @param ctx the parse tree
	 */
	void exitStructureInitializerValue(YParser.StructureInitializerValueContext ctx);
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
	 * Enter a parse tree produced by the {@code AssignOperator}
	 * labeled alternative in {@link YParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void enterAssignOperator(YParser.AssignOperatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignOperator}
	 * labeled alternative in {@link YParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void exitAssignOperator(YParser.AssignOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PlusAssignOperator}
	 * labeled alternative in {@link YParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void enterPlusAssignOperator(YParser.PlusAssignOperatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PlusAssignOperator}
	 * labeled alternative in {@link YParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void exitPlusAssignOperator(YParser.PlusAssignOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MinusAssignOperator}
	 * labeled alternative in {@link YParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void enterMinusAssignOperator(YParser.MinusAssignOperatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MinusAssignOperator}
	 * labeled alternative in {@link YParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void exitMinusAssignOperator(YParser.MinusAssignOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultiplyAssignOperator}
	 * labeled alternative in {@link YParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void enterMultiplyAssignOperator(YParser.MultiplyAssignOperatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultiplyAssignOperator}
	 * labeled alternative in {@link YParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void exitMultiplyAssignOperator(YParser.MultiplyAssignOperatorContext ctx);
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
	 * Enter a parse tree produced by the {@code ToLogicalAndExpr}
	 * labeled alternative in {@link YParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterToLogicalAndExpr(YParser.ToLogicalAndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ToLogicalAndExpr}
	 * labeled alternative in {@link YParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitToLogicalAndExpr(YParser.ToLogicalAndExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OrExpr}
	 * labeled alternative in {@link YParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(YParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OrExpr}
	 * labeled alternative in {@link YParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(YParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AndExpr}
	 * labeled alternative in {@link YParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(YParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AndExpr}
	 * labeled alternative in {@link YParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(YParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ToEqualityExpr}
	 * labeled alternative in {@link YParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void enterToEqualityExpr(YParser.ToEqualityExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ToEqualityExpr}
	 * labeled alternative in {@link YParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void exitToEqualityExpr(YParser.ToEqualityExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EqualExpr}
	 * labeled alternative in {@link YParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void enterEqualExpr(YParser.EqualExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EqualExpr}
	 * labeled alternative in {@link YParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void exitEqualExpr(YParser.EqualExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ToComparisonExpr}
	 * labeled alternative in {@link YParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void enterToComparisonExpr(YParser.ToComparisonExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ToComparisonExpr}
	 * labeled alternative in {@link YParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void exitToComparisonExpr(YParser.ToComparisonExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NotEqualExpr}
	 * labeled alternative in {@link YParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void enterNotEqualExpr(YParser.NotEqualExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NotEqualExpr}
	 * labeled alternative in {@link YParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void exitNotEqualExpr(YParser.NotEqualExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ToAdditiveExpr}
	 * labeled alternative in {@link YParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void enterToAdditiveExpr(YParser.ToAdditiveExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ToAdditiveExpr}
	 * labeled alternative in {@link YParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void exitToAdditiveExpr(YParser.ToAdditiveExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GreaterExpr}
	 * labeled alternative in {@link YParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void enterGreaterExpr(YParser.GreaterExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GreaterExpr}
	 * labeled alternative in {@link YParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void exitGreaterExpr(YParser.GreaterExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GreaterEqualExpr}
	 * labeled alternative in {@link YParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void enterGreaterEqualExpr(YParser.GreaterEqualExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GreaterEqualExpr}
	 * labeled alternative in {@link YParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void exitGreaterEqualExpr(YParser.GreaterEqualExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LessExpr}
	 * labeled alternative in {@link YParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void enterLessExpr(YParser.LessExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LessExpr}
	 * labeled alternative in {@link YParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void exitLessExpr(YParser.LessExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LessEqualExpr}
	 * labeled alternative in {@link YParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void enterLessEqualExpr(YParser.LessEqualExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LessEqualExpr}
	 * labeled alternative in {@link YParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void exitLessEqualExpr(YParser.LessEqualExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ToMultiplicativeExpr}
	 * labeled alternative in {@link YParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterToMultiplicativeExpr(YParser.ToMultiplicativeExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ToMultiplicativeExpr}
	 * labeled alternative in {@link YParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitToMultiplicativeExpr(YParser.ToMultiplicativeExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AdditionExpr}
	 * labeled alternative in {@link YParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterAdditionExpr(YParser.AdditionExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AdditionExpr}
	 * labeled alternative in {@link YParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitAdditionExpr(YParser.AdditionExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SubtractionExpr}
	 * labeled alternative in {@link YParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterSubtractionExpr(YParser.SubtractionExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SubtractionExpr}
	 * labeled alternative in {@link YParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitSubtractionExpr(YParser.SubtractionExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ModuloExpr}
	 * labeled alternative in {@link YParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterModuloExpr(YParser.ModuloExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ModuloExpr}
	 * labeled alternative in {@link YParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitModuloExpr(YParser.ModuloExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ToUnaryExpr}
	 * labeled alternative in {@link YParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterToUnaryExpr(YParser.ToUnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ToUnaryExpr}
	 * labeled alternative in {@link YParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitToUnaryExpr(YParser.ToUnaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DivisionExpr}
	 * labeled alternative in {@link YParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterDivisionExpr(YParser.DivisionExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DivisionExpr}
	 * labeled alternative in {@link YParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitDivisionExpr(YParser.DivisionExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultiplicationExpr}
	 * labeled alternative in {@link YParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicationExpr(YParser.MultiplicationExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultiplicationExpr}
	 * labeled alternative in {@link YParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicationExpr(YParser.MultiplicationExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link YParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpr(YParser.NotExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link YParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpr(YParser.NotExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NegateExpr}
	 * labeled alternative in {@link YParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterNegateExpr(YParser.NegateExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NegateExpr}
	 * labeled alternative in {@link YParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitNegateExpr(YParser.NegateExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ToPostfixExpr}
	 * labeled alternative in {@link YParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterToPostfixExpr(YParser.ToPostfixExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ToPostfixExpr}
	 * labeled alternative in {@link YParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitToPostfixExpr(YParser.ToPostfixExprContext ctx);
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
	 * Enter a parse tree produced by the {@code ArrayAccessOp}
	 * labeled alternative in {@link YParser#postfixOperation}.
	 * @param ctx the parse tree
	 */
	void enterArrayAccessOp(YParser.ArrayAccessOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayAccessOp}
	 * labeled alternative in {@link YParser#postfixOperation}.
	 * @param ctx the parse tree
	 */
	void exitArrayAccessOp(YParser.ArrayAccessOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MemberAccessOp}
	 * labeled alternative in {@link YParser#postfixOperation}.
	 * @param ctx the parse tree
	 */
	void enterMemberAccessOp(YParser.MemberAccessOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MemberAccessOp}
	 * labeled alternative in {@link YParser#postfixOperation}.
	 * @param ctx the parse tree
	 */
	void exitMemberAccessOp(YParser.MemberAccessOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunctionCallOp}
	 * labeled alternative in {@link YParser#postfixOperation}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallOp(YParser.FunctionCallOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunctionCallOp}
	 * labeled alternative in {@link YParser#postfixOperation}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallOp(YParser.FunctionCallOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostIncrementOp}
	 * labeled alternative in {@link YParser#postfixOperation}.
	 * @param ctx the parse tree
	 */
	void enterPostIncrementOp(YParser.PostIncrementOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostIncrementOp}
	 * labeled alternative in {@link YParser#postfixOperation}.
	 * @param ctx the parse tree
	 */
	void exitPostIncrementOp(YParser.PostIncrementOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostDecrementOp}
	 * labeled alternative in {@link YParser#postfixOperation}.
	 * @param ctx the parse tree
	 */
	void enterPostDecrementOp(YParser.PostDecrementOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostDecrementOp}
	 * labeled alternative in {@link YParser#postfixOperation}.
	 * @param ctx the parse tree
	 */
	void exitPostDecrementOp(YParser.PostDecrementOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IntegerLiteralExpr}
	 * labeled alternative in {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterIntegerLiteralExpr(YParser.IntegerLiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IntegerLiteralExpr}
	 * labeled alternative in {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitIntegerLiteralExpr(YParser.IntegerLiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FloatLiteralExpr}
	 * labeled alternative in {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterFloatLiteralExpr(YParser.FloatLiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FloatLiteralExpr}
	 * labeled alternative in {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitFloatLiteralExpr(YParser.FloatLiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CharLiteralExpr}
	 * labeled alternative in {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterCharLiteralExpr(YParser.CharLiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CharLiteralExpr}
	 * labeled alternative in {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitCharLiteralExpr(YParser.CharLiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringLiteralExpr}
	 * labeled alternative in {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteralExpr(YParser.StringLiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringLiteralExpr}
	 * labeled alternative in {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteralExpr(YParser.StringLiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TrueLiteralExpr}
	 * labeled alternative in {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterTrueLiteralExpr(YParser.TrueLiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TrueLiteralExpr}
	 * labeled alternative in {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitTrueLiteralExpr(YParser.TrueLiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FalseLiteralExpr}
	 * labeled alternative in {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterFalseLiteralExpr(YParser.FalseLiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FalseLiteralExpr}
	 * labeled alternative in {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitFalseLiteralExpr(YParser.FalseLiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VariableExpr}
	 * labeled alternative in {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterVariableExpr(YParser.VariableExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VariableExpr}
	 * labeled alternative in {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitVariableExpr(YParser.VariableExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReadExpr}
	 * labeled alternative in {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterReadExpr(YParser.ReadExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReadExpr}
	 * labeled alternative in {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitReadExpr(YParser.ReadExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParenthesizedExpr}
	 * labeled alternative in {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedExpr(YParser.ParenthesizedExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenthesizedExpr}
	 * labeled alternative in {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedExpr(YParser.ParenthesizedExprContext ctx);
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
	 * Enter a parse tree produced by the {@code IntegerType}
	 * labeled alternative in {@link YParser#type}.
	 * @param ctx the parse tree
	 */
	void enterIntegerType(YParser.IntegerTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IntegerType}
	 * labeled alternative in {@link YParser#type}.
	 * @param ctx the parse tree
	 */
	void exitIntegerType(YParser.IntegerTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FloatType}
	 * labeled alternative in {@link YParser#type}.
	 * @param ctx the parse tree
	 */
	void enterFloatType(YParser.FloatTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FloatType}
	 * labeled alternative in {@link YParser#type}.
	 * @param ctx the parse tree
	 */
	void exitFloatType(YParser.FloatTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CharacterType}
	 * labeled alternative in {@link YParser#type}.
	 * @param ctx the parse tree
	 */
	void enterCharacterType(YParser.CharacterTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CharacterType}
	 * labeled alternative in {@link YParser#type}.
	 * @param ctx the parse tree
	 */
	void exitCharacterType(YParser.CharacterTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanType}
	 * labeled alternative in {@link YParser#type}.
	 * @param ctx the parse tree
	 */
	void enterBooleanType(YParser.BooleanTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanType}
	 * labeled alternative in {@link YParser#type}.
	 * @param ctx the parse tree
	 */
	void exitBooleanType(YParser.BooleanTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringType}
	 * labeled alternative in {@link YParser#type}.
	 * @param ctx the parse tree
	 */
	void enterStringType(YParser.StringTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringType}
	 * labeled alternative in {@link YParser#type}.
	 * @param ctx the parse tree
	 */
	void exitStringType(YParser.StringTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StructureType}
	 * labeled alternative in {@link YParser#type}.
	 * @param ctx the parse tree
	 */
	void enterStructureType(YParser.StructureTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StructureType}
	 * labeled alternative in {@link YParser#type}.
	 * @param ctx the parse tree
	 */
	void exitStructureType(YParser.StructureTypeContext ctx);
}