// Generated from Karyotype.g4 by ANTLR 4.7

package compiler; 
import java.util.*; 

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class KaryotypeParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, T__53=54, APPROX=55, POSITIVEDIGIT=56, MINUS=57, PLUS=58, QUES=59, 
		LETTER=60, WS=61;
	public static final int
		RULE_row = 0, RULE_rowTypeI = 1, RULE_firstClone = 2, RULE_clone = 3, 
		RULE_nonclonalClone = 4, RULE_rowTypeII = 5, RULE_firstStemlineGroup = 6, 
		RULE_otherStemlineGroup = 7, RULE_stemlineClone = 8, RULE_additionalStemlineClone = 9, 
		RULE_sidelineCloneTypeI = 10, RULE_sidelineCloneTypeII = 11, RULE_additionalClone = 12, 
		RULE_karyotypeI = 13, RULE_karyotypeII = 14, RULE_karyotypeIII = 15, RULE_karyotypeIV = 16, 
		RULE_karyotypeV = 17, RULE_ploidy = 18, RULE_tooManySlant = 19, RULE_incorrectSlant = 20, 
		RULE_modalNum = 21, RULE_modalNumContent = 22, RULE_gender = 23, RULE_tooManyComma = 24, 
		RULE_orSex = 25, RULE_idemSpecial = 26, RULE_slSpecial = 27, RULE_sdlSpecial = 28, 
		RULE_regularEvent = 29, RULE_incorrectComma = 30, RULE_idemEvent = 31, 
		RULE_slEvent = 32, RULE_slMark = 33, RULE_sdlEvent = 34, RULE_sdlMark = 35, 
		RULE_regEvent = 36, RULE_regEventType = 37, RULE_gainLossChr = 38, RULE_undecoded = 39, 
		RULE_aberration = 40, RULE_rearrangementElement = 41, RULE_rearrangement = 42, 
		RULE_derChrList = 43, RULE_derChrListElements = 44, RULE_incorrectDerChrListElements = 45, 
		RULE_incorrectLeftParenthesis = 46, RULE_incorrectRightParenthesis = 47, 
		RULE_derBreakpointsList = 48, RULE_derBreakpointsListElements = 49, RULE_incorrectDerBreakpointsListElements = 50, 
		RULE_derBreakpointsInOneChr = 51, RULE_chrList = 52, RULE_chrListElements = 53, 
		RULE_incorrectChrListElements = 54, RULE_breakpointsList = 55, RULE_breakpointsListElements = 56, 
		RULE_incorrectBreakpointsListElements = 57, RULE_breakpointsInOneChr = 58, 
		RULE_rChrList = 59, RULE_rBreakpointsList = 60, RULE_idUncertainChr = 61, 
		RULE_uncertainChr = 62, RULE_uncertainChrContent = 63, RULE_arm = 64, 
		RULE_band = 65, RULE_breakpoint = 66, RULE_breakpoints = 67, RULE_c = 68, 
		RULE_cellNum = 69, RULE_cellNumContent = 70, RULE_incorrectLeftParenthesisII = 71, 
		RULE_incorrectRightParenthesisII = 72, RULE_cen = 73, RULE_chr = 74, RULE_chrNum = 75, 
		RULE_cp = 76, RULE_derBreakpoints = 77, RULE_derChr = 78, RULE_derId = 79, 
		RULE_digit = 80, RULE_dminNum = 81, RULE_gainChr = 82, RULE_id = 83, RULE_inh = 84, 
		RULE_integer = 85, RULE_lossChr = 86, RULE_marNum = 87, RULE_modalDesc = 88, 
		RULE_modalLevel = 89, RULE_modalPrefix = 90, RULE_modalSuffix = 91, RULE_mosChi = 92, 
		RULE_multiplication = 93, RULE_numRangeTypeI = 94, RULE_numRangeTypeII = 95, 
		RULE_numRangeTypeIII = 96, RULE_prefix = 97, RULE_prefixMinus = 98, RULE_prefixPlus = 99, 
		RULE_rId = 100, RULE_sex = 101, RULE_sexChr = 102, RULE_suffix = 103, 
		RULE_subband = 104, RULE_undeterminedPrefix = 105;
	public static final String[] ruleNames = {
		"row", "rowTypeI", "firstClone", "clone", "nonclonalClone", "rowTypeII", 
		"firstStemlineGroup", "otherStemlineGroup", "stemlineClone", "additionalStemlineClone", 
		"sidelineCloneTypeI", "sidelineCloneTypeII", "additionalClone", "karyotypeI", 
		"karyotypeII", "karyotypeIII", "karyotypeIV", "karyotypeV", "ploidy", 
		"tooManySlant", "incorrectSlant", "modalNum", "modalNumContent", "gender", 
		"tooManyComma", "orSex", "idemSpecial", "slSpecial", "sdlSpecial", "regularEvent", 
		"incorrectComma", "idemEvent", "slEvent", "slMark", "sdlEvent", "sdlMark", 
		"regEvent", "regEventType", "gainLossChr", "undecoded", "aberration", 
		"rearrangementElement", "rearrangement", "derChrList", "derChrListElements", 
		"incorrectDerChrListElements", "incorrectLeftParenthesis", "incorrectRightParenthesis", 
		"derBreakpointsList", "derBreakpointsListElements", "incorrectDerBreakpointsListElements", 
		"derBreakpointsInOneChr", "chrList", "chrListElements", "incorrectChrListElements", 
		"breakpointsList", "breakpointsListElements", "incorrectBreakpointsListElements", 
		"breakpointsInOneChr", "rChrList", "rBreakpointsList", "idUncertainChr", 
		"uncertainChr", "uncertainChrContent", "arm", "band", "breakpoint", "breakpoints", 
		"c", "cellNum", "cellNumContent", "incorrectLeftParenthesisII", "incorrectRightParenthesisII", 
		"cen", "chr", "chrNum", "cp", "derBreakpoints", "derChr", "derId", "digit", 
		"dminNum", "gainChr", "id", "inh", "integer", "lossChr", "marNum", "modalDesc", 
		"modalLevel", "modalPrefix", "modalSuffix", "mosChi", "multiplication", 
		"numRangeTypeI", "numRangeTypeII", "numRangeTypeIII", "prefix", "prefixMinus", 
		"prefixPlus", "rId", "sex", "sexChr", "suffix", "subband", "undeterminedPrefix"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'.'", "'/'", "','", "'n'", "'N'", "'o'", "'O'", "'c'", "'C'", "'l'", 
		"'L'", "'a'", "'A'", "'b'", "'B'", "'r'", "'R'", "'m'", "'M'", "'e'", 
		"'E'", "':'", "';'", "'<'", "'>'", "'i'", "'I'", "'d'", "'D'", "'s'", 
		"'S'", "'('", "')'", "'['", "'{'", "']'", "'}'", "'p'", "'P'", "'u'", 
		"'U'", "'t'", "'T'", "'q'", "'Q'", "'h'", "'H'", "'v'", "'V'", "'0'", 
		"'x'", "'X'", "'y'", "'Y'", "'~'", null, "'-'", "'+'", "'?'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, "APPROX", "POSITIVEDIGIT", "MINUS", 
		"PLUS", "QUES", "LETTER", "WS"
	};
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
	public String getGrammarFileName() { return "Karyotype.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


		int i = 0;
		List<String> sexChrList = new ArrayList<>();

	public KaryotypeParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RowContext extends ParserRuleContext {
		public RowTypeIContext rowTypeI() {
			return getRuleContext(RowTypeIContext.class,0);
		}
		public RowTypeIIContext rowTypeII() {
			return getRuleContext(RowTypeIIContext.class,0);
		}
		public List<TerminalNode> LETTER() { return getTokens(KaryotypeParser.LETTER); }
		public TerminalNode LETTER(int i) {
			return getToken(KaryotypeParser.LETTER, i);
		}
		public RowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_row; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterRow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitRow(this);
		}
	}

	public final RowContext row() throws RecognitionException {
		RowContext _localctx = new RowContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_row);
		int _la;
		try {
			setState(221);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(212);
				rowTypeI();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(213);
				rowTypeII();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(216); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					setState(216);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
					case 1:
						{
						setState(214);
						match(LETTER);
						}
						break;
					case 2:
						{
						setState(215);
						matchWildcard();
						}
						break;
					}
					}
					setState(218); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << T__40) | (1L << T__41) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45) | (1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__51) | (1L << T__52) | (1L << T__53) | (1L << APPROX) | (1L << POSITIVEDIGIT) | (1L << MINUS) | (1L << PLUS) | (1L << QUES) | (1L << LETTER) | (1L << WS))) != 0) );
				notifyErrorListeners("-1|This is an incorrect input for karyotype parsing.");
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

	public static class RowTypeIContext extends ParserRuleContext {
		public FirstCloneContext firstClone() {
			return getRuleContext(FirstCloneContext.class,0);
		}
		public List<NonclonalCloneContext> nonclonalClone() {
			return getRuleContexts(NonclonalCloneContext.class);
		}
		public NonclonalCloneContext nonclonalClone(int i) {
			return getRuleContext(NonclonalCloneContext.class,i);
		}
		public List<CloneContext> clone() {
			return getRuleContexts(CloneContext.class);
		}
		public CloneContext clone(int i) {
			return getRuleContext(CloneContext.class,i);
		}
		public RowTypeIContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rowTypeI; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterRowTypeI(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitRowTypeI(this);
		}
	}

	public final RowTypeIContext rowTypeI() throws RecognitionException {
		RowTypeIContext _localctx = new RowTypeIContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_rowTypeI);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			firstClone();
			setState(228);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(226);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						setState(224);
						nonclonalClone();
						}
						break;
					case 2:
						{
						setState(225);
						clone();
						}
						break;
					}
					} 
				}
				setState(230);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			setState(232);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(231);
				match(T__0);
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

	public static class FirstCloneContext extends ParserRuleContext {
		public KaryotypeIContext karyotypeI() {
			return getRuleContext(KaryotypeIContext.class,0);
		}
		public PloidyContext ploidy() {
			return getRuleContext(PloidyContext.class,0);
		}
		public FirstCloneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_firstClone; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterFirstClone(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitFirstClone(this);
		}
	}

	public final FirstCloneContext firstClone() throws RecognitionException {
		FirstCloneContext _localctx = new FirstCloneContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_firstClone);
		i = i + 1;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(234);
				ploidy();
				}
				break;
			}
			setState(237);
			karyotypeI();
			}
			_ctx.stop = _input.LT(-1);
			sexChrList = new ArrayList<>();
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

	public static class CloneContext extends ParserRuleContext {
		public String s = "";
		public int l = 0;
		public PloidyContext ploidy;
		public KaryotypeIIContext karyotypeII;
		public TooManySlantContext tooManySlant;
		public IncorrectSlantContext incorrectSlant;
		public KaryotypeIIContext karyotypeII() {
			return getRuleContext(KaryotypeIIContext.class,0);
		}
		public PloidyContext ploidy() {
			return getRuleContext(PloidyContext.class,0);
		}
		public TooManySlantContext tooManySlant() {
			return getRuleContext(TooManySlantContext.class,0);
		}
		public IncorrectSlantContext incorrectSlant() {
			return getRuleContext(IncorrectSlantContext.class,0);
		}
		public CloneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_clone; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterClone(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitClone(this);
		}
	}

	public final CloneContext clone() throws RecognitionException {
		CloneContext _localctx = new CloneContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_clone);
		i = i + 1;
		try {
			setState(264);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(239);
				match(T__1);
				setState(241);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(240);
					ploidy();
					}
					break;
				}
				setState(243);
				karyotypeII();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(245);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					{
					setState(244);
					((CloneContext)_localctx).ploidy = ploidy();
					}
					break;
				}
				setState(247);
				((CloneContext)_localctx).karyotypeII = karyotypeII();

					if ((((CloneContext)_localctx).ploidy!=null?_input.getText(((CloneContext)_localctx).ploidy.start,((CloneContext)_localctx).ploidy.stop):null) != null) {
						((CloneContext)_localctx).s =  (((CloneContext)_localctx).ploidy!=null?_input.getText(((CloneContext)_localctx).ploidy.start,((CloneContext)_localctx).ploidy.stop):null) + (((CloneContext)_localctx).karyotypeII!=null?_input.getText(((CloneContext)_localctx).karyotypeII.start,((CloneContext)_localctx).karyotypeII.stop):null);
					} else {
						((CloneContext)_localctx).s =  (((CloneContext)_localctx).karyotypeII!=null?_input.getText(((CloneContext)_localctx).karyotypeII.start,((CloneContext)_localctx).karyotypeII.stop):null);
					}
					((CloneContext)_localctx).l =  _localctx.s.length();
					notifyErrorListeners(_localctx.l + "|Missing a slant line before '" + _localctx.s + "'");
				  
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(250);
				((CloneContext)_localctx).tooManySlant = tooManySlant();
				setState(252);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(251);
					((CloneContext)_localctx).ploidy = ploidy();
					}
					break;
				}
				setState(254);
				((CloneContext)_localctx).karyotypeII = karyotypeII();

					if ((((CloneContext)_localctx).ploidy!=null?_input.getText(((CloneContext)_localctx).ploidy.start,((CloneContext)_localctx).ploidy.stop):null) != null) {
						((CloneContext)_localctx).s =  (((CloneContext)_localctx).ploidy!=null?_input.getText(((CloneContext)_localctx).ploidy.start,((CloneContext)_localctx).ploidy.stop):null) + (((CloneContext)_localctx).karyotypeII!=null?_input.getText(((CloneContext)_localctx).karyotypeII.start,((CloneContext)_localctx).karyotypeII.stop):null);
					} else {
						((CloneContext)_localctx).s =  (((CloneContext)_localctx).karyotypeII!=null?_input.getText(((CloneContext)_localctx).karyotypeII.start,((CloneContext)_localctx).karyotypeII.stop):null);
					}
					((CloneContext)_localctx).l =  _localctx.s.length() + (((CloneContext)_localctx).tooManySlant!=null?_input.getText(((CloneContext)_localctx).tooManySlant.start,((CloneContext)_localctx).tooManySlant.stop):null).length();
					notifyErrorListeners(_localctx.l + "|Too many slant lines before '" + _localctx.s + "'");
				  
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(257);
				((CloneContext)_localctx).incorrectSlant = incorrectSlant();
				setState(259);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(258);
					((CloneContext)_localctx).ploidy = ploidy();
					}
					break;
				}
				setState(261);
				((CloneContext)_localctx).karyotypeII = karyotypeII();

					if ((((CloneContext)_localctx).ploidy!=null?_input.getText(((CloneContext)_localctx).ploidy.start,((CloneContext)_localctx).ploidy.stop):null) != null) {
						((CloneContext)_localctx).s =  (((CloneContext)_localctx).ploidy!=null?_input.getText(((CloneContext)_localctx).ploidy.start,((CloneContext)_localctx).ploidy.stop):null) + (((CloneContext)_localctx).karyotypeII!=null?_input.getText(((CloneContext)_localctx).karyotypeII.start,((CloneContext)_localctx).karyotypeII.stop):null);
					} else {
						((CloneContext)_localctx).s =  (((CloneContext)_localctx).karyotypeII!=null?_input.getText(((CloneContext)_localctx).karyotypeII.start,((CloneContext)_localctx).karyotypeII.stop):null);
					}
					((CloneContext)_localctx).l =  _localctx.s.length() + (((CloneContext)_localctx).incorrectSlant!=null?_input.getText(((CloneContext)_localctx).incorrectSlant.start,((CloneContext)_localctx).incorrectSlant.stop):null).length();
					notifyErrorListeners(_localctx.l + "|Incorrect clone separation before '" + _localctx.s + "'");
				  
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			sexChrList = new ArrayList<>();
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

	public static class NonclonalCloneContext extends ParserRuleContext {
		public String s = "";
		public int l = 0;
		public PloidyContext ploidy;
		public KaryotypeVContext karyotypeV;
		public TooManySlantContext tooManySlant;
		public IncorrectSlantContext incorrectSlant;
		public KaryotypeVContext karyotypeV() {
			return getRuleContext(KaryotypeVContext.class,0);
		}
		public PloidyContext ploidy() {
			return getRuleContext(PloidyContext.class,0);
		}
		public TooManySlantContext tooManySlant() {
			return getRuleContext(TooManySlantContext.class,0);
		}
		public IncorrectSlantContext incorrectSlant() {
			return getRuleContext(IncorrectSlantContext.class,0);
		}
		public NonclonalCloneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonclonalClone; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterNonclonalClone(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitNonclonalClone(this);
		}
	}

	public final NonclonalCloneContext nonclonalClone() throws RecognitionException {
		NonclonalCloneContext _localctx = new NonclonalCloneContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_nonclonalClone);
		i = i + 1;
		try {
			setState(291);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(266);
				match(T__1);
				setState(268);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(267);
					ploidy();
					}
					break;
				}
				setState(270);
				karyotypeV();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(272);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
				case 1:
					{
					setState(271);
					((NonclonalCloneContext)_localctx).ploidy = ploidy();
					}
					break;
				}
				setState(274);
				((NonclonalCloneContext)_localctx).karyotypeV = karyotypeV();

					if ((((NonclonalCloneContext)_localctx).ploidy!=null?_input.getText(((NonclonalCloneContext)_localctx).ploidy.start,((NonclonalCloneContext)_localctx).ploidy.stop):null) != null) {
						((NonclonalCloneContext)_localctx).s =  (((NonclonalCloneContext)_localctx).ploidy!=null?_input.getText(((NonclonalCloneContext)_localctx).ploidy.start,((NonclonalCloneContext)_localctx).ploidy.stop):null) + (((NonclonalCloneContext)_localctx).karyotypeV!=null?_input.getText(((NonclonalCloneContext)_localctx).karyotypeV.start,((NonclonalCloneContext)_localctx).karyotypeV.stop):null);
					} else {
						((NonclonalCloneContext)_localctx).s =  (((NonclonalCloneContext)_localctx).karyotypeV!=null?_input.getText(((NonclonalCloneContext)_localctx).karyotypeV.start,((NonclonalCloneContext)_localctx).karyotypeV.stop):null);
					}
					((NonclonalCloneContext)_localctx).l =  _localctx.s.length();
					notifyErrorListeners(_localctx.l + "|Missing a slant line before '" + _localctx.s + "'");
				  
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(277);
				((NonclonalCloneContext)_localctx).tooManySlant = tooManySlant();
				setState(279);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(278);
					((NonclonalCloneContext)_localctx).ploidy = ploidy();
					}
					break;
				}
				setState(281);
				((NonclonalCloneContext)_localctx).karyotypeV = karyotypeV();

					if ((((NonclonalCloneContext)_localctx).ploidy!=null?_input.getText(((NonclonalCloneContext)_localctx).ploidy.start,((NonclonalCloneContext)_localctx).ploidy.stop):null) != null) {
						((NonclonalCloneContext)_localctx).s =  (((NonclonalCloneContext)_localctx).ploidy!=null?_input.getText(((NonclonalCloneContext)_localctx).ploidy.start,((NonclonalCloneContext)_localctx).ploidy.stop):null) + (((NonclonalCloneContext)_localctx).karyotypeV!=null?_input.getText(((NonclonalCloneContext)_localctx).karyotypeV.start,((NonclonalCloneContext)_localctx).karyotypeV.stop):null);
					} else {
						((NonclonalCloneContext)_localctx).s =  (((NonclonalCloneContext)_localctx).karyotypeV!=null?_input.getText(((NonclonalCloneContext)_localctx).karyotypeV.start,((NonclonalCloneContext)_localctx).karyotypeV.stop):null);
					}
					((NonclonalCloneContext)_localctx).l =  _localctx.s.length() + (((NonclonalCloneContext)_localctx).tooManySlant!=null?_input.getText(((NonclonalCloneContext)_localctx).tooManySlant.start,((NonclonalCloneContext)_localctx).tooManySlant.stop):null).length();
					notifyErrorListeners(_localctx.l + "|Too many slant lines before '" + _localctx.s + "'");
				  
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(284);
				((NonclonalCloneContext)_localctx).incorrectSlant = incorrectSlant();
				setState(286);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
				case 1:
					{
					setState(285);
					((NonclonalCloneContext)_localctx).ploidy = ploidy();
					}
					break;
				}
				setState(288);
				((NonclonalCloneContext)_localctx).karyotypeV = karyotypeV();

					if ((((NonclonalCloneContext)_localctx).ploidy!=null?_input.getText(((NonclonalCloneContext)_localctx).ploidy.start,((NonclonalCloneContext)_localctx).ploidy.stop):null) != null) {
						((NonclonalCloneContext)_localctx).s =  (((NonclonalCloneContext)_localctx).ploidy!=null?_input.getText(((NonclonalCloneContext)_localctx).ploidy.start,((NonclonalCloneContext)_localctx).ploidy.stop):null) + (((NonclonalCloneContext)_localctx).karyotypeV!=null?_input.getText(((NonclonalCloneContext)_localctx).karyotypeV.start,((NonclonalCloneContext)_localctx).karyotypeV.stop):null);
					} else {
						((NonclonalCloneContext)_localctx).s =  (((NonclonalCloneContext)_localctx).karyotypeV!=null?_input.getText(((NonclonalCloneContext)_localctx).karyotypeV.start,((NonclonalCloneContext)_localctx).karyotypeV.stop):null);
					}
					((NonclonalCloneContext)_localctx).l =  _localctx.s.length() + (((NonclonalCloneContext)_localctx).incorrectSlant!=null?_input.getText(((NonclonalCloneContext)_localctx).incorrectSlant.start,((NonclonalCloneContext)_localctx).incorrectSlant.stop):null).length();
					notifyErrorListeners(_localctx.l + "|Incorrect clone separation before '" + _localctx.s + "'");
				  
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

	public static class RowTypeIIContext extends ParserRuleContext {
		public FirstStemlineGroupContext firstStemlineGroup() {
			return getRuleContext(FirstStemlineGroupContext.class,0);
		}
		public List<OtherStemlineGroupContext> otherStemlineGroup() {
			return getRuleContexts(OtherStemlineGroupContext.class);
		}
		public OtherStemlineGroupContext otherStemlineGroup(int i) {
			return getRuleContext(OtherStemlineGroupContext.class,i);
		}
		public List<AdditionalCloneContext> additionalClone() {
			return getRuleContexts(AdditionalCloneContext.class);
		}
		public AdditionalCloneContext additionalClone(int i) {
			return getRuleContext(AdditionalCloneContext.class,i);
		}
		public RowTypeIIContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rowTypeII; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterRowTypeII(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitRowTypeII(this);
		}
	}

	public final RowTypeIIContext rowTypeII() throws RecognitionException {
		RowTypeIIContext _localctx = new RowTypeIIContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_rowTypeII);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			firstStemlineGroup();
			setState(297);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(294);
					otherStemlineGroup();
					}
					} 
				}
				setState(299);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			setState(303);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(300);
					additionalClone();
					}
					} 
				}
				setState(305);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			setState(307);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(306);
				match(T__0);
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

	public static class FirstStemlineGroupContext extends ParserRuleContext {
		public StemlineCloneContext stemlineClone() {
			return getRuleContext(StemlineCloneContext.class,0);
		}
		public List<SidelineCloneTypeIContext> sidelineCloneTypeI() {
			return getRuleContexts(SidelineCloneTypeIContext.class);
		}
		public SidelineCloneTypeIContext sidelineCloneTypeI(int i) {
			return getRuleContext(SidelineCloneTypeIContext.class,i);
		}
		public List<SidelineCloneTypeIIContext> sidelineCloneTypeII() {
			return getRuleContexts(SidelineCloneTypeIIContext.class);
		}
		public SidelineCloneTypeIIContext sidelineCloneTypeII(int i) {
			return getRuleContext(SidelineCloneTypeIIContext.class,i);
		}
		public FirstStemlineGroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_firstStemlineGroup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterFirstStemlineGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitFirstStemlineGroup(this);
		}
	}

	public final FirstStemlineGroupContext firstStemlineGroup() throws RecognitionException {
		FirstStemlineGroupContext _localctx = new FirstStemlineGroupContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_firstStemlineGroup);
		try {
			int _alt;
			setState(341);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(309);
				stemlineClone();
				setState(310);
				sidelineCloneTypeI();
				setState(314);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(311);
						sidelineCloneTypeII();
						}
						} 
					}
					setState(316);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
				}
				setState(320);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(317);
						sidelineCloneTypeI();
						}
						} 
					}
					setState(322);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(323);
				stemlineClone();
				setState(325); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(324);
						sidelineCloneTypeI();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(327); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(329);
				sidelineCloneTypeI();
				setState(331); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(330);
						sidelineCloneTypeII();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(333); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(338);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(335);
						sidelineCloneTypeI();
						}
						} 
					}
					setState(340);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
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

	public static class OtherStemlineGroupContext extends ParserRuleContext {
		public AdditionalStemlineCloneContext additionalStemlineClone() {
			return getRuleContext(AdditionalStemlineCloneContext.class,0);
		}
		public List<SidelineCloneTypeIContext> sidelineCloneTypeI() {
			return getRuleContexts(SidelineCloneTypeIContext.class);
		}
		public SidelineCloneTypeIContext sidelineCloneTypeI(int i) {
			return getRuleContext(SidelineCloneTypeIContext.class,i);
		}
		public OtherStemlineGroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_otherStemlineGroup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterOtherStemlineGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitOtherStemlineGroup(this);
		}
	}

	public final OtherStemlineGroupContext otherStemlineGroup() throws RecognitionException {
		OtherStemlineGroupContext _localctx = new OtherStemlineGroupContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_otherStemlineGroup);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(343);
			additionalStemlineClone();
			setState(345); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(344);
					sidelineCloneTypeI();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(347); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class StemlineCloneContext extends ParserRuleContext {
		public KaryotypeIContext karyotypeI() {
			return getRuleContext(KaryotypeIContext.class,0);
		}
		public PloidyContext ploidy() {
			return getRuleContext(PloidyContext.class,0);
		}
		public StemlineCloneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stemlineClone; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterStemlineClone(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitStemlineClone(this);
		}
	}

	public final StemlineCloneContext stemlineClone() throws RecognitionException {
		StemlineCloneContext _localctx = new StemlineCloneContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_stemlineClone);
		i = i + 1;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(350);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				{
				setState(349);
				ploidy();
				}
				break;
			}
			setState(352);
			karyotypeI();
			}
			_ctx.stop = _input.LT(-1);
			sexChrList = new ArrayList<>();
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

	public static class AdditionalStemlineCloneContext extends ParserRuleContext {
		public String s = "";
		public int l = 0;
		public PloidyContext ploidy;
		public KaryotypeIContext karyotypeI;
		public TooManySlantContext tooManySlant;
		public IncorrectSlantContext incorrectSlant;
		public KaryotypeIContext karyotypeI() {
			return getRuleContext(KaryotypeIContext.class,0);
		}
		public PloidyContext ploidy() {
			return getRuleContext(PloidyContext.class,0);
		}
		public TooManySlantContext tooManySlant() {
			return getRuleContext(TooManySlantContext.class,0);
		}
		public IncorrectSlantContext incorrectSlant() {
			return getRuleContext(IncorrectSlantContext.class,0);
		}
		public AdditionalStemlineCloneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additionalStemlineClone; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterAdditionalStemlineClone(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitAdditionalStemlineClone(this);
		}
	}

	public final AdditionalStemlineCloneContext additionalStemlineClone() throws RecognitionException {
		AdditionalStemlineCloneContext _localctx = new AdditionalStemlineCloneContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_additionalStemlineClone);
		i = i + 1;
		try {
			setState(379);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(354);
				match(T__1);
				setState(356);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(355);
					ploidy();
					}
					break;
				}
				setState(358);
				karyotypeI();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(360);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
				case 1:
					{
					setState(359);
					((AdditionalStemlineCloneContext)_localctx).ploidy = ploidy();
					}
					break;
				}
				setState(362);
				((AdditionalStemlineCloneContext)_localctx).karyotypeI = karyotypeI();

					if ((((AdditionalStemlineCloneContext)_localctx).ploidy!=null?_input.getText(((AdditionalStemlineCloneContext)_localctx).ploidy.start,((AdditionalStemlineCloneContext)_localctx).ploidy.stop):null) != null) {
						((AdditionalStemlineCloneContext)_localctx).s =  (((AdditionalStemlineCloneContext)_localctx).ploidy!=null?_input.getText(((AdditionalStemlineCloneContext)_localctx).ploidy.start,((AdditionalStemlineCloneContext)_localctx).ploidy.stop):null) + (((AdditionalStemlineCloneContext)_localctx).karyotypeI!=null?_input.getText(((AdditionalStemlineCloneContext)_localctx).karyotypeI.start,((AdditionalStemlineCloneContext)_localctx).karyotypeI.stop):null);
					} else {
						((AdditionalStemlineCloneContext)_localctx).s =  (((AdditionalStemlineCloneContext)_localctx).karyotypeI!=null?_input.getText(((AdditionalStemlineCloneContext)_localctx).karyotypeI.start,((AdditionalStemlineCloneContext)_localctx).karyotypeI.stop):null);
					}
					((AdditionalStemlineCloneContext)_localctx).l =  _localctx.s.length();
					notifyErrorListeners(_localctx.l + "|Missing a slant line before '" + _localctx.s + "'");
				  
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(365);
				((AdditionalStemlineCloneContext)_localctx).tooManySlant = tooManySlant();
				setState(367);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
				case 1:
					{
					setState(366);
					((AdditionalStemlineCloneContext)_localctx).ploidy = ploidy();
					}
					break;
				}
				setState(369);
				((AdditionalStemlineCloneContext)_localctx).karyotypeI = karyotypeI();

					if ((((AdditionalStemlineCloneContext)_localctx).ploidy!=null?_input.getText(((AdditionalStemlineCloneContext)_localctx).ploidy.start,((AdditionalStemlineCloneContext)_localctx).ploidy.stop):null) != null) {
						((AdditionalStemlineCloneContext)_localctx).s =  (((AdditionalStemlineCloneContext)_localctx).ploidy!=null?_input.getText(((AdditionalStemlineCloneContext)_localctx).ploidy.start,((AdditionalStemlineCloneContext)_localctx).ploidy.stop):null) + (((AdditionalStemlineCloneContext)_localctx).karyotypeI!=null?_input.getText(((AdditionalStemlineCloneContext)_localctx).karyotypeI.start,((AdditionalStemlineCloneContext)_localctx).karyotypeI.stop):null);
					} else {
						((AdditionalStemlineCloneContext)_localctx).s =  (((AdditionalStemlineCloneContext)_localctx).karyotypeI!=null?_input.getText(((AdditionalStemlineCloneContext)_localctx).karyotypeI.start,((AdditionalStemlineCloneContext)_localctx).karyotypeI.stop):null);
					}
					((AdditionalStemlineCloneContext)_localctx).l =  _localctx.s.length() + (((AdditionalStemlineCloneContext)_localctx).tooManySlant!=null?_input.getText(((AdditionalStemlineCloneContext)_localctx).tooManySlant.start,((AdditionalStemlineCloneContext)_localctx).tooManySlant.stop):null).length();
					notifyErrorListeners(_localctx.l + "|Too many slant lines before '" + _localctx.s + "'");
				  
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(372);
				((AdditionalStemlineCloneContext)_localctx).incorrectSlant = incorrectSlant();
				setState(374);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
				case 1:
					{
					setState(373);
					((AdditionalStemlineCloneContext)_localctx).ploidy = ploidy();
					}
					break;
				}
				setState(376);
				((AdditionalStemlineCloneContext)_localctx).karyotypeI = karyotypeI();

					if ((((AdditionalStemlineCloneContext)_localctx).ploidy!=null?_input.getText(((AdditionalStemlineCloneContext)_localctx).ploidy.start,((AdditionalStemlineCloneContext)_localctx).ploidy.stop):null) != null) {
						((AdditionalStemlineCloneContext)_localctx).s =  (((AdditionalStemlineCloneContext)_localctx).ploidy!=null?_input.getText(((AdditionalStemlineCloneContext)_localctx).ploidy.start,((AdditionalStemlineCloneContext)_localctx).ploidy.stop):null) + (((AdditionalStemlineCloneContext)_localctx).karyotypeI!=null?_input.getText(((AdditionalStemlineCloneContext)_localctx).karyotypeI.start,((AdditionalStemlineCloneContext)_localctx).karyotypeI.stop):null);
					} else {
						((AdditionalStemlineCloneContext)_localctx).s =  (((AdditionalStemlineCloneContext)_localctx).karyotypeI!=null?_input.getText(((AdditionalStemlineCloneContext)_localctx).karyotypeI.start,((AdditionalStemlineCloneContext)_localctx).karyotypeI.stop):null);
					}
					((AdditionalStemlineCloneContext)_localctx).l =  _localctx.s.length() + (((AdditionalStemlineCloneContext)_localctx).incorrectSlant!=null?_input.getText(((AdditionalStemlineCloneContext)_localctx).incorrectSlant.start,((AdditionalStemlineCloneContext)_localctx).incorrectSlant.stop):null).length();
					notifyErrorListeners(_localctx.l + "|Incorrect clone separation before '" + _localctx.s + "'");
				  
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			sexChrList = new ArrayList<>();
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

	public static class SidelineCloneTypeIContext extends ParserRuleContext {
		public String s = "";
		public int l = 0;
		public PloidyContext ploidy;
		public KaryotypeIIIContext karyotypeIII;
		public TooManySlantContext tooManySlant;
		public IncorrectSlantContext incorrectSlant;
		public KaryotypeIIIContext karyotypeIII() {
			return getRuleContext(KaryotypeIIIContext.class,0);
		}
		public PloidyContext ploidy() {
			return getRuleContext(PloidyContext.class,0);
		}
		public TooManySlantContext tooManySlant() {
			return getRuleContext(TooManySlantContext.class,0);
		}
		public IncorrectSlantContext incorrectSlant() {
			return getRuleContext(IncorrectSlantContext.class,0);
		}
		public SidelineCloneTypeIContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sidelineCloneTypeI; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterSidelineCloneTypeI(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitSidelineCloneTypeI(this);
		}
	}

	public final SidelineCloneTypeIContext sidelineCloneTypeI() throws RecognitionException {
		SidelineCloneTypeIContext _localctx = new SidelineCloneTypeIContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_sidelineCloneTypeI);
		i = i + 1;
		int _la;
		try {
			setState(406);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(381);
				match(T__1);
				setState(383);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__37) | (1L << T__38) | (1L << T__45) | (1L << T__46))) != 0)) {
					{
					setState(382);
					ploidy();
					}
				}

				setState(385);
				karyotypeIII();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(387);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__37) | (1L << T__38) | (1L << T__45) | (1L << T__46))) != 0)) {
					{
					setState(386);
					((SidelineCloneTypeIContext)_localctx).ploidy = ploidy();
					}
				}

				setState(389);
				((SidelineCloneTypeIContext)_localctx).karyotypeIII = karyotypeIII();

					if ((((SidelineCloneTypeIContext)_localctx).ploidy!=null?_input.getText(((SidelineCloneTypeIContext)_localctx).ploidy.start,((SidelineCloneTypeIContext)_localctx).ploidy.stop):null) != null) {
						((SidelineCloneTypeIContext)_localctx).s =  (((SidelineCloneTypeIContext)_localctx).ploidy!=null?_input.getText(((SidelineCloneTypeIContext)_localctx).ploidy.start,((SidelineCloneTypeIContext)_localctx).ploidy.stop):null) + (((SidelineCloneTypeIContext)_localctx).karyotypeIII!=null?_input.getText(((SidelineCloneTypeIContext)_localctx).karyotypeIII.start,((SidelineCloneTypeIContext)_localctx).karyotypeIII.stop):null);
					} else {
						((SidelineCloneTypeIContext)_localctx).s =  (((SidelineCloneTypeIContext)_localctx).karyotypeIII!=null?_input.getText(((SidelineCloneTypeIContext)_localctx).karyotypeIII.start,((SidelineCloneTypeIContext)_localctx).karyotypeIII.stop):null);
					}
					((SidelineCloneTypeIContext)_localctx).l =  _localctx.s.length();
					notifyErrorListeners(_localctx.l + "|Missing a slant line before '" + _localctx.s + "'");	
				  
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(392);
				((SidelineCloneTypeIContext)_localctx).tooManySlant = tooManySlant();
				setState(394);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__37) | (1L << T__38) | (1L << T__45) | (1L << T__46))) != 0)) {
					{
					setState(393);
					((SidelineCloneTypeIContext)_localctx).ploidy = ploidy();
					}
				}

				setState(396);
				((SidelineCloneTypeIContext)_localctx).karyotypeIII = karyotypeIII();

					if ((((SidelineCloneTypeIContext)_localctx).ploidy!=null?_input.getText(((SidelineCloneTypeIContext)_localctx).ploidy.start,((SidelineCloneTypeIContext)_localctx).ploidy.stop):null) != null) {
						((SidelineCloneTypeIContext)_localctx).s =  (((SidelineCloneTypeIContext)_localctx).ploidy!=null?_input.getText(((SidelineCloneTypeIContext)_localctx).ploidy.start,((SidelineCloneTypeIContext)_localctx).ploidy.stop):null) + (((SidelineCloneTypeIContext)_localctx).karyotypeIII!=null?_input.getText(((SidelineCloneTypeIContext)_localctx).karyotypeIII.start,((SidelineCloneTypeIContext)_localctx).karyotypeIII.stop):null);
					} else {
						((SidelineCloneTypeIContext)_localctx).s =  (((SidelineCloneTypeIContext)_localctx).karyotypeIII!=null?_input.getText(((SidelineCloneTypeIContext)_localctx).karyotypeIII.start,((SidelineCloneTypeIContext)_localctx).karyotypeIII.stop):null);
					}
					((SidelineCloneTypeIContext)_localctx).l =  _localctx.s.length() + (((SidelineCloneTypeIContext)_localctx).tooManySlant!=null?_input.getText(((SidelineCloneTypeIContext)_localctx).tooManySlant.start,((SidelineCloneTypeIContext)_localctx).tooManySlant.stop):null).length();
					notifyErrorListeners(_localctx.l + "|Too many slant lines before '" + _localctx.s + "'");	
				  
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(399);
				((SidelineCloneTypeIContext)_localctx).incorrectSlant = incorrectSlant();
				setState(401);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__37) | (1L << T__38) | (1L << T__45) | (1L << T__46))) != 0)) {
					{
					setState(400);
					((SidelineCloneTypeIContext)_localctx).ploidy = ploidy();
					}
				}

				setState(403);
				((SidelineCloneTypeIContext)_localctx).karyotypeIII = karyotypeIII();

					if ((((SidelineCloneTypeIContext)_localctx).ploidy!=null?_input.getText(((SidelineCloneTypeIContext)_localctx).ploidy.start,((SidelineCloneTypeIContext)_localctx).ploidy.stop):null) != null) {
						((SidelineCloneTypeIContext)_localctx).s =  (((SidelineCloneTypeIContext)_localctx).ploidy!=null?_input.getText(((SidelineCloneTypeIContext)_localctx).ploidy.start,((SidelineCloneTypeIContext)_localctx).ploidy.stop):null) + (((SidelineCloneTypeIContext)_localctx).karyotypeIII!=null?_input.getText(((SidelineCloneTypeIContext)_localctx).karyotypeIII.start,((SidelineCloneTypeIContext)_localctx).karyotypeIII.stop):null);
					} else {
						((SidelineCloneTypeIContext)_localctx).s =  (((SidelineCloneTypeIContext)_localctx).karyotypeIII!=null?_input.getText(((SidelineCloneTypeIContext)_localctx).karyotypeIII.start,((SidelineCloneTypeIContext)_localctx).karyotypeIII.stop):null);
					}
					((SidelineCloneTypeIContext)_localctx).l =  _localctx.s.length() + (((SidelineCloneTypeIContext)_localctx).incorrectSlant!=null?_input.getText(((SidelineCloneTypeIContext)_localctx).incorrectSlant.start,((SidelineCloneTypeIContext)_localctx).incorrectSlant.stop):null).length();
					notifyErrorListeners(_localctx.l + "|Incorrect clone separation before '" + _localctx.s + "'");
				  
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			sexChrList = new ArrayList<>();
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

	public static class SidelineCloneTypeIIContext extends ParserRuleContext {
		public String s = "";
		public int l = 0;
		public PloidyContext ploidy;
		public KaryotypeIVContext karyotypeIV;
		public TooManySlantContext tooManySlant;
		public IncorrectSlantContext incorrectSlant;
		public KaryotypeIVContext karyotypeIV() {
			return getRuleContext(KaryotypeIVContext.class,0);
		}
		public PloidyContext ploidy() {
			return getRuleContext(PloidyContext.class,0);
		}
		public TooManySlantContext tooManySlant() {
			return getRuleContext(TooManySlantContext.class,0);
		}
		public IncorrectSlantContext incorrectSlant() {
			return getRuleContext(IncorrectSlantContext.class,0);
		}
		public SidelineCloneTypeIIContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sidelineCloneTypeII; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterSidelineCloneTypeII(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitSidelineCloneTypeII(this);
		}
	}

	public final SidelineCloneTypeIIContext sidelineCloneTypeII() throws RecognitionException {
		SidelineCloneTypeIIContext _localctx = new SidelineCloneTypeIIContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_sidelineCloneTypeII);
		i = i + 1;
		int _la;
		try {
			setState(433);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(408);
				match(T__1);
				setState(410);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__37) | (1L << T__38) | (1L << T__45) | (1L << T__46))) != 0)) {
					{
					setState(409);
					ploidy();
					}
				}

				setState(412);
				karyotypeIV();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(414);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__37) | (1L << T__38) | (1L << T__45) | (1L << T__46))) != 0)) {
					{
					setState(413);
					((SidelineCloneTypeIIContext)_localctx).ploidy = ploidy();
					}
				}

				setState(416);
				((SidelineCloneTypeIIContext)_localctx).karyotypeIV = karyotypeIV();

					if ((((SidelineCloneTypeIIContext)_localctx).ploidy!=null?_input.getText(((SidelineCloneTypeIIContext)_localctx).ploidy.start,((SidelineCloneTypeIIContext)_localctx).ploidy.stop):null) != null) {
						((SidelineCloneTypeIIContext)_localctx).s =  (((SidelineCloneTypeIIContext)_localctx).ploidy!=null?_input.getText(((SidelineCloneTypeIIContext)_localctx).ploidy.start,((SidelineCloneTypeIIContext)_localctx).ploidy.stop):null) + (((SidelineCloneTypeIIContext)_localctx).karyotypeIV!=null?_input.getText(((SidelineCloneTypeIIContext)_localctx).karyotypeIV.start,((SidelineCloneTypeIIContext)_localctx).karyotypeIV.stop):null);
					} else {
						((SidelineCloneTypeIIContext)_localctx).s =  (((SidelineCloneTypeIIContext)_localctx).karyotypeIV!=null?_input.getText(((SidelineCloneTypeIIContext)_localctx).karyotypeIV.start,((SidelineCloneTypeIIContext)_localctx).karyotypeIV.stop):null);
					}
					((SidelineCloneTypeIIContext)_localctx).l =  _localctx.s.length();
					notifyErrorListeners(_localctx.l + "|Missing a slant line before '" + _localctx.s + "'");
				  
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(419);
				((SidelineCloneTypeIIContext)_localctx).tooManySlant = tooManySlant();
				setState(421);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__37) | (1L << T__38) | (1L << T__45) | (1L << T__46))) != 0)) {
					{
					setState(420);
					((SidelineCloneTypeIIContext)_localctx).ploidy = ploidy();
					}
				}

				setState(423);
				((SidelineCloneTypeIIContext)_localctx).karyotypeIV = karyotypeIV();

					if ((((SidelineCloneTypeIIContext)_localctx).ploidy!=null?_input.getText(((SidelineCloneTypeIIContext)_localctx).ploidy.start,((SidelineCloneTypeIIContext)_localctx).ploidy.stop):null) != null) {
						((SidelineCloneTypeIIContext)_localctx).s =  (((SidelineCloneTypeIIContext)_localctx).ploidy!=null?_input.getText(((SidelineCloneTypeIIContext)_localctx).ploidy.start,((SidelineCloneTypeIIContext)_localctx).ploidy.stop):null) + (((SidelineCloneTypeIIContext)_localctx).karyotypeIV!=null?_input.getText(((SidelineCloneTypeIIContext)_localctx).karyotypeIV.start,((SidelineCloneTypeIIContext)_localctx).karyotypeIV.stop):null);
					} else {
						((SidelineCloneTypeIIContext)_localctx).s =  (((SidelineCloneTypeIIContext)_localctx).karyotypeIV!=null?_input.getText(((SidelineCloneTypeIIContext)_localctx).karyotypeIV.start,((SidelineCloneTypeIIContext)_localctx).karyotypeIV.stop):null);
					}
					((SidelineCloneTypeIIContext)_localctx).l =  _localctx.s.length() + (((SidelineCloneTypeIIContext)_localctx).tooManySlant!=null?_input.getText(((SidelineCloneTypeIIContext)_localctx).tooManySlant.start,((SidelineCloneTypeIIContext)_localctx).tooManySlant.stop):null).length();
					notifyErrorListeners(_localctx.l + "|Too many slant lines before '" + _localctx.s + "'");	
				  
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(426);
				((SidelineCloneTypeIIContext)_localctx).incorrectSlant = incorrectSlant();
				setState(428);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__37) | (1L << T__38) | (1L << T__45) | (1L << T__46))) != 0)) {
					{
					setState(427);
					((SidelineCloneTypeIIContext)_localctx).ploidy = ploidy();
					}
				}

				setState(430);
				((SidelineCloneTypeIIContext)_localctx).karyotypeIV = karyotypeIV();

					if ((((SidelineCloneTypeIIContext)_localctx).ploidy!=null?_input.getText(((SidelineCloneTypeIIContext)_localctx).ploidy.start,((SidelineCloneTypeIIContext)_localctx).ploidy.stop):null) != null) {
						((SidelineCloneTypeIIContext)_localctx).s =  (((SidelineCloneTypeIIContext)_localctx).ploidy!=null?_input.getText(((SidelineCloneTypeIIContext)_localctx).ploidy.start,((SidelineCloneTypeIIContext)_localctx).ploidy.stop):null) + (((SidelineCloneTypeIIContext)_localctx).karyotypeIV!=null?_input.getText(((SidelineCloneTypeIIContext)_localctx).karyotypeIV.start,((SidelineCloneTypeIIContext)_localctx).karyotypeIV.stop):null);
					} else {
						((SidelineCloneTypeIIContext)_localctx).s =  (((SidelineCloneTypeIIContext)_localctx).karyotypeIV!=null?_input.getText(((SidelineCloneTypeIIContext)_localctx).karyotypeIV.start,((SidelineCloneTypeIIContext)_localctx).karyotypeIV.stop):null);
					}
					((SidelineCloneTypeIIContext)_localctx).l =  _localctx.s.length() + (((SidelineCloneTypeIIContext)_localctx).incorrectSlant!=null?_input.getText(((SidelineCloneTypeIIContext)_localctx).incorrectSlant.start,((SidelineCloneTypeIIContext)_localctx).incorrectSlant.stop):null).length();
					notifyErrorListeners(_localctx.l + "|Incorrect clone separation before '" + _localctx.s + "'");	
				  
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			sexChrList = new ArrayList<>();
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

	public static class AdditionalCloneContext extends ParserRuleContext {
		public String s = "";
		public int l = 0;
		public PloidyContext ploidy;
		public KaryotypeIContext karyotypeI;
		public TooManySlantContext tooManySlant;
		public IncorrectSlantContext incorrectSlant;
		public KaryotypeIContext karyotypeI() {
			return getRuleContext(KaryotypeIContext.class,0);
		}
		public PloidyContext ploidy() {
			return getRuleContext(PloidyContext.class,0);
		}
		public TooManySlantContext tooManySlant() {
			return getRuleContext(TooManySlantContext.class,0);
		}
		public IncorrectSlantContext incorrectSlant() {
			return getRuleContext(IncorrectSlantContext.class,0);
		}
		public AdditionalCloneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additionalClone; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterAdditionalClone(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitAdditionalClone(this);
		}
	}

	public final AdditionalCloneContext additionalClone() throws RecognitionException {
		AdditionalCloneContext _localctx = new AdditionalCloneContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_additionalClone);
		i = i + 1;
		try {
			setState(460);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(435);
				match(T__1);
				setState(437);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
				case 1:
					{
					setState(436);
					ploidy();
					}
					break;
				}
				setState(439);
				karyotypeI();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(441);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
				case 1:
					{
					setState(440);
					((AdditionalCloneContext)_localctx).ploidy = ploidy();
					}
					break;
				}
				setState(443);
				((AdditionalCloneContext)_localctx).karyotypeI = karyotypeI();

					if ((((AdditionalCloneContext)_localctx).ploidy!=null?_input.getText(((AdditionalCloneContext)_localctx).ploidy.start,((AdditionalCloneContext)_localctx).ploidy.stop):null) != null) {
						((AdditionalCloneContext)_localctx).s =  (((AdditionalCloneContext)_localctx).ploidy!=null?_input.getText(((AdditionalCloneContext)_localctx).ploidy.start,((AdditionalCloneContext)_localctx).ploidy.stop):null) + (((AdditionalCloneContext)_localctx).karyotypeI!=null?_input.getText(((AdditionalCloneContext)_localctx).karyotypeI.start,((AdditionalCloneContext)_localctx).karyotypeI.stop):null);
					} else {
						((AdditionalCloneContext)_localctx).s =  (((AdditionalCloneContext)_localctx).karyotypeI!=null?_input.getText(((AdditionalCloneContext)_localctx).karyotypeI.start,((AdditionalCloneContext)_localctx).karyotypeI.stop):null);
					}
					((AdditionalCloneContext)_localctx).l =  _localctx.s.length();
					notifyErrorListeners(_localctx.l + "|Missing a slant line before '" + _localctx.s + "'");	
				  
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(446);
				((AdditionalCloneContext)_localctx).tooManySlant = tooManySlant();
				setState(448);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
				case 1:
					{
					setState(447);
					((AdditionalCloneContext)_localctx).ploidy = ploidy();
					}
					break;
				}
				setState(450);
				((AdditionalCloneContext)_localctx).karyotypeI = karyotypeI();

					if ((((AdditionalCloneContext)_localctx).ploidy!=null?_input.getText(((AdditionalCloneContext)_localctx).ploidy.start,((AdditionalCloneContext)_localctx).ploidy.stop):null) != null) {
						((AdditionalCloneContext)_localctx).s =  (((AdditionalCloneContext)_localctx).ploidy!=null?_input.getText(((AdditionalCloneContext)_localctx).ploidy.start,((AdditionalCloneContext)_localctx).ploidy.stop):null) + (((AdditionalCloneContext)_localctx).karyotypeI!=null?_input.getText(((AdditionalCloneContext)_localctx).karyotypeI.start,((AdditionalCloneContext)_localctx).karyotypeI.stop):null);
					} else {
						((AdditionalCloneContext)_localctx).s =  (((AdditionalCloneContext)_localctx).karyotypeI!=null?_input.getText(((AdditionalCloneContext)_localctx).karyotypeI.start,((AdditionalCloneContext)_localctx).karyotypeI.stop):null);
					}
					((AdditionalCloneContext)_localctx).l =  _localctx.s.length() + (((AdditionalCloneContext)_localctx).tooManySlant!=null?_input.getText(((AdditionalCloneContext)_localctx).tooManySlant.start,((AdditionalCloneContext)_localctx).tooManySlant.stop):null).length();
					notifyErrorListeners(_localctx.l + "|Too many slant lines before '" + _localctx.s + "'");
				  
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(453);
				((AdditionalCloneContext)_localctx).incorrectSlant = incorrectSlant();
				setState(455);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
				case 1:
					{
					setState(454);
					((AdditionalCloneContext)_localctx).ploidy = ploidy();
					}
					break;
				}
				setState(457);
				((AdditionalCloneContext)_localctx).karyotypeI = karyotypeI();

					if ((((AdditionalCloneContext)_localctx).ploidy!=null?_input.getText(((AdditionalCloneContext)_localctx).ploidy.start,((AdditionalCloneContext)_localctx).ploidy.stop):null) != null) {
						((AdditionalCloneContext)_localctx).s =  (((AdditionalCloneContext)_localctx).ploidy!=null?_input.getText(((AdditionalCloneContext)_localctx).ploidy.start,((AdditionalCloneContext)_localctx).ploidy.stop):null) + (((AdditionalCloneContext)_localctx).karyotypeI!=null?_input.getText(((AdditionalCloneContext)_localctx).karyotypeI.start,((AdditionalCloneContext)_localctx).karyotypeI.stop):null);
					} else {
						((AdditionalCloneContext)_localctx).s =  (((AdditionalCloneContext)_localctx).karyotypeI!=null?_input.getText(((AdditionalCloneContext)_localctx).karyotypeI.start,((AdditionalCloneContext)_localctx).karyotypeI.stop):null);
					}
					((AdditionalCloneContext)_localctx).l =  _localctx.s.length() + (((AdditionalCloneContext)_localctx).incorrectSlant!=null?_input.getText(((AdditionalCloneContext)_localctx).incorrectSlant.start,((AdditionalCloneContext)_localctx).incorrectSlant.stop):null).length();
					notifyErrorListeners(_localctx.l + "|Incorrect clone separation before '" + _localctx.s + "'");	
				  
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			sexChrList = new ArrayList<>();
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

	public static class KaryotypeIContext extends ParserRuleContext {
		public int l = 0;
		public MosChiContext mosChi;
		public ChrNumContext chrNum;
		public ModalNumContext modalNum;
		public RegularEventContext r;
		public CellNumContext cellNum;
		public ChrNumContext chrNum() {
			return getRuleContext(ChrNumContext.class,0);
		}
		public GenderContext gender() {
			return getRuleContext(GenderContext.class,0);
		}
		public MosChiContext mosChi() {
			return getRuleContext(MosChiContext.class,0);
		}
		public ModalNumContext modalNum() {
			return getRuleContext(ModalNumContext.class,0);
		}
		public List<RegularEventContext> regularEvent() {
			return getRuleContexts(RegularEventContext.class);
		}
		public RegularEventContext regularEvent(int i) {
			return getRuleContext(RegularEventContext.class,i);
		}
		public CellNumContext cellNum() {
			return getRuleContext(CellNumContext.class,0);
		}
		public KaryotypeIContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_karyotypeI; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterKaryotypeI(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitKaryotypeI(this);
		}
	}

	public final KaryotypeIContext karyotypeI() throws RecognitionException {
		KaryotypeIContext _localctx = new KaryotypeIContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_karyotypeI);
		int _la;
		try {
			int _alt;
			setState(510);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(463);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__17) | (1L << T__18))) != 0)) {
					{
					setState(462);
					mosChi();
					}
				}

				setState(465);
				chrNum();
				setState(467);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__23) | (1L << T__31) | (1L << T__33) | (1L << T__34) | (1L << POSITIVEDIGIT))) != 0)) {
					{
					setState(466);
					modalNum();
					}
				}

				setState(469);
				gender();
				setState(473);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,50,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(470);
						regularEvent();
						}
						} 
					}
					setState(475);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,50,_ctx);
				}
				setState(477);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
				case 1:
					{
					setState(476);
					cellNum();
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(480);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__17) | (1L << T__18))) != 0)) {
					{
					setState(479);
					((KaryotypeIContext)_localctx).mosChi = mosChi();
					}
				}

				setState(482);
				((KaryotypeIContext)_localctx).chrNum = chrNum();
				setState(484);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
				case 1:
					{
					setState(483);
					((KaryotypeIContext)_localctx).modalNum = modalNum();
					}
					break;
				}
				setState(489);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(486);
						((KaryotypeIContext)_localctx).r = regularEvent();
						}
						} 
					}
					setState(491);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
				}
				setState(493);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
				case 1:
					{
					setState(492);
					((KaryotypeIContext)_localctx).cellNum = cellNum();
					}
					break;
				}

					if (sexChrList.size() == 0) {	
						if ((((KaryotypeIContext)_localctx).mosChi!=null?_input.getText(((KaryotypeIContext)_localctx).mosChi.start,((KaryotypeIContext)_localctx).mosChi.stop):null) != null) {
							((KaryotypeIContext)_localctx).l =  _localctx.l + (((KaryotypeIContext)_localctx).mosChi!=null?_input.getText(((KaryotypeIContext)_localctx).mosChi.start,((KaryotypeIContext)_localctx).mosChi.stop):null).length();
						}
						((KaryotypeIContext)_localctx).l =  _localctx.l + (((KaryotypeIContext)_localctx).chrNum!=null?_input.getText(((KaryotypeIContext)_localctx).chrNum.start,((KaryotypeIContext)_localctx).chrNum.stop):null).length();
						if ((((KaryotypeIContext)_localctx).modalNum!=null?_input.getText(((KaryotypeIContext)_localctx).modalNum.start,((KaryotypeIContext)_localctx).modalNum.stop):null) != null) {
							((KaryotypeIContext)_localctx).l =  _localctx.l + (((KaryotypeIContext)_localctx).modalNum!=null?_input.getText(((KaryotypeIContext)_localctx).modalNum.start,((KaryotypeIContext)_localctx).modalNum.stop):null).length();
						}
						if ((((KaryotypeIContext)_localctx).r!=null?_input.getText(((KaryotypeIContext)_localctx).r.start,((KaryotypeIContext)_localctx).r.stop):null) != null) {
							((KaryotypeIContext)_localctx).l =  _localctx.l + (((KaryotypeIContext)_localctx).r!=null?_input.getText(((KaryotypeIContext)_localctx).r.start,((KaryotypeIContext)_localctx).r.stop):null).length();
						}
						if ((((KaryotypeIContext)_localctx).cellNum!=null?_input.getText(((KaryotypeIContext)_localctx).cellNum.start,((KaryotypeIContext)_localctx).cellNum.stop):null) != null) {
							((KaryotypeIContext)_localctx).l =  _localctx.l + (((KaryotypeIContext)_localctx).cellNum!=null?_input.getText(((KaryotypeIContext)_localctx).cellNum.start,((KaryotypeIContext)_localctx).cellNum.stop):null).length();
						}
						notifyErrorListeners(_localctx.l + "|Missing gender in clone # " + i);
					}
				  
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(498);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
				case 1:
					{
					setState(497);
					((KaryotypeIContext)_localctx).mosChi = mosChi();
					}
					break;
				}
				setState(501); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(500);
						((KaryotypeIContext)_localctx).r = regularEvent();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(503); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,57,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(506);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
				case 1:
					{
					setState(505);
					((KaryotypeIContext)_localctx).cellNum = cellNum();
					}
					break;
				}

					if ((((KaryotypeIContext)_localctx).mosChi!=null?_input.getText(((KaryotypeIContext)_localctx).mosChi.start,((KaryotypeIContext)_localctx).mosChi.stop):null) != null) {
						((KaryotypeIContext)_localctx).l =  _localctx.l + (((KaryotypeIContext)_localctx).mosChi!=null?_input.getText(((KaryotypeIContext)_localctx).mosChi.start,((KaryotypeIContext)_localctx).mosChi.stop):null).length();
					}
					((KaryotypeIContext)_localctx).l =  _localctx.l + (((KaryotypeIContext)_localctx).r!=null?_input.getText(((KaryotypeIContext)_localctx).r.start,((KaryotypeIContext)_localctx).r.stop):null).length();
					if ((((KaryotypeIContext)_localctx).cellNum!=null?_input.getText(((KaryotypeIContext)_localctx).cellNum.start,((KaryotypeIContext)_localctx).cellNum.stop):null) != null) {
						((KaryotypeIContext)_localctx).l =  _localctx.l + (((KaryotypeIContext)_localctx).cellNum!=null?_input.getText(((KaryotypeIContext)_localctx).cellNum.start,((KaryotypeIContext)_localctx).cellNum.stop):null).length();
					}	
					notifyErrorListeners(_localctx.l + "|Missing chromosome numbers and gender");
				  
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

	public static class KaryotypeIIContext extends ParserRuleContext {
		public int l = 0;
		public MosChiContext mosChi;
		public IdemSpecialContext idemSpecial;
		public RegularEventContext r;
		public CellNumContext cellNum;
		public ChrNumContext chrNum;
		public ModalNumContext modalNum;
		public ChrNumContext chrNum() {
			return getRuleContext(ChrNumContext.class,0);
		}
		public GenderContext gender() {
			return getRuleContext(GenderContext.class,0);
		}
		public MosChiContext mosChi() {
			return getRuleContext(MosChiContext.class,0);
		}
		public ModalNumContext modalNum() {
			return getRuleContext(ModalNumContext.class,0);
		}
		public List<RegularEventContext> regularEvent() {
			return getRuleContexts(RegularEventContext.class);
		}
		public RegularEventContext regularEvent(int i) {
			return getRuleContext(RegularEventContext.class,i);
		}
		public CellNumContext cellNum() {
			return getRuleContext(CellNumContext.class,0);
		}
		public IdemSpecialContext idemSpecial() {
			return getRuleContext(IdemSpecialContext.class,0);
		}
		public KaryotypeIIContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_karyotypeII; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterKaryotypeII(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitKaryotypeII(this);
		}
	}

	public final KaryotypeIIContext karyotypeII() throws RecognitionException {
		KaryotypeIIContext _localctx = new KaryotypeIIContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_karyotypeII);
		int _la;
		try {
			int _alt;
			setState(592);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(513);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__17) | (1L << T__18))) != 0)) {
					{
					setState(512);
					mosChi();
					}
				}

				setState(515);
				chrNum();
				setState(517);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__23) | (1L << T__31) | (1L << T__33) | (1L << T__34) | (1L << POSITIVEDIGIT))) != 0)) {
					{
					setState(516);
					modalNum();
					}
				}

				setState(519);
				gender();
				setState(523);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,62,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(520);
						regularEvent();
						}
						} 
					}
					setState(525);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,62,_ctx);
				}
				setState(527);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
				case 1:
					{
					setState(526);
					cellNum();
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(530);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__17) | (1L << T__18))) != 0)) {
					{
					setState(529);
					mosChi();
					}
				}

				setState(532);
				chrNum();
				setState(534);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__23) | (1L << T__31) | (1L << T__33) | (1L << T__34) | (1L << POSITIVEDIGIT))) != 0)) {
					{
					setState(533);
					modalNum();
					}
				}

				setState(536);
				idemSpecial();
				setState(540);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,66,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(537);
						regularEvent();
						}
						} 
					}
					setState(542);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,66,_ctx);
				}
				setState(544);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
				case 1:
					{
					setState(543);
					cellNum();
					}
					break;
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(547);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__17) | (1L << T__18))) != 0)) {
					{
					setState(546);
					((KaryotypeIIContext)_localctx).mosChi = mosChi();
					}
				}

				setState(549);
				((KaryotypeIIContext)_localctx).idemSpecial = idemSpecial();
				setState(553);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,69,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(550);
						((KaryotypeIIContext)_localctx).r = regularEvent();
						}
						} 
					}
					setState(555);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,69,_ctx);
				}
				setState(557);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
				case 1:
					{
					setState(556);
					((KaryotypeIIContext)_localctx).cellNum = cellNum();
					}
					break;
				}

					if ((((KaryotypeIIContext)_localctx).mosChi!=null?_input.getText(((KaryotypeIIContext)_localctx).mosChi.start,((KaryotypeIIContext)_localctx).mosChi.stop):null) != null) {
						((KaryotypeIIContext)_localctx).l =  _localctx.l + (((KaryotypeIIContext)_localctx).mosChi!=null?_input.getText(((KaryotypeIIContext)_localctx).mosChi.start,((KaryotypeIIContext)_localctx).mosChi.stop):null).length();
					}
					((KaryotypeIIContext)_localctx).l =  _localctx.l + (((KaryotypeIIContext)_localctx).idemSpecial!=null?_input.getText(((KaryotypeIIContext)_localctx).idemSpecial.start,((KaryotypeIIContext)_localctx).idemSpecial.stop):null).length();
					if ((((KaryotypeIIContext)_localctx).r!=null?_input.getText(((KaryotypeIIContext)_localctx).r.start,((KaryotypeIIContext)_localctx).r.stop):null) != null) {
						((KaryotypeIIContext)_localctx).l =  _localctx.l + (((KaryotypeIIContext)_localctx).r!=null?_input.getText(((KaryotypeIIContext)_localctx).r.start,((KaryotypeIIContext)_localctx).r.stop):null).length();
					}
					if ((((KaryotypeIIContext)_localctx).cellNum!=null?_input.getText(((KaryotypeIIContext)_localctx).cellNum.start,((KaryotypeIIContext)_localctx).cellNum.stop):null) != null) {
						((KaryotypeIIContext)_localctx).l =  _localctx.l + (((KaryotypeIIContext)_localctx).cellNum!=null?_input.getText(((KaryotypeIIContext)_localctx).cellNum.start,((KaryotypeIIContext)_localctx).cellNum.stop):null).length();
					}
					notifyErrorListeners(_localctx.l + "|Missing chromosome numbers");
				  
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(562);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__17) | (1L << T__18))) != 0)) {
					{
					setState(561);
					((KaryotypeIIContext)_localctx).mosChi = mosChi();
					}
				}

				setState(564);
				((KaryotypeIIContext)_localctx).chrNum = chrNum();
				setState(566);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
				case 1:
					{
					setState(565);
					((KaryotypeIIContext)_localctx).modalNum = modalNum();
					}
					break;
				}
				setState(571);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,73,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(568);
						((KaryotypeIIContext)_localctx).r = regularEvent();
						}
						} 
					}
					setState(573);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,73,_ctx);
				}
				setState(575);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
				case 1:
					{
					setState(574);
					((KaryotypeIIContext)_localctx).cellNum = cellNum();
					}
					break;
				}

					if (sexChrList.size() == 0) {
						if ((((KaryotypeIIContext)_localctx).mosChi!=null?_input.getText(((KaryotypeIIContext)_localctx).mosChi.start,((KaryotypeIIContext)_localctx).mosChi.stop):null) != null) {
							((KaryotypeIIContext)_localctx).l =  _localctx.l + (((KaryotypeIIContext)_localctx).mosChi!=null?_input.getText(((KaryotypeIIContext)_localctx).mosChi.start,((KaryotypeIIContext)_localctx).mosChi.stop):null).length();
						}
						((KaryotypeIIContext)_localctx).l =  _localctx.l + (((KaryotypeIIContext)_localctx).chrNum!=null?_input.getText(((KaryotypeIIContext)_localctx).chrNum.start,((KaryotypeIIContext)_localctx).chrNum.stop):null).length();
						if ((((KaryotypeIIContext)_localctx).modalNum!=null?_input.getText(((KaryotypeIIContext)_localctx).modalNum.start,((KaryotypeIIContext)_localctx).modalNum.stop):null) != null) {
							((KaryotypeIIContext)_localctx).l =  _localctx.l + (((KaryotypeIIContext)_localctx).modalNum!=null?_input.getText(((KaryotypeIIContext)_localctx).modalNum.start,((KaryotypeIIContext)_localctx).modalNum.stop):null).length();
						}
						if ((((KaryotypeIIContext)_localctx).r!=null?_input.getText(((KaryotypeIIContext)_localctx).r.start,((KaryotypeIIContext)_localctx).r.stop):null) != null) {
							((KaryotypeIIContext)_localctx).l =  _localctx.l + (((KaryotypeIIContext)_localctx).r!=null?_input.getText(((KaryotypeIIContext)_localctx).r.start,((KaryotypeIIContext)_localctx).r.stop):null).length();
						}
						if ((((KaryotypeIIContext)_localctx).cellNum!=null?_input.getText(((KaryotypeIIContext)_localctx).cellNum.start,((KaryotypeIIContext)_localctx).cellNum.stop):null) != null) {
							((KaryotypeIIContext)_localctx).l =  _localctx.l + (((KaryotypeIIContext)_localctx).cellNum!=null?_input.getText(((KaryotypeIIContext)_localctx).cellNum.start,((KaryotypeIIContext)_localctx).cellNum.stop):null).length();
						}
						notifyErrorListeners(_localctx.l + "|Missing gender in clone # " + i);
					}
				  
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(580);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,75,_ctx) ) {
				case 1:
					{
					setState(579);
					((KaryotypeIIContext)_localctx).mosChi = mosChi();
					}
					break;
				}
				setState(583); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(582);
						((KaryotypeIIContext)_localctx).r = regularEvent();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(585); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,76,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(588);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
				case 1:
					{
					setState(587);
					((KaryotypeIIContext)_localctx).cellNum = cellNum();
					}
					break;
				}

					if ((((KaryotypeIIContext)_localctx).mosChi!=null?_input.getText(((KaryotypeIIContext)_localctx).mosChi.start,((KaryotypeIIContext)_localctx).mosChi.stop):null) != null) {
						((KaryotypeIIContext)_localctx).l =  _localctx.l + (((KaryotypeIIContext)_localctx).mosChi!=null?_input.getText(((KaryotypeIIContext)_localctx).mosChi.start,((KaryotypeIIContext)_localctx).mosChi.stop):null).length();
					}
					((KaryotypeIIContext)_localctx).l =  _localctx.l + (((KaryotypeIIContext)_localctx).r!=null?_input.getText(((KaryotypeIIContext)_localctx).r.start,((KaryotypeIIContext)_localctx).r.stop):null).length();
					if ((((KaryotypeIIContext)_localctx).cellNum!=null?_input.getText(((KaryotypeIIContext)_localctx).cellNum.start,((KaryotypeIIContext)_localctx).cellNum.stop):null) != null) {
						((KaryotypeIIContext)_localctx).l =  _localctx.l + (((KaryotypeIIContext)_localctx).cellNum!=null?_input.getText(((KaryotypeIIContext)_localctx).cellNum.start,((KaryotypeIIContext)_localctx).cellNum.stop):null).length();
					}	
					notifyErrorListeners(_localctx.l + "|Missing chromosome numbers and gender");
				  
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

	public static class KaryotypeIIIContext extends ParserRuleContext {
		public int l = 0;
		public MosChiContext mosChi;
		public SlSpecialContext slSpecial;
		public RegularEventContext r;
		public CellNumContext cellNum;
		public ChrNumContext chrNum() {
			return getRuleContext(ChrNumContext.class,0);
		}
		public SlSpecialContext slSpecial() {
			return getRuleContext(SlSpecialContext.class,0);
		}
		public MosChiContext mosChi() {
			return getRuleContext(MosChiContext.class,0);
		}
		public ModalNumContext modalNum() {
			return getRuleContext(ModalNumContext.class,0);
		}
		public List<RegularEventContext> regularEvent() {
			return getRuleContexts(RegularEventContext.class);
		}
		public RegularEventContext regularEvent(int i) {
			return getRuleContext(RegularEventContext.class,i);
		}
		public CellNumContext cellNum() {
			return getRuleContext(CellNumContext.class,0);
		}
		public KaryotypeIIIContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_karyotypeIII; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterKaryotypeIII(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitKaryotypeIII(this);
		}
	}

	public final KaryotypeIIIContext karyotypeIII() throws RecognitionException {
		KaryotypeIIIContext _localctx = new KaryotypeIIIContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_karyotypeIII);
		int _la;
		try {
			int _alt;
			setState(626);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,86,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(595);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__17) | (1L << T__18))) != 0)) {
					{
					setState(594);
					mosChi();
					}
				}

				setState(597);
				chrNum();
				setState(599);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__23) | (1L << T__31) | (1L << T__33) | (1L << T__34) | (1L << POSITIVEDIGIT))) != 0)) {
					{
					setState(598);
					modalNum();
					}
				}

				setState(601);
				slSpecial();
				setState(605);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,81,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(602);
						regularEvent();
						}
						} 
					}
					setState(607);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,81,_ctx);
				}
				setState(609);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
				case 1:
					{
					setState(608);
					cellNum();
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(612);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__17) | (1L << T__18))) != 0)) {
					{
					setState(611);
					((KaryotypeIIIContext)_localctx).mosChi = mosChi();
					}
				}

				setState(614);
				((KaryotypeIIIContext)_localctx).slSpecial = slSpecial();
				setState(618);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,84,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(615);
						((KaryotypeIIIContext)_localctx).r = regularEvent();
						}
						} 
					}
					setState(620);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,84,_ctx);
				}
				setState(622);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,85,_ctx) ) {
				case 1:
					{
					setState(621);
					((KaryotypeIIIContext)_localctx).cellNum = cellNum();
					}
					break;
				}

					if ((((KaryotypeIIIContext)_localctx).mosChi!=null?_input.getText(((KaryotypeIIIContext)_localctx).mosChi.start,((KaryotypeIIIContext)_localctx).mosChi.stop):null) != null) {
						((KaryotypeIIIContext)_localctx).l =  _localctx.l + (((KaryotypeIIIContext)_localctx).mosChi!=null?_input.getText(((KaryotypeIIIContext)_localctx).mosChi.start,((KaryotypeIIIContext)_localctx).mosChi.stop):null).length();
					}
					((KaryotypeIIIContext)_localctx).l =  _localctx.l + (((KaryotypeIIIContext)_localctx).slSpecial!=null?_input.getText(((KaryotypeIIIContext)_localctx).slSpecial.start,((KaryotypeIIIContext)_localctx).slSpecial.stop):null).length();
					if ((((KaryotypeIIIContext)_localctx).r!=null?_input.getText(((KaryotypeIIIContext)_localctx).r.start,((KaryotypeIIIContext)_localctx).r.stop):null) != null) {
						((KaryotypeIIIContext)_localctx).l =  _localctx.l + (((KaryotypeIIIContext)_localctx).r!=null?_input.getText(((KaryotypeIIIContext)_localctx).r.start,((KaryotypeIIIContext)_localctx).r.stop):null).length();
					}
					if ((((KaryotypeIIIContext)_localctx).cellNum!=null?_input.getText(((KaryotypeIIIContext)_localctx).cellNum.start,((KaryotypeIIIContext)_localctx).cellNum.stop):null) != null) {
						((KaryotypeIIIContext)_localctx).l =  _localctx.l + (((KaryotypeIIIContext)_localctx).cellNum!=null?_input.getText(((KaryotypeIIIContext)_localctx).cellNum.start,((KaryotypeIIIContext)_localctx).cellNum.stop):null).length();
					}
					notifyErrorListeners(_localctx.l + "|Missing chromosome numbers");
				  
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

	public static class KaryotypeIVContext extends ParserRuleContext {
		public int l = 0;
		public MosChiContext mosChi;
		public SdlSpecialContext sdlSpecial;
		public RegularEventContext r;
		public CellNumContext cellNum;
		public ChrNumContext chrNum() {
			return getRuleContext(ChrNumContext.class,0);
		}
		public SdlSpecialContext sdlSpecial() {
			return getRuleContext(SdlSpecialContext.class,0);
		}
		public MosChiContext mosChi() {
			return getRuleContext(MosChiContext.class,0);
		}
		public ModalNumContext modalNum() {
			return getRuleContext(ModalNumContext.class,0);
		}
		public List<RegularEventContext> regularEvent() {
			return getRuleContexts(RegularEventContext.class);
		}
		public RegularEventContext regularEvent(int i) {
			return getRuleContext(RegularEventContext.class,i);
		}
		public CellNumContext cellNum() {
			return getRuleContext(CellNumContext.class,0);
		}
		public KaryotypeIVContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_karyotypeIV; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterKaryotypeIV(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitKaryotypeIV(this);
		}
	}

	public final KaryotypeIVContext karyotypeIV() throws RecognitionException {
		KaryotypeIVContext _localctx = new KaryotypeIVContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_karyotypeIV);
		int _la;
		try {
			int _alt;
			setState(660);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,94,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(629);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__17) | (1L << T__18))) != 0)) {
					{
					setState(628);
					mosChi();
					}
				}

				setState(631);
				chrNum();
				setState(633);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__23) | (1L << T__31) | (1L << T__33) | (1L << T__34) | (1L << POSITIVEDIGIT))) != 0)) {
					{
					setState(632);
					modalNum();
					}
				}

				setState(635);
				sdlSpecial();
				setState(639);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,89,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(636);
						regularEvent();
						}
						} 
					}
					setState(641);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,89,_ctx);
				}
				setState(643);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,90,_ctx) ) {
				case 1:
					{
					setState(642);
					cellNum();
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(646);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__17) | (1L << T__18))) != 0)) {
					{
					setState(645);
					((KaryotypeIVContext)_localctx).mosChi = mosChi();
					}
				}

				setState(648);
				((KaryotypeIVContext)_localctx).sdlSpecial = sdlSpecial();
				setState(652);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,92,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(649);
						((KaryotypeIVContext)_localctx).r = regularEvent();
						}
						} 
					}
					setState(654);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,92,_ctx);
				}
				setState(656);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,93,_ctx) ) {
				case 1:
					{
					setState(655);
					((KaryotypeIVContext)_localctx).cellNum = cellNum();
					}
					break;
				}

					if ((((KaryotypeIVContext)_localctx).mosChi!=null?_input.getText(((KaryotypeIVContext)_localctx).mosChi.start,((KaryotypeIVContext)_localctx).mosChi.stop):null) != null) {
						((KaryotypeIVContext)_localctx).l =  _localctx.l + (((KaryotypeIVContext)_localctx).mosChi!=null?_input.getText(((KaryotypeIVContext)_localctx).mosChi.start,((KaryotypeIVContext)_localctx).mosChi.stop):null).length();
					}
					((KaryotypeIVContext)_localctx).l =  _localctx.l + (((KaryotypeIVContext)_localctx).sdlSpecial!=null?_input.getText(((KaryotypeIVContext)_localctx).sdlSpecial.start,((KaryotypeIVContext)_localctx).sdlSpecial.stop):null).length();
					if ((((KaryotypeIVContext)_localctx).r!=null?_input.getText(((KaryotypeIVContext)_localctx).r.start,((KaryotypeIVContext)_localctx).r.stop):null) != null) {
						((KaryotypeIVContext)_localctx).l =  _localctx.l + (((KaryotypeIVContext)_localctx).r!=null?_input.getText(((KaryotypeIVContext)_localctx).r.start,((KaryotypeIVContext)_localctx).r.stop):null).length();
					}
					if ((((KaryotypeIVContext)_localctx).cellNum!=null?_input.getText(((KaryotypeIVContext)_localctx).cellNum.start,((KaryotypeIVContext)_localctx).cellNum.stop):null) != null) {
						((KaryotypeIVContext)_localctx).l =  _localctx.l + (((KaryotypeIVContext)_localctx).cellNum!=null?_input.getText(((KaryotypeIVContext)_localctx).cellNum.start,((KaryotypeIVContext)_localctx).cellNum.stop):null).length();
					}	
					notifyErrorListeners(_localctx.l + "|Missing chromosome numbers");
				  
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

	public static class KaryotypeVContext extends ParserRuleContext {
		public MosChiContext mosChi() {
			return getRuleContext(MosChiContext.class,0);
		}
		public ChrNumContext chrNum() {
			return getRuleContext(ChrNumContext.class,0);
		}
		public GenderContext gender() {
			return getRuleContext(GenderContext.class,0);
		}
		public CellNumContext cellNum() {
			return getRuleContext(CellNumContext.class,0);
		}
		public ModalNumContext modalNum() {
			return getRuleContext(ModalNumContext.class,0);
		}
		public KaryotypeVContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_karyotypeV; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterKaryotypeV(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitKaryotypeV(this);
		}
	}

	public final KaryotypeVContext karyotypeV() throws RecognitionException {
		KaryotypeVContext _localctx = new KaryotypeVContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_karyotypeV);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(663);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__17) | (1L << T__18))) != 0)) {
				{
				setState(662);
				mosChi();
				}
			}

			setState(672);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__49) | (1L << POSITIVEDIGIT) | (1L << QUES))) != 0)) {
				{
				setState(665);
				chrNum();
				setState(667);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__23) | (1L << T__31) | (1L << T__33) | (1L << T__34) | (1L << POSITIVEDIGIT))) != 0)) {
					{
					setState(666);
					modalNum();
					}
				}

				setState(669);
				gender();
				setState(670);
				match(T__2);
				}
			}

			setState(674);
			_la = _input.LA(1);
			if ( !(_la==T__3 || _la==T__4) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(675);
			_la = _input.LA(1);
			if ( !(_la==T__5 || _la==T__6) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(676);
			_la = _input.LA(1);
			if ( !(_la==T__3 || _la==T__4) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(678);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MINUS) {
				{
				setState(677);
				match(MINUS);
				}
			}

			setState(680);
			_la = _input.LA(1);
			if ( !(_la==T__7 || _la==T__8) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(681);
			_la = _input.LA(1);
			if ( !(_la==T__9 || _la==T__10) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(682);
			_la = _input.LA(1);
			if ( !(_la==T__5 || _la==T__6) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(683);
			_la = _input.LA(1);
			if ( !(_la==T__3 || _la==T__4) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(684);
			_la = _input.LA(1);
			if ( !(_la==T__11 || _la==T__12) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(685);
			_la = _input.LA(1);
			if ( !(_la==T__9 || _la==T__10) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(696);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
			case 1:
				{
				setState(686);
				_la = _input.LA(1);
				if ( !(_la==T__11 || _la==T__12) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(687);
				_la = _input.LA(1);
				if ( !(_la==T__13 || _la==T__14) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(688);
				_la = _input.LA(1);
				if ( !(_la==T__3 || _la==T__4) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(694);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__5 || _la==T__6) {
					{
					setState(689);
					_la = _input.LA(1);
					if ( !(_la==T__5 || _la==T__6) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(690);
					_la = _input.LA(1);
					if ( !(_la==T__15 || _la==T__16) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(691);
					_la = _input.LA(1);
					if ( !(_la==T__17 || _la==T__18) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(692);
					_la = _input.LA(1);
					if ( !(_la==T__11 || _la==T__12) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(693);
					_la = _input.LA(1);
					if ( !(_la==T__9 || _la==T__10) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				}
				break;
			}
			setState(699);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,101,_ctx) ) {
			case 1:
				{
				setState(698);
				cellNum();
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

	public static class PloidyContext extends ParserRuleContext {
		public ModalDescContext modalDesc() {
			return getRuleContext(ModalDescContext.class,0);
		}
		public PloidyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ploidy; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterPloidy(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitPloidy(this);
		}
	}

	public final PloidyContext ploidy() throws RecognitionException {
		PloidyContext _localctx = new PloidyContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_ploidy);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(701);
			modalDesc();
			setState(710);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,103,_ctx) ) {
			case 1:
				{
				setState(702);
				_la = _input.LA(1);
				if ( !(_la==T__7 || _la==T__8) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(703);
				_la = _input.LA(1);
				if ( !(_la==T__9 || _la==T__10) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(704);
				_la = _input.LA(1);
				if ( !(_la==T__5 || _la==T__6) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(705);
				_la = _input.LA(1);
				if ( !(_la==T__3 || _la==T__4) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(706);
				_la = _input.LA(1);
				if ( !(_la==T__19 || _la==T__20) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(708);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__21) {
					{
					setState(707);
					match(T__21);
					}
				}

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

	public static class TooManySlantContext extends ParserRuleContext {
		public TooManySlantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tooManySlant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterTooManySlant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitTooManySlant(this);
		}
	}

	public final TooManySlantContext tooManySlant() throws RecognitionException {
		TooManySlantContext _localctx = new TooManySlantContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_tooManySlant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(712);
			match(T__1);
			setState(714); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(713);
				match(T__1);
				}
				}
				setState(716); 
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

	public static class IncorrectSlantContext extends ParserRuleContext {
		public IncorrectSlantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_incorrectSlant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterIncorrectSlant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitIncorrectSlant(this);
		}
	}

	public final IncorrectSlantContext incorrectSlant() throws RecognitionException {
		IncorrectSlantContext _localctx = new IncorrectSlantContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_incorrectSlant);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(719); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(718);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__22))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(721); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,105,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class ModalNumContext extends ParserRuleContext {
		public String s = "";
		public int l = 0;
		public ModalNumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modalNum; }
	 
		public ModalNumContext() { }
		public void copyFrom(ModalNumContext ctx) {
			super.copyFrom(ctx);
			this.s = ctx.s;
			this.l = ctx.l;
		}
	}
	public static class CorrectModalNumContext extends ModalNumContext {
		public ModalNumContentContext modalNumContent() {
			return getRuleContext(ModalNumContentContext.class,0);
		}
		public CorrectModalNumContext(ModalNumContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterCorrectModalNum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitCorrectModalNum(this);
		}
	}
	public static class IncorrectModalNumContext extends ModalNumContext {
		public ModalNumContentContext modalNumContent;
		public IncorrectLeftParenthesisContext incorrectLeftParenthesis;
		public IncorrectRightParenthesisContext incorrectRightParenthesis;
		public ModalNumContentContext modalNumContent() {
			return getRuleContext(ModalNumContentContext.class,0);
		}
		public IncorrectLeftParenthesisContext incorrectLeftParenthesis() {
			return getRuleContext(IncorrectLeftParenthesisContext.class,0);
		}
		public IncorrectRightParenthesisContext incorrectRightParenthesis() {
			return getRuleContext(IncorrectRightParenthesisContext.class,0);
		}
		public IncorrectModalNumContext(ModalNumContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterIncorrectModalNum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitIncorrectModalNum(this);
		}
	}

	public final ModalNumContext modalNum() throws RecognitionException {
		ModalNumContext _localctx = new ModalNumContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_modalNum);
		try {
			setState(743);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,106,_ctx) ) {
			case 1:
				_localctx = new CorrectModalNumContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(723);
				match(T__23);
				setState(724);
				modalNumContent();
				setState(725);
				match(T__24);
				}
				break;
			case 2:
				_localctx = new IncorrectModalNumContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(727);
				((IncorrectModalNumContext)_localctx).modalNumContent = modalNumContent();
				setState(728);
				match(T__24);

				  	((IncorrectModalNumContext)_localctx).s =  (((IncorrectModalNumContext)_localctx).modalNumContent!=null?_input.getText(((IncorrectModalNumContext)_localctx).modalNumContent.start,((IncorrectModalNumContext)_localctx).modalNumContent.stop):null);
				  	((IncorrectModalNumContext)_localctx).l =  _localctx.s.length() + 1;
				  	notifyErrorListeners(_localctx.l + "|Missing '<' for a modal number '" + _localctx.s + "'");
				  
				}
				break;
			case 3:
				_localctx = new IncorrectModalNumContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(731);
				match(T__23);
				setState(732);
				((IncorrectModalNumContext)_localctx).modalNumContent = modalNumContent();

				  	((IncorrectModalNumContext)_localctx).s =  (((IncorrectModalNumContext)_localctx).modalNumContent!=null?_input.getText(((IncorrectModalNumContext)_localctx).modalNumContent.start,((IncorrectModalNumContext)_localctx).modalNumContent.stop):null);
				  	((IncorrectModalNumContext)_localctx).l =  _localctx.s.length() + 1;
				  	notifyErrorListeners(_localctx.l + "|Missing '>' for a modal number '" + _localctx.s + "'");
				  
				}
				break;
			case 4:
				_localctx = new IncorrectModalNumContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(735);
				((IncorrectModalNumContext)_localctx).modalNumContent = modalNumContent();

				  	((IncorrectModalNumContext)_localctx).s =  (((IncorrectModalNumContext)_localctx).modalNumContent!=null?_input.getText(((IncorrectModalNumContext)_localctx).modalNumContent.start,((IncorrectModalNumContext)_localctx).modalNumContent.stop):null);
				  	((IncorrectModalNumContext)_localctx).l =  _localctx.s.length();
				  	notifyErrorListeners(_localctx.l + "|Missing '<>' for a modal number '" + _localctx.s + "'");
				  
				}
				break;
			case 5:
				_localctx = new IncorrectModalNumContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(738);
				((IncorrectModalNumContext)_localctx).incorrectLeftParenthesis = incorrectLeftParenthesis();
				setState(739);
				((IncorrectModalNumContext)_localctx).modalNumContent = modalNumContent();
				setState(740);
				((IncorrectModalNumContext)_localctx).incorrectRightParenthesis = incorrectRightParenthesis();

				  	((IncorrectModalNumContext)_localctx).s =  (((IncorrectModalNumContext)_localctx).modalNumContent!=null?_input.getText(((IncorrectModalNumContext)_localctx).modalNumContent.start,((IncorrectModalNumContext)_localctx).modalNumContent.stop):null);
				  	((IncorrectModalNumContext)_localctx).l =  _localctx.s.length();
				  	if ((((IncorrectModalNumContext)_localctx).incorrectLeftParenthesis!=null?_input.getText(((IncorrectModalNumContext)_localctx).incorrectLeftParenthesis.start,((IncorrectModalNumContext)_localctx).incorrectLeftParenthesis.stop):null) != null) {
				  		((IncorrectModalNumContext)_localctx).l =  _localctx.l + (((IncorrectModalNumContext)_localctx).incorrectLeftParenthesis!=null?_input.getText(((IncorrectModalNumContext)_localctx).incorrectLeftParenthesis.start,((IncorrectModalNumContext)_localctx).incorrectLeftParenthesis.stop):null).length();
				  	}
				  	if ((((IncorrectModalNumContext)_localctx).incorrectRightParenthesis!=null?_input.getText(((IncorrectModalNumContext)_localctx).incorrectRightParenthesis.start,((IncorrectModalNumContext)_localctx).incorrectRightParenthesis.stop):null) != null) {
				  		((IncorrectModalNumContext)_localctx).l =  _localctx.l + (((IncorrectModalNumContext)_localctx).incorrectRightParenthesis!=null?_input.getText(((IncorrectModalNumContext)_localctx).incorrectRightParenthesis.start,((IncorrectModalNumContext)_localctx).incorrectRightParenthesis.stop):null).length();
				  	}
				  	notifyErrorListeners(_localctx.l + "|Wrong bracket format for a modal number '" + _localctx.s + "', expecting '<>'");
				  
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

	public static class ModalNumContentContext extends ParserRuleContext {
		public TerminalNode POSITIVEDIGIT() { return getToken(KaryotypeParser.POSITIVEDIGIT, 0); }
		public ModalNumContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modalNumContent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterModalNumContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitModalNumContent(this);
		}
	}

	public final ModalNumContentContext modalNumContent() throws RecognitionException {
		ModalNumContentContext _localctx = new ModalNumContentContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_modalNumContent);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(745);
			match(POSITIVEDIGIT);
			setState(746);
			_la = _input.LA(1);
			if ( !(_la==T__3 || _la==T__4) ) {
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

	public static class GenderContext extends ParserRuleContext {
		public String s = "";
		public int l = 0;
		public SexChrContext sexChr;
		public OrSexContext orSex;
		public CContext c;
		public TooManyCommaContext tooManyComma;
		public SexChrContext sexChr() {
			return getRuleContext(SexChrContext.class,0);
		}
		public OrSexContext orSex() {
			return getRuleContext(OrSexContext.class,0);
		}
		public CContext c() {
			return getRuleContext(CContext.class,0);
		}
		public TooManyCommaContext tooManyComma() {
			return getRuleContext(TooManyCommaContext.class,0);
		}
		public GenderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gender; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterGender(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitGender(this);
		}
	}

	public final GenderContext gender() throws RecognitionException {
		GenderContext _localctx = new GenderContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_gender);
		try {
			setState(769);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,110,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(748);
				match(T__2);
				setState(749);
				sexChr();
				setState(750);
				orSex();
				setState(752);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,107,_ctx) ) {
				case 1:
					{
					setState(751);
					c();
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(754);
				((GenderContext)_localctx).sexChr = sexChr();
				setState(755);
				((GenderContext)_localctx).orSex = orSex();
				setState(757);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,108,_ctx) ) {
				case 1:
					{
					setState(756);
					((GenderContext)_localctx).c = c();
					}
					break;
				}

					((GenderContext)_localctx).s =  (((GenderContext)_localctx).sexChr!=null?_input.getText(((GenderContext)_localctx).sexChr.start,((GenderContext)_localctx).sexChr.stop):null);
					if ((((GenderContext)_localctx).orSex!=null?_input.getText(((GenderContext)_localctx).orSex.start,((GenderContext)_localctx).orSex.stop):null) != null) {
						((GenderContext)_localctx).s =  _localctx.s + (((GenderContext)_localctx).orSex!=null?_input.getText(((GenderContext)_localctx).orSex.start,((GenderContext)_localctx).orSex.stop):null);
					}
					if ((((GenderContext)_localctx).c!=null?_input.getText(((GenderContext)_localctx).c.start,((GenderContext)_localctx).c.stop):null) != null) {
						((GenderContext)_localctx).s =  _localctx.s + (((GenderContext)_localctx).c!=null?_input.getText(((GenderContext)_localctx).c.start,((GenderContext)_localctx).c.stop):null);
					}
					((GenderContext)_localctx).l =  _localctx.s.length();
					notifyErrorListeners(_localctx.l + "|Missing a comma before '" + _localctx.s + "'");
				  
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(761);
				((GenderContext)_localctx).tooManyComma = tooManyComma();
				setState(762);
				((GenderContext)_localctx).sexChr = sexChr();
				setState(763);
				((GenderContext)_localctx).orSex = orSex();
				setState(765);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,109,_ctx) ) {
				case 1:
					{
					setState(764);
					((GenderContext)_localctx).c = c();
					}
					break;
				}

					((GenderContext)_localctx).s =  (((GenderContext)_localctx).sexChr!=null?_input.getText(((GenderContext)_localctx).sexChr.start,((GenderContext)_localctx).sexChr.stop):null);
					if ((((GenderContext)_localctx).orSex!=null?_input.getText(((GenderContext)_localctx).orSex.start,((GenderContext)_localctx).orSex.stop):null) != null) {
						((GenderContext)_localctx).s =  _localctx.s + (((GenderContext)_localctx).orSex!=null?_input.getText(((GenderContext)_localctx).orSex.start,((GenderContext)_localctx).orSex.stop):null);
					}
					if ((((GenderContext)_localctx).c!=null?_input.getText(((GenderContext)_localctx).c.start,((GenderContext)_localctx).c.stop):null) != null) {
						((GenderContext)_localctx).s =  _localctx.s + (((GenderContext)_localctx).c!=null?_input.getText(((GenderContext)_localctx).c.start,((GenderContext)_localctx).c.stop):null);
					}
					((GenderContext)_localctx).l =  _localctx.s.length() + (((GenderContext)_localctx).tooManyComma!=null?_input.getText(((GenderContext)_localctx).tooManyComma.start,((GenderContext)_localctx).tooManyComma.stop):null).length();
					notifyErrorListeners(_localctx.l + "|Too many commas before '" + _localctx.s + "'");
				  
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

	public static class TooManyCommaContext extends ParserRuleContext {
		public TooManyCommaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tooManyComma; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterTooManyComma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitTooManyComma(this);
		}
	}

	public final TooManyCommaContext tooManyComma() throws RecognitionException {
		TooManyCommaContext _localctx = new TooManyCommaContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_tooManyComma);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(771);
			match(T__2);
			setState(773); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(772);
				match(T__2);
				}
				}
				setState(775); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 );
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

	public static class OrSexContext extends ParserRuleContext {
		public List<SexChrContext> sexChr() {
			return getRuleContexts(SexChrContext.class);
		}
		public SexChrContext sexChr(int i) {
			return getRuleContext(SexChrContext.class,i);
		}
		public OrSexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orSex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterOrSex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitOrSex(this);
		}
	}

	public final OrSexContext orSex() throws RecognitionException {
		OrSexContext _localctx = new OrSexContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_orSex);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(782);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5 || _la==T__6) {
				{
				{
				setState(777);
				_la = _input.LA(1);
				if ( !(_la==T__5 || _la==T__6) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(778);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__16) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(779);
				sexChr();
				}
				}
				setState(784);
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

	public static class IdemSpecialContext extends ParserRuleContext {
		public String s = "";
		public int l = 0;
		public IdemEventContext idemEvent;
		public TooManyCommaContext tooManyComma;
		public IdemEventContext idemEvent() {
			return getRuleContext(IdemEventContext.class,0);
		}
		public TooManyCommaContext tooManyComma() {
			return getRuleContext(TooManyCommaContext.class,0);
		}
		public IdemSpecialContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idemSpecial; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterIdemSpecial(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitIdemSpecial(this);
		}
	}

	public final IdemSpecialContext idemSpecial() throws RecognitionException {
		IdemSpecialContext _localctx = new IdemSpecialContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_idemSpecial);
		try {
			setState(794);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,113,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(785);
				match(T__2);
				setState(786);
				idemEvent();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(787);
				((IdemSpecialContext)_localctx).idemEvent = idemEvent();

					((IdemSpecialContext)_localctx).s =  (((IdemSpecialContext)_localctx).idemEvent!=null?_input.getText(((IdemSpecialContext)_localctx).idemEvent.start,((IdemSpecialContext)_localctx).idemEvent.stop):null);
					((IdemSpecialContext)_localctx).l =  _localctx.s.length();
					notifyErrorListeners(_localctx.l + "|Missing a comma before '" + _localctx.s + "'");
				  
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(790);
				((IdemSpecialContext)_localctx).tooManyComma = tooManyComma();
				setState(791);
				((IdemSpecialContext)_localctx).idemEvent = idemEvent();

					((IdemSpecialContext)_localctx).s =  (((IdemSpecialContext)_localctx).idemEvent!=null?_input.getText(((IdemSpecialContext)_localctx).idemEvent.start,((IdemSpecialContext)_localctx).idemEvent.stop):null);
					((IdemSpecialContext)_localctx).l =  _localctx.s.length() + (((IdemSpecialContext)_localctx).tooManyComma!=null?_input.getText(((IdemSpecialContext)_localctx).tooManyComma.start,((IdemSpecialContext)_localctx).tooManyComma.stop):null).length();
					notifyErrorListeners(_localctx.l + "|Too many commas before '" + _localctx.s + "'");
				  
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

	public static class SlSpecialContext extends ParserRuleContext {
		public String s = "";
		public int l = 0;
		public SlEventContext slEvent;
		public TooManyCommaContext tooManyComma;
		public SlEventContext slEvent() {
			return getRuleContext(SlEventContext.class,0);
		}
		public TooManyCommaContext tooManyComma() {
			return getRuleContext(TooManyCommaContext.class,0);
		}
		public SlSpecialContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_slSpecial; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterSlSpecial(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitSlSpecial(this);
		}
	}

	public final SlSpecialContext slSpecial() throws RecognitionException {
		SlSpecialContext _localctx = new SlSpecialContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_slSpecial);
		try {
			setState(805);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,114,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(796);
				match(T__2);
				setState(797);
				slEvent();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(798);
				((SlSpecialContext)_localctx).slEvent = slEvent();

					((SlSpecialContext)_localctx).s =  (((SlSpecialContext)_localctx).slEvent!=null?_input.getText(((SlSpecialContext)_localctx).slEvent.start,((SlSpecialContext)_localctx).slEvent.stop):null);
				 	((SlSpecialContext)_localctx).l =  _localctx.s.length();
					notifyErrorListeners(_localctx.l + "|Missing a comma before '" + _localctx.s + "'");
				  
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(801);
				((SlSpecialContext)_localctx).tooManyComma = tooManyComma();
				setState(802);
				((SlSpecialContext)_localctx).slEvent = slEvent();

					((SlSpecialContext)_localctx).s =  (((SlSpecialContext)_localctx).slEvent!=null?_input.getText(((SlSpecialContext)_localctx).slEvent.start,((SlSpecialContext)_localctx).slEvent.stop):null);
					((SlSpecialContext)_localctx).l =  _localctx.s.length() + (((SlSpecialContext)_localctx).tooManyComma!=null?_input.getText(((SlSpecialContext)_localctx).tooManyComma.start,((SlSpecialContext)_localctx).tooManyComma.stop):null).length();
					notifyErrorListeners(_localctx.l + "|Too many commas before '" + _localctx.s + "'");
				  
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

	public static class SdlSpecialContext extends ParserRuleContext {
		public String s = "";
		public int l = 0;
		public SdlEventContext sdlEvent;
		public TooManyCommaContext tooManyComma;
		public SdlEventContext sdlEvent() {
			return getRuleContext(SdlEventContext.class,0);
		}
		public TooManyCommaContext tooManyComma() {
			return getRuleContext(TooManyCommaContext.class,0);
		}
		public SdlSpecialContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sdlSpecial; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterSdlSpecial(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitSdlSpecial(this);
		}
	}

	public final SdlSpecialContext sdlSpecial() throws RecognitionException {
		SdlSpecialContext _localctx = new SdlSpecialContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_sdlSpecial);
		try {
			setState(816);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,115,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(807);
				match(T__2);
				setState(808);
				sdlEvent();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(809);
				((SdlSpecialContext)_localctx).sdlEvent = sdlEvent();

					((SdlSpecialContext)_localctx).s =  (((SdlSpecialContext)_localctx).sdlEvent!=null?_input.getText(((SdlSpecialContext)_localctx).sdlEvent.start,((SdlSpecialContext)_localctx).sdlEvent.stop):null);
				 	((SdlSpecialContext)_localctx).l =  _localctx.s.length();
					notifyErrorListeners(_localctx.l + "|Missing a comma before '" + _localctx.s + "'");
				  
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(812);
				((SdlSpecialContext)_localctx).tooManyComma = tooManyComma();
				setState(813);
				((SdlSpecialContext)_localctx).sdlEvent = sdlEvent();

					((SdlSpecialContext)_localctx).s =  (((SdlSpecialContext)_localctx).sdlEvent!=null?_input.getText(((SdlSpecialContext)_localctx).sdlEvent.start,((SdlSpecialContext)_localctx).sdlEvent.stop):null);
					((SdlSpecialContext)_localctx).l =  _localctx.s.length() + (((SdlSpecialContext)_localctx).tooManyComma!=null?_input.getText(((SdlSpecialContext)_localctx).tooManyComma.start,((SdlSpecialContext)_localctx).tooManyComma.stop):null).length();
					notifyErrorListeners(_localctx.l + "|Too many commas before '" + _localctx.s + "'");
				  
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

	public static class RegularEventContext extends ParserRuleContext {
		public String s = "";
		public int l = 0;
		public RegEventContext regEvent;
		public TooManyCommaContext tooManyComma;
		public RegEventContext regEvent() {
			return getRuleContext(RegEventContext.class,0);
		}
		public TooManyCommaContext tooManyComma() {
			return getRuleContext(TooManyCommaContext.class,0);
		}
		public RegularEventContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_regularEvent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterRegularEvent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitRegularEvent(this);
		}
	}

	public final RegularEventContext regularEvent() throws RecognitionException {
		RegularEventContext _localctx = new RegularEventContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_regularEvent);
		try {
			setState(827);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,116,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(818);
				match(T__2);
				setState(819);
				regEvent();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(820);
				((RegularEventContext)_localctx).regEvent = regEvent();

					((RegularEventContext)_localctx).s =  (((RegularEventContext)_localctx).regEvent!=null?_input.getText(((RegularEventContext)_localctx).regEvent.start,((RegularEventContext)_localctx).regEvent.stop):null);
				 	((RegularEventContext)_localctx).l =  _localctx.s.length();
					notifyErrorListeners(_localctx.l + "|Missing a comma before '" + _localctx.s + "'");
				  
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(823);
				((RegularEventContext)_localctx).tooManyComma = tooManyComma();
				setState(824);
				((RegularEventContext)_localctx).regEvent = regEvent();

					((RegularEventContext)_localctx).s =  (((RegularEventContext)_localctx).regEvent!=null?_input.getText(((RegularEventContext)_localctx).regEvent.start,((RegularEventContext)_localctx).regEvent.stop):null);
					((RegularEventContext)_localctx).l =  _localctx.s.length() + (((RegularEventContext)_localctx).tooManyComma!=null?_input.getText(((RegularEventContext)_localctx).tooManyComma.start,((RegularEventContext)_localctx).tooManyComma.stop):null).length();
					notifyErrorListeners(_localctx.l + "|Too many commas before '" + _localctx.s + "'");
				  
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

	public static class IncorrectCommaContext extends ParserRuleContext {
		public IncorrectCommaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_incorrectComma; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterIncorrectComma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitIncorrectComma(this);
		}
	}

	public final IncorrectCommaContext incorrectComma() throws RecognitionException {
		IncorrectCommaContext _localctx = new IncorrectCommaContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_incorrectComma);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(830); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(829);
				match(T__2);
				}
				}
				setState(832); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 );
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

	public static class IdemEventContext extends ParserRuleContext {
		public String s = "";
		public int l = 0;
		public IncorrectCommaContext incorrectComma;
		public MultiplicationContext multiplication;
		public MultiplicationContext multiplication() {
			return getRuleContext(MultiplicationContext.class,0);
		}
		public IncorrectCommaContext incorrectComma() {
			return getRuleContext(IncorrectCommaContext.class,0);
		}
		public IdemEventContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idemEvent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterIdemEvent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitIdemEvent(this);
		}
	}

	public final IdemEventContext idemEvent() throws RecognitionException {
		IdemEventContext _localctx = new IdemEventContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_idemEvent);
		int _la;
		try {
			setState(849);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,119,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(834);
				_la = _input.LA(1);
				if ( !(_la==T__25 || _la==T__26) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(835);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(836);
				_la = _input.LA(1);
				if ( !(_la==T__19 || _la==T__20) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(837);
				_la = _input.LA(1);
				if ( !(_la==T__17 || _la==T__18) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(839);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__50 || _la==T__51) {
					{
					setState(838);
					multiplication();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(841);
				_la = _input.LA(1);
				if ( !(_la==T__25 || _la==T__26) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(842);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(843);
				_la = _input.LA(1);
				if ( !(_la==T__19 || _la==T__20) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(844);
				_la = _input.LA(1);
				if ( !(_la==T__17 || _la==T__18) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(845);
				((IdemEventContext)_localctx).incorrectComma = incorrectComma();
				setState(846);
				((IdemEventContext)_localctx).multiplication = multiplication();

				  	((IdemEventContext)_localctx).s =  (((IdemEventContext)_localctx).multiplication!=null?_input.getText(((IdemEventContext)_localctx).multiplication.start,((IdemEventContext)_localctx).multiplication.stop):null);
				  	((IdemEventContext)_localctx).l =  _localctx.s.length() + 4 + (((IdemEventContext)_localctx).incorrectComma!=null?_input.getText(((IdemEventContext)_localctx).incorrectComma.start,((IdemEventContext)_localctx).incorrectComma.stop):null).length();
				  	notifyErrorListeners(_localctx.l + "|No comma should be used between 'idem' and '" + _localctx.s + "'");
				  
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

	public static class SlEventContext extends ParserRuleContext {
		public String s1 = "";
		public String s2 = "";
		public int l = 0;
		public SlMarkContext slMark;
		public IncorrectCommaContext incorrectComma;
		public MultiplicationContext multiplication;
		public SlMarkContext slMark() {
			return getRuleContext(SlMarkContext.class,0);
		}
		public MultiplicationContext multiplication() {
			return getRuleContext(MultiplicationContext.class,0);
		}
		public IncorrectCommaContext incorrectComma() {
			return getRuleContext(IncorrectCommaContext.class,0);
		}
		public SlEventContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_slEvent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterSlEvent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitSlEvent(this);
		}
	}

	public final SlEventContext slEvent() throws RecognitionException {
		SlEventContext _localctx = new SlEventContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_slEvent);
		int _la;
		try {
			setState(860);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,121,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(851);
				slMark();
				setState(853);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__50 || _la==T__51) {
					{
					setState(852);
					multiplication();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(855);
				((SlEventContext)_localctx).slMark = slMark();
				setState(856);
				((SlEventContext)_localctx).incorrectComma = incorrectComma();
				setState(857);
				((SlEventContext)_localctx).multiplication = multiplication();

				  	((SlEventContext)_localctx).s1 =  (((SlEventContext)_localctx).slMark!=null?_input.getText(((SlEventContext)_localctx).slMark.start,((SlEventContext)_localctx).slMark.stop):null);
				  	((SlEventContext)_localctx).s2 =  (((SlEventContext)_localctx).multiplication!=null?_input.getText(((SlEventContext)_localctx).multiplication.start,((SlEventContext)_localctx).multiplication.stop):null);
				  	((SlEventContext)_localctx).l =  _localctx.s1.length() + _localctx.s2.length() + (((SlEventContext)_localctx).incorrectComma!=null?_input.getText(((SlEventContext)_localctx).incorrectComma.start,((SlEventContext)_localctx).incorrectComma.stop):null).length();
				  	notifyErrorListeners(_localctx.l + "|No comma should be used between '" + _localctx.s1 + "' and '" + _localctx.s2 + "'");
				  
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

	public static class SlMarkContext extends ParserRuleContext {
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public SlMarkContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_slMark; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterSlMark(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitSlMark(this);
		}
	}

	public final SlMarkContext slMark() throws RecognitionException {
		SlMarkContext _localctx = new SlMarkContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_slMark);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(862);
			_la = _input.LA(1);
			if ( !(_la==T__29 || _la==T__30) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(863);
			_la = _input.LA(1);
			if ( !(_la==T__9 || _la==T__10) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(865);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,122,_ctx) ) {
			case 1:
				{
				setState(864);
				integer();
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

	public static class SdlEventContext extends ParserRuleContext {
		public String s1 = "";
		public String s2 = "";
		public int l = 0;
		public SdlMarkContext sdlMark;
		public IncorrectCommaContext incorrectComma;
		public MultiplicationContext multiplication;
		public SdlMarkContext sdlMark() {
			return getRuleContext(SdlMarkContext.class,0);
		}
		public MultiplicationContext multiplication() {
			return getRuleContext(MultiplicationContext.class,0);
		}
		public IncorrectCommaContext incorrectComma() {
			return getRuleContext(IncorrectCommaContext.class,0);
		}
		public SdlEventContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sdlEvent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterSdlEvent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitSdlEvent(this);
		}
	}

	public final SdlEventContext sdlEvent() throws RecognitionException {
		SdlEventContext _localctx = new SdlEventContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_sdlEvent);
		int _la;
		try {
			setState(876);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,124,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(867);
				sdlMark();
				setState(869);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__50 || _la==T__51) {
					{
					setState(868);
					multiplication();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(871);
				((SdlEventContext)_localctx).sdlMark = sdlMark();
				setState(872);
				((SdlEventContext)_localctx).incorrectComma = incorrectComma();
				setState(873);
				((SdlEventContext)_localctx).multiplication = multiplication();

				  	((SdlEventContext)_localctx).s1 =  (((SdlEventContext)_localctx).sdlMark!=null?_input.getText(((SdlEventContext)_localctx).sdlMark.start,((SdlEventContext)_localctx).sdlMark.stop):null);
				  	((SdlEventContext)_localctx).s2 =  (((SdlEventContext)_localctx).multiplication!=null?_input.getText(((SdlEventContext)_localctx).multiplication.start,((SdlEventContext)_localctx).multiplication.stop):null);
				  	((SdlEventContext)_localctx).l =  _localctx.s1.length() + _localctx.s2.length() + (((SdlEventContext)_localctx).incorrectComma!=null?_input.getText(((SdlEventContext)_localctx).incorrectComma.start,((SdlEventContext)_localctx).incorrectComma.stop):null).length();
				  	notifyErrorListeners(_localctx.l + "|No comma should be used between '" + _localctx.s1 + "' and '" + _localctx.s2 + "'");
				  
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

	public static class SdlMarkContext extends ParserRuleContext {
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public SdlMarkContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sdlMark; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterSdlMark(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitSdlMark(this);
		}
	}

	public final SdlMarkContext sdlMark() throws RecognitionException {
		SdlMarkContext _localctx = new SdlMarkContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_sdlMark);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(878);
			_la = _input.LA(1);
			if ( !(_la==T__29 || _la==T__30) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(879);
			_la = _input.LA(1);
			if ( !(_la==T__27 || _la==T__28) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(880);
			_la = _input.LA(1);
			if ( !(_la==T__9 || _la==T__10) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(882);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,125,_ctx) ) {
			case 1:
				{
				setState(881);
				integer();
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

	public static class RegEventContext extends ParserRuleContext {
		public RegEventContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_regEvent; }
	 
		public RegEventContext() { }
		public void copyFrom(RegEventContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MultipleInterpretationRegularEventContext extends RegEventContext {
		public List<RegEventTypeContext> regEventType() {
			return getRuleContexts(RegEventTypeContext.class);
		}
		public RegEventTypeContext regEventType(int i) {
			return getRuleContext(RegEventTypeContext.class,i);
		}
		public MultipleInterpretationRegularEventContext(RegEventContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterMultipleInterpretationRegularEvent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitMultipleInterpretationRegularEvent(this);
		}
	}
	public static class SimpleRegularEventContext extends RegEventContext {
		public RegEventTypeContext regEventType() {
			return getRuleContext(RegEventTypeContext.class,0);
		}
		public SimpleRegularEventContext(RegEventContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterSimpleRegularEvent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitSimpleRegularEvent(this);
		}
	}

	public final RegEventContext regEvent() throws RecognitionException {
		RegEventContext _localctx = new RegEventContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_regEvent);
		int _la;
		try {
			setState(893);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,127,_ctx) ) {
			case 1:
				_localctx = new MultipleInterpretationRegularEventContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(884);
				regEventType();
				setState(888); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(885);
					_la = _input.LA(1);
					if ( !(_la==T__5 || _la==T__6) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(886);
					_la = _input.LA(1);
					if ( !(_la==T__15 || _la==T__16) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(887);
					regEventType();
					}
					}
					setState(890); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__5 || _la==T__6 );
				}
				break;
			case 2:
				_localctx = new SimpleRegularEventContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(892);
				regEventType();
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

	public static class RegEventTypeContext extends ParserRuleContext {
		public RegEventTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_regEventType; }
	 
		public RegEventTypeContext() { }
		public void copyFrom(RegEventTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class GainLossChrEventContext extends RegEventTypeContext {
		public GainLossChrContext gainLossChr() {
			return getRuleContext(GainLossChrContext.class,0);
		}
		public GainLossChrEventContext(RegEventTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterGainLossChrEvent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitGainLossChrEvent(this);
		}
	}
	public static class UndecodedEventContext extends RegEventTypeContext {
		public UndecodedContext undecoded() {
			return getRuleContext(UndecodedContext.class,0);
		}
		public UndecodedEventContext(RegEventTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterUndecodedEvent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitUndecodedEvent(this);
		}
	}
	public static class AberrationEventContext extends RegEventTypeContext {
		public AberrationContext aberration() {
			return getRuleContext(AberrationContext.class,0);
		}
		public AberrationEventContext(RegEventTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterAberrationEvent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitAberrationEvent(this);
		}
	}
	public static class UndecodedSpecialEventContext extends RegEventTypeContext {
		public PrefixPlusContext prefixPlus() {
			return getRuleContext(PrefixPlusContext.class,0);
		}
		public List<SuffixContext> suffix() {
			return getRuleContexts(SuffixContext.class);
		}
		public SuffixContext suffix(int i) {
			return getRuleContext(SuffixContext.class,i);
		}
		public TerminalNode QUES() { return getToken(KaryotypeParser.QUES, 0); }
		public DminNumContext dminNum() {
			return getRuleContext(DminNumContext.class,0);
		}
		public UndecodedSpecialEventContext(RegEventTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterUndecodedSpecialEvent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitUndecodedSpecialEvent(this);
		}
	}

	public final RegEventTypeContext regEventType() throws RecognitionException {
		RegEventTypeContext _localctx = new RegEventTypeContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_regEventType);
		int _la;
		try {
			setState(919);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,133,_ctx) ) {
			case 1:
				_localctx = new UndecodedSpecialEventContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(896);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PLUS || _la==QUES) {
					{
					setState(895);
					prefixPlus();
					}
				}

				setState(898);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__16) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(900);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__17) | (1L << T__18) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__37) | (1L << T__38) | (1L << T__50) | (1L << T__51))) != 0)) {
					{
					setState(899);
					suffix();
					}
				}

				setState(902);
				match(T__2);
				setState(904);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==QUES) {
					{
					setState(903);
					match(QUES);
					}
				}

				setState(907);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__49) | (1L << APPROX) | (1L << POSITIVEDIGIT) | (1L << MINUS))) != 0)) {
					{
					setState(906);
					dminNum();
					}
				}

				setState(909);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(910);
				_la = _input.LA(1);
				if ( !(_la==T__17 || _la==T__18) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(911);
				_la = _input.LA(1);
				if ( !(_la==T__25 || _la==T__26) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(912);
				_la = _input.LA(1);
				if ( !(_la==T__3 || _la==T__4) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(914);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,132,_ctx) ) {
				case 1:
					{
					setState(913);
					suffix();
					}
					break;
				}
				}
				break;
			case 2:
				_localctx = new AberrationEventContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(916);
				aberration();
				}
				break;
			case 3:
				_localctx = new UndecodedEventContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(917);
				undecoded();
				}
				break;
			case 4:
				_localctx = new GainLossChrEventContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(918);
				gainLossChr();
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

	public static class GainLossChrContext extends ParserRuleContext {
		public String s = "";
		public int l = 0;
		public GainLossChrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gainLossChr; }
	 
		public GainLossChrContext() { }
		public void copyFrom(GainLossChrContext ctx) {
			super.copyFrom(ctx);
			this.s = ctx.s;
			this.l = ctx.l;
		}
	}
	public static class GainLossChrErrorContext extends GainLossChrContext {
		public UndeterminedPrefixContext undeterminedPrefix;
		public GainChrContext gainChr;
		public CContext c;
		public InhContext inh;
		public PrefixPlusContext prefixPlus;
		public PrefixMinusContext prefixMinus;
		public UndeterminedPrefixContext undeterminedPrefix() {
			return getRuleContext(UndeterminedPrefixContext.class,0);
		}
		public GainChrContext gainChr() {
			return getRuleContext(GainChrContext.class,0);
		}
		public CContext c() {
			return getRuleContext(CContext.class,0);
		}
		public InhContext inh() {
			return getRuleContext(InhContext.class,0);
		}
		public PrefixPlusContext prefixPlus() {
			return getRuleContext(PrefixPlusContext.class,0);
		}
		public PrefixMinusContext prefixMinus() {
			return getRuleContext(PrefixMinusContext.class,0);
		}
		public GainLossChrErrorContext(GainLossChrContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterGainLossChrError(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitGainLossChrError(this);
		}
	}
	public static class GainChrEventContext extends GainLossChrContext {
		public GainChrContext gainChr;
		public PrefixPlusContext prefixPlus() {
			return getRuleContext(PrefixPlusContext.class,0);
		}
		public GainChrContext gainChr() {
			return getRuleContext(GainChrContext.class,0);
		}
		public CContext c() {
			return getRuleContext(CContext.class,0);
		}
		public InhContext inh() {
			return getRuleContext(InhContext.class,0);
		}
		public GainChrEventContext(GainLossChrContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterGainChrEvent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitGainChrEvent(this);
		}
	}
	public static class LossChrEventContext extends GainLossChrContext {
		public LossChrContext lossChr;
		public PrefixMinusContext prefixMinus() {
			return getRuleContext(PrefixMinusContext.class,0);
		}
		public LossChrContext lossChr() {
			return getRuleContext(LossChrContext.class,0);
		}
		public CContext c() {
			return getRuleContext(CContext.class,0);
		}
		public InhContext inh() {
			return getRuleContext(InhContext.class,0);
		}
		public LossChrEventContext(GainLossChrContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterLossChrEvent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitLossChrEvent(this);
		}
	}

	public final GainLossChrContext gainLossChr() throws RecognitionException {
		GainLossChrContext _localctx = new GainLossChrContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_gainLossChr);
		try {
			setState(953);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,139,_ctx) ) {
			case 1:
				_localctx = new GainChrEventContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(921);
				prefixPlus();
				setState(922);
				((GainChrEventContext)_localctx).gainChr = gainChr();
				setState(925);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,134,_ctx) ) {
				case 1:
					{
					setState(923);
					c();
					}
					break;
				case 2:
					{
					setState(924);
					inh();
					}
					break;
				}
				 
				  	((GainChrEventContext)_localctx).s =  (((GainChrEventContext)_localctx).gainChr!=null?_input.getText(((GainChrEventContext)_localctx).gainChr.start,((GainChrEventContext)_localctx).gainChr.stop):null).toUpperCase(); 
				  	if (_localctx.s.contains("X")) {sexChrList.add("X");}
				  	if (_localctx.s.contains("Y")) {sexChrList.add("Y");}
				  
				}
				break;
			case 2:
				_localctx = new LossChrEventContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(929);
				prefixMinus();
				setState(930);
				((LossChrEventContext)_localctx).lossChr = lossChr();
				setState(933);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,135,_ctx) ) {
				case 1:
					{
					setState(931);
					c();
					}
					break;
				case 2:
					{
					setState(932);
					inh();
					}
					break;
				}
				 
				  	((LossChrEventContext)_localctx).s =  (((LossChrEventContext)_localctx).lossChr!=null?_input.getText(((LossChrEventContext)_localctx).lossChr.start,((LossChrEventContext)_localctx).lossChr.stop):null).toUpperCase(); 
				  	if (_localctx.s.contains("X")) {sexChrList.add("X");}
				  	if (_localctx.s.contains("Y")) {sexChrList.add("Y");}
				  
				}
				break;
			case 3:
				_localctx = new GainLossChrErrorContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(937);
				((GainLossChrErrorContext)_localctx).undeterminedPrefix = undeterminedPrefix();
				setState(939);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,136,_ctx) ) {
				case 1:
					{
					setState(938);
					((GainLossChrErrorContext)_localctx).gainChr = gainChr();
					}
					break;
				}
				setState(943);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,137,_ctx) ) {
				case 1:
					{
					setState(941);
					((GainLossChrErrorContext)_localctx).c = c();
					}
					break;
				case 2:
					{
					setState(942);
					((GainLossChrErrorContext)_localctx).inh = inh();
					}
					break;
				}

				  	((GainLossChrErrorContext)_localctx).s =  (((GainLossChrErrorContext)_localctx).gainChr!=null?_input.getText(((GainLossChrErrorContext)_localctx).gainChr.start,((GainLossChrErrorContext)_localctx).gainChr.stop):null);
				  	((GainLossChrErrorContext)_localctx).l =  (((GainLossChrErrorContext)_localctx).undeterminedPrefix!=null?_input.getText(((GainLossChrErrorContext)_localctx).undeterminedPrefix.start,((GainLossChrErrorContext)_localctx).undeterminedPrefix.stop):null).length(); 
				  	if ((((GainLossChrErrorContext)_localctx).c!=null?_input.getText(((GainLossChrErrorContext)_localctx).c.start,((GainLossChrErrorContext)_localctx).c.stop):null) != null) {
				  		((GainLossChrErrorContext)_localctx).l =  _localctx.l + (((GainLossChrErrorContext)_localctx).c!=null?_input.getText(((GainLossChrErrorContext)_localctx).c.start,((GainLossChrErrorContext)_localctx).c.stop):null).length(); 
				  	} 	
				  	if ((((GainLossChrErrorContext)_localctx).inh!=null?_input.getText(((GainLossChrErrorContext)_localctx).inh.start,((GainLossChrErrorContext)_localctx).inh.stop):null) != null) {
				  		((GainLossChrErrorContext)_localctx).l =  _localctx.l + (((GainLossChrErrorContext)_localctx).inh!=null?_input.getText(((GainLossChrErrorContext)_localctx).inh.start,((GainLossChrErrorContext)_localctx).inh.stop):null).length(); 
				  	}
				 	if (_localctx.s != null) {
				 		((GainLossChrErrorContext)_localctx).l =  _localctx.l + _localctx.s.length();
				 		notifyErrorListeners(_localctx.l + "|Cannot use both '-' and '+' for the gain/loss aberration of chromosome '" + (((GainLossChrErrorContext)_localctx).gainChr!=null?_input.getText(((GainLossChrErrorContext)_localctx).gainChr.start,((GainLossChrErrorContext)_localctx).gainChr.stop):null) + "'");
				 	} else {
				 		notifyErrorListeners(_localctx.l + "|Cannot use both '-' and '+' for the gain/loss aberration and missing chromosome information"); 
				 	} 		          	            	
				  
				}
				break;
			case 4:
				_localctx = new GainLossChrErrorContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(949);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,138,_ctx) ) {
				case 1:
					{
					setState(947);
					((GainLossChrErrorContext)_localctx).prefixPlus = prefixPlus();
					}
					break;
				case 2:
					{
					setState(948);
					((GainLossChrErrorContext)_localctx).prefixMinus = prefixMinus();
					}
					break;
				}

				  	if ((((GainLossChrErrorContext)_localctx).prefixPlus!=null?_input.getText(((GainLossChrErrorContext)_localctx).prefixPlus.start,((GainLossChrErrorContext)_localctx).prefixPlus.stop):null) != null) {
				  		((GainLossChrErrorContext)_localctx).l =  (((GainLossChrErrorContext)_localctx).prefixPlus!=null?_input.getText(((GainLossChrErrorContext)_localctx).prefixPlus.start,((GainLossChrErrorContext)_localctx).prefixPlus.stop):null).length();
				  	}
				  	if ((((GainLossChrErrorContext)_localctx).prefixMinus!=null?_input.getText(((GainLossChrErrorContext)_localctx).prefixMinus.start,((GainLossChrErrorContext)_localctx).prefixMinus.stop):null) != null) {
				  		((GainLossChrErrorContext)_localctx).l =  (((GainLossChrErrorContext)_localctx).prefixMinus!=null?_input.getText(((GainLossChrErrorContext)_localctx).prefixMinus.start,((GainLossChrErrorContext)_localctx).prefixMinus.stop):null).length();
				  	}
				  	notifyErrorListeners(_localctx.l + "|Missing chromosome information of the gain/loss aberration");
				  
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

	public static class UndecodedContext extends ParserRuleContext {
		public PrefixPlusContext prefixPlus() {
			return getRuleContext(PrefixPlusContext.class,0);
		}
		public SuffixContext suffix() {
			return getRuleContext(SuffixContext.class,0);
		}
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public MarNumContext marNum() {
			return getRuleContext(MarNumContext.class,0);
		}
		public TerminalNode QUES() { return getToken(KaryotypeParser.QUES, 0); }
		public DminNumContext dminNum() {
			return getRuleContext(DminNumContext.class,0);
		}
		public UndecodedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_undecoded; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterUndecoded(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitUndecoded(this);
		}
	}

	public final UndecodedContext undecoded() throws RecognitionException {
		UndecodedContext _localctx = new UndecodedContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_undecoded);
		int _la;
		try {
			setState(996);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,148,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(956);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PLUS || _la==QUES) {
					{
					setState(955);
					prefixPlus();
					}
				}

				setState(958);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__16) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(960);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,141,_ctx) ) {
				case 1:
					{
					setState(959);
					suffix();
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(962);
				prefixPlus();
				setState(963);
				_la = _input.LA(1);
				if ( !(_la==T__17 || _la==T__18) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(964);
				_la = _input.LA(1);
				if ( !(_la==T__11 || _la==T__12) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(965);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__16) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(966);
				integer();
				setState(968);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,142,_ctx) ) {
				case 1:
					{
					setState(967);
					suffix();
					}
					break;
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(970);
				prefixPlus();
				setState(972);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__49 || _la==POSITIVEDIGIT) {
					{
					setState(971);
					marNum();
					}
				}

				setState(974);
				_la = _input.LA(1);
				if ( !(_la==T__17 || _la==T__18) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(975);
				_la = _input.LA(1);
				if ( !(_la==T__11 || _la==T__12) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(976);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__16) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(978);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,144,_ctx) ) {
				case 1:
					{
					setState(977);
					suffix();
					}
					break;
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(981);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==QUES) {
					{
					setState(980);
					match(QUES);
					}
				}

				setState(984);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__49) | (1L << APPROX) | (1L << POSITIVEDIGIT) | (1L << MINUS))) != 0)) {
					{
					setState(983);
					dminNum();
					}
				}

				setState(986);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(987);
				_la = _input.LA(1);
				if ( !(_la==T__17 || _la==T__18) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(988);
				_la = _input.LA(1);
				if ( !(_la==T__25 || _la==T__26) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(989);
				_la = _input.LA(1);
				if ( !(_la==T__3 || _la==T__4) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(991);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,147,_ctx) ) {
				case 1:
					{
					setState(990);
					suffix();
					}
					break;
				}
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(993);
				_la = _input.LA(1);
				if ( !(_la==T__25 || _la==T__26) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(994);
				_la = _input.LA(1);
				if ( !(_la==T__3 || _la==T__4) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(995);
				_la = _input.LA(1);
				if ( !(_la==T__7 || _la==T__8) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
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

	public static class AberrationContext extends ParserRuleContext {
		public String s = "";
		public int l = 0;
		public AberrationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aberration; }
	 
		public AberrationContext() { }
		public void copyFrom(AberrationContext ctx) {
			super.copyFrom(ctx);
			this.s = ctx.s;
			this.l = ctx.l;
		}
	}
	public static class BasicAberrationContext extends AberrationContext {
		public ChrListContext chrList;
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ChrListContext chrList() {
			return getRuleContext(ChrListContext.class,0);
		}
		public PrefixContext prefix() {
			return getRuleContext(PrefixContext.class,0);
		}
		public MosChiContext mosChi() {
			return getRuleContext(MosChiContext.class,0);
		}
		public BreakpointsListContext breakpointsList() {
			return getRuleContext(BreakpointsListContext.class,0);
		}
		public SuffixContext suffix() {
			return getRuleContext(SuffixContext.class,0);
		}
		public BasicAberrationContext(AberrationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterBasicAberration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitBasicAberration(this);
		}
	}
	public static class DerAberrationContext extends AberrationContext {
		public DerChrListContext derChrList;
		public DerIdContext derId() {
			return getRuleContext(DerIdContext.class,0);
		}
		public DerChrListContext derChrList() {
			return getRuleContext(DerChrListContext.class,0);
		}
		public PrefixContext prefix() {
			return getRuleContext(PrefixContext.class,0);
		}
		public MosChiContext mosChi() {
			return getRuleContext(MosChiContext.class,0);
		}
		public DerBreakpointsListContext derBreakpointsList() {
			return getRuleContext(DerBreakpointsListContext.class,0);
		}
		public List<RearrangementElementContext> rearrangementElement() {
			return getRuleContexts(RearrangementElementContext.class);
		}
		public RearrangementElementContext rearrangementElement(int i) {
			return getRuleContext(RearrangementElementContext.class,i);
		}
		public SuffixContext suffix() {
			return getRuleContext(SuffixContext.class,0);
		}
		public DerAberrationContext(AberrationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterDerAberration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitDerAberration(this);
		}
	}
	public static class DerAberrationErrorContext extends AberrationContext {
		public PrefixContext prefix;
		public MosChiContext mosChi;
		public DerIdContext derId;
		public Token comma;
		public DerChrListContext derChrList;
		public DerBreakpointsListContext derBreakpointsList;
		public RearrangementElementContext r;
		public SuffixContext suffix;
		public DerIdContext derId() {
			return getRuleContext(DerIdContext.class,0);
		}
		public DerChrListContext derChrList() {
			return getRuleContext(DerChrListContext.class,0);
		}
		public PrefixContext prefix() {
			return getRuleContext(PrefixContext.class,0);
		}
		public MosChiContext mosChi() {
			return getRuleContext(MosChiContext.class,0);
		}
		public DerBreakpointsListContext derBreakpointsList() {
			return getRuleContext(DerBreakpointsListContext.class,0);
		}
		public SuffixContext suffix() {
			return getRuleContext(SuffixContext.class,0);
		}
		public List<RearrangementElementContext> rearrangementElement() {
			return getRuleContexts(RearrangementElementContext.class);
		}
		public RearrangementElementContext rearrangementElement(int i) {
			return getRuleContext(RearrangementElementContext.class,i);
		}
		public DerAberrationErrorContext(AberrationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterDerAberrationError(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitDerAberrationError(this);
		}
	}
	public static class BasicAberrationErrorContext extends AberrationContext {
		public PrefixContext prefix;
		public MosChiContext mosChi;
		public IdContext id;
		public Token comma;
		public ChrListContext chrList;
		public BreakpointsListContext breakpointsList;
		public SuffixContext suffix;
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ChrListContext chrList() {
			return getRuleContext(ChrListContext.class,0);
		}
		public PrefixContext prefix() {
			return getRuleContext(PrefixContext.class,0);
		}
		public MosChiContext mosChi() {
			return getRuleContext(MosChiContext.class,0);
		}
		public BreakpointsListContext breakpointsList() {
			return getRuleContext(BreakpointsListContext.class,0);
		}
		public SuffixContext suffix() {
			return getRuleContext(SuffixContext.class,0);
		}
		public BasicAberrationErrorContext(AberrationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterBasicAberrationError(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitBasicAberrationError(this);
		}
	}
	public static class UncertainBasicAberrationContext extends AberrationContext {
		public UncertainChrContext uncertainChr;
		public IdUncertainChrContext idUncertainChr() {
			return getRuleContext(IdUncertainChrContext.class,0);
		}
		public UncertainChrContext uncertainChr() {
			return getRuleContext(UncertainChrContext.class,0);
		}
		public PrefixContext prefix() {
			return getRuleContext(PrefixContext.class,0);
		}
		public MosChiContext mosChi() {
			return getRuleContext(MosChiContext.class,0);
		}
		public SuffixContext suffix() {
			return getRuleContext(SuffixContext.class,0);
		}
		public UncertainBasicAberrationContext(AberrationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterUncertainBasicAberration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitUncertainBasicAberration(this);
		}
	}

	public final AberrationContext aberration() throws RecognitionException {
		AberrationContext _localctx = new AberrationContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_aberration);
		int _la;
		try {
			int _alt;
			setState(1097);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,172,_ctx) ) {
			case 1:
				_localctx = new DerAberrationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(999);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << PLUS) | (1L << QUES))) != 0)) {
					{
					setState(998);
					prefix();
					}
				}

				setState(1002);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__17) | (1L << T__18))) != 0)) {
					{
					setState(1001);
					mosChi();
					}
				}

				setState(1004);
				derId();
				setState(1005);
				((DerAberrationContext)_localctx).derChrList = derChrList();
				setState(1007);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,151,_ctx) ) {
				case 1:
					{
					setState(1006);
					derBreakpointsList();
					}
					break;
				}
				setState(1012);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,152,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1009);
						rearrangementElement();
						}
						} 
					}
					setState(1014);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,152,_ctx);
				}
				setState(1016);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,153,_ctx) ) {
				case 1:
					{
					setState(1015);
					suffix();
					}
					break;
				}

				  	((DerAberrationContext)_localctx).s =  (((DerAberrationContext)_localctx).derChrList!=null?_input.getText(((DerAberrationContext)_localctx).derChrList.start,((DerAberrationContext)_localctx).derChrList.stop):null).toUpperCase();
				  	if (_localctx.s.contains("X")) {sexChrList.add("X");}
				  	if (_localctx.s.contains("Y")) {sexChrList.add("Y");}
				  
				}
				break;
			case 2:
				_localctx = new BasicAberrationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(1021);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << PLUS) | (1L << QUES))) != 0)) {
					{
					setState(1020);
					prefix();
					}
				}

				setState(1024);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__17) | (1L << T__18))) != 0)) {
					{
					setState(1023);
					mosChi();
					}
				}

				setState(1026);
				id();
				setState(1027);
				((BasicAberrationContext)_localctx).chrList = chrList();
				setState(1029);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,156,_ctx) ) {
				case 1:
					{
					setState(1028);
					breakpointsList();
					}
					break;
				}
				setState(1032);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,157,_ctx) ) {
				case 1:
					{
					setState(1031);
					suffix();
					}
					break;
				}

				  	((BasicAberrationContext)_localctx).s =  (((BasicAberrationContext)_localctx).chrList!=null?_input.getText(((BasicAberrationContext)_localctx).chrList.start,((BasicAberrationContext)_localctx).chrList.stop):null).toUpperCase();
				  	if (_localctx.s.contains("X")) {sexChrList.add("X");}
				  	if (_localctx.s.contains("Y")) {sexChrList.add("Y");}
				  
				}
				break;
			case 3:
				_localctx = new UncertainBasicAberrationContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(1037);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << PLUS) | (1L << QUES))) != 0)) {
					{
					setState(1036);
					prefix();
					}
				}

				setState(1040);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__17) | (1L << T__18))) != 0)) {
					{
					setState(1039);
					mosChi();
					}
				}

				setState(1042);
				idUncertainChr();
				setState(1043);
				((UncertainBasicAberrationContext)_localctx).uncertainChr = uncertainChr();
				setState(1045);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,160,_ctx) ) {
				case 1:
					{
					setState(1044);
					suffix();
					}
					break;
				}

				  	((UncertainBasicAberrationContext)_localctx).s =  (((UncertainBasicAberrationContext)_localctx).uncertainChr!=null?_input.getText(((UncertainBasicAberrationContext)_localctx).uncertainChr.start,((UncertainBasicAberrationContext)_localctx).uncertainChr.stop):null).toUpperCase();
				  	if (_localctx.s.contains("X")) {sexChrList.add("X");}
				  	if (_localctx.s.contains("Y")) {sexChrList.add("Y");}
				  
				}
				break;
			case 4:
				_localctx = new DerAberrationErrorContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(1050);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << PLUS) | (1L << QUES))) != 0)) {
					{
					setState(1049);
					((DerAberrationErrorContext)_localctx).prefix = prefix();
					}
				}

				setState(1053);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__17) | (1L << T__18))) != 0)) {
					{
					setState(1052);
					((DerAberrationErrorContext)_localctx).mosChi = mosChi();
					}
				}

				setState(1055);
				((DerAberrationErrorContext)_localctx).derId = derId();
				setState(1057); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(1056);
					((DerAberrationErrorContext)_localctx).comma = match(T__2);
					}
					}
					setState(1059); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__2 );
				setState(1061);
				((DerAberrationErrorContext)_localctx).derChrList = derChrList();
				setState(1063);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,164,_ctx) ) {
				case 1:
					{
					setState(1062);
					((DerAberrationErrorContext)_localctx).derBreakpointsList = derBreakpointsList();
					}
					break;
				}
				setState(1068);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,165,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1065);
						((DerAberrationErrorContext)_localctx).r = rearrangementElement();
						}
						} 
					}
					setState(1070);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,165,_ctx);
				}
				setState(1072);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,166,_ctx) ) {
				case 1:
					{
					setState(1071);
					((DerAberrationErrorContext)_localctx).suffix = suffix();
					}
					break;
				}

				  	if ((((DerAberrationErrorContext)_localctx).prefix!=null?_input.getText(((DerAberrationErrorContext)_localctx).prefix.start,((DerAberrationErrorContext)_localctx).prefix.stop):null) != null) {
				  		((DerAberrationErrorContext)_localctx).l =  _localctx.l + (((DerAberrationErrorContext)_localctx).prefix!=null?_input.getText(((DerAberrationErrorContext)_localctx).prefix.start,((DerAberrationErrorContext)_localctx).prefix.stop):null).length(); 
				  	}
				  	if ((((DerAberrationErrorContext)_localctx).mosChi!=null?_input.getText(((DerAberrationErrorContext)_localctx).mosChi.start,((DerAberrationErrorContext)_localctx).mosChi.stop):null) != null) {
				  		((DerAberrationErrorContext)_localctx).l =  _localctx.l + (((DerAberrationErrorContext)_localctx).mosChi!=null?_input.getText(((DerAberrationErrorContext)_localctx).mosChi.start,((DerAberrationErrorContext)_localctx).mosChi.stop):null).length(); 
				  	}
				  	((DerAberrationErrorContext)_localctx).l =  _localctx.l + (((DerAberrationErrorContext)_localctx).derId!=null?_input.getText(((DerAberrationErrorContext)_localctx).derId.start,((DerAberrationErrorContext)_localctx).derId.stop):null).length(); 
				  	((DerAberrationErrorContext)_localctx).l =  _localctx.l + (((DerAberrationErrorContext)_localctx).comma!=null?((DerAberrationErrorContext)_localctx).comma.getText():null).length(); 
				  	((DerAberrationErrorContext)_localctx).l =  _localctx.l + (((DerAberrationErrorContext)_localctx).derChrList!=null?_input.getText(((DerAberrationErrorContext)_localctx).derChrList.start,((DerAberrationErrorContext)_localctx).derChrList.stop):null).length(); 
				  	if ((((DerAberrationErrorContext)_localctx).r!=null?_input.getText(((DerAberrationErrorContext)_localctx).r.start,((DerAberrationErrorContext)_localctx).r.stop):null) != null) {
				  		((DerAberrationErrorContext)_localctx).l =  _localctx.l + (((DerAberrationErrorContext)_localctx).r!=null?_input.getText(((DerAberrationErrorContext)_localctx).r.start,((DerAberrationErrorContext)_localctx).r.stop):null).length(); 
				  	}
				  	if ((((DerAberrationErrorContext)_localctx).suffix!=null?_input.getText(((DerAberrationErrorContext)_localctx).suffix.start,((DerAberrationErrorContext)_localctx).suffix.stop):null) != null) {
				  		((DerAberrationErrorContext)_localctx).l =  _localctx.l + (((DerAberrationErrorContext)_localctx).suffix!=null?_input.getText(((DerAberrationErrorContext)_localctx).suffix.start,((DerAberrationErrorContext)_localctx).suffix.stop):null).length(); 
				  	}  	
				 	if ((((DerAberrationErrorContext)_localctx).derBreakpointsList!=null?_input.getText(((DerAberrationErrorContext)_localctx).derBreakpointsList.start,((DerAberrationErrorContext)_localctx).derBreakpointsList.stop):null) != null) {
				 		((DerAberrationErrorContext)_localctx).l =  _localctx.l + (((DerAberrationErrorContext)_localctx).derBreakpointsList!=null?_input.getText(((DerAberrationErrorContext)_localctx).derBreakpointsList.start,((DerAberrationErrorContext)_localctx).derBreakpointsList.stop):null).length();
				 		notifyErrorListeners(_localctx.l + "|Incorrect comma before '" + (((DerAberrationErrorContext)_localctx).derChrList!=null?_input.getText(((DerAberrationErrorContext)_localctx).derChrList.start,((DerAberrationErrorContext)_localctx).derChrList.stop):null) + (((DerAberrationErrorContext)_localctx).derBreakpointsList!=null?_input.getText(((DerAberrationErrorContext)_localctx).derBreakpointsList.start,((DerAberrationErrorContext)_localctx).derBreakpointsList.stop):null) + "'");
				 	} else {
				 		notifyErrorListeners(_localctx.l + "|Incorrect comma before '" + (((DerAberrationErrorContext)_localctx).derChrList!=null?_input.getText(((DerAberrationErrorContext)_localctx).derChrList.start,((DerAberrationErrorContext)_localctx).derChrList.stop):null) + "'");
				 	} 	    
				  
				}
				break;
			case 5:
				_localctx = new BasicAberrationErrorContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(1077);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << PLUS) | (1L << QUES))) != 0)) {
					{
					setState(1076);
					((BasicAberrationErrorContext)_localctx).prefix = prefix();
					}
				}

				setState(1080);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__17) | (1L << T__18))) != 0)) {
					{
					setState(1079);
					((BasicAberrationErrorContext)_localctx).mosChi = mosChi();
					}
				}

				setState(1082);
				((BasicAberrationErrorContext)_localctx).id = id();
				setState(1084); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(1083);
					((BasicAberrationErrorContext)_localctx).comma = match(T__2);
					}
					}
					setState(1086); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__2 );
				setState(1088);
				((BasicAberrationErrorContext)_localctx).chrList = chrList();
				setState(1090);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,170,_ctx) ) {
				case 1:
					{
					setState(1089);
					((BasicAberrationErrorContext)_localctx).breakpointsList = breakpointsList();
					}
					break;
				}
				setState(1093);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,171,_ctx) ) {
				case 1:
					{
					setState(1092);
					((BasicAberrationErrorContext)_localctx).suffix = suffix();
					}
					break;
				}

				  	if ((((BasicAberrationErrorContext)_localctx).prefix!=null?_input.getText(((BasicAberrationErrorContext)_localctx).prefix.start,((BasicAberrationErrorContext)_localctx).prefix.stop):null) != null) {
				  		((BasicAberrationErrorContext)_localctx).l =  _localctx.l + (((BasicAberrationErrorContext)_localctx).prefix!=null?_input.getText(((BasicAberrationErrorContext)_localctx).prefix.start,((BasicAberrationErrorContext)_localctx).prefix.stop):null).length(); 
				  	}
				  	if ((((BasicAberrationErrorContext)_localctx).mosChi!=null?_input.getText(((BasicAberrationErrorContext)_localctx).mosChi.start,((BasicAberrationErrorContext)_localctx).mosChi.stop):null) != null) {
				  		((BasicAberrationErrorContext)_localctx).l =  _localctx.l + (((BasicAberrationErrorContext)_localctx).mosChi!=null?_input.getText(((BasicAberrationErrorContext)_localctx).mosChi.start,((BasicAberrationErrorContext)_localctx).mosChi.stop):null).length(); 
				  	}
				  	((BasicAberrationErrorContext)_localctx).l =  _localctx.l + (((BasicAberrationErrorContext)_localctx).id!=null?_input.getText(((BasicAberrationErrorContext)_localctx).id.start,((BasicAberrationErrorContext)_localctx).id.stop):null).length(); 
				  	((BasicAberrationErrorContext)_localctx).l =  _localctx.l + (((BasicAberrationErrorContext)_localctx).comma!=null?((BasicAberrationErrorContext)_localctx).comma.getText():null).length(); 
				  	((BasicAberrationErrorContext)_localctx).l =  _localctx.l + (((BasicAberrationErrorContext)_localctx).chrList!=null?_input.getText(((BasicAberrationErrorContext)_localctx).chrList.start,((BasicAberrationErrorContext)_localctx).chrList.stop):null).length(); 
				  	if ((((BasicAberrationErrorContext)_localctx).suffix!=null?_input.getText(((BasicAberrationErrorContext)_localctx).suffix.start,((BasicAberrationErrorContext)_localctx).suffix.stop):null) != null) {
				  		((BasicAberrationErrorContext)_localctx).l =  _localctx.l + (((BasicAberrationErrorContext)_localctx).suffix!=null?_input.getText(((BasicAberrationErrorContext)_localctx).suffix.start,((BasicAberrationErrorContext)_localctx).suffix.stop):null).length(); 
				  	}
				 	if ((((BasicAberrationErrorContext)_localctx).breakpointsList!=null?_input.getText(((BasicAberrationErrorContext)_localctx).breakpointsList.start,((BasicAberrationErrorContext)_localctx).breakpointsList.stop):null) != null) {
				 		((BasicAberrationErrorContext)_localctx).l =  _localctx.l + (((BasicAberrationErrorContext)_localctx).breakpointsList!=null?_input.getText(((BasicAberrationErrorContext)_localctx).breakpointsList.start,((BasicAberrationErrorContext)_localctx).breakpointsList.stop):null).length();
				 		notifyErrorListeners(_localctx.l + "|Incorrect comma before '" + (((BasicAberrationErrorContext)_localctx).chrList!=null?_input.getText(((BasicAberrationErrorContext)_localctx).chrList.start,((BasicAberrationErrorContext)_localctx).chrList.stop):null) + (((BasicAberrationErrorContext)_localctx).breakpointsList!=null?_input.getText(((BasicAberrationErrorContext)_localctx).breakpointsList.start,((BasicAberrationErrorContext)_localctx).breakpointsList.stop):null) + "'");
				 	} else {
				 		notifyErrorListeners(_localctx.l + "|Incorrect comma before '" + (((BasicAberrationErrorContext)_localctx).chrList!=null?_input.getText(((BasicAberrationErrorContext)_localctx).chrList.start,((BasicAberrationErrorContext)_localctx).chrList.stop):null) + "'"); 
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

	public static class RearrangementElementContext extends ParserRuleContext {
		public RearrangementElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rearrangementElement; }
	 
		public RearrangementElementContext() { }
		public void copyFrom(RearrangementElementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MultipleInterpretationRearrangementContext extends RearrangementElementContext {
		public List<RearrangementContext> rearrangement() {
			return getRuleContexts(RearrangementContext.class);
		}
		public RearrangementContext rearrangement(int i) {
			return getRuleContext(RearrangementContext.class,i);
		}
		public MultipleInterpretationRearrangementContext(RearrangementElementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterMultipleInterpretationRearrangement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitMultipleInterpretationRearrangement(this);
		}
	}
	public static class NormalRearrangementContext extends RearrangementElementContext {
		public RearrangementContext rearrangement() {
			return getRuleContext(RearrangementContext.class,0);
		}
		public NormalRearrangementContext(RearrangementElementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterNormalRearrangement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitNormalRearrangement(this);
		}
	}

	public final RearrangementElementContext rearrangementElement() throws RecognitionException {
		RearrangementElementContext _localctx = new RearrangementElementContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_rearrangementElement);
		int _la;
		try {
			int _alt;
			setState(1108);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,174,_ctx) ) {
			case 1:
				_localctx = new NormalRearrangementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(1099);
				rearrangement();
				}
				break;
			case 2:
				_localctx = new MultipleInterpretationRearrangementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(1100);
				rearrangement();
				setState(1104); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(1101);
						_la = _input.LA(1);
						if ( !(_la==T__5 || _la==T__6) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1102);
						_la = _input.LA(1);
						if ( !(_la==T__15 || _la==T__16) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1103);
						rearrangement();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(1106); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,173,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class RearrangementContext extends ParserRuleContext {
		public int l = 0;
		public Token QUES;
		public RIdContext rId;
		public Token comma;
		public RChrListContext rChrList;
		public RBreakpointsListContext rBreakpointsList;
		public RIdContext rId() {
			return getRuleContext(RIdContext.class,0);
		}
		public RChrListContext rChrList() {
			return getRuleContext(RChrListContext.class,0);
		}
		public TerminalNode QUES() { return getToken(KaryotypeParser.QUES, 0); }
		public RBreakpointsListContext rBreakpointsList() {
			return getRuleContext(RBreakpointsListContext.class,0);
		}
		public RearrangementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rearrangement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterRearrangement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitRearrangement(this);
		}
	}

	public final RearrangementContext rearrangement() throws RecognitionException {
		RearrangementContext _localctx = new RearrangementContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_rearrangement);
		int _la;
		try {
			setState(1133);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,180,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1111);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==QUES) {
					{
					setState(1110);
					match(QUES);
					}
				}

				setState(1113);
				rId();
				setState(1114);
				rChrList();
				setState(1116);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,176,_ctx) ) {
				case 1:
					{
					setState(1115);
					rBreakpointsList();
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1119);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==QUES) {
					{
					setState(1118);
					((RearrangementContext)_localctx).QUES = match(QUES);
					}
				}

				setState(1121);
				((RearrangementContext)_localctx).rId = rId();
				setState(1123); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(1122);
					((RearrangementContext)_localctx).comma = match(T__2);
					}
					}
					setState(1125); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__2 );
				setState(1127);
				((RearrangementContext)_localctx).rChrList = rChrList();
				setState(1129);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,179,_ctx) ) {
				case 1:
					{
					setState(1128);
					((RearrangementContext)_localctx).rBreakpointsList = rBreakpointsList();
					}
					break;
				}

				  	if ((((RearrangementContext)_localctx).QUES!=null?((RearrangementContext)_localctx).QUES.getText():null) != null) {
				  		((RearrangementContext)_localctx).l =  _localctx.l + (((RearrangementContext)_localctx).QUES!=null?((RearrangementContext)_localctx).QUES.getText():null).length(); 
				  	}
				  	((RearrangementContext)_localctx).l =  _localctx.l + (((RearrangementContext)_localctx).rId!=null?_input.getText(((RearrangementContext)_localctx).rId.start,((RearrangementContext)_localctx).rId.stop):null).length(); 
				  	((RearrangementContext)_localctx).l =  _localctx.l + (((RearrangementContext)_localctx).comma!=null?((RearrangementContext)_localctx).comma.getText():null).length();
				  	((RearrangementContext)_localctx).l =  _localctx.l + (((RearrangementContext)_localctx).rChrList!=null?_input.getText(((RearrangementContext)_localctx).rChrList.start,((RearrangementContext)_localctx).rChrList.stop):null).length();  	
					if ((((RearrangementContext)_localctx).rBreakpointsList!=null?_input.getText(((RearrangementContext)_localctx).rBreakpointsList.start,((RearrangementContext)_localctx).rBreakpointsList.stop):null) != null) {
						((RearrangementContext)_localctx).l =  _localctx.l + (((RearrangementContext)_localctx).rBreakpointsList!=null?_input.getText(((RearrangementContext)_localctx).rBreakpointsList.start,((RearrangementContext)_localctx).rBreakpointsList.stop):null).length();
						notifyErrorListeners(_localctx.l + "|Incorrect comma before '" + (((RearrangementContext)_localctx).rChrList!=null?_input.getText(((RearrangementContext)_localctx).rChrList.start,((RearrangementContext)_localctx).rChrList.stop):null) + (((RearrangementContext)_localctx).rBreakpointsList!=null?_input.getText(((RearrangementContext)_localctx).rBreakpointsList.start,((RearrangementContext)_localctx).rBreakpointsList.stop):null) + "'");
					} else {
						notifyErrorListeners(_localctx.l + "|Incorrect comma before '" + (((RearrangementContext)_localctx).rChrList!=null?_input.getText(((RearrangementContext)_localctx).rChrList.start,((RearrangementContext)_localctx).rChrList.stop):null) + "'");
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

	public static class DerChrListContext extends ParserRuleContext {
		public String s = "";
		public int l = 0;
		public DerChrListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_derChrList; }
	 
		public DerChrListContext() { }
		public void copyFrom(DerChrListContext ctx) {
			super.copyFrom(ctx);
			this.s = ctx.s;
			this.l = ctx.l;
		}
	}
	public static class IncorrectDerChrListContext extends DerChrListContext {
		public DerChrListElementsContext derChrListElements;
		public IncorrectLeftParenthesisContext incorrectLeftParenthesis;
		public IncorrectRightParenthesisContext incorrectRightParenthesis;
		public DerChrListElementsContext derChrListElements() {
			return getRuleContext(DerChrListElementsContext.class,0);
		}
		public IncorrectLeftParenthesisContext incorrectLeftParenthesis() {
			return getRuleContext(IncorrectLeftParenthesisContext.class,0);
		}
		public IncorrectRightParenthesisContext incorrectRightParenthesis() {
			return getRuleContext(IncorrectRightParenthesisContext.class,0);
		}
		public IncorrectDerChrListContext(DerChrListContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterIncorrectDerChrList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitIncorrectDerChrList(this);
		}
	}
	public static class CorrectDerChrListContext extends DerChrListContext {
		public DerChrListElementsContext derChrListElements() {
			return getRuleContext(DerChrListElementsContext.class,0);
		}
		public CorrectDerChrListContext(DerChrListContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterCorrectDerChrList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitCorrectDerChrList(this);
		}
	}

	public final DerChrListContext derChrList() throws RecognitionException {
		DerChrListContext _localctx = new DerChrListContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_derChrList);
		try {
			setState(1155);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,181,_ctx) ) {
			case 1:
				_localctx = new CorrectDerChrListContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(1135);
				match(T__31);
				setState(1136);
				derChrListElements();
				setState(1137);
				match(T__32);
				}
				break;
			case 2:
				_localctx = new IncorrectDerChrListContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(1139);
				((IncorrectDerChrListContext)_localctx).derChrListElements = derChrListElements();
				setState(1140);
				match(T__32);

				  	((IncorrectDerChrListContext)_localctx).s =  (((IncorrectDerChrListContext)_localctx).derChrListElements!=null?_input.getText(((IncorrectDerChrListContext)_localctx).derChrListElements.start,((IncorrectDerChrListContext)_localctx).derChrListElements.stop):null);
				  	((IncorrectDerChrListContext)_localctx).l =  _localctx.s.length() + 1;
				  	notifyErrorListeners(_localctx.l + "|Missing '(' for derivative chromosome list '" + _localctx.s + "'");
				  
				}
				break;
			case 3:
				_localctx = new IncorrectDerChrListContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(1143);
				match(T__31);
				setState(1144);
				((IncorrectDerChrListContext)_localctx).derChrListElements = derChrListElements();

				  	((IncorrectDerChrListContext)_localctx).s =  (((IncorrectDerChrListContext)_localctx).derChrListElements!=null?_input.getText(((IncorrectDerChrListContext)_localctx).derChrListElements.start,((IncorrectDerChrListContext)_localctx).derChrListElements.stop):null);
				  	((IncorrectDerChrListContext)_localctx).l =  _localctx.s.length() + 1;
				  	notifyErrorListeners(_localctx.l + "|Missing ')' for derivative chromosome list '" + _localctx.s + "'");
				  
				}
				break;
			case 4:
				_localctx = new IncorrectDerChrListContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(1147);
				((IncorrectDerChrListContext)_localctx).derChrListElements = derChrListElements();

				  	((IncorrectDerChrListContext)_localctx).s =  (((IncorrectDerChrListContext)_localctx).derChrListElements!=null?_input.getText(((IncorrectDerChrListContext)_localctx).derChrListElements.start,((IncorrectDerChrListContext)_localctx).derChrListElements.stop):null);
				  	((IncorrectDerChrListContext)_localctx).l =  _localctx.s.length();
				  	notifyErrorListeners(_localctx.l + "|Missing '()' for derivative chromosome list '" + _localctx.s + "'");
				  
				}
				break;
			case 5:
				_localctx = new IncorrectDerChrListContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(1150);
				((IncorrectDerChrListContext)_localctx).incorrectLeftParenthesis = incorrectLeftParenthesis();
				setState(1151);
				((IncorrectDerChrListContext)_localctx).derChrListElements = derChrListElements();
				setState(1152);
				((IncorrectDerChrListContext)_localctx).incorrectRightParenthesis = incorrectRightParenthesis();

				  	((IncorrectDerChrListContext)_localctx).s =  (((IncorrectDerChrListContext)_localctx).derChrListElements!=null?_input.getText(((IncorrectDerChrListContext)_localctx).derChrListElements.start,((IncorrectDerChrListContext)_localctx).derChrListElements.stop):null);
				  	((IncorrectDerChrListContext)_localctx).l =  _localctx.s.length();
				  	if ((((IncorrectDerChrListContext)_localctx).incorrectLeftParenthesis!=null?_input.getText(((IncorrectDerChrListContext)_localctx).incorrectLeftParenthesis.start,((IncorrectDerChrListContext)_localctx).incorrectLeftParenthesis.stop):null) != null) {
				  		((IncorrectDerChrListContext)_localctx).l =  _localctx.l + (((IncorrectDerChrListContext)_localctx).incorrectLeftParenthesis!=null?_input.getText(((IncorrectDerChrListContext)_localctx).incorrectLeftParenthesis.start,((IncorrectDerChrListContext)_localctx).incorrectLeftParenthesis.stop):null).length();
				  	}
				  	if ((((IncorrectDerChrListContext)_localctx).incorrectRightParenthesis!=null?_input.getText(((IncorrectDerChrListContext)_localctx).incorrectRightParenthesis.start,((IncorrectDerChrListContext)_localctx).incorrectRightParenthesis.stop):null) != null) {
				  		((IncorrectDerChrListContext)_localctx).l =  _localctx.l + (((IncorrectDerChrListContext)_localctx).incorrectRightParenthesis!=null?_input.getText(((IncorrectDerChrListContext)_localctx).incorrectRightParenthesis.start,((IncorrectDerChrListContext)_localctx).incorrectRightParenthesis.stop):null).length();
				  	}
				  	notifyErrorListeners(_localctx.l + "|Wrong bracket format for derivative chromosome list '" + _localctx.s + "', expecting '()'");
				  
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

	public static class DerChrListElementsContext extends ParserRuleContext {
		public IncorrectDerChrListElementsContext incorrectDerChrListElements;
		public List<DerChrContext> derChr() {
			return getRuleContexts(DerChrContext.class);
		}
		public DerChrContext derChr(int i) {
			return getRuleContext(DerChrContext.class,i);
		}
		public IncorrectDerChrListElementsContext incorrectDerChrListElements() {
			return getRuleContext(IncorrectDerChrListElementsContext.class,0);
		}
		public DerChrListElementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_derChrListElements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterDerChrListElements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitDerChrListElements(this);
		}
	}

	public final DerChrListElementsContext derChrListElements() throws RecognitionException {
		DerChrListElementsContext _localctx = new DerChrListElementsContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_derChrListElements);
		try {
			int _alt;
			setState(1168);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,183,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1157);
				derChr();
				setState(1162);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,182,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1158);
						match(T__22);
						setState(1159);
						derChr();
						}
						} 
					}
					setState(1164);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,182,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1165);
				((DerChrListElementsContext)_localctx).incorrectDerChrListElements = incorrectDerChrListElements();

				  	notifyErrorListeners((((DerChrListElementsContext)_localctx).incorrectDerChrListElements!=null?_input.getText(((DerChrListElementsContext)_localctx).incorrectDerChrListElements.start,((DerChrListElementsContext)_localctx).incorrectDerChrListElements.stop):null).length() + "|Incorrect use of separators inside derivative chromosome list '" + (((DerChrListElementsContext)_localctx).incorrectDerChrListElements!=null?_input.getText(((DerChrListElementsContext)_localctx).incorrectDerChrListElements.start,((DerChrListElementsContext)_localctx).incorrectDerChrListElements.stop):null) + "', expecting ';'");
				  
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

	public static class IncorrectDerChrListElementsContext extends ParserRuleContext {
		public List<DerChrContext> derChr() {
			return getRuleContexts(DerChrContext.class);
		}
		public DerChrContext derChr(int i) {
			return getRuleContext(DerChrContext.class,i);
		}
		public IncorrectDerChrListElementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_incorrectDerChrListElements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterIncorrectDerChrListElements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitIncorrectDerChrListElements(this);
		}
	}

	public final IncorrectDerChrListElementsContext incorrectDerChrListElements() throws RecognitionException {
		IncorrectDerChrListElementsContext _localctx = new IncorrectDerChrListElementsContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_incorrectDerChrListElements);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1170);
			derChr();
			setState(1173); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1171);
					_la = _input.LA(1);
					if ( !(_la==T__2 || _la==T__22) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(1172);
					derChr();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1175); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,184,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class IncorrectLeftParenthesisContext extends ParserRuleContext {
		public IncorrectLeftParenthesisContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_incorrectLeftParenthesis; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterIncorrectLeftParenthesis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitIncorrectLeftParenthesis(this);
		}
	}

	public final IncorrectLeftParenthesisContext incorrectLeftParenthesis() throws RecognitionException {
		IncorrectLeftParenthesisContext _localctx = new IncorrectLeftParenthesisContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_incorrectLeftParenthesis);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__23) | (1L << T__31) | (1L << T__33) | (1L << T__34))) != 0)) {
				{
				{
				setState(1177);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__23) | (1L << T__31) | (1L << T__33) | (1L << T__34))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(1182);
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

	public static class IncorrectRightParenthesisContext extends ParserRuleContext {
		public IncorrectRightParenthesisContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_incorrectRightParenthesis; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterIncorrectRightParenthesis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitIncorrectRightParenthesis(this);
		}
	}

	public final IncorrectRightParenthesisContext incorrectRightParenthesis() throws RecognitionException {
		IncorrectRightParenthesisContext _localctx = new IncorrectRightParenthesisContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_incorrectRightParenthesis);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1186);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__32) | (1L << T__35) | (1L << T__36))) != 0)) {
				{
				{
				setState(1183);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__32) | (1L << T__35) | (1L << T__36))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(1188);
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

	public static class DerBreakpointsListContext extends ParserRuleContext {
		public String s = "";
		public int l = 0;
		public DerBreakpointsListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_derBreakpointsList; }
	 
		public DerBreakpointsListContext() { }
		public void copyFrom(DerBreakpointsListContext ctx) {
			super.copyFrom(ctx);
			this.s = ctx.s;
			this.l = ctx.l;
		}
	}
	public static class CorrectDerBreakpointsListContext extends DerBreakpointsListContext {
		public DerBreakpointsListElementsContext derBreakpointsListElements() {
			return getRuleContext(DerBreakpointsListElementsContext.class,0);
		}
		public CorrectDerBreakpointsListContext(DerBreakpointsListContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterCorrectDerBreakpointsList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitCorrectDerBreakpointsList(this);
		}
	}
	public static class IncorrectDerBreakpointsListContext extends DerBreakpointsListContext {
		public DerBreakpointsListElementsContext derBreakpointsListElements;
		public IncorrectLeftParenthesisContext incorrectLeftParenthesis;
		public IncorrectRightParenthesisContext incorrectRightParenthesis;
		public DerBreakpointsListElementsContext derBreakpointsListElements() {
			return getRuleContext(DerBreakpointsListElementsContext.class,0);
		}
		public IncorrectLeftParenthesisContext incorrectLeftParenthesis() {
			return getRuleContext(IncorrectLeftParenthesisContext.class,0);
		}
		public IncorrectRightParenthesisContext incorrectRightParenthesis() {
			return getRuleContext(IncorrectRightParenthesisContext.class,0);
		}
		public IncorrectDerBreakpointsListContext(DerBreakpointsListContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterIncorrectDerBreakpointsList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitIncorrectDerBreakpointsList(this);
		}
	}

	public final DerBreakpointsListContext derBreakpointsList() throws RecognitionException {
		DerBreakpointsListContext _localctx = new DerBreakpointsListContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_derBreakpointsList);
		try {
			setState(1209);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,187,_ctx) ) {
			case 1:
				_localctx = new CorrectDerBreakpointsListContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(1189);
				match(T__31);
				setState(1190);
				derBreakpointsListElements();
				setState(1191);
				match(T__32);
				}
				break;
			case 2:
				_localctx = new IncorrectDerBreakpointsListContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(1193);
				((IncorrectDerBreakpointsListContext)_localctx).derBreakpointsListElements = derBreakpointsListElements();
				setState(1194);
				match(T__32);

				  	((IncorrectDerBreakpointsListContext)_localctx).s =  (((IncorrectDerBreakpointsListContext)_localctx).derBreakpointsListElements!=null?_input.getText(((IncorrectDerBreakpointsListContext)_localctx).derBreakpointsListElements.start,((IncorrectDerBreakpointsListContext)_localctx).derBreakpointsListElements.stop):null);
				  	((IncorrectDerBreakpointsListContext)_localctx).l =  _localctx.s.length() + 1;
				  	notifyErrorListeners(_localctx.l + "|Missing '(' for derivative breakpoints list '" + _localctx.s + "'");
				  
				}
				break;
			case 3:
				_localctx = new IncorrectDerBreakpointsListContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(1197);
				match(T__31);
				setState(1198);
				((IncorrectDerBreakpointsListContext)_localctx).derBreakpointsListElements = derBreakpointsListElements();

				  	((IncorrectDerBreakpointsListContext)_localctx).s =  (((IncorrectDerBreakpointsListContext)_localctx).derBreakpointsListElements!=null?_input.getText(((IncorrectDerBreakpointsListContext)_localctx).derBreakpointsListElements.start,((IncorrectDerBreakpointsListContext)_localctx).derBreakpointsListElements.stop):null);
				  	((IncorrectDerBreakpointsListContext)_localctx).l =  _localctx.s.length() + 1;
				  	notifyErrorListeners(_localctx.l + "|Missing ')' for derivative breakpoints list '" + _localctx.s + "'");
				  
				}
				break;
			case 4:
				_localctx = new IncorrectDerBreakpointsListContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(1201);
				((IncorrectDerBreakpointsListContext)_localctx).derBreakpointsListElements = derBreakpointsListElements();

				  	((IncorrectDerBreakpointsListContext)_localctx).s =  (((IncorrectDerBreakpointsListContext)_localctx).derBreakpointsListElements!=null?_input.getText(((IncorrectDerBreakpointsListContext)_localctx).derBreakpointsListElements.start,((IncorrectDerBreakpointsListContext)_localctx).derBreakpointsListElements.stop):null);
				  	((IncorrectDerBreakpointsListContext)_localctx).l =  _localctx.s.length();
				  	notifyErrorListeners(_localctx.l + "|Missing '()' for derivative breakpoints list '" + _localctx.s + "'");
				  
				}
				break;
			case 5:
				_localctx = new IncorrectDerBreakpointsListContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(1204);
				((IncorrectDerBreakpointsListContext)_localctx).incorrectLeftParenthesis = incorrectLeftParenthesis();
				setState(1205);
				((IncorrectDerBreakpointsListContext)_localctx).derBreakpointsListElements = derBreakpointsListElements();
				setState(1206);
				((IncorrectDerBreakpointsListContext)_localctx).incorrectRightParenthesis = incorrectRightParenthesis();

				  	((IncorrectDerBreakpointsListContext)_localctx).s =  (((IncorrectDerBreakpointsListContext)_localctx).derBreakpointsListElements!=null?_input.getText(((IncorrectDerBreakpointsListContext)_localctx).derBreakpointsListElements.start,((IncorrectDerBreakpointsListContext)_localctx).derBreakpointsListElements.stop):null);
				  	((IncorrectDerBreakpointsListContext)_localctx).l =  _localctx.s.length();
				  	if ((((IncorrectDerBreakpointsListContext)_localctx).incorrectLeftParenthesis!=null?_input.getText(((IncorrectDerBreakpointsListContext)_localctx).incorrectLeftParenthesis.start,((IncorrectDerBreakpointsListContext)_localctx).incorrectLeftParenthesis.stop):null) != null) {
				  		((IncorrectDerBreakpointsListContext)_localctx).l =  _localctx.l + (((IncorrectDerBreakpointsListContext)_localctx).incorrectLeftParenthesis!=null?_input.getText(((IncorrectDerBreakpointsListContext)_localctx).incorrectLeftParenthesis.start,((IncorrectDerBreakpointsListContext)_localctx).incorrectLeftParenthesis.stop):null).length();
				  	}
				  	if ((((IncorrectDerBreakpointsListContext)_localctx).incorrectRightParenthesis!=null?_input.getText(((IncorrectDerBreakpointsListContext)_localctx).incorrectRightParenthesis.start,((IncorrectDerBreakpointsListContext)_localctx).incorrectRightParenthesis.stop):null) != null) {
				  		((IncorrectDerBreakpointsListContext)_localctx).l =  _localctx.l + (((IncorrectDerBreakpointsListContext)_localctx).incorrectRightParenthesis!=null?_input.getText(((IncorrectDerBreakpointsListContext)_localctx).incorrectRightParenthesis.start,((IncorrectDerBreakpointsListContext)_localctx).incorrectRightParenthesis.stop):null).length();
				  	}
				  	notifyErrorListeners(_localctx.l + "|Wrong bracket format for derivative breakpoints list '" + _localctx.s + "', expecting '()'");
				  
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

	public static class DerBreakpointsListElementsContext extends ParserRuleContext {
		public IncorrectDerBreakpointsListElementsContext incorrectDerBreakpointsListElements;
		public List<DerBreakpointsInOneChrContext> derBreakpointsInOneChr() {
			return getRuleContexts(DerBreakpointsInOneChrContext.class);
		}
		public DerBreakpointsInOneChrContext derBreakpointsInOneChr(int i) {
			return getRuleContext(DerBreakpointsInOneChrContext.class,i);
		}
		public IncorrectDerBreakpointsListElementsContext incorrectDerBreakpointsListElements() {
			return getRuleContext(IncorrectDerBreakpointsListElementsContext.class,0);
		}
		public DerBreakpointsListElementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_derBreakpointsListElements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterDerBreakpointsListElements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitDerBreakpointsListElements(this);
		}
	}

	public final DerBreakpointsListElementsContext derBreakpointsListElements() throws RecognitionException {
		DerBreakpointsListElementsContext _localctx = new DerBreakpointsListElementsContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_derBreakpointsListElements);
		try {
			int _alt;
			setState(1222);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,189,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1211);
				derBreakpointsInOneChr();
				setState(1216);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,188,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1212);
						match(T__22);
						setState(1213);
						derBreakpointsInOneChr();
						}
						} 
					}
					setState(1218);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,188,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1219);
				((DerBreakpointsListElementsContext)_localctx).incorrectDerBreakpointsListElements = incorrectDerBreakpointsListElements();

				  	notifyErrorListeners((((DerBreakpointsListElementsContext)_localctx).incorrectDerBreakpointsListElements!=null?_input.getText(((DerBreakpointsListElementsContext)_localctx).incorrectDerBreakpointsListElements.start,((DerBreakpointsListElementsContext)_localctx).incorrectDerBreakpointsListElements.stop):null).length() + "|Incorrect use of separators inside breakpoints list '" + (((DerBreakpointsListElementsContext)_localctx).incorrectDerBreakpointsListElements!=null?_input.getText(((DerBreakpointsListElementsContext)_localctx).incorrectDerBreakpointsListElements.start,((DerBreakpointsListElementsContext)_localctx).incorrectDerBreakpointsListElements.stop):null) + "', expecting ';'");
				  
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

	public static class IncorrectDerBreakpointsListElementsContext extends ParserRuleContext {
		public List<DerBreakpointsInOneChrContext> derBreakpointsInOneChr() {
			return getRuleContexts(DerBreakpointsInOneChrContext.class);
		}
		public DerBreakpointsInOneChrContext derBreakpointsInOneChr(int i) {
			return getRuleContext(DerBreakpointsInOneChrContext.class,i);
		}
		public IncorrectDerBreakpointsListElementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_incorrectDerBreakpointsListElements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterIncorrectDerBreakpointsListElements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitIncorrectDerBreakpointsListElements(this);
		}
	}

	public final IncorrectDerBreakpointsListElementsContext incorrectDerBreakpointsListElements() throws RecognitionException {
		IncorrectDerBreakpointsListElementsContext _localctx = new IncorrectDerBreakpointsListElementsContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_incorrectDerBreakpointsListElements);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1224);
			derBreakpointsInOneChr();
			setState(1227); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1225);
					_la = _input.LA(1);
					if ( !(_la==T__2 || _la==T__22) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(1226);
					derBreakpointsInOneChr();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1229); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,190,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class DerBreakpointsInOneChrContext extends ParserRuleContext {
		public DerBreakpointsInOneChrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_derBreakpointsInOneChr; }
	 
		public DerBreakpointsInOneChrContext() { }
		public void copyFrom(DerBreakpointsInOneChrContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MultipleInterpretationDerBreakpointsContext extends DerBreakpointsInOneChrContext {
		public List<DerBreakpointsContext> derBreakpoints() {
			return getRuleContexts(DerBreakpointsContext.class);
		}
		public DerBreakpointsContext derBreakpoints(int i) {
			return getRuleContext(DerBreakpointsContext.class,i);
		}
		public MultipleInterpretationDerBreakpointsContext(DerBreakpointsInOneChrContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterMultipleInterpretationDerBreakpoints(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitMultipleInterpretationDerBreakpoints(this);
		}
	}
	public static class NormalDerBreakpointsContext extends DerBreakpointsInOneChrContext {
		public DerBreakpointsContext derBreakpoints() {
			return getRuleContext(DerBreakpointsContext.class,0);
		}
		public NormalDerBreakpointsContext(DerBreakpointsInOneChrContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterNormalDerBreakpoints(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitNormalDerBreakpoints(this);
		}
	}

	public final DerBreakpointsInOneChrContext derBreakpointsInOneChr() throws RecognitionException {
		DerBreakpointsInOneChrContext _localctx = new DerBreakpointsInOneChrContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_derBreakpointsInOneChr);
		int _la;
		try {
			int _alt;
			setState(1240);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,192,_ctx) ) {
			case 1:
				_localctx = new NormalDerBreakpointsContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(1231);
				derBreakpoints();
				}
				break;
			case 2:
				_localctx = new MultipleInterpretationDerBreakpointsContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(1232);
				derBreakpoints();
				setState(1236); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(1233);
						_la = _input.LA(1);
						if ( !(_la==T__5 || _la==T__6) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1234);
						_la = _input.LA(1);
						if ( !(_la==T__15 || _la==T__16) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1235);
						derBreakpoints();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(1238); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,191,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class ChrListContext extends ParserRuleContext {
		public String s = "";
		public int l = 0;
		public ChrListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_chrList; }
	 
		public ChrListContext() { }
		public void copyFrom(ChrListContext ctx) {
			super.copyFrom(ctx);
			this.s = ctx.s;
			this.l = ctx.l;
		}
	}
	public static class CorrectChrListContext extends ChrListContext {
		public ChrListElementsContext chrListElements() {
			return getRuleContext(ChrListElementsContext.class,0);
		}
		public CorrectChrListContext(ChrListContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterCorrectChrList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitCorrectChrList(this);
		}
	}
	public static class IncorrectChrListContext extends ChrListContext {
		public ChrListElementsContext chrListElements;
		public IncorrectLeftParenthesisContext incorrectLeftParenthesis;
		public IncorrectRightParenthesisContext incorrectRightParenthesis;
		public ChrListElementsContext chrListElements() {
			return getRuleContext(ChrListElementsContext.class,0);
		}
		public IncorrectLeftParenthesisContext incorrectLeftParenthesis() {
			return getRuleContext(IncorrectLeftParenthesisContext.class,0);
		}
		public IncorrectRightParenthesisContext incorrectRightParenthesis() {
			return getRuleContext(IncorrectRightParenthesisContext.class,0);
		}
		public IncorrectChrListContext(ChrListContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterIncorrectChrList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitIncorrectChrList(this);
		}
	}

	public final ChrListContext chrList() throws RecognitionException {
		ChrListContext _localctx = new ChrListContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_chrList);
		try {
			setState(1262);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,193,_ctx) ) {
			case 1:
				_localctx = new CorrectChrListContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(1242);
				match(T__31);
				setState(1243);
				chrListElements();
				setState(1244);
				match(T__32);
				}
				break;
			case 2:
				_localctx = new IncorrectChrListContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(1246);
				((IncorrectChrListContext)_localctx).chrListElements = chrListElements();
				setState(1247);
				match(T__32);

				  	((IncorrectChrListContext)_localctx).s =  (((IncorrectChrListContext)_localctx).chrListElements!=null?_input.getText(((IncorrectChrListContext)_localctx).chrListElements.start,((IncorrectChrListContext)_localctx).chrListElements.stop):null);
				  	((IncorrectChrListContext)_localctx).l =  _localctx.s.length() + 1;
				  	notifyErrorListeners(_localctx.l + "|Missing '(' for chromosome list '" + _localctx.s + "'");
				  
				}
				break;
			case 3:
				_localctx = new IncorrectChrListContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(1250);
				match(T__31);
				setState(1251);
				((IncorrectChrListContext)_localctx).chrListElements = chrListElements();

				  	((IncorrectChrListContext)_localctx).s =  (((IncorrectChrListContext)_localctx).chrListElements!=null?_input.getText(((IncorrectChrListContext)_localctx).chrListElements.start,((IncorrectChrListContext)_localctx).chrListElements.stop):null);
				  	((IncorrectChrListContext)_localctx).l =  _localctx.s.length() + 1;
				  	notifyErrorListeners(_localctx.l + "|Missing ')' for chromosome list '" + _localctx.s + "'");
				  
				}
				break;
			case 4:
				_localctx = new IncorrectChrListContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(1254);
				((IncorrectChrListContext)_localctx).chrListElements = chrListElements();

				  	((IncorrectChrListContext)_localctx).s =  (((IncorrectChrListContext)_localctx).chrListElements!=null?_input.getText(((IncorrectChrListContext)_localctx).chrListElements.start,((IncorrectChrListContext)_localctx).chrListElements.stop):null);
				  	((IncorrectChrListContext)_localctx).l =  _localctx.s.length();
				  	notifyErrorListeners(_localctx.l + "|Missing '()' for chromosome list '" + _localctx.s + "'");
				  
				}
				break;
			case 5:
				_localctx = new IncorrectChrListContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(1257);
				((IncorrectChrListContext)_localctx).incorrectLeftParenthesis = incorrectLeftParenthesis();
				setState(1258);
				((IncorrectChrListContext)_localctx).chrListElements = chrListElements();
				setState(1259);
				((IncorrectChrListContext)_localctx).incorrectRightParenthesis = incorrectRightParenthesis();

				  	((IncorrectChrListContext)_localctx).s =  (((IncorrectChrListContext)_localctx).chrListElements!=null?_input.getText(((IncorrectChrListContext)_localctx).chrListElements.start,((IncorrectChrListContext)_localctx).chrListElements.stop):null);
				  	((IncorrectChrListContext)_localctx).l =  _localctx.s.length();
				  	if ((((IncorrectChrListContext)_localctx).incorrectLeftParenthesis!=null?_input.getText(((IncorrectChrListContext)_localctx).incorrectLeftParenthesis.start,((IncorrectChrListContext)_localctx).incorrectLeftParenthesis.stop):null) != null) {
				  		((IncorrectChrListContext)_localctx).l =  _localctx.l + (((IncorrectChrListContext)_localctx).incorrectLeftParenthesis!=null?_input.getText(((IncorrectChrListContext)_localctx).incorrectLeftParenthesis.start,((IncorrectChrListContext)_localctx).incorrectLeftParenthesis.stop):null).length();
				  	}
				  	if ((((IncorrectChrListContext)_localctx).incorrectRightParenthesis!=null?_input.getText(((IncorrectChrListContext)_localctx).incorrectRightParenthesis.start,((IncorrectChrListContext)_localctx).incorrectRightParenthesis.stop):null) != null) {
				  		((IncorrectChrListContext)_localctx).l =  _localctx.l + (((IncorrectChrListContext)_localctx).incorrectRightParenthesis!=null?_input.getText(((IncorrectChrListContext)_localctx).incorrectRightParenthesis.start,((IncorrectChrListContext)_localctx).incorrectRightParenthesis.stop):null).length();
				  	}
				  	notifyErrorListeners(_localctx.l + "|Wrong bracket format for chromosome list '" + _localctx.s + "', expecting '()'");
				  
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

	public static class ChrListElementsContext extends ParserRuleContext {
		public IncorrectChrListElementsContext incorrectChrListElements;
		public List<ChrContext> chr() {
			return getRuleContexts(ChrContext.class);
		}
		public ChrContext chr(int i) {
			return getRuleContext(ChrContext.class,i);
		}
		public IncorrectChrListElementsContext incorrectChrListElements() {
			return getRuleContext(IncorrectChrListElementsContext.class,0);
		}
		public ChrListElementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_chrListElements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterChrListElements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitChrListElements(this);
		}
	}

	public final ChrListElementsContext chrListElements() throws RecognitionException {
		ChrListElementsContext _localctx = new ChrListElementsContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_chrListElements);
		try {
			int _alt;
			setState(1275);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,195,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1264);
				chr();
				setState(1269);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,194,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1265);
						match(T__22);
						setState(1266);
						chr();
						}
						} 
					}
					setState(1271);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,194,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1272);
				((ChrListElementsContext)_localctx).incorrectChrListElements = incorrectChrListElements();

				  	notifyErrorListeners((((ChrListElementsContext)_localctx).incorrectChrListElements!=null?_input.getText(((ChrListElementsContext)_localctx).incorrectChrListElements.start,((ChrListElementsContext)_localctx).incorrectChrListElements.stop):null).length() + "|Incorrect use of separators inside chromosome list '" + (((ChrListElementsContext)_localctx).incorrectChrListElements!=null?_input.getText(((ChrListElementsContext)_localctx).incorrectChrListElements.start,((ChrListElementsContext)_localctx).incorrectChrListElements.stop):null) + "', expecting ';'");
				  
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

	public static class IncorrectChrListElementsContext extends ParserRuleContext {
		public List<ChrContext> chr() {
			return getRuleContexts(ChrContext.class);
		}
		public ChrContext chr(int i) {
			return getRuleContext(ChrContext.class,i);
		}
		public IncorrectChrListElementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_incorrectChrListElements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterIncorrectChrListElements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitIncorrectChrListElements(this);
		}
	}

	public final IncorrectChrListElementsContext incorrectChrListElements() throws RecognitionException {
		IncorrectChrListElementsContext _localctx = new IncorrectChrListElementsContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_incorrectChrListElements);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1277);
			chr();
			setState(1280); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1278);
					_la = _input.LA(1);
					if ( !(_la==T__2 || _la==T__22) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(1279);
					chr();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1282); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,196,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class BreakpointsListContext extends ParserRuleContext {
		public String s = "";
		public int l = 0;
		public BreakpointsListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakpointsList; }
	 
		public BreakpointsListContext() { }
		public void copyFrom(BreakpointsListContext ctx) {
			super.copyFrom(ctx);
			this.s = ctx.s;
			this.l = ctx.l;
		}
	}
	public static class CorrectBreakpointsListContext extends BreakpointsListContext {
		public BreakpointsListElementsContext breakpointsListElements() {
			return getRuleContext(BreakpointsListElementsContext.class,0);
		}
		public CorrectBreakpointsListContext(BreakpointsListContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterCorrectBreakpointsList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitCorrectBreakpointsList(this);
		}
	}
	public static class IncorrectBreakpointsListContext extends BreakpointsListContext {
		public BreakpointsListElementsContext breakpointsListElements;
		public IncorrectLeftParenthesisContext incorrectLeftParenthesis;
		public IncorrectRightParenthesisContext incorrectRightParenthesis;
		public BreakpointsListElementsContext breakpointsListElements() {
			return getRuleContext(BreakpointsListElementsContext.class,0);
		}
		public IncorrectLeftParenthesisContext incorrectLeftParenthesis() {
			return getRuleContext(IncorrectLeftParenthesisContext.class,0);
		}
		public IncorrectRightParenthesisContext incorrectRightParenthesis() {
			return getRuleContext(IncorrectRightParenthesisContext.class,0);
		}
		public IncorrectBreakpointsListContext(BreakpointsListContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterIncorrectBreakpointsList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitIncorrectBreakpointsList(this);
		}
	}

	public final BreakpointsListContext breakpointsList() throws RecognitionException {
		BreakpointsListContext _localctx = new BreakpointsListContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_breakpointsList);
		try {
			setState(1304);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,197,_ctx) ) {
			case 1:
				_localctx = new CorrectBreakpointsListContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(1284);
				match(T__31);
				setState(1285);
				breakpointsListElements();
				setState(1286);
				match(T__32);
				}
				break;
			case 2:
				_localctx = new IncorrectBreakpointsListContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(1288);
				((IncorrectBreakpointsListContext)_localctx).breakpointsListElements = breakpointsListElements();
				setState(1289);
				match(T__32);

				  	((IncorrectBreakpointsListContext)_localctx).s =  (((IncorrectBreakpointsListContext)_localctx).breakpointsListElements!=null?_input.getText(((IncorrectBreakpointsListContext)_localctx).breakpointsListElements.start,((IncorrectBreakpointsListContext)_localctx).breakpointsListElements.stop):null);
				  	((IncorrectBreakpointsListContext)_localctx).l =  _localctx.s.length() + 1;
				  	notifyErrorListeners(_localctx.l + "|Missing '(' for breakpoints list '" + _localctx.s + "'");
				  
				}
				break;
			case 3:
				_localctx = new IncorrectBreakpointsListContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(1292);
				match(T__31);
				setState(1293);
				((IncorrectBreakpointsListContext)_localctx).breakpointsListElements = breakpointsListElements();

				  	((IncorrectBreakpointsListContext)_localctx).s =  (((IncorrectBreakpointsListContext)_localctx).breakpointsListElements!=null?_input.getText(((IncorrectBreakpointsListContext)_localctx).breakpointsListElements.start,((IncorrectBreakpointsListContext)_localctx).breakpointsListElements.stop):null);
				  	((IncorrectBreakpointsListContext)_localctx).l =  _localctx.s.length() + 1;
				  	notifyErrorListeners(_localctx.l + "|Missing ')' for breakpoints list '" + _localctx.s + "'");
				  
				}
				break;
			case 4:
				_localctx = new IncorrectBreakpointsListContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(1296);
				((IncorrectBreakpointsListContext)_localctx).breakpointsListElements = breakpointsListElements();

				  	((IncorrectBreakpointsListContext)_localctx).s =  (((IncorrectBreakpointsListContext)_localctx).breakpointsListElements!=null?_input.getText(((IncorrectBreakpointsListContext)_localctx).breakpointsListElements.start,((IncorrectBreakpointsListContext)_localctx).breakpointsListElements.stop):null);
				  	((IncorrectBreakpointsListContext)_localctx).l =  _localctx.s.length();
				  	notifyErrorListeners(_localctx.l + "|Missing '()' for breakpoints list '" + _localctx.s + "'");
				  
				}
				break;
			case 5:
				_localctx = new IncorrectBreakpointsListContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(1299);
				((IncorrectBreakpointsListContext)_localctx).incorrectLeftParenthesis = incorrectLeftParenthesis();
				setState(1300);
				((IncorrectBreakpointsListContext)_localctx).breakpointsListElements = breakpointsListElements();
				setState(1301);
				((IncorrectBreakpointsListContext)_localctx).incorrectRightParenthesis = incorrectRightParenthesis();

				  	((IncorrectBreakpointsListContext)_localctx).s =  (((IncorrectBreakpointsListContext)_localctx).breakpointsListElements!=null?_input.getText(((IncorrectBreakpointsListContext)_localctx).breakpointsListElements.start,((IncorrectBreakpointsListContext)_localctx).breakpointsListElements.stop):null);
				  	((IncorrectBreakpointsListContext)_localctx).l =  _localctx.s.length();
				  	if ((((IncorrectBreakpointsListContext)_localctx).incorrectLeftParenthesis!=null?_input.getText(((IncorrectBreakpointsListContext)_localctx).incorrectLeftParenthesis.start,((IncorrectBreakpointsListContext)_localctx).incorrectLeftParenthesis.stop):null) != null) {
				  		((IncorrectBreakpointsListContext)_localctx).l =  _localctx.l + (((IncorrectBreakpointsListContext)_localctx).incorrectLeftParenthesis!=null?_input.getText(((IncorrectBreakpointsListContext)_localctx).incorrectLeftParenthesis.start,((IncorrectBreakpointsListContext)_localctx).incorrectLeftParenthesis.stop):null).length();
				  	}
				  	if ((((IncorrectBreakpointsListContext)_localctx).incorrectRightParenthesis!=null?_input.getText(((IncorrectBreakpointsListContext)_localctx).incorrectRightParenthesis.start,((IncorrectBreakpointsListContext)_localctx).incorrectRightParenthesis.stop):null) != null) {
				  		((IncorrectBreakpointsListContext)_localctx).l =  _localctx.l + (((IncorrectBreakpointsListContext)_localctx).incorrectRightParenthesis!=null?_input.getText(((IncorrectBreakpointsListContext)_localctx).incorrectRightParenthesis.start,((IncorrectBreakpointsListContext)_localctx).incorrectRightParenthesis.stop):null).length();
				  	}
				  	notifyErrorListeners(_localctx.l + "|Wrong bracket format for breakpoints list '" + _localctx.s + "', expecting '()'");
				  
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

	public static class BreakpointsListElementsContext extends ParserRuleContext {
		public IncorrectBreakpointsListElementsContext incorrectBreakpointsListElements;
		public List<BreakpointsInOneChrContext> breakpointsInOneChr() {
			return getRuleContexts(BreakpointsInOneChrContext.class);
		}
		public BreakpointsInOneChrContext breakpointsInOneChr(int i) {
			return getRuleContext(BreakpointsInOneChrContext.class,i);
		}
		public IncorrectBreakpointsListElementsContext incorrectBreakpointsListElements() {
			return getRuleContext(IncorrectBreakpointsListElementsContext.class,0);
		}
		public BreakpointsListElementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakpointsListElements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterBreakpointsListElements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitBreakpointsListElements(this);
		}
	}

	public final BreakpointsListElementsContext breakpointsListElements() throws RecognitionException {
		BreakpointsListElementsContext _localctx = new BreakpointsListElementsContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_breakpointsListElements);
		try {
			int _alt;
			setState(1317);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,199,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1306);
				breakpointsInOneChr();
				setState(1311);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,198,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1307);
						match(T__22);
						setState(1308);
						breakpointsInOneChr();
						}
						} 
					}
					setState(1313);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,198,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1314);
				((BreakpointsListElementsContext)_localctx).incorrectBreakpointsListElements = incorrectBreakpointsListElements();

				  	notifyErrorListeners((((BreakpointsListElementsContext)_localctx).incorrectBreakpointsListElements!=null?_input.getText(((BreakpointsListElementsContext)_localctx).incorrectBreakpointsListElements.start,((BreakpointsListElementsContext)_localctx).incorrectBreakpointsListElements.stop):null).length() + "|Incorrect use of separators inside breakpoints list '" + (((BreakpointsListElementsContext)_localctx).incorrectBreakpointsListElements!=null?_input.getText(((BreakpointsListElementsContext)_localctx).incorrectBreakpointsListElements.start,((BreakpointsListElementsContext)_localctx).incorrectBreakpointsListElements.stop):null) + "', expecting ';'");
				  
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

	public static class IncorrectBreakpointsListElementsContext extends ParserRuleContext {
		public List<BreakpointsInOneChrContext> breakpointsInOneChr() {
			return getRuleContexts(BreakpointsInOneChrContext.class);
		}
		public BreakpointsInOneChrContext breakpointsInOneChr(int i) {
			return getRuleContext(BreakpointsInOneChrContext.class,i);
		}
		public IncorrectBreakpointsListElementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_incorrectBreakpointsListElements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterIncorrectBreakpointsListElements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitIncorrectBreakpointsListElements(this);
		}
	}

	public final IncorrectBreakpointsListElementsContext incorrectBreakpointsListElements() throws RecognitionException {
		IncorrectBreakpointsListElementsContext _localctx = new IncorrectBreakpointsListElementsContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_incorrectBreakpointsListElements);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1319);
			breakpointsInOneChr();
			setState(1322); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1320);
					_la = _input.LA(1);
					if ( !(_la==T__2 || _la==T__22) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(1321);
					breakpointsInOneChr();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1324); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,200,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class BreakpointsInOneChrContext extends ParserRuleContext {
		public BreakpointsInOneChrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakpointsInOneChr; }
	 
		public BreakpointsInOneChrContext() { }
		public void copyFrom(BreakpointsInOneChrContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NormalBreakpointsContext extends BreakpointsInOneChrContext {
		public BreakpointsContext breakpoints() {
			return getRuleContext(BreakpointsContext.class,0);
		}
		public NormalBreakpointsContext(BreakpointsInOneChrContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterNormalBreakpoints(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitNormalBreakpoints(this);
		}
	}
	public static class MultipleInterpretationBreakpointsContext extends BreakpointsInOneChrContext {
		public List<BreakpointsContext> breakpoints() {
			return getRuleContexts(BreakpointsContext.class);
		}
		public BreakpointsContext breakpoints(int i) {
			return getRuleContext(BreakpointsContext.class,i);
		}
		public MultipleInterpretationBreakpointsContext(BreakpointsInOneChrContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterMultipleInterpretationBreakpoints(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitMultipleInterpretationBreakpoints(this);
		}
	}

	public final BreakpointsInOneChrContext breakpointsInOneChr() throws RecognitionException {
		BreakpointsInOneChrContext _localctx = new BreakpointsInOneChrContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_breakpointsInOneChr);
		int _la;
		try {
			int _alt;
			setState(1335);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,202,_ctx) ) {
			case 1:
				_localctx = new NormalBreakpointsContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(1326);
				breakpoints();
				}
				break;
			case 2:
				_localctx = new MultipleInterpretationBreakpointsContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(1327);
				breakpoints();
				setState(1331); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(1328);
						_la = _input.LA(1);
						if ( !(_la==T__5 || _la==T__6) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1329);
						_la = _input.LA(1);
						if ( !(_la==T__15 || _la==T__16) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1330);
						breakpoints();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(1333); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,201,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class RChrListContext extends ParserRuleContext {
		public String s = "";
		public int l = 0;
		public RChrListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rChrList; }
	 
		public RChrListContext() { }
		public void copyFrom(RChrListContext ctx) {
			super.copyFrom(ctx);
			this.s = ctx.s;
			this.l = ctx.l;
		}
	}
	public static class CorrectRChrListContext extends RChrListContext {
		public ChrListElementsContext chrListElements() {
			return getRuleContext(ChrListElementsContext.class,0);
		}
		public CorrectRChrListContext(RChrListContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterCorrectRChrList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitCorrectRChrList(this);
		}
	}
	public static class IncorrectRChrListContext extends RChrListContext {
		public ChrListElementsContext chrListElements;
		public IncorrectLeftParenthesisContext incorrectLeftParenthesis;
		public IncorrectRightParenthesisContext incorrectRightParenthesis;
		public ChrListElementsContext chrListElements() {
			return getRuleContext(ChrListElementsContext.class,0);
		}
		public IncorrectLeftParenthesisContext incorrectLeftParenthesis() {
			return getRuleContext(IncorrectLeftParenthesisContext.class,0);
		}
		public IncorrectRightParenthesisContext incorrectRightParenthesis() {
			return getRuleContext(IncorrectRightParenthesisContext.class,0);
		}
		public IncorrectRChrListContext(RChrListContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterIncorrectRChrList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitIncorrectRChrList(this);
		}
	}

	public final RChrListContext rChrList() throws RecognitionException {
		RChrListContext _localctx = new RChrListContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_rChrList);
		try {
			setState(1357);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,203,_ctx) ) {
			case 1:
				_localctx = new CorrectRChrListContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(1337);
				match(T__31);
				setState(1338);
				chrListElements();
				setState(1339);
				match(T__32);
				}
				break;
			case 2:
				_localctx = new IncorrectRChrListContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(1341);
				((IncorrectRChrListContext)_localctx).chrListElements = chrListElements();
				setState(1342);
				match(T__32);

				  	((IncorrectRChrListContext)_localctx).s =  (((IncorrectRChrListContext)_localctx).chrListElements!=null?_input.getText(((IncorrectRChrListContext)_localctx).chrListElements.start,((IncorrectRChrListContext)_localctx).chrListElements.stop):null);
				  	((IncorrectRChrListContext)_localctx).l =  _localctx.s.length() + 1;
				  	notifyErrorListeners(_localctx.l + "|Missing '(' for chromosome list '" + _localctx.s + "'");
				  
				}
				break;
			case 3:
				_localctx = new IncorrectRChrListContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(1345);
				match(T__31);
				setState(1346);
				((IncorrectRChrListContext)_localctx).chrListElements = chrListElements();

				  	((IncorrectRChrListContext)_localctx).s =  (((IncorrectRChrListContext)_localctx).chrListElements!=null?_input.getText(((IncorrectRChrListContext)_localctx).chrListElements.start,((IncorrectRChrListContext)_localctx).chrListElements.stop):null);
				  	((IncorrectRChrListContext)_localctx).l =  _localctx.s.length() + 1;
				  	notifyErrorListeners(_localctx.l + "|Missing ')' for chromosome list '" + _localctx.s + "'");
				  
				}
				break;
			case 4:
				_localctx = new IncorrectRChrListContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(1349);
				((IncorrectRChrListContext)_localctx).chrListElements = chrListElements();

				  	((IncorrectRChrListContext)_localctx).s =  (((IncorrectRChrListContext)_localctx).chrListElements!=null?_input.getText(((IncorrectRChrListContext)_localctx).chrListElements.start,((IncorrectRChrListContext)_localctx).chrListElements.stop):null);
				  	((IncorrectRChrListContext)_localctx).l =  _localctx.s.length();
				  	notifyErrorListeners(_localctx.l + "|Missing '()' for chromosome list '" + _localctx.s + "'");
				  
				}
				break;
			case 5:
				_localctx = new IncorrectRChrListContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(1352);
				((IncorrectRChrListContext)_localctx).incorrectLeftParenthesis = incorrectLeftParenthesis();
				setState(1353);
				((IncorrectRChrListContext)_localctx).chrListElements = chrListElements();
				setState(1354);
				((IncorrectRChrListContext)_localctx).incorrectRightParenthesis = incorrectRightParenthesis();

				  	((IncorrectRChrListContext)_localctx).s =  (((IncorrectRChrListContext)_localctx).chrListElements!=null?_input.getText(((IncorrectRChrListContext)_localctx).chrListElements.start,((IncorrectRChrListContext)_localctx).chrListElements.stop):null);
				  	((IncorrectRChrListContext)_localctx).l =  _localctx.s.length();
				  	if ((((IncorrectRChrListContext)_localctx).incorrectLeftParenthesis!=null?_input.getText(((IncorrectRChrListContext)_localctx).incorrectLeftParenthesis.start,((IncorrectRChrListContext)_localctx).incorrectLeftParenthesis.stop):null) != null) {
				  		((IncorrectRChrListContext)_localctx).l =  _localctx.l + (((IncorrectRChrListContext)_localctx).incorrectLeftParenthesis!=null?_input.getText(((IncorrectRChrListContext)_localctx).incorrectLeftParenthesis.start,((IncorrectRChrListContext)_localctx).incorrectLeftParenthesis.stop):null).length();
				  	}
				  	if ((((IncorrectRChrListContext)_localctx).incorrectRightParenthesis!=null?_input.getText(((IncorrectRChrListContext)_localctx).incorrectRightParenthesis.start,((IncorrectRChrListContext)_localctx).incorrectRightParenthesis.stop):null) != null) {
				  		((IncorrectRChrListContext)_localctx).l =  _localctx.l + (((IncorrectRChrListContext)_localctx).incorrectRightParenthesis!=null?_input.getText(((IncorrectRChrListContext)_localctx).incorrectRightParenthesis.start,((IncorrectRChrListContext)_localctx).incorrectRightParenthesis.stop):null).length();
				  	}
				  	notifyErrorListeners(_localctx.l + "|Wrong bracket format for chromosome list '" + _localctx.s + "', expecting '()'");
				  
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

	public static class RBreakpointsListContext extends ParserRuleContext {
		public String s = "";
		public int l = 0;
		public RBreakpointsListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rBreakpointsList; }
	 
		public RBreakpointsListContext() { }
		public void copyFrom(RBreakpointsListContext ctx) {
			super.copyFrom(ctx);
			this.s = ctx.s;
			this.l = ctx.l;
		}
	}
	public static class CorrectRBreakpointsListContext extends RBreakpointsListContext {
		public BreakpointsListElementsContext breakpointsListElements() {
			return getRuleContext(BreakpointsListElementsContext.class,0);
		}
		public CorrectRBreakpointsListContext(RBreakpointsListContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterCorrectRBreakpointsList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitCorrectRBreakpointsList(this);
		}
	}
	public static class IncorrectRBreakpointsListContext extends RBreakpointsListContext {
		public BreakpointsListElementsContext breakpointsListElements;
		public IncorrectLeftParenthesisContext incorrectLeftParenthesis;
		public IncorrectRightParenthesisContext incorrectRightParenthesis;
		public BreakpointsListElementsContext breakpointsListElements() {
			return getRuleContext(BreakpointsListElementsContext.class,0);
		}
		public IncorrectLeftParenthesisContext incorrectLeftParenthesis() {
			return getRuleContext(IncorrectLeftParenthesisContext.class,0);
		}
		public IncorrectRightParenthesisContext incorrectRightParenthesis() {
			return getRuleContext(IncorrectRightParenthesisContext.class,0);
		}
		public IncorrectRBreakpointsListContext(RBreakpointsListContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterIncorrectRBreakpointsList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitIncorrectRBreakpointsList(this);
		}
	}

	public final RBreakpointsListContext rBreakpointsList() throws RecognitionException {
		RBreakpointsListContext _localctx = new RBreakpointsListContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_rBreakpointsList);
		try {
			setState(1379);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,204,_ctx) ) {
			case 1:
				_localctx = new CorrectRBreakpointsListContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(1359);
				match(T__31);
				setState(1360);
				breakpointsListElements();
				setState(1361);
				match(T__32);
				}
				break;
			case 2:
				_localctx = new IncorrectRBreakpointsListContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(1363);
				((IncorrectRBreakpointsListContext)_localctx).breakpointsListElements = breakpointsListElements();
				setState(1364);
				match(T__32);

				  	((IncorrectRBreakpointsListContext)_localctx).s =  (((IncorrectRBreakpointsListContext)_localctx).breakpointsListElements!=null?_input.getText(((IncorrectRBreakpointsListContext)_localctx).breakpointsListElements.start,((IncorrectRBreakpointsListContext)_localctx).breakpointsListElements.stop):null);
				  	((IncorrectRBreakpointsListContext)_localctx).l =  _localctx.s.length() + 1;
				  	notifyErrorListeners(_localctx.l + "|Missing '(' for breakpoints list '" + _localctx.s + "'");
				  
				}
				break;
			case 3:
				_localctx = new IncorrectRBreakpointsListContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(1367);
				match(T__31);
				setState(1368);
				((IncorrectRBreakpointsListContext)_localctx).breakpointsListElements = breakpointsListElements();

				  	((IncorrectRBreakpointsListContext)_localctx).s =  (((IncorrectRBreakpointsListContext)_localctx).breakpointsListElements!=null?_input.getText(((IncorrectRBreakpointsListContext)_localctx).breakpointsListElements.start,((IncorrectRBreakpointsListContext)_localctx).breakpointsListElements.stop):null);
				  	((IncorrectRBreakpointsListContext)_localctx).l =  _localctx.s.length() + 1;
				  	notifyErrorListeners(_localctx.l + "|Missing ')' for breakpoints list '" + _localctx.s + "'");
				  
				}
				break;
			case 4:
				_localctx = new IncorrectRBreakpointsListContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(1371);
				((IncorrectRBreakpointsListContext)_localctx).breakpointsListElements = breakpointsListElements();

				  	((IncorrectRBreakpointsListContext)_localctx).s =  (((IncorrectRBreakpointsListContext)_localctx).breakpointsListElements!=null?_input.getText(((IncorrectRBreakpointsListContext)_localctx).breakpointsListElements.start,((IncorrectRBreakpointsListContext)_localctx).breakpointsListElements.stop):null);
				  	((IncorrectRBreakpointsListContext)_localctx).l =  _localctx.s.length();
				  	notifyErrorListeners(_localctx.l + "|Missing '()' for breakpoints list '" + _localctx.s + "'");
				  
				}
				break;
			case 5:
				_localctx = new IncorrectRBreakpointsListContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(1374);
				((IncorrectRBreakpointsListContext)_localctx).incorrectLeftParenthesis = incorrectLeftParenthesis();
				setState(1375);
				((IncorrectRBreakpointsListContext)_localctx).breakpointsListElements = breakpointsListElements();
				setState(1376);
				((IncorrectRBreakpointsListContext)_localctx).incorrectRightParenthesis = incorrectRightParenthesis();

				  	((IncorrectRBreakpointsListContext)_localctx).s =  (((IncorrectRBreakpointsListContext)_localctx).breakpointsListElements!=null?_input.getText(((IncorrectRBreakpointsListContext)_localctx).breakpointsListElements.start,((IncorrectRBreakpointsListContext)_localctx).breakpointsListElements.stop):null);
				  	((IncorrectRBreakpointsListContext)_localctx).l =  _localctx.s.length();
				  	if ((((IncorrectRBreakpointsListContext)_localctx).incorrectLeftParenthesis!=null?_input.getText(((IncorrectRBreakpointsListContext)_localctx).incorrectLeftParenthesis.start,((IncorrectRBreakpointsListContext)_localctx).incorrectLeftParenthesis.stop):null) != null) {
				  		((IncorrectRBreakpointsListContext)_localctx).l =  _localctx.l + (((IncorrectRBreakpointsListContext)_localctx).incorrectLeftParenthesis!=null?_input.getText(((IncorrectRBreakpointsListContext)_localctx).incorrectLeftParenthesis.start,((IncorrectRBreakpointsListContext)_localctx).incorrectLeftParenthesis.stop):null).length();
				  	}
				  	if ((((IncorrectRBreakpointsListContext)_localctx).incorrectRightParenthesis!=null?_input.getText(((IncorrectRBreakpointsListContext)_localctx).incorrectRightParenthesis.start,((IncorrectRBreakpointsListContext)_localctx).incorrectRightParenthesis.stop):null) != null) {
				  		((IncorrectRBreakpointsListContext)_localctx).l =  _localctx.l + (((IncorrectRBreakpointsListContext)_localctx).incorrectRightParenthesis!=null?_input.getText(((IncorrectRBreakpointsListContext)_localctx).incorrectRightParenthesis.start,((IncorrectRBreakpointsListContext)_localctx).incorrectRightParenthesis.stop):null).length();
				  	}
				  	notifyErrorListeners(_localctx.l + "|Wrong bracket format for breakpoints list '" + _localctx.s + "', expecting '()'");
				  
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

	public static class IdUncertainChrContext extends ParserRuleContext {
		public IdUncertainChrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idUncertainChr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterIdUncertainChr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitIdUncertainChr(this);
		}
	}

	public final IdUncertainChrContext idUncertainChr() throws RecognitionException {
		IdUncertainChrContext _localctx = new IdUncertainChrContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_idUncertainChr);
		int _la;
		try {
			setState(1415);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,206,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1381);
				_la = _input.LA(1);
				if ( !(_la==T__11 || _la==T__12) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1382);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1383);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1384);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1385);
				_la = _input.LA(1);
				if ( !(_la==T__19 || _la==T__20) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1386);
				_la = _input.LA(1);
				if ( !(_la==T__9 || _la==T__10) ) {
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
				enterOuterAlt(_localctx, 3);
				{
				setState(1390);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__37 || _la==T__38) {
					{
					setState(1387);
					_la = _input.LA(1);
					if ( !(_la==T__37 || _la==T__38) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(1388);
					_la = _input.LA(1);
					if ( !(_la==T__29 || _la==T__30) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(1389);
					_la = _input.LA(1);
					if ( !(_la==T__39 || _la==T__40) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(1392);
				_la = _input.LA(1);
				if ( !(_la==T__25 || _la==T__26) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1393);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1394);
				_la = _input.LA(1);
				if ( !(_la==T__25 || _la==T__26) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1395);
				_la = _input.LA(1);
				if ( !(_la==T__7 || _la==T__8) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1396);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1397);
				_la = _input.LA(1);
				if ( !(_la==T__39 || _la==T__40) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1398);
				_la = _input.LA(1);
				if ( !(_la==T__37 || _la==T__38) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1399);
				_la = _input.LA(1);
				if ( !(_la==T__41 || _la==T__42) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1400);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__16) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1401);
				_la = _input.LA(1);
				if ( !(_la==T__37 || _la==T__38) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1402);
				_la = _input.LA(1);
				if ( !(_la==T__43 || _la==T__44) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1403);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1404);
				_la = _input.LA(1);
				if ( !(_la==T__37 || _la==T__38) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(1405);
				_la = _input.LA(1);
				if ( !(_la==T__45 || _la==T__46) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1406);
				_la = _input.LA(1);
				if ( !(_la==T__29 || _la==T__30) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1407);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__16) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(1408);
				_la = _input.LA(1);
				if ( !(_la==T__25 || _la==T__26) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1409);
				_la = _input.LA(1);
				if ( !(_la==T__3 || _la==T__4) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1410);
				_la = _input.LA(1);
				if ( !(_la==T__29 || _la==T__30) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(1411);
				_la = _input.LA(1);
				if ( !(_la==T__25 || _la==T__26) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1412);
				_la = _input.LA(1);
				if ( !(_la==T__3 || _la==T__4) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1413);
				_la = _input.LA(1);
				if ( !(_la==T__47 || _la==T__48) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(1414);
				_la = _input.LA(1);
				if ( !(_la==T__25 || _la==T__26) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
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

	public static class UncertainChrContext extends ParserRuleContext {
		public String s = "";
		public int l = 0;
		public UncertainChrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_uncertainChr; }
	 
		public UncertainChrContext() { }
		public void copyFrom(UncertainChrContext ctx) {
			super.copyFrom(ctx);
			this.s = ctx.s;
			this.l = ctx.l;
		}
	}
	public static class IncorrectUncertainChrContext extends UncertainChrContext {
		public UncertainChrContentContext uncertainChrContent;
		public IncorrectLeftParenthesisContext incorrectLeftParenthesis;
		public IncorrectRightParenthesisContext incorrectRightParenthesis;
		public UncertainChrContentContext uncertainChrContent() {
			return getRuleContext(UncertainChrContentContext.class,0);
		}
		public IncorrectLeftParenthesisContext incorrectLeftParenthesis() {
			return getRuleContext(IncorrectLeftParenthesisContext.class,0);
		}
		public IncorrectRightParenthesisContext incorrectRightParenthesis() {
			return getRuleContext(IncorrectRightParenthesisContext.class,0);
		}
		public IncorrectUncertainChrContext(UncertainChrContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterIncorrectUncertainChr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitIncorrectUncertainChr(this);
		}
	}
	public static class CorrectUncertainChrContext extends UncertainChrContext {
		public UncertainChrContentContext uncertainChrContent() {
			return getRuleContext(UncertainChrContentContext.class,0);
		}
		public CorrectUncertainChrContext(UncertainChrContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterCorrectUncertainChr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitCorrectUncertainChr(this);
		}
	}

	public final UncertainChrContext uncertainChr() throws RecognitionException {
		UncertainChrContext _localctx = new UncertainChrContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_uncertainChr);
		try {
			setState(1437);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,207,_ctx) ) {
			case 1:
				_localctx = new CorrectUncertainChrContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(1417);
				match(T__31);
				setState(1418);
				uncertainChrContent();
				setState(1419);
				match(T__32);
				}
				break;
			case 2:
				_localctx = new IncorrectUncertainChrContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(1421);
				((IncorrectUncertainChrContext)_localctx).uncertainChrContent = uncertainChrContent();
				setState(1422);
				match(T__32);

				  	((IncorrectUncertainChrContext)_localctx).s =  (((IncorrectUncertainChrContext)_localctx).uncertainChrContent!=null?_input.getText(((IncorrectUncertainChrContext)_localctx).uncertainChrContent.start,((IncorrectUncertainChrContext)_localctx).uncertainChrContent.stop):null);
				  	((IncorrectUncertainChrContext)_localctx).l =  _localctx.s.length() + 1;
				  	notifyErrorListeners(_localctx.l + "|Missing '(' for an uncertain chr '" + _localctx.s + "'");
				  
				}
				break;
			case 3:
				_localctx = new IncorrectUncertainChrContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(1425);
				match(T__31);
				setState(1426);
				((IncorrectUncertainChrContext)_localctx).uncertainChrContent = uncertainChrContent();

				  	((IncorrectUncertainChrContext)_localctx).s =  (((IncorrectUncertainChrContext)_localctx).uncertainChrContent!=null?_input.getText(((IncorrectUncertainChrContext)_localctx).uncertainChrContent.start,((IncorrectUncertainChrContext)_localctx).uncertainChrContent.stop):null);
				  	((IncorrectUncertainChrContext)_localctx).l =  _localctx.s.length() + 1;
				  	notifyErrorListeners(_localctx.l + "|Missing ')' for an uncertain chr '" + _localctx.s + "'");
				  
				}
				break;
			case 4:
				_localctx = new IncorrectUncertainChrContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(1429);
				((IncorrectUncertainChrContext)_localctx).uncertainChrContent = uncertainChrContent();

				  	((IncorrectUncertainChrContext)_localctx).s =  (((IncorrectUncertainChrContext)_localctx).uncertainChrContent!=null?_input.getText(((IncorrectUncertainChrContext)_localctx).uncertainChrContent.start,((IncorrectUncertainChrContext)_localctx).uncertainChrContent.stop):null);
				  	((IncorrectUncertainChrContext)_localctx).l =  _localctx.s.length();
				  	notifyErrorListeners(_localctx.l + "|Missing '()' for an uncertain chr '" + _localctx.s + "'");
				  
				}
				break;
			case 5:
				_localctx = new IncorrectUncertainChrContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(1432);
				((IncorrectUncertainChrContext)_localctx).incorrectLeftParenthesis = incorrectLeftParenthesis();
				setState(1433);
				((IncorrectUncertainChrContext)_localctx).uncertainChrContent = uncertainChrContent();
				setState(1434);
				((IncorrectUncertainChrContext)_localctx).incorrectRightParenthesis = incorrectRightParenthesis();

				  	((IncorrectUncertainChrContext)_localctx).s =  (((IncorrectUncertainChrContext)_localctx).uncertainChrContent!=null?_input.getText(((IncorrectUncertainChrContext)_localctx).uncertainChrContent.start,((IncorrectUncertainChrContext)_localctx).uncertainChrContent.stop):null);
				  	((IncorrectUncertainChrContext)_localctx).l =  _localctx.s.length();
				  	if ((((IncorrectUncertainChrContext)_localctx).incorrectLeftParenthesis!=null?_input.getText(((IncorrectUncertainChrContext)_localctx).incorrectLeftParenthesis.start,((IncorrectUncertainChrContext)_localctx).incorrectLeftParenthesis.stop):null) != null) {
				  		((IncorrectUncertainChrContext)_localctx).l =  _localctx.l + (((IncorrectUncertainChrContext)_localctx).incorrectLeftParenthesis!=null?_input.getText(((IncorrectUncertainChrContext)_localctx).incorrectLeftParenthesis.start,((IncorrectUncertainChrContext)_localctx).incorrectLeftParenthesis.stop):null).length();
				  	}
				  	if ((((IncorrectUncertainChrContext)_localctx).incorrectRightParenthesis!=null?_input.getText(((IncorrectUncertainChrContext)_localctx).incorrectRightParenthesis.start,((IncorrectUncertainChrContext)_localctx).incorrectRightParenthesis.stop):null) != null) {
				  		((IncorrectUncertainChrContext)_localctx).l =  _localctx.l + (((IncorrectUncertainChrContext)_localctx).incorrectRightParenthesis!=null?_input.getText(((IncorrectUncertainChrContext)_localctx).incorrectRightParenthesis.start,((IncorrectUncertainChrContext)_localctx).incorrectRightParenthesis.stop):null).length();
				  	}
				  	notifyErrorListeners(_localctx.l + "|Wrong bracket format for an uncertain chr '" + _localctx.s + "', expecting '()'");
				  
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

	public static class UncertainChrContentContext extends ParserRuleContext {
		public ChrContext chr() {
			return getRuleContext(ChrContext.class,0);
		}
		public ArmContext arm() {
			return getRuleContext(ArmContext.class,0);
		}
		public TerminalNode QUES() { return getToken(KaryotypeParser.QUES, 0); }
		public UncertainChrContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_uncertainChrContent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterUncertainChrContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitUncertainChrContent(this);
		}
	}

	public final UncertainChrContentContext uncertainChrContent() throws RecognitionException {
		UncertainChrContentContext _localctx = new UncertainChrContentContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_uncertainChrContent);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1439);
			chr();
			setState(1441);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QUES) {
				{
				setState(1440);
				match(QUES);
				}
			}

			setState(1443);
			arm();
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

	public static class ArmContext extends ParserRuleContext {
		public ArmContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterArm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitArm(this);
		}
	}

	public final ArmContext arm() throws RecognitionException {
		ArmContext _localctx = new ArmContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_arm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1445);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__37) | (1L << T__38) | (1L << T__43) | (1L << T__44))) != 0)) ) {
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

	public static class BandContext extends ParserRuleContext {
		public ArmContext arm() {
			return getRuleContext(ArmContext.class,0);
		}
		public List<TerminalNode> QUES() { return getTokens(KaryotypeParser.QUES); }
		public TerminalNode QUES(int i) {
			return getToken(KaryotypeParser.QUES, i);
		}
		public List<DigitContext> digit() {
			return getRuleContexts(DigitContext.class);
		}
		public DigitContext digit(int i) {
			return getRuleContext(DigitContext.class,i);
		}
		public NumRangeTypeIIContext numRangeTypeII() {
			return getRuleContext(NumRangeTypeIIContext.class,0);
		}
		public BandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_band; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterBand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitBand(this);
		}
	}

	public final BandContext band() throws RecognitionException {
		BandContext _localctx = new BandContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_band);
		int _la;
		try {
			int _alt;
			setState(1468);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,214,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1448);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==QUES) {
					{
					setState(1447);
					match(QUES);
					}
				}

				setState(1450);
				arm();
				setState(1453); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						setState(1453);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case T__49:
						case POSITIVEDIGIT:
							{
							setState(1451);
							digit();
							}
							break;
						case QUES:
							{
							setState(1452);
							match(QUES);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(1455); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,211,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1457);
				match(QUES);
				setState(1460); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						setState(1460);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case T__49:
						case POSITIVEDIGIT:
							{
							setState(1458);
							digit();
							}
							break;
						case QUES:
							{
							setState(1459);
							match(QUES);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(1462); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,213,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1464);
				match(QUES);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1465);
				arm();
				setState(1466);
				numRangeTypeII();
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

	public static class BreakpointContext extends ParserRuleContext {
		public BandContext band() {
			return getRuleContext(BandContext.class,0);
		}
		public SubbandContext subband() {
			return getRuleContext(SubbandContext.class,0);
		}
		public BreakpointContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakpoint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterBreakpoint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitBreakpoint(this);
		}
	}

	public final BreakpointContext breakpoint() throws RecognitionException {
		BreakpointContext _localctx = new BreakpointContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_breakpoint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1470);
			band();
			setState(1472);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,215,_ctx) ) {
			case 1:
				{
				setState(1471);
				subband();
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

	public static class BreakpointsContext extends ParserRuleContext {
		public List<BreakpointContext> breakpoint() {
			return getRuleContexts(BreakpointContext.class);
		}
		public BreakpointContext breakpoint(int i) {
			return getRuleContext(BreakpointContext.class,i);
		}
		public BreakpointsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakpoints; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterBreakpoints(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitBreakpoints(this);
		}
	}

	public final BreakpointsContext breakpoints() throws RecognitionException {
		BreakpointsContext _localctx = new BreakpointsContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_breakpoints);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1475); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1474);
					breakpoint();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1477); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,216,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class CContext extends ParserRuleContext {
		public CContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_c; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterC(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitC(this);
		}
	}

	public final CContext c() throws RecognitionException {
		CContext _localctx = new CContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_c);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1479);
			_la = _input.LA(1);
			if ( !(_la==T__7 || _la==T__8) ) {
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

	public static class CellNumContext extends ParserRuleContext {
		public int l = 0;
		public CellNumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cellNum; }
	 
		public CellNumContext() { }
		public void copyFrom(CellNumContext ctx) {
			super.copyFrom(ctx);
			this.l = ctx.l;
		}
	}
	public static class IncorrectCellNumContext extends CellNumContext {
		public CellNumContentContext cellNumContent;
		public IncorrectLeftParenthesisIIContext incorrectLeftParenthesisII;
		public IncorrectRightParenthesisContext incorrectRightParenthesis;
		public CpContext cp;
		public Token s;
		public CellNumContentContext cellNumContent() {
			return getRuleContext(CellNumContentContext.class,0);
		}
		public IncorrectLeftParenthesisIIContext incorrectLeftParenthesisII() {
			return getRuleContext(IncorrectLeftParenthesisIIContext.class,0);
		}
		public IncorrectRightParenthesisContext incorrectRightParenthesis() {
			return getRuleContext(IncorrectRightParenthesisContext.class,0);
		}
		public CpContext cp() {
			return getRuleContext(CpContext.class,0);
		}
		public IncorrectCellNumContext(CellNumContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterIncorrectCellNum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitIncorrectCellNum(this);
		}
	}
	public static class CorrectCellNumContext extends CellNumContext {
		public CellNumContentContext cellNumContent() {
			return getRuleContext(CellNumContentContext.class,0);
		}
		public CorrectCellNumContext(CellNumContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterCorrectCellNum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitCorrectCellNum(this);
		}
	}

	public final CellNumContext cellNum() throws RecognitionException {
		CellNumContext _localctx = new CellNumContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_cellNum);
		int _la;
		try {
			setState(1517);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,219,_ctx) ) {
			case 1:
				_localctx = new CorrectCellNumContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(1481);
				match(T__33);
				setState(1482);
				cellNumContent();
				setState(1483);
				match(T__35);
				}
				break;
			case 2:
				_localctx = new IncorrectCellNumContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(1485);
				((IncorrectCellNumContext)_localctx).cellNumContent = cellNumContent();
				setState(1486);
				match(T__35);

				  	((IncorrectCellNumContext)_localctx).l =  (((IncorrectCellNumContext)_localctx).cellNumContent!=null?_input.getText(((IncorrectCellNumContext)_localctx).cellNumContent.start,((IncorrectCellNumContext)_localctx).cellNumContent.stop):null).length() + 1;
				  	notifyErrorListeners(_localctx.l + "|Missing '[' for the cell number designation");
				  
				}
				break;
			case 3:
				_localctx = new IncorrectCellNumContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(1489);
				match(T__33);
				setState(1490);
				((IncorrectCellNumContext)_localctx).cellNumContent = cellNumContent();

				  	((IncorrectCellNumContext)_localctx).l =  (((IncorrectCellNumContext)_localctx).cellNumContent!=null?_input.getText(((IncorrectCellNumContext)_localctx).cellNumContent.start,((IncorrectCellNumContext)_localctx).cellNumContent.stop):null).length() + 1;
				  	notifyErrorListeners(_localctx.l + "|Missing ']' for the cell number designation");
				  
				}
				break;
			case 4:
				_localctx = new IncorrectCellNumContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(1493);
				((IncorrectCellNumContext)_localctx).incorrectLeftParenthesisII = incorrectLeftParenthesisII();
				setState(1494);
				((IncorrectCellNumContext)_localctx).cellNumContent = cellNumContent();
				setState(1495);
				((IncorrectCellNumContext)_localctx).incorrectRightParenthesis = incorrectRightParenthesis();

				  	((IncorrectCellNumContext)_localctx).l =  (((IncorrectCellNumContext)_localctx).cellNumContent!=null?_input.getText(((IncorrectCellNumContext)_localctx).cellNumContent.start,((IncorrectCellNumContext)_localctx).cellNumContent.stop):null).length();
				  	((IncorrectCellNumContext)_localctx).l =  _localctx.l + (((IncorrectCellNumContext)_localctx).incorrectLeftParenthesisII!=null?_input.getText(((IncorrectCellNumContext)_localctx).incorrectLeftParenthesisII.start,((IncorrectCellNumContext)_localctx).incorrectLeftParenthesisII.stop):null).length();
				  	if ((((IncorrectCellNumContext)_localctx).incorrectRightParenthesis!=null?_input.getText(((IncorrectCellNumContext)_localctx).incorrectRightParenthesis.start,((IncorrectCellNumContext)_localctx).incorrectRightParenthesis.stop):null) != null) {
				  		((IncorrectCellNumContext)_localctx).l =  _localctx.l + (((IncorrectCellNumContext)_localctx).incorrectRightParenthesis!=null?_input.getText(((IncorrectCellNumContext)_localctx).incorrectRightParenthesis.start,((IncorrectCellNumContext)_localctx).incorrectRightParenthesis.stop):null).length();
				  	}
				  	notifyErrorListeners(_localctx.l + "|Wrong bracket format for the cell number designation");
				  
				}
				break;
			case 5:
				_localctx = new IncorrectCellNumContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(1499);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__7 || _la==T__8) {
					{
					setState(1498);
					((IncorrectCellNumContext)_localctx).cp = cp();
					}
				}

				setState(1501);
				_la = _input.LA(1);
				if ( !(_la==T__25 || _la==T__26) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1502);
				_la = _input.LA(1);
				if ( !(_la==T__3 || _la==T__4) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1503);
				((IncorrectCellNumContext)_localctx).cellNumContent = cellNumContent();

				  	((IncorrectCellNumContext)_localctx).l =  (((IncorrectCellNumContext)_localctx).cellNumContent!=null?_input.getText(((IncorrectCellNumContext)_localctx).cellNumContent.start,((IncorrectCellNumContext)_localctx).cellNumContent.stop):null).length() + 2;
				  	if ((((IncorrectCellNumContext)_localctx).cp!=null?_input.getText(((IncorrectCellNumContext)_localctx).cp.start,((IncorrectCellNumContext)_localctx).cp.stop):null) != null) {
				  		((IncorrectCellNumContext)_localctx).l =  _localctx.l + (((IncorrectCellNumContext)_localctx).cp!=null?_input.getText(((IncorrectCellNumContext)_localctx).cp.start,((IncorrectCellNumContext)_localctx).cp.stop):null).length();
				  	}
				  	notifyErrorListeners(_localctx.l + "|Wrong expression for the cell number designation");
				  
				}
				break;
			case 6:
				_localctx = new IncorrectCellNumContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(1506);
				((IncorrectCellNumContext)_localctx).cellNumContent = cellNumContent();
				setState(1507);
				_la = _input.LA(1);
				if ( !(_la==T__7 || _la==T__8) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1508);
				_la = _input.LA(1);
				if ( !(_la==T__19 || _la==T__20) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1509);
				_la = _input.LA(1);
				if ( !(_la==T__9 || _la==T__10) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1510);
				_la = _input.LA(1);
				if ( !(_la==T__9 || _la==T__10) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1513);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,218,_ctx) ) {
				case 1:
					{
					setState(1511);
					((IncorrectCellNumContext)_localctx).s = match(T__29);
					}
					break;
				case 2:
					{
					setState(1512);
					((IncorrectCellNumContext)_localctx).s = match(T__30);
					}
					break;
				}

				  	((IncorrectCellNumContext)_localctx).l =  (((IncorrectCellNumContext)_localctx).cellNumContent!=null?_input.getText(((IncorrectCellNumContext)_localctx).cellNumContent.start,((IncorrectCellNumContext)_localctx).cellNumContent.stop):null).length() + 4;
				  	if ((((IncorrectCellNumContext)_localctx).s!=null?((IncorrectCellNumContext)_localctx).s.getText():null) != null) {
				  		((IncorrectCellNumContext)_localctx).l =  _localctx.l + 1;
				  	}
				  	notifyErrorListeners(_localctx.l + "|Wrong expression for the cell number designation");
				  
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

	public static class CellNumContentContext extends ParserRuleContext {
		public int l = 0;
		public IntegerContext integer;
		public CpContext cp;
		public Token s;
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public CpContext cp() {
			return getRuleContext(CpContext.class,0);
		}
		public CellNumContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cellNumContent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterCellNumContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitCellNumContent(this);
		}
	}

	public final CellNumContentContext cellNumContent() throws RecognitionException {
		CellNumContentContext _localctx = new CellNumContentContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_cellNumContent);
		int _la;
		try {
			setState(1553);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,224,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1520);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__7 || _la==T__8) {
					{
					setState(1519);
					cp();
					}
				}

				setState(1522);
				integer();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1523);
				((CellNumContentContext)_localctx).integer = integer();
				setState(1524);
				cp();

				  	((CellNumContentContext)_localctx).l =  (((CellNumContentContext)_localctx).integer!=null?_input.getText(((CellNumContentContext)_localctx).integer.start,((CellNumContentContext)_localctx).integer.stop):null).length() + 2;
				  	notifyErrorListeners(_localctx.l + "|Incorrect expression of composite karyotype (cp)");
				  
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1528);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__7 || _la==T__8) {
					{
					setState(1527);
					((CellNumContentContext)_localctx).cp = cp();
					}
				}

				setState(1530);
				((CellNumContentContext)_localctx).integer = integer();
				setState(1531);
				_la = _input.LA(1);
				if ( !(_la==T__7 || _la==T__8) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1532);
				_la = _input.LA(1);
				if ( !(_la==T__19 || _la==T__20) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1533);
				_la = _input.LA(1);
				if ( !(_la==T__9 || _la==T__10) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1534);
				_la = _input.LA(1);
				if ( !(_la==T__9 || _la==T__10) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1537);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,222,_ctx) ) {
				case 1:
					{
					setState(1535);
					((CellNumContentContext)_localctx).s = match(T__29);
					}
					break;
				case 2:
					{
					setState(1536);
					((CellNumContentContext)_localctx).s = match(T__30);
					}
					break;
				}

				  	((CellNumContentContext)_localctx).l =  (((CellNumContentContext)_localctx).integer!=null?_input.getText(((CellNumContentContext)_localctx).integer.start,((CellNumContentContext)_localctx).integer.stop):null).length() + 4;
				  	if ((((CellNumContentContext)_localctx).cp!=null?_input.getText(((CellNumContentContext)_localctx).cp.start,((CellNumContentContext)_localctx).cp.stop):null) != null) {
				  		((CellNumContentContext)_localctx).l =  _localctx.l + 2;
				  	}
				  	if ((((CellNumContentContext)_localctx).s!=null?((CellNumContentContext)_localctx).s.getText():null) != null) {
				  		((CellNumContentContext)_localctx).l =  _localctx.l + 1;
				  	}
				  	notifyErrorListeners(_localctx.l + "|Incorrect addition of the word 'cell(s)', expecting integer only");
				  
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1541);
				((CellNumContentContext)_localctx).integer = integer();
				setState(1542);
				_la = _input.LA(1);
				if ( !(_la==T__7 || _la==T__8) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1543);
				_la = _input.LA(1);
				if ( !(_la==T__19 || _la==T__20) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1544);
				_la = _input.LA(1);
				if ( !(_la==T__9 || _la==T__10) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1545);
				_la = _input.LA(1);
				if ( !(_la==T__9 || _la==T__10) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1548);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__29:
					{
					setState(1546);
					((CellNumContentContext)_localctx).s = match(T__29);
					}
					break;
				case T__30:
					{
					setState(1547);
					((CellNumContentContext)_localctx).s = match(T__30);
					}
					break;
				case T__7:
				case T__8:
					break;
				default:
					break;
				}
				setState(1550);
				((CellNumContentContext)_localctx).cp = cp();

				  	((CellNumContentContext)_localctx).l =  (((CellNumContentContext)_localctx).integer!=null?_input.getText(((CellNumContentContext)_localctx).integer.start,((CellNumContentContext)_localctx).integer.stop):null).length() + 4;
				  	if ((((CellNumContentContext)_localctx).cp!=null?_input.getText(((CellNumContentContext)_localctx).cp.start,((CellNumContentContext)_localctx).cp.stop):null) != null) {
				  		((CellNumContentContext)_localctx).l =  _localctx.l + 2;
				  	}
				  	if ((((CellNumContentContext)_localctx).s!=null?((CellNumContentContext)_localctx).s.getText():null) != null) {
				  		((CellNumContentContext)_localctx).l =  _localctx.l + 1;
				  	}
				  	notifyErrorListeners(_localctx.l + "|i) Incorrect addition of the word 'cell(s)', expecting integer only; ii) Incorrect expression of composite karyotype (cp)");
				  
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

	public static class IncorrectLeftParenthesisIIContext extends ParserRuleContext {
		public IncorrectLeftParenthesisIIContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_incorrectLeftParenthesisII; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterIncorrectLeftParenthesisII(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitIncorrectLeftParenthesisII(this);
		}
	}

	public final IncorrectLeftParenthesisIIContext incorrectLeftParenthesisII() throws RecognitionException {
		IncorrectLeftParenthesisIIContext _localctx = new IncorrectLeftParenthesisIIContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_incorrectLeftParenthesisII);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1556); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1555);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__23) | (1L << T__31) | (1L << T__33) | (1L << T__34))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(1558); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__23) | (1L << T__31) | (1L << T__33) | (1L << T__34))) != 0) );
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

	public static class IncorrectRightParenthesisIIContext extends ParserRuleContext {
		public IncorrectRightParenthesisIIContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_incorrectRightParenthesisII; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterIncorrectRightParenthesisII(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitIncorrectRightParenthesisII(this);
		}
	}

	public final IncorrectRightParenthesisIIContext incorrectRightParenthesisII() throws RecognitionException {
		IncorrectRightParenthesisIIContext _localctx = new IncorrectRightParenthesisIIContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_incorrectRightParenthesisII);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1561); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1560);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__32) | (1L << T__35) | (1L << T__36))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(1563); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__32) | (1L << T__35) | (1L << T__36))) != 0) );
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

	public static class CenContext extends ParserRuleContext {
		public ArmContext arm() {
			return getRuleContext(ArmContext.class,0);
		}
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public TerminalNode QUES() { return getToken(KaryotypeParser.QUES, 0); }
		public CenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cen; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterCen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitCen(this);
		}
	}

	public final CenContext cen() throws RecognitionException {
		CenContext _localctx = new CenContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_cen);
		int _la;
		try {
			setState(1574);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,228,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1566);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==QUES) {
					{
					setState(1565);
					match(QUES);
					}
				}

				setState(1568);
				arm();
				setState(1569);
				integer();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1571);
				match(QUES);
				setState(1572);
				integer();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1573);
				match(QUES);
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

	public static class ChrContext extends ParserRuleContext {
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public SexContext sex() {
			return getRuleContext(SexContext.class,0);
		}
		public TerminalNode QUES() { return getToken(KaryotypeParser.QUES, 0); }
		public List<DigitContext> digit() {
			return getRuleContexts(DigitContext.class);
		}
		public DigitContext digit(int i) {
			return getRuleContext(DigitContext.class,i);
		}
		public ChrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_chr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterChr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitChr(this);
		}
	}

	public final ChrContext chr() throws RecognitionException {
		ChrContext _localctx = new ChrContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_chr);
		int _la;
		try {
			setState(1589);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,232,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1577);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==QUES) {
					{
					setState(1576);
					match(QUES);
					}
				}

				setState(1581);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__49:
				case POSITIVEDIGIT:
					{
					setState(1579);
					integer();
					}
					break;
				case T__50:
				case T__51:
				case T__52:
				case T__53:
					{
					setState(1580);
					sex();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1583);
				digit();
				setState(1584);
				match(QUES);
				setState(1586);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,231,_ctx) ) {
				case 1:
					{
					setState(1585);
					digit();
					}
					break;
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1588);
				match(QUES);
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

	public static class ChrNumContext extends ParserRuleContext {
		public NumRangeTypeIContext numRangeTypeI() {
			return getRuleContext(NumRangeTypeIContext.class,0);
		}
		public List<TerminalNode> QUES() { return getTokens(KaryotypeParser.QUES); }
		public TerminalNode QUES(int i) {
			return getToken(KaryotypeParser.QUES, i);
		}
		public ChrNumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_chrNum; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterChrNum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitChrNum(this);
		}
	}

	public final ChrNumContext chrNum() throws RecognitionException {
		ChrNumContext _localctx = new ChrNumContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_chrNum);
		try {
			int _alt;
			setState(1597);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__49:
			case POSITIVEDIGIT:
				enterOuterAlt(_localctx, 1);
				{
				setState(1591);
				numRangeTypeI();
				}
				break;
			case QUES:
				enterOuterAlt(_localctx, 2);
				{
				setState(1593); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(1592);
						match(QUES);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(1595); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,233,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class CpContext extends ParserRuleContext {
		public CpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterCp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitCp(this);
		}
	}

	public final CpContext cp() throws RecognitionException {
		CpContext _localctx = new CpContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_cp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1599);
			_la = _input.LA(1);
			if ( !(_la==T__7 || _la==T__8) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(1600);
			_la = _input.LA(1);
			if ( !(_la==T__37 || _la==T__38) ) {
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

	public static class DerBreakpointsContext extends ParserRuleContext {
		public CenContext cen() {
			return getRuleContext(CenContext.class,0);
		}
		public DerBreakpointsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_derBreakpoints; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterDerBreakpoints(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitDerBreakpoints(this);
		}
	}

	public final DerBreakpointsContext derBreakpoints() throws RecognitionException {
		DerBreakpointsContext _localctx = new DerBreakpointsContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_derBreakpoints);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1602);
			cen();
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

	public static class DerChrContext extends ParserRuleContext {
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public SexContext sex() {
			return getRuleContext(SexContext.class,0);
		}
		public TerminalNode QUES() { return getToken(KaryotypeParser.QUES, 0); }
		public List<DigitContext> digit() {
			return getRuleContexts(DigitContext.class);
		}
		public DigitContext digit(int i) {
			return getRuleContext(DigitContext.class,i);
		}
		public DerChrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_derChr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterDerChr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitDerChr(this);
		}
	}

	public final DerChrContext derChr() throws RecognitionException {
		DerChrContext _localctx = new DerChrContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_derChr);
		int _la;
		try {
			setState(1617);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,238,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1605);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==QUES) {
					{
					setState(1604);
					match(QUES);
					}
				}

				setState(1609);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__49:
				case POSITIVEDIGIT:
					{
					setState(1607);
					integer();
					}
					break;
				case T__50:
				case T__51:
				case T__52:
				case T__53:
					{
					setState(1608);
					sex();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1611);
				digit();
				setState(1612);
				match(QUES);
				setState(1614);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,237,_ctx) ) {
				case 1:
					{
					setState(1613);
					digit();
					}
					break;
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1616);
				match(QUES);
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

	public static class DerIdContext extends ParserRuleContext {
		public DerIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_derId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterDerId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitDerId(this);
		}
	}

	public final DerIdContext derId() throws RecognitionException {
		DerIdContext _localctx = new DerIdContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_derId);
		int _la;
		try {
			setState(1626);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__25:
			case T__26:
				enterOuterAlt(_localctx, 1);
				{
				setState(1619);
				_la = _input.LA(1);
				if ( !(_la==T__25 || _la==T__26) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1620);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1621);
				_la = _input.LA(1);
				if ( !(_la==T__19 || _la==T__20) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1622);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__16) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case T__27:
			case T__28:
				enterOuterAlt(_localctx, 2);
				{
				setState(1623);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1624);
				_la = _input.LA(1);
				if ( !(_la==T__19 || _la==T__20) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1625);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__16) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
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

	public static class DigitContext extends ParserRuleContext {
		public TerminalNode POSITIVEDIGIT() { return getToken(KaryotypeParser.POSITIVEDIGIT, 0); }
		public DigitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_digit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterDigit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitDigit(this);
		}
	}

	public final DigitContext digit() throws RecognitionException {
		DigitContext _localctx = new DigitContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_digit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1628);
			_la = _input.LA(1);
			if ( !(_la==T__49 || _la==POSITIVEDIGIT) ) {
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

	public static class DminNumContext extends ParserRuleContext {
		public NumRangeTypeIContext numRangeTypeI() {
			return getRuleContext(NumRangeTypeIContext.class,0);
		}
		public NumRangeTypeIIIContext numRangeTypeIII() {
			return getRuleContext(NumRangeTypeIIIContext.class,0);
		}
		public DminNumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dminNum; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterDminNum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitDminNum(this);
		}
	}

	public final DminNumContext dminNum() throws RecognitionException {
		DminNumContext _localctx = new DminNumContext(_ctx, getState());
		enterRule(_localctx, 162, RULE_dminNum);
		try {
			setState(1632);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__49:
			case POSITIVEDIGIT:
				enterOuterAlt(_localctx, 1);
				{
				setState(1630);
				numRangeTypeI();
				}
				break;
			case APPROX:
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(1631);
				numRangeTypeIII();
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

	public static class GainChrContext extends ParserRuleContext {
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public SexContext sex() {
			return getRuleContext(SexContext.class,0);
		}
		public GainChrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gainChr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterGainChr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitGainChr(this);
		}
	}

	public final GainChrContext gainChr() throws RecognitionException {
		GainChrContext _localctx = new GainChrContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_gainChr);
		try {
			setState(1636);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__49:
			case POSITIVEDIGIT:
				enterOuterAlt(_localctx, 1);
				{
				setState(1634);
				integer();
				}
				break;
			case T__50:
			case T__51:
			case T__52:
			case T__53:
				enterOuterAlt(_localctx, 2);
				{
				setState(1635);
				sex();
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

	public static class IdContext extends ParserRuleContext {
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitId(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 166, RULE_id);
		int _la;
		try {
			setState(1704);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,246,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1638);
				_la = _input.LA(1);
				if ( !(_la==T__11 || _la==T__12) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1639);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1640);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1641);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1642);
				_la = _input.LA(1);
				if ( !(_la==T__19 || _la==T__20) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1643);
				_la = _input.LA(1);
				if ( !(_la==T__9 || _la==T__10) ) {
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
				enterOuterAlt(_localctx, 3);
				{
				setState(1647);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__37 || _la==T__38) {
					{
					setState(1644);
					_la = _input.LA(1);
					if ( !(_la==T__37 || _la==T__38) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(1645);
					_la = _input.LA(1);
					if ( !(_la==T__29 || _la==T__30) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(1646);
					_la = _input.LA(1);
					if ( !(_la==T__39 || _la==T__40) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(1649);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1650);
				_la = _input.LA(1);
				if ( !(_la==T__25 || _la==T__26) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1651);
				_la = _input.LA(1);
				if ( !(_la==T__7 || _la==T__8) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1655);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__37 || _la==T__38) {
					{
					setState(1652);
					_la = _input.LA(1);
					if ( !(_la==T__37 || _la==T__38) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(1653);
					_la = _input.LA(1);
					if ( !(_la==T__29 || _la==T__30) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(1654);
					_la = _input.LA(1);
					if ( !(_la==T__39 || _la==T__40) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(1657);
				_la = _input.LA(1);
				if ( !(_la==T__25 || _la==T__26) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1658);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1659);
				_la = _input.LA(1);
				if ( !(_la==T__25 || _la==T__26) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1660);
				_la = _input.LA(1);
				if ( !(_la==T__7 || _la==T__8) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1661);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1662);
				_la = _input.LA(1);
				if ( !(_la==T__39 || _la==T__40) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1663);
				_la = _input.LA(1);
				if ( !(_la==T__37 || _la==T__38) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1664);
				_la = _input.LA(1);
				if ( !(_la==T__41 || _la==T__42) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1665);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__16) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1666);
				_la = _input.LA(1);
				if ( !(_la==T__37 || _la==T__38) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(1667);
				_la = _input.LA(1);
				if ( !(_la==T__43 || _la==T__44) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1668);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1669);
				_la = _input.LA(1);
				if ( !(_la==T__37 || _la==T__38) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(1670);
				_la = _input.LA(1);
				if ( !(_la==T__45 || _la==T__46) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1671);
				_la = _input.LA(1);
				if ( !(_la==T__29 || _la==T__30) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1672);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__16) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(1673);
				_la = _input.LA(1);
				if ( !(_la==T__25 || _la==T__26) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1674);
				_la = _input.LA(1);
				if ( !(_la==T__3 || _la==T__4) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1675);
				_la = _input.LA(1);
				if ( !(_la==T__29 || _la==T__30) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(1676);
				_la = _input.LA(1);
				if ( !(_la==T__25 || _la==T__26) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1677);
				_la = _input.LA(1);
				if ( !(_la==T__3 || _la==T__4) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1678);
				_la = _input.LA(1);
				if ( !(_la==T__47 || _la==T__48) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(1679);
				_la = _input.LA(1);
				if ( !(_la==T__25 || _la==T__26) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(1686);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__27:
				case T__28:
					{
					setState(1680);
					_la = _input.LA(1);
					if ( !(_la==T__27 || _la==T__28) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(1681);
					_la = _input.LA(1);
					if ( !(_la==T__25 || _la==T__26) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(1682);
					_la = _input.LA(1);
					if ( !(_la==T__7 || _la==T__8) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					break;
				case T__41:
				case T__42:
					{
					setState(1683);
					_la = _input.LA(1);
					if ( !(_la==T__41 || _la==T__42) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(1684);
					_la = _input.LA(1);
					if ( !(_la==T__15 || _la==T__16) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(1685);
					_la = _input.LA(1);
					if ( !(_la==T__7 || _la==T__8) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					break;
				case T__15:
				case T__16:
					break;
				default:
					break;
				}
				setState(1688);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__16) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(1689);
				_la = _input.LA(1);
				if ( !(_la==T__41 || _la==T__42) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1690);
				_la = _input.LA(1);
				if ( !(_la==T__11 || _la==T__12) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1691);
				_la = _input.LA(1);
				if ( !(_la==T__29 || _la==T__30) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(1692);
				_la = _input.LA(1);
				if ( !(_la==T__41 || _la==T__42) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(1696);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__37 || _la==T__38) {
					{
					setState(1693);
					_la = _input.LA(1);
					if ( !(_la==T__37 || _la==T__38) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(1694);
					_la = _input.LA(1);
					if ( !(_la==T__29 || _la==T__30) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(1695);
					_la = _input.LA(1);
					if ( !(_la==T__39 || _la==T__40) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(1698);
				_la = _input.LA(1);
				if ( !(_la==T__41 || _la==T__42) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1699);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__16) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1700);
				_la = _input.LA(1);
				if ( !(_la==T__7 || _la==T__8) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(1701);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__16) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1702);
				_la = _input.LA(1);
				if ( !(_la==T__5 || _la==T__6) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1703);
				_la = _input.LA(1);
				if ( !(_la==T__13 || _la==T__14) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
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

	public static class InhContext extends ParserRuleContext {
		public InhContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inh; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterInh(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitInh(this);
		}
	}

	public final InhContext inh() throws RecognitionException {
		InhContext _localctx = new InhContext(_ctx, getState());
		enterRule(_localctx, 168, RULE_inh);
		int _la;
		try {
			setState(1717);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__27:
			case T__28:
				enterOuterAlt(_localctx, 1);
				{
				setState(1706);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1707);
				_la = _input.LA(1);
				if ( !(_la==T__3 || _la==T__4) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case T__25:
			case T__26:
				enterOuterAlt(_localctx, 2);
				{
				setState(1708);
				_la = _input.LA(1);
				if ( !(_la==T__25 || _la==T__26) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1709);
				_la = _input.LA(1);
				if ( !(_la==T__3 || _la==T__4) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1710);
				_la = _input.LA(1);
				if ( !(_la==T__45 || _la==T__46) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case T__17:
			case T__18:
				enterOuterAlt(_localctx, 3);
				{
				setState(1711);
				_la = _input.LA(1);
				if ( !(_la==T__17 || _la==T__18) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1712);
				_la = _input.LA(1);
				if ( !(_la==T__11 || _la==T__12) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1713);
				_la = _input.LA(1);
				if ( !(_la==T__41 || _la==T__42) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case T__37:
			case T__38:
				enterOuterAlt(_localctx, 4);
				{
				setState(1714);
				_la = _input.LA(1);
				if ( !(_la==T__37 || _la==T__38) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1715);
				_la = _input.LA(1);
				if ( !(_la==T__11 || _la==T__12) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1716);
				_la = _input.LA(1);
				if ( !(_la==T__41 || _la==T__42) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
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

	public static class IntegerContext extends ParserRuleContext {
		public List<DigitContext> digit() {
			return getRuleContexts(DigitContext.class);
		}
		public DigitContext digit(int i) {
			return getRuleContext(DigitContext.class,i);
		}
		public IntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitInteger(this);
		}
	}

	public final IntegerContext integer() throws RecognitionException {
		IntegerContext _localctx = new IntegerContext(_ctx, getState());
		enterRule(_localctx, 170, RULE_integer);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1720); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1719);
					digit();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1722); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,248,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class LossChrContext extends ParserRuleContext {
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public SexContext sex() {
			return getRuleContext(SexContext.class,0);
		}
		public LossChrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lossChr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterLossChr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitLossChr(this);
		}
	}

	public final LossChrContext lossChr() throws RecognitionException {
		LossChrContext _localctx = new LossChrContext(_ctx, getState());
		enterRule(_localctx, 172, RULE_lossChr);
		try {
			setState(1726);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__49:
			case POSITIVEDIGIT:
				enterOuterAlt(_localctx, 1);
				{
				setState(1724);
				integer();
				}
				break;
			case T__50:
			case T__51:
			case T__52:
			case T__53:
				enterOuterAlt(_localctx, 2);
				{
				setState(1725);
				sex();
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

	public static class MarNumContext extends ParserRuleContext {
		public NumRangeTypeIContext numRangeTypeI() {
			return getRuleContext(NumRangeTypeIContext.class,0);
		}
		public MarNumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_marNum; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterMarNum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitMarNum(this);
		}
	}

	public final MarNumContext marNum() throws RecognitionException {
		MarNumContext _localctx = new MarNumContext(_ctx, getState());
		enterRule(_localctx, 174, RULE_marNum);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1728);
			numRangeTypeI();
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

	public static class ModalDescContext extends ParserRuleContext {
		public ModalPrefixContext modalPrefix() {
			return getRuleContext(ModalPrefixContext.class,0);
		}
		public ModalLevelContext modalLevel() {
			return getRuleContext(ModalLevelContext.class,0);
		}
		public ModalSuffixContext modalSuffix() {
			return getRuleContext(ModalSuffixContext.class,0);
		}
		public ModalDescContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modalDesc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterModalDesc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitModalDesc(this);
		}
	}

	public final ModalDescContext modalDesc() throws RecognitionException {
		ModalDescContext _localctx = new ModalDescContext(_ctx, getState());
		enterRule(_localctx, 176, RULE_modalDesc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1730);
			modalPrefix();
			setState(1731);
			modalLevel();
			setState(1732);
			modalSuffix();
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

	public static class ModalLevelContext extends ParserRuleContext {
		public ModalLevelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modalLevel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterModalLevel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitModalLevel(this);
		}
	}

	public final ModalLevelContext modalLevel() throws RecognitionException {
		ModalLevelContext _localctx = new ModalLevelContext(_ctx, getState());
		enterRule(_localctx, 178, RULE_modalLevel);
		int _la;
		try {
			setState(1764);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,250,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1734);
				_la = _input.LA(1);
				if ( !(_la==T__45 || _la==T__46) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1735);
				_la = _input.LA(1);
				if ( !(_la==T__11 || _la==T__12) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1736);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1737);
				_la = _input.LA(1);
				if ( !(_la==T__25 || _la==T__26) ) {
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
				enterOuterAlt(_localctx, 3);
				{
				setState(1738);
				_la = _input.LA(1);
				if ( !(_la==T__41 || _la==T__42) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1739);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__16) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1740);
				_la = _input.LA(1);
				if ( !(_la==T__25 || _la==T__26) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1741);
				_la = _input.LA(1);
				if ( !(_la==T__41 || _la==T__42) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1742);
				_la = _input.LA(1);
				if ( !(_la==T__19 || _la==T__20) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1743);
				_la = _input.LA(1);
				if ( !(_la==T__41 || _la==T__42) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1744);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__16) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1745);
				_la = _input.LA(1);
				if ( !(_la==T__11 || _la==T__12) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1746);
				_la = _input.LA(1);
				if ( !(_la==T__37 || _la==T__38) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1747);
				_la = _input.LA(1);
				if ( !(_la==T__19 || _la==T__20) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1748);
				_la = _input.LA(1);
				if ( !(_la==T__3 || _la==T__4) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1749);
				_la = _input.LA(1);
				if ( !(_la==T__41 || _la==T__42) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1750);
				_la = _input.LA(1);
				if ( !(_la==T__11 || _la==T__12) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1751);
				_la = _input.LA(1);
				if ( !(_la==T__45 || _la==T__46) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1752);
				_la = _input.LA(1);
				if ( !(_la==T__19 || _la==T__20) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1753);
				_la = _input.LA(1);
				if ( !(_la==T__50 || _la==T__51) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1754);
				_la = _input.LA(1);
				if ( !(_la==T__11 || _la==T__12) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(1755);
				_la = _input.LA(1);
				if ( !(_la==T__45 || _la==T__46) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1756);
				_la = _input.LA(1);
				if ( !(_la==T__19 || _la==T__20) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1757);
				_la = _input.LA(1);
				if ( !(_la==T__37 || _la==T__38) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1758);
				_la = _input.LA(1);
				if ( !(_la==T__41 || _la==T__42) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1759);
				_la = _input.LA(1);
				if ( !(_la==T__11 || _la==T__12) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(1760);
				_la = _input.LA(1);
				if ( !(_la==T__5 || _la==T__6) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1761);
				_la = _input.LA(1);
				if ( !(_la==T__7 || _la==T__8) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1762);
				_la = _input.LA(1);
				if ( !(_la==T__41 || _la==T__42) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1763);
				_la = _input.LA(1);
				if ( !(_la==T__11 || _la==T__12) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
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

	public static class ModalPrefixContext extends ParserRuleContext {
		public ModalPrefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modalPrefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterModalPrefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitModalPrefix(this);
		}
	}

	public final ModalPrefixContext modalPrefix() throws RecognitionException {
		ModalPrefixContext _localctx = new ModalPrefixContext(_ctx, getState());
		enterRule(_localctx, 180, RULE_modalPrefix);
		int _la;
		try {
			setState(1788);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,252,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1766);
				_la = _input.LA(1);
				if ( !(_la==T__3 || _la==T__4) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1767);
				_la = _input.LA(1);
				if ( !(_la==T__19 || _la==T__20) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1768);
				_la = _input.LA(1);
				if ( !(_la==T__11 || _la==T__12) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1769);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__16) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1771);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINUS) {
					{
					setState(1770);
					match(MINUS);
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1773);
				_la = _input.LA(1);
				if ( !(_la==T__45 || _la==T__46) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1774);
				_la = _input.LA(1);
				if ( !(_la==T__52 || _la==T__53) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1775);
				_la = _input.LA(1);
				if ( !(_la==T__37 || _la==T__38) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1776);
				_la = _input.LA(1);
				if ( !(_la==T__5 || _la==T__6) ) {
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
				enterOuterAlt(_localctx, 3);
				{
				setState(1777);
				_la = _input.LA(1);
				if ( !(_la==T__45 || _la==T__46) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1778);
				_la = _input.LA(1);
				if ( !(_la==T__52 || _la==T__53) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1779);
				_la = _input.LA(1);
				if ( !(_la==T__37 || _la==T__38) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1780);
				_la = _input.LA(1);
				if ( !(_la==T__19 || _la==T__20) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1781);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__16) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1782);
				_la = _input.LA(1);
				if ( !(_la==T__37 || _la==T__38) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1783);
				_la = _input.LA(1);
				if ( !(_la==T__29 || _la==T__30) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1784);
				_la = _input.LA(1);
				if ( !(_la==T__19 || _la==T__20) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1785);
				_la = _input.LA(1);
				if ( !(_la==T__39 || _la==T__40) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1786);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1787);
				_la = _input.LA(1);
				if ( !(_la==T__5 || _la==T__6) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
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

	public static class ModalSuffixContext extends ParserRuleContext {
		public ModalSuffixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modalSuffix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterModalSuffix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitModalSuffix(this);
		}
	}

	public final ModalSuffixContext modalSuffix() throws RecognitionException {
		ModalSuffixContext _localctx = new ModalSuffixContext(_ctx, getState());
		enterRule(_localctx, 182, RULE_modalSuffix);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1790);
			_la = _input.LA(1);
			if ( !(_la==T__37 || _la==T__38) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(1791);
			_la = _input.LA(1);
			if ( !(_la==T__9 || _la==T__10) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(1792);
			_la = _input.LA(1);
			if ( !(_la==T__5 || _la==T__6) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(1793);
			_la = _input.LA(1);
			if ( !(_la==T__25 || _la==T__26) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(1794);
			_la = _input.LA(1);
			if ( !(_la==T__27 || _la==T__28) ) {
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

	public static class MosChiContext extends ParserRuleContext {
		public MosChiContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mosChi; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterMosChi(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitMosChi(this);
		}
	}

	public final MosChiContext mosChi() throws RecognitionException {
		MosChiContext _localctx = new MosChiContext(_ctx, getState());
		enterRule(_localctx, 184, RULE_mosChi);
		int _la;
		try {
			setState(1802);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__17:
			case T__18:
				enterOuterAlt(_localctx, 1);
				{
				setState(1796);
				_la = _input.LA(1);
				if ( !(_la==T__17 || _la==T__18) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1797);
				_la = _input.LA(1);
				if ( !(_la==T__5 || _la==T__6) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1798);
				_la = _input.LA(1);
				if ( !(_la==T__29 || _la==T__30) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case T__7:
			case T__8:
				enterOuterAlt(_localctx, 2);
				{
				setState(1799);
				_la = _input.LA(1);
				if ( !(_la==T__7 || _la==T__8) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1800);
				_la = _input.LA(1);
				if ( !(_la==T__45 || _la==T__46) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1801);
				_la = _input.LA(1);
				if ( !(_la==T__25 || _la==T__26) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
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

	public static class MultiplicationContext extends ParserRuleContext {
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public MultiplicationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplication; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterMultiplication(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitMultiplication(this);
		}
	}

	public final MultiplicationContext multiplication() throws RecognitionException {
		MultiplicationContext _localctx = new MultiplicationContext(_ctx, getState());
		enterRule(_localctx, 186, RULE_multiplication);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1804);
			_la = _input.LA(1);
			if ( !(_la==T__50 || _la==T__51) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(1805);
			integer();
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

	public static class NumRangeTypeIContext extends ParserRuleContext {
		public List<IntegerContext> integer() {
			return getRuleContexts(IntegerContext.class);
		}
		public IntegerContext integer(int i) {
			return getRuleContext(IntegerContext.class,i);
		}
		public TerminalNode APPROX() { return getToken(KaryotypeParser.APPROX, 0); }
		public TerminalNode MINUS() { return getToken(KaryotypeParser.MINUS, 0); }
		public NumRangeTypeIContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numRangeTypeI; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterNumRangeTypeI(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitNumRangeTypeI(this);
		}
	}

	public final NumRangeTypeIContext numRangeTypeI() throws RecognitionException {
		NumRangeTypeIContext _localctx = new NumRangeTypeIContext(_ctx, getState());
		enterRule(_localctx, 188, RULE_numRangeTypeI);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1807);
			integer();
			setState(1810);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,254,_ctx) ) {
			case 1:
				{
				setState(1808);
				_la = _input.LA(1);
				if ( !(_la==APPROX || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1809);
				integer();
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

	public static class NumRangeTypeIIContext extends ParserRuleContext {
		public List<IntegerContext> integer() {
			return getRuleContexts(IntegerContext.class);
		}
		public IntegerContext integer(int i) {
			return getRuleContext(IntegerContext.class,i);
		}
		public TerminalNode APPROX() { return getToken(KaryotypeParser.APPROX, 0); }
		public TerminalNode MINUS() { return getToken(KaryotypeParser.MINUS, 0); }
		public NumRangeTypeIIContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numRangeTypeII; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterNumRangeTypeII(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitNumRangeTypeII(this);
		}
	}

	public final NumRangeTypeIIContext numRangeTypeII() throws RecognitionException {
		NumRangeTypeIIContext _localctx = new NumRangeTypeIIContext(_ctx, getState());
		enterRule(_localctx, 190, RULE_numRangeTypeII);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1812);
			integer();
			setState(1813);
			_la = _input.LA(1);
			if ( !(_la==APPROX || _la==MINUS) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(1814);
			integer();
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

	public static class NumRangeTypeIIIContext extends ParserRuleContext {
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public TerminalNode APPROX() { return getToken(KaryotypeParser.APPROX, 0); }
		public TerminalNode MINUS() { return getToken(KaryotypeParser.MINUS, 0); }
		public NumRangeTypeIIIContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numRangeTypeIII; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterNumRangeTypeIII(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitNumRangeTypeIII(this);
		}
	}

	public final NumRangeTypeIIIContext numRangeTypeIII() throws RecognitionException {
		NumRangeTypeIIIContext _localctx = new NumRangeTypeIIIContext(_ctx, getState());
		enterRule(_localctx, 192, RULE_numRangeTypeIII);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1816);
			_la = _input.LA(1);
			if ( !(_la==APPROX || _la==MINUS) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(1817);
			integer();
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

	public static class PrefixContext extends ParserRuleContext {
		public TerminalNode QUES() { return getToken(KaryotypeParser.QUES, 0); }
		public TerminalNode PLUS() { return getToken(KaryotypeParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(KaryotypeParser.MINUS, 0); }
		public PrefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterPrefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitPrefix(this);
		}
	}

	public final PrefixContext prefix() throws RecognitionException {
		PrefixContext _localctx = new PrefixContext(_ctx, getState());
		enterRule(_localctx, 194, RULE_prefix);
		int _la;
		try {
			setState(1827);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,257,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1820);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINUS || _la==PLUS) {
					{
					setState(1819);
					_la = _input.LA(1);
					if ( !(_la==MINUS || _la==PLUS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(1822);
				match(QUES);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1824);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==QUES) {
					{
					setState(1823);
					match(QUES);
					}
				}

				setState(1826);
				_la = _input.LA(1);
				if ( !(_la==MINUS || _la==PLUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
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

	public static class PrefixMinusContext extends ParserRuleContext {
		public TerminalNode MINUS() { return getToken(KaryotypeParser.MINUS, 0); }
		public TerminalNode QUES() { return getToken(KaryotypeParser.QUES, 0); }
		public PrefixMinusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefixMinus; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterPrefixMinus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitPrefixMinus(this);
		}
	}

	public final PrefixMinusContext prefixMinus() throws RecognitionException {
		PrefixMinusContext _localctx = new PrefixMinusContext(_ctx, getState());
		enterRule(_localctx, 196, RULE_prefixMinus);
		try {
			setState(1835);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MINUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(1829);
				match(MINUS);
				setState(1831);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,258,_ctx) ) {
				case 1:
					{
					setState(1830);
					match(QUES);
					}
					break;
				}
				}
				break;
			case QUES:
				enterOuterAlt(_localctx, 2);
				{
				setState(1833);
				match(QUES);
				setState(1834);
				match(MINUS);
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

	public static class PrefixPlusContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(KaryotypeParser.PLUS, 0); }
		public TerminalNode QUES() { return getToken(KaryotypeParser.QUES, 0); }
		public PrefixPlusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefixPlus; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterPrefixPlus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitPrefixPlus(this);
		}
	}

	public final PrefixPlusContext prefixPlus() throws RecognitionException {
		PrefixPlusContext _localctx = new PrefixPlusContext(_ctx, getState());
		enterRule(_localctx, 198, RULE_prefixPlus);
		try {
			setState(1843);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(1837);
				match(PLUS);
				setState(1839);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,260,_ctx) ) {
				case 1:
					{
					setState(1838);
					match(QUES);
					}
					break;
				}
				}
				break;
			case QUES:
				enterOuterAlt(_localctx, 2);
				{
				setState(1841);
				match(QUES);
				setState(1842);
				match(PLUS);
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

	public static class RIdContext extends ParserRuleContext {
		public RIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterRId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitRId(this);
		}
	}

	public final RIdContext rId() throws RecognitionException {
		RIdContext _localctx = new RIdContext(_ctx, getState());
		enterRule(_localctx, 200, RULE_rId);
		int _la;
		try {
			setState(1871);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,262,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1845);
				_la = _input.LA(1);
				if ( !(_la==T__11 || _la==T__12) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1846);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1847);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1848);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1849);
				_la = _input.LA(1);
				if ( !(_la==T__19 || _la==T__20) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1850);
				_la = _input.LA(1);
				if ( !(_la==T__9 || _la==T__10) ) {
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
				enterOuterAlt(_localctx, 3);
				{
				setState(1851);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1852);
				_la = _input.LA(1);
				if ( !(_la==T__39 || _la==T__40) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1853);
				_la = _input.LA(1);
				if ( !(_la==T__37 || _la==T__38) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1854);
				_la = _input.LA(1);
				if ( !(_la==T__41 || _la==T__42) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1855);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__16) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1856);
				_la = _input.LA(1);
				if ( !(_la==T__37 || _la==T__38) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1857);
				_la = _input.LA(1);
				if ( !(_la==T__43 || _la==T__44) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1858);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1859);
				_la = _input.LA(1);
				if ( !(_la==T__37 || _la==T__38) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1860);
				_la = _input.LA(1);
				if ( !(_la==T__45 || _la==T__46) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1861);
				_la = _input.LA(1);
				if ( !(_la==T__29 || _la==T__30) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1862);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__16) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(1863);
				_la = _input.LA(1);
				if ( !(_la==T__25 || _la==T__26) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1864);
				_la = _input.LA(1);
				if ( !(_la==T__3 || _la==T__4) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1865);
				_la = _input.LA(1);
				if ( !(_la==T__29 || _la==T__30) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(1866);
				_la = _input.LA(1);
				if ( !(_la==T__25 || _la==T__26) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1867);
				_la = _input.LA(1);
				if ( !(_la==T__3 || _la==T__4) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1868);
				_la = _input.LA(1);
				if ( !(_la==T__47 || _la==T__48) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(1869);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__16) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(1870);
				_la = _input.LA(1);
				if ( !(_la==T__41 || _la==T__42) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
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

	public static class SexContext extends ParserRuleContext {
		public SexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterSex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitSex(this);
		}
	}

	public final SexContext sex() throws RecognitionException {
		SexContext _localctx = new SexContext(_ctx, getState());
		enterRule(_localctx, 202, RULE_sex);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1873);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__50) | (1L << T__51) | (1L << T__52) | (1L << T__53))) != 0)) ) {
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

	public static class SexChrContext extends ParserRuleContext {
		public List<SexContext> sex() {
			return getRuleContexts(SexContext.class);
		}
		public SexContext sex(int i) {
			return getRuleContext(SexContext.class,i);
		}
		public List<TerminalNode> QUES() { return getTokens(KaryotypeParser.QUES); }
		public TerminalNode QUES(int i) {
			return getToken(KaryotypeParser.QUES, i);
		}
		public SexChrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sexChr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterSexChr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitSexChr(this);
		}
	}

	public final SexChrContext sexChr() throws RecognitionException {
		SexChrContext _localctx = new SexChrContext(_ctx, getState());
		enterRule(_localctx, 204, RULE_sexChr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1877); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					setState(1877);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__50:
					case T__51:
					case T__52:
					case T__53:
						{
						setState(1875);
						sex();
						}
						break;
					case QUES:
						{
						setState(1876);
						match(QUES);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1879); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,264,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class SuffixContext extends ParserRuleContext {
		public MultiplicationContext multiplication() {
			return getRuleContext(MultiplicationContext.class,0);
		}
		public CContext c() {
			return getRuleContext(CContext.class,0);
		}
		public InhContext inh() {
			return getRuleContext(InhContext.class,0);
		}
		public SuffixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_suffix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterSuffix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitSuffix(this);
		}
	}

	public final SuffixContext suffix() throws RecognitionException {
		SuffixContext _localctx = new SuffixContext(_ctx, getState());
		enterRule(_localctx, 206, RULE_suffix);
		int _la;
		try {
			setState(1893);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,268,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1883);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__7:
				case T__8:
					{
					setState(1881);
					c();
					}
					break;
				case T__17:
				case T__18:
				case T__25:
				case T__26:
				case T__27:
				case T__28:
				case T__37:
				case T__38:
					{
					setState(1882);
					inh();
					}
					break;
				case T__50:
				case T__51:
					break;
				default:
					break;
				}
				setState(1885);
				multiplication();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1887);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__50 || _la==T__51) {
					{
					setState(1886);
					multiplication();
					}
				}

				setState(1891);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__7:
				case T__8:
					{
					setState(1889);
					c();
					}
					break;
				case T__17:
				case T__18:
				case T__25:
				case T__26:
				case T__27:
				case T__28:
				case T__37:
				case T__38:
					{
					setState(1890);
					inh();
					}
					break;
				default:
					throw new NoViableAltException(this);
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

	public static class SubbandContext extends ParserRuleContext {
		public NumRangeTypeIIContext numRangeTypeII() {
			return getRuleContext(NumRangeTypeIIContext.class,0);
		}
		public List<DigitContext> digit() {
			return getRuleContexts(DigitContext.class);
		}
		public DigitContext digit(int i) {
			return getRuleContext(DigitContext.class,i);
		}
		public List<TerminalNode> QUES() { return getTokens(KaryotypeParser.QUES); }
		public TerminalNode QUES(int i) {
			return getToken(KaryotypeParser.QUES, i);
		}
		public SubbandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subband; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterSubband(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitSubband(this);
		}
	}

	public final SubbandContext subband() throws RecognitionException {
		SubbandContext _localctx = new SubbandContext(_ctx, getState());
		enterRule(_localctx, 208, RULE_subband);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1895);
			match(T__0);
			setState(1903);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,271,_ctx) ) {
			case 1:
				{
				setState(1898); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						setState(1898);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case T__49:
						case POSITIVEDIGIT:
							{
							setState(1896);
							digit();
							}
							break;
						case QUES:
							{
							setState(1897);
							match(QUES);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(1900); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,270,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 2:
				{
				setState(1902);
				numRangeTypeII();
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

	public static class UndeterminedPrefixContext extends ParserRuleContext {
		public TerminalNode MINUS() { return getToken(KaryotypeParser.MINUS, 0); }
		public TerminalNode PLUS() { return getToken(KaryotypeParser.PLUS, 0); }
		public TerminalNode QUES() { return getToken(KaryotypeParser.QUES, 0); }
		public UndeterminedPrefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_undeterminedPrefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).enterUndeterminedPrefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KaryotypeListener ) ((KaryotypeListener)listener).exitUndeterminedPrefix(this);
		}
	}

	public final UndeterminedPrefixContext undeterminedPrefix() throws RecognitionException {
		UndeterminedPrefixContext _localctx = new UndeterminedPrefixContext(_ctx, getState());
		enterRule(_localctx, 210, RULE_undeterminedPrefix);
		try {
			setState(1921);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MINUS:
			case PLUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(1909);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case MINUS:
					{
					setState(1905);
					match(MINUS);
					setState(1906);
					match(PLUS);
					}
					break;
				case PLUS:
					{
					setState(1907);
					match(PLUS);
					setState(1908);
					match(MINUS);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1912);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,273,_ctx) ) {
				case 1:
					{
					setState(1911);
					match(QUES);
					}
					break;
				}
				}
				break;
			case QUES:
				enterOuterAlt(_localctx, 2);
				{
				setState(1914);
				match(QUES);
				setState(1919);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case MINUS:
					{
					setState(1915);
					match(MINUS);
					setState(1916);
					match(PLUS);
					}
					break;
				case PLUS:
					{
					setState(1917);
					match(PLUS);
					setState(1918);
					match(MINUS);
					}
					break;
				default:
					throw new NoViableAltException(this);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3?\u0786\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\4_\t_\4"+
		"`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\4f\tf\4g\tg\4h\th\4i\ti\4j\tj\4k\t"+
		"k\3\2\3\2\3\2\3\2\6\2\u00db\n\2\r\2\16\2\u00dc\3\2\5\2\u00e0\n\2\3\3\3"+
		"\3\3\3\7\3\u00e5\n\3\f\3\16\3\u00e8\13\3\3\3\5\3\u00eb\n\3\3\4\5\4\u00ee"+
		"\n\4\3\4\3\4\3\5\3\5\5\5\u00f4\n\5\3\5\3\5\5\5\u00f8\n\5\3\5\3\5\3\5\3"+
		"\5\3\5\5\5\u00ff\n\5\3\5\3\5\3\5\3\5\3\5\5\5\u0106\n\5\3\5\3\5\3\5\5\5"+
		"\u010b\n\5\3\6\3\6\5\6\u010f\n\6\3\6\3\6\5\6\u0113\n\6\3\6\3\6\3\6\3\6"+
		"\3\6\5\6\u011a\n\6\3\6\3\6\3\6\3\6\3\6\5\6\u0121\n\6\3\6\3\6\3\6\5\6\u0126"+
		"\n\6\3\7\3\7\7\7\u012a\n\7\f\7\16\7\u012d\13\7\3\7\7\7\u0130\n\7\f\7\16"+
		"\7\u0133\13\7\3\7\5\7\u0136\n\7\3\b\3\b\3\b\7\b\u013b\n\b\f\b\16\b\u013e"+
		"\13\b\3\b\7\b\u0141\n\b\f\b\16\b\u0144\13\b\3\b\3\b\6\b\u0148\n\b\r\b"+
		"\16\b\u0149\3\b\3\b\6\b\u014e\n\b\r\b\16\b\u014f\3\b\7\b\u0153\n\b\f\b"+
		"\16\b\u0156\13\b\5\b\u0158\n\b\3\t\3\t\6\t\u015c\n\t\r\t\16\t\u015d\3"+
		"\n\5\n\u0161\n\n\3\n\3\n\3\13\3\13\5\13\u0167\n\13\3\13\3\13\5\13\u016b"+
		"\n\13\3\13\3\13\3\13\3\13\3\13\5\13\u0172\n\13\3\13\3\13\3\13\3\13\3\13"+
		"\5\13\u0179\n\13\3\13\3\13\3\13\5\13\u017e\n\13\3\f\3\f\5\f\u0182\n\f"+
		"\3\f\3\f\5\f\u0186\n\f\3\f\3\f\3\f\3\f\3\f\5\f\u018d\n\f\3\f\3\f\3\f\3"+
		"\f\3\f\5\f\u0194\n\f\3\f\3\f\3\f\5\f\u0199\n\f\3\r\3\r\5\r\u019d\n\r\3"+
		"\r\3\r\5\r\u01a1\n\r\3\r\3\r\3\r\3\r\3\r\5\r\u01a8\n\r\3\r\3\r\3\r\3\r"+
		"\3\r\5\r\u01af\n\r\3\r\3\r\3\r\5\r\u01b4\n\r\3\16\3\16\5\16\u01b8\n\16"+
		"\3\16\3\16\5\16\u01bc\n\16\3\16\3\16\3\16\3\16\3\16\5\16\u01c3\n\16\3"+
		"\16\3\16\3\16\3\16\3\16\5\16\u01ca\n\16\3\16\3\16\3\16\5\16\u01cf\n\16"+
		"\3\17\5\17\u01d2\n\17\3\17\3\17\5\17\u01d6\n\17\3\17\3\17\7\17\u01da\n"+
		"\17\f\17\16\17\u01dd\13\17\3\17\5\17\u01e0\n\17\3\17\5\17\u01e3\n\17\3"+
		"\17\3\17\5\17\u01e7\n\17\3\17\7\17\u01ea\n\17\f\17\16\17\u01ed\13\17\3"+
		"\17\5\17\u01f0\n\17\3\17\3\17\3\17\5\17\u01f5\n\17\3\17\6\17\u01f8\n\17"+
		"\r\17\16\17\u01f9\3\17\5\17\u01fd\n\17\3\17\3\17\5\17\u0201\n\17\3\20"+
		"\5\20\u0204\n\20\3\20\3\20\5\20\u0208\n\20\3\20\3\20\7\20\u020c\n\20\f"+
		"\20\16\20\u020f\13\20\3\20\5\20\u0212\n\20\3\20\5\20\u0215\n\20\3\20\3"+
		"\20\5\20\u0219\n\20\3\20\3\20\7\20\u021d\n\20\f\20\16\20\u0220\13\20\3"+
		"\20\5\20\u0223\n\20\3\20\5\20\u0226\n\20\3\20\3\20\7\20\u022a\n\20\f\20"+
		"\16\20\u022d\13\20\3\20\5\20\u0230\n\20\3\20\3\20\3\20\5\20\u0235\n\20"+
		"\3\20\3\20\5\20\u0239\n\20\3\20\7\20\u023c\n\20\f\20\16\20\u023f\13\20"+
		"\3\20\5\20\u0242\n\20\3\20\3\20\3\20\5\20\u0247\n\20\3\20\6\20\u024a\n"+
		"\20\r\20\16\20\u024b\3\20\5\20\u024f\n\20\3\20\3\20\5\20\u0253\n\20\3"+
		"\21\5\21\u0256\n\21\3\21\3\21\5\21\u025a\n\21\3\21\3\21\7\21\u025e\n\21"+
		"\f\21\16\21\u0261\13\21\3\21\5\21\u0264\n\21\3\21\5\21\u0267\n\21\3\21"+
		"\3\21\7\21\u026b\n\21\f\21\16\21\u026e\13\21\3\21\5\21\u0271\n\21\3\21"+
		"\3\21\5\21\u0275\n\21\3\22\5\22\u0278\n\22\3\22\3\22\5\22\u027c\n\22\3"+
		"\22\3\22\7\22\u0280\n\22\f\22\16\22\u0283\13\22\3\22\5\22\u0286\n\22\3"+
		"\22\5\22\u0289\n\22\3\22\3\22\7\22\u028d\n\22\f\22\16\22\u0290\13\22\3"+
		"\22\5\22\u0293\n\22\3\22\3\22\5\22\u0297\n\22\3\23\5\23\u029a\n\23\3\23"+
		"\3\23\5\23\u029e\n\23\3\23\3\23\3\23\5\23\u02a3\n\23\3\23\3\23\3\23\3"+
		"\23\5\23\u02a9\n\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\5\23\u02b9\n\23\5\23\u02bb\n\23\3\23\5\23\u02be\n"+
		"\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u02c7\n\24\5\24\u02c9\n\24"+
		"\3\25\3\25\6\25\u02cd\n\25\r\25\16\25\u02ce\3\26\6\26\u02d2\n\26\r\26"+
		"\16\26\u02d3\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3"+
		"\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u02ea\n\27\3\30\3\30"+
		"\3\30\3\31\3\31\3\31\3\31\5\31\u02f3\n\31\3\31\3\31\3\31\5\31\u02f8\n"+
		"\31\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u0300\n\31\3\31\3\31\5\31\u0304"+
		"\n\31\3\32\3\32\6\32\u0308\n\32\r\32\16\32\u0309\3\33\3\33\3\33\7\33\u030f"+
		"\n\33\f\33\16\33\u0312\13\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3"+
		"\34\5\34\u031d\n\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\5\35"+
		"\u0328\n\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u0333\n"+
		"\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\5\37\u033e\n\37\3 \6"+
		" \u0341\n \r \16 \u0342\3!\3!\3!\3!\3!\5!\u034a\n!\3!\3!\3!\3!\3!\3!\3"+
		"!\3!\5!\u0354\n!\3\"\3\"\5\"\u0358\n\"\3\"\3\"\3\"\3\"\3\"\5\"\u035f\n"+
		"\"\3#\3#\3#\5#\u0364\n#\3$\3$\5$\u0368\n$\3$\3$\3$\3$\3$\5$\u036f\n$\3"+
		"%\3%\3%\3%\5%\u0375\n%\3&\3&\3&\3&\6&\u037b\n&\r&\16&\u037c\3&\5&\u0380"+
		"\n&\3\'\5\'\u0383\n\'\3\'\3\'\5\'\u0387\n\'\3\'\3\'\5\'\u038b\n\'\3\'"+
		"\5\'\u038e\n\'\3\'\3\'\3\'\3\'\3\'\5\'\u0395\n\'\3\'\3\'\3\'\5\'\u039a"+
		"\n\'\3(\3(\3(\3(\5(\u03a0\n(\3(\3(\3(\3(\3(\3(\5(\u03a8\n(\3(\3(\3(\3"+
		"(\5(\u03ae\n(\3(\3(\5(\u03b2\n(\3(\3(\3(\3(\5(\u03b8\n(\3(\3(\5(\u03bc"+
		"\n(\3)\5)\u03bf\n)\3)\3)\5)\u03c3\n)\3)\3)\3)\3)\3)\3)\5)\u03cb\n)\3)"+
		"\3)\5)\u03cf\n)\3)\3)\3)\3)\5)\u03d5\n)\3)\5)\u03d8\n)\3)\5)\u03db\n)"+
		"\3)\3)\3)\3)\3)\5)\u03e2\n)\3)\3)\3)\5)\u03e7\n)\3*\5*\u03ea\n*\3*\5*"+
		"\u03ed\n*\3*\3*\3*\5*\u03f2\n*\3*\7*\u03f5\n*\f*\16*\u03f8\13*\3*\5*\u03fb"+
		"\n*\3*\3*\3*\5*\u0400\n*\3*\5*\u0403\n*\3*\3*\3*\5*\u0408\n*\3*\5*\u040b"+
		"\n*\3*\3*\3*\5*\u0410\n*\3*\5*\u0413\n*\3*\3*\3*\5*\u0418\n*\3*\3*\3*"+
		"\5*\u041d\n*\3*\5*\u0420\n*\3*\3*\6*\u0424\n*\r*\16*\u0425\3*\3*\5*\u042a"+
		"\n*\3*\7*\u042d\n*\f*\16*\u0430\13*\3*\5*\u0433\n*\3*\3*\3*\5*\u0438\n"+
		"*\3*\5*\u043b\n*\3*\3*\6*\u043f\n*\r*\16*\u0440\3*\3*\5*\u0445\n*\3*\5"+
		"*\u0448\n*\3*\3*\5*\u044c\n*\3+\3+\3+\3+\3+\6+\u0453\n+\r+\16+\u0454\5"+
		"+\u0457\n+\3,\5,\u045a\n,\3,\3,\3,\5,\u045f\n,\3,\5,\u0462\n,\3,\3,\6"+
		",\u0466\n,\r,\16,\u0467\3,\3,\5,\u046c\n,\3,\3,\5,\u0470\n,\3-\3-\3-\3"+
		"-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\5-\u0486\n-\3.\3.\3"+
		".\7.\u048b\n.\f.\16.\u048e\13.\3.\3.\3.\5.\u0493\n.\3/\3/\3/\6/\u0498"+
		"\n/\r/\16/\u0499\3\60\7\60\u049d\n\60\f\60\16\60\u04a0\13\60\3\61\7\61"+
		"\u04a3\n\61\f\61\16\61\u04a6\13\61\3\62\3\62\3\62\3\62\3\62\3\62\3\62"+
		"\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\5\62"+
		"\u04bc\n\62\3\63\3\63\3\63\7\63\u04c1\n\63\f\63\16\63\u04c4\13\63\3\63"+
		"\3\63\3\63\5\63\u04c9\n\63\3\64\3\64\3\64\6\64\u04ce\n\64\r\64\16\64\u04cf"+
		"\3\65\3\65\3\65\3\65\3\65\6\65\u04d7\n\65\r\65\16\65\u04d8\5\65\u04db"+
		"\n\65\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66"+
		"\3\66\3\66\3\66\3\66\3\66\3\66\3\66\5\66\u04f1\n\66\3\67\3\67\3\67\7\67"+
		"\u04f6\n\67\f\67\16\67\u04f9\13\67\3\67\3\67\3\67\5\67\u04fe\n\67\38\3"+
		"8\38\68\u0503\n8\r8\168\u0504\39\39\39\39\39\39\39\39\39\39\39\39\39\3"+
		"9\39\39\39\39\39\39\59\u051b\n9\3:\3:\3:\7:\u0520\n:\f:\16:\u0523\13:"+
		"\3:\3:\3:\5:\u0528\n:\3;\3;\3;\6;\u052d\n;\r;\16;\u052e\3<\3<\3<\3<\3"+
		"<\6<\u0536\n<\r<\16<\u0537\5<\u053a\n<\3=\3=\3=\3=\3=\3=\3=\3=\3=\3=\3"+
		"=\3=\3=\3=\3=\3=\3=\3=\3=\3=\5=\u0550\n=\3>\3>\3>\3>\3>\3>\3>\3>\3>\3"+
		">\3>\3>\3>\3>\3>\3>\3>\3>\3>\3>\5>\u0566\n>\3?\3?\3?\3?\3?\3?\3?\3?\3"+
		"?\5?\u0571\n?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3"+
		"?\3?\3?\3?\3?\5?\u058a\n?\3@\3@\3@\3@\3@\3@\3@\3@\3@\3@\3@\3@\3@\3@\3"+
		"@\3@\3@\3@\3@\3@\5@\u05a0\n@\3A\3A\5A\u05a4\nA\3A\3A\3B\3B\3C\5C\u05ab"+
		"\nC\3C\3C\3C\6C\u05b0\nC\rC\16C\u05b1\3C\3C\3C\6C\u05b7\nC\rC\16C\u05b8"+
		"\3C\3C\3C\3C\5C\u05bf\nC\3D\3D\5D\u05c3\nD\3E\6E\u05c6\nE\rE\16E\u05c7"+
		"\3F\3F\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\5G\u05de"+
		"\nG\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\5G\u05ec\nG\3G\3G\5G\u05f0\nG"+
		"\3H\5H\u05f3\nH\3H\3H\3H\3H\3H\3H\5H\u05fb\nH\3H\3H\3H\3H\3H\3H\3H\5H"+
		"\u0604\nH\3H\3H\3H\3H\3H\3H\3H\3H\3H\5H\u060f\nH\3H\3H\3H\5H\u0614\nH"+
		"\3I\6I\u0617\nI\rI\16I\u0618\3J\6J\u061c\nJ\rJ\16J\u061d\3K\5K\u0621\n"+
		"K\3K\3K\3K\3K\3K\3K\5K\u0629\nK\3L\5L\u062c\nL\3L\3L\5L\u0630\nL\3L\3"+
		"L\3L\5L\u0635\nL\3L\5L\u0638\nL\3M\3M\6M\u063c\nM\rM\16M\u063d\5M\u0640"+
		"\nM\3N\3N\3N\3O\3O\3P\5P\u0648\nP\3P\3P\5P\u064c\nP\3P\3P\3P\5P\u0651"+
		"\nP\3P\5P\u0654\nP\3Q\3Q\3Q\3Q\3Q\3Q\3Q\5Q\u065d\nQ\3R\3R\3S\3S\5S\u0663"+
		"\nS\3T\3T\5T\u0667\nT\3U\3U\3U\3U\3U\3U\3U\3U\3U\5U\u0672\nU\3U\3U\3U"+
		"\3U\3U\3U\5U\u067a\nU\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U"+
		"\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\5U\u0699\nU\3U\3U\3U\3U\3U\3U"+
		"\3U\3U\5U\u06a3\nU\3U\3U\3U\3U\3U\3U\5U\u06ab\nU\3V\3V\3V\3V\3V\3V\3V"+
		"\3V\3V\3V\3V\5V\u06b8\nV\3W\6W\u06bb\nW\rW\16W\u06bc\3X\3X\5X\u06c1\n"+
		"X\3Y\3Y\3Z\3Z\3Z\3Z\3[\3[\3[\3[\3[\3[\3[\3[\3[\3[\3[\3[\3[\3[\3[\3[\3"+
		"[\3[\3[\3[\3[\3[\3[\3[\3[\3[\3[\3[\3[\3[\5[\u06e7\n[\3\\\3\\\3\\\3\\\3"+
		"\\\5\\\u06ee\n\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3"+
		"\\\3\\\5\\\u06ff\n\\\3]\3]\3]\3]\3]\3]\3^\3^\3^\3^\3^\3^\5^\u070d\n^\3"+
		"_\3_\3_\3`\3`\3`\5`\u0715\n`\3a\3a\3a\3a\3b\3b\3b\3c\5c\u071f\nc\3c\3"+
		"c\5c\u0723\nc\3c\5c\u0726\nc\3d\3d\5d\u072a\nd\3d\3d\5d\u072e\nd\3e\3"+
		"e\5e\u0732\ne\3e\3e\5e\u0736\ne\3f\3f\3f\3f\3f\3f\3f\3f\3f\3f\3f\3f\3"+
		"f\3f\3f\3f\3f\3f\3f\3f\3f\3f\3f\3f\3f\3f\5f\u0752\nf\3g\3g\3h\3h\6h\u0758"+
		"\nh\rh\16h\u0759\3i\3i\5i\u075e\ni\3i\3i\5i\u0762\ni\3i\3i\5i\u0766\n"+
		"i\5i\u0768\ni\3j\3j\3j\6j\u076d\nj\rj\16j\u076e\3j\5j\u0772\nj\3k\3k\3"+
		"k\3k\5k\u0778\nk\3k\5k\u077b\nk\3k\3k\3k\3k\3k\5k\u0782\nk\5k\u0784\n"+
		"k\3k\2\2l\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\66"+
		"8:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a"+
		"\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e\u00a0\u00a2"+
		"\u00a4\u00a6\u00a8\u00aa\u00ac\u00ae\u00b0\u00b2\u00b4\u00b6\u00b8\u00ba"+
		"\u00bc\u00be\u00c0\u00c2\u00c4\u00c6\u00c8\u00ca\u00cc\u00ce\u00d0\u00d2"+
		"\u00d4\2\37\3\2\6\7\3\2\b\t\3\2\n\13\3\2\f\r\3\2\16\17\3\2\20\21\3\2\22"+
		"\23\3\2\24\25\3\2\26\27\4\2\3\5\31\31\3\2\34\35\3\2\36\37\3\2 !\4\2\5"+
		"\5\31\31\5\2\32\32\"\"$%\5\2\33\33##&\'\3\2()\3\2*+\3\2,-\3\2./\3\2\60"+
		"\61\3\2\62\63\4\2()./\4\2\64\64::\3\2\65\66\3\2\678\4\299;;\3\2;<\3\2"+
		"\658\2\u08a2\2\u00df\3\2\2\2\4\u00e1\3\2\2\2\6\u00ed\3\2\2\2\b\u010a\3"+
		"\2\2\2\n\u0125\3\2\2\2\f\u0127\3\2\2\2\16\u0157\3\2\2\2\20\u0159\3\2\2"+
		"\2\22\u0160\3\2\2\2\24\u017d\3\2\2\2\26\u0198\3\2\2\2\30\u01b3\3\2\2\2"+
		"\32\u01ce\3\2\2\2\34\u0200\3\2\2\2\36\u0252\3\2\2\2 \u0274\3\2\2\2\"\u0296"+
		"\3\2\2\2$\u0299\3\2\2\2&\u02bf\3\2\2\2(\u02ca\3\2\2\2*\u02d1\3\2\2\2,"+
		"\u02e9\3\2\2\2.\u02eb\3\2\2\2\60\u0303\3\2\2\2\62\u0305\3\2\2\2\64\u0310"+
		"\3\2\2\2\66\u031c\3\2\2\28\u0327\3\2\2\2:\u0332\3\2\2\2<\u033d\3\2\2\2"+
		">\u0340\3\2\2\2@\u0353\3\2\2\2B\u035e\3\2\2\2D\u0360\3\2\2\2F\u036e\3"+
		"\2\2\2H\u0370\3\2\2\2J\u037f\3\2\2\2L\u0399\3\2\2\2N\u03bb\3\2\2\2P\u03e6"+
		"\3\2\2\2R\u044b\3\2\2\2T\u0456\3\2\2\2V\u046f\3\2\2\2X\u0485\3\2\2\2Z"+
		"\u0492\3\2\2\2\\\u0494\3\2\2\2^\u049e\3\2\2\2`\u04a4\3\2\2\2b\u04bb\3"+
		"\2\2\2d\u04c8\3\2\2\2f\u04ca\3\2\2\2h\u04da\3\2\2\2j\u04f0\3\2\2\2l\u04fd"+
		"\3\2\2\2n\u04ff\3\2\2\2p\u051a\3\2\2\2r\u0527\3\2\2\2t\u0529\3\2\2\2v"+
		"\u0539\3\2\2\2x\u054f\3\2\2\2z\u0565\3\2\2\2|\u0589\3\2\2\2~\u059f\3\2"+
		"\2\2\u0080\u05a1\3\2\2\2\u0082\u05a7\3\2\2\2\u0084\u05be\3\2\2\2\u0086"+
		"\u05c0\3\2\2\2\u0088\u05c5\3\2\2\2\u008a\u05c9\3\2\2\2\u008c\u05ef\3\2"+
		"\2\2\u008e\u0613\3\2\2\2\u0090\u0616\3\2\2\2\u0092\u061b\3\2\2\2\u0094"+
		"\u0628\3\2\2\2\u0096\u0637\3\2\2\2\u0098\u063f\3\2\2\2\u009a\u0641\3\2"+
		"\2\2\u009c\u0644\3\2\2\2\u009e\u0653\3\2\2\2\u00a0\u065c\3\2\2\2\u00a2"+
		"\u065e\3\2\2\2\u00a4\u0662\3\2\2\2\u00a6\u0666\3\2\2\2\u00a8\u06aa\3\2"+
		"\2\2\u00aa\u06b7\3\2\2\2\u00ac\u06ba\3\2\2\2\u00ae\u06c0\3\2\2\2\u00b0"+
		"\u06c2\3\2\2\2\u00b2\u06c4\3\2\2\2\u00b4\u06e6\3\2\2\2\u00b6\u06fe\3\2"+
		"\2\2\u00b8\u0700\3\2\2\2\u00ba\u070c\3\2\2\2\u00bc\u070e\3\2\2\2\u00be"+
		"\u0711\3\2\2\2\u00c0\u0716\3\2\2\2\u00c2\u071a\3\2\2\2\u00c4\u0725\3\2"+
		"\2\2\u00c6\u072d\3\2\2\2\u00c8\u0735\3\2\2\2\u00ca\u0751\3\2\2\2\u00cc"+
		"\u0753\3\2\2\2\u00ce\u0757\3\2\2\2\u00d0\u0767\3\2\2\2\u00d2\u0769\3\2"+
		"\2\2\u00d4\u0783\3\2\2\2\u00d6\u00e0\5\4\3\2\u00d7\u00e0\5\f\7\2\u00d8"+
		"\u00db\7>\2\2\u00d9\u00db\13\2\2\2\u00da\u00d8\3\2\2\2\u00da\u00d9\3\2"+
		"\2\2\u00db\u00dc\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd"+
		"\u00de\3\2\2\2\u00de\u00e0\b\2\1\2\u00df\u00d6\3\2\2\2\u00df\u00d7\3\2"+
		"\2\2\u00df\u00da\3\2\2\2\u00e0\3\3\2\2\2\u00e1\u00e6\5\6\4\2\u00e2\u00e5"+
		"\5\n\6\2\u00e3\u00e5\5\b\5\2\u00e4\u00e2\3\2\2\2\u00e4\u00e3\3\2\2\2\u00e5"+
		"\u00e8\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00ea\3\2"+
		"\2\2\u00e8\u00e6\3\2\2\2\u00e9\u00eb\7\3\2\2\u00ea\u00e9\3\2\2\2\u00ea"+
		"\u00eb\3\2\2\2\u00eb\5\3\2\2\2\u00ec\u00ee\5&\24\2\u00ed\u00ec\3\2\2\2"+
		"\u00ed\u00ee\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f0\5\34\17\2\u00f0\7"+
		"\3\2\2\2\u00f1\u00f3\7\4\2\2\u00f2\u00f4\5&\24\2\u00f3\u00f2\3\2\2\2\u00f3"+
		"\u00f4\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u010b\5\36\20\2\u00f6\u00f8\5"+
		"&\24\2\u00f7\u00f6\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9"+
		"\u00fa\5\36\20\2\u00fa\u00fb\b\5\1\2\u00fb\u010b\3\2\2\2\u00fc\u00fe\5"+
		"(\25\2\u00fd\u00ff\5&\24\2\u00fe\u00fd\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff"+
		"\u0100\3\2\2\2\u0100\u0101\5\36\20\2\u0101\u0102\b\5\1\2\u0102\u010b\3"+
		"\2\2\2\u0103\u0105\5*\26\2\u0104\u0106\5&\24\2\u0105\u0104\3\2\2\2\u0105"+
		"\u0106\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u0108\5\36\20\2\u0108\u0109\b"+
		"\5\1\2\u0109\u010b\3\2\2\2\u010a\u00f1\3\2\2\2\u010a\u00f7\3\2\2\2\u010a"+
		"\u00fc\3\2\2\2\u010a\u0103\3\2\2\2\u010b\t\3\2\2\2\u010c\u010e\7\4\2\2"+
		"\u010d\u010f\5&\24\2\u010e\u010d\3\2\2\2\u010e\u010f\3\2\2\2\u010f\u0110"+
		"\3\2\2\2\u0110\u0126\5$\23\2\u0111\u0113\5&\24\2\u0112\u0111\3\2\2\2\u0112"+
		"\u0113\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0115\5$\23\2\u0115\u0116\b\6"+
		"\1\2\u0116\u0126\3\2\2\2\u0117\u0119\5(\25\2\u0118\u011a\5&\24\2\u0119"+
		"\u0118\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u011c\5$"+
		"\23\2\u011c\u011d\b\6\1\2\u011d\u0126\3\2\2\2\u011e\u0120\5*\26\2\u011f"+
		"\u0121\5&\24\2\u0120\u011f\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u0122\3\2"+
		"\2\2\u0122\u0123\5$\23\2\u0123\u0124\b\6\1\2\u0124\u0126\3\2\2\2\u0125"+
		"\u010c\3\2\2\2\u0125\u0112\3\2\2\2\u0125\u0117\3\2\2\2\u0125\u011e\3\2"+
		"\2\2\u0126\13\3\2\2\2\u0127\u012b\5\16\b\2\u0128\u012a\5\20\t\2\u0129"+
		"\u0128\3\2\2\2\u012a\u012d\3\2\2\2\u012b\u0129\3\2\2\2\u012b\u012c\3\2"+
		"\2\2\u012c\u0131\3\2\2\2\u012d\u012b\3\2\2\2\u012e\u0130\5\32\16\2\u012f"+
		"\u012e\3\2\2\2\u0130\u0133\3\2\2\2\u0131\u012f\3\2\2\2\u0131\u0132\3\2"+
		"\2\2\u0132\u0135\3\2\2\2\u0133\u0131\3\2\2\2\u0134\u0136\7\3\2\2\u0135"+
		"\u0134\3\2\2\2\u0135\u0136\3\2\2\2\u0136\r\3\2\2\2\u0137\u0138\5\22\n"+
		"\2\u0138\u013c\5\26\f\2\u0139\u013b\5\30\r\2\u013a\u0139\3\2\2\2\u013b"+
		"\u013e\3\2\2\2\u013c\u013a\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u0142\3\2"+
		"\2\2\u013e\u013c\3\2\2\2\u013f\u0141\5\26\f\2\u0140\u013f\3\2\2\2\u0141"+
		"\u0144\3\2\2\2\u0142\u0140\3\2\2\2\u0142\u0143\3\2\2\2\u0143\u0158\3\2"+
		"\2\2\u0144\u0142\3\2\2\2\u0145\u0147\5\22\n\2\u0146\u0148\5\26\f\2\u0147"+
		"\u0146\3\2\2\2\u0148\u0149\3\2\2\2\u0149\u0147\3\2\2\2\u0149\u014a\3\2"+
		"\2\2\u014a\u014b\3\2\2\2\u014b\u014d\5\26\f\2\u014c\u014e\5\30\r\2\u014d"+
		"\u014c\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u014d\3\2\2\2\u014f\u0150\3\2"+
		"\2\2\u0150\u0154\3\2\2\2\u0151\u0153\5\26\f\2\u0152\u0151\3\2\2\2\u0153"+
		"\u0156\3\2\2\2\u0154\u0152\3\2\2\2\u0154\u0155\3\2\2\2\u0155\u0158\3\2"+
		"\2\2\u0156\u0154\3\2\2\2\u0157\u0137\3\2\2\2\u0157\u0145\3\2\2\2\u0158"+
		"\17\3\2\2\2\u0159\u015b\5\24\13\2\u015a\u015c\5\26\f\2\u015b\u015a\3\2"+
		"\2\2\u015c\u015d\3\2\2\2\u015d\u015b\3\2\2\2\u015d\u015e\3\2\2\2\u015e"+
		"\21\3\2\2\2\u015f\u0161\5&\24\2\u0160\u015f\3\2\2\2\u0160\u0161\3\2\2"+
		"\2\u0161\u0162\3\2\2\2\u0162\u0163\5\34\17\2\u0163\23\3\2\2\2\u0164\u0166"+
		"\7\4\2\2\u0165\u0167\5&\24\2\u0166\u0165\3\2\2\2\u0166\u0167\3\2\2\2\u0167"+
		"\u0168\3\2\2\2\u0168\u017e\5\34\17\2\u0169\u016b\5&\24\2\u016a\u0169\3"+
		"\2\2\2\u016a\u016b\3\2\2\2\u016b\u016c\3\2\2\2\u016c\u016d\5\34\17\2\u016d"+
		"\u016e\b\13\1\2\u016e\u017e\3\2\2\2\u016f\u0171\5(\25\2\u0170\u0172\5"+
		"&\24\2\u0171\u0170\3\2\2\2\u0171\u0172\3\2\2\2\u0172\u0173\3\2\2\2\u0173"+
		"\u0174\5\34\17\2\u0174\u0175\b\13\1\2\u0175\u017e\3\2\2\2\u0176\u0178"+
		"\5*\26\2\u0177\u0179\5&\24\2\u0178\u0177\3\2\2\2\u0178\u0179\3\2\2\2\u0179"+
		"\u017a\3\2\2\2\u017a\u017b\5\34\17\2\u017b\u017c\b\13\1\2\u017c\u017e"+
		"\3\2\2\2\u017d\u0164\3\2\2\2\u017d\u016a\3\2\2\2\u017d\u016f\3\2\2\2\u017d"+
		"\u0176\3\2\2\2\u017e\25\3\2\2\2\u017f\u0181\7\4\2\2\u0180\u0182\5&\24"+
		"\2\u0181\u0180\3\2\2\2\u0181\u0182\3\2\2\2\u0182\u0183\3\2\2\2\u0183\u0199"+
		"\5 \21\2\u0184\u0186\5&\24\2\u0185\u0184\3\2\2\2\u0185\u0186\3\2\2\2\u0186"+
		"\u0187\3\2\2\2\u0187\u0188\5 \21\2\u0188\u0189\b\f\1\2\u0189\u0199\3\2"+
		"\2\2\u018a\u018c\5(\25\2\u018b\u018d\5&\24\2\u018c\u018b\3\2\2\2\u018c"+
		"\u018d\3\2\2\2\u018d\u018e\3\2\2\2\u018e\u018f\5 \21\2\u018f\u0190\b\f"+
		"\1\2\u0190\u0199\3\2\2\2\u0191\u0193\5*\26\2\u0192\u0194\5&\24\2\u0193"+
		"\u0192\3\2\2\2\u0193\u0194\3\2\2\2\u0194\u0195\3\2\2\2\u0195\u0196\5 "+
		"\21\2\u0196\u0197\b\f\1\2\u0197\u0199\3\2\2\2\u0198\u017f\3\2\2\2\u0198"+
		"\u0185\3\2\2\2\u0198\u018a\3\2\2\2\u0198\u0191\3\2\2\2\u0199\27\3\2\2"+
		"\2\u019a\u019c\7\4\2\2\u019b\u019d\5&\24\2\u019c\u019b\3\2\2\2\u019c\u019d"+
		"\3\2\2\2\u019d\u019e\3\2\2\2\u019e\u01b4\5\"\22\2\u019f\u01a1\5&\24\2"+
		"\u01a0\u019f\3\2\2\2\u01a0\u01a1\3\2\2\2\u01a1\u01a2\3\2\2\2\u01a2\u01a3"+
		"\5\"\22\2\u01a3\u01a4\b\r\1\2\u01a4\u01b4\3\2\2\2\u01a5\u01a7\5(\25\2"+
		"\u01a6\u01a8\5&\24\2\u01a7\u01a6\3\2\2\2\u01a7\u01a8\3\2\2\2\u01a8\u01a9"+
		"\3\2\2\2\u01a9\u01aa\5\"\22\2\u01aa\u01ab\b\r\1\2\u01ab\u01b4\3\2\2\2"+
		"\u01ac\u01ae\5*\26\2\u01ad\u01af\5&\24\2\u01ae\u01ad\3\2\2\2\u01ae\u01af"+
		"\3\2\2\2\u01af\u01b0\3\2\2\2\u01b0\u01b1\5\"\22\2\u01b1\u01b2\b\r\1\2"+
		"\u01b2\u01b4\3\2\2\2\u01b3\u019a\3\2\2\2\u01b3\u01a0\3\2\2\2\u01b3\u01a5"+
		"\3\2\2\2\u01b3\u01ac\3\2\2\2\u01b4\31\3\2\2\2\u01b5\u01b7\7\4\2\2\u01b6"+
		"\u01b8\5&\24\2\u01b7\u01b6\3\2\2\2\u01b7\u01b8\3\2\2\2\u01b8\u01b9\3\2"+
		"\2\2\u01b9\u01cf\5\34\17\2\u01ba\u01bc\5&\24\2\u01bb\u01ba\3\2\2\2\u01bb"+
		"\u01bc\3\2\2\2\u01bc\u01bd\3\2\2\2\u01bd\u01be\5\34\17\2\u01be\u01bf\b"+
		"\16\1\2\u01bf\u01cf\3\2\2\2\u01c0\u01c2\5(\25\2\u01c1\u01c3\5&\24\2\u01c2"+
		"\u01c1\3\2\2\2\u01c2\u01c3\3\2\2\2\u01c3\u01c4\3\2\2\2\u01c4\u01c5\5\34"+
		"\17\2\u01c5\u01c6\b\16\1\2\u01c6\u01cf\3\2\2\2\u01c7\u01c9\5*\26\2\u01c8"+
		"\u01ca\5&\24\2\u01c9\u01c8\3\2\2\2\u01c9\u01ca\3\2\2\2\u01ca\u01cb\3\2"+
		"\2\2\u01cb\u01cc\5\34\17\2\u01cc\u01cd\b\16\1\2\u01cd\u01cf\3\2\2\2\u01ce"+
		"\u01b5\3\2\2\2\u01ce\u01bb\3\2\2\2\u01ce\u01c0\3\2\2\2\u01ce\u01c7\3\2"+
		"\2\2\u01cf\33\3\2\2\2\u01d0\u01d2\5\u00ba^\2\u01d1\u01d0\3\2\2\2\u01d1"+
		"\u01d2\3\2\2\2\u01d2\u01d3\3\2\2\2\u01d3\u01d5\5\u0098M\2\u01d4\u01d6"+
		"\5,\27\2\u01d5\u01d4\3\2\2\2\u01d5\u01d6\3\2\2\2\u01d6\u01d7\3\2\2\2\u01d7"+
		"\u01db\5\60\31\2\u01d8\u01da\5<\37\2\u01d9\u01d8\3\2\2\2\u01da\u01dd\3"+
		"\2\2\2\u01db\u01d9\3\2\2\2\u01db\u01dc\3\2\2\2\u01dc\u01df\3\2\2\2\u01dd"+
		"\u01db\3\2\2\2\u01de\u01e0\5\u008cG\2\u01df\u01de\3\2\2\2\u01df\u01e0"+
		"\3\2\2\2\u01e0\u0201\3\2\2\2\u01e1\u01e3\5\u00ba^\2\u01e2\u01e1\3\2\2"+
		"\2\u01e2\u01e3\3\2\2\2\u01e3\u01e4\3\2\2\2\u01e4\u01e6\5\u0098M\2\u01e5"+
		"\u01e7\5,\27\2\u01e6\u01e5\3\2\2\2\u01e6\u01e7\3\2\2\2\u01e7\u01eb\3\2"+
		"\2\2\u01e8\u01ea\5<\37\2\u01e9\u01e8\3\2\2\2\u01ea\u01ed\3\2\2\2\u01eb"+
		"\u01e9\3\2\2\2\u01eb\u01ec\3\2\2\2\u01ec\u01ef\3\2\2\2\u01ed\u01eb\3\2"+
		"\2\2\u01ee\u01f0\5\u008cG\2\u01ef\u01ee\3\2\2\2\u01ef\u01f0\3\2\2\2\u01f0"+
		"\u01f1\3\2\2\2\u01f1\u01f2\b\17\1\2\u01f2\u0201\3\2\2\2\u01f3\u01f5\5"+
		"\u00ba^\2\u01f4\u01f3\3\2\2\2\u01f4\u01f5\3\2\2\2\u01f5\u01f7\3\2\2\2"+
		"\u01f6\u01f8\5<\37\2\u01f7\u01f6\3\2\2\2\u01f8\u01f9\3\2\2\2\u01f9\u01f7"+
		"\3\2\2\2\u01f9\u01fa\3\2\2\2\u01fa\u01fc\3\2\2\2\u01fb\u01fd\5\u008cG"+
		"\2\u01fc\u01fb\3\2\2\2\u01fc\u01fd\3\2\2\2\u01fd\u01fe\3\2\2\2\u01fe\u01ff"+
		"\b\17\1\2\u01ff\u0201\3\2\2\2\u0200\u01d1\3\2\2\2\u0200\u01e2\3\2\2\2"+
		"\u0200\u01f4\3\2\2\2\u0201\35\3\2\2\2\u0202\u0204\5\u00ba^\2\u0203\u0202"+
		"\3\2\2\2\u0203\u0204\3\2\2\2\u0204\u0205\3\2\2\2\u0205\u0207\5\u0098M"+
		"\2\u0206\u0208\5,\27\2\u0207\u0206\3\2\2\2\u0207\u0208\3\2\2\2\u0208\u0209"+
		"\3\2\2\2\u0209\u020d\5\60\31\2\u020a\u020c\5<\37\2\u020b\u020a\3\2\2\2"+
		"\u020c\u020f\3\2\2\2\u020d\u020b\3\2\2\2\u020d\u020e\3\2\2\2\u020e\u0211"+
		"\3\2\2\2\u020f\u020d\3\2\2\2\u0210\u0212\5\u008cG\2\u0211\u0210\3\2\2"+
		"\2\u0211\u0212\3\2\2\2\u0212\u0253\3\2\2\2\u0213\u0215\5\u00ba^\2\u0214"+
		"\u0213\3\2\2\2\u0214\u0215\3\2\2\2\u0215\u0216\3\2\2\2\u0216\u0218\5\u0098"+
		"M\2\u0217\u0219\5,\27\2\u0218\u0217\3\2\2\2\u0218\u0219\3\2\2\2\u0219"+
		"\u021a\3\2\2\2\u021a\u021e\5\66\34\2\u021b\u021d\5<\37\2\u021c\u021b\3"+
		"\2\2\2\u021d\u0220\3\2\2\2\u021e\u021c\3\2\2\2\u021e\u021f\3\2\2\2\u021f"+
		"\u0222\3\2\2\2\u0220\u021e\3\2\2\2\u0221\u0223\5\u008cG\2\u0222\u0221"+
		"\3\2\2\2\u0222\u0223\3\2\2\2\u0223\u0253\3\2\2\2\u0224\u0226\5\u00ba^"+
		"\2\u0225\u0224\3\2\2\2\u0225\u0226\3\2\2\2\u0226\u0227\3\2\2\2\u0227\u022b"+
		"\5\66\34\2\u0228\u022a\5<\37\2\u0229\u0228\3\2\2\2\u022a\u022d\3\2\2\2"+
		"\u022b\u0229\3\2\2\2\u022b\u022c\3\2\2\2\u022c\u022f\3\2\2\2\u022d\u022b"+
		"\3\2\2\2\u022e\u0230\5\u008cG\2\u022f\u022e\3\2\2\2\u022f\u0230\3\2\2"+
		"\2\u0230\u0231\3\2\2\2\u0231\u0232\b\20\1\2\u0232\u0253\3\2\2\2\u0233"+
		"\u0235\5\u00ba^\2\u0234\u0233\3\2\2\2\u0234\u0235\3\2\2\2\u0235\u0236"+
		"\3\2\2\2\u0236\u0238\5\u0098M\2\u0237\u0239\5,\27\2\u0238\u0237\3\2\2"+
		"\2\u0238\u0239\3\2\2\2\u0239\u023d\3\2\2\2\u023a\u023c\5<\37\2\u023b\u023a"+
		"\3\2\2\2\u023c\u023f\3\2\2\2\u023d\u023b\3\2\2\2\u023d\u023e\3\2\2\2\u023e"+
		"\u0241\3\2\2\2\u023f\u023d\3\2\2\2\u0240\u0242\5\u008cG\2\u0241\u0240"+
		"\3\2\2\2\u0241\u0242\3\2\2\2\u0242\u0243\3\2\2\2\u0243\u0244\b\20\1\2"+
		"\u0244\u0253\3\2\2\2\u0245\u0247\5\u00ba^\2\u0246\u0245\3\2\2\2\u0246"+
		"\u0247\3\2\2\2\u0247\u0249\3\2\2\2\u0248\u024a\5<\37\2\u0249\u0248\3\2"+
		"\2\2\u024a\u024b\3\2\2\2\u024b\u0249\3\2\2\2\u024b\u024c\3\2\2\2\u024c"+
		"\u024e\3\2\2\2\u024d\u024f\5\u008cG\2\u024e\u024d\3\2\2\2\u024e\u024f"+
		"\3\2\2\2\u024f\u0250\3\2\2\2\u0250\u0251\b\20\1\2\u0251\u0253\3\2\2\2"+
		"\u0252\u0203\3\2\2\2\u0252\u0214\3\2\2\2\u0252\u0225\3\2\2\2\u0252\u0234"+
		"\3\2\2\2\u0252\u0246\3\2\2\2\u0253\37\3\2\2\2\u0254\u0256\5\u00ba^\2\u0255"+
		"\u0254\3\2\2\2\u0255\u0256\3\2\2\2\u0256\u0257\3\2\2\2\u0257\u0259\5\u0098"+
		"M\2\u0258\u025a\5,\27\2\u0259\u0258\3\2\2\2\u0259\u025a\3\2\2\2\u025a"+
		"\u025b\3\2\2\2\u025b\u025f\58\35\2\u025c\u025e\5<\37\2\u025d\u025c\3\2"+
		"\2\2\u025e\u0261\3\2\2\2\u025f\u025d\3\2\2\2\u025f\u0260\3\2\2\2\u0260"+
		"\u0263\3\2\2\2\u0261\u025f\3\2\2\2\u0262\u0264\5\u008cG\2\u0263\u0262"+
		"\3\2\2\2\u0263\u0264\3\2\2\2\u0264\u0275\3\2\2\2\u0265\u0267\5\u00ba^"+
		"\2\u0266\u0265\3\2\2\2\u0266\u0267\3\2\2\2\u0267\u0268\3\2\2\2\u0268\u026c"+
		"\58\35\2\u0269\u026b\5<\37\2\u026a\u0269\3\2\2\2\u026b\u026e\3\2\2\2\u026c"+
		"\u026a\3\2\2\2\u026c\u026d\3\2\2\2\u026d\u0270\3\2\2\2\u026e\u026c\3\2"+
		"\2\2\u026f\u0271\5\u008cG\2\u0270\u026f\3\2\2\2\u0270\u0271\3\2\2\2\u0271"+
		"\u0272\3\2\2\2\u0272\u0273\b\21\1\2\u0273\u0275\3\2\2\2\u0274\u0255\3"+
		"\2\2\2\u0274\u0266\3\2\2\2\u0275!\3\2\2\2\u0276\u0278\5\u00ba^\2\u0277"+
		"\u0276\3\2\2\2\u0277\u0278\3\2\2\2\u0278\u0279\3\2\2\2\u0279\u027b\5\u0098"+
		"M\2\u027a\u027c\5,\27\2\u027b\u027a\3\2\2\2\u027b\u027c\3\2\2\2\u027c"+
		"\u027d\3\2\2\2\u027d\u0281\5:\36\2\u027e\u0280\5<\37\2\u027f\u027e\3\2"+
		"\2\2\u0280\u0283\3\2\2\2\u0281\u027f\3\2\2\2\u0281\u0282\3\2\2\2\u0282"+
		"\u0285\3\2\2\2\u0283\u0281\3\2\2\2\u0284\u0286\5\u008cG\2\u0285\u0284"+
		"\3\2\2\2\u0285\u0286\3\2\2\2\u0286\u0297\3\2\2\2\u0287\u0289\5\u00ba^"+
		"\2\u0288\u0287\3\2\2\2\u0288\u0289\3\2\2\2\u0289\u028a\3\2\2\2\u028a\u028e"+
		"\5:\36\2\u028b\u028d\5<\37\2\u028c\u028b\3\2\2\2\u028d\u0290\3\2\2\2\u028e"+
		"\u028c\3\2\2\2\u028e\u028f\3\2\2\2\u028f\u0292\3\2\2\2\u0290\u028e\3\2"+
		"\2\2\u0291\u0293\5\u008cG\2\u0292\u0291\3\2\2\2\u0292\u0293\3\2\2\2\u0293"+
		"\u0294\3\2\2\2\u0294\u0295\b\22\1\2\u0295\u0297\3\2\2\2\u0296\u0277\3"+
		"\2\2\2\u0296\u0288\3\2\2\2\u0297#\3\2\2\2\u0298\u029a\5\u00ba^\2\u0299"+
		"\u0298\3\2\2\2\u0299\u029a\3\2\2\2\u029a\u02a2\3\2\2\2\u029b\u029d\5\u0098"+
		"M\2\u029c\u029e\5,\27\2\u029d\u029c\3\2\2\2\u029d\u029e\3\2\2\2\u029e"+
		"\u029f\3\2\2\2\u029f\u02a0\5\60\31\2\u02a0\u02a1\7\5\2\2\u02a1\u02a3\3"+
		"\2\2\2\u02a2\u029b\3\2\2\2\u02a2\u02a3\3\2\2\2\u02a3\u02a4\3\2\2\2\u02a4"+
		"\u02a5\t\2\2\2\u02a5\u02a6\t\3\2\2\u02a6\u02a8\t\2\2\2\u02a7\u02a9\7;"+
		"\2\2\u02a8\u02a7\3\2\2\2\u02a8\u02a9\3\2\2\2\u02a9\u02aa\3\2\2\2\u02aa"+
		"\u02ab\t\4\2\2\u02ab\u02ac\t\5\2\2\u02ac\u02ad\t\3\2\2\u02ad\u02ae\t\2"+
		"\2\2\u02ae\u02af\t\6\2\2\u02af\u02ba\t\5\2\2\u02b0\u02b1\t\6\2\2\u02b1"+
		"\u02b2\t\7\2\2\u02b2\u02b8\t\2\2\2\u02b3\u02b4\t\3\2\2\u02b4\u02b5\t\b"+
		"\2\2\u02b5\u02b6\t\t\2\2\u02b6\u02b7\t\6\2\2\u02b7\u02b9\t\5\2\2\u02b8"+
		"\u02b3\3\2\2\2\u02b8\u02b9\3\2\2\2\u02b9\u02bb\3\2\2\2\u02ba\u02b0\3\2"+
		"\2\2\u02ba\u02bb\3\2\2\2\u02bb\u02bd\3\2\2\2\u02bc\u02be\5\u008cG\2\u02bd"+
		"\u02bc\3\2\2\2\u02bd\u02be\3\2\2\2\u02be%\3\2\2\2\u02bf\u02c8\5\u00b2"+
		"Z\2\u02c0\u02c1\t\4\2\2\u02c1\u02c2\t\5\2\2\u02c2\u02c3\t\3\2\2\u02c3"+
		"\u02c4\t\2\2\2\u02c4\u02c6\t\n\2\2\u02c5\u02c7\7\30\2\2\u02c6\u02c5\3"+
		"\2\2\2\u02c6\u02c7\3\2\2\2\u02c7\u02c9\3\2\2\2\u02c8\u02c0\3\2\2\2\u02c8"+
		"\u02c9\3\2\2\2\u02c9\'\3\2\2\2\u02ca\u02cc\7\4\2\2\u02cb\u02cd\7\4\2\2"+
		"\u02cc\u02cb\3\2\2\2\u02cd\u02ce\3\2\2\2\u02ce\u02cc\3\2\2\2\u02ce\u02cf"+
		"\3\2\2\2\u02cf)\3\2\2\2\u02d0\u02d2\t\13\2\2\u02d1\u02d0\3\2\2\2\u02d2"+
		"\u02d3\3\2\2\2\u02d3\u02d1\3\2\2\2\u02d3\u02d4\3\2\2\2\u02d4+\3\2\2\2"+
		"\u02d5\u02d6\7\32\2\2\u02d6\u02d7\5.\30\2\u02d7\u02d8\7\33\2\2\u02d8\u02ea"+
		"\3\2\2\2\u02d9\u02da\5.\30\2\u02da\u02db\7\33\2\2\u02db\u02dc\b\27\1\2"+
		"\u02dc\u02ea\3\2\2\2\u02dd\u02de\7\32\2\2\u02de\u02df\5.\30\2\u02df\u02e0"+
		"\b\27\1\2\u02e0\u02ea\3\2\2\2\u02e1\u02e2\5.\30\2\u02e2\u02e3\b\27\1\2"+
		"\u02e3\u02ea\3\2\2\2\u02e4\u02e5\5^\60\2\u02e5\u02e6\5.\30\2\u02e6\u02e7"+
		"\5`\61\2\u02e7\u02e8\b\27\1\2\u02e8\u02ea\3\2\2\2\u02e9\u02d5\3\2\2\2"+
		"\u02e9\u02d9\3\2\2\2\u02e9\u02dd\3\2\2\2\u02e9\u02e1\3\2\2\2\u02e9\u02e4"+
		"\3\2\2\2\u02ea-\3\2\2\2\u02eb\u02ec\7:\2\2\u02ec\u02ed\t\2\2\2\u02ed/"+
		"\3\2\2\2\u02ee\u02ef\7\5\2\2\u02ef\u02f0\5\u00ceh\2\u02f0\u02f2\5\64\33"+
		"\2\u02f1\u02f3\5\u008aF\2\u02f2\u02f1\3\2\2\2\u02f2\u02f3\3\2\2\2\u02f3"+
		"\u0304\3\2\2\2\u02f4\u02f5\5\u00ceh\2\u02f5\u02f7\5\64\33\2\u02f6\u02f8"+
		"\5\u008aF\2\u02f7\u02f6\3\2\2\2\u02f7\u02f8\3\2\2\2\u02f8\u02f9\3\2\2"+
		"\2\u02f9\u02fa\b\31\1\2\u02fa\u0304\3\2\2\2\u02fb\u02fc\5\62\32\2\u02fc"+
		"\u02fd\5\u00ceh\2\u02fd\u02ff\5\64\33\2\u02fe\u0300\5\u008aF\2\u02ff\u02fe"+
		"\3\2\2\2\u02ff\u0300\3\2\2\2\u0300\u0301\3\2\2\2\u0301\u0302\b\31\1\2"+
		"\u0302\u0304\3\2\2\2\u0303\u02ee\3\2\2\2\u0303\u02f4\3\2\2\2\u0303\u02fb"+
		"\3\2\2\2\u0304\61\3\2\2\2\u0305\u0307\7\5\2\2\u0306\u0308\7\5\2\2\u0307"+
		"\u0306\3\2\2\2\u0308\u0309\3\2\2\2\u0309\u0307\3\2\2\2\u0309\u030a\3\2"+
		"\2\2\u030a\63\3\2\2\2\u030b\u030c\t\3\2\2\u030c\u030d\t\b\2\2\u030d\u030f"+
		"\5\u00ceh\2\u030e\u030b\3\2\2\2\u030f\u0312\3\2\2\2\u0310\u030e\3\2\2"+
		"\2\u0310\u0311\3\2\2\2\u0311\65\3\2\2\2\u0312\u0310\3\2\2\2\u0313\u0314"+
		"\7\5\2\2\u0314\u031d\5@!\2\u0315\u0316\5@!\2\u0316\u0317\b\34\1\2\u0317"+
		"\u031d\3\2\2\2\u0318\u0319\5\62\32\2\u0319\u031a\5@!\2\u031a\u031b\b\34"+
		"\1\2\u031b\u031d\3\2\2\2\u031c\u0313\3\2\2\2\u031c\u0315\3\2\2\2\u031c"+
		"\u0318\3\2\2\2\u031d\67\3\2\2\2\u031e\u031f\7\5\2\2\u031f\u0328\5B\"\2"+
		"\u0320\u0321\5B\"\2\u0321\u0322\b\35\1\2\u0322\u0328\3\2\2\2\u0323\u0324"+
		"\5\62\32\2\u0324\u0325\5B\"\2\u0325\u0326\b\35\1\2\u0326\u0328\3\2\2\2"+
		"\u0327\u031e\3\2\2\2\u0327\u0320\3\2\2\2\u0327\u0323\3\2\2\2\u03289\3"+
		"\2\2\2\u0329\u032a\7\5\2\2\u032a\u0333\5F$\2\u032b\u032c\5F$\2\u032c\u032d"+
		"\b\36\1\2\u032d\u0333\3\2\2\2\u032e\u032f\5\62\32\2\u032f\u0330\5F$\2"+
		"\u0330\u0331\b\36\1\2\u0331\u0333\3\2\2\2\u0332\u0329\3\2\2\2\u0332\u032b"+
		"\3\2\2\2\u0332\u032e\3\2\2\2\u0333;\3\2\2\2\u0334\u0335\7\5\2\2\u0335"+
		"\u033e\5J&\2\u0336\u0337\5J&\2\u0337\u0338\b\37\1\2\u0338\u033e\3\2\2"+
		"\2\u0339\u033a\5\62\32\2\u033a\u033b\5J&\2\u033b\u033c\b\37\1\2\u033c"+
		"\u033e\3\2\2\2\u033d\u0334\3\2\2\2\u033d\u0336\3\2\2\2\u033d\u0339\3\2"+
		"\2\2\u033e=\3\2\2\2\u033f\u0341\7\5\2\2\u0340\u033f\3\2\2\2\u0341\u0342"+
		"\3\2\2\2\u0342\u0340\3\2\2\2\u0342\u0343\3\2\2\2\u0343?\3\2\2\2\u0344"+
		"\u0345\t\f\2\2\u0345\u0346\t\r\2\2\u0346\u0347\t\n\2\2\u0347\u0349\t\t"+
		"\2\2\u0348\u034a\5\u00bc_\2\u0349\u0348\3\2\2\2\u0349\u034a\3\2\2\2\u034a"+
		"\u0354\3\2\2\2\u034b\u034c\t\f\2\2\u034c\u034d\t\r\2\2\u034d\u034e\t\n"+
		"\2\2\u034e\u034f\t\t\2\2\u034f\u0350\5> \2\u0350\u0351\5\u00bc_\2\u0351"+
		"\u0352\b!\1\2\u0352\u0354\3\2\2\2\u0353\u0344\3\2\2\2\u0353\u034b\3\2"+
		"\2\2\u0354A\3\2\2\2\u0355\u0357\5D#\2\u0356\u0358\5\u00bc_\2\u0357\u0356"+
		"\3\2\2\2\u0357\u0358\3\2\2\2\u0358\u035f\3\2\2\2\u0359\u035a\5D#\2\u035a"+
		"\u035b\5> \2\u035b\u035c\5\u00bc_\2\u035c\u035d\b\"\1\2\u035d\u035f\3"+
		"\2\2\2\u035e\u0355\3\2\2\2\u035e\u0359\3\2\2\2\u035fC\3\2\2\2\u0360\u0361"+
		"\t\16\2\2\u0361\u0363\t\5\2\2\u0362\u0364\5\u00acW\2\u0363\u0362\3\2\2"+
		"\2\u0363\u0364\3\2\2\2\u0364E\3\2\2\2\u0365\u0367\5H%\2\u0366\u0368\5"+
		"\u00bc_\2\u0367\u0366\3\2\2\2\u0367\u0368\3\2\2\2\u0368\u036f\3\2\2\2"+
		"\u0369\u036a\5H%\2\u036a\u036b\5> \2\u036b\u036c\5\u00bc_\2\u036c\u036d"+
		"\b$\1\2\u036d\u036f\3\2\2\2\u036e\u0365\3\2\2\2\u036e\u0369\3\2\2\2\u036f"+
		"G\3\2\2\2\u0370\u0371\t\16\2\2\u0371\u0372\t\r\2\2\u0372\u0374\t\5\2\2"+
		"\u0373\u0375\5\u00acW\2\u0374\u0373\3\2\2\2\u0374\u0375\3\2\2\2\u0375"+
		"I\3\2\2\2\u0376\u037a\5L\'\2\u0377\u0378\t\3\2\2\u0378\u0379\t\b\2\2\u0379"+
		"\u037b\5L\'\2\u037a\u0377\3\2\2\2\u037b\u037c\3\2\2\2\u037c\u037a\3\2"+
		"\2\2\u037c\u037d\3\2\2\2\u037d\u0380\3\2\2\2\u037e\u0380\5L\'\2\u037f"+
		"\u0376\3\2\2\2\u037f\u037e\3\2\2\2\u0380K\3\2\2\2\u0381\u0383\5\u00c8"+
		"e\2\u0382\u0381\3\2\2\2\u0382\u0383\3\2\2\2\u0383\u0384\3\2\2\2\u0384"+
		"\u0386\t\b\2\2\u0385\u0387\5\u00d0i\2\u0386\u0385\3\2\2\2\u0386\u0387"+
		"\3\2\2\2\u0387\u0388\3\2\2\2\u0388\u038a\7\5\2\2\u0389\u038b\7=\2\2\u038a"+
		"\u0389\3\2\2\2\u038a\u038b\3\2\2\2\u038b\u038d\3\2\2\2\u038c\u038e\5\u00a4"+
		"S\2\u038d\u038c\3\2\2\2\u038d\u038e\3\2\2\2\u038e\u038f\3\2\2\2\u038f"+
		"\u0390\t\r\2\2\u0390\u0391\t\t\2\2\u0391\u0392\t\f\2\2\u0392\u0394\t\2"+
		"\2\2\u0393\u0395\5\u00d0i\2\u0394\u0393\3\2\2\2\u0394\u0395\3\2\2\2\u0395"+
		"\u039a\3\2\2\2\u0396\u039a\5R*\2\u0397\u039a\5P)\2\u0398\u039a\5N(\2\u0399"+
		"\u0382\3\2\2\2\u0399\u0396\3\2\2\2\u0399\u0397\3\2\2\2\u0399\u0398\3\2"+
		"\2\2\u039aM\3\2\2\2\u039b\u039c\5\u00c8e\2\u039c\u039f\5\u00a6T\2\u039d"+
		"\u03a0\5\u008aF\2\u039e\u03a0\5\u00aaV\2\u039f\u039d\3\2\2\2\u039f\u039e"+
		"\3\2\2\2\u039f\u03a0\3\2\2\2\u03a0\u03a1\3\2\2\2\u03a1\u03a2\b(\1\2\u03a2"+
		"\u03bc\3\2\2\2\u03a3\u03a4\5\u00c6d\2\u03a4\u03a7\5\u00aeX\2\u03a5\u03a8"+
		"\5\u008aF\2\u03a6\u03a8\5\u00aaV\2\u03a7\u03a5\3\2\2\2\u03a7\u03a6\3\2"+
		"\2\2\u03a7\u03a8\3\2\2\2\u03a8\u03a9\3\2\2\2\u03a9\u03aa\b(\1\2\u03aa"+
		"\u03bc\3\2\2\2\u03ab\u03ad\5\u00d4k\2\u03ac\u03ae\5\u00a6T\2\u03ad\u03ac"+
		"\3\2\2\2\u03ad\u03ae\3\2\2\2\u03ae\u03b1\3\2\2\2\u03af\u03b2\5\u008aF"+
		"\2\u03b0\u03b2\5\u00aaV\2\u03b1\u03af\3\2\2\2\u03b1\u03b0\3\2\2\2\u03b1"+
		"\u03b2\3\2\2\2\u03b2\u03b3\3\2\2\2\u03b3\u03b4\b(\1\2\u03b4\u03bc\3\2"+
		"\2\2\u03b5\u03b8\5\u00c8e\2\u03b6\u03b8\5\u00c6d\2\u03b7\u03b5\3\2\2\2"+
		"\u03b7\u03b6\3\2\2\2\u03b8\u03b9\3\2\2\2\u03b9\u03ba\b(\1\2\u03ba\u03bc"+
		"\3\2\2\2\u03bb\u039b\3\2\2\2\u03bb\u03a3\3\2\2\2\u03bb\u03ab\3\2\2\2\u03bb"+
		"\u03b7\3\2\2\2\u03bcO\3\2\2\2\u03bd\u03bf\5\u00c8e\2\u03be\u03bd\3\2\2"+
		"\2\u03be\u03bf\3\2\2\2\u03bf\u03c0\3\2\2\2\u03c0\u03c2\t\b\2\2\u03c1\u03c3"+
		"\5\u00d0i\2\u03c2\u03c1\3\2\2\2\u03c2\u03c3\3\2\2\2\u03c3\u03e7\3\2\2"+
		"\2\u03c4\u03c5\5\u00c8e\2\u03c5\u03c6\t\t\2\2\u03c6\u03c7\t\6\2\2\u03c7"+
		"\u03c8\t\b\2\2\u03c8\u03ca\5\u00acW\2\u03c9\u03cb\5\u00d0i\2\u03ca\u03c9"+
		"\3\2\2\2\u03ca\u03cb\3\2\2\2\u03cb\u03e7\3\2\2\2\u03cc\u03ce\5\u00c8e"+
		"\2\u03cd\u03cf\5\u00b0Y\2\u03ce\u03cd\3\2\2\2\u03ce\u03cf\3\2\2\2\u03cf"+
		"\u03d0\3\2\2\2\u03d0\u03d1\t\t\2\2\u03d1\u03d2\t\6\2\2\u03d2\u03d4\t\b"+
		"\2\2\u03d3\u03d5\5\u00d0i\2\u03d4\u03d3\3\2\2\2\u03d4\u03d5\3\2\2\2\u03d5"+
		"\u03e7\3\2\2\2\u03d6\u03d8\7=\2\2\u03d7\u03d6\3\2\2\2\u03d7\u03d8\3\2"+
		"\2\2\u03d8\u03da\3\2\2\2\u03d9\u03db\5\u00a4S\2\u03da\u03d9\3\2\2\2\u03da"+
		"\u03db\3\2\2\2\u03db\u03dc\3\2\2\2\u03dc\u03dd\t\r\2\2\u03dd\u03de\t\t"+
		"\2\2\u03de\u03df\t\f\2\2\u03df\u03e1\t\2\2\2\u03e0\u03e2\5\u00d0i\2\u03e1"+
		"\u03e0\3\2\2\2\u03e1\u03e2\3\2\2\2\u03e2\u03e7\3\2\2\2\u03e3\u03e4\t\f"+
		"\2\2\u03e4\u03e5\t\2\2\2\u03e5\u03e7\t\4\2\2\u03e6\u03be\3\2\2\2\u03e6"+
		"\u03c4\3\2\2\2\u03e6\u03cc\3\2\2\2\u03e6\u03d7\3\2\2\2\u03e6\u03e3\3\2"+
		"\2\2\u03e7Q\3\2\2\2\u03e8\u03ea\5\u00c4c\2\u03e9\u03e8\3\2\2\2\u03e9\u03ea"+
		"\3\2\2\2\u03ea\u03ec\3\2\2\2\u03eb\u03ed\5\u00ba^\2\u03ec\u03eb\3\2\2"+
		"\2\u03ec\u03ed\3\2\2\2\u03ed\u03ee\3\2\2\2\u03ee\u03ef\5\u00a0Q\2\u03ef"+
		"\u03f1\5X-\2\u03f0\u03f2\5b\62\2\u03f1\u03f0\3\2\2\2\u03f1\u03f2\3\2\2"+
		"\2\u03f2\u03f6\3\2\2\2\u03f3\u03f5\5T+\2\u03f4\u03f3\3\2\2\2\u03f5\u03f8"+
		"\3\2\2\2\u03f6\u03f4\3\2\2\2\u03f6\u03f7\3\2\2\2\u03f7\u03fa\3\2\2\2\u03f8"+
		"\u03f6\3\2\2\2\u03f9\u03fb\5\u00d0i\2\u03fa\u03f9\3\2\2\2\u03fa\u03fb"+
		"\3\2\2\2\u03fb\u03fc\3\2\2\2\u03fc\u03fd\b*\1\2\u03fd\u044c\3\2\2\2\u03fe"+
		"\u0400\5\u00c4c\2\u03ff\u03fe\3\2\2\2\u03ff\u0400\3\2\2\2\u0400\u0402"+
		"\3\2\2\2\u0401\u0403\5\u00ba^\2\u0402\u0401\3\2\2\2\u0402\u0403\3\2\2"+
		"\2\u0403\u0404\3\2\2\2\u0404\u0405\5\u00a8U\2\u0405\u0407\5j\66\2\u0406"+
		"\u0408\5p9\2\u0407\u0406\3\2\2\2\u0407\u0408\3\2\2\2\u0408\u040a\3\2\2"+
		"\2\u0409\u040b\5\u00d0i\2\u040a\u0409\3\2\2\2\u040a\u040b\3\2\2\2\u040b"+
		"\u040c\3\2\2\2\u040c\u040d\b*\1\2\u040d\u044c\3\2\2\2\u040e\u0410\5\u00c4"+
		"c\2\u040f\u040e\3\2\2\2\u040f\u0410\3\2\2\2\u0410\u0412\3\2\2\2\u0411"+
		"\u0413\5\u00ba^\2\u0412\u0411\3\2\2\2\u0412\u0413\3\2\2\2\u0413\u0414"+
		"\3\2\2\2\u0414\u0415\5|?\2\u0415\u0417\5~@\2\u0416\u0418\5\u00d0i\2\u0417"+
		"\u0416\3\2\2\2\u0417\u0418\3\2\2\2\u0418\u0419\3\2\2\2\u0419\u041a\b*"+
		"\1\2\u041a\u044c\3\2\2\2\u041b\u041d\5\u00c4c\2\u041c\u041b\3\2\2\2\u041c"+
		"\u041d\3\2\2\2\u041d\u041f\3\2\2\2\u041e\u0420\5\u00ba^\2\u041f\u041e"+
		"\3\2\2\2\u041f\u0420\3\2\2\2\u0420\u0421\3\2\2\2\u0421\u0423\5\u00a0Q"+
		"\2\u0422\u0424\7\5\2\2\u0423\u0422\3\2\2\2\u0424\u0425\3\2\2\2\u0425\u0423"+
		"\3\2\2\2\u0425\u0426\3\2\2\2\u0426\u0427\3\2\2\2\u0427\u0429\5X-\2\u0428"+
		"\u042a\5b\62\2\u0429\u0428\3\2\2\2\u0429\u042a\3\2\2\2\u042a\u042e\3\2"+
		"\2\2\u042b\u042d\5T+\2\u042c\u042b\3\2\2\2\u042d\u0430\3\2\2\2\u042e\u042c"+
		"\3\2\2\2\u042e\u042f\3\2\2\2\u042f\u0432\3\2\2\2\u0430\u042e\3\2\2\2\u0431"+
		"\u0433\5\u00d0i\2\u0432\u0431\3\2\2\2\u0432\u0433\3\2\2\2\u0433\u0434"+
		"\3\2\2\2\u0434\u0435\b*\1\2\u0435\u044c\3\2\2\2\u0436\u0438\5\u00c4c\2"+
		"\u0437\u0436\3\2\2\2\u0437\u0438\3\2\2\2\u0438\u043a\3\2\2\2\u0439\u043b"+
		"\5\u00ba^\2\u043a\u0439\3\2\2\2\u043a\u043b\3\2\2\2\u043b\u043c\3\2\2"+
		"\2\u043c\u043e\5\u00a8U\2\u043d\u043f\7\5\2\2\u043e\u043d\3\2\2\2\u043f"+
		"\u0440\3\2\2\2\u0440\u043e\3\2\2\2\u0440\u0441\3\2\2\2\u0441\u0442\3\2"+
		"\2\2\u0442\u0444\5j\66\2\u0443\u0445\5p9\2\u0444\u0443\3\2\2\2\u0444\u0445"+
		"\3\2\2\2\u0445\u0447\3\2\2\2\u0446\u0448\5\u00d0i\2\u0447\u0446\3\2\2"+
		"\2\u0447\u0448\3\2\2\2\u0448\u0449\3\2\2\2\u0449\u044a\b*\1\2\u044a\u044c"+
		"\3\2\2\2\u044b\u03e9\3\2\2\2\u044b\u03ff\3\2\2\2\u044b\u040f\3\2\2\2\u044b"+
		"\u041c\3\2\2\2\u044b\u0437\3\2\2\2\u044cS\3\2\2\2\u044d\u0457\5V,\2\u044e"+
		"\u0452\5V,\2\u044f\u0450\t\3\2\2\u0450\u0451\t\b\2\2\u0451\u0453\5V,\2"+
		"\u0452\u044f\3\2\2\2\u0453\u0454\3\2\2\2\u0454\u0452\3\2\2\2\u0454\u0455"+
		"\3\2\2\2\u0455\u0457\3\2\2\2\u0456\u044d\3\2\2\2\u0456\u044e\3\2\2\2\u0457"+
		"U\3\2\2\2\u0458\u045a\7=\2\2\u0459\u0458\3\2\2\2\u0459\u045a\3\2\2\2\u045a"+
		"\u045b\3\2\2\2\u045b\u045c\5\u00caf\2\u045c\u045e\5x=\2\u045d\u045f\5"+
		"z>\2\u045e\u045d\3\2\2\2\u045e\u045f\3\2\2\2\u045f\u0470\3\2\2\2\u0460"+
		"\u0462\7=\2\2\u0461\u0460\3\2\2\2\u0461\u0462\3\2\2\2\u0462\u0463\3\2"+
		"\2\2\u0463\u0465\5\u00caf\2\u0464\u0466\7\5\2\2\u0465\u0464\3\2\2\2\u0466"+
		"\u0467\3\2\2\2\u0467\u0465\3\2\2\2\u0467\u0468\3\2\2\2\u0468\u0469\3\2"+
		"\2\2\u0469\u046b\5x=\2\u046a\u046c\5z>\2\u046b\u046a\3\2\2\2\u046b\u046c"+
		"\3\2\2\2\u046c\u046d\3\2\2\2\u046d\u046e\b,\1\2\u046e\u0470\3\2\2\2\u046f"+
		"\u0459\3\2\2\2\u046f\u0461\3\2\2\2\u0470W\3\2\2\2\u0471\u0472\7\"\2\2"+
		"\u0472\u0473\5Z.\2\u0473\u0474\7#\2\2\u0474\u0486\3\2\2\2\u0475\u0476"+
		"\5Z.\2\u0476\u0477\7#\2\2\u0477\u0478\b-\1\2\u0478\u0486\3\2\2\2\u0479"+
		"\u047a\7\"\2\2\u047a\u047b\5Z.\2\u047b\u047c\b-\1\2\u047c\u0486\3\2\2"+
		"\2\u047d\u047e\5Z.\2\u047e\u047f\b-\1\2\u047f\u0486\3\2\2\2\u0480\u0481"+
		"\5^\60\2\u0481\u0482\5Z.\2\u0482\u0483\5`\61\2\u0483\u0484\b-\1\2\u0484"+
		"\u0486\3\2\2\2\u0485\u0471\3\2\2\2\u0485\u0475\3\2\2\2\u0485\u0479\3\2"+
		"\2\2\u0485\u047d\3\2\2\2\u0485\u0480\3\2\2\2\u0486Y\3\2\2\2\u0487\u048c"+
		"\5\u009eP\2\u0488\u0489\7\31\2\2\u0489\u048b\5\u009eP\2\u048a\u0488\3"+
		"\2\2\2\u048b\u048e\3\2\2\2\u048c\u048a\3\2\2\2\u048c\u048d\3\2\2\2\u048d"+
		"\u0493\3\2\2\2\u048e\u048c\3\2\2\2\u048f\u0490\5\\/\2\u0490\u0491\b.\1"+
		"\2\u0491\u0493\3\2\2\2\u0492\u0487\3\2\2\2\u0492\u048f\3\2\2\2\u0493["+
		"\3\2\2\2\u0494\u0497\5\u009eP\2\u0495\u0496\t\17\2\2\u0496\u0498\5\u009e"+
		"P\2\u0497\u0495\3\2\2\2\u0498\u0499\3\2\2\2\u0499\u0497\3\2\2\2\u0499"+
		"\u049a\3\2\2\2\u049a]\3\2\2\2\u049b\u049d\t\20\2\2\u049c\u049b\3\2\2\2"+
		"\u049d\u04a0\3\2\2\2\u049e\u049c\3\2\2\2\u049e\u049f\3\2\2\2\u049f_\3"+
		"\2\2\2\u04a0\u049e\3\2\2\2\u04a1\u04a3\t\21\2\2\u04a2\u04a1\3\2\2\2\u04a3"+
		"\u04a6\3\2\2\2\u04a4\u04a2\3\2\2\2\u04a4\u04a5\3\2\2\2\u04a5a\3\2\2\2"+
		"\u04a6\u04a4\3\2\2\2\u04a7\u04a8\7\"\2\2\u04a8\u04a9\5d\63\2\u04a9\u04aa"+
		"\7#\2\2\u04aa\u04bc\3\2\2\2\u04ab\u04ac\5d\63\2\u04ac\u04ad\7#\2\2\u04ad"+
		"\u04ae\b\62\1\2\u04ae\u04bc\3\2\2\2\u04af\u04b0\7\"\2\2\u04b0\u04b1\5"+
		"d\63\2\u04b1\u04b2\b\62\1\2\u04b2\u04bc\3\2\2\2\u04b3\u04b4\5d\63\2\u04b4"+
		"\u04b5\b\62\1\2\u04b5\u04bc\3\2\2\2\u04b6\u04b7\5^\60\2\u04b7\u04b8\5"+
		"d\63\2\u04b8\u04b9\5`\61\2\u04b9\u04ba\b\62\1\2\u04ba\u04bc\3\2\2\2\u04bb"+
		"\u04a7\3\2\2\2\u04bb\u04ab\3\2\2\2\u04bb\u04af\3\2\2\2\u04bb\u04b3\3\2"+
		"\2\2\u04bb\u04b6\3\2\2\2\u04bcc\3\2\2\2\u04bd\u04c2\5h\65\2\u04be\u04bf"+
		"\7\31\2\2\u04bf\u04c1\5h\65\2\u04c0\u04be\3\2\2\2\u04c1\u04c4\3\2\2\2"+
		"\u04c2\u04c0\3\2\2\2\u04c2\u04c3\3\2\2\2\u04c3\u04c9\3\2\2\2\u04c4\u04c2"+
		"\3\2\2\2\u04c5\u04c6\5f\64\2\u04c6\u04c7\b\63\1\2\u04c7\u04c9\3\2\2\2"+
		"\u04c8\u04bd\3\2\2\2\u04c8\u04c5\3\2\2\2\u04c9e\3\2\2\2\u04ca\u04cd\5"+
		"h\65\2\u04cb\u04cc\t\17\2\2\u04cc\u04ce\5h\65\2\u04cd\u04cb\3\2\2\2\u04ce"+
		"\u04cf\3\2\2\2\u04cf\u04cd\3\2\2\2\u04cf\u04d0\3\2\2\2\u04d0g\3\2\2\2"+
		"\u04d1\u04db\5\u009cO\2\u04d2\u04d6\5\u009cO\2\u04d3\u04d4\t\3\2\2\u04d4"+
		"\u04d5\t\b\2\2\u04d5\u04d7\5\u009cO\2\u04d6\u04d3\3\2\2\2\u04d7\u04d8"+
		"\3\2\2\2\u04d8\u04d6\3\2\2\2\u04d8\u04d9\3\2\2\2\u04d9\u04db\3\2\2\2\u04da"+
		"\u04d1\3\2\2\2\u04da\u04d2\3\2\2\2\u04dbi\3\2\2\2\u04dc\u04dd\7\"\2\2"+
		"\u04dd\u04de\5l\67\2\u04de\u04df\7#\2\2\u04df\u04f1\3\2\2\2\u04e0\u04e1"+
		"\5l\67\2\u04e1\u04e2\7#\2\2\u04e2\u04e3\b\66\1\2\u04e3\u04f1\3\2\2\2\u04e4"+
		"\u04e5\7\"\2\2\u04e5\u04e6\5l\67\2\u04e6\u04e7\b\66\1\2\u04e7\u04f1\3"+
		"\2\2\2\u04e8\u04e9\5l\67\2\u04e9\u04ea\b\66\1\2\u04ea\u04f1\3\2\2\2\u04eb"+
		"\u04ec\5^\60\2\u04ec\u04ed\5l\67\2\u04ed\u04ee\5`\61\2\u04ee\u04ef\b\66"+
		"\1\2\u04ef\u04f1\3\2\2\2\u04f0\u04dc\3\2\2\2\u04f0\u04e0\3\2\2\2\u04f0"+
		"\u04e4\3\2\2\2\u04f0\u04e8\3\2\2\2\u04f0\u04eb\3\2\2\2\u04f1k\3\2\2\2"+
		"\u04f2\u04f7\5\u0096L\2\u04f3\u04f4\7\31\2\2\u04f4\u04f6\5\u0096L\2\u04f5"+
		"\u04f3\3\2\2\2\u04f6\u04f9\3\2\2\2\u04f7\u04f5\3\2\2\2\u04f7\u04f8\3\2"+
		"\2\2\u04f8\u04fe\3\2\2\2\u04f9\u04f7\3\2\2\2\u04fa\u04fb\5n8\2\u04fb\u04fc"+
		"\b\67\1\2\u04fc\u04fe\3\2\2\2\u04fd\u04f2\3\2\2\2\u04fd\u04fa\3\2\2\2"+
		"\u04fem\3\2\2\2\u04ff\u0502\5\u0096L\2\u0500\u0501\t\17\2\2\u0501\u0503"+
		"\5\u0096L\2\u0502\u0500\3\2\2\2\u0503\u0504\3\2\2\2\u0504\u0502\3\2\2"+
		"\2\u0504\u0505\3\2\2\2\u0505o\3\2\2\2\u0506\u0507\7\"\2\2\u0507\u0508"+
		"\5r:\2\u0508\u0509\7#\2\2\u0509\u051b\3\2\2\2\u050a\u050b\5r:\2\u050b"+
		"\u050c\7#\2\2\u050c\u050d\b9\1\2\u050d\u051b\3\2\2\2\u050e\u050f\7\"\2"+
		"\2\u050f\u0510\5r:\2\u0510\u0511\b9\1\2\u0511\u051b\3\2\2\2\u0512\u0513"+
		"\5r:\2\u0513\u0514\b9\1\2\u0514\u051b\3\2\2\2\u0515\u0516\5^\60\2\u0516"+
		"\u0517\5r:\2\u0517\u0518\5`\61\2\u0518\u0519\b9\1\2\u0519\u051b\3\2\2"+
		"\2\u051a\u0506\3\2\2\2\u051a\u050a\3\2\2\2\u051a\u050e\3\2\2\2\u051a\u0512"+
		"\3\2\2\2\u051a\u0515\3\2\2\2\u051bq\3\2\2\2\u051c\u0521\5v<\2\u051d\u051e"+
		"\7\31\2\2\u051e\u0520\5v<\2\u051f\u051d\3\2\2\2\u0520\u0523\3\2\2\2\u0521"+
		"\u051f\3\2\2\2\u0521\u0522\3\2\2\2\u0522\u0528\3\2\2\2\u0523\u0521\3\2"+
		"\2\2\u0524\u0525\5t;\2\u0525\u0526\b:\1\2\u0526\u0528\3\2\2\2\u0527\u051c"+
		"\3\2\2\2\u0527\u0524\3\2\2\2\u0528s\3\2\2\2\u0529\u052c\5v<\2\u052a\u052b"+
		"\t\17\2\2\u052b\u052d\5v<\2\u052c\u052a\3\2\2\2\u052d\u052e\3\2\2\2\u052e"+
		"\u052c\3\2\2\2\u052e\u052f\3\2\2\2\u052fu\3\2\2\2\u0530\u053a\5\u0088"+
		"E\2\u0531\u0535\5\u0088E\2\u0532\u0533\t\3\2\2\u0533\u0534\t\b\2\2\u0534"+
		"\u0536\5\u0088E\2\u0535\u0532\3\2\2\2\u0536\u0537\3\2\2\2\u0537\u0535"+
		"\3\2\2\2\u0537\u0538\3\2\2\2\u0538\u053a\3\2\2\2\u0539\u0530\3\2\2\2\u0539"+
		"\u0531\3\2\2\2\u053aw\3\2\2\2\u053b\u053c\7\"\2\2\u053c\u053d\5l\67\2"+
		"\u053d\u053e\7#\2\2\u053e\u0550\3\2\2\2\u053f\u0540\5l\67\2\u0540\u0541"+
		"\7#\2\2\u0541\u0542\b=\1\2\u0542\u0550\3\2\2\2\u0543\u0544\7\"\2\2\u0544"+
		"\u0545\5l\67\2\u0545\u0546\b=\1\2\u0546\u0550\3\2\2\2\u0547\u0548\5l\67"+
		"\2\u0548\u0549\b=\1\2\u0549\u0550\3\2\2\2\u054a\u054b\5^\60\2\u054b\u054c"+
		"\5l\67\2\u054c\u054d\5`\61\2\u054d\u054e\b=\1\2\u054e\u0550\3\2\2\2\u054f"+
		"\u053b\3\2\2\2\u054f\u053f\3\2\2\2\u054f\u0543\3\2\2\2\u054f\u0547\3\2"+
		"\2\2\u054f\u054a\3\2\2\2\u0550y\3\2\2\2\u0551\u0552\7\"\2\2\u0552\u0553"+
		"\5r:\2\u0553\u0554\7#\2\2\u0554\u0566\3\2\2\2\u0555\u0556\5r:\2\u0556"+
		"\u0557\7#\2\2\u0557\u0558\b>\1\2\u0558\u0566\3\2\2\2\u0559\u055a\7\"\2"+
		"\2\u055a\u055b\5r:\2\u055b\u055c\b>\1\2\u055c\u0566\3\2\2\2\u055d\u055e"+
		"\5r:\2\u055e\u055f\b>\1\2\u055f\u0566\3\2\2\2\u0560\u0561\5^\60\2\u0561"+
		"\u0562\5r:\2\u0562\u0563\5`\61\2\u0563\u0564\b>\1\2\u0564\u0566\3\2\2"+
		"\2\u0565\u0551\3\2\2\2\u0565\u0555\3\2\2\2\u0565\u0559\3\2\2\2\u0565\u055d"+
		"\3\2\2\2\u0565\u0560\3\2\2\2\u0566{\3\2\2\2\u0567\u0568\t\6\2\2\u0568"+
		"\u0569\t\r\2\2\u0569\u058a\t\r\2\2\u056a\u056b\t\r\2\2\u056b\u056c\t\n"+
		"\2\2\u056c\u058a\t\5\2\2\u056d\u056e\t\22\2\2\u056e\u056f\t\16\2\2\u056f"+
		"\u0571\t\23\2\2\u0570\u056d\3\2\2\2\u0570\u0571\3\2\2\2\u0571\u0572\3"+
		"\2\2\2\u0572\u0573\t\f\2\2\u0573\u0574\t\r\2\2\u0574\u0575\t\f\2\2\u0575"+
		"\u058a\t\4\2\2\u0576\u0577\t\r\2\2\u0577\u0578\t\23\2\2\u0578\u058a\t"+
		"\22\2\2\u0579\u057a\t\24\2\2\u057a\u057b\t\b\2\2\u057b\u058a\t\22\2\2"+
		"\u057c\u057d\t\25\2\2\u057d\u057e\t\r\2\2\u057e\u058a\t\22\2\2\u057f\u0580"+
		"\t\26\2\2\u0580\u0581\t\16\2\2\u0581\u058a\t\b\2\2\u0582\u0583\t\f\2\2"+
		"\u0583\u0584\t\2\2\2\u0584\u058a\t\16\2\2\u0585\u0586\t\f\2\2\u0586\u0587"+
		"\t\2\2\2\u0587\u058a\t\27\2\2\u0588\u058a\t\f\2\2\u0589\u0567\3\2\2\2"+
		"\u0589\u056a\3\2\2\2\u0589\u0570\3\2\2\2\u0589\u0576\3\2\2\2\u0589\u0579"+
		"\3\2\2\2\u0589\u057c\3\2\2\2\u0589\u057f\3\2\2\2\u0589\u0582\3\2\2\2\u0589"+
		"\u0585\3\2\2\2\u0589\u0588\3\2\2\2\u058a}\3\2\2\2\u058b\u058c\7\"\2\2"+
		"\u058c\u058d\5\u0080A\2\u058d\u058e\7#\2\2\u058e\u05a0\3\2\2\2\u058f\u0590"+
		"\5\u0080A\2\u0590\u0591\7#\2\2\u0591\u0592\b@\1\2\u0592\u05a0\3\2\2\2"+
		"\u0593\u0594\7\"\2\2\u0594\u0595\5\u0080A\2\u0595\u0596\b@\1\2\u0596\u05a0"+
		"\3\2\2\2\u0597\u0598\5\u0080A\2\u0598\u0599\b@\1\2\u0599\u05a0\3\2\2\2"+
		"\u059a\u059b\5^\60\2\u059b\u059c\5\u0080A\2\u059c\u059d\5`\61\2\u059d"+
		"\u059e\b@\1\2\u059e\u05a0\3\2\2\2\u059f\u058b\3\2\2\2\u059f\u058f\3\2"+
		"\2\2\u059f\u0593\3\2\2\2\u059f\u0597\3\2\2\2\u059f\u059a\3\2\2\2\u05a0"+
		"\177\3\2\2\2\u05a1\u05a3\5\u0096L\2\u05a2\u05a4\7=\2\2\u05a3\u05a2\3\2"+
		"\2\2\u05a3\u05a4\3\2\2\2\u05a4\u05a5\3\2\2\2\u05a5\u05a6\5\u0082B\2\u05a6"+
		"\u0081\3\2\2\2\u05a7\u05a8\t\30\2\2\u05a8\u0083\3\2\2\2\u05a9\u05ab\7"+
		"=\2\2\u05aa\u05a9\3\2\2\2\u05aa\u05ab\3\2\2\2\u05ab\u05ac\3\2\2\2\u05ac"+
		"\u05af\5\u0082B\2\u05ad\u05b0\5\u00a2R\2\u05ae\u05b0\7=\2\2\u05af\u05ad"+
		"\3\2\2\2\u05af\u05ae\3\2\2\2\u05b0\u05b1\3\2\2\2\u05b1\u05af\3\2\2\2\u05b1"+
		"\u05b2\3\2\2\2\u05b2\u05bf\3\2\2\2\u05b3\u05b6\7=\2\2\u05b4\u05b7\5\u00a2"+
		"R\2\u05b5\u05b7\7=\2\2\u05b6\u05b4\3\2\2\2\u05b6\u05b5\3\2\2\2\u05b7\u05b8"+
		"\3\2\2\2\u05b8\u05b6\3\2\2\2\u05b8\u05b9\3\2\2\2\u05b9\u05bf\3\2\2\2\u05ba"+
		"\u05bf\7=\2\2\u05bb\u05bc\5\u0082B\2\u05bc\u05bd\5\u00c0a\2\u05bd\u05bf"+
		"\3\2\2\2\u05be\u05aa\3\2\2\2\u05be\u05b3\3\2\2\2\u05be\u05ba\3\2\2\2\u05be"+
		"\u05bb\3\2\2\2\u05bf\u0085\3\2\2\2\u05c0\u05c2\5\u0084C\2\u05c1\u05c3"+
		"\5\u00d2j\2\u05c2\u05c1\3\2\2\2\u05c2\u05c3\3\2\2\2\u05c3\u0087\3\2\2"+
		"\2\u05c4\u05c6\5\u0086D\2\u05c5\u05c4\3\2\2\2\u05c6\u05c7\3\2\2\2\u05c7"+
		"\u05c5\3\2\2\2\u05c7\u05c8\3\2\2\2\u05c8\u0089\3\2\2\2\u05c9\u05ca\t\4"+
		"\2\2\u05ca\u008b\3\2\2\2\u05cb\u05cc\7$\2\2\u05cc\u05cd\5\u008eH\2\u05cd"+
		"\u05ce\7&\2\2\u05ce\u05f0\3\2\2\2\u05cf\u05d0\5\u008eH\2\u05d0\u05d1\7"+
		"&\2\2\u05d1\u05d2\bG\1\2\u05d2\u05f0\3\2\2\2\u05d3\u05d4\7$\2\2\u05d4"+
		"\u05d5\5\u008eH\2\u05d5\u05d6\bG\1\2\u05d6\u05f0\3\2\2\2\u05d7\u05d8\5"+
		"\u0090I\2\u05d8\u05d9\5\u008eH\2\u05d9\u05da\5`\61\2\u05da\u05db\bG\1"+
		"\2\u05db\u05f0\3\2\2\2\u05dc\u05de\5\u009aN\2\u05dd\u05dc\3\2\2\2\u05dd"+
		"\u05de\3\2\2\2\u05de\u05df\3\2\2\2\u05df\u05e0\t\f\2\2\u05e0\u05e1\t\2"+
		"\2\2\u05e1\u05e2\5\u008eH\2\u05e2\u05e3\bG\1\2\u05e3\u05f0\3\2\2\2\u05e4"+
		"\u05e5\5\u008eH\2\u05e5\u05e6\t\4\2\2\u05e6\u05e7\t\n\2\2\u05e7\u05e8"+
		"\t\5\2\2\u05e8\u05eb\t\5\2\2\u05e9\u05ec\7 \2\2\u05ea\u05ec\7!\2\2\u05eb"+
		"\u05e9\3\2\2\2\u05eb\u05ea\3\2\2\2\u05eb\u05ec\3\2\2\2\u05ec\u05ed\3\2"+
		"\2\2\u05ed\u05ee\bG\1\2\u05ee\u05f0\3\2\2\2\u05ef\u05cb\3\2\2\2\u05ef"+
		"\u05cf\3\2\2\2\u05ef\u05d3\3\2\2\2\u05ef\u05d7\3\2\2\2\u05ef\u05dd\3\2"+
		"\2\2\u05ef\u05e4\3\2\2\2\u05f0\u008d\3\2\2\2\u05f1\u05f3\5\u009aN\2\u05f2"+
		"\u05f1\3\2\2\2\u05f2\u05f3\3\2\2\2\u05f3\u05f4\3\2\2\2\u05f4\u0614\5\u00ac"+
		"W\2\u05f5\u05f6\5\u00acW\2\u05f6\u05f7\5\u009aN\2\u05f7\u05f8\bH\1\2\u05f8"+
		"\u0614\3\2\2\2\u05f9\u05fb\5\u009aN\2\u05fa\u05f9\3\2\2\2\u05fa\u05fb"+
		"\3\2\2\2\u05fb\u05fc\3\2\2\2\u05fc\u05fd\5\u00acW\2\u05fd\u05fe\t\4\2"+
		"\2\u05fe\u05ff\t\n\2\2\u05ff\u0600\t\5\2\2\u0600\u0603\t\5\2\2\u0601\u0604"+
		"\7 \2\2\u0602\u0604\7!\2\2\u0603\u0601\3\2\2\2\u0603\u0602\3\2\2\2\u0603"+
		"\u0604\3\2\2\2\u0604\u0605\3\2\2\2\u0605\u0606\bH\1\2\u0606\u0614\3\2"+
		"\2\2\u0607\u0608\5\u00acW\2\u0608\u0609\t\4\2\2\u0609\u060a\t\n\2\2\u060a"+
		"\u060b\t\5\2\2\u060b\u060e\t\5\2\2\u060c\u060f\7 \2\2\u060d\u060f\7!\2"+
		"\2\u060e\u060c\3\2\2\2\u060e\u060d\3\2\2\2\u060e\u060f\3\2\2\2\u060f\u0610"+
		"\3\2\2\2\u0610\u0611\5\u009aN\2\u0611\u0612\bH\1\2\u0612\u0614\3\2\2\2"+
		"\u0613\u05f2\3\2\2\2\u0613\u05f5\3\2\2\2\u0613\u05fa\3\2\2\2\u0613\u0607"+
		"\3\2\2\2\u0614\u008f\3\2\2\2\u0615\u0617\t\20\2\2\u0616\u0615\3\2\2\2"+
		"\u0617\u0618\3\2\2\2\u0618\u0616\3\2\2\2\u0618\u0619\3\2\2\2\u0619\u0091"+
		"\3\2\2\2\u061a\u061c\t\21\2\2\u061b\u061a\3\2\2\2\u061c\u061d\3\2\2\2"+
		"\u061d\u061b\3\2\2\2\u061d\u061e\3\2\2\2\u061e\u0093\3\2\2\2\u061f\u0621"+
		"\7=\2\2\u0620\u061f\3\2\2\2\u0620\u0621\3\2\2\2\u0621\u0622\3\2\2\2\u0622"+
		"\u0623\5\u0082B\2\u0623\u0624\5\u00acW\2\u0624\u0629\3\2\2\2\u0625\u0626"+
		"\7=\2\2\u0626\u0629\5\u00acW\2\u0627\u0629\7=\2\2\u0628\u0620\3\2\2\2"+
		"\u0628\u0625\3\2\2\2\u0628\u0627\3\2\2\2\u0629\u0095\3\2\2\2\u062a\u062c"+
		"\7=\2\2\u062b\u062a\3\2\2\2\u062b\u062c\3\2\2\2\u062c\u062f\3\2\2\2\u062d"+
		"\u0630\5\u00acW\2\u062e\u0630\5\u00ccg\2\u062f\u062d\3\2\2\2\u062f\u062e"+
		"\3\2\2\2\u0630\u0638\3\2\2\2\u0631\u0632\5\u00a2R\2\u0632\u0634\7=\2\2"+
		"\u0633\u0635\5\u00a2R\2\u0634\u0633\3\2\2\2\u0634\u0635\3\2\2\2\u0635"+
		"\u0638\3\2\2\2\u0636\u0638\7=\2\2\u0637\u062b\3\2\2\2\u0637\u0631\3\2"+
		"\2\2\u0637\u0636\3\2\2\2\u0638\u0097\3\2\2\2\u0639\u0640\5\u00be`\2\u063a"+
		"\u063c\7=\2\2\u063b\u063a\3\2\2\2\u063c\u063d\3\2\2\2\u063d\u063b\3\2"+
		"\2\2\u063d\u063e\3\2\2\2\u063e\u0640\3\2\2\2\u063f\u0639\3\2\2\2\u063f"+
		"\u063b\3\2\2\2\u0640\u0099\3\2\2\2\u0641\u0642\t\4\2\2\u0642\u0643\t\22"+
		"\2\2\u0643\u009b\3\2\2\2\u0644\u0645\5\u0094K\2\u0645\u009d\3\2\2\2\u0646"+
		"\u0648\7=\2\2\u0647\u0646\3\2\2\2\u0647\u0648\3\2\2\2\u0648\u064b\3\2"+
		"\2\2\u0649\u064c\5\u00acW\2\u064a\u064c\5\u00ccg\2\u064b\u0649\3\2\2\2"+
		"\u064b\u064a\3\2\2\2\u064c\u0654\3\2\2\2\u064d\u064e\5\u00a2R\2\u064e"+
		"\u0650\7=\2\2\u064f\u0651\5\u00a2R\2\u0650\u064f\3\2\2\2\u0650\u0651\3"+
		"\2\2\2\u0651\u0654\3\2\2\2\u0652\u0654\7=\2\2\u0653\u0647\3\2\2\2\u0653"+
		"\u064d\3\2\2\2\u0653\u0652\3\2\2\2\u0654\u009f\3\2\2\2\u0655\u0656\t\f"+
		"\2\2\u0656\u0657\t\r\2\2\u0657\u0658\t\n\2\2\u0658\u065d\t\b\2\2\u0659"+
		"\u065a\t\r\2\2\u065a\u065b\t\n\2\2\u065b\u065d\t\b\2\2\u065c\u0655\3\2"+
		"\2\2\u065c\u0659\3\2\2\2\u065d\u00a1\3\2\2\2\u065e\u065f\t\31\2\2\u065f"+
		"\u00a3\3\2\2\2\u0660\u0663\5\u00be`\2\u0661\u0663\5\u00c2b\2\u0662\u0660"+
		"\3\2\2\2\u0662\u0661\3\2\2\2\u0663\u00a5\3\2\2\2\u0664\u0667\5\u00acW"+
		"\2\u0665\u0667\5\u00ccg\2\u0666\u0664\3\2\2\2\u0666\u0665\3\2\2\2\u0667"+
		"\u00a7\3\2\2\2\u0668\u0669\t\6\2\2\u0669\u066a\t\r\2\2\u066a\u06ab\t\r"+
		"\2\2\u066b\u066c\t\r\2\2\u066c\u066d\t\n\2\2\u066d\u06ab\t\5\2\2\u066e"+
		"\u066f\t\22\2\2\u066f\u0670\t\16\2\2\u0670\u0672\t\23\2\2\u0671\u066e"+
		"\3\2\2\2\u0671\u0672\3\2\2\2\u0672\u0673\3\2\2\2\u0673\u0674\t\r\2\2\u0674"+
		"\u0675\t\f\2\2\u0675\u06ab\t\4\2\2\u0676\u0677\t\22\2\2\u0677\u0678\t"+
		"\16\2\2\u0678\u067a\t\23\2\2\u0679\u0676\3\2\2\2\u0679\u067a\3\2\2\2\u067a"+
		"\u067b\3\2\2\2\u067b\u067c\t\f\2\2\u067c\u067d\t\r\2\2\u067d\u067e\t\f"+
		"\2\2\u067e\u06ab\t\4\2\2\u067f\u0680\t\r\2\2\u0680\u0681\t\23\2\2\u0681"+
		"\u06ab\t\22\2\2\u0682\u0683\t\24\2\2\u0683\u0684\t\b\2\2\u0684\u06ab\t"+
		"\22\2\2\u0685\u0686\t\25\2\2\u0686\u0687\t\r\2\2\u0687\u06ab\t\22\2\2"+
		"\u0688\u0689\t\26\2\2\u0689\u068a\t\16\2\2\u068a\u06ab\t\b\2\2\u068b\u068c"+
		"\t\f\2\2\u068c\u068d\t\2\2\2\u068d\u06ab\t\16\2\2\u068e\u068f\t\f\2\2"+
		"\u068f\u0690\t\2\2\2\u0690\u06ab\t\27\2\2\u0691\u06ab\t\f\2\2\u0692\u0693"+
		"\t\r\2\2\u0693\u0694\t\f\2\2\u0694\u0699\t\4\2\2\u0695\u0696\t\24\2\2"+
		"\u0696\u0697\t\b\2\2\u0697\u0699\t\4\2\2\u0698\u0692\3\2\2\2\u0698\u0695"+
		"\3\2\2\2\u0698\u0699\3\2\2\2\u0699\u069a\3\2\2\2\u069a\u06ab\t\b\2\2\u069b"+
		"\u069c\t\24\2\2\u069c\u069d\t\6\2\2\u069d\u06ab\t\16\2\2\u069e\u06ab\t"+
		"\24\2\2\u069f\u06a0\t\22\2\2\u06a0\u06a1\t\16\2\2\u06a1\u06a3\t\23\2\2"+
		"\u06a2\u069f\3\2\2\2\u06a2\u06a3\3\2\2\2\u06a3\u06a4\3\2\2\2\u06a4\u06a5"+
		"\t\24\2\2\u06a5\u06a6\t\b\2\2\u06a6\u06ab\t\4\2\2\u06a7\u06a8\t\b\2\2"+
		"\u06a8\u06a9\t\3\2\2\u06a9\u06ab\t\7\2\2\u06aa\u0668\3\2\2\2\u06aa\u066b"+
		"\3\2\2\2\u06aa\u0671\3\2\2\2\u06aa\u0679\3\2\2\2\u06aa\u067f\3\2\2\2\u06aa"+
		"\u0682\3\2\2\2\u06aa\u0685\3\2\2\2\u06aa\u0688\3\2\2\2\u06aa\u068b\3\2"+
		"\2\2\u06aa\u068e\3\2\2\2\u06aa\u0691\3\2\2\2\u06aa\u0698\3\2\2\2\u06aa"+
		"\u069b\3\2\2\2\u06aa\u069e\3\2\2\2\u06aa\u06a2\3\2\2\2\u06aa\u06a7\3\2"+
		"\2\2\u06ab\u00a9\3\2\2\2\u06ac\u06ad\t\r\2\2\u06ad\u06b8\t\2\2\2\u06ae"+
		"\u06af\t\f\2\2\u06af\u06b0\t\2\2\2\u06b0\u06b8\t\26\2\2\u06b1\u06b2\t"+
		"\t\2\2\u06b2\u06b3\t\6\2\2\u06b3\u06b8\t\24\2\2\u06b4\u06b5\t\22\2\2\u06b5"+
		"\u06b6\t\6\2\2\u06b6\u06b8\t\24\2\2\u06b7\u06ac\3\2\2\2\u06b7\u06ae\3"+
		"\2\2\2\u06b7\u06b1\3\2\2\2\u06b7\u06b4\3\2\2\2\u06b8\u00ab\3\2\2\2\u06b9"+
		"\u06bb\5\u00a2R\2\u06ba\u06b9\3\2\2\2\u06bb\u06bc\3\2\2\2\u06bc\u06ba"+
		"\3\2\2\2\u06bc\u06bd\3\2\2\2\u06bd\u00ad\3\2\2\2\u06be\u06c1\5\u00acW"+
		"\2\u06bf\u06c1\5\u00ccg\2\u06c0\u06be\3\2\2\2\u06c0\u06bf\3\2\2\2\u06c1"+
		"\u00af\3\2\2\2\u06c2\u06c3\5\u00be`\2\u06c3\u00b1\3\2\2\2\u06c4\u06c5"+
		"\5\u00b6\\\2\u06c5\u06c6\5\u00b4[\2\u06c6\u06c7\5\u00b8]\2\u06c7\u00b3"+
		"\3\2\2\2\u06c8\u06c9\t\26\2\2\u06c9\u06e7\t\6\2\2\u06ca\u06cb\t\r\2\2"+
		"\u06cb\u06e7\t\f\2\2\u06cc\u06cd\t\24\2\2\u06cd\u06ce\t\b\2\2\u06ce\u06e7"+
		"\t\f\2\2\u06cf\u06d0\t\24\2\2\u06d0\u06d1\t\n\2\2\u06d1\u06d2\t\24\2\2"+
		"\u06d2\u06d3\t\b\2\2\u06d3\u06e7\t\6\2\2\u06d4\u06d5\t\22\2\2\u06d5\u06d6"+
		"\t\n\2\2\u06d6\u06d7\t\2\2\2\u06d7\u06d8\t\24\2\2\u06d8\u06e7\t\6\2\2"+
		"\u06d9\u06da\t\26\2\2\u06da\u06db\t\n\2\2\u06db\u06dc\t\32\2\2\u06dc\u06e7"+
		"\t\6\2\2\u06dd\u06de\t\26\2\2\u06de\u06df\t\n\2\2\u06df\u06e0\t\22\2\2"+
		"\u06e0\u06e1\t\24\2\2\u06e1\u06e7\t\6\2\2\u06e2\u06e3\t\3\2\2\u06e3\u06e4"+
		"\t\4\2\2\u06e4\u06e5\t\24\2\2\u06e5\u06e7\t\6\2\2\u06e6\u06c8\3\2\2\2"+
		"\u06e6\u06ca\3\2\2\2\u06e6\u06cc\3\2\2\2\u06e6\u06cf\3\2\2\2\u06e6\u06d4"+
		"\3\2\2\2\u06e6\u06d9\3\2\2\2\u06e6\u06dd\3\2\2\2\u06e6\u06e2\3\2\2\2\u06e7"+
		"\u00b5\3\2\2\2\u06e8\u06e9\t\2\2\2\u06e9\u06ea\t\n\2\2\u06ea\u06eb\t\6"+
		"\2\2\u06eb\u06ed\t\b\2\2\u06ec\u06ee\7;\2\2\u06ed\u06ec\3\2\2\2\u06ed"+
		"\u06ee\3\2\2\2\u06ee\u06ff\3\2\2\2\u06ef\u06f0\t\26\2\2\u06f0\u06f1\t"+
		"\33\2\2\u06f1\u06f2\t\22\2\2\u06f2\u06ff\t\3\2\2\u06f3\u06f4\t\26\2\2"+
		"\u06f4\u06f5\t\33\2\2\u06f5\u06f6\t\22\2\2\u06f6\u06f7\t\n\2\2\u06f7\u06ff"+
		"\t\b\2\2\u06f8\u06f9\t\22\2\2\u06f9\u06fa\t\16\2\2\u06fa\u06fb\t\n\2\2"+
		"\u06fb\u06fc\t\23\2\2\u06fc\u06fd\t\r\2\2\u06fd\u06ff\t\3\2\2\u06fe\u06e8"+
		"\3\2\2\2\u06fe\u06ef\3\2\2\2\u06fe\u06f3\3\2\2\2\u06fe\u06f8\3\2\2\2\u06ff"+
		"\u00b7\3\2\2\2\u0700\u0701\t\22\2\2\u0701\u0702\t\5\2\2\u0702\u0703\t"+
		"\3\2\2\u0703\u0704\t\f\2\2\u0704\u0705\t\r\2\2\u0705\u00b9\3\2\2\2\u0706"+
		"\u0707\t\t\2\2\u0707\u0708\t\3\2\2\u0708\u070d\t\16\2\2\u0709\u070a\t"+
		"\4\2\2\u070a\u070b\t\26\2\2\u070b\u070d\t\f\2\2\u070c\u0706\3\2\2\2\u070c"+
		"\u0709\3\2\2\2\u070d\u00bb\3\2\2\2\u070e\u070f\t\32\2\2\u070f\u0710\5"+
		"\u00acW\2\u0710\u00bd\3\2\2\2\u0711\u0714\5\u00acW\2\u0712\u0713\t\34"+
		"\2\2\u0713\u0715\5\u00acW\2\u0714\u0712\3\2\2\2\u0714\u0715\3\2\2\2\u0715"+
		"\u00bf\3\2\2\2\u0716\u0717\5\u00acW\2\u0717\u0718\t\34\2\2\u0718\u0719"+
		"\5\u00acW\2\u0719\u00c1\3\2\2\2\u071a\u071b\t\34\2\2\u071b\u071c\5\u00ac"+
		"W\2\u071c\u00c3\3\2\2\2\u071d\u071f\t\35\2\2\u071e\u071d\3\2\2\2\u071e"+
		"\u071f\3\2\2\2\u071f\u0720\3\2\2\2\u0720\u0726\7=\2\2\u0721\u0723\7=\2"+
		"\2\u0722\u0721\3\2\2\2\u0722\u0723\3\2\2\2\u0723\u0724\3\2\2\2\u0724\u0726"+
		"\t\35\2\2\u0725\u071e\3\2\2\2\u0725\u0722\3\2\2\2\u0726\u00c5\3\2\2\2"+
		"\u0727\u0729\7;\2\2\u0728\u072a\7=\2\2\u0729\u0728\3\2\2\2\u0729\u072a"+
		"\3\2\2\2\u072a\u072e\3\2\2\2\u072b\u072c\7=\2\2\u072c\u072e\7;\2\2\u072d"+
		"\u0727\3\2\2\2\u072d\u072b\3\2\2\2\u072e\u00c7\3\2\2\2\u072f\u0731\7<"+
		"\2\2\u0730\u0732\7=\2\2\u0731\u0730\3\2\2\2\u0731\u0732\3\2\2\2\u0732"+
		"\u0736\3\2\2\2\u0733\u0734\7=\2\2\u0734\u0736\7<\2\2\u0735\u072f\3\2\2"+
		"\2\u0735\u0733\3\2\2\2\u0736\u00c9\3\2\2\2\u0737\u0738\t\6\2\2\u0738\u0739"+
		"\t\r\2\2\u0739\u0752\t\r\2\2\u073a\u073b\t\r\2\2\u073b\u073c\t\n\2\2\u073c"+
		"\u0752\t\5\2\2\u073d\u073e\t\r\2\2\u073e\u073f\t\23\2\2\u073f\u0752\t"+
		"\22\2\2\u0740\u0741\t\24\2\2\u0741\u0742\t\b\2\2\u0742\u0752\t\22\2\2"+
		"\u0743\u0744\t\25\2\2\u0744\u0745\t\r\2\2\u0745\u0752\t\22\2\2\u0746\u0747"+
		"\t\26\2\2\u0747\u0748\t\16\2\2\u0748\u0752\t\b\2\2\u0749\u074a\t\f\2\2"+
		"\u074a\u074b\t\2\2\2\u074b\u0752\t\16\2\2\u074c\u074d\t\f\2\2\u074d\u074e"+
		"\t\2\2\2\u074e\u0752\t\27\2\2\u074f\u0752\t\b\2\2\u0750\u0752\t\24\2\2"+
		"\u0751\u0737\3\2\2\2\u0751\u073a\3\2\2\2\u0751\u073d\3\2\2\2\u0751\u0740"+
		"\3\2\2\2\u0751\u0743\3\2\2\2\u0751\u0746\3\2\2\2\u0751\u0749\3\2\2\2\u0751"+
		"\u074c\3\2\2\2\u0751\u074f\3\2\2\2\u0751\u0750\3\2\2\2\u0752\u00cb\3\2"+
		"\2\2\u0753\u0754\t\36\2\2\u0754\u00cd\3\2\2\2\u0755\u0758\5\u00ccg\2\u0756"+
		"\u0758\7=\2\2\u0757\u0755\3\2\2\2\u0757\u0756\3\2\2\2\u0758\u0759\3\2"+
		"\2\2\u0759\u0757\3\2\2\2\u0759\u075a\3\2\2\2\u075a\u00cf\3\2\2\2\u075b"+
		"\u075e\5\u008aF\2\u075c\u075e\5\u00aaV\2\u075d\u075b\3\2\2\2\u075d\u075c"+
		"\3\2\2\2\u075d\u075e\3\2\2\2\u075e\u075f\3\2\2\2\u075f\u0768\5\u00bc_"+
		"\2\u0760\u0762\5\u00bc_\2\u0761\u0760\3\2\2\2\u0761\u0762\3\2\2\2\u0762"+
		"\u0765\3\2\2\2\u0763\u0766\5\u008aF\2\u0764\u0766\5\u00aaV\2\u0765\u0763"+
		"\3\2\2\2\u0765\u0764\3\2\2\2\u0766\u0768\3\2\2\2\u0767\u075d\3\2\2\2\u0767"+
		"\u0761\3\2\2\2\u0768\u00d1\3\2\2\2\u0769\u0771\7\3\2\2\u076a\u076d\5\u00a2"+
		"R\2\u076b\u076d\7=\2\2\u076c\u076a\3\2\2\2\u076c\u076b\3\2\2\2\u076d\u076e"+
		"\3\2\2\2\u076e\u076c\3\2\2\2\u076e\u076f\3\2\2\2\u076f\u0772\3\2\2\2\u0770"+
		"\u0772\5\u00c0a\2\u0771\u076c\3\2\2\2\u0771\u0770\3\2\2\2\u0772\u00d3"+
		"\3\2\2\2\u0773\u0774\7;\2\2\u0774\u0778\7<\2\2\u0775\u0776\7<\2\2\u0776"+
		"\u0778\7;\2\2\u0777\u0773\3\2\2\2\u0777\u0775\3\2\2\2\u0778\u077a\3\2"+
		"\2\2\u0779\u077b\7=\2\2\u077a\u0779\3\2\2\2\u077a\u077b\3\2\2\2\u077b"+
		"\u0784\3\2\2\2\u077c\u0781\7=\2\2\u077d\u077e\7;\2\2\u077e\u0782\7<\2"+
		"\2\u077f\u0780\7<\2\2\u0780\u0782\7;\2\2\u0781\u077d\3\2\2\2\u0781\u077f"+
		"\3\2\2\2\u0782\u0784\3\2\2\2\u0783\u0777\3\2\2\2\u0783\u077c\3\2\2\2\u0784"+
		"\u00d5\3\2\2\2\u0116\u00da\u00dc\u00df\u00e4\u00e6\u00ea\u00ed\u00f3\u00f7"+
		"\u00fe\u0105\u010a\u010e\u0112\u0119\u0120\u0125\u012b\u0131\u0135\u013c"+
		"\u0142\u0149\u014f\u0154\u0157\u015d\u0160\u0166\u016a\u0171\u0178\u017d"+
		"\u0181\u0185\u018c\u0193\u0198\u019c\u01a0\u01a7\u01ae\u01b3\u01b7\u01bb"+
		"\u01c2\u01c9\u01ce\u01d1\u01d5\u01db\u01df\u01e2\u01e6\u01eb\u01ef\u01f4"+
		"\u01f9\u01fc\u0200\u0203\u0207\u020d\u0211\u0214\u0218\u021e\u0222\u0225"+
		"\u022b\u022f\u0234\u0238\u023d\u0241\u0246\u024b\u024e\u0252\u0255\u0259"+
		"\u025f\u0263\u0266\u026c\u0270\u0274\u0277\u027b\u0281\u0285\u0288\u028e"+
		"\u0292\u0296\u0299\u029d\u02a2\u02a8\u02b8\u02ba\u02bd\u02c6\u02c8\u02ce"+
		"\u02d3\u02e9\u02f2\u02f7\u02ff\u0303\u0309\u0310\u031c\u0327\u0332\u033d"+
		"\u0342\u0349\u0353\u0357\u035e\u0363\u0367\u036e\u0374\u037c\u037f\u0382"+
		"\u0386\u038a\u038d\u0394\u0399\u039f\u03a7\u03ad\u03b1\u03b7\u03bb\u03be"+
		"\u03c2\u03ca\u03ce\u03d4\u03d7\u03da\u03e1\u03e6\u03e9\u03ec\u03f1\u03f6"+
		"\u03fa\u03ff\u0402\u0407\u040a\u040f\u0412\u0417\u041c\u041f\u0425\u0429"+
		"\u042e\u0432\u0437\u043a\u0440\u0444\u0447\u044b\u0454\u0456\u0459\u045e"+
		"\u0461\u0467\u046b\u046f\u0485\u048c\u0492\u0499\u049e\u04a4\u04bb\u04c2"+
		"\u04c8\u04cf\u04d8\u04da\u04f0\u04f7\u04fd\u0504\u051a\u0521\u0527\u052e"+
		"\u0537\u0539\u054f\u0565\u0570\u0589\u059f\u05a3\u05aa\u05af\u05b1\u05b6"+
		"\u05b8\u05be\u05c2\u05c7\u05dd\u05eb\u05ef\u05f2\u05fa\u0603\u060e\u0613"+
		"\u0618\u061d\u0620\u0628\u062b\u062f\u0634\u0637\u063d\u063f\u0647\u064b"+
		"\u0650\u0653\u065c\u0662\u0666\u0671\u0679\u0698\u06a2\u06aa\u06b7\u06bc"+
		"\u06c0\u06e6\u06ed\u06fe\u070c\u0714\u071e\u0722\u0725\u0729\u072d\u0731"+
		"\u0735\u0751\u0757\u0759\u075d\u0761\u0765\u0767\u076c\u076e\u0771\u0777"+
		"\u077a\u0781\u0783";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}