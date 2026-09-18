package com.navi.backend.lexer_parser.z;// Generated from Z.g4 by ANTLR 4.13.2

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class ZParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PUBLIC=1, CLASS=2, VOID=3, RETURN=4, IF=5, ELSE=6, SWITCH=7, CASE=8, DEFAULT=9, 
		FOR=10, WHILE=11, DO=12, BREAK=13, CONTINUE=14, PRINT=15, PRINTLN=16, 
		READLN=17, NEW=18, NULL=19, TRUE=20, FALSE=21, INT=22, DOUBLE=23, CHAR=24, 
		BOOLEAN=25, STRING=26, PLUS=27, MINUS=28, MULT=29, DIV=30, MOD=31, INCREMENT=32, 
		DECREMENT=33, AND=34, OR=35, NOT=36, EQUAL=37, NOT_EQUAL=38, LESS=39, 
		GREATER=40, LESS_EQUAL=41, GREATER_EQUAL=42, ASSIGN=43, PLUS_ASSIGN=44, 
		MINUS_ASSIGN=45, MULT_ASSIGN=46, QUESTION=47, LPAREN=48, RPAREN=49, LBRACE=50, 
		RBRACE=51, LBRACK=52, RBRACK=53, DOT=54, COMMA=55, COLON=56, SEMI=57, 
		FLOAT_LITERAL=58, INTEGER_LITERAL=59, CHAR_LITERAL=60, STRING_LITERAL=61, 
		ID=62, LINE_COMMENT=63, BLOCK_COMMENT=64, WS=65;
	public static final int
		RULE_program = 0, RULE_classDeclaration = 1, RULE_classMember = 2, RULE_fieldDeclaration = 3, 
		RULE_variableDeclaratorList = 4, RULE_variableDeclarator = 5, RULE_constructorDeclaration = 6, 
		RULE_methodDeclaration = 7, RULE_parameterList = 8, RULE_parameter = 9, 
		RULE_block = 10, RULE_statement = 11, RULE_variableDeclarationStatement = 12, 
		RULE_initializer = 13, RULE_statementOrBlock = 14, RULE_ifStatement = 15, 
		RULE_switchStatement = 16, RULE_switchCase = 17, RULE_defaultCase = 18, 
		RULE_forStatement = 19, RULE_forInitializer = 20, RULE_variableDeclarationStatementNoSemi = 21, 
		RULE_forUpdate = 22, RULE_expressionList = 23, RULE_whileStatement = 24, 
		RULE_doWhileStatement = 25, RULE_returnStatement = 26, RULE_breakStatement = 27, 
		RULE_continueStatement = 28, RULE_printStatement = 29, RULE_printlnStatement = 30, 
		RULE_readlnStatement = 31, RULE_expressionStatement = 32, RULE_expression = 33, 
		RULE_assignmentExpression = 34, RULE_assignmentOperator = 35, RULE_conditionalExpression = 36, 
		RULE_logicalOrExpression = 37, RULE_logicalAndExpression = 38, RULE_equalityExpression = 39, 
		RULE_comparisonExpression = 40, RULE_additiveExpression = 41, RULE_multiplicativeExpression = 42, 
		RULE_unaryExpression = 43, RULE_postfixExpression = 44, RULE_postfixOperation = 45, 
		RULE_primaryExpression = 46, RULE_arrayCreation = 47, RULE_arrayCreationDimensions = 48, 
		RULE_literal = 49, RULE_argumentList = 50, RULE_arrayInitializer = 51, 
		RULE_arrayInitializerElementList = 52, RULE_arrayInitializerElement = 53, 
		RULE_arrayDimensions = 54, RULE_type = 55, RULE_primitiveType = 56, RULE_referenceType = 57;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "classDeclaration", "classMember", "fieldDeclaration", "variableDeclaratorList", 
			"variableDeclarator", "constructorDeclaration", "methodDeclaration", 
			"parameterList", "parameter", "block", "statement", "variableDeclarationStatement", 
			"initializer", "statementOrBlock", "ifStatement", "switchStatement", 
			"switchCase", "defaultCase", "forStatement", "forInitializer", "variableDeclarationStatementNoSemi", 
			"forUpdate", "expressionList", "whileStatement", "doWhileStatement", 
			"returnStatement", "breakStatement", "continueStatement", "printStatement", 
			"printlnStatement", "readlnStatement", "expressionStatement", "expression", 
			"assignmentExpression", "assignmentOperator", "conditionalExpression", 
			"logicalOrExpression", "logicalAndExpression", "equalityExpression", 
			"comparisonExpression", "additiveExpression", "multiplicativeExpression", 
			"unaryExpression", "postfixExpression", "postfixOperation", "primaryExpression", 
			"arrayCreation", "arrayCreationDimensions", "literal", "argumentList", 
			"arrayInitializer", "arrayInitializerElementList", "arrayInitializerElement", 
			"arrayDimensions", "type", "primitiveType", "referenceType"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'public'", "'class'", "'void'", "'return'", "'if'", "'else'", 
			"'switch'", "'case'", "'default'", "'for'", "'while'", "'do'", "'break'", 
			"'continue'", "'print'", "'println'", "'readln'", "'new'", "'null'", 
			"'true'", "'false'", "'int'", "'double'", "'char'", "'boolean'", "'String'", 
			"'+'", "'-'", "'*'", "'/'", "'%'", "'++'", "'--'", "'&&'", "'||'", "'!'", 
			"'=='", "'!='", "'<'", "'>'", "'<='", "'>='", "'='", "'+='", "'-='", 
			"'*='", "'?'", "'('", "')'", "'{'", "'}'", "'['", "']'", "'.'", "','", 
			"':'", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PUBLIC", "CLASS", "VOID", "RETURN", "IF", "ELSE", "SWITCH", "CASE", 
			"DEFAULT", "FOR", "WHILE", "DO", "BREAK", "CONTINUE", "PRINT", "PRINTLN", 
			"READLN", "NEW", "NULL", "TRUE", "FALSE", "INT", "DOUBLE", "CHAR", "BOOLEAN", 
			"STRING", "PLUS", "MINUS", "MULT", "DIV", "MOD", "INCREMENT", "DECREMENT", 
			"AND", "OR", "NOT", "EQUAL", "NOT_EQUAL", "LESS", "GREATER", "LESS_EQUAL", 
			"GREATER_EQUAL", "ASSIGN", "PLUS_ASSIGN", "MINUS_ASSIGN", "MULT_ASSIGN", 
			"QUESTION", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", 
			"DOT", "COMMA", "COLON", "SEMI", "FLOAT_LITERAL", "INTEGER_LITERAL", 
			"CHAR_LITERAL", "STRING_LITERAL", "ID", "LINE_COMMENT", "BLOCK_COMMENT", 
			"WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Z.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ZParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public TerminalNode EOF() { return getToken(ZParser.EOF, 0); }
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			classDeclaration();
			setState(117);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassDeclarationContext extends ParserRuleContext {
		public TerminalNode PUBLIC() { return getToken(ZParser.PUBLIC, 0); }
		public TerminalNode CLASS() { return getToken(ZParser.CLASS, 0); }
		public TerminalNode ID() { return getToken(ZParser.ID, 0); }
		public TerminalNode LBRACE() { return getToken(ZParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(ZParser.RBRACE, 0); }
		public List<ClassMemberContext> classMember() {
			return getRuleContexts(ClassMemberContext.class);
		}
		public ClassMemberContext classMember(int i) {
			return getRuleContext(ClassMemberContext.class,i);
		}
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitClassDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitClassDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_classDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(PUBLIC);
			setState(120);
			match(CLASS);
			setState(121);
			match(ID);
			setState(122);
			match(LBRACE);
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4611686018557411338L) != 0)) {
				{
				{
				setState(123);
				classMember();
				}
				}
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(129);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassMemberContext extends ParserRuleContext {
		public ClassMemberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classMember; }
	 
		public ClassMemberContext() { }
		public void copyFrom(ClassMemberContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ConstructorMemberContext extends ClassMemberContext {
		public ConstructorDeclarationContext constructorDeclaration() {
			return getRuleContext(ConstructorDeclarationContext.class,0);
		}
		public ConstructorMemberContext(ClassMemberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterConstructorMember(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitConstructorMember(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitConstructorMember(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FieldMemberContext extends ClassMemberContext {
		public FieldDeclarationContext fieldDeclaration() {
			return getRuleContext(FieldDeclarationContext.class,0);
		}
		public FieldMemberContext(ClassMemberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterFieldMember(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitFieldMember(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitFieldMember(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MethodMemberContext extends ClassMemberContext {
		public MethodDeclarationContext methodDeclaration() {
			return getRuleContext(MethodDeclarationContext.class,0);
		}
		public MethodMemberContext(ClassMemberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterMethodMember(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitMethodMember(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitMethodMember(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassMemberContext classMember() throws RecognitionException {
		ClassMemberContext _localctx = new ClassMemberContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_classMember);
		try {
			setState(134);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				_localctx = new FieldMemberContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(131);
				fieldDeclaration();
				}
				break;
			case 2:
				_localctx = new ConstructorMemberContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(132);
				constructorDeclaration();
				}
				break;
			case 3:
				_localctx = new MethodMemberContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(133);
				methodDeclaration();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FieldDeclarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VariableDeclaratorListContext variableDeclaratorList() {
			return getRuleContext(VariableDeclaratorListContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(ZParser.SEMI, 0); }
		public TerminalNode PUBLIC() { return getToken(ZParser.PUBLIC, 0); }
		public FieldDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterFieldDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitFieldDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitFieldDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldDeclarationContext fieldDeclaration() throws RecognitionException {
		FieldDeclarationContext _localctx = new FieldDeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_fieldDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PUBLIC) {
				{
				setState(136);
				match(PUBLIC);
				}
			}

			setState(139);
			type();
			setState(140);
			variableDeclaratorList();
			setState(141);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableDeclaratorListContext extends ParserRuleContext {
		public List<VariableDeclaratorContext> variableDeclarator() {
			return getRuleContexts(VariableDeclaratorContext.class);
		}
		public VariableDeclaratorContext variableDeclarator(int i) {
			return getRuleContext(VariableDeclaratorContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ZParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ZParser.COMMA, i);
		}
		public VariableDeclaratorListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaratorList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterVariableDeclaratorList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitVariableDeclaratorList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitVariableDeclaratorList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclaratorListContext variableDeclaratorList() throws RecognitionException {
		VariableDeclaratorListContext _localctx = new VariableDeclaratorListContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_variableDeclaratorList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			variableDeclarator();
			setState(148);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(144);
				match(COMMA);
				setState(145);
				variableDeclarator();
				}
				}
				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableDeclaratorContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ZParser.ID, 0); }
		public InitializerContext initializer() {
			return getRuleContext(InitializerContext.class,0);
		}
		public VariableDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterVariableDeclarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitVariableDeclarator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitVariableDeclarator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclaratorContext variableDeclarator() throws RecognitionException {
		VariableDeclaratorContext _localctx = new VariableDeclaratorContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_variableDeclarator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(ID);
			setState(153);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(152);
				initializer();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConstructorDeclarationContext extends ParserRuleContext {
		public TerminalNode PUBLIC() { return getToken(ZParser.PUBLIC, 0); }
		public TerminalNode ID() { return getToken(ZParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(ZParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ZParser.RPAREN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public ConstructorDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructorDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterConstructorDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitConstructorDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitConstructorDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstructorDeclarationContext constructorDeclaration() throws RecognitionException {
		ConstructorDeclarationContext _localctx = new ConstructorDeclarationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_constructorDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			match(PUBLIC);
			setState(156);
			match(ID);
			setState(157);
			match(LPAREN);
			setState(159);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4611686018557411328L) != 0)) {
				{
				setState(158);
				parameterList();
				}
			}

			setState(161);
			match(RPAREN);
			setState(162);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MethodDeclarationContext extends ParserRuleContext {
		public MethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodDeclaration; }
	 
		public MethodDeclarationContext() { }
		public void copyFrom(MethodDeclarationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TypedMethodDeclarationContext extends MethodDeclarationContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(ZParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(ZParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ZParser.RPAREN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode PUBLIC() { return getToken(ZParser.PUBLIC, 0); }
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public TypedMethodDeclarationContext(MethodDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterTypedMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitTypedMethodDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitTypedMethodDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VoidMethodDeclarationContext extends MethodDeclarationContext {
		public TerminalNode VOID() { return getToken(ZParser.VOID, 0); }
		public TerminalNode ID() { return getToken(ZParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(ZParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ZParser.RPAREN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode PUBLIC() { return getToken(ZParser.PUBLIC, 0); }
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public VoidMethodDeclarationContext(MethodDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterVoidMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitVoidMethodDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitVoidMethodDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodDeclarationContext methodDeclaration() throws RecognitionException {
		MethodDeclarationContext _localctx = new MethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_methodDeclaration);
		int _la;
		try {
			setState(187);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				_localctx = new TypedMethodDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(165);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PUBLIC) {
					{
					setState(164);
					match(PUBLIC);
					}
				}

				setState(167);
				type();
				setState(168);
				match(ID);
				setState(169);
				match(LPAREN);
				setState(171);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4611686018557411328L) != 0)) {
					{
					setState(170);
					parameterList();
					}
				}

				setState(173);
				match(RPAREN);
				setState(174);
				block();
				}
				break;
			case 2:
				_localctx = new VoidMethodDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PUBLIC) {
					{
					setState(176);
					match(PUBLIC);
					}
				}

				setState(179);
				match(VOID);
				setState(180);
				match(ID);
				setState(181);
				match(LPAREN);
				setState(183);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4611686018557411328L) != 0)) {
					{
					setState(182);
					parameterList();
					}
				}

				setState(185);
				match(RPAREN);
				setState(186);
				block();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParameterListContext extends ParserRuleContext {
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ZParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ZParser.COMMA, i);
		}
		public ParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitParameterList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_parameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			parameter();
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(190);
				match(COMMA);
				setState(191);
				parameter();
				}
				}
				setState(196);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParameterContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(ZParser.ID, 0); }
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			type();
			setState(198);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(ZParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(ZParser.RBRACE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			match(LBRACE);
			setState(204);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8935423204936121520L) != 0)) {
				{
				{
				setState(201);
				statement();
				}
				}
				setState(206);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(207);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public VariableDeclarationStatementContext variableDeclarationStatement() {
			return getRuleContext(VariableDeclarationStatementContext.class,0);
		}
		public ExpressionStatementContext expressionStatement() {
			return getRuleContext(ExpressionStatementContext.class,0);
		}
		public PrintStatementContext printStatement() {
			return getRuleContext(PrintStatementContext.class,0);
		}
		public PrintlnStatementContext printlnStatement() {
			return getRuleContext(PrintlnStatementContext.class,0);
		}
		public ReadlnStatementContext readlnStatement() {
			return getRuleContext(ReadlnStatementContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public SwitchStatementContext switchStatement() {
			return getRuleContext(SwitchStatementContext.class,0);
		}
		public ForStatementContext forStatement() {
			return getRuleContext(ForStatementContext.class,0);
		}
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public DoWhileStatementContext doWhileStatement() {
			return getRuleContext(DoWhileStatementContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public BreakStatementContext breakStatement() {
			return getRuleContext(BreakStatementContext.class,0);
		}
		public ContinueStatementContext continueStatement() {
			return getRuleContext(ContinueStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_statement);
		try {
			setState(222);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(209);
				variableDeclarationStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(210);
				expressionStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(211);
				printStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(212);
				printlnStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(213);
				readlnStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(214);
				ifStatement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(215);
				switchStatement();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(216);
				forStatement();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(217);
				whileStatement();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(218);
				doWhileStatement();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(219);
				returnStatement();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(220);
				breakStatement();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(221);
				continueStatement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableDeclarationStatementContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VariableDeclaratorListContext variableDeclaratorList() {
			return getRuleContext(VariableDeclaratorListContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(ZParser.SEMI, 0); }
		public VariableDeclarationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclarationStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterVariableDeclarationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitVariableDeclarationStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitVariableDeclarationStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationStatementContext variableDeclarationStatement() throws RecognitionException {
		VariableDeclarationStatementContext _localctx = new VariableDeclarationStatementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_variableDeclarationStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			type();
			setState(225);
			variableDeclaratorList();
			setState(226);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InitializerContext extends ParserRuleContext {
		public InitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initializer; }
	 
		public InitializerContext() { }
		public void copyFrom(InitializerContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionInitializerValueContext extends InitializerContext {
		public TerminalNode ASSIGN() { return getToken(ZParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionInitializerValueContext(InitializerContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterExpressionInitializerValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitExpressionInitializerValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitExpressionInitializerValue(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArrayInitializerValueContext extends InitializerContext {
		public TerminalNode ASSIGN() { return getToken(ZParser.ASSIGN, 0); }
		public ArrayInitializerContext arrayInitializer() {
			return getRuleContext(ArrayInitializerContext.class,0);
		}
		public ArrayInitializerValueContext(InitializerContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterArrayInitializerValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitArrayInitializerValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitArrayInitializerValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitializerContext initializer() throws RecognitionException {
		InitializerContext _localctx = new InitializerContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_initializer);
		try {
			setState(232);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				_localctx = new ExpressionInitializerValueContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(228);
				match(ASSIGN);
				setState(229);
				expression();
				}
				break;
			case 2:
				_localctx = new ArrayInitializerValueContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(230);
				match(ASSIGN);
				setState(231);
				arrayInitializer();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementOrBlockContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public StatementOrBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementOrBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterStatementOrBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitStatementOrBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitStatementOrBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementOrBlockContext statementOrBlock() throws RecognitionException {
		StatementOrBlockContext _localctx = new StatementOrBlockContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_statementOrBlock);
		try {
			setState(236);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RETURN:
			case IF:
			case SWITCH:
			case FOR:
			case WHILE:
			case DO:
			case BREAK:
			case CONTINUE:
			case PRINT:
			case PRINTLN:
			case READLN:
			case NEW:
			case NULL:
			case TRUE:
			case FALSE:
			case INT:
			case DOUBLE:
			case CHAR:
			case BOOLEAN:
			case STRING:
			case PLUS:
			case MINUS:
			case NOT:
			case LPAREN:
			case FLOAT_LITERAL:
			case INTEGER_LITERAL:
			case CHAR_LITERAL:
			case STRING_LITERAL:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(234);
				statement();
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(235);
				block();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfStatementContext extends ParserRuleContext {
		public List<TerminalNode> IF() { return getTokens(ZParser.IF); }
		public TerminalNode IF(int i) {
			return getToken(ZParser.IF, i);
		}
		public List<TerminalNode> LPAREN() { return getTokens(ZParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(ZParser.LPAREN, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(ZParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(ZParser.RPAREN, i);
		}
		public List<StatementOrBlockContext> statementOrBlock() {
			return getRuleContexts(StatementOrBlockContext.class);
		}
		public StatementOrBlockContext statementOrBlock(int i) {
			return getRuleContext(StatementOrBlockContext.class,i);
		}
		public List<TerminalNode> ELSE() { return getTokens(ZParser.ELSE); }
		public TerminalNode ELSE(int i) {
			return getToken(ZParser.ELSE, i);
		}
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_ifStatement);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			match(IF);
			setState(239);
			match(LPAREN);
			setState(240);
			expression();
			setState(241);
			match(RPAREN);
			setState(242);
			statementOrBlock();
			setState(252);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(243);
					match(ELSE);
					setState(244);
					match(IF);
					setState(245);
					match(LPAREN);
					setState(246);
					expression();
					setState(247);
					match(RPAREN);
					setState(248);
					statementOrBlock();
					}
					} 
				}
				setState(254);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			setState(257);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(255);
				match(ELSE);
				setState(256);
				statementOrBlock();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SwitchStatementContext extends ParserRuleContext {
		public TerminalNode SWITCH() { return getToken(ZParser.SWITCH, 0); }
		public TerminalNode LPAREN() { return getToken(ZParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ZParser.RPAREN, 0); }
		public TerminalNode LBRACE() { return getToken(ZParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(ZParser.RBRACE, 0); }
		public List<SwitchCaseContext> switchCase() {
			return getRuleContexts(SwitchCaseContext.class);
		}
		public SwitchCaseContext switchCase(int i) {
			return getRuleContext(SwitchCaseContext.class,i);
		}
		public DefaultCaseContext defaultCase() {
			return getRuleContext(DefaultCaseContext.class,0);
		}
		public SwitchStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterSwitchStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitSwitchStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitSwitchStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SwitchStatementContext switchStatement() throws RecognitionException {
		SwitchStatementContext _localctx = new SwitchStatementContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_switchStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			match(SWITCH);
			setState(260);
			match(LPAREN);
			setState(261);
			expression();
			setState(262);
			match(RPAREN);
			setState(263);
			match(LBRACE);
			setState(267);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CASE) {
				{
				{
				setState(264);
				switchCase();
				}
				}
				setState(269);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(271);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DEFAULT) {
				{
				setState(270);
				defaultCase();
				}
			}

			setState(273);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SwitchCaseContext extends ParserRuleContext {
		public TerminalNode CASE() { return getToken(ZParser.CASE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode COLON() { return getToken(ZParser.COLON, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public SwitchCaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchCase; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterSwitchCase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitSwitchCase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitSwitchCase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SwitchCaseContext switchCase() throws RecognitionException {
		SwitchCaseContext _localctx = new SwitchCaseContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_switchCase);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			match(CASE);
			setState(276);
			expression();
			setState(277);
			match(COLON);
			setState(281);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8935423204936121520L) != 0)) {
				{
				{
				setState(278);
				statement();
				}
				}
				setState(283);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefaultCaseContext extends ParserRuleContext {
		public TerminalNode DEFAULT() { return getToken(ZParser.DEFAULT, 0); }
		public TerminalNode COLON() { return getToken(ZParser.COLON, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public DefaultCaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultCase; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterDefaultCase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitDefaultCase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitDefaultCase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefaultCaseContext defaultCase() throws RecognitionException {
		DefaultCaseContext _localctx = new DefaultCaseContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_defaultCase);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(284);
			match(DEFAULT);
			setState(285);
			match(COLON);
			setState(289);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8935423204936121520L) != 0)) {
				{
				{
				setState(286);
				statement();
				}
				}
				setState(291);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForStatementContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(ZParser.FOR, 0); }
		public TerminalNode LPAREN() { return getToken(ZParser.LPAREN, 0); }
		public List<TerminalNode> SEMI() { return getTokens(ZParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(ZParser.SEMI, i);
		}
		public TerminalNode RPAREN() { return getToken(ZParser.RPAREN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ForInitializerContext forInitializer() {
			return getRuleContext(ForInitializerContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ForUpdateContext forUpdate() {
			return getRuleContext(ForUpdateContext.class,0);
		}
		public ForStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterForStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitForStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitForStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStatementContext forStatement() throws RecognitionException {
		ForStatementContext _localctx = new ForStatementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_forStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(292);
			match(FOR);
			setState(293);
			match(LPAREN);
			setState(295);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8935423204935991296L) != 0)) {
				{
				setState(294);
				forInitializer();
				}
			}

			setState(297);
			match(SEMI);
			setState(299);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8935423204805967872L) != 0)) {
				{
				setState(298);
				expression();
				}
			}

			setState(301);
			match(SEMI);
			setState(303);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8935423204805967872L) != 0)) {
				{
				setState(302);
				forUpdate();
				}
			}

			setState(305);
			match(RPAREN);
			setState(306);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForInitializerContext extends ParserRuleContext {
		public VariableDeclarationStatementNoSemiContext variableDeclarationStatementNoSemi() {
			return getRuleContext(VariableDeclarationStatementNoSemiContext.class,0);
		}
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ForInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterForInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitForInitializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitForInitializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForInitializerContext forInitializer() throws RecognitionException {
		ForInitializerContext _localctx = new ForInitializerContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_forInitializer);
		try {
			setState(310);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(308);
				variableDeclarationStatementNoSemi();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(309);
				expressionList();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableDeclarationStatementNoSemiContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VariableDeclaratorListContext variableDeclaratorList() {
			return getRuleContext(VariableDeclaratorListContext.class,0);
		}
		public VariableDeclarationStatementNoSemiContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclarationStatementNoSemi; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterVariableDeclarationStatementNoSemi(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitVariableDeclarationStatementNoSemi(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitVariableDeclarationStatementNoSemi(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationStatementNoSemiContext variableDeclarationStatementNoSemi() throws RecognitionException {
		VariableDeclarationStatementNoSemiContext _localctx = new VariableDeclarationStatementNoSemiContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_variableDeclarationStatementNoSemi);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			type();
			setState(313);
			variableDeclaratorList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForUpdateContext extends ParserRuleContext {
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ForUpdateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forUpdate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterForUpdate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitForUpdate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitForUpdate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForUpdateContext forUpdate() throws RecognitionException {
		ForUpdateContext _localctx = new ForUpdateContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_forUpdate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(315);
			expressionList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ZParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ZParser.COMMA, i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitExpressionList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitExpressionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(317);
			expression();
			setState(322);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(318);
				match(COMMA);
				setState(319);
				expression();
				}
				}
				setState(324);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhileStatementContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(ZParser.WHILE, 0); }
		public TerminalNode LPAREN() { return getToken(ZParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ZParser.RPAREN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitWhileStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(325);
			match(WHILE);
			setState(326);
			match(LPAREN);
			setState(327);
			expression();
			setState(328);
			match(RPAREN);
			setState(329);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DoWhileStatementContext extends ParserRuleContext {
		public TerminalNode DO() { return getToken(ZParser.DO, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(ZParser.WHILE, 0); }
		public TerminalNode LPAREN() { return getToken(ZParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ZParser.RPAREN, 0); }
		public TerminalNode SEMI() { return getToken(ZParser.SEMI, 0); }
		public DoWhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doWhileStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterDoWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitDoWhileStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitDoWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoWhileStatementContext doWhileStatement() throws RecognitionException {
		DoWhileStatementContext _localctx = new DoWhileStatementContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_doWhileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(331);
			match(DO);
			setState(332);
			block();
			setState(333);
			match(WHILE);
			setState(334);
			match(LPAREN);
			setState(335);
			expression();
			setState(336);
			match(RPAREN);
			setState(337);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnStatementContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(ZParser.RETURN, 0); }
		public TerminalNode SEMI() { return getToken(ZParser.SEMI, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitReturnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_returnStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(339);
			match(RETURN);
			setState(341);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8935423204805967872L) != 0)) {
				{
				setState(340);
				expression();
				}
			}

			setState(343);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BreakStatementContext extends ParserRuleContext {
		public TerminalNode BREAK() { return getToken(ZParser.BREAK, 0); }
		public TerminalNode SEMI() { return getToken(ZParser.SEMI, 0); }
		public BreakStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterBreakStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitBreakStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitBreakStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreakStatementContext breakStatement() throws RecognitionException {
		BreakStatementContext _localctx = new BreakStatementContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_breakStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345);
			match(BREAK);
			setState(346);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ContinueStatementContext extends ParserRuleContext {
		public TerminalNode CONTINUE() { return getToken(ZParser.CONTINUE, 0); }
		public TerminalNode SEMI() { return getToken(ZParser.SEMI, 0); }
		public ContinueStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterContinueStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitContinueStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitContinueStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContinueStatementContext continueStatement() throws RecognitionException {
		ContinueStatementContext _localctx = new ContinueStatementContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_continueStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(348);
			match(CONTINUE);
			setState(349);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrintStatementContext extends ParserRuleContext {
		public TerminalNode PRINT() { return getToken(ZParser.PRINT, 0); }
		public TerminalNode LPAREN() { return getToken(ZParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ZParser.RPAREN, 0); }
		public TerminalNode SEMI() { return getToken(ZParser.SEMI, 0); }
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public PrintStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_printStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterPrintStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitPrintStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitPrintStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintStatementContext printStatement() throws RecognitionException {
		PrintStatementContext _localctx = new PrintStatementContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_printStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(351);
			match(PRINT);
			setState(352);
			match(LPAREN);
			setState(354);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8935423204805967872L) != 0)) {
				{
				setState(353);
				argumentList();
				}
			}

			setState(356);
			match(RPAREN);
			setState(357);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrintlnStatementContext extends ParserRuleContext {
		public TerminalNode PRINTLN() { return getToken(ZParser.PRINTLN, 0); }
		public TerminalNode LPAREN() { return getToken(ZParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ZParser.RPAREN, 0); }
		public TerminalNode SEMI() { return getToken(ZParser.SEMI, 0); }
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public PrintlnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_printlnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterPrintlnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitPrintlnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitPrintlnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintlnStatementContext printlnStatement() throws RecognitionException {
		PrintlnStatementContext _localctx = new PrintlnStatementContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_printlnStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(359);
			match(PRINTLN);
			setState(360);
			match(LPAREN);
			setState(362);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8935423204805967872L) != 0)) {
				{
				setState(361);
				argumentList();
				}
			}

			setState(364);
			match(RPAREN);
			setState(365);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReadlnStatementContext extends ParserRuleContext {
		public TerminalNode READLN() { return getToken(ZParser.READLN, 0); }
		public TerminalNode LPAREN() { return getToken(ZParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ZParser.RPAREN, 0); }
		public TerminalNode SEMI() { return getToken(ZParser.SEMI, 0); }
		public ReadlnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_readlnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterReadlnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitReadlnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitReadlnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReadlnStatementContext readlnStatement() throws RecognitionException {
		ReadlnStatementContext _localctx = new ReadlnStatementContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_readlnStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(367);
			match(READLN);
			setState(368);
			match(LPAREN);
			setState(369);
			match(RPAREN);
			setState(370);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(ZParser.SEMI, 0); }
		public ExpressionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterExpressionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitExpressionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitExpressionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionStatementContext expressionStatement() throws RecognitionException {
		ExpressionStatementContext _localctx = new ExpressionStatementContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_expressionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(372);
			expression();
			setState(373);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(375);
			assignmentExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentExpressionContext extends ParserRuleContext {
		public AssignmentExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentExpression; }
	 
		public AssignmentExpressionContext() { }
		public void copyFrom(AssignmentExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentExpressionValueContext extends AssignmentExpressionContext {
		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class,0);
		}
		public AssignmentOperatorContext assignmentOperator() {
			return getRuleContext(AssignmentOperatorContext.class,0);
		}
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public AssignmentExpressionValueContext(AssignmentExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterAssignmentExpressionValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitAssignmentExpressionValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitAssignmentExpressionValue(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ToConditionalExpressionContext extends AssignmentExpressionContext {
		public ConditionalExpressionContext conditionalExpression() {
			return getRuleContext(ConditionalExpressionContext.class,0);
		}
		public ToConditionalExpressionContext(AssignmentExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterToConditionalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitToConditionalExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitToConditionalExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentExpressionContext assignmentExpression() throws RecognitionException {
		AssignmentExpressionContext _localctx = new AssignmentExpressionContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_assignmentExpression);
		try {
			setState(382);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				_localctx = new ToConditionalExpressionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(377);
				conditionalExpression();
				}
				break;
			case 2:
				_localctx = new AssignmentExpressionValueContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(378);
				postfixExpression();
				setState(379);
				assignmentOperator();
				setState(380);
				assignmentExpression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentOperatorContext extends ParserRuleContext {
		public TerminalNode ASSIGN() { return getToken(ZParser.ASSIGN, 0); }
		public TerminalNode PLUS_ASSIGN() { return getToken(ZParser.PLUS_ASSIGN, 0); }
		public TerminalNode MINUS_ASSIGN() { return getToken(ZParser.MINUS_ASSIGN, 0); }
		public TerminalNode MULT_ASSIGN() { return getToken(ZParser.MULT_ASSIGN, 0); }
		public AssignmentOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterAssignmentOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitAssignmentOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitAssignmentOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentOperatorContext assignmentOperator() throws RecognitionException {
		AssignmentOperatorContext _localctx = new AssignmentOperatorContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_assignmentOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(384);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 131941395333120L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConditionalExpressionContext extends ParserRuleContext {
		public ConditionalExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionalExpression; }
	 
		public ConditionalExpressionContext() { }
		public void copyFrom(ConditionalExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TernaryExpressionContext extends ConditionalExpressionContext {
		public LogicalOrExpressionContext logicalOrExpression() {
			return getRuleContext(LogicalOrExpressionContext.class,0);
		}
		public TerminalNode QUESTION() { return getToken(ZParser.QUESTION, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode COLON() { return getToken(ZParser.COLON, 0); }
		public TernaryExpressionContext(ConditionalExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterTernaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitTernaryExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitTernaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LogicalOrConditionalExpressionContext extends ConditionalExpressionContext {
		public LogicalOrExpressionContext logicalOrExpression() {
			return getRuleContext(LogicalOrExpressionContext.class,0);
		}
		public LogicalOrConditionalExpressionContext(ConditionalExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterLogicalOrConditionalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitLogicalOrConditionalExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitLogicalOrConditionalExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionalExpressionContext conditionalExpression() throws RecognitionException {
		ConditionalExpressionContext _localctx = new ConditionalExpressionContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_conditionalExpression);
		try {
			setState(393);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				_localctx = new LogicalOrConditionalExpressionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(386);
				logicalOrExpression(0);
				}
				break;
			case 2:
				_localctx = new TernaryExpressionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(387);
				logicalOrExpression(0);
				setState(388);
				match(QUESTION);
				setState(389);
				expression();
				setState(390);
				match(COLON);
				setState(391);
				expression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LogicalOrExpressionContext extends ParserRuleContext {
		public LogicalOrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalOrExpression; }
	 
		public LogicalOrExpressionContext() { }
		public void copyFrom(LogicalOrExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ToLogicalAndExprContext extends LogicalOrExpressionContext {
		public LogicalAndExpressionContext logicalAndExpression() {
			return getRuleContext(LogicalAndExpressionContext.class,0);
		}
		public ToLogicalAndExprContext(LogicalOrExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterToLogicalAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitToLogicalAndExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitToLogicalAndExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OrExprContext extends LogicalOrExpressionContext {
		public LogicalOrExpressionContext logicalOrExpression() {
			return getRuleContext(LogicalOrExpressionContext.class,0);
		}
		public TerminalNode OR() { return getToken(ZParser.OR, 0); }
		public LogicalAndExpressionContext logicalAndExpression() {
			return getRuleContext(LogicalAndExpressionContext.class,0);
		}
		public OrExprContext(LogicalOrExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitOrExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitOrExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalOrExpressionContext logicalOrExpression() throws RecognitionException {
		return logicalOrExpression(0);
	}

	private LogicalOrExpressionContext logicalOrExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		LogicalOrExpressionContext _localctx = new LogicalOrExpressionContext(_ctx, _parentState);
		LogicalOrExpressionContext _prevctx = _localctx;
		int _startState = 74;
		enterRecursionRule(_localctx, 74, RULE_logicalOrExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new ToLogicalAndExprContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(396);
			logicalAndExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(403);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new OrExprContext(new LogicalOrExpressionContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_logicalOrExpression);
					setState(398);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(399);
					match(OR);
					setState(400);
					logicalAndExpression(0);
					}
					} 
				}
				setState(405);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LogicalAndExpressionContext extends ParserRuleContext {
		public LogicalAndExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalAndExpression; }
	 
		public LogicalAndExpressionContext() { }
		public void copyFrom(LogicalAndExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AndExprContext extends LogicalAndExpressionContext {
		public LogicalAndExpressionContext logicalAndExpression() {
			return getRuleContext(LogicalAndExpressionContext.class,0);
		}
		public TerminalNode AND() { return getToken(ZParser.AND, 0); }
		public EqualityExpressionContext equalityExpression() {
			return getRuleContext(EqualityExpressionContext.class,0);
		}
		public AndExprContext(LogicalAndExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitAndExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitAndExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ToEqualityExprContext extends LogicalAndExpressionContext {
		public EqualityExpressionContext equalityExpression() {
			return getRuleContext(EqualityExpressionContext.class,0);
		}
		public ToEqualityExprContext(LogicalAndExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterToEqualityExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitToEqualityExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitToEqualityExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalAndExpressionContext logicalAndExpression() throws RecognitionException {
		return logicalAndExpression(0);
	}

	private LogicalAndExpressionContext logicalAndExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		LogicalAndExpressionContext _localctx = new LogicalAndExpressionContext(_ctx, _parentState);
		LogicalAndExpressionContext _prevctx = _localctx;
		int _startState = 76;
		enterRecursionRule(_localctx, 76, RULE_logicalAndExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new ToEqualityExprContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(407);
			equalityExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(414);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new AndExprContext(new LogicalAndExpressionContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_logicalAndExpression);
					setState(409);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(410);
					match(AND);
					setState(411);
					equalityExpression(0);
					}
					} 
				}
				setState(416);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EqualityExpressionContext extends ParserRuleContext {
		public EqualityExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equalityExpression; }
	 
		public EqualityExpressionContext() { }
		public void copyFrom(EqualityExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EqualExprContext extends EqualityExpressionContext {
		public EqualityExpressionContext equalityExpression() {
			return getRuleContext(EqualityExpressionContext.class,0);
		}
		public TerminalNode EQUAL() { return getToken(ZParser.EQUAL, 0); }
		public ComparisonExpressionContext comparisonExpression() {
			return getRuleContext(ComparisonExpressionContext.class,0);
		}
		public EqualExprContext(EqualityExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterEqualExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitEqualExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitEqualExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ToComparisonExprContext extends EqualityExpressionContext {
		public ComparisonExpressionContext comparisonExpression() {
			return getRuleContext(ComparisonExpressionContext.class,0);
		}
		public ToComparisonExprContext(EqualityExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterToComparisonExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitToComparisonExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitToComparisonExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NotEqualExprContext extends EqualityExpressionContext {
		public EqualityExpressionContext equalityExpression() {
			return getRuleContext(EqualityExpressionContext.class,0);
		}
		public TerminalNode NOT_EQUAL() { return getToken(ZParser.NOT_EQUAL, 0); }
		public ComparisonExpressionContext comparisonExpression() {
			return getRuleContext(ComparisonExpressionContext.class,0);
		}
		public NotEqualExprContext(EqualityExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterNotEqualExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitNotEqualExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitNotEqualExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqualityExpressionContext equalityExpression() throws RecognitionException {
		return equalityExpression(0);
	}

	private EqualityExpressionContext equalityExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		EqualityExpressionContext _localctx = new EqualityExpressionContext(_ctx, _parentState);
		EqualityExpressionContext _prevctx = _localctx;
		int _startState = 78;
		enterRecursionRule(_localctx, 78, RULE_equalityExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new ToComparisonExprContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(418);
			comparisonExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(428);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(426);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
					case 1:
						{
						_localctx = new EqualExprContext(new EqualityExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_equalityExpression);
						setState(420);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(421);
						match(EQUAL);
						setState(422);
						comparisonExpression(0);
						}
						break;
					case 2:
						{
						_localctx = new NotEqualExprContext(new EqualityExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_equalityExpression);
						setState(423);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(424);
						match(NOT_EQUAL);
						setState(425);
						comparisonExpression(0);
						}
						break;
					}
					} 
				}
				setState(430);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComparisonExpressionContext extends ParserRuleContext {
		public ComparisonExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonExpression; }
	 
		public ComparisonExpressionContext() { }
		public void copyFrom(ComparisonExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ToAdditiveExprContext extends ComparisonExpressionContext {
		public AdditiveExpressionContext additiveExpression() {
			return getRuleContext(AdditiveExpressionContext.class,0);
		}
		public ToAdditiveExprContext(ComparisonExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterToAdditiveExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitToAdditiveExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitToAdditiveExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GreaterExprContext extends ComparisonExpressionContext {
		public ComparisonExpressionContext comparisonExpression() {
			return getRuleContext(ComparisonExpressionContext.class,0);
		}
		public TerminalNode GREATER() { return getToken(ZParser.GREATER, 0); }
		public AdditiveExpressionContext additiveExpression() {
			return getRuleContext(AdditiveExpressionContext.class,0);
		}
		public GreaterExprContext(ComparisonExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterGreaterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitGreaterExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitGreaterExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GreaterEqualExprContext extends ComparisonExpressionContext {
		public ComparisonExpressionContext comparisonExpression() {
			return getRuleContext(ComparisonExpressionContext.class,0);
		}
		public TerminalNode GREATER_EQUAL() { return getToken(ZParser.GREATER_EQUAL, 0); }
		public AdditiveExpressionContext additiveExpression() {
			return getRuleContext(AdditiveExpressionContext.class,0);
		}
		public GreaterEqualExprContext(ComparisonExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterGreaterEqualExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitGreaterEqualExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitGreaterEqualExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LessExprContext extends ComparisonExpressionContext {
		public ComparisonExpressionContext comparisonExpression() {
			return getRuleContext(ComparisonExpressionContext.class,0);
		}
		public TerminalNode LESS() { return getToken(ZParser.LESS, 0); }
		public AdditiveExpressionContext additiveExpression() {
			return getRuleContext(AdditiveExpressionContext.class,0);
		}
		public LessExprContext(ComparisonExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterLessExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitLessExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitLessExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LessEqualExprContext extends ComparisonExpressionContext {
		public ComparisonExpressionContext comparisonExpression() {
			return getRuleContext(ComparisonExpressionContext.class,0);
		}
		public TerminalNode LESS_EQUAL() { return getToken(ZParser.LESS_EQUAL, 0); }
		public AdditiveExpressionContext additiveExpression() {
			return getRuleContext(AdditiveExpressionContext.class,0);
		}
		public LessEqualExprContext(ComparisonExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterLessEqualExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitLessEqualExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitLessEqualExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonExpressionContext comparisonExpression() throws RecognitionException {
		return comparisonExpression(0);
	}

	private ComparisonExpressionContext comparisonExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ComparisonExpressionContext _localctx = new ComparisonExpressionContext(_ctx, _parentState);
		ComparisonExpressionContext _prevctx = _localctx;
		int _startState = 80;
		enterRecursionRule(_localctx, 80, RULE_comparisonExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new ToAdditiveExprContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(432);
			additiveExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(448);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(446);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
					case 1:
						{
						_localctx = new LessExprContext(new ComparisonExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_comparisonExpression);
						setState(434);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(435);
						match(LESS);
						setState(436);
						additiveExpression(0);
						}
						break;
					case 2:
						{
						_localctx = new GreaterExprContext(new ComparisonExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_comparisonExpression);
						setState(437);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(438);
						match(GREATER);
						setState(439);
						additiveExpression(0);
						}
						break;
					case 3:
						{
						_localctx = new LessEqualExprContext(new ComparisonExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_comparisonExpression);
						setState(440);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(441);
						match(LESS_EQUAL);
						setState(442);
						additiveExpression(0);
						}
						break;
					case 4:
						{
						_localctx = new GreaterEqualExprContext(new ComparisonExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_comparisonExpression);
						setState(443);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(444);
						match(GREATER_EQUAL);
						setState(445);
						additiveExpression(0);
						}
						break;
					}
					} 
				}
				setState(450);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AdditiveExpressionContext extends ParserRuleContext {
		public AdditiveExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpression; }
	 
		public AdditiveExpressionContext() { }
		public void copyFrom(AdditiveExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ToMultiplicativeExprContext extends AdditiveExpressionContext {
		public MultiplicativeExpressionContext multiplicativeExpression() {
			return getRuleContext(MultiplicativeExpressionContext.class,0);
		}
		public ToMultiplicativeExprContext(AdditiveExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterToMultiplicativeExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitToMultiplicativeExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitToMultiplicativeExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AdditionExprContext extends AdditiveExpressionContext {
		public AdditiveExpressionContext additiveExpression() {
			return getRuleContext(AdditiveExpressionContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(ZParser.PLUS, 0); }
		public MultiplicativeExpressionContext multiplicativeExpression() {
			return getRuleContext(MultiplicativeExpressionContext.class,0);
		}
		public AdditionExprContext(AdditiveExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterAdditionExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitAdditionExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitAdditionExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SubtractionExprContext extends AdditiveExpressionContext {
		public AdditiveExpressionContext additiveExpression() {
			return getRuleContext(AdditiveExpressionContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(ZParser.MINUS, 0); }
		public MultiplicativeExpressionContext multiplicativeExpression() {
			return getRuleContext(MultiplicativeExpressionContext.class,0);
		}
		public SubtractionExprContext(AdditiveExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterSubtractionExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitSubtractionExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitSubtractionExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditiveExpressionContext additiveExpression() throws RecognitionException {
		return additiveExpression(0);
	}

	private AdditiveExpressionContext additiveExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		AdditiveExpressionContext _localctx = new AdditiveExpressionContext(_ctx, _parentState);
		AdditiveExpressionContext _prevctx = _localctx;
		int _startState = 82;
		enterRecursionRule(_localctx, 82, RULE_additiveExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new ToMultiplicativeExprContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(452);
			multiplicativeExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(462);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(460);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
					case 1:
						{
						_localctx = new AdditionExprContext(new AdditiveExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_additiveExpression);
						setState(454);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(455);
						match(PLUS);
						setState(456);
						multiplicativeExpression(0);
						}
						break;
					case 2:
						{
						_localctx = new SubtractionExprContext(new AdditiveExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_additiveExpression);
						setState(457);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(458);
						match(MINUS);
						setState(459);
						multiplicativeExpression(0);
						}
						break;
					}
					} 
				}
				setState(464);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MultiplicativeExpressionContext extends ParserRuleContext {
		public MultiplicativeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpression; }
	 
		public MultiplicativeExpressionContext() { }
		public void copyFrom(MultiplicativeExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ModuloExprContext extends MultiplicativeExpressionContext {
		public MultiplicativeExpressionContext multiplicativeExpression() {
			return getRuleContext(MultiplicativeExpressionContext.class,0);
		}
		public TerminalNode MOD() { return getToken(ZParser.MOD, 0); }
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public ModuloExprContext(MultiplicativeExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterModuloExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitModuloExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitModuloExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ToUnaryExprContext extends MultiplicativeExpressionContext {
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public ToUnaryExprContext(MultiplicativeExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterToUnaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitToUnaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitToUnaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DivisionExprContext extends MultiplicativeExpressionContext {
		public MultiplicativeExpressionContext multiplicativeExpression() {
			return getRuleContext(MultiplicativeExpressionContext.class,0);
		}
		public TerminalNode DIV() { return getToken(ZParser.DIV, 0); }
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public DivisionExprContext(MultiplicativeExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterDivisionExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitDivisionExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitDivisionExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MultiplicationExprContext extends MultiplicativeExpressionContext {
		public MultiplicativeExpressionContext multiplicativeExpression() {
			return getRuleContext(MultiplicativeExpressionContext.class,0);
		}
		public TerminalNode MULT() { return getToken(ZParser.MULT, 0); }
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public MultiplicationExprContext(MultiplicativeExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterMultiplicationExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitMultiplicationExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitMultiplicationExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicativeExpressionContext multiplicativeExpression() throws RecognitionException {
		return multiplicativeExpression(0);
	}

	private MultiplicativeExpressionContext multiplicativeExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		MultiplicativeExpressionContext _localctx = new MultiplicativeExpressionContext(_ctx, _parentState);
		MultiplicativeExpressionContext _prevctx = _localctx;
		int _startState = 84;
		enterRecursionRule(_localctx, 84, RULE_multiplicativeExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new ToUnaryExprContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(466);
			unaryExpression();
			}
			_ctx.stop = _input.LT(-1);
			setState(479);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(477);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
					case 1:
						{
						_localctx = new MultiplicationExprContext(new MultiplicativeExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_multiplicativeExpression);
						setState(468);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(469);
						match(MULT);
						setState(470);
						unaryExpression();
						}
						break;
					case 2:
						{
						_localctx = new DivisionExprContext(new MultiplicativeExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_multiplicativeExpression);
						setState(471);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(472);
						match(DIV);
						setState(473);
						unaryExpression();
						}
						break;
					case 3:
						{
						_localctx = new ModuloExprContext(new MultiplicativeExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_multiplicativeExpression);
						setState(474);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(475);
						match(MOD);
						setState(476);
						unaryExpression();
						}
						break;
					}
					} 
				}
				setState(481);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnaryExpressionContext extends ParserRuleContext {
		public UnaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpression; }
	 
		public UnaryExpressionContext() { }
		public void copyFrom(UnaryExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ToPostfixExprContext extends UnaryExpressionContext {
		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class,0);
		}
		public ToPostfixExprContext(UnaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterToPostfixExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitToPostfixExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitToPostfixExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PositiveExprContext extends UnaryExpressionContext {
		public TerminalNode PLUS() { return getToken(ZParser.PLUS, 0); }
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public PositiveExprContext(UnaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterPositiveExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitPositiveExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitPositiveExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NotExprContext extends UnaryExpressionContext {
		public TerminalNode NOT() { return getToken(ZParser.NOT, 0); }
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public NotExprContext(UnaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterNotExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitNotExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitNotExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NegateExprContext extends UnaryExpressionContext {
		public TerminalNode MINUS() { return getToken(ZParser.MINUS, 0); }
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public NegateExprContext(UnaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterNegateExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitNegateExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitNegateExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryExpressionContext unaryExpression() throws RecognitionException {
		UnaryExpressionContext _localctx = new UnaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_unaryExpression);
		try {
			setState(489);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
				_localctx = new NotExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(482);
				match(NOT);
				setState(483);
				unaryExpression();
				}
				break;
			case MINUS:
				_localctx = new NegateExprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(484);
				match(MINUS);
				setState(485);
				unaryExpression();
				}
				break;
			case PLUS:
				_localctx = new PositiveExprContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(486);
				match(PLUS);
				setState(487);
				unaryExpression();
				}
				break;
			case READLN:
			case NEW:
			case NULL:
			case TRUE:
			case FALSE:
			case LPAREN:
			case FLOAT_LITERAL:
			case INTEGER_LITERAL:
			case CHAR_LITERAL:
			case STRING_LITERAL:
			case ID:
				_localctx = new ToPostfixExprContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(488);
				postfixExpression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PostfixExpressionContext extends ParserRuleContext {
		public PrimaryExpressionContext primaryExpression() {
			return getRuleContext(PrimaryExpressionContext.class,0);
		}
		public List<PostfixOperationContext> postfixOperation() {
			return getRuleContexts(PostfixOperationContext.class);
		}
		public PostfixOperationContext postfixOperation(int i) {
			return getRuleContext(PostfixOperationContext.class,i);
		}
		public PostfixExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfixExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterPostfixExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitPostfixExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitPostfixExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PostfixExpressionContext postfixExpression() throws RecognitionException {
		PostfixExpressionContext _localctx = new PostfixExpressionContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_postfixExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(491);
			primaryExpression();
			setState(495);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(492);
					postfixOperation();
					}
					} 
				}
				setState(497);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PostfixOperationContext extends ParserRuleContext {
		public PostfixOperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfixOperation; }
	 
		public PostfixOperationContext() { }
		public void copyFrom(PostfixOperationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArrayAccessOpContext extends PostfixOperationContext {
		public TerminalNode LBRACK() { return getToken(ZParser.LBRACK, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RBRACK() { return getToken(ZParser.RBRACK, 0); }
		public ArrayAccessOpContext(PostfixOperationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterArrayAccessOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitArrayAccessOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitArrayAccessOp(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PostIncrementOpContext extends PostfixOperationContext {
		public TerminalNode INCREMENT() { return getToken(ZParser.INCREMENT, 0); }
		public PostIncrementOpContext(PostfixOperationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterPostIncrementOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitPostIncrementOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitPostIncrementOp(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PostDecrementOpContext extends PostfixOperationContext {
		public TerminalNode DECREMENT() { return getToken(ZParser.DECREMENT, 0); }
		public PostDecrementOpContext(PostfixOperationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterPostDecrementOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitPostDecrementOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitPostDecrementOp(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FunctionCallOpContext extends PostfixOperationContext {
		public TerminalNode LPAREN() { return getToken(ZParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ZParser.RPAREN, 0); }
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public FunctionCallOpContext(PostfixOperationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterFunctionCallOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitFunctionCallOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitFunctionCallOp(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MemberAccessOpContext extends PostfixOperationContext {
		public TerminalNode DOT() { return getToken(ZParser.DOT, 0); }
		public TerminalNode ID() { return getToken(ZParser.ID, 0); }
		public MemberAccessOpContext(PostfixOperationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterMemberAccessOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitMemberAccessOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitMemberAccessOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PostfixOperationContext postfixOperation() throws RecognitionException {
		PostfixOperationContext _localctx = new PostfixOperationContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_postfixOperation);
		int _la;
		try {
			setState(511);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACK:
				_localctx = new ArrayAccessOpContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(498);
				match(LBRACK);
				setState(499);
				expression();
				setState(500);
				match(RBRACK);
				}
				break;
			case DOT:
				_localctx = new MemberAccessOpContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(502);
				match(DOT);
				setState(503);
				match(ID);
				}
				break;
			case LPAREN:
				_localctx = new FunctionCallOpContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(504);
				match(LPAREN);
				setState(506);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8935423204805967872L) != 0)) {
					{
					setState(505);
					argumentList();
					}
				}

				setState(508);
				match(RPAREN);
				}
				break;
			case INCREMENT:
				_localctx = new PostIncrementOpContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(509);
				match(INCREMENT);
				}
				break;
			case DECREMENT:
				_localctx = new PostDecrementOpContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(510);
				match(DECREMENT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryExpressionContext extends ParserRuleContext {
		public PrimaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryExpression; }
	 
		public PrimaryExpressionContext() { }
		public void copyFrom(PrimaryExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ObjCreationExprContext extends PrimaryExpressionContext {
		public TerminalNode NEW() { return getToken(ZParser.NEW, 0); }
		public TerminalNode ID() { return getToken(ZParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(ZParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ZParser.RPAREN, 0); }
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public ObjCreationExprContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterObjCreationExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitObjCreationExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitObjCreationExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ReadExprContext extends PrimaryExpressionContext {
		public TerminalNode READLN() { return getToken(ZParser.READLN, 0); }
		public TerminalNode LPAREN() { return getToken(ZParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ZParser.RPAREN, 0); }
		public ReadExprContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterReadExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitReadExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitReadExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LiteralExprContext extends PrimaryExpressionContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public LiteralExprContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterLiteralExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitLiteralExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitLiteralExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VariableExprContext extends PrimaryExpressionContext {
		public TerminalNode ID() { return getToken(ZParser.ID, 0); }
		public VariableExprContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterVariableExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitVariableExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitVariableExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenthesizedExprContext extends PrimaryExpressionContext {
		public TerminalNode LPAREN() { return getToken(ZParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ZParser.RPAREN, 0); }
		public ParenthesizedExprContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterParenthesizedExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitParenthesizedExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitParenthesizedExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArrayCreationExprContext extends PrimaryExpressionContext {
		public ArrayCreationContext arrayCreation() {
			return getRuleContext(ArrayCreationContext.class,0);
		}
		public ArrayCreationExprContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterArrayCreationExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitArrayCreationExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitArrayCreationExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NullExprContext extends PrimaryExpressionContext {
		public TerminalNode NULL() { return getToken(ZParser.NULL, 0); }
		public NullExprContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterNullExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitNullExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitNullExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryExpressionContext primaryExpression() throws RecognitionException {
		PrimaryExpressionContext _localctx = new PrimaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_primaryExpression);
		int _la;
		try {
			setState(531);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
			case 1:
				_localctx = new LiteralExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(513);
				literal();
				}
				break;
			case 2:
				_localctx = new VariableExprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(514);
				match(ID);
				}
				break;
			case 3:
				_localctx = new ObjCreationExprContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(515);
				match(NEW);
				setState(516);
				match(ID);
				setState(517);
				match(LPAREN);
				setState(519);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8935423204805967872L) != 0)) {
					{
					setState(518);
					argumentList();
					}
				}

				setState(521);
				match(RPAREN);
				}
				break;
			case 4:
				_localctx = new ArrayCreationExprContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(522);
				arrayCreation();
				}
				break;
			case 5:
				_localctx = new NullExprContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(523);
				match(NULL);
				}
				break;
			case 6:
				_localctx = new ReadExprContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(524);
				match(READLN);
				setState(525);
				match(LPAREN);
				setState(526);
				match(RPAREN);
				}
				break;
			case 7:
				_localctx = new ParenthesizedExprContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(527);
				match(LPAREN);
				setState(528);
				expression();
				setState(529);
				match(RPAREN);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayCreationContext extends ParserRuleContext {
		public TerminalNode NEW() { return getToken(ZParser.NEW, 0); }
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public ArrayCreationDimensionsContext arrayCreationDimensions() {
			return getRuleContext(ArrayCreationDimensionsContext.class,0);
		}
		public TerminalNode ID() { return getToken(ZParser.ID, 0); }
		public ArrayCreationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayCreation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterArrayCreation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitArrayCreation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitArrayCreation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayCreationContext arrayCreation() throws RecognitionException {
		ArrayCreationContext _localctx = new ArrayCreationContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_arrayCreation);
		try {
			setState(540);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(533);
				match(NEW);
				setState(534);
				primitiveType();
				setState(535);
				arrayCreationDimensions();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(537);
				match(NEW);
				setState(538);
				match(ID);
				setState(539);
				arrayCreationDimensions();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayCreationDimensionsContext extends ParserRuleContext {
		public List<TerminalNode> LBRACK() { return getTokens(ZParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(ZParser.LBRACK, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(ZParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(ZParser.RBRACK, i);
		}
		public ArrayCreationDimensionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayCreationDimensions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterArrayCreationDimensions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitArrayCreationDimensions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitArrayCreationDimensions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayCreationDimensionsContext arrayCreationDimensions() throws RecognitionException {
		ArrayCreationDimensionsContext _localctx = new ArrayCreationDimensionsContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_arrayCreationDimensions);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(546); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(542);
					match(LBRACK);
					setState(543);
					expression();
					setState(544);
					match(RBRACK);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(548); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,49,_ctx);
			} while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralContext extends ParserRuleContext {
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	 
		public LiteralContext() { }
		public void copyFrom(LiteralContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IntegerLiteralExprContext extends LiteralContext {
		public TerminalNode INTEGER_LITERAL() { return getToken(ZParser.INTEGER_LITERAL, 0); }
		public IntegerLiteralExprContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterIntegerLiteralExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitIntegerLiteralExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitIntegerLiteralExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TrueLiteralExprContext extends LiteralContext {
		public TerminalNode TRUE() { return getToken(ZParser.TRUE, 0); }
		public TrueLiteralExprContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterTrueLiteralExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitTrueLiteralExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitTrueLiteralExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FloatLiteralExprContext extends LiteralContext {
		public TerminalNode FLOAT_LITERAL() { return getToken(ZParser.FLOAT_LITERAL, 0); }
		public FloatLiteralExprContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterFloatLiteralExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitFloatLiteralExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitFloatLiteralExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FalseLiteralExprContext extends LiteralContext {
		public TerminalNode FALSE() { return getToken(ZParser.FALSE, 0); }
		public FalseLiteralExprContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterFalseLiteralExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitFalseLiteralExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitFalseLiteralExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringLiteralExprContext extends LiteralContext {
		public TerminalNode STRING_LITERAL() { return getToken(ZParser.STRING_LITERAL, 0); }
		public StringLiteralExprContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterStringLiteralExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitStringLiteralExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitStringLiteralExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CharLiteralExprContext extends LiteralContext {
		public TerminalNode CHAR_LITERAL() { return getToken(ZParser.CHAR_LITERAL, 0); }
		public CharLiteralExprContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterCharLiteralExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitCharLiteralExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitCharLiteralExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_literal);
		try {
			setState(556);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER_LITERAL:
				_localctx = new IntegerLiteralExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(550);
				match(INTEGER_LITERAL);
				}
				break;
			case FLOAT_LITERAL:
				_localctx = new FloatLiteralExprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(551);
				match(FLOAT_LITERAL);
				}
				break;
			case CHAR_LITERAL:
				_localctx = new CharLiteralExprContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(552);
				match(CHAR_LITERAL);
				}
				break;
			case STRING_LITERAL:
				_localctx = new StringLiteralExprContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(553);
				match(STRING_LITERAL);
				}
				break;
			case TRUE:
				_localctx = new TrueLiteralExprContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(554);
				match(TRUE);
				}
				break;
			case FALSE:
				_localctx = new FalseLiteralExprContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(555);
				match(FALSE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ZParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ZParser.COMMA, i);
		}
		public ArgumentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterArgumentList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitArgumentList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitArgumentList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentListContext argumentList() throws RecognitionException {
		ArgumentListContext _localctx = new ArgumentListContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_argumentList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(558);
			expression();
			setState(563);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(559);
				match(COMMA);
				setState(560);
				expression();
				}
				}
				setState(565);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayInitializerContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(ZParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(ZParser.RBRACE, 0); }
		public ArrayInitializerElementListContext arrayInitializerElementList() {
			return getRuleContext(ArrayInitializerElementListContext.class,0);
		}
		public ArrayInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterArrayInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitArrayInitializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitArrayInitializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayInitializerContext arrayInitializer() throws RecognitionException {
		ArrayInitializerContext _localctx = new ArrayInitializerContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_arrayInitializer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(566);
			match(LBRACE);
			setState(568);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8936549104712810496L) != 0)) {
				{
				setState(567);
				arrayInitializerElementList();
				}
			}

			setState(570);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayInitializerElementListContext extends ParserRuleContext {
		public List<ArrayInitializerElementContext> arrayInitializerElement() {
			return getRuleContexts(ArrayInitializerElementContext.class);
		}
		public ArrayInitializerElementContext arrayInitializerElement(int i) {
			return getRuleContext(ArrayInitializerElementContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ZParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ZParser.COMMA, i);
		}
		public ArrayInitializerElementListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayInitializerElementList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterArrayInitializerElementList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitArrayInitializerElementList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitArrayInitializerElementList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayInitializerElementListContext arrayInitializerElementList() throws RecognitionException {
		ArrayInitializerElementListContext _localctx = new ArrayInitializerElementListContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_arrayInitializerElementList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(572);
			arrayInitializerElement();
			setState(577);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(573);
				match(COMMA);
				setState(574);
				arrayInitializerElement();
				}
				}
				setState(579);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayInitializerElementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArrayInitializerContext arrayInitializer() {
			return getRuleContext(ArrayInitializerContext.class,0);
		}
		public ArrayInitializerElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayInitializerElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterArrayInitializerElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitArrayInitializerElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitArrayInitializerElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayInitializerElementContext arrayInitializerElement() throws RecognitionException {
		ArrayInitializerElementContext _localctx = new ArrayInitializerElementContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_arrayInitializerElement);
		try {
			setState(582);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case READLN:
			case NEW:
			case NULL:
			case TRUE:
			case FALSE:
			case PLUS:
			case MINUS:
			case NOT:
			case LPAREN:
			case FLOAT_LITERAL:
			case INTEGER_LITERAL:
			case CHAR_LITERAL:
			case STRING_LITERAL:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(580);
				expression();
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(581);
				arrayInitializer();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayDimensionsContext extends ParserRuleContext {
		public List<TerminalNode> LBRACK() { return getTokens(ZParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(ZParser.LBRACK, i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(ZParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(ZParser.RBRACK, i);
		}
		public ArrayDimensionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayDimensions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterArrayDimensions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitArrayDimensions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitArrayDimensions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayDimensionsContext arrayDimensions() throws RecognitionException {
		ArrayDimensionsContext _localctx = new ArrayDimensionsContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_arrayDimensions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(586); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(584);
				match(LBRACK);
				setState(585);
				match(RBRACK);
				}
				}
				setState(588); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==LBRACK );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public ArrayDimensionsContext arrayDimensions() {
			return getRuleContext(ArrayDimensionsContext.class,0);
		}
		public ReferenceTypeContext referenceType() {
			return getRuleContext(ReferenceTypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_type);
		int _la;
		try {
			setState(598);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case DOUBLE:
			case CHAR:
			case BOOLEAN:
				enterOuterAlt(_localctx, 1);
				{
				setState(590);
				primitiveType();
				setState(592);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LBRACK) {
					{
					setState(591);
					arrayDimensions();
					}
				}

				}
				break;
			case STRING:
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(594);
				referenceType();
				setState(596);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LBRACK) {
					{
					setState(595);
					arrayDimensions();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrimitiveTypeContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(ZParser.INT, 0); }
		public TerminalNode DOUBLE() { return getToken(ZParser.DOUBLE, 0); }
		public TerminalNode CHAR() { return getToken(ZParser.CHAR, 0); }
		public TerminalNode BOOLEAN() { return getToken(ZParser.BOOLEAN, 0); }
		public PrimitiveTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitiveType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterPrimitiveType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitPrimitiveType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitPrimitiveType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimitiveTypeContext primitiveType() throws RecognitionException {
		PrimitiveTypeContext _localctx = new PrimitiveTypeContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_primitiveType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(600);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 62914560L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReferenceTypeContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(ZParser.STRING, 0); }
		public TerminalNode ID() { return getToken(ZParser.ID, 0); }
		public ReferenceTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_referenceType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).enterReferenceType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZListener) ((ZListener)listener).exitReferenceType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZVisitor) return ((ZVisitor<? extends T>)visitor).visitReferenceType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReferenceTypeContext referenceType() throws RecognitionException {
		ReferenceTypeContext _localctx = new ReferenceTypeContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_referenceType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(602);
			_la = _input.LA(1);
			if ( !(_la==STRING || _la==ID) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 37:
			return logicalOrExpression_sempred((LogicalOrExpressionContext)_localctx, predIndex);
		case 38:
			return logicalAndExpression_sempred((LogicalAndExpressionContext)_localctx, predIndex);
		case 39:
			return equalityExpression_sempred((EqualityExpressionContext)_localctx, predIndex);
		case 40:
			return comparisonExpression_sempred((ComparisonExpressionContext)_localctx, predIndex);
		case 41:
			return additiveExpression_sempred((AdditiveExpressionContext)_localctx, predIndex);
		case 42:
			return multiplicativeExpression_sempred((MultiplicativeExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean logicalOrExpression_sempred(LogicalOrExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean logicalAndExpression_sempred(LogicalAndExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean equalityExpression_sempred(EqualityExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 3);
		case 3:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean comparisonExpression_sempred(ComparisonExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 5);
		case 5:
			return precpred(_ctx, 4);
		case 6:
			return precpred(_ctx, 3);
		case 7:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean additiveExpression_sempred(AdditiveExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 8:
			return precpred(_ctx, 3);
		case 9:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean multiplicativeExpression_sempred(MultiplicativeExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 10:
			return precpred(_ctx, 4);
		case 11:
			return precpred(_ctx, 3);
		case 12:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001A\u025d\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u00071\u0002"+
		"2\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u00076\u0002"+
		"7\u00077\u00028\u00078\u00029\u00079\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001"+
		"}\b\u0001\n\u0001\f\u0001\u0080\t\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0003\u0002\u0087\b\u0002\u0001\u0003\u0003"+
		"\u0003\u008a\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0005\u0004\u0093\b\u0004\n\u0004\f\u0004"+
		"\u0096\t\u0004\u0001\u0005\u0001\u0005\u0003\u0005\u009a\b\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u00a0\b\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0003\u0007\u00a6\b\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00ac\b\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00b2\b\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00b8\b\u0007\u0001"+
		"\u0007\u0001\u0007\u0003\u0007\u00bc\b\u0007\u0001\b\u0001\b\u0001\b\u0005"+
		"\b\u00c1\b\b\n\b\f\b\u00c4\t\b\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n"+
		"\u0005\n\u00cb\b\n\n\n\f\n\u00ce\t\n\u0001\n\u0001\n\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003"+
		"\u000b\u00df\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0003\r\u00e9\b\r\u0001\u000e\u0001\u000e\u0003\u000e\u00ed"+
		"\b\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0005\u000f\u00fb\b\u000f\n\u000f\f\u000f\u00fe\t\u000f\u0001\u000f"+
		"\u0001\u000f\u0003\u000f\u0102\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u010a\b\u0010\n\u0010"+
		"\f\u0010\u010d\t\u0010\u0001\u0010\u0003\u0010\u0110\b\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011"+
		"\u0118\b\u0011\n\u0011\f\u0011\u011b\t\u0011\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0005\u0012\u0120\b\u0012\n\u0012\f\u0012\u0123\t\u0012\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0003\u0013\u0128\b\u0013\u0001\u0013\u0001\u0013"+
		"\u0003\u0013\u012c\b\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u0130\b"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0003"+
		"\u0014\u0137\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001"+
		"\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0005\u0017\u0141\b\u0017\n"+
		"\u0017\f\u0017\u0144\t\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001"+
		"\u001a\u0003\u001a\u0156\b\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001"+
		"\u001d\u0001\u001d\u0003\u001d\u0163\b\u001d\u0001\u001d\u0001\u001d\u0001"+
		"\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u016b\b\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0001 \u0001 \u0001 \u0001!\u0001!\u0001\"\u0001\""+
		"\u0001\"\u0001\"\u0001\"\u0003\"\u017f\b\"\u0001#\u0001#\u0001$\u0001"+
		"$\u0001$\u0001$\u0001$\u0001$\u0001$\u0003$\u018a\b$\u0001%\u0001%\u0001"+
		"%\u0001%\u0001%\u0001%\u0005%\u0192\b%\n%\f%\u0195\t%\u0001&\u0001&\u0001"+
		"&\u0001&\u0001&\u0001&\u0005&\u019d\b&\n&\f&\u01a0\t&\u0001\'\u0001\'"+
		"\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0005\'\u01ab"+
		"\b\'\n\'\f\'\u01ae\t\'\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001"+
		"(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0005(\u01bf"+
		"\b(\n(\f(\u01c2\t(\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001"+
		")\u0001)\u0005)\u01cd\b)\n)\f)\u01d0\t)\u0001*\u0001*\u0001*\u0001*\u0001"+
		"*\u0001*\u0001*\u0001*\u0001*\u0001*\u0001*\u0001*\u0005*\u01de\b*\n*"+
		"\f*\u01e1\t*\u0001+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001+\u0003+\u01ea"+
		"\b+\u0001,\u0001,\u0005,\u01ee\b,\n,\f,\u01f1\t,\u0001-\u0001-\u0001-"+
		"\u0001-\u0001-\u0001-\u0001-\u0001-\u0003-\u01fb\b-\u0001-\u0001-\u0001"+
		"-\u0003-\u0200\b-\u0001.\u0001.\u0001.\u0001.\u0001.\u0001.\u0003.\u0208"+
		"\b.\u0001.\u0001.\u0001.\u0001.\u0001.\u0001.\u0001.\u0001.\u0001.\u0001"+
		".\u0003.\u0214\b.\u0001/\u0001/\u0001/\u0001/\u0001/\u0001/\u0001/\u0003"+
		"/\u021d\b/\u00010\u00010\u00010\u00010\u00040\u0223\b0\u000b0\f0\u0224"+
		"\u00011\u00011\u00011\u00011\u00011\u00011\u00031\u022d\b1\u00012\u0001"+
		"2\u00012\u00052\u0232\b2\n2\f2\u0235\t2\u00013\u00013\u00033\u0239\b3"+
		"\u00013\u00013\u00014\u00014\u00014\u00054\u0240\b4\n4\f4\u0243\t4\u0001"+
		"5\u00015\u00035\u0247\b5\u00016\u00016\u00046\u024b\b6\u000b6\f6\u024c"+
		"\u00017\u00017\u00037\u0251\b7\u00017\u00017\u00037\u0255\b7\u00037\u0257"+
		"\b7\u00018\u00018\u00019\u00019\u00019\u0000\u0006JLNPRT:\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e"+
		" \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdfhjlnpr\u0000\u0003\u0001\u0000+"+
		".\u0001\u0000\u0016\u0019\u0002\u0000\u001a\u001a>>\u027a\u0000t\u0001"+
		"\u0000\u0000\u0000\u0002w\u0001\u0000\u0000\u0000\u0004\u0086\u0001\u0000"+
		"\u0000\u0000\u0006\u0089\u0001\u0000\u0000\u0000\b\u008f\u0001\u0000\u0000"+
		"\u0000\n\u0097\u0001\u0000\u0000\u0000\f\u009b\u0001\u0000\u0000\u0000"+
		"\u000e\u00bb\u0001\u0000\u0000\u0000\u0010\u00bd\u0001\u0000\u0000\u0000"+
		"\u0012\u00c5\u0001\u0000\u0000\u0000\u0014\u00c8\u0001\u0000\u0000\u0000"+
		"\u0016\u00de\u0001\u0000\u0000\u0000\u0018\u00e0\u0001\u0000\u0000\u0000"+
		"\u001a\u00e8\u0001\u0000\u0000\u0000\u001c\u00ec\u0001\u0000\u0000\u0000"+
		"\u001e\u00ee\u0001\u0000\u0000\u0000 \u0103\u0001\u0000\u0000\u0000\""+
		"\u0113\u0001\u0000\u0000\u0000$\u011c\u0001\u0000\u0000\u0000&\u0124\u0001"+
		"\u0000\u0000\u0000(\u0136\u0001\u0000\u0000\u0000*\u0138\u0001\u0000\u0000"+
		"\u0000,\u013b\u0001\u0000\u0000\u0000.\u013d\u0001\u0000\u0000\u00000"+
		"\u0145\u0001\u0000\u0000\u00002\u014b\u0001\u0000\u0000\u00004\u0153\u0001"+
		"\u0000\u0000\u00006\u0159\u0001\u0000\u0000\u00008\u015c\u0001\u0000\u0000"+
		"\u0000:\u015f\u0001\u0000\u0000\u0000<\u0167\u0001\u0000\u0000\u0000>"+
		"\u016f\u0001\u0000\u0000\u0000@\u0174\u0001\u0000\u0000\u0000B\u0177\u0001"+
		"\u0000\u0000\u0000D\u017e\u0001\u0000\u0000\u0000F\u0180\u0001\u0000\u0000"+
		"\u0000H\u0189\u0001\u0000\u0000\u0000J\u018b\u0001\u0000\u0000\u0000L"+
		"\u0196\u0001\u0000\u0000\u0000N\u01a1\u0001\u0000\u0000\u0000P\u01af\u0001"+
		"\u0000\u0000\u0000R\u01c3\u0001\u0000\u0000\u0000T\u01d1\u0001\u0000\u0000"+
		"\u0000V\u01e9\u0001\u0000\u0000\u0000X\u01eb\u0001\u0000\u0000\u0000Z"+
		"\u01ff\u0001\u0000\u0000\u0000\\\u0213\u0001\u0000\u0000\u0000^\u021c"+
		"\u0001\u0000\u0000\u0000`\u0222\u0001\u0000\u0000\u0000b\u022c\u0001\u0000"+
		"\u0000\u0000d\u022e\u0001\u0000\u0000\u0000f\u0236\u0001\u0000\u0000\u0000"+
		"h\u023c\u0001\u0000\u0000\u0000j\u0246\u0001\u0000\u0000\u0000l\u024a"+
		"\u0001\u0000\u0000\u0000n\u0256\u0001\u0000\u0000\u0000p\u0258\u0001\u0000"+
		"\u0000\u0000r\u025a\u0001\u0000\u0000\u0000tu\u0003\u0002\u0001\u0000"+
		"uv\u0005\u0000\u0000\u0001v\u0001\u0001\u0000\u0000\u0000wx\u0005\u0001"+
		"\u0000\u0000xy\u0005\u0002\u0000\u0000yz\u0005>\u0000\u0000z~\u00052\u0000"+
		"\u0000{}\u0003\u0004\u0002\u0000|{\u0001\u0000\u0000\u0000}\u0080\u0001"+
		"\u0000\u0000\u0000~|\u0001\u0000\u0000\u0000~\u007f\u0001\u0000\u0000"+
		"\u0000\u007f\u0081\u0001\u0000\u0000\u0000\u0080~\u0001\u0000\u0000\u0000"+
		"\u0081\u0082\u00053\u0000\u0000\u0082\u0003\u0001\u0000\u0000\u0000\u0083"+
		"\u0087\u0003\u0006\u0003\u0000\u0084\u0087\u0003\f\u0006\u0000\u0085\u0087"+
		"\u0003\u000e\u0007\u0000\u0086\u0083\u0001\u0000\u0000\u0000\u0086\u0084"+
		"\u0001\u0000\u0000\u0000\u0086\u0085\u0001\u0000\u0000\u0000\u0087\u0005"+
		"\u0001\u0000\u0000\u0000\u0088\u008a\u0005\u0001\u0000\u0000\u0089\u0088"+
		"\u0001\u0000\u0000\u0000\u0089\u008a\u0001\u0000\u0000\u0000\u008a\u008b"+
		"\u0001\u0000\u0000\u0000\u008b\u008c\u0003n7\u0000\u008c\u008d\u0003\b"+
		"\u0004\u0000\u008d\u008e\u00059\u0000\u0000\u008e\u0007\u0001\u0000\u0000"+
		"\u0000\u008f\u0094\u0003\n\u0005\u0000\u0090\u0091\u00057\u0000\u0000"+
		"\u0091\u0093\u0003\n\u0005\u0000\u0092\u0090\u0001\u0000\u0000\u0000\u0093"+
		"\u0096\u0001\u0000\u0000\u0000\u0094\u0092\u0001\u0000\u0000\u0000\u0094"+
		"\u0095\u0001\u0000\u0000\u0000\u0095\t\u0001\u0000\u0000\u0000\u0096\u0094"+
		"\u0001\u0000\u0000\u0000\u0097\u0099\u0005>\u0000\u0000\u0098\u009a\u0003"+
		"\u001a\r\u0000\u0099\u0098\u0001\u0000\u0000\u0000\u0099\u009a\u0001\u0000"+
		"\u0000\u0000\u009a\u000b\u0001\u0000\u0000\u0000\u009b\u009c\u0005\u0001"+
		"\u0000\u0000\u009c\u009d\u0005>\u0000\u0000\u009d\u009f\u00050\u0000\u0000"+
		"\u009e\u00a0\u0003\u0010\b\u0000\u009f\u009e\u0001\u0000\u0000\u0000\u009f"+
		"\u00a0\u0001\u0000\u0000\u0000\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a1"+
		"\u00a2\u00051\u0000\u0000\u00a2\u00a3\u0003\u0014\n\u0000\u00a3\r\u0001"+
		"\u0000\u0000\u0000\u00a4\u00a6\u0005\u0001\u0000\u0000\u00a5\u00a4\u0001"+
		"\u0000\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6\u00a7\u0001"+
		"\u0000\u0000\u0000\u00a7\u00a8\u0003n7\u0000\u00a8\u00a9\u0005>\u0000"+
		"\u0000\u00a9\u00ab\u00050\u0000\u0000\u00aa\u00ac\u0003\u0010\b\u0000"+
		"\u00ab\u00aa\u0001\u0000\u0000\u0000\u00ab\u00ac\u0001\u0000\u0000\u0000"+
		"\u00ac\u00ad\u0001\u0000\u0000\u0000\u00ad\u00ae\u00051\u0000\u0000\u00ae"+
		"\u00af\u0003\u0014\n\u0000\u00af\u00bc\u0001\u0000\u0000\u0000\u00b0\u00b2"+
		"\u0005\u0001\u0000\u0000\u00b1\u00b0\u0001\u0000\u0000\u0000\u00b1\u00b2"+
		"\u0001\u0000\u0000\u0000\u00b2\u00b3\u0001\u0000\u0000\u0000\u00b3\u00b4"+
		"\u0005\u0003\u0000\u0000\u00b4\u00b5\u0005>\u0000\u0000\u00b5\u00b7\u0005"+
		"0\u0000\u0000\u00b6\u00b8\u0003\u0010\b\u0000\u00b7\u00b6\u0001\u0000"+
		"\u0000\u0000\u00b7\u00b8\u0001\u0000\u0000\u0000\u00b8\u00b9\u0001\u0000"+
		"\u0000\u0000\u00b9\u00ba\u00051\u0000\u0000\u00ba\u00bc\u0003\u0014\n"+
		"\u0000\u00bb\u00a5\u0001\u0000\u0000\u0000\u00bb\u00b1\u0001\u0000\u0000"+
		"\u0000\u00bc\u000f\u0001\u0000\u0000\u0000\u00bd\u00c2\u0003\u0012\t\u0000"+
		"\u00be\u00bf\u00057\u0000\u0000\u00bf\u00c1\u0003\u0012\t\u0000\u00c0"+
		"\u00be\u0001\u0000\u0000\u0000\u00c1\u00c4\u0001\u0000\u0000\u0000\u00c2"+
		"\u00c0\u0001\u0000\u0000\u0000\u00c2\u00c3\u0001\u0000\u0000\u0000\u00c3"+
		"\u0011\u0001\u0000\u0000\u0000\u00c4\u00c2\u0001\u0000\u0000\u0000\u00c5"+
		"\u00c6\u0003n7\u0000\u00c6\u00c7\u0005>\u0000\u0000\u00c7\u0013\u0001"+
		"\u0000\u0000\u0000\u00c8\u00cc\u00052\u0000\u0000\u00c9\u00cb\u0003\u0016"+
		"\u000b\u0000\u00ca\u00c9\u0001\u0000\u0000\u0000\u00cb\u00ce\u0001\u0000"+
		"\u0000\u0000\u00cc\u00ca\u0001\u0000\u0000\u0000\u00cc\u00cd\u0001\u0000"+
		"\u0000\u0000\u00cd\u00cf\u0001\u0000\u0000\u0000\u00ce\u00cc\u0001\u0000"+
		"\u0000\u0000\u00cf\u00d0\u00053\u0000\u0000\u00d0\u0015\u0001\u0000\u0000"+
		"\u0000\u00d1\u00df\u0003\u0018\f\u0000\u00d2\u00df\u0003@ \u0000\u00d3"+
		"\u00df\u0003:\u001d\u0000\u00d4\u00df\u0003<\u001e\u0000\u00d5\u00df\u0003"+
		">\u001f\u0000\u00d6\u00df\u0003\u001e\u000f\u0000\u00d7\u00df\u0003 \u0010"+
		"\u0000\u00d8\u00df\u0003&\u0013\u0000\u00d9\u00df\u00030\u0018\u0000\u00da"+
		"\u00df\u00032\u0019\u0000\u00db\u00df\u00034\u001a\u0000\u00dc\u00df\u0003"+
		"6\u001b\u0000\u00dd\u00df\u00038\u001c\u0000\u00de\u00d1\u0001\u0000\u0000"+
		"\u0000\u00de\u00d2\u0001\u0000\u0000\u0000\u00de\u00d3\u0001\u0000\u0000"+
		"\u0000\u00de\u00d4\u0001\u0000\u0000\u0000\u00de\u00d5\u0001\u0000\u0000"+
		"\u0000\u00de\u00d6\u0001\u0000\u0000\u0000\u00de\u00d7\u0001\u0000\u0000"+
		"\u0000\u00de\u00d8\u0001\u0000\u0000\u0000\u00de\u00d9\u0001\u0000\u0000"+
		"\u0000\u00de\u00da\u0001\u0000\u0000\u0000\u00de\u00db\u0001\u0000\u0000"+
		"\u0000\u00de\u00dc\u0001\u0000\u0000\u0000\u00de\u00dd\u0001\u0000\u0000"+
		"\u0000\u00df\u0017\u0001\u0000\u0000\u0000\u00e0\u00e1\u0003n7\u0000\u00e1"+
		"\u00e2\u0003\b\u0004\u0000\u00e2\u00e3\u00059\u0000\u0000\u00e3\u0019"+
		"\u0001\u0000\u0000\u0000\u00e4\u00e5\u0005+\u0000\u0000\u00e5\u00e9\u0003"+
		"B!\u0000\u00e6\u00e7\u0005+\u0000\u0000\u00e7\u00e9\u0003f3\u0000\u00e8"+
		"\u00e4\u0001\u0000\u0000\u0000\u00e8\u00e6\u0001\u0000\u0000\u0000\u00e9"+
		"\u001b\u0001\u0000\u0000\u0000\u00ea\u00ed\u0003\u0016\u000b\u0000\u00eb"+
		"\u00ed\u0003\u0014\n\u0000\u00ec\u00ea\u0001\u0000\u0000\u0000\u00ec\u00eb"+
		"\u0001\u0000\u0000\u0000\u00ed\u001d\u0001\u0000\u0000\u0000\u00ee\u00ef"+
		"\u0005\u0005\u0000\u0000\u00ef\u00f0\u00050\u0000\u0000\u00f0\u00f1\u0003"+
		"B!\u0000\u00f1\u00f2\u00051\u0000\u0000\u00f2\u00fc\u0003\u001c\u000e"+
		"\u0000\u00f3\u00f4\u0005\u0006\u0000\u0000\u00f4\u00f5\u0005\u0005\u0000"+
		"\u0000\u00f5\u00f6\u00050\u0000\u0000\u00f6\u00f7\u0003B!\u0000\u00f7"+
		"\u00f8\u00051\u0000\u0000\u00f8\u00f9\u0003\u001c\u000e\u0000\u00f9\u00fb"+
		"\u0001\u0000\u0000\u0000\u00fa\u00f3\u0001\u0000\u0000\u0000\u00fb\u00fe"+
		"\u0001\u0000\u0000\u0000\u00fc\u00fa\u0001\u0000\u0000\u0000\u00fc\u00fd"+
		"\u0001\u0000\u0000\u0000\u00fd\u0101\u0001\u0000\u0000\u0000\u00fe\u00fc"+
		"\u0001\u0000\u0000\u0000\u00ff\u0100\u0005\u0006\u0000\u0000\u0100\u0102"+
		"\u0003\u001c\u000e\u0000\u0101\u00ff\u0001\u0000\u0000\u0000\u0101\u0102"+
		"\u0001\u0000\u0000\u0000\u0102\u001f\u0001\u0000\u0000\u0000\u0103\u0104"+
		"\u0005\u0007\u0000\u0000\u0104\u0105\u00050\u0000\u0000\u0105\u0106\u0003"+
		"B!\u0000\u0106\u0107\u00051\u0000\u0000\u0107\u010b\u00052\u0000\u0000"+
		"\u0108\u010a\u0003\"\u0011\u0000\u0109\u0108\u0001\u0000\u0000\u0000\u010a"+
		"\u010d\u0001\u0000\u0000\u0000\u010b\u0109\u0001\u0000\u0000\u0000\u010b"+
		"\u010c\u0001\u0000\u0000\u0000\u010c\u010f\u0001\u0000\u0000\u0000\u010d"+
		"\u010b\u0001\u0000\u0000\u0000\u010e\u0110\u0003$\u0012\u0000\u010f\u010e"+
		"\u0001\u0000\u0000\u0000\u010f\u0110\u0001\u0000\u0000\u0000\u0110\u0111"+
		"\u0001\u0000\u0000\u0000\u0111\u0112\u00053\u0000\u0000\u0112!\u0001\u0000"+
		"\u0000\u0000\u0113\u0114\u0005\b\u0000\u0000\u0114\u0115\u0003B!\u0000"+
		"\u0115\u0119\u00058\u0000\u0000\u0116\u0118\u0003\u0016\u000b\u0000\u0117"+
		"\u0116\u0001\u0000\u0000\u0000\u0118\u011b\u0001\u0000\u0000\u0000\u0119"+
		"\u0117\u0001\u0000\u0000\u0000\u0119\u011a\u0001\u0000\u0000\u0000\u011a"+
		"#\u0001\u0000\u0000\u0000\u011b\u0119\u0001\u0000\u0000\u0000\u011c\u011d"+
		"\u0005\t\u0000\u0000\u011d\u0121\u00058\u0000\u0000\u011e\u0120\u0003"+
		"\u0016\u000b\u0000\u011f\u011e\u0001\u0000\u0000\u0000\u0120\u0123\u0001"+
		"\u0000\u0000\u0000\u0121\u011f\u0001\u0000\u0000\u0000\u0121\u0122\u0001"+
		"\u0000\u0000\u0000\u0122%\u0001\u0000\u0000\u0000\u0123\u0121\u0001\u0000"+
		"\u0000\u0000\u0124\u0125\u0005\n\u0000\u0000\u0125\u0127\u00050\u0000"+
		"\u0000\u0126\u0128\u0003(\u0014\u0000\u0127\u0126\u0001\u0000\u0000\u0000"+
		"\u0127\u0128\u0001\u0000\u0000\u0000\u0128\u0129\u0001\u0000\u0000\u0000"+
		"\u0129\u012b\u00059\u0000\u0000\u012a\u012c\u0003B!\u0000\u012b\u012a"+
		"\u0001\u0000\u0000\u0000\u012b\u012c\u0001\u0000\u0000\u0000\u012c\u012d"+
		"\u0001\u0000\u0000\u0000\u012d\u012f\u00059\u0000\u0000\u012e\u0130\u0003"+
		",\u0016\u0000\u012f\u012e\u0001\u0000\u0000\u0000\u012f\u0130\u0001\u0000"+
		"\u0000\u0000\u0130\u0131\u0001\u0000\u0000\u0000\u0131\u0132\u00051\u0000"+
		"\u0000\u0132\u0133\u0003\u0014\n\u0000\u0133\'\u0001\u0000\u0000\u0000"+
		"\u0134\u0137\u0003*\u0015\u0000\u0135\u0137\u0003.\u0017\u0000\u0136\u0134"+
		"\u0001\u0000\u0000\u0000\u0136\u0135\u0001\u0000\u0000\u0000\u0137)\u0001"+
		"\u0000\u0000\u0000\u0138\u0139\u0003n7\u0000\u0139\u013a\u0003\b\u0004"+
		"\u0000\u013a+\u0001\u0000\u0000\u0000\u013b\u013c\u0003.\u0017\u0000\u013c"+
		"-\u0001\u0000\u0000\u0000\u013d\u0142\u0003B!\u0000\u013e\u013f\u0005"+
		"7\u0000\u0000\u013f\u0141\u0003B!\u0000\u0140\u013e\u0001\u0000\u0000"+
		"\u0000\u0141\u0144\u0001\u0000\u0000\u0000\u0142\u0140\u0001\u0000\u0000"+
		"\u0000\u0142\u0143\u0001\u0000\u0000\u0000\u0143/\u0001\u0000\u0000\u0000"+
		"\u0144\u0142\u0001\u0000\u0000\u0000\u0145\u0146\u0005\u000b\u0000\u0000"+
		"\u0146\u0147\u00050\u0000\u0000\u0147\u0148\u0003B!\u0000\u0148\u0149"+
		"\u00051\u0000\u0000\u0149\u014a\u0003\u0014\n\u0000\u014a1\u0001\u0000"+
		"\u0000\u0000\u014b\u014c\u0005\f\u0000\u0000\u014c\u014d\u0003\u0014\n"+
		"\u0000\u014d\u014e\u0005\u000b\u0000\u0000\u014e\u014f\u00050\u0000\u0000"+
		"\u014f\u0150\u0003B!\u0000\u0150\u0151\u00051\u0000\u0000\u0151\u0152"+
		"\u00059\u0000\u0000\u01523\u0001\u0000\u0000\u0000\u0153\u0155\u0005\u0004"+
		"\u0000\u0000\u0154\u0156\u0003B!\u0000\u0155\u0154\u0001\u0000\u0000\u0000"+
		"\u0155\u0156\u0001\u0000\u0000\u0000\u0156\u0157\u0001\u0000\u0000\u0000"+
		"\u0157\u0158\u00059\u0000\u0000\u01585\u0001\u0000\u0000\u0000\u0159\u015a"+
		"\u0005\r\u0000\u0000\u015a\u015b\u00059\u0000\u0000\u015b7\u0001\u0000"+
		"\u0000\u0000\u015c\u015d\u0005\u000e\u0000\u0000\u015d\u015e\u00059\u0000"+
		"\u0000\u015e9\u0001\u0000\u0000\u0000\u015f\u0160\u0005\u000f\u0000\u0000"+
		"\u0160\u0162\u00050\u0000\u0000\u0161\u0163\u0003d2\u0000\u0162\u0161"+
		"\u0001\u0000\u0000\u0000\u0162\u0163\u0001\u0000\u0000\u0000\u0163\u0164"+
		"\u0001\u0000\u0000\u0000\u0164\u0165\u00051\u0000\u0000\u0165\u0166\u0005"+
		"9\u0000\u0000\u0166;\u0001\u0000\u0000\u0000\u0167\u0168\u0005\u0010\u0000"+
		"\u0000\u0168\u016a\u00050\u0000\u0000\u0169\u016b\u0003d2\u0000\u016a"+
		"\u0169\u0001\u0000\u0000\u0000\u016a\u016b\u0001\u0000\u0000\u0000\u016b"+
		"\u016c\u0001\u0000\u0000\u0000\u016c\u016d\u00051\u0000\u0000\u016d\u016e"+
		"\u00059\u0000\u0000\u016e=\u0001\u0000\u0000\u0000\u016f\u0170\u0005\u0011"+
		"\u0000\u0000\u0170\u0171\u00050\u0000\u0000\u0171\u0172\u00051\u0000\u0000"+
		"\u0172\u0173\u00059\u0000\u0000\u0173?\u0001\u0000\u0000\u0000\u0174\u0175"+
		"\u0003B!\u0000\u0175\u0176\u00059\u0000\u0000\u0176A\u0001\u0000\u0000"+
		"\u0000\u0177\u0178\u0003D\"\u0000\u0178C\u0001\u0000\u0000\u0000\u0179"+
		"\u017f\u0003H$\u0000\u017a\u017b\u0003X,\u0000\u017b\u017c\u0003F#\u0000"+
		"\u017c\u017d\u0003D\"\u0000\u017d\u017f\u0001\u0000\u0000\u0000\u017e"+
		"\u0179\u0001\u0000\u0000\u0000\u017e\u017a\u0001\u0000\u0000\u0000\u017f"+
		"E\u0001\u0000\u0000\u0000\u0180\u0181\u0007\u0000\u0000\u0000\u0181G\u0001"+
		"\u0000\u0000\u0000\u0182\u018a\u0003J%\u0000\u0183\u0184\u0003J%\u0000"+
		"\u0184\u0185\u0005/\u0000\u0000\u0185\u0186\u0003B!\u0000\u0186\u0187"+
		"\u00058\u0000\u0000\u0187\u0188\u0003B!\u0000\u0188\u018a\u0001\u0000"+
		"\u0000\u0000\u0189\u0182\u0001\u0000\u0000\u0000\u0189\u0183\u0001\u0000"+
		"\u0000\u0000\u018aI\u0001\u0000\u0000\u0000\u018b\u018c\u0006%\uffff\uffff"+
		"\u0000\u018c\u018d\u0003L&\u0000\u018d\u0193\u0001\u0000\u0000\u0000\u018e"+
		"\u018f\n\u0002\u0000\u0000\u018f\u0190\u0005#\u0000\u0000\u0190\u0192"+
		"\u0003L&\u0000\u0191\u018e\u0001\u0000\u0000\u0000\u0192\u0195\u0001\u0000"+
		"\u0000\u0000\u0193\u0191\u0001\u0000\u0000\u0000\u0193\u0194\u0001\u0000"+
		"\u0000\u0000\u0194K\u0001\u0000\u0000\u0000\u0195\u0193\u0001\u0000\u0000"+
		"\u0000\u0196\u0197\u0006&\uffff\uffff\u0000\u0197\u0198\u0003N\'\u0000"+
		"\u0198\u019e\u0001\u0000\u0000\u0000\u0199\u019a\n\u0002\u0000\u0000\u019a"+
		"\u019b\u0005\"\u0000\u0000\u019b\u019d\u0003N\'\u0000\u019c\u0199\u0001"+
		"\u0000\u0000\u0000\u019d\u01a0\u0001\u0000\u0000\u0000\u019e\u019c\u0001"+
		"\u0000\u0000\u0000\u019e\u019f\u0001\u0000\u0000\u0000\u019fM\u0001\u0000"+
		"\u0000\u0000\u01a0\u019e\u0001\u0000\u0000\u0000\u01a1\u01a2\u0006\'\uffff"+
		"\uffff\u0000\u01a2\u01a3\u0003P(\u0000\u01a3\u01ac\u0001\u0000\u0000\u0000"+
		"\u01a4\u01a5\n\u0003\u0000\u0000\u01a5\u01a6\u0005%\u0000\u0000\u01a6"+
		"\u01ab\u0003P(\u0000\u01a7\u01a8\n\u0002\u0000\u0000\u01a8\u01a9\u0005"+
		"&\u0000\u0000\u01a9\u01ab\u0003P(\u0000\u01aa\u01a4\u0001\u0000\u0000"+
		"\u0000\u01aa\u01a7\u0001\u0000\u0000\u0000\u01ab\u01ae\u0001\u0000\u0000"+
		"\u0000\u01ac\u01aa\u0001\u0000\u0000\u0000\u01ac\u01ad\u0001\u0000\u0000"+
		"\u0000\u01adO\u0001\u0000\u0000\u0000\u01ae\u01ac\u0001\u0000\u0000\u0000"+
		"\u01af\u01b0\u0006(\uffff\uffff\u0000\u01b0\u01b1\u0003R)\u0000\u01b1"+
		"\u01c0\u0001\u0000\u0000\u0000\u01b2\u01b3\n\u0005\u0000\u0000\u01b3\u01b4"+
		"\u0005\'\u0000\u0000\u01b4\u01bf\u0003R)\u0000\u01b5\u01b6\n\u0004\u0000"+
		"\u0000\u01b6\u01b7\u0005(\u0000\u0000\u01b7\u01bf\u0003R)\u0000\u01b8"+
		"\u01b9\n\u0003\u0000\u0000\u01b9\u01ba\u0005)\u0000\u0000\u01ba\u01bf"+
		"\u0003R)\u0000\u01bb\u01bc\n\u0002\u0000\u0000\u01bc\u01bd\u0005*\u0000"+
		"\u0000\u01bd\u01bf\u0003R)\u0000\u01be\u01b2\u0001\u0000\u0000\u0000\u01be"+
		"\u01b5\u0001\u0000\u0000\u0000\u01be\u01b8\u0001\u0000\u0000\u0000\u01be"+
		"\u01bb\u0001\u0000\u0000\u0000\u01bf\u01c2\u0001\u0000\u0000\u0000\u01c0"+
		"\u01be\u0001\u0000\u0000\u0000\u01c0\u01c1\u0001\u0000\u0000\u0000\u01c1"+
		"Q\u0001\u0000\u0000\u0000\u01c2\u01c0\u0001\u0000\u0000\u0000\u01c3\u01c4"+
		"\u0006)\uffff\uffff\u0000\u01c4\u01c5\u0003T*\u0000\u01c5\u01ce\u0001"+
		"\u0000\u0000\u0000\u01c6\u01c7\n\u0003\u0000\u0000\u01c7\u01c8\u0005\u001b"+
		"\u0000\u0000\u01c8\u01cd\u0003T*\u0000\u01c9\u01ca\n\u0002\u0000\u0000"+
		"\u01ca\u01cb\u0005\u001c\u0000\u0000\u01cb\u01cd\u0003T*\u0000\u01cc\u01c6"+
		"\u0001\u0000\u0000\u0000\u01cc\u01c9\u0001\u0000\u0000\u0000\u01cd\u01d0"+
		"\u0001\u0000\u0000\u0000\u01ce\u01cc\u0001\u0000\u0000\u0000\u01ce\u01cf"+
		"\u0001\u0000\u0000\u0000\u01cfS\u0001\u0000\u0000\u0000\u01d0\u01ce\u0001"+
		"\u0000\u0000\u0000\u01d1\u01d2\u0006*\uffff\uffff\u0000\u01d2\u01d3\u0003"+
		"V+\u0000\u01d3\u01df\u0001\u0000\u0000\u0000\u01d4\u01d5\n\u0004\u0000"+
		"\u0000\u01d5\u01d6\u0005\u001d\u0000\u0000\u01d6\u01de\u0003V+\u0000\u01d7"+
		"\u01d8\n\u0003\u0000\u0000\u01d8\u01d9\u0005\u001e\u0000\u0000\u01d9\u01de"+
		"\u0003V+\u0000\u01da\u01db\n\u0002\u0000\u0000\u01db\u01dc\u0005\u001f"+
		"\u0000\u0000\u01dc\u01de\u0003V+\u0000\u01dd\u01d4\u0001\u0000\u0000\u0000"+
		"\u01dd\u01d7\u0001\u0000\u0000\u0000\u01dd\u01da\u0001\u0000\u0000\u0000"+
		"\u01de\u01e1\u0001\u0000\u0000\u0000\u01df\u01dd\u0001\u0000\u0000\u0000"+
		"\u01df\u01e0\u0001\u0000\u0000\u0000\u01e0U\u0001\u0000\u0000\u0000\u01e1"+
		"\u01df\u0001\u0000\u0000\u0000\u01e2\u01e3\u0005$\u0000\u0000\u01e3\u01ea"+
		"\u0003V+\u0000\u01e4\u01e5\u0005\u001c\u0000\u0000\u01e5\u01ea\u0003V"+
		"+\u0000\u01e6\u01e7\u0005\u001b\u0000\u0000\u01e7\u01ea\u0003V+\u0000"+
		"\u01e8\u01ea\u0003X,\u0000\u01e9\u01e2\u0001\u0000\u0000\u0000\u01e9\u01e4"+
		"\u0001\u0000\u0000\u0000\u01e9\u01e6\u0001\u0000\u0000\u0000\u01e9\u01e8"+
		"\u0001\u0000\u0000\u0000\u01eaW\u0001\u0000\u0000\u0000\u01eb\u01ef\u0003"+
		"\\.\u0000\u01ec\u01ee\u0003Z-\u0000\u01ed\u01ec\u0001\u0000\u0000\u0000"+
		"\u01ee\u01f1\u0001\u0000\u0000\u0000\u01ef\u01ed\u0001\u0000\u0000\u0000"+
		"\u01ef\u01f0\u0001\u0000\u0000\u0000\u01f0Y\u0001\u0000\u0000\u0000\u01f1"+
		"\u01ef\u0001\u0000\u0000\u0000\u01f2\u01f3\u00054\u0000\u0000\u01f3\u01f4"+
		"\u0003B!\u0000\u01f4\u01f5\u00055\u0000\u0000\u01f5\u0200\u0001\u0000"+
		"\u0000\u0000\u01f6\u01f7\u00056\u0000\u0000\u01f7\u0200\u0005>\u0000\u0000"+
		"\u01f8\u01fa\u00050\u0000\u0000\u01f9\u01fb\u0003d2\u0000\u01fa\u01f9"+
		"\u0001\u0000\u0000\u0000\u01fa\u01fb\u0001\u0000\u0000\u0000\u01fb\u01fc"+
		"\u0001\u0000\u0000\u0000\u01fc\u0200\u00051\u0000\u0000\u01fd\u0200\u0005"+
		" \u0000\u0000\u01fe\u0200\u0005!\u0000\u0000\u01ff\u01f2\u0001\u0000\u0000"+
		"\u0000\u01ff\u01f6\u0001\u0000\u0000\u0000\u01ff\u01f8\u0001\u0000\u0000"+
		"\u0000\u01ff\u01fd\u0001\u0000\u0000\u0000\u01ff\u01fe\u0001\u0000\u0000"+
		"\u0000\u0200[\u0001\u0000\u0000\u0000\u0201\u0214\u0003b1\u0000\u0202"+
		"\u0214\u0005>\u0000\u0000\u0203\u0204\u0005\u0012\u0000\u0000\u0204\u0205"+
		"\u0005>\u0000\u0000\u0205\u0207\u00050\u0000\u0000\u0206\u0208\u0003d"+
		"2\u0000\u0207\u0206\u0001\u0000\u0000\u0000\u0207\u0208\u0001\u0000\u0000"+
		"\u0000\u0208\u0209\u0001\u0000\u0000\u0000\u0209\u0214\u00051\u0000\u0000"+
		"\u020a\u0214\u0003^/\u0000\u020b\u0214\u0005\u0013\u0000\u0000\u020c\u020d"+
		"\u0005\u0011\u0000\u0000\u020d\u020e\u00050\u0000\u0000\u020e\u0214\u0005"+
		"1\u0000\u0000\u020f\u0210\u00050\u0000\u0000\u0210\u0211\u0003B!\u0000"+
		"\u0211\u0212\u00051\u0000\u0000\u0212\u0214\u0001\u0000\u0000\u0000\u0213"+
		"\u0201\u0001\u0000\u0000\u0000\u0213\u0202\u0001\u0000\u0000\u0000\u0213"+
		"\u0203\u0001\u0000\u0000\u0000\u0213\u020a\u0001\u0000\u0000\u0000\u0213"+
		"\u020b\u0001\u0000\u0000\u0000\u0213\u020c\u0001\u0000\u0000\u0000\u0213"+
		"\u020f\u0001\u0000\u0000\u0000\u0214]\u0001\u0000\u0000\u0000\u0215\u0216"+
		"\u0005\u0012\u0000\u0000\u0216\u0217\u0003p8\u0000\u0217\u0218\u0003`"+
		"0\u0000\u0218\u021d\u0001\u0000\u0000\u0000\u0219\u021a\u0005\u0012\u0000"+
		"\u0000\u021a\u021b\u0005>\u0000\u0000\u021b\u021d\u0003`0\u0000\u021c"+
		"\u0215\u0001\u0000\u0000\u0000\u021c\u0219\u0001\u0000\u0000\u0000\u021d"+
		"_\u0001\u0000\u0000\u0000\u021e\u021f\u00054\u0000\u0000\u021f\u0220\u0003"+
		"B!\u0000\u0220\u0221\u00055\u0000\u0000\u0221\u0223\u0001\u0000\u0000"+
		"\u0000\u0222\u021e\u0001\u0000\u0000\u0000\u0223\u0224\u0001\u0000\u0000"+
		"\u0000\u0224\u0222\u0001\u0000\u0000\u0000\u0224\u0225\u0001\u0000\u0000"+
		"\u0000\u0225a\u0001\u0000\u0000\u0000\u0226\u022d\u0005;\u0000\u0000\u0227"+
		"\u022d\u0005:\u0000\u0000\u0228\u022d\u0005<\u0000\u0000\u0229\u022d\u0005"+
		"=\u0000\u0000\u022a\u022d\u0005\u0014\u0000\u0000\u022b\u022d\u0005\u0015"+
		"\u0000\u0000\u022c\u0226\u0001\u0000\u0000\u0000\u022c\u0227\u0001\u0000"+
		"\u0000\u0000\u022c\u0228\u0001\u0000\u0000\u0000\u022c\u0229\u0001\u0000"+
		"\u0000\u0000\u022c\u022a\u0001\u0000\u0000\u0000\u022c\u022b\u0001\u0000"+
		"\u0000\u0000\u022dc\u0001\u0000\u0000\u0000\u022e\u0233\u0003B!\u0000"+
		"\u022f\u0230\u00057\u0000\u0000\u0230\u0232\u0003B!\u0000\u0231\u022f"+
		"\u0001\u0000\u0000\u0000\u0232\u0235\u0001\u0000\u0000\u0000\u0233\u0231"+
		"\u0001\u0000\u0000\u0000\u0233\u0234\u0001\u0000\u0000\u0000\u0234e\u0001"+
		"\u0000\u0000\u0000\u0235\u0233\u0001\u0000\u0000\u0000\u0236\u0238\u0005"+
		"2\u0000\u0000\u0237\u0239\u0003h4\u0000\u0238\u0237\u0001\u0000\u0000"+
		"\u0000\u0238\u0239\u0001\u0000\u0000\u0000\u0239\u023a\u0001\u0000\u0000"+
		"\u0000\u023a\u023b\u00053\u0000\u0000\u023bg\u0001\u0000\u0000\u0000\u023c"+
		"\u0241\u0003j5\u0000\u023d\u023e\u00057\u0000\u0000\u023e\u0240\u0003"+
		"j5\u0000\u023f\u023d\u0001\u0000\u0000\u0000\u0240\u0243\u0001\u0000\u0000"+
		"\u0000\u0241\u023f\u0001\u0000\u0000\u0000\u0241\u0242\u0001\u0000\u0000"+
		"\u0000\u0242i\u0001\u0000\u0000\u0000\u0243\u0241\u0001\u0000\u0000\u0000"+
		"\u0244\u0247\u0003B!\u0000\u0245\u0247\u0003f3\u0000\u0246\u0244\u0001"+
		"\u0000\u0000\u0000\u0246\u0245\u0001\u0000\u0000\u0000\u0247k\u0001\u0000"+
		"\u0000\u0000\u0248\u0249\u00054\u0000\u0000\u0249\u024b\u00055\u0000\u0000"+
		"\u024a\u0248\u0001\u0000\u0000\u0000\u024b\u024c\u0001\u0000\u0000\u0000"+
		"\u024c\u024a\u0001\u0000\u0000\u0000\u024c\u024d\u0001\u0000\u0000\u0000"+
		"\u024dm\u0001\u0000\u0000\u0000\u024e\u0250\u0003p8\u0000\u024f\u0251"+
		"\u0003l6\u0000\u0250\u024f\u0001\u0000\u0000\u0000\u0250\u0251\u0001\u0000"+
		"\u0000\u0000\u0251\u0257\u0001\u0000\u0000\u0000\u0252\u0254\u0003r9\u0000"+
		"\u0253\u0255\u0003l6\u0000\u0254\u0253\u0001\u0000\u0000\u0000\u0254\u0255"+
		"\u0001\u0000\u0000\u0000\u0255\u0257\u0001\u0000\u0000\u0000\u0256\u024e"+
		"\u0001\u0000\u0000\u0000\u0256\u0252\u0001\u0000\u0000\u0000\u0257o\u0001"+
		"\u0000\u0000\u0000\u0258\u0259\u0007\u0001\u0000\u0000\u0259q\u0001\u0000"+
		"\u0000\u0000\u025a\u025b\u0007\u0002\u0000\u0000\u025bs\u0001\u0000\u0000"+
		"\u0000;~\u0086\u0089\u0094\u0099\u009f\u00a5\u00ab\u00b1\u00b7\u00bb\u00c2"+
		"\u00cc\u00de\u00e8\u00ec\u00fc\u0101\u010b\u010f\u0119\u0121\u0127\u012b"+
		"\u012f\u0136\u0142\u0155\u0162\u016a\u017e\u0189\u0193\u019e\u01aa\u01ac"+
		"\u01be\u01c0\u01cc\u01ce\u01dd\u01df\u01e9\u01ef\u01fa\u01ff\u0207\u0213"+
		"\u021c\u0224\u022c\u0233\u0238\u0241\u0246\u024c\u0250\u0254\u0256";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}