package validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import business.DerEvent;
import business.Event;
import toolkit.ChrComparator;

/**
 * @author Lin Zhang
 * Programmer / Software Development Engineer
 * Institute for Informatics
 * Washington University School of Medicine in St. Louis
 * 
 * Date: August 30, 2021 
 */
public class DicDerivativeValidationDifferentDerChrNoBreakpoints extends DerivativeValidation {
	
	String derChr0, derChr1;
	List<List<String>> derChr0GainedSegments = new ArrayList<>();
	List<List<String>> derChr1GainedSegments = new ArrayList<>();
	
	public DicDerivativeValidationDifferentDerChrNoBreakpoints() {}
	public DicDerivativeValidationDifferentDerChrNoBreakpoints(DerEvent e) {		
		derChr0 = e.getChrList().get(0);
		derChr1 = e.getChrList().get(1);
		commonMarkSet.add("M0");
		markToChrMap.put("M0", derChr0);
		commonMarkSet.add("M1");
		markToChrMap.put("M1", derChr1);
		markSpecificLossGain.put("M0", initializeChrLossGain(derChr0));
		markSpecificLossGain.put("M1", initializeChrLossGain(derChr1));
		subevents = e.getSubevents();
		for (Event subevent: subevents) {
			eventIndexList.add(subeventIndex++);
			subeventNatureList.add(subevent.getNature());
			subeventChrList.add(subevent.getChrList());
		}
		int i = 0;
    	for (String[] chrArmArray: chrArmArrays) {
        	for(String chrBand: chrArmArray) {
        		indexToChrMap.put(i, chrBand);
        		chrToIndexMap.put(chrBand, i);
        		i++;        		        		
        	}
        }
    	karyotypeLossOutcome = new ArrayList<>(Collections.nCopies(indexToChrMap.size(), 0));
    	karyotypeGainOutcome = new ArrayList<>(karyotypeLossOutcome);
    	karyotypeFusionOutcome = new ArrayList<>(karyotypeLossOutcome);
	}
	
	
	public DerivativeValidationOutcome getDicDerivativeValidationOutcome() {
		
		boolean validDerivativeChr = true;
		List<String> derChrList = Arrays.asList(derChr0, derChr1);
		int firstPrimaryTEventIndex = getFirstPrimaryTEventIndex(eventIndexList, subeventNatureList, subeventChrList, derChrList);
		int reverseOrderEnd = 0;
		boolean reverseOrder = false;
		outer:
		for (int i: eventIndexList) {
			Event subevent = subevents.get(i);
			if (!subevent.isUncertainEvent()) {
				List<List<String>> breakpointsFullName = subevent.getBreakpointsFullName(subevent.getChrList(), subevent.getBreakpoints());
				switch (subevent.getNature()) {
					case "add": {
						if (!isValidAdd(breakpointsFullName, derChr0, derChr1, eventIndexList, i)) {
							if (i < firstPrimaryTEventIndex) {
								reverseOrder = true;
								reverseOrderEnd = i;
							}
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "del": {
						if (!isValidDel(breakpointsFullName, derChr0, derChr1, eventIndexList, i)) {
							if (i < firstPrimaryTEventIndex) {
								reverseOrder = true;
								reverseOrderEnd = i;
							}
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "dup": {
						if (!isValidDup(breakpointsFullName, derChr0, derChr1, eventIndexList, i)) {
							if (i < firstPrimaryTEventIndex) {
								reverseOrder = true;
								reverseOrderEnd = i;
							}
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "trp": {
						if (!isValidTrp(breakpointsFullName, derChr0, derChr1, eventIndexList, i)) {
							if (i < firstPrimaryTEventIndex) {
								reverseOrder = true;
								reverseOrderEnd = i;
							}
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "qdp": {
						if (!isValidQdp(breakpointsFullName, derChr0, derChr1, eventIndexList, i)) {
							if (i < firstPrimaryTEventIndex) {
								reverseOrder = true;
								reverseOrderEnd = i;
							}
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "ins": {
						if (!isValidIns(breakpointsFullName, derChr0, derChr1, eventIndexList, i)) {
							if (i < firstPrimaryTEventIndex) {
								reverseOrder = true;
								reverseOrderEnd = i;
							}
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "inv": {
						if (!isValidInv(breakpointsFullName, derChr0, derChr1, eventIndexList, i)) {
							if (i < firstPrimaryTEventIndex) {
								reverseOrder = true;
								reverseOrderEnd = i;
							}
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "hsr": {
						if (!isValidHsr(breakpointsFullName, derChr0, derChr1, eventIndexList, i)) {
							if (i < firstPrimaryTEventIndex) {
								reverseOrder = true;
								reverseOrderEnd = i;
							}
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "t": {
						if (!isValidT(breakpointsFullName, derChr0, derChr1, eventIndexList, i)) {
							if (i < firstPrimaryTEventIndex) {
								reverseOrder = true;
								reverseOrderEnd = i;
							}
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
				}
			}
		}
        if (reverseOrder) {

			if (eventIndexList.size() > firstPrimaryTEventIndex + 1 && !subeventChrList.get(firstPrimaryTEventIndex + 1).contains(derChr0) && !subeventChrList.get(firstPrimaryTEventIndex + 1).contains(derChr1)) {
				return new DerivativeValidationOutcome(false, segments, null, null);
			}
			
			List<Integer> correctParsingOrder = new ArrayList<>();
			correctParsingOrder.addAll(eventIndexList);
			Collections.reverse(correctParsingOrder.subList(reverseOrderEnd, firstPrimaryTEventIndex + 1));
    		
    		rawStringList = new ArrayList<>();
    		segments = new ArrayList<>();
    		insertedPlaceListMap = new HashMap<>();
    		hsrPlaceListMap = new HashMap<>();
    		parsingOrderMarksMap = new HashMap<>();
    		commonMarkSet = new HashSet<>();
    		homologousMarkSet = new HashSet<>();
    		markToChrMap = new HashMap<>();
    		commonMarkSet.add("M0");
    		markToChrMap.put("M0", derChr0);
    		commonMarkSet.add("M1");
    		markToChrMap.put("M1", derChr1);
    		markSpecificLossGain = new HashMap<>();
    		markSpecificLossGain.put("M0", initializeChrLossGain(derChr0));
    		markSpecificLossGain.put("M1", initializeChrLossGain(derChr1));
    		derChr0GainedSegments = new ArrayList<>();
    		derChr1GainedSegments = new ArrayList<>();
    		karyotypeLossOutcome = new ArrayList<>(Collections.nCopies(indexToChrMap.size(), 0));
        	karyotypeGainOutcome = new ArrayList<>(karyotypeLossOutcome);
        	karyotypeFusionOutcome = new ArrayList<>(karyotypeLossOutcome);
        	
        	validDerivativeChr = true;
        	
        	outer2:
    		for (int i: eventIndexList) {
				Event subevent = subevents.get(correctParsingOrder.get(i));
				if (!subevent.isUncertainEvent()) {
					List<List<String>> breakpointsFullName = subevent.getBreakpointsFullName(subevent.getChrList(), subevent.getBreakpoints());
					switch (subevent.getNature()) {
						case "add": {							
							if (!isValidAdd(breakpointsFullName, derChr0, derChr1, correctParsingOrder, i)) {							
								validDerivativeChr = false;
								break outer2;
							}
							break;
						}
						case "del": {							
							if (!isValidDel(breakpointsFullName, derChr0, derChr1, correctParsingOrder, i)) {							
								validDerivativeChr = false;
								break outer2;
							}
							break;
						}
						case "dup": {							
							if (!isValidDup(breakpointsFullName, derChr0, derChr1, correctParsingOrder, i)) {							
								validDerivativeChr = false;
								break outer2;
							}
							break;
						}
						case "trp": {							
							if (!isValidTrp(breakpointsFullName, derChr0, derChr1, correctParsingOrder, i)) {							
								validDerivativeChr = false;
								break outer2;
							}
							break;
						}
						case "qdp": {							
							if (!isValidQdp(breakpointsFullName, derChr0, derChr1, correctParsingOrder, i)) {							
								validDerivativeChr = false;
								break outer2;
							}
							break;
						}
						case "ins": {							
							if (!isValidIns(breakpointsFullName, derChr0, derChr1, correctParsingOrder, i)) {							
								validDerivativeChr = false;
								break outer2;
							}
							break;
						}
						case "inv": {							
							if (!isValidInv(breakpointsFullName, derChr0, derChr1, correctParsingOrder, i)) {							
								validDerivativeChr = false;
								break outer2;
							}
							break;
						}
						case "hsr": {							
							if (!isValidHsr(breakpointsFullName, derChr0, derChr1, correctParsingOrder, i)) {							
								validDerivativeChr = false;
								break outer2;
							}
							break;
						}
						case "t": {
							if (!isValidT(breakpointsFullName, derChr0, derChr1, correctParsingOrder, i)) {							
								validDerivativeChr = false;
								break outer2;
							}
							break;
						}
					}
				}
			} 
        
        }
               
    	// Examine whether both centromeres of derChr0 and derChr1 are contained in the deatiled system
    	int derCen0Segment = findSegmentContainingDerCen(derChr0, segments);
		int derCen1Segment = findSegmentContainingDerCen(derChr1, segments);
		if (derCen0Segment == -1 || derCen1Segment == -1) {
			validDerivativeChr = false;
		}
        
		if (validDerivativeChr) {
			return new DerivativeValidationOutcome(validDerivativeChr, segments, getDetailedSystem(), getDerKaryotypeLGF());
		} else {
			return new DerivativeValidationOutcome(validDerivativeChr, segments, null, null);
		}
	}
	
	protected void initializeSystem(String derChr, String mark) {
		rawStringList.add(derChr + "pter" + mark);
		rawStringList.add(derChr + "qter" + mark);
		segments.add(Arrays.asList(rawStringList.get(0), rawStringList.get(1)));
	}
	
	private void initializeSystemWithTwoChrs(String chrBreakpoint0, String chrBreakpoint1) {
		String chr0 = getChr(chrBreakpoint0);
		String chr1 = getChr(chrBreakpoint1);		
		String chrL = Stream.of(chr0, chr1).sorted(new ChrComparator()).findFirst().get();
		String chrBreakpointL = chrL.equals(chr0)? chrBreakpoint0 : chrBreakpoint1;
		String chrBreakpointR = chrBreakpointL.equals(chrBreakpoint0)? chrBreakpoint1: chrBreakpoint0;
		String markL = chrL.equals(derChr0) ? "M0" : "M1";
		String markR = markL.equals("M0") ? "M1" : "M0";
		String oppositeChrLArm = getOppositeChrArm(getChrArm(chrBreakpointL));
		String oppositeChrRArm = getOppositeChrArm(getChrArm(chrBreakpointR));
		rawStringList.add(oppositeChrLArm + "ter" + markL);
		rawStringList.add(chrBreakpointL + markL);
		rawStringList.add(chrBreakpointR + markR);
		rawStringList.add(oppositeChrRArm + "ter" + markR);		
		segments = getSegments(rawStringList);		
	}
	
	protected boolean isMarkInSystem(String mark) {
		return rawStringList.stream().anyMatch(s -> s.endsWith(mark));
	}
	
	private boolean isValidAdd(List<List<String>> breakpointsFullName, String derChr0, String derChr1, List<Integer> correctParsingOrder, int currentParsingPlace) {
		String chrBreakpoint = breakpointsFullName.get(0).get(0);
		String chr = getChr(chrBreakpoint);
		
		String mark;
		if (currentParsingPlace == 0) {
			if (chr.equals(derChr0)) {
				mark = "M0";
				initializeSystem(derChr0, mark);
			} else if (chr.equals(derChr1)) {
				mark = "M1";
				initializeSystem(derChr1, mark);
			} else {
				return false;
			}
		} else {
			Event previousEvent = subevents.get(correctParsingOrder.get(currentParsingPlace - 1));
			List<String> previousChrList = previousEvent.getChrList();
			List<String> previousChrMarks = parsingOrderMarksMap.get(currentParsingPlace - 1);
			if (chr.equals(derChr0) || chr.equals(derChr1)) {
				boolean isDerChr0 = chr.equals(derChr0);
				mark = isDerChr0? "M0" : "M1";
				if (!isMarkInSystem(mark)) {
					return false;
				}
				if (findSegment(chrBreakpoint, getSegmentNumList(mark)) == -1) {
					int chrIndex = previousChrList.indexOf(chr);
					if (chrIndex == -1) {
						return false;
					}
					mark = previousChrMarks.get(chrIndex);
					if (!mark.startsWith("M")) {
						return false;
					}
				}				
			} else {				
				int chrIndex = previousChrList.indexOf(chr);
				if (chrIndex == -1) {
					return false;
				}
				mark = previousChrMarks.get(chrIndex);
				if (!mark.startsWith("M")) {
					return false;
				}
			}			 
		}
		
		List<List<String>> segmentsOfChr = getSegmentsOfChr(mark);
		if (isInMultipleSegments(chrBreakpoint, segmentsOfChr)) {
			return false;
		} else {
			int segmentNum = findSegment(chrBreakpoint, getSegmentNumList(mark));
			if (segmentNum == -1) {
				return false;
			} else {
				List<String> segment = segments.get(segmentNum);
				if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint, segment) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint, segment)) {
					return false;
				}
				List<List<String>> deletedSegments = new ArrayList<>();
				int derCen0Segment = findSegmentContainingDerCen(derChr0, segments);
				int derCen1Segment = findSegmentContainingDerCen(derChr1, segments);
				if (derCen0Segment == -1 && derCen1Segment == -1) {
					return false;
				} else if (derCen0Segment != -1 && derCen1Segment != -1) {
					int max = Arrays.asList(derCen0Segment, derCen1Segment).stream().mapToInt(i -> i).max().getAsInt(); // int max = Arrays.asList(derCen0Segment, derCen1Segment).stream().max(Comparator.comparingInt(i -> i)).get();
					int min = Arrays.asList(derCen0Segment, derCen1Segment).stream().mapToInt(i -> i).min().getAsInt();
					if (segmentNum < min) {
						addLHS(chrBreakpoint, mark, segmentNum, deletedSegments);
					} else if (segmentNum > max) {
						addRHS(chrBreakpoint, mark, segmentNum, deletedSegments);
					} else if (segmentNum == min) {
						if (getChrArm(chrBreakpoint).equals(getChrArm(segments.get(min).get(1)))) {
							return false;
						} else {
							addLHS(chrBreakpoint, mark, segmentNum, deletedSegments);
						}
					} else if (segmentNum == max) {
						if (getChrArm(chrBreakpoint).equals(getChrArm(segments.get(max).get(0)))) {
							return false;
						} else {
							addRHS(chrBreakpoint, mark, segmentNum, deletedSegments);
						}
					} else {
						return false; // We can do nothing when min < segmentNum && segmentNum < max 
					} 
				} else {
					int derCenSegment = derCen0Segment == -1 ? derCen1Segment : derCen0Segment;
					if (segmentNum < derCenSegment) {
						addLHS(chrBreakpoint, mark, segmentNum, deletedSegments);
					} else if (segmentNum > derCenSegment) {
						addRHS(chrBreakpoint, mark, segmentNum, deletedSegments);
					} else if (getChrArm(chrBreakpoint).equals(getChrArm(segments.get(derCenSegment).get(0)))) {
						addLHS(chrBreakpoint, mark, segmentNum, deletedSegments);
					} else {
						addRHS(chrBreakpoint, mark, segmentNum, deletedSegments);
					} // segmentNum == derCenSegment
				} // derCen0Segment == -1 || derCen1Segment == -1	
				// Record LGF
				recordLoss(deletedSegments, derChr0GainedSegments, derChr1GainedSegments);
			}
		}		
		parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(mark));
		segments = getSegments(rawStringList);
		return true;
	}
	
	private boolean isValidDel(List<List<String>> breakpointsFullName, String derChr0, String derChr1, List<Integer> correctParsingOrder, int currentParsingPlace) {
		String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
		String chr = getChr(chrBreakpoint0);
		boolean isTerminalDeletion = breakpointsFullName.get(0).size() == 1;
		String chrBreakpoint1 = isTerminalDeletion? "" : breakpointsFullName.get(0).get(1);
		
		String mark;
		if (currentParsingPlace == 0) {
			if (chr.equals(derChr0)) {
				mark = "M0";
				initializeSystem(derChr0, mark);
			} else if (chr.equals(derChr1)) {
				mark = "M1";
				initializeSystem(derChr1, mark);
			} else {
				return false;
			}
		} else {
			Event previousEvent = subevents.get(correctParsingOrder.get(currentParsingPlace - 1));
			List<String> previousChrList = previousEvent.getChrList();
			List<String> previousChrMarks = parsingOrderMarksMap.get(currentParsingPlace - 1);
			if (chr.equals(derChr0) || chr.equals(derChr1)) {
				boolean isDerChr0 = chr.equals(derChr0);
				mark = isDerChr0? "M0" : "M1";
				if (!isMarkInSystem(mark)) {
					return false;
				}
				
				boolean chrBreakpointNotFoundInMainMark = isTerminalDeletion?
						findSegment(chrBreakpoint0, getSegmentNumList(mark)) == -1 :
							findSegment(chrBreakpoint0, getSegmentNumList(mark)) == -1 || findSegment(chrBreakpoint1, getSegmentNumList(mark)) == -1;
				
				if (chrBreakpointNotFoundInMainMark) {
					int chrIndex = previousChrList.indexOf(chr);
					if (chrIndex == -1) {
						return false;
					}
					mark = previousChrMarks.get(chrIndex);
					if (!mark.startsWith("M")) {
						return false;
					}
				}				
								
			} else {				
				int chrIndex = previousChrList.indexOf(chr);
				if (chrIndex == -1) {
					return false;
				}
				mark = previousChrMarks.get(chrIndex);
				if (!mark.startsWith("M")) {
					return false;
				}
			}
		}
		
		List<List<String>> segmentsOfChr = getSegmentsOfChr(mark);
		List<Integer> segmentNumsOfMark = getSegmentNumList(mark);
		if (isTerminalDeletion) {
			if (isInMultipleSegments(chrBreakpoint0, segmentsOfChr)) {
				return false;
			} else {
				int segmentNum = findSegment(chrBreakpoint0, segmentNumsOfMark);
				if (segmentNum == -1) {
					return false;
				} else {
					List<String> segment = segments.get(segmentNum);
					if (isFinerThanExistingSegmentEndPoint(chrBreakpoint0, segment) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint0, segment)) {
						return false;
					}
					List<List<String>> deletedSegments = new ArrayList<>();
					int derCen0Segment = findSegmentContainingDerCen(derChr0, segments);
					int derCen1Segment = findSegmentContainingDerCen(derChr1, segments);
					if (derCen0Segment == -1 && derCen1Segment == -1) {
						return false;
					} else if (derCen0Segment != -1 && derCen1Segment != -1) {
						int max = Arrays.asList(derCen0Segment, derCen1Segment).stream().mapToInt(i -> i).max().getAsInt();
						int min = Arrays.asList(derCen0Segment, derCen1Segment).stream().mapToInt(i -> i).min().getAsInt();
						if (segmentNum < min) {
							delLHS(chrBreakpoint0, mark, segmentNum, deletedSegments);
						} else if (segmentNum > max) {
							delRHS(chrBreakpoint0, mark, segmentNum, deletedSegments);
						} else if (segmentNum == min) {
							if (getChrArm(chrBreakpoint0).equals(getChrArm(segments.get(min).get(1)))) {
								return false;
							} else {
								delLHS(chrBreakpoint0, mark, segmentNum, deletedSegments);
							}
						} else if (segmentNum == max) {
							if (getChrArm(chrBreakpoint0).equals(getChrArm(segments.get(max).get(0)))) {
								return false;
							} else {
								delRHS(chrBreakpoint0, mark, segmentNum, deletedSegments);
							}
						} else {
							return false; // We can do nothing when min < segmentNum && segmentNum < max 
						}
					} else {
						int derCenSegment = derCen0Segment == -1 ? derCen1Segment : derCen0Segment;
						if (segmentNum < derCenSegment) {
							delLHS(chrBreakpoint0, mark, segmentNum, deletedSegments);
						} else if (segmentNum > derCenSegment) {
							delRHS(chrBreakpoint0, mark, segmentNum, deletedSegments);
						} else if (getChrArm(chrBreakpoint0).equals(getChrArm(segments.get(derCenSegment).get(0)))) {
							delLHS(chrBreakpoint0, mark, segmentNum, deletedSegments);
						} else {
							delRHS(chrBreakpoint0, mark, segmentNum, deletedSegments);
						} // segmentNum == derCenSegment
					} // derCen0Segment == -1 || derCen1Segment == -1
					// Record LGF
					recordLoss(deletedSegments, derChr0GainedSegments, derChr1GainedSegments);
					parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(mark));
					segments = getSegments(rawStringList);
				}
			}
		} else {
			List<String> chrBreakpoints = Arrays.asList(chrBreakpoint0, chrBreakpoint1);
			int segmentNum0, segmentNum1;
			if (isInMultipleSegments(chrBreakpoint0, segmentsOfChr) || isInMultipleSegments(chrBreakpoint1, segmentsOfChr)) {
				segmentNum0 = getUniqueSegment(chrBreakpoint0, chrBreakpoint1, segmentsOfChr, segmentNumsOfMark);
				if (segmentNum0 == -1) {
					return false;
				}
				segmentNum1 = segmentNum0;
			} else {
				segmentNum0 = findSegment(chrBreakpoint0, segmentNumsOfMark);
    			segmentNum1 = findSegment(chrBreakpoint1, segmentNumsOfMark);
    			if (segmentNum0 == -1 || segmentNum1 == -1) {
    				return false;
    			}
    			List<String> segment0 = segments.get(segmentNum0);
    			if (chrBreakpoint0.equals(chrBreakpoint1)) {
    				if (isFinerThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint0, segment0)) {
						return false;
					}
    			} else {
    				if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint0, segment0)) {
						return false;
					}
					List<String> segment1 = segments.get(segmentNum1);
					if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint1, segment1) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint1, segment1)) {
						return false;
					}
    			}
			}
			if (segmentNum0 == segmentNum1 && !getChrArm(chrBreakpoint0).equals(getChrArm(chrBreakpoint1))) {
				return false;
			} 
			
			int derCen0Segment = findSegmentContainingDerCen(derChr0, segments);
			int derCen1Segment = findSegmentContainingDerCen(derChr1, segments);
			List<String> derChrCentromereBeforeDeletion; 
			if (derCen0Segment == -1 && derCen1Segment == -1) {
				return false;
			} else if (derCen0Segment != -1 && derCen1Segment != -1) {
				derChrCentromereBeforeDeletion = Arrays.asList(derChr0, derChr1);
			} else if (derCen0Segment != -1) {
				derChrCentromereBeforeDeletion = Arrays.asList(derChr0);
			} else {
				derChrCentromereBeforeDeletion = Arrays.asList(derChr1);
			}
			
			List<List<String>> deletedSegments = new ArrayList<>();
			if (segmentNum0 == segmentNum1) {
    			delInOneSegment(chrBreakpoints, segmentNum0, mark, deletedSegments);
    		} else if (segmentNum0 < segmentNum1) {
    			delInMoreThanOneSegment(chrBreakpoint0, chrBreakpoint1, segmentNum0, segmentNum1, mark, deletedSegments);	    			
    		} else {
    			delInMoreThanOneSegment(chrBreakpoint1, chrBreakpoint0, segmentNum1, segmentNum0, mark, deletedSegments);
    		}
			
			// Check whether derChr's cen has been deleted
			if (mark.equals("M0") || mark.equals("M1")) {
				for (String derChr: derChrCentromereBeforeDeletion) {
					if (findSegmentContainingDerCen(derChr, segments) == -1) {
						return false;
					}
				}
			}
			// Record LGF
			recordLoss(deletedSegments, derChr0GainedSegments, derChr1GainedSegments);
			parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(mark));
		}			
		return true;	
	}
	
	private boolean isValidDup(List<List<String>> breakpointsFullName, String derChr0, String derChr1, List<Integer> correctParsingOrder, int currentParsingPlace) {
		String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
		String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
		String chr = getChr(chrBreakpoint0);
		
		String mark;
		if (currentParsingPlace == 0) {
			if (chr.equals(derChr0)) {
				mark = "M0";
				initializeSystem(derChr0, mark);
			} else if (chr.equals(derChr1)) {
				mark = "M1";
				initializeSystem(derChr1, mark);
			} else {
				return false;
			}
		} else {
			Event previousEvent = subevents.get(correctParsingOrder.get(currentParsingPlace - 1));
			List<String> previousChrList = previousEvent.getChrList();
			List<String> previousChrMarks = parsingOrderMarksMap.get(currentParsingPlace - 1);
			if (chr.equals(derChr0) || chr.equals(derChr1)) {
				boolean isDerChr0 = chr.equals(derChr0);
				mark = isDerChr0? "M0" : "M1";
				if (!isMarkInSystem(mark)) {
					return false;
				}
				
				boolean chrBreakpointNotFoundInMainMark = findSegment(chrBreakpoint0, getSegmentNumList(mark)) == -1 || findSegment(chrBreakpoint1, getSegmentNumList(mark)) == -1;
				
				if (chrBreakpointNotFoundInMainMark) {
					int chrIndex = previousChrList.indexOf(chr);
					if (chrIndex == -1) {
						return false;
					}
					mark = previousChrMarks.get(chrIndex);
					if (!mark.startsWith("M")) {
						return false;
					}
				}				
								
			} else {				
				int chrIndex = previousChrList.indexOf(chr);
				if (chrIndex == -1) {
					return false;
				}
				mark = previousChrMarks.get(chrIndex);
				if (!mark.startsWith("M")) {
					return false;
				}
			}
		}
		List<List<String>> segmentsOfChr = getSegmentsOfChr(mark);
		List<Integer> segmentNumsOfMark = getSegmentNumList(mark);
		int segmentNum0, segmentNum1;
		if (isInMultipleSegments(chrBreakpoint0, segmentsOfChr) || isInMultipleSegments(chrBreakpoint1, segmentsOfChr)) {
			segmentNum0 = getUniqueSegment(chrBreakpoint0, chrBreakpoint1, segmentsOfChr, segmentNumsOfMark);
			if (segmentNum0 == -1) {
				return false;
			}
			// Check whether derChr's cen has been duplicated
			if ((mark.equals("M0") || mark.equals("M1")) && !getChrArm(chrBreakpoint0).equals(getChrArm(chrBreakpoint1))) {
				return false;
			}
			segmentNum1 = segmentNum0;
		} else {
			segmentNum0 = findSegment(chrBreakpoint0, segmentNumsOfMark);
			segmentNum1 = findSegment(chrBreakpoint1, segmentNumsOfMark);
			    			
			if (segmentNum0 == -1 || segmentNum1 == -1) {
				return false;
			} else {
				List<String> segment0 = segments.get(segmentNum0);
				List<String> segment1 = segments.get(segmentNum1);
			    if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint1, segment1) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint1, segment1)) {
			    	return false;
			    }	
			} 
			// Check whether derChr's cen has been duplicated
            if (mark.equals("M0") || mark.equals("M1")) {
				if (segmentNum0 == segmentNum1) {
					if (!getChrArm(chrBreakpoint0).equals(getChrArm(chrBreakpoint1))) {
	    				return false;
	    			}
				} else {
					int derCenSegment = findSegmentContainingDerCen(markToChrMap.get(mark), segments);
					if (segmentNum0 < segmentNum1) {
						if (isDerCenDuplicated(segmentNum0, segmentNum1, derCenSegment, chrBreakpoint0, chrBreakpoint1)) {
							return false;
						}
					} else {
						if (isDerCenDuplicated(segmentNum1, segmentNum0, derCenSegment, chrBreakpoint1, chrBreakpoint0)) {
							return false;
						}
					}
				} 
            }			
		}
		
		List<List<String>> duplicatedSegments = new ArrayList<>();
		
		if (chrBreakpoint0.equals(chrBreakpoint1)) {
			duplicateI(chrBreakpoint0, segmentNum0, mark, duplicatedSegments);
		} else if (segmentNum0 == segmentNum1) {
			duplicateII(chrBreakpoint0, chrBreakpoint1, derChr0, derChr1, segmentNum0, mark, segmentsOfChr, segmentNumsOfMark, duplicatedSegments);
		} else {
			duplicateIII(chrBreakpoint0, chrBreakpoint1, derChr0, derChr1, segmentNum0, segmentNum1, mark, segmentsOfChr, segmentNumsOfMark, duplicatedSegments);
		}
		
		parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(mark));
		recordDuplicate(duplicatedSegments, derChr0GainedSegments, derChr1GainedSegments);
				
		return true;
	}
	
	private int findFurtherLeftBreakpoint(String chrBreakpoint0, String chrBreakpoint1, String derChr0, String derChr1, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark) {
		int segmentNum0, segmentNum1;   		
		if (isInMultipleSegments(chrBreakpoint0, segmentsOfChr) || isInMultipleSegments(chrBreakpoint1, segmentsOfChr)) {
			// Then it must be in a unique segment containing both chrBreakpoints
			segmentNum0 = getUniqueSegment(chrBreakpoint0, chrBreakpoint1, segmentsOfChr, segmentNumsOfMark);
			segmentNum1 = segmentNum0;    			
		} else {
			segmentNum0 = findSegment(chrBreakpoint0, segmentNumsOfMark);
			segmentNum1 = findSegment(chrBreakpoint1, segmentNumsOfMark);    			
		}
		if (segmentNum0 == segmentNum1) {
			int derCen0Segment = findSegmentContainingDerCen(derChr0, segments);
			int derCen1Segment = findSegmentContainingDerCen(derChr1, segments);
			if (segmentNum0 == derCen0Segment || segmentNum0 == derCen1Segment) {
				// Find the orientation of this segment by examining whether it is to the left or right of derCen
				String segLeftArm = getChrArm(segments.get(segmentNum0).get(0));
				String chrBreakpointArm = getChrArm(chrBreakpoint0);				
				if (segLeftArm.equals(chrBreakpointArm)) {
					return chrBreakpoint0.compareTo(chrBreakpoint1) <= 0 ? 1 : 0;
				} else {
					return chrBreakpoint0.compareTo(chrBreakpoint1) <= 0 ? 0 : 1;
				}
			} else {
				// Find the orientation of this segment by comparing both ends of this segment
				List<String> segment = segments.get(segmentNum0);
				if (segment.get(0).compareTo(segment.get(1)) < 0) {
					return chrBreakpoint0.compareTo(chrBreakpoint1) <= 0 ? 0 : 1;
				} else {
					return chrBreakpoint0.compareTo(chrBreakpoint1) <= 0 ? 1 : 0;
				}
			}
		} else if (segmentNum0 < segmentNum1) {
			return 0;
		} else {
			return 1;
		}
	}
	
	private int getSegmentOfFurtherLeftBreakpoint(String chrBreakpoint0, String chrBreakpoint1, String derChr0, String derChr1, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark) {
		if (isInMultipleSegments(chrBreakpoint0, segmentsOfChr) || isInMultipleSegments(chrBreakpoint1, segmentsOfChr)) {
			// Then it must be in a unique segment containing both chrBreakpoints
			return getUniqueSegment(chrBreakpoint0, chrBreakpoint1, segmentsOfChr, segmentNumsOfMark);    			
		} else {
			int segmentNum0 = findSegment(chrBreakpoint0, segmentNumsOfMark);
			int segmentNum1 = findSegment(chrBreakpoint1, segmentNumsOfMark);
			return findFurtherLeftBreakpoint(chrBreakpoint0, chrBreakpoint1, derChr0, derChr1, segmentsOfChr, segmentNumsOfMark) == 0 ? segmentNum0 : segmentNum1 ;
		}
	}
	
	private int findFurtherLeftBreakpointOfDuplicationCopy(String chrBreakpoint0, String chrBreakpoint1, String derChr0, String derChr1, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark) {
		int leftBreakpointSegment = getSegmentOfFurtherLeftBreakpoint(chrBreakpoint0, chrBreakpoint1, derChr0, derChr1, segmentsOfChr, segmentNumsOfMark);
		int derCen0Segment = findSegmentContainingDerCen(derChr0, segments);
		int derCen1Segment = findSegmentContainingDerCen(derChr1, segments);
		if (derCen0Segment != -1 && derCen1Segment != -1) {
			int minDerCenSegment = Integer.min(derCen0Segment, derCen1Segment);
			int maxDerCenSegment = Integer.max(derCen0Segment, derCen1Segment);
			String minDerCenMark = minDerCenSegment == derCen0Segment ? "M0" : "M1";
			String maxDerCenMark = maxDerCenSegment == derCen0Segment ? "M0" : "M1";
			if (leftBreakpointSegment < minDerCenSegment) {
				return 1;
			} else if (leftBreakpointSegment == minDerCenSegment) {
				return getChrArm(segments.get(minDerCenSegment).get(0)).equals(getChrArm(chrBreakpoint0)) ? 1 : 0 ;
			} else if (leftBreakpointSegment > minDerCenSegment && leftBreakpointSegment < maxDerCenSegment) {
				int segmentNum0, segmentNum1;   		
				if (isInMultipleSegments(chrBreakpoint0, segmentsOfChr) || isInMultipleSegments(chrBreakpoint1, segmentsOfChr)) {
					segmentNum0 = getUniqueSegment(chrBreakpoint0, chrBreakpoint1, segmentsOfChr, segmentNumsOfMark);
					segmentNum1 = segmentNum0;    			
				} else {
					segmentNum0 = findSegment(chrBreakpoint0, segmentNumsOfMark);
					segmentNum1 = findSegment(chrBreakpoint1, segmentNumsOfMark);    			
				}
				int segmentNumL = Integer.min(segmentNum0, segmentNum1);
				int segmentNumR = Integer.max(segmentNum0, segmentNum1);
				List<Integer> segmentNumsOfMinDerCenMark = getSegmentNumList(minDerCenMark);
				List<Integer> segmentNumsOfMaxDerCenMark = getSegmentNumList(maxDerCenMark);
				if (segmentNumR <= segmentNumsOfMinDerCenMark.get(segmentNumsOfMinDerCenMark.size() - 1)) {
					return 0;
				} else if (segmentNumL >= segmentNumsOfMaxDerCenMark.get(0)) {
					return 1;
				} else {
					// We decide not to change the orientation for this case
					return findFurtherLeftBreakpoint(chrBreakpoint0, chrBreakpoint1, derChr0, derChr1, segmentsOfChr, segmentNumsOfMark);
				} 
			} else if (leftBreakpointSegment == maxDerCenSegment) {
				return getChrArm(segments.get(maxDerCenSegment).get(0)).equals(getChrArm(chrBreakpoint0)) ? 1 : 0 ;
			} else {
				return 0;
			}
		} else {
			int derCenSegment = derCen0Segment != -1 ? derCen0Segment : derCen1Segment;
			if (leftBreakpointSegment < derCenSegment) {
	    		return 1;
	    	} else if (leftBreakpointSegment > derCenSegment) {
	    		return 0;
	    	} else {
	    		return getChrArm(segments.get(derCenSegment).get(0)).equals(getChrArm(chrBreakpoint0)) ? 1 : 0 ;
	    	}
		} // derCen0Segment != -1 || derCen1Segment != -1 Note it's impossible to have both inequalities hold.
	}
	
	// This is for duplication which happens inside a segment.
	private void duplicateII(String chrBreakpoint0, String chrBreakpoint1, String derChr0, String derChr1, int segmentNum, String mark, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark, List<List<String>> duplicatedSegments) {
		int offset = getOffsetInRawStringList(segmentNum);
		List<String> segment = segments.get(segmentNum);
		
		int leftBreakpointN = findFurtherLeftBreakpoint(chrBreakpoint0, chrBreakpoint1, derChr0, derChr1, segmentsOfChr, segmentNumsOfMark);
		int leftBreakpointNDuplicationCopy = findFurtherLeftBreakpointOfDuplicationCopy(chrBreakpoint0, chrBreakpoint1, derChr0, derChr1, segmentsOfChr, segmentNumsOfMark);
		
		String leftBreakpoint, rightBreakpoint;
    	if (leftBreakpointN == 0) {
    		leftBreakpoint = chrBreakpoint0 + mark;
    		rightBreakpoint = chrBreakpoint1 + mark;
    	} else {
    		leftBreakpoint = chrBreakpoint1 + mark;
    		rightBreakpoint = chrBreakpoint0 + mark;
    	}
    	
		List<String> sourceList;
    	
    	// isDuplicationReverselyOriented when leftBreakpointN != leftBreakpointNDuplicationCopy
    	if (leftBreakpointN == leftBreakpointNDuplicationCopy) {    		
    		sourceList = Arrays.asList(rightBreakpoint, leftBreakpoint);
    		segments.add(segmentNum + 1, Arrays.asList(leftBreakpoint, segment.get(1)));
    		segments.get(segmentNum).set(1, rightBreakpoint);
    	} else {
    		sourceList = Arrays.asList(rightBreakpoint, rightBreakpoint, leftBreakpoint, rightBreakpoint);
    		segments.add(segmentNum + 1, Arrays.asList(rightBreakpoint, segment.get(1)));
    		segments.add(segmentNum + 1, Arrays.asList(rightBreakpoint, leftBreakpoint));
    		segments.get(segmentNum).set(1, rightBreakpoint);
    	}
    	
		rawStringList.addAll(offset, sourceList);
    	
    	// Recording LGF doesn't care about orientation 
    	duplicatedSegments.add(Arrays.asList(leftBreakpoint, rightBreakpoint));
		
	}
	
	// Two chrBreakpoints are located in different segments
	private void duplicateIII(String chrBreakpoint0, String chrBreakpoint1, String derChr0, String derChr1, int segmentNum0, int segmentNum1, String mark, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark, List<List<String>> duplicatedSegments) {
		int segmentNumL = Integer.min(segmentNum0, segmentNum1);
		int segmentNumR = Integer.max(segmentNum0, segmentNum1);
		int offset = getOffsetInRawStringList(segmentNumR);
		
		int leftBreakpointN = findFurtherLeftBreakpoint(chrBreakpoint0, chrBreakpoint1, derChr0, derChr1, segmentsOfChr, segmentNumsOfMark);
		int leftBreakpointNDuplicationCopy = findFurtherLeftBreakpointOfDuplicationCopy(chrBreakpoint0, chrBreakpoint1, derChr0, derChr1, segmentsOfChr, segmentNumsOfMark);
		
		String leftBreakpoint, rightBreakpoint;
    	if (leftBreakpointN == 0) {
    		leftBreakpoint = chrBreakpoint0 + mark;
    		rightBreakpoint = chrBreakpoint1 + mark;
    	} else {
    		leftBreakpoint = chrBreakpoint1 + mark;
    		rightBreakpoint = chrBreakpoint0 + mark;
    	} 
    	
    	List<String> sourceList = new ArrayList<>();
    	
    	// isDuplicationReverselyOriented when leftBreakpointN != leftBreakpointNDuplicationCopy
    	if (leftBreakpointN == leftBreakpointNDuplicationCopy) {
    		sourceList.add(rightBreakpoint);	
    		sourceList.add(leftBreakpoint);
    		sourceList.add(segments.get(segmentNumL).get(1));
    		for (int i = segmentNumL + 1; i < segmentNumR; i++) {
    			for (String s: segments.get(i)) {
    				sourceList.add(s);
    			}
    		}
    		sourceList.add(segments.get(segmentNumR).get(0));
    	} else {
    		sourceList.add(rightBreakpoint);
    		sourceList.add(rightBreakpoint);
    		List<String> invertedSublist = new ArrayList<>();
    		invertedSublist.add(segments.get(segmentNumL).get(1));
    		for (int i = segmentNumL + 1; i < segmentNumR; i++) {
    			for (String s: segments.get(i)) {
    				invertedSublist.add(s);
    			}
    		}
    		invertedSublist.add(segments.get(segmentNumR).get(0));
    		Collections.reverse(invertedSublist);
    		sourceList.addAll(invertedSublist);
    		sourceList.add(leftBreakpoint);
    		sourceList.add(rightBreakpoint);
    	}
    	
    	duplicatedSegments.add(Arrays.asList(leftBreakpoint, segments.get(segmentNumL).get(1)));
    	for (int i = segmentNumL + 1; i < segmentNumR; i++) {
    		duplicatedSegments.add(new ArrayList<String>(segments.get(i))); // We must use new instance here!
    	}
    	duplicatedSegments.add(Arrays.asList(segments.get(segmentNumR).get(0), rightBreakpoint));
    	
    	rawStringList.addAll(offset, sourceList);
    	segments = getSegments(rawStringList);
	}
	
	private boolean isValidTrp(List<List<String>> breakpointsFullName, String derChr0, String derChr1, List<Integer> correctParsingOrder, int currentParsingPlace) {
		String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
		String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
		String chr = getChr(chrBreakpoint0);
		
		String mark;
		if (currentParsingPlace == 0) {
			if (chr.equals(derChr0)) {
				mark = "M0";
				initializeSystem(derChr0, mark);
			} else if (chr.equals(derChr1)) {
				mark = "M1";
				initializeSystem(derChr1, mark);
			} else {
				return false;
			}
		} else {
			Event previousEvent = subevents.get(correctParsingOrder.get(currentParsingPlace - 1));
			List<String> previousChrList = previousEvent.getChrList();
			List<String> previousChrMarks = parsingOrderMarksMap.get(currentParsingPlace - 1);
			if (chr.equals(derChr0) || chr.equals(derChr1)) {
				boolean isDerChr0 = chr.equals(derChr0);
				mark = isDerChr0? "M0" : "M1";
				if (!isMarkInSystem(mark)) {
					return false;
				}
				
				boolean chrBreakpointNotFoundInMainMark = findSegment(chrBreakpoint0, getSegmentNumList(mark)) == -1 || findSegment(chrBreakpoint1, getSegmentNumList(mark)) == -1;
				
				if (chrBreakpointNotFoundInMainMark) {
					int chrIndex = previousChrList.indexOf(chr);
					if (chrIndex == -1) {
						return false;
					}
					mark = previousChrMarks.get(chrIndex);
					if (!mark.startsWith("M")) {
						return false;
					}
				}				
								
			} else {				
				int chrIndex = previousChrList.indexOf(chr);
				if (chrIndex == -1) {
					return false;
				}
				mark = previousChrMarks.get(chrIndex);
				if (!mark.startsWith("M")) {
					return false;
				}
			}
		}
		List<List<String>> segmentsOfChr = getSegmentsOfChr(mark);
		List<Integer> segmentNumsOfMark = getSegmentNumList(mark);
		int segmentNum0, segmentNum1;
		if (isInMultipleSegments(chrBreakpoint0, segmentsOfChr) || isInMultipleSegments(chrBreakpoint1, segmentsOfChr)) {
			segmentNum0 = getUniqueSegment(chrBreakpoint0, chrBreakpoint1, segmentsOfChr, segmentNumsOfMark);
			if (segmentNum0 == -1) {
				return false;
			}
			// Check whether derChr's cen has been duplicated
			if ((mark.equals("M0") || mark.equals("M1")) && !getChrArm(chrBreakpoint0).equals(getChrArm(chrBreakpoint1))) {
				return false;
			}
			segmentNum1 = segmentNum0;
		} else {
			segmentNum0 = findSegment(chrBreakpoint0, segmentNumsOfMark);
			segmentNum1 = findSegment(chrBreakpoint1, segmentNumsOfMark);
			    			
			if (segmentNum0 == -1 || segmentNum1 == -1) {
				return false;
			} else {
				List<String> segment0 = segments.get(segmentNum0);
				List<String> segment1 = segments.get(segmentNum1);
			    if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint1, segment1) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint1, segment1)) {
			    	return false;
			    }	
			} 
			// Check whether derChr's cen has been duplicated
            if (mark.equals("M0") || mark.equals("M1")) {
				if (segmentNum0 == segmentNum1) {
					if (!getChrArm(chrBreakpoint0).equals(getChrArm(chrBreakpoint1))) {
	    				return false;
	    			}
				} else {
					int derCenSegment = findSegmentContainingDerCen(markToChrMap.get(mark), segments);
					if (segmentNum0 < segmentNum1) {
						if (isDerCenDuplicated(segmentNum0, segmentNum1, derCenSegment, chrBreakpoint0, chrBreakpoint1)) {
							return false;
						}
					} else {
						if (isDerCenDuplicated(segmentNum1, segmentNum0, derCenSegment, chrBreakpoint1, chrBreakpoint0)) {
							return false;
						}
					}
				} 
            }			
		}
		
		List<List<String>> duplicatedSegments = new ArrayList<>();
		
		if (chrBreakpoint0.equals(chrBreakpoint1)) {
			triplicateI(chrBreakpoint0, segmentNum0, mark, duplicatedSegments);
		} else if (segmentNum0 == segmentNum1) {
			triplicateII(chrBreakpoint0, chrBreakpoint1, derChr0, derChr1, segmentNum0, mark, segmentsOfChr, segmentNumsOfMark, duplicatedSegments);
		} else {
			triplicateIII(chrBreakpoint0, chrBreakpoint1, derChr0, derChr1, segmentNum0, segmentNum1, mark, segmentsOfChr, segmentNumsOfMark, duplicatedSegments);
		}
		
		parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(mark));
		recordDuplicate(duplicatedSegments, derChr0GainedSegments, derChr1GainedSegments);
				
		return true;
	}
	
	// This is for triplication which happens inside a segment. 
    private void triplicateII(String chrBreakpoint0, String chrBreakpoint1, String derChr0, String derChr1, int segmentNum, String mark, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark, List<List<String>> duplicatedSegments) {
    	int offset = getOffsetInRawStringList(segmentNum);
    	List<String> segment = segments.get(segmentNum);
    	
    	int leftBreakpointN = findFurtherLeftBreakpoint(chrBreakpoint0, chrBreakpoint1, derChr0, derChr1, segmentsOfChr, segmentNumsOfMark);
    	int leftBreakpointNDuplicationCopy = findFurtherLeftBreakpointOfDuplicationCopy(chrBreakpoint0, chrBreakpoint1, derChr0, derChr1, segmentsOfChr, segmentNumsOfMark);
    		    	
    	String leftBreakpoint, rightBreakpoint;
    	if (leftBreakpointN == 0) {
    		leftBreakpoint = chrBreakpoint0 + mark;
    		rightBreakpoint = chrBreakpoint1 + mark;
    	} else {
    		leftBreakpoint = chrBreakpoint1 + mark;
    		rightBreakpoint = chrBreakpoint0 + mark;
    	} 
    	
    	List<String> sourceList;
    	// isDuplicationReverselyOriented when leftBreakpointN != leftBreakpointNDuplicationCopy
    	if (leftBreakpointN == leftBreakpointNDuplicationCopy) {
    		
    		sourceList = Arrays.asList(rightBreakpoint, leftBreakpoint, rightBreakpoint, leftBreakpoint);
    		
			String leftend = segment.get(0); // String is immutable; even if in the future segment.get(0) will be changed, leftend will be locked in its current value. That's why I don't need to use String leftend = new String(segment.get(0))      		
    		segments.get(segmentNum).set(0, leftBreakpoint);
    		segments.add(segmentNum, Arrays.asList(leftBreakpoint, rightBreakpoint));
    		segments.add(segmentNum, Arrays.asList(leftend, rightBreakpoint));
    	} else {
    		sourceList = Arrays.asList(rightBreakpoint, rightBreakpoint, leftBreakpoint, leftBreakpoint);
    		
			String leftend = segment.get(0); // String is immutable; even if in the future segment.get(0) will be changed, leftend will be locked in its current value. That's why I don't need to use String leftend = new String(segment.get(0))      		
    		segments.get(segmentNum).set(0, leftBreakpoint);
    		segments.add(segmentNum, Arrays.asList(rightBreakpoint, leftBreakpoint));
    		segments.add(segmentNum, Arrays.asList(leftend, rightBreakpoint));
    		
    	}
    	
    	rawStringList.addAll(offset, sourceList);
    	
    	// Recording LGF doesn't care about orientation 
    	duplicatedSegments.addAll(Collections.nCopies(2, Arrays.asList(leftBreakpoint, rightBreakpoint)));

    }
    
    // Two chrBreakpoints are located in different segments
    private void triplicateIII(String chrBreakpoint0, String chrBreakpoint1, String derChr0, String derChr1, int segmentNum0, int segmentNum1, String mark, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark, List<List<String>> duplicatedSegments) {
    	
    	int segmentNumL = Integer.min(segmentNum0, segmentNum1);
		int segmentNumR = Integer.max(segmentNum0, segmentNum1);
    	int offset = getOffsetInRawStringList(segmentNumR);
    	
    	int leftBreakpointN = findFurtherLeftBreakpoint(chrBreakpoint0, chrBreakpoint1, derChr0, derChr1, segmentsOfChr, segmentNumsOfMark);
    	int leftBreakpointNDuplicationCopy = findFurtherLeftBreakpointOfDuplicationCopy(chrBreakpoint0, chrBreakpoint1, derChr0, derChr1, segmentsOfChr, segmentNumsOfMark);
    		    	
    	String leftBreakpoint, rightBreakpoint;
    	if (leftBreakpointN == 0) {
    		leftBreakpoint = chrBreakpoint0 + mark;
    		rightBreakpoint = chrBreakpoint1 + mark;
    	} else {
    		leftBreakpoint = chrBreakpoint1 + mark;
    		rightBreakpoint = chrBreakpoint0 + mark;
    	} 
    	
    	List<String> sourceList = new ArrayList<>();
    	
    	// isDuplicationReverselyOriented when leftBreakpointN != leftBreakpointNDuplicationCopy
    	if (leftBreakpointN == leftBreakpointNDuplicationCopy) {
    		sourceList.add(rightBreakpoint);
    		sourceList.add(leftBreakpoint);
    		List<String> duplicatedSublist = new ArrayList<>();
    		duplicatedSublist.add(segments.get(segmentNumL).get(1));
    		for (int i = segmentNumL + 1; i < segmentNumR; i++) {
    			for (String s: segments.get(i)) {
    				duplicatedSublist.add(s);
    			}
    		}
    		duplicatedSublist.add(segments.get(segmentNumR).get(0));
    		sourceList.addAll(duplicatedSublist);
    		sourceList.add(rightBreakpoint);
    		sourceList.add(leftBreakpoint);
    		sourceList.addAll(duplicatedSublist);
    	} else {
    		sourceList.add(rightBreakpoint);
    		sourceList.add(rightBreakpoint);
    		List<String> invertedSublist = new ArrayList<>();
    		invertedSublist.add(segments.get(segmentNumL).get(1));
    		for (int i = segmentNumL + 1; i < segmentNumR; i++) {
    			for (String s: segments.get(i)) {
    				invertedSublist.add(s);
    			}
    		}
    		invertedSublist.add(segments.get(segmentNumR).get(0));
    		Collections.reverse(invertedSublist);
    		sourceList.addAll(invertedSublist);
    		sourceList.add(leftBreakpoint);
    		sourceList.add(leftBreakpoint);
    		Collections.reverse(invertedSublist);
    		sourceList.addAll(invertedSublist);
    	}
    	
    	duplicatedSegments.addAll(Collections.nCopies(2, Arrays.asList(leftBreakpoint, segments.get(segmentNumL).get(1))));
    	for (int i = segmentNumL + 1; i < segmentNumR; i++) {
    		duplicatedSegments.addAll(Collections.nCopies(2, new ArrayList<String>(segments.get(i)))); // We must use new instance here!
    	}
    	duplicatedSegments.addAll(Collections.nCopies(2, Arrays.asList(segments.get(segmentNumR).get(0), rightBreakpoint)));
    	
    	rawStringList.addAll(offset, sourceList);
    	segments = getSegments(rawStringList);
    }
    
    private boolean isValidQdp(List<List<String>> breakpointsFullName, String derChr0, String derChr1, List<Integer> correctParsingOrder, int currentParsingPlace) {
		String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
		String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
		String chr = getChr(chrBreakpoint0);
		
		String mark;
		if (currentParsingPlace == 0) {
			if (chr.equals(derChr0)) {
				mark = "M0";
				initializeSystem(derChr0, mark);
			} else if (chr.equals(derChr1)) {
				mark = "M1";
				initializeSystem(derChr1, mark);
			} else {
				return false;
			}
		} else {
			Event previousEvent = subevents.get(correctParsingOrder.get(currentParsingPlace - 1));
			List<String> previousChrList = previousEvent.getChrList();
			List<String> previousChrMarks = parsingOrderMarksMap.get(currentParsingPlace - 1);
			if (chr.equals(derChr0) || chr.equals(derChr1)) {
				boolean isDerChr0 = chr.equals(derChr0);
				mark = isDerChr0? "M0" : "M1";
				if (!isMarkInSystem(mark)) {
					return false;
				}
				
				boolean chrBreakpointNotFoundInMainMark = findSegment(chrBreakpoint0, getSegmentNumList(mark)) == -1 || findSegment(chrBreakpoint1, getSegmentNumList(mark)) == -1;
				
				if (chrBreakpointNotFoundInMainMark) {
					int chrIndex = previousChrList.indexOf(chr);
					if (chrIndex == -1) {
						return false;
					}
					mark = previousChrMarks.get(chrIndex);
					if (!mark.startsWith("M")) {
						return false;
					}
				}				
								
			} else {				
				int chrIndex = previousChrList.indexOf(chr);
				if (chrIndex == -1) {
					return false;
				}
				mark = previousChrMarks.get(chrIndex);
				if (!mark.startsWith("M")) {
					return false;
				}
			}
		}
		List<List<String>> segmentsOfChr = getSegmentsOfChr(mark);
		List<Integer> segmentNumsOfMark = getSegmentNumList(mark);
		int segmentNum0, segmentNum1;
		if (isInMultipleSegments(chrBreakpoint0, segmentsOfChr) || isInMultipleSegments(chrBreakpoint1, segmentsOfChr)) {
			segmentNum0 = getUniqueSegment(chrBreakpoint0, chrBreakpoint1, segmentsOfChr, segmentNumsOfMark);
			if (segmentNum0 == -1) {
				return false;
			}
			// Check whether derChr's cen has been duplicated
			if ((mark.equals("M0") || mark.equals("M1")) && !getChrArm(chrBreakpoint0).equals(getChrArm(chrBreakpoint1))) {
				return false;
			}
			segmentNum1 = segmentNum0;
		} else {
			segmentNum0 = findSegment(chrBreakpoint0, segmentNumsOfMark);
			segmentNum1 = findSegment(chrBreakpoint1, segmentNumsOfMark);
			    			
			if (segmentNum0 == -1 || segmentNum1 == -1) {
				return false;
			} else {
				List<String> segment0 = segments.get(segmentNum0);
				List<String> segment1 = segments.get(segmentNum1);
			    if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint1, segment1) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint1, segment1)) {
			    	return false;
			    }	
			} 
			// Check whether derChr's cen has been duplicated
            if (mark.equals("M0") || mark.equals("M1")) {
				if (segmentNum0 == segmentNum1) {
					if (!getChrArm(chrBreakpoint0).equals(getChrArm(chrBreakpoint1))) {
	    				return false;
	    			}
				} else {
					int derCenSegment = findSegmentContainingDerCen(markToChrMap.get(mark), segments);
					if (segmentNum0 < segmentNum1) {
						if (isDerCenDuplicated(segmentNum0, segmentNum1, derCenSegment, chrBreakpoint0, chrBreakpoint1)) {
							return false;
						}
					} else {
						if (isDerCenDuplicated(segmentNum1, segmentNum0, derCenSegment, chrBreakpoint1, chrBreakpoint0)) {
							return false;
						}
					}
				} 
            }			
		}
		
		List<List<String>> duplicatedSegments = new ArrayList<>();
		
		if (chrBreakpoint0.equals(chrBreakpoint1)) {
			quadruplicateI(chrBreakpoint0, segmentNum0, mark, duplicatedSegments);
		} else if (segmentNum0 == segmentNum1) {
			quadruplicateII(chrBreakpoint0, chrBreakpoint1, derChr0, derChr1, segmentNum0, mark, segmentsOfChr, segmentNumsOfMark, duplicatedSegments);
		} else {
			quadruplicateIII(chrBreakpoint0, chrBreakpoint1, derChr0, derChr1, segmentNum0, segmentNum1, mark, segmentsOfChr, segmentNumsOfMark, duplicatedSegments);
		}
		
		parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(mark));
		recordDuplicate(duplicatedSegments, derChr0GainedSegments, derChr1GainedSegments);
				
		return true;
	}
    
    // This is for quadruplication which happens inside a segment. 
    private void quadruplicateII(String chrBreakpoint0, String chrBreakpoint1, String derChr0, String derChr1, int segmentNum, String mark, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark, List<List<String>> duplicatedSegments) {
    	int offset = getOffsetInRawStringList(segmentNum);
    	List<String> segment = segments.get(segmentNum);
    	
    	int leftBreakpointN = findFurtherLeftBreakpoint(chrBreakpoint0, chrBreakpoint1, derChr0, derChr1, segmentsOfChr, segmentNumsOfMark);
    	int leftBreakpointNDuplicationCopy = findFurtherLeftBreakpointOfDuplicationCopy(chrBreakpoint0, chrBreakpoint1, derChr0, derChr1, segmentsOfChr, segmentNumsOfMark);
    		    	
    	String leftBreakpoint, rightBreakpoint;
    	if (leftBreakpointN == 0) {
    		leftBreakpoint = chrBreakpoint0 + mark;
    		rightBreakpoint = chrBreakpoint1 + mark;
    	} else {
    		leftBreakpoint = chrBreakpoint1 + mark;
    		rightBreakpoint = chrBreakpoint0 + mark;
    	} 
    	
    	List<String> sourceList;
    	// isDuplicationReverselyOriented when leftBreakpointN != leftBreakpointNDuplicationCopy
    	if (leftBreakpointN == leftBreakpointNDuplicationCopy) {    		
    		sourceList = Arrays.asList(rightBreakpoint, leftBreakpoint, rightBreakpoint, leftBreakpoint, rightBreakpoint, leftBreakpoint);
			String leftend = segment.get(0); // String is immutable; even if in the future segment.get(0) will be changed, leftend will be locked in its current value. That's why I don't need to use String leftend = new String(segment.get(0))      		
    		segments.get(segmentNum).set(0, leftBreakpoint);    		
    		segments.addAll(segmentNum, Collections.nCopies(2, Arrays.asList(leftBreakpoint, rightBreakpoint)));    		
    		segments.add(segmentNum, Arrays.asList(leftend, rightBreakpoint));
    	} else {
    		sourceList = Arrays.asList(rightBreakpoint, rightBreakpoint, leftBreakpoint, rightBreakpoint, leftBreakpoint, leftBreakpoint);
			String leftend = segment.get(0); // String is immutable; even if in the future segment.get(0) will be changed, leftend will be locked in its current value. That's why I don't need to use String leftend = new String(segment.get(0))      		
    		segments.get(segmentNum).set(0, leftBreakpoint);    		
    		segments.addAll(segmentNum, Collections.nCopies(2, Arrays.asList(rightBreakpoint, leftBreakpoint)));    		
    		segments.add(segmentNum, Arrays.asList(leftend, rightBreakpoint));    		
    	}
    	
    	rawStringList.addAll(offset, sourceList);
    	
    	// Recording LGF doesn't care about orientation     	
    	duplicatedSegments.addAll(Collections.nCopies(3, Arrays.asList(leftBreakpoint, rightBreakpoint)));

    }
    
    // Two chrBreakpoints are located in different segments
    private void quadruplicateIII(String chrBreakpoint0, String chrBreakpoint1, String derChr0, String derChr1, int segmentNum0, int segmentNum1, String mark, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark, List<List<String>> duplicatedSegments) {
    	
    	int segmentNumL = Integer.min(segmentNum0, segmentNum1);
		int segmentNumR = Integer.max(segmentNum0, segmentNum1);
    	int offset = getOffsetInRawStringList(segmentNumR);
    	
    	int leftBreakpointN = findFurtherLeftBreakpoint(chrBreakpoint0, chrBreakpoint1, derChr0, derChr1, segmentsOfChr, segmentNumsOfMark);
    	int leftBreakpointNDuplicationCopy = findFurtherLeftBreakpointOfDuplicationCopy(chrBreakpoint0, chrBreakpoint1, derChr0, derChr1, segmentsOfChr, segmentNumsOfMark);
    		    	
    	String leftBreakpoint, rightBreakpoint;
    	if (leftBreakpointN == 0) {
    		leftBreakpoint = chrBreakpoint0 + mark;
    		rightBreakpoint = chrBreakpoint1 + mark;
    	} else {
    		leftBreakpoint = chrBreakpoint1 + mark;
    		rightBreakpoint = chrBreakpoint0 + mark;
    	} 
    	
    	List<String> sourceList = new ArrayList<>();
    	
    	// isDuplicationReverselyOriented when leftBreakpointN != leftBreakpointNDuplicationCopy
    	if (leftBreakpointN == leftBreakpointNDuplicationCopy) {
    		sourceList.add(rightBreakpoint);
    		sourceList.add(leftBreakpoint);
    		List<String> duplicatedSublist = new ArrayList<>();
    		duplicatedSublist.add(segments.get(segmentNumL).get(1));
    		for (int i = segmentNumL + 1; i < segmentNumR; i++) {
    			for (String s: segments.get(i)) {
    				duplicatedSublist.add(s);
    			}
    		}
    		duplicatedSublist.add(segments.get(segmentNumR).get(0));
    		sourceList.addAll(duplicatedSublist);
    		sourceList.add(rightBreakpoint);
    		sourceList.add(leftBreakpoint);
    		sourceList.addAll(duplicatedSublist);
    		sourceList.add(rightBreakpoint);
    		sourceList.add(leftBreakpoint);
    		sourceList.addAll(duplicatedSublist);
    	} else {
    		sourceList.add(rightBreakpoint);
    		sourceList.add(rightBreakpoint);
    		List<String> invertedSublist = new ArrayList<>();
    		invertedSublist.add(segments.get(segmentNumL).get(1));
    		for (int i = segmentNumL + 1; i < segmentNumR; i++) {
    			for (String s: segments.get(i)) {
    				invertedSublist.add(s);
    			}
    		}
    		invertedSublist.add(segments.get(segmentNumR).get(0));
    		Collections.reverse(invertedSublist);
    		sourceList.addAll(invertedSublist);
    		sourceList.add(leftBreakpoint);
    		sourceList.add(rightBreakpoint);
    		sourceList.addAll(invertedSublist);
    		sourceList.add(leftBreakpoint);
    		sourceList.add(leftBreakpoint);
    		Collections.reverse(invertedSublist);
    		sourceList.addAll(invertedSublist);
    	}
    	
    	duplicatedSegments.addAll(Collections.nCopies(3, Arrays.asList(leftBreakpoint, segments.get(segmentNumL).get(1))));
    	for (int i = segmentNumL + 1; i < segmentNumR; i++) {
    		duplicatedSegments.addAll(Collections.nCopies(3, new ArrayList<String>(segments.get(i)))); // We must use new instance here!
    	}
    	duplicatedSegments.addAll(Collections.nCopies(3, Arrays.asList(segments.get(segmentNumR).get(0), rightBreakpoint)));
    	
    	rawStringList.addAll(offset, sourceList);
    	segments = getSegments(rawStringList);
    }
    
    private boolean isValidIns(List<List<String>> breakpointsFullName, String derChr0, String derChr1, List<Integer> correctParsingOrder, int currentParsingPlace) {
    	// We have either within-chromosome insertion or between-chromosome insertion
    	if (breakpointsFullName.size() == 1) {
    		String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
    		String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
    		String chrBreakpoint2 = breakpointsFullName.get(0).get(2);
    		
    		String chr = getChr(chrBreakpoint0);
    		String mark;
    		if (currentParsingPlace == 0) {
    			if (chr.equals(derChr0)) {
    				mark = "M0";
    				initializeSystem(derChr0, mark);
    			} else if (chr.equals(derChr1)) {
    				mark = "M1";
    				initializeSystem(derChr1, mark);
    			} else {
    				return false;
    			}
    		} else {
    			Event previousEvent = subevents.get(correctParsingOrder.get(currentParsingPlace - 1));
    			List<String> previousChrList = previousEvent.getChrList();
    			List<String> previousChrMarks = parsingOrderMarksMap.get(currentParsingPlace - 1);
    			if (chr.equals(derChr0) || chr.equals(derChr1)) {
    				boolean isDerChr0 = chr.equals(derChr0);
    				mark = isDerChr0? "M0" : "M1";
    				if (!isMarkInSystem(mark)) {
    					return false;
    				}
    				
    				boolean chrBreakpointNotFoundInMainMark = findSegment(chrBreakpoint0, getSegmentNumList(mark)) == -1 || findSegment(chrBreakpoint1, getSegmentNumList(mark)) == -1 || findSegment(chrBreakpoint2, getSegmentNumList(mark)) == -1;
    				
    				if (chrBreakpointNotFoundInMainMark) {
    					int chrIndex = previousChrList.indexOf(chr);
    					if (chrIndex == -1) {
    						return false;
    					}
    					mark = previousChrMarks.get(chrIndex);
    					if (!mark.startsWith("M")) {
    						return false;
    					}
    				}
    			} else {
    				int chrIndex = previousChrList.indexOf(chr);
    				if (chrIndex == -1) {
    					return false;
    				}
    				mark = previousChrMarks.get(chrIndex);
    				if (!mark.startsWith("M")) {
    					return false;
    				}
    			}
    		}
    		
    		for (String s: getChrBreakpointsOfAllResolution(chrBreakpoint0)) {
				if (insertedPlaceListMap.get(mark) != null && insertedPlaceListMap.get(mark).contains(s)) {
					return false;
				}
			}
    		
    		List<List<String>> segmentsOfChr = getSegmentsOfChr(mark);
			List<Integer> segmentNumsOfMark = getSegmentNumList(mark);
			int segmentNum0, segmentNum1, segmentNum2;
			if (isInMultipleSegments(chrBreakpoint0, segmentsOfChr)) {
				return false;
			} else if (isInMultipleSegments(chrBreakpoint1, segmentsOfChr) || isInMultipleSegments(chrBreakpoint2, segmentsOfChr)) {
				segmentNum0 = findSegment(chrBreakpoint0, segmentNumsOfMark);
				segmentNum1 = getUniqueSegment(chrBreakpoint1, chrBreakpoint2, segmentsOfChr, segmentNumsOfMark);
				segmentNum2 = segmentNum1;
				if (segmentNum0 == -1 || segmentNum1 == -1) {
    				return false;
    			}
				if (!isValidInsertedSegment(chrBreakpoint0, chrBreakpoint1, chrBreakpoint2, mark, segmentsOfChr, segmentNumsOfMark)) {
    				return false;
    			}
			} else {
				segmentNum0 = findSegment(chrBreakpoint0, segmentNumsOfMark);
    			segmentNum1 = findSegment(chrBreakpoint1, segmentNumsOfMark);
    			segmentNum2 = findSegment(chrBreakpoint2, segmentNumsOfMark);
    			if (segmentNum0 == -1 || segmentNum1 == -1 || segmentNum2 == -1) {
    				return false;
    			}
    			if (!isValidInsertedSegment(chrBreakpoint0, chrBreakpoint1, chrBreakpoint2, mark, segmentsOfChr, segmentNumsOfMark)) {
    				return false;
    			}
    			List<String> segment1 = segments.get(segmentNum1);
    			if (chrBreakpoint1.equals(chrBreakpoint2)) {
					if (isFinerThanExistingSegmentEndPoint(chrBreakpoint1, segment1) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint1, segment1)) {
						return false;
					} 
    			} else {
					if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint1, segment1) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint1, segment1)) {
						return false;
					}
					List<String> segment2 = segments.get(segmentNum2);
					if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint2, segment2) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint2, segment2)) {
						return false;
					}
    			}
			}
			List<String> segment0 = segments.get(segmentNum0);
			if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint0, segment0)) {
				return false;
			} 
			// Do the main task
			insertWithinChr(chrBreakpoint0, chrBreakpoint1, chrBreakpoint2, derChr0, derChr1, segmentNum0, segmentNum1, segmentNum2, mark, segmentsOfChr, segmentNumsOfMark);
			// Record the inserted place
			if (insertedPlaceListMap.get(mark) == null) {
				insertedPlaceListMap.put(mark, new ArrayList<String>());
			}
			insertedPlaceListMap.get(mark).add(chrBreakpoint0);
			// Record ParsingOrder_Marks map
			parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(mark));
			
    	} else {
    		
    		String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
    		String chrBreakpoint1 = breakpointsFullName.get(1).get(0);
    		String chrBreakpoint2 = breakpointsFullName.get(1).get(1);
    		String chr0 = getChr(chrBreakpoint0);
    		String chr1 = getChr(chrBreakpoint1);
    		
    		String mark0;
    		if (currentParsingPlace == 0) {
    			if (chr0.equals(derChr0)) {
    				mark0 = "M0";
    				initializeSystem(derChr0, mark0);
    			} else if (chr0.equals(derChr1)) {
    				mark0 = "M1";
    				initializeSystem(derChr1, mark0);
    			} else {
    				return false;
    			}
    		} else {
    			Event previousEvent = subevents.get(correctParsingOrder.get(currentParsingPlace - 1));
    			List<String> previousChrList = previousEvent.getChrList();
    			List<String> previousChrMarks = parsingOrderMarksMap.get(currentParsingPlace - 1);
    			if (chr0.equals(derChr0) || chr0.equals(derChr1)) {
    				boolean isDerChr0 = chr0.equals(derChr0);
    				mark0 = isDerChr0? "M0" : "M1";
    				if (!isMarkInSystem(mark0)) {
    					return false;
    				}
    				
    				boolean chrBreakpointNotFoundInMainMark = findSegment(chrBreakpoint0, getSegmentNumList(mark0)) == -1;
    				
    				if (chrBreakpointNotFoundInMainMark) {
    					int chrIndex = previousChrList.indexOf(chr0);
    					if (chrIndex == -1) {
    						return false;
    					}
    					mark0 = previousChrMarks.get(chrIndex);
    					if (!mark0.startsWith("M")) {
    						return false;
    					}
    				}
    			} else {
    				int chrIndex = previousChrList.indexOf(chr0);
    				if (chrIndex == -1) {
    					return false;
    				}
    				mark0 = previousChrMarks.get(chrIndex);
    				if (!mark0.startsWith("M")) {
    					return false;
    				}
    			}
    		}
    		// Examine inserted place
    		for (String s: getChrBreakpointsOfAllResolution(chrBreakpoint0)) {
				if (insertedPlaceListMap.get(mark0) != null && insertedPlaceListMap.get(mark0).contains(s)) {
					return false;
				}
			}
    		// Exclude confusing karyotypes
    		List<List<String>> segmentsOfChr0 = getSegmentsOfChr(mark0);
			List<Integer> segmentNumsOfMark0 = getSegmentNumList(mark0);
			// Chr1 is a new added chr, so that we don't need to check
			if (isInMultipleSegments(chrBreakpoint0, segmentsOfChr0)) {
				return false;
			}
			// Examine unmatching resolution case of chrBreakpoint0
			int segmentNum0 = findSegment(chrBreakpoint0, segmentNumsOfMark0);
			if (segmentNum0 == -1) {
				return false;
			}
			List<String> segment0 = segments.get(segmentNum0);
			if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint0, segment0)) {
				return false;
			}
			// Create a new mark for chr1
			String mark1;
			if (!chr0.equals(chr1)) {    				
				mark1 = getNextCommonMark();
				commonMarkSet.add(mark1);
			} else {
				mark1 = getNextHomologousMark();
				homologousMarkSet.add(mark1);    				
			}
			markToChrMap.put(mark1, chr1);
			markSpecificLossGain.put(mark1, initializeChrLossGain(chr1));
			// Do the main task
			insertBetweenChr(chrBreakpoint0, chrBreakpoint1, chrBreakpoint2, segmentNum0, mark0, mark1, segmentNumsOfMark0);
			// Record the inserted place
			if (insertedPlaceListMap.get(mark0) == null) {
				insertedPlaceListMap.put(mark0, new ArrayList<String>());
			}
			insertedPlaceListMap.get(mark0).add(chrBreakpoint0);
			// Record ParsingOrder_Marks map
			parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(mark0, mark1));
			// Record LG
			List<String> markedSegment =  getMarkedSegment(breakpointsFullName.get(1), mark1);
			recordGain(markedSegment, mark1);
			
    	} // Between-chromosome insertion
    	
    	return true;
    }
    
    // Within-chromosome insertion
    protected boolean isValidInsertedSegment(String chrBreakpoint0, String chrBreakpoint1, String chrBreakpoint2, String mark, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark) {
    	List<List<String>> insertedSegmentsList = new ArrayList<>();
    	if (isInMultipleSegments(chrBreakpoint1, segmentsOfChr) || isInMultipleSegments(chrBreakpoint2, segmentsOfChr)) {
    		// Then it must be in a unique segment containing both chrBreakpoints
    		insertedSegmentsList.add(Arrays.asList(chrBreakpoint1, chrBreakpoint2));    		
    	} else {
			int segmentNum1 = findSegment(chrBreakpoint1, segmentNumsOfMark);
			int segmentNum2 = findSegment(chrBreakpoint2, segmentNumsOfMark);
			if (segmentNum1 == segmentNum2) {
				insertedSegmentsList.add(Arrays.asList(chrBreakpoint1, chrBreakpoint2));
			} else {
				int segmentNumL, segmentNumR;
				String chrBreakpointL, chrBreakpointR;
				if (segmentNum1 < segmentNum2) {
					segmentNumL = segmentNum1;
					segmentNumR = segmentNum2;
					chrBreakpointL = chrBreakpoint1;
					chrBreakpointR = chrBreakpoint2;
				} else {
					segmentNumL = segmentNum2;
					segmentNumR = segmentNum1;
					chrBreakpointL = chrBreakpoint2;
					chrBreakpointR = chrBreakpoint1;
				}
				insertedSegmentsList.add(Arrays.asList(chrBreakpointL, segments.get(segmentNumL).get(1)));
				for (int i = segmentNumL +  1; i < segmentNumR; i++) {
					insertedSegmentsList.add(segments.get(i));
				}
				insertedSegmentsList.add(Arrays.asList(segments.get(segmentNumR).get(0), chrBreakpointR));
			}			
    	}
    	for (List<String> segment: insertedSegmentsList) {
    		// Make sure the inserted location is not inside the insertedSegment
    		if (isInSegment(chrBreakpoint0, segment)) {
    			return false;
    		}
    		// Make sure derCen is not inside the inserted segment, and this will guarantee that the location of derCen will not be changing
    		if ((mark.equals("M0") || mark.equals("M1")) && (isInSegment(markToChrMap.get(mark) + "p10", segment) || isInSegment(markToChrMap.get(mark) + "q10", segment))) {
    			return false;
			}
    	}
    	return true;
    }
    
    // Order the three breakpoints inside a within-chr insertion
    private List<String> orderChrBreakpoints(String chrBreakpoint0, String chrBreakpoint1, String chrBreakpoint2, String derChr0, String derChr1, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark) {
    	int segmentNum0 = findSegment(chrBreakpoint0, segmentNumsOfMark), segmentNum1, segmentNum2;
    	if (isInMultipleSegments(chrBreakpoint1, segmentsOfChr) || isInMultipleSegments(chrBreakpoint2, segmentsOfChr)) {
    		// Then it must be in a unique segment containing both chrBreakpoints
    		segmentNum1 = getUniqueSegment(chrBreakpoint1, chrBreakpoint2, segmentsOfChr, segmentNumsOfMark);
			segmentNum2 = segmentNum1;    		
    	} else {
			segmentNum1 = findSegment(chrBreakpoint1, segmentNumsOfMark);
			segmentNum2 = findSegment(chrBreakpoint2, segmentNumsOfMark);
    	}
    	// Order chrBreakpoint1 and chrBreakpoint2
    	int leftEndOfInsertedSegment = findFurtherLeftBreakpoint(chrBreakpoint1, chrBreakpoint2, derChr0, derChr1, segmentsOfChr, segmentNumsOfMark) == 0 ? 1 : 2 ;
    	
    	List<String> list1 = Arrays.asList(chrBreakpoint1, chrBreakpoint2, chrBreakpoint0);
    	List<String> list2 = Arrays.asList(chrBreakpoint2, chrBreakpoint1, chrBreakpoint0);
    	List<String> list3 = Arrays.asList(chrBreakpoint0, chrBreakpoint1, chrBreakpoint2);
    	List<String> list4 = Arrays.asList(chrBreakpoint0, chrBreakpoint2, chrBreakpoint1);
    	
    	if (segmentNum0 == segmentNum1 && segmentNum1 == segmentNum2) {
    		// Order chrBreakpoint0 and chrBreakpoint1
    		int derCen0Segment = findSegmentContainingDerCen(derChr0, segments);
			int derCen1Segment = findSegmentContainingDerCen(derChr1, segments);
			if (segmentNum0 == derCen0Segment || segmentNum0 == derCen1Segment) {
				int derCenSegment = segmentNum0 == derCen0Segment? derCen0Segment : derCen1Segment;
				String chrBreakpointArm0 = getChrArm(chrBreakpoint0);
    			String chrBreakpointArm1 = getChrArm(chrBreakpoint1);
    			String segLeftArm = getChrArm(segments.get(derCenSegment).get(0));
    			if (chrBreakpointArm0.equals(chrBreakpointArm1)) {
    				if (segLeftArm.equals(chrBreakpointArm0)) {
    					if (chrBreakpoint0.compareTo(chrBreakpoint1) < 0) {
    						return leftEndOfInsertedSegment == 1 ? list1 : list2 ;
    	    			} else {
    	    				return leftEndOfInsertedSegment == 1 ? list3 : list4 ;
    	    			}
    				} else {
    					if (chrBreakpoint0.compareTo(chrBreakpoint1) < 0) {
    						return leftEndOfInsertedSegment == 1 ? list3 : list4 ;
    	    			} else {
    	    				return leftEndOfInsertedSegment == 1 ? list1 : list2 ;
    	    			}
    				}
    			} else if (chrBreakpointArm0.equals(segLeftArm)) {
    				return leftEndOfInsertedSegment == 1 ? list3 : list4 ;
    			} else {
    				return leftEndOfInsertedSegment == 1 ? list1 : list2 ;
    			}
			} else {
				
				List<String> segment = segments.get(segmentNum0);
        		boolean isSegmentReversed = segment.get(0).compareTo(segment.get(1)) > 0;  
    			
    			if (chrBreakpoint0.compareTo(chrBreakpoint1) < 0) {
    				if (!isSegmentReversed) {
    					return leftEndOfInsertedSegment == 1 ? list3 : list4 ;
    				} else {
    					return leftEndOfInsertedSegment == 1 ? list1 : list2 ;
    				}				
    			} else {
    				if (!isSegmentReversed) {
    					return leftEndOfInsertedSegment == 1 ? list1 : list2 ;
    				} else {
    					return leftEndOfInsertedSegment == 1 ? list3 : list4 ;
    				}				
    			}
				
			}
    	} else if (segmentNum0 < segmentNum1 && segmentNum1 == segmentNum2) {    		
    		return leftEndOfInsertedSegment == 1 ? list3 : list4 ;
    	} else if (segmentNum0 > segmentNum1 && segmentNum1 == segmentNum2) {
    		return leftEndOfInsertedSegment == 1 ? list1 : list2 ;
    	} else if (segmentNum0 == segmentNum1 && segmentNum1 < segmentNum2) {
    		return list3;
    	} else if (segmentNum0 == segmentNum1 && segmentNum1 > segmentNum2) {
    		return list2;
    	} else if (segmentNum0 == segmentNum2 && segmentNum1 < segmentNum2) {
    		return list1;
    	} else if (segmentNum0 == segmentNum2 && segmentNum1 > segmentNum2) {
    		return list4;
    	} else if (segmentNum0 < segmentNum1 && segmentNum1 < segmentNum2) {
    		return list3;
    	} else if (segmentNum1 < segmentNum2 && segmentNum2 < segmentNum0) {
    		return list1;
    	} else if (segmentNum0 < segmentNum2 && segmentNum2 < segmentNum1) {
    		return list4;
    	} else if (segmentNum2 < segmentNum1 && segmentNum1 < segmentNum0) {
    		return list2;
    	} else {
    		// It's impossible to have 
    		// i) segmentNum1 < segmentNum0 && segmentNum0 < segmentNum2
    		// ii) segmentNum2 < segmentNum0 && segmentNum0 < segmentNum1
    		// as these are invalid inserted segments.
    		return null;
    	}
    }
    
    private Boolean isInsertedPlaceToLeftOfCen(String chrBreakpoint0, List<Integer> segmentNumsOfMark0) {
    	int segmentNum0 = findSegment(chrBreakpoint0, segmentNumsOfMark0);    	
    	int derCen0Segment = findSegmentContainingDerCen(derChr0, segments);
		int derCen1Segment = findSegmentContainingDerCen(derChr1, segments);
		if (derCen0Segment != -1 && derCen1Segment != -1) {
			int minDerCenSegment = Integer.min(derCen0Segment, derCen1Segment);
			int maxDerCenSegment = Integer.max(derCen0Segment, derCen1Segment);
			String minDerCenMark = minDerCenSegment == derCen0Segment ? "M0" : "M1";
			String maxDerCenMark = maxDerCenSegment == derCen0Segment ? "M0" : "M1";
			if (segmentNum0 < minDerCenSegment) {
				return true;
			} else if (segmentNum0 == minDerCenSegment) {
				return getChrArm(segments.get(minDerCenSegment).get(0)).equals(getChrArm(chrBreakpoint0));
			} else if (segmentNum0 > minDerCenSegment && segmentNum0 < maxDerCenSegment) {
				List<Integer> segmentNumsOfMinDerCenMark = getSegmentNumList(minDerCenMark);
				List<Integer> segmentNumsOfMaxDerCenMark = getSegmentNumList(maxDerCenMark);
				if (segmentNum0 <= segmentNumsOfMinDerCenMark.get(segmentNumsOfMinDerCenMark.size() - 1)) {
					return false;
				} else if (segmentNum0 >= segmentNumsOfMaxDerCenMark.get(0)) {
					return true;
				} else {
					// We decide not to change the orientation for this case
					return null;
				} 
			} else if (segmentNum0 == maxDerCenSegment) {
				return getChrArm(segments.get(maxDerCenSegment).get(0)).equals(getChrArm(chrBreakpoint0));
			} else {
				return false;
			}
		} else {
			int derCenSegment = derCen0Segment != -1 ? derCen0Segment : derCen1Segment;
			if (segmentNum0 < derCenSegment) {
	    		return true;
	    	} else if (segmentNum0 > derCenSegment) {
	    		return false;
	    	} else if (getChrArm(chrBreakpoint0).equals(getChrArm(segments.get(derCenSegment).get(0)))) {
	    		return true;
	    	} else {
	    		return false;
	    	}
		} // derCen0Segment != -1 || derCen1Segment != -1 Note it's impossible to have both inequalities hold.
    }
    
    private void insertWithinChr(String chrBreakpoint0, String chrBreakpoint1, String chrBreakpoint2, String derChr0, String derChr1, int segmentNum0, int segmentNum1, int segmentNum2, String mark, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark) {
    	
    	int leftBreakpointN = findFurtherLeftBreakpoint(chrBreakpoint1, chrBreakpoint2, derChr0, derChr1, segmentsOfChr, segmentNumsOfMark);
    	String leftBreakpoint, rightBreakpoint;
    	if (leftBreakpointN == 0) {
    		leftBreakpoint = chrBreakpoint1 + mark;
    		rightBreakpoint = chrBreakpoint2 + mark;
    	} else {
    		leftBreakpoint = chrBreakpoint2 + mark;
    		rightBreakpoint = chrBreakpoint1 + mark;
    	}
    	
    	int offset2 = getOffsetInRawStringList(segmentNum2);
		int offset1 = getOffsetInRawStringList(segmentNum1);
		int offset0 = getOffsetInRawStringList(segmentNum0);
		
		Boolean insertToLeftOfCen = isInsertedPlaceToLeftOfCen(chrBreakpoint0, segmentNumsOfMark);
		
		if (segmentNum1 == segmentNum2) { 
			
			if (segmentNum0 == segmentNum1) {
				List<String> sourceList;
				List<String> orderedChrBreakpoints = orderChrBreakpoints(chrBreakpoint0, chrBreakpoint1, chrBreakpoint2, derChr0, derChr1, segmentsOfChr, segmentNumsOfMark);
				int indexOfInsertedPlace = orderedChrBreakpoints.indexOf(chrBreakpoint0); // either 0 or 2
				// I don't need to worry about the situation that chrBreakpoint0 will be (partially) the same with either chrBreakpoint1 or chrBreakpoint2
    			// b/c at this stage, it has passed the valid_inserted_segment test so that chrBreakpoint0 will not be inside the inserted segment.
				if (indexOfInsertedPlace == 0) {					
					if (insertToLeftOfCen == null) {
						sourceList = Arrays.asList(chrBreakpoint0 + mark, leftBreakpoint, rightBreakpoint, chrBreakpoint0 + mark, leftBreakpoint, rightBreakpoint);
					} else if (insertToLeftOfCen) {
    					sourceList = Arrays.asList(chrBreakpoint0 + mark, chrBreakpoint2 + mark, chrBreakpoint1 + mark, chrBreakpoint0 + mark, leftBreakpoint, rightBreakpoint);
    				} else {
    					sourceList = Arrays.asList(chrBreakpoint0 + mark, chrBreakpoint1 + mark, chrBreakpoint2 + mark, chrBreakpoint0 + mark, leftBreakpoint, rightBreakpoint);
    				}
				} else {
					if (insertToLeftOfCen == null) {
						sourceList = Arrays.asList(leftBreakpoint, rightBreakpoint, chrBreakpoint0 + mark, leftBreakpoint, rightBreakpoint, chrBreakpoint0 + mark);
					} else if (insertToLeftOfCen) {
						sourceList = Arrays.asList(leftBreakpoint, rightBreakpoint, chrBreakpoint0 + mark, chrBreakpoint2 + mark, chrBreakpoint1 + mark, chrBreakpoint0 + mark);
					} else {
						sourceList = Arrays.asList(leftBreakpoint, rightBreakpoint, chrBreakpoint0 + mark, chrBreakpoint1 + mark, chrBreakpoint2 + mark, chrBreakpoint0 + mark);
					}
				}
				rawStringList.addAll(offset0, sourceList); 
			} else {
				List<String> sourceList0;				
				if (insertToLeftOfCen == null) {
					sourceList0 = Arrays.asList(chrBreakpoint0 + mark, leftBreakpoint, rightBreakpoint, chrBreakpoint0 + mark);
				} else if (insertToLeftOfCen) {
					sourceList0 = Arrays.asList(chrBreakpoint0 + mark, chrBreakpoint2 + mark, chrBreakpoint1 + mark, chrBreakpoint0 + mark);
				} else {
					sourceList0 = Arrays.asList(chrBreakpoint0 + mark, chrBreakpoint1 + mark, chrBreakpoint2 + mark, chrBreakpoint0 + mark);
				} 
				if (segmentNum0 < segmentNum1) {
    				rawStringList.addAll(offset1, Arrays.asList(leftBreakpoint, rightBreakpoint));    				
    				rawStringList.addAll(offset0, sourceList0);    				
    			} else {
    				rawStringList.addAll(offset0, sourceList0);     				
    				rawStringList.addAll(offset1, Arrays.asList(leftBreakpoint, rightBreakpoint)); 
    			}
			}
			
		} else if (segmentNum1 < segmentNum2) {
			
			List<String> deletedSublist = rawStringList.subList(offset1, offset2); // Seems I don't need to use a new instance. Double check!!! 
			List<String> insertedSegment = new ArrayList<>();
			insertedSegment.add(chrBreakpoint1 + mark);
			insertedSegment.addAll(deletedSublist);
			insertedSegment.add(chrBreakpoint2 + mark);
			
			List<String> sourceList0 = new ArrayList<>();
			sourceList0.add(chrBreakpoint0 + mark);    			
			if (insertToLeftOfCen != null && insertToLeftOfCen) { 
				Collections.reverse(insertedSegment);
			}
			sourceList0.addAll(insertedSegment);
			sourceList0.add(chrBreakpoint0 + mark);
			
			if (segmentNum0 <= segmentNum1) {
	   			
    			rawStringList.subList(offset1, offset2).clear();
    			rawStringList.add(offset1, chrBreakpoint2 + mark);
    			rawStringList.add(offset1, chrBreakpoint1 + mark);    			
    			rawStringList.addAll(offset0, sourceList0);
    			
    		} else {
    			
    			rawStringList.addAll(offset0, sourceList0);    			
    			rawStringList.subList(offset1, offset2).clear();
    			rawStringList.add(offset1, chrBreakpoint2 + mark);
    			rawStringList.add(offset1, chrBreakpoint1 + mark);
    			
    		} // segmentNum2 <= segmentNum0; Note it's impossible to have segmentNum1 < segmentNum0 && segmentNum0 < segmentNum2
			
		} else {
			
			List<String> deletedSublist = rawStringList.subList(offset2, offset1);
			List<String> insertedSegment = new ArrayList<>();
			insertedSegment.add(chrBreakpoint2 + mark);
			insertedSegment.addAll(deletedSublist);
			insertedSegment.add(chrBreakpoint1 + mark);
			
			List<String> sourceList0 = new ArrayList<>();
			sourceList0.add(chrBreakpoint0 + mark);    			
			if (insertToLeftOfCen != null && !insertToLeftOfCen) { 
				Collections.reverse(insertedSegment);
			} 
			sourceList0.addAll(insertedSegment);
			sourceList0.add(chrBreakpoint0 + mark);
			
			if (segmentNum0 <= segmentNum2) {
    			
    			rawStringList.subList(offset2, offset1).clear();
    			rawStringList.add(offset2, chrBreakpoint1 + mark);
    			rawStringList.add(offset2, chrBreakpoint2 + mark);
    			rawStringList.addAll(offset0, sourceList0);
    			
    		} else {

    			rawStringList.addAll(offset0, sourceList0);    			
    			rawStringList.subList(offset2, offset1).clear();
    			rawStringList.add(offset2, chrBreakpoint1 + mark);
    			rawStringList.add(offset2, chrBreakpoint2 + mark);
    			
    		} // segmentNum1 <= segmentNum0; Note it's impossible to have segmentNum2 < segmentNum0 && segmentNum0 < segmentNum1
			
		} // segmentNum2 < segmentNum1
		
		segments = getSegments(rawStringList);
		
    }
    
    private void insertBetweenChr(String chrBreakpoint0, String chrBreakpoint1, String chrBreakpoint2, int segmentNum0, String mark0, String mark1, List<Integer> segmentNumsOfMark0) {
    	List<String> sourceList0 = new ArrayList<>();
    	Boolean insertToLeftOfCen = isInsertedPlaceToLeftOfCen(chrBreakpoint0, segmentNumsOfMark0);
    	if (insertToLeftOfCen == null) {
    		sourceList0 = Arrays.asList(chrBreakpoint0 + mark0, chrBreakpoint1 + mark1, chrBreakpoint2 + mark1, chrBreakpoint0 + mark0);
    	} else if (insertToLeftOfCen) {
    		sourceList0 = Arrays.asList(chrBreakpoint0 + mark0, chrBreakpoint2 + mark1, chrBreakpoint1 + mark1, chrBreakpoint0 + mark0);
    	} else {
    		sourceList0 = Arrays.asList(chrBreakpoint0 + mark0, chrBreakpoint1 + mark1, chrBreakpoint2 + mark1, chrBreakpoint0 + mark0);
    	}
    	int offset0 = getOffsetInRawStringList(segmentNum0);    	
    	rawStringList.addAll(offset0, sourceList0);
    	segments = getSegments(rawStringList);
    }
    
    private boolean isValidInv(List<List<String>> breakpointsFullName, String derChr0, String derChr1, List<Integer> correctParsingOrder, int currentParsingPlace) {
		String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
		String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
		String chr = getChr(chrBreakpoint0);
		
		String mark;
		if (currentParsingPlace == 0) {
			if (chr.equals(derChr0)) {
				mark = "M0";
				initializeSystem(derChr0, mark);
			} else if (chr.equals(derChr1)) {
				mark = "M1";
				initializeSystem(derChr1, mark);
			} else {
				return false;
			}
		} else {
			Event previousEvent = subevents.get(correctParsingOrder.get(currentParsingPlace - 1));
			List<String> previousChrList = previousEvent.getChrList();
			List<String> previousChrMarks = parsingOrderMarksMap.get(currentParsingPlace - 1);
			if (chr.equals(derChr0) || chr.equals(derChr1)) {
				boolean isDerChr0 = chr.equals(derChr0);
				mark = isDerChr0? "M0" : "M1";
				if (!isMarkInSystem(mark)) {
					return false;
				}
				
				boolean chrBreakpointNotFoundInMainMark = findSegment(chrBreakpoint0, getSegmentNumList(mark)) == -1 || findSegment(chrBreakpoint1, getSegmentNumList(mark)) == -1;
				
				if (chrBreakpointNotFoundInMainMark) {
					int chrIndex = previousChrList.indexOf(chr);
					if (chrIndex == -1) {
						return false;
					}
					mark = previousChrMarks.get(chrIndex);
					if (!mark.startsWith("M")) {
						return false;
					}
				}				
								
			} else {				
				int chrIndex = previousChrList.indexOf(chr);
				if (chrIndex == -1) {
					return false;
				}
				mark = previousChrMarks.get(chrIndex);
				if (!mark.startsWith("M")) {
					return false;
				}
			}
		}
		List<List<String>> segmentsOfChr = getSegmentsOfChr(mark);
		List<Integer> segmentNumsOfMark = getSegmentNumList(mark);
		int segmentNum0, segmentNum1;
		if (isInMultipleSegments(chrBreakpoint0, segmentsOfChr) || isInMultipleSegments(chrBreakpoint1, segmentsOfChr)) {
			segmentNum0 = getUniqueSegment(chrBreakpoint0, chrBreakpoint1, segmentsOfChr, segmentNumsOfMark);
			if (segmentNum0 == -1) {
				return false;
			}
			segmentNum1 = segmentNum0;
		} else {
			segmentNum0 = findSegment(chrBreakpoint0, segmentNumsOfMark);
			segmentNum1 = findSegment(chrBreakpoint1, segmentNumsOfMark);
			    			
			if (segmentNum0 == -1 || segmentNum1 == -1) {
				return false;
			} else {
				List<String> segment0 = segments.get(segmentNum0);
				List<String> segment1 = segments.get(segmentNum1);
			    if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint1, segment1) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint1, segment1)) {
			    	return false;
			    }	
			} 			
		}
		
		invert(chrBreakpoint0, chrBreakpoint1, segmentNum0, segmentNum1, mark);
		parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(mark));
		
    	return true; 
	}
    
    private boolean isValidHsr(List<List<String>> breakpointsFullName, String derChr0, String derChr1, List<Integer> correctParsingOrder, int currentParsingPlace) {
    	// We have either within-chromosome-band hsr or between-chromosome-band-interface hsr
    	if (breakpointsFullName.size() == 1) {
    		String chrBreakpoint = breakpointsFullName.get(0).get(0);
    		
    		String chr = getChr(chrBreakpoint);
    		String mark;
    		if (currentParsingPlace == 0) {
    			if (chr.equals(derChr0)) {
    				mark = "M0";
    				initializeSystem(derChr0, mark);
    			} else if (chr.equals(derChr1)) {
    				mark = "M1";
    				initializeSystem(derChr1, mark);
    			} else {
    				return false;
    			}
    		} else {
    			Event previousEvent = subevents.get(correctParsingOrder.get(currentParsingPlace - 1));
    			List<String> previousChrList = previousEvent.getChrList();
    			List<String> previousChrMarks = parsingOrderMarksMap.get(currentParsingPlace - 1);
    			if (chr.equals(derChr0) || chr.equals(derChr1)) {
    				boolean isDerChr0 = chr.equals(derChr0);
    				mark = isDerChr0? "M0" : "M1";
    				if (!isMarkInSystem(mark)) {
    					return false;
    				}
    				if (findSegment(chrBreakpoint, getSegmentNumList(mark)) == -1) {
    					int chrIndex = previousChrList.indexOf(chr);
    					if (chrIndex == -1) {
    						return false;
    					}
    					mark = previousChrMarks.get(chrIndex);
    					if (!mark.startsWith("M")) {
    						return false;
    					}
    				}				
    			} else {				
    				int chrIndex = previousChrList.indexOf(chr);
    				if (chrIndex == -1) {
    					return false;
    				}
    				mark = previousChrMarks.get(chrIndex);
    				if (!mark.startsWith("M")) {
    					return false;
    				}
    			}
    		}
    		// First we cannot have multiple segments inserted at the same place.
    		for (String s: getChrBreakpointsOfAllResolution(chrBreakpoint)) {
				if (hsrPlaceListMap.get(mark) != null && hsrPlaceListMap.get(mark).contains(s)) {
					return false;
				}
			}
    		// Exclude confusing karyotypes
    		List<List<String>> segmentsOfChr = getSegmentsOfChr(mark);
			int segmentNum;
			if (isInMultipleSegments(chrBreakpoint, segmentsOfChr)) {
				return false;
			} else {
            	List<Integer> segmentNumsOfMark = getSegmentNumList(mark);
            	segmentNum = findSegment(chrBreakpoint, segmentNumsOfMark);
            	if (segmentNum == -1) {
            		return false;
            	} else {
            		List<String> segment = segments.get(segmentNum);
            		if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint, segment) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint, segment)) {
            			return false;
            		}
            		List<String> sourceList = Arrays.asList(chrBreakpoint + mark, "hsr" + mark, chrBreakpoint + mark);
            		int offset = getOffsetInRawStringList(segmentNum);            		
            		rawStringList.addAll(offset, sourceList);            		
            	}  
            }
			
			// Record hsr place
			if (hsrPlaceListMap.get(mark) == null) {
				hsrPlaceListMap.put(mark, new ArrayList<String>());
			}
			hsrPlaceListMap.get(mark).add(chrBreakpoint);
			// Record ParsingOrder_Marks map
			parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(mark));
    	} else {
    		
    		if (currentParsingPlace == 0) {
    			return false;
    		}
    		
    		String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
    		String chrBreakpoint1 = breakpointsFullName.get(1).get(0);
    		
    		int hsrSegmentNum = getHsrSegmentNum(Arrays.asList(chrBreakpoint0, chrBreakpoint1));
    		// Exclude invalid hsr interface
    		if (hsrSegmentNum == -1) {
    			return false;
    		}
    		
    		int offset = getOffsetInRawStringList(hsrSegmentNum - 1);
    		String leftMark = getMark(segments.get(hsrSegmentNum - 1));
    		String rightMark = getMark(segments.get(hsrSegmentNum));
    		
    		rawStringList.add(offset + 1, "hsr"); // Since this is an interface hsr, I don't add marks as suffix.
    		
    		// Record ParsingOrder_Marks map
    		String mark0, mark1;
    		if (!chrBreakpoint0.equals(chrBreakpoint1)) {
    			String leftBreakpoint = segments.get(hsrSegmentNum - 1).get(1);
    			mark0 = chrBreakpoint0.equals(leftBreakpoint) ? leftMark : rightMark ;
    			mark1 = chrBreakpoint0.equals(leftBreakpoint) ? rightMark : leftMark ;
    		} else {
    			mark0 = leftMark;
    			mark1 = rightMark;
    		}
    		parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(mark0, mark1));
    	}
    	
    	segments = getSegments(rawStringList);
    	return true;
    }
	
	private boolean isValidT(List<List<String>> breakpointsFullName, String derChr0, String derChr1, List<Integer> correctParsingOrder, int currentParsingPlace) {
		String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
    	String chrBreakpoint1 = breakpointsFullName.get(1).get(0);
    	String chr0 = getChr(chrBreakpoint0);
    	String chr1 = getChr(chrBreakpoint1);
    	List<String> chrList = Arrays.asList(chr0, chr1);
    	String chrBreakpointDeletion, chrBreakpointInsertion;
    	String markDeletion, markInsertion = "";
    	int segmentNumDeletion = -1;
    	boolean isDualDeletion = false;
    	boolean isDI = false;
    	
    	if (currentParsingPlace == 0) {
    		if (chrList.contains(derChr0) && chrList.contains(derChr1)) {
    			isDualDeletion = true;
    			initializeSystemWithTwoChrs(chrBreakpoint0, chrBreakpoint1);   
    			markDeletion = "";
    			chrBreakpointDeletion = "";
    			chrBreakpointInsertion = "";
    		} else if (chrList.contains(derChr0) || chrList.contains(derChr1)) {
    			String derChr;
    			if (chrList.contains(derChr0)) {
    				derChr = derChr0;
    				markDeletion = "M0";
    			} else {
    				derChr = derChr1;
    				markDeletion = "M1";
    			} 
    			initializeSystem(derChr, markDeletion); 
    			isDI = chrList.indexOf(derChr) == 0;
    			if (isDI) {
    				chrBreakpointDeletion = chrBreakpoint0;
    				chrBreakpointInsertion = chrBreakpoint1;
    			} else {
    				chrBreakpointDeletion = chrBreakpoint1;
    				chrBreakpointInsertion = chrBreakpoint0;
    			} 		
    		} else {
    			return false;
    		}
    	} else {
    		Event previousEvent = subevents.get(correctParsingOrder.get(currentParsingPlace - 1));
			List<String> previousChrList = previousEvent.getChrList();
			List<String> previousChrMarks = parsingOrderMarksMap.get(currentParsingPlace - 1);
    		if (chrList.contains(derChr0) && chrList.contains(derChr1)) {
    	    	String chr0Mark = chr0.equals(derChr0) ? "M0" : "M1";
    			String chr1Mark = chr0Mark.equals("M0") ? "M1" : "M0";
    			if (isMarkInSystem(chr0Mark) && isMarkInSystem(chr1Mark)) {
    				int segmentNum0 = findSegment(chrBreakpoint0, getSegmentNumList(chr0Mark));
    				int segmentNum1 = findSegment(chrBreakpoint1, getSegmentNumList(chr1Mark));
    				if (segmentNum0 == -1 && segmentNum1 == -1) {
    					// maybe correct e.g. 45,XY,der(8;17)t(8;17)(p12;q21)t(17;1)(p12;q42)ins(1;8)(q43;p21p23)t(8;.)(p22;.)
						TranslocationWithoutUsingDerChr translocationWithoutUsingDerChr = parseTranslocationWithoutUsingDerChr(previousChrList, previousChrMarks, chr0, chr1, chrBreakpoint0, chrBreakpoint1);
    	    			if (translocationWithoutUsingDerChr.isWrong()) {
    	    				return false;
    	    			} else {
    	    				markDeletion = translocationWithoutUsingDerChr.getMarkDeletion();
    	    				chrBreakpointDeletion = translocationWithoutUsingDerChr.getChrBreakpointDeletion();
    	    				chrBreakpointInsertion = translocationWithoutUsingDerChr.getChrBreakpointInsertion();
    	    				isDI = translocationWithoutUsingDerChr.isDI();
    	    			}
    				} else if (segmentNum0 != -1 && segmentNum1 == -1) {
    					markDeletion = chr0Mark;
    					chrBreakpointDeletion = chrBreakpoint0;
    					chrBreakpointInsertion = chrBreakpoint1;
    					isDI = true;
    				} else if (segmentNum0 == -1 && segmentNum1 != -1) {
    					markDeletion = chr1Mark;
    					chrBreakpointDeletion = chrBreakpoint1;
    					chrBreakpointInsertion = chrBreakpoint0;
    					isDI = false;
    				} else {
    					return false; // It may be chrBreakpoint0 in multiple segments of mark0 while chrBreakpoint1 in unique segment of mark1, I still think it's wrong. Debatable! 
    				} // segmentNum0 != -1 && segmentNum1 != -1   
    			} else if (isMarkInSystem(chr0Mark) || isMarkInSystem(chr1Mark)) {
    				isDualDeletion = true;
    				markDeletion = isMarkInSystem(chr0Mark)? chr0Mark : chr1Mark; 
    				markInsertion = markDeletion.equals(chr0Mark)? chr1Mark : chr0Mark;
    				chrBreakpointDeletion = markDeletion.equals(chr0Mark)? chrBreakpoint0 : chrBreakpoint1;  
                    chrBreakpointInsertion = markDeletion.equals(chr0Mark)? chrBreakpoint1 : chrBreakpoint0;
                    if (findSegment(chrBreakpointDeletion, getSegmentNumList(markDeletion)) == -1) {
	        			String chrDeletion = getChr(chrBreakpointDeletion);
	        			int chrDeletionPreviousChrIndex = previousChrList.indexOf(chrDeletion);
	        			if (chrDeletionPreviousChrIndex == -1) {	        				
	        				return false;
	        			} 
	        			markDeletion = previousChrMarks.get(chrDeletionPreviousChrIndex);	
                    }
    			} else {
    				return false; // Impossible to appear
    			}    			
    			
    		} else if (chrList.contains(derChr0) || chrList.contains(derChr1)) { 
    			String derChr = chrList.contains(derChr0)? derChr0 : derChr1;
    			markDeletion = chrList.contains(derChr0)? "M0" : "M1";
    			if (!isMarkInSystem(markDeletion)) {
    				if (chr0.equals(chr1)) {
    					return false;
    				}
    				int anotherChrIndex = chrList.indexOf(derChr) == 0? 1 : 0;
    				String anotherChr =  chrList.get(anotherChrIndex);
    				if (previousChrList.contains(anotherChr)) {
    					// Change markDeletion
    					isDualDeletion = true;
    					int chrIndex = previousChrList.indexOf(anotherChr);
    					markInsertion = markDeletion; // String is immutable, no need to use new instantiation
    					markDeletion = previousChrMarks.get(chrIndex);
    					chrBreakpointInsertion = derChr.equals(chr0)? chrBreakpoint0 : chrBreakpoint1;
    					chrBreakpointDeletion = derChr.equals(chr0)? chrBreakpoint1 : chrBreakpoint0;
    				} else {
    					return false;
    				}
				} else {
	    			isDI = chrList.indexOf(derChr) == 0;
	    			if (isDI) {
	    				chrBreakpointDeletion = chrBreakpoint0;
	    				chrBreakpointInsertion = chrBreakpoint1;
	    			} else {
	    				chrBreakpointDeletion = chrBreakpoint1;
	    				chrBreakpointInsertion = chrBreakpoint0;
	    			}
	    			if (findSegment(chrBreakpointDeletion, getSegmentNumList(markDeletion)) == -1) {
	    				TranslocationWithoutUsingDerChr translocationWithoutUsingDerChr = parseTranslocationWithoutUsingDerChr(previousChrList, previousChrMarks, chr0, chr1, chrBreakpoint0, chrBreakpoint1);
	        			if (translocationWithoutUsingDerChr.isWrong()) {
	        				return false;
	        			} else {
	        				markDeletion = translocationWithoutUsingDerChr.getMarkDeletion();
	        				chrBreakpointDeletion = translocationWithoutUsingDerChr.getChrBreakpointDeletion();
	        				chrBreakpointInsertion = translocationWithoutUsingDerChr.getChrBreakpointInsertion();
	        				isDI = translocationWithoutUsingDerChr.isDI();
	        			} 	    				
	    			}
				}    			
    		} else {
    			TranslocationWithoutUsingDerChr translocationWithoutUsingDerChr = parseTranslocationWithoutUsingDerChr(previousChrList, previousChrMarks, chr0, chr1, chrBreakpoint0, chrBreakpoint1);
    			if (translocationWithoutUsingDerChr.isWrong()) {
    				return false;
    			} else {
    				markDeletion = translocationWithoutUsingDerChr.getMarkDeletion();
    				chrBreakpointDeletion = translocationWithoutUsingDerChr.getChrBreakpointDeletion();
    				chrBreakpointInsertion = translocationWithoutUsingDerChr.getChrBreakpointInsertion();
    				isDI = translocationWithoutUsingDerChr.isDI();
    			}
    		}
    	}
    	
    	// Exclude confusing karyotypes and validate chrBreakpointDeletion
    	if (!markDeletion.isEmpty()) {
    		List<List<String>> segmentsOfChrDeletion = getSegmentsOfChr(markDeletion);
    		List<Integer> segmentNumsOfMarkDeletion = getSegmentNumList(markDeletion);
    		if (isInMultipleSegments(chrBreakpointDeletion, segmentsOfChrDeletion)) {
    			return false;
    		} else {
    			segmentNumDeletion = findSegment(chrBreakpointDeletion, segmentNumsOfMarkDeletion);
    			if (segmentNumDeletion == -1) {
    				return false;
    			} else {
    				List<String> segment = segments.get(segmentNumDeletion);
    				if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpointDeletion, segment) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpointDeletion, segment)) {
    					return false;
    				}
    			}
    		}
    	}    	
    	
    	// Create a new mark for chrToBeInserted
    	if (!chrBreakpointInsertion.isEmpty() && markInsertion.isEmpty()) {
    		if (!chr0.equals(chr1)) {
        		markInsertion = getNextCommonMark();
    			commonMarkSet.add(markInsertion);
        	} else {
        		// chrToBeInserted must be chr1
    			markInsertion = getNextHomologousMark();
    			homologousMarkSet.add(markInsertion);
        	}
    		String chrInsertion = getChr(chrBreakpointInsertion);
        	markToChrMap.put(markInsertion, chrInsertion);
        	markSpecificLossGain.put(markInsertion, initializeChrLossGain(chrInsertion));
    	}
    	
		List<Integer> segmentNumsOfMarkDeletion = getSegmentNumList(markDeletion);
    	int segmentNum = findSegment(chrBreakpointDeletion, segmentNumsOfMarkDeletion);
		boolean isTranslocateOnTheLeft;
    	if (!isDualDeletion) {    		
    		int derCen0Segment = findSegmentContainingDerCen(derChr0, segments);
    		int derCen1Segment = findSegmentContainingDerCen(derChr1, segments);
    		// Exclude unfeasible translocation
    		if (derCen0Segment == -1 && derCen1Segment == -1) {
				return false;
			} else if (derCen0Segment != -1 && derCen1Segment != -1) {
				int max = Arrays.asList(derCen0Segment, derCen1Segment).stream().mapToInt(i -> i).max().getAsInt();
				int min = Arrays.asList(derCen0Segment, derCen1Segment).stream().mapToInt(i -> i).min().getAsInt();
				if (segmentNum > min && segmentNum < max) {
					return false;
				} else if (segmentNum == min) {
					if (getChrArm(chrBreakpointDeletion).equals(getChrArm(segments.get(min).get(1)))) {
						return false;
					} else {
						isTranslocateOnTheLeft = true;
					}
				} else if (segmentNum == max) {
					if (getChrArm(chrBreakpointDeletion).equals(getChrArm(segments.get(max).get(0)))) {
						return false;
					} else {
						isTranslocateOnTheLeft = false;
					}
				} else if (segmentNum < min) {
					isTranslocateOnTheLeft = true;
				} else {
					isTranslocateOnTheLeft = false;
				}				 
			} else {
				int derCenSegment = derCen0Segment == -1 ? derCen1Segment : derCen0Segment;
				if (segmentNum < derCenSegment) {
					isTranslocateOnTheLeft = true;
		    	} else if (segmentNum > derCenSegment) {
		    		isTranslocateOnTheLeft = false;
		    	} else if (getChrArm(chrBreakpointDeletion).equals(getChrArm(segments.get(derCenSegment).get(0)))) {
		    		isTranslocateOnTheLeft = true;
		    	} else {
		    		isTranslocateOnTheLeft = false;
				} 
			} // derCen0Segment == -1 || derCen1Segment == -1	
        	// Do the main task
    		List<List<String>> deletedSegments = new ArrayList<>();
    		translocate(chrBreakpointDeletion, segmentNumDeletion, markDeletion, chrBreakpointInsertion, markInsertion, deletedSegments, isTranslocateOnTheLeft);
    		// Record Loss and Gain
    		recordLoss(deletedSegments, derChr0GainedSegments, derChr1GainedSegments);
    		List<String> gainedSegment =  Arrays.asList(getChrArm(chrBreakpointInsertion) + "ter" + markInsertion, chrBreakpointInsertion + markInsertion);
        	recordGain(gainedSegment, markInsertion);
        	
    	} else if (currentParsingPlace == 0) {
    		List<List<String>> deletedSegments = new ArrayList<>();
        	String chr0Mark = chr0.equals(derChr0) ? "M0" : "M1";
    		String chr1Mark = chr0Mark.equals("M0") ? "M1" : "M0";
			deletedSegments.add(Arrays.asList(chrBreakpoint0 + chr0Mark, getChrArm(chrBreakpoint0) + "ter" + chr0Mark));
			deletedSegments.add(Arrays.asList(chrBreakpoint1 + chr1Mark, getChrArm(chrBreakpoint1) + "ter" + chr1Mark));
			// Record Loss 
			recordLoss(deletedSegments, derChr0GainedSegments, derChr1GainedSegments);
    	} else {
    		String derChr;
    		if (chrList.contains(derChr0) && chrList.contains(derChr1)) {
    			derChr = getChr(chrBreakpointDeletion);
    		} else {
    			derChr = markInsertion.equals("M0")? derChr1 : derChr0;
    		}    		
			int derCenSegment = findSegmentContainingDerCen(derChr, segments);
			if (derCenSegment == -1) {
				return false;
			}	
			
			if (segmentNum < derCenSegment) {
				isTranslocateOnTheLeft = true;
	    	} else if (segmentNum > derCenSegment) {
	    		isTranslocateOnTheLeft = false;
	    	} else if (getChrArm(chrBreakpointDeletion).equals(getChrArm(segments.get(derCenSegment).get(0)))) {
	    		isTranslocateOnTheLeft = true;
	    	} else {
	    		isTranslocateOnTheLeft = false;
			} 		
			// Do the main task
			List<List<String>> deletedSegments = new ArrayList<>();
			translocateForDualDeletion(chrBreakpointDeletion, segmentNumDeletion, markDeletion, chrBreakpointInsertion, markInsertion, deletedSegments, isTranslocateOnTheLeft);
			// Add the second deletion
			deletedSegments.add(Arrays.asList(getChrArm(chrBreakpointInsertion) + "ter" + markInsertion, chrBreakpointInsertion + markInsertion));
			// Record Loss 
			recordLoss(deletedSegments, derChr0GainedSegments, derChr1GainedSegments);
			
			// Finally, adjust the order the derChrs according to ChrComparator
			String derChrMin = Stream.of(derChr0, derChr1).sorted(new ChrComparator()).collect(Collectors.toList()).get(0);
			String derChrMax = Stream.of(derChr0, derChr1).sorted(new ChrComparator()).collect(Collectors.toList()).get(1);
			if (findSegmentContainingDerCen(derChrMax, segments) < findSegmentContainingDerCen(derChrMin, segments)) {
				Collections.reverse(rawStringList);
				segments = getSegments(rawStringList);
			}
    	}
		
		// Record ParsingOrder_Marks map
		if (!isDualDeletion) {
			if (isDI) {
				parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(markDeletion, markInsertion));
			} else {
				parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(markInsertion, markDeletion));
			}
		} else if (chrList.contains(derChr0) && chrList.contains(derChr1)) {
			String chr0Mark = chr0.equals(derChr0) ? "M0" : "M1";
			String chr1Mark = chr0Mark.equals("M0") ? "M1" : "M0";
			parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(chr0Mark, chr1Mark));
		} else {
			String derChr = chrList.contains(derChr0)? derChr0 : derChr1;
			if (chrList.indexOf(derChr) == 0) {
				parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(markInsertion, markDeletion));
			} else {
				parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(markDeletion, markInsertion));
			}
		}
		
    	return true;
	}
	
	protected TranslocationWithoutUsingDerChr parseTranslocationWithoutUsingDerChr(List<String> previousChrList, List<String> previousChrMarks, String chr0, String chr1, String chrBreakpoint0, String chrBreakpoint1) {
		TranslocationWithoutUsingDerChr translocationWithoutUsingDerChr = new TranslocationWithoutUsingDerChr();
		boolean isWrong = false, isDI = false;
		String markDeletion = null;
		String chrBreakpointDeletion = null, chrBreakpointInsertion = null;
		if (chr0.equals(chr1)) {
			if (previousChrList.contains(chr0)) {
				markDeletion = previousChrMarks.get(previousChrList.indexOf(chr0));
				if (!markDeletion.startsWith("M")) {
					isWrong = true;
				}
				chrBreakpointDeletion = chrBreakpoint0;
				chrBreakpointInsertion = chrBreakpoint1;
				isDI = true;
			} else {
				isWrong = true;
			}
		} else {
			if (previousChrList.contains(chr0) && previousChrList.contains(chr1)) {
				String chr0Mark = previousChrMarks.get(previousChrList.indexOf(chr0));
				String chr1Mark = previousChrMarks.get(previousChrList.indexOf(chr1));
				if ((chr0Mark == null || !chr0Mark.startsWith("M")) && chr1Mark.startsWith("M")) {
					markDeletion = chr1Mark; // Impossible to appear
					chrBreakpointDeletion = chrBreakpoint1;
					chrBreakpointInsertion = chrBreakpoint0;
					isDI = false;
    			} else if ((chr1Mark == null || !chr1Mark.startsWith("M")) && chr0Mark.startsWith("M")) {
    				markDeletion = chr0Mark; // Impossible to appear
    				chrBreakpointDeletion = chrBreakpoint0;
    				chrBreakpointInsertion = chrBreakpoint1;
    				isDI = true;
    			} else if ((chr0Mark == null || !chr0Mark.startsWith("M")) && (chr1Mark == null || !chr1Mark.startsWith("M"))) {
    				isWrong = true; // Impossible to appear
    			} else {
    				int segmentNum0 = findSegment(chrBreakpoint0, getSegmentNumList(chr0Mark));
    				int segmentNum1 = findSegment(chrBreakpoint1, getSegmentNumList(chr1Mark));
    				if (segmentNum0 == -1 && segmentNum1 == -1) {
    					isWrong = true;
    				} else if (segmentNum0 != -1 && segmentNum1 == -1) {
    					markDeletion = chr0Mark;
    					chrBreakpointDeletion = chrBreakpoint0;
    					chrBreakpointInsertion = chrBreakpoint1;
    					isDI = true;
    				} else if (segmentNum0 == -1 && segmentNum1 != -1) {
    					markDeletion = chr1Mark;
    					chrBreakpointDeletion = chrBreakpoint1;
    					chrBreakpointInsertion = chrBreakpoint0;
    					isDI = false;
    				} else {
    					isWrong = true; // It may be chrBreakpoint0 in multiple segments of mark0 while chrBreakpoint1 in unique segment of mark1, I still think it's wrong. Debatable! 
    				} // segmentNum0 != -1 && segmentNum1 != -1
    			} 
				
			} else if (previousChrList.contains(chr0) || previousChrList.contains(chr1)) {	
				String chrDeletion = previousChrList.contains(chr0)? chr0 : chr1;
				markDeletion = previousChrMarks.get(previousChrList.indexOf(chrDeletion));
    			if (markDeletion == null || !markDeletion.startsWith("M")) {
    				isWrong = true;
    			}
    			isDI = chrDeletion.equals(chr0);
    			chrBreakpointDeletion = isDI? chrBreakpoint0 : chrBreakpoint1;
    			chrBreakpointInsertion = isDI? chrBreakpoint1 : chrBreakpoint0;
			} else {
				isWrong = true;
			}
		}
		
		if (isWrong) {
			translocationWithoutUsingDerChr.setWrong(true);
		} else {
			translocationWithoutUsingDerChr.setDI(isDI);
			translocationWithoutUsingDerChr.setMarkDeletion(markDeletion);
			translocationWithoutUsingDerChr.setChrBreakpointDeletion(chrBreakpointDeletion);
			translocationWithoutUsingDerChr.setChrBreakpointInsertion(chrBreakpointInsertion);
		}
		return translocationWithoutUsingDerChr;
	}
	
	protected void translocate(String chrBreakpointDeletion, int segmentNumDeletion, String markDeletion, String chrBreakpointInsertion, String markInsertion, List<List<String>> deletedSegments, boolean isTranslocateOnTheLeft) {
		List<String> deletedChrBreakpoints;
		int offset = getOffsetInRawStringList(segmentNumDeletion);
		List<String> sourceList;
		String chrArmInsertion = getChrArm(chrBreakpointInsertion);
		if (isTranslocateOnTheLeft) {
			sourceList = Arrays.asList(chrArmInsertion + "ter" + markInsertion, chrBreakpointInsertion + markInsertion, chrBreakpointDeletion + markDeletion);
			deletedChrBreakpoints = new ArrayList<>(rawStringList.subList(0, offset));
			deletedChrBreakpoints.add(chrBreakpointDeletion + markDeletion);
			rawStringList.subList(0, offset).clear();  
			rawStringList.addAll(0, sourceList);
		} else {
			sourceList = Arrays.asList(chrBreakpointDeletion + markDeletion, chrBreakpointInsertion + markInsertion, chrArmInsertion + "ter" + markInsertion);
			deletedChrBreakpoints = new ArrayList<>(rawStringList.subList(offset, rawStringList.size()));
			deletedChrBreakpoints.add(0, chrBreakpointDeletion + markDeletion);
			rawStringList.subList(offset, rawStringList.size()).clear();
			rawStringList.addAll(sourceList);
		}
		segments = getSegments(rawStringList);
		deletedSegments.addAll(getSegments(deletedChrBreakpoints));
	}
	
	protected void translocateForDualDeletion(String chrBreakpointDeletion, int segmentNumDeletion, String markDeletion, String chrBreakpointInsertion, String markInsertion, List<List<String>> deletedSegments, boolean isTranslocateOnTheLeft) {
		List<String> deletedChrBreakpoints;
		int offset = getOffsetInRawStringList(segmentNumDeletion);
		List<String> sourceList;
		String chrArmInsertion = getOppositeChrArm(getChrArm(chrBreakpointInsertion));
		if (isTranslocateOnTheLeft) {
			sourceList = Arrays.asList(chrArmInsertion + "ter" + markInsertion, chrBreakpointInsertion + markInsertion, chrBreakpointDeletion + markDeletion);
			deletedChrBreakpoints = new ArrayList<>(rawStringList.subList(0, offset));
			deletedChrBreakpoints.add(chrBreakpointDeletion + markDeletion);
			rawStringList.subList(0, offset).clear();  
			rawStringList.addAll(0, sourceList);
		} else {
			sourceList = Arrays.asList(chrBreakpointDeletion + markDeletion, chrBreakpointInsertion + markInsertion, chrArmInsertion + "ter" + markInsertion);
			deletedChrBreakpoints = new ArrayList<>(rawStringList.subList(offset, rawStringList.size()));
			deletedChrBreakpoints.add(0, chrBreakpointDeletion + markDeletion);
			rawStringList.subList(offset, rawStringList.size()).clear();
			rawStringList.addAll(sourceList);
		}
		segments = getSegments(rawStringList);
		deletedSegments.addAll(getSegments(deletedChrBreakpoints));
	}
	
	protected void recordLoss(List<List<String>> deletedSegments, List<List<String>> derChr0GainedSegments, List<List<String>> derChr1GainedSegments) {
    	for (List<String> segment: deletedSegments) {
    		// It may contain hsr segments.
    		if (segment.size() == 1) {
    			continue;
    		}
    		
    		if (segment.get(0).endsWith("M0") || segment.get(0).endsWith("M1")) {
    			boolean m0 = segment.get(0).endsWith("M0");
    			List<List<String>> derChrGainedSegments = m0? derChr0GainedSegments : derChr1GainedSegments;
    			List<String> derChrBandsList = m0? getChrBandsList(derChr0): getChrBandsList(derChr1);
    			List<Integer> derChrLoss = m0? markSpecificLossGain.get("M0"): markSpecificLossGain.get("M1");   	
    			List<String> gainedSegment =  updateDerChrGainedSegments(derChrGainedSegments, getTranslatedSegment(segment));
    			List<String> segmentBands = getAllSegmentBands(segment);
    			if (gainedSegment != null) {
    			 	segmentBands.removeAll(getAllSegmentBands(gainedSegment));
    			}
    			for (String segmentBand: segmentBands) {
    				int index = derChrBandsList.indexOf(segmentBand);
    				if (derChrLoss.get(index) == 0) {
    					derChrLoss.set(index, 1);
    				}
    			}
    		} else {
    			String mark = getMark(segment);
    			List<Integer> chrGain = markSpecificLossGain.get(mark);
    			String chr = markToChrMap.get(mark);
    			List<String> chrBandsList = getChrBandsList(chr);
    			List<String> segmentBands = getAllSegmentBands(segment);
    			for (String segmentBand: segmentBands) {
    				int index = chrBandsList.indexOf(segmentBand);
    				if (chrGain.get(index) > 0) {
    					chrGain.set(index, chrGain.get(index) - 1);
    				}
    			}
    		}
    	}
    }
	
    protected void recordDuplicate(List<List<String>> duplicatedSegments, List<List<String>> derChr0GainedSegments, List<List<String>> derChr1GainedSegments) {
    	for (List<String> segment: duplicatedSegments) {
    		// It may contain hsr segments.
    		if (segment.size() == 1) {
    			continue;
    		}
    		
    		if (segment.get(0).endsWith("M0") || segment.get(0).endsWith("M1")) {
    			if (segment.get(0).endsWith("M0")) {
    				derChr0GainedSegments.add(segment);
    			} else {
    				derChr1GainedSegments.add(segment);
    			} 
    		} else {
    			String mark = getMark(segment);
    			List<Integer> chrGain = markSpecificLossGain.get(mark);
    			String chr = markToChrMap.get(mark);
    			List<String> chrBandsList = getChrBandsList(chr);
    			List<String> segmentBands = getAllSegmentBands(segment);
    			for (String segmentBand: segmentBands) {
    				int index = chrBandsList.indexOf(segmentBand);
    				chrGain.set(index, chrGain.get(index) + 1);
    			}
    		}
    	}
    }
	
    public List<List<Integer>> getDerKaryotypeLGF() {
    	List<List<Integer>> derKaryotypeLGF = new ArrayList<>();
    	int[] chrsOffset = getChrsOffset(chrArmArrays);
    	
        List<String> derMarkList = Arrays.asList("M0", "M1");
        List<String> derChrList = Arrays.asList(derChr0, derChr1);
        List<List<List<String>>> derChrGainedSegmentsList = Arrays.asList(derChr0GainedSegments, derChr1GainedSegments);
        IntStream.range(0, 2).forEach(i -> {
								        	List<Integer> derChrLoss = markSpecificLossGain.get(derMarkList.get(i));
								        	String derChr = derChrList.get(i);
								        	int derchrOffset = chrsOffset[getChrBlockIndex(derChr)];
								        	IntStream.range(0, derChrLoss.size())
								        	         .filter(j -> derChrLoss.get(j) == 1)
								        	         .forEach(j -> karyotypeLossOutcome.set(derchrOffset + j, 1 + karyotypeLossOutcome.get(derchrOffset + j)));
								        	List<Integer> derChrGain = recordDerchrGainedSegemnts(derChrGainedSegmentsList.get(i), derChr);
								        	IntStream.range(0, derChrGain.size())
								        			 .filter(j -> derChrGain.get(j) > 0)
								        			 .forEach(j -> karyotypeGainOutcome.set(derchrOffset + j, derChrGain.get(j) + karyotypeGainOutcome.get(derchrOffset + j)));
									       }
						             );    	
    	
        for (String mark: commonMarkSet) {
    		if (mark.equals("M0") || mark.equals("M1")) {
    			continue;
    		}
    		String chr = markToChrMap.get(mark);
    		List<Integer> chrGain = markSpecificLossGain.get(mark);
    		int chrOffset = chrsOffset[getChrBlockIndex(chr)];
    		for (int i = 0; i < chrGain.size(); i++) {
    			if (chrGain.get(i) > 0) {
        			karyotypeGainOutcome.set(chrOffset + i, karyotypeGainOutcome.get(chrOffset + i) + chrGain.get(i));
        		}
    		}
    	}
    	
    	for (String mark: homologousMarkSet) {
    		String chr = markToChrMap.get(mark);
    		List<Integer> chrGain = markSpecificLossGain.get(mark);
    		int chrOffset = chrsOffset[getChrBlockIndex(chr)];
    		for (int i = 0; i < chrGain.size(); i++) {
    			if (chrGain.get(i) > 0) {
        			karyotypeGainOutcome.set(chrOffset + i, karyotypeGainOutcome.get(chrOffset + i) + chrGain.get(i));
        		}
    		}
    	}
    	
    	List<String> fusionPointsList = getFusionPoints();
		for (String fusionPoint: fusionPointsList) {
			int index;
			if (chrToIndexMap.get(fusionPoint) == null) {
				for (String subband: getAllKeys(fusionPoint)) {
					index = chrToIndexMap.get(subband);
					karyotypeFusionOutcome.set(index, karyotypeFusionOutcome.get(index) + 1);
				}
			} else {
				index = chrToIndexMap.get(fusionPoint);
				karyotypeFusionOutcome.set(index, karyotypeFusionOutcome.get(index) + 1);
			}
		}
    	
    	derKaryotypeLGF.add(karyotypeLossOutcome);
    	derKaryotypeLGF.add(karyotypeGainOutcome);
    	derKaryotypeLGF.add(karyotypeFusionOutcome);
    	
    	return derKaryotypeLGF;
    }

    protected String getDetailedSystem() {
    	StringBuilder sb = new StringBuilder();
    	int count = 0;
    	for (List<String> segment: segments) {
    		List<String> translatedSegment = getTranslatedSegmentII(segment);
    		if (translatedSegment.size() == 1) {
    			sb.append(translatedSegment.get(0));
    		} else if (translatedSegment.get(0).equals(translatedSegment.get(1))) { 
    			sb.append(translatedSegment.get(0));
    		} else {
    			sb.append(translatedSegment.get(0) + "->" + translatedSegment.get(1));
    		}
    		count++;
    		if (count < segments.size()) {
    			sb.append("::");
    		}    		
    	}   	
    	
    	if (subeventNatureList.contains("r")) {
    		sb.insert(0, "::");
    		sb.append("::");
    	} else {
    		if (segments.get(0).size() > 1 && !getTranslatedSegmentII(segments.get(0)).get(0).endsWith("ter")) {
    			sb.insert(0, ":");
    		}
    		if (segments.get(segments.size() - 1).size() > 1 && !getTranslatedSegmentII(segments.get(segments.size() - 1)).get(1).endsWith("ter")) {
    			sb.append(":");
    		}
    	} 
    	
    	List<String> sortedDerChr = Stream.of(derChr0, derChr1).sorted(new ChrComparator()).collect(Collectors.toList());
    	sb.insert(0, "der(" + sortedDerChr.get(0) + ";" + sortedDerChr.get(1) + ")(");
    	sb.append(")");
    	
    	return sb.toString();
    }

}
