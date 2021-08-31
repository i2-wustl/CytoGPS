package validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import business.DerEvent;
import business.Event;
import toolkit.CenComparator;

/**
 * @author Lin Zhang
 * Programmer / Software Development Engineer
 * Institute for Informatics
 * Washington University School of Medicine in St. Louis
 * 
 * Date: August 30, 2021 
 */
public class DicDerivativeValidationDifferentDerChrWithBreakpoints extends DicDerivativeValidationDifferentDerChrNoBreakpoints {
	
	protected List<List<String>> derCens = new ArrayList<>();
	private String sortedDerCen0Mark, sortedDerCen1Mark;
	protected String derCen0, derCen1;
	protected String derChrArm0, derChrArm1;
	
	public DicDerivativeValidationDifferentDerChrWithBreakpoints(DerEvent e) {
		super(e);
		derCens = e.getBreakpointsFullName(e.getChrList(), e.getBreakpoints());
		derCen0 = derCens.get(0).get(0);
		derCen1 = derCens.get(1).get(0);
		derChrArm0 = getChrArm(derCen0);
		derChrArm1 = getChrArm(derCen1);
		if (!derChr0.equals(derChr1)) {
			List<String> sortedDerCens = derCens.stream().flatMap(list -> list.stream()).sorted(new CenComparator()).collect(Collectors.toList());
			String sortedDerCen0 = sortedDerCens.get(0);
			String sortedDerCen1 = sortedDerCens.get(1);
			String sortedDerCenArm0 = getChrArm(sortedDerCen0);
			String sortedDerCenArm1 = getChrArm(sortedDerCen1);
			boolean originalOrder = getChr(sortedDerCen0).equals(derChr0);
			sortedDerCen0Mark = originalOrder? "M0" : "M1";
			sortedDerCen1Mark = originalOrder? "M1" : "M0";		
			rawStringList.add(sortedDerCenArm0 + "ter" + sortedDerCen0Mark);
			rawStringList.add(sortedDerCen0 + sortedDerCen0Mark);
			rawStringList.add(sortedDerCen1 + sortedDerCen1Mark);
			rawStringList.add(sortedDerCenArm1 + "ter" + sortedDerCen1Mark);
		} else {	
			rawStringList.add(derChrArm0 + "ter" + "M0");
			rawStringList.add(derCen0 + "M0");
			rawStringList.add(derCen1 + "M1");
			rawStringList.add(derChrArm1 + "ter" + "M1");
		}
		segments = getSegments(rawStringList);
	}
	
