// Generated from D:/ideaProject/BedrockLang/BDLCompiler/src/main/antlr\BedrockLang.g4 by ANTLR 4.9.1
package com.blocklynukkit.bedrockLang.compiler.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BedrockLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, COMMENT=2, MUTICOMMENT=3, COMMA=4, SEMICOLON=5, COLON=6, LB=7, RB=8, 
		LA=9, RA=10, START=11, END=12, DOT=13, EQ=14, NEQ=15, SET=16, PLUS=17, 
		MINUS=18, MULTIPLY=19, DIVIDE=20, REMAIN=21, NOT=22, AND=23, OR=24, GTR=25, 
		GTREQ=26, LWR=27, LWREQ=28, DOLLAR=29, DEF=30, VAR=31, WHEN=32, RETURN=33, 
		IMPORT=34, FROM=35, AS=36, STRING=37, INT=38, DEC=39, BOOL=40, NULL=41, 
		ID=42;
	public static final int
		RULE_program = 0, RULE_stat = 1, RULE_expr = 2, RULE_importStat = 3, RULE_whenStat = 4, 
		RULE_defineCmdStat = 5, RULE_defineSignature = 6, RULE_defineSignatureWordSingle = 7, 
		RULE_defineSignatureWordMultiple = 8, RULE_defineSignatureVariable = 9, 
		RULE_declareVarStat = 10, RULE_returnStat = 11, RULE_command = 12, RULE_block = 13, 
		RULE_id = 14, RULE_varid = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "stat", "expr", "importStat", "whenStat", "defineCmdStat", 
			"defineSignature", "defineSignatureWordSingle", "defineSignatureWordMultiple", 
			"defineSignatureVariable", "declareVarStat", "returnStat", "command", 
			"block", "id", "varid"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "','", null, "':'", "'('", "')'", "'['", "']'", 
			"'{'", "'}'", "'.'", "'=='", "'!='", "'='", "'+'", "'-'", "'*'", "'/'", 
			"'%'", "'!'", "'&'", "'|'", "'>'", "'>='", "'<'", "'<='", "'$'", "'def'", 
			"'var'", "'when'", "'return'", "'import'", "'from'", "'as'", null, null, 
			null, null, "'null'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WS", "COMMENT", "MUTICOMMENT", "COMMA", "SEMICOLON", "COLON", 
			"LB", "RB", "LA", "RA", "START", "END", "DOT", "EQ", "NEQ", "SET", "PLUS", 
			"MINUS", "MULTIPLY", "DIVIDE", "REMAIN", "NOT", "AND", "OR", "GTR", "GTREQ", 
			"LWR", "LWREQ", "DOLLAR", "DEF", "VAR", "WHEN", "RETURN", "IMPORT", "FROM", 
			"AS", "STRING", "INT", "DEC", "BOOL", "NULL", "ID"
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
	public String getGrammarFileName() { return "BedrockLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public BedrockLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public List<ImportStatContext> importStat() {
			return getRuleContexts(ImportStatContext.class);
		}
		public ImportStatContext importStat(int i) {
			return getRuleContext(ImportStatContext.class,i);
		}
		public List<DeclareVarStatContext> declareVarStat() {
			return getRuleContexts(DeclareVarStatContext.class);
		}
		public DeclareVarStatContext declareVarStat(int i) {
			return getRuleContext(DeclareVarStatContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(BedrockLangParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(BedrockLangParser.SEMICOLON, i);
		}
		public List<DefineCmdStatContext> defineCmdStat() {
			return getRuleContexts(DefineCmdStatContext.class);
		}
		public DefineCmdStatContext defineCmdStat(int i) {
			return getRuleContext(DefineCmdStatContext.class,i);
		}
		public List<WhenStatContext> whenStat() {
			return getRuleContexts(WhenStatContext.class);
		}
		public WhenStatContext whenStat(int i) {
			return getRuleContext(WhenStatContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitProgram(this);
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
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IMPORT) {
				{
				{
				setState(32);
				importStat();
				}
				}
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DEF) | (1L << VAR) | (1L << WHEN))) != 0)) {
				{
				setState(43);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case VAR:
					{
					setState(38);
					declareVarStat();
					setState(39);
					match(SEMICOLON);
					}
					break;
				case DEF:
					{
					setState(41);
					defineCmdStat();
					}
					break;
				case WHEN:
					{
					setState(42);
					whenStat();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(47);
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

	public static class StatContext extends ParserRuleContext {
		public DefineCmdStatContext defineCmdStat() {
			return getRuleContext(DefineCmdStatContext.class,0);
		}
		public WhenStatContext whenStat() {
			return getRuleContext(WhenStatContext.class,0);
		}
		public DeclareVarStatContext declareVarStat() {
			return getRuleContext(DeclareVarStatContext.class,0);
		}
		public ReturnStatContext returnStat() {
			return getRuleContext(ReturnStatContext.class,0);
		}
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stat);
		try {
			setState(52);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DEF:
				enterOuterAlt(_localctx, 1);
				{
				setState(48);
				defineCmdStat();
				}
				break;
			case WHEN:
				enterOuterAlt(_localctx, 2);
				{
				setState(49);
				whenStat();
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 3);
				{
				setState(50);
				declareVarStat();
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 4);
				{
				setState(51);
				returnStat();
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

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CommandExprContext extends ExprContext {
		public CommandContext command() {
			return getRuleContext(CommandContext.class,0);
		}
		public CommandExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterCommandExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitCommandExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitCommandExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VarExprContext extends ExprContext {
		public VaridContext varid() {
			return getRuleContext(VaridContext.class,0);
		}
		public VarExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterVarExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitVarExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitVarExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SetVarExprContext extends ExprContext {
		public VaridContext varid() {
			return getRuleContext(VaridContext.class,0);
		}
		public TerminalNode SET() { return getToken(BedrockLangParser.SET, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public SetVarExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterSetVarExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitSetVarExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitSetVarExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DevideExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode DIVIDE() { return getToken(BedrockLangParser.DIVIDE, 0); }
		public DevideExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterDevideExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitDevideExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitDevideExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BracketExprContext extends ExprContext {
		public TerminalNode LB() { return getToken(BedrockLangParser.LB, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RB() { return getToken(BedrockLangParser.RB, 0); }
		public BracketExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterBracketExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitBracketExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitBracketExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LiteralExprContext extends ExprContext {
		public TerminalNode INT() { return getToken(BedrockLangParser.INT, 0); }
		public TerminalNode DEC() { return getToken(BedrockLangParser.DEC, 0); }
		public TerminalNode BOOL() { return getToken(BedrockLangParser.BOOL, 0); }
		public TerminalNode NULL() { return getToken(BedrockLangParser.NULL, 0); }
		public TerminalNode STRING() { return getToken(BedrockLangParser.STRING, 0); }
		public LiteralExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterLiteralExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitLiteralExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitLiteralExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NegativeExprContext extends ExprContext {
		public TerminalNode MINUS() { return getToken(BedrockLangParser.MINUS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NegativeExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterNegativeExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitNegativeExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitNegativeExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PositiveExprContext extends ExprContext {
		public TerminalNode PLUS() { return getToken(BedrockLangParser.PLUS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PositiveExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterPositiveExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitPositiveExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitPositiveExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultiplyExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MULTIPLY() { return getToken(BedrockLangParser.MULTIPLY, 0); }
		public MultiplyExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterMultiplyExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitMultiplyExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitMultiplyExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MinusExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MINUS() { return getToken(BedrockLangParser.MINUS, 0); }
		public MinusExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterMinusExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitMinusExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitMinusExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RemainExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode REMAIN() { return getToken(BedrockLangParser.REMAIN, 0); }
		public RemainExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterRemainExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitRemainExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitRemainExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PlusExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(BedrockLangParser.PLUS, 0); }
		public PlusExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterPlusExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitPlusExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitPlusExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				_localctx = new CommandExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(55);
				command();
				}
				break;
			case 2:
				{
				_localctx = new LiteralExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(56);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << INT) | (1L << DEC) | (1L << BOOL) | (1L << NULL))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 3:
				{
				_localctx = new VarExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(57);
				varid();
				}
				break;
			case 4:
				{
				_localctx = new BracketExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(58);
				match(LB);
				setState(59);
				expr(0);
				setState(60);
				match(RB);
				}
				break;
			case 5:
				{
				_localctx = new PositiveExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(62);
				match(PLUS);
				setState(63);
				expr(8);
				}
				break;
			case 6:
				{
				_localctx = new NegativeExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(64);
				match(MINUS);
				setState(65);
				expr(7);
				}
				break;
			case 7:
				{
				_localctx = new SetVarExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(66);
				varid();
				setState(67);
				match(SET);
				setState(68);
				expr(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(89);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(87);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						_localctx = new PlusExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(72);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(73);
						match(PLUS);
						setState(74);
						expr(7);
						}
						break;
					case 2:
						{
						_localctx = new MinusExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(75);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(76);
						match(MINUS);
						setState(77);
						expr(6);
						}
						break;
					case 3:
						{
						_localctx = new MultiplyExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(78);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(79);
						match(MULTIPLY);
						setState(80);
						expr(5);
						}
						break;
					case 4:
						{
						_localctx = new DevideExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(81);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(82);
						match(DIVIDE);
						setState(83);
						expr(4);
						}
						break;
					case 5:
						{
						_localctx = new RemainExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(84);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(85);
						match(REMAIN);
						setState(86);
						expr(3);
						}
						break;
					}
					} 
				}
				setState(91);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
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

	public static class ImportStatContext extends ParserRuleContext {
		public ImportStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importStat; }
	 
		public ImportStatContext() { }
		public void copyFrom(ImportStatContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ImportAllStaticContext extends ImportStatContext {
		public TerminalNode IMPORT() { return getToken(BedrockLangParser.IMPORT, 0); }
		public TerminalNode MULTIPLY() { return getToken(BedrockLangParser.MULTIPLY, 0); }
		public TerminalNode FROM() { return getToken(BedrockLangParser.FROM, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ImportAllStaticContext(ImportStatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterImportAllStatic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitImportAllStatic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitImportAllStatic(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ImportClassContext extends ImportStatContext {
		public TerminalNode IMPORT() { return getToken(BedrockLangParser.IMPORT, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ImportClassContext(ImportStatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterImportClass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitImportClass(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitImportClass(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ImportSingleStaticContext extends ImportStatContext {
		public TerminalNode IMPORT() { return getToken(BedrockLangParser.IMPORT, 0); }
		public TerminalNode FROM() { return getToken(BedrockLangParser.FROM, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(BedrockLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(BedrockLangParser.ID, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(BedrockLangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(BedrockLangParser.COMMA, i);
		}
		public ImportSingleStaticContext(ImportStatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterImportSingleStatic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitImportSingleStatic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitImportSingleStatic(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportStatContext importStat() throws RecognitionException {
		ImportStatContext _localctx = new ImportStatContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_importStat);
		int _la;
		try {
			setState(109);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				_localctx = new ImportSingleStaticContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(92);
				match(IMPORT);
				setState(97); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(93);
					match(ID);
					setState(95);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(94);
						match(COMMA);
						}
					}

					}
					}
					setState(99); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ID );
				setState(101);
				match(FROM);
				setState(102);
				id();
				}
				break;
			case 2:
				_localctx = new ImportAllStaticContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(103);
				match(IMPORT);
				setState(104);
				match(MULTIPLY);
				setState(105);
				match(FROM);
				setState(106);
				id();
				}
				break;
			case 3:
				_localctx = new ImportClassContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(107);
				match(IMPORT);
				setState(108);
				id();
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

	public static class WhenStatContext extends ParserRuleContext {
		public TerminalNode WHEN() { return getToken(BedrockLangParser.WHEN, 0); }
		public TerminalNode ID() { return getToken(BedrockLangParser.ID, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public WhenStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whenStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterWhenStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitWhenStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitWhenStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhenStatContext whenStat() throws RecognitionException {
		WhenStatContext _localctx = new WhenStatContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_whenStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(WHEN);
			setState(112);
			match(ID);
			setState(113);
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

	public static class DefineCmdStatContext extends ParserRuleContext {
		public TerminalNode DEF() { return getToken(BedrockLangParser.DEF, 0); }
		public DefineSignatureContext defineSignature() {
			return getRuleContext(DefineSignatureContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public DefineCmdStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defineCmdStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterDefineCmdStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitDefineCmdStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitDefineCmdStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefineCmdStatContext defineCmdStat() throws RecognitionException {
		DefineCmdStatContext _localctx = new DefineCmdStatContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_defineCmdStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			match(DEF);
			setState(116);
			defineSignature();
			setState(117);
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

	public static class DefineSignatureContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(BedrockLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(BedrockLangParser.ID, i);
		}
		public VaridContext varid() {
			return getRuleContext(VaridContext.class,0);
		}
		public TerminalNode COLON() { return getToken(BedrockLangParser.COLON, 0); }
		public List<DefineSignatureWordSingleContext> defineSignatureWordSingle() {
			return getRuleContexts(DefineSignatureWordSingleContext.class);
		}
		public DefineSignatureWordSingleContext defineSignatureWordSingle(int i) {
			return getRuleContext(DefineSignatureWordSingleContext.class,i);
		}
		public List<DefineSignatureWordMultipleContext> defineSignatureWordMultiple() {
			return getRuleContexts(DefineSignatureWordMultipleContext.class);
		}
		public DefineSignatureWordMultipleContext defineSignatureWordMultiple(int i) {
			return getRuleContext(DefineSignatureWordMultipleContext.class,i);
		}
		public List<DefineSignatureVariableContext> defineSignatureVariable() {
			return getRuleContexts(DefineSignatureVariableContext.class);
		}
		public DefineSignatureVariableContext defineSignatureVariable(int i) {
			return getRuleContext(DefineSignatureVariableContext.class,i);
		}
		public DefineSignatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defineSignature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterDefineSignature(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitDefineSignature(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitDefineSignature(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefineSignatureContext defineSignature() throws RecognitionException {
		DefineSignatureContext _localctx = new DefineSignatureContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_defineSignature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(ID);
			setState(124);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(120);
				varid();
				setState(121);
				match(COLON);
				setState(122);
				match(ID);
				}
				break;
			}
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LWR) | (1L << DOLLAR) | (1L << ID))) != 0)) {
				{
				setState(129);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ID:
					{
					setState(126);
					defineSignatureWordSingle();
					}
					break;
				case LWR:
					{
					setState(127);
					defineSignatureWordMultiple();
					}
					break;
				case DOLLAR:
					{
					setState(128);
					defineSignatureVariable();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(133);
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

	public static class DefineSignatureWordSingleContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(BedrockLangParser.ID, 0); }
		public DefineSignatureWordSingleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defineSignatureWordSingle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterDefineSignatureWordSingle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitDefineSignatureWordSingle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitDefineSignatureWordSingle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefineSignatureWordSingleContext defineSignatureWordSingle() throws RecognitionException {
		DefineSignatureWordSingleContext _localctx = new DefineSignatureWordSingleContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_defineSignatureWordSingle);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
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

	public static class DefineSignatureWordMultipleContext extends ParserRuleContext {
		public TerminalNode LWR() { return getToken(BedrockLangParser.LWR, 0); }
		public TerminalNode GTR() { return getToken(BedrockLangParser.GTR, 0); }
		public List<TerminalNode> ID() { return getTokens(BedrockLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(BedrockLangParser.ID, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(BedrockLangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(BedrockLangParser.COMMA, i);
		}
		public DefineSignatureWordMultipleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defineSignatureWordMultiple; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterDefineSignatureWordMultiple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitDefineSignatureWordMultiple(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitDefineSignatureWordMultiple(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefineSignatureWordMultipleContext defineSignatureWordMultiple() throws RecognitionException {
		DefineSignatureWordMultipleContext _localctx = new DefineSignatureWordMultipleContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_defineSignatureWordMultiple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(LWR);
			setState(141); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(137);
				match(ID);
				setState(139);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(138);
					match(COMMA);
					}
				}

				}
				}
				setState(143); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(145);
			match(GTR);
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

	public static class DefineSignatureVariableContext extends ParserRuleContext {
		public VaridContext varid() {
			return getRuleContext(VaridContext.class,0);
		}
		public TerminalNode COLON() { return getToken(BedrockLangParser.COLON, 0); }
		public TerminalNode ID() { return getToken(BedrockLangParser.ID, 0); }
		public DefineSignatureVariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defineSignatureVariable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterDefineSignatureVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitDefineSignatureVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitDefineSignatureVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefineSignatureVariableContext defineSignatureVariable() throws RecognitionException {
		DefineSignatureVariableContext _localctx = new DefineSignatureVariableContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_defineSignatureVariable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			varid();
			setState(148);
			match(COLON);
			setState(149);
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

	public static class DeclareVarStatContext extends ParserRuleContext {
		public DeclareVarStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declareVarStat; }
	 
		public DeclareVarStatContext() { }
		public void copyFrom(DeclareVarStatContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class HasTypeVarDeclareContext extends DeclareVarStatContext {
		public TerminalNode VAR() { return getToken(BedrockLangParser.VAR, 0); }
		public VaridContext varid() {
			return getRuleContext(VaridContext.class,0);
		}
		public TerminalNode COLON() { return getToken(BedrockLangParser.COLON, 0); }
		public TerminalNode ID() { return getToken(BedrockLangParser.ID, 0); }
		public TerminalNode SET() { return getToken(BedrockLangParser.SET, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public HasTypeVarDeclareContext(DeclareVarStatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterHasTypeVarDeclare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitHasTypeVarDeclare(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitHasTypeVarDeclare(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InferTypeVarDeclareContext extends DeclareVarStatContext {
		public TerminalNode VAR() { return getToken(BedrockLangParser.VAR, 0); }
		public VaridContext varid() {
			return getRuleContext(VaridContext.class,0);
		}
		public TerminalNode SET() { return getToken(BedrockLangParser.SET, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public InferTypeVarDeclareContext(DeclareVarStatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterInferTypeVarDeclare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitInferTypeVarDeclare(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitInferTypeVarDeclare(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclareVarStatContext declareVarStat() throws RecognitionException {
		DeclareVarStatContext _localctx = new DeclareVarStatContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_declareVarStat);
		int _la;
		try {
			setState(164);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				_localctx = new HasTypeVarDeclareContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(151);
				match(VAR);
				setState(152);
				varid();
				setState(153);
				match(COLON);
				setState(154);
				match(ID);
				setState(157);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SET) {
					{
					setState(155);
					match(SET);
					setState(156);
					expr(0);
					}
				}

				}
				break;
			case 2:
				_localctx = new InferTypeVarDeclareContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(159);
				match(VAR);
				setState(160);
				varid();
				setState(161);
				match(SET);
				setState(162);
				expr(0);
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

	public static class ReturnStatContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(BedrockLangParser.RETURN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ReturnStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterReturnStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitReturnStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitReturnStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStatContext returnStat() throws RecognitionException {
		ReturnStatContext _localctx = new ReturnStatContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_returnStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			match(RETURN);
			setState(167);
			expr(0);
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

	public static class CommandContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(BedrockLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(BedrockLangParser.ID, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public CommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_command; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommandContext command() throws RecognitionException {
		CommandContext _localctx = new CommandContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_command);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			id();
			setState(174);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(172);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
					case 1:
						{
						setState(170);
						match(ID);
						}
						break;
					case 2:
						{
						setState(171);
						expr(0);
						}
						break;
					}
					} 
				}
				setState(176);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
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

	public static class BlockContext extends ParserRuleContext {
		public TerminalNode START() { return getToken(BedrockLangParser.START, 0); }
		public TerminalNode END() { return getToken(BedrockLangParser.END, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(BedrockLangParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(BedrockLangParser.SEMICOLON, i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_block);
		int _la;
		try {
			setState(197);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(177);
				match(START);
				setState(180);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LB:
				case PLUS:
				case MINUS:
				case DOLLAR:
				case STRING:
				case INT:
				case DEC:
				case BOOL:
				case NULL:
				case ID:
					{
					setState(178);
					expr(0);
					}
					break;
				case DEF:
				case VAR:
				case WHEN:
				case RETURN:
					{
					setState(179);
					stat();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(182);
				match(END);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(184);
				match(START);
				setState(193);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LB) | (1L << PLUS) | (1L << MINUS) | (1L << DOLLAR) | (1L << DEF) | (1L << VAR) | (1L << WHEN) | (1L << RETURN) | (1L << STRING) | (1L << INT) | (1L << DEC) | (1L << BOOL) | (1L << NULL) | (1L << ID))) != 0)) {
					{
					{
					setState(187);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case LB:
					case PLUS:
					case MINUS:
					case DOLLAR:
					case STRING:
					case INT:
					case DEC:
					case BOOL:
					case NULL:
					case ID:
						{
						setState(185);
						expr(0);
						}
						break;
					case DEF:
					case VAR:
					case WHEN:
					case RETURN:
						{
						setState(186);
						stat();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(189);
					match(SEMICOLON);
					}
					}
					setState(195);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(196);
				match(END);
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

	public static class IdContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(BedrockLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(BedrockLangParser.ID, i);
		}
		public List<TerminalNode> DOT() { return getTokens(BedrockLangParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(BedrockLangParser.DOT, i);
		}
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_id);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			match(ID);
			setState(204);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(200);
					match(DOT);
					setState(201);
					match(ID);
					}
					} 
				}
				setState(206);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
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

	public static class VaridContext extends ParserRuleContext {
		public TerminalNode DOLLAR() { return getToken(BedrockLangParser.DOLLAR, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public VaridContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varid; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterVarid(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitVarid(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitVarid(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VaridContext varid() throws RecognitionException {
		VaridContext _localctx = new VaridContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_varid);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			match(DOLLAR);
			setState(208);
			id();
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
		case 2:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		case 2:
			return precpred(_ctx, 4);
		case 3:
			return precpred(_ctx, 3);
		case 4:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3,\u00d5\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\7\2$\n"+
		"\2\f\2\16\2\'\13\2\3\2\3\2\3\2\3\2\3\2\7\2.\n\2\f\2\16\2\61\13\2\3\3\3"+
		"\3\3\3\3\3\5\3\67\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\5\4I\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\7\4Z\n\4\f\4\16\4]\13\4\3\5\3\5\3\5\5\5b\n\5\6\5d\n\5"+
		"\r\5\16\5e\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5p\n\5\3\6\3\6\3\6\3\6\3"+
		"\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\5\b\177\n\b\3\b\3\b\3\b\7\b\u0084\n"+
		"\b\f\b\16\b\u0087\13\b\3\t\3\t\3\n\3\n\3\n\5\n\u008e\n\n\6\n\u0090\n\n"+
		"\r\n\16\n\u0091\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\5"+
		"\f\u00a0\n\f\3\f\3\f\3\f\3\f\3\f\5\f\u00a7\n\f\3\r\3\r\3\r\3\16\3\16\3"+
		"\16\7\16\u00af\n\16\f\16\16\16\u00b2\13\16\3\17\3\17\3\17\5\17\u00b7\n"+
		"\17\3\17\3\17\3\17\3\17\3\17\5\17\u00be\n\17\3\17\3\17\7\17\u00c2\n\17"+
		"\f\17\16\17\u00c5\13\17\3\17\5\17\u00c8\n\17\3\20\3\20\3\20\7\20\u00cd"+
		"\n\20\f\20\16\20\u00d0\13\20\3\21\3\21\3\21\3\21\2\3\6\22\2\4\6\b\n\f"+
		"\16\20\22\24\26\30\32\34\36 \2\3\3\2\'+\2\u00e9\2%\3\2\2\2\4\66\3\2\2"+
		"\2\6H\3\2\2\2\bo\3\2\2\2\nq\3\2\2\2\fu\3\2\2\2\16y\3\2\2\2\20\u0088\3"+
		"\2\2\2\22\u008a\3\2\2\2\24\u0095\3\2\2\2\26\u00a6\3\2\2\2\30\u00a8\3\2"+
		"\2\2\32\u00ab\3\2\2\2\34\u00c7\3\2\2\2\36\u00c9\3\2\2\2 \u00d1\3\2\2\2"+
		"\"$\5\b\5\2#\"\3\2\2\2$\'\3\2\2\2%#\3\2\2\2%&\3\2\2\2&/\3\2\2\2\'%\3\2"+
		"\2\2()\5\26\f\2)*\7\7\2\2*.\3\2\2\2+.\5\f\7\2,.\5\n\6\2-(\3\2\2\2-+\3"+
		"\2\2\2-,\3\2\2\2.\61\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\3\3\2\2\2\61/\3"+
		"\2\2\2\62\67\5\f\7\2\63\67\5\n\6\2\64\67\5\26\f\2\65\67\5\30\r\2\66\62"+
		"\3\2\2\2\66\63\3\2\2\2\66\64\3\2\2\2\66\65\3\2\2\2\67\5\3\2\2\289\b\4"+
		"\1\29I\5\32\16\2:I\t\2\2\2;I\5 \21\2<=\7\t\2\2=>\5\6\4\2>?\7\n\2\2?I\3"+
		"\2\2\2@A\7\23\2\2AI\5\6\4\nBC\7\24\2\2CI\5\6\4\tDE\5 \21\2EF\7\22\2\2"+
		"FG\5\6\4\3GI\3\2\2\2H8\3\2\2\2H:\3\2\2\2H;\3\2\2\2H<\3\2\2\2H@\3\2\2\2"+
		"HB\3\2\2\2HD\3\2\2\2I[\3\2\2\2JK\f\b\2\2KL\7\23\2\2LZ\5\6\4\tMN\f\7\2"+
		"\2NO\7\24\2\2OZ\5\6\4\bPQ\f\6\2\2QR\7\25\2\2RZ\5\6\4\7ST\f\5\2\2TU\7\26"+
		"\2\2UZ\5\6\4\6VW\f\4\2\2WX\7\27\2\2XZ\5\6\4\5YJ\3\2\2\2YM\3\2\2\2YP\3"+
		"\2\2\2YS\3\2\2\2YV\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\\7\3\2\2\2"+
		"][\3\2\2\2^c\7$\2\2_a\7,\2\2`b\7\6\2\2a`\3\2\2\2ab\3\2\2\2bd\3\2\2\2c"+
		"_\3\2\2\2de\3\2\2\2ec\3\2\2\2ef\3\2\2\2fg\3\2\2\2gh\7%\2\2hp\5\36\20\2"+
		"ij\7$\2\2jk\7\25\2\2kl\7%\2\2lp\5\36\20\2mn\7$\2\2np\5\36\20\2o^\3\2\2"+
		"\2oi\3\2\2\2om\3\2\2\2p\t\3\2\2\2qr\7\"\2\2rs\7,\2\2st\5\34\17\2t\13\3"+
		"\2\2\2uv\7 \2\2vw\5\16\b\2wx\5\34\17\2x\r\3\2\2\2y~\7,\2\2z{\5 \21\2{"+
		"|\7\b\2\2|}\7,\2\2}\177\3\2\2\2~z\3\2\2\2~\177\3\2\2\2\177\u0085\3\2\2"+
		"\2\u0080\u0084\5\20\t\2\u0081\u0084\5\22\n\2\u0082\u0084\5\24\13\2\u0083"+
		"\u0080\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0082\3\2\2\2\u0084\u0087\3\2"+
		"\2\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\17\3\2\2\2\u0087\u0085"+
		"\3\2\2\2\u0088\u0089\7,\2\2\u0089\21\3\2\2\2\u008a\u008f\7\35\2\2\u008b"+
		"\u008d\7,\2\2\u008c\u008e\7\6\2\2\u008d\u008c\3\2\2\2\u008d\u008e\3\2"+
		"\2\2\u008e\u0090\3\2\2\2\u008f\u008b\3\2\2\2\u0090\u0091\3\2\2\2\u0091"+
		"\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0094\7\33"+
		"\2\2\u0094\23\3\2\2\2\u0095\u0096\5 \21\2\u0096\u0097\7\b\2\2\u0097\u0098"+
		"\7,\2\2\u0098\25\3\2\2\2\u0099\u009a\7!\2\2\u009a\u009b\5 \21\2\u009b"+
		"\u009c\7\b\2\2\u009c\u009f\7,\2\2\u009d\u009e\7\22\2\2\u009e\u00a0\5\6"+
		"\4\2\u009f\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a7\3\2\2\2\u00a1"+
		"\u00a2\7!\2\2\u00a2\u00a3\5 \21\2\u00a3\u00a4\7\22\2\2\u00a4\u00a5\5\6"+
		"\4\2\u00a5\u00a7\3\2\2\2\u00a6\u0099\3\2\2\2\u00a6\u00a1\3\2\2\2\u00a7"+
		"\27\3\2\2\2\u00a8\u00a9\7#\2\2\u00a9\u00aa\5\6\4\2\u00aa\31\3\2\2\2\u00ab"+
		"\u00b0\5\36\20\2\u00ac\u00af\7,\2\2\u00ad\u00af\5\6\4\2\u00ae\u00ac\3"+
		"\2\2\2\u00ae\u00ad\3\2\2\2\u00af\u00b2\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b0"+
		"\u00b1\3\2\2\2\u00b1\33\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b3\u00b6\7\r\2"+
		"\2\u00b4\u00b7\5\6\4\2\u00b5\u00b7\5\4\3\2\u00b6\u00b4\3\2\2\2\u00b6\u00b5"+
		"\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b9\7\16\2\2\u00b9\u00c8\3\2\2\2"+
		"\u00ba\u00c3\7\r\2\2\u00bb\u00be\5\6\4\2\u00bc\u00be\5\4\3\2\u00bd\u00bb"+
		"\3\2\2\2\u00bd\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c0\7\7\2\2\u00c0"+
		"\u00c2\3\2\2\2\u00c1\u00bd\3\2\2\2\u00c2\u00c5\3\2\2\2\u00c3\u00c1\3\2"+
		"\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c6\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c6"+
		"\u00c8\7\16\2\2\u00c7\u00b3\3\2\2\2\u00c7\u00ba\3\2\2\2\u00c8\35\3\2\2"+
		"\2\u00c9\u00ce\7,\2\2\u00ca\u00cb\7\17\2\2\u00cb\u00cd\7,\2\2\u00cc\u00ca"+
		"\3\2\2\2\u00cd\u00d0\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf"+
		"\37\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d1\u00d2\7\37\2\2\u00d2\u00d3\5\36"+
		"\20\2\u00d3!\3\2\2\2\32%-/\66HY[aeo~\u0083\u0085\u008d\u0091\u009f\u00a6"+
		"\u00ae\u00b0\u00b6\u00bd\u00c3\u00c7\u00ce";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}