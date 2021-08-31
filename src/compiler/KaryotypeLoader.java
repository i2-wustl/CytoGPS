package compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.Clone;
import business.DerEvent;
import business.Event;

/**
 * @author Lin Zhang
 * Programmer / Software Development Engineer
 * Institute for Informatics
 * Washington University School of Medicine in St. Louis
 * 
 * Date: August 30, 2021 
 */
public class KaryotypeLoader extends KaryotypeBaseListener {

	public List<Clone> getRowClones() {
		return rowClones;
	}

	int cloneIndex = -1;
	List<Clone> rowClones = new ArrayList<>();
	boolean isFirstClone;
	boolean isClone;
	boolean isIdemClone = false;
	boolean isSpecialCancellingEventWithMinusSign = false;
	int eventNumInFirstOrPreviousCloneToBeCancelled;
	Clone firstClone;
	Clone clone;

	int copies;
	int idemCopies;
	Event e, e1, e2;
	DerEvent d;
	boolean uncertainty;
	boolean derUncertainty;
	boolean multipleInterpretableEvent = false;
	boolean multipleInterpretableRearrangement = false;
	String gainChr;
	String lossChr;
	String id;
	String rId;
	String derId;
	String chr;
	String derChr;
	List<String> breakpoints;
	List<String> derBreakpoints;
	boolean multipleInterpretationBreakpoints = false;
	List<String> multipleInterpretationBreakpointsList;
	boolean multipleInterpretationDerBreakpoints = false;
	List<String> multipleInterpretationDerBreakpointsList;
	String breakpoint;
	String derCen;

	public void enterFirstClone(KaryotypeParser.FirstCloneContext ctx) {
		isFirstClone = true;
		firstClone = new Clone();
	}

	public void exitFirstClone(KaryotypeParser.FirstCloneContext ctx) {
		firstClone.setCloneCode(ctx.getText());
		rowClones.add(firstClone);
		isFirstClone = false;
		cloneIndex++;
	}

	public void enterClone(KaryotypeParser.CloneContext ctx) {
		isClone = true;
		clone = new Clone();
	}

	public void exitClone(KaryotypeParser.CloneContext ctx) {
		clone.setCloneCode(ctx.getText().substring(1));
		if (isIdemClone) {
			clone.setRelatedClone(0);
			clone.getRelationship().put("idem", "refers to the first clone");
			isIdemClone = false;
		}
		rowClones.add(clone);
		isClone = false;
		cloneIndex++;
	}

	public void enterIdemEvent(KaryotypeParser.IdemEventContext ctx) {
		idemCopies = 1;
		isIdemClone = true;
	}

	public void exitIdemEvent(KaryotypeParser.IdemEventContext ctx) {
		if (ctx.multiplication() != null) {
			idemCopies = Integer.parseInt(ctx.multiplication().integer().getText());
		}
		for (int i = 1; i <= idemCopies; i++) {
			clone.getCloneInput().addAll(firstClone.getCloneInput());
		}
	}

	public void enterMultipleInterpretationRegularEvent(KaryotypeParser.MultipleInterpretationRegularEventContext ctx) {
		multipleInterpretableEvent = true;
	}

	public void exitMultipleInterpretationRegularEvent(KaryotypeParser.MultipleInterpretationRegularEventContext ctx) {
		multipleInterpretableEvent = false;
	}

	public void enterUndecodedEvent(KaryotypeParser.UndecodedEventContext ctx) {
		e = new Event();
	}

	public void exitUndecodedEvent(KaryotypeParser.UndecodedEventContext ctx) {
		e.setUncertainEvent(true);
		e.setEventCode(ctx.getText());
		if (isFirstClone) {
			firstClone.getCloneInput().add(e);
		} else if (isClone) {
			clone.getCloneInput().add(e);
		} else {
			currentClone.getCloneInput().add(e);
		}
	}
	
