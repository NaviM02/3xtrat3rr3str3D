package com.navi.backend.lexer_parser.lat;// Generated from PigLatin.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PigLatinParser}.
 */
public interface PigLatinListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(PigLatinParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(PigLatinParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#globalVariablesSection}.
	 * @param ctx the parse tree
	 */
	void enterGlobalVariablesSection(PigLatinParser.GlobalVariablesSectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#globalVariablesSection}.
	 * @param ctx the parse tree
	 */
	void exitGlobalVariablesSection(PigLatinParser.GlobalVariablesSectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#functionSection}.
	 * @param ctx the parse tree
	 */
	void enterFunctionSection(PigLatinParser.FunctionSectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#functionSection}.
	 * @param ctx the parse tree
	 */
	void exitFunctionSection(PigLatinParser.FunctionSectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#mainSection}.
	 * @param ctx the parse tree
	 */
	void enterMainSection(PigLatinParser.MainSectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#mainSection}.
	 * @param ctx the parse tree
	 */
	void exitMainSection(PigLatinParser.MainSectionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StructWithSemicolon}
	 * labeled alternative in {@link PigLatinParser#structDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterStructWithSemicolon(PigLatinParser.StructWithSemicolonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StructWithSemicolon}
	 * labeled alternative in {@link PigLatinParser#structDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitStructWithSemicolon(PigLatinParser.StructWithSemicolonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StructWithComma}
	 * labeled alternative in {@link PigLatinParser#structDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterStructWithComma(PigLatinParser.StructWithCommaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StructWithComma}
	 * labeled alternative in {@link PigLatinParser#structDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitStructWithComma(PigLatinParser.StructWithCommaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StructVariableField}
	 * labeled alternative in {@link PigLatinParser#structField}.
	 * @param ctx the parse tree
	 */
	void enterStructVariableField(PigLatinParser.StructVariableFieldContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StructVariableField}
	 * labeled alternative in {@link PigLatinParser#structField}.
	 * @param ctx the parse tree
	 */
	void exitStructVariableField(PigLatinParser.StructVariableFieldContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StructArrayField}
	 * labeled alternative in {@link PigLatinParser#structField}.
	 * @param ctx the parse tree
	 */
	void enterStructArrayField(PigLatinParser.StructArrayFieldContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StructArrayField}
	 * labeled alternative in {@link PigLatinParser#structField}.
	 * @param ctx the parse tree
	 */
	void exitStructArrayField(PigLatinParser.StructArrayFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclaration(PigLatinParser.FunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclaration(PigLatinParser.FunctionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#procedureDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterProcedureDeclaration(PigLatinParser.ProcedureDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#procedureDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitProcedureDeclaration(PigLatinParser.ProcedureDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#functionWithReturn}.
	 * @param ctx the parse tree
	 */
	void enterFunctionWithReturn(PigLatinParser.FunctionWithReturnContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#functionWithReturn}.
	 * @param ctx the parse tree
	 */
	void exitFunctionWithReturn(PigLatinParser.FunctionWithReturnContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void enterParameterList(PigLatinParser.ParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void exitParameterList(PigLatinParser.ParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(PigLatinParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(PigLatinParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#functionBody}.
	 * @param ctx the parse tree
	 */
	void enterFunctionBody(PigLatinParser.FunctionBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#functionBody}.
	 * @param ctx the parse tree
	 */
	void exitFunctionBody(PigLatinParser.FunctionBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(PigLatinParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(PigLatinParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#localVariableSection}.
	 * @param ctx the parse tree
	 */
	void enterLocalVariableSection(PigLatinParser.LocalVariableSectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#localVariableSection}.
	 * @param ctx the parse tree
	 */
	void exitLocalVariableSection(PigLatinParser.LocalVariableSectionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NormalVarDeclaration}
	 * labeled alternative in {@link PigLatinParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterNormalVarDeclaration(PigLatinParser.NormalVarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NormalVarDeclaration}
	 * labeled alternative in {@link PigLatinParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitNormalVarDeclaration(PigLatinParser.NormalVarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprInit}
	 * labeled alternative in {@link PigLatinParser#initializer}.
	 * @param ctx the parse tree
	 */
	void enterExprInit(PigLatinParser.ExprInitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprInit}
	 * labeled alternative in {@link PigLatinParser#initializer}.
	 * @param ctx the parse tree
	 */
	void exitExprInit(PigLatinParser.ExprInitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StructInit}
	 * labeled alternative in {@link PigLatinParser#initializer}.
	 * @param ctx the parse tree
	 */
	void enterStructInit(PigLatinParser.StructInitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StructInit}
	 * labeled alternative in {@link PigLatinParser#initializer}.
	 * @param ctx the parse tree
	 */
	void exitStructInit(PigLatinParser.StructInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#structInitializer}.
	 * @param ctx the parse tree
	 */
	void enterStructInitializer(PigLatinParser.StructInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#structInitializer}.
	 * @param ctx the parse tree
	 */
	void exitStructInitializer(PigLatinParser.StructInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#structFieldInitializer}.
	 * @param ctx the parse tree
	 */
	void enterStructFieldInitializer(PigLatinParser.StructFieldInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#structFieldInitializer}.
	 * @param ctx the parse tree
	 */
	void exitStructFieldInitializer(PigLatinParser.StructFieldInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#arrayConstructor}.
	 * @param ctx the parse tree
	 */
	void enterArrayConstructor(PigLatinParser.ArrayConstructorContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#arrayConstructor}.
	 * @param ctx the parse tree
	 */
	void exitArrayConstructor(PigLatinParser.ArrayConstructorContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#arrayDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterArrayDeclaration(PigLatinParser.ArrayDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#arrayDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitArrayDeclaration(PigLatinParser.ArrayDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#arrayInitializer}.
	 * @param ctx the parse tree
	 */
	void enterArrayInitializer(PigLatinParser.ArrayInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#arrayInitializer}.
	 * @param ctx the parse tree
	 */
	void exitArrayInitializer(PigLatinParser.ArrayInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(PigLatinParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(PigLatinParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignmentStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentStmt(PigLatinParser.AssignmentStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignmentStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentStmt(PigLatinParser.AssignmentStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IncrementStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIncrementStmt(PigLatinParser.IncrementStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IncrementStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIncrementStmt(PigLatinParser.IncrementStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunctionCallStatementStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallStatementStmt(PigLatinParser.FunctionCallStatementStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunctionCallStatementStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallStatementStmt(PigLatinParser.FunctionCallStatementStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(PigLatinParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(PigLatinParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WhileStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(PigLatinParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WhileStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(PigLatinParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DoWhileStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterDoWhileStmt(PigLatinParser.DoWhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DoWhileStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitDoWhileStmt(PigLatinParser.DoWhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ForStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterForStmt(PigLatinParser.ForStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ForStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitForStmt(PigLatinParser.ForStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReturnStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(PigLatinParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReturnStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(PigLatinParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BreakStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStmt(PigLatinParser.BreakStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BreakStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStmt(PigLatinParser.BreakStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ContinueStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStmt(PigLatinParser.ContinueStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ContinueStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStmt(PigLatinParser.ContinueStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrintStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterPrintStmt(PigLatinParser.PrintStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrintStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitPrintStmt(PigLatinParser.PrintStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReadStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReadStmt(PigLatinParser.ReadStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReadStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReadStmt(PigLatinParser.ReadStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(PigLatinParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(PigLatinParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#incrementStatement}.
	 * @param ctx the parse tree
	 */
	void enterIncrementStatement(PigLatinParser.IncrementStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#incrementStatement}.
	 * @param ctx the parse tree
	 */
	void exitIncrementStatement(PigLatinParser.IncrementStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IncrementVariable}
	 * labeled alternative in {@link PigLatinParser#incrementableExpression}.
	 * @param ctx the parse tree
	 */
	void enterIncrementVariable(PigLatinParser.IncrementVariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IncrementVariable}
	 * labeled alternative in {@link PigLatinParser#incrementableExpression}.
	 * @param ctx the parse tree
	 */
	void exitIncrementVariable(PigLatinParser.IncrementVariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IncrementArrayAccess}
	 * labeled alternative in {@link PigLatinParser#incrementableExpression}.
	 * @param ctx the parse tree
	 */
	void enterIncrementArrayAccess(PigLatinParser.IncrementArrayAccessContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IncrementArrayAccess}
	 * labeled alternative in {@link PigLatinParser#incrementableExpression}.
	 * @param ctx the parse tree
	 */
	void exitIncrementArrayAccess(PigLatinParser.IncrementArrayAccessContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IncrementMemberAccess}
	 * labeled alternative in {@link PigLatinParser#incrementableExpression}.
	 * @param ctx the parse tree
	 */
	void enterIncrementMemberAccess(PigLatinParser.IncrementMemberAccessContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IncrementMemberAccess}
	 * labeled alternative in {@link PigLatinParser#incrementableExpression}.
	 * @param ctx the parse tree
	 */
	void exitIncrementMemberAccess(PigLatinParser.IncrementMemberAccessContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#functionCallStatement}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallStatement(PigLatinParser.FunctionCallStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#functionCallStatement}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallStatement(PigLatinParser.FunctionCallStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CallVariable}
	 * labeled alternative in {@link PigLatinParser#callableExpression}.
	 * @param ctx the parse tree
	 */
	void enterCallVariable(PigLatinParser.CallVariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CallVariable}
	 * labeled alternative in {@link PigLatinParser#callableExpression}.
	 * @param ctx the parse tree
	 */
	void exitCallVariable(PigLatinParser.CallVariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CallArrayAccess}
	 * labeled alternative in {@link PigLatinParser#callableExpression}.
	 * @param ctx the parse tree
	 */
	void enterCallArrayAccess(PigLatinParser.CallArrayAccessContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CallArrayAccess}
	 * labeled alternative in {@link PigLatinParser#callableExpression}.
	 * @param ctx the parse tree
	 */
	void exitCallArrayAccess(PigLatinParser.CallArrayAccessContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CallMemberAccess}
	 * labeled alternative in {@link PigLatinParser#callableExpression}.
	 * @param ctx the parse tree
	 */
	void enterCallMemberAccess(PigLatinParser.CallMemberAccessContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CallMemberAccess}
	 * labeled alternative in {@link PigLatinParser#callableExpression}.
	 * @param ctx the parse tree
	 */
	void exitCallMemberAccess(PigLatinParser.CallMemberAccessContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(PigLatinParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(PigLatinParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#elseIfStatement}.
	 * @param ctx the parse tree
	 */
	void enterElseIfStatement(PigLatinParser.ElseIfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#elseIfStatement}.
	 * @param ctx the parse tree
	 */
	void exitElseIfStatement(PigLatinParser.ElseIfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#elseStatement}.
	 * @param ctx the parse tree
	 */
	void enterElseStatement(PigLatinParser.ElseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#elseStatement}.
	 * @param ctx the parse tree
	 */
	void exitElseStatement(PigLatinParser.ElseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(PigLatinParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(PigLatinParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(PigLatinParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(PigLatinParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#doWhileStatement}.
	 * @param ctx the parse tree
	 */
	void enterDoWhileStatement(PigLatinParser.DoWhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#doWhileStatement}.
	 * @param ctx the parse tree
	 */
	void exitDoWhileStatement(PigLatinParser.DoWhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(PigLatinParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(PigLatinParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(PigLatinParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(PigLatinParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStatement(PigLatinParser.ContinueStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStatement(PigLatinParser.ContinueStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(PigLatinParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(PigLatinParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#readStatement}.
	 * @param ctx the parse tree
	 */
	void enterReadStatement(PigLatinParser.ReadStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#readStatement}.
	 * @param ctx the parse tree
	 */
	void exitReadStatement(PigLatinParser.ReadStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void enterPrintStatement(PigLatinParser.PrintStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void exitPrintStatement(PigLatinParser.PrintStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(PigLatinParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(PigLatinParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ToLogicalAndExpr}
	 * labeled alternative in {@link PigLatinParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterToLogicalAndExpr(PigLatinParser.ToLogicalAndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ToLogicalAndExpr}
	 * labeled alternative in {@link PigLatinParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitToLogicalAndExpr(PigLatinParser.ToLogicalAndExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OrExpr}
	 * labeled alternative in {@link PigLatinParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(PigLatinParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OrExpr}
	 * labeled alternative in {@link PigLatinParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(PigLatinParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AndExpr}
	 * labeled alternative in {@link PigLatinParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(PigLatinParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AndExpr}
	 * labeled alternative in {@link PigLatinParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(PigLatinParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ToEqualityExpr}
	 * labeled alternative in {@link PigLatinParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void enterToEqualityExpr(PigLatinParser.ToEqualityExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ToEqualityExpr}
	 * labeled alternative in {@link PigLatinParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void exitToEqualityExpr(PigLatinParser.ToEqualityExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EqualExpr}
	 * labeled alternative in {@link PigLatinParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void enterEqualExpr(PigLatinParser.EqualExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EqualExpr}
	 * labeled alternative in {@link PigLatinParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void exitEqualExpr(PigLatinParser.EqualExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ToComparisonExpr}
	 * labeled alternative in {@link PigLatinParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void enterToComparisonExpr(PigLatinParser.ToComparisonExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ToComparisonExpr}
	 * labeled alternative in {@link PigLatinParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void exitToComparisonExpr(PigLatinParser.ToComparisonExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NotEqualExpr}
	 * labeled alternative in {@link PigLatinParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void enterNotEqualExpr(PigLatinParser.NotEqualExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NotEqualExpr}
	 * labeled alternative in {@link PigLatinParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void exitNotEqualExpr(PigLatinParser.NotEqualExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ToAdditiveExpr}
	 * labeled alternative in {@link PigLatinParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void enterToAdditiveExpr(PigLatinParser.ToAdditiveExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ToAdditiveExpr}
	 * labeled alternative in {@link PigLatinParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void exitToAdditiveExpr(PigLatinParser.ToAdditiveExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GreaterExpr}
	 * labeled alternative in {@link PigLatinParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void enterGreaterExpr(PigLatinParser.GreaterExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GreaterExpr}
	 * labeled alternative in {@link PigLatinParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void exitGreaterExpr(PigLatinParser.GreaterExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GreaterEqualExpr}
	 * labeled alternative in {@link PigLatinParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void enterGreaterEqualExpr(PigLatinParser.GreaterEqualExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GreaterEqualExpr}
	 * labeled alternative in {@link PigLatinParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void exitGreaterEqualExpr(PigLatinParser.GreaterEqualExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LessExpr}
	 * labeled alternative in {@link PigLatinParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void enterLessExpr(PigLatinParser.LessExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LessExpr}
	 * labeled alternative in {@link PigLatinParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void exitLessExpr(PigLatinParser.LessExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LessEqualExpr}
	 * labeled alternative in {@link PigLatinParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void enterLessEqualExpr(PigLatinParser.LessEqualExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LessEqualExpr}
	 * labeled alternative in {@link PigLatinParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void exitLessEqualExpr(PigLatinParser.LessEqualExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ToMultiplicativeExpr}
	 * labeled alternative in {@link PigLatinParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterToMultiplicativeExpr(PigLatinParser.ToMultiplicativeExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ToMultiplicativeExpr}
	 * labeled alternative in {@link PigLatinParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitToMultiplicativeExpr(PigLatinParser.ToMultiplicativeExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AdditionExpr}
	 * labeled alternative in {@link PigLatinParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterAdditionExpr(PigLatinParser.AdditionExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AdditionExpr}
	 * labeled alternative in {@link PigLatinParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitAdditionExpr(PigLatinParser.AdditionExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SubtractionExpr}
	 * labeled alternative in {@link PigLatinParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterSubtractionExpr(PigLatinParser.SubtractionExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SubtractionExpr}
	 * labeled alternative in {@link PigLatinParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitSubtractionExpr(PigLatinParser.SubtractionExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ToUnaryExpr}
	 * labeled alternative in {@link PigLatinParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterToUnaryExpr(PigLatinParser.ToUnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ToUnaryExpr}
	 * labeled alternative in {@link PigLatinParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitToUnaryExpr(PigLatinParser.ToUnaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DivisionExpr}
	 * labeled alternative in {@link PigLatinParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterDivisionExpr(PigLatinParser.DivisionExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DivisionExpr}
	 * labeled alternative in {@link PigLatinParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitDivisionExpr(PigLatinParser.DivisionExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultiplicationExpr}
	 * labeled alternative in {@link PigLatinParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicationExpr(PigLatinParser.MultiplicationExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultiplicationExpr}
	 * labeled alternative in {@link PigLatinParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicationExpr(PigLatinParser.MultiplicationExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link PigLatinParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpr(PigLatinParser.NotExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link PigLatinParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpr(PigLatinParser.NotExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NegateExpr}
	 * labeled alternative in {@link PigLatinParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterNegateExpr(PigLatinParser.NegateExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NegateExpr}
	 * labeled alternative in {@link PigLatinParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitNegateExpr(PigLatinParser.NegateExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ToPostfixExpr}
	 * labeled alternative in {@link PigLatinParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterToPostfixExpr(PigLatinParser.ToPostfixExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ToPostfixExpr}
	 * labeled alternative in {@link PigLatinParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitToPostfixExpr(PigLatinParser.ToPostfixExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunctionCallExpr}
	 * labeled alternative in {@link PigLatinParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallExpr(PigLatinParser.FunctionCallExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunctionCallExpr}
	 * labeled alternative in {@link PigLatinParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallExpr(PigLatinParser.FunctionCallExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostDecrementExpr}
	 * labeled alternative in {@link PigLatinParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void enterPostDecrementExpr(PigLatinParser.PostDecrementExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostDecrementExpr}
	 * labeled alternative in {@link PigLatinParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void exitPostDecrementExpr(PigLatinParser.PostDecrementExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayAccessExpr}
	 * labeled alternative in {@link PigLatinParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void enterArrayAccessExpr(PigLatinParser.ArrayAccessExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayAccessExpr}
	 * labeled alternative in {@link PigLatinParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void exitArrayAccessExpr(PigLatinParser.ArrayAccessExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MemberAccessExpr}
	 * labeled alternative in {@link PigLatinParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void enterMemberAccessExpr(PigLatinParser.MemberAccessExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MemberAccessExpr}
	 * labeled alternative in {@link PigLatinParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void exitMemberAccessExpr(PigLatinParser.MemberAccessExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ToPrimaryExpr}
	 * labeled alternative in {@link PigLatinParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void enterToPrimaryExpr(PigLatinParser.ToPrimaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ToPrimaryExpr}
	 * labeled alternative in {@link PigLatinParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void exitToPrimaryExpr(PigLatinParser.ToPrimaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostIncrementExpr}
	 * labeled alternative in {@link PigLatinParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void enterPostIncrementExpr(PigLatinParser.PostIncrementExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostIncrementExpr}
	 * labeled alternative in {@link PigLatinParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void exitPostIncrementExpr(PigLatinParser.PostIncrementExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#functionArguments}.
	 * @param ctx the parse tree
	 */
	void enterFunctionArguments(PigLatinParser.FunctionArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#functionArguments}.
	 * @param ctx the parse tree
	 */
	void exitFunctionArguments(PigLatinParser.FunctionArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void enterArgumentList(PigLatinParser.ArgumentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void exitArgumentList(PigLatinParser.ArgumentListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberLiteralExpr}
	 * labeled alternative in {@link PigLatinParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterNumberLiteralExpr(PigLatinParser.NumberLiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberLiteralExpr}
	 * labeled alternative in {@link PigLatinParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitNumberLiteralExpr(PigLatinParser.NumberLiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DecimalLiteralExpr}
	 * labeled alternative in {@link PigLatinParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterDecimalLiteralExpr(PigLatinParser.DecimalLiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DecimalLiteralExpr}
	 * labeled alternative in {@link PigLatinParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitDecimalLiteralExpr(PigLatinParser.DecimalLiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringLiteralExpr}
	 * labeled alternative in {@link PigLatinParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteralExpr(PigLatinParser.StringLiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringLiteralExpr}
	 * labeled alternative in {@link PigLatinParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteralExpr(PigLatinParser.StringLiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CharLiteralExpr}
	 * labeled alternative in {@link PigLatinParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterCharLiteralExpr(PigLatinParser.CharLiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CharLiteralExpr}
	 * labeled alternative in {@link PigLatinParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitCharLiteralExpr(PigLatinParser.CharLiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TrueLiteralExpr}
	 * labeled alternative in {@link PigLatinParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterTrueLiteralExpr(PigLatinParser.TrueLiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TrueLiteralExpr}
	 * labeled alternative in {@link PigLatinParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitTrueLiteralExpr(PigLatinParser.TrueLiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FalseLiteralExpr}
	 * labeled alternative in {@link PigLatinParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterFalseLiteralExpr(PigLatinParser.FalseLiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FalseLiteralExpr}
	 * labeled alternative in {@link PigLatinParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitFalseLiteralExpr(PigLatinParser.FalseLiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VariableExpr}
	 * labeled alternative in {@link PigLatinParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterVariableExpr(PigLatinParser.VariableExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VariableExpr}
	 * labeled alternative in {@link PigLatinParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitVariableExpr(PigLatinParser.VariableExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParenthesizedExpr}
	 * labeled alternative in {@link PigLatinParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedExpr(PigLatinParser.ParenthesizedExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenthesizedExpr}
	 * labeled alternative in {@link PigLatinParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedExpr(PigLatinParser.ParenthesizedExprContext ctx);
}