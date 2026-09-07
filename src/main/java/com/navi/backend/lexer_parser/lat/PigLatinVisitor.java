package com.navi.backend.lexer_parser.lat;// Generated from PigLatin.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PigLatinParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PigLatinVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(PigLatinParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#globalVariablesSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobalVariablesSection(PigLatinParser.GlobalVariablesSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#functionSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionSection(PigLatinParser.FunctionSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#mainSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMainSection(PigLatinParser.MainSectionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StructWithSemicolon}
	 * labeled alternative in {@link PigLatinParser#structDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructWithSemicolon(PigLatinParser.StructWithSemicolonContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StructWithComma}
	 * labeled alternative in {@link PigLatinParser#structDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructWithComma(PigLatinParser.StructWithCommaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StructVariableField}
	 * labeled alternative in {@link PigLatinParser#structField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructVariableField(PigLatinParser.StructVariableFieldContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StructArrayField}
	 * labeled alternative in {@link PigLatinParser#structField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructArrayField(PigLatinParser.StructArrayFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#functionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclaration(PigLatinParser.FunctionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#procedureDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureDeclaration(PigLatinParser.ProcedureDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#functionWithReturn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionWithReturn(PigLatinParser.FunctionWithReturnContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#parameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterList(PigLatinParser.ParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(PigLatinParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#functionBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionBody(PigLatinParser.FunctionBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(PigLatinParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#localVariableSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocalVariableSection(PigLatinParser.LocalVariableSectionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NormalVarDeclaration}
	 * labeled alternative in {@link PigLatinParser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNormalVarDeclaration(PigLatinParser.NormalVarDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprInit}
	 * labeled alternative in {@link PigLatinParser#initializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprInit(PigLatinParser.ExprInitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StructInit}
	 * labeled alternative in {@link PigLatinParser#initializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructInit(PigLatinParser.StructInitContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#structInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructInitializer(PigLatinParser.StructInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#structFieldInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructFieldInitializer(PigLatinParser.StructFieldInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#arrayConstructor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayConstructor(PigLatinParser.ArrayConstructorContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#arrayDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayDeclaration(PigLatinParser.ArrayDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#arrayInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayInitializer(PigLatinParser.ArrayInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(PigLatinParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignmentStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStmt(PigLatinParser.AssignmentStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IncrementStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIncrementStmt(PigLatinParser.IncrementStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunctionCallStatementStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCallStatementStmt(PigLatinParser.FunctionCallStatementStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(PigLatinParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WhileStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStmt(PigLatinParser.WhileStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DoWhileStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoWhileStmt(PigLatinParser.DoWhileStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ForStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStmt(PigLatinParser.ForStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReturnStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStmt(PigLatinParser.ReturnStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BreakStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStmt(PigLatinParser.BreakStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ContinueStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStmt(PigLatinParser.ContinueStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrintStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStmt(PigLatinParser.PrintStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReadStmt}
	 * labeled alternative in {@link PigLatinParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadStmt(PigLatinParser.ReadStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(PigLatinParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#incrementStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIncrementStatement(PigLatinParser.IncrementStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IncrementVariable}
	 * labeled alternative in {@link PigLatinParser#incrementableExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIncrementVariable(PigLatinParser.IncrementVariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IncrementArrayAccess}
	 * labeled alternative in {@link PigLatinParser#incrementableExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIncrementArrayAccess(PigLatinParser.IncrementArrayAccessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IncrementMemberAccess}
	 * labeled alternative in {@link PigLatinParser#incrementableExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIncrementMemberAccess(PigLatinParser.IncrementMemberAccessContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#functionCallStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCallStatement(PigLatinParser.FunctionCallStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallVariable}
	 * labeled alternative in {@link PigLatinParser#callableExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallVariable(PigLatinParser.CallVariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallArrayAccess}
	 * labeled alternative in {@link PigLatinParser#callableExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallArrayAccess(PigLatinParser.CallArrayAccessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallMemberAccess}
	 * labeled alternative in {@link PigLatinParser#callableExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallMemberAccess(PigLatinParser.CallMemberAccessContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(PigLatinParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#elseIfStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseIfStatement(PigLatinParser.ElseIfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#elseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseStatement(PigLatinParser.ElseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(PigLatinParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(PigLatinParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#doWhileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoWhileStatement(PigLatinParser.DoWhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(PigLatinParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(PigLatinParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#continueStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStatement(PigLatinParser.ContinueStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#breakStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStatement(PigLatinParser.BreakStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#readStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadStatement(PigLatinParser.ReadStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#printStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStatement(PigLatinParser.PrintStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(PigLatinParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ToLogicalAndExpr}
	 * labeled alternative in {@link PigLatinParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToLogicalAndExpr(PigLatinParser.ToLogicalAndExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OrExpr}
	 * labeled alternative in {@link PigLatinParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr(PigLatinParser.OrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AndExpr}
	 * labeled alternative in {@link PigLatinParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(PigLatinParser.AndExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ToEqualityExpr}
	 * labeled alternative in {@link PigLatinParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToEqualityExpr(PigLatinParser.ToEqualityExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EqualExpr}
	 * labeled alternative in {@link PigLatinParser#equalityExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualExpr(PigLatinParser.EqualExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ToComparisonExpr}
	 * labeled alternative in {@link PigLatinParser#equalityExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToComparisonExpr(PigLatinParser.ToComparisonExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NotEqualExpr}
	 * labeled alternative in {@link PigLatinParser#equalityExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotEqualExpr(PigLatinParser.NotEqualExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ToAdditiveExpr}
	 * labeled alternative in {@link PigLatinParser#comparisonExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToAdditiveExpr(PigLatinParser.ToAdditiveExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code GreaterExpr}
	 * labeled alternative in {@link PigLatinParser#comparisonExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGreaterExpr(PigLatinParser.GreaterExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code GreaterEqualExpr}
	 * labeled alternative in {@link PigLatinParser#comparisonExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGreaterEqualExpr(PigLatinParser.GreaterEqualExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LessExpr}
	 * labeled alternative in {@link PigLatinParser#comparisonExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLessExpr(PigLatinParser.LessExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LessEqualExpr}
	 * labeled alternative in {@link PigLatinParser#comparisonExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLessEqualExpr(PigLatinParser.LessEqualExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ToMultiplicativeExpr}
	 * labeled alternative in {@link PigLatinParser#additiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToMultiplicativeExpr(PigLatinParser.ToMultiplicativeExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AdditionExpr}
	 * labeled alternative in {@link PigLatinParser#additiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditionExpr(PigLatinParser.AdditionExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SubtractionExpr}
	 * labeled alternative in {@link PigLatinParser#additiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubtractionExpr(PigLatinParser.SubtractionExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ToUnaryExpr}
	 * labeled alternative in {@link PigLatinParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToUnaryExpr(PigLatinParser.ToUnaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DivisionExpr}
	 * labeled alternative in {@link PigLatinParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivisionExpr(PigLatinParser.DivisionExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultiplicationExpr}
	 * labeled alternative in {@link PigLatinParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicationExpr(PigLatinParser.MultiplicationExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link PigLatinParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpr(PigLatinParser.NotExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NegateExpr}
	 * labeled alternative in {@link PigLatinParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegateExpr(PigLatinParser.NegateExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ToPostfixExpr}
	 * labeled alternative in {@link PigLatinParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToPostfixExpr(PigLatinParser.ToPostfixExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunctionCallExpr}
	 * labeled alternative in {@link PigLatinParser#postfixExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCallExpr(PigLatinParser.FunctionCallExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostDecrementExpr}
	 * labeled alternative in {@link PigLatinParser#postfixExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostDecrementExpr(PigLatinParser.PostDecrementExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayAccessExpr}
	 * labeled alternative in {@link PigLatinParser#postfixExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAccessExpr(PigLatinParser.ArrayAccessExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MemberAccessExpr}
	 * labeled alternative in {@link PigLatinParser#postfixExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberAccessExpr(PigLatinParser.MemberAccessExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ToPrimaryExpr}
	 * labeled alternative in {@link PigLatinParser#postfixExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToPrimaryExpr(PigLatinParser.ToPrimaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostIncrementExpr}
	 * labeled alternative in {@link PigLatinParser#postfixExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostIncrementExpr(PigLatinParser.PostIncrementExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#functionArguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionArguments(PigLatinParser.FunctionArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#argumentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentList(PigLatinParser.ArgumentListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumberLiteralExpr}
	 * labeled alternative in {@link PigLatinParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberLiteralExpr(PigLatinParser.NumberLiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DecimalLiteralExpr}
	 * labeled alternative in {@link PigLatinParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecimalLiteralExpr(PigLatinParser.DecimalLiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringLiteralExpr}
	 * labeled alternative in {@link PigLatinParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteralExpr(PigLatinParser.StringLiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CharLiteralExpr}
	 * labeled alternative in {@link PigLatinParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharLiteralExpr(PigLatinParser.CharLiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TrueLiteralExpr}
	 * labeled alternative in {@link PigLatinParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueLiteralExpr(PigLatinParser.TrueLiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FalseLiteralExpr}
	 * labeled alternative in {@link PigLatinParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalseLiteralExpr(PigLatinParser.FalseLiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VariableExpr}
	 * labeled alternative in {@link PigLatinParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableExpr(PigLatinParser.VariableExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParenthesizedExpr}
	 * labeled alternative in {@link PigLatinParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesizedExpr(PigLatinParser.ParenthesizedExprContext ctx);
}