	public void enterUndecodedSpecialEvent(KaryotypeParser.UndecodedSpecialEventContext ctx) {
		e1 = new Event();
		e2 = new Event();
	}
	
	public void exitUndecodedSpecialEvent(KaryotypeParser.UndecodedSpecialEventContext ctx) {
		String[] inputs = ctx.getText().split(",");
		e1.setUncertainEvent(true);
		e1.setEventCode(inputs[0]);
		e2.setUncertainEvent(true);
		e2.setEventCode(inputs[1]);
		if (isFirstClone) {
			firstClone.getCloneInput().add(e1);
			firstClone.getCloneInput().add(e2);
		} else if (isClone) {
			clone.getCloneInput().add(e1);
			clone.getCloneInput().add(e2);
		} else {
			currentClone.getCloneInput().add(e1);
			currentClone.getCloneInput().add(e2);
		}
	}

	public void enterGainLossChrEvent(KaryotypeParser.GainLossChrEventContext ctx) {
		e = new Event();
	}

	public void exitGainLossChrEvent(KaryotypeParser.GainLossChrEventContext ctx) {
		if (multipleInterpretableEvent) {
			e.setUncertainEvent(true);
		}
		e.setEventCode(ctx.getText());
		if (isFirstClone) {
			firstClone.getCloneInput().add(e);
		} else if (isClone) {
			clone.getCloneInput().add(e);
		} else {
			currentClone.getCloneInput().add(e);
		}
	}

	public void enterGainChrEvent(KaryotypeParser.GainChrEventContext ctx) {
		gainChr = "";
	}

	public void exitGainChrEvent(KaryotypeParser.GainChrEventContext ctx) {
		if (ctx.prefixPlus().QUES() != null) {
			e.setUncertainEvent(true);
		}
		gainChr = ctx.gainChr().getText().toUpperCase();
		e.getGainChrs().add(gainChr);
	}

	public void enterLossChrEvent(KaryotypeParser.LossChrEventContext ctx) {
		lossChr = "";
	}

	public void exitLossChrEvent(KaryotypeParser.LossChrEventContext ctx) {
		if (ctx.prefixMinus().QUES() != null) {
			e.setUncertainEvent(true);
		}
		lossChr = ctx.lossChr().getText().toUpperCase();
		e.getLossChrs().add(lossChr);
	}

	public void exitAberrationEvent(KaryotypeParser.AberrationEventContext ctx) {
		if (multipleInterpretableEvent) {
			e.setUncertainEvent(true);
		}
		e.setEventCode(ctx.getText());
		if (isFirstClone) {
			firstClone.getCloneInput().add(e);
		} else if (isClone) {
			if (isSpecialCancellingEventWithMinusSign) {
				if (cancelledCopies <= idemCopies) {
					if (cancelledCopies == 1) {
						clone.getCloneInput().remove(eventNumInFirstOrPreviousCloneToBeCancelled);
					} else {
						int relatedCloneEventSize = firstClone.getCloneInput().size();
						for (int i = cancelledCopies - 1; i >= 0; i--) {
							clone.getCloneInput().remove(eventNumInFirstOrPreviousCloneToBeCancelled + i * relatedCloneEventSize);
						}
					}
				} else {
					String errorMsg = "No enough idem copies to make cancellations for \"" + e.getEventCode() + "\"";
					errorMsgList.add(errorMsg);
				}			
				isSpecialCancellingEventWithMinusSign = false;
			} else {
				clone.getCloneInput().add(e);
			}
		} else {
			if (isSpecialCancellingEventWithMinusSign) {
				if (isSlClone) {
					if (cancelledCopies <= slCopies) {
						if (cancelledCopies == 1) {
							currentClone.getCloneInput().remove(eventNumInFirstOrPreviousCloneToBeCancelled);
						} else {
							int relatedCloneEventSize = rowClones.get(currentClone.getRelatedClone()).getCloneInput().size();
							for (int i = cancelledCopies - 1; i >= 0; i--) {
								currentClone.getCloneInput().remove(eventNumInFirstOrPreviousCloneToBeCancelled + i * relatedCloneEventSize);
							}
						}
					} else {
						String errorMsg = "No enough sl copies to make cancellations for \"" + e.getEventCode() + "\"";
						errorMsgList.add(errorMsg);
					}
				} else {
					if (cancelledCopies <= sdlCopies) {
						if (cancelledCopies == 1) {
							currentClone.getCloneInput().remove(eventNumInFirstOrPreviousCloneToBeCancelled);
						} else {
							int relatedCloneEventSize = rowClones.get(currentClone.getRelatedClone()).getCloneInput().size();
							for (int i = cancelledCopies - 1; i >= 0; i--) {
								currentClone.getCloneInput().remove(eventNumInFirstOrPreviousCloneToBeCancelled + i * relatedCloneEventSize);
							}
						}
					} else {
						String errorMsg = "No enough sdl copies to make cancellations for \"" + e.getEventCode() + "\"";
						errorMsgList.add(errorMsg);
					}
				} // isSdlClone		
				isSpecialCancellingEventWithMinusSign = false;
			} else {
				currentClone.getCloneInput().add(e);
			}
		}
	}

