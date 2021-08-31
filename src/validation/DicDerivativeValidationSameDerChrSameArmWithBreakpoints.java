package validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
public class DicDerivativeValidationSameDerChrSameArmWithBreakpoints extends DicDerivativeValidationDifferentDerChrWithBreakpoints {

	public DicDerivativeValidationSameDerChrSameArmWithBreakpoints(DerEvent e) {
		super(e);
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
						if (!isValidAdd(breakpointsFullName, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "del": {	
						if (!isValidDel(breakpointsFullName, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "dup": {	
						if (!isValidDup(breakpointsFullName, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "trp": {	
						if (!isValidTrp(breakpointsFullName, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "qdp": {	
						if (!isValidQdp(breakpointsFullName, eventIndexList, i)) {
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
						if (!isValidInv(breakpointsFullName, eventIndexList, i)) {
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
						if (!isValidT(breakpointsFullName, eventIndexList, i)) {
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
		int derCen1Segment = findSegmentContainingDerCen(derChr0, segments.subList(derCen0Segment + 1, segments.size()));
		if (derCen0Segment == -1 || derCen1Segment == -1) {
			validDerivativeChr = false;
		}
		
		if (validDerivativeChr) {
			// Record Loss of the opposite of derChrArms
			List<List<String>> deletedSegments = new ArrayList<>();
			IntStream.range(0, 2)
			         .forEach(i -> {
										String oppositeArm = getOppositeChrArm(derChrArm0);
										String mark = i == 0? "M0" : "M1";
										deletedSegments.add(Arrays.asList(oppositeArm + "ter" + mark, oppositeArm + "10" + mark));
								   });	
			
			recordLoss(deletedSegments, derChr0GainedSegments, derChr1GainedSegments);
			
			return new DerivativeValidationOutcome(validDerivativeChr, segments, getDetailedSystem(), getDerKaryotypeLGF());
		} else {
			return new DerivativeValidationOutcome(validDerivativeChr, segments, null, null);
		}
		
	}
	
	private boolean isValidAdd(List<List<String>> breakpointsFullName, List<Integer> correctParsingOrder, int currentParsingPlace) {
		String chrBreakpoint = breakpointsFullName.get(0).get(0);
		String chr = getChr(chrBreakpoint);		
		String chrArm = getChrArm(chrBreakpoint);
		String mark;
		
		boolean notAtPlace0 = currentParsingPlace > 0;
		Event previousEvent = notAtPlace0? subevents.get(correctParsingOrder.get(currentParsingPlace - 1)) : null;
		List<String> previousChrList = notAtPlace0? previousEvent.getChrList() : null;
		List<String> previousChrMarks = notAtPlace0? parsingOrderMarksMap.get(currentParsingPlace - 1) : null;
		
		if (chrArm.equals(derChrArm0)) {
			if (!notAtPlace0) {
				mark = "M0";
			} else {
				if (isAvailableOnArm(chrBreakpoint, "M0")) {
					mark = "M0";
				} else if (isAvailableOnArm(chrBreakpoint, "M1")) {
					mark = "M1";
				} else if (findSegment(chrBreakpoint, getSegmentNumList("M0")) != -1 && findSegment(chrBreakpoint, getSegmentNumList("M1")) != -1) {
					return false;
				} else if (findSegment(chrBreakpoint, getSegmentNumList("M0")) != -1 || findSegment(chrBreakpoint, getSegmentNumList("M1")) != -1) {
					mark = findSegment(chrBreakpoint, getSegmentNumList("M0")) != -1? "M0" : "M1";
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
				int derCen1Segment = findSegmentContainingDerCen(derChr0, segments.subList(derCen0Segment + 1, segments.size()));
				if (derCen0Segment == -1 || derCen1Segment == -1) {
					return false;
				} 
				derCen1Segment += derCen0Segment + 1;				
				if (segmentNum > derCen0Segment && segmentNum < derCen1Segment) {
					return false;
				} else if (segmentNum <= derCen0Segment) {
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
	
    private boolean isAvailableOnArm(String chrBreakpoint, String mark) { 
    	List<Integer> derChrLoss = markSpecificLossGain.get(mark);
    	List<String> segmentBands = getAllSegmentBands(Arrays.asList(chrBreakpoint, getChrArm(chrBreakpoint) + "ter")); 
    	List<String> derChrBandsList = getChrBandsList(derChr0);    	
    	for (String segmentBand: segmentBands) {
    		int index = derChrBandsList.indexOf(segmentBand);
    		if (derChrLoss.get(index) == 1) {
    			return false;
    		}
    	}
    	return true;
    }
    
    private boolean isAvailableOnArm(String chrBreakpoint0, String chrBreakpoint1, String mark) {
    	List<Integer> derChrLoss = markSpecificLossGain.get(mark);
    	List<String> segmentBands = getAllSegmentBands(Arrays.asList(chrBreakpoint0, chrBreakpoint1)); 
    	List<String> derChrBandsList = getChrBandsList(derChr0);    	
    	for (String segmentBand: segmentBands) {
    		int index = derChrBandsList.indexOf(segmentBand);
    		if (derChrLoss.get(index) == 1) {
    			return false;
    		}
    	}
    	return true;
    }
    
    private boolean isValidDel(List<List<String>> breakpointsFullName, List<Integer> correctParsingOrder, int currentParsingPlace) {
    	String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
		String chr = getChr(chrBreakpoint0);		
		String chrArm = getChrArm(chrBreakpoint0);
		boolean isTerminalDeletion = breakpointsFullName.get(0).size() == 1;
		String chrBreakpoint1 = isTerminalDeletion? "" : breakpointsFullName.get(0).get(1);
		String mark = "";
		
		boolean notAtPlace0 = currentParsingPlace > 0;
		Event previousEvent = notAtPlace0? subevents.get(correctParsingOrder.get(currentParsingPlace - 1)) : null;
		List<String> previousChrList = notAtPlace0? previousEvent.getChrList() : null;
		List<String> previousChrMarks = notAtPlace0? parsingOrderMarksMap.get(currentParsingPlace - 1) : null;
		
		boolean checkPreviousEvent = false;
		if (chrArm.equals(derChrArm0)) {
			if (!notAtPlace0) {
				mark = "M0";
			} else if (isTerminalDeletion) {
				if (isAvailableOnArm(chrBreakpoint0, "M0")) {
					mark = "M0";
				} else if (isAvailableOnArm(chrBreakpoint0, "M1")) {
					mark = "M1";
				} else if (findSegment(chrBreakpoint0, getSegmentNumList("M0")) != -1 && findSegment(chrBreakpoint0, getSegmentNumList("M1")) != -1) {
					return false;
				} else if (findSegment(chrBreakpoint0, getSegmentNumList("M0")) != -1 || findSegment(chrBreakpoint0, getSegmentNumList("M1")) != -1) {
					mark = findSegment(chrBreakpoint0, getSegmentNumList("M0")) != -1? "M0" : "M1";
				} else {
					checkPreviousEvent = true;
				}
			} else {// After validation, we guarantee that chrBreakpoint0 <= chrBreakpoint1
				if (isAvailableOnArm(chrBreakpoint0, chrBreakpoint1, "M0")) {
					mark = "M0";
				} else if (isAvailableOnArm(chrBreakpoint0, chrBreakpoint1, "M1")) {
					mark = "M1";
				} else if (findSegment(chrBreakpoint0, getSegmentNumList("M0")) != -1 && findSegment(chrBreakpoint1, getSegmentNumList("M0")) != -1 && findSegment(chrBreakpoint0, getSegmentNumList("M1")) != -1 && findSegment(chrBreakpoint1, getSegmentNumList("M1")) != -1) {
					return false;
				} else if (findSegment(chrBreakpoint0, getSegmentNumList("M0")) != -1 && findSegment(chrBreakpoint1, getSegmentNumList("M0")) != -1) {
					mark = "M0";
				} else if (findSegment(chrBreakpoint0, getSegmentNumList("M1")) != -1 && findSegment(chrBreakpoint1, getSegmentNumList("M1")) != -1) {
					mark = "M1";
				} else {
					checkPreviousEvent = true;
				}
			}
		} else {
			if (!notAtPlace0) {
				return false;
			} else {
				checkPreviousEvent = true;
			}			
		}
		
		if (checkPreviousEvent) {
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
					int derCen1Segment = findSegmentContainingDerCen(derChr0, segments.subList(derCen0Segment + 1, segments.size()));
					if (derCen0Segment == -1 || derCen1Segment == -1) {
						return false;
					} 
					derCen1Segment += derCen0Segment + 1;				
					if (segmentNum > derCen0Segment && segmentNum < derCen1Segment) {
						return false;
					} else if (segmentNum <= derCen0Segment) {
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
			while (true) {
				segmentsOfChr = getSegmentsOfChr(mark);
				segmentNumsOfMark = getSegmentNumList(mark);
				if (isInMultipleSegments(chrBreakpoint0, segmentsOfChr) || isInMultipleSegments(chrBreakpoint1, segmentsOfChr)) {
					segmentNum0 = getUniqueSegment(chrBreakpoint0, chrBreakpoint1, segmentsOfChr, segmentNumsOfMark);
					if (segmentNum0 == -1) {
						if (mark.equals("M0")) {
							mark = "M1";
							continue;
						} else {
							return false;
						}
					}
					segmentNum1 = segmentNum0;
				} else {
					segmentNum0 = findSegment(chrBreakpoint0, segmentNumsOfMark);
	    			segmentNum1 = findSegment(chrBreakpoint1, segmentNumsOfMark);
	    			if (segmentNum0 == -1 || segmentNum1 == -1) {
	    				if (mark.equals("M0")) {
							mark = "M1";
							continue;
						} else {
							return false;
						}
	    			}
	    			List<String> segment0 = segments.get(segmentNum0);
	    			if (chrBreakpoint0.equals(chrBreakpoint1)) {
	    				if (isFinerThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint0, segment0)) {
	    					if (mark.equals("M0")) {
	    						mark = "M1";
	    						continue;
	    					} else {
	    						return false;
	    					}
						}
	    			} else {
	    				if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint0, segment0)) {
	    					if (mark.equals("M0")) {
	    						mark = "M1";
	    						continue;
	    					} else {
	    						return false;
	    					}
						}
						List<String> segment1 = segments.get(segmentNum1);
						if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint1, segment1) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint1, segment1)) {
							if (mark.equals("M0")) {
								mark = "M1";
								continue;
							} else {
								return false;
							}
						}
	    			}
	    			
	    			// Check whether derChr's cen has been deleted
					if (mark.equals("M0") || mark.equals("M1")) {
						if (segmentNum0 != segmentNum1) {
							int derCen0Segment = findSegmentContainingDerCen(derChr0, segments);
							int derCen1Segment = findSegmentContainingDerCen(derChr0, segments.subList(derCen0Segment + 1, segments.size()));
							if (segmentNum0 < segmentNum1) {
								if (isDerCenDeleted(segmentNum0, segmentNum1, derCen0Segment, derCen1Segment)) {
									if (mark.equals("M0")) {
										mark = "M1";
										continue;
									} else {
										return false;
									}								
								}
							} else {
								if (isDerCenDeleted(segmentNum1, segmentNum0, derCen0Segment, derCen1Segment)) {
									if (mark.equals("M0")) {
										mark = "M1";
										continue;
									} else {
										return false;
									}								
								}
							}
						}						
					}
					
				}			
				
				break;
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
			
			// Record LGF
			recordLoss(deletedSegments, derChr0GainedSegments, derChr1GainedSegments);
			parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(mark));			
		}		
		return true;
    }
    
    private boolean isDerCenDeleted(int segmentNumL, int segmentNumR, int derCenSegmentL, int derCenSegmentR) {
    	if (segmentNumL <= derCenSegmentL && derCenSegmentR <= segmentNumR) {
			return true;
		} // Example: 45,XY,der(8;8)(q10;q10)add(8)(q22.2)t(8;8)(q21;q23)del(8)(q12q22.1) for the first 8
		return false;
    }
    
    private boolean isValidDup(List<List<String>> breakpointsFullName, List<Integer> correctParsingOrder, int currentParsingPlace) {
    	String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
		String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
		String chr = getChr(chrBreakpoint0);		
		String chrArm = getChrArm(chrBreakpoint0);		
		String mark = "";
		
		boolean notAtPlace0 = currentParsingPlace > 0;
		Event previousEvent = notAtPlace0? subevents.get(correctParsingOrder.get(currentParsingPlace - 1)) : null;
		List<String> previousChrList = notAtPlace0? previousEvent.getChrList() : null;
		List<String> previousChrMarks = notAtPlace0? parsingOrderMarksMap.get(currentParsingPlace - 1) : null;
		
		boolean checkPreviousEvent = false;
		if (chrArm.equals(derChrArm0)) {
			if (!notAtPlace0) {
				mark = "M0";
			} else {
				if (findSegment(chrBreakpoint0, getSegmentNumList("M0")) != -1 && findSegment(chrBreakpoint1, getSegmentNumList("M0")) != -1) {
					mark = "M0";
				} else if (findSegment(chrBreakpoint0, getSegmentNumList("M1")) != -1 && findSegment(chrBreakpoint1, getSegmentNumList("M1")) != -1) {
					mark = "M1";
				} else {
					checkPreviousEvent = true;
				}
			}
		} else {
			if (!notAtPlace0) {
				return false;
			} else {
				checkPreviousEvent = true;
			}
		}
		
		if (checkPreviousEvent) {
			int chrIndex = previousChrList.indexOf(chr);
			if (chrIndex == -1) {
				return false;
			}
			mark = previousChrMarks.get(chrIndex);
			if (!mark.startsWith("M")) {
				return false;
			}
		}
		
		List<List<String>> segmentsOfChr;
		List<Integer> segmentNumsOfMark;
		int segmentNum0, segmentNum1;
		while (true) {
			segmentsOfChr = getSegmentsOfChr(mark);
			segmentNumsOfMark = getSegmentNumList(mark);
			if (isInMultipleSegments(chrBreakpoint0, segmentsOfChr) || isInMultipleSegments(chrBreakpoint1, segmentsOfChr)) {
				segmentNum0 = getUniqueSegment(chrBreakpoint0, chrBreakpoint1, segmentsOfChr, segmentNumsOfMark);
				if (segmentNum0 == -1) {
					if (mark.equals("M0")) {
						mark = "M1";
						continue;
					} else {
						return false;
					}
				}
				// Check whether derChr's cen has been duplicated
				if ((mark.equals("M0") || mark.equals("M1")) && !getChrArm(chrBreakpoint0).equals(getChrArm(chrBreakpoint1))) {
					if (mark.equals("M0")) {
						mark = "M1";
						continue;
					} else {
						return false;
					}
				}
				segmentNum1 = segmentNum0;
			} else {
				segmentNum0 = findSegment(chrBreakpoint0, segmentNumsOfMark);
				segmentNum1 = findSegment(chrBreakpoint1, segmentNumsOfMark);
				if (segmentNum0 == -1 || segmentNum1 == -1) {
					if (mark.equals("M0")) {
						mark = "M1";
						continue;
					} else {
						return false;
					}
				}
				List<String> segment0 = segments.get(segmentNum0);
				List<String> segment1 = segments.get(segmentNum1);
				if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint1, segment1) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint1, segment1)) {
					if (mark.equals("M0")) {
						mark = "M1";
						continue;
					} else {
						return false;
					}
			    }
				// Check whether derChr's cen has been duplicated
		        if (mark.equals("M0") || mark.equals("M1")) {
					if (segmentNum0 == segmentNum1) {
						if (!getChrArm(chrBreakpoint0).equals(getChrArm(chrBreakpoint1))) {
							if (mark.equals("M0")) {
								mark = "M1";
								continue;
							} else {
								return false;
							}
		    			}
					} else {
						int derCen0Segment = findSegmentContainingDerCen(derChr0, segments);
						int derCen1Segment = findSegmentContainingDerCen(derChr0, segments.subList(derCen0Segment + 1, segments.size())) + derCen0Segment + 1;
						if (segmentNum0 < segmentNum1) {
							if (isDerCenDuplicated(segmentNum0, segmentNum1, derCen0Segment, derCen1Segment)) {
								if (mark.equals("M0")) {
									mark = "M1";
									continue;
								} else {
									return false;
								}								
							}
						} else {
							if (isDerCenDuplicated(segmentNum1, segmentNum0, derCen0Segment, derCen1Segment)) {
								if (mark.equals("M0")) {
									mark = "M1";
									continue;
								} else {
									return false;
								}								
							}
						}
					} 
		        }
			}
			break;
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
    
    private boolean isValidTrp(List<List<String>> breakpointsFullName, List<Integer> correctParsingOrder, int currentParsingPlace) {
    	String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
		String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
		String chr = getChr(chrBreakpoint0);		
		String chrArm = getChrArm(chrBreakpoint0);		
		String mark = "";
		
		boolean notAtPlace0 = currentParsingPlace > 0;
		Event previousEvent = notAtPlace0? subevents.get(correctParsingOrder.get(currentParsingPlace - 1)) : null;
		List<String> previousChrList = notAtPlace0? previousEvent.getChrList() : null;
		List<String> previousChrMarks = notAtPlace0? parsingOrderMarksMap.get(currentParsingPlace - 1) : null;
		
		boolean checkPreviousEvent = false;
		if (chrArm.equals(derChrArm0)) {
			if (!notAtPlace0) {
				mark = "M0";
			} else {
				if (findSegment(chrBreakpoint0, getSegmentNumList("M0")) != -1 && findSegment(chrBreakpoint1, getSegmentNumList("M0")) != -1) {
					mark = "M0";
				} else if (findSegment(chrBreakpoint0, getSegmentNumList("M1")) != -1 && findSegment(chrBreakpoint1, getSegmentNumList("M1")) != -1) {
					mark = "M1";
				} else {
					checkPreviousEvent = true;
				}
			}
		} else {
			if (!notAtPlace0) {
				return false;
			} else {
				checkPreviousEvent = true;
			}
		}
		
		if (checkPreviousEvent) {
			int chrIndex = previousChrList.indexOf(chr);
			if (chrIndex == -1) {
				return false;
			}
			mark = previousChrMarks.get(chrIndex);
			if (!mark.startsWith("M")) {
				return false;
			}
		}
		
		List<List<String>> segmentsOfChr;
		List<Integer> segmentNumsOfMark;
		int segmentNum0, segmentNum1;
		while (true) {
			segmentsOfChr = getSegmentsOfChr(mark);
			segmentNumsOfMark = getSegmentNumList(mark);
			if (isInMultipleSegments(chrBreakpoint0, segmentsOfChr) || isInMultipleSegments(chrBreakpoint1, segmentsOfChr)) {
				segmentNum0 = getUniqueSegment(chrBreakpoint0, chrBreakpoint1, segmentsOfChr, segmentNumsOfMark);
				if (segmentNum0 == -1) {
					if (mark.equals("M0")) {
						mark = "M1";
						continue;
					} else {
						return false;
					}
				}
				// Check whether derChr's cen has been duplicated
				if ((mark.equals("M0") || mark.equals("M1")) && !getChrArm(chrBreakpoint0).equals(getChrArm(chrBreakpoint1))) {
					if (mark.equals("M0")) {
						mark = "M1";
						continue;
					} else {
						return false;
					}
				}
				segmentNum1 = segmentNum0;
			} else {
				segmentNum0 = findSegment(chrBreakpoint0, segmentNumsOfMark);
				segmentNum1 = findSegment(chrBreakpoint1, segmentNumsOfMark);
				if (segmentNum0 == -1 || segmentNum1 == -1) {
					if (mark.equals("M0")) {
						mark = "M1";
						continue;
					} else {
						return false;
					}
				}
				List<String> segment0 = segments.get(segmentNum0);
				List<String> segment1 = segments.get(segmentNum1);
				if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint1, segment1) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint1, segment1)) {
					if (mark.equals("M0")) {
						mark = "M1";
						continue;
					} else {
						return false;
					}
			    }
				// Check whether derChr's cen has been duplicated
		        if (mark.equals("M0") || mark.equals("M1")) {
					if (segmentNum0 == segmentNum1) {
						if (!getChrArm(chrBreakpoint0).equals(getChrArm(chrBreakpoint1))) {
							if (mark.equals("M0")) {
								mark = "M1";
								continue;
							} else {
								return false;
							}
		    			}
					} else {
						int derCen0Segment = findSegmentContainingDerCen(derChr0, segments);
						int derCen1Segment = findSegmentContainingDerCen(derChr0, segments.subList(derCen0Segment + 1, segments.size())) + derCen0Segment + 1;
						if (segmentNum0 < segmentNum1) {
							if (isDerCenDuplicated(segmentNum0, segmentNum1, derCen0Segment, derCen1Segment)) {
								if (mark.equals("M0")) {
									mark = "M1";
									continue;
								} else {
									return false;
								}								
							}
						} else {
							if (isDerCenDuplicated(segmentNum1, segmentNum0, derCen0Segment, derCen1Segment)) {
								if (mark.equals("M0")) {
									mark = "M1";
									continue;
								} else {
									return false;
								}								
							}
						}
					} 
		        }
			}
			break;
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
    
    private boolean isValidQdp(List<List<String>> breakpointsFullName, List<Integer> correctParsingOrder, int currentParsingPlace) {
    	String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
		String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
		String chr = getChr(chrBreakpoint0);		
		String chrArm = getChrArm(chrBreakpoint0);		
		String mark = "";
		
		boolean notAtPlace0 = currentParsingPlace > 0;
		Event previousEvent = notAtPlace0? subevents.get(correctParsingOrder.get(currentParsingPlace - 1)) : null;
		List<String> previousChrList = notAtPlace0? previousEvent.getChrList() : null;
		List<String> previousChrMarks = notAtPlace0? parsingOrderMarksMap.get(currentParsingPlace - 1) : null;
		
		boolean checkPreviousEvent = false;
		if (chrArm.equals(derChrArm0)) {
			if (!notAtPlace0) {
				mark = "M0";
			} else {
				if (findSegment(chrBreakpoint0, getSegmentNumList("M0")) != -1 && findSegment(chrBreakpoint1, getSegmentNumList("M0")) != -1) {
					mark = "M0";
				} else if (findSegment(chrBreakpoint0, getSegmentNumList("M1")) != -1 && findSegment(chrBreakpoint1, getSegmentNumList("M1")) != -1) {
					mark = "M1";
				} else {
					checkPreviousEvent = true;
				}
			}
		} else {
			if (!notAtPlace0) {
				return false;
			} else {
				checkPreviousEvent = true;
			}
		}
		
		if (checkPreviousEvent) {
			int chrIndex = previousChrList.indexOf(chr);
			if (chrIndex == -1) {
				return false;
			}
			mark = previousChrMarks.get(chrIndex);
			if (!mark.startsWith("M")) {
				return false;
			}
		}
		
		List<List<String>> segmentsOfChr;
		List<Integer> segmentNumsOfMark;
		int segmentNum0, segmentNum1;
		while (true) {
			segmentsOfChr = getSegmentsOfChr(mark);
			segmentNumsOfMark = getSegmentNumList(mark);
			if (isInMultipleSegments(chrBreakpoint0, segmentsOfChr) || isInMultipleSegments(chrBreakpoint1, segmentsOfChr)) {
				segmentNum0 = getUniqueSegment(chrBreakpoint0, chrBreakpoint1, segmentsOfChr, segmentNumsOfMark);
				if (segmentNum0 == -1) {
					if (mark.equals("M0")) {
						mark = "M1";
						continue;
					} else {
						return false;
					}
				}
				// Check whether derChr's cen has been duplicated
				if ((mark.equals("M0") || mark.equals("M1")) && !getChrArm(chrBreakpoint0).equals(getChrArm(chrBreakpoint1))) {
					if (mark.equals("M0")) {
						mark = "M1";
						continue;
					} else {
						return false;
					}
				}
				segmentNum1 = segmentNum0;
			} else {
				segmentNum0 = findSegment(chrBreakpoint0, segmentNumsOfMark);
				segmentNum1 = findSegment(chrBreakpoint1, segmentNumsOfMark);
				if (segmentNum0 == -1 || segmentNum1 == -1) {
					if (mark.equals("M0")) {
						mark = "M1";
						continue;
					} else {
						return false;
					}
				}
				List<String> segment0 = segments.get(segmentNum0);
				List<String> segment1 = segments.get(segmentNum1);
				if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint1, segment1) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint1, segment1)) {
					if (mark.equals("M0")) {
						mark = "M1";
						continue;
					} else {
						return false;
					}
			    }
				// Check whether derChr's cen has been duplicated
		        if (mark.equals("M0") || mark.equals("M1")) {
					if (segmentNum0 == segmentNum1) {
						if (!getChrArm(chrBreakpoint0).equals(getChrArm(chrBreakpoint1))) {
							if (mark.equals("M0")) {
								mark = "M1";
								continue;
							} else {
								return false;
							}
		    			}
					} else {
						int derCen0Segment = findSegmentContainingDerCen(derChr0, segments);
						int derCen1Segment = findSegmentContainingDerCen(derChr0, segments.subList(derCen0Segment + 1, segments.size())) + derCen0Segment + 1;
						if (segmentNum0 < segmentNum1) {
							if (isDerCenDuplicated(segmentNum0, segmentNum1, derCen0Segment, derCen1Segment)) {
								if (mark.equals("M0")) {
									mark = "M1";
									continue;
								} else {
									return false;
								}								
							}
						} else {
							if (isDerCenDuplicated(segmentNum1, segmentNum0, derCen0Segment, derCen1Segment)) {
								if (mark.equals("M0")) {
									mark = "M1";
									continue;
								} else {
									return false;
								}								
							}
						}
					} 
		        }
			}
			break;
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
    		String mark = "";
    		boolean checkPreviousEvent = false;
    		
    		if (chrArm.equals(derChrArm0)) {      			
    			if (!notAtPlace0) {
    				mark = "M0";
    			} else {
    				if (findSegment(chrBreakpoint0, getSegmentNumList("M0")) != -1 && findSegment(chrBreakpoint1, getSegmentNumList("M0")) != -1 && findSegment(chrBreakpoint2, getSegmentNumList("M0")) != -1) {
    					mark = "M0";
    				} else if (findSegment(chrBreakpoint0, getSegmentNumList("M1")) != -1 && findSegment(chrBreakpoint1, getSegmentNumList("M1")) != -1 && findSegment(chrBreakpoint2, getSegmentNumList("M1")) != -1) {
    					mark = "M1";
    				} else {
    					checkPreviousEvent = true;
    				}
    			}
    		} else {
    			if (!notAtPlace0) {
    				return false;
    			} else {
    				checkPreviousEvent = true;
    			}
    		}
    		
    		if (checkPreviousEvent) {
    			int chrIndex = previousChrList.indexOf(chr);
    			if (chrIndex == -1) {
    				return false;
    			}
    			mark = previousChrMarks.get(chrIndex);
    			if (!mark.startsWith("M")) {
    				return false;
    			}
    		}
    		
    		List<List<String>> segmentsOfChr;
    		List<Integer> segmentNumsOfMark;
    		int segmentNum0, segmentNum1, segmentNum2;
    		outer: 
    		while (true) {
    			for (String s: getChrBreakpointsOfAllResolution(chrBreakpoint0)) {
					if (insertedPlaceListMap.get(mark) != null && insertedPlaceListMap.get(mark).contains(s)) {
						if (mark.equals("M0")) {
							mark = "M1";
							continue outer;
						} else {
							return false;
						}
					}
				}
	    		
	    		segmentsOfChr = getSegmentsOfChr(mark);
				segmentNumsOfMark = getSegmentNumList(mark);
				if (isInMultipleSegments(chrBreakpoint0, segmentsOfChr)) {
					if (mark.equals("M0")) {
						mark = "M1";
						continue outer;
					} else {
						return false;
					}
				} else if (isInMultipleSegments(chrBreakpoint1, segmentsOfChr) || isInMultipleSegments(chrBreakpoint2, segmentsOfChr)) {
					segmentNum0 = findSegment(chrBreakpoint0, segmentNumsOfMark);
					segmentNum1 = getUniqueSegment(chrBreakpoint1, chrBreakpoint2, segmentsOfChr, segmentNumsOfMark);
					segmentNum2 = segmentNum1;
					if (segmentNum0 == -1 || segmentNum1 == -1) {
						if (mark.equals("M0")) {
							mark = "M1";
							continue outer;
						} else {
							return false;
						}
	    			}
					if (!isValidInsertedSegment(chrBreakpoint0, chrBreakpoint1, chrBreakpoint2, mark, segmentsOfChr, segmentNumsOfMark)) {
						if (mark.equals("M0")) {
							mark = "M1";
							continue outer;
						} else {
							return false;
						}
	    			}
				} else {
					segmentNum0 = findSegment(chrBreakpoint0, segmentNumsOfMark);
	    			segmentNum1 = findSegment(chrBreakpoint1, segmentNumsOfMark);
	    			segmentNum2 = findSegment(chrBreakpoint2, segmentNumsOfMark);
	    			if (segmentNum0 == -1 || segmentNum1 == -1 || segmentNum2 == -1) {
	    				if (mark.equals("M0")) {
							mark = "M1";
							continue outer;
						} else {
							return false;
						}
	    			}
	    			if (!isValidInsertedSegment(chrBreakpoint0, chrBreakpoint1, chrBreakpoint2, mark, segmentsOfChr, segmentNumsOfMark)) {
	    				if (mark.equals("M0")) {
							mark = "M1";
							continue outer;
						} else {
							return false;
						}
	    			}
	    			List<String> segment1 = segments.get(segmentNum1);
	    			if (chrBreakpoint1.equals(chrBreakpoint2)) {
						if (isFinerThanExistingSegmentEndPoint(chrBreakpoint1, segment1) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint1, segment1)) {
							if (mark.equals("M0")) {
								mark = "M1";
								continue outer;
							} else {
								return false;
							}
						} 
	    			} else {
						if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint1, segment1) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint1, segment1)) {
							if (mark.equals("M0")) {
								mark = "M1";
								continue outer;
							} else {
								return false;
							}
						}
						List<String> segment2 = segments.get(segmentNum2);
						if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint2, segment2) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint2, segment2)) {
							if (mark.equals("M0")) {
								mark = "M1";
								continue outer;
							} else {
								return false;
							}
						}
	    			}
				}
				List<String> segment0 = segments.get(segmentNum0);
				if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint0, segment0)) {
					if (mark.equals("M0")) {
						mark = "M1";
						continue outer;
					} else {
						return false;
					}
				} 
				break;
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
    		String mark0 = "";
    		boolean checkPreviousEvent = false;
    		
    		if (chrArm.equals(derChrArm0)) {    			
    			if (!notAtPlace0) {
    				mark0 = "M0";
    			} else {
    				if (findSegment(chrBreakpoint0, getSegmentNumList("M0")) != -1) {
    					mark0 = "M0";
    				} else if (findSegment(chrBreakpoint0, getSegmentNumList("M1")) != -1) {
    					mark0 = "M1";
    				} else {
    					checkPreviousEvent = true;
    				}
    			}
    		} else {
    			if (!notAtPlace0) {
    				return false;
    			} else {
    				checkPreviousEvent = true;
    			}
    		}
    		
    		if (checkPreviousEvent) {
    			int chrIndex = previousChrList.indexOf(chr0);
    			if (chrIndex == -1) {
    				return false;
    			}
    			mark0 = previousChrMarks.get(chrIndex);
    			if (!mark0.startsWith("M")) {
    				return false;
    			}
    		}
    		
    		List<List<String>> segmentsOfChr0;
    		List<Integer> segmentNumsOfMark0;
    		int segmentNum0;
    		outer: 
        	while (true) {
        		// Examine inserted place
        		for (String s: getChrBreakpointsOfAllResolution(chrBreakpoint0)) {
    				if (insertedPlaceListMap.get(mark0) != null && insertedPlaceListMap.get(mark0).contains(s)) {
    					if (mark0.equals("M0")) {
    						mark0 = "M1";
    						continue outer;
    					} else {
    						return false;
    					}
    				}
    			}
        		// Exclude confusing karyotypes
        		segmentsOfChr0 = getSegmentsOfChr(mark0);
    			segmentNumsOfMark0 = getSegmentNumList(mark0);
    			// Chr1 is a new added chr, so that we don't need to check
    			if (isInMultipleSegments(chrBreakpoint0, segmentsOfChr0)) {
    				if (mark0.equals("M0")) {
    					mark0 = "M1";
    					continue outer;
    				} else {
    					return false;
    				}
    			}
    			// Examine unmatching resolution case of chrBreakpoint0
    			segmentNum0 = findSegment(chrBreakpoint0, segmentNumsOfMark0);
    			if (segmentNum0 == -1) {
    				if (mark0.equals("M0")) {
    					mark0 = "M1";
    					continue outer;
    				} else {
    					return false;
    				}
    			}
    			List<String> segment0 = segments.get(segmentNum0);
    			if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint0, segment0)) {
    				if (mark0.equals("M0")) {
    					mark0 = "M1";
    					continue outer;
    				} else {
    					return false;
    				}
    			}
        		break;
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
    
    private boolean isValidInv(List<List<String>> breakpointsFullName, List<Integer> correctParsingOrder, int currentParsingPlace) {
    	String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
		String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
		String chr = getChr(chrBreakpoint0);		
		String chrArm0 = getChrArm(chrBreakpoint0);
		String chrArm1 = getChrArm(chrBreakpoint1);		
		if (!chrArm0.equals(chrArm1)) {
			return false;
		}
		String chrArm = chrArm0;		
		String mark = "";
		
		boolean notAtPlace0 = currentParsingPlace > 0;
		Event previousEvent = notAtPlace0? subevents.get(correctParsingOrder.get(currentParsingPlace - 1)) : null;
		List<String> previousChrList = notAtPlace0? previousEvent.getChrList() : null;
		List<String> previousChrMarks = notAtPlace0? parsingOrderMarksMap.get(currentParsingPlace - 1) : null;
		
		boolean checkPreviousEvent = false;
		if (chrArm.equals(derChrArm0)) {
			if (!notAtPlace0) {
				mark = "M0";
			} else {
				if (findSegment(chrBreakpoint0, getSegmentNumList("M0")) != -1 && findSegment(chrBreakpoint1, getSegmentNumList("M0")) != -1) {
					mark = "M0";
				} else if (findSegment(chrBreakpoint0, getSegmentNumList("M1")) != -1 && findSegment(chrBreakpoint1, getSegmentNumList("M1")) != -1) {
					mark = "M1";
				} else {
					checkPreviousEvent = true;
				}
			}
		} else {
			if (!notAtPlace0) {
				return false;
			} else {
				checkPreviousEvent = true;
			}
		}
		
		if (checkPreviousEvent) {
			int chrIndex = previousChrList.indexOf(chr);
			if (chrIndex == -1) {
				return false;
			}
			mark = previousChrMarks.get(chrIndex);
			if (!mark.startsWith("M")) {
				return false;
			}
		}
		
		List<List<String>> segmentsOfChr;
		List<Integer> segmentNumsOfMark;
		int segmentNum0, segmentNum1;
		while (true) {
			segmentsOfChr = getSegmentsOfChr(mark);
			segmentNumsOfMark = getSegmentNumList(mark);
			if (isInMultipleSegments(chrBreakpoint0, segmentsOfChr) || isInMultipleSegments(chrBreakpoint1, segmentsOfChr)) {
				segmentNum0 = getUniqueSegment(chrBreakpoint0, chrBreakpoint1, segmentsOfChr, segmentNumsOfMark);
				if (segmentNum0 == -1) {
					if (mark.equals("M0")) {
						mark = "M1";
						continue;
					} else {
						return false;
					}
				}
				segmentNum1 = segmentNum0;
			} else {
				segmentNum0 = findSegment(chrBreakpoint0, segmentNumsOfMark);
				segmentNum1 = findSegment(chrBreakpoint1, segmentNumsOfMark);
				if (segmentNum0 == -1 || segmentNum1 == -1) {
					if (mark.equals("M0")) {
						mark = "M1";
						continue;
					} else {
						return false;
					}
				}
				List<String> segment0 = segments.get(segmentNum0);
				List<String> segment1 = segments.get(segmentNum1);
				if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint1, segment1) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint1, segment1)) {
					if (mark.equals("M0")) {
						mark = "M1";
						continue;
					} else {
						return false;
					}
			    }
				// Check whether derChr's cen has been inverted
		        if (mark.equals("M0") || mark.equals("M1")) {
					if (segmentNum0 != segmentNum1) {
						int derCen0Segment = findSegmentContainingDerCen(derChr0, segments);
						int derCen1Segment = findSegmentContainingDerCen(derChr0, segments.subList(derCen0Segment + 1, segments.size())) + derCen0Segment + 1;
						if (segmentNum0 < segmentNum1) {
							if (isDerCenInverted(segmentNum0, segmentNum1, derCen0Segment, derCen1Segment)) {
								if (mark.equals("M0")) {
									mark = "M1";
									continue;
								} else {
									return false;
								}								
							}
						} else {
							if (isDerCenInverted(segmentNum1, segmentNum0, derCen0Segment, derCen1Segment)) {
								if (mark.equals("M0")) {
									mark = "M1";
									continue;
								} else {
									return false;
								}								
							}
						}
					} 
		        }
			}
			break;
		}
		
		invert(chrBreakpoint0, chrBreakpoint1, segmentNum0, segmentNum1, mark);
		parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(mark));
		
