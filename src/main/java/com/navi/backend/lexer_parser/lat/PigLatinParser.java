package com.navi.backend.lexer_parser.lat;// Generated from PigLatin.g4 by ANTLR 4.13.2

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class PigLatinParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, VARIABLES_SECTION=12, FUNCTIONS_SECTION=13, MAIN_SECTION=14, 
		FINIS_PROGRAM=15, FINIS=16, ESTO=17, SERIES=18, ACTIO=19, RATIO=20, SI=21, 
		ALITER=22, DUM=23, FACERE=24, PER=25, REDDERE=26, PERGE=27, INTERRUMPE=28, 
		VARIABILES=29, IMPORT=30, NOVUS=31, NUMERUS=32, DECIMALIS=33, TEXTUM=34, 
		LITTERA=35, BOOL=36, VERUM=37, FALSUS=38, PLUS=39, MINUS=40, MULT=41, 
		DIV=42, PLUSPLUS=43, MINUSMINUS=44, AND=45, OR=46, NON=47, EQUAL=48, NOT_EQUAL=49, 
		LESS=50, GREATER=51, LESS_EQUAL=52, GREATER_EQUAL=53, READ=54, PRINT=55, 
		NUMBER=56, DECIMAL=57, STRING=58, CHAR=59, ID=60, LINE_COMMENT=61, BLOCK_COMMENT=62, 
		WS=63;
	public static final int
		RULE_program = 0, RULE_importDeclaration = 1, RULE_importPath = 2, RULE_globalVariablesSection = 3, 
		RULE_functionSection = 4, RULE_mainSection = 5, RULE_functionDeclaration = 6, 
		RULE_procedureDeclaration = 7, RULE_functionWithReturn = 8, RULE_parameterList = 9, 
		RULE_parameter = 10, RULE_functionBody = 11, RULE_declaration = 12, RULE_localVariableSection = 13, 
		RULE_variableDeclaration = 14, RULE_initializer = 15, RULE_structInitializer = 16, 
		RULE_structFieldInitializer = 17, RULE_arrayConstructor = 18, RULE_arrayDeclaration = 19, 
		RULE_arrayInitializer = 20, RULE_type = 21, RULE_statement = 22, RULE_assignment = 23, 
		RULE_incrementStatement = 24, RULE_incrementableExpression = 25, RULE_functionCallStatement = 26, 
		RULE_callableExpression = 27, RULE_ifStatement = 28, RULE_elseIfStatement = 29, 
		RULE_elseStatement = 30, RULE_block = 31, RULE_whileStatement = 32, RULE_doWhileStatement = 33, 
		RULE_forStatement = 34, RULE_returnStatement = 35, RULE_continueStatement = 36, 
		RULE_breakStatement = 37, RULE_readStatement = 38, RULE_printStatement = 39, 
		RULE_expression = 40, RULE_logicalOrExpression = 41, RULE_logicalAndExpression = 42, 
		RULE_equalityExpression = 43, RULE_comparisonExpression = 44, RULE_additiveExpression = 45, 
		RULE_multiplicativeExpression = 46, RULE_unaryExpression = 47, RULE_postfixExpression = 48, 
		RULE_functionArguments = 49, RULE_argumentList = 50, RULE_primaryExpression = 51;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "importDeclaration", "importPath", "globalVariablesSection", 
			"functionSection", "mainSection", "functionDeclaration", "procedureDeclaration", 
			"functionWithReturn", "parameterList", "parameter", "functionBody", "declaration", 
			"localVariableSection", "variableDeclaration", "initializer", "structInitializer", 
			"structFieldInitializer", "arrayConstructor", "arrayDeclaration", "arrayInitializer", 
			"type", "statement", "assignment", "incrementStatement", "incrementableExpression", 
			"functionCallStatement", "callableExpression", "ifStatement", "elseIfStatement", 
			"elseStatement", "block", "whileStatement", "doWhileStatement", "forStatement", 
			"returnStatement", "continueStatement", "breakStatement", "readStatement", 
			"printStatement", "expression", "logicalOrExpression", "logicalAndExpression", 
			"equalityExpression", "comparisonExpression", "additiveExpression", "multiplicativeExpression", 
			"unaryExpression", "postfixExpression", "functionArguments", "argumentList", 
			"primaryExpression"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'.'", "'('", "')'", "','", "':'", "'{'", "'}'", "'['", 
			"']'", "'='", "'VARIABILES>'", "'MUNERA>'", "'MAIOR>'", "'FINIS'", "'finis'", 
			"'esto'", "'series'", "'actio'", "'ratio'", "'si'", "'aliter'", "'dum'", 
			"'facere'", "'per'", "'reddere'", "'perge'", "'interrumpe'", "'VARIABILES'", 
			"'import'", "'novus'", "'numerus'", "'decimalis'", "'textum'", "'littera'", 
			"'bool'", "'verum'", "'falsus'", "'+'", "'-'", "'*'", "'/'", "'++'", 
			"'--'", "'&&'", "'||'", "'non'", "'=='", "'!='", "'<'", "'>'", "'<='", 
			"'>='", "'<<'", "'>>'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"VARIABLES_SECTION", "FUNCTIONS_SECTION", "MAIN_SECTION", "FINIS_PROGRAM", 
			"FINIS", "ESTO", "SERIES", "ACTIO", "RATIO", "SI", "ALITER", "DUM", "FACERE", 
			"PER", "REDDERE", "PERGE", "INTERRUMPE", "VARIABILES", "IMPORT", "NOVUS", 
			"NUMERUS", "DECIMALIS", "TEXTUM", "LITTERA", "BOOL", "VERUM", "FALSUS", 
			"PLUS", "MINUS", "MULT", "DIV", "PLUSPLUS", "MINUSMINUS", "AND", "OR", 
			"NON", "EQUAL", "NOT_EQUAL", "LESS", "GREATER", "LESS_EQUAL", "GREATER_EQUAL", 
			"READ", "PRINT", "NUMBER", "DECIMAL", "STRING", "CHAR", "ID", "LINE_COMMENT", 
			"BLOCK_COMMENT", "WS"
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
	public String getGrammarFileName() { return "PigLatin.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PigLatinParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public MainSectionContext mainSection() {
			return getRuleContext(MainSectionContext.class,0);
		}
		public TerminalNode FINIS_PROGRAM() { return getToken(PigLatinParser.FINIS_PROGRAM, 0); }
		public TerminalNode EOF() { return getToken(PigLatinParser.EOF, 0); }
		public List<ImportDeclarationContext> importDeclaration() {
			return getRuleContexts(ImportDeclarationContext.class);
		}
		public ImportDeclarationContext importDeclaration(int i) {
			return getRuleContext(ImportDeclarationContext.class,i);
		}
		public GlobalVariablesSectionContext globalVariablesSection() {
			return getRuleContext(GlobalVariablesSectionContext.class,0);
		}
		public FunctionSectionContext functionSection() {
			return getRuleContext(FunctionSectionContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IMPORT) {
				{
				{
				setState(104);
				importDeclaration();
				}
				}
				setState(109);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(111);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VARIABLES_SECTION) {
				{
				setState(110);
				globalVariablesSection();
				}
			}

			setState(114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FUNCTIONS_SECTION) {
				{
				setState(113);
				functionSection();
				}
			}

			setState(116);
			mainSection();
			setState(117);
			match(FINIS_PROGRAM);
			setState(118);
			match(T__0);
			setState(119);
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
	public static class ImportDeclarationContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(PigLatinParser.IMPORT, 0); }
		public ImportPathContext importPath() {
			return getRuleContext(ImportPathContext.class,0);
		}
		public ImportDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterImportDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitImportDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitImportDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportDeclarationContext importDeclaration() throws RecognitionException {
		ImportDeclarationContext _localctx = new ImportDeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_importDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(IMPORT);
			setState(122);
			importPath();
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
	public static class ImportPathContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(PigLatinParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PigLatinParser.ID, i);
		}
		public ImportPathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importPath; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterImportPath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitImportPath(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitImportPath(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportPathContext importPath() throws RecognitionException {
		ImportPathContext _localctx = new ImportPathContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_importPath);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(ID);
			setState(127); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(125);
				match(T__1);
				setState(126);
				match(ID);
				}
				}
				setState(129); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__1 );
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
	public static class GlobalVariablesSectionContext extends ParserRuleContext {
		public TerminalNode VARIABLES_SECTION() { return getToken(PigLatinParser.VARIABLES_SECTION, 0); }
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public GlobalVariablesSectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_globalVariablesSection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterGlobalVariablesSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitGlobalVariablesSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitGlobalVariablesSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GlobalVariablesSectionContext globalVariablesSection() throws RecognitionException {
		GlobalVariablesSectionContext _localctx = new GlobalVariablesSectionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_globalVariablesSection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(VARIABLES_SECTION);
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ESTO || _la==SERIES) {
				{
				{
				setState(132);
				declaration();
				}
				}
				setState(137);
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
	public static class FunctionSectionContext extends ParserRuleContext {
		public TerminalNode FUNCTIONS_SECTION() { return getToken(PigLatinParser.FUNCTIONS_SECTION, 0); }
		public List<FunctionDeclarationContext> functionDeclaration() {
			return getRuleContexts(FunctionDeclarationContext.class);
		}
		public FunctionDeclarationContext functionDeclaration(int i) {
			return getRuleContext(FunctionDeclarationContext.class,i);
		}
		public FunctionSectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionSection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterFunctionSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitFunctionSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitFunctionSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionSectionContext functionSection() throws RecognitionException {
		FunctionSectionContext _localctx = new FunctionSectionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_functionSection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(FUNCTIONS_SECTION);
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ACTIO || _la==RATIO) {
				{
				{
				setState(139);
				functionDeclaration();
				}
				}
				setState(144);
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
	public static class MainSectionContext extends ParserRuleContext {
		public TerminalNode MAIN_SECTION() { return getToken(PigLatinParser.MAIN_SECTION, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public MainSectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mainSection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterMainSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitMainSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitMainSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MainSectionContext mainSection() throws RecognitionException {
		MainSectionContext _localctx = new MainSectionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_mainSection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(MAIN_SECTION);
			setState(149);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2287829025699135496L) != 0)) {
				{
				{
				setState(146);
				statement();
				}
				}
				setState(151);
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
	public static class FunctionDeclarationContext extends ParserRuleContext {
		public ProcedureDeclarationContext procedureDeclaration() {
			return getRuleContext(ProcedureDeclarationContext.class,0);
		}
		public FunctionWithReturnContext functionWithReturn() {
			return getRuleContext(FunctionWithReturnContext.class,0);
		}
		public FunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterFunctionDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitFunctionDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitFunctionDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDeclarationContext functionDeclaration() throws RecognitionException {
		FunctionDeclarationContext _localctx = new FunctionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_functionDeclaration);
		try {
			setState(154);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ACTIO:
				enterOuterAlt(_localctx, 1);
				{
				setState(152);
				procedureDeclaration();
				}
				break;
			case RATIO:
				enterOuterAlt(_localctx, 2);
				{
				setState(153);
				functionWithReturn();
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
	public static class ProcedureDeclarationContext extends ParserRuleContext {
		public TerminalNode ACTIO() { return getToken(PigLatinParser.ACTIO, 0); }
		public TerminalNode ID() { return getToken(PigLatinParser.ID, 0); }
		public FunctionBodyContext functionBody() {
			return getRuleContext(FunctionBodyContext.class,0);
		}
		public TerminalNode FINIS() { return getToken(PigLatinParser.FINIS, 0); }
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public ProcedureDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procedureDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterProcedureDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitProcedureDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitProcedureDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProcedureDeclarationContext procedureDeclaration() throws RecognitionException {
		ProcedureDeclarationContext _localctx = new ProcedureDeclarationContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_procedureDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			match(ACTIO);
			setState(157);
			match(ID);
			setState(158);
			match(T__2);
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ESTO) {
				{
				setState(159);
				parameterList();
				}
			}

			setState(162);
			match(T__3);
			setState(163);
			functionBody();
			setState(164);
			match(FINIS);
			setState(165);
			match(T__0);
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
	public static class FunctionWithReturnContext extends ParserRuleContext {
		public TerminalNode RATIO() { return getToken(PigLatinParser.RATIO, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(PigLatinParser.ID, 0); }
		public FunctionBodyContext functionBody() {
			return getRuleContext(FunctionBodyContext.class,0);
		}
		public TerminalNode FINIS() { return getToken(PigLatinParser.FINIS, 0); }
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public FunctionWithReturnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionWithReturn; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterFunctionWithReturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitFunctionWithReturn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitFunctionWithReturn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionWithReturnContext functionWithReturn() throws RecognitionException {
		FunctionWithReturnContext _localctx = new FunctionWithReturnContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_functionWithReturn);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			match(RATIO);
			setState(168);
			type();
			setState(169);
			match(ID);
			setState(170);
			match(T__2);
			setState(172);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ESTO) {
				{
				setState(171);
				parameterList();
				}
			}

			setState(174);
			match(T__3);
			setState(175);
			functionBody();
			setState(176);
			match(FINIS);
			setState(177);
			match(T__0);
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
		public ParameterContext parameter;
		public List<ParameterContext> parameters = new ArrayList<ParameterContext>();
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public ParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitParameterList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_parameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			((ParameterListContext)_localctx).parameter = parameter();
			((ParameterListContext)_localctx).parameters.add(((ParameterListContext)_localctx).parameter);
			setState(184);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(180);
				match(T__4);
				setState(181);
				((ParameterListContext)_localctx).parameter = parameter();
				((ParameterListContext)_localctx).parameters.add(((ParameterListContext)_localctx).parameter);
				}
				}
				setState(186);
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
		public TerminalNode ESTO() { return getToken(PigLatinParser.ESTO, 0); }
		public TerminalNode ID() { return getToken(PigLatinParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			match(ESTO);
			setState(188);
			match(ID);
			setState(189);
			match(T__5);
			setState(190);
			type();
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
	public static class FunctionBodyContext extends ParserRuleContext {
		public LocalVariableSectionContext localVariableSection() {
			return getRuleContext(LocalVariableSectionContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public FunctionBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterFunctionBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitFunctionBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitFunctionBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionBodyContext functionBody() throws RecognitionException {
		FunctionBodyContext _localctx = new FunctionBodyContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_functionBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			match(T__6);
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VARIABILES) {
				{
				setState(193);
				localVariableSection();
				}
			}

			setState(199);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2287829025699135496L) != 0)) {
				{
				{
				setState(196);
				statement();
				}
				}
				setState(201);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(202);
			match(T__7);
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
	public static class DeclarationContext extends ParserRuleContext {
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public ArrayDeclarationContext arrayDeclaration() {
			return getRuleContext(ArrayDeclarationContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_declaration);
		try {
			setState(206);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ESTO:
				enterOuterAlt(_localctx, 1);
				{
				setState(204);
				variableDeclaration();
				}
				break;
			case SERIES:
				enterOuterAlt(_localctx, 2);
				{
				setState(205);
				arrayDeclaration();
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
	public static class LocalVariableSectionContext extends ParserRuleContext {
		public TerminalNode VARIABILES() { return getToken(PigLatinParser.VARIABILES, 0); }
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public LocalVariableSectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_localVariableSection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterLocalVariableSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitLocalVariableSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitLocalVariableSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocalVariableSectionContext localVariableSection() throws RecognitionException {
		LocalVariableSectionContext _localctx = new LocalVariableSectionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_localVariableSection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			match(VARIABILES);
			setState(209);
			match(T__8);
			setState(213);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ESTO || _la==SERIES) {
				{
				{
				setState(210);
				declaration();
				}
				}
				setState(215);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(216);
			match(T__9);
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
	public static class VariableDeclarationContext extends ParserRuleContext {
		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaration; }
	 
		public VariableDeclarationContext() { }
		public void copyFrom(VariableDeclarationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NormalVarDeclarationContext extends VariableDeclarationContext {
		public TerminalNode ESTO() { return getToken(PigLatinParser.ESTO, 0); }
		public TerminalNode ID() { return getToken(PigLatinParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StructInitializerContext structInitializer() {
			return getRuleContext(StructInitializerContext.class,0);
		}
		public NormalVarDeclarationContext(VariableDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterNormalVarDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitNormalVarDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitNormalVarDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_variableDeclaration);
		int _la;
		try {
			setState(239);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				_localctx = new NormalVarDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(218);
				match(ESTO);
				setState(219);
				match(ID);
				setState(220);
				match(T__5);
				setState(221);
				type();
				setState(222);
				match(T__0);
				}
				break;
			case 2:
				_localctx = new NormalVarDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(224);
				match(ESTO);
				setState(225);
				match(ID);
				setState(226);
				match(T__5);
				setState(227);
				type();
				setState(228);
				expression();
				setState(229);
				match(T__0);
				}
				break;
			case 3:
				_localctx = new NormalVarDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(231);
				match(ESTO);
				setState(232);
				match(ID);
				setState(233);
				match(T__5);
				setState(234);
				type();
				setState(235);
				structInitializer();
				setState(237);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(236);
					match(T__0);
					}
				}

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
	public static class ExprInitContext extends InitializerContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprInitContext(InitializerContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterExprInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitExprInit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitExprInit(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StructInitContext extends InitializerContext {
		public StructInitializerContext structInitializer() {
			return getRuleContext(StructInitializerContext.class,0);
		}
		public StructInitContext(InitializerContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterStructInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitStructInit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitStructInit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitializerContext initializer() throws RecognitionException {
		InitializerContext _localctx = new InitializerContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_initializer);
		try {
			setState(243);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
			case NOVUS:
			case VERUM:
			case FALSUS:
			case MINUS:
			case NON:
			case NUMBER:
			case DECIMAL:
			case STRING:
			case CHAR:
			case ID:
				_localctx = new ExprInitContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(241);
				expression();
				}
				break;
			case T__6:
				_localctx = new StructInitContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(242);
				structInitializer();
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
	public static class StructInitializerContext extends ParserRuleContext {
		public StructFieldInitializerContext structFieldInitializer;
		public List<StructFieldInitializerContext> fields = new ArrayList<StructFieldInitializerContext>();
		public List<StructFieldInitializerContext> structFieldInitializer() {
			return getRuleContexts(StructFieldInitializerContext.class);
		}
		public StructFieldInitializerContext structFieldInitializer(int i) {
			return getRuleContext(StructFieldInitializerContext.class,i);
		}
		public StructInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterStructInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitStructInitializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitStructInitializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructInitializerContext structInitializer() throws RecognitionException {
		StructInitializerContext _localctx = new StructInitializerContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_structInitializer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			match(T__6);
			setState(246);
			((StructInitializerContext)_localctx).structFieldInitializer = structFieldInitializer();
			((StructInitializerContext)_localctx).fields.add(((StructInitializerContext)_localctx).structFieldInitializer);
			setState(251);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(247);
				match(T__4);
				setState(248);
				((StructInitializerContext)_localctx).structFieldInitializer = structFieldInitializer();
				((StructInitializerContext)_localctx).fields.add(((StructInitializerContext)_localctx).structFieldInitializer);
				}
				}
				setState(253);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(254);
			match(T__7);
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
	public static class StructFieldInitializerContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(PigLatinParser.ID, 0); }
		public InitializerContext initializer() {
			return getRuleContext(InitializerContext.class,0);
		}
		public StructFieldInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structFieldInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterStructFieldInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitStructFieldInitializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitStructFieldInitializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructFieldInitializerContext structFieldInitializer() throws RecognitionException {
		StructFieldInitializerContext _localctx = new StructFieldInitializerContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_structFieldInitializer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			match(ID);
			setState(257);
			match(T__5);
			setState(258);
			initializer();
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
	public static class ArrayConstructorContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArrayConstructorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayConstructor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterArrayConstructor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitArrayConstructor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitArrayConstructor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayConstructorContext arrayConstructor() throws RecognitionException {
		ArrayConstructorContext _localctx = new ArrayConstructorContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_arrayConstructor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			type();
			setState(261);
			match(T__8);
			setState(262);
			expression();
			setState(263);
			match(T__9);
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
	public static class ArrayDeclarationContext extends ParserRuleContext {
		public TerminalNode SERIES() { return getToken(PigLatinParser.SERIES, 0); }
		public TerminalNode ID() { return getToken(PigLatinParser.ID, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ArrayInitializerContext arrayInitializer() {
			return getRuleContext(ArrayInitializerContext.class,0);
		}
		public ArrayDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterArrayDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitArrayDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitArrayDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayDeclarationContext arrayDeclaration() throws RecognitionException {
		ArrayDeclarationContext _localctx = new ArrayDeclarationContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_arrayDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(265);
			match(SERIES);
			setState(266);
			match(ID);
			setState(267);
			match(T__8);
			setState(268);
			expression();
			setState(269);
			match(T__9);
			setState(272);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(270);
				match(T__5);
				setState(271);
				type();
				}
			}

			setState(275);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(274);
				arrayInitializer();
				}
			}

			setState(277);
			match(T__0);
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
		public ExpressionContext expression;
		public List<ExpressionContext> values = new ArrayList<ExpressionContext>();
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArrayInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterArrayInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitArrayInitializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitArrayInitializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayInitializerContext arrayInitializer() throws RecognitionException {
		ArrayInitializerContext _localctx = new ArrayInitializerContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_arrayInitializer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(279);
			match(T__6);
			setState(280);
			((ArrayInitializerContext)_localctx).expression = expression();
			((ArrayInitializerContext)_localctx).values.add(((ArrayInitializerContext)_localctx).expression);
			setState(285);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(281);
				match(T__4);
				setState(282);
				((ArrayInitializerContext)_localctx).expression = expression();
				((ArrayInitializerContext)_localctx).values.add(((ArrayInitializerContext)_localctx).expression);
				}
				}
				setState(287);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(288);
			match(T__7);
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
		public TerminalNode NUMERUS() { return getToken(PigLatinParser.NUMERUS, 0); }
		public TerminalNode DECIMALIS() { return getToken(PigLatinParser.DECIMALIS, 0); }
		public TerminalNode TEXTUM() { return getToken(PigLatinParser.TEXTUM, 0); }
		public TerminalNode BOOL() { return getToken(PigLatinParser.BOOL, 0); }
		public TerminalNode LITTERA() { return getToken(PigLatinParser.LITTERA, 0); }
		public TerminalNode ID() { return getToken(PigLatinParser.ID, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1152921637750833152L) != 0)) ) {
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
	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ContinueStmtContext extends StatementContext {
		public ContinueStatementContext continueStatement() {
			return getRuleContext(ContinueStatementContext.class,0);
		}
		public ContinueStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterContinueStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitContinueStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitContinueStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IfStmtContext extends StatementContext {
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public IfStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterIfStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitIfStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitIfStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrintStmtContext extends StatementContext {
		public PrintStatementContext printStatement() {
			return getRuleContext(PrintStatementContext.class,0);
		}
		public PrintStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterPrintStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitPrintStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitPrintStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WhileStmtContext extends StatementContext {
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public WhileStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterWhileStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitWhileStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitWhileStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BreakStmtContext extends StatementContext {
		public BreakStatementContext breakStatement() {
			return getRuleContext(BreakStatementContext.class,0);
		}
		public BreakStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterBreakStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitBreakStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitBreakStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentStmtContext extends StatementContext {
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public AssignmentStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterAssignmentStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitAssignmentStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitAssignmentStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FunctionCallStatementStmtContext extends StatementContext {
		public FunctionCallStatementContext functionCallStatement() {
			return getRuleContext(FunctionCallStatementContext.class,0);
		}
		public FunctionCallStatementStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterFunctionCallStatementStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitFunctionCallStatementStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitFunctionCallStatementStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ReadStmtContext extends StatementContext {
		public ReadStatementContext readStatement() {
			return getRuleContext(ReadStatementContext.class,0);
		}
		public ReadStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterReadStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitReadStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitReadStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IncrementStmtContext extends StatementContext {
		public IncrementStatementContext incrementStatement() {
			return getRuleContext(IncrementStatementContext.class,0);
		}
		public IncrementStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterIncrementStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitIncrementStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitIncrementStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DoWhileStmtContext extends StatementContext {
		public DoWhileStatementContext doWhileStatement() {
			return getRuleContext(DoWhileStatementContext.class,0);
		}
		public DoWhileStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterDoWhileStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitDoWhileStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitDoWhileStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ForStmtContext extends StatementContext {
		public ForStatementContext forStatement() {
			return getRuleContext(ForStatementContext.class,0);
		}
		public ForStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterForStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitForStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitForStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ReturnStmtContext extends StatementContext {
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public ReturnStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterReturnStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitReturnStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitReturnStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_statement);
		try {
			setState(304);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				_localctx = new AssignmentStmtContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(292);
				assignment();
				}
				break;
			case 2:
				_localctx = new IncrementStmtContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(293);
				incrementStatement();
				}
				break;
			case 3:
				_localctx = new FunctionCallStatementStmtContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(294);
				functionCallStatement();
				}
				break;
			case 4:
				_localctx = new IfStmtContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(295);
				ifStatement();
				}
				break;
			case 5:
				_localctx = new WhileStmtContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(296);
				whileStatement();
				}
				break;
			case 6:
				_localctx = new DoWhileStmtContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(297);
				doWhileStatement();
				}
				break;
			case 7:
				_localctx = new ForStmtContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(298);
				forStatement();
				}
				break;
			case 8:
				_localctx = new ReturnStmtContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(299);
				returnStatement();
				}
				break;
			case 9:
				_localctx = new BreakStmtContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(300);
				breakStatement();
				}
				break;
			case 10:
				_localctx = new ContinueStmtContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(301);
				continueStatement();
				}
				break;
			case 11:
				_localctx = new PrintStmtContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(302);
				printStatement();
				}
				break;
			case 12:
				_localctx = new ReadStmtContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(303);
				readStatement();
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
	public static class AssignmentContext extends ParserRuleContext {
		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StructInitializerContext structInitializer() {
			return getRuleContext(StructInitializerContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_assignment);
		int _la;
		try {
			setState(317);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(306);
				postfixExpression(0);
				setState(307);
				match(T__10);
				setState(308);
				expression();
				setState(309);
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(311);
				postfixExpression(0);
				setState(312);
				match(T__10);
				setState(313);
				structInitializer();
				setState(315);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(314);
					match(T__0);
					}
				}

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
	public static class IncrementStatementContext extends ParserRuleContext {
		public IncrementableExpressionContext incrementableExpression() {
			return getRuleContext(IncrementableExpressionContext.class,0);
		}
		public TerminalNode PLUSPLUS() { return getToken(PigLatinParser.PLUSPLUS, 0); }
		public TerminalNode MINUSMINUS() { return getToken(PigLatinParser.MINUSMINUS, 0); }
		public IncrementStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_incrementStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterIncrementStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitIncrementStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitIncrementStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IncrementStatementContext incrementStatement() throws RecognitionException {
		IncrementStatementContext _localctx = new IncrementStatementContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_incrementStatement);
		try {
			setState(327);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(319);
				incrementableExpression();
				setState(320);
				match(PLUSPLUS);
				setState(321);
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(323);
				incrementableExpression();
				setState(324);
				match(MINUSMINUS);
				setState(325);
				match(T__0);
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
	public static class IncrementableExpressionContext extends ParserRuleContext {
		public IncrementableExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_incrementableExpression; }
	 
		public IncrementableExpressionContext() { }
		public void copyFrom(IncrementableExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IncrementVariableContext extends IncrementableExpressionContext {
		public TerminalNode ID() { return getToken(PigLatinParser.ID, 0); }
		public IncrementVariableContext(IncrementableExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterIncrementVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitIncrementVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitIncrementVariable(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IncrementMemberAccessContext extends IncrementableExpressionContext {
		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class,0);
		}
		public TerminalNode ID() { return getToken(PigLatinParser.ID, 0); }
		public IncrementMemberAccessContext(IncrementableExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterIncrementMemberAccess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitIncrementMemberAccess(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitIncrementMemberAccess(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IncrementArrayAccessContext extends IncrementableExpressionContext {
		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IncrementArrayAccessContext(IncrementableExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterIncrementArrayAccess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitIncrementArrayAccess(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitIncrementArrayAccess(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IncrementableExpressionContext incrementableExpression() throws RecognitionException {
		IncrementableExpressionContext _localctx = new IncrementableExpressionContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_incrementableExpression);
		try {
			setState(339);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				_localctx = new IncrementVariableContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(329);
				match(ID);
				}
				break;
			case 2:
				_localctx = new IncrementArrayAccessContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(330);
				postfixExpression(0);
				setState(331);
				match(T__8);
				setState(332);
				expression();
				setState(333);
				match(T__9);
				}
				break;
			case 3:
				_localctx = new IncrementMemberAccessContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(335);
				postfixExpression(0);
				setState(336);
				match(T__1);
				setState(337);
				match(ID);
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
	public static class FunctionCallStatementContext extends ParserRuleContext {
		public CallableExpressionContext callableExpression() {
			return getRuleContext(CallableExpressionContext.class,0);
		}
		public FunctionArgumentsContext functionArguments() {
			return getRuleContext(FunctionArgumentsContext.class,0);
		}
		public FunctionCallStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCallStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterFunctionCallStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitFunctionCallStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitFunctionCallStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallStatementContext functionCallStatement() throws RecognitionException {
		FunctionCallStatementContext _localctx = new FunctionCallStatementContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_functionCallStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(341);
			callableExpression();
			setState(342);
			functionArguments();
			setState(343);
			match(T__0);
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
	public static class CallableExpressionContext extends ParserRuleContext {
		public CallableExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callableExpression; }
	 
		public CallableExpressionContext() { }
		public void copyFrom(CallableExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CallArrayAccessContext extends CallableExpressionContext {
		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public CallArrayAccessContext(CallableExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterCallArrayAccess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitCallArrayAccess(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitCallArrayAccess(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CallVariableContext extends CallableExpressionContext {
		public TerminalNode ID() { return getToken(PigLatinParser.ID, 0); }
		public CallVariableContext(CallableExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterCallVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitCallVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitCallVariable(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CallMemberAccessContext extends CallableExpressionContext {
		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class,0);
		}
		public TerminalNode ID() { return getToken(PigLatinParser.ID, 0); }
		public CallMemberAccessContext(CallableExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterCallMemberAccess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitCallMemberAccess(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitCallMemberAccess(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallableExpressionContext callableExpression() throws RecognitionException {
		CallableExpressionContext _localctx = new CallableExpressionContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_callableExpression);
		try {
			setState(355);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				_localctx = new CallVariableContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(345);
				match(ID);
				}
				break;
			case 2:
				_localctx = new CallArrayAccessContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(346);
				postfixExpression(0);
				setState(347);
				match(T__8);
				setState(348);
				expression();
				setState(349);
				match(T__9);
				}
				break;
			case 3:
				_localctx = new CallMemberAccessContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(351);
				postfixExpression(0);
				setState(352);
				match(T__1);
				setState(353);
				match(ID);
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
	public static class IfStatementContext extends ParserRuleContext {
		public TerminalNode SI() { return getToken(PigLatinParser.SI, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode FINIS() { return getToken(PigLatinParser.FINIS, 0); }
		public List<ElseIfStatementContext> elseIfStatement() {
			return getRuleContexts(ElseIfStatementContext.class);
		}
		public ElseIfStatementContext elseIfStatement(int i) {
			return getRuleContext(ElseIfStatementContext.class,i);
		}
		public ElseStatementContext elseStatement() {
			return getRuleContext(ElseStatementContext.class,0);
		}
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_ifStatement);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(357);
			match(SI);
			setState(358);
			match(T__2);
			setState(359);
			expression();
			setState(360);
			match(T__3);
			setState(361);
			block();
			setState(365);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(362);
					elseIfStatement();
					}
					} 
				}
				setState(367);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			}
			setState(369);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ALITER) {
				{
				setState(368);
				elseStatement();
				}
			}

			setState(371);
			match(FINIS);
			setState(372);
			match(T__0);
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
	public static class ElseIfStatementContext extends ParserRuleContext {
		public TerminalNode ALITER() { return getToken(PigLatinParser.ALITER, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ElseIfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseIfStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterElseIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitElseIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitElseIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseIfStatementContext elseIfStatement() throws RecognitionException {
		ElseIfStatementContext _localctx = new ElseIfStatementContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_elseIfStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(374);
			match(ALITER);
			setState(375);
			match(T__2);
			setState(376);
			expression();
			setState(377);
			match(T__3);
			setState(378);
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
	public static class ElseStatementContext extends ParserRuleContext {
		public TerminalNode ALITER() { return getToken(PigLatinParser.ALITER, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ElseStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterElseStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitElseStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitElseStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseStatementContext elseStatement() throws RecognitionException {
		ElseStatementContext _localctx = new ElseStatementContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_elseStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(380);
			match(ALITER);
			setState(381);
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
	public static class BlockContext extends ParserRuleContext {
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
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(383);
			match(T__6);
			setState(387);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2287829025699135496L) != 0)) {
				{
				{
				setState(384);
				statement();
				}
				}
				setState(389);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(390);
			match(T__7);
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
		public TerminalNode DUM() { return getToken(PigLatinParser.DUM, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode FINIS() { return getToken(PigLatinParser.FINIS, 0); }
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitWhileStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(392);
			match(DUM);
			setState(393);
			match(T__2);
			setState(394);
			expression();
			setState(395);
			match(T__3);
			setState(396);
			block();
			setState(397);
			match(FINIS);
			setState(398);
			match(T__0);
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
		public TerminalNode FACERE() { return getToken(PigLatinParser.FACERE, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode DUM() { return getToken(PigLatinParser.DUM, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DoWhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doWhileStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterDoWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitDoWhileStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitDoWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoWhileStatementContext doWhileStatement() throws RecognitionException {
		DoWhileStatementContext _localctx = new DoWhileStatementContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_doWhileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(400);
			match(FACERE);
			setState(401);
			block();
			setState(402);
			match(DUM);
			setState(403);
			match(T__2);
			setState(404);
			expression();
			setState(405);
			match(T__3);
			setState(406);
			match(T__0);
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
		public TerminalNode PER() { return getToken(PigLatinParser.PER, 0); }
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ForStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterForStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitForStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitForStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStatementContext forStatement() throws RecognitionException {
		ForStatementContext _localctx = new ForStatementContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_forStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(408);
			match(PER);
			setState(409);
			match(T__2);
			setState(410);
			variableDeclaration();
			setState(411);
			expression();
			setState(412);
			match(T__0);
			setState(413);
			expression();
			setState(414);
			match(T__3);
			setState(415);
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
	public static class ReturnStatementContext extends ParserRuleContext {
		public TerminalNode REDDERE() { return getToken(PigLatinParser.REDDERE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitReturnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_returnStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(417);
			match(REDDERE);
			setState(419);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2233927666640093192L) != 0)) {
				{
				setState(418);
				expression();
				}
			}

			setState(421);
			match(T__0);
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
		public TerminalNode PERGE() { return getToken(PigLatinParser.PERGE, 0); }
		public ContinueStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterContinueStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitContinueStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitContinueStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContinueStatementContext continueStatement() throws RecognitionException {
		ContinueStatementContext _localctx = new ContinueStatementContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_continueStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(423);
			match(PERGE);
			setState(424);
			match(T__0);
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
		public TerminalNode INTERRUMPE() { return getToken(PigLatinParser.INTERRUMPE, 0); }
		public BreakStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterBreakStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitBreakStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitBreakStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreakStatementContext breakStatement() throws RecognitionException {
		BreakStatementContext _localctx = new BreakStatementContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_breakStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(426);
			match(INTERRUMPE);
			setState(427);
			match(T__0);
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
	public static class ReadStatementContext extends ParserRuleContext {
		public TerminalNode READ() { return getToken(PigLatinParser.READ, 0); }
		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class,0);
		}
		public ReadStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_readStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterReadStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitReadStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitReadStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReadStatementContext readStatement() throws RecognitionException {
		ReadStatementContext _localctx = new ReadStatementContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_readStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(430);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2233785829640110088L) != 0)) {
				{
				setState(429);
				postfixExpression(0);
				}
			}

			setState(432);
			match(READ);
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
		public ExpressionContext expression;
		public List<ExpressionContext> expressions = new ArrayList<ExpressionContext>();
		public List<TerminalNode> PRINT() { return getTokens(PigLatinParser.PRINT); }
		public TerminalNode PRINT(int i) {
			return getToken(PigLatinParser.PRINT, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public PrintStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_printStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterPrintStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitPrintStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitPrintStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintStatementContext printStatement() throws RecognitionException {
		PrintStatementContext _localctx = new PrintStatementContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_printStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(434);
			match(PRINT);
			setState(435);
			((PrintStatementContext)_localctx).expression = expression();
			((PrintStatementContext)_localctx).expressions.add(((PrintStatementContext)_localctx).expression);
			setState(440);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PRINT) {
				{
				{
				setState(436);
				match(PRINT);
				setState(437);
				((PrintStatementContext)_localctx).expression = expression();
				((PrintStatementContext)_localctx).expressions.add(((PrintStatementContext)_localctx).expression);
				}
				}
				setState(442);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(443);
			match(T__0);
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
		public LogicalOrExpressionContext logicalOrExpression() {
			return getRuleContext(LogicalOrExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(445);
			logicalOrExpression(0);
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
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterToLogicalAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitToLogicalAndExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitToLogicalAndExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OrExprContext extends LogicalOrExpressionContext {
		public LogicalOrExpressionContext logicalOrExpression() {
			return getRuleContext(LogicalOrExpressionContext.class,0);
		}
		public TerminalNode OR() { return getToken(PigLatinParser.OR, 0); }
		public LogicalAndExpressionContext logicalAndExpression() {
			return getRuleContext(LogicalAndExpressionContext.class,0);
		}
		public OrExprContext(LogicalOrExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitOrExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitOrExpr(this);
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
		int _startState = 82;
		enterRecursionRule(_localctx, 82, RULE_logicalOrExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new ToLogicalAndExprContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(448);
			logicalAndExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(455);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new OrExprContext(new LogicalOrExpressionContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_logicalOrExpression);
					setState(450);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(451);
					match(OR);
					setState(452);
					logicalAndExpression(0);
					}
					} 
				}
				setState(457);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
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
		public TerminalNode AND() { return getToken(PigLatinParser.AND, 0); }
		public EqualityExpressionContext equalityExpression() {
			return getRuleContext(EqualityExpressionContext.class,0);
		}
		public AndExprContext(LogicalAndExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitAndExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitAndExpr(this);
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
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterToEqualityExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitToEqualityExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitToEqualityExpr(this);
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
		int _startState = 84;
		enterRecursionRule(_localctx, 84, RULE_logicalAndExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new ToEqualityExprContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(459);
			equalityExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(466);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new AndExprContext(new LogicalAndExpressionContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_logicalAndExpression);
					setState(461);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(462);
					match(AND);
					setState(463);
					equalityExpression(0);
					}
					} 
				}
				setState(468);
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
		public TerminalNode EQUAL() { return getToken(PigLatinParser.EQUAL, 0); }
		public ComparisonExpressionContext comparisonExpression() {
			return getRuleContext(ComparisonExpressionContext.class,0);
		}
		public EqualExprContext(EqualityExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterEqualExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitEqualExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitEqualExpr(this);
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
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterToComparisonExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitToComparisonExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitToComparisonExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NotEqualExprContext extends EqualityExpressionContext {
		public EqualityExpressionContext equalityExpression() {
			return getRuleContext(EqualityExpressionContext.class,0);
		}
		public TerminalNode NOT_EQUAL() { return getToken(PigLatinParser.NOT_EQUAL, 0); }
		public ComparisonExpressionContext comparisonExpression() {
			return getRuleContext(ComparisonExpressionContext.class,0);
		}
		public NotEqualExprContext(EqualityExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterNotEqualExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitNotEqualExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitNotEqualExpr(this);
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
		int _startState = 86;
		enterRecursionRule(_localctx, 86, RULE_equalityExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new ToComparisonExprContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(470);
			comparisonExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(480);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(478);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
					case 1:
						{
						_localctx = new EqualExprContext(new EqualityExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_equalityExpression);
						setState(472);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(473);
						match(EQUAL);
						setState(474);
						comparisonExpression(0);
						}
						break;
					case 2:
						{
						_localctx = new NotEqualExprContext(new EqualityExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_equalityExpression);
						setState(475);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(476);
						match(NOT_EQUAL);
						setState(477);
						comparisonExpression(0);
						}
						break;
					}
					} 
				}
				setState(482);
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
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterToAdditiveExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitToAdditiveExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitToAdditiveExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GreaterExprContext extends ComparisonExpressionContext {
		public ComparisonExpressionContext comparisonExpression() {
			return getRuleContext(ComparisonExpressionContext.class,0);
		}
		public TerminalNode GREATER() { return getToken(PigLatinParser.GREATER, 0); }
		public AdditiveExpressionContext additiveExpression() {
			return getRuleContext(AdditiveExpressionContext.class,0);
		}
		public GreaterExprContext(ComparisonExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterGreaterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitGreaterExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitGreaterExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GreaterEqualExprContext extends ComparisonExpressionContext {
		public ComparisonExpressionContext comparisonExpression() {
			return getRuleContext(ComparisonExpressionContext.class,0);
		}
		public TerminalNode GREATER_EQUAL() { return getToken(PigLatinParser.GREATER_EQUAL, 0); }
		public AdditiveExpressionContext additiveExpression() {
			return getRuleContext(AdditiveExpressionContext.class,0);
		}
		public GreaterEqualExprContext(ComparisonExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterGreaterEqualExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitGreaterEqualExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitGreaterEqualExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LessExprContext extends ComparisonExpressionContext {
		public ComparisonExpressionContext comparisonExpression() {
			return getRuleContext(ComparisonExpressionContext.class,0);
		}
		public TerminalNode LESS() { return getToken(PigLatinParser.LESS, 0); }
		public AdditiveExpressionContext additiveExpression() {
			return getRuleContext(AdditiveExpressionContext.class,0);
		}
		public LessExprContext(ComparisonExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterLessExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitLessExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitLessExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LessEqualExprContext extends ComparisonExpressionContext {
		public ComparisonExpressionContext comparisonExpression() {
			return getRuleContext(ComparisonExpressionContext.class,0);
		}
		public TerminalNode LESS_EQUAL() { return getToken(PigLatinParser.LESS_EQUAL, 0); }
		public AdditiveExpressionContext additiveExpression() {
			return getRuleContext(AdditiveExpressionContext.class,0);
		}
		public LessEqualExprContext(ComparisonExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterLessEqualExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitLessEqualExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitLessEqualExpr(this);
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
		int _startState = 88;
		enterRecursionRule(_localctx, 88, RULE_comparisonExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new ToAdditiveExprContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(484);
			additiveExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(500);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(498);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
					case 1:
						{
						_localctx = new LessExprContext(new ComparisonExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_comparisonExpression);
						setState(486);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(487);
						match(LESS);
						setState(488);
						additiveExpression(0);
						}
						break;
					case 2:
						{
						_localctx = new GreaterExprContext(new ComparisonExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_comparisonExpression);
						setState(489);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(490);
						match(GREATER);
						setState(491);
						additiveExpression(0);
						}
						break;
					case 3:
						{
						_localctx = new LessEqualExprContext(new ComparisonExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_comparisonExpression);
						setState(492);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(493);
						match(LESS_EQUAL);
						setState(494);
						additiveExpression(0);
						}
						break;
					case 4:
						{
						_localctx = new GreaterEqualExprContext(new ComparisonExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_comparisonExpression);
						setState(495);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(496);
						match(GREATER_EQUAL);
						setState(497);
						additiveExpression(0);
						}
						break;
					}
					} 
				}
				setState(502);
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
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterToMultiplicativeExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitToMultiplicativeExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitToMultiplicativeExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AdditionExprContext extends AdditiveExpressionContext {
		public AdditiveExpressionContext additiveExpression() {
			return getRuleContext(AdditiveExpressionContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(PigLatinParser.PLUS, 0); }
		public MultiplicativeExpressionContext multiplicativeExpression() {
			return getRuleContext(MultiplicativeExpressionContext.class,0);
		}
		public AdditionExprContext(AdditiveExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterAdditionExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitAdditionExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitAdditionExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SubtractionExprContext extends AdditiveExpressionContext {
		public AdditiveExpressionContext additiveExpression() {
			return getRuleContext(AdditiveExpressionContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(PigLatinParser.MINUS, 0); }
		public MultiplicativeExpressionContext multiplicativeExpression() {
			return getRuleContext(MultiplicativeExpressionContext.class,0);
		}
		public SubtractionExprContext(AdditiveExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterSubtractionExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitSubtractionExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitSubtractionExpr(this);
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
		int _startState = 90;
		enterRecursionRule(_localctx, 90, RULE_additiveExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new ToMultiplicativeExprContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(504);
			multiplicativeExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(514);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(512);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
					case 1:
						{
						_localctx = new AdditionExprContext(new AdditiveExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_additiveExpression);
						setState(506);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(507);
						match(PLUS);
						setState(508);
						multiplicativeExpression(0);
						}
						break;
					case 2:
						{
						_localctx = new SubtractionExprContext(new AdditiveExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_additiveExpression);
						setState(509);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(510);
						match(MINUS);
						setState(511);
						multiplicativeExpression(0);
						}
						break;
					}
					} 
				}
				setState(516);
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
	public static class ToUnaryExprContext extends MultiplicativeExpressionContext {
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public ToUnaryExprContext(MultiplicativeExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterToUnaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitToUnaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitToUnaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DivisionExprContext extends MultiplicativeExpressionContext {
		public MultiplicativeExpressionContext multiplicativeExpression() {
			return getRuleContext(MultiplicativeExpressionContext.class,0);
		}
		public TerminalNode DIV() { return getToken(PigLatinParser.DIV, 0); }
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public DivisionExprContext(MultiplicativeExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterDivisionExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitDivisionExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitDivisionExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MultiplicationExprContext extends MultiplicativeExpressionContext {
		public MultiplicativeExpressionContext multiplicativeExpression() {
			return getRuleContext(MultiplicativeExpressionContext.class,0);
		}
		public TerminalNode MULT() { return getToken(PigLatinParser.MULT, 0); }
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public MultiplicationExprContext(MultiplicativeExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterMultiplicationExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitMultiplicationExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitMultiplicationExpr(this);
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
		int _startState = 92;
		enterRecursionRule(_localctx, 92, RULE_multiplicativeExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new ToUnaryExprContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(518);
			unaryExpression();
			}
			_ctx.stop = _input.LT(-1);
			setState(528);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(526);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
					case 1:
						{
						_localctx = new MultiplicationExprContext(new MultiplicativeExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_multiplicativeExpression);
						setState(520);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(521);
						match(MULT);
						setState(522);
						unaryExpression();
						}
						break;
					case 2:
						{
						_localctx = new DivisionExprContext(new MultiplicativeExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_multiplicativeExpression);
						setState(523);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(524);
						match(DIV);
						setState(525);
						unaryExpression();
						}
						break;
					}
					} 
				}
				setState(530);
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
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterToPostfixExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitToPostfixExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitToPostfixExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NotExprContext extends UnaryExpressionContext {
		public TerminalNode NON() { return getToken(PigLatinParser.NON, 0); }
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public NotExprContext(UnaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterNotExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitNotExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitNotExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NegateExprContext extends UnaryExpressionContext {
		public TerminalNode MINUS() { return getToken(PigLatinParser.MINUS, 0); }
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public NegateExprContext(UnaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterNegateExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitNegateExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitNegateExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryExpressionContext unaryExpression() throws RecognitionException {
		UnaryExpressionContext _localctx = new UnaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_unaryExpression);
		try {
			setState(536);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NON:
				_localctx = new NotExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(531);
				match(NON);
				setState(532);
				unaryExpression();
				}
				break;
			case MINUS:
				_localctx = new NegateExprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(533);
				match(MINUS);
				setState(534);
				unaryExpression();
				}
				break;
			case T__2:
			case NOVUS:
			case VERUM:
			case FALSUS:
			case NUMBER:
			case DECIMAL:
			case STRING:
			case CHAR:
			case ID:
				_localctx = new ToPostfixExprContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(535);
				postfixExpression(0);
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
		public PostfixExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfixExpression; }
	 
		public PostfixExpressionContext() { }
		public void copyFrom(PostfixExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FunctionCallExprContext extends PostfixExpressionContext {
		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class,0);
		}
		public FunctionArgumentsContext functionArguments() {
			return getRuleContext(FunctionArgumentsContext.class,0);
		}
		public FunctionCallExprContext(PostfixExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterFunctionCallExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitFunctionCallExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitFunctionCallExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PostDecrementExprContext extends PostfixExpressionContext {
		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class,0);
		}
		public TerminalNode MINUSMINUS() { return getToken(PigLatinParser.MINUSMINUS, 0); }
		public PostDecrementExprContext(PostfixExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterPostDecrementExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitPostDecrementExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitPostDecrementExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArrayAccessExprContext extends PostfixExpressionContext {
		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArrayAccessExprContext(PostfixExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterArrayAccessExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitArrayAccessExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitArrayAccessExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MemberAccessExprContext extends PostfixExpressionContext {
		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class,0);
		}
		public TerminalNode ID() { return getToken(PigLatinParser.ID, 0); }
		public MemberAccessExprContext(PostfixExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterMemberAccessExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitMemberAccessExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitMemberAccessExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ToPrimaryExprContext extends PostfixExpressionContext {
		public PrimaryExpressionContext primaryExpression() {
			return getRuleContext(PrimaryExpressionContext.class,0);
		}
		public ToPrimaryExprContext(PostfixExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterToPrimaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitToPrimaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitToPrimaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PostIncrementExprContext extends PostfixExpressionContext {
		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class,0);
		}
		public TerminalNode PLUSPLUS() { return getToken(PigLatinParser.PLUSPLUS, 0); }
		public PostIncrementExprContext(PostfixExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterPostIncrementExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitPostIncrementExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitPostIncrementExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PostfixExpressionContext postfixExpression() throws RecognitionException {
		return postfixExpression(0);
	}

	private PostfixExpressionContext postfixExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PostfixExpressionContext _localctx = new PostfixExpressionContext(_ctx, _parentState);
		PostfixExpressionContext _prevctx = _localctx;
		int _startState = 96;
		enterRecursionRule(_localctx, 96, RULE_postfixExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new ToPrimaryExprContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(539);
			primaryExpression();
			}
			_ctx.stop = _input.LT(-1);
			setState(557);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(555);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
					case 1:
						{
						_localctx = new ArrayAccessExprContext(new PostfixExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_postfixExpression);
						setState(541);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(542);
						match(T__8);
						setState(543);
						expression();
						setState(544);
						match(T__9);
						}
						break;
					case 2:
						{
						_localctx = new MemberAccessExprContext(new PostfixExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_postfixExpression);
						setState(546);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(547);
						match(T__1);
						setState(548);
						match(ID);
						}
						break;
					case 3:
						{
						_localctx = new FunctionCallExprContext(new PostfixExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_postfixExpression);
						setState(549);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(550);
						functionArguments();
						}
						break;
					case 4:
						{
						_localctx = new PostIncrementExprContext(new PostfixExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_postfixExpression);
						setState(551);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(552);
						match(PLUSPLUS);
						}
						break;
					case 5:
						{
						_localctx = new PostDecrementExprContext(new PostfixExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_postfixExpression);
						setState(553);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(554);
						match(MINUSMINUS);
						}
						break;
					}
					} 
				}
				setState(559);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
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
	public static class FunctionArgumentsContext extends ParserRuleContext {
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public FunctionArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionArguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterFunctionArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitFunctionArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitFunctionArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionArgumentsContext functionArguments() throws RecognitionException {
		FunctionArgumentsContext _localctx = new FunctionArgumentsContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_functionArguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(560);
			match(T__2);
			setState(562);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2233927666640093192L) != 0)) {
				{
				setState(561);
				argumentList();
				}
			}

			setState(564);
			match(T__3);
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
		public ExpressionContext expression;
		public List<ExpressionContext> arguments = new ArrayList<ExpressionContext>();
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArgumentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterArgumentList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitArgumentList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitArgumentList(this);
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
			setState(566);
			((ArgumentListContext)_localctx).expression = expression();
			((ArgumentListContext)_localctx).arguments.add(((ArgumentListContext)_localctx).expression);
			setState(571);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(567);
				match(T__4);
				setState(568);
				((ArgumentListContext)_localctx).expression = expression();
				((ArgumentListContext)_localctx).arguments.add(((ArgumentListContext)_localctx).expression);
				}
				}
				setState(573);
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
	public static class ObjectCreationExprContext extends PrimaryExpressionContext {
		public TerminalNode NOVUS() { return getToken(PigLatinParser.NOVUS, 0); }
		public TerminalNode ID() { return getToken(PigLatinParser.ID, 0); }
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public ObjectCreationExprContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterObjectCreationExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitObjectCreationExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitObjectCreationExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TrueLiteralExprContext extends PrimaryExpressionContext {
		public TerminalNode VERUM() { return getToken(PigLatinParser.VERUM, 0); }
		public TrueLiteralExprContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterTrueLiteralExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitTrueLiteralExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitTrueLiteralExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumberLiteralExprContext extends PrimaryExpressionContext {
		public TerminalNode NUMBER() { return getToken(PigLatinParser.NUMBER, 0); }
		public NumberLiteralExprContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterNumberLiteralExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitNumberLiteralExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitNumberLiteralExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FalseLiteralExprContext extends PrimaryExpressionContext {
		public TerminalNode FALSUS() { return getToken(PigLatinParser.FALSUS, 0); }
		public FalseLiteralExprContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterFalseLiteralExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitFalseLiteralExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitFalseLiteralExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringLiteralExprContext extends PrimaryExpressionContext {
		public TerminalNode STRING() { return getToken(PigLatinParser.STRING, 0); }
		public StringLiteralExprContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterStringLiteralExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitStringLiteralExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitStringLiteralExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CharLiteralExprContext extends PrimaryExpressionContext {
		public TerminalNode CHAR() { return getToken(PigLatinParser.CHAR, 0); }
		public CharLiteralExprContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterCharLiteralExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitCharLiteralExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitCharLiteralExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VariableExprContext extends PrimaryExpressionContext {
		public TerminalNode ID() { return getToken(PigLatinParser.ID, 0); }
		public VariableExprContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterVariableExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitVariableExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitVariableExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenthesizedExprContext extends PrimaryExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParenthesizedExprContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterParenthesizedExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitParenthesizedExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitParenthesizedExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DecimalLiteralExprContext extends PrimaryExpressionContext {
		public TerminalNode DECIMAL() { return getToken(PigLatinParser.DECIMAL, 0); }
		public DecimalLiteralExprContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).enterDecimalLiteralExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener) ((PigLatinListener)listener).exitDecimalLiteralExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor) return ((PigLatinVisitor<? extends T>)visitor).visitDecimalLiteralExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryExpressionContext primaryExpression() throws RecognitionException {
		PrimaryExpressionContext _localctx = new PrimaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_primaryExpression);
		int _la;
		try {
			setState(592);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				_localctx = new NumberLiteralExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(574);
				match(NUMBER);
				}
				break;
			case DECIMAL:
				_localctx = new DecimalLiteralExprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(575);
				match(DECIMAL);
				}
				break;
			case STRING:
				_localctx = new StringLiteralExprContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(576);
				match(STRING);
				}
				break;
			case CHAR:
				_localctx = new CharLiteralExprContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(577);
				match(CHAR);
				}
				break;
			case VERUM:
				_localctx = new TrueLiteralExprContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(578);
				match(VERUM);
				}
				break;
			case FALSUS:
				_localctx = new FalseLiteralExprContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(579);
				match(FALSUS);
				}
				break;
			case ID:
				_localctx = new VariableExprContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(580);
				match(ID);
				}
				break;
			case NOVUS:
				_localctx = new ObjectCreationExprContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(581);
				match(NOVUS);
				setState(582);
				match(ID);
				setState(583);
				match(T__2);
				setState(585);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2233927666640093192L) != 0)) {
					{
					setState(584);
					argumentList();
					}
				}

				setState(587);
				match(T__3);
				}
				break;
			case T__2:
				_localctx = new ParenthesizedExprContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(588);
				match(T__2);
				setState(589);
				expression();
				setState(590);
				match(T__3);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 41:
			return logicalOrExpression_sempred((LogicalOrExpressionContext)_localctx, predIndex);
		case 42:
			return logicalAndExpression_sempred((LogicalAndExpressionContext)_localctx, predIndex);
		case 43:
			return equalityExpression_sempred((EqualityExpressionContext)_localctx, predIndex);
		case 44:
			return comparisonExpression_sempred((ComparisonExpressionContext)_localctx, predIndex);
		case 45:
			return additiveExpression_sempred((AdditiveExpressionContext)_localctx, predIndex);
		case 46:
			return multiplicativeExpression_sempred((MultiplicativeExpressionContext)_localctx, predIndex);
		case 48:
			return postfixExpression_sempred((PostfixExpressionContext)_localctx, predIndex);
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
			return precpred(_ctx, 3);
		case 11:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean postfixExpression_sempred(PostfixExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 12:
			return precpred(_ctx, 6);
		case 13:
			return precpred(_ctx, 5);
		case 14:
			return precpred(_ctx, 4);
		case 15:
			return precpred(_ctx, 3);
		case 16:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001?\u0253\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"2\u00072\u00023\u00073\u0001\u0000\u0005\u0000j\b\u0000\n\u0000\f\u0000"+
		"m\t\u0000\u0001\u0000\u0003\u0000p\b\u0000\u0001\u0000\u0003\u0000s\b"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0004"+
		"\u0002\u0080\b\u0002\u000b\u0002\f\u0002\u0081\u0001\u0003\u0001\u0003"+
		"\u0005\u0003\u0086\b\u0003\n\u0003\f\u0003\u0089\t\u0003\u0001\u0004\u0001"+
		"\u0004\u0005\u0004\u008d\b\u0004\n\u0004\f\u0004\u0090\t\u0004\u0001\u0005"+
		"\u0001\u0005\u0005\u0005\u0094\b\u0005\n\u0005\f\u0005\u0097\t\u0005\u0001"+
		"\u0006\u0001\u0006\u0003\u0006\u009b\b\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0003\u0007\u00a1\b\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0003\b\u00ad\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001"+
		"\t\u0001\t\u0005\t\u00b7\b\t\n\t\f\t\u00ba\t\t\u0001\n\u0001\n\u0001\n"+
		"\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0003\u000b\u00c3\b\u000b\u0001"+
		"\u000b\u0005\u000b\u00c6\b\u000b\n\u000b\f\u000b\u00c9\t\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\f\u0001\f\u0003\f\u00cf\b\f\u0001\r\u0001\r\u0001\r"+
		"\u0005\r\u00d4\b\r\n\r\f\r\u00d7\t\r\u0001\r\u0001\r\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003"+
		"\u000e\u00ee\b\u000e\u0003\u000e\u00f0\b\u000e\u0001\u000f\u0001\u000f"+
		"\u0003\u000f\u00f4\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0005\u0010\u00fa\b\u0010\n\u0010\f\u0010\u00fd\t\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u0111"+
		"\b\u0013\u0001\u0013\u0003\u0013\u0114\b\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0005\u0014\u011c\b\u0014"+
		"\n\u0014\f\u0014\u011f\t\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001"+
		"\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0003\u0016\u0131\b\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003"+
		"\u0017\u013c\b\u0017\u0003\u0017\u013e\b\u0017\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0003\u0018\u0148\b\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0003\u0019\u0154\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0003\u001b\u0164\b\u001b"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0005\u001c\u016c\b\u001c\n\u001c\f\u001c\u016f\t\u001c\u0001\u001c\u0003"+
		"\u001c\u0172\b\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001"+
		"\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0005\u001f\u0182\b\u001f\n"+
		"\u001f\f\u001f\u0185\t\u001f\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001"+
		" \u0001 \u0001 \u0001 \u0001 \u0001 \u0001!\u0001!\u0001!\u0001!\u0001"+
		"!\u0001!\u0001!\u0001!\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\""+
		"\u0001\"\u0001\"\u0001\"\u0001#\u0001#\u0003#\u01a4\b#\u0001#\u0001#\u0001"+
		"$\u0001$\u0001$\u0001%\u0001%\u0001%\u0001&\u0003&\u01af\b&\u0001&\u0001"+
		"&\u0001\'\u0001\'\u0001\'\u0001\'\u0005\'\u01b7\b\'\n\'\f\'\u01ba\t\'"+
		"\u0001\'\u0001\'\u0001(\u0001(\u0001)\u0001)\u0001)\u0001)\u0001)\u0001"+
		")\u0005)\u01c6\b)\n)\f)\u01c9\t)\u0001*\u0001*\u0001*\u0001*\u0001*\u0001"+
		"*\u0005*\u01d1\b*\n*\f*\u01d4\t*\u0001+\u0001+\u0001+\u0001+\u0001+\u0001"+
		"+\u0001+\u0001+\u0001+\u0005+\u01df\b+\n+\f+\u01e2\t+\u0001,\u0001,\u0001"+
		",\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001"+
		",\u0001,\u0001,\u0005,\u01f3\b,\n,\f,\u01f6\t,\u0001-\u0001-\u0001-\u0001"+
		"-\u0001-\u0001-\u0001-\u0001-\u0001-\u0005-\u0201\b-\n-\f-\u0204\t-\u0001"+
		".\u0001.\u0001.\u0001.\u0001.\u0001.\u0001.\u0001.\u0001.\u0005.\u020f"+
		"\b.\n.\f.\u0212\t.\u0001/\u0001/\u0001/\u0001/\u0001/\u0003/\u0219\b/"+
		"\u00010\u00010\u00010\u00010\u00010\u00010\u00010\u00010\u00010\u0001"+
		"0\u00010\u00010\u00010\u00010\u00010\u00010\u00010\u00050\u022c\b0\n0"+
		"\f0\u022f\t0\u00011\u00011\u00031\u0233\b1\u00011\u00011\u00012\u0001"+
		"2\u00012\u00052\u023a\b2\n2\f2\u023d\t2\u00013\u00013\u00013\u00013\u0001"+
		"3\u00013\u00013\u00013\u00013\u00013\u00013\u00033\u024a\b3\u00013\u0001"+
		"3\u00013\u00013\u00013\u00033\u0251\b3\u00013\u0000\u0007RTVXZ\\`4\u0000"+
		"\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c"+
		"\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdf\u0000\u0001\u0002\u0000 "+
		"$<<\u026b\u0000k\u0001\u0000\u0000\u0000\u0002y\u0001\u0000\u0000\u0000"+
		"\u0004|\u0001\u0000\u0000\u0000\u0006\u0083\u0001\u0000\u0000\u0000\b"+
		"\u008a\u0001\u0000\u0000\u0000\n\u0091\u0001\u0000\u0000\u0000\f\u009a"+
		"\u0001\u0000\u0000\u0000\u000e\u009c\u0001\u0000\u0000\u0000\u0010\u00a7"+
		"\u0001\u0000\u0000\u0000\u0012\u00b3\u0001\u0000\u0000\u0000\u0014\u00bb"+
		"\u0001\u0000\u0000\u0000\u0016\u00c0\u0001\u0000\u0000\u0000\u0018\u00ce"+
		"\u0001\u0000\u0000\u0000\u001a\u00d0\u0001\u0000\u0000\u0000\u001c\u00ef"+
		"\u0001\u0000\u0000\u0000\u001e\u00f3\u0001\u0000\u0000\u0000 \u00f5\u0001"+
		"\u0000\u0000\u0000\"\u0100\u0001\u0000\u0000\u0000$\u0104\u0001\u0000"+
		"\u0000\u0000&\u0109\u0001\u0000\u0000\u0000(\u0117\u0001\u0000\u0000\u0000"+
		"*\u0122\u0001\u0000\u0000\u0000,\u0130\u0001\u0000\u0000\u0000.\u013d"+
		"\u0001\u0000\u0000\u00000\u0147\u0001\u0000\u0000\u00002\u0153\u0001\u0000"+
		"\u0000\u00004\u0155\u0001\u0000\u0000\u00006\u0163\u0001\u0000\u0000\u0000"+
		"8\u0165\u0001\u0000\u0000\u0000:\u0176\u0001\u0000\u0000\u0000<\u017c"+
		"\u0001\u0000\u0000\u0000>\u017f\u0001\u0000\u0000\u0000@\u0188\u0001\u0000"+
		"\u0000\u0000B\u0190\u0001\u0000\u0000\u0000D\u0198\u0001\u0000\u0000\u0000"+
		"F\u01a1\u0001\u0000\u0000\u0000H\u01a7\u0001\u0000\u0000\u0000J\u01aa"+
		"\u0001\u0000\u0000\u0000L\u01ae\u0001\u0000\u0000\u0000N\u01b2\u0001\u0000"+
		"\u0000\u0000P\u01bd\u0001\u0000\u0000\u0000R\u01bf\u0001\u0000\u0000\u0000"+
		"T\u01ca\u0001\u0000\u0000\u0000V\u01d5\u0001\u0000\u0000\u0000X\u01e3"+
		"\u0001\u0000\u0000\u0000Z\u01f7\u0001\u0000\u0000\u0000\\\u0205\u0001"+
		"\u0000\u0000\u0000^\u0218\u0001\u0000\u0000\u0000`\u021a\u0001\u0000\u0000"+
		"\u0000b\u0230\u0001\u0000\u0000\u0000d\u0236\u0001\u0000\u0000\u0000f"+
		"\u0250\u0001\u0000\u0000\u0000hj\u0003\u0002\u0001\u0000ih\u0001\u0000"+
		"\u0000\u0000jm\u0001\u0000\u0000\u0000ki\u0001\u0000\u0000\u0000kl\u0001"+
		"\u0000\u0000\u0000lo\u0001\u0000\u0000\u0000mk\u0001\u0000\u0000\u0000"+
		"np\u0003\u0006\u0003\u0000on\u0001\u0000\u0000\u0000op\u0001\u0000\u0000"+
		"\u0000pr\u0001\u0000\u0000\u0000qs\u0003\b\u0004\u0000rq\u0001\u0000\u0000"+
		"\u0000rs\u0001\u0000\u0000\u0000st\u0001\u0000\u0000\u0000tu\u0003\n\u0005"+
		"\u0000uv\u0005\u000f\u0000\u0000vw\u0005\u0001\u0000\u0000wx\u0005\u0000"+
		"\u0000\u0001x\u0001\u0001\u0000\u0000\u0000yz\u0005\u001e\u0000\u0000"+
		"z{\u0003\u0004\u0002\u0000{\u0003\u0001\u0000\u0000\u0000|\u007f\u0005"+
		"<\u0000\u0000}~\u0005\u0002\u0000\u0000~\u0080\u0005<\u0000\u0000\u007f"+
		"}\u0001\u0000\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000\u0081\u007f"+
		"\u0001\u0000\u0000\u0000\u0081\u0082\u0001\u0000\u0000\u0000\u0082\u0005"+
		"\u0001\u0000\u0000\u0000\u0083\u0087\u0005\f\u0000\u0000\u0084\u0086\u0003"+
		"\u0018\f\u0000\u0085\u0084\u0001\u0000\u0000\u0000\u0086\u0089\u0001\u0000"+
		"\u0000\u0000\u0087\u0085\u0001\u0000\u0000\u0000\u0087\u0088\u0001\u0000"+
		"\u0000\u0000\u0088\u0007\u0001\u0000\u0000\u0000\u0089\u0087\u0001\u0000"+
		"\u0000\u0000\u008a\u008e\u0005\r\u0000\u0000\u008b\u008d\u0003\f\u0006"+
		"\u0000\u008c\u008b\u0001\u0000\u0000\u0000\u008d\u0090\u0001\u0000\u0000"+
		"\u0000\u008e\u008c\u0001\u0000\u0000\u0000\u008e\u008f\u0001\u0000\u0000"+
		"\u0000\u008f\t\u0001\u0000\u0000\u0000\u0090\u008e\u0001\u0000\u0000\u0000"+
		"\u0091\u0095\u0005\u000e\u0000\u0000\u0092\u0094\u0003,\u0016\u0000\u0093"+
		"\u0092\u0001\u0000\u0000\u0000\u0094\u0097\u0001\u0000\u0000\u0000\u0095"+
		"\u0093\u0001\u0000\u0000\u0000\u0095\u0096\u0001\u0000\u0000\u0000\u0096"+
		"\u000b\u0001\u0000\u0000\u0000\u0097\u0095\u0001\u0000\u0000\u0000\u0098"+
		"\u009b\u0003\u000e\u0007\u0000\u0099\u009b\u0003\u0010\b\u0000\u009a\u0098"+
		"\u0001\u0000\u0000\u0000\u009a\u0099\u0001\u0000\u0000\u0000\u009b\r\u0001"+
		"\u0000\u0000\u0000\u009c\u009d\u0005\u0013\u0000\u0000\u009d\u009e\u0005"+
		"<\u0000\u0000\u009e\u00a0\u0005\u0003\u0000\u0000\u009f\u00a1\u0003\u0012"+
		"\t\u0000\u00a0\u009f\u0001\u0000\u0000\u0000\u00a0\u00a1\u0001\u0000\u0000"+
		"\u0000\u00a1\u00a2\u0001\u0000\u0000\u0000\u00a2\u00a3\u0005\u0004\u0000"+
		"\u0000\u00a3\u00a4\u0003\u0016\u000b\u0000\u00a4\u00a5\u0005\u0010\u0000"+
		"\u0000\u00a5\u00a6\u0005\u0001\u0000\u0000\u00a6\u000f\u0001\u0000\u0000"+
		"\u0000\u00a7\u00a8\u0005\u0014\u0000\u0000\u00a8\u00a9\u0003*\u0015\u0000"+
		"\u00a9\u00aa\u0005<\u0000\u0000\u00aa\u00ac\u0005\u0003\u0000\u0000\u00ab"+
		"\u00ad\u0003\u0012\t\u0000\u00ac\u00ab\u0001\u0000\u0000\u0000\u00ac\u00ad"+
		"\u0001\u0000\u0000\u0000\u00ad\u00ae\u0001\u0000\u0000\u0000\u00ae\u00af"+
		"\u0005\u0004\u0000\u0000\u00af\u00b0\u0003\u0016\u000b\u0000\u00b0\u00b1"+
		"\u0005\u0010\u0000\u0000\u00b1\u00b2\u0005\u0001\u0000\u0000\u00b2\u0011"+
		"\u0001\u0000\u0000\u0000\u00b3\u00b8\u0003\u0014\n\u0000\u00b4\u00b5\u0005"+
		"\u0005\u0000\u0000\u00b5\u00b7\u0003\u0014\n\u0000\u00b6\u00b4\u0001\u0000"+
		"\u0000\u0000\u00b7\u00ba\u0001\u0000\u0000\u0000\u00b8\u00b6\u0001\u0000"+
		"\u0000\u0000\u00b8\u00b9\u0001\u0000\u0000\u0000\u00b9\u0013\u0001\u0000"+
		"\u0000\u0000\u00ba\u00b8\u0001\u0000\u0000\u0000\u00bb\u00bc\u0005\u0011"+
		"\u0000\u0000\u00bc\u00bd\u0005<\u0000\u0000\u00bd\u00be\u0005\u0006\u0000"+
		"\u0000\u00be\u00bf\u0003*\u0015\u0000\u00bf\u0015\u0001\u0000\u0000\u0000"+
		"\u00c0\u00c2\u0005\u0007\u0000\u0000\u00c1\u00c3\u0003\u001a\r\u0000\u00c2"+
		"\u00c1\u0001\u0000\u0000\u0000\u00c2\u00c3\u0001\u0000\u0000\u0000\u00c3"+
		"\u00c7\u0001\u0000\u0000\u0000\u00c4\u00c6\u0003,\u0016\u0000\u00c5\u00c4"+
		"\u0001\u0000\u0000\u0000\u00c6\u00c9\u0001\u0000\u0000\u0000\u00c7\u00c5"+
		"\u0001\u0000\u0000\u0000\u00c7\u00c8\u0001\u0000\u0000\u0000\u00c8\u00ca"+
		"\u0001\u0000\u0000\u0000\u00c9\u00c7\u0001\u0000\u0000\u0000\u00ca\u00cb"+
		"\u0005\b\u0000\u0000\u00cb\u0017\u0001\u0000\u0000\u0000\u00cc\u00cf\u0003"+
		"\u001c\u000e\u0000\u00cd\u00cf\u0003&\u0013\u0000\u00ce\u00cc\u0001\u0000"+
		"\u0000\u0000\u00ce\u00cd\u0001\u0000\u0000\u0000\u00cf\u0019\u0001\u0000"+
		"\u0000\u0000\u00d0\u00d1\u0005\u001d\u0000\u0000\u00d1\u00d5\u0005\t\u0000"+
		"\u0000\u00d2\u00d4\u0003\u0018\f\u0000\u00d3\u00d2\u0001\u0000\u0000\u0000"+
		"\u00d4\u00d7\u0001\u0000\u0000\u0000\u00d5\u00d3\u0001\u0000\u0000\u0000"+
		"\u00d5\u00d6\u0001\u0000\u0000\u0000\u00d6\u00d8\u0001\u0000\u0000\u0000"+
		"\u00d7\u00d5\u0001\u0000\u0000\u0000\u00d8\u00d9\u0005\n\u0000\u0000\u00d9"+
		"\u001b\u0001\u0000\u0000\u0000\u00da\u00db\u0005\u0011\u0000\u0000\u00db"+
		"\u00dc\u0005<\u0000\u0000\u00dc\u00dd\u0005\u0006\u0000\u0000\u00dd\u00de"+
		"\u0003*\u0015\u0000\u00de\u00df\u0005\u0001\u0000\u0000\u00df\u00f0\u0001"+
		"\u0000\u0000\u0000\u00e0\u00e1\u0005\u0011\u0000\u0000\u00e1\u00e2\u0005"+
		"<\u0000\u0000\u00e2\u00e3\u0005\u0006\u0000\u0000\u00e3\u00e4\u0003*\u0015"+
		"\u0000\u00e4\u00e5\u0003P(\u0000\u00e5\u00e6\u0005\u0001\u0000\u0000\u00e6"+
		"\u00f0\u0001\u0000\u0000\u0000\u00e7\u00e8\u0005\u0011\u0000\u0000\u00e8"+
		"\u00e9\u0005<\u0000\u0000\u00e9\u00ea\u0005\u0006\u0000\u0000\u00ea\u00eb"+
		"\u0003*\u0015\u0000\u00eb\u00ed\u0003 \u0010\u0000\u00ec\u00ee\u0005\u0001"+
		"\u0000\u0000\u00ed\u00ec\u0001\u0000\u0000\u0000\u00ed\u00ee\u0001\u0000"+
		"\u0000\u0000\u00ee\u00f0\u0001\u0000\u0000\u0000\u00ef\u00da\u0001\u0000"+
		"\u0000\u0000\u00ef\u00e0\u0001\u0000\u0000\u0000\u00ef\u00e7\u0001\u0000"+
		"\u0000\u0000\u00f0\u001d\u0001\u0000\u0000\u0000\u00f1\u00f4\u0003P(\u0000"+
		"\u00f2\u00f4\u0003 \u0010\u0000\u00f3\u00f1\u0001\u0000\u0000\u0000\u00f3"+
		"\u00f2\u0001\u0000\u0000\u0000\u00f4\u001f\u0001\u0000\u0000\u0000\u00f5"+
		"\u00f6\u0005\u0007\u0000\u0000\u00f6\u00fb\u0003\"\u0011\u0000\u00f7\u00f8"+
		"\u0005\u0005\u0000\u0000\u00f8\u00fa\u0003\"\u0011\u0000\u00f9\u00f7\u0001"+
		"\u0000\u0000\u0000\u00fa\u00fd\u0001\u0000\u0000\u0000\u00fb\u00f9\u0001"+
		"\u0000\u0000\u0000\u00fb\u00fc\u0001\u0000\u0000\u0000\u00fc\u00fe\u0001"+
		"\u0000\u0000\u0000\u00fd\u00fb\u0001\u0000\u0000\u0000\u00fe\u00ff\u0005"+
		"\b\u0000\u0000\u00ff!\u0001\u0000\u0000\u0000\u0100\u0101\u0005<\u0000"+
		"\u0000\u0101\u0102\u0005\u0006\u0000\u0000\u0102\u0103\u0003\u001e\u000f"+
		"\u0000\u0103#\u0001\u0000\u0000\u0000\u0104\u0105\u0003*\u0015\u0000\u0105"+
		"\u0106\u0005\t\u0000\u0000\u0106\u0107\u0003P(\u0000\u0107\u0108\u0005"+
		"\n\u0000\u0000\u0108%\u0001\u0000\u0000\u0000\u0109\u010a\u0005\u0012"+
		"\u0000\u0000\u010a\u010b\u0005<\u0000\u0000\u010b\u010c\u0005\t\u0000"+
		"\u0000\u010c\u010d\u0003P(\u0000\u010d\u0110\u0005\n\u0000\u0000\u010e"+
		"\u010f\u0005\u0006\u0000\u0000\u010f\u0111\u0003*\u0015\u0000\u0110\u010e"+
		"\u0001\u0000\u0000\u0000\u0110\u0111\u0001\u0000\u0000\u0000\u0111\u0113"+
		"\u0001\u0000\u0000\u0000\u0112\u0114\u0003(\u0014\u0000\u0113\u0112\u0001"+
		"\u0000\u0000\u0000\u0113\u0114\u0001\u0000\u0000\u0000\u0114\u0115\u0001"+
		"\u0000\u0000\u0000\u0115\u0116\u0005\u0001\u0000\u0000\u0116\'\u0001\u0000"+
		"\u0000\u0000\u0117\u0118\u0005\u0007\u0000\u0000\u0118\u011d\u0003P(\u0000"+
		"\u0119\u011a\u0005\u0005\u0000\u0000\u011a\u011c\u0003P(\u0000\u011b\u0119"+
		"\u0001\u0000\u0000\u0000\u011c\u011f\u0001\u0000\u0000\u0000\u011d\u011b"+
		"\u0001\u0000\u0000\u0000\u011d\u011e\u0001\u0000\u0000\u0000\u011e\u0120"+
		"\u0001\u0000\u0000\u0000\u011f\u011d\u0001\u0000\u0000\u0000\u0120\u0121"+
		"\u0005\b\u0000\u0000\u0121)\u0001\u0000\u0000\u0000\u0122\u0123\u0007"+
		"\u0000\u0000\u0000\u0123+\u0001\u0000\u0000\u0000\u0124\u0131\u0003.\u0017"+
		"\u0000\u0125\u0131\u00030\u0018\u0000\u0126\u0131\u00034\u001a\u0000\u0127"+
		"\u0131\u00038\u001c\u0000\u0128\u0131\u0003@ \u0000\u0129\u0131\u0003"+
		"B!\u0000\u012a\u0131\u0003D\"\u0000\u012b\u0131\u0003F#\u0000\u012c\u0131"+
		"\u0003J%\u0000\u012d\u0131\u0003H$\u0000\u012e\u0131\u0003N\'\u0000\u012f"+
		"\u0131\u0003L&\u0000\u0130\u0124\u0001\u0000\u0000\u0000\u0130\u0125\u0001"+
		"\u0000\u0000\u0000\u0130\u0126\u0001\u0000\u0000\u0000\u0130\u0127\u0001"+
		"\u0000\u0000\u0000\u0130\u0128\u0001\u0000\u0000\u0000\u0130\u0129\u0001"+
		"\u0000\u0000\u0000\u0130\u012a\u0001\u0000\u0000\u0000\u0130\u012b\u0001"+
		"\u0000\u0000\u0000\u0130\u012c\u0001\u0000\u0000\u0000\u0130\u012d\u0001"+
		"\u0000\u0000\u0000\u0130\u012e\u0001\u0000\u0000\u0000\u0130\u012f\u0001"+
		"\u0000\u0000\u0000\u0131-\u0001\u0000\u0000\u0000\u0132\u0133\u0003`0"+
		"\u0000\u0133\u0134\u0005\u000b\u0000\u0000\u0134\u0135\u0003P(\u0000\u0135"+
		"\u0136\u0005\u0001\u0000\u0000\u0136\u013e\u0001\u0000\u0000\u0000\u0137"+
		"\u0138\u0003`0\u0000\u0138\u0139\u0005\u000b\u0000\u0000\u0139\u013b\u0003"+
		" \u0010\u0000\u013a\u013c\u0005\u0001\u0000\u0000\u013b\u013a\u0001\u0000"+
		"\u0000\u0000\u013b\u013c\u0001\u0000\u0000\u0000\u013c\u013e\u0001\u0000"+
		"\u0000\u0000\u013d\u0132\u0001\u0000\u0000\u0000\u013d\u0137\u0001\u0000"+
		"\u0000\u0000\u013e/\u0001\u0000\u0000\u0000\u013f\u0140\u00032\u0019\u0000"+
		"\u0140\u0141\u0005+\u0000\u0000\u0141\u0142\u0005\u0001\u0000\u0000\u0142"+
		"\u0148\u0001\u0000\u0000\u0000\u0143\u0144\u00032\u0019\u0000\u0144\u0145"+
		"\u0005,\u0000\u0000\u0145\u0146\u0005\u0001\u0000\u0000\u0146\u0148\u0001"+
		"\u0000\u0000\u0000\u0147\u013f\u0001\u0000\u0000\u0000\u0147\u0143\u0001"+
		"\u0000\u0000\u0000\u01481\u0001\u0000\u0000\u0000\u0149\u0154\u0005<\u0000"+
		"\u0000\u014a\u014b\u0003`0\u0000\u014b\u014c\u0005\t\u0000\u0000\u014c"+
		"\u014d\u0003P(\u0000\u014d\u014e\u0005\n\u0000\u0000\u014e\u0154\u0001"+
		"\u0000\u0000\u0000\u014f\u0150\u0003`0\u0000\u0150\u0151\u0005\u0002\u0000"+
		"\u0000\u0151\u0152\u0005<\u0000\u0000\u0152\u0154\u0001\u0000\u0000\u0000"+
		"\u0153\u0149\u0001\u0000\u0000\u0000\u0153\u014a\u0001\u0000\u0000\u0000"+
		"\u0153\u014f\u0001\u0000\u0000\u0000\u01543\u0001\u0000\u0000\u0000\u0155"+
		"\u0156\u00036\u001b\u0000\u0156\u0157\u0003b1\u0000\u0157\u0158\u0005"+
		"\u0001\u0000\u0000\u01585\u0001\u0000\u0000\u0000\u0159\u0164\u0005<\u0000"+
		"\u0000\u015a\u015b\u0003`0\u0000\u015b\u015c\u0005\t\u0000\u0000\u015c"+
		"\u015d\u0003P(\u0000\u015d\u015e\u0005\n\u0000\u0000\u015e\u0164\u0001"+
		"\u0000\u0000\u0000\u015f\u0160\u0003`0\u0000\u0160\u0161\u0005\u0002\u0000"+
		"\u0000\u0161\u0162\u0005<\u0000\u0000\u0162\u0164\u0001\u0000\u0000\u0000"+
		"\u0163\u0159\u0001\u0000\u0000\u0000\u0163\u015a\u0001\u0000\u0000\u0000"+
		"\u0163\u015f\u0001\u0000\u0000\u0000\u01647\u0001\u0000\u0000\u0000\u0165"+
		"\u0166\u0005\u0015\u0000\u0000\u0166\u0167\u0005\u0003\u0000\u0000\u0167"+
		"\u0168\u0003P(\u0000\u0168\u0169\u0005\u0004\u0000\u0000\u0169\u016d\u0003"+
		">\u001f\u0000\u016a\u016c\u0003:\u001d\u0000\u016b\u016a\u0001\u0000\u0000"+
		"\u0000\u016c\u016f\u0001\u0000\u0000\u0000\u016d\u016b\u0001\u0000\u0000"+
		"\u0000\u016d\u016e\u0001\u0000\u0000\u0000\u016e\u0171\u0001\u0000\u0000"+
		"\u0000\u016f\u016d\u0001\u0000\u0000\u0000\u0170\u0172\u0003<\u001e\u0000"+
		"\u0171\u0170\u0001\u0000\u0000\u0000\u0171\u0172\u0001\u0000\u0000\u0000"+
		"\u0172\u0173\u0001\u0000\u0000\u0000\u0173\u0174\u0005\u0010\u0000\u0000"+
		"\u0174\u0175\u0005\u0001\u0000\u0000\u01759\u0001\u0000\u0000\u0000\u0176"+
		"\u0177\u0005\u0016\u0000\u0000\u0177\u0178\u0005\u0003\u0000\u0000\u0178"+
		"\u0179\u0003P(\u0000\u0179\u017a\u0005\u0004\u0000\u0000\u017a\u017b\u0003"+
		">\u001f\u0000\u017b;\u0001\u0000\u0000\u0000\u017c\u017d\u0005\u0016\u0000"+
		"\u0000\u017d\u017e\u0003>\u001f\u0000\u017e=\u0001\u0000\u0000\u0000\u017f"+
		"\u0183\u0005\u0007\u0000\u0000\u0180\u0182\u0003,\u0016\u0000\u0181\u0180"+
		"\u0001\u0000\u0000\u0000\u0182\u0185\u0001\u0000\u0000\u0000\u0183\u0181"+
		"\u0001\u0000\u0000\u0000\u0183\u0184\u0001\u0000\u0000\u0000\u0184\u0186"+
		"\u0001\u0000\u0000\u0000\u0185\u0183\u0001\u0000\u0000\u0000\u0186\u0187"+
		"\u0005\b\u0000\u0000\u0187?\u0001\u0000\u0000\u0000\u0188\u0189\u0005"+
		"\u0017\u0000\u0000\u0189\u018a\u0005\u0003\u0000\u0000\u018a\u018b\u0003"+
		"P(\u0000\u018b\u018c\u0005\u0004\u0000\u0000\u018c\u018d\u0003>\u001f"+
		"\u0000\u018d\u018e\u0005\u0010\u0000\u0000\u018e\u018f\u0005\u0001\u0000"+
		"\u0000\u018fA\u0001\u0000\u0000\u0000\u0190\u0191\u0005\u0018\u0000\u0000"+
		"\u0191\u0192\u0003>\u001f\u0000\u0192\u0193\u0005\u0017\u0000\u0000\u0193"+
		"\u0194\u0005\u0003\u0000\u0000\u0194\u0195\u0003P(\u0000\u0195\u0196\u0005"+
		"\u0004\u0000\u0000\u0196\u0197\u0005\u0001\u0000\u0000\u0197C\u0001\u0000"+
		"\u0000\u0000\u0198\u0199\u0005\u0019\u0000\u0000\u0199\u019a\u0005\u0003"+
		"\u0000\u0000\u019a\u019b\u0003\u001c\u000e\u0000\u019b\u019c\u0003P(\u0000"+
		"\u019c\u019d\u0005\u0001\u0000\u0000\u019d\u019e\u0003P(\u0000\u019e\u019f"+
		"\u0005\u0004\u0000\u0000\u019f\u01a0\u0003>\u001f\u0000\u01a0E\u0001\u0000"+
		"\u0000\u0000\u01a1\u01a3\u0005\u001a\u0000\u0000\u01a2\u01a4\u0003P(\u0000"+
		"\u01a3\u01a2\u0001\u0000\u0000\u0000\u01a3\u01a4\u0001\u0000\u0000\u0000"+
		"\u01a4\u01a5\u0001\u0000\u0000\u0000\u01a5\u01a6\u0005\u0001\u0000\u0000"+
		"\u01a6G\u0001\u0000\u0000\u0000\u01a7\u01a8\u0005\u001b\u0000\u0000\u01a8"+
		"\u01a9\u0005\u0001\u0000\u0000\u01a9I\u0001\u0000\u0000\u0000\u01aa\u01ab"+
		"\u0005\u001c\u0000\u0000\u01ab\u01ac\u0005\u0001\u0000\u0000\u01acK\u0001"+
		"\u0000\u0000\u0000\u01ad\u01af\u0003`0\u0000\u01ae\u01ad\u0001\u0000\u0000"+
		"\u0000\u01ae\u01af\u0001\u0000\u0000\u0000\u01af\u01b0\u0001\u0000\u0000"+
		"\u0000\u01b0\u01b1\u00056\u0000\u0000\u01b1M\u0001\u0000\u0000\u0000\u01b2"+
		"\u01b3\u00057\u0000\u0000\u01b3\u01b8\u0003P(\u0000\u01b4\u01b5\u0005"+
		"7\u0000\u0000\u01b5\u01b7\u0003P(\u0000\u01b6\u01b4\u0001\u0000\u0000"+
		"\u0000\u01b7\u01ba\u0001\u0000\u0000\u0000\u01b8\u01b6\u0001\u0000\u0000"+
		"\u0000\u01b8\u01b9\u0001\u0000\u0000\u0000\u01b9\u01bb\u0001\u0000\u0000"+
		"\u0000\u01ba\u01b8\u0001\u0000\u0000\u0000\u01bb\u01bc\u0005\u0001\u0000"+
		"\u0000\u01bcO\u0001\u0000\u0000\u0000\u01bd\u01be\u0003R)\u0000\u01be"+
		"Q\u0001\u0000\u0000\u0000\u01bf\u01c0\u0006)\uffff\uffff\u0000\u01c0\u01c1"+
		"\u0003T*\u0000\u01c1\u01c7\u0001\u0000\u0000\u0000\u01c2\u01c3\n\u0002"+
		"\u0000\u0000\u01c3\u01c4\u0005.\u0000\u0000\u01c4\u01c6\u0003T*\u0000"+
		"\u01c5\u01c2\u0001\u0000\u0000\u0000\u01c6\u01c9\u0001\u0000\u0000\u0000"+
		"\u01c7\u01c5\u0001\u0000\u0000\u0000\u01c7\u01c8\u0001\u0000\u0000\u0000"+
		"\u01c8S\u0001\u0000\u0000\u0000\u01c9\u01c7\u0001\u0000\u0000\u0000\u01ca"+
		"\u01cb\u0006*\uffff\uffff\u0000\u01cb\u01cc\u0003V+\u0000\u01cc\u01d2"+
		"\u0001\u0000\u0000\u0000\u01cd\u01ce\n\u0002\u0000\u0000\u01ce\u01cf\u0005"+
		"-\u0000\u0000\u01cf\u01d1\u0003V+\u0000\u01d0\u01cd\u0001\u0000\u0000"+
		"\u0000\u01d1\u01d4\u0001\u0000\u0000\u0000\u01d2\u01d0\u0001\u0000\u0000"+
		"\u0000\u01d2\u01d3\u0001\u0000\u0000\u0000\u01d3U\u0001\u0000\u0000\u0000"+
		"\u01d4\u01d2\u0001\u0000\u0000\u0000\u01d5\u01d6\u0006+\uffff\uffff\u0000"+
		"\u01d6\u01d7\u0003X,\u0000\u01d7\u01e0\u0001\u0000\u0000\u0000\u01d8\u01d9"+
		"\n\u0003\u0000\u0000\u01d9\u01da\u00050\u0000\u0000\u01da\u01df\u0003"+
		"X,\u0000\u01db\u01dc\n\u0002\u0000\u0000\u01dc\u01dd\u00051\u0000\u0000"+
		"\u01dd\u01df\u0003X,\u0000\u01de\u01d8\u0001\u0000\u0000\u0000\u01de\u01db"+
		"\u0001\u0000\u0000\u0000\u01df\u01e2\u0001\u0000\u0000\u0000\u01e0\u01de"+
		"\u0001\u0000\u0000\u0000\u01e0\u01e1\u0001\u0000\u0000\u0000\u01e1W\u0001"+
		"\u0000\u0000\u0000\u01e2\u01e0\u0001\u0000\u0000\u0000\u01e3\u01e4\u0006"+
		",\uffff\uffff\u0000\u01e4\u01e5\u0003Z-\u0000\u01e5\u01f4\u0001\u0000"+
		"\u0000\u0000\u01e6\u01e7\n\u0005\u0000\u0000\u01e7\u01e8\u00052\u0000"+
		"\u0000\u01e8\u01f3\u0003Z-\u0000\u01e9\u01ea\n\u0004\u0000\u0000\u01ea"+
		"\u01eb\u00053\u0000\u0000\u01eb\u01f3\u0003Z-\u0000\u01ec\u01ed\n\u0003"+
		"\u0000\u0000\u01ed\u01ee\u00054\u0000\u0000\u01ee\u01f3\u0003Z-\u0000"+
		"\u01ef\u01f0\n\u0002\u0000\u0000\u01f0\u01f1\u00055\u0000\u0000\u01f1"+
		"\u01f3\u0003Z-\u0000\u01f2\u01e6\u0001\u0000\u0000\u0000\u01f2\u01e9\u0001"+
		"\u0000\u0000\u0000\u01f2\u01ec\u0001\u0000\u0000\u0000\u01f2\u01ef\u0001"+
		"\u0000\u0000\u0000\u01f3\u01f6\u0001\u0000\u0000\u0000\u01f4\u01f2\u0001"+
		"\u0000\u0000\u0000\u01f4\u01f5\u0001\u0000\u0000\u0000\u01f5Y\u0001\u0000"+
		"\u0000\u0000\u01f6\u01f4\u0001\u0000\u0000\u0000\u01f7\u01f8\u0006-\uffff"+
		"\uffff\u0000\u01f8\u01f9\u0003\\.\u0000\u01f9\u0202\u0001\u0000\u0000"+
		"\u0000\u01fa\u01fb\n\u0003\u0000\u0000\u01fb\u01fc\u0005\'\u0000\u0000"+
		"\u01fc\u0201\u0003\\.\u0000\u01fd\u01fe\n\u0002\u0000\u0000\u01fe\u01ff"+
		"\u0005(\u0000\u0000\u01ff\u0201\u0003\\.\u0000\u0200\u01fa\u0001\u0000"+
		"\u0000\u0000\u0200\u01fd\u0001\u0000\u0000\u0000\u0201\u0204\u0001\u0000"+
		"\u0000\u0000\u0202\u0200\u0001\u0000\u0000\u0000\u0202\u0203\u0001\u0000"+
		"\u0000\u0000\u0203[\u0001\u0000\u0000\u0000\u0204\u0202\u0001\u0000\u0000"+
		"\u0000\u0205\u0206\u0006.\uffff\uffff\u0000\u0206\u0207\u0003^/\u0000"+
		"\u0207\u0210\u0001\u0000\u0000\u0000\u0208\u0209\n\u0003\u0000\u0000\u0209"+
		"\u020a\u0005)\u0000\u0000\u020a\u020f\u0003^/\u0000\u020b\u020c\n\u0002"+
		"\u0000\u0000\u020c\u020d\u0005*\u0000\u0000\u020d\u020f\u0003^/\u0000"+
		"\u020e\u0208\u0001\u0000\u0000\u0000\u020e\u020b\u0001\u0000\u0000\u0000"+
		"\u020f\u0212\u0001\u0000\u0000\u0000\u0210\u020e\u0001\u0000\u0000\u0000"+
		"\u0210\u0211\u0001\u0000\u0000\u0000\u0211]\u0001\u0000\u0000\u0000\u0212"+
		"\u0210\u0001\u0000\u0000\u0000\u0213\u0214\u0005/\u0000\u0000\u0214\u0219"+
		"\u0003^/\u0000\u0215\u0216\u0005(\u0000\u0000\u0216\u0219\u0003^/\u0000"+
		"\u0217\u0219\u0003`0\u0000\u0218\u0213\u0001\u0000\u0000\u0000\u0218\u0215"+
		"\u0001\u0000\u0000\u0000\u0218\u0217\u0001\u0000\u0000\u0000\u0219_\u0001"+
		"\u0000\u0000\u0000\u021a\u021b\u00060\uffff\uffff\u0000\u021b\u021c\u0003"+
		"f3\u0000\u021c\u022d\u0001\u0000\u0000\u0000\u021d\u021e\n\u0006\u0000"+
		"\u0000\u021e\u021f\u0005\t\u0000\u0000\u021f\u0220\u0003P(\u0000\u0220"+
		"\u0221\u0005\n\u0000\u0000\u0221\u022c\u0001\u0000\u0000\u0000\u0222\u0223"+
		"\n\u0005\u0000\u0000\u0223\u0224\u0005\u0002\u0000\u0000\u0224\u022c\u0005"+
		"<\u0000\u0000\u0225\u0226\n\u0004\u0000\u0000\u0226\u022c\u0003b1\u0000"+
		"\u0227\u0228\n\u0003\u0000\u0000\u0228\u022c\u0005+\u0000\u0000\u0229"+
		"\u022a\n\u0002\u0000\u0000\u022a\u022c\u0005,\u0000\u0000\u022b\u021d"+
		"\u0001\u0000\u0000\u0000\u022b\u0222\u0001\u0000\u0000\u0000\u022b\u0225"+
		"\u0001\u0000\u0000\u0000\u022b\u0227\u0001\u0000\u0000\u0000\u022b\u0229"+
		"\u0001\u0000\u0000\u0000\u022c\u022f\u0001\u0000\u0000\u0000\u022d\u022b"+
		"\u0001\u0000\u0000\u0000\u022d\u022e\u0001\u0000\u0000\u0000\u022ea\u0001"+
		"\u0000\u0000\u0000\u022f\u022d\u0001\u0000\u0000\u0000\u0230\u0232\u0005"+
		"\u0003\u0000\u0000\u0231\u0233\u0003d2\u0000\u0232\u0231\u0001\u0000\u0000"+
		"\u0000\u0232\u0233\u0001\u0000\u0000\u0000\u0233\u0234\u0001\u0000\u0000"+
		"\u0000\u0234\u0235\u0005\u0004\u0000\u0000\u0235c\u0001\u0000\u0000\u0000"+
		"\u0236\u023b\u0003P(\u0000\u0237\u0238\u0005\u0005\u0000\u0000\u0238\u023a"+
		"\u0003P(\u0000\u0239\u0237\u0001\u0000\u0000\u0000\u023a\u023d\u0001\u0000"+
		"\u0000\u0000\u023b\u0239\u0001\u0000\u0000\u0000\u023b\u023c\u0001\u0000"+
		"\u0000\u0000\u023ce\u0001\u0000\u0000\u0000\u023d\u023b\u0001\u0000\u0000"+
		"\u0000\u023e\u0251\u00058\u0000\u0000\u023f\u0251\u00059\u0000\u0000\u0240"+
		"\u0251\u0005:\u0000\u0000\u0241\u0251\u0005;\u0000\u0000\u0242\u0251\u0005"+
		"%\u0000\u0000\u0243\u0251\u0005&\u0000\u0000\u0244\u0251\u0005<\u0000"+
		"\u0000\u0245\u0246\u0005\u001f\u0000\u0000\u0246\u0247\u0005<\u0000\u0000"+
		"\u0247\u0249\u0005\u0003\u0000\u0000\u0248\u024a\u0003d2\u0000\u0249\u0248"+
		"\u0001\u0000\u0000\u0000\u0249\u024a\u0001\u0000\u0000\u0000\u024a\u024b"+
		"\u0001\u0000\u0000\u0000\u024b\u0251\u0005\u0004\u0000\u0000\u024c\u024d"+
		"\u0005\u0003\u0000\u0000\u024d\u024e\u0003P(\u0000\u024e\u024f\u0005\u0004"+
		"\u0000\u0000\u024f\u0251\u0001\u0000\u0000\u0000\u0250\u023e\u0001\u0000"+
		"\u0000\u0000\u0250\u023f\u0001\u0000\u0000\u0000\u0250\u0240\u0001\u0000"+
		"\u0000\u0000\u0250\u0241\u0001\u0000\u0000\u0000\u0250\u0242\u0001\u0000"+
		"\u0000\u0000\u0250\u0243\u0001\u0000\u0000\u0000\u0250\u0244\u0001\u0000"+
		"\u0000\u0000\u0250\u0245\u0001\u0000\u0000\u0000\u0250\u024c\u0001\u0000"+
		"\u0000\u0000\u0251g\u0001\u0000\u0000\u00003kor\u0081\u0087\u008e\u0095"+
		"\u009a\u00a0\u00ac\u00b8\u00c2\u00c7\u00ce\u00d5\u00ed\u00ef\u00f3\u00fb"+
		"\u0110\u0113\u011d\u0130\u013b\u013d\u0147\u0153\u0163\u016d\u0171\u0183"+
		"\u01a3\u01ae\u01b8\u01c7\u01d2\u01de\u01e0\u01f2\u01f4\u0200\u0202\u020e"+
		"\u0210\u0218\u022b\u022d\u0232\u023b\u0249\u0250";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}