	public void enterMultipleInterpretationRearrangement(
			KaryotypeParser.MultipleInterpretationRearrangementContext ctx) {
		multipleInterpretableRearrangement = true;
	}

	public void exitMultipleInterpretationRearrangement(
			KaryotypeParser.MultipleInterpretationRearrangementContext ctx) {
		multipleInterpretableRearrangement = false;
	}

	public void enterDerAberration(KaryotypeParser.DerAberrationContext ctx) {
		d = new DerEvent();
		derUncertainty = false;
	}

	public void exitDerAberration(KaryotypeParser.DerAberrationContext ctx) {
		if (ctx.prefix() != null) {
			if (ctx.prefix().QUES() != null) {
				derUncertainty = true;
			}
			if (ctx.prefix().PLUS() != null) {
				for (String chr : d.getChrList()) {
					d.getGainChrs().add(chr);
				}
			}
			if (ctx.prefix().MINUS() != null) {
				String searchedS = ctx.derId().getText() + ctx.derChrList().getText();
				if (ctx.derBreakpointsList() != null) {
					searchedS += ctx.derBreakpointsList().getText();
				}
				for (KaryotypeParser.RearrangementElementContext rearrangementElementCtx : ctx.rearrangementElement()) {
					searchedS += rearrangementElementCtx.getText();
				}
				boolean isDerBeingCancelled = true;
				if (isIdemClone)
					eventNumInFirstOrPreviousCloneToBeCancelled = searchInFirstClone(searchedS, isDerBeingCancelled);
				if (isSlClone || isSdlClone)
					eventNumInFirstOrPreviousCloneToBeCancelled = searchInPreviousClone(searchedS, isDerBeingCancelled);
				if (ctx.prefix().QUES() == null && (isIdemClone || isSlClone || isSdlClone)
						&& eventNumInFirstOrPreviousCloneToBeCancelled != -1) {
					isSpecialCancellingEventWithMinusSign = true;
				} else {
					for (String chr : d.getChrList()) {
						d.getLossChrs().add(chr);
					}
				}
			}
		}
		if (ctx.suffix() != null) {
			if (ctx.suffix().multiplication() != null) {
				copies = Integer.parseInt(ctx.suffix().multiplication().integer().getText());
				if (isSpecialCancellingEventWithMinusSign) {
					cancelledCopies = copies;
				} else {
					d.setCopies(copies);
				}				
			}
		}
		if (derUncertainty) {
			d.setUncertainEvent(true);
		}
		e = d;
	}

	public void enterDerId(KaryotypeParser.DerIdContext ctx) {
		derId = "";
	}

	public void exitDerId(KaryotypeParser.DerIdContext ctx) {
		derId = ctx.getText().toLowerCase();
		d.setNature(derId);
	}

