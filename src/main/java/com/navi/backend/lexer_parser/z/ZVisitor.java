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
	 * Visit a parse tree produced by the {@code FieldMember}
	 * labeled alternative in {@link ZParser#classMember}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldMember(ZParser.FieldMemberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ConstructorMember}
	 * labeled alternative in {@link ZParser#classMember}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructorMember(ZParser.ConstructorMemberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MethodMember}
	 * labeled alternative in {@link ZParser#classMember}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodMember(ZParser.MethodMemberContext ctx);
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
	 * Visit a parse tree produced by the {@code TypedMethodDeclaration}
	 * labeled alternative in {@link ZParser#methodDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypedMethodDeclaration(ZParser.TypedMethodDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VoidMethodDeclaration}
	 * labeled alternative in {@link ZParser#methodDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoidMethodDeclaration(ZParser.VoidMethodDeclarationContext ctx);
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
	 * Visit a parse tree produced by the {@code ExpressionInitializerValue}
	 * labeled alternative in {@link ZParser#initializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionInitializerValue(ZParser.ExpressionInitializerValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayInitializerValue}
	 * labeled alternative in {@link ZParser#initializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayInitializerValue(ZParser.ArrayInitializerValueContext ctx);
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
	 * Visit a parse tree produced by the {@code ToConditionalExpression}
	 * labeled alternative in {@link ZParser#assignmentExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToConditionalExpression(ZParser.ToConditionalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignmentExpressionValue}
	 * labeled alternative in {@link ZParser#assignmentExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentExpressionValue(ZParser.AssignmentExpressionValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#assignmentOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentOperator(ZParser.AssignmentOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LogicalOrConditionalExpression}
	 * labeled alternative in {@link ZParser#conditionalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOrConditionalExpression(ZParser.LogicalOrConditionalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TernaryExpression}
	 * labeled alternative in {@link ZParser#conditionalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTernaryExpression(ZParser.TernaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ToLogicalAndExpr}
	 * labeled alternative in {@link ZParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToLogicalAndExpr(ZParser.ToLogicalAndExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OrExpr}
	 * labeled alternative in {@link ZParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr(ZParser.OrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AndExpr}
	 * labeled alternative in {@link ZParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(ZParser.AndExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ToEqualityExpr}
	 * labeled alternative in {@link ZParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToEqualityExpr(ZParser.ToEqualityExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EqualExpr}
	 * labeled alternative in {@link ZParser#equalityExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualExpr(ZParser.EqualExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ToComparisonExpr}
	 * labeled alternative in {@link ZParser#equalityExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToComparisonExpr(ZParser.ToComparisonExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NotEqualExpr}
	 * labeled alternative in {@link ZParser#equalityExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotEqualExpr(ZParser.NotEqualExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ToAdditiveExpr}
	 * labeled alternative in {@link ZParser#comparisonExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToAdditiveExpr(ZParser.ToAdditiveExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code GreaterExpr}
	 * labeled alternative in {@link ZParser#comparisonExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGreaterExpr(ZParser.GreaterExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code GreaterEqualExpr}
	 * labeled alternative in {@link ZParser#comparisonExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGreaterEqualExpr(ZParser.GreaterEqualExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LessExpr}
	 * labeled alternative in {@link ZParser#comparisonExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLessExpr(ZParser.LessExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LessEqualExpr}
	 * labeled alternative in {@link ZParser#comparisonExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLessEqualExpr(ZParser.LessEqualExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ToMultiplicativeExpr}
	 * labeled alternative in {@link ZParser#additiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToMultiplicativeExpr(ZParser.ToMultiplicativeExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AdditionExpr}
	 * labeled alternative in {@link ZParser#additiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditionExpr(ZParser.AdditionExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SubtractionExpr}
	 * labeled alternative in {@link ZParser#additiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubtractionExpr(ZParser.SubtractionExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ModuloExpr}
	 * labeled alternative in {@link ZParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModuloExpr(ZParser.ModuloExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ToUnaryExpr}
	 * labeled alternative in {@link ZParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToUnaryExpr(ZParser.ToUnaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DivisionExpr}
	 * labeled alternative in {@link ZParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivisionExpr(ZParser.DivisionExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultiplicationExpr}
	 * labeled alternative in {@link ZParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicationExpr(ZParser.MultiplicationExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link ZParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpr(ZParser.NotExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NegateExpr}
	 * labeled alternative in {@link ZParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegateExpr(ZParser.NegateExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PositiveExpr}
	 * labeled alternative in {@link ZParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPositiveExpr(ZParser.PositiveExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ToPostfixExpr}
	 * labeled alternative in {@link ZParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToPostfixExpr(ZParser.ToPostfixExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZParser#postfixExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixExpression(ZParser.PostfixExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayAccessOp}
	 * labeled alternative in {@link ZParser#postfixOperation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAccessOp(ZParser.ArrayAccessOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MemberAccessOp}
	 * labeled alternative in {@link ZParser#postfixOperation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberAccessOp(ZParser.MemberAccessOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunctionCallOp}
	 * labeled alternative in {@link ZParser#postfixOperation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCallOp(ZParser.FunctionCallOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostIncrementOp}
	 * labeled alternative in {@link ZParser#postfixOperation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostIncrementOp(ZParser.PostIncrementOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostDecrementOp}
	 * labeled alternative in {@link ZParser#postfixOperation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostDecrementOp(ZParser.PostDecrementOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LiteralExpr}
	 * labeled alternative in {@link ZParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralExpr(ZParser.LiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VariableExpr}
	 * labeled alternative in {@link ZParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableExpr(ZParser.VariableExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ObjCreationExpr}
	 * labeled alternative in {@link ZParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjCreationExpr(ZParser.ObjCreationExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayCreationExpr}
	 * labeled alternative in {@link ZParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayCreationExpr(ZParser.ArrayCreationExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NullExpr}
	 * labeled alternative in {@link ZParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullExpr(ZParser.NullExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReadExpr}
	 * labeled alternative in {@link ZParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadExpr(ZParser.ReadExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParenthesizedExpr}
	 * labeled alternative in {@link ZParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesizedExpr(ZParser.ParenthesizedExprContext ctx);
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
	 * Visit a parse tree produced by the {@code IntegerLiteralExpr}
	 * labeled alternative in {@link ZParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerLiteralExpr(ZParser.IntegerLiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FloatLiteralExpr}
	 * labeled alternative in {@link ZParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatLiteralExpr(ZParser.FloatLiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CharLiteralExpr}
	 * labeled alternative in {@link ZParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharLiteralExpr(ZParser.CharLiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringLiteralExpr}
	 * labeled alternative in {@link ZParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteralExpr(ZParser.StringLiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TrueLiteralExpr}
	 * labeled alternative in {@link ZParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueLiteralExpr(ZParser.TrueLiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FalseLiteralExpr}
	 * labeled alternative in {@link ZParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalseLiteralExpr(ZParser.FalseLiteralExprContext ctx);
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