	public DerivativeValidationOutcome getDicDerivativeValidationOutcome() {
		boolean validDerivativeChr = true;
		outer:
		for (int i: eventIndexList) {
			Event subevent = subevents.get(i);
			if (!subevent.isUncertainEvent()) {
				List<List<String>> breakpointsFullName = subevent.getBreakpointsFullName(subevent.getChrList(), subevent.getBreakpoints());
				switch (subevent.getNature()) {
					case "add": {							
						if (!isValidAdd(breakpointsFullName, derChr0, derChr1, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "del": {							
						if (!isValidDel(breakpointsFullName, derChr0, derChr1, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "dup": {							
						if (!isValidDup(breakpointsFullName, derChr0, derChr1, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "trp": {							
						if (!isValidTrp(breakpointsFullName, derChr0, derChr1, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "qdp": {							
						if (!isValidQdp(breakpointsFullName, derChr0, derChr1, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "ins": {							
						if (!isValidIns(breakpointsFullName, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "inv": {							
						if (!isValidInv(breakpointsFullName, derChr0, derChr1, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "hsr": {
						if (!isValidHsr(breakpointsFullName, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "t": {
						if (!isValidT(breakpointsFullName, derChr0, derChr1, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
				}
			}
		}
		
		// Examine whether both centromeres of derChr0 and derChr1 are contained in the deatiled system
    	int derCen0Segment = findSegmentContainingDerCen(derChr0, segments);
		int derCen1Segment = derChr0.equals(derChr1)? findSegmentContainingDerCen(derChr0, segments.subList(derCen0Segment + 1, segments.size())) : findSegmentContainingDerCen(derChr1, segments);
		if (derCen0Segment == -1 || derCen1Segment == -1) {
			validDerivativeChr = false;
		}
        
		if (validDerivativeChr) {
			// Record Loss of the opposite of derChrArms
			List<List<String>> deletedSegments = new ArrayList<>();
			Stream.of(derChrArm0, derChrArm1)
			      .forEach(derChrArm -> {
											String oppositeArm = getOppositeChrArm(derChrArm);
											String mark = derChrArm.equals(derChrArm0)? "M0" : "M1";
											deletedSegments.add(Arrays.asList(oppositeArm + "ter" + mark, oppositeArm + "10" + mark));
										});	
			
			recordLoss(deletedSegments, derChr0GainedSegments, derChr1GainedSegments);	
			return new DerivativeValidationOutcome(validDerivativeChr, segments, getDetailedSystem(), getDerKaryotypeLGF());
		} else {
			return new DerivativeValidationOutcome(validDerivativeChr, segments, null, null);
		}
	}
	
	protected int findSegmentContainingDerCen(String derChr, List<List<String>> segments) {
		int index = 0;
		for (List<String> segment: segments) {
			if (segment.size() == 2 && (isInSegment(derChr + "p10", segment) || isInSegment(derChr + "q10", segment))) {
				return index;
			} 
			index++;
		}
		return -1;
	}
	
	private boolean isValidAdd(List<List<String>> breakpointsFullName, String derChr0, String derChr1, List<Integer> correctParsingOrder, int currentParsingPlace) {
		String chrBreakpoint = breakpointsFullName.get(0).get(0);
		String chr = getChr(chrBreakpoint);		
		String chrArm = getChrArm(chrBreakpoint);
		String mark;
		
		boolean notAtPlace0 = currentParsingPlace > 0;
		Event previousEvent = notAtPlace0? subevents.get(correctParsingOrder.get(currentParsingPlace - 1)) : null;
		List<String> previousChrList = notAtPlace0? previousEvent.getChrList() : null;
		List<String> previousChrMarks = notAtPlace0? parsingOrderMarksMap.get(currentParsingPlace - 1) : null;
		
		if (chrArm.equals(derChrArm0) || chrArm.equals(derChrArm1)) {
			boolean isDerChrArm0 = chrArm.equals(derChrArm0);
			mark = isDerChrArm0? "M0" : "M1";
			if (findSegment(chrBreakpoint, getSegmentNumList(mark)) == -1) {
				if (!notAtPlace0) {
					return false;
				}
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
			if (!notAtPlace0) {
				return false;
			}
			int chrIndex = previousChrList.indexOf(chr);
			if (chrIndex == -1) {
				return false;
			}
			mark = previousChrMarks.get(chrIndex);
			if (!mark.startsWith("M")) {
				return false;
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
				int derCen1Segment = derChr0.equals(derChr1)? findSegmentContainingDerCen(derChr0, segments.subList(derCen0Segment + 1, segments.size())) : findSegmentContainingDerCen(derChr1, segments);
				if (derCen0Segment == -1 || derCen1Segment == -1) {
					return false;
				} 
				if (derChr0.equals(derChr1)) {
					derCen1Segment += derCen0Segment + 1;
				}				
				int max = Arrays.asList(derCen0Segment, derCen1Segment).stream().mapToInt(i -> i).max().getAsInt(); // int max = Arrays.asList(derCen0Segment, derCen1Segment).stream().max(Comparator.comparingInt(i -> i)).get();
				int min = Arrays.asList(derCen0Segment, derCen1Segment).stream().mapToInt(i -> i).min().getAsInt();					
				if (segmentNum > min && segmentNum < max) {
					return false;
				} else if (segmentNum <= min) {
					addLHS(chrBreakpoint, mark, segmentNum, deletedSegments);
				} else {
					addRHS(chrBreakpoint, mark, segmentNum, deletedSegments);
				}
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
		String chrArm = getChrArm(chrBreakpoint0);
		boolean isTerminalDeletion = breakpointsFullName.get(0).size() == 1;
		String chrBreakpoint1 = isTerminalDeletion? "" : breakpointsFullName.get(0).get(1);
		String mark;
		
		boolean notAtPlace0 = currentParsingPlace > 0;
		Event previousEvent = notAtPlace0? subevents.get(correctParsingOrder.get(currentParsingPlace - 1)) : null;
		List<String> previousChrList = notAtPlace0? previousEvent.getChrList() : null;
		List<String> previousChrMarks = notAtPlace0? parsingOrderMarksMap.get(currentParsingPlace - 1) : null;
		
		if (chrArm.equals(derChrArm0) || chrArm.equals(derChrArm1)) {
			boolean isDerChrArm0 = chrArm.equals(derChrArm0);
			mark = isDerChrArm0? "M0" : "M1";
			
			boolean chrBreakpointNotFoundInMainMark = isTerminalDeletion?
					findSegment(chrBreakpoint0, getSegmentNumList(mark)) == -1 :
						findSegment(chrBreakpoint0, getSegmentNumList(mark)) == -1 || findSegment(chrBreakpoint1, getSegmentNumList(mark)) == -1;
			
			if (chrBreakpointNotFoundInMainMark) {
				if (!notAtPlace0) {
					return false;
				}
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
			if (!notAtPlace0) {
				return false;
			}
			int chrIndex = previousChrList.indexOf(chr);
			if (chrIndex == -1) {
				return false;
			}
			mark = previousChrMarks.get(chrIndex);
			if (!mark.startsWith("M")) {
				return false;
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
					int derCen1Segment = derChr0.equals(derChr1)? findSegmentContainingDerCen(derChr0, segments.subList(derCen0Segment + 1, segments.size())) : findSegmentContainingDerCen(derChr1, segments);
					if (derCen0Segment == -1 || derCen1Segment == -1) {
						return false;
					} 
					if (derChr0.equals(derChr1)) {
						derCen1Segment += derCen0Segment + 1;
					}				
					int max = Arrays.asList(derCen0Segment, derCen1Segment).stream().mapToInt(i -> i).max().getAsInt(); // int max = Arrays.asList(derCen0Segment, derCen1Segment).stream().max(Comparator.comparingInt(i -> i)).get();
					int min = Arrays.asList(derCen0Segment, derCen1Segment).stream().mapToInt(i -> i).min().getAsInt();					
					if (segmentNum > min && segmentNum < max) {
						return false;
					} else if (segmentNum <= min) {
						delLHS(chrBreakpoint0, mark, segmentNum, deletedSegments);
					} else {
						delRHS(chrBreakpoint0, mark, segmentNum, deletedSegments);
					}
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
//			if (segmentNum0 == segmentNum1 && !getChrArm(chrBreakpoint0).equals(getChrArm(chrBreakpoint1))) {
//				return false;
//			} 
			
			List<List<String>> deletedSegments = new ArrayList<>();
			if (segmentNum0 == segmentNum1) {
    			delInOneSegment(chrBreakpoints, segmentNum0, mark, deletedSegments);
    		} else if (segmentNum0 < segmentNum1) {
    			delInMoreThanOneSegment(chrBreakpoint0, chrBreakpoint1, segmentNum0, segmentNum1, mark, deletedSegments);	    			
    		} else {
    			delInMoreThanOneSegment(chrBreakpoint1, chrBreakpoint0, segmentNum1, segmentNum0, mark, deletedSegments);
    		}
			
			// Check whether derChr's cen has been deleted. 
			// This is necessary. For example: 45,XY,der(8;9)(q10;q10)t(8;9)(q24.1;q32)del(8)(q13q24.2)
			if (mark.equals("M0") || mark.equals("M1")) {
				int derCen0Segment = findSegmentContainingDerCen(derChr0, segments);
				if (derCen0Segment == -1) {
					return false;
				}
				if (!derChr0.equals(derChr1)) {
					if (findSegmentContainingDerCen(derChr1, segments) == -1) {
						return false;
					}
				} else {
					if (findSegmentContainingDerCen(derChr0, segments.subList(derCen0Segment + 1, segments.size())) == -1) {
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
	
	// Assume chrBreakpoints are in different segments with segmentNumL < segmentNumR and derCenSegmentL < derCenSegmentR
	protected boolean isDerCenDuplicated(int segmentNumL, int segmentNumR, int derCenSegmentL, int derCenSegmentR) {
		if (segmentNumL <= derCenSegmentL && derCenSegmentR <= segmentNumR) {
			return true;
		} // Example: 45,XY,der(8;9)(q10;q10)t(8;9)(q24.1;q32)dup(8)(q22q24.2)
		return false;
	}
	
	private boolean isValidDup(List<List<String>> breakpointsFullName, String derChr0, String derChr1, List<Integer> correctParsingOrder, int currentParsingPlace) {
		String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
		String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
		String chr = getChr(chrBreakpoint0);		
		String chrArm = getChrArm(chrBreakpoint0);		
		String mark;
		
		boolean notAtPlace0 = currentParsingPlace > 0;
		Event previousEvent = notAtPlace0? subevents.get(correctParsingOrder.get(currentParsingPlace - 1)) : null;
		List<String> previousChrList = notAtPlace0? previousEvent.getChrList() : null;
		List<String> previousChrMarks = notAtPlace0? parsingOrderMarksMap.get(currentParsingPlace - 1) : null;
		
		if (chrArm.equals(derChrArm0) || chrArm.equals(derChrArm1)) {
			boolean isDerChrArm0 = chrArm.equals(derChrArm0);
			mark = isDerChrArm0? "M0" : "M1";
			
			boolean chrBreakpointNotFoundInMainMark = findSegment(chrBreakpoint0, getSegmentNumList(mark)) == -1 || findSegment(chrBreakpoint1, getSegmentNumList(mark)) == -1;
			
			if (chrBreakpointNotFoundInMainMark) {
				if (!notAtPlace0) {
					return false;
				}
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
			if (!notAtPlace0) {
				return false;
			}
			int chrIndex = previousChrList.indexOf(chr);
			if (chrIndex == -1) {
				return false;
			}
			mark = previousChrMarks.get(chrIndex);
			if (!mark.startsWith("M")) {
				return false;
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
			}
			List<String> segment0 = segments.get(segmentNum0);
			List<String> segment1 = segments.get(segmentNum1);
			if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint1, segment1) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint1, segment1)) {
		    	return false;
		    }
			// Check whether derChr's cen has been duplicated
            if (mark.equals("M0") || mark.equals("M1")) {
				if (segmentNum0 == segmentNum1) {
					if (!getChrArm(chrBreakpoint0).equals(getChrArm(chrBreakpoint1))) {
	    				return false;
	    			}
				} else {
					int derCen0Segment = findSegmentContainingDerCen(derChr0, segments);
					int derCen1Segment = !derChr0.equals(derChr1)? findSegmentContainingDerCen(derChr1, segments) : findSegmentContainingDerCen(derChr0, segments.subList(derCen0Segment + 1, segments.size())) + derCen0Segment + 1;
					int derCenSegmentL = Integer.min(derCen0Segment, derCen1Segment);
					int derCenSegmentR = Integer.max(derCen0Segment, derCen1Segment);
					if (segmentNum0 < segmentNum1) {
						if (isDerCenDuplicated(segmentNum0, segmentNum1, derCenSegmentL, derCenSegmentR)) {
							return false;
						}
					} else {
						if (isDerCenDuplicated(segmentNum1, segmentNum0, derCenSegmentL, derCenSegmentR)) {
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
	
	private int findFurtherLeftBreakpoint(String chrBreakpoint0, String chrBreakpoint1, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark) {
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
			// Find the orientation of this segment by comparing both ends of this segment
			List<String> segment = segments.get(segmentNum0);
			if (segment.get(0).compareTo(segment.get(1)) < 0) {
				return chrBreakpoint0.compareTo(chrBreakpoint1) <= 0 ? 0 : 1;
			} else {
				return chrBreakpoint0.compareTo(chrBreakpoint1) <= 0 ? 1 : 0;
			}
		} else if (segmentNum0 < segmentNum1) {
			return 0;
		} else {
			return 1;
		}
	}
	
	private int getSegmentOfFurtherLeftBreakpoint(String chrBreakpoint0, String chrBreakpoint1, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark) {
		if (isInMultipleSegments(chrBreakpoint0, segmentsOfChr) || isInMultipleSegments(chrBreakpoint1, segmentsOfChr)) {
			// Then it must be in a unique segment containing both chrBreakpoints
			return getUniqueSegment(chrBreakpoint0, chrBreakpoint1, segmentsOfChr, segmentNumsOfMark);    			
		} else {
			int segmentNum0 = findSegment(chrBreakpoint0, segmentNumsOfMark);
			int segmentNum1 = findSegment(chrBreakpoint1, segmentNumsOfMark);
			return findFurtherLeftBreakpoint(chrBreakpoint0, chrBreakpoint1, segmentsOfChr, segmentNumsOfMark) == 0 ? segmentNum0 : segmentNum1 ;
		}
	}
	
	private int findFurtherLeftBreakpointOfDuplicationCopy(String chrBreakpoint0, String chrBreakpoint1, String derChr0, String derChr1, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark) {
		int leftBreakpointSegment = getSegmentOfFurtherLeftBreakpoint(chrBreakpoint0, chrBreakpoint1, segmentsOfChr, segmentNumsOfMark);
		int derCen0Segment = findSegmentContainingDerCen(derChr0, segments);
		int derCen1Segment = !derChr0.equals(derChr1)? findSegmentContainingDerCen(derChr1, segments) : findSegmentContainingDerCen(derChr0, segments.subList(derCen0Segment + 1, segments.size())) + derCen0Segment + 1;
		int minDerCenSegment = Integer.min(derCen0Segment, derCen1Segment);
		if (leftBreakpointSegment <= minDerCenSegment) {
			return 1;
		} else {			
			return 0; // Must be leftBreakpointSegment >= maxDerCenSegment. Note that it's impossible to have anything between minDerCenSegment and maxDerCenSegment
		}
	}
	
	// This is for duplication which happens inside a segment.
	protected void duplicateII(String chrBreakpoint0, String chrBreakpoint1, String derChr0, String derChr1, int segmentNum, String mark, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark, List<List<String>> duplicatedSegments) {
		int offset = getOffsetInRawStringList(segmentNum);
		List<String> segment = segments.get(segmentNum);
		
		int leftBreakpointN = findFurtherLeftBreakpoint(chrBreakpoint0, chrBreakpoint1, segmentsOfChr, segmentNumsOfMark);
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
	protected void duplicateIII(String chrBreakpoint0, String chrBreakpoint1, String derChr0, String derChr1, int segmentNum0, int segmentNum1, String mark, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark, List<List<String>> duplicatedSegments) {
		int segmentNumL = Integer.min(segmentNum0, segmentNum1);
		int segmentNumR = Integer.max(segmentNum0, segmentNum1);
		int offset = getOffsetInRawStringList(segmentNumR);
		
		int leftBreakpointN = findFurtherLeftBreakpoint(chrBreakpoint0, chrBreakpoint1, segmentsOfChr, segmentNumsOfMark);
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
		String chrArm = getChrArm(chrBreakpoint0);		
		String mark;
		
		boolean notAtPlace0 = currentParsingPlace > 0;
		Event previousEvent = notAtPlace0? subevents.get(correctParsingOrder.get(currentParsingPlace - 1)) : null;
		List<String> previousChrList = notAtPlace0? previousEvent.getChrList() : null;
		List<String> previousChrMarks = notAtPlace0? parsingOrderMarksMap.get(currentParsingPlace - 1) : null;
		
		if (chrArm.equals(derChrArm0) || chrArm.equals(derChrArm1)) {
			boolean isDerChrArm0 = chrArm.equals(derChrArm0);
			mark = isDerChrArm0? "M0" : "M1";
			
			boolean chrBreakpointNotFoundInMainMark = findSegment(chrBreakpoint0, getSegmentNumList(mark)) == -1 || findSegment(chrBreakpoint1, getSegmentNumList(mark)) == -1;
			
			if (chrBreakpointNotFoundInMainMark) {
				if (!notAtPlace0) {
					return false;
				}
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
			if (!notAtPlace0) {
				return false;
			}
			int chrIndex = previousChrList.indexOf(chr);
			if (chrIndex == -1) {
				return false;
			}
			mark = previousChrMarks.get(chrIndex);
			if (!mark.startsWith("M")) {
				return false;
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
			}
			List<String> segment0 = segments.get(segmentNum0);
			List<String> segment1 = segments.get(segmentNum1);
			if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint1, segment1) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint1, segment1)) {
		    	return false;
		    }
			// Check whether derChr's cen has been duplicated
            if (mark.equals("M0") || mark.equals("M1")) {
				if (segmentNum0 == segmentNum1) {
					if (!getChrArm(chrBreakpoint0).equals(getChrArm(chrBreakpoint1))) {
	    				return false;
	    			}
				} else {
					int derCen0Segment = findSegmentContainingDerCen(derChr0, segments);
					int derCen1Segment = !derChr0.equals(derChr1)? findSegmentContainingDerCen(derChr1, segments) : findSegmentContainingDerCen(derChr0, segments.subList(derCen0Segment + 1, segments.size())) + derCen0Segment + 1;
					int derCenSegmentL = Integer.min(derCen0Segment, derCen1Segment);
					int derCenSegmentR = Integer.max(derCen0Segment, derCen1Segment);
					if (segmentNum0 < segmentNum1) {
						if (isDerCenDuplicated(segmentNum0, segmentNum1, derCenSegmentL, derCenSegmentR)) {
							return false;
						}
					} else {
						if (isDerCenDuplicated(segmentNum1, segmentNum0, derCenSegmentL, derCenSegmentR)) {
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
	protected void triplicateII(String chrBreakpoint0, String chrBreakpoint1, String derChr0, String derChr1, int segmentNum, String mark, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark, List<List<String>> duplicatedSegments) {
    	int offset = getOffsetInRawStringList(segmentNum);
    	List<String> segment = segments.get(segmentNum);
    	
    	int leftBreakpointN = findFurtherLeftBreakpoint(chrBreakpoint0, chrBreakpoint1, segmentsOfChr, segmentNumsOfMark);
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
	protected void triplicateIII(String chrBreakpoint0, String chrBreakpoint1, String derChr0, String derChr1, int segmentNum0, int segmentNum1, String mark, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark, List<List<String>> duplicatedSegments) {
    	
    	int segmentNumL = Integer.min(segmentNum0, segmentNum1);
		int segmentNumR = Integer.max(segmentNum0, segmentNum1);
    	int offset = getOffsetInRawStringList(segmentNumR);
    	
    	int leftBreakpointN = findFurtherLeftBreakpoint(chrBreakpoint0, chrBreakpoint1, segmentsOfChr, segmentNumsOfMark);
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
		String chrArm = getChrArm(chrBreakpoint0);		
		String mark;
		
		boolean notAtPlace0 = currentParsingPlace > 0;
		Event previousEvent = notAtPlace0? subevents.get(correctParsingOrder.get(currentParsingPlace - 1)) : null;
		List<String> previousChrList = notAtPlace0? previousEvent.getChrList() : null;
		List<String> previousChrMarks = notAtPlace0? parsingOrderMarksMap.get(currentParsingPlace - 1) : null;
		
		if (chrArm.equals(derChrArm0) || chrArm.equals(derChrArm1)) {
			boolean isDerChrArm0 = chrArm.equals(derChrArm0);
			mark = isDerChrArm0? "M0" : "M1";
			
			boolean chrBreakpointNotFoundInMainMark = findSegment(chrBreakpoint0, getSegmentNumList(mark)) == -1 || findSegment(chrBreakpoint1, getSegmentNumList(mark)) == -1;
			
			if (chrBreakpointNotFoundInMainMark) {
				if (!notAtPlace0) {
					return false;
				}
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
			if (!notAtPlace0) {
				return false;
			}
			int chrIndex = previousChrList.indexOf(chr);
			if (chrIndex == -1) {
				return false;
			}
			mark = previousChrMarks.get(chrIndex);
			if (!mark.startsWith("M")) {
				return false;
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
			}
			List<String> segment0 = segments.get(segmentNum0);
			List<String> segment1 = segments.get(segmentNum1);
			if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint1, segment1) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint1, segment1)) {
		    	return false;
		    }
			// Check whether derChr's cen has been duplicated
            if (mark.equals("M0") || mark.equals("M1")) {
				if (segmentNum0 == segmentNum1) {
					if (!getChrArm(chrBreakpoint0).equals(getChrArm(chrBreakpoint1))) {
	    				return false;
	    			}
				} else {
					int derCen0Segment = findSegmentContainingDerCen(derChr0, segments);
					int derCen1Segment = !derChr0.equals(derChr1)? findSegmentContainingDerCen(derChr1, segments) : findSegmentContainingDerCen(derChr0, segments.subList(derCen0Segment + 1, segments.size())) + derCen0Segment + 1;
					int derCenSegmentL = Integer.min(derCen0Segment, derCen1Segment);
					int derCenSegmentR = Integer.max(derCen0Segment, derCen1Segment);
					if (segmentNum0 < segmentNum1) {
						if (isDerCenDuplicated(segmentNum0, segmentNum1, derCenSegmentL, derCenSegmentR)) {
							return false;
						}
					} else {
						if (isDerCenDuplicated(segmentNum1, segmentNum0, derCenSegmentL, derCenSegmentR)) {
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
	protected void quadruplicateII(String chrBreakpoint0, String chrBreakpoint1, String derChr0, String derChr1, int segmentNum, String mark, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark, List<List<String>> duplicatedSegments) {
    	int offset = getOffsetInRawStringList(segmentNum);
    	List<String> segment = segments.get(segmentNum);
    	
    	int leftBreakpointN = findFurtherLeftBreakpoint(chrBreakpoint0, chrBreakpoint1, segmentsOfChr, segmentNumsOfMark);
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
	protected void quadruplicateIII(String chrBreakpoint0, String chrBreakpoint1, String derChr0, String derChr1, int segmentNum0, int segmentNum1, String mark, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark, List<List<String>> duplicatedSegments) {
    	
    	int segmentNumL = Integer.min(segmentNum0, segmentNum1);
		int segmentNumR = Integer.max(segmentNum0, segmentNum1);
    	int offset = getOffsetInRawStringList(segmentNumR);
    	
    	int leftBreakpointN = findFurtherLeftBreakpoint(chrBreakpoint0, chrBreakpoint1, segmentsOfChr, segmentNumsOfMark);
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
	
	private boolean isValidIns(List<List<String>> breakpointsFullName, List<Integer> correctParsingOrder, int currentParsingPlace) {
		
		boolean notAtPlace0 = currentParsingPlace > 0;
		Event previousEvent = notAtPlace0? subevents.get(correctParsingOrder.get(currentParsingPlace - 1)) : null;
		List<String> previousChrList = notAtPlace0? previousEvent.getChrList() : null;
		List<String> previousChrMarks = notAtPlace0? parsingOrderMarksMap.get(currentParsingPlace - 1) : null;
		
		// We have either within-chromosome insertion or between-chromosome insertion
    	if (breakpointsFullName.size() == 1) {
    		String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
    		String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
    		String chrBreakpoint2 = breakpointsFullName.get(0).get(2);
    		
    		String chr = getChr(chrBreakpoint0);
    		String chrArm = getChrArm(chrBreakpoint0);
    		String mark;
    		
    		if (chrArm.equals(derChrArm0) || chrArm.equals(derChrArm1)) {
    			boolean isDerChrArm0 = chrArm.equals(derChrArm0);
    			mark = isDerChrArm0? "M0" : "M1";
    			
    			boolean chrBreakpointNotFoundInMainMark = findSegment(chrBreakpoint0, getSegmentNumList(mark)) == -1 || findSegment(chrBreakpoint1, getSegmentNumList(mark)) == -1 || findSegment(chrBreakpoint2, getSegmentNumList(mark)) == -1;
    			
    			if (chrBreakpointNotFoundInMainMark) {
    				if (!notAtPlace0) {
    					return false;
    				}
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
    			if (!notAtPlace0) {
    				return false;
    			}
    			int chrIndex = previousChrList.indexOf(chr);
    			if (chrIndex == -1) {
    				return false;
    			}
    			mark = previousChrMarks.get(chrIndex);
    			if (!mark.startsWith("M")) {
    				return false;
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
			insertWithinChr(chrBreakpoint0, chrBreakpoint1, chrBreakpoint2, segmentNum0, segmentNum1, segmentNum2, mark, segmentsOfChr, segmentNumsOfMark);
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
    		
    		String chrArm = getChrArm(chrBreakpoint0);
    		String mark0;
    		
    		if (chrArm.equals(derChrArm0) || chrArm.equals(derChrArm1)) {
    			boolean isDerChrArm0 = chrArm.equals(derChrArm0);
    			mark0 = isDerChrArm0? "M0" : "M1";
    			
    			boolean chrBreakpointNotFoundInMainMark = findSegment(chrBreakpoint0, getSegmentNumList(mark0)) == -1;
    			
    			if (chrBreakpointNotFoundInMainMark) {
    				if (!notAtPlace0) {
    					return false;
    				}
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
    			if (!notAtPlace0) {
    				return false;
    			}
    			int chrIndex = previousChrList.indexOf(chr0);
    			if (chrIndex == -1) {
    				return false;
    			}
    			mark0 = previousChrMarks.get(chrIndex);
    			if (!mark0.startsWith("M")) {
    				return false;
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
	
	// Order the three breakpoints inside a within-chr insertion
    protected List<String> orderChrBreakpoints(String chrBreakpoint0, String chrBreakpoint1, String chrBreakpoint2, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark) {
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
    	int leftEndOfInsertedSegment = findFurtherLeftBreakpoint(chrBreakpoint1, chrBreakpoint2, segmentsOfChr, segmentNumsOfMark) == 0 ? 1 : 2 ;
    	
    	List<String> list1 = Arrays.asList(chrBreakpoint1, chrBreakpoint2, chrBreakpoint0);
    	List<String> list2 = Arrays.asList(chrBreakpoint2, chrBreakpoint1, chrBreakpoint0);
    	List<String> list3 = Arrays.asList(chrBreakpoint0, chrBreakpoint1, chrBreakpoint2);
    	List<String> list4 = Arrays.asList(chrBreakpoint0, chrBreakpoint2, chrBreakpoint1);
    	
    	if (segmentNum0 == segmentNum1 && segmentNum1 == segmentNum2) {
    		// Order chrBreakpoint0 and chrBreakpoint1				
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
    
    protected boolean isInsertedPlaceToLeftOfCen(String chrBreakpoint0, List<Integer> segmentNumsOfMark0) {
    	int segmentNum0 = findSegment(chrBreakpoint0, segmentNumsOfMark0);    	
    	int derCen0Segment = findSegmentContainingDerCen(derChr0, segments);
    	int derCen1Segment = !derChr0.equals(derChr1)? findSegmentContainingDerCen(derChr1, segments) : findSegmentContainingDerCen(derChr0, segments.subList(derCen0Segment + 1, segments.size())) + derCen0Segment + 1;
    	// Note that we must have derCen0Segment != -1 && derCen1Segment != -1
		int minDerCenSegment = Integer.min(derCen0Segment, derCen1Segment);
		if (segmentNum0 <= minDerCenSegment) {
			return true;
		} else {
			return false; 
		} // Must be segmentNum0 >= maxDerCenSegment. It's impossible to have segmentNum0 > minDerCenSegment && segmentNum0 < maxDerCenSegment
    }
    
	protected void insertWithinChr(String chrBreakpoint0, String chrBreakpoint1, String chrBreakpoint2, int segmentNum0, int segmentNum1, int segmentNum2, String mark, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark) {
    	
    	int leftBreakpointN = findFurtherLeftBreakpoint(chrBreakpoint1, chrBreakpoint2, segmentsOfChr, segmentNumsOfMark);
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
		
		boolean insertToLeftOfCen = isInsertedPlaceToLeftOfCen(chrBreakpoint0, segmentNumsOfMark);
		
		if (segmentNum1 == segmentNum2) { 
			
			if (segmentNum0 == segmentNum1) {
				List<String> sourceList;
				List<String> orderedChrBreakpoints = orderChrBreakpoints(chrBreakpoint0, chrBreakpoint1, chrBreakpoint2, segmentsOfChr, segmentNumsOfMark);
				int indexOfInsertedPlace = orderedChrBreakpoints.indexOf(chrBreakpoint0); // either 0 or 2
				// I don't need to worry about the situation that chrBreakpoint0 will be (partially) the same with either chrBreakpoint1 or chrBreakpoint2
    			// b/c at this stage, it has passed the valid_inserted_segment test so that chrBreakpoint0 will not be inside the inserted segment.
				if (indexOfInsertedPlace == 0) {					
					if (insertToLeftOfCen) {
    					sourceList = Arrays.asList(chrBreakpoint0 + mark, chrBreakpoint2 + mark, chrBreakpoint1 + mark, chrBreakpoint0 + mark, leftBreakpoint, rightBreakpoint);
    				} else {
    					sourceList = Arrays.asList(chrBreakpoint0 + mark, chrBreakpoint1 + mark, chrBreakpoint2 + mark, chrBreakpoint0 + mark, leftBreakpoint, rightBreakpoint);
    				}
				} else {
					if (insertToLeftOfCen) {
						sourceList = Arrays.asList(leftBreakpoint, rightBreakpoint, chrBreakpoint0 + mark, chrBreakpoint2 + mark, chrBreakpoint1 + mark, chrBreakpoint0 + mark);
					} else {
						sourceList = Arrays.asList(leftBreakpoint, rightBreakpoint, chrBreakpoint0 + mark, chrBreakpoint1 + mark, chrBreakpoint2 + mark, chrBreakpoint0 + mark);
					}
				}
				rawStringList.addAll(offset0, sourceList); 
			} else {
				List<String> sourceList0;				
				if (insertToLeftOfCen) {
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
			if (insertToLeftOfCen) { 
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
			if (!insertToLeftOfCen) { 
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

	protected void insertBetweenChr(String chrBreakpoint0, String chrBreakpoint1, String chrBreakpoint2, int segmentNum0, String mark0, String mark1, List<Integer> segmentNumsOfMark0) {
		List<String> sourceList0 = new ArrayList<>();
		boolean insertToLeftOfCen = isInsertedPlaceToLeftOfCen(chrBreakpoint0, segmentNumsOfMark0);
		if (insertToLeftOfCen) {
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
		String chrArm0 = getChrArm(chrBreakpoint0);
		String chrArm1 = getChrArm(chrBreakpoint1);		
		if (!chrArm0.equals(chrArm1)) {
			return false;
		}
		String chrArm = chrArm0;
		String mark;
		
		boolean notAtPlace0 = currentParsingPlace > 0;
		Event previousEvent = notAtPlace0? subevents.get(correctParsingOrder.get(currentParsingPlace - 1)) : null;
		List<String> previousChrList = notAtPlace0? previousEvent.getChrList() : null;
		List<String> previousChrMarks = notAtPlace0? parsingOrderMarksMap.get(currentParsingPlace - 1) : null;
		
		if (chrArm.equals(derChrArm0) || chrArm.equals(derChrArm1)) {
			boolean isDerChrArm0 = chrArm.equals(derChrArm0);
			mark = isDerChrArm0? "M0" : "M1";
			
			boolean chrBreakpointNotFoundInMainMark = findSegment(chrBreakpoint0, getSegmentNumList(mark)) == -1 || findSegment(chrBreakpoint1, getSegmentNumList(mark)) == -1;
			
			if (chrBreakpointNotFoundInMainMark) {
				if (!notAtPlace0) {
					return false;
				}
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
			if (!notAtPlace0) {
				return false;
			}
			int chrIndex = previousChrList.indexOf(chr);
			if (chrIndex == -1) {
				return false;
			}
			mark = previousChrMarks.get(chrIndex);
			if (!mark.startsWith("M")) {
				return false;
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
			// Check whether derChr's cen has been inverted
            if (mark.equals("M0") || mark.equals("M1")) {
				if (segmentNum0 != segmentNum1) {					
					int derCen0Segment = findSegmentContainingDerCen(derChr0, segments);
					int derCen1Segment = !derChr0.equals(derChr1)? findSegmentContainingDerCen(derChr1, segments) : findSegmentContainingDerCen(derChr0, segments.subList(derCen0Segment + 1, segments.size())) + derCen0Segment + 1;
					int derCenSegmentL = Integer.min(derCen0Segment, derCen1Segment);
					int derCenSegmentR = Integer.max(derCen0Segment, derCen1Segment);
					if (segmentNum0 < segmentNum1) {
						if (isDerCenInverted(segmentNum0, segmentNum1, derCenSegmentL, derCenSegmentR)) {
							System.out.println("hi");
							return false;
						}
					} else {
						if (isDerCenInverted(segmentNum1, segmentNum0, derCenSegmentL, derCenSegmentR)) {
							return false;
						}
					}
				} 
            }			
		}
		
		invert(chrBreakpoint0, chrBreakpoint1, segmentNum0, segmentNum1, mark);
		parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(mark));
		
		return true; 
	}
	
	protected boolean isDerCenInverted(int segmentNumL, int segmentNumR, int derCenSegmentL, int derCenSegmentR) {
		if (segmentNumL <= derCenSegmentL && derCenSegmentR <= segmentNumR) {
			return true;
		} // Example: 45,XY,der(8;9)(q10;q10)t(8;9)(q24.1;q32)inv(8)(q13q24.2)
		return false;
	}
	
	private boolean isValidHsr(List<List<String>> breakpointsFullName, List<Integer> correctParsingOrder, int currentParsingPlace) {
		// We have either within-chromosome-band hsr or between-chromosome-band-interface hsr
    	if (breakpointsFullName.size() == 1) {
    		String chrBreakpoint = breakpointsFullName.get(0).get(0);
    		String chr = getChr(chrBreakpoint);		
    		String chrArm = getChrArm(chrBreakpoint);
    		String mark;
    		
    		boolean notAtPlace0 = currentParsingPlace > 0;
    		Event previousEvent = notAtPlace0? subevents.get(correctParsingOrder.get(currentParsingPlace - 1)) : null;
    		List<String> previousChrList = notAtPlace0? previousEvent.getChrList() : null;
    		List<String> previousChrMarks = notAtPlace0? parsingOrderMarksMap.get(currentParsingPlace - 1) : null;
    		
    		if (chrArm.equals(derChrArm0) || chrArm.equals(derChrArm1)) {
    			boolean isDerChrArm0 = chrArm.equals(derChrArm0);
    			mark = isDerChrArm0? "M0" : "M1";
    			if (findSegment(chrBreakpoint, getSegmentNumList(mark)) == -1) {
    				if (!notAtPlace0) {
    					return false;
    				}
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
    			if (!notAtPlace0) {
    				return false;
    			}
    			int chrIndex = previousChrList.indexOf(chr);
    			if (chrIndex == -1) {
    				return false;
    			}
    			mark = previousChrMarks.get(chrIndex);
    			if (!mark.startsWith("M")) {
    				return false;
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
    	String chrArm0 = getChrArm(chrBreakpoint0);
    	String chrArm1 = getChrArm(chrBreakpoint1);   
    	List<String> chrArmList = Arrays.asList(chrArm0, chrArm1);
    	String chrBreakpointDeletion, chrBreakpointInsertion;
    	String markDeletion, markInsertion = "";
    	int segmentNumDeletion = -1;
    	boolean isBalancedExchange = false;
    	boolean isDI = false;
    	
    	boolean notAtPlace0 = currentParsingPlace > 0;
		Event previousEvent = notAtPlace0? subevents.get(correctParsingOrder.get(currentParsingPlace - 1)) : null;
		List<String> previousChrList = notAtPlace0? previousEvent.getChrList() : null;
		List<String> previousChrMarks = notAtPlace0? parsingOrderMarksMap.get(currentParsingPlace - 1) : null;
    	
		if (chrArmList.contains(derChrArm0) && chrArmList.contains(derChrArm1)) {
			String chr0Mark = chrArm0.equals(derChrArm0)? "M0" : "M1"; 
			String chr1Mark = chr0Mark.equals("M0") ? "M1" : "M0";    				
			int segmentNum0 = findSegment(chrBreakpoint0, getSegmentNumList(chr0Mark));
			int segmentNum1 = findSegment(chrBreakpoint1, getSegmentNumList(chr1Mark));
			if (segmentNum0 == -1 && segmentNum1 == -1) {
				if (!notAtPlace0) {
					return false;
				}
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
				isBalancedExchange = true;
				markDeletion = "";
				chrBreakpointDeletion = "";
    			chrBreakpointInsertion = "";
			} // segmentNum0 != -1 && segmentNum1 != -1   				
		} else if (chrArmList.contains(derChrArm0) || chrArmList.contains(derChrArm1)) {  
			String derChrArm = chrArmList.contains(derChrArm0)? derChrArm0 : derChrArm1;
			markDeletion = chrArmList.contains(derChrArm0)? "M0" : "M1"; 
			isDI = chrArmList.indexOf(derChrArm) == 0;
			if (isDI) {
				chrBreakpointDeletion = chrBreakpoint0;
				chrBreakpointInsertion = chrBreakpoint1;
			} else {
				chrBreakpointDeletion = chrBreakpoint1;
				chrBreakpointInsertion = chrBreakpoint0;
			}	
			if (findSegment(chrBreakpointDeletion, getSegmentNumList(markDeletion)) == -1) {
				if (!notAtPlace0) {
					return false;
				}
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
		} else {
			if (!notAtPlace0) {
				return false;
			}
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
    	} else {
    		String chr0Mark = chrArm0.equals(derChrArm0)? "M0" : "M1"; 
			String chr1Mark = chr0Mark.equals("M0") ? "M1" : "M0";			
    		for (String chrMark: Arrays.asList(chr0Mark, chr1Mark)) {
    			List<List<String>> segmentsOfChr = getSegmentsOfChr(chrMark);
		    	List<Integer> segmentNumsOfMark = getSegmentNumList(chrMark); 
		    	String chrBreakpoint = chrMark.equals(chr0Mark)? chrBreakpoint0 : chrBreakpoint1;
	    		if (isInMultipleSegments(chrBreakpoint, segmentsOfChr)) {
	    			return false;
	    		} else {	    			
	    			int segmentNum = findSegment(chrBreakpoint, segmentNumsOfMark);
	    			if (segmentNum == -1) {
	    				return false;
	    			} else {
	    				List<String> segment = segments.get(segmentNum);
	    				if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint, segment) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint, segment)) {
	    					return false;
	    				}
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
				
		if (!isBalancedExchange) {			
			boolean isTranslocateOnTheLeft;
			int derCen0Segment = findSegmentContainingDerCen(derChr0, segments);
    		int derCen1Segment = derChr0.equals(derChr1)? findSegmentContainingDerCen(derChr0, segments.subList(derCen0Segment + 1, segments.size())) : findSegmentContainingDerCen(derChr1, segments);
    		if (derCen0Segment == -1 || derCen1Segment == -1) {
				return false; // Impossible
			} 
    		if (derChr0.equals(derChr1)) {
				derCen1Segment += derCen0Segment + 1;
			}
    		int max = Arrays.asList(derCen0Segment, derCen1Segment).stream().mapToInt(i -> i).max().getAsInt();
			int min = Arrays.asList(derCen0Segment, derCen1Segment).stream().mapToInt(i -> i).min().getAsInt();
			List<Integer> segmentNumsOfMarkDeletion = getSegmentNumList(markDeletion);
	    	int segmentNum = findSegment(chrBreakpointDeletion, segmentNumsOfMarkDeletion);
			if (segmentNum > min && segmentNum < max) {
				return false; // Impossible by construction
			} else if (segmentNum <= min) {
				isTranslocateOnTheLeft = true;
			} else {
				isTranslocateOnTheLeft = false;
			}
			// Do the main task
			List<List<String>> deletedSegments = new ArrayList<>();
    		translocate(chrBreakpointDeletion, segmentNumDeletion, markDeletion, chrBreakpointInsertion, markInsertion, deletedSegments, isTranslocateOnTheLeft);
    		// Record Loss and Gain
    		recordLoss(deletedSegments, derChr0GainedSegments, derChr1GainedSegments);
    		List<String> gainedSegment =  Arrays.asList(getChrArm(chrBreakpointInsertion) + "ter" + markInsertion, chrBreakpointInsertion + markInsertion);
        	recordGain(gainedSegment, markInsertion);
		} else {
			String chr0Mark = chrArm0.equals(derChrArm0)? "M0" : "M1"; 
			String chr1Mark = chr0Mark.equals("M0") ? "M1" : "M0";			
			int segmentNum0 = findSegment(chrBreakpoint0, getSegmentNumList(chr0Mark));
			int segmentNum1 = findSegment(chrBreakpoint1, getSegmentNumList(chr1Mark));		
			boolean from0to1 = segmentNum0 < segmentNum1;
			int segmentNumL = from0to1? segmentNum0 : segmentNum1;
			int segmentNumR = from0to1? segmentNum1 : segmentNum0;
			String chrBreakpointL = from0to1? chrBreakpoint0 : chrBreakpoint1;
			String chrBreakpointR = from0to1? chrBreakpoint1 : chrBreakpoint0;
			String chrLMark = from0to1? chr0Mark : chr1Mark;
			String chrRMark = from0to1? chr1Mark : chr0Mark;
			translocateForBalancedExchange(segmentNumL, segmentNumR, chrBreakpointL, chrBreakpointR, chrLMark, chrRMark);
		}
		
		// Record ParsingOrder_Marks map
		if (!isBalancedExchange) {
			if (isDI) {
				parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(markDeletion, markInsertion));
			} else {
				parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(markInsertion, markDeletion));
			}
		} else {
			String chr0Mark = chr0.equals(derChrArm0)? "M0" : "M1"; 
			String chr1Mark = chr0Mark.equals("M0") ? "M1" : "M0";
			parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(chr0Mark, chr1Mark));
		}
		
		return true;
	}
    
    protected void translocateForBalancedExchange(int segmentNumL, int segmentNumR, String chrBreakpointL, String chrBreakpointR, String chrLMark, String chrRMark) {
    	int offsetL = getOffsetInRawStringList(segmentNumL);  
    	int offsetR = getOffsetInRawStringList(segmentNumR);  	
    	List<String> rightSideBreakpoints = new ArrayList<>(rawStringList.subList(offsetR, rawStringList.size()));
    	Collections.reverse(rightSideBreakpoints);
    	List<String> leftSideBreakpoints = new ArrayList<>(rawStringList.subList(0, offsetL));
    	Collections.reverse(leftSideBreakpoints);
    	rawStringList.subList(offsetR, rawStringList.size()).clear();
    	rawStringList.add(chrBreakpointR + chrRMark);
    	rawStringList.add(chrBreakpointL + chrLMark);
    	rawStringList.addAll(leftSideBreakpoints);
    	rawStringList.subList(0, offsetL).clear();
    	rawStringList.add(0, chrBreakpointL + chrLMark);
    	rawStringList.add(0, chrBreakpointR + chrRMark);
    	rawStringList.addAll(0, rightSideBreakpoints);
    	segments = getSegments(rawStringList);
    }

}