	public void enterDerChr(KaryotypeParser.DerChrContext ctx) {
		derChr = "";
	}

	public void exitDerChr(KaryotypeParser.DerChrContext ctx) {
		derChr = ctx.getText().toUpperCase();
		d.getChrList().add(derChr);
		if (ctx.QUES() != null) {
			derUncertainty = true;
		}
	}

	public void enterDerBreakpoints(KaryotypeParser.DerBreakpointsContext ctx) {
		derBreakpoints = new ArrayList<String>();
	}

	public void exitDerBreakpoints(KaryotypeParser.DerBreakpointsContext ctx) {
		if (multipleInterpretationDerBreakpoints) {
			multipleInterpretationDerBreakpointsList.add("or" + ctx.getText());
		} else {
			d.getBreakpoints().add(derBreakpoints);
		}
	}

	public void enterMultipleInterpretationDerBreakpoints(
			KaryotypeParser.MultipleInterpretationDerBreakpointsContext ctx) {
		multipleInterpretationDerBreakpoints = true;
		multipleInterpretationDerBreakpointsList = new ArrayList<>();
	}

	public void exitMultipleInterpretationDerBreakpoints(
			KaryotypeParser.MultipleInterpretationDerBreakpointsContext ctx) {
		multipleInterpretationDerBreakpoints = false;
		d.getBreakpoints().add(multipleInterpretationDerBreakpointsList);
		derUncertainty = true;
	}

	public void enterCen(KaryotypeParser.CenContext ctx) {
		derCen = "";
	}

	public void exitCen(KaryotypeParser.CenContext ctx) {
		derCen = ctx.getText().toLowerCase();
		derBreakpoints.add(derCen);
		if (ctx.QUES() != null) {
			derUncertainty = true;
		}
		// if (!ctx.intger().getText().equals("10")) {
		// derUncertainty = true;
		// }
	}

	public void enterBasicAberration(KaryotypeParser.BasicAberrationContext ctx) {
		e = new Event();
		uncertainty = false;
	}

	public void exitBasicAberration(KaryotypeParser.BasicAberrationContext ctx) {
		if (ctx.prefix() != null) {
			if (ctx.prefix().QUES() != null) {
				uncertainty = true;
			}
			if (ctx.prefix().PLUS() != null) {
				for (String chr : e.getChrList()) {
					e.getGainChrs().add(chr);
				}
			}
			if (ctx.prefix().MINUS() != null) {
				String searchedS = ctx.id().getText() + ctx.chrList().getText();
				if (ctx.breakpointsList() != null) {
					searchedS += ctx.breakpointsList().getText();
				}
				boolean isDerBeingCancelled = false;
				if (isIdemClone)
					eventNumInFirstOrPreviousCloneToBeCancelled = searchInFirstClone(searchedS, isDerBeingCancelled);
				if (isSlClone || isSdlClone)
					eventNumInFirstOrPreviousCloneToBeCancelled = searchInPreviousClone(searchedS, isDerBeingCancelled);
				if (ctx.prefix().QUES() == null && (isIdemClone || isSlClone || isSdlClone)
						&& eventNumInFirstOrPreviousCloneToBeCancelled != -1) {
					isSpecialCancellingEventWithMinusSign = true;
				} else {
					for (String chr : e.getChrList()) {
						e.getLossChrs().add(chr);
					}
				}
			}
		}
		if (ctx.suffix() != null) {
			if (ctx.suffix().multiplication() != null) {
				copies = Integer.parseInt(ctx.suffix().multiplication().integer().getText());
				if (isSpecialCancellingEventWithMinusSign) {
					cancelledCopies = copies;
				} else {
					e.setCopies(copies);
				}				
			}
		}
		if (uncertainty) {
			e.setUncertainEvent(true);
		}
	}

	public void enterId(KaryotypeParser.IdContext ctx) {
		id = "";
	}

