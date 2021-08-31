package validation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
public class DerivativeValidation {
	
	protected String derChr;
	protected List<Event> subevents;
	private boolean containingR;
	
	protected int subeventIndex = 0;
	protected List<Integer> eventIndexList = new ArrayList<>();
	protected List<String> subeventNatureList = new ArrayList<>();
	protected List<List<String>> subeventChrList = new ArrayList<>();	
	
	protected List<String> rawStringList = new ArrayList<>();
	
	protected List<List<String>> segments = new ArrayList<>();
	// Each segment can be defined by either one string or two strings
	// If a segment is defined by only one string, it is a special segment, like ? (? is used for "add" subevent) and hsr.
	// A typical segment is normally defined by two chromesome breakpoints, del(5)(q13q33) will generate two segments, [5pter, 5q13] and [5q33, 5qter].
	// ** In previously version, I use a particular mark, like S1, to point to a homologous chromosome, e.g. der(1)t(1;1)(p31;q32) the added segment [1q32, 1qter] will be marked as S1. 

	protected Map<Integer, List<String>> parsingOrderMarksMap = new HashMap<>(); // Include M and H marks, listed in the order of chr 
	protected Set<String> commonMarkSet = new HashSet<>(); // Include only M marks
	protected Set<String> homologousMarkSet = new HashSet<>(); // Include only H marks
	// One chr may have multiple marks; different chrs must have different marks. The relationship between marks and chrs is N:1.
	protected Map<String, String> markToChrMap = new HashMap<>();
	// Except M0, each mark has only one list recording gained bands; M0 has two lists recording lost bands and gained bands, respectively.
	// The following map is to record the lost bands on derChr (M0) and the gain bands on all the other chrs 
	protected Map<String, ArrayList<Integer>> markSpecificLossGain = new HashMap<>();
	// For M0, we need a second collection to record gained segments (Note segments MOT bands)
	protected List<List<String>> derChrGainedSegments = new ArrayList<>();
		
//	private Map<String, List<String>> homologousChrSubstitutionMap = new HashMap<>(); //Map to segment, instead of listing all its bands, because later on we may need to write out the detailed format of the derivative chromosome.
	
	protected Map<String, List<String>> insertedPlaceListMap = new HashMap<>();
	protected Map<String, List<String>> hsrPlaceListMap = new HashMap<>();
	
