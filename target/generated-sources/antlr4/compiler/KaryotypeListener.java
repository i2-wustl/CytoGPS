// Generated from Karyotype.g4 by ANTLR 4.7

package compiler; 
import java.util.*; 

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link KaryotypeParser}.
 */
public interface KaryotypeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#row}.
	 * @param ctx the parse tree
	 */
	void enterRow(KaryotypeParser.RowContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#row}.
	 * @param ctx the parse tree
	 */
	void exitRow(KaryotypeParser.RowContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#rowTypeI}.
	 * @param ctx the parse tree
	 */
	void enterRowTypeI(KaryotypeParser.RowTypeIContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#rowTypeI}.
	 * @param ctx the parse tree
	 */
	void exitRowTypeI(KaryotypeParser.RowTypeIContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#firstClone}.
	 * @param ctx the parse tree
	 */
	void enterFirstClone(KaryotypeParser.FirstCloneContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#firstClone}.
	 * @param ctx the parse tree
	 */
	void exitFirstClone(KaryotypeParser.FirstCloneContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#clone}.
	 * @param ctx the parse tree
	 */
	void enterClone(KaryotypeParser.CloneContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#clone}.
	 * @param ctx the parse tree
	 */
	void exitClone(KaryotypeParser.CloneContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#nonclonalClone}.
	 * @param ctx the parse tree
	 */
	void enterNonclonalClone(KaryotypeParser.NonclonalCloneContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#nonclonalClone}.
	 * @param ctx the parse tree
	 */
	void exitNonclonalClone(KaryotypeParser.NonclonalCloneContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#rowTypeII}.
	 * @param ctx the parse tree
	 */
	void enterRowTypeII(KaryotypeParser.RowTypeIIContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#rowTypeII}.
	 * @param ctx the parse tree
	 */
	void exitRowTypeII(KaryotypeParser.RowTypeIIContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#firstStemlineGroup}.
	 * @param ctx the parse tree
	 */
	void enterFirstStemlineGroup(KaryotypeParser.FirstStemlineGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#firstStemlineGroup}.
	 * @param ctx the parse tree
	 */
	void exitFirstStemlineGroup(KaryotypeParser.FirstStemlineGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#otherStemlineGroup}.
	 * @param ctx the parse tree
	 */
	void enterOtherStemlineGroup(KaryotypeParser.OtherStemlineGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#otherStemlineGroup}.
	 * @param ctx the parse tree
	 */
	void exitOtherStemlineGroup(KaryotypeParser.OtherStemlineGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#stemlineClone}.
	 * @param ctx the parse tree
	 */
	void enterStemlineClone(KaryotypeParser.StemlineCloneContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#stemlineClone}.
	 * @param ctx the parse tree
	 */
	void exitStemlineClone(KaryotypeParser.StemlineCloneContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#additionalStemlineClone}.
	 * @param ctx the parse tree
	 */
	void enterAdditionalStemlineClone(KaryotypeParser.AdditionalStemlineCloneContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#additionalStemlineClone}.
	 * @param ctx the parse tree
	 */
	void exitAdditionalStemlineClone(KaryotypeParser.AdditionalStemlineCloneContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#sidelineCloneTypeI}.
	 * @param ctx the parse tree
	 */
	void enterSidelineCloneTypeI(KaryotypeParser.SidelineCloneTypeIContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#sidelineCloneTypeI}.
	 * @param ctx the parse tree
	 */
	void exitSidelineCloneTypeI(KaryotypeParser.SidelineCloneTypeIContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#sidelineCloneTypeII}.
	 * @param ctx the parse tree
	 */
	void enterSidelineCloneTypeII(KaryotypeParser.SidelineCloneTypeIIContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#sidelineCloneTypeII}.
	 * @param ctx the parse tree
	 */
	void exitSidelineCloneTypeII(KaryotypeParser.SidelineCloneTypeIIContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#additionalClone}.
	 * @param ctx the parse tree
	 */
	void enterAdditionalClone(KaryotypeParser.AdditionalCloneContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#additionalClone}.
	 * @param ctx the parse tree
	 */
	void exitAdditionalClone(KaryotypeParser.AdditionalCloneContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#karyotypeI}.
	 * @param ctx the parse tree
	 */
	void enterKaryotypeI(KaryotypeParser.KaryotypeIContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#karyotypeI}.
	 * @param ctx the parse tree
	 */
	void exitKaryotypeI(KaryotypeParser.KaryotypeIContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#karyotypeII}.
	 * @param ctx the parse tree
	 */
	void enterKaryotypeII(KaryotypeParser.KaryotypeIIContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#karyotypeII}.
	 * @param ctx the parse tree
	 */
	void exitKaryotypeII(KaryotypeParser.KaryotypeIIContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#karyotypeIII}.
	 * @param ctx the parse tree
	 */
	void enterKaryotypeIII(KaryotypeParser.KaryotypeIIIContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#karyotypeIII}.
	 * @param ctx the parse tree
	 */
	void exitKaryotypeIII(KaryotypeParser.KaryotypeIIIContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#karyotypeIV}.
	 * @param ctx the parse tree
	 */
	void enterKaryotypeIV(KaryotypeParser.KaryotypeIVContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#karyotypeIV}.
	 * @param ctx the parse tree
	 */
	void exitKaryotypeIV(KaryotypeParser.KaryotypeIVContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#karyotypeV}.
	 * @param ctx the parse tree
	 */
	void enterKaryotypeV(KaryotypeParser.KaryotypeVContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#karyotypeV}.
	 * @param ctx the parse tree
	 */
	void exitKaryotypeV(KaryotypeParser.KaryotypeVContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#ploidy}.
	 * @param ctx the parse tree
	 */
	void enterPloidy(KaryotypeParser.PloidyContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#ploidy}.
	 * @param ctx the parse tree
	 */
	void exitPloidy(KaryotypeParser.PloidyContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#tooManySlant}.
	 * @param ctx the parse tree
	 */
	void enterTooManySlant(KaryotypeParser.TooManySlantContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#tooManySlant}.
	 * @param ctx the parse tree
	 */
	void exitTooManySlant(KaryotypeParser.TooManySlantContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#incorrectSlant}.
	 * @param ctx the parse tree
	 */
	void enterIncorrectSlant(KaryotypeParser.IncorrectSlantContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#incorrectSlant}.
	 * @param ctx the parse tree
	 */
	void exitIncorrectSlant(KaryotypeParser.IncorrectSlantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code correctModalNum}
	 * labeled alternative in {@link KaryotypeParser#modalNum}.
	 * @param ctx the parse tree
	 */
	void enterCorrectModalNum(KaryotypeParser.CorrectModalNumContext ctx);
	/**
	 * Exit a parse tree produced by the {@code correctModalNum}
	 * labeled alternative in {@link KaryotypeParser#modalNum}.
	 * @param ctx the parse tree
	 */
	void exitCorrectModalNum(KaryotypeParser.CorrectModalNumContext ctx);
	/**
	 * Enter a parse tree produced by the {@code incorrectModalNum}
	 * labeled alternative in {@link KaryotypeParser#modalNum}.
	 * @param ctx the parse tree
	 */
	void enterIncorrectModalNum(KaryotypeParser.IncorrectModalNumContext ctx);
	/**
	 * Exit a parse tree produced by the {@code incorrectModalNum}
	 * labeled alternative in {@link KaryotypeParser#modalNum}.
	 * @param ctx the parse tree
	 */
	void exitIncorrectModalNum(KaryotypeParser.IncorrectModalNumContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#modalNumContent}.
	 * @param ctx the parse tree
	 */
	void enterModalNumContent(KaryotypeParser.ModalNumContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#modalNumContent}.
	 * @param ctx the parse tree
	 */
	void exitModalNumContent(KaryotypeParser.ModalNumContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#gender}.
	 * @param ctx the parse tree
	 */
	void enterGender(KaryotypeParser.GenderContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#gender}.
	 * @param ctx the parse tree
	 */
	void exitGender(KaryotypeParser.GenderContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#tooManyComma}.
	 * @param ctx the parse tree
	 */
	void enterTooManyComma(KaryotypeParser.TooManyCommaContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#tooManyComma}.
	 * @param ctx the parse tree
	 */
	void exitTooManyComma(KaryotypeParser.TooManyCommaContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#orSex}.
	 * @param ctx the parse tree
	 */
	void enterOrSex(KaryotypeParser.OrSexContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#orSex}.
	 * @param ctx the parse tree
	 */
	void exitOrSex(KaryotypeParser.OrSexContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#idemSpecial}.
	 * @param ctx the parse tree
	 */
	void enterIdemSpecial(KaryotypeParser.IdemSpecialContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#idemSpecial}.
	 * @param ctx the parse tree
	 */
	void exitIdemSpecial(KaryotypeParser.IdemSpecialContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#slSpecial}.
	 * @param ctx the parse tree
	 */
	void enterSlSpecial(KaryotypeParser.SlSpecialContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#slSpecial}.
	 * @param ctx the parse tree
	 */
	void exitSlSpecial(KaryotypeParser.SlSpecialContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#sdlSpecial}.
	 * @param ctx the parse tree
	 */
	void enterSdlSpecial(KaryotypeParser.SdlSpecialContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#sdlSpecial}.
	 * @param ctx the parse tree
	 */
	void exitSdlSpecial(KaryotypeParser.SdlSpecialContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#regularEvent}.
	 * @param ctx the parse tree
	 */
	void enterRegularEvent(KaryotypeParser.RegularEventContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#regularEvent}.
	 * @param ctx the parse tree
	 */
	void exitRegularEvent(KaryotypeParser.RegularEventContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#incorrectComma}.
	 * @param ctx the parse tree
	 */
	void enterIncorrectComma(KaryotypeParser.IncorrectCommaContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#incorrectComma}.
	 * @param ctx the parse tree
	 */
	void exitIncorrectComma(KaryotypeParser.IncorrectCommaContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#idemEvent}.
	 * @param ctx the parse tree
	 */
	void enterIdemEvent(KaryotypeParser.IdemEventContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#idemEvent}.
	 * @param ctx the parse tree
	 */
	void exitIdemEvent(KaryotypeParser.IdemEventContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#slEvent}.
	 * @param ctx the parse tree
	 */
	void enterSlEvent(KaryotypeParser.SlEventContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#slEvent}.
	 * @param ctx the parse tree
	 */
	void exitSlEvent(KaryotypeParser.SlEventContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#slMark}.
	 * @param ctx the parse tree
	 */
	void enterSlMark(KaryotypeParser.SlMarkContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#slMark}.
	 * @param ctx the parse tree
	 */
	void exitSlMark(KaryotypeParser.SlMarkContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#sdlEvent}.
	 * @param ctx the parse tree
	 */
	void enterSdlEvent(KaryotypeParser.SdlEventContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#sdlEvent}.
	 * @param ctx the parse tree
	 */
	void exitSdlEvent(KaryotypeParser.SdlEventContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#sdlMark}.
	 * @param ctx the parse tree
	 */
	void enterSdlMark(KaryotypeParser.SdlMarkContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#sdlMark}.
	 * @param ctx the parse tree
	 */
	void exitSdlMark(KaryotypeParser.SdlMarkContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multipleInterpretationRegularEvent}
	 * labeled alternative in {@link KaryotypeParser#regEvent}.
	 * @param ctx the parse tree
	 */
	void enterMultipleInterpretationRegularEvent(KaryotypeParser.MultipleInterpretationRegularEventContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multipleInterpretationRegularEvent}
	 * labeled alternative in {@link KaryotypeParser#regEvent}.
	 * @param ctx the parse tree
	 */
	void exitMultipleInterpretationRegularEvent(KaryotypeParser.MultipleInterpretationRegularEventContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleRegularEvent}
	 * labeled alternative in {@link KaryotypeParser#regEvent}.
	 * @param ctx the parse tree
	 */
	void enterSimpleRegularEvent(KaryotypeParser.SimpleRegularEventContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleRegularEvent}
	 * labeled alternative in {@link KaryotypeParser#regEvent}.
	 * @param ctx the parse tree
	 */
	void exitSimpleRegularEvent(KaryotypeParser.SimpleRegularEventContext ctx);
	/**
	 * Enter a parse tree produced by the {@code undecodedSpecialEvent}
	 * labeled alternative in {@link KaryotypeParser#regEventType}.
	 * @param ctx the parse tree
	 */
	void enterUndecodedSpecialEvent(KaryotypeParser.UndecodedSpecialEventContext ctx);
	/**
	 * Exit a parse tree produced by the {@code undecodedSpecialEvent}
	 * labeled alternative in {@link KaryotypeParser#regEventType}.
	 * @param ctx the parse tree
	 */
	void exitUndecodedSpecialEvent(KaryotypeParser.UndecodedSpecialEventContext ctx);
	/**
	 * Enter a parse tree produced by the {@code aberrationEvent}
	 * labeled alternative in {@link KaryotypeParser#regEventType}.
	 * @param ctx the parse tree
	 */
	void enterAberrationEvent(KaryotypeParser.AberrationEventContext ctx);
	/**
	 * Exit a parse tree produced by the {@code aberrationEvent}
	 * labeled alternative in {@link KaryotypeParser#regEventType}.
	 * @param ctx the parse tree
	 */
	void exitAberrationEvent(KaryotypeParser.AberrationEventContext ctx);
	/**
	 * Enter a parse tree produced by the {@code undecodedEvent}
	 * labeled alternative in {@link KaryotypeParser#regEventType}.
	 * @param ctx the parse tree
	 */
	void enterUndecodedEvent(KaryotypeParser.UndecodedEventContext ctx);
	/**
	 * Exit a parse tree produced by the {@code undecodedEvent}
	 * labeled alternative in {@link KaryotypeParser#regEventType}.
	 * @param ctx the parse tree
	 */
	void exitUndecodedEvent(KaryotypeParser.UndecodedEventContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gainLossChrEvent}
	 * labeled alternative in {@link KaryotypeParser#regEventType}.
	 * @param ctx the parse tree
	 */
	void enterGainLossChrEvent(KaryotypeParser.GainLossChrEventContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gainLossChrEvent}
	 * labeled alternative in {@link KaryotypeParser#regEventType}.
	 * @param ctx the parse tree
	 */
	void exitGainLossChrEvent(KaryotypeParser.GainLossChrEventContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gainChrEvent}
	 * labeled alternative in {@link KaryotypeParser#gainLossChr}.
	 * @param ctx the parse tree
	 */
	void enterGainChrEvent(KaryotypeParser.GainChrEventContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gainChrEvent}
	 * labeled alternative in {@link KaryotypeParser#gainLossChr}.
	 * @param ctx the parse tree
	 */
	void exitGainChrEvent(KaryotypeParser.GainChrEventContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lossChrEvent}
	 * labeled alternative in {@link KaryotypeParser#gainLossChr}.
	 * @param ctx the parse tree
	 */
	void enterLossChrEvent(KaryotypeParser.LossChrEventContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lossChrEvent}
	 * labeled alternative in {@link KaryotypeParser#gainLossChr}.
	 * @param ctx the parse tree
	 */
	void exitLossChrEvent(KaryotypeParser.LossChrEventContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gainLossChrError}
	 * labeled alternative in {@link KaryotypeParser#gainLossChr}.
	 * @param ctx the parse tree
	 */
	void enterGainLossChrError(KaryotypeParser.GainLossChrErrorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gainLossChrError}
	 * labeled alternative in {@link KaryotypeParser#gainLossChr}.
	 * @param ctx the parse tree
	 */
	void exitGainLossChrError(KaryotypeParser.GainLossChrErrorContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#undecoded}.
	 * @param ctx the parse tree
	 */
	void enterUndecoded(KaryotypeParser.UndecodedContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#undecoded}.
	 * @param ctx the parse tree
	 */
	void exitUndecoded(KaryotypeParser.UndecodedContext ctx);
	/**
	 * Enter a parse tree produced by the {@code derAberration}
	 * labeled alternative in {@link KaryotypeParser#aberration}.
	 * @param ctx the parse tree
	 */
	void enterDerAberration(KaryotypeParser.DerAberrationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code derAberration}
	 * labeled alternative in {@link KaryotypeParser#aberration}.
	 * @param ctx the parse tree
	 */
	void exitDerAberration(KaryotypeParser.DerAberrationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code basicAberration}
	 * labeled alternative in {@link KaryotypeParser#aberration}.
	 * @param ctx the parse tree
	 */
	void enterBasicAberration(KaryotypeParser.BasicAberrationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code basicAberration}
	 * labeled alternative in {@link KaryotypeParser#aberration}.
	 * @param ctx the parse tree
	 */
	void exitBasicAberration(KaryotypeParser.BasicAberrationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code uncertainBasicAberration}
	 * labeled alternative in {@link KaryotypeParser#aberration}.
	 * @param ctx the parse tree
	 */
	void enterUncertainBasicAberration(KaryotypeParser.UncertainBasicAberrationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code uncertainBasicAberration}
	 * labeled alternative in {@link KaryotypeParser#aberration}.
	 * @param ctx the parse tree
	 */
	void exitUncertainBasicAberration(KaryotypeParser.UncertainBasicAberrationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code derAberrationError}
	 * labeled alternative in {@link KaryotypeParser#aberration}.
	 * @param ctx the parse tree
	 */
	void enterDerAberrationError(KaryotypeParser.DerAberrationErrorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code derAberrationError}
	 * labeled alternative in {@link KaryotypeParser#aberration}.
	 * @param ctx the parse tree
	 */
	void exitDerAberrationError(KaryotypeParser.DerAberrationErrorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code basicAberrationError}
	 * labeled alternative in {@link KaryotypeParser#aberration}.
	 * @param ctx the parse tree
	 */
	void enterBasicAberrationError(KaryotypeParser.BasicAberrationErrorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code basicAberrationError}
	 * labeled alternative in {@link KaryotypeParser#aberration}.
	 * @param ctx the parse tree
	 */
	void exitBasicAberrationError(KaryotypeParser.BasicAberrationErrorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code normalRearrangement}
	 * labeled alternative in {@link KaryotypeParser#rearrangementElement}.
	 * @param ctx the parse tree
	 */
	void enterNormalRearrangement(KaryotypeParser.NormalRearrangementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code normalRearrangement}
	 * labeled alternative in {@link KaryotypeParser#rearrangementElement}.
	 * @param ctx the parse tree
	 */
	void exitNormalRearrangement(KaryotypeParser.NormalRearrangementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multipleInterpretationRearrangement}
	 * labeled alternative in {@link KaryotypeParser#rearrangementElement}.
	 * @param ctx the parse tree
	 */
	void enterMultipleInterpretationRearrangement(KaryotypeParser.MultipleInterpretationRearrangementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multipleInterpretationRearrangement}
	 * labeled alternative in {@link KaryotypeParser#rearrangementElement}.
	 * @param ctx the parse tree
	 */
	void exitMultipleInterpretationRearrangement(KaryotypeParser.MultipleInterpretationRearrangementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#rearrangement}.
	 * @param ctx the parse tree
	 */
	void enterRearrangement(KaryotypeParser.RearrangementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#rearrangement}.
	 * @param ctx the parse tree
	 */
	void exitRearrangement(KaryotypeParser.RearrangementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code correctDerChrList}
	 * labeled alternative in {@link KaryotypeParser#derChrList}.
	 * @param ctx the parse tree
	 */
	void enterCorrectDerChrList(KaryotypeParser.CorrectDerChrListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code correctDerChrList}
	 * labeled alternative in {@link KaryotypeParser#derChrList}.
	 * @param ctx the parse tree
	 */
	void exitCorrectDerChrList(KaryotypeParser.CorrectDerChrListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code incorrectDerChrList}
	 * labeled alternative in {@link KaryotypeParser#derChrList}.
	 * @param ctx the parse tree
	 */
	void enterIncorrectDerChrList(KaryotypeParser.IncorrectDerChrListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code incorrectDerChrList}
	 * labeled alternative in {@link KaryotypeParser#derChrList}.
	 * @param ctx the parse tree
	 */
	void exitIncorrectDerChrList(KaryotypeParser.IncorrectDerChrListContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#derChrListElements}.
	 * @param ctx the parse tree
	 */
	void enterDerChrListElements(KaryotypeParser.DerChrListElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#derChrListElements}.
	 * @param ctx the parse tree
	 */
	void exitDerChrListElements(KaryotypeParser.DerChrListElementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#incorrectDerChrListElements}.
	 * @param ctx the parse tree
	 */
	void enterIncorrectDerChrListElements(KaryotypeParser.IncorrectDerChrListElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#incorrectDerChrListElements}.
	 * @param ctx the parse tree
	 */
	void exitIncorrectDerChrListElements(KaryotypeParser.IncorrectDerChrListElementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#incorrectLeftParenthesis}.
	 * @param ctx the parse tree
	 */
	void enterIncorrectLeftParenthesis(KaryotypeParser.IncorrectLeftParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#incorrectLeftParenthesis}.
	 * @param ctx the parse tree
	 */
	void exitIncorrectLeftParenthesis(KaryotypeParser.IncorrectLeftParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#incorrectRightParenthesis}.
	 * @param ctx the parse tree
	 */
	void enterIncorrectRightParenthesis(KaryotypeParser.IncorrectRightParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#incorrectRightParenthesis}.
	 * @param ctx the parse tree
	 */
	void exitIncorrectRightParenthesis(KaryotypeParser.IncorrectRightParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code correctDerBreakpointsList}
	 * labeled alternative in {@link KaryotypeParser#derBreakpointsList}.
	 * @param ctx the parse tree
	 */
	void enterCorrectDerBreakpointsList(KaryotypeParser.CorrectDerBreakpointsListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code correctDerBreakpointsList}
	 * labeled alternative in {@link KaryotypeParser#derBreakpointsList}.
	 * @param ctx the parse tree
	 */
	void exitCorrectDerBreakpointsList(KaryotypeParser.CorrectDerBreakpointsListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code incorrectDerBreakpointsList}
	 * labeled alternative in {@link KaryotypeParser#derBreakpointsList}.
	 * @param ctx the parse tree
	 */
	void enterIncorrectDerBreakpointsList(KaryotypeParser.IncorrectDerBreakpointsListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code incorrectDerBreakpointsList}
	 * labeled alternative in {@link KaryotypeParser#derBreakpointsList}.
	 * @param ctx the parse tree
	 */
	void exitIncorrectDerBreakpointsList(KaryotypeParser.IncorrectDerBreakpointsListContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#derBreakpointsListElements}.
	 * @param ctx the parse tree
	 */
	void enterDerBreakpointsListElements(KaryotypeParser.DerBreakpointsListElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#derBreakpointsListElements}.
	 * @param ctx the parse tree
	 */
	void exitDerBreakpointsListElements(KaryotypeParser.DerBreakpointsListElementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#incorrectDerBreakpointsListElements}.
	 * @param ctx the parse tree
	 */
	void enterIncorrectDerBreakpointsListElements(KaryotypeParser.IncorrectDerBreakpointsListElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#incorrectDerBreakpointsListElements}.
	 * @param ctx the parse tree
	 */
	void exitIncorrectDerBreakpointsListElements(KaryotypeParser.IncorrectDerBreakpointsListElementsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code normalDerBreakpoints}
	 * labeled alternative in {@link KaryotypeParser#derBreakpointsInOneChr}.
	 * @param ctx the parse tree
	 */
	void enterNormalDerBreakpoints(KaryotypeParser.NormalDerBreakpointsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code normalDerBreakpoints}
	 * labeled alternative in {@link KaryotypeParser#derBreakpointsInOneChr}.
	 * @param ctx the parse tree
	 */
	void exitNormalDerBreakpoints(KaryotypeParser.NormalDerBreakpointsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multipleInterpretationDerBreakpoints}
	 * labeled alternative in {@link KaryotypeParser#derBreakpointsInOneChr}.
	 * @param ctx the parse tree
	 */
	void enterMultipleInterpretationDerBreakpoints(KaryotypeParser.MultipleInterpretationDerBreakpointsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multipleInterpretationDerBreakpoints}
	 * labeled alternative in {@link KaryotypeParser#derBreakpointsInOneChr}.
	 * @param ctx the parse tree
	 */
	void exitMultipleInterpretationDerBreakpoints(KaryotypeParser.MultipleInterpretationDerBreakpointsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code correctChrList}
	 * labeled alternative in {@link KaryotypeParser#chrList}.
	 * @param ctx the parse tree
	 */
	void enterCorrectChrList(KaryotypeParser.CorrectChrListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code correctChrList}
	 * labeled alternative in {@link KaryotypeParser#chrList}.
	 * @param ctx the parse tree
	 */
	void exitCorrectChrList(KaryotypeParser.CorrectChrListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code incorrectChrList}
	 * labeled alternative in {@link KaryotypeParser#chrList}.
	 * @param ctx the parse tree
	 */
	void enterIncorrectChrList(KaryotypeParser.IncorrectChrListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code incorrectChrList}
	 * labeled alternative in {@link KaryotypeParser#chrList}.
	 * @param ctx the parse tree
	 */
	void exitIncorrectChrList(KaryotypeParser.IncorrectChrListContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#chrListElements}.
	 * @param ctx the parse tree
	 */
	void enterChrListElements(KaryotypeParser.ChrListElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#chrListElements}.
	 * @param ctx the parse tree
	 */
	void exitChrListElements(KaryotypeParser.ChrListElementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#incorrectChrListElements}.
	 * @param ctx the parse tree
	 */
	void enterIncorrectChrListElements(KaryotypeParser.IncorrectChrListElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#incorrectChrListElements}.
	 * @param ctx the parse tree
	 */
	void exitIncorrectChrListElements(KaryotypeParser.IncorrectChrListElementsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code correctBreakpointsList}
	 * labeled alternative in {@link KaryotypeParser#breakpointsList}.
	 * @param ctx the parse tree
	 */
	void enterCorrectBreakpointsList(KaryotypeParser.CorrectBreakpointsListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code correctBreakpointsList}
	 * labeled alternative in {@link KaryotypeParser#breakpointsList}.
	 * @param ctx the parse tree
	 */
	void exitCorrectBreakpointsList(KaryotypeParser.CorrectBreakpointsListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code incorrectBreakpointsList}
	 * labeled alternative in {@link KaryotypeParser#breakpointsList}.
	 * @param ctx the parse tree
	 */
	void enterIncorrectBreakpointsList(KaryotypeParser.IncorrectBreakpointsListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code incorrectBreakpointsList}
	 * labeled alternative in {@link KaryotypeParser#breakpointsList}.
	 * @param ctx the parse tree
	 */
	void exitIncorrectBreakpointsList(KaryotypeParser.IncorrectBreakpointsListContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#breakpointsListElements}.
	 * @param ctx the parse tree
	 */
	void enterBreakpointsListElements(KaryotypeParser.BreakpointsListElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#breakpointsListElements}.
	 * @param ctx the parse tree
	 */
	void exitBreakpointsListElements(KaryotypeParser.BreakpointsListElementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#incorrectBreakpointsListElements}.
	 * @param ctx the parse tree
	 */
	void enterIncorrectBreakpointsListElements(KaryotypeParser.IncorrectBreakpointsListElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#incorrectBreakpointsListElements}.
	 * @param ctx the parse tree
	 */
	void exitIncorrectBreakpointsListElements(KaryotypeParser.IncorrectBreakpointsListElementsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code normalBreakpoints}
	 * labeled alternative in {@link KaryotypeParser#breakpointsInOneChr}.
	 * @param ctx the parse tree
	 */
	void enterNormalBreakpoints(KaryotypeParser.NormalBreakpointsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code normalBreakpoints}
	 * labeled alternative in {@link KaryotypeParser#breakpointsInOneChr}.
	 * @param ctx the parse tree
	 */
	void exitNormalBreakpoints(KaryotypeParser.NormalBreakpointsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multipleInterpretationBreakpoints}
	 * labeled alternative in {@link KaryotypeParser#breakpointsInOneChr}.
	 * @param ctx the parse tree
	 */
	void enterMultipleInterpretationBreakpoints(KaryotypeParser.MultipleInterpretationBreakpointsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multipleInterpretationBreakpoints}
	 * labeled alternative in {@link KaryotypeParser#breakpointsInOneChr}.
	 * @param ctx the parse tree
	 */
	void exitMultipleInterpretationBreakpoints(KaryotypeParser.MultipleInterpretationBreakpointsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code correctRChrList}
	 * labeled alternative in {@link KaryotypeParser#rChrList}.
	 * @param ctx the parse tree
	 */
	void enterCorrectRChrList(KaryotypeParser.CorrectRChrListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code correctRChrList}
	 * labeled alternative in {@link KaryotypeParser#rChrList}.
	 * @param ctx the parse tree
	 */
	void exitCorrectRChrList(KaryotypeParser.CorrectRChrListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code incorrectRChrList}
	 * labeled alternative in {@link KaryotypeParser#rChrList}.
	 * @param ctx the parse tree
	 */
	void enterIncorrectRChrList(KaryotypeParser.IncorrectRChrListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code incorrectRChrList}
	 * labeled alternative in {@link KaryotypeParser#rChrList}.
	 * @param ctx the parse tree
	 */
	void exitIncorrectRChrList(KaryotypeParser.IncorrectRChrListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code correctRBreakpointsList}
	 * labeled alternative in {@link KaryotypeParser#rBreakpointsList}.
	 * @param ctx the parse tree
	 */
	void enterCorrectRBreakpointsList(KaryotypeParser.CorrectRBreakpointsListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code correctRBreakpointsList}
	 * labeled alternative in {@link KaryotypeParser#rBreakpointsList}.
	 * @param ctx the parse tree
	 */
	void exitCorrectRBreakpointsList(KaryotypeParser.CorrectRBreakpointsListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code incorrectRBreakpointsList}
	 * labeled alternative in {@link KaryotypeParser#rBreakpointsList}.
	 * @param ctx the parse tree
	 */
	void enterIncorrectRBreakpointsList(KaryotypeParser.IncorrectRBreakpointsListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code incorrectRBreakpointsList}
	 * labeled alternative in {@link KaryotypeParser#rBreakpointsList}.
	 * @param ctx the parse tree
	 */
	void exitIncorrectRBreakpointsList(KaryotypeParser.IncorrectRBreakpointsListContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#idUncertainChr}.
	 * @param ctx the parse tree
	 */
	void enterIdUncertainChr(KaryotypeParser.IdUncertainChrContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#idUncertainChr}.
	 * @param ctx the parse tree
	 */
	void exitIdUncertainChr(KaryotypeParser.IdUncertainChrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code correctUncertainChr}
	 * labeled alternative in {@link KaryotypeParser#uncertainChr}.
	 * @param ctx the parse tree
	 */
	void enterCorrectUncertainChr(KaryotypeParser.CorrectUncertainChrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code correctUncertainChr}
	 * labeled alternative in {@link KaryotypeParser#uncertainChr}.
	 * @param ctx the parse tree
	 */
	void exitCorrectUncertainChr(KaryotypeParser.CorrectUncertainChrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code incorrectUncertainChr}
	 * labeled alternative in {@link KaryotypeParser#uncertainChr}.
	 * @param ctx the parse tree
	 */
	void enterIncorrectUncertainChr(KaryotypeParser.IncorrectUncertainChrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code incorrectUncertainChr}
	 * labeled alternative in {@link KaryotypeParser#uncertainChr}.
	 * @param ctx the parse tree
	 */
	void exitIncorrectUncertainChr(KaryotypeParser.IncorrectUncertainChrContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#uncertainChrContent}.
	 * @param ctx the parse tree
	 */
	void enterUncertainChrContent(KaryotypeParser.UncertainChrContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#uncertainChrContent}.
	 * @param ctx the parse tree
	 */
	void exitUncertainChrContent(KaryotypeParser.UncertainChrContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#arm}.
	 * @param ctx the parse tree
	 */
	void enterArm(KaryotypeParser.ArmContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#arm}.
	 * @param ctx the parse tree
	 */
	void exitArm(KaryotypeParser.ArmContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#band}.
	 * @param ctx the parse tree
	 */
	void enterBand(KaryotypeParser.BandContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#band}.
	 * @param ctx the parse tree
	 */
	void exitBand(KaryotypeParser.BandContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#breakpoint}.
	 * @param ctx the parse tree
	 */
	void enterBreakpoint(KaryotypeParser.BreakpointContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#breakpoint}.
	 * @param ctx the parse tree
	 */
	void exitBreakpoint(KaryotypeParser.BreakpointContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#breakpoints}.
	 * @param ctx the parse tree
	 */
	void enterBreakpoints(KaryotypeParser.BreakpointsContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#breakpoints}.
	 * @param ctx the parse tree
	 */
	void exitBreakpoints(KaryotypeParser.BreakpointsContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#c}.
	 * @param ctx the parse tree
	 */
	void enterC(KaryotypeParser.CContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#c}.
	 * @param ctx the parse tree
	 */
	void exitC(KaryotypeParser.CContext ctx);
	/**
	 * Enter a parse tree produced by the {@code correctCellNum}
	 * labeled alternative in {@link KaryotypeParser#cellNum}.
	 * @param ctx the parse tree
	 */
	void enterCorrectCellNum(KaryotypeParser.CorrectCellNumContext ctx);
	/**
	 * Exit a parse tree produced by the {@code correctCellNum}
	 * labeled alternative in {@link KaryotypeParser#cellNum}.
	 * @param ctx the parse tree
	 */
	void exitCorrectCellNum(KaryotypeParser.CorrectCellNumContext ctx);
	/**
	 * Enter a parse tree produced by the {@code incorrectCellNum}
	 * labeled alternative in {@link KaryotypeParser#cellNum}.
	 * @param ctx the parse tree
	 */
	void enterIncorrectCellNum(KaryotypeParser.IncorrectCellNumContext ctx);
	/**
	 * Exit a parse tree produced by the {@code incorrectCellNum}
	 * labeled alternative in {@link KaryotypeParser#cellNum}.
	 * @param ctx the parse tree
	 */
	void exitIncorrectCellNum(KaryotypeParser.IncorrectCellNumContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#cellNumContent}.
	 * @param ctx the parse tree
	 */
	void enterCellNumContent(KaryotypeParser.CellNumContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#cellNumContent}.
	 * @param ctx the parse tree
	 */
	void exitCellNumContent(KaryotypeParser.CellNumContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#incorrectLeftParenthesisII}.
	 * @param ctx the parse tree
	 */
	void enterIncorrectLeftParenthesisII(KaryotypeParser.IncorrectLeftParenthesisIIContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#incorrectLeftParenthesisII}.
	 * @param ctx the parse tree
	 */
	void exitIncorrectLeftParenthesisII(KaryotypeParser.IncorrectLeftParenthesisIIContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#incorrectRightParenthesisII}.
	 * @param ctx the parse tree
	 */
	void enterIncorrectRightParenthesisII(KaryotypeParser.IncorrectRightParenthesisIIContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#incorrectRightParenthesisII}.
	 * @param ctx the parse tree
	 */
	void exitIncorrectRightParenthesisII(KaryotypeParser.IncorrectRightParenthesisIIContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#cen}.
	 * @param ctx the parse tree
	 */
	void enterCen(KaryotypeParser.CenContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#cen}.
	 * @param ctx the parse tree
	 */
	void exitCen(KaryotypeParser.CenContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#chr}.
	 * @param ctx the parse tree
	 */
	void enterChr(KaryotypeParser.ChrContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#chr}.
	 * @param ctx the parse tree
	 */
	void exitChr(KaryotypeParser.ChrContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#chrNum}.
	 * @param ctx the parse tree
	 */
	void enterChrNum(KaryotypeParser.ChrNumContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#chrNum}.
	 * @param ctx the parse tree
	 */
	void exitChrNum(KaryotypeParser.ChrNumContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#cp}.
	 * @param ctx the parse tree
	 */
	void enterCp(KaryotypeParser.CpContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#cp}.
	 * @param ctx the parse tree
	 */
	void exitCp(KaryotypeParser.CpContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#derBreakpoints}.
	 * @param ctx the parse tree
	 */
	void enterDerBreakpoints(KaryotypeParser.DerBreakpointsContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#derBreakpoints}.
	 * @param ctx the parse tree
	 */
	void exitDerBreakpoints(KaryotypeParser.DerBreakpointsContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#derChr}.
	 * @param ctx the parse tree
	 */
	void enterDerChr(KaryotypeParser.DerChrContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#derChr}.
	 * @param ctx the parse tree
	 */
	void exitDerChr(KaryotypeParser.DerChrContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#derId}.
	 * @param ctx the parse tree
	 */
	void enterDerId(KaryotypeParser.DerIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#derId}.
	 * @param ctx the parse tree
	 */
	void exitDerId(KaryotypeParser.DerIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#digit}.
	 * @param ctx the parse tree
	 */
	void enterDigit(KaryotypeParser.DigitContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#digit}.
	 * @param ctx the parse tree
	 */
	void exitDigit(KaryotypeParser.DigitContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#dminNum}.
	 * @param ctx the parse tree
	 */
	void enterDminNum(KaryotypeParser.DminNumContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#dminNum}.
	 * @param ctx the parse tree
	 */
	void exitDminNum(KaryotypeParser.DminNumContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#gainChr}.
	 * @param ctx the parse tree
	 */
	void enterGainChr(KaryotypeParser.GainChrContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#gainChr}.
	 * @param ctx the parse tree
	 */
	void exitGainChr(KaryotypeParser.GainChrContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(KaryotypeParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(KaryotypeParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#inh}.
	 * @param ctx the parse tree
	 */
	void enterInh(KaryotypeParser.InhContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#inh}.
	 * @param ctx the parse tree
	 */
	void exitInh(KaryotypeParser.InhContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#integer}.
	 * @param ctx the parse tree
	 */
	void enterInteger(KaryotypeParser.IntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#integer}.
	 * @param ctx the parse tree
	 */
	void exitInteger(KaryotypeParser.IntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#lossChr}.
	 * @param ctx the parse tree
	 */
	void enterLossChr(KaryotypeParser.LossChrContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#lossChr}.
	 * @param ctx the parse tree
	 */
	void exitLossChr(KaryotypeParser.LossChrContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#marNum}.
	 * @param ctx the parse tree
	 */
	void enterMarNum(KaryotypeParser.MarNumContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#marNum}.
	 * @param ctx the parse tree
	 */
	void exitMarNum(KaryotypeParser.MarNumContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#modalDesc}.
	 * @param ctx the parse tree
	 */
	void enterModalDesc(KaryotypeParser.ModalDescContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#modalDesc}.
	 * @param ctx the parse tree
	 */
	void exitModalDesc(KaryotypeParser.ModalDescContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#modalLevel}.
	 * @param ctx the parse tree
	 */
	void enterModalLevel(KaryotypeParser.ModalLevelContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#modalLevel}.
	 * @param ctx the parse tree
	 */
	void exitModalLevel(KaryotypeParser.ModalLevelContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#modalPrefix}.
	 * @param ctx the parse tree
	 */
	void enterModalPrefix(KaryotypeParser.ModalPrefixContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#modalPrefix}.
	 * @param ctx the parse tree
	 */
	void exitModalPrefix(KaryotypeParser.ModalPrefixContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#modalSuffix}.
	 * @param ctx the parse tree
	 */
	void enterModalSuffix(KaryotypeParser.ModalSuffixContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#modalSuffix}.
	 * @param ctx the parse tree
	 */
	void exitModalSuffix(KaryotypeParser.ModalSuffixContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#mosChi}.
	 * @param ctx the parse tree
	 */
	void enterMosChi(KaryotypeParser.MosChiContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#mosChi}.
	 * @param ctx the parse tree
	 */
	void exitMosChi(KaryotypeParser.MosChiContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#multiplication}.
	 * @param ctx the parse tree
	 */
	void enterMultiplication(KaryotypeParser.MultiplicationContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#multiplication}.
	 * @param ctx the parse tree
	 */
	void exitMultiplication(KaryotypeParser.MultiplicationContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#numRangeTypeI}.
	 * @param ctx the parse tree
	 */
	void enterNumRangeTypeI(KaryotypeParser.NumRangeTypeIContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#numRangeTypeI}.
	 * @param ctx the parse tree
	 */
	void exitNumRangeTypeI(KaryotypeParser.NumRangeTypeIContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#numRangeTypeII}.
	 * @param ctx the parse tree
	 */
	void enterNumRangeTypeII(KaryotypeParser.NumRangeTypeIIContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#numRangeTypeII}.
	 * @param ctx the parse tree
	 */
	void exitNumRangeTypeII(KaryotypeParser.NumRangeTypeIIContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#numRangeTypeIII}.
	 * @param ctx the parse tree
	 */
	void enterNumRangeTypeIII(KaryotypeParser.NumRangeTypeIIIContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#numRangeTypeIII}.
	 * @param ctx the parse tree
	 */
	void exitNumRangeTypeIII(KaryotypeParser.NumRangeTypeIIIContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#prefix}.
	 * @param ctx the parse tree
	 */
	void enterPrefix(KaryotypeParser.PrefixContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#prefix}.
	 * @param ctx the parse tree
	 */
	void exitPrefix(KaryotypeParser.PrefixContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#prefixMinus}.
	 * @param ctx the parse tree
	 */
	void enterPrefixMinus(KaryotypeParser.PrefixMinusContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#prefixMinus}.
	 * @param ctx the parse tree
	 */
	void exitPrefixMinus(KaryotypeParser.PrefixMinusContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#prefixPlus}.
	 * @param ctx the parse tree
	 */
	void enterPrefixPlus(KaryotypeParser.PrefixPlusContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#prefixPlus}.
	 * @param ctx the parse tree
	 */
	void exitPrefixPlus(KaryotypeParser.PrefixPlusContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#rId}.
	 * @param ctx the parse tree
	 */
	void enterRId(KaryotypeParser.RIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#rId}.
	 * @param ctx the parse tree
	 */
	void exitRId(KaryotypeParser.RIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#sex}.
	 * @param ctx the parse tree
	 */
	void enterSex(KaryotypeParser.SexContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#sex}.
	 * @param ctx the parse tree
	 */
	void exitSex(KaryotypeParser.SexContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#sexChr}.
	 * @param ctx the parse tree
	 */
	void enterSexChr(KaryotypeParser.SexChrContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#sexChr}.
	 * @param ctx the parse tree
	 */
	void exitSexChr(KaryotypeParser.SexChrContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#suffix}.
	 * @param ctx the parse tree
	 */
	void enterSuffix(KaryotypeParser.SuffixContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#suffix}.
	 * @param ctx the parse tree
	 */
	void exitSuffix(KaryotypeParser.SuffixContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#subband}.
	 * @param ctx the parse tree
	 */
	void enterSubband(KaryotypeParser.SubbandContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#subband}.
	 * @param ctx the parse tree
	 */
	void exitSubband(KaryotypeParser.SubbandContext ctx);
	/**
	 * Enter a parse tree produced by {@link KaryotypeParser#undeterminedPrefix}.
	 * @param ctx the parse tree
	 */
	void enterUndeterminedPrefix(KaryotypeParser.UndeterminedPrefixContext ctx);
	/**
	 * Exit a parse tree produced by {@link KaryotypeParser#undeterminedPrefix}.
	 * @param ctx the parse tree
	 */
	void exitUndeterminedPrefix(KaryotypeParser.UndeterminedPrefixContext ctx);
}