	public void exitId(KaryotypeParser.IdContext ctx) {
		id = ctx.getText().toLowerCase();
		if (id.equals("dicr")) {
			id = "r";
		}
		if (id.equals("trcr")) {
			id = "r";
		}
		if (id.equals("psudic")) {
			id = "dic";
		}
		if (id.equals("psutrc")) {
			id = "trc";
		}
		if (id.equals("psuidic")) {
			id = "idic";
		}
		e.setNature(id);
	}

	public void enterChr(KaryotypeParser.ChrContext ctx) {
		chr = "";
	}

	public void exitChr(KaryotypeParser.ChrContext ctx) {
		chr = ctx.getText().toUpperCase();
		e.getChrList().add(chr);
		if (ctx.QUES() != null) {
			uncertainty = true;
		}
	}

	public void enterBreakpoints(KaryotypeParser.BreakpointsContext ctx) {
		breakpoints = new ArrayList<String>();
	}

	public void exitBreakpoints(KaryotypeParser.BreakpointsContext ctx) {
		if (multipleInterpretationBreakpoints) {
			multipleInterpretationBreakpointsList.add("or" + ctx.getText());
		} else {
			e.getBreakpoints().add(breakpoints);
		}
	}

	public void enterMultipleInterpretationBreakpoints(KaryotypeParser.MultipleInterpretationBreakpointsContext ctx) {
		multipleInterpretationBreakpoints = true;
		multipleInterpretationBreakpointsList = new ArrayList<>();
	}

	public void exitMultipleInterpretationBreakpoints(KaryotypeParser.MultipleInterpretationBreakpointsContext ctx) {
		multipleInterpretationBreakpoints = false;
		e.getBreakpoints().add(multipleInterpretationBreakpointsList);
		uncertainty = true;
	}

	public void enterBreakpoint(KaryotypeParser.BreakpointContext ctx) {
		breakpoint = "";
	}

	public void exitBreakpoint(KaryotypeParser.BreakpointContext ctx) {
		breakpoint = ctx.getText().toLowerCase();
		breakpoints.add(breakpoint);
		String band = ctx.band().getText();
		if (band.contains("?") || band.contains("-") || band.contains("~")) {
			uncertainty = true;
		}
		if (ctx.subband() != null) {
			String subband = ctx.subband().getText();
			if (subband.contains("?") || subband.contains("-") || subband.contains("~")) {
				uncertainty = true;
			}
		}
	}

	public void enterRearrangement(KaryotypeParser.RearrangementContext ctx) {
		e = new Event();
		uncertainty = false;
	}

	public void exitRearrangement(KaryotypeParser.RearrangementContext ctx) {
		if (ctx.QUES() != null) {
			uncertainty = true;
		}
		if (multipleInterpretableRearrangement) {
			uncertainty = true;
		}
		e.setEventCode(ctx.getText());
		if (uncertainty) {
			e.setUncertainEvent(true);
		}
		d.getSubevents().add(e);
	}

	public void enterRId(KaryotypeParser.RIdContext ctx) {
		rId = "";
	}

	public void exitRId(KaryotypeParser.RIdContext ctx) {
		rId = ctx.getText().toLowerCase();
		e.setNature(rId);
	}

	public void enterUncertainBasicAberration(KaryotypeParser.UncertainBasicAberrationContext ctx) {
		e = new Event();
	}

	public void exitUncertainBasicAberration(KaryotypeParser.UncertainBasicAberrationContext ctx) {
		e.setUncertainEvent(true);
	}

	private int searchInFirstClone(String searchedS, boolean isDerBeingCancelled) {
		for (int i = 0; i < firstClone.getCloneInput().size(); i++) {
			Event e = firstClone.getCloneInput().get(i);
			if (isDerBeingCancelled) {
				if (!e.isUncertainEvent() && e.getEventCode().toLowerCase().contains(searchedS.toLowerCase())) {
					return i;
				}
			} else {
				if (!e.isUncertainEvent() && e.getEventCode().toLowerCase().contains(searchedS.toLowerCase())
						&& !(e instanceof DerEvent)) {
					return i;
				}
			}
		}
		return -1;
	}

