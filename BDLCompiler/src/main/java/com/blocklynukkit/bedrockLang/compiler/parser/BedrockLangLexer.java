// Generated from D:/ideaProject/BedrockLang/BDLCompiler/src/main/antlr\BedrockLang.g4 by ANTLR 4.9.1
package com.blocklynukkit.bedrockLang.compiler.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BedrockLangLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"WS", "COMMENT", "MUTICOMMENT", "COMMA", "SEMICOLON", "COLON", "LB", 
			"RB", "LA", "RA", "START", "END", "DOT", "EQ", "NEQ", "SET", "PLUS", 
			"MINUS", "MULTIPLY", "DIVIDE", "REMAIN", "NOT", "AND", "OR", "GTR", "GTREQ", 
			"LWR", "LWREQ", "DOLLAR", "DEF", "VAR", "WHEN", "RETURN", "IMPORT", "FROM", 
			"AS", "STRCHAR", "INTEGER", "ESCAPECHAR", "STRING", "INT", "DEC", "BOOL", 
			"NULL", "IDStart", "IDPart", "ID"
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


	public BedrockLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "BedrockLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2,\u011e\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3j"+
		"\n\3\f\3\16\3m\13\3\3\3\3\3\3\4\3\4\3\4\3\4\7\4u\n\4\f\4\16\4x\13\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\5\3\5\3\6\6\6\u0082\n\6\r\6\16\6\u0083\3\7\3\7\3"+
		"\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3"+
		"\17\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3"+
		"\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\33\3\34\3"+
		"\34\3\35\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3!\3!\3!"+
		"\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3"+
		"$\3%\3%\3%\3&\3&\3&\5&\u00e0\n&\3\'\6\'\u00e3\n\'\r\'\16\'\u00e4\3(\3"+
		"(\3(\3)\3)\3)\7)\u00ed\n)\f)\16)\u00f0\13)\3)\3)\3)\3)\7)\u00f6\n)\f)"+
		"\16)\u00f9\13)\3)\5)\u00fc\n)\3*\3*\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3,\3"+
		",\3,\5,\u010d\n,\3-\3-\3-\3-\3-\3.\3.\3/\3/\3\60\3\60\7\60\u011a\n\60"+
		"\f\60\16\60\u011d\13\60\3v\2\61\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\2M\2O\2Q\'S(U)W*Y+[\2"+
		"]\2_,\3\2\n\5\2\13\f\17\17\"\"\3\2\f\f\3\2$$\3\2\62;\4\2$$^^\4\2))^^\25"+
		"\2\13\f\17\17\"B]`bb}\u0080\u00b9\u00b9\u201a\u201b\u201e\u201f\u2028"+
		"\u2028\u3003\u3004\u300c\u300d\u3012\u3013\uff03\uff03\uff0a\uff0b\uff0e"+
		"\uff0e\uff1c\uff1d\uff21\uff21\uffe7\uffe7\26\2\13\f\17\17\"\61<B]`bb"+
		"}\u0080\u00b9\u00b9\u201a\u201b\u201e\u201f\u2028\u2028\u3003\u3004\u300c"+
		"\u300d\u3012\u3013\uff03\uff03\uff0a\uff0b\uff0e\uff0e\uff1c\uff1d\uff21"+
		"\uff21\uffe7\uffe7\2\u0124\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3"+
		"\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2"+
		"\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67"+
		"\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2"+
		"\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2"+
		"\2W\3\2\2\2\2Y\3\2\2\2\2_\3\2\2\2\3a\3\2\2\2\5e\3\2\2\2\7p\3\2\2\2\t~"+
		"\3\2\2\2\13\u0081\3\2\2\2\r\u0085\3\2\2\2\17\u0087\3\2\2\2\21\u0089\3"+
		"\2\2\2\23\u008b\3\2\2\2\25\u008d\3\2\2\2\27\u008f\3\2\2\2\31\u0091\3\2"+
		"\2\2\33\u0093\3\2\2\2\35\u0095\3\2\2\2\37\u0098\3\2\2\2!\u009b\3\2\2\2"+
		"#\u009d\3\2\2\2%\u009f\3\2\2\2\'\u00a1\3\2\2\2)\u00a3\3\2\2\2+\u00a5\3"+
		"\2\2\2-\u00a7\3\2\2\2/\u00a9\3\2\2\2\61\u00ab\3\2\2\2\63\u00ad\3\2\2\2"+
		"\65\u00af\3\2\2\2\67\u00b2\3\2\2\29\u00b4\3\2\2\2;\u00b7\3\2\2\2=\u00b9"+
		"\3\2\2\2?\u00bd\3\2\2\2A\u00c1\3\2\2\2C\u00c6\3\2\2\2E\u00cd\3\2\2\2G"+
		"\u00d4\3\2\2\2I\u00d9\3\2\2\2K\u00df\3\2\2\2M\u00e2\3\2\2\2O\u00e6\3\2"+
		"\2\2Q\u00fb\3\2\2\2S\u00fd\3\2\2\2U\u00ff\3\2\2\2W\u010c\3\2\2\2Y\u010e"+
		"\3\2\2\2[\u0113\3\2\2\2]\u0115\3\2\2\2_\u0117\3\2\2\2ab\t\2\2\2bc\3\2"+
		"\2\2cd\b\2\2\2d\4\3\2\2\2ef\7\61\2\2fg\7\61\2\2gk\3\2\2\2hj\n\3\2\2ih"+
		"\3\2\2\2jm\3\2\2\2ki\3\2\2\2kl\3\2\2\2ln\3\2\2\2mk\3\2\2\2no\b\3\2\2o"+
		"\6\3\2\2\2pq\7\61\2\2qr\7,\2\2rv\3\2\2\2su\13\2\2\2ts\3\2\2\2ux\3\2\2"+
		"\2vw\3\2\2\2vt\3\2\2\2wy\3\2\2\2xv\3\2\2\2yz\7,\2\2z{\7\61\2\2{|\3\2\2"+
		"\2|}\b\4\2\2}\b\3\2\2\2~\177\7.\2\2\177\n\3\2\2\2\u0080\u0082\7=\2\2\u0081"+
		"\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2"+
		"\2\2\u0084\f\3\2\2\2\u0085\u0086\7<\2\2\u0086\16\3\2\2\2\u0087\u0088\7"+
		"*\2\2\u0088\20\3\2\2\2\u0089\u008a\7+\2\2\u008a\22\3\2\2\2\u008b\u008c"+
		"\7]\2\2\u008c\24\3\2\2\2\u008d\u008e\7_\2\2\u008e\26\3\2\2\2\u008f\u0090"+
		"\7}\2\2\u0090\30\3\2\2\2\u0091\u0092\7\177\2\2\u0092\32\3\2\2\2\u0093"+
		"\u0094\7\60\2\2\u0094\34\3\2\2\2\u0095\u0096\7?\2\2\u0096\u0097\7?\2\2"+
		"\u0097\36\3\2\2\2\u0098\u0099\7#\2\2\u0099\u009a\7?\2\2\u009a \3\2\2\2"+
		"\u009b\u009c\7?\2\2\u009c\"\3\2\2\2\u009d\u009e\7-\2\2\u009e$\3\2\2\2"+
		"\u009f\u00a0\7/\2\2\u00a0&\3\2\2\2\u00a1\u00a2\7,\2\2\u00a2(\3\2\2\2\u00a3"+
		"\u00a4\7\61\2\2\u00a4*\3\2\2\2\u00a5\u00a6\7\'\2\2\u00a6,\3\2\2\2\u00a7"+
		"\u00a8\7#\2\2\u00a8.\3\2\2\2\u00a9\u00aa\7(\2\2\u00aa\60\3\2\2\2\u00ab"+
		"\u00ac\7~\2\2\u00ac\62\3\2\2\2\u00ad\u00ae\7@\2\2\u00ae\64\3\2\2\2\u00af"+
		"\u00b0\7@\2\2\u00b0\u00b1\7?\2\2\u00b1\66\3\2\2\2\u00b2\u00b3\7>\2\2\u00b3"+
		"8\3\2\2\2\u00b4\u00b5\7>\2\2\u00b5\u00b6\7?\2\2\u00b6:\3\2\2\2\u00b7\u00b8"+
		"\7&\2\2\u00b8<\3\2\2\2\u00b9\u00ba\7f\2\2\u00ba\u00bb\7g\2\2\u00bb\u00bc"+
		"\7h\2\2\u00bc>\3\2\2\2\u00bd\u00be\7x\2\2\u00be\u00bf\7c\2\2\u00bf\u00c0"+
		"\7t\2\2\u00c0@\3\2\2\2\u00c1\u00c2\7y\2\2\u00c2\u00c3\7j\2\2\u00c3\u00c4"+
		"\7g\2\2\u00c4\u00c5\7p\2\2\u00c5B\3\2\2\2\u00c6\u00c7\7t\2\2\u00c7\u00c8"+
		"\7g\2\2\u00c8\u00c9\7v\2\2\u00c9\u00ca\7w\2\2\u00ca\u00cb\7t\2\2\u00cb"+
		"\u00cc\7p\2\2\u00ccD\3\2\2\2\u00cd\u00ce\7k\2\2\u00ce\u00cf\7o\2\2\u00cf"+
		"\u00d0\7r\2\2\u00d0\u00d1\7q\2\2\u00d1\u00d2\7t\2\2\u00d2\u00d3\7v\2\2"+
		"\u00d3F\3\2\2\2\u00d4\u00d5\7h\2\2\u00d5\u00d6\7t\2\2\u00d6\u00d7\7q\2"+
		"\2\u00d7\u00d8\7o\2\2\u00d8H\3\2\2\2\u00d9\u00da\7c\2\2\u00da\u00db\7"+
		"u\2\2\u00dbJ\3\2\2\2\u00dc\u00e0\n\4\2\2\u00dd\u00de\7^\2\2\u00de\u00e0"+
		"\7$\2\2\u00df\u00dc\3\2\2\2\u00df\u00dd\3\2\2\2\u00e0L\3\2\2\2\u00e1\u00e3"+
		"\t\5\2\2\u00e2\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e4"+
		"\u00e5\3\2\2\2\u00e5N\3\2\2\2\u00e6\u00e7\7^\2\2\u00e7\u00e8\13\2\2\2"+
		"\u00e8P\3\2\2\2\u00e9\u00ee\7$\2\2\u00ea\u00ed\5O(\2\u00eb\u00ed\n\6\2"+
		"\2\u00ec\u00ea\3\2\2\2\u00ec\u00eb\3\2\2\2\u00ed\u00f0\3\2\2\2\u00ee\u00ec"+
		"\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f1\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f1"+
		"\u00fc\7$\2\2\u00f2\u00f7\7)\2\2\u00f3\u00f6\5O(\2\u00f4\u00f6\n\7\2\2"+
		"\u00f5\u00f3\3\2\2\2\u00f5\u00f4\3\2\2\2\u00f6\u00f9\3\2\2\2\u00f7\u00f5"+
		"\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00fa\3\2\2\2\u00f9\u00f7\3\2\2\2\u00fa"+
		"\u00fc\7)\2\2\u00fb\u00e9\3\2\2\2\u00fb\u00f2\3\2\2\2\u00fcR\3\2\2\2\u00fd"+
		"\u00fe\5M\'\2\u00feT\3\2\2\2\u00ff\u0100\5M\'\2\u0100\u0101\7\60\2\2\u0101"+
		"\u0102\5M\'\2\u0102V\3\2\2\2\u0103\u0104\7v\2\2\u0104\u0105\7t\2\2\u0105"+
		"\u0106\7w\2\2\u0106\u010d\7g\2\2\u0107\u0108\7h\2\2\u0108\u0109\7c\2\2"+
		"\u0109\u010a\7n\2\2\u010a\u010b\7u\2\2\u010b\u010d\7g\2\2\u010c\u0103"+
		"\3\2\2\2\u010c\u0107\3\2\2\2\u010dX\3\2\2\2\u010e\u010f\7p\2\2\u010f\u0110"+
		"\7w\2\2\u0110\u0111\7n\2\2\u0111\u0112\7n\2\2\u0112Z\3\2\2\2\u0113\u0114"+
		"\n\b\2\2\u0114\\\3\2\2\2\u0115\u0116\n\t\2\2\u0116^\3\2\2\2\u0117\u011b"+
		"\5[.\2\u0118\u011a\5]/\2\u0119\u0118\3\2\2\2\u011a\u011d\3\2\2\2\u011b"+
		"\u0119\3\2\2\2\u011b\u011c\3\2\2\2\u011c`\3\2\2\2\u011d\u011b\3\2\2\2"+
		"\17\2kv\u0083\u00df\u00e4\u00ec\u00ee\u00f5\u00f7\u00fb\u010c\u011b\3"+
		"\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}