		return true;
    }
    
    private boolean isValidHsr(List<List<String>> breakpointsFullName, List<Integer> correctParsingOrder, int currentParsingPlace) {
    	// We have either within-chromosome-band hsr or between-chromosome-band-interface hsr
    	if (breakpointsFullName.size() == 1) {
    		String chrBreakpoint = breakpointsFullName.get(0).get(0);
    		String chr = getChr(chrBreakpoint);		
    		String chrArm = getChrArm(chrBreakpoint);
    		String mark = "";
    		
    		boolean notAtPlace0 = currentParsingPlace > 0;
    		Event previousEvent = notAtPlace0? subevents.get(correctParsingOrder.get(currentParsingPlace - 1)) : null;
    		List<String> previousChrList = notAtPlace0? previousEvent.getChrList() : null;
    		List<String> previousChrMarks = notAtPlace0? parsingOrderMarksMap.get(currentParsingPlace - 1) : null;
    		
    		boolean checkPreviousEvent = false;
    		if (chrArm.equals(derChrArm0)) {
    			if (!notAtPlace0) {
    				mark = "M0";
    			} else {
    				if (findSegment(chrBreakpoint, getSegmentNumList("M0")) != -1) {
    					mark = "M0";
    				} else if (findSegment(chrBreakpoint, getSegmentNumList("M1")) != -1) {
    					mark = "M1";
    				} else {
    					checkPreviousEvent = true;
    				}
    			}
    		} else {
    			if (!notAtPlace0) {
    				return false;
    			} else {
    				checkPreviousEvent = true;
    			}
    		}
    		
    		if (checkPreviousEvent) {
    			int chrIndex = previousChrList.indexOf(chr);
    			if (chrIndex == -1) {
    				return false;
    			}
    			mark = previousChrMarks.get(chrIndex);
    			if (!mark.startsWith("M")) {
    				return false;
    			}
    		}
    		
    		outer: 
    		while (true) {
	    		// First we cannot have multiple segments inserted at the same place.
	    		for (String s: getChrBreakpointsOfAllResolution(chrBreakpoint)) {
					if (hsrPlaceListMap.get(mark) != null && hsrPlaceListMap.get(mark).contains(s)) {
						if (mark.equals("M0")) {
							mark = "M1";
							continue outer;
						} else {
							return false;
						}
					}
				}
	    		// Exclude confusing karyotypes
	    		List<List<String>> segmentsOfChr = getSegmentsOfChr(mark);
				int segmentNum;
				if (isInMultipleSegments(chrBreakpoint, segmentsOfChr)) {
					if (mark.equals("M0")) {
						mark = "M1";
						continue outer;
					} else {
						return false;
					}
				} else {
	            	List<Integer> segmentNumsOfMark = getSegmentNumList(mark);
	            	segmentNum = findSegment(chrBreakpoint, segmentNumsOfMark);
	            	if (segmentNum == -1) {
	            		if (mark.equals("M0")) {
							mark = "M1";
							continue outer;
						} else {
							return false;
						}
	            	} else {
	            		List<String> segment = segments.get(segmentNum);
	            		if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint, segment) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint, segment)) {
	            			if (mark.equals("M0")) {
								mark = "M1";
								continue outer;
							} else {
								return false;
							}
	            		}
	            		List<String> sourceList = Arrays.asList(chrBreakpoint + mark, "hsr" + mark, chrBreakpoint + mark);
	            		int offset = getOffsetInRawStringList(segmentNum);            		
	            		rawStringList.addAll(offset, sourceList);            		
	            	}  
	            }
				break;
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
    
    private boolean isValidT(List<List<String>> breakpointsFullName, List<Integer> correctParsingOrder, int currentParsingPlace) {
    	String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
    	String chrBreakpoint1 = breakpointsFullName.get(1).get(0);
    	String chr0 = getChr(chrBreakpoint0);
    	String chr1 = getChr(chrBreakpoint1);
    	String chrArm0 = getChrArm(chrBreakpoint0);
    	String chrArm1 = getChrArm(chrBreakpoint1);   
    	List<String> chrArmList = Arrays.asList(chrArm0, chrArm1);
    	String chrBreakpointDeletion = "", chrBreakpointInsertion = "";
    	String markDeletion = "", markInsertion = "";
    	int segmentNumDeletion = -1;
    	boolean isBalancedExchange = false;
    	String mark0ChrBreakpoint = "", mark1ChrBreakpoint = "";
    	boolean isDI = false;
    	
    	boolean notAtPlace0 = currentParsingPlace > 0;
		Event previousEvent = notAtPlace0? subevents.get(correctParsingOrder.get(currentParsingPlace - 1)) : null;
		List<String> previousChrList = notAtPlace0? previousEvent.getChrList() : null;
		List<String> previousChrMarks = notAtPlace0? parsingOrderMarksMap.get(currentParsingPlace - 1) : null;
		
		int derChrccurrences = Collections.frequency(chrArmList, derChrArm0);
		
		switch (derChrccurrences) {
			case 2: {
				int segmentNum0M0 = findSegment(chrBreakpoint0, getSegmentNumList("M0"));
				int segmentNum0M1 = findSegment(chrBreakpoint0, getSegmentNumList("M1"));
				int segmentNum1M0 = findSegment(chrBreakpoint1, getSegmentNumList("M0"));
				int segmentNum1M1 = findSegment(chrBreakpoint1, getSegmentNumList("M1"));
				if (segmentNum0M0 != -1 && segmentNum0M1 != -1 && segmentNum1M0 != -1 && segmentNum1M1 != -1) {
					return false;
				} else if (segmentNum0M0 != -1 && segmentNum0M1 != -1 && segmentNum1M0 != -1 && segmentNum1M1 == -1) {
					isBalancedExchange = true;
					mark0ChrBreakpoint = chrBreakpoint1;
					mark1ChrBreakpoint = chrBreakpoint0;
				} else if (segmentNum0M0 != -1 && segmentNum0M1 != -1 && segmentNum1M0 == -1 && segmentNum1M1 != -1) {
					isBalancedExchange = true;
					mark0ChrBreakpoint = chrBreakpoint0;
					mark1ChrBreakpoint = chrBreakpoint1;
				} else if (segmentNum0M0 == -1 && segmentNum0M1 != -1 && segmentNum1M0 != -1 && segmentNum1M1 != -1) {
					isBalancedExchange = true;
					mark0ChrBreakpoint = chrBreakpoint1;
					mark1ChrBreakpoint = chrBreakpoint0;
				} else if (segmentNum0M0 != -1 && segmentNum0M1 == -1 && segmentNum1M0 != -1 && segmentNum1M1 != -1) {
					isBalancedExchange = true;
					mark0ChrBreakpoint = chrBreakpoint0;
					mark1ChrBreakpoint = chrBreakpoint1;
				} else if (segmentNum0M0 != -1 && segmentNum0M1 == -1 && segmentNum1M0 == -1 && segmentNum1M1 != -1) {
					isBalancedExchange = true;
					mark0ChrBreakpoint = chrBreakpoint0;
					mark1ChrBreakpoint = chrBreakpoint1;
				} else if (segmentNum0M0 == -1 && segmentNum0M1 != -1 && segmentNum1M0 != -1 && segmentNum1M1 == -1) {
					isBalancedExchange = true;
					mark0ChrBreakpoint = chrBreakpoint1;
					mark1ChrBreakpoint = chrBreakpoint0;
				} else if (segmentNum0M0 != -1 && segmentNum0M1 == -1 && segmentNum1M0 == -1 && segmentNum1M1 == -1) {
					markDeletion = "M0";
					chrBreakpointDeletion = chrBreakpoint0;
					chrBreakpointInsertion = chrBreakpoint1;
					isDI = true;
				} else if (segmentNum0M1 != -1 && segmentNum0M0 == -1 && segmentNum1M0 == -1 && segmentNum1M1 == -1) {
					markDeletion = "M1";
					chrBreakpointDeletion = chrBreakpoint0;
					chrBreakpointInsertion = chrBreakpoint1;
					isDI = true;
				} else if (segmentNum1M0 != -1 && segmentNum1M1 == -1 && segmentNum0M0 == -1 && segmentNum0M1 == -1) {
					markDeletion = "M0";
					chrBreakpointDeletion = chrBreakpoint1;
					chrBreakpointInsertion = chrBreakpoint0;
					isDI = false;
				} else if (segmentNum1M1 != -1 && segmentNum1M0 == -1 && segmentNum0M0 == -1 && segmentNum0M1 == -1) {
					markDeletion = "M1";
					chrBreakpointDeletion = chrBreakpoint1;
					chrBreakpointInsertion = chrBreakpoint0;
					isDI = false;
				} else if (segmentNum0M0 == -1 && segmentNum0M1 == -1 && segmentNum1M0 == -1 && segmentNum1M1 == -1) {
					TranslocationWithoutUsingDerChr translocationWithoutUsingDerChr = parseTranslocationWithoutUsingDerChr(previousChrList, previousChrMarks, chr0, chr1, chrBreakpoint0, chrBreakpoint1);
					if (translocationWithoutUsingDerChr.isWrong()) {
						return false;
					} else {
						markDeletion = translocationWithoutUsingDerChr.getMarkDeletion();
						chrBreakpointDeletion = translocationWithoutUsingDerChr.getChrBreakpointDeletion();
						chrBreakpointInsertion = translocationWithoutUsingDerChr.getChrBreakpointInsertion();
						isDI = translocationWithoutUsingDerChr.isDI();
					}
				} else {
					return false;
				}	
				break;
			}
			case 1: {				
				isDI = chrArmList.indexOf(derChrArm0) == 0;
				if (isDI) {
					chrBreakpointDeletion = chrBreakpoint0;
					chrBreakpointInsertion = chrBreakpoint1;
				} else {
					chrBreakpointDeletion = chrBreakpoint1;
					chrBreakpointInsertion = chrBreakpoint0;
				}					
				if (findSegment(chrBreakpointDeletion, getSegmentNumList("M0")) != -1 && findSegment(chrBreakpointDeletion, getSegmentNumList("M1")) != -1) {
					return false;
				} else if (findSegment(chrBreakpointDeletion, getSegmentNumList("M0")) != -1 || findSegment(chrBreakpointDeletion, getSegmentNumList("M1")) != -1) {
					markDeletion = findSegment(chrBreakpointDeletion, getSegmentNumList("M0")) != -1 ? "M0" : "M1";
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
				break;
			}
			case 0: {
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
				break;
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
    		List<String> chrBreakpointList = Arrays.asList(mark0ChrBreakpoint, mark1ChrBreakpoint); 
			List<String> chrMarkList = Arrays.asList("M0", "M1");	
			for (int i = 0; i < 2; i++) {
				String chrBreakpoint = chrBreakpointList.get(i);
				String chrMark = chrMarkList.get(i);
				List<List<String>> segmentsOfChr = getSegmentsOfChr(chrMark);
		    	List<Integer> segmentNumsOfMark = getSegmentNumList(chrMark); 
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
			int derCen1Segment = findSegmentContainingDerCen(derChr0, segments.subList(derCen0Segment + 1, segments.size()));
			if (derCen0Segment == -1 || derCen1Segment == -1) {
				return false;
			} 
			derCen1Segment += derCen0Segment + 1;
			List<Integer> segmentNumsOfMarkDeletion = getSegmentNumList(markDeletion);
	    	int segmentNum = findSegment(chrBreakpointDeletion, segmentNumsOfMarkDeletion);
	    	if (segmentNum > derCen0Segment && segmentNum < derCen1Segment) {
				return false; // Impossible by construction
			} else if (segmentNum <= derCen0Segment) {
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
			int segmentNum0 = findSegment(mark0ChrBreakpoint, getSegmentNumList("M0"));
			int segmentNum1 = findSegment(mark1ChrBreakpoint, getSegmentNumList("M1"));	
			translocateForBalancedExchange(segmentNum0, segmentNum1, mark0ChrBreakpoint, mark1ChrBreakpoint, "M0", "M1");
		}	
		
		// Record ParsingOrder_Marks map
		if (!isBalancedExchange) {
			if (isDI) {
				parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(markDeletion, markInsertion));
			} else {
				parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(markInsertion, markDeletion));
			}
		} else {
			String chr0Mark = chrBreakpoint0.equals(mark0ChrBreakpoint)? "M0" : "M1"; 
			String chr1Mark = chr0Mark.equals("M0") ? "M1" : "M0";
			parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(chr0Mark, chr1Mark));
		}
		
		return true;
		
    }
	
}