	public List<String> getErrorMsgList() {
		return errorMsgList;
	}

	List<String> errorMsgList = new ArrayList<>();
	List<String> slMarkListAcrossCloneGroup;
	List<String> sdlMarkListWithinCloneGroup;
	String slMark;
	String sdlMark;
	int slCopies;
	int sdlCopies;
	boolean isCloneTypeII;
	Map<String, Integer> slCloneMap;
	Map<String, Integer> sdlCloneMap;
	boolean isUsingConstantSlMark;

	public void enterRowTypeII(KaryotypeParser.RowTypeIIContext ctx) {
		isCloneTypeII = true;
		slMarkListAcrossCloneGroup = new ArrayList<>();
	}

	public void exitRowTypeII(KaryotypeParser.RowTypeIIContext ctx) {
		if (!isStrictlyIncreasingSlMarkList(slMarkListAcrossCloneGroup)) {
			String errorMsg = "In a karyotype with multiple stemlines, you must use a strictly increasing sequence of sl marks, such as sl1, sl2, sl3, and so on.";
			errorMsgList.add(errorMsg);
		}
		isCloneTypeII = false;
	}

	public void enterFirstStemlineGroup(KaryotypeParser.FirstStemlineGroupContext ctx) {
		sdlMarkListWithinCloneGroup = new ArrayList<String>();
		slCloneMap = new HashMap<>();
		sdlCloneMap = new HashMap<>();
		isUsingConstantSlMark = true;
	}

	public void exitFirstStemlineGroup(KaryotypeParser.FirstStemlineGroupContext ctx) {
		String stemline = ctx.getText();
		if (!isUsingConstantSlMark) {
			String errorMsg = "In the stemline group \"" + stemline + "\", you must use the same sl marks.";
			errorMsgList.add(errorMsg);
		} 
		if (!isIncreasingSdlMarkList(sdlMarkListWithinCloneGroup)) {
			String errorMsg = "In the stemline group \"" + stemline
					+ "\", you must use an increasing sequence of sdl marks, such as sdl1, sdl2, sdl2, sdl3, and so on.";
			errorMsgList.add(errorMsg);
		}
	}

	public void enterOtherStemlineGroup(KaryotypeParser.OtherStemlineGroupContext ctx) {
		slCloneMap = new HashMap<>();
		isUsingConstantSlMark = true;
	}

	public void exitOtherStemlineGroup(KaryotypeParser.OtherStemlineGroupContext ctx) {
		String stemline = ctx.getText();
		if (!isUsingConstantSlMark) {
			String errorMsg = "In the stemline group \"" + stemline + "\", you must use the same sl marks.";
			errorMsgList.add(errorMsg);
		}
	}

	public void enterSlEvent(KaryotypeParser.SlEventContext ctx) {
		slMark = "";
		slCopies = 1;
	}

	public void exitSlEvent(KaryotypeParser.SlEventContext ctx) {
		slMark = ctx.slMark().getText().toLowerCase();
		if (slCloneMap.size() == 0) {
			slMarkListAcrossCloneGroup.add(slMark);
			slCloneMap.put(slMark, cloneIndex);
		} else if (slCloneMap.get(slMark) != null) {
			
		} else {
			isUsingConstantSlMark = false;
		}
		
		if (isUsingConstantSlMark) {
			int relatedClone = slCloneMap.get(slMark);
			currentClone.setRelatedClone(relatedClone);
			currentClone.getRelationship().put(slMark, "refers to the stemline clone");
			if (ctx.multiplication() != null) {
				slCopies = Integer.parseInt(ctx.multiplication().integer().getText());
			}
			for (int i = 1; i <= slCopies; i++) {
				currentClone.getCloneInput().addAll(rowClones.get(relatedClone).getCloneInput());
			}
		}

	}

