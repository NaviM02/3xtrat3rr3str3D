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
	 * Visit a parse tree produced by the {@code ArrayParameterParam}
	 * labeled alternative in {@link YParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayParameterParam(YParser.ArrayParameterParamContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StructureParameterParam}
	 * labeled alternative in {@link YParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructureParameterParam(YParser.StructureParameterParamContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NormalParameter}
	 * labeled alternative in {@link YParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNormalParameter(YParser.NormalParameterContext ctx);
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
	 * Visit a parse tree produced by the {@code VariableDeclarationStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarationStmt(YParser.VariableDeclarationStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StructureDeclarationStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructureDeclarationStmt(YParser.StructureDeclarationStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignmentStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStmt(YParser.AssignmentStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IncrementStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIncrementStmt(YParser.IncrementStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(YParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SwitchStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchStmt(YParser.SwitchStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ForStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStmt(YParser.ForStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WhileStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStmt(YParser.WhileStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DoWhileStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoWhileStmt(YParser.DoWhileStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BreakStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStmt(YParser.BreakStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ContinueStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStmt(YParser.ContinueStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReturnStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStmt(YParser.ReturnStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrintStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStmt(YParser.PrintStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReadStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadStmt(YParser.ReadStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionStmt}
	 * labeled alternative in {@link YParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionStmt(YParser.ExpressionStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(YParser.VariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#variableDeclarationCore}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarationCore(YParser.VariableDeclarationCoreContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#arrayDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayDeclaration(YParser.ArrayDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionInitializer}
	 * labeled alternative in {@link YParser#initializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionInitializer(YParser.ExpressionInitializerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayInitializerValue}
	 * labeled alternative in {@link YParser#initializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayInitializerValue(YParser.ArrayInitializerValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StructureInitializerValue}
	 * labeled alternative in {@link YParser#initializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructureInitializerValue(YParser.StructureInitializerValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#arrayInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayInitializer(YParser.ArrayInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#arrayInitializerElementList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayInitializerElementList(YParser.ArrayInitializerElementListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayExprElement}
	 * labeled alternative in {@link YParser#arrayInitializerElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayExprElement(YParser.ArrayExprElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NestedArrayElement}
	 * labeled alternative in {@link YParser#arrayInitializerElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNestedArrayElement(YParser.NestedArrayElementContext ctx);
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
	 * Visit a parse tree produced by the {@code AssignOperator}
	 * labeled alternative in {@link YParser#assignmentOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignOperator(YParser.AssignOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PlusAssignOperator}
	 * labeled alternative in {@link YParser#assignmentOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusAssignOperator(YParser.PlusAssignOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MinusAssignOperator}
	 * labeled alternative in {@link YParser#assignmentOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinusAssignOperator(YParser.MinusAssignOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultiplyAssignOperator}
	 * labeled alternative in {@link YParser#assignmentOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplyAssignOperator(YParser.MultiplyAssignOperatorContext ctx);
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
	 * Visit a parse tree produced by the {@code ToLogicalAndExpr}
	 * labeled alternative in {@link YParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToLogicalAndExpr(YParser.ToLogicalAndExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OrExpr}
	 * labeled alternative in {@link YParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr(YParser.OrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AndExpr}
	 * labeled alternative in {@link YParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(YParser.AndExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ToEqualityExpr}
	 * labeled alternative in {@link YParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToEqualityExpr(YParser.ToEqualityExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EqualExpr}
	 * labeled alternative in {@link YParser#equalityExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualExpr(YParser.EqualExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ToComparisonExpr}
	 * labeled alternative in {@link YParser#equalityExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToComparisonExpr(YParser.ToComparisonExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NotEqualExpr}
	 * labeled alternative in {@link YParser#equalityExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotEqualExpr(YParser.NotEqualExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ToAdditiveExpr}
	 * labeled alternative in {@link YParser#comparisonExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToAdditiveExpr(YParser.ToAdditiveExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code GreaterExpr}
	 * labeled alternative in {@link YParser#comparisonExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGreaterExpr(YParser.GreaterExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code GreaterEqualExpr}
	 * labeled alternative in {@link YParser#comparisonExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGreaterEqualExpr(YParser.GreaterEqualExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LessExpr}
	 * labeled alternative in {@link YParser#comparisonExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLessExpr(YParser.LessExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LessEqualExpr}
	 * labeled alternative in {@link YParser#comparisonExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLessEqualExpr(YParser.LessEqualExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ToMultiplicativeExpr}
	 * labeled alternative in {@link YParser#additiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToMultiplicativeExpr(YParser.ToMultiplicativeExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AdditionExpr}
	 * labeled alternative in {@link YParser#additiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditionExpr(YParser.AdditionExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SubtractionExpr}
	 * labeled alternative in {@link YParser#additiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubtractionExpr(YParser.SubtractionExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ModuloExpr}
	 * labeled alternative in {@link YParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModuloExpr(YParser.ModuloExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ToUnaryExpr}
	 * labeled alternative in {@link YParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToUnaryExpr(YParser.ToUnaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DivisionExpr}
	 * labeled alternative in {@link YParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivisionExpr(YParser.DivisionExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultiplicationExpr}
	 * labeled alternative in {@link YParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicationExpr(YParser.MultiplicationExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link YParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpr(YParser.NotExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NegateExpr}
	 * labeled alternative in {@link YParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegateExpr(YParser.NegateExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ToPostfixExpr}
	 * labeled alternative in {@link YParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToPostfixExpr(YParser.ToPostfixExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#postfixExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixExpression(YParser.PostfixExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayAccessOp}
	 * labeled alternative in {@link YParser#postfixOperation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAccessOp(YParser.ArrayAccessOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MemberAccessOp}
	 * labeled alternative in {@link YParser#postfixOperation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberAccessOp(YParser.MemberAccessOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunctionCallOp}
	 * labeled alternative in {@link YParser#postfixOperation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCallOp(YParser.FunctionCallOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostIncrementOp}
	 * labeled alternative in {@link YParser#postfixOperation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostIncrementOp(YParser.PostIncrementOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostDecrementOp}
	 * labeled alternative in {@link YParser#postfixOperation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostDecrementOp(YParser.PostDecrementOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IntegerLiteralExpr}
	 * labeled alternative in {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerLiteralExpr(YParser.IntegerLiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FloatLiteralExpr}
	 * labeled alternative in {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatLiteralExpr(YParser.FloatLiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CharLiteralExpr}
	 * labeled alternative in {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharLiteralExpr(YParser.CharLiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringLiteralExpr}
	 * labeled alternative in {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteralExpr(YParser.StringLiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TrueLiteralExpr}
	 * labeled alternative in {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueLiteralExpr(YParser.TrueLiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FalseLiteralExpr}
	 * labeled alternative in {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalseLiteralExpr(YParser.FalseLiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VariableExpr}
	 * labeled alternative in {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableExpr(YParser.VariableExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReadExpr}
	 * labeled alternative in {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadExpr(YParser.ReadExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParenthesizedExpr}
	 * labeled alternative in {@link YParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesizedExpr(YParser.ParenthesizedExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#argumentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentList(YParser.ArgumentListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IntegerType}
	 * labeled alternative in {@link YParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerType(YParser.IntegerTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FloatType}
	 * labeled alternative in {@link YParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatType(YParser.FloatTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CharacterType}
	 * labeled alternative in {@link YParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharacterType(YParser.CharacterTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanType}
	 * labeled alternative in {@link YParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanType(YParser.BooleanTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringType}
	 * labeled alternative in {@link YParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringType(YParser.StringTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StructureType}
	 * labeled alternative in {@link YParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructureType(YParser.StructureTypeContext ctx);
}