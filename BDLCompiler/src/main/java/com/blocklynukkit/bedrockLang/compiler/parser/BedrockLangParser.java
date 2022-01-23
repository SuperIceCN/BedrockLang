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
		IMPORT=34, FROM=35, AS=36, IF=37, ELSE=38, ELIF=39, WHILE=40, BREAK=41, 
		CONTINUE=42, STRING=43, INT=44, DEC=45, BOOL=46, NULL=47, ID=48;
	public static final int
		RULE_program = 0, RULE_stat = 1, RULE_expr = 2, RULE_importStat = 3, RULE_whenStat = 4, 
		RULE_ifElseStat = 5, RULE_whileStat = 6, RULE_defineCmdStat = 7, RULE_defineSignature = 8, 
		RULE_defineSignatureWordSingle = 9, RULE_defineSignatureVariable = 10, 
		RULE_declareVarStat = 11, RULE_returnStat = 12, RULE_breakStat = 13, RULE_continueStat = 14, 
		RULE_command = 15, RULE_block = 16, RULE_id = 17, RULE_varid = 18, RULE_commandId = 19;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "stat", "expr", "importStat", "whenStat", "ifElseStat", "whileStat", 
			"defineCmdStat", "defineSignature", "defineSignatureWordSingle", "defineSignatureVariable", 
			"declareVarStat", "returnStat", "breakStat", "continueStat", "command", 
			"block", "id", "varid", "commandId"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "','", null, "':'", "'('", "')'", "'['", "']'", 
			"'{'", "'}'", "'.'", "'=='", "'!='", "'='", "'+'", "'-'", "'*'", "'/'", 
			"'%'", "'!'", "'&'", "'|'", "'>'", "'>='", "'<'", "'<='", "'$'", "'def'", 
			"'var'", "'when'", "'return'", "'import'", "'from'", "'as'", "'if'", 
			"'else'", "'elif'", "'while'", "'break'", "'continue'", null, null, null, 
			null, "'null'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WS", "COMMENT", "MUTICOMMENT", "COMMA", "SEMICOLON", "COLON", 
			"LB", "RB", "LA", "RA", "START", "END", "DOT", "EQ", "NEQ", "SET", "PLUS", 
			"MINUS", "MULTIPLY", "DIVIDE", "REMAIN", "NOT", "AND", "OR", "GTR", "GTREQ", 
			"LWR", "LWREQ", "DOLLAR", "DEF", "VAR", "WHEN", "RETURN", "IMPORT", "FROM", 
			"AS", "IF", "ELSE", "ELIF", "WHILE", "BREAK", "CONTINUE", "STRING", "INT", 
			"DEC", "BOOL", "NULL", "ID"
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
			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IMPORT) {
				{
				{
				setState(40);
				importStat();
				setState(42);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SEMICOLON) {
					{
					setState(41);
					match(SEMICOLON);
					}
				}

				}
				}
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DEF) | (1L << VAR) | (1L << WHEN))) != 0)) {
				{
				setState(54);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case VAR:
					{
					setState(49);
					declareVarStat();
					setState(50);
					match(SEMICOLON);
					}
					break;
				case DEF:
					{
					setState(52);
					defineCmdStat();
					}
					break;
				case WHEN:
					{
					setState(53);
					whenStat();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(58);
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
		public IfElseStatContext ifElseStat() {
			return getRuleContext(IfElseStatContext.class,0);
		}
		public WhileStatContext whileStat() {
			return getRuleContext(WhileStatContext.class,0);
		}
		public DeclareVarStatContext declareVarStat() {
			return getRuleContext(DeclareVarStatContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(BedrockLangParser.SEMICOLON, 0); }
		public ReturnStatContext returnStat() {
			return getRuleContext(ReturnStatContext.class,0);
		}
		public BreakStatContext breakStat() {
			return getRuleContext(BreakStatContext.class,0);
		}
		public ContinueStatContext continueStat() {
			return getRuleContext(ContinueStatContext.class,0);
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
			setState(73);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IF:
				enterOuterAlt(_localctx, 1);
				{
				setState(59);
				ifElseStat();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
				whileStat();
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 3);
				{
				setState(61);
				declareVarStat();
				setState(62);
				match(SEMICOLON);
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 4);
				{
				setState(64);
				returnStat();
				setState(65);
				match(SEMICOLON);
				}
				break;
			case BREAK:
				enterOuterAlt(_localctx, 5);
				{
				setState(67);
				breakStat();
				setState(68);
				match(SEMICOLON);
				}
				break;
			case CONTINUE:
				enterOuterAlt(_localctx, 6);
				{
				setState(70);
				continueStat();
				setState(71);
				match(SEMICOLON);
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
	public static class ChainVirtualFieldExprContext extends ExprContext {
		public List<VaridContext> varid() {
			return getRuleContexts(VaridContext.class);
		}
		public VaridContext varid(int i) {
			return getRuleContext(VaridContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(BedrockLangParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(BedrockLangParser.DOT, i);
		}
		public List<TerminalNode> ID() { return getTokens(BedrockLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(BedrockLangParser.ID, i);
		}
		public ChainVirtualFieldExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterChainVirtualFieldExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitChainVirtualFieldExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitChainVirtualFieldExpr(this);
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
	public static class ChainStaticFieldExprContext extends ExprContext {
		public List<TerminalNode> ID() { return getTokens(BedrockLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(BedrockLangParser.ID, i);
		}
		public List<TerminalNode> DOT() { return getTokens(BedrockLangParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(BedrockLangParser.DOT, i);
		}
		public List<VaridContext> varid() {
			return getRuleContexts(VaridContext.class);
		}
		public VaridContext varid(int i) {
			return getRuleContext(VaridContext.class,i);
		}
		public ChainStaticFieldExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterChainStaticFieldExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitChainStaticFieldExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitChainStaticFieldExpr(this);
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
			setState(116);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				_localctx = new CommandExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(76);
				command();
				}
				break;
			case 2:
				{
				_localctx = new LiteralExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(77);
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
				setState(78);
				varid();
				}
				break;
			case 4:
				{
				_localctx = new BracketExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(79);
				match(LB);
				setState(80);
				expr(0);
				setState(81);
				match(RB);
				}
				break;
			case 5:
				{
				_localctx = new PositiveExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(83);
				match(PLUS);
				setState(84);
				expr(10);
				}
				break;
			case 6:
				{
				_localctx = new NegativeExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(85);
				match(MINUS);
				setState(86);
				expr(9);
				}
				break;
			case 7:
				{
				_localctx = new SetVarExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(87);
				varid();
				setState(88);
				match(SET);
				setState(89);
				expr(3);
				}
				break;
			case 8:
				{
				_localctx = new ChainStaticFieldExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(91);
				match(ID);
				setState(98);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						setState(96);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
						case 1:
							{
							setState(92);
							match(DOT);
							setState(93);
							match(ID);
							}
							break;
						case 2:
							{
							setState(94);
							match(DOT);
							setState(95);
							varid();
							}
							break;
						}
						} 
					}
					setState(100);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
				}
				setState(101);
				match(DOT);
				setState(102);
				varid();
				}
				break;
			case 9:
				{
				_localctx = new ChainVirtualFieldExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(103);
				varid();
				setState(110);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						setState(108);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
						case 1:
							{
							setState(104);
							match(DOT);
							setState(105);
							match(ID);
							}
							break;
						case 2:
							{
							setState(106);
							match(DOT);
							setState(107);
							varid();
							}
							break;
						}
						} 
					}
					setState(112);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
				}
				setState(113);
				match(DOT);
				setState(114);
				varid();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(135);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(133);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
					case 1:
						{
						_localctx = new PlusExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(118);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(119);
						match(PLUS);
						setState(120);
						expr(9);
						}
						break;
					case 2:
						{
						_localctx = new MinusExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(121);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(122);
						match(MINUS);
						setState(123);
						expr(8);
						}
						break;
					case 3:
						{
						_localctx = new MultiplyExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(124);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(125);
						match(MULTIPLY);
						setState(126);
						expr(7);
						}
						break;
					case 4:
						{
						_localctx = new DevideExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(127);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(128);
						match(DIVIDE);
						setState(129);
						expr(6);
						}
						break;
					case 5:
						{
						_localctx = new RemainExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(130);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(131);
						match(REMAIN);
						setState(132);
						expr(5);
						}
						break;
					}
					} 
				}
				setState(137);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
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
			setState(155);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				_localctx = new ImportSingleStaticContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(138);
				match(IMPORT);
				setState(143); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(139);
					match(ID);
					setState(141);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(140);
						match(COMMA);
						}
					}

					}
					}
					setState(145); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ID );
				setState(147);
				match(FROM);
				setState(148);
				id();
				}
				break;
			case 2:
				_localctx = new ImportAllStaticContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(149);
				match(IMPORT);
				setState(150);
				match(MULTIPLY);
				setState(151);
				match(FROM);
				setState(152);
				id();
				}
				break;
			case 3:
				_localctx = new ImportClassContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(153);
				match(IMPORT);
				setState(154);
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
			setState(157);
			match(WHEN);
			setState(158);
			match(ID);
			setState(159);
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

	public static class IfElseStatContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(BedrockLangParser.IF, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public List<TerminalNode> ELIF() { return getTokens(BedrockLangParser.ELIF); }
		public TerminalNode ELIF(int i) {
			return getToken(BedrockLangParser.ELIF, i);
		}
		public TerminalNode ELSE() { return getToken(BedrockLangParser.ELSE, 0); }
		public IfElseStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifElseStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterIfElseStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitIfElseStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitIfElseStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfElseStatContext ifElseStat() throws RecognitionException {
		IfElseStatContext _localctx = new IfElseStatContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_ifElseStat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(IF);
			setState(162);
			expr(0);
			setState(163);
			block();
			setState(170);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELIF) {
				{
				{
				setState(164);
				match(ELIF);
				setState(165);
				expr(0);
				setState(166);
				block();
				}
				}
				setState(172);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(175);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(173);
				match(ELSE);
				setState(174);
				block();
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

	public static class WhileStatContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(BedrockLangParser.WHILE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode LA() { return getToken(BedrockLangParser.LA, 0); }
		public TerminalNode ID() { return getToken(BedrockLangParser.ID, 0); }
		public TerminalNode RA() { return getToken(BedrockLangParser.RA, 0); }
		public WhileStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterWhileStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitWhileStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitWhileStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStatContext whileStat() throws RecognitionException {
		WhileStatContext _localctx = new WhileStatContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_whileStat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			match(WHILE);
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LA) {
				{
				setState(178);
				match(LA);
				setState(179);
				match(ID);
				setState(180);
				match(RA);
				}
			}

			setState(183);
			expr(0);
			setState(184);
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
		enterRule(_localctx, 14, RULE_defineCmdStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			match(DEF);
			setState(187);
			defineSignature();
			setState(188);
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
		public TerminalNode COLON() { return getToken(BedrockLangParser.COLON, 0); }
		public List<DefineSignatureWordSingleContext> defineSignatureWordSingle() {
			return getRuleContexts(DefineSignatureWordSingleContext.class);
		}
		public DefineSignatureWordSingleContext defineSignatureWordSingle(int i) {
			return getRuleContext(DefineSignatureWordSingleContext.class,i);
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
		enterRule(_localctx, 16, RULE_defineSignature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			match(ID);
			setState(193);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(191);
				match(COLON);
				setState(192);
				match(ID);
				}
			}

			setState(199);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOLLAR || _la==ID) {
				{
				setState(197);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ID:
					{
					setState(195);
					defineSignatureWordSingle();
					}
					break;
				case DOLLAR:
					{
					setState(196);
					defineSignatureVariable();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(201);
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
		enterRule(_localctx, 18, RULE_defineSignatureWordSingle);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
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
		enterRule(_localctx, 20, RULE_defineSignatureVariable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			varid();
			setState(205);
			match(COLON);
			setState(206);
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
		enterRule(_localctx, 22, RULE_declareVarStat);
		int _la;
		try {
			setState(221);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				_localctx = new HasTypeVarDeclareContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(208);
				match(VAR);
				setState(209);
				varid();
				setState(210);
				match(COLON);
				setState(211);
				match(ID);
				setState(214);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SET) {
					{
					setState(212);
					match(SET);
					setState(213);
					expr(0);
					}
				}

				}
				break;
			case 2:
				_localctx = new InferTypeVarDeclareContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(216);
				match(VAR);
				setState(217);
				varid();
				setState(218);
				match(SET);
				setState(219);
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
		enterRule(_localctx, 24, RULE_returnStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			match(RETURN);
			setState(224);
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

	public static class BreakStatContext extends ParserRuleContext {
		public TerminalNode BREAK() { return getToken(BedrockLangParser.BREAK, 0); }
		public TerminalNode ID() { return getToken(BedrockLangParser.ID, 0); }
		public BreakStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterBreakStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitBreakStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitBreakStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreakStatContext breakStat() throws RecognitionException {
		BreakStatContext _localctx = new BreakStatContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_breakStat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			match(BREAK);
			setState(228);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(227);
				match(ID);
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

	public static class ContinueStatContext extends ParserRuleContext {
		public TerminalNode CONTINUE() { return getToken(BedrockLangParser.CONTINUE, 0); }
		public TerminalNode ID() { return getToken(BedrockLangParser.ID, 0); }
		public ContinueStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterContinueStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitContinueStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitContinueStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContinueStatContext continueStat() throws RecognitionException {
		ContinueStatContext _localctx = new ContinueStatContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_continueStat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			match(CONTINUE);
			setState(232);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(231);
				match(ID);
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

	public static class CommandContext extends ParserRuleContext {
		public CommandIdContext commandId() {
			return getRuleContext(CommandIdContext.class,0);
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
		enterRule(_localctx, 30, RULE_command);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
			commandId();
			setState(239);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(237);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
					case 1:
						{
						setState(235);
						match(ID);
						}
						break;
					case 2:
						{
						setState(236);
						expr(0);
						}
						break;
					}
					} 
				}
				setState(241);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
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
		public List<TerminalNode> SEMICOLON() { return getTokens(BedrockLangParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(BedrockLangParser.SEMICOLON, i);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
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
		enterRule(_localctx, 32, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			match(START);
			setState(249);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LB) | (1L << PLUS) | (1L << MINUS) | (1L << DOLLAR) | (1L << VAR) | (1L << RETURN) | (1L << IF) | (1L << WHILE) | (1L << BREAK) | (1L << CONTINUE) | (1L << STRING) | (1L << INT) | (1L << DEC) | (1L << BOOL) | (1L << NULL) | (1L << ID))) != 0)) {
				{
				setState(247);
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
					setState(243);
					expr(0);
					setState(244);
					match(SEMICOLON);
					}
					break;
				case VAR:
				case RETURN:
				case IF:
				case WHILE:
				case BREAK:
				case CONTINUE:
					{
					setState(246);
					stat();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(251);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(252);
			match(END);
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
		public List<VaridContext> varid() {
			return getRuleContexts(VaridContext.class);
		}
		public VaridContext varid(int i) {
			return getRuleContext(VaridContext.class,i);
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
		enterRule(_localctx, 34, RULE_id);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			match(ID);
			setState(261);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(259);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
					case 1:
						{
						setState(255);
						match(DOT);
						setState(256);
						match(ID);
						}
						break;
					case 2:
						{
						setState(257);
						match(DOT);
						setState(258);
						varid();
						}
						break;
					}
					} 
				}
				setState(263);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
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
		enterRule(_localctx, 36, RULE_varid);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264);
			match(DOLLAR);
			setState(265);
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

	public static class CommandIdContext extends ParserRuleContext {
		public CommandIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_commandId; }
	 
		public CommandIdContext() { }
		public void copyFrom(CommandIdContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VirtualCommandContext extends CommandIdContext {
		public List<VaridContext> varid() {
			return getRuleContexts(VaridContext.class);
		}
		public VaridContext varid(int i) {
			return getRuleContext(VaridContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(BedrockLangParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(BedrockLangParser.DOT, i);
		}
		public List<TerminalNode> ID() { return getTokens(BedrockLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(BedrockLangParser.ID, i);
		}
		public VirtualCommandContext(CommandIdContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterVirtualCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitVirtualCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitVirtualCommand(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InvokeCommandContext extends CommandIdContext {
		public List<TerminalNode> ID() { return getTokens(BedrockLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(BedrockLangParser.ID, i);
		}
		public List<TerminalNode> DOT() { return getTokens(BedrockLangParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(BedrockLangParser.DOT, i);
		}
		public List<VaridContext> varid() {
			return getRuleContexts(VaridContext.class);
		}
		public VaridContext varid(int i) {
			return getRuleContext(VaridContext.class,i);
		}
		public InvokeCommandContext(CommandIdContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterInvokeCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitInvokeCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitInvokeCommand(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CallCommandContext extends CommandIdContext {
		public TerminalNode ID() { return getToken(BedrockLangParser.ID, 0); }
		public CallCommandContext(CommandIdContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).enterCallCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BedrockLangListener ) ((BedrockLangListener)listener).exitCallCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BedrockLangVisitor ) return ((BedrockLangVisitor<? extends T>)visitor).visitCallCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommandIdContext commandId() throws RecognitionException {
		CommandIdContext _localctx = new CommandIdContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_commandId);
		try {
			int _alt;
			setState(293);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				_localctx = new InvokeCommandContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(267);
				match(ID);
				setState(274);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						setState(272);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
						case 1:
							{
							setState(268);
							match(DOT);
							setState(269);
							match(ID);
							}
							break;
						case 2:
							{
							setState(270);
							match(DOT);
							setState(271);
							varid();
							}
							break;
						}
						} 
					}
					setState(276);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
				}
				setState(277);
				match(DOT);
				setState(278);
				match(ID);
				}
				break;
			case 2:
				_localctx = new CallCommandContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(279);
				match(ID);
				}
				break;
			case 3:
				_localctx = new VirtualCommandContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(280);
				varid();
				setState(287);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						setState(285);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
						case 1:
							{
							setState(281);
							match(DOT);
							setState(282);
							match(ID);
							}
							break;
						case 2:
							{
							setState(283);
							match(DOT);
							setState(284);
							varid();
							}
							break;
						}
						} 
					}
					setState(289);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
				}
				setState(290);
				match(DOT);
				setState(291);
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
			return precpred(_ctx, 8);
		case 1:
			return precpred(_ctx, 7);
		case 2:
			return precpred(_ctx, 6);
		case 3:
			return precpred(_ctx, 5);
		case 4:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\62\u012a\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\5\2-\n\2\7\2/\n\2\f\2\16\2\62\13"+
		"\2\3\2\3\2\3\2\3\2\3\2\7\29\n\2\f\2\16\2<\13\2\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3L\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4c\n\4\f\4"+
		"\16\4f\13\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4o\n\4\f\4\16\4r\13\4\3\4\3"+
		"\4\3\4\5\4w\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\7\4\u0088\n\4\f\4\16\4\u008b\13\4\3\5\3\5\3\5\5\5\u0090\n\5\6\5"+
		"\u0092\n\5\r\5\16\5\u0093\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u009e\n"+
		"\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7\u00ab\n\7\f\7\16\7"+
		"\u00ae\13\7\3\7\3\7\5\7\u00b2\n\7\3\b\3\b\3\b\3\b\5\b\u00b8\n\b\3\b\3"+
		"\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\5\n\u00c4\n\n\3\n\3\n\7\n\u00c8\n\n"+
		"\f\n\16\n\u00cb\13\n\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\5\r\u00d9\n\r\3\r\3\r\3\r\3\r\3\r\5\r\u00e0\n\r\3\16\3\16\3\16\3\17\3"+
		"\17\5\17\u00e7\n\17\3\20\3\20\5\20\u00eb\n\20\3\21\3\21\3\21\7\21\u00f0"+
		"\n\21\f\21\16\21\u00f3\13\21\3\22\3\22\3\22\3\22\3\22\7\22\u00fa\n\22"+
		"\f\22\16\22\u00fd\13\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\7\23\u0106"+
		"\n\23\f\23\16\23\u0109\13\23\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\7"+
		"\25\u0113\n\25\f\25\16\25\u0116\13\25\3\25\3\25\3\25\3\25\3\25\3\25\3"+
		"\25\3\25\7\25\u0120\n\25\f\25\16\25\u0123\13\25\3\25\3\25\3\25\5\25\u0128"+
		"\n\25\3\25\2\3\6\26\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(\2\3\3"+
		"\2-\61\2\u014a\2\60\3\2\2\2\4K\3\2\2\2\6v\3\2\2\2\b\u009d\3\2\2\2\n\u009f"+
		"\3\2\2\2\f\u00a3\3\2\2\2\16\u00b3\3\2\2\2\20\u00bc\3\2\2\2\22\u00c0\3"+
		"\2\2\2\24\u00cc\3\2\2\2\26\u00ce\3\2\2\2\30\u00df\3\2\2\2\32\u00e1\3\2"+
		"\2\2\34\u00e4\3\2\2\2\36\u00e8\3\2\2\2 \u00ec\3\2\2\2\"\u00f4\3\2\2\2"+
		"$\u0100\3\2\2\2&\u010a\3\2\2\2(\u0127\3\2\2\2*,\5\b\5\2+-\7\7\2\2,+\3"+
		"\2\2\2,-\3\2\2\2-/\3\2\2\2.*\3\2\2\2/\62\3\2\2\2\60.\3\2\2\2\60\61\3\2"+
		"\2\2\61:\3\2\2\2\62\60\3\2\2\2\63\64\5\30\r\2\64\65\7\7\2\2\659\3\2\2"+
		"\2\669\5\20\t\2\679\5\n\6\28\63\3\2\2\28\66\3\2\2\28\67\3\2\2\29<\3\2"+
		"\2\2:8\3\2\2\2:;\3\2\2\2;\3\3\2\2\2<:\3\2\2\2=L\5\f\7\2>L\5\16\b\2?@\5"+
		"\30\r\2@A\7\7\2\2AL\3\2\2\2BC\5\32\16\2CD\7\7\2\2DL\3\2\2\2EF\5\34\17"+
		"\2FG\7\7\2\2GL\3\2\2\2HI\5\36\20\2IJ\7\7\2\2JL\3\2\2\2K=\3\2\2\2K>\3\2"+
		"\2\2K?\3\2\2\2KB\3\2\2\2KE\3\2\2\2KH\3\2\2\2L\5\3\2\2\2MN\b\4\1\2Nw\5"+
		" \21\2Ow\t\2\2\2Pw\5&\24\2QR\7\t\2\2RS\5\6\4\2ST\7\n\2\2Tw\3\2\2\2UV\7"+
		"\23\2\2Vw\5\6\4\fWX\7\24\2\2Xw\5\6\4\13YZ\5&\24\2Z[\7\22\2\2[\\\5\6\4"+
		"\5\\w\3\2\2\2]d\7\62\2\2^_\7\17\2\2_c\7\62\2\2`a\7\17\2\2ac\5&\24\2b^"+
		"\3\2\2\2b`\3\2\2\2cf\3\2\2\2db\3\2\2\2de\3\2\2\2eg\3\2\2\2fd\3\2\2\2g"+
		"h\7\17\2\2hw\5&\24\2ip\5&\24\2jk\7\17\2\2ko\7\62\2\2lm\7\17\2\2mo\5&\24"+
		"\2nj\3\2\2\2nl\3\2\2\2or\3\2\2\2pn\3\2\2\2pq\3\2\2\2qs\3\2\2\2rp\3\2\2"+
		"\2st\7\17\2\2tu\5&\24\2uw\3\2\2\2vM\3\2\2\2vO\3\2\2\2vP\3\2\2\2vQ\3\2"+
		"\2\2vU\3\2\2\2vW\3\2\2\2vY\3\2\2\2v]\3\2\2\2vi\3\2\2\2w\u0089\3\2\2\2"+
		"xy\f\n\2\2yz\7\23\2\2z\u0088\5\6\4\13{|\f\t\2\2|}\7\24\2\2}\u0088\5\6"+
		"\4\n~\177\f\b\2\2\177\u0080\7\25\2\2\u0080\u0088\5\6\4\t\u0081\u0082\f"+
		"\7\2\2\u0082\u0083\7\26\2\2\u0083\u0088\5\6\4\b\u0084\u0085\f\6\2\2\u0085"+
		"\u0086\7\27\2\2\u0086\u0088\5\6\4\7\u0087x\3\2\2\2\u0087{\3\2\2\2\u0087"+
		"~\3\2\2\2\u0087\u0081\3\2\2\2\u0087\u0084\3\2\2\2\u0088\u008b\3\2\2\2"+
		"\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\7\3\2\2\2\u008b\u0089\3"+
		"\2\2\2\u008c\u0091\7$\2\2\u008d\u008f\7\62\2\2\u008e\u0090\7\6\2\2\u008f"+
		"\u008e\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0092\3\2\2\2\u0091\u008d\3\2"+
		"\2\2\u0092\u0093\3\2\2\2\u0093\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094"+
		"\u0095\3\2\2\2\u0095\u0096\7%\2\2\u0096\u009e\5$\23\2\u0097\u0098\7$\2"+
		"\2\u0098\u0099\7\25\2\2\u0099\u009a\7%\2\2\u009a\u009e\5$\23\2\u009b\u009c"+
		"\7$\2\2\u009c\u009e\5$\23\2\u009d\u008c\3\2\2\2\u009d\u0097\3\2\2\2\u009d"+
		"\u009b\3\2\2\2\u009e\t\3\2\2\2\u009f\u00a0\7\"\2\2\u00a0\u00a1\7\62\2"+
		"\2\u00a1\u00a2\5\"\22\2\u00a2\13\3\2\2\2\u00a3\u00a4\7\'\2\2\u00a4\u00a5"+
		"\5\6\4\2\u00a5\u00ac\5\"\22\2\u00a6\u00a7\7)\2\2\u00a7\u00a8\5\6\4\2\u00a8"+
		"\u00a9\5\"\22\2\u00a9\u00ab\3\2\2\2\u00aa\u00a6\3\2\2\2\u00ab\u00ae\3"+
		"\2\2\2\u00ac\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00b1\3\2\2\2\u00ae"+
		"\u00ac\3\2\2\2\u00af\u00b0\7(\2\2\u00b0\u00b2\5\"\22\2\u00b1\u00af\3\2"+
		"\2\2\u00b1\u00b2\3\2\2\2\u00b2\r\3\2\2\2\u00b3\u00b7\7*\2\2\u00b4\u00b5"+
		"\7\13\2\2\u00b5\u00b6\7\62\2\2\u00b6\u00b8\7\f\2\2\u00b7\u00b4\3\2\2\2"+
		"\u00b7\u00b8\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00ba\5\6\4\2\u00ba\u00bb"+
		"\5\"\22\2\u00bb\17\3\2\2\2\u00bc\u00bd\7 \2\2\u00bd\u00be\5\22\n\2\u00be"+
		"\u00bf\5\"\22\2\u00bf\21\3\2\2\2\u00c0\u00c3\7\62\2\2\u00c1\u00c2\7\b"+
		"\2\2\u00c2\u00c4\7\62\2\2\u00c3\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4"+
		"\u00c9\3\2\2\2\u00c5\u00c8\5\24\13\2\u00c6\u00c8\5\26\f\2\u00c7\u00c5"+
		"\3\2\2\2\u00c7\u00c6\3\2\2\2\u00c8\u00cb\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9"+
		"\u00ca\3\2\2\2\u00ca\23\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cc\u00cd\7\62\2"+
		"\2\u00cd\25\3\2\2\2\u00ce\u00cf\5&\24\2\u00cf\u00d0\7\b\2\2\u00d0\u00d1"+
		"\7\62\2\2\u00d1\27\3\2\2\2\u00d2\u00d3\7!\2\2\u00d3\u00d4\5&\24\2\u00d4"+
		"\u00d5\7\b\2\2\u00d5\u00d8\7\62\2\2\u00d6\u00d7\7\22\2\2\u00d7\u00d9\5"+
		"\6\4\2\u00d8\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00e0\3\2\2\2\u00da"+
		"\u00db\7!\2\2\u00db\u00dc\5&\24\2\u00dc\u00dd\7\22\2\2\u00dd\u00de\5\6"+
		"\4\2\u00de\u00e0\3\2\2\2\u00df\u00d2\3\2\2\2\u00df\u00da\3\2\2\2\u00e0"+
		"\31\3\2\2\2\u00e1\u00e2\7#\2\2\u00e2\u00e3\5\6\4\2\u00e3\33\3\2\2\2\u00e4"+
		"\u00e6\7+\2\2\u00e5\u00e7\7\62\2\2\u00e6\u00e5\3\2\2\2\u00e6\u00e7\3\2"+
		"\2\2\u00e7\35\3\2\2\2\u00e8\u00ea\7,\2\2\u00e9\u00eb\7\62\2\2\u00ea\u00e9"+
		"\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\37\3\2\2\2\u00ec\u00f1\5(\25\2\u00ed"+
		"\u00f0\7\62\2\2\u00ee\u00f0\5\6\4\2\u00ef\u00ed\3\2\2\2\u00ef\u00ee\3"+
		"\2\2\2\u00f0\u00f3\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2"+
		"!\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f4\u00fb\7\r\2\2\u00f5\u00f6\5\6\4\2"+
		"\u00f6\u00f7\7\7\2\2\u00f7\u00fa\3\2\2\2\u00f8\u00fa\5\4\3\2\u00f9\u00f5"+
		"\3\2\2\2\u00f9\u00f8\3\2\2\2\u00fa\u00fd\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb"+
		"\u00fc\3\2\2\2\u00fc\u00fe\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fe\u00ff\7\16"+
		"\2\2\u00ff#\3\2\2\2\u0100\u0107\7\62\2\2\u0101\u0102\7\17\2\2\u0102\u0106"+
		"\7\62\2\2\u0103\u0104\7\17\2\2\u0104\u0106\5&\24\2\u0105\u0101\3\2\2\2"+
		"\u0105\u0103\3\2\2\2\u0106\u0109\3\2\2\2\u0107\u0105\3\2\2\2\u0107\u0108"+
		"\3\2\2\2\u0108%\3\2\2\2\u0109\u0107\3\2\2\2\u010a\u010b\7\37\2\2\u010b"+
		"\u010c\5$\23\2\u010c\'\3\2\2\2\u010d\u0114\7\62\2\2\u010e\u010f\7\17\2"+
		"\2\u010f\u0113\7\62\2\2\u0110\u0111\7\17\2\2\u0111\u0113\5&\24\2\u0112"+
		"\u010e\3\2\2\2\u0112\u0110\3\2\2\2\u0113\u0116\3\2\2\2\u0114\u0112\3\2"+
		"\2\2\u0114\u0115\3\2\2\2\u0115\u0117\3\2\2\2\u0116\u0114\3\2\2\2\u0117"+
		"\u0118\7\17\2\2\u0118\u0128\7\62\2\2\u0119\u0128\7\62\2\2\u011a\u0121"+
		"\5&\24\2\u011b\u011c\7\17\2\2\u011c\u0120\7\62\2\2\u011d\u011e\7\17\2"+
		"\2\u011e\u0120\5&\24\2\u011f\u011b\3\2\2\2\u011f\u011d\3\2\2\2\u0120\u0123"+
		"\3\2\2\2\u0121\u011f\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0124\3\2\2\2\u0123"+
		"\u0121\3\2\2\2\u0124\u0125\7\17\2\2\u0125\u0126\7\62\2\2\u0126\u0128\3"+
		"\2\2\2\u0127\u010d\3\2\2\2\u0127\u0119\3\2\2\2\u0127\u011a\3\2\2\2\u0128"+
		")\3\2\2\2&,\608:Kbdnpv\u0087\u0089\u008f\u0093\u009d\u00ac\u00b1\u00b7"+
		"\u00c3\u00c7\u00c9\u00d8\u00df\u00e6\u00ea\u00ef\u00f1\u00f9\u00fb\u0105"+
		"\u0107\u0112\u0114\u011f\u0121\u0127";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}