	public void enterSdlEvent(KaryotypeParser.SdlEventContext ctx) {
		sdlMark = "";
		sdlCopies = 1;
	}

	public void exitSdlEvent(KaryotypeParser.SdlEventContext ctx) {
		sdlMark = ctx.sdlMark().getText().toLowerCase();
		sdlMarkListWithinCloneGroup.add(sdlMark);
		int relatedClone;
		if (sdlCloneMap.get(sdlMark) == null) {
			relatedClone = cloneIndex;
		    sdlCloneMap.put(sdlMark, relatedClone);	
		} else {
			relatedClone = sdlCloneMap.get(sdlMark);
		}
		currentClone.setRelatedClone(relatedClone);
		currentClone.getRelationship().put(sdlMark, "refers to the sideline clone");
		if (ctx.multiplication() != null) {
			sdlCopies = Integer.parseInt(ctx.multiplication().integer().getText());
		}
		for (int i = 1; i <= sdlCopies; i++) {
			currentClone.getCloneInput().addAll(rowClones.get(relatedClone).getCloneInput());
		}
	}

	private boolean isStrictlyIncreasingSlMarkList(List<String> slMarkListAcrossCloneGroup) {
		if (slMarkListAcrossCloneGroup.size() > 1) {
			String previousCloneGroupMarkNumS = slMarkListAcrossCloneGroup.get(0).substring(2);
			int previousCloneGroupMarkNum;
			if (previousCloneGroupMarkNumS.isEmpty()) {
				previousCloneGroupMarkNum = 0;
			} else {
				previousCloneGroupMarkNum = Integer.parseInt(previousCloneGroupMarkNumS);
			}
			for (int i = 1; i < slMarkListAcrossCloneGroup.size(); i++) {
				String currentCloneGroupMarkNumS = slMarkListAcrossCloneGroup.get(i).substring(2);
				int currentCloneGroupMarkNum;
				if (currentCloneGroupMarkNumS.isEmpty()) {
					currentCloneGroupMarkNum = 0;
				} else {
					currentCloneGroupMarkNum = Integer.parseInt(currentCloneGroupMarkNumS);
				}
				if (currentCloneGroupMarkNum <= previousCloneGroupMarkNum) {
					return false;
				}
				previousCloneGroupMarkNum = currentCloneGroupMarkNum;
			}
		}
		return true;
	}

	private boolean isIncreasingSdlMarkList(List<String> sdlMarkListWithinCloneGroup) {
		if (sdlMarkListWithinCloneGroup.size() > 1) {
			String previousCloneMarkNumS = sdlMarkListWithinCloneGroup.get(0).substring(3);
			int previousCloneMarkNum;
			if (previousCloneMarkNumS.isEmpty()) {
				previousCloneMarkNum = 0;
			} else {
				previousCloneMarkNum = Integer.parseInt(previousCloneMarkNumS);
			}
			for (int i = 1; i < sdlMarkListWithinCloneGroup.size(); i++) {
				String currentCloneMarkNumS = sdlMarkListWithinCloneGroup.get(i).substring(3);
				int currentCloneMarkNum;
				if (currentCloneMarkNumS.isEmpty()) {
					currentCloneMarkNum = 0;
				} else {
					currentCloneMarkNum = Integer.parseInt(currentCloneMarkNumS);
				}
				if (currentCloneMarkNum < previousCloneMarkNum) {
					return false;
				} // Equality is allowed
				previousCloneMarkNum = currentCloneMarkNum;
			}
		}
		return true;
	}

//	Clone previousClone = new Clone();
	Clone currentClone;
	boolean isSlClone = false;
	boolean isSdlClone = false;

	public void enterStemlineClone(KaryotypeParser.StemlineCloneContext ctx) {
		currentClone = new Clone();
	}