	public DerivativeValidation() {}
	public DerivativeValidation(DerEvent e) {
		this.derChr = e.getChrList().get(0);
		rawStringList.add(derChr + "pterM0");
		rawStringList.add(derChr + "qterM0");
		segments.add(Arrays.asList(rawStringList.get(0), rawStringList.get(1)));
		commonMarkSet.add("M0");
		markToChrMap.put("M0", derChr);
		markSpecificLossGain.put("M0", initializeChrLossGain(derChr));
		subevents = e.getSubevents();
		for (Event subevent: subevents) {
			eventIndexList.add(subeventIndex++);
			subeventNatureList.add(subevent.getNature());
			subeventChrList.add(subevent.getChrList());
		}
		containingR = false;
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
	
	public DerivativeValidationOutcome getDerivativeValidationOutcome() {		
		if (subeventNatureList.indexOf("r") > -1) {
			return getDerivativeValidationOutcomeContainingR();
		} else {
			return getDerivativeValidationOutcomeWithoutR();
		}
	}
	
	public DerivativeValidationOutcome getDerivativeValidationOutcomeContainingR() {
		int rIndex = subeventNatureList.indexOf("r");
		if (!isValidDerContainingR(subevents, rIndex)) {
			return new DerivativeValidationOutcome(false, segments, null, null);
		}
		boolean validDerivativeChr = true;
		outer:
		for (int i: eventIndexList) {
			Event subevent = subevents.get(i);
			if (!subevent.isUncertainEvent()) {
				List<List<String>> breakpointsFullName = subevent.getBreakpointsFullName(subevent.getChrList(), subevent.getBreakpoints());
				switch (subevent.getNature()) {
					case "add": {
						if (!isValidAdd(breakpointsFullName, derChr, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "del": {
						if (!isValidDel(breakpointsFullName, derChr, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "dup": {
						if (!isValidDup(breakpointsFullName, derChr, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "trp": {
						if (!isValidTrp(breakpointsFullName, derChr, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "qdp": {
						if (!isValidQdp(breakpointsFullName, derChr, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "ins": {
						if (!isValidIns(breakpointsFullName, derChr, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "inv": {
						if (!isValidInv(breakpointsFullName, derChr, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "t": {
						if (!isValidT(breakpointsFullName, derChr, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "r": {
						if (!isValidR(breakpointsFullName, subevent.getChrList(), derChr, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
					case "hsr": {
						if (!isValidHsr(breakpointsFullName, derChr, eventIndexList, i)) {
							validDerivativeChr = false;
							break outer;
						}
						break;
					}
				}
			}
		}
		if (validDerivativeChr) {
			return new DerivativeValidationOutcome(validDerivativeChr, segments, getDetailedSystem(), getDerKaryotypeLGF());
		} else {
			return new DerivativeValidationOutcome(validDerivativeChr, segments, null, null);
		}
	}
	
	public DerivativeValidationOutcome getDerivativeValidationOutcomeWithoutR() {
		boolean validDerivativeChr = true;
		List<String> derChrList = Arrays.asList(derChr);
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
						if (!isValidAdd(breakpointsFullName, derChr, eventIndexList, i)) {
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
						if (!isValidDel(breakpointsFullName, derChr, eventIndexList, i)) {
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
						if (!isValidDup(breakpointsFullName, derChr, eventIndexList, i)) {
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
						if (!isValidTrp(breakpointsFullName, derChr, eventIndexList, i)) {
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
						if (!isValidQdp(breakpointsFullName, derChr, eventIndexList, i)) {
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
						if (!isValidIns(breakpointsFullName, derChr, eventIndexList, i)) {
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
						if (!isValidInv(breakpointsFullName, derChr, eventIndexList, i)) {
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
						if (!isValidT(breakpointsFullName, derChr, eventIndexList, i)) {
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
						if (!isValidHsr(breakpointsFullName, derChr, eventIndexList, i)) {
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
			
			if (eventIndexList.size() > firstPrimaryTEventIndex + 1 && !subeventChrList.get(firstPrimaryTEventIndex + 1).contains(derChr)) {
				return new DerivativeValidationOutcome(false, segments, null, null);
			} // Exclude: 46,XY,der(9)t(3;5)(q24;p14)t(3;9)(q21;p23)del(5)(p15.2)t(9;11)(q32;q13)
			
			List<Integer> correctParsingOrder = new ArrayList<>();
			correctParsingOrder.addAll(eventIndexList);
			correctParsingOrder.subList(reverseOrderEnd, correctParsingOrder.size()).clear();
			for (int i = firstPrimaryTEventIndex; i >= reverseOrderEnd; i--) {
    			correctParsingOrder.add(i);
    		}
    		for (int i = firstPrimaryTEventIndex + 1; i < eventIndexList.size(); i++) {
    			correctParsingOrder.add(i);
    		}    		
    		rawStringList = new ArrayList<>();
    		rawStringList.add(derChr + "pterM0");
    		rawStringList.add(derChr + "qterM0");
    		segments = new ArrayList<>();
    		segments.add(Arrays.asList(rawStringList.get(0), rawStringList.get(1)));
    		insertedPlaceListMap = new HashMap<>();
    		hsrPlaceListMap = new HashMap<>();
    		parsingOrderMarksMap = new HashMap<>();
    		commonMarkSet = new HashSet<>();
    		commonMarkSet.add("M0");
    		homologousMarkSet = new HashSet<>();
    		markToChrMap = new HashMap<>();
    		markToChrMap.put("M0", derChr);
    		markSpecificLossGain = new HashMap<>();
    		markSpecificLossGain.put("M0", initializeChrLossGain(derChr));
    		derChrGainedSegments = new ArrayList<>();
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
							if (!isValidAdd(breakpointsFullName, derChr, correctParsingOrder, i)) {							
								validDerivativeChr = false;
								break outer2;
							}
							break;
						}
						case "del": {
							if (!isValidDel(breakpointsFullName, derChr, correctParsingOrder, i)) {							
								validDerivativeChr = false;
								break outer2;
							}
							break;
						}
						case "dup": {
							if (!isValidDup(breakpointsFullName, derChr, correctParsingOrder, i)) {							
								validDerivativeChr = false;
								break outer2;
							}
							break;
						}
						case "trp": {
							if (!isValidTrp(breakpointsFullName, derChr, correctParsingOrder, i)) {							
								validDerivativeChr = false;
								break outer2;
							}
							break;
						}
						case "qdp": {
							if (!isValidQdp(breakpointsFullName, derChr, correctParsingOrder, i)) {							
								validDerivativeChr = false;
								break outer2;
							}
							break;
						}
						case "ins": {
							if (!isValidIns(breakpointsFullName, derChr, correctParsingOrder, i)) {							
								validDerivativeChr = false;
								break outer2;
							}
							break;
						}
						case "inv": {
							if (!isValidInv(breakpointsFullName, derChr, correctParsingOrder, i)) {	
								validDerivativeChr = false;
								break outer2;
							}
							break;
						}
						case "t": {
							if (!isValidT(breakpointsFullName, derChr, correctParsingOrder, i)) {	
								validDerivativeChr = false;
								break outer2;
							}
							break;
						}
						case "hsr": {
							if (!isValidHsr(breakpointsFullName, derChr, correctParsingOrder, i)) {
								validDerivativeChr = false;
								break outer2;
							}
							break;
						}
					}
				}
			}    		   		
		} 
		
		if (validDerivativeChr) {
			return new DerivativeValidationOutcome(validDerivativeChr, segments, getDetailedSystem(), getDerKaryotypeLGF());
		} else {
			return new DerivativeValidationOutcome(validDerivativeChr, segments, null, null);
		}
	}
	
	protected boolean isParacentricSegment(List<String> segment) {
		return getChrArm(segment.get(0)).equals(getChrArm(segment.get(1))) ? true : false;
	}
	
	// This will transform the segment from the interval to all its SegmentBands list 
	protected List<String> getAllSegmentBands(List<String> segment) {
		List<String> translatedSegment = getTranslatedSegment(segment);
		List<String> segmentBands = new ArrayList<>();
		if (isParacentricSegment(translatedSegment)) {
			String beginBand = getMin(translatedSegment);
			String endBand = getMax(translatedSegment);
			int beginIndex = getBeginIndex(beginBand);
			int endIndex = getEndIndex(endBand);
			for (int i = beginIndex; i <= endIndex; i++) {
				segmentBands.add(indexToChrMap.get(i));
			}
		} else {
			for (String segmentEndPoint: translatedSegment) {
				String chrArm = getChrArm(segmentEndPoint);
				for (int i = chrToIndexMap.get(chrArm + "10"); i <= getEndIndex(segmentEndPoint); i++) {
					segmentBands.add(indexToChrMap.get(i));
				}
			}			
		}
		return segmentBands;
	}
	
	protected List<String> getTranslatedSegment(List<String> segment) {
		List<String> translatedSegment = new ArrayList<>();
		for (String endPoint: segment) {
			if (endPoint.contains("ter")) {	
				String chr;
				if (endPoint.contains("p")) {
					chr = endPoint.substring(0, endPoint.indexOf("p"));
					translatedSegment.add(doChrTerReflection(chr, "P"));
				} else {
					chr = endPoint.substring(0, endPoint.indexOf("q"));
					translatedSegment.add(doChrTerReflection(chr, "Q"));
				}
			} else if (endPoint.contains("M")) {
				String chrBreakpoint = endPoint.substring(0, endPoint.indexOf("M"));
				translatedSegment.add(chrBreakpoint);
			} else if (endPoint.contains("H")) {
				String chrBreakpoint = endPoint.substring(0, endPoint.indexOf("H"));
				translatedSegment.add(chrBreakpoint);
			} else {
				translatedSegment.add(endPoint);
			}			
		}
		return translatedSegment;
	}
	
	// Not testing whether to be a proper subset, but testing whether both have an intersection
	protected boolean isInSegment(String chrBreakpoint, List<String> segment) {
		List<String> subbandsOfBreakpoint = getAllKeys(chrBreakpoint);		
		List<String> segmentBands = getAllSegmentBands(segment);
		return subbandsOfBreakpoint.removeAll(segmentBands);		
	}
	
	protected List<List<String>> getSegmentsOfChr(String mark) {
		List<List<String>> segmentsOfChr = new ArrayList<>();
		for (int i = 0; i < segments.size(); i++) {
			if (segments.get(i).get(0).endsWith(mark)) {
				segmentsOfChr.add(segments.get(i));
			}
		}
		return segmentsOfChr;		
//		List<String> rawStringListOfChr = new ArrayList<>();
//		for (String s: rawStringList) {
//			if (s.endsWith(mark)) {
//				rawStringListOfChr.add(s);
//			}
//		}
//		return getSegments(rawStringListOfChr);
	}
	
	protected boolean isInMultipleSegments(String chrBreakpoint, List<List<String>> segmentsOfChr) {
		if (segmentsOfChr.size() <= 1) {
			return false;
		} else {
			int count = 0;
			for (List<String> segment: segmentsOfChr) {
				if (segment.size() == 2 && isInSegment(chrBreakpoint, segment)) {
					count++;
				}
				if (count > 1) {
					return true;
				}
			}
			return false;
		}
	}
	
	protected List<Integer> getSegmentNumList(String mark) {	
		return Stream.iterate(0, i -> i + 1).limit(segments.size()).filter(i -> segments.get(i).get(0).endsWith(mark)).collect(Collectors.toList());		
//		List<Integer> segmentNumsOfMark = new ArrayList<>();
//		for (int i = 0; i < segments.size(); i++) {
//			if (segments.get(i).get(0).endsWith(mark)) {
//				segmentNumsOfMark.add(i);
//			}
//		}
//		return segmentNumsOfMark;
	}
	
	private List<Integer> getMultipleSegments(String chrBreakpoint, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark) {
		if (segmentsOfChr.size() <= 1) {
			return null;
		} else {
			List<Integer> segmentNums = new ArrayList<>();
			for (int i = 0; i < segmentNumsOfMark.size(); i++) {
				int segmentNum = segmentNumsOfMark.get(i); // i is relative number, segmentNum is absolute number
                List<String> segment = segments.get(segmentNum);
				if (segment.size() == 2 && isInSegment(chrBreakpoint, segment)) {
					segmentNums.add(segmentNum); 
				}
			}			
			return segmentNums;
		}
	}
	
	// Assume isInMultipleSegments = false
	protected int findSegment(String chrBreakpoint, List<Integer> segmentNumsOfMark) {
		for (int i = 0; i < segmentNumsOfMark.size(); i++) {
			int segmentNum = segmentNumsOfMark.get(i);
			List<String> segment = segments.get(segmentNum);
			if (segment.size() == 2 && isInSegment(chrBreakpoint, segment)) {
				return segmentNum;
			}
		}
		return -1;
	}	
	
	// We have to use getTranslatedSegmentII. O/w 46,XY,der(8)t(8;22)(q22;q12)t(8;17)(p23;q21) will not pass isStrictlyCoarserThanExistingSegmentEndPoint() test
	
	protected boolean isStrictlyFinerThanExistingSegmentEndPoint(String chrBreakpoint, List<String> segment) {
		for (String segmentEndPoint: getTranslatedSegmentII(segment)) {
			if (chrBreakpoint.startsWith(segmentEndPoint) && !chrBreakpoint.equals(segmentEndPoint)) {
				return true;
			}
		}
		return false;
	}
	
	protected boolean isStrictlyCoarserThanExistingSegmentEndPoint(String chrBreakpoint, List<String> segment) {
		for (String segmentEndPoint: getTranslatedSegmentII(segment)) {
			if (segmentEndPoint.startsWith(chrBreakpoint) && !segmentEndPoint.equals(chrBreakpoint)) {
				return true;
			}
		}
		return false;
	}
	
	protected boolean isFinerThanExistingSegmentEndPoint(String chrBreakpoint, List<String> segment) {
		for (String segmentEndPoint: getTranslatedSegmentII(segment)) {
			if (chrBreakpoint.startsWith(segmentEndPoint)) {
				return true;
			}
		}
		return false;
	}

//	private String getNextSubstitutionKey() {
//		int count = homologousChrSubstitutionMap.size();
//		return "S" + count;
//	}
	
	protected String getNextCommonMark() {
		return "M" + commonMarkSet.size();
	}
	
	protected String getNextHomologousMark() {
		return "H" + homologousMarkSet.size();
	}
	
	protected List<List<String>> getSegments(List<String> rawStringList) {
		// Since we may have regular segments defined by two strings indicating chromosome bands
		// and special segments defined by one symbolic string like "?" or "hsr", 
		// we need to find a way to correctly transform from a rawStringList to a list of segments.
		// Be particularly careful about "?", as "?" can be generated by additional meterial or other uncentainty. 
		int[] segmentNumeratingArray = new int[rawStringList.size()];
		int segmentCount = 0;
		int segmentEndPointCount = 0;
		for (int i = 0; i < rawStringList.size(); i++) {
			if (rawStringList.get(i).startsWith("?M") || rawStringList.get(i).startsWith("hsr")) {
				segmentCount++;  
			} else {
				// Whenever we have two segmentEndPoints for a regular segment, we add 1 to the number of segments.
				if (segmentEndPointCount % 2 == 0) {
					segmentCount++;					
				} 
				segmentEndPointCount++;
			}
			segmentNumeratingArray[i] = segmentCount;
		}
		List<List<String>> segments = new ArrayList<>();
		IntStream.range(0, segmentCount).mapToObj(ArrayList<String>::new).forEach(segments::add);		
		for (int i = 0; i < segmentNumeratingArray.length; i++) {
			segments.get(segmentNumeratingArray[i] - 1).add(rawStringList.get(i));
		}
		return segments;
	} 
	
	protected int findSegmentContainingDerCen(String derChr, List<List<String>> segments) {
		int index = 0;
		for (List<String> segment: segments) {
			if (segment.size() == 2 && isInSegment(derChr + "p10", segment) && isInSegment(derChr + "q10", segment)) {
				return index;
			} 
			index++;
		}
		return -1;
	}
	
		

	protected boolean isValidAdd(List<List<String>> breakpointsFullName, String derChr, List<Integer> correctParsingOrder, int currentParsingPlace) {
		String chrBreakpoint = breakpointsFullName.get(0).get(0);
		String chr = getChr(chrBreakpoint);
		int derCenSegment = findSegmentContainingDerCen(derChr, segments);
		
		String mark;
		if (chr.equals(derChr)) {
			mark = "M0";
		} else {
			// Find whether the previous parsing rearrangement contains this chr
			if (currentParsingPlace == 0) {
				return false;
			}
			Event previousEvent = subevents.get(correctParsingOrder.get(currentParsingPlace - 1));
			int chrIndex = previousEvent.getChrList().indexOf(chr);
			if (chrIndex == -1) {
				return false;
			}
			// If yes, find its corresponding mark
			mark = parsingOrderMarksMap.get(currentParsingPlace - 1).get(chrIndex);
			if (mark == null || !mark.startsWith("M")) {
				return false;
			} 
		}
		List<List<String>> segmentsOfChr = getSegmentsOfChr(mark);
		if (isInMultipleSegments(chrBreakpoint, segmentsOfChr)) {
			return false;
		} else {
			List<Integer> segmentNumsOfMark = getSegmentNumList(mark);
			int segmentNum = findSegment(chrBreakpoint, segmentNumsOfMark);
			if (segmentNum == -1) {
				return false;
			} else {
				List<String> segment = segments.get(segmentNum);
				if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint, segment) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint, segment)) {
					return false;
					// Example 1: 46,XX,der(1)del(1)(p13)add(1)(p13.2) -- Unmatched chrBreakpoints (F)
					// Example 2: 46,XX,der(1)del(1)(p13.2)add(1)(p13) -- Unmatched chrBreakpoints (F)
					// Example 3: 46,XX,der(1)del(1)(p13)add(1)(p13) -- Matched chrBreakpoints (T)
				}
				List<List<String>> deletedSegments = new ArrayList<>();
				if (chr.equals(derChr)) {
					if (isParacentricSegment(segment)) {
						if (segmentNum < derCenSegment) {
							addLHS(chrBreakpoint, mark, segmentNum, deletedSegments);
						} else if (segmentNum > derCenSegment) {
		                	addRHS(chrBreakpoint, mark, segmentNum, deletedSegments);
						} else {
							// Impossible to have segmentNum == derCenSegment, as derCenSegment is the only pericentric segment.
						}
					} else {
						if (getChrArm(segment.get(0)).equals(getChrArm(chrBreakpoint))) {
							addLHS(chrBreakpoint, mark, segmentNum, deletedSegments);
						} else {
							addRHS(chrBreakpoint, mark, segmentNum, deletedSegments);
						}
					}
				} else {
					if (segmentNum < derCenSegment) {
						addLHS(chrBreakpoint, mark, segmentNum, deletedSegments);
					}
					if (segmentNum > derCenSegment) {
		            	addRHS(chrBreakpoint, mark, segmentNum, deletedSegments);
					}
				}
				// Record LGF
				recordLoss(deletedSegments, derChrGainedSegments);
			}
		}
		parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(mark));
		segments = getSegments(rawStringList);
		return true;
	}
	
	// This index will be the index of the last endpoint of the segment segmentNum
	protected int getOffsetInRawStringList(int segmentNum) {
		int index = -1;
		for (int i = 0; i <= segmentNum; i++) {
			index += segments.get(i).size();
		}
		return index;
	}	
	
	protected void addLHS(String chrBreakpoint, String mark, int segmentNum, List<List<String>> deletedSegments) {
		List<String> deletedChrBreakpoints = new ArrayList<>();
		deletedChrBreakpoints.addAll(rawStringList.subList(0, getOffsetInRawStringList(segmentNum)));
		deletedChrBreakpoints.add(chrBreakpoint + mark);
		deletedSegments.addAll(getSegments(deletedChrBreakpoints));
		
		rawStringList.subList(0, getOffsetInRawStringList(segmentNum)).clear();
		rawStringList.add(0, chrBreakpoint + mark);
		rawStringList.add(0, "?" + mark);
	}
	
	protected void addRHS(String chrBreakpoint, String mark, int segmentNum, List<List<String>> deletedSegments) {
    	List<String> deletedChrBreakpoints = new ArrayList<>();
    	deletedChrBreakpoints.addAll(rawStringList.subList(getOffsetInRawStringList(segmentNum), rawStringList.size()));
    	deletedChrBreakpoints.add(0, chrBreakpoint + mark);
    	deletedSegments.addAll(getSegments(deletedChrBreakpoints));
    	
    	rawStringList.subList(getOffsetInRawStringList(segmentNum), rawStringList.size()).clear();
    	rawStringList.add(chrBreakpoint + mark);
    	rawStringList.add("?" + mark);
	}
    
    // When any problematic chrBreakpoint is in multiple segments, it may still be correct, e.g. 46,XX,der(1)inv(1)(p12q22)del(1)(q12q22)
    // Three conditions must be satisfied:
    // i) The "part" containing the above problematic chrBreakpoint is concerned with 2 chrBreakpoints in the structural rearrangement being examined 
    // ii) There is ONLY one segment containing both of the above 2 chrBreakpoints so that the confusion will disappear.  
    // iii) Note that the endpoint of this segment should NOT be coarser than any problematic chrBreakpoint, e.g. 46,XX,der(1)inv(1)(p13p22)del(1)(p13.2p22.2)
    // Note this exception is applicable only when chrBreakpoints are different.
	protected int getUniqueSegment(String chrBreakpoint0, String chrBreakpoint1, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark) {
    	if (chrBreakpoint0.equals(chrBreakpoint1)) {
    		return -1;
    	}    	
    	List<Integer> segmentNumList = new ArrayList<>();
    	int index = 0;
    	for (List<String> segment: segmentsOfChr) {
    		if (segment.size() == 2 && isInSegment(chrBreakpoint0, segment) && isInSegment(chrBreakpoint1, segment) && !isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint0, segment) && !isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint1, segment) && !isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint0, segment) && !isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint1, segment)) {
    			segmentNumList.add(segmentNumsOfMark.get(index));
    		}
    		if (segmentNumList.size() > 1) {
    			return -1;
    		}
    		index++;
    	}
    	return segmentNumList.size() == 1 ? segmentNumList.get(0) : -1;
    }
    
    protected boolean isValidDel(List<List<String>> breakpointsFullName, String derChr, List<Integer> correctParsingOrder, int currentParsingPlace) {
    	String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
    	int derCenSegment = findSegmentContainingDerCen(derChr, segments);
    	String chr = getChr(chrBreakpoint0);
    	
    	String mark;
		if (chr.equals(derChr)) {
			mark = "M0";
		} else {
			// Find whether the previous parsing rearrangement contains this chr
			if (currentParsingPlace == 0) {
				return false;
			}
			Event previousEvent = subevents.get(correctParsingOrder.get(currentParsingPlace - 1));
			int chrIndex = previousEvent.getChrList().indexOf(chr);
			if (chrIndex == -1) {
				return false;
			}
			// If yes, find its corresponding mark
			mark = parsingOrderMarksMap.get(currentParsingPlace - 1).get(chrIndex);
			if (mark == null || !mark.startsWith("M")) {
				return false;
			} 
		}
		List<List<String>> segmentsOfChr = getSegmentsOfChr(mark);
		List<Integer> segmentNumsOfMark = getSegmentNumList(mark);
        if (breakpointsFullName.get(0).size() == 1) {
			if (isInMultipleSegments(chrBreakpoint0, segmentsOfChr)) {
				return false;
			} 
			int segmentNum = findSegment(chrBreakpoint0, segmentNumsOfMark);
			if (segmentNum == -1) {
				return false;
			} else {
				List<String> segment = segments.get(segmentNum);
				if (isFinerThanExistingSegmentEndPoint(chrBreakpoint0, segment) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint0, segment)) {
					return false;
				} // Note we don't allow karyotypes like 46,XX,der(1)del(1)(p12)del(1)(p12); we do allow 46,XX,der(1)del(1)(p12)add(1)(p12), though.
				List<List<String>> deletedSegments = new ArrayList<>();
				if (chr.equals(derChr)) {
					if (isParacentricSegment(segment)) {
						if (segmentNum < derCenSegment) {
							delLHS(chrBreakpoint0, mark, segmentNum, deletedSegments);
						} else if (segmentNum > derCenSegment) {
		                	delRHS(chrBreakpoint0, mark, segmentNum, deletedSegments);
						} else {
							// Impossible to have segmentNum == derCenSegment, as derCenSegment is pericentric.
						}
					} else {
						if (getChrArm(segment.get(0)).equals(getChrArm(chrBreakpoint0))) {
							delLHS(chrBreakpoint0, mark, segmentNum, deletedSegments);
						} else {
							delRHS(chrBreakpoint0, mark, segmentNum, deletedSegments);
						}
					}
				} else {
					if (segmentNum < derCenSegment) {
						delLHS(chrBreakpoint0, mark, segmentNum, deletedSegments);
					}
					if (segmentNum > derCenSegment) {
		            	delRHS(chrBreakpoint0, mark, segmentNum, deletedSegments);
					}
				}
				// Record LGF
				recordLoss(deletedSegments, derChrGainedSegments);
			}
			parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(mark));
			segments = getSegments(rawStringList);	
		} else {
			String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
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
					} // We don't allow der(1)del(1)(q12)del(1)(q12q12)
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
			
			List<List<String>> deletedSegments = new ArrayList<>();
			if (segmentNum0 == segmentNum1) {
    			delInOneSegment(chrBreakpoints, segmentNum0, mark, deletedSegments);
    		} else if (segmentNum0 < segmentNum1) {
    			delInMoreThanOneSegment(chrBreakpoint0, chrBreakpoint1, segmentNum0, segmentNum1, mark, deletedSegments);
    			// Check whether derChr's cen has been deleted
    			if (chr.equals(derChr) && findSegmentContainingDerCen(derChr, segments) == -1) {
    				return false;
    			}	    			
    		} else {
    			delInMoreThanOneSegment(chrBreakpoint1, chrBreakpoint0, segmentNum1, segmentNum0, mark, deletedSegments);
    			// Check whether derChr's cen has been deleted
    			if (chr.equals(derChr) && findSegmentContainingDerCen(derChr, segments) == -1) {
    				return false;
    			}
    		}
			// Record LGF
			recordLoss(deletedSegments, derChrGainedSegments);
			parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(mark));
		}
    	return true;
    }
    
    protected void delLHS(String chrBreakpoint, String mark, int segmentNum, List<List<String>> deletedSegments) {
		List<String> deletedChrBreakpoints = new ArrayList<>();
		deletedChrBreakpoints.addAll(rawStringList.subList(0, getOffsetInRawStringList(segmentNum)));
		deletedChrBreakpoints.add(chrBreakpoint + mark);
		deletedSegments.addAll(getSegments(deletedChrBreakpoints));
		
		rawStringList.subList(0, getOffsetInRawStringList(segmentNum)).clear();
		rawStringList.add(0, chrBreakpoint + mark);
	}
	
    protected void delRHS(String chrBreakpoint, String mark, int segmentNum, List<List<String>> deletedSegments) {
    	List<String> deletedChrBreakpoints = new ArrayList<>();
    	deletedChrBreakpoints.addAll(rawStringList.subList(getOffsetInRawStringList(segmentNum), rawStringList.size()));
    	deletedChrBreakpoints.add(0, chrBreakpoint + mark);
    	deletedSegments.addAll(getSegments(deletedChrBreakpoints));    	
    	
    	rawStringList.subList(getOffsetInRawStringList(segmentNum), rawStringList.size()).clear();
    	rawStringList.add(rawStringList.size(), chrBreakpoint + mark);
	}
    
    protected void delInOneSegment(List<String> chrBreakpoints, int segmentNum, String mark, List<List<String>> deletedSegments) {
    	int offset = getOffsetInRawStringList(segmentNum);
    	List<String> segment = segments.get(segmentNum);
    	
    	List<String> holeInserted = digHoleInSegment(chrBreakpoints, segment, mark);
    	
    	rawStringList.addAll(offset, holeInserted);
    	segments.remove(segmentNum);
    	// Add elements from right to left, so that the element is always added in the same offset.
    	segments.add(segmentNum, Arrays.asList(holeInserted.get(1), segment.get(1)));
    	segments.add(segmentNum, Arrays.asList(segment.get(0), holeInserted.get(0)));    
    	
    	deletedSegments.add(holeInserted);
    }
    
    private List<String> digHoleInSegment(List<String> chrBreakpoints, List<String> segment, String mark) {
    	List<String> segmentHole = new ArrayList<>();
    	if (isParacentricSegment(segment)) {
    		if (segment.get(0).compareTo(segment.get(1)) <= 0) {
        		segmentHole.add(getMin(chrBreakpoints) + mark);
        		segmentHole.add(getMax(chrBreakpoints) + mark);
        	} else {
        		segmentHole.add(getMax(chrBreakpoints) + mark);
        		segmentHole.add(getMin(chrBreakpoints) + mark);
        	}    		
    	} else if (getChrArm(chrBreakpoints.get(0)).equals(getChrArm(segment.get(0)))) {
			segmentHole.add(getMax(chrBreakpoints) + mark);
			segmentHole.add(getMin(chrBreakpoints) + mark);
		} else {
			segmentHole.add(getMin(chrBreakpoints) + mark);
    		segmentHole.add(getMax(chrBreakpoints) + mark);    		
    	} 
    	return segmentHole;
    }
    
    protected void delInMoreThanOneSegment(String chrBreakpointL, String chrBreakpointR, int segmentLNum, int segmentRNum, String mark, List<List<String>> deletedSegments) {
    	List<String> deletedChrBreakpoints = new ArrayList<>();
    	int offset0 = getOffsetInRawStringList(segmentLNum);
    	int offset1 = getOffsetInRawStringList(segmentRNum);
    	deletedChrBreakpoints.addAll(rawStringList.subList(offset0, offset1));
    	deletedChrBreakpoints.add(0, chrBreakpointL + mark);
    	deletedChrBreakpoints.add(chrBreakpointR + mark);
    	deletedSegments.addAll(getSegments(deletedChrBreakpoints));
    	
    	rawStringList.subList(offset0, offset1).clear();
    	rawStringList.add(offset0, chrBreakpointR + mark);
    	rawStringList.add(offset0, chrBreakpointL + mark);
    	// Change the R endpoint of L segment with chrBreakpointL
    	segments.get(segmentLNum).set(1, chrBreakpointL + mark);
    	// Change the L endpoint of R segment with chrBreakpointR
    	segments.get(segmentRNum).set(0, chrBreakpointR + mark);
    	segments.subList(segmentLNum + 1, segmentRNum).clear();
    }   
    
    
    // Assume chrBreakpoints are in different segments with segmentNumL < segmentNumR
    protected boolean isDerCenDuplicated(int segmentNumL, int segmentNumR, int derCenSegment, String chrBreakpointL, String chrBreakpointR) {
		if (segmentNumL < derCenSegment && derCenSegment < segmentNumR) {
			return true;
		}
		if (segmentNumL == derCenSegment && derCenSegment < segmentNumR) {
			List<String> segment = segments.get(segmentNumL);
			if (getChrArm(segment.get(0)).equals(getChrArm(chrBreakpointL))) {
				return true;
			}
		}
		if (segmentNumL < derCenSegment && derCenSegment == segmentNumR) {
			List<String> segment = segments.get(segmentNumR);
			if (getChrArm(segment.get(1)).equals(getChrArm(chrBreakpointR))) {
				return true;
			}
		}
		return false;
	}
       

    protected boolean isValidDup(List<List<String>> breakpointsFullName, String derChr, List<Integer> correctParsingOrder, int currentParsingPlace) {
    	String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
    	String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
    	String chr = getChr(chrBreakpoint0);
    	
    	String mark;
		if (chr.equals(derChr)) {
			mark = "M0";
		} else {
			// Find whether the previous parsing rearrangement contains this chr
			if (currentParsingPlace == 0) {
				return false;
			}
			Event previousEvent = subevents.get(correctParsingOrder.get(currentParsingPlace - 1));
			int chrIndex = previousEvent.getChrList().indexOf(chr);
			if (chrIndex == -1) {
				return false;
			}
			// If yes, find its corresponding mark
			mark = parsingOrderMarksMap.get(currentParsingPlace - 1).get(chrIndex);
			if (mark == null || !mark.startsWith("M")) {
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
			if (chr.equals(derChr) && !getChrArm(chrBreakpoint0).equals(getChrArm(chrBreakpoint1))) {
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
            if (chr.equals(derChr)) {
				if (segmentNum0 == segmentNum1) {
					if (!getChrArm(chrBreakpoint0).equals(getChrArm(chrBreakpoint1))) {
	    				return false;
	    			}
				} else {
					int derCenSegment = findSegmentContainingDerCen(derChr, segments);
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
			duplicateII(chrBreakpoint0, chrBreakpoint1, derChr, segmentNum0, mark, segmentsOfChr, segmentNumsOfMark, duplicatedSegments);
		} else {
			duplicateIII(chrBreakpoint0, chrBreakpoint1, derChr, segmentNum0, segmentNum1, mark, segmentsOfChr, segmentNumsOfMark, duplicatedSegments);
		}
		
		parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(mark));
		recordDuplicate(duplicatedSegments, derChrGainedSegments);
		
    	return true;
    }
    
    // Identify which is the left breakpoint in the existing breakpoints list, assuming derChr's cen is not duplicated
    private int findFurtherLeftBreakpoint(String chrBreakpoint0, String chrBreakpoint1, String derChr, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark) {    	
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
			List<String> segment = segments.get(segmentNum0);
			int derCenSegment = findSegmentContainingDerCen(derChr, segments);
			if (segmentNum0 != derCenSegment) {
				// Find the orientation of this segment by comparing both ends of this segment
				if (segment.get(0).compareTo(segment.get(1)) < 0) {
					return chrBreakpoint0.compareTo(chrBreakpoint1) <= 0 ? 0 : 1;
				} else {
					return chrBreakpoint0.compareTo(chrBreakpoint1) <= 0 ? 1 : 0;
				}
			} else {
				// Find the orientation of this segment by examining whether it is to the left or right of derCen
				String segLeftArm = getChrArm(segments.get(derCenSegment).get(0));
				String chrBreakpointArm = getChrArm(chrBreakpoint0);				
				if (segLeftArm.equals(chrBreakpointArm)) {
					return chrBreakpoint0.compareTo(chrBreakpoint1) <= 0 ? 1 : 0;
				} else {
					return chrBreakpoint0.compareTo(chrBreakpoint1) <= 0 ? 0 : 1;
				}
			}
		} else if (segmentNum0 < segmentNum1) {
			return 0;
		} else {
			return 1;
		} 
    }
       
    private int findFurtherRightBreakpoint(int leftBreakpointN) {
    	return leftBreakpointN == 0 ? 1 : 0 ;
    }
    
    // This returns global segment number
    private int getSegmentOfFurtherLeftBreakpoint(String chrBreakpoint0, String chrBreakpoint1, String derChr, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark) {		
		if (isInMultipleSegments(chrBreakpoint0, segmentsOfChr) || isInMultipleSegments(chrBreakpoint1, segmentsOfChr)) {
			// Then it must be in a unique segment containing both chrBreakpoints
			return getUniqueSegment(chrBreakpoint0, chrBreakpoint1, segmentsOfChr, segmentNumsOfMark);    			
		} else {
			int segmentNum0 = findSegment(chrBreakpoint0, segmentNumsOfMark);
			int segmentNum1 = findSegment(chrBreakpoint1, segmentNumsOfMark);
			return findFurtherLeftBreakpoint(chrBreakpoint0, chrBreakpoint1, derChr, segmentsOfChr, segmentNumsOfMark) == 0 ? segmentNum0 : segmentNum1 ;
		}
    }
    
    // Find the orientation of duplication by identifying which is the left breakpoint in Duplication copy
    // If chrBreakpoints are to the left of derCen, then the orientation will be chrBreakpoint1->chrBreakpoint0
    // If chrBreakpoints are to the right of derCen, then the orientation will be chrBreakpoint0->chrBreakpoint1
    private int findFurtherLeftBreakpointOfDuplicationCopy(String chrBreakpoint0, String chrBreakpoint1, String derChr, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark) {
    	int derCenSegment = findSegmentContainingDerCen(derChr, segments);
    	int leftBreakpointSegment = getSegmentOfFurtherLeftBreakpoint(chrBreakpoint0, chrBreakpoint1, derChr, segmentsOfChr, segmentNumsOfMark);
    	if (leftBreakpointSegment < derCenSegment) {
    		return 1;
    	} else if (leftBreakpointSegment > derCenSegment) {
    		return 0;
    	} else {
    		// Since both chrBreakpoints are located in the same segment as derCen and they will not duplicate derCen, they must have the same chr arm.
    		return getChrArm(segments.get(derCenSegment).get(0)).equals(getChrArm(chrBreakpoint0)) ? 1 : 0 ;
    	}
    }
    
    // This is for duplication which happens inside a segment. 
    // Example: der(1)inv(1)(p31p35)dup(1)(p31p33)
    // Example: der(1)add(1)(p35)dup(1)(p31p33)
    // Note that if this is the inverted segment, then it is impossible to have same chrBreakpoints, as they will not pass the InUniqueSegment test
    private void duplicateII(String chrBreakpoint0, String chrBreakpoint1, String derChr, int segmentNum, String mark, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark, List<List<String>> duplicatedSegments) {
    	int offset = getOffsetInRawStringList(segmentNum);
    	List<String> segment = segments.get(segmentNum);
    	
    	int leftBreakpointN = findFurtherLeftBreakpoint(chrBreakpoint0, chrBreakpoint1, derChr, segmentsOfChr, segmentNumsOfMark);
    	int leftBreakpointNDuplicationCopy = findFurtherLeftBreakpointOfDuplicationCopy(chrBreakpoint0, chrBreakpoint1, derChr, segmentsOfChr, segmentNumsOfMark);
    		    	
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
    
    // Both chrBreakpoints are the same
    protected void duplicateI(String chrBreakpoint, int segmentNum, String mark, List<List<String>> duplicatedSegments) {
    	int offset = getOffsetInRawStringList(segmentNum);    	
    	List<String> segment = segments.get(segmentNum);
    	String markedChrBreakpoint = chrBreakpoint + mark;    	
    	rawStringList.addAll(offset, Collections.nCopies(4, markedChrBreakpoint));
		if (segment.get(1).equals(chrBreakpoint)) {    	
			segments.addAll(segmentNum + 1, Collections.nCopies(2, Arrays.asList(markedChrBreakpoint, markedChrBreakpoint)));
		} else if (segment.get(0).equals(chrBreakpoint)) {
			segments.addAll(segmentNum, Collections.nCopies(2, Arrays.asList(markedChrBreakpoint, markedChrBreakpoint)));
		} else {
			String leftend = segment.get(0); // String is immutable; even if in the future segment.get(0) will be changed, leftend will be locked in its current value. That's why I don't need to use String leftend = new String(segment.get(0))      		
    		segments.get(segmentNum).set(0, markedChrBreakpoint);
    		segments.add(segmentNum, Arrays.asList(markedChrBreakpoint, markedChrBreakpoint));
    		segments.add(segmentNum, Arrays.asList(leftend, markedChrBreakpoint));
		}
		duplicatedSegments.add(Arrays.asList(markedChrBreakpoint, markedChrBreakpoint));
    }
    
    // Two chrBreakpoints are located in different segments
    private void duplicateIII(String chrBreakpoint0, String chrBreakpoint1, String derChr, int segmentNum0, int segmentNum1, String mark, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark, List<List<String>> duplicatedSegments) {
    	
    	int segmentNumL = Integer.min(segmentNum0, segmentNum1);
		int segmentNumR = Integer.max(segmentNum0, segmentNum1);
    	int offset = getOffsetInRawStringList(segmentNumR);
    	
    	int leftBreakpointN = findFurtherLeftBreakpoint(chrBreakpoint0, chrBreakpoint1, derChr, segmentsOfChr, segmentNumsOfMark);
    	int leftBreakpointNDuplicationCopy = findFurtherLeftBreakpointOfDuplicationCopy(chrBreakpoint0, chrBreakpoint1, derChr, segmentsOfChr, segmentNumsOfMark);
    		    	
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
    

    protected boolean isValidTrp(List<List<String>> breakpointsFullName, String derChr, List<Integer> correctParsingOrder, int currentParsingPlace) {
    	String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
    	String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
    	String chr = getChr(chrBreakpoint0);
    	
    	String mark;
		if (chr.equals(derChr)) {
			mark = "M0";
		} else {
			// Find whether the previous parsing rearrangement contains this chr
			if (currentParsingPlace == 0) {
				return false;
			}
			Event previousEvent = subevents.get(correctParsingOrder.get(currentParsingPlace - 1));
			int chrIndex = previousEvent.getChrList().indexOf(chr);
			if (chrIndex == -1) {
				return false;
			}
			// If yes, find its corresponding mark
			mark = parsingOrderMarksMap.get(currentParsingPlace - 1).get(chrIndex);
			if (mark == null || !mark.startsWith("M")) {
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
			if (chr.equals(derChr) && !getChrArm(chrBreakpoint0).equals(getChrArm(chrBreakpoint1))) {
				return false;
			}
			segmentNum1 = segmentNum0;
		} else {
			segmentNum0 = findSegment(chrBreakpoint0, segmentNumsOfMark);
			segmentNum1 = findSegment(chrBreakpoint1, segmentNumsOfMark);;
			    			
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
            if (chr.equals(derChr)) {
				if (segmentNum0 == segmentNum1) {
					if (!getChrArm(chrBreakpoint0).equals(getChrArm(chrBreakpoint1))) {
	    				return false;
	    			}
				} else {
					int derCenSegment = findSegmentContainingDerCen(derChr, segments);
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
			triplicateII(chrBreakpoint0, chrBreakpoint1, derChr, segmentNum0, mark, segmentsOfChr, segmentNumsOfMark, duplicatedSegments);
		} else {
			triplicateIII(chrBreakpoint0, chrBreakpoint1, derChr, segmentNum0, segmentNum1, mark, segmentsOfChr, segmentNumsOfMark, duplicatedSegments);
		}
		
		parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(mark));
		recordDuplicate(duplicatedSegments, derChrGainedSegments);
		
    	return true;
    }
    
    // This is for triplication which happens inside a segment. 
    private void triplicateII(String chrBreakpoint0, String chrBreakpoint1, String derChr, int segmentNum, String mark, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark, List<List<String>> duplicatedSegments) {
    	int offset = getOffsetInRawStringList(segmentNum);
    	List<String> segment = segments.get(segmentNum);
    	
    	int leftBreakpointN = findFurtherLeftBreakpoint(chrBreakpoint0, chrBreakpoint1, derChr, segmentsOfChr, segmentNumsOfMark);
    	int leftBreakpointNDuplicationCopy = findFurtherLeftBreakpointOfDuplicationCopy(chrBreakpoint0, chrBreakpoint1, derChr, segmentsOfChr, segmentNumsOfMark);
    		    	
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
    
    // Both chrBreakpoints are the same
    protected void triplicateI(String chrBreakpoint, int segmentNum, String mark, List<List<String>> duplicatedSegments) {
    	int offset = getOffsetInRawStringList(segmentNum);    	
    	List<String> segment = segments.get(segmentNum);
    	String markedChrBreakpoint = chrBreakpoint + mark;
    	rawStringList.addAll(offset, Collections.nCopies(6, markedChrBreakpoint));
		if (segment.get(1).equals(chrBreakpoint)) {    
			segments.addAll(segmentNum + 1, Collections.nCopies(3, Arrays.asList(markedChrBreakpoint, markedChrBreakpoint)));
		} else if (segment.get(0).equals(chrBreakpoint)) {
			segments.addAll(segmentNum, Collections.nCopies(3, Arrays.asList(markedChrBreakpoint, markedChrBreakpoint)));
		} else {
			String leftend = segment.get(0); // String is immutable; even if in the future segment.get(0) will be changed, leftend will be locked in its current value. That's why I don't need to use String leftend = new String(segment.get(0))      		
    		segments.get(segmentNum).set(0, markedChrBreakpoint);
    		segments.addAll(segmentNum, Collections.nCopies(2, Arrays.asList(markedChrBreakpoint, markedChrBreakpoint)));
    		segments.add(segmentNum, Arrays.asList(leftend, markedChrBreakpoint));
		}
		
		duplicatedSegments.addAll(Collections.nCopies(2, Arrays.asList(markedChrBreakpoint, markedChrBreakpoint)));
    }
    
    // Two chrBreakpoints are located in different segments
    private void triplicateIII(String chrBreakpoint0, String chrBreakpoint1, String derChr, int segmentNum0, int segmentNum1, String mark, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark, List<List<String>> duplicatedSegments) {
    	
    	int segmentNumL = Integer.min(segmentNum0, segmentNum1);
		int segmentNumR = Integer.max(segmentNum0, segmentNum1);
    	int offset = getOffsetInRawStringList(segmentNumR);
    	
    	int leftBreakpointN = findFurtherLeftBreakpoint(chrBreakpoint0, chrBreakpoint1, derChr, segmentsOfChr, segmentNumsOfMark);
    	int leftBreakpointNDuplicationCopy = findFurtherLeftBreakpointOfDuplicationCopy(chrBreakpoint0, chrBreakpoint1, derChr, segmentsOfChr, segmentNumsOfMark);
    		    	
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
    

    protected boolean isValidQdp(List<List<String>> breakpointsFullName, String derChr, List<Integer> correctParsingOrder, int currentParsingPlace) {
    	String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
    	String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
    	String chr = getChr(chrBreakpoint0);
    	
    	String mark;
		if (chr.equals(derChr)) {
			mark = "M0";
		} else {
			// Find whether the previous parsing rearrangement contains this chr
			if (currentParsingPlace == 0) {
				return false;
			}
			Event previousEvent = subevents.get(correctParsingOrder.get(currentParsingPlace - 1));
			int chrIndex = previousEvent.getChrList().indexOf(chr);
			if (chrIndex == -1) {
				return false;
			}
			// If yes, find its corresponding mark
			mark = parsingOrderMarksMap.get(currentParsingPlace - 1).get(chrIndex);
			if (mark == null || !mark.startsWith("M")) {
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
			if (chr.equals(derChr) && !getChrArm(chrBreakpoint0).equals(getChrArm(chrBreakpoint1))) {
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
            if (chr.equals(derChr)) {
				if (segmentNum0 == segmentNum1) {
					if (!getChrArm(chrBreakpoint0).equals(getChrArm(chrBreakpoint1))) {
	    				return false;
	    			}
				} else {
					int derCenSegment = findSegmentContainingDerCen(derChr, segments);
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
			quadruplicateII(chrBreakpoint0, chrBreakpoint1, derChr, segmentNum0, mark, segmentsOfChr, segmentNumsOfMark, duplicatedSegments);
		} else {
			quadruplicateIII(chrBreakpoint0, chrBreakpoint1, derChr, segmentNum0, segmentNum1, mark, segmentsOfChr, segmentNumsOfMark, duplicatedSegments);
		}
		
		parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(mark));
		recordDuplicate(duplicatedSegments, derChrGainedSegments);
		
    	return true;
    }
    
    // This is for quadruplication which happens inside a segment. 
    private void quadruplicateII(String chrBreakpoint0, String chrBreakpoint1, String derChr, int segmentNum, String mark, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark, List<List<String>> duplicatedSegments) {
    	int offset = getOffsetInRawStringList(segmentNum);
    	List<String> segment = segments.get(segmentNum);
    	
    	int leftBreakpointN = findFurtherLeftBreakpoint(chrBreakpoint0, chrBreakpoint1, derChr, segmentsOfChr, segmentNumsOfMark);
    	int leftBreakpointNDuplicationCopy = findFurtherLeftBreakpointOfDuplicationCopy(chrBreakpoint0, chrBreakpoint1, derChr, segmentsOfChr, segmentNumsOfMark);
    		    	
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
    
    // Both chrBreakpoints are the same
    protected void quadruplicateI(String chrBreakpoint, int segmentNum, String mark, List<List<String>> duplicatedSegments) {
    	int offset = getOffsetInRawStringList(segmentNum);    	
    	List<String> segment = segments.get(segmentNum);
    	String markedChrBreakpoint = chrBreakpoint + mark;
    	rawStringList.addAll(offset, Collections.nCopies(8, markedChrBreakpoint));
		if (segment.get(1).equals(chrBreakpoint)) {
			segments.addAll(segmentNum + 1, Collections.nCopies(4, Arrays.asList(markedChrBreakpoint, markedChrBreakpoint)));
		} else if (segment.get(0).equals(chrBreakpoint)) {
			segments.addAll(segmentNum, Collections.nCopies(4, Arrays.asList(markedChrBreakpoint, markedChrBreakpoint)));
		} else {
			String leftend = segment.get(0); // String is immutable; even if in the future segment.get(0) will be changed, leftend will be locked in its current value. That's why I don't need to use String leftend = new String(segment.get(0))      		
    		segments.get(segmentNum).set(0, markedChrBreakpoint);
    		segments.addAll(segmentNum, Collections.nCopies(3, Arrays.asList(markedChrBreakpoint, markedChrBreakpoint)));
    		segments.add(segmentNum, Arrays.asList(leftend, markedChrBreakpoint));
		}
		
		duplicatedSegments.addAll(Collections.nCopies(3, Arrays.asList(markedChrBreakpoint, markedChrBreakpoint)));
    }
    
    // Two chrBreakpoints are located in different segments
    private void quadruplicateIII(String chrBreakpoint0, String chrBreakpoint1, String derChr, int segmentNum0, int segmentNum1, String mark, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark, List<List<String>> duplicatedSegments) {
    	
    	int segmentNumL = Integer.min(segmentNum0, segmentNum1);
		int segmentNumR = Integer.max(segmentNum0, segmentNum1);
    	int offset = getOffsetInRawStringList(segmentNumR);
    	
    	int leftBreakpointN = findFurtherLeftBreakpoint(chrBreakpoint0, chrBreakpoint1, derChr, segmentsOfChr, segmentNumsOfMark);
    	int leftBreakpointNDuplicationCopy = findFurtherLeftBreakpointOfDuplicationCopy(chrBreakpoint0, chrBreakpoint1, derChr, segmentsOfChr, segmentNumsOfMark);
    		    	
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
    
    
    protected boolean isValidIns(List<List<String>> breakpointsFullName, String derChr, List<Integer> correctParsingOrder, int currentParsingPlace) {
    	// We have either within-chromosome insertion or between-chromosome insertion
    	if (breakpointsFullName.size() == 1) {
    		String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
    		String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
    		String chrBreakpoint2 = breakpointsFullName.get(0).get(2);
    		
    		String chr = getChr(chrBreakpoint0);
    		String mark;
    		if (chr.equals(derChr)) {
    			mark = "M0";
    		} else {
    			// Check whether the current rearrangement is connecting with the previous one 
    			if (currentParsingPlace == 0) {
    				return false;
    			}
    			Event previousEvent = subevents.get(correctParsingOrder.get(currentParsingPlace - 1));
    			int chrIndex = previousEvent.getChrList().indexOf(chr);
    			if (chrIndex == -1) {
    				return false;
    			}			
    			mark = parsingOrderMarksMap.get(currentParsingPlace - 1).get(chrIndex);
    			if (mark == null || !mark.startsWith("M")) {
    				return false;
    			}
    		}
    		// First we cannot have multiple segments inserted at the same place. c.f. My email correspondence with Dr. Yang Cao on 11/7/2017
    		// For example: 46,XX,der(1)ins(1;2)(q12;q11q13)ins(1;3)(q12;q11q13)
			for (String s: getChrBreakpointsOfAllResolution(chrBreakpoint0)) {
				if (insertedPlaceListMap.get(mark) != null && insertedPlaceListMap.get(mark).contains(s)) {
					return false;
				}
			}
			// Exclude confusing karyotypes
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
				if (!isValidInsertedSegment(chrBreakpoint0, chrBreakpoint1, chrBreakpoint2, chr, derChr, segmentsOfChr, segmentNumsOfMark)) {
    				return false;
    			}
			} else {
    			segmentNum0 = findSegment(chrBreakpoint0, segmentNumsOfMark);
    			segmentNum1 = findSegment(chrBreakpoint1, segmentNumsOfMark);
    			segmentNum2 = findSegment(chrBreakpoint2, segmentNumsOfMark);
    			if (segmentNum0 == -1 || segmentNum1 == -1 || segmentNum2 == -1) {
    				return false;
    			}
    			if (!isValidInsertedSegment(chrBreakpoint0, chrBreakpoint1, chrBreakpoint2, chr, derChr, segmentsOfChr, segmentNumsOfMark)) {
    				return false;
    			}
    			List<String> segment1 = segments.get(segmentNum1);
    			if (chrBreakpoint1.equals(chrBreakpoint2)) {
					if (isFinerThanExistingSegmentEndPoint(chrBreakpoint1, segment1) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint1, segment1)) {
						return false;
					} // We don't allow der(1)del(1)(q12)ins(1)(p21q12q12)
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
			insertWithinChr(chrBreakpoint0, chrBreakpoint1, chrBreakpoint2, derChr, segmentNum0, segmentNum1, segmentNum2, mark, segmentsOfChr, segmentNumsOfMark);
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
    		if (chr0.equals(derChr)) {
    			mark0 = "M0";
    		} else {
    			if (currentParsingPlace == 0) {
    				return false;
    			}
    			Event previousEvent = subevents.get(correctParsingOrder.get(currentParsingPlace - 1));
    			int chrIndex = previousEvent.getChrList().indexOf(chr0);
    			if (chrIndex == -1) {
    				return false;
    			}			
    			mark0 = parsingOrderMarksMap.get(currentParsingPlace - 1).get(chrIndex);
    			if (mark0 == null || !mark0.startsWith("M")) {
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
    
    // Within-chromosome insertion
    private boolean isValidInsertedSegment(String chrBreakpoint0, String chrBreakpoint1, String chrBreakpoint2, String chr, String derChr, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark) {
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
    		if (chr.equals(derChr) && (isInSegment(derChr + "p10", segment) || isInSegment(derChr + "q10", segment))) {
				return false;
			}
    	}
    	return true;
    }
    
    protected List<String> getChrBreakpointsOfAllResolution(String chrBreakpoint) {
    	List<String> chrBreakpointsOfAllResolutionList = new ArrayList<>();
    	chrBreakpointsOfAllResolutionList.add(chrBreakpoint);
    	List<String> chrBreakpointsOfLowerResolution = getChrBreakpointsOfLowerResolution(chrBreakpoint);
    	List<String> chrBreakpointsSetOfHigherResolution = getChrBreakpointsOfHigherResolution(chrBreakpoint);
    	if (chrBreakpointsOfLowerResolution != null) {
    		chrBreakpointsOfAllResolutionList.addAll(chrBreakpointsOfLowerResolution);
    	}
    	if (chrBreakpointsSetOfHigherResolution != null) {
    		chrBreakpointsOfAllResolutionList.addAll(chrBreakpointsSetOfHigherResolution);
    	}    	
    	return chrBreakpointsOfAllResolutionList;
    }
    
    private List<String> getChrBreakpointsOfLowerResolution(String chrBreakpoint) {
    	if (!chrBreakpoint.contains(".")) {
    		return null;
    	}
    	List<String> chrBreakpointsOfLowerResolution = new ArrayList<>();
    	String subband = chrBreakpoint.substring(chrBreakpoint.indexOf(".") + 1);
    	if (subband.length() == 3) {
    		chrBreakpointsOfLowerResolution.add(chrBreakpoint.substring(0, chrBreakpoint.length() - 1));
    		chrBreakpointsOfLowerResolution.add(chrBreakpoint.substring(0, chrBreakpoint.length() - 2));
    		chrBreakpointsOfLowerResolution.add(chrBreakpoint.substring(0, chrBreakpoint.length() - 4));
    	}
    	if (subband.length() == 2) {
    		chrBreakpointsOfLowerResolution.add(chrBreakpoint.substring(0, chrBreakpoint.length() - 1));
    		chrBreakpointsOfLowerResolution.add(chrBreakpoint.substring(0, chrBreakpoint.length() - 3));
    	}
    	if (subband.length() == 1) {
    		chrBreakpointsOfLowerResolution.add(chrBreakpoint.substring(0, chrBreakpoint.length() - 2));
    	}
    	return chrBreakpointsOfLowerResolution;
    }
    
    private List<String> getChrBreakpointsOfHigherResolution(String chrBreakpoint) {
    	if (chrToIndexMap.get(chrBreakpoint) != null) {
    		return null;
    	} 
    	List<String> chrBreakpointsOfHighestResolution = getAllKeys(chrBreakpoint);
    	int lengthOfHighestResolution = chrBreakpointsOfHighestResolution.get(0).length();
    	if (!chrBreakpoint.contains(".")) {
    		if (lengthOfHighestResolution == chrBreakpoint.length() + 4) {
    			Set<String> completeChrBreakpointsSetOfHigherResolution = new HashSet<>(chrBreakpointsOfHighestResolution);
    			for (String s: chrBreakpointsOfHighestResolution) {
    				completeChrBreakpointsSetOfHigherResolution.add(s.substring(0, s.length() - 1));
    				completeChrBreakpointsSetOfHigherResolution.add(s.substring(0, s.length() - 2));
    			}
    			return new ArrayList<>(completeChrBreakpointsSetOfHigherResolution);
    		}    		
    		if (lengthOfHighestResolution == chrBreakpoint.length() + 3) {
    			Set<String> completeChrBreakpointsSetOfHigherResolution = new HashSet<>(chrBreakpointsOfHighestResolution);
    			for (String s: chrBreakpointsOfHighestResolution) {
    				completeChrBreakpointsSetOfHigherResolution.add(s.substring(0, s.length() - 1));
    			}
    			return new ArrayList<>(completeChrBreakpointsSetOfHigherResolution);
    		}			
    	} else {
    		String subband = chrBreakpoint.substring(chrBreakpoint.indexOf(".") + 1);
    		if (subband.length() == 1 && lengthOfHighestResolution == chrBreakpoint.length() + 2) {
				Set<String> completeChrBreakpointsSetOfHigherResolution = new HashSet<>(chrBreakpointsOfHighestResolution);
    			for (String s: chrBreakpointsOfHighestResolution) {
    				completeChrBreakpointsSetOfHigherResolution.add(s.substring(0, s.length() - 1));
    			}
    			return new ArrayList<>(completeChrBreakpointsSetOfHigherResolution);
    		}
    	}    	
    	return chrBreakpointsOfHighestResolution;
    }
    
    // Order the three breakpoints inside a within-chr insertion
    private List<String> orderChrBreakpoints(String chrBreakpoint0, String chrBreakpoint1, String chrBreakpoint2, String derChr, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark) {
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
    	int leftEndOfInsertedSegment = findFurtherLeftBreakpoint(chrBreakpoint1, chrBreakpoint2, derChr, segmentsOfChr, segmentNumsOfMark) == 0 ? 1 : 2 ;

    	List<String> list1 = Arrays.asList(chrBreakpoint1, chrBreakpoint2, chrBreakpoint0);
    	List<String> list2 = Arrays.asList(chrBreakpoint2, chrBreakpoint1, chrBreakpoint0);
    	List<String> list3 = Arrays.asList(chrBreakpoint0, chrBreakpoint1, chrBreakpoint2);
    	List<String> list4 = Arrays.asList(chrBreakpoint0, chrBreakpoint2, chrBreakpoint1);
    	
    	if (segmentNum0 == segmentNum1 && segmentNum1 == segmentNum2) {
    		// Order chrBreakpoint0 and chrBreakpoint1
    		int derCenSegment = findSegmentContainingDerCen(derChr, segments);
    		
    		if (segmentNum0 == derCenSegment) {
    			
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
    
    private boolean isInsertedPlaceToLeftOfCen(String chrBreakpoint0, List<Integer> segmentNumsOfMark0) {
    	int segmentNum0 = findSegment(chrBreakpoint0, segmentNumsOfMark0);
    	int derCenSegment = findSegmentContainingDerCen(derChr, segments);
    	if (segmentNum0 < derCenSegment) {
    		return true;
    	} else if (segmentNum0 > derCenSegment) {
    		return false;
    	} else if (getChrArm(chrBreakpoint0).equals(getChrArm(segments.get(derCenSegment).get(0)))) {
    		return true;
    	} else {
    		return false;
    	}
    }    
    
    private void insertWithinChr(String chrBreakpoint0, String chrBreakpoint1, String chrBreakpoint2, String derChr, int segmentNum0, int segmentNum1, int segmentNum2, String mark, List<List<String>> segmentsOfChr, List<Integer> segmentNumsOfMark) {
    	
    	int leftBreakpointN = findFurtherLeftBreakpoint(chrBreakpoint1, chrBreakpoint2, derChr, segmentsOfChr, segmentNumsOfMark);
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

    	if (segmentNum1 == segmentNum2) {   		
    		
    		if (segmentNum0 == segmentNum1) {
    			List<String> sourceList;
    			List<String> orderedChrBreakpoints = orderChrBreakpoints(chrBreakpoint0, chrBreakpoint1, chrBreakpoint2, derChr, segmentsOfChr, segmentNumsOfMark);
    			int indexOfInsertedPlace = orderedChrBreakpoints.indexOf(chrBreakpoint0); // either 0 or 2
    			// I don't need to worry about the situation that chrBreakpoint0 will be (partially) the same with either chrBreakpoint1 or chrBreakpoint2
    			// b/c at this stage, it has passed the valid_inserted_segment test so that chrBreakpoint0 will not be inside the inserted segment.
    			if (indexOfInsertedPlace == 0) {
    				if (isInsertedPlaceToLeftOfCen(chrBreakpoint0, segmentNumsOfMark)) {
    					sourceList = Arrays.asList(chrBreakpoint0 + mark, chrBreakpoint2 + mark, chrBreakpoint1 + mark, chrBreakpoint0 + mark, leftBreakpoint, rightBreakpoint);
    				} else {
    					sourceList = Arrays.asList(chrBreakpoint0 + mark, chrBreakpoint1 + mark, chrBreakpoint2 + mark, chrBreakpoint0 + mark, leftBreakpoint, rightBreakpoint);
    				}
    			} else {
    				if (isInsertedPlaceToLeftOfCen(chrBreakpoint0, segmentNumsOfMark)) {
    					sourceList = Arrays.asList(leftBreakpoint, rightBreakpoint, chrBreakpoint0 + mark, chrBreakpoint2 + mark, chrBreakpoint1 + mark, chrBreakpoint0 + mark);
    				} else {
    					sourceList = Arrays.asList(leftBreakpoint, rightBreakpoint, chrBreakpoint0 + mark, chrBreakpoint1 + mark, chrBreakpoint2 + mark, chrBreakpoint0 + mark);
    				}
    			}    			
    			rawStringList.addAll(offset0, sourceList);    			
    		} else {
    			List<String> sourceList0;
    			if (isInsertedPlaceToLeftOfCen(chrBreakpoint0, segmentNumsOfMark)) {
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
			if (isInsertedPlaceToLeftOfCen(chrBreakpoint0, segmentNumsOfMark)) { 
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
			if (!isInsertedPlaceToLeftOfCen(chrBreakpoint0, segmentNumsOfMark)) { 
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
    	if (isInsertedPlaceToLeftOfCen(chrBreakpoint0, segmentNumsOfMark0)) {
    		sourceList0 = Arrays.asList(chrBreakpoint0 + mark0, chrBreakpoint2 + mark1, chrBreakpoint1 + mark1, chrBreakpoint0 + mark0);
    	} else {
    		sourceList0 = Arrays.asList(chrBreakpoint0 + mark0, chrBreakpoint1 + mark1, chrBreakpoint2 + mark1, chrBreakpoint0 + mark0);
    	}
    	int offset0 = getOffsetInRawStringList(segmentNum0);    	
    	rawStringList.addAll(offset0, sourceList0);
    	segments = getSegments(rawStringList);
    }
    
    
    protected boolean isValidInv(List<List<String>> breakpointsFullName, String derChr, List<Integer> correctParsingOrder, int currentParsingPlace) {
    	String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
    	String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
    	String chr = getChr(chrBreakpoint0);
    	int segmentNum0, segmentNum1;
    	
    	String mark;
		if (chr.equals(derChr)) {
			mark = "M0";
		} else {
			if (currentParsingPlace == 0) {
				return false;
			}
			Event previousEvent = subevents.get(correctParsingOrder.get(currentParsingPlace - 1));
			int chrIndex = previousEvent.getChrList().indexOf(chr);
			if (chrIndex == -1) {
				return false;
			}			
			mark = parsingOrderMarksMap.get(currentParsingPlace - 1).get(chrIndex);
			if (mark == null || !mark.startsWith("M")) {
				return false;
			}
		}
		
		List<List<String>> segmentsOfChr = getSegmentsOfChr(mark);
		List<Integer> segmentNumsOfMark = getSegmentNumList(mark);
		
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
		    List<String> segment1 = segments.get(segmentNum1);
			if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint1, segment1) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint1, segment1)) {
				return false;
			}				
		}
		invert(chrBreakpoint0, chrBreakpoint1, segmentNum0, segmentNum1, mark);
		parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(mark));
		
    	return true;
    }
    
    protected void invert(String chrBreakpoint0, String chrBreakpoint1, int segmentNum0, int segmentNum1, String mark) {
    	int offset0 = getOffsetInRawStringList(segmentNum0);
    	int offset1 = getOffsetInRawStringList(segmentNum1);
    	
    	// Assuming chrBreakpoint0.compareTo(chrBreakpoint1) < 0, which is what I required in the initial validation step
    	if (segmentNum0 == segmentNum1) {
    		List<String> sourceList0;
    		List<String> segment0 = segments.get(segmentNum0);
    		boolean segmentNotReverselyOriented = segment0.get(0).compareTo(segment0.get(1)) <= 0;
    		if (segmentNotReverselyOriented) { 
    			if (!isParacentricSegment(segment0) && getChrArm(chrBreakpoint0).equals(getChrArm(chrBreakpoint1)) && getChrArm(chrBreakpoint0).endsWith("p")) {
    				sourceList0 = Arrays.asList(chrBreakpoint1 + mark, chrBreakpoint0 + mark, chrBreakpoint1 + mark, chrBreakpoint0 + mark);
    			} else {
    				sourceList0 = Arrays.asList(chrBreakpoint0 + mark, chrBreakpoint1 + mark, chrBreakpoint0 + mark, chrBreakpoint1 + mark);
    			}				    			
    		} else {
    			if (!isParacentricSegment(segment0) && getChrArm(chrBreakpoint0).equals(getChrArm(chrBreakpoint1)) && getChrArm(chrBreakpoint0).endsWith("p")) {
    				sourceList0 = Arrays.asList(chrBreakpoint0 + mark, chrBreakpoint1 + mark, chrBreakpoint0 + mark, chrBreakpoint1 + mark);
    			} else {
    				sourceList0 = Arrays.asList(chrBreakpoint1 + mark, chrBreakpoint0 + mark, chrBreakpoint1 + mark, chrBreakpoint0 + mark);
    			}    			
    		} 
    		rawStringList.addAll(offset0, sourceList0);
    	} else if (segmentNum0 < segmentNum1) {
    		List<String> deletedSublist = rawStringList.subList(offset0, offset1); 
    		List<String> invertedSegment = new ArrayList<>();
    		invertedSegment.add(chrBreakpoint0 + mark);
    		invertedSegment.addAll(deletedSublist);
    		invertedSegment.add(chrBreakpoint1 + mark);
    		List<String> sourceList0 = new ArrayList<>();
    		sourceList0.add(chrBreakpoint0 + mark);   
    		Collections.reverse(invertedSegment);
    		sourceList0.addAll(invertedSegment);
    		sourceList0.add(chrBreakpoint1 + mark);
    		rawStringList.subList(offset0, offset1).clear();
    		rawStringList.addAll(offset0, sourceList0);
    	} else {
    		List<String> deletedSublist = rawStringList.subList(offset1, offset0); 
    		List<String> invertedSegment = new ArrayList<>();
    		invertedSegment.add(chrBreakpoint1 + mark);
    		invertedSegment.addAll(deletedSublist);
    		invertedSegment.add(chrBreakpoint0 + mark);
    		List<String> sourceList1 = new ArrayList<>();
    		sourceList1.add(chrBreakpoint1 + mark);
    		Collections.reverse(invertedSegment);
    		sourceList1.addAll(invertedSegment);
    		sourceList1.add(chrBreakpoint0 + mark);
    		rawStringList.subList(offset1, offset0).clear();    		
    		rawStringList.addAll(offset1, sourceList1);
    	}
    	segments = getSegments(rawStringList);
    }
    
    protected boolean isValidT(List<List<String>> breakpointsFullName, String derChr, List<Integer> correctParsingOrder, int currentParsingPlace) {
    	String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
    	String chrBreakpoint1 = breakpointsFullName.get(1).get(0);
    	String chr0 = getChr(chrBreakpoint0);
    	String chr1 = getChr(chrBreakpoint1);
    	String markDeletion, markInsertion;   				
    	int segmentNumDeletion;	
    	String chrBreakpointDeletion, chrBreakpointInsertion;
    	
    	if (!chr0.equals(chr1)) {
    		if (chr0.equals(derChr) || chr1.equals(derChr)) {
    			if (chr0.equals(derChr)) {
    				chrBreakpointDeletion = chrBreakpoint0;
    				chrBreakpointInsertion = chrBreakpoint1;
    			} else {
    				chrBreakpointDeletion = chrBreakpoint1;
    				chrBreakpointInsertion = chrBreakpoint0;
    			}
    			markDeletion = "M0";
    		} else {    			
    			// Find chrDeletion candidate
    			if (currentParsingPlace == 0) {
    				return false;
    			}
    			Event previousEvent = subevents.get(correctParsingOrder.get(currentParsingPlace - 1));
    			List<String> previousChrList = previousEvent.getChrList();
    			if (previousChrList.contains(chr0) && previousChrList.contains(chr1)) {
    				int chr0Index = previousChrList.indexOf(chr0);
    				int chr1Index = previousChrList.indexOf(chr1);
    				String chr0Mark = parsingOrderMarksMap.get(currentParsingPlace - 1).get(chr0Index);
    				String chr1Mark = parsingOrderMarksMap.get(currentParsingPlace - 1).get(chr1Index);
    				if ((chr0Mark == null || !chr0Mark.startsWith("M")) && chr1Mark.startsWith("M")) {
    					markDeletion = chr1Mark; // Impossible to appear
    					chrBreakpointDeletion = chrBreakpoint1;
    					chrBreakpointInsertion = chrBreakpoint0;
        			} else if ((chr1Mark == null || !chr1Mark.startsWith("M")) && chr0Mark.startsWith("M")) {
        				markDeletion = chr0Mark; // Impossible to appear
        				chrBreakpointDeletion = chrBreakpoint0;
        				chrBreakpointInsertion = chrBreakpoint1;
        			} else if ((chr0Mark == null || !chr0Mark.startsWith("M")) && (chr1Mark == null || !chr1Mark.startsWith("M"))) {
        				return false; // Impossible to appear
        			} else {
        				int segmentNum0 = findSegment(chrBreakpoint0, getSegmentNumList(chr0Mark));
        				int segmentNum1 = findSegment(chrBreakpoint1, getSegmentNumList(chr1Mark));
        				if (segmentNum0 == -1 && segmentNum1 == -1) {
        					return false;
        				} else if (segmentNum0 != -1 && segmentNum1 == -1) {
        					markDeletion = chr0Mark;
        					chrBreakpointDeletion = chrBreakpoint0;
        					chrBreakpointInsertion = chrBreakpoint1;
        				} else if (segmentNum0 == -1 && segmentNum1 != -1) {
        					markDeletion = chr1Mark;
        					chrBreakpointDeletion = chrBreakpoint1;
        					chrBreakpointInsertion = chrBreakpoint0;
        				} else {
        					return false; // It may be chrBreakpoint0 in multiple segments of mark0 while chrBreakpoint1 in unique segment of mark1, I still think it's wrong. Debatable! 
        				} // segmentNum0 != -1 && segmentNum1 != -1
        			}    				
    			} else if (previousChrList.contains(chr0)) {
    				int chrIndex = previousChrList.indexOf(chr0);			
    				markDeletion = parsingOrderMarksMap.get(currentParsingPlace - 1).get(chrIndex);
        			if (markDeletion == null || !markDeletion.startsWith("M")) {
        				return false;
        			}
        			chrBreakpointDeletion = chrBreakpoint0;
        			chrBreakpointInsertion = chrBreakpoint1;
    			} else if (previousChrList.contains(chr1)) {
    				int chrIndex = previousChrList.indexOf(chr1);			
    				markDeletion = parsingOrderMarksMap.get(currentParsingPlace - 1).get(chrIndex);
        			if (markDeletion == null || !markDeletion.startsWith("M")) {
        				return false;
        			}
        			chrBreakpointDeletion = chrBreakpoint1;
        			chrBreakpointInsertion = chrBreakpoint0;
    			} else {
    				return false;
    			}
    			
    		}
    	} else if (!chr0.equals(derChr)) {
    		if (currentParsingPlace == 0) {
				return false;
			}
    		// Find relevant mark from previous parsing event
    		Event previousEvent = subevents.get(correctParsingOrder.get(currentParsingPlace - 1));
    		int chrIndex = previousEvent.getChrList().indexOf(chr0);
    		if (chrIndex == -1) {
				return false;
			}
    		markDeletion = parsingOrderMarksMap.get(currentParsingPlace - 1).get(chrIndex);
    		if (markDeletion == null || !markDeletion.startsWith("M")) {
				return false;
			}
    		chrBreakpointDeletion = chrBreakpoint0;
			chrBreakpointInsertion = chrBreakpoint1;
    	} else {
    		markDeletion = "M0";
    		chrBreakpointDeletion = chrBreakpoint0;
			chrBreakpointInsertion = chrBreakpoint1;
    	} 
    	// Exclude confusing karyotypes and validate chrBreakpointDeletion
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
    	// Create a new mark for chrToBeInserted
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
    	// Do the main task
		List<List<String>> deletedSegments = new ArrayList<>();
    	translocate(chrBreakpointDeletion, segmentNumDeletion, markDeletion, segmentNumsOfMarkDeletion, chrBreakpointInsertion, markInsertion, deletedSegments);
		// Record Loss and Gain
    	recordLoss(deletedSegments, derChrGainedSegments);
    	List<String> gainedSegment =  Arrays.asList(getChrArm(chrBreakpointInsertion) + "ter" + markInsertion, chrBreakpointInsertion + markInsertion);
    	recordGain(gainedSegment, markInsertion);
    	// Record ParsingOrder_Marks map
    	if (!chr0.equals(chr1)) {
    		if (chr0.equals(derChr)) {
    			parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(markDeletion, markInsertion));
    		} else if (chr1.equals(derChr)) {
    			parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(markInsertion, markDeletion));
    		} else if (chrBreakpointDeletion == chrBreakpoint0) {
    			parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(markDeletion, markInsertion));
    		} else {
    			parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(markInsertion, markDeletion));
    		}
    	} else {
    		parsingOrderMarksMap.put(currentParsingPlace, Arrays.asList(markDeletion, markInsertion));
    	}
    	
    	return true;
    }
    
    private void translocate(String chrBreakpointDeletion, int segmentNumDeletion, String markDeletion, List<Integer> segmentNumsOfMarkDeletion, String chrBreakpointInsertion, String markInsertion, List<List<String>> deletedSegments) {
    	List<String> deletedChrBreakpoints;
    	
    	int offset = getOffsetInRawStringList(segmentNumDeletion);
    	List<String> sourceList;
    	String chrArmInsertion = getChrArm(chrBreakpointInsertion);
    	if (isInsertedPlaceToLeftOfCen(chrBreakpointDeletion, segmentNumsOfMarkDeletion)) {
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
    
      
    private boolean isValidDerContainingR(List<Event> subevents, int rIndex) {
    	for (int i = rIndex + 1; i < subevents.size(); i++) {
    		Event subevent = subevents.get(i);
			switch (subevent.getNature()) {
				case "add": return false;
				case "del": {
					if (subevent.getBreakpoints().get(0).size() == 1) {
						return false;
					}
					break;
				}
				case "dup": break;
				case "trp": break;
				case "qdp": break;
				case "ins": break;
				case "inv": break;
				case "r": return false;
				case "t": return false;
				case "hsr": break;
    		}
    	}
    	return true;
    }
    
    private boolean isValidR(List<List<String>> breakpointsFullName, List<String> chrList, String derChr, List<Integer> correctParsingOrder, int currentParsingPlace) {
    	if (containingR) {
    		return false;
    	}
    	
    	int derChrIndex = chrList.indexOf(derChr);
    	if (derChrIndex == -1) {
    		return false;
    	}
    	String chrBreakpoint0 = breakpointsFullName.get(derChrIndex).get(0);
    	String chrBreakpoint1 = breakpointsFullName.get(derChrIndex).get(1);
    	List<List<String>> segmentsOfDerChr = getSegmentsOfChr("M0");
		List<Integer> segmentNumsOfDerMark = getSegmentNumList("M0");
		
		int segmentNum0, segmentNum1;	
		
		if (isInMultipleSegments(chrBreakpoint0, segmentsOfDerChr) || isInMultipleSegments(chrBreakpoint1, segmentsOfDerChr)) {
			segmentNum0 = getUniqueSegment(chrBreakpoint0, chrBreakpoint1, segmentsOfDerChr, segmentNumsOfDerMark);
			if (segmentNum0 == -1) {
				return false;
			}
			segmentNum1 = segmentNum0;
		} else {
			segmentNum0 = findSegment(chrBreakpoint0, segmentNumsOfDerMark);
			segmentNum1 = findSegment(chrBreakpoint1, segmentNumsOfDerMark);
			if (segmentNum0 == -1 || segmentNum1 == -1) {
				return false;
			} 
			List<String> segment0 = segments.get(segmentNum0);
		    List<String> segment1 = segments.get(segmentNum1);
			if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint0, segment0) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint0, segment0)) {
				return false;
			}
			if (isStrictlyFinerThanExistingSegmentEndPoint(chrBreakpoint1, segment1) || isStrictlyCoarserThanExistingSegmentEndPoint(chrBreakpoint1, segment1)) {
				return false;
			}				
		}
		
		String leftChrBreakpoint, rightChrBreakpoint;
		int leftSegmentNum, rightSegmentNum;
		// Examine the feasibility of the ring
		if (segmentNum0 == segmentNum1) {
			// Examine whether the fused ring contains a centromere
			if (getChrArm(chrBreakpoint0).equals(getChrArm(chrBreakpoint1))) {
				return false;
			} 
			List<String> segment = segments.get(segmentNum0);
			if (segment.get(0).compareTo(segment.get(1)) < 0) {
				if (chrBreakpoint0.compareTo(chrBreakpoint1) < 0) {
					leftChrBreakpoint = chrBreakpoint0;
					rightChrBreakpoint = chrBreakpoint1;
				} else {
					leftChrBreakpoint = chrBreakpoint1;
					rightChrBreakpoint = chrBreakpoint0;
				}
			} else {
				if (chrBreakpoint0.compareTo(chrBreakpoint1) < 0) {
					leftChrBreakpoint = chrBreakpoint1;
					rightChrBreakpoint = chrBreakpoint0;
				} else {
					leftChrBreakpoint = chrBreakpoint0;
					rightChrBreakpoint = chrBreakpoint1;
				}
			}
			leftSegmentNum = segmentNum0;
			rightSegmentNum = segmentNum0;
		} else {
			List<List<String>> fusedSegments = new ArrayList<>();
			if (segmentNum0 < segmentNum1) {
				leftChrBreakpoint = chrBreakpoint0;
				rightChrBreakpoint = chrBreakpoint1;
				leftSegmentNum = segmentNum0;
				rightSegmentNum = segmentNum1;
			} else {
				leftChrBreakpoint = chrBreakpoint1;
				rightChrBreakpoint = chrBreakpoint0;
				leftSegmentNum = segmentNum1;
				rightSegmentNum = segmentNum0;
			}
			// Examine whether the fused ring contains a centromere
			fusedSegments.add(Arrays.asList(leftChrBreakpoint, segments.get(leftSegmentNum).get(1)));
			for (int i = leftSegmentNum + 1; i < rightSegmentNum; i++) {
				fusedSegments.add(new ArrayList<>(segments.get(i)));
			}
			fusedSegments.add(Arrays.asList(segments.get(rightSegmentNum).get(0), rightChrBreakpoint));
			if (findSegmentContainingDerCen(derChr, fusedSegments) == -1) {
				return false;
			}
		}
		
		List<List<String>> deletedLSegments = new ArrayList<>();
		List<List<String>> deletedRSegments = new ArrayList<>();
		// Delete both ends from the DerChr <Remember always start from the right hand side!>
		delRHS(rightChrBreakpoint, "M0", rightSegmentNum, deletedRSegments);
		delLHS(leftChrBreakpoint, "M0", leftSegmentNum, deletedLSegments);
		
		// Record Loss
		List<List<String>> deletedSegments = new ArrayList<>();
		deletedSegments.addAll(deletedLSegments);
		deletedSegments.addAll(deletedRSegments);
		recordLoss(deletedSegments, derChrGainedSegments);
		
		// Process other chrs 
		List<String> markList = new ArrayList<>();
		Set<String> otherChrSet = new HashSet<>();
		for (int i = 0; i < chrList.size(); i++) {
			if (i == derChrIndex) {
				markList.add("M0");
				continue;
			}
			// Create a new mark for each of the remaining chrs
			String mark;
			String otherChr = chrList.get(i);
			if (otherChr.equals(derChr)) {
				mark = getNextHomologousMark();
				homologousMarkSet.add(mark);
			} else if (otherChrSet.contains(otherChr)) {				
				mark = getNextHomologousMark();
				homologousMarkSet.add(mark);
			} else {
				otherChrSet.add(otherChr);
				mark = getNextCommonMark();
				commonMarkSet.add(mark);
			}
			markToChrMap.put(mark, otherChr);
			markSpecificLossGain.put(mark, initializeChrLossGain(otherChr));
			List<String> markedSegment =  getMarkedSegment(breakpointsFullName.get(i), mark);			
			rawStringList.addAll(markedSegment);
			markList.add(mark);
			// Record Gain
			recordGain(markedSegment, mark);
		}
		
		segments = getSegments(rawStringList);
		parsingOrderMarksMap.put(currentParsingPlace, markList);
    	containingR = true;    	
    	
    	return true;
    }
    
    protected List<String> getMarkedSegment(List<String> segment, String mark) {
    	return segment.stream().map(s -> s + mark).collect(Collectors.toCollection(ArrayList::new));
    }
    
    protected boolean isValidHsr(List<List<String>> breakpointsFullName, String derChr, List<Integer> correctParsingOrder, int currentParsingPlace) {
    	// We have either within-chromosome-band hsr or between-chromosome-band-interface hsr
    	if (breakpointsFullName.size() == 1) {
    		String chrBreakpoint = breakpointsFullName.get(0).get(0);
    		
    		String chr = getChr(chrBreakpoint);
    		String mark;
    		if (chr.equals(derChr)) {
    			mark = "M0";
    		} else {
    			// Check whether the current rearrangement is connecting with the previous one 
    			if (currentParsingPlace == 0) {
    				return false;
    			}
    			Event previousEvent = subevents.get(correctParsingOrder.get(currentParsingPlace - 1));
    			int chrIndex = previousEvent.getChrList().indexOf(chr);
    			if (chrIndex == -1) {
    				return false;
    			}			
    			mark = parsingOrderMarksMap.get(currentParsingPlace - 1).get(chrIndex);
    			if (mark == null || !mark.startsWith("M")) {
    				return false;
    			}
    		}
    		// First we cannot have multiple segments inserted at the same place. c.f. My email correspondence with Dr. Yang Cao on 11/7/2017
    		// For example: 46,XX,der(1)hsr(1)(p22)hsr(1)(p22)
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
    
    // Get the RHS segment # of the interface
    protected int getHsrSegmentNum(List<String> hsrInterface) {
    	List<Integer> segmentNumList = new ArrayList<>();
    	for (int i = 1; i < segments.size(); i++) {
    		if (segments.get(i).size() == 2 && segments.get(i - 1).size() == 2) { 
    			if (areEqualSets(getTranslatedSegmentII(Arrays.asList(segments.get(i - 1).get(1), segments.get(i).get(0))), hsrInterface)) {
    				segmentNumList.add(i);
    			}
    		}
    		if (segmentNumList.size() > 1) {
    			return -1;
    		}
    	}
    	return segmentNumList.size() == 1 ? segmentNumList.get(0) : -1;
    }
    
    
	
	private static final String[] CHR1P = {"1p10", "1p11.1", "1p11.2", "1p12", "1p13.1", "1p13.2", "1p13.3", 
								           "1p21.1", "1p21.2", "1p21.3", "1p22.1", "1p22.2", "1p22.3", 
								           "1p31.1", "1p31.2", "1p31.3", "1p32.1", "1p32.2", "1p32.3", "1p33", 
								           "1p34.1", "1p34.2", "1p34.3", "1p35.1", "1p35.2", "1p35.3", 
								           "1p36.11", "1p36.12", "1p36.13", "1p36.21", "1p36.22", "1p36.23", "1p36.31", "1p36.32", "1p36.33"};
	private static final String[] CHR1Q = {"1q10", "1q11", "1q12", "1q21.1", "1q21.2", "1q21.3", "1q22", "1q23.1", "1q23.2", "1q23.3", 
								           "1q24.1", "1q24.2", "1q24.3", "1q25.1", "1q25.2", "1q25.3", 
								           "1q31.1", "1q31.2", "1q31.3", "1q32.11", "1q32.12", "1q32.13", "1q32.2", "1q32.3",
								           "1q41", "1q42.11", "1q42.12", "1q42.13", "1q42.2", "1q42.3", "1q43", "1q44"};
	private static final String[] CHR2P = {"2p10", "2p11.1", "2p11.2", "2p12", "2p13.1", "2p13.2", "2p13.3", "2p14", "2p15", 
								           "2p16.1", "2p16.2", "2p16.3", "2p21.1", "2p21.2", "2p21.3", "2p22.1", "2p22.2", "2p22.3", 
								           "2p23.1", "2p23.2", "2p23.3", "2p24.1", "2p24.2", "2p24.3", "2p25.1", "2p25.2", "2p25.3"};
	private static final String[] CHR2Q = {"2q10", "2q11.1", "2q11.2", "2q12.1", "2q12.2", "2q12.3", "2q13", 
								           "2q14.1", "2q14.2", "2q14.3", "2q21.1", "2q21.2", "2q21.3", 
								           "2q22.1", "2q22.2", "2q22.3", "2q23.1", "2q23.2", "2q23.3", "2q24.1", "2q24.2", "2q24.3", 
								           "2q31.1", "2q31.2", "2q31.3", "2q32.1", "2q32.2", "2q32.3", "2q33.1", "2q33.2", "2q33.3", 
								           "2q34", "2q35", "2q36.1", "2q36.2", "2q36.3", "2q37.1", "2q37.2", "2q37.3"};
	private static final String[] CHR3P = {"3p10", "3p11.1", "3p11.2", "3p12.1", "3p12.2", "3p12.3", "3p13", "3p14.1", "3p14.2", "3p14.3", 
								           "3p21.1", "3p21.2", "3p21.31", "3p21.32", "3p21.33", "3p22.1", "3p22.2", "3p22.3", "3p23", 
								           "3p24.1", "3p24.2", "3p24.3", "3p25.1", "3p25.2", "3p25.3", "3p26.1", "3p26.2", "3p26.3"};
	private static final String[] CHR3Q = {"3q10", "3q11.1", "3q11.2", "3q12.1", "3q12.2", "3q12.3", "3q13.11", "3q13.12", "3q13.13",
								           "3q13.2", "3q13.31", "3q13.32", "3q13.33", "3q21.1", "3q21.2", "3q21.3", "3q22.1", "3q22.2", "3q22.3", 
								           "3q23", "3q24", "3q25.1", "3q25.2", "3q25.31", "3q25.32", "3q25.33", "3q26.1", "3q26.2", 
								           "3q26.31", "3q26.32", "3q26.33", "3q27.1", "3q27.2", "3q27.3", "3q28", "3q29"};
	private static final String[] CHR4P = {"4p10", "4p11", "4p12", "4p13", "4p14", "4p15.1", "4p15.2", 
								           "4p15.31", "4p15.32", "4p15.33", "4p16.1", "4p16.2", "4p16.3"};
	private static final String[] CHR4Q = {"4q10", "4q11", "4q12", "4q13.1", "4q13.2", "4q13.3", 
								           "4q21.1", "4q21.21", "4q21.22", "4q21.23", "4q21.3", "4q22.1", "4q22.2", "4q22.3", 
								           "4q23", "4q24", "4q25", "4q26", "4q27", "4q28.1", "4q28.2", "4q28.3", 
								           "4q31.1", "4q31.21", "4q31.22", "4q31.23", "4q31.3", "4q32.1", "4q32.2", "4q32.3", 
								           "4q33", "4q34.1", "4q34.2", "4q34.3", "4q35.1", "4q35.2"};
	private static final String[] CHR5P = {"5p10", "5p11", "5p12", "5p13.1", "5p13.2", "5p13.3", 
								           "5p14.1", "5p14.2", "5p14.3", "5p15.1", "5p15.2", "5p15.31", "5p15.32", "5p15.33"};
	private static final String[] CHR5Q = {"5q10", "5q11.1", "5q11.2", "5q12.1", "5q12.2", "5q12.3", "5q13.1", 
								           "5q13.21", "5q13.22", "5q13.23", "5q13.3", "5q14.1", "5q14.2", "5q14.3", "5q15", 
								           "5q21.1", "5q21.2", "5q21.3", "5q22.1", "5q22.2", "5q22.3", "5q23.1", "5q23.2", "5q23.3",
								           "5q31.1", "5q31.2", "5q31.3", "5q32", "5q33.1", "5q33.2", "5q33.3", "5q34", "5q35.1", "5q35.2", "5q35.3"};
	private static final String[] CHR6P = {"6p10", "6p11.1", "6p11.2", "6p12.1", "6p12.2", "6p12.3", 
								           "6p21.1", "6p21.2", "6p21.31", "6p21.32", "6p21.33", "6p22.1", "6p22.2", 
								           "6p22.31", "6p22.32", "6p22.33", "6p23", "6p24", "6p25.1", "6p25.2", "6p25.3"};
	private static final String[] CHR6Q = {"6q10", "6q11.1", "6q11.2", "6q12", "6q13", "6q14.1", "6q14.2", "6q14.3", "6q15", "6q16.1", "6q16.2", "6q16.3", 
								           "6q21.1", "6q21.2", "6q21.3", "6q22.1", "6q22.2", "6q22.31", "6q22.32", "6q22.33", 
								           "6q23.1", "6q23.2", "6q23.3", "6q24.1", "6q24.2", "6q24.3", "6q25.1", "6q25.2", "6q25.3", "6q26", "6q27"};
	private static final String[] CHR7P = {"7p10", "7p11.1", "7p11.2", "7p12.1", "7p12.2", "7p12.3", "7p13", "7p14.1", "7p14.2", "7p14.3", 
								           "7p15.1", "7p15.2", "7p15.3", "7p21.1", "7p21.2", "7p21.3", "7p22.1", "7p22.2", "7p22.3"};
	private static final String[] CHR7Q = {"7q10", "7q11.1", "7q11.21", "7q11.22", "7q11.23", "7q21.11", "7q21.12", "7q21.13", 
								           "7q21.2", "7q21.3", "7q22.1", "7q22.2", "7q22.3", "7q31.1", "7q31.2", "7q31.31", "7q31.32", "7q31.33", 
								           "7q32.1", "7q32.2", "7q32.3", "7q33", "7q34", "7q35", "7q36.1", "7q36.2", "7q36.3"};
	private static final String[] CHR8P = {"8p10", "8p11.1", "8p11.21", "8p11.22", "8p11.23", "8p12", 
								           "8p21.1", "8p21.2", "8p21.3", "8p22", "8p23.1", "8p23.2", "8p23.3"};
	private static final String[] CHR8Q = {"8q10", "8q11.1", "8q11.21", "8q11.22", "8q11.23", "8q12.1", "8q12.2", "8q12.3",
								           "8q13.1", "8q13.2", "8q13.3", "8q21.11", "8q21.12", "8q21.13", "8q21.2", "8q21.3", 
								           "8q22.1", "8q22.2", "8q22.3", "8q23.1", "8q23.2", "8q23.3", "8q24.11", "8q24.12", "8q24.13", 
								           "8q24.21", "8q24.22", "8q24.23", "8q24.3"};
	private static final String[] CHR9P = {"9p10", "9p11.1", "9p11.2", "9p12", "9p13.1", "9p13.2", "9p13.3", 
								           "9p21.1", "9p21.2", "9p21.3", "9p22.1", "9p22.2", "9p22.3", "9p23", "9p24.1", "9p24.2", "9p24.3"};
	private static final String[] CHR9Q = {"9q10", "9q11", "9q12", "9q13", "9q21.11", "9q21.12", "9q21.13", "9q21.2", "9q21.31", "9q21.32", "9q21.33", 
								           "9q22.1", "9q22.2", "9q22.31", "9q22.32", "9q22.33", "9q31.1", "9q31.2", "9q31.3", 
								           "9q32", "9q33.1", "9q33.2", "9q33.3", "9q34.1", "9q34.2", "9q34.3"};
	private static final String[] CHR10P= {"10p10", "10p11.1", "10p11.21", "10p11.22", "10p11.23", "10p12.1", "10p12.2", 
								           "10p12.31", "10p12.32", "10p12.33", "10p13", "10p14", "10p15.1", "10p15.2", "10p15.3"};
	private static final String[] CHR10Q= {"10q10", "10q11.1", "10q11.21", "10q11.22", "10q11.23", "10q21.1", "10q21.2", "10q21.3", 
								           "10q22.1", "10q22.2", "10q22.3", "10q23.1", "10q23.2", "10q23.31", "10q23.32", "10q23.33", 
								           "10q24.1", "10q24.2", "10q24.31", "10q24.32", "10q24.33", "10q25.1", "10q25.2", "10q25.3", 
								           "10q26.11", "10q26.12", "10q26.13", "10q26.2", "10q26.3"};
	private static final String[] CHR11P= {"11p10", "11p11.11", "11p11.12", "11p11.2", "11p12", "11p13", "11p14.1", "11p14.2", "11p14.3", 
								           "11p15.1", "11p15.2", "11p15.3", "11p15.4", "11p15.5"};
	private static final String[] CHR11Q= {"11q10", "11q11", "11q12.1", "11q12.2", "11q12.3", "11q13.1", "11q13.2", "11q13.3", "11q13.4", "11q13.5", 
								           "11q14.1", "11q14.2", "11q14.3", "11q21", "11q22.1", "11q22.2", "11q22.3", "11q23.1", "11q23.2", "11q23.3", 
								           "11q24.1", "11q24.2", "11q24.3", "11q25"};
	private static final String[] CHR12P= {"12p10", "12p11.1", "12p11.21", "12p11.22", "12p11.23", "12p12.1", "12p12.2", "12p12.3", 
								           "12p13.1", "12p13.2", "12p13.31", "12p13.32", "12p13.33"};
	private static final String[] CHR12Q= {"12q10", "12q11", "12q12", "12q13.11", "12q13.12", "12q13.13", "12q13.2", "12q13.3", 
								           "12q14.1", "12q14.2", "12q14.3", "12q15", 
								           "12q21.1", "12q21.2", "12q21.31", "12q21.32", "12q21.33", "12q22", "12q23.1", "12q23.2", "12q23.3", 
								           "12q24.11", "12q24.12", "12q24.13", "12q24.21", "12q24.22", "12q24.23", "12q24.31", "12q24.32", "12q24.33"};
	private static final String[] CHR13P= {"13p10", "13p11.1", "13p11.2", "13p12", "13p13"};
	private static final String[] CHR13Q= {"13q10", "13q11", "13q12.11", "13q12.12", "13q12.13", "13q12.2", "13q12.3", 
								           "13q13.1", "13q13.2", "13q13.3", "13q14.11", "13q14.12", "13q14.13", "13q14.2", "13q14.3", 
								           "13q21.1", "13q21.2", "13q21.31", "13q21.32", "13q21.33", "13q22.1", "13q22.2", "13q22.3", 
								           "13q31.1", "13q31.2", "13q31.3", "13q32.1", "13q32.2", "13q32.3", "13q33.1", "13q33.2", "13q33.3", "13q34"};
	private static final String[] CHR14P= {"14p10", "14p11.1", "14p11.2", "14p12", "14p13"};
	private static final String[] CHR14Q= {"14q10", "14q11.1", "14q11.2", "14q12", "14q13.1", "14q13.2", "14q13.3", 
								           "14q21.1", "14q21.2", "14q21.3", "14q22.1", "14q22.2", "14q22.3", "14q23.1", "14q23.2", "14q23.3", 
								           "14q24.1", "14q24.2", "14q24.3", "14q31.1", "14q31.2", "14q31.3", 
								           "14q32.11", "14q32.12", "14q32.13", "14q32.2", "14q32.31", "14q32.32", "14q32.33"};
	private static final String[] CHR15P= {"15p10", "15p11.1", "15p11.2", "15p12", "15p13"};
	private static final String[] CHR15Q= {"15q10", "15q11.1", "15q11.2", "15q12", "15q13.1", "15q13.2", "15q13.3", "15q14", 
								           "15q15.1", "15q15.2", "15q15.3", "15q21.1", "15q21.2", "15q21.3", "15q22.1", "15q22.2", 
								           "15q22.31", "15q22.32", "15q22.33", "15q23", "15q24.1", "15q24.2", "15q24.3", 
								           "15q25.1", "15q25.2", "15q25.3", "15q26.1", "15q26.2", "15q26.3"};
	private static final String[] CHR16P= {"16p10", "16p11.1", "16p11.2", "16p12.1", "16p12.2", "16p12.3", 
								           "16p13.11", "16p13.12", "16p13.13", "16p13.2", "16p13.3"};
	private static final String[] CHR16Q= {"16q10", "16q11.1", "16q11.2", "16q12.1", "16q12.2", "16q13", 
								           "16q21", "16q22.1", "16q22.2", "16q22.3", "16q23.1", "16q23.2", "16q23.3", "16q24.1", "16q24.2", "16q24.3"};
	private static final String[] CHR17P= {"17p10", "17p11.1", "17p11.2", "17p12", "17p13.1", "17p13.2", "17p13.3"};
	private static final String[] CHR17Q= {"17q10", "17q11.1", "17q11.2", "17q12", "17q21.1", "17q21.2", "17q21.31", "17q21.32", "17q21.33", 
								           "17q22", "17q23.1", "17q23.2", "17q23.3", "17q24.1", "17q24.2", "17q24.3", "17q25.1", "17q25.2", "17q25.3"};
	private static final String[] CHR18P= {"18p10", "18p11.1", "18p11.21", "18p11.22", "18p11.23", "18p11.31", "18p11.32"};
	private static final String[] CHR18Q= {"18q10", "18q11.1", "18q11.2", "18q12.1", "18q12.2", "18q12.3", "18q21.1", "18q21.2", 
								           "18q21.31", "18q21.32", "18q21.33", "18q22.1", "18q22.2", "18q22.3", "18q23"};
	private static final String[] CHR19P= {"19p10", "19p11", "19p12", "19p13.11", "19p13.12", "19p13.13", "19p13.2", "19p13.3"};
	private static final String[] CHR19Q= {"19q10", "19q11", "19q12", "19q13.11", "19q13.12", "19q13.13", "19q13.2", 
								           "19q13.31", "19q13.32", "19q13.33", "19q13.41", "19q13.42", "19q13.43"};
	private static final String[] CHR20P= {"20p10", "20p11.1", "20p11.21", "20p11.22", "20p11.23", "20p12.1", "20p12.2", "20p12.3", "20p13"};
	private static final String[] CHR20Q= {"20q10", "20q11.1", "20q11.21", "20q11.22", "20q11.23", "20q12", 
								           "20q13.11", "20q13.12", "20q13.13", "20q13.2", "20q13.31", "20q13.32", "20q13.33"};
	private static final String[] CHR21P= {"21p10", "21p11.1", "21p11.2", "21p12", "21p13"};
	private static final String[] CHR21Q= {"21q10", "21q11.1", "21q11.2", "21q21.1", "21q21.2", "21q21.3", 
								           "21q22.11", "21q22.12", "21q22.13", "21q22.2", "21q22.3"};
	private static final String[] CHR22P= {"22p10", "22p11.1", "22p11.2", "22p12", "22p13"};
	private static final String[] CHR22Q= {"22q10", "22q11.1", "22q11.21", "22q11.22", "22q11.23", "22q12.1", "22q12.2", "22q12.3", 
								           "22q13.1", "22q13.2", "22q13.31", "22q13.32", "22q13.33"};
	private static final String[] CHRXP = {"Xp10", "Xp11.1", "Xp11.21", "Xp11.22", "Xp11.23", "Xp11.3", "Xp11.4", 
								           "Xp21.1", "Xp21.2", "Xp21.3", "Xp22.11", "Xp22.12", "Xp22.13", "Xp22.2", 
								           "Xp22.31", "Xp22.32", "Xp22.33"};
	private static final String[] CHRXQ = {"Xq10", "Xq11.1", "Xq11.2", "Xq12", "Xq13.1", "Xq13.2", "Xq13.3", 
								           "Xq21.1", "Xq21.2", "Xq21.31", "Xq21.32", "Xq21.33", "Xq22.1", "Xq22.2", "Xq22.3", "Xq23", 
								           "Xq24", "Xq25", "Xq26.1", "Xq26.2", "Xq26.3", "Xq27.1", "Xq27.2", "Xq27.3", "Xq28"};
	private static final String[] CHRYP = {"Yp10", "Yp11.1", "Yp11.2", "Yp11.31", "Yp11.32"};
	private static final String[] CHRYQ = {"Yq10", "Yq11.1", "Yq11.21", "Yq11.221", "Yq11.222", "Yq11.223", "Yq11.23", "Yq12"};
	
	private static final String CHR1PTER = "1p36.33";
	private static final String CHR1QTER = "1q44";
	private static final String CHR2PTER = "2p25.3";
	private static final String CHR2QTER = "2q37.3";
	private static final String CHR3PTER = "3p26.3";
	private static final String CHR3QTER = "3q29";
	private static final String CHR4PTER = "4p16.3";
	private static final String CHR4QTER = "4q35.2";
	private static final String CHR5PTER = "5p15.33";
	private static final String CHR5QTER = "5q35.3";
	private static final String CHR6PTER = "6p25.3";
	private static final String CHR6QTER = "6q27";
	private static final String CHR7PTER = "7p22.3";
	private static final String CHR7QTER = "7q36.3";
	private static final String CHR8PTER = "8p23.3";
	private static final String CHR8QTER = "8q24.3";
	private static final String CHR9PTER = "9p24.3";
	private static final String CHR9QTER = "9q34.3";
	private static final String CHR10PTER = "10p15.3";
	private static final String CHR10QTER = "10q26.3";
	private static final String CHR11PTER = "11p15.5";
	private static final String CHR11QTER = "11q25";
	private static final String CHR12PTER = "12p13.33";
	private static final String CHR12QTER = "12q24.33";
	private static final String CHR13PTER = "13p13";
	private static final String CHR13QTER = "13q34";
	private static final String CHR14PTER = "14p13";
	private static final String CHR14QTER = "14q32.33";
	private static final String CHR15PTER = "15p13";
	private static final String CHR15QTER = "15q26.3";
	private static final String CHR16PTER = "16p13.3";
	private static final String CHR16QTER = "16q24.3";
	private static final String CHR17PTER = "17p13.3";
	private static final String CHR17QTER = "17q25.3";
	private static final String CHR18PTER = "18p11.32";
	private static final String CHR18QTER = "18q23";
	private static final String CHR19PTER = "19p13.3";
	private static final String CHR19QTER = "19q13.43";
	private static final String CHR20PTER = "20p13";
	private static final String CHR20QTER = "20q13.33";
	private static final String CHR21PTER = "21p13";
	private static final String CHR21QTER = "21q22.3";
	private static final String CHR22PTER = "22p13";
	private static final String CHR22QTER = "22q13.33";
	private static final String CHRXPTER = "Xp22.33";
	private static final String CHRXQTER = "Xq28";
	private static final String CHRYPTER = "Yp11.32";
	private static final String CHRYQTER = "Yq12";
	
	protected Map<Integer, String> indexToChrMap = new HashMap<>();
	protected Map<String, Integer> chrToIndexMap = new HashMap<>();
	
	protected String[][] chrArmArrays = {CHR1P, CHR1Q, CHR2P, CHR2Q, CHR3P, CHR3Q, CHR4P, CHR4Q, CHR5P, CHR5Q, 
									     CHR6P, CHR6Q, CHR7P, CHR7Q, CHR8P, CHR8Q, CHR9P, CHR9Q, CHR10P, CHR10Q, 
									     CHR11P, CHR11Q, CHR12P, CHR12Q, CHR13P, CHR13Q, CHR14P, CHR14Q, CHR15P, CHR15Q, 
									     CHR16P, CHR16Q, CHR17P, CHR17Q, CHR18P, CHR18Q, CHR19P, CHR19Q, CHR20P, CHR20Q, 
									     CHR21P, CHR21Q, CHR22P, CHR22Q, CHRXP, CHRXQ, CHRYP, CHRYQ};
	
	public static String getChrTer(String chrArm) {
		String ter = "";
		switch(chrArm) {
			case "1p": ter = CHR1PTER; break;
			case "1q": ter = CHR1QTER; break;
			case "2p": ter = CHR2PTER; break;
			case "2q": ter = CHR2QTER; break;
			case "3p": ter = CHR3PTER; break;
			case "3q": ter = CHR3QTER; break;
			case "4p": ter = CHR4PTER; break;
			case "4q": ter = CHR4QTER; break;
			case "5p": ter = CHR5PTER; break;
			case "5q": ter = CHR5QTER; break;
			case "6p": ter = CHR6PTER; break;
			case "6q": ter = CHR6QTER; break;
			case "7p": ter = CHR7PTER; break;
			case "7q": ter = CHR7QTER; break;
			case "8p": ter = CHR8PTER; break;
			case "8q": ter = CHR8QTER; break;
			case "9p": ter = CHR9PTER; break;
			case "9q": ter = CHR9QTER; break;
			case "10p":ter = CHR10PTER;break;
			case "10q":ter = CHR10QTER;break;
			case "11p":ter = CHR11PTER;break;
			case "11q":ter = CHR11QTER;break;
			case "12p":ter = CHR12PTER;break;
			case "12q":ter = CHR12QTER;break;
			case "13p":ter = CHR13PTER;break;
			case "13q":ter = CHR13QTER;break;
			case "14p":ter = CHR14PTER;break;
			case "14q":ter = CHR14QTER;break;
			case "15p":ter = CHR15PTER;break;
			case "15q":ter = CHR15QTER;break;
			case "16p":ter = CHR16PTER;break;
			case "16q":ter = CHR16QTER;break;
			case "17p":ter = CHR17PTER;break;
			case "17q":ter = CHR17QTER;break;
			case "18p":ter = CHR18PTER;break;
			case "18q":ter = CHR18QTER;break;
			case "19p":ter = CHR19PTER;break;
			case "19q":ter = CHR19QTER;break;
			case "20p":ter = CHR20PTER;break;
			case "20q":ter = CHR20QTER;break;
			case "21p":ter = CHR21PTER;break;
			case "21q":ter = CHR21QTER;break;
			case "22p":ter = CHR22PTER;break;
			case "22q":ter = CHR22QTER;break;
			case "Xp": ter = CHRXPTER; break;
			case "Xq": ter = CHRXQTER; break;
			case "Yp": ter = CHRYPTER; break;
			case "Yq": ter = CHRYQTER; break;
		}
		return ter;
	}
	
	public String doChrTerReflection(String chr, String arm) {
		DerivativeValidation derivativeValidation = new DerivativeValidation();
		try {
			Field privateField = derivativeValidation.getClass().getDeclaredField("CHR" + chr + arm + "TER");
			privateField.setAccessible(true);
			String chrTer = (String)privateField.get(derivativeValidation);
			return chrTer;
		} catch (Exception ex) {
			return null;
		}
    }
	
	public List<String> getAllKeys(String particalKey) {
		Set<String> allKeys = chrToIndexMap.keySet();
		List<String> targetedKeys = new ArrayList<>();
		for (String s: allKeys) {
			if (s.startsWith(particalKey)) {
				targetedKeys.add(s);
			}
		}
		Collections.sort(targetedKeys);
		return targetedKeys;
	}
	
	public String getChr(String chrBreakpoint) {
		String chr;
		if (chrBreakpoint.contains("p")) {
			chr = chrBreakpoint.substring(0, chrBreakpoint.indexOf('p'));				
		} else {
			chr = chrBreakpoint.substring(0, chrBreakpoint.indexOf('q'));							
		}
		return chr;
	}
	
	public String getChrArm(String chrBreakpoint) {
		String chrArm;
		if (chrBreakpoint.contains("p")) {
			chrArm = chrBreakpoint.substring(0, chrBreakpoint.indexOf('p') + 1);				
		} else {
			chrArm = chrBreakpoint.substring(0, chrBreakpoint.indexOf('q') + 1);							
		}
		return chrArm;
	}
	
	public String getBreakpoint(String chrBreakpoint) {
		String breakpoint;
		if (chrBreakpoint.contains("p")) {
			breakpoint = chrBreakpoint.substring(chrBreakpoint.indexOf('p'));				
		} else {
			breakpoint = chrBreakpoint.substring(chrBreakpoint.indexOf('q'));							
		}
		return breakpoint;
	}

	public String getOppositeChrArm(String chrArm) {
		String oppositeChrArm;
		if (chrArm.contains("p")) {
			oppositeChrArm = chrArm.substring(0, chrArm.indexOf('p')) + "q";
		} else {
			oppositeChrArm = chrArm.substring(0, chrArm.indexOf('q')) + "p";
		}
		return oppositeChrArm;
	}
	
    private int getBeginIndex(String chrBreakpoint) {
    	int beginIndex;
    	if (chrToIndexMap.get(chrBreakpoint) == null) {					
    		beginIndex = chrToIndexMap.get(getAllKeys(chrBreakpoint).get(0));							
		} else {
			beginIndex = chrToIndexMap.get(chrBreakpoint);					
		}
    	return beginIndex;
    }
    
    private int getEndIndex(String chrBreakpoint) {
    	int endIndex;
    	if (chrToIndexMap.get(chrBreakpoint) == null) {					
    		endIndex = chrToIndexMap.get(getLastElement(getAllKeys(chrBreakpoint)));							
		} else {
			endIndex = chrToIndexMap.get(chrBreakpoint);					
		}
    	return endIndex;
    }
    
	public String getLastElement(List<String> list) {
		return list.get(list.size() - 1);
	}
	
    public String getMin(List<String> twoStrings) {
    	if (twoStrings.get(0).compareTo(twoStrings.get(1)) <= 0) {
    		return twoStrings.get(0);
    	} else {
    		return twoStrings.get(1);
    	}
    }
    
    public String getMax(List<String> twoStrings) {
    	if (twoStrings.get(0).compareTo(twoStrings.get(1)) <= 0) {
    		return twoStrings.get(1);
    	} else {
    		return twoStrings.get(0);
    	}
    }
    
    protected int getFirstPrimaryTEventIndex(List<Integer> eventRemainingIndexList, List<String> subeventNatureList, List<List<String>> subeventChrList, List<String> derChrList) {
    	int firstPrimaryTEventIndex = -1;
    	for (int i = 0; i < eventRemainingIndexList.size(); i++) {    
    		int eventIndex = eventRemainingIndexList.get(i);
    		List<String> commonDerList = new ArrayList<>(subeventChrList.get(eventIndex));
    		boolean hasCommomDer = commonDerList.removeAll(derChrList); // true if commonDerList changed as a result of the call of removeAll(derChrList)    		
    		if (subeventNatureList.get(eventIndex).equals("t") && hasCommomDer) {
    			firstPrimaryTEventIndex = eventIndex;
    			break;
    		}    		
    	}
    	return firstPrimaryTEventIndex;
    }
    
    private List<List<String>> getUnmarkedSegments(List<List<String>> derChrGainedSegments) {
    	List<List<String>> demarked_derChrGainedSegments = new ArrayList<>(derChrGainedSegments);
    	demarked_derChrGainedSegments.forEach(list->list.replaceAll(s -> s.replaceAll("M[\\d]+", "")));
    	return demarked_derChrGainedSegments;
//    	return derChrGainedSegments.stream()
//					        	   .map(t->t.stream()
//					        				.map(s->s.replaceAll("M[\\d]+", ""))
//					        				.collect(Collectors.toList()))
//					        	   .collect(Collectors.toList());
    }    
    
	public List<List<String>> getDistinctSegments(List<List<String>> derChrGainedSegments) {
	    List<List<String>> uniqueGainedSegments = new ArrayList<>();
	    for (List<String> segment: getUnmarkedSegments(derChrGainedSegments)) {
	        if (!containsSegment(uniqueGainedSegments, segment)) {
	            uniqueGainedSegments.add(segment);             
	        }
	    }              
	    return uniqueGainedSegments;
	}
	
	// I purposefully call this method areEqualSets, other than areEqualLists, because I don't care the order of elements inside the list
	protected boolean areEqualSets(List<String> listA, List<String> listB) {
		return listA.containsAll(listB) && listB.containsAll(listA);
	}
	
	protected boolean containsSegment(List<List<String>> segmentsList, List<String> segment) {
        if (segmentsList.size() == 0) {
	        return false;
	    } 
        for (List<String> seg: segmentsList) {
	        if (areEqualSets(seg, segment)) {
	            return true;
	        }
        }
	    return false;
	} 
    
    private boolean isSubsetSegment(List<String> segmentA, List<String> segmentB) {
    	return getAllSegmentBands(segmentB).containsAll(getAllSegmentBands(segmentA));
	}
    

    public String[] doReflection(String chr, String arm) {
        DerivativeValidation derivativeValidation = new DerivativeValidation();
		try {
            Field privateArrayField = derivativeValidation.getClass().getDeclaredField("CHR" + chr + arm);
            privateArrayField.setAccessible(true);
            String[] chrBreakpoints = (String[])privateArrayField.get(derivativeValidation);
            return chrBreakpoints;
		} catch (Exception ex) {
            return null;
		}
    }
    
    protected ArrayList<Integer> initializeChrLossGain(String chr) {                         
        return new ArrayList<Integer>(Collections.nCopies(getChrBandsList(chr).size(), 0)); 
	}
	
    protected List<String> getChrBandsList(String chr) {
		List<String> chrBandsList = new ArrayList<>();
		chrBandsList.addAll(Arrays.asList(doReflection(chr, "P")));
		chrBandsList.addAll(Arrays.asList(doReflection(chr, "Q")));
		return chrBandsList;
	}
	
    protected List<String> updateDerChrGainedSegments(List<List<String>> derChrGainedSegments, List<String> segment) {
        if (derChrGainedSegments.size() == 0) {
	        return null;
	    }         
        int i = 0;
        List<List<String>> demarked_derChrGainedSegments = getUnmarkedSegments(derChrGainedSegments);
        for (List<String> gainedSegment: demarked_derChrGainedSegments) {
            if (i > 0 && areEqualSets(gainedSegment, demarked_derChrGainedSegments.get(i - 1))) {
	            continue;
            } 
	        if (areEqualSets(gainedSegment, segment) || isSubsetSegment(gainedSegment, segment)) {
	            derChrGainedSegments.remove(i);
	            return gainedSegment;
	        }
            i++;
        }
	    return null;
	}
    
    protected String getMark(List<String> segment) {
    	return segment.get(0).contains("M") ? segment.get(0).substring(segment.get(0).indexOf('M')) : segment.get(0).substring(segment.get(0).indexOf('H'));
    }
    
    protected void recordLoss(List<List<String>> deletedSegments, List<List<String>> derChrGainedSegments) {
    	List<String> derChrBandsList = getChrBandsList(derChr);
    	List<Integer> derChrLoss = markSpecificLossGain.get("M0");
    	for (List<String> segment: deletedSegments) {
    		// It may contain hsr segments.
    		if (segment.size() == 1) {
    			continue;
    		}
    		
    		if (segment.get(0).endsWith("M0")) {
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
    
    protected void recordGain(List<String> segment, String mark) {
    	List<Integer> chrGain = markSpecificLossGain.get(mark);
    	String chr = markToChrMap.get(mark);
    	List<String> chrBandsList = getChrBandsList(chr);
    	List<String> segmentBands = getAllSegmentBands(segment);
    	for (String segmentBand: segmentBands) {
			int index = chrBandsList.indexOf(segmentBand);
			chrGain.set(index, chrGain.get(index) + 1);
		}
    }
	
    private void recordDuplicate(List<List<String>> duplicatedSegments, List<List<String>> derChrGainedSegments) {
    	for (List<String> segment: duplicatedSegments) {
    		// It may contain hsr segments.
    		if (segment.size() == 1) {
    			continue;
    		}
    		
    		if (segment.get(0).endsWith("M0")) {
    			derChrGainedSegments.add(segment);
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
    
    protected int[] getChrsOffset(String[][] chrArmArrays) {
    	int[] chBandsLength = new int[chrArmArrays.length / 2];
    	for (int i = 0; i < chrArmArrays.length / 2; i++) {
    		chBandsLength[i] = chrArmArrays[2*i].length + chrArmArrays[2*i + 1].length;    		
    	}
    	int[] chrsOffset = new int[chBandsLength.length];
    	chrsOffset[0]= 0;
        for (int i = 1; i < chrsOffset.length; i++) {
        	chrsOffset[i] = chrsOffset[i-1] + chBandsLength[i-1];    		
    	}
    	return chrsOffset;
    }
    
    protected int getChrBlockIndex(String chr) {
    	switch (chr) {
	    	case "X": return 22;
	    	case "Y": return 23;
	    	default: return Integer.parseInt(chr) - 1;
    	}
    }
    
    protected ArrayList<Integer> karyotypeLossOutcome;
    protected ArrayList<Integer> karyotypeGainOutcome;
    protected ArrayList<Integer> karyotypeFusionOutcome;

    
    protected List<Integer> recordDerchrGainedSegemnts(List<List<String>> derChrGainedSegments, String derChr) {
    	List<String> derChrBandsList = getChrBandsList(derChr);
    	List<Integer> derChrGain = initializeChrLossGain(derChr);
    	for (List<String> derChrGainedSegment: derChrGainedSegments) {
    		for (String segmentBand: getAllSegmentBands(derChrGainedSegment)) {
    			int index = derChrBandsList.indexOf(segmentBand);
    			derChrGain.set(index, derChrGain.get(index) + 1);
    		}
    	}
    	return derChrGain;
    }
    
    public List<List<Integer>> getDerKaryotypeLGF() {
    	List<List<Integer>> derKaryotypeLGF = new ArrayList<>();
    	int[] chrsOffset = getChrsOffset(chrArmArrays);
    	
    	List<Integer> derChrLoss = markSpecificLossGain.get("M0");
    	int derchrOffset = chrsOffset[getChrBlockIndex(derChr)];
    	for (int i = 0; i < derChrLoss.size(); i++) {
    		if (derChrLoss.get(i) == 1) {
    			karyotypeLossOutcome.set(derchrOffset + i, 1);
    		}
    	}
    	
    	List<Integer> derChrGain = recordDerchrGainedSegemnts(derChrGainedSegments, derChr);
    	for (int i = 0; i < derChrGain.size(); i++) {
    		if (derChrGain.get(i) > 0) {
    			karyotypeGainOutcome.set(derchrOffset + i, derChrGain.get(i));
    		}
    	}
    	
    	for (String mark: commonMarkSet) {
    		if (mark.equals("M0")) {
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
    	
    	// Cancel out
//    	for (int i = 0; i < karyotypeLossOutcome.size(); i++) {
//    		if (karyotypeLossOutcome.get(i) == 1 && karyotypeGainOutcome.get(i) > 0) {
//    			karyotypeLossOutcome.set(i, 0);
//    			karyotypeGainOutcome.set(i, karyotypeGainOutcome.get(i) - 1);
//    		}
//    	}
    	
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
    	
    	sb.insert(0, "der(" + derChr + ")(");
    	sb.append(")");
    	
    	return sb.toString();
    }
    
	protected List<String> getTranslatedSegmentII(List<String> segment) {
		List<String> translatedSegment = new ArrayList<>();
		for (String endPoint: segment) {
			if (endPoint.contains("M")) {
				String chrBreakpoint = endPoint.substring(0, endPoint.indexOf("M"));
				translatedSegment.add(chrBreakpoint);
			} else if (endPoint.contains("H")) {
				String chrBreakpoint = endPoint.substring(0, endPoint.indexOf("H"));
				translatedSegment.add(chrBreakpoint);
			} else {
				translatedSegment.add(endPoint);
			}			
		}
		return translatedSegment;
	}
	
	protected List<String> getFusionPoints() {
		List<String> fusionPointsList = new ArrayList<>();
		if (subeventNatureList.contains("r")) {
			for (List<String> segment: segments) {
				for (String fusionPoint: getTranslatedSegment(segment)) {
					if (!fusionPoint.equals("hsr")) {
						fusionPointsList.add(fusionPoint);
					}
				}
			}
		} else if (segments.size() > 1) {
			if (segments.get(0).size() > 1) {
				fusionPointsList.add(getTranslatedSegment(segments.get(0)).get(1));
			}
			for (int i = 1; i < segments.size() - 1; i++) {
				if (segments.get(i).size() > 1) {
					List<String> translatedSegment = getTranslatedSegment(segments.get(i)); 
					fusionPointsList.add(translatedSegment.get(0));
					if (!translatedSegment.get(1).equals(translatedSegment.get(0))) {
						fusionPointsList.add(translatedSegment.get(1));
					}
				}
			} 
			if (segments.get(segments.size() - 1).size() > 1) {
				fusionPointsList.add(getTranslatedSegment(segments.get(segments.size() - 1)).get(0));
			}
			
		} // Note that for a non-ring one-segment derived chr, there is no fusion point.
		
		return fusionPointsList;
	}
    
	
}