	public void exitStemlineClone(KaryotypeParser.StemlineCloneContext ctx) {
		currentClone.setCloneCode(ctx.getText());
		rowClones.add(currentClone);
//		previousClone.setCloneInput(currentClone.getCloneInput());
		cloneIndex++;
	}

	public void enterAdditionalStemlineClone(KaryotypeParser.AdditionalStemlineCloneContext ctx) {
		currentClone = new Clone();
	}

	public void exitAdditionalStemlineClone(KaryotypeParser.AdditionalStemlineCloneContext ctx) {
		currentClone.setCloneCode(ctx.getText().substring(1));
		rowClones.add(currentClone);
//		previousClone.setCloneInput(currentClone.getCloneInput());
		cloneIndex++;
	}

	public void enterSidelineCloneTypeI(KaryotypeParser.SidelineCloneTypeIContext ctx) {
		isSlClone = true;
		currentClone = new Clone();
	}

	public void exitSidelineCloneTypeI(KaryotypeParser.SidelineCloneTypeIContext ctx) {
		currentClone.setCloneCode(ctx.getText().substring(1));
		rowClones.add(currentClone);
//		previousClone.setCloneInput(currentClone.getCloneInput());
		isSlClone = false;
		cloneIndex++;
	}

	public void enterSidelineCloneTypeII(KaryotypeParser.SidelineCloneTypeIIContext ctx) {
		isSdlClone = true;
		currentClone = new Clone();
	}

	public void exitSidelineCloneTypeII(KaryotypeParser.SidelineCloneTypeIIContext ctx) {
		currentClone.setCloneCode(ctx.getText().substring(1));
		rowClones.add(currentClone);
//		previousClone.setCloneInput(currentClone.getCloneInput());
		isSdlClone = false;
		cloneIndex++;
	}

	public void enterAdditionalClone(KaryotypeParser.AdditionalCloneContext ctx) {
		currentClone = new Clone();
	}

	public void exitAdditionalClone(KaryotypeParser.AdditionalCloneContext ctx) {
		currentClone.setCloneCode(ctx.getText().substring(1));
		rowClones.add(currentClone);
		cloneIndex++;
	}

	private int searchInPreviousClone(String searchedS, boolean isDerBeingCancelled) {
		Clone previousCloneSearched = rowClones.get(currentClone.getRelatedClone());
		for (int i = 0; i < previousCloneSearched.getCloneInput().size(); i++) {
			Event e = previousCloneSearched.getCloneInput().get(i);
			if (isDerBeingCancelled) {
				if (!e.isUncertainEvent() && e.getEventCode().toLowerCase().contains(searchedS.toLowerCase())) {
					return i;
				}
			} else {
				if (!e.isUncertainEvent() && e.getEventCode().toLowerCase().contains(searchedS.toLowerCase())
						&& !(e instanceof DerEvent)) {
					return i;
				}
			}
		}
		return -1;
	}

	public void exitCellNumContent(KaryotypeParser.CellNumContentContext ctx) {
		int i = Integer.parseInt(ctx.integer().getText());
		if (isFirstClone) {
			firstClone.setCellNumber(i);
		} else if (isClone) {
			clone.setCellNumber(i);
		} else if (isNonclonalClone) {
			nonclonalClone.setCellNumber(i);
		} else {
			currentClone.setCellNumber(i);
		}
	}

	boolean isNonclonalClone;
	Clone nonclonalClone;

	public void enterNonclonalClone(KaryotypeParser.NonclonalCloneContext ctx) {
		nonclonalClone = new Clone();
		isNonclonalClone = true;
	}

	public void exitNonclonalClone(KaryotypeParser.NonclonalCloneContext ctx) {
		nonclonalClone.setCloneCode(ctx.getText().substring(1));
		rowClones.add(nonclonalClone);
		isNonclonalClone = false;
	}

	int cancelledCopies;
	
	public void enterAberrationEvent(KaryotypeParser.AberrationEventContext ctx) {
		cancelledCopies = 1;
	}
	
}
