package validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import business.Clone;
import business.DerEvent;
import business.Event;
import business.ParseEvent;
import toolkit.ListComparator;
import toolkit.Permutation;

/**
 * @author Lin Zhang
 * Programmer / Software Development Engineer
 * Institute for Informatics
 * Washington University School of Medicine in St. Louis
 * 
 * Date: August 30, 2021 
 */
public class ValidationError {
	
	public static boolean isValidChr(String chr) {
		String[] chrArray = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "X", "Y"};
		List<String> chrList = Arrays.asList(chrArray);
		if (chrList.contains(chr)) return true;
		else return false;
	}
	
	public static boolean isValidChrList(List<String> chrList) {
		for (String chr: chrList) {
			if (!isValidChr(chr)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isValidTerminal(String chrBreakpoint) {
		String[] terminalArray = {"1p36", "1p36.3", "1p36.33", "1q44", "2p25", "2p25.3", "2q37", "2q37.3", "3p26", "3p26.3", "3q29", "4p16", "4p16.3", "4q35", "4q35.2", "5p15", "5p15.3", "5p15.33", "5q35", "5q35.3", "6p25", "6p25.3", "6q27", "7p22", "7p22.3", "7q36", "7q36.3", "8p23", "8p23.3", "8q24", "8q24.3", "9p24", "9p24.3", "9q34", "9q34.3", "10p15", "10p15.3", "10q26", "10q26.3", "11p15", "11p15.5", "11q25", "12p13", "12p13.3", "12p13.33", "12q24", "12q24.3", "12q24.33", "13p13", "13q34", "14p13", "14q32", "14q32.3", "14q32.33", "15p13", "15q26", "15q26.3", "16p13", "16p13.3", "16q24", "16q24.3", "17p13", "17p13.3", "17q25", "17q25.3", "18p11", "18p11.3", "18p11.32", "18q23", "19p13", "19p13.3", "19q13", "19q13.4", "19q13.43", "20p13", "20q13", "20q13.3", "20q13.33", "21p13", "21q22", "21q22.3", "22p13", "22q13", "22q13.3", "22q13.33", "Xp22", "Xp22.3", "Xp22.33", "Xq28", "Yp11", "Yp11.3", "Yp11.32", "Yq12"};
		List<String> terminalList = Arrays.asList(terminalArray);
		if (terminalList.contains(chrBreakpoint)) return true;
		else return false;
	}
	
	public static boolean isValidTerminalList(List<List<String>> breakpointsFullName) {
		for (List<String> chrBreakpoints: breakpointsFullName) {
			for (String chrBreakpoint: chrBreakpoints) {
				if (!isValidTerminal(chrBreakpoint)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean isValidBreakpointsWithFixedNumBreakpointsInEachChr(List<List<String>> breakpoints, int size) {
		for (List<String> pointList: breakpoints) {
			if (pointList.size() != size) {
				return false;
			}
		}
		return true;
	} 
	
	public static boolean isValidBreakpointsWithVariedNumBreakpointsInEachChr(List<List<String>> breakpoints, int endSize, int interiorSize) {
		if (breakpoints.get(0).size() != endSize) {
			return false;
		}
		if (breakpoints.get(breakpoints.size() - 1).size() != endSize) {
			return false;
		}
		for (int i = 1; i < breakpoints.size() - 1; i++) {
			if (breakpoints.get(i).size() != interiorSize) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isValidChrBreakpoint(String chrBreakpoint) {
		ParseEvent p = new ParseEvent();
		Map<String, Integer> chrToIndexMap = p.getChrToIndexMap();
		if (chrToIndexMap.get(chrBreakpoint) != null) {
			return true;
		} else {
			if (!chrBreakpoint.contains(".") && getBand(chrBreakpoint).length() < 2) {
				return false;
			}
			List<String> targetedKeys = p.getAllKeys(chrBreakpoint);
			if (targetedKeys.size() > 0) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public static boolean isValidChrBreakpointList(List<List<String>> breakpointsFullName) {
		for (List<String> chrBreakpoints: breakpointsFullName) {
			for (String chrBreakpoint: chrBreakpoints) {
				if (!isValidChrBreakpoint(chrBreakpoint)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean isValidCen(String chrBreakpoint) {
		ParseEvent p = new ParseEvent();
		Map<String, Integer> chrToIndexMap = p.getChrToIndexMap();
		if (chrToIndexMap.get(chrBreakpoint) != null && (chrBreakpoint.endsWith("p10") || chrBreakpoint.endsWith("q10"))) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isValidCenList(List<List<String>> breakpointsFullName) {
		for (List<String> chrBreakpoints: breakpointsFullName) {			
			if (!isValidCen(chrBreakpoints.get(0))) {
				return false;
			}
		}
		return true;
	}
	
	public static List<String> getRowClonesError(List<Clone> rowClones) {
		List<String> validationMessageList = new ArrayList<String>();
		for (Clone clone: rowClones) {
			for (Event e: clone.getCloneInput()) {
				if (!e.getNature().isEmpty() && !e.isUncertainEvent()) {
					validationMessageList = getEventValidationError(e, validationMessageList);
				} else if (e.getNature().isEmpty() && !e.isUncertainEvent()) {
					String eventCode = e.getEventCode();
					String errorHead = "Event \"" + eventCode + "\": ";
					if (!isValidChrList(e.getGainChrs())) {
						for (String chr: e.getGainChrs()) {
							if (!isValidChr(chr)) {
								validationMessageList.add(errorHead + "\"" + chr + "\" is not a valid chromosome");
							}
						}
					}    					
    				if (!isValidChrList(e.getLossChrs())) {
    					for (String chr: e.getLossChrs()) {
							if (!isValidChr(chr)) {
								validationMessageList.add(errorHead + "\"" + chr + "\" is not a valid chromosome");
							}
						}
					}    				
    			}
			}
		}
		return validationMessageList;
	}
	
	public static List<String> getEventValidationError(Event e, List<String> validationMessageList) {
		String eventCode = e.getEventCode();
		String errorHead = "Event \"" + eventCode + "\": ";
		List<String> chrList = e.getChrList();
		List<List<String>> breakpoints = e.getBreakpoints();
		List<List<String>> breakpointsFullName = new ArrayList<>();
		if (!(e instanceof DerEvent)) {
			if (chrList.size() != breakpoints.size()) {
				String errorMsg = errorHead + "The number of \";\" in its chromosome list is not equal to that in its breakpoints list";
				String fixMsg = sizeFixMsg(e);
				if (!fixMsg.isEmpty()) {
					errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
				}
				validationMessageList.add(errorMsg);
				return validationMessageList;
			} else {
				breakpointsFullName = e.getBreakpointsFullName(chrList, breakpoints); 
			}
		} else {
    		breakpointsFullName = null;
    	}
		
		switch (e.getNature()) {
			case "add": {
				if (chrList.size() != 1) {
					validationMessageList.add(errorHead + "the cytogenetic \"add\" event can happen on a single chromosome, but there are " + chrList.size() + " chromosomes");
				} else if (!isValidChr(chrList.get(0))) {
					validationMessageList.add(errorHead + "\"" + chrList.get(0) + "\" is not a valid chromosome");
				} else if (breakpoints.get(0).size() != 1) {
					validationMessageList.add(errorHead + "the cytogenetic \"add\" event can involve only one breakpoint, but there are " + breakpoints.get(0).size() + " breakpoints");
				} else if (!isValidChrBreakpoint(breakpointsFullName.get(0).get(0))) {
					String errorMsg = errorHead + "\"" + breakpointsFullName.get(0).get(0) + "\" is not a valid chromosome breakpoint";
					String fixMsg = fixChrBreakpoint(breakpointsFullName.get(0).get(0));
					if (!fixMsg.isEmpty()) {
						errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
					}
					validationMessageList.add(errorMsg);
				} else {
				}
				return validationMessageList;
			}
			case "del": {
				if (chrList.size() != 1) {
					String errorMsg = errorHead + "the cytogenetic \"del\" event can happen on a single chromosome, but there are " + chrList.size() + " chromosomes";
					if (chrList.size() == 2) {
						String fixMsg = sizeFixMsg(e);
						if (!fixMsg.isEmpty()) {
							errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
						}
					}
					validationMessageList.add(errorMsg);					
				} else if (!isValidChr(chrList.get(0))) {
					validationMessageList.add(errorHead + "\"" + chrList.get(0) + "\" is not a valid chromosome");
				} else if (breakpoints.get(0).size() != 1 && breakpoints.get(0).size() != 2) {
					validationMessageList.add(errorHead + "the cytogenetic \"del\" event can involve either one or two breakpoints, but there are " + breakpoints.get(0).size() + " breakpoints");
				} else if (breakpoints.get(0).size() == 1) {
					if (!isValidChrBreakpoint(breakpointsFullName.get(0).get(0))) {
						String errorMsg = errorHead + "\"" + breakpointsFullName.get(0).get(0) + "\" is not a valid chromosome breakpoint";
						String fixMsg = fixChrBreakpoint(breakpointsFullName.get(0).get(0));
						if (!fixMsg.isEmpty()) {
							errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
						}
						validationMessageList.add(errorMsg);
					} else {
					}
				} else {
					ParseEvent p = new ParseEvent();
					String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
					String chrBreakpoint1 = breakpointsFullName.get(0).get(1); 
					if (!isValidChrBreakpoint(chrBreakpoint0) || !isValidChrBreakpoint(chrBreakpoint1)) {
						if (!isValidChrBreakpoint(chrBreakpoint0)) {
							String errorMsg = errorHead + "\"" + chrBreakpoint0 + "\" is not a valid chromosome breakpoint";
							String fixMsg = fixChrBreakpoint(chrBreakpoint0);
							if (!fixMsg.isEmpty()) {
								errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
							}
							validationMessageList.add(errorMsg);							
						}
						if (!isValidChrBreakpoint(chrBreakpoint1)) {
							String errorMsg = errorHead + "\"" + chrBreakpoint1 + "\" is not a valid chromosome breakpoint";
							String fixMsg = fixChrBreakpoint(chrBreakpoint1);
							if (!fixMsg.isEmpty()) {
								errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
							}
							validationMessageList.add(errorMsg);
						}
					} else if (!p.getChrArm(chrBreakpoint0).equals(p.getChrArm(chrBreakpoint1))) {
						validationMessageList.add(errorHead + "the two breakpoints in an interstitial deletion should come from the same arm");
					} else if (areUnbalancedResolution(chrBreakpoint0, chrBreakpoint1)) {
						validationMessageList.add(errorHead + "the two breakpoints in an interstitial deletion should be in the same level of resolution if both are in the same band");
					} else if (chrBreakpoint0.compareTo(chrBreakpoint1) > 0) {
						validationMessageList.add(errorHead + "the breakpoint more proximal to the centromere should be specified first, maybe you want to try \"" + flipBreakpoints(eventCode) + "\"");
					} else {
					}
				}
				return validationMessageList;
			}
			case "dic": {
				if (chrList.size() != 2) {
					if (chrList.size() == 1) {
						String errorMsg = errorHead + "the cytogenetic \"dic\" event must happen on two chromosomes, but there is only one chromosome";
						String fixMsg = sizeFixMsg(e);
						if (!fixMsg.isEmpty()) {
							errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
						}
						validationMessageList.add(errorMsg);
					} else {
						validationMessageList.add(errorHead + "the cytogenetic \"dic\" event must happen on two chromosomes, but there are " + chrList.size() + " chromosomes");
					}					
				} else if (!isValidChr(chrList.get(0)) || !isValidChr(chrList.get(1))) {
					if (!isValidChr(chrList.get(0))) {
						validationMessageList.add(errorHead + "\"" + chrList.get(0) + "\" is not a valid chromosome");
					}
					if (!isValidChr(chrList.get(1))) {
						validationMessageList.add(errorHead + "\"" + chrList.get(1) + "\" is not a valid chromosome");
					}
				} else if (breakpoints.get(0).size() != 1 || breakpoints.get(1).size() != 1) {
					if (breakpoints.get(0).size() != 1) {
						validationMessageList.add(errorHead + "the cytogenetic \"dic\" event can involve only one breakpoint for each chromosome, but there are " + breakpoints.get(0).size() + " breakpoints for chromosome \"" + chrList.get(0) + "\"");
					}
					if (breakpoints.get(1).size() != 1) {
						validationMessageList.add(errorHead + "the cytogenetic \"dic\" event can involve only one breakpoint for each chromosome, but there are " + breakpoints.get(1).size() + " breakpoints for chromosome \"" + chrList.get(1) + "\"");
					}
				} else if (!isValidChrBreakpoint(breakpointsFullName.get(0).get(0)) || !isValidChrBreakpoint(breakpointsFullName.get(1).get(0))) {
					if (!isValidChrBreakpoint(breakpointsFullName.get(0).get(0))) {
						String errorMsg = errorHead + "\"" + breakpointsFullName.get(0).get(0) + "\" is not a valid chromosome breakpoint";
						String fixMsg = fixChrBreakpoint(breakpointsFullName.get(0).get(0));
						if (!fixMsg.isEmpty()) {
							errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
						}						
						validationMessageList.add(errorMsg);
					} 
					if (!isValidChrBreakpoint(breakpointsFullName.get(1).get(0))) {
						String errorMsg = errorHead + "\"" + breakpointsFullName.get(1).get(0) + "\" is not a valid chromosome breakpoint";
						String fixMsg = fixChrBreakpoint(breakpointsFullName.get(1).get(0));
						if (!fixMsg.isEmpty()) {
							errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
						}						
						validationMessageList.add(errorMsg);
					}
				} else {
				}
				return validationMessageList;
			}
			case "idic": {
				if (chrList.size() != 1) {
					validationMessageList.add(errorHead + "the cytogenetic \"idic\" event can happen on a single chromosome, but there are " + chrList.size() + " chromosomes");
				} else if (!isValidChr(chrList.get(0))) {
					validationMessageList.add(errorHead + "\"" + chrList.get(0) + "\" is not a valid chromosome");
				} else if (breakpoints.get(0).size() != 1) {
					validationMessageList.add(errorHead + "the cytogenetic \"idic\" event can involve only one breakpoint, but there are " + breakpoints.get(0).size() + " breakpoints");
				} else if (!isValidChrBreakpoint(breakpointsFullName.get(0).get(0))) {
					String errorMsg = errorHead + "\"" + breakpointsFullName.get(0).get(0) + "\" is not a valid chromosome breakpoint";
					String fixMsg = fixChrBreakpoint(breakpointsFullName.get(0).get(0));
					if (!fixMsg.isEmpty()) {
						errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
					}					
					validationMessageList.add(errorMsg);
				} else {
				}
				return validationMessageList;
			}
			case "dup": {
				if (chrList.size() != 1) {
					String errorMsg = errorHead + "the cytogenetic \"dup\" event can happen on a single chromosome, but there are " + chrList.size() + " chromosomes";
					if (chrList.size() == 2) {
						String fixMsg = sizeFixMsg(e);
						if (!fixMsg.isEmpty()) {
							errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
						}
					}
					validationMessageList.add(errorMsg);
				} else if (!isValidChr(chrList.get(0))) {
					validationMessageList.add(errorHead + "\"" + chrList.get(0) + "\" is not a valid chromosome");
				} else if (breakpoints.get(0).size() != 2) {
					if (breakpoints.get(0).size() == 1) {
						validationMessageList.add(errorHead + "the cytogenetic \"dup\" event must involve two breakpoints, but there is only one breakpoint");
					} else {
						validationMessageList.add(errorHead + "the cytogenetic \"dup\" event must involve two breakpoints, but there are " + breakpoints.get(0).size() + " breakpoints");
					}						
				} else {
					ParseEvent p = new ParseEvent();
					String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
					String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
					if (!isValidChrBreakpoint(chrBreakpoint0) || !isValidChrBreakpoint(chrBreakpoint1)) {
						if (!isValidChrBreakpoint(chrBreakpoint0)) {
							String errorMsg = errorHead + "\"" + chrBreakpoint0 + "\" is not a valid chromosome breakpoint";
							String fixMsg = fixChrBreakpoint(chrBreakpoint0);
							if (!fixMsg.isEmpty()) {
								errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
							}
							validationMessageList.add(errorMsg);							
						}
						if (!isValidChrBreakpoint(chrBreakpoint1)) {
							String errorMsg = errorHead + "\"" + chrBreakpoint1 + "\" is not a valid chromosome breakpoint";
							String fixMsg = fixChrBreakpoint(chrBreakpoint1);
							if (!fixMsg.isEmpty()) {
								errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
							}
							validationMessageList.add(errorMsg);
						}
					} else if (!p.getChrArm(chrBreakpoint0).equals(p.getChrArm(chrBreakpoint1))) {
						validationMessageList.add(errorHead + "the two breakpoints in a duplication should come from the same arm");
					} else if (areUnbalancedResolution(chrBreakpoint0, chrBreakpoint1)) {
						validationMessageList.add(errorHead + "the two breakpoints in a duplication should be in the same level of resolution if both are in the same band");
					} else {
					}
				}
				return validationMessageList;
			}
			case "trp": {
				if (chrList.size() != 1) {
					String errorMsg = errorHead + "the cytogenetic \"trp\" event can happen on a single chromosome, but there are " + chrList.size() + " chromosomes";
					if (chrList.size() == 2) {
						String fixMsg = sizeFixMsg(e);
						if (!fixMsg.isEmpty()) {
							errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
						}
					}
					validationMessageList.add(errorMsg);
				} else if (!isValidChr(chrList.get(0))) {
					validationMessageList.add(errorHead + "\"" + chrList.get(0) + "\" is not a valid chromosome");
				} else if (breakpoints.get(0).size() != 2) {
					if (breakpoints.get(0).size() == 1) {
						validationMessageList.add(errorHead + "the cytogenetic \"trp\" event must involve two breakpoints, but there is only one breakpoint");
					} else {
						validationMessageList.add(errorHead + "the cytogenetic \"trp\" event must involve two breakpoints, but there are " + breakpoints.get(0).size() + " breakpoints");
					}
				} else {
					ParseEvent p = new ParseEvent();
					String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
					String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
					if (!isValidChrBreakpoint(chrBreakpoint0) || !isValidChrBreakpoint(chrBreakpoint1)) {
						if (!isValidChrBreakpoint(chrBreakpoint0)) {
							String errorMsg = errorHead + "\"" + chrBreakpoint0 + "\" is not a valid chromosome breakpoint";
							String fixMsg = fixChrBreakpoint(chrBreakpoint0);
							if (!fixMsg.isEmpty()) {
								errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
							}
							validationMessageList.add(errorMsg);							
						}
						if (!isValidChrBreakpoint(chrBreakpoint1)) {
							String errorMsg = errorHead + "\"" + chrBreakpoint1 + "\" is not a valid chromosome breakpoint";
							String fixMsg = fixChrBreakpoint(chrBreakpoint1);
							if (!fixMsg.isEmpty()) {
								errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
							}
							validationMessageList.add(errorMsg);
						}
					} else if (!p.getChrArm(chrBreakpoint0).equals(p.getChrArm(chrBreakpoint1))) {
						validationMessageList.add(errorHead + "the two breakpoints in a triplication should come from the same arm");
					} else if (areUnbalancedResolution(chrBreakpoint0, chrBreakpoint1)) {
						validationMessageList.add(errorHead + "the two breakpoints in a triplication should be in the same level of resolution if both are in the same band");
					} else {
					}
				}
				return validationMessageList;
			}
			case "qdp": {
				if (chrList.size() != 1) {
					String errorMsg = errorHead + "the cytogenetic \"qdp\" event can happen on a single chromosome, but there are " + chrList.size() + " chromosomes";
					if (chrList.size() == 2) {
						String fixMsg = sizeFixMsg(e);
						if (!fixMsg.isEmpty()) {
							errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
						}
					}
					validationMessageList.add(errorMsg);
				} else if (!isValidChr(chrList.get(0))) {
					validationMessageList.add(errorHead + "\"" + chrList.get(0) + "\" is not a valid chromosome");
				} else if (breakpoints.get(0).size() != 2) {
					if (breakpoints.get(0).size() == 1) {
						validationMessageList.add(errorHead + "the cytogenetic \"qdp\" event must involve two breakpoints, but there is only one breakpoint");
					} else {
						validationMessageList.add(errorHead + "the cytogenetic \"qdp\" event must involve two breakpoints, but there are " + breakpoints.get(0).size() + " breakpoints");
					}
				} else {
					ParseEvent p = new ParseEvent();
					String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
					String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
					if (!isValidChrBreakpoint(chrBreakpoint0) || !isValidChrBreakpoint(chrBreakpoint1)) {
						if (!isValidChrBreakpoint(chrBreakpoint0)) {
							String errorMsg = errorHead + "\"" + chrBreakpoint0 + "\" is not a valid chromosome breakpoint";
							String fixMsg = fixChrBreakpoint(chrBreakpoint0);
							if (!fixMsg.isEmpty()) {
								errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
							}
							validationMessageList.add(errorMsg);							
						}
						if (!isValidChrBreakpoint(chrBreakpoint1)) {
							String errorMsg = errorHead + "\"" + chrBreakpoint1 + "\" is not a valid chromosome breakpoint";
							String fixMsg = fixChrBreakpoint(chrBreakpoint1);
							if (!fixMsg.isEmpty()) {
								errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
							}
							validationMessageList.add(errorMsg);
						}
					} else if (!p.getChrArm(chrBreakpoint0).equals(p.getChrArm(chrBreakpoint1))) {
						validationMessageList.add(errorHead + "the two breakpoints in a quadruplication should come from the same arm");
					} else if (areUnbalancedResolution(chrBreakpoint0, chrBreakpoint1)) {
						validationMessageList.add(errorHead + "the two breakpoints in a quadruplication should be in the same level of resolution if both are in the same band");
					} else {
					}
				}
				return validationMessageList;
			}
			case "hsr": {
				if (chrList.size() != 1) {
					validationMessageList.add(errorHead + "the cytogenetic \"hsr\" event can happen on a single chromosome, but there are " + chrList.size() + " chromosomes");
				} else if (!isValidChr(chrList.get(0))) {
					validationMessageList.add(errorHead + "\"" + chrList.get(0) + "\" is not a valid chromosome");
				} else if (breakpoints.get(0).size() != 1) {
					validationMessageList.add(errorHead + "the cytogenetic \"hsr\" event can involve only one breakpoint, but there are " + breakpoints.get(0).size() + " breakpoints");
				} else if (!isValidChrBreakpoint(breakpointsFullName.get(0).get(0))) {
					String errorMsg = errorHead + "\"" + breakpointsFullName.get(0).get(0) + "\" is not a valid chromosome breakpoint";
					String fixMsg = fixChrBreakpoint(breakpointsFullName.get(0).get(0));
					if (!fixMsg.isEmpty()) {
						errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
					}
					validationMessageList.add(errorMsg);
				} else {
				}
				return validationMessageList;
			}
			case "ins": {
				if (chrList.size() != 1 && chrList.size() != 2) {
					String errorMsg = errorHead + "the cytogenetic \"ins\" event can happen on a single chromosome or two chromosomes, but there are " + chrList.size() + " chromosomes";
					if (chrList.size() == 3) {
						String fixMsg = sizeFixMsg(e);
						if (!fixMsg.isEmpty()) {
							errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
						}
					}
					validationMessageList.add(errorMsg);
				} else if (chrList.size() == 1) {
					if (!isValidChr(chrList.get(0))) {
						String errorMsg = errorHead + "\"" + chrList.get(0) + "\" is not a valid chromosome";
						String fixMsg = sizeFixMsg(e);
						if (!fixMsg.isEmpty()) {
							errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
						}
						validationMessageList.add(errorMsg);
					} else if (breakpoints.get(0).size() != 3) {
						if (breakpoints.get(0).size() == 1) {
							validationMessageList.add(errorHead + "the cytogenetic \"ins\" event must involve three breakpoints when there is only one chromosome involved, but there is only one breakpoint");
						} else {
							validationMessageList.add(errorHead + "the cytogenetic \"ins\" event must involve three breakpoints when there is only one chromosome involved, but there are " + breakpoints.get(0).size() + " breakpoints");
						}
					} else if (!isValidChrBreakpointList(breakpointsFullName)) {
						List<String> chrBreakpoints = breakpointsFullName.get(0);
						for (int i = 0; i < chrBreakpoints.size(); i++) {
							if (!isValidChrBreakpoint(chrBreakpoints.get(i))) {
								String errorMsg = errorHead + "\"" + chrBreakpoints.get(i) + "\" is not a valid chromosome breakpoint";
								String fixMsg = fixChrBreakpoint(chrBreakpoints.get(i));
								if (!fixMsg.isEmpty()) {
									errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
								}
								validationMessageList.add(errorMsg);
							}							
						}
						String fixMsg = sizeFixMsg(e);
						if (!fixMsg.isEmpty()) {
							validationMessageList.add(errorHead + "maybe you want to try \"" + fixMsg + "\"");
						}
					} else {
						ParseEvent p = new ParseEvent();
						String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
						String chrBreakpoint2 = breakpointsFullName.get(0).get(2);
						if (!p.getChrArm(chrBreakpoint1).equals(p.getChrArm(chrBreakpoint2))) {
							validationMessageList.add(errorHead + "the inserted segment should come from the same arm of a chromosome");
						} else if (areUnbalancedResolution(chrBreakpoint1, chrBreakpoint2)) {
							validationMessageList.add(errorHead + "the two breakpoints of the inserted segment should be in the same level of resolution if both are in the same band");
						} else {
						}
					}
				} else {					
					if (!isValidChr(chrList.get(0)) || !isValidChr(chrList.get(1))) {
						if (!isValidChr(chrList.get(0))) {
							validationMessageList.add(errorHead + "\"" + chrList.get(0) + "\" is not a valid chromosome");
						}
						if (!isValidChr(chrList.get(1))) {
							validationMessageList.add(errorHead + "\"" + chrList.get(1) + "\" is not a valid chromosome");
						}
						String fixMsg = sizeFixMsg(e);
						if (!fixMsg.isEmpty()) {
							validationMessageList.add(errorHead + "maybe you want to try \"" + fixMsg + "\"");
						}
					} else if (breakpoints.get(0).size() != 1 || breakpoints.get(1).size() != 2) {
						String errorMsg = errorHead + "the cytogenetic insertion between two chromosomes must involve only one breakpoint for the chromosome  where the inserted segment is inserted, which is specified first, and two breakpoints on the chromosome where the inserted segment is coming from";
						String fixMsg = sizeFixMsg(e);
						if (!fixMsg.isEmpty()) {
							errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
						}
						validationMessageList.add(errorMsg);						
					} else if (!isValidChrBreakpointList(breakpointsFullName)) {
						for (List<String> chrBreakpoints: breakpointsFullName) {
							for (String chrBreakpoint: chrBreakpoints) {
								if (!isValidChrBreakpoint(chrBreakpoint)) {
									String errorMsg = errorHead + "\"" + chrBreakpoint + "\" is not a valid chromosome breakpoint";
									String fixMsg = fixChrBreakpoint(chrBreakpoint);
									if (!fixMsg.isEmpty()) {
										errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
									}
									validationMessageList.add(errorMsg);									
								}
							}
						}
						String fixMsg = sizeFixMsg(e);
						if (!fixMsg.isEmpty()) {
							validationMessageList.add(errorHead + "maybe you want to try \"" + fixMsg + "\"");
						}
					} else {
						ParseEvent p = new ParseEvent();
						String chrBreakpoint1 = breakpointsFullName.get(1).get(0);
						String chrBreakpoint2 = breakpointsFullName.get(1).get(1);
						if (!p.getChrArm(chrBreakpoint1).equals(p.getChrArm(chrBreakpoint2))) {
							validationMessageList.add(errorHead + "the inserted segment should come from the same arm of a chromosome");
						} else if (areUnbalancedResolution(chrBreakpoint1, chrBreakpoint2)) {
							validationMessageList.add(errorHead + "the two breakpoints of the inserted segment should be in the same level of resolution if both are in the same band");
						} else {
						}
					}
				}
				return validationMessageList;
			}
			case "inv": {
				if (chrList.size() != 1) {
					String errorMsg = errorHead + "the cytogenetic \"inv\" event can happen on a single chromosome, but there are " + chrList.size() + " chromosomes";					
					if (chrList.size() == 2) {
						String fixMsg = sizeFixMsg(e);
						if (!fixMsg.isEmpty()) {
							errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
						}
					}
					validationMessageList.add(errorMsg);
				} else if (!isValidChr(chrList.get(0))) {
					validationMessageList.add(errorHead + "\"" + chrList.get(0) + "\" is not a valid chromosome");
				} else if (breakpoints.get(0).size() != 2) {
					if (breakpoints.get(0).size() == 1) {
						validationMessageList.add(errorHead + "the cytogenetic \"inv\" event must involve two breakpoints, but there is only one breakpoint");
					} else {
						validationMessageList.add(errorHead + "the cytogenetic \"inv\" event must involve two breakpoints, but there are " + breakpoints.get(0).size() + " breakpoints");
					}
				} else if (!isValidChrBreakpoint(breakpointsFullName.get(0).get(0)) || !isValidChrBreakpoint(breakpointsFullName.get(0).get(1))) {
					String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
					String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
					if (!isValidChrBreakpoint(chrBreakpoint0)) {
						String errorMsg = errorHead + "\"" + chrBreakpoint0 + "\" is not a valid chromosome breakpoint";
						String fixMsg = fixChrBreakpoint(chrBreakpoint0);
						if (!fixMsg.isEmpty()) {
							errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
						}
						validationMessageList.add(errorMsg);
					}
					if (!isValidChrBreakpoint(chrBreakpoint1)) {
						String errorMsg = errorHead + "\"" + chrBreakpoint1 + "\" is not a valid chromosome breakpoint";
						String fixMsg = fixChrBreakpoint(chrBreakpoint1);
						if (!fixMsg.isEmpty()) {
							errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
						}
						validationMessageList.add(errorMsg);
					}
				} else if (areUnbalancedResolution(breakpointsFullName.get(0).get(0), breakpointsFullName.get(0).get(1))) {
					validationMessageList.add(errorHead + "the two breakpoints of the inverted segment should be in the same level of resolution if both are in the same band");
				} else if (breakpointsFullName.get(0).get(0).compareTo(breakpointsFullName.get(0).get(1)) > 0) {
					validationMessageList.add(errorHead + "the breakpoint more proximal to the centromere should be specified first, maybe you want to try \"" + flipBreakpoints(eventCode) + "\"");
				} else {
				}
				return validationMessageList;
			}
			case "i": {
				if (chrList.size() != 1) {
					validationMessageList.add(errorHead + "the cytogenetic \"i\" event can happen on a single chromosome, but there are " + chrList.size() + " chromosomes");
				} else if (!isValidChr(chrList.get(0))) {
					validationMessageList.add(errorHead + "\"" + chrList.get(0) + "\" is not a valid chromosome");
				} else if (breakpoints.get(0).size() != 1) {
					validationMessageList.add(errorHead + "the cytogenetic \"i\" event can involve only one breakpoint, but there are " + breakpoints.get(0).size() + " breakpoints");
				} else if (!isValidCen(breakpointsFullName.get(0).get(0))) {
					validationMessageList.add(errorHead + "the breakpoint of the cytogenetic \"i\" event must be a centromeric band, either p10 or q10, but \"" + breakpointsFullName.get(0).get(0) + "\" is not such one");
				} else {
				}
				return validationMessageList;
			}
			case "r": {
				if (!isValidChrList(chrList)) {
					for (int i = 0; i < chrList.size(); i++) {
						if (!isValidChr(chrList.get(i))) {
							validationMessageList.add(errorHead + "\"" + chrList.get(i) + "\" is not a valid chromosome");
						}
					}
					String fixMsg = sizeFixMsg(e);
					if (!fixMsg.isEmpty()) {
						validationMessageList.add(errorHead + "maybe you want to try \"" + fixMsg + "\"");
					}
				} else if (!isValidBreakpointsWithFixedNumBreakpointsInEachChr(breakpoints, 2)) {
					int i = 0;
					for (List<String> pointList: breakpoints) {
						if (pointList.size() != 2) {
							if (pointList.size() == 1) {
								validationMessageList.add(errorHead + "the cytogenetic \"r\" event must involve two breakpoints for each chromosome, but there is one breakpoint for chromosome \"" + chrList.get(i) + "\"");
							} else {
								validationMessageList.add(errorHead + "the cytogenetic \"r\" event must involve two breakpoints for each chromosome, but there are " + breakpoints.get(i).size() + " breakpoints for chromosome \"" + chrList.get(i) + "\"");
							}							
						}
						i++;
					}
					String fixMsg = sizeFixMsg(e);
					if (!fixMsg.isEmpty()) {
						validationMessageList.add(errorHead + "maybe you want to try \"" + fixMsg + "\"");
					}
				} else if (!isValidChrBreakpointList(breakpointsFullName)) {
					for (List<String> chrBreakpoints: breakpointsFullName) {
						for (String chrBreakpoint: chrBreakpoints) {
							if (!isValidChrBreakpoint(chrBreakpoint)) {
								String errorMsg = errorHead + "\"" + chrBreakpoint + "\" is not a valid chromosome breakpoint";
								String fixMsg = fixChrBreakpoint(chrBreakpoint);
								if (!fixMsg.isEmpty()) {
									errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
								}
								validationMessageList.add(errorMsg);
							}
						}
					}
					String fixMsg = sizeFixMsg(e);
					if (!fixMsg.isEmpty()) {
						validationMessageList.add(errorHead + "maybe you want to try \"" + fixMsg + "\"");
					}
				} else {
					ParseEvent p = new ParseEvent();
					for (List<String> chrBreakpoints: breakpointsFullName) {
						if (p.getChrArm(chrBreakpoints.get(0)).equals(p.getChrArm(chrBreakpoints.get(1)))) {
							validationMessageList.add(errorHead + "the two breakpoints of each chromosome in a ring should come from different arms");
							break;
						}
					}
				}
				return validationMessageList;
			}
			case "tas": {
				if (chrList.size() < 2) {
					String errorMsg = errorHead + "the cytogenetic \"tas\" event must involve at least two chromosomes, but there is only one chromosome";
					String fixMsg = sizeFixMsg(e);
					if (!fixMsg.isEmpty()) {
						errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
					}
					validationMessageList.add(errorMsg);
				} else if (!isValidChrList(chrList)) {
					for (int i = 0; i < chrList.size(); i++) {
						if (!isValidChr(chrList.get(i))) {
							validationMessageList.add(errorHead + "\"" + chrList.get(i) + "\" is not a valid chromosome");
						}
					}
					String fixMsg = sizeFixMsg(e);
					if (!fixMsg.isEmpty()) {
						validationMessageList.add(errorHead + "maybe you want to try \"" + fixMsg + "\"");
					}
				} else if (!isValidBreakpointsWithVariedNumBreakpointsInEachChr(breakpoints, 1, 2)) {
					if (breakpoints.get(0).size() != 1) {
						validationMessageList.add(errorHead + "the cytogenetic \"tas\" event can involve only one breakpoint on the ending chromosomes, but there are " + breakpoints.get(0).size() + " breakpoints for chromosome \"" + chrList.get(0) + "\"");
					}
					if (breakpoints.get(breakpoints.size() - 1).size() != 1) {
						validationMessageList.add(errorHead + "the cytogenetic \"tas\" event can involve only one breakpoint on the ending chromosomes, but there are " + breakpoints.get(breakpoints.size() - 1).size() + " breakpoints for chromosome \"" + chrList.get(breakpoints.size() - 1) + "\"");
					}
					for (int i = 1; i < breakpoints.size() - 1; i++) {
						if (breakpoints.get(i).size() != 2) {
							if (breakpoints.get(i).size() == 1) {
								validationMessageList.add(errorHead + "the cytogenetic \"tas\" event must involve two breakpoints on the interior chromosomes, but there is only one breakpoint for chromosome \"" + chrList.get(i) + "\"");
							} else {
								validationMessageList.add(errorHead + "the cytogenetic \"tas\" event must involve two breakpoints on the interior chromosomes, but there are " + breakpoints.get(i).size() + " breakpoints for chromosome \"" + chrList.get(i) + "\"");
							}							
						}
					}
					String fixMsg = sizeFixMsg(e);
					if (!fixMsg.isEmpty()) {
						validationMessageList.add(errorHead + "maybe you want to try \"" + fixMsg + "\"");
					}
				} else if (!isValidTerminalList(breakpointsFullName)) {
					int i = 0;
					for (List<String> chrBreakpoints: breakpointsFullName) {
						int j = 0;
						for (String chrBreakpoint: chrBreakpoints) {
							if (!isValidTerminal(chrBreakpoint)) {
								validationMessageList.add(errorHead + "the cytogenetic \"tas\" event must involve only terminal bands, but \"" + breakpointsFullName.get(i).get(j) + "\" is not a valid terminal breakpoint");
							}
							j++;
						}
						i++;
					}
				} else {
					ParseEvent p = new ParseEvent();
					for (List<String> chrBreakpoints: breakpointsFullName) {
						if (chrBreakpoints.size() == 2 && p.getChrArm(chrBreakpoints.get(0)).equals(p.getChrArm(chrBreakpoints.get(1)))) {
							validationMessageList.add(errorHead + "the two breakpoints on each of the interior chromosomes in telomeric associations should come from different arms");
							break;
						}
					}
				}
				return validationMessageList;
			}
			case "t": {
				if (chrList.size() < 2) {
					String errorMsg = errorHead + "the cytogenetic \"t\" event must involve at least two chromosomes, but there is only one chromosome";
					String fixMsg = sizeFixMsg(e);
					if (!fixMsg.isEmpty()) {
						errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
					}
					validationMessageList.add(errorMsg);
				} else if (!isValidChrList(chrList)) {
					for (int i = 0; i < chrList.size(); i++) {
						if (!isValidChr(chrList.get(i))) {
							validationMessageList.add(errorHead + "\"" + chrList.get(i) + "\" is not a valid chromosome");
						}
					}
					String fixMsg = sizeFixMsg(e);
					if (!fixMsg.isEmpty()) {
						validationMessageList.add(errorHead + "maybe you want to try \"" + fixMsg + "\"");
					}
				} else if (!isValidBreakpointsWithFixedNumBreakpointsInEachChr(breakpoints, 1) && !isValidBreakpointsWithFixedNumBreakpointsInEachChr(breakpoints, 2)) {
					String errorMsg = errorHead + "the cytogenetic \"t\" event must involve one breakpoint for all chromosomes, or two breakpoints for all chromosomes";
					String fixMsg = sizeFixMsg(e);
					if (!fixMsg.isEmpty()) {
						errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
					}
					validationMessageList.add(errorMsg);
				} else if (!isValidChrBreakpointList(breakpointsFullName)) {
					for (List<String> chrBreakpoints: breakpointsFullName) {
						for (String chrBreakpoint: chrBreakpoints) {
							if (!isValidChrBreakpoint(chrBreakpoint)) {
								String errorMsg = errorHead + "\"" + chrBreakpoint + "\" is not a valid chromosome breakpoint";
								String fixMsg = fixChrBreakpoint(chrBreakpoint);
								if (!fixMsg.isEmpty()) {
									errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
								}
								validationMessageList.add(errorMsg);
							}
						}
					}
					String fixMsg = sizeFixMsg(e);
					if (!fixMsg.isEmpty()) {
						validationMessageList.add(errorHead + "maybe you want to try \"" + fixMsg + "\"");
					}
				} else {
					if (isValidBreakpointsWithFixedNumBreakpointsInEachChr(breakpoints, 2)) {
						ParseEvent p = new ParseEvent();
						for (List<String> chrBreakpoints: breakpointsFullName) {
							if (!p.getChrArm(chrBreakpoints.get(0)).equals(p.getChrArm(chrBreakpoints.get(1)))) {
								String errorMsg = errorHead + "the two breakpoints in an interstitial translocation should come from the same arm";
								String fixMsg = sizeFixMsg(e);
								if (!fixMsg.isEmpty()) {
									errorMsg += ", maybe you want to try \"" + fixMsg + "\"";
								}
								validationMessageList.add(errorMsg);
								break;
							} else if (areUnbalancedResolution(chrBreakpoints.get(0), chrBreakpoints.get(1))) {
								validationMessageList.add(errorHead + "the two breakpoints in an interstitial translocation should be in the same level of resolution if both are in the same band");
								break;
							}
						}
					}
				}
				return validationMessageList;
			}
			case "trc": {
				if (chrList.size() != 3) {
					String errorMsg;
					if (chrList.size() == 1) {
						errorMsg = errorHead + "the cytogenetic \"trc\" event must happen on three chromosomes, but there is only one chromosome";						
					} else {
						errorMsg = errorHead + "the cytogenetic \"trc\" event must happen on three chromosomes, but there are " + chrList.size() + " chromosomes";						
					}	
					String fixMsg = sizeFixMsg(e);
					if (!fixMsg.isEmpty()) {
						errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
					}
					validationMessageList.add(errorMsg);
				} else if (!isValidChrList(chrList)) {
					for (int i = 0; i < chrList.size(); i++) {
						if (!isValidChr(chrList.get(i))) {
							validationMessageList.add(errorHead + "\"" + chrList.get(i) + "\" is not a valid chromosome");
						}
					}					
					String fixMsg = sizeFixMsg(e);
					if (!fixMsg.isEmpty()) {
						validationMessageList.add(errorHead + "maybe you want to try \"" + fixMsg + "\"");
					}
				} else if (!isValidBreakpointsWithVariedNumBreakpointsInEachChr(breakpoints, 1, 2)) {
					if (breakpoints.get(0).size() != 1) {
						validationMessageList.add(errorHead + "the cytogenetic \"trc\" event can involve only one breakpoint on the ending chromosomes, but there are " + breakpoints.get(0).size() + " breakpoints for chromosome \"" + chrList.get(0) + "\"");
					}
					if (breakpoints.get(1).size() != 2) {
						if (breakpoints.get(1).size() == 1) {
							validationMessageList.add(errorHead + "the cytogenetic \"trc\" event must involve two breakpoints on the middle chromosome, but there is only one breakpoint for chromosome \"" + chrList.get(1) + "\"");
						} else {
							validationMessageList.add(errorHead + "the cytogenetic \"trc\" event must involve two breakpoints on the middle chromosome, but there are " + breakpoints.get(1).size() + " breakpoints for chromosome \"" + chrList.get(1) + "\"");
						}						
					}
					if (breakpoints.get(2).size() != 1) {
						validationMessageList.add(errorHead + "the cytogenetic \"trc\" event can involve only one breakpoint on the ending chromosomes, but there are " + breakpoints.get(2).size() + " breakpoints for chromosome \"" + chrList.get(2) + "\"");
					}	
					String fixMsg = sizeFixMsg(e);
					if (!fixMsg.isEmpty()) {
						validationMessageList.add(errorHead + "maybe you want to try \"" + fixMsg + "\"");
					}
				} else if (!isValidChrBreakpointList(breakpointsFullName)) {
					for (List<String> chrBreakpoints: breakpointsFullName) {
						for (String chrBreakpoint: chrBreakpoints) {
							if (!isValidChrBreakpoint(chrBreakpoint)) {
								String errorMsg = errorHead + "\"" + chrBreakpoint + "\" is not a valid chromosome breakpoint";
								String fixMsg = fixChrBreakpoint(chrBreakpoint);
								if (!fixMsg.isEmpty()) {
									errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
								}
								validationMessageList.add(errorMsg);
							}
						}
					}
				} else {
					ParseEvent p = new ParseEvent();
					if (p.getChrArm(breakpointsFullName.get(1).get(0)).equals(p.getChrArm(breakpointsFullName.get(1).get(1)))) {
						validationMessageList.add(errorHead + "the two breakpoints on the second chromosome in tricentric chromosomes should come from different arms");
					} 
				}
				return validationMessageList;
			}
			case "rob": {
				if (chrList.size() != 2) {					
					if (chrList.size() == 1) {
						String errorMsg = errorHead + "the cytogenetic \"rob\" event must happen on two chromosomes, but there is only one chromosome";
						String fixMsg = sizeFixMsg(e);
						if (!fixMsg.isEmpty()) {
							errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
						}
						validationMessageList.add(errorMsg);
					} else {
						validationMessageList.add(errorHead + "the cytogenetic \"rob\" event must happen on two chromosomes, but there are " + chrList.size() + " chromosomes");
					}
				} else if (!isValidChrList(chrList)) {
					for (int i = 0; i < chrList.size(); i++) {
						if (!isValidChr(chrList.get(i))) {
							validationMessageList.add(errorHead + "\"" + chrList.get(i) + "\" is not a valid chromosome");
						}
					}
					String fixMsg = sizeFixMsg(e);
					if (!fixMsg.isEmpty()) {
						validationMessageList.add(errorHead + "maybe you want to try \"" + fixMsg + "\"");
					}
				} else if (!isValidBreakpointsWithFixedNumBreakpointsInEachChr(breakpoints, 1)) {
					int i = 0;
					for (List<String> pointList: breakpoints) {
						if (pointList.size() != 1) {
							validationMessageList.add(errorHead + "the cytogenetic \"rob\" event can involve only one breakpoint for each chromosome, but there are " + breakpoints.get(i).size() + " breakpoints for chromosome \"" + chrList.get(i) + "\"");
						}
						i++;
					}
				} else if (!isValidCenList(breakpointsFullName)) {
					for (List<String> chrBreakpoints: breakpointsFullName) {			
						if (!isValidCen(chrBreakpoints.get(0))) {
							validationMessageList.add(errorHead + "the breakpoint of the cytogenetic \"rob\" event must be a centromeric band, either p10 or q10, but \"" + chrBreakpoints.get(0) + "\" is not such one");
						}
					}
				} else {
				}
				return validationMessageList;
			}
			case "ider": {
				if (chrList.size() != 1 || breakpoints.size() != 1) {
					validationMessageList.add(errorHead + "the cytogenetic \"ider\" event can happen on a single chromosome, but there are " + chrList.size() + " chromosomes");
				} else if (!isValidChr(chrList.get(0))) {
					validationMessageList.add(errorHead + "\"" + chrList.get(0) + "\" is not a valid chromosome");
				} else if (breakpoints.get(0).size() != 1) {
					validationMessageList.add(errorHead + "the cytogenetic \"ider\" event can involve only one breakpoint, but there are " + breakpoints.get(0).size() + " breakpoints");
				} else if (!isValidCen(e.getBreakpointsFullName(chrList, breakpoints).get(0).get(0))) {
					validationMessageList.add(errorHead + "the breakpoint of the cytogenetic \"i\" event must be a centromeric band, either p10 or q10, but \"" + e.getBreakpointsFullName(chrList, breakpoints).get(0).get(0) + "\" is not such one");
//				} else if (((DerEvent)e).getSubevents().size() == 0) {
//					validationMessageList.add(errorHead + "the cytogenetic \"ider\" event must have at least one structural rearrangement");
				} else {
					boolean allStructuralRearrangementsValid = true;
					for (Event subevent: ((DerEvent)e).getSubevents()) {
						if (!subevent.isUncertainEvent()) {							
							for (String errorMessage: getGenericSubeventValidationError(subevent)) {
								validationMessageList.add(errorHead + errorMessage);
								allStructuralRearrangementsValid = false;
							}
						}
					}
					if (allStructuralRearrangementsValid && !isUncerntainDer((DerEvent)e)) { 
						if (!new IderValidation((DerEvent)e).getDerivativeValidationOutcome().isValidDerivativeChr()) {
							validationMessageList.add(errorHead + "The structural rearrangements of this derivative chromosome are incompatible");
						}
					}					
				}
				return validationMessageList;
			}
			case "der": {
				boolean allStructuralRearrangementsValid = true;
				if (chrList.size() != 1 && chrList.size() != 2) {
					validationMessageList.add(errorHead + "the cytogenetic \"der\" event can happen on a single chromosome or two chromosomes, but there are " + chrList.size() + " chromosomes");
				} else if (chrList.size() == 1) {
					if (!isValidChr(chrList.get(0))) {
						validationMessageList.add(errorHead + "\"" + chrList.get(0) + "\" is not a valid chromosome");
					} else if (breakpoints.size() > 0) {
						validationMessageList.add(errorHead + "the cytogenetic \"der\" event with only one chromosome should not have any breakpoint");
					} else if (((DerEvent)e).getSubevents().size() == 0) {
						validationMessageList.add(errorHead + "the cytogenetic \"der\" event must have at least one structural rearrangement");
					} else {
						String derChr = e.getChrList().get(0);
						List<Event> subevents = ((DerEvent)e).getSubevents();
						for (Event subevent: subevents) {
							switch (subevent.getNature()) {
								case "r": {
									if (!subevent.isUncertainEvent()) {
										for (String errorMessage: getRSubeventValidationError(subevent, derChr)) {
											validationMessageList.add(errorHead + errorMessage);
											allStructuralRearrangementsValid = false;
										}
									}
									break;
								}
								default: {
									if (!subevent.isUncertainEvent()) {										
										for (String errorMessage: getGenericSubeventValidationError(subevent)) {
											validationMessageList.add(errorHead + errorMessage);
											allStructuralRearrangementsValid = false;
										}
									}
								}
							}
						}
						if (allStructuralRearrangementsValid) {
							if (!Validator.containDerChr(subevents, derChr)) {
								validationMessageList.add(errorHead + "At least one structural rearrangement must contain the derivative chromosome's derived chromosome \"" + derChr + "\"");
							} else {
								if (!isUncerntainDer((DerEvent)e) && !new DerivativeValidation((DerEvent)e).getDerivativeValidationOutcome().isValidDerivativeChr()) {
									validationMessageList.add(errorHead + "The structural rearrangements of this derivative chromosome are incompatible");
								}
							}
						}						
					} 
				} else {
					if (!isValidChr(chrList.get(0)) || !isValidChr(chrList.get(1))) {
						if (!isValidChr(chrList.get(0))) {
							validationMessageList.add(errorHead + "\"" + chrList.get(0) + "\" is not a valid chromosome");
						}
						if (!isValidChr(chrList.get(1))) {
							validationMessageList.add(errorHead + "\"" + chrList.get(1) + "\" is not a valid chromosome");
						}
					} else if (breakpoints.size() > 0) {
						if (breakpoints.size() != 2) {
							validationMessageList.add(errorHead + "The number of \";\" in its chromosome list is not equal to that in its breakpoints list");
						} else if (breakpoints.get(0).size() != 1 || breakpoints.get(1).size() != 1) {
							if (breakpoints.get(0).size() != 1) {
								validationMessageList.add(errorHead + "the cytogenetic \"der\" event can involve only one breakpoint on each chromosome, but there are " + breakpoints.get(0).size() + " breakpoints for chromosome \"" + chrList.get(0) + "\"");
							}
							if (breakpoints.get(1).size() != 1) {
								validationMessageList.add(errorHead + "the cytogenetic \"der\" event can involve only one breakpoint on each chromosome, but there are " + breakpoints.get(1).size() + " breakpoints for chromosome \"" + chrList.get(1) + "\"");
							}
						} else if (!isValidCenList(e.getBreakpointsFullName(chrList, breakpoints))) {
							breakpointsFullName = e.getBreakpointsFullName(chrList, breakpoints);
							for (List<String> chrBreakpoints: breakpointsFullName) {			
								if (!isValidCen(chrBreakpoints.get(0))) {
									validationMessageList.add(errorHead + "the breakpoint of the cytogenetic \"der\" event must be a centromeric band, either p10 or q10, but \"" + chrBreakpoints.get(0) + "\" is not such one");
								}
							}							
						} else if (((DerEvent)e).getSubevents().size() > 0) {
							for (Event subevent: ((DerEvent)e).getSubevents()) {
								if (!subevent.isUncertainEvent()) {
									for (String errorMessage: getGenericSubeventValidationError(subevent)) {
										validationMessageList.add(errorHead + errorMessage);
										allStructuralRearrangementsValid = false;
									}
								}
							}
							if (allStructuralRearrangementsValid && !isUncerntainDer((DerEvent)e)) {
								if (!chrList.get(0).equals(chrList.get(1))) {
									if (!new DicDerivativeValidationDifferentDerChrWithBreakpoints((DerEvent)e).getDicDerivativeValidationOutcome().isValidDerivativeChr()) {
										validationMessageList.add(errorHead + "The structural rearrangements of this derivative chromosome are incompatible");
									}
								} else {
									List<List<String>> derCens = e.getBreakpointsFullName(chrList, breakpoints);
									if (!getChrArm(derCens.get(0).get(0)).equals(getChrArm(derCens.get(1).get(0)))) {
										if (!new DicDerivativeValidationDifferentDerChrWithBreakpoints((DerEvent)e).getDicDerivativeValidationOutcome().isValidDerivativeChr()) {
											validationMessageList.add(errorHead + "The structural rearrangements of this derivative chromosome are incompatible");
										}
									} else {
										if (!new DicDerivativeValidationSameDerChrSameArmWithBreakpoints((DerEvent)e).getDicDerivativeValidationOutcome().isValidDerivativeChr()) {
											validationMessageList.add(errorHead + "The structural rearrangements of this derivative chromosome are incompatible");
										}
									}
								}						
							}
						} else {
                          // e.g. der(1;7)(q10;p10)
						}						
					} else {
						if (((DerEvent)e).getSubevents().size() == 0) {
							validationMessageList.add(errorHead + "the cytogenetic \"der\" event must have at least one structural rearrangement");
						} else {
							for (Event subevent: ((DerEvent)e).getSubevents()) {
								if (!subevent.isUncertainEvent()) {
									for (String errorMessage: getGenericSubeventValidationError(subevent)) {
										validationMessageList.add(errorHead + errorMessage);
										allStructuralRearrangementsValid = false;
									}
								}
							}
							if (allStructuralRearrangementsValid && !isUncerntainDer((DerEvent)e)) {
								if (!chrList.get(0).equals(chrList.get(1))) {
									if (!new DicDerivativeValidationDifferentDerChrNoBreakpoints((DerEvent)e).getDicDerivativeValidationOutcome().isValidDerivativeChr()) {
										validationMessageList.add(errorHead + "The structural rearrangements of this derivative chromosome are incompatible");
									}
								} else {
									if (!new DicDerivativeValidationSameDerChrNoBreakpoints((DerEvent)e).getDicDerivativeValidationOutcome().isValidDerivativeChr()) {
										validationMessageList.add(errorHead + "The structural rearrangements of this derivative chromosome are incompatible");
									}
								}								
							}
						}
					}					
				}
				return validationMessageList;
			}
			// We don't need to add "default" here, because we have listed all cases allowed by our grammar.			
		}
		return validationMessageList;
	} 
	
	public static List<String> getRSubeventValidationError(Event e, String derChr) {
		List<String> validationMessageList = new ArrayList<String>();
		String eventCode = e.getEventCode();
		String errorHead = "Subevent \"" + eventCode + "\": ";
		List<String> chrList = e.getChrList();
		List<List<String>> breakpoints = e.getBreakpoints();
		List<List<String>> breakpointsFullName = new ArrayList<>();
		if (chrList.size() != breakpoints.size()) {
			String errorMsg = errorHead + "The number of \";\" in its chromosome list is not equal to that in its breakpoints list";
			String fixMsg = fixRSubevent(e, derChr);
			if (!fixMsg.isEmpty()) {
				errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
			}
			validationMessageList.add(errorMsg);
			return validationMessageList;
		} else {
			breakpointsFullName = e.getBreakpointsFullName(chrList, breakpoints); 
		}
		if (!isValidChrList(chrList)) {
			for (int i = 0; i < chrList.size(); i++) {
				if (!isValidChr(chrList.get(i))) {
					validationMessageList.add(errorHead + "\"" + chrList.get(i) + "\" is not a valid chromosome");
				}
			}
			String fixMsg = fixRSubevent(e, derChr);
			if (!fixMsg.isEmpty()) {
				validationMessageList.add(errorHead + "maybe you want to try \"" + fixMsg + "\"");
			}
		}  else if (!chrList.contains(derChr)) {
			String errorMsg = errorHead + "The chromosome of \"" + derChr + "\" must be included in the chromosome list of the ring which is part of the structural rearrangement for derivative chromosome of \"" + derChr + "\"";
			String fixMsg = fixRSubevent(e, derChr);
			if (!fixMsg.isEmpty()) {
				errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
			}
			validationMessageList.add(errorMsg);
			return validationMessageList;					
		}  else if (!isValidBreakpointsWithFixedNumBreakpointsInEachChr(breakpoints, 2)) {
			int i = 0;
			for (List<String> pointList: breakpoints) {
				if (pointList.size() != 2) {
					if (pointList.size() == 1) {
						validationMessageList.add(errorHead + "the cytogenetic \"r\" subevent must involve two breakpoints for each chromosome, but there is one breakpoint for chromosome \"" + chrList.get(i) + "\"");
					} else {
						validationMessageList.add(errorHead + "the cytogenetic \"r\" subevent must involve two breakpoints for each chromosome, but there are " + breakpoints.get(i).size() + " breakpoints for chromosome \"" + chrList.get(i) + "\"");
					}							
				}
				i++;
			}
			String fixMsg = fixRSubevent(e, derChr);
			if (!fixMsg.isEmpty()) {
				validationMessageList.add(errorHead + "maybe you want to try \"" + fixMsg + "\"");
			}
		} else if (!isValidChrBreakpointList(breakpointsFullName)) {
			int i = 0;
			for (List<String> chrBreakpoints: breakpointsFullName) {
				int j = 0;
				for (String chrBreakpoint: chrBreakpoints) {
					if (!isValidChrBreakpoint(chrBreakpoint)) {
						validationMessageList.add(errorHead + "\"" + breakpointsFullName.get(i).get(j) + "\" is not a valid chromosome breakpoint");
					}
					j++;
				}
				i++;
			}
			String fixMsg = fixRSubevent(e, derChr);
			if (!fixMsg.isEmpty()) {
				validationMessageList.add(errorHead + "maybe you want to try \"" + fixMsg + "\"");
			}
		} else {
			int index = chrList.indexOf(derChr);
			List<List<String>> breakpointsExceptDerChr = new ArrayList<>();
			for (int i = 0; i < e.getChrList().size(); i++) {							        
	        	if (i != index) {
	        		breakpointsExceptDerChr.add(breakpointsFullName.get(i));						        	
		        }
		    }
			ParseEvent p = new ParseEvent();
//			List<String> chrBreakpointsOfDerChr = breakpointsFullName.get(index);			
//			if (p.getChrArm(chrBreakpointsOfDerChr.get(0)).equals(p.getChrArm(chrBreakpointsOfDerChr.get(1)))) {
//				validationMessageList.add(errorHead + "the two breakpoints on the derivative chromosome should come from different arms");
//			} 
			for (List<String> chrBreakpoints: breakpointsExceptDerChr) {
				if (!p.getChrArm(chrBreakpoints.get(0)).equals(p.getChrArm(chrBreakpoints.get(1)))) {
					validationMessageList.add(errorHead + "the two breakpoints on the different chromosome than the derivative chromosome or the other homologous copy of the derivative chromosome should come from the same arm");
					break;
				} else if (areUnbalancedResolution(chrBreakpoints.get(0), chrBreakpoints.get(1))) {
					validationMessageList.add(errorHead + "the two breakpoints on the different chromosome than the derivative chromosome or the other homologous copy of the derivative chromosome should be in the same level of resolution if both are in the same band");
					break;
				}
			} 
		}		
		return validationMessageList;
	}
	
	public static List<String> getGenericSubeventValidationError(Event e) {
		List<String> validationMessageList = new ArrayList<String>();
		String eventCode = e.getEventCode();
		String errorHead = "Subevent \"" + eventCode + "\": ";
		List<String> chrList = e.getChrList();
		List<List<String>> breakpoints = e.getBreakpoints();
		List<List<String>> breakpointsFullName = new ArrayList<>();
		
		if (chrList.size() != breakpoints.size()) {
			String errorMsg = errorHead + "The number of \";\" in its chromosome list is not equal to that in its breakpoints list";
			String fixMsg = sizeFixSubeventMsg(e);
			if (!fixMsg.isEmpty()) {
				errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
			}
			validationMessageList.add(errorMsg);
			return validationMessageList;
		} else {
			breakpointsFullName = e.getBreakpointsFullName(chrList, breakpoints); 
		}
		
		switch (e.getNature()) {
			case "add": {		    	
		    	if (chrList.size() != 1) {
					validationMessageList.add(errorHead + "the cytogenetic \"add\" subevent can happen on a single chromosome, but there are " + chrList.size() + " chromosomes");
				} else if (!isValidChr(chrList.get(0))) {
					validationMessageList.add(errorHead + "\"" + chrList.get(0) + "\" is not a valid chromosome");
				} else if (breakpoints.get(0).size() != 1) {
					validationMessageList.add(errorHead + "the cytogenetic \"add\" subevent can involve only one breakpoint, but there are " + breakpoints.get(0).size() + " breakpoints");
				} else if (!isValidChrBreakpoint(breakpointsFullName.get(0).get(0))) {
					String errorMsg = errorHead + "\"" + breakpointsFullName.get(0).get(0) + "\" is not a valid chromosome breakpoint";
					String fixMsg = fixChrBreakpoint(breakpointsFullName.get(0).get(0));
					if (!fixMsg.isEmpty()) {
						errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
					}
					validationMessageList.add(errorMsg);					
				} else {
				}
				return validationMessageList;		    	
		    }
			case "del": {
		    	if (chrList.size() != 1) {
		    		String errorMsg = errorHead + "the cytogenetic \"del\" subevent can happen on a single chromosome, but there are " + chrList.size() + " chromosomes";
		    		if (chrList.size() == 2) {
						String fixMsg = sizeFixSubeventMsg(e);
						if (!fixMsg.isEmpty()) {
							errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
						}
					}
					validationMessageList.add(errorMsg);
				} else if (!isValidChr(chrList.get(0))) {
					validationMessageList.add(errorHead + "\"" + chrList.get(0) + "\" is not a valid chromosome");
				} else if (breakpoints.get(0).size() != 1 && breakpoints.get(0).size() != 2) {
					validationMessageList.add(errorHead + "the cytogenetic \"del\" subevent can involve either one or two breakpoints, but there are " + breakpoints.get(0).size() + " breakpoints");
				} else if (breakpoints.get(0).size() == 1) {
					if (!isValidChrBreakpoint(breakpointsFullName.get(0).get(0))) {
						String errorMsg = errorHead + "\"" + breakpointsFullName.get(0).get(0) + "\" is not a valid chromosome breakpoint";
						String fixMsg = fixChrBreakpoint(breakpointsFullName.get(0).get(0));
						if (!fixMsg.isEmpty()) {
							errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
						}
						validationMessageList.add(errorMsg);						
					} else {
					}
				} else {
					ParseEvent p = new ParseEvent();
					String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
					String chrBreakpoint1 = breakpointsFullName.get(0).get(1); 
					if (!isValidChrBreakpoint(chrBreakpoint0) || !isValidChrBreakpoint(chrBreakpoint1)) {
						if (!isValidChrBreakpoint(chrBreakpoint0)) {
							String errorMsg = errorHead + "\"" + chrBreakpoint0 + "\" is not a valid chromosome breakpoint";
							String fixMsg = fixChrBreakpoint(chrBreakpoint0);
							if (!fixMsg.isEmpty()) {
								errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
							}
							validationMessageList.add(errorMsg);							
						}
						if (!isValidChrBreakpoint(chrBreakpoint1)) {
							String errorMsg = errorHead + "\"" + chrBreakpoint1 + "\" is not a valid chromosome breakpoint";
							String fixMsg = fixChrBreakpoint(chrBreakpoint1);
							if (!fixMsg.isEmpty()) {
								errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
							}
							validationMessageList.add(errorMsg);
						}
					} else if (!p.getChrArm(chrBreakpoint0).equals(p.getChrArm(chrBreakpoint1))) {
						validationMessageList.add(errorHead + "the two breakpoints in an interstitial deletion should come from the same arm");
					} else if (areUnbalancedResolution(chrBreakpoint0, chrBreakpoint1)) {
						validationMessageList.add(errorHead + "the two breakpoints in an interstitial deletion should be in the same level of resolution if both are in the same band");
					} else if (chrBreakpoint0.compareTo(chrBreakpoint1) > 0) {
						validationMessageList.add(errorHead + "the breakpoint more proximal to the centromere should be specified first, maybe you want to try \"" + flipBreakpoints(eventCode) + "\"");
					} else {
					}
				}
				return validationMessageList;
			}				
			case "dup": {
				if (chrList.size() != 1) {
					String errorMsg = errorHead + "the cytogenetic \"dup\" subevent can happen on a single chromosome, but there are " + chrList.size() + " chromosomes";
					if (chrList.size() == 2) {
						String fixMsg = sizeFixSubeventMsg(e);
						if (!fixMsg.isEmpty()) {
							errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
						}
					}
					validationMessageList.add(errorMsg);
				} else if (!isValidChr(chrList.get(0))) {
					validationMessageList.add(errorHead + "\"" + chrList.get(0) + "\" is not a valid chromosome");
				} else if (breakpoints.get(0).size() != 2) {
					if (breakpoints.get(0).size() == 1) {
						validationMessageList.add(errorHead + "the cytogenetic \"dup\" subevent must involve two breakpoints, but there is only one breakpoint");
					} else {
						validationMessageList.add(errorHead + "the cytogenetic \"dup\" subevent must involve two breakpoints, but there are " + breakpoints.get(0).size() + " breakpoints");
					}						
				} else {
					ParseEvent p = new ParseEvent();
					String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
					String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
					if (!isValidChrBreakpoint(chrBreakpoint0) || !isValidChrBreakpoint(chrBreakpoint1)) {
						if (!isValidChrBreakpoint(chrBreakpoint0)) {
							String errorMsg = errorHead + "\"" + chrBreakpoint0 + "\" is not a valid chromosome breakpoint";
							String fixMsg = fixChrBreakpoint(chrBreakpoint0);
							if (!fixMsg.isEmpty()) {
								errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
							}
							validationMessageList.add(errorMsg);							
						}
						if (!isValidChrBreakpoint(chrBreakpoint1)) {
							String errorMsg = errorHead + "\"" + chrBreakpoint1 + "\" is not a valid chromosome breakpoint";
							String fixMsg = fixChrBreakpoint(chrBreakpoint1);
							if (!fixMsg.isEmpty()) {
								errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
							}
							validationMessageList.add(errorMsg);
						}
					} else if (!p.getChrArm(chrBreakpoint0).equals(p.getChrArm(chrBreakpoint1))) {
						validationMessageList.add(errorHead + "the two breakpoints in a duplication should come from the same arm");
					} else if (areUnbalancedResolution(chrBreakpoint0, chrBreakpoint1)) {
						validationMessageList.add(errorHead + "the two breakpoints in a duplication should be in the same level of resolution if both are in the same band");
					} else {
					}
				}
				return validationMessageList;				
			}			
			case "trp": {				
				if (chrList.size() != 1) {
					String errorMsg = errorHead + "the cytogenetic \"trp\" subevent can happen on a single chromosome, but there are " + chrList.size() + " chromosomes";
					if (chrList.size() == 2) {
						String fixMsg = sizeFixSubeventMsg(e);
						if (!fixMsg.isEmpty()) {
							errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
						}
					}
					validationMessageList.add(errorMsg);
				} else if (!isValidChr(chrList.get(0))) {
					validationMessageList.add(errorHead + "\"" + chrList.get(0) + "\" is not a valid chromosome");
				} else if (breakpoints.get(0).size() != 2) {
					if (breakpoints.get(0).size() == 1) {
						validationMessageList.add(errorHead + "the cytogenetic \"trp\" subevent must involve two breakpoints, but there is only one breakpoint");
					} else {
						validationMessageList.add(errorHead + "the cytogenetic \"trp\" subevent must involve two breakpoints, but there are " + breakpoints.get(0).size() + " breakpoints");
					}
				} else {
					ParseEvent p = new ParseEvent();
					String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
					String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
					if (!isValidChrBreakpoint(chrBreakpoint0) || !isValidChrBreakpoint(chrBreakpoint1)) {
						if (!isValidChrBreakpoint(chrBreakpoint0)) {
							String errorMsg = errorHead + "\"" + chrBreakpoint0 + "\" is not a valid chromosome breakpoint";
							String fixMsg = fixChrBreakpoint(chrBreakpoint0);
							if (!fixMsg.isEmpty()) {
								errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
							}
							validationMessageList.add(errorMsg);							
						}
						if (!isValidChrBreakpoint(chrBreakpoint1)) {
							String errorMsg = errorHead + "\"" + chrBreakpoint1 + "\" is not a valid chromosome breakpoint";
							String fixMsg = fixChrBreakpoint(chrBreakpoint1);
							if (!fixMsg.isEmpty()) {
								errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
							}
							validationMessageList.add(errorMsg);
						}
					} else if (!p.getChrArm(chrBreakpoint0).equals(p.getChrArm(chrBreakpoint1))) {
						validationMessageList.add(errorHead + "the two breakpoints in a triplication should come from the same arm");
					} else if (areUnbalancedResolution(chrBreakpoint0, chrBreakpoint1)) {
						validationMessageList.add(errorHead + "the two breakpoints in a triplication should be in the same level of resolution if both are in the same band");
					} else {
					}
				}
				return validationMessageList;				
			}
			case "qdp": {		
				if (chrList.size() != 1) {
					String errorMsg = errorHead + "the cytogenetic \"qdp\" subevent can happen on a single chromosome, but there are " + chrList.size() + " chromosomes";
					if (chrList.size() == 2) {
						String fixMsg = sizeFixSubeventMsg(e);
						if (!fixMsg.isEmpty()) {
							errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
						}
					}
					validationMessageList.add(errorMsg);
				} else if (!isValidChr(chrList.get(0))) {
					validationMessageList.add(errorHead + "\"" + chrList.get(0) + "\" is not a valid chromosome");
				} else if (breakpoints.get(0).size() != 2) {
					if (breakpoints.get(0).size() == 1) {
						validationMessageList.add(errorHead + "the cytogenetic \"qdp\" subevent must involve two breakpoints, but there is only one breakpoint");
					} else {
						validationMessageList.add(errorHead + "the cytogenetic \"qdp\" subevent must involve two breakpoints, but there are " + breakpoints.get(0).size() + " breakpoints");
					}
				} else {
					ParseEvent p = new ParseEvent();
					String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
					String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
					if (!isValidChrBreakpoint(chrBreakpoint0) || !isValidChrBreakpoint(chrBreakpoint1)) {
						if (!isValidChrBreakpoint(chrBreakpoint0)) {
							String errorMsg = errorHead + "\"" + chrBreakpoint0 + "\" is not a valid chromosome breakpoint";
							String fixMsg = fixChrBreakpoint(chrBreakpoint0);
							if (!fixMsg.isEmpty()) {
								errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
							}
							validationMessageList.add(errorMsg);							
						}
						if (!isValidChrBreakpoint(chrBreakpoint1)) {
							String errorMsg = errorHead + "\"" + chrBreakpoint1 + "\" is not a valid chromosome breakpoint";
							String fixMsg = fixChrBreakpoint(chrBreakpoint1);
							if (!fixMsg.isEmpty()) {
								errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
							}
							validationMessageList.add(errorMsg);
						}
					} else if (!p.getChrArm(chrBreakpoint0).equals(p.getChrArm(chrBreakpoint1))) {
						validationMessageList.add(errorHead + "the two breakpoints in a quadruplication should come from the same arm");
					} else if (areUnbalancedResolution(chrBreakpoint0, chrBreakpoint1)) {
						validationMessageList.add(errorHead + "the two breakpoints in a quadruplication should be in the same level of resolution if both are in the same band");
					} else {
					}
				}
				return validationMessageList;				
			}
			case "hsr": {
				if (chrList.size() > 2) {
					String errorMsg = errorHead + "the cytogenetic \"hsr\" subevent can happen on a single chromosome or two chromosomes, but there are " + chrList.size() + " chromosomes";
					String fixMsg = sizeFixSubeventMsg(e);
					if (!fixMsg.isEmpty()) {
						errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
					}
					validationMessageList.add(errorMsg);
				} else if (!isValidChrList(chrList)) {					
					for (int i = 0; i < chrList.size(); i++) {
						if (!isValidChr(chrList.get(i))) {
							validationMessageList.add(errorHead + "\"" + chrList.get(i) + "\" is not a valid chromosome");
						}
					}
					String fixMsg = sizeFixSubeventMsg(e);
					if (!fixMsg.isEmpty()) {
						validationMessageList.add(errorHead + "maybe you want to try \"" + fixMsg + "\"");
					}
				} else if (!isValidBreakpointsWithFixedNumBreakpointsInEachChr(breakpoints, 1)) {
					int i = 0;
					for (List<String> pointList: breakpoints) {
						if (pointList.size() != 1) {
							validationMessageList.add(errorHead + "the cytogenetic \"hsr\" subevent can involve only one breakpoint for each chromosome, but there are " + breakpoints.get(i).size() + " breakpoints for chromosome \"" + chrList.get(i) + "\"");
						}
						i++;
					}
					String fixMsg = sizeFixSubeventMsg(e);
					if (!fixMsg.isEmpty()) {
						validationMessageList.add(errorHead + "maybe you want to try \"" + fixMsg + "\"");
					}
				} else if (!isValidChrBreakpointList(breakpointsFullName)) {
					for (int i = 0; i < breakpointsFullName.size(); i++) {						
						if (!isValidChrBreakpoint(breakpointsFullName.get(i).get(0))) {
							String errorMsg = errorHead + "\"" + breakpointsFullName.get(i).get(0) + "\" is not a valid chromosome breakpoint";
							String fixMsg = fixChrBreakpoint(breakpointsFullName.get(i).get(0));
							if (!fixMsg.isEmpty()) {
								errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
							}
							validationMessageList.add(errorMsg);							
						}
					}
					String fixMsg = sizeFixSubeventMsg(e);
					if (!fixMsg.isEmpty()) {
						validationMessageList.add(errorHead + "maybe you want to try \"" + fixMsg + "\"");
					}
				} else {
				}
				return validationMessageList;				
			}
			case "ins": {				
				if (chrList.size() != 1 && chrList.size() != 2) {
					String errorMsg = errorHead + "the cytogenetic \"ins\" subevent can happen on a single chromosome or two chromosomes, but there are " + chrList.size() + " chromosomes";
					if (chrList.size() == 3) {
						String fixMsg = sizeFixSubeventMsg(e);
						if (!fixMsg.isEmpty()) {
							errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
						}
					}
					validationMessageList.add(errorMsg);
				} else if (chrList.size() == 1) {
					if (!isValidChr(chrList.get(0))) {
						String errorMsg = errorHead + "\"" + chrList.get(0) + "\" is not a valid chromosome";
						String fixMsg = sizeFixSubeventMsg(e);
						if (!fixMsg.isEmpty()) {
							errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
						}
						validationMessageList.add(errorMsg);
					} else if (breakpoints.get(0).size() != 3) {
						if (breakpoints.get(0).size() == 1) {
							validationMessageList.add(errorHead + "the cytogenetic \"ins\" subevent must involve three breakpoints when there is only one chromosome involved, but there is only one breakpoint");
						} else {
							validationMessageList.add(errorHead + "the cytogenetic \"ins\" subevent must involve three breakpoints when there is only one chromosome involved, but there are " + breakpoints.get(0).size() + " breakpoints");
						}
					} else if (!isValidChrBreakpointList(breakpointsFullName)) {
						List<String> chrBreakpoints = breakpointsFullName.get(0);
						for (int i = 0; i < chrBreakpoints.size(); i++) {
							if (!isValidChrBreakpoint(chrBreakpoints.get(i))) {
								String errorMsg = errorHead + "\"" + chrBreakpoints.get(i) + "\" is not a valid chromosome breakpoint";
								String fixMsg = fixChrBreakpoint(chrBreakpoints.get(i));
								if (!fixMsg.isEmpty()) {
									errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
								}
								validationMessageList.add(errorMsg);
							}							
						}
						String fixMsg = sizeFixSubeventMsg(e);
						if (!fixMsg.isEmpty()) {
							validationMessageList.add(errorHead + "maybe you want to try \"" + fixMsg + "\"");
						}
					} else {
						ParseEvent p = new ParseEvent();
						String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
						String chrBreakpoint2 = breakpointsFullName.get(0).get(2);
						if (!p.getChrArm(chrBreakpoint1).equals(p.getChrArm(chrBreakpoint2))) {
							validationMessageList.add(errorHead + "the inserted segment should come from the same arm of a chromosome");
						} else if (areUnbalancedResolution(chrBreakpoint1, chrBreakpoint2)) {
							validationMessageList.add(errorHead + "the two breakpoints of the inserted segment should be in the same level of resolution if both are in the same band");
						} else {
						}
					}
				} else {
					if (!isValidChr(chrList.get(0)) || !isValidChr(chrList.get(1))) {
						if (!isValidChr(chrList.get(0))) {
							validationMessageList.add(errorHead + "\"" + chrList.get(0) + "\" is not a valid chromosome");
						}
						if (!isValidChr(chrList.get(1))) {
							validationMessageList.add(errorHead + "\"" + chrList.get(1) + "\" is not a valid chromosome");
						}
						String fixMsg = sizeFixSubeventMsg(e);
						if (!fixMsg.isEmpty()) {
							validationMessageList.add(errorHead + "maybe you want to try \"" + fixMsg + "\"");
						}
					} else if (breakpoints.get(0).size() != 1 || breakpoints.get(1).size() != 2) {
						String errorMsg = errorHead + "the cytogenetic insertion between two chromosomes must involve only one breakpoint for the chromosome  where the inserted segment is inserted, which is specified first, and two breakpoints on the chromosome where the inserted segment is coming from";
						String fixMsg = sizeFixSubeventMsg(e);
						if (!fixMsg.isEmpty()) {
							errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
						}
						validationMessageList.add(errorMsg);
					} else if (!isValidChrBreakpointList(breakpointsFullName)) {
						for (List<String> chrBreakpoints: breakpointsFullName) {
							for (String chrBreakpoint: chrBreakpoints) {
								if (!isValidChrBreakpoint(chrBreakpoint)) {
									String errorMsg = errorHead + "\"" + chrBreakpoint + "\" is not a valid chromosome breakpoint";
									String fixMsg = fixChrBreakpoint(chrBreakpoint);
									if (!fixMsg.isEmpty()) {
										errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
									}
									validationMessageList.add(errorMsg);
								}
							}
						}
						String fixMsg = sizeFixSubeventMsg(e);
						if (!fixMsg.isEmpty()) {
							validationMessageList.add(errorHead + "maybe you want to try \"" + fixMsg + "\"");
						}
					} else {
						ParseEvent p = new ParseEvent();
						String chrBreakpoint1 = breakpointsFullName.get(1).get(0);
						String chrBreakpoint2 = breakpointsFullName.get(1).get(1);
						if (!p.getChrArm(chrBreakpoint1).equals(p.getChrArm(chrBreakpoint2))) {
							validationMessageList.add(errorHead + "the inserted segment should come from the same arm of a chromosome");
						} else if (areUnbalancedResolution(chrBreakpoint1, chrBreakpoint2)) {
							validationMessageList.add(errorHead + "the two breakpoints of the inserted segment should be in the same level of resolution if both are in the same band");
						} else {
						}
					}
				}
				return validationMessageList;				
			}
			case "inv": {								
				if (chrList.size() != 1) {
					String errorMsg = errorHead + "the cytogenetic \"inv\" subevent can happen on a single chromosome, but there are " + chrList.size() + " chromosomes";
					if (chrList.size() == 2) {
						String fixMsg = sizeFixSubeventMsg(e);
						if (!fixMsg.isEmpty()) {
							errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
						}
					}
					validationMessageList.add(errorMsg);
				} else if (!isValidChr(chrList.get(0))) {
					validationMessageList.add(errorHead + "\"" + chrList.get(0) + "\" is not a valid chromosome");
				} else if (breakpoints.get(0).size() != 2) {
					if (breakpoints.get(0).size() == 1) {
						validationMessageList.add(errorHead + "the cytogenetic \"inv\" subevent must involve two breakpoints, but there is only one breakpoint");
					} else {
						validationMessageList.add(errorHead + "the cytogenetic \"inv\" subevent must involve two breakpoints, but there are " + breakpoints.get(0).size() + " breakpoints");
					}
				} else if (!isValidChrBreakpoint(breakpointsFullName.get(0).get(0)) || !isValidChrBreakpoint(breakpointsFullName.get(0).get(1))) {
					String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
					String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
					if (!isValidChrBreakpoint(chrBreakpoint0)) {
						String errorMsg = errorHead + "\"" + chrBreakpoint0 + "\" is not a valid chromosome breakpoint";
						String fixMsg = fixChrBreakpoint(chrBreakpoint0);
						if (!fixMsg.isEmpty()) {
							errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
						}
						validationMessageList.add(errorMsg);
					}
					if (!isValidChrBreakpoint(chrBreakpoint1)) {
						String errorMsg = errorHead + "\"" + chrBreakpoint1 + "\" is not a valid chromosome breakpoint";
						String fixMsg = fixChrBreakpoint(chrBreakpoint1);
						if (!fixMsg.isEmpty()) {
							errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
						}
						validationMessageList.add(errorMsg);
					}
				} else if (areUnbalancedResolution(breakpointsFullName.get(0).get(0), breakpointsFullName.get(0).get(1))) {
					validationMessageList.add(errorHead + "the two breakpoints of the inverted segment should be in the same level of resolution if both are in the same band");
				} else if (breakpointsFullName.get(0).get(0).compareTo(breakpointsFullName.get(0).get(1)) > 0) {
					validationMessageList.add(errorHead + "the breakpoint more proximal to the centromere should be specified first, maybe you want to try \"" + flipBreakpoints(eventCode) + "\"");
				} else {
				}
				return validationMessageList;				
			}
			case "t": {	
				if (chrList.size() != 2) {
					String errorMsg;
					if (chrList.size() == 1) {
						errorMsg = errorHead + "the cytogenetic \"t\" subevent must involve only two chromosomes, but there is only one chromosome";
						String fixMsg = sizeFixSubeventMsg(e);
						if (!fixMsg.isEmpty()) {
							errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
						}
					} else {
						errorMsg = errorHead + "the cytogenetic \"t\" subevent must involve only two chromosomes, but there are " + chrList.size() + " chromosomes";
					}					
					validationMessageList.add(errorMsg);
				} else if (!isValidChrList(chrList)) {
					for (int i = 0; i < chrList.size(); i++) {
						if (!isValidChr(chrList.get(i))) {
							validationMessageList.add(errorHead + "\"" + chrList.get(i) + "\" is not a valid chromosome");
						}
					}
					String fixMsg = sizeFixSubeventMsg(e);
					if (!fixMsg.isEmpty()) {
						validationMessageList.add(errorHead + "maybe you want to try \"" + fixMsg + "\"");
					}
				} else if (!isValidBreakpointsWithFixedNumBreakpointsInEachChr(breakpoints, 1)) {
					String errorMsg = errorHead + "the cytogenetic \"t\" subevent must involve one breakpoint for all chromosomes";
					String fixMsg = sizeFixSubeventMsg(e);
					if (!fixMsg.isEmpty()) {
						errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
					}
					validationMessageList.add(errorMsg);					
				} else if (!isValidChrBreakpointList(breakpointsFullName)) {
					for (List<String> chrBreakpoints: breakpointsFullName) {
						for (String chrBreakpoint: chrBreakpoints) {
							if (!isValidChrBreakpoint(chrBreakpoint)) {
								String errorMsg = errorHead + "\"" + chrBreakpoint + "\" is not a valid chromosome breakpoint";
								String fixMsg = fixChrBreakpoint(chrBreakpoint);
								if (!fixMsg.isEmpty()) {
									errorMsg += ", maybe you want to try \"" + fixMsg + "\""; 
								}
								validationMessageList.add(errorMsg);
							}
						}
					}
					String fixMsg = sizeFixSubeventMsg(e);
					if (!fixMsg.isEmpty()) {
						validationMessageList.add(errorHead + "maybe you want to try \"" + fixMsg + "\"");
					}
				} else {
				}
				return validationMessageList;				
			}
			default: {
				validationMessageList.add(errorHead + "\"" + e.getNature() + "\" is not an allowed cytogenetic subevent");
				return validationMessageList;
			}
		}
	}
	
	private static String flipBreakpoints(String eventCode) {
		String breakpointsString = getBreakpointsString(eventCode);
		List<String> breakpointList = getAllBreakpointsFromBreakpointsString(breakpointsString);
		int offset0 = eventCode.indexOf(")");
		int breakpointsLeftParentheses = eventCode.indexOf("(", offset0 + 1);
		int breakpointsRightParentheses = eventCode.indexOf(")", offset0 + 1);
		return new StringBuilder(eventCode).replace(breakpointsLeftParentheses + 1, breakpointsRightParentheses, breakpointList.get(1) + breakpointList.get(0)).toString();
	}
	
	private static String sizeFixMsg(Event e) {
		switch (e.getNature()) {
		    case "add": return fixAddIdicHsrI(e);
			case "del": return fixDel(e);
			case "dic":	return fixDicRob(e);
			case "idic": return fixAddIdicHsrI(e);	
			case "dup": return fixDupTrpQdp(e);
			case "trp": return fixDupTrpQdp(e);
			case "qdp": return fixDupTrpQdp(e);
			case "hsr": return fixAddIdicHsrI(e);
			case "ins": return fixIns(e);
			case "inv": return fixInv(e);
			case "i": return fixAddIdicHsrI(e);
			case "r": return fixR(e);
			case "tas": return fixTas(e);
			case "t": return fixT(e);
			case "trc": return fixTrc(e);
			case "rob": return fixDicRob(e);
			default: return "";
		}		
	}
	
	private static String sizeFixSubeventMsg(Event e) {
		switch (e.getNature()) {
			case "add": return fixAddIdicHsrI(e);
			case "del": return fixDel(e);
			case "dup": return fixDupTrpQdp(e);
			case "trp": return fixDupTrpQdp(e);
			case "qdp": return fixDupTrpQdp(e);
			case "hsr": return fixHsrTSubevent(e);
			case "ins": return fixIns(e);
			case "inv": return fixInv(e);
			case "t": return fixHsrTSubevent(e);
			default: return "";		
		}
	}
	
	private static String fixAddIdicHsrI(Event e) {
		Event eCopy = new Event(e);
		List<String> chrList = eCopy.getChrList();
		if (eCopy.getChrList().size() == 2 && isNumericChrOfLengthOneList(chrList) && eCopy.getBreakpoints().size() == 1 && eCopy.getBreakpoints().get(0).size() == 1) {
			String chr1 = chrList.remove(1);
			String chr0 = chrList.remove(0);
			chrList.add(chr0 + chr1);
			eCopy.setEventCode(eCopy.getEventCode().replaceAll(";", ""));
			if (Validator.isValidEvent(eCopy)) {
				return eCopy.getEventCode();
			} else {
				return "";
			}
		} else {
			return "";
		}
	}
	
	private static String fixHsrTSubevent(Event e) {
		Event eCopy = new Event(e);
		String breakpointsString = getBreakpointsString(eCopy.getEventCode());
		int numOfBreakpoints = countBreakpoints(breakpointsString);
		if (numOfBreakpoints > 2) {
			return "";
		} else if (numOfBreakpoints == 1 && e.getNature().equals("t")) {
			return "";
		} else {
			List<String> chrList = eCopy.getChrList();
			if (!isValidBreakpointsWithFixedNumBreakpointsInEachChr(eCopy.getBreakpoints(), 1)) {
				String chrListStringWithoutSemicolon = getChrListString(eCopy.getEventCode()).replaceAll(";", "");
				if (chrList.size() <= numOfBreakpoints * 2) {
					
					int offsetSize = numOfBreakpoints - 1;
					List<Integer> offsetList = new ArrayList<>();
					int offset = 0;
					for (int i = 0; i < offsetSize; i++) {
						offset += 1;
						offsetList.add(offset);
					}
					List<String> breakpointList = getAllBreakpointsFromBreakpointsString(breakpointsString);
					List<List<String>> breakpoints = new ArrayList<>();
					breakpoints.add(new ArrayList<String>());
					breakpoints.get(0).add(breakpointList.get(0));
					for (int i = 1; i <= offsetSize; i++) {
						breakpoints.add(new ArrayList<String>());
						breakpoints.get(i).add(breakpointList.get(i));
					}
					eCopy.setBreakpoints(breakpoints);
					
					Collections.reverse(offsetList);
					String breakpointsStringNoSemicolon = breakpointsString.replaceAll(";", "");		
					List<Integer> armIndexes = findArmIndexesInBreakpointsString(breakpointsStringNoSemicolon);
					StringBuilder breakpointsStringRevised = new StringBuilder(breakpointsStringNoSemicolon); 
					for (int i = 0; i < offsetSize; i++) {
						breakpointsStringRevised.insert(armIndexes.get(offsetList.get(i)), ";");
					}
					String eventCode = eCopy.getEventCode();
					int offset0 = eventCode.indexOf(")");
					int breakpointsLeftParentheses = eventCode.indexOf("(", offset0 + 1);
					int breakpointsRightParentheses = eventCode.indexOf(")", offset0 + 1);
					eCopy.setEventCode(new StringBuilder(eventCode).replace(breakpointsLeftParentheses + 1, breakpointsRightParentheses, breakpointsStringRevised.toString()).toString());
					
					if (Validator.isValidGenericSubevent(eCopy)) {
						return eCopy.getEventCode();
					} else {
						return divideHsrTSubeventChrStringIntoList(eCopy, chrListStringWithoutSemicolon, numOfBreakpoints);
					}
					
				} else {
					return "";
				}
			} else {
				if (Validator.isValidGenericSubevent(eCopy)) {
					return "";
				} else {									
					if (chrList.size() <= numOfBreakpoints * 2) {
						String chrListStringWithoutSemicolon = getChrListString(eCopy.getEventCode()).replaceAll(";", "");
						return divideHsrTSubeventChrStringIntoList(eCopy, chrListStringWithoutSemicolon, numOfBreakpoints);
					} else {
						return "";
					}
				}
			}
		}
		
	}
	
	private static String fixDupTrpQdp(Event e) {
		Event eCopy = new Event(e);	
		if (eCopy.getChrList().size() == 1 && eCopy.getBreakpoints().size() == 2 && eCopy.getBreakpoints().get(0).size() == 1 && eCopy.getBreakpoints().get(1).size() == 1) {			
			removeSemicolonInBreakpointsString(eCopy);
			if (Validator.isValidEvent(eCopy)) {
				return eCopy.getEventCode();
			} else {
				return "";
			}
		} else if (eCopy.getChrList().size() == 2) {
			List<String> chrList = eCopy.getChrList();				
			if (isNumericChrOfLengthOneList(chrList)) {				
				if (eCopy.getBreakpoints().size() != 1 && eCopy.getBreakpoints().size() != 2) {
					return "";
				} else {
					if (eCopy.getBreakpoints().size() == 2) {
						if (eCopy.getBreakpoints().get(0).size() == 1 && eCopy.getBreakpoints().get(1).size() == 1) {
							removeSemicolonInBreakpointsString(eCopy);
						} else {
							return "";
						}						
					} else {
						if (eCopy.getBreakpoints().get(0).size() != 2) {
							return "";
						}
					}					
					String chr1 = chrList.remove(1);
					String chr0 = chrList.remove(0);
					chrList.add(chr0 + chr1);
					eCopy.setEventCode(eCopy.getEventCode().replaceAll(";", ""));
					if (Validator.isValidEvent(eCopy)) {
						return eCopy.getEventCode();
					} else {
						return "";
					}
				}				
			} else {
				return "";
			}			
		} else {
			return "";
		}
	}
	
	private static String fixInv(Event e) {
		Event eCopy = new Event(e);	
		if (eCopy.getChrList().size() == 1 && eCopy.getBreakpoints().size() == 2 && eCopy.getBreakpoints().get(0).size() == 1 && eCopy.getBreakpoints().get(1).size() == 1) {			
			removeSemicolonInBreakpointsString(eCopy);
			List<String> chrList = eCopy.getChrList();
			if (Validator.isValidEvent(eCopy)) {
				return eCopy.getEventCode();
			} else if (!isValidChr(chrList.get(0))){
				return "";
			} else {
				List<List<String>> breakpointsFullName = eCopy.getBreakpointsFullName(chrList, eCopy.getBreakpoints());
				String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
				String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
				if (isValidChrBreakpointList(breakpointsFullName) && chrBreakpoint0.compareTo(chrBreakpoint1) > 0) {
					return flipBreakpoints(eCopy.getEventCode());
				} else {
					return "";
				}
			}
		} else if (eCopy.getChrList().size() == 2) {
			List<String> chrList = eCopy.getChrList();				
			if (isNumericChrOfLengthOneList(chrList)) {				
				if (eCopy.getBreakpoints().size() != 1 && eCopy.getBreakpoints().size() != 2) {
					return "";
				} else {
					if (eCopy.getBreakpoints().size() == 2) {
						if (eCopy.getBreakpoints().get(0).size() == 1 && eCopy.getBreakpoints().get(1).size() == 1) {
							removeSemicolonInBreakpointsString(eCopy);
						} else {
							return "";
						}						
					} else {
						if (eCopy.getBreakpoints().get(0).size() != 2) {
							return "";
						}
					}					
					String chr1 = chrList.remove(1);
					String chr0 = chrList.remove(0);
					chrList.add(chr0 + chr1);
					eCopy.setEventCode(eCopy.getEventCode().replaceAll(";", ""));
					if (Validator.isValidEvent(eCopy)) {
						return eCopy.getEventCode();
					} else if (!isValidChr(chrList.get(0))) {
						return "";
					} else {
						List<List<String>> breakpointsFullName = eCopy.getBreakpointsFullName(chrList, eCopy.getBreakpoints());
						String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
						String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
						if (isValidChrBreakpointList(breakpointsFullName) && chrBreakpoint0.compareTo(chrBreakpoint1) > 0) {
							return flipBreakpoints(eCopy.getEventCode());
						} else {
							return "";
						}					
					}
				}				
			} else {
				return "";
			}			
		} else {
			return "";
		}
	}
	
	private static String fixDel(Event e) {
		Event eCopy = new Event(e);		
		if (eCopy.getChrList().size() == 1 && eCopy.getBreakpoints().size() == 2 && eCopy.getBreakpoints().get(0).size() == 1 && eCopy.getBreakpoints().get(1).size() == 1) {			
			removeSemicolonInBreakpointsString(eCopy);
			List<String> chrList = eCopy.getChrList();
			if (Validator.isValidEvent(eCopy)) {
				return eCopy.getEventCode();
			} else if (!isValidChr(chrList.get(0))){
				return "";
			} else {
				List<List<String>> breakpointsFullName = eCopy.getBreakpointsFullName(chrList, eCopy.getBreakpoints());
				String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
				String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
				ParseEvent p = new ParseEvent();
				if (isValidChrBreakpointList(breakpointsFullName) && p.getChrArm(chrBreakpoint0).equals(p.getChrArm(chrBreakpoint1)) && chrBreakpoint0.compareTo(chrBreakpoint1) > 0) {
					return flipBreakpoints(eCopy.getEventCode());
				} else {
					return "";
				}
			}
		} else if (eCopy.getChrList().size() == 2) {
			List<String> chrList = eCopy.getChrList();				
			if (isNumericChrOfLengthOneList(chrList)) {				
				if (eCopy.getBreakpoints().size() != 1 && eCopy.getBreakpoints().size() != 2) {
					return "";
				} else {
					if (eCopy.getBreakpoints().size() == 2) {
						if (eCopy.getBreakpoints().get(0).size() == 1 && eCopy.getBreakpoints().get(1).size() == 1) {
							removeSemicolonInBreakpointsString(eCopy);
						} else {
							return "";
						}						
					} else {
						if (eCopy.getBreakpoints().get(0).size() != 1 && eCopy.getBreakpoints().get(0).size() != 2) {
							return "";
						}
					} 					
					String chr1 = chrList.remove(1);
					String chr0 = chrList.remove(0);
					chrList.add(chr0 + chr1);
					eCopy.setEventCode(eCopy.getEventCode().replaceAll(";", ""));
					if (Validator.isValidEvent(eCopy)) {
						return eCopy.getEventCode();
					} else if (!isValidChr(chrList.get(0))) {
						return "";
					} else {
						if (eCopy.getBreakpoints().get(0).size() == 2) {
							List<List<String>> breakpointsFullName = eCopy.getBreakpointsFullName(chrList, eCopy.getBreakpoints());
							String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
							String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
							ParseEvent p = new ParseEvent();
							if (isValidChrBreakpointList(breakpointsFullName) && p.getChrArm(chrBreakpoint0).equals(p.getChrArm(chrBreakpoint1)) && chrBreakpoint0.compareTo(chrBreakpoint1) > 0) {
								return flipBreakpoints(eCopy.getEventCode());
							} else {
								return "";
							}
						} else {
							return "";
						}						
					}
				}				
			} else {
				return "";
			}			
		} else {
			return "";
		}
	}
	
	private static String fixIns(Event e) {
		Event eCopy = new Event(e);
		String breakpointsString = getBreakpointsString(eCopy.getEventCode());
		if (countBreakpoints(breakpointsString) != 3) {
			return "";
		} else {
			List<String> chrList = eCopy.getChrList();
			if (chrList.size() == 1) {
				if (eCopy.getBreakpoints().size() == 3) {
					removeSemicolonInBreakpointsString(eCopy);
					if (Validator.isValidEvent(eCopy)) {
						return eCopy.getEventCode();
					} else {
						eCopy = new Event(e);
						removeSecondSemicolonInBreakpointsString(eCopy);
						String incorrectChrS = eCopy.getChrList().get(0);
						return divideChrStringIntoList(eCopy, incorrectChrS);
					}
				} else if (eCopy.getBreakpoints().size() == 2) {
					if (eCopy.getBreakpoints().get(0).size() == 2 && eCopy.getBreakpoints().get(1).size() == 1) {
						removeSemicolonInBreakpointsString(eCopy);
						if (Validator.isValidEvent(eCopy)) {
							return eCopy.getEventCode();
						} else {
							eCopy = new Event(e);
							exchangeBreakpoints(eCopy);	
							removeSemicolonInBreakpointsString(eCopy);
							if (Validator.isValidEvent(eCopy)) {
								return eCopy.getEventCode();
							} else {
								insertOneSemicolonToBreakpoints(eCopy);
								String incorrectChrS = eCopy.getChrList().get(0);
								String fixMsg = divideChrStringIntoList(eCopy, incorrectChrS);
								if (!fixMsg.isEmpty()) {
									return fixMsg;
								} else {
									eCopy = new Event(e);								
									moveSemicolonPositionInBreakpoints(eCopy);
									return divideChrStringIntoList(eCopy, incorrectChrS);
								}								
							}
						}
					} else {
						if (!Validator.isValidEvent(eCopy)) {
							removeSemicolonInBreakpointsString(eCopy);
							if (Validator.isValidEvent(eCopy)) {
								return eCopy.getEventCode();
							} else {
								eCopy = new Event(e);
								String incorrectChrS = eCopy.getChrList().get(0);
								return divideChrStringIntoList(eCopy, incorrectChrS);
							}
						} else {
							return "";
						}						
					} // This is the case where eCopy.getBreakpoints().get(0).size() == 1 && eCopy.getBreakpoints().get(1).size() == 2, given that countBreakpoints(breakpointsString) == 3
				} else {
					insertOneSemicolonToBreakpoints(eCopy);
					String incorrectChrS = eCopy.getChrList().get(0);
					return divideChrStringIntoList(eCopy, incorrectChrS);
				} // This is the case where eCopy.getBreakpoints().size() == 1, given that countBreakpoints(breakpointsString) == 3
			} else if (chrList.size() == 2) {
				if (eCopy.getBreakpoints().size() == 1) {
					insertOneSemicolonToBreakpoints(eCopy);
					if (Validator.isValidEvent(eCopy)) {
						return eCopy.getEventCode();
					} else if (isNumericChrOfLengthOneList(chrList)) {
						eCopy = new Event(e);
						chrList = eCopy.getChrList();
						String chr1 = chrList.remove(1);
						String chr0 = chrList.remove(0);
						chrList.add(chr0 + chr1);
						eCopy.setEventCode(eCopy.getEventCode().replaceAll(";", ""));
						if (Validator.isValidEvent(eCopy)) {
							return eCopy.getEventCode();
						} else {
							return "";
						}
					} else {
						return "";
					}
				} else if (eCopy.getBreakpoints().size() == 3) {
					removeSecondSemicolonInBreakpointsString(eCopy);
					if (Validator.isValidEvent(eCopy)) {
						return eCopy.getEventCode();
					} else if (isNumericChrOfLengthOneList(chrList)) {
						eCopy = new Event(e);
						removeSemicolonInBreakpointsString(eCopy);
						chrList = eCopy.getChrList();
						String chr1 = chrList.remove(1);
						String chr0 = chrList.remove(0);
						chrList.add(chr0 + chr1);
						eCopy.setEventCode(eCopy.getEventCode().replaceAll(";", ""));
						if (Validator.isValidEvent(eCopy)) {
							return eCopy.getEventCode();
						} else {
							return "";
						}
					} else {
						return "";
					}
				} else {
					if (eCopy.getBreakpoints().get(0).size() == 2 && eCopy.getBreakpoints().get(1).size() == 1) {
						exchangeBreakpoints(eCopy);
						if (Validator.isValidEvent(eCopy)) {
							return eCopy.getEventCode();
						} else {
							exchangeChrs(eCopy);
							if (Validator.isValidEvent(eCopy)) {
								return eCopy.getEventCode();
							} else {
								eCopy = new Event(e);
								moveSemicolonPositionInBreakpoints(eCopy);
								if (Validator.isValidEvent(eCopy)) {
									return eCopy.getEventCode();
								} else {
									String chrListStringWithoutSemicolon = getChrListString(eCopy.getEventCode()).replaceAll(";", "");
									String msg = divideChrStringIntoList(eCopy, chrListStringWithoutSemicolon, 2);
									if (!msg.isEmpty()) {
										return msg;
									} else {
										eCopy = new Event(e);
										chrList = eCopy.getChrList();
										if (isNumericChrOfLengthOneList(chrList)) {
											removeSemicolonInBreakpointsString(eCopy);
											String chr1 = chrList.remove(1);
											String chr0 = chrList.remove(0);
											chrList.add(chr0 + chr1);
											eCopy.setEventCode(eCopy.getEventCode().replaceAll(";", ""));
											if (Validator.isValidEvent(eCopy)) {
												return eCopy.getEventCode();
											} else {
												return "";
											}
										} else {
											return "";
										}
									}
								}									
							}							
						}
					} else {
						if (!Validator.isValidEvent(eCopy) && isNumericChrOfLengthOneList(chrList)) {
							removeSemicolonInBreakpointsString(eCopy);
							chrList = eCopy.getChrList();
							String chr1 = chrList.remove(1);
							String chr0 = chrList.remove(0);
							chrList.add(chr0 + chr1);
							eCopy.setEventCode(eCopy.getEventCode().replaceAll(";", ""));
							if (Validator.isValidEvent(eCopy)) {
								return eCopy.getEventCode();
							} else {
								return "";
							}
						} else if (!Validator.isValidEvent(eCopy)) {
							String chrListStringWithoutSemicolon = getChrListString(eCopy.getEventCode()).replaceAll(";", "");
							return divideChrStringIntoList(eCopy, chrListStringWithoutSemicolon, 2);
						} else {
							return "";
						}
					} // This is the case where eCopy.getBreakpoints().get(0).size() == 1 && eCopy.getBreakpoints().get(1).size() == 2, given that countBreakpoints(breakpointsString) == 3					
				} // This is the case where eCopy.getBreakpoints().size() == 2, given that countBreakpoints(breakpointsString) == 3
			} else if (eCopy.getChrList().size() == 3) {
				if (eCopy.getBreakpoints().size() == 1) {					
					insertOneSemicolonToBreakpoints(eCopy);
					return dealWithChrListOfSizeThree(eCopy);
				} else if (eCopy.getBreakpoints().size() == 3) {
					removeSecondSemicolonInBreakpointsString(eCopy);
					return dealWithChrListOfSizeThree(eCopy);
				} else {
					if (eCopy.getBreakpoints().get(0).size() == 1 && eCopy.getBreakpoints().get(1).size() == 2) {
						return dealWithChrListOfSizeThree(eCopy);
					} else {
						exchangeBreakpoints(eCopy);
						String msg = dealWithChrListOfSizeThree(eCopy);
						if (!msg.isEmpty()) {
							return msg;
						} else {
							eCopy = new Event(e);
							moveSemicolonPositionInBreakpoints(eCopy);
							return dealWithChrListOfSizeThree(eCopy);
						}
					} // This is the case where eCopy.getBreakpoints().get(0).size() == 2 && eCopy.getBreakpoints().get(1).size() == 1, given that countBreakpoints(breakpointsString) == 3
				} // This is the case where eCopy.getBreakpoints().size() == 2, given that countBreakpoints(breakpointsString) == 3  	 			
			} else if (eCopy.getChrList().size() == 4 && isNumericChrOfLengthOneList(eCopy.getChrList())) {
				dealWithChrListOfSizeFour(eCopy);
				if (eCopy.getBreakpoints().size() == 1) {
					insertOneSemicolonToBreakpoints(eCopy);
					if (Validator.isValidEvent(eCopy)) {
						return eCopy.getEventCode();
					} else {
						return "";
					}
				} else if (eCopy.getBreakpoints().size() == 3) {
					removeSecondSemicolonInBreakpointsString(eCopy);
					if (Validator.isValidEvent(eCopy)) {
						return eCopy.getEventCode();
					} else {
						return "";
					}
				} else {
					if (eCopy.getBreakpoints().get(0).size() == 1 && eCopy.getBreakpoints().get(1).size() == 2) {
						if (Validator.isValidEvent(eCopy)) {
							return eCopy.getEventCode();
						} else {
							return "";
						}
					} else {
						exchangeBreakpoints(eCopy);
						if (Validator.isValidEvent(eCopy)) {
							return eCopy.getEventCode();
						} else {
							exchangeChrs(eCopy);
							if (Validator.isValidEvent(eCopy)) {
								return eCopy.getEventCode();
							} else {
								eCopy = new Event(e);
								dealWithChrListOfSizeFour(eCopy);
								moveSemicolonPositionInBreakpoints(eCopy);
								if (Validator.isValidEvent(eCopy)) {
									return eCopy.getEventCode();
								} else {
									return "";
								}
							}							
						}
					} // This is the case where eCopy.getBreakpoints().get(0).size() == 2 && eCopy.getBreakpoints().get(1).size() == 1, given that countBreakpoints(breakpointsString) == 3
				} // This is the case where eCopy.getBreakpoints().size() == 2, given that countBreakpoints(breakpointsString) == 3
			} else {
				return "";
			}		
		}
	}
	
	private static String fixDicRob(Event e) {
		Event eCopy = new Event(e);
		if (eCopy.getChrList().size() == 1 && eCopy.getBreakpoints().size() == 2 && eCopy.getBreakpoints().get(0).size() == 1 && eCopy.getBreakpoints().get(1).size() == 1) {
			String incorrectChrS = eCopy.getChrList().get(0);
			return divideChrStringIntoList(eCopy, incorrectChrS);
		} else if (eCopy.getChrList().size() == 2 && eCopy.getBreakpoints().size() == 1) {
			if (eCopy.getBreakpoints().get(0).size() == 2) {
				insertOneSemicolonToBreakpoints(eCopy);
				if (Validator.isValidEvent(eCopy)) {
					return eCopy.getEventCode();
				} else {
					return "";
				}
			} else {
				return "";
			}
		} else if (eCopy.getChrList().size() == 1 && eCopy.getBreakpoints().size() == 1) {
			if (eCopy.getBreakpoints().get(0).size() == 2) {
				insertOneSemicolonToBreakpoints(eCopy);
				String incorrectChrS = eCopy.getChrList().get(0);
				return divideChrStringIntoList(eCopy, incorrectChrS);
			} else {
				return "";
			}
		} else if (eCopy.getChrList().size() == 3) {
			if (eCopy.getBreakpoints().size() == 1 && eCopy.getBreakpoints().get(0).size() == 2) {
				insertOneSemicolonToBreakpoints(eCopy);
				return dealWithChrListOfSizeThree(eCopy);	
			} else if (eCopy.getBreakpoints().size() == 2 && eCopy.getBreakpoints().get(0).size() == 1 && eCopy.getBreakpoints().get(1).size() == 1) {
				return dealWithChrListOfSizeThree(eCopy);
			} else {
            	return "";
            }
		} else if (eCopy.getChrList().size() == 4 && isNumericChrOfLengthOneList(eCopy.getChrList())) {
			dealWithChrListOfSizeFour(eCopy);
			if (eCopy.getBreakpoints().size() == 1 && eCopy.getBreakpoints().get(0).size() == 2) {
				insertOneSemicolonToBreakpoints(eCopy);
				if (Validator.isValidEvent(eCopy)) {
					return eCopy.getEventCode();
				} else {
					return "";
				}
			} else if (eCopy.getBreakpoints().size() == 2 && eCopy.getBreakpoints().get(0).size() == 1 && eCopy.getBreakpoints().get(1).size() == 1) {
				if (Validator.isValidEvent(eCopy)) {
					return eCopy.getEventCode();
				} else {
					return "";
				}
			} else {
            	return "";
            }
		} else {
			return "";
		}
	}
	
	private static String fixR(Event e) {
		Event eCopy = new Event(e);
		String breakpointsString = getBreakpointsString(eCopy.getEventCode());
		int numOfBreakpoints = countBreakpoints(breakpointsString);
		
		if (numOfBreakpoints % 2 != 0) {
			return "";
		} else {
			if (!isValidBreakpointsWithFixedNumBreakpointsInEachChr(eCopy.getBreakpoints(), 2)) {
				List<String> chrList = eCopy.getChrList();				
				if (chrList.size() <= numOfBreakpoints) {
					
					int offsetSize = numOfBreakpoints / 2 - 1;
					List<Integer> offsetList = new ArrayList<>();
					int offset = 0;
					for (int i = 0; i < offsetSize; i++) {
						offset += 2;
						offsetList.add(offset);
					}
					List<String> breakpointList = getAllBreakpointsFromBreakpointsString(breakpointsString);
					List<List<String>> breakpoints = new ArrayList<>();
					breakpoints.add(new ArrayList<String>());
					breakpoints.get(0).add(breakpointList.get(0));
					breakpoints.get(0).add(breakpointList.get(1));
					for (int i = 1; i <= offsetSize; i++) {
						breakpoints.add(new ArrayList<String>());
						breakpoints.get(i).add(breakpointList.get(2 * i));
						breakpoints.get(i).add(breakpointList.get(2 * i + 1));
					}
					eCopy.setBreakpoints(breakpoints);
					
					Collections.reverse(offsetList);
					String breakpointsStringNoSemicolon = breakpointsString.replaceAll(";", "");		
					List<Integer> armIndexes = findArmIndexesInBreakpointsString(breakpointsStringNoSemicolon);
					StringBuilder breakpointsStringRevised = new StringBuilder(breakpointsStringNoSemicolon); 
					for (int i = 0; i < offsetSize; i++) {
						breakpointsStringRevised.insert(armIndexes.get(offsetList.get(i)), ";");
					}
					String eventCode = eCopy.getEventCode();
					int offset0 = eventCode.indexOf(")");
					int breakpointsLeftParentheses = eventCode.indexOf("(", offset0 + 1);
					int breakpointsRightParentheses = eventCode.indexOf(")", offset0 + 1);
					eCopy.setEventCode(new StringBuilder(eventCode).replace(breakpointsLeftParentheses + 1, breakpointsRightParentheses, breakpointsStringRevised.toString()).toString());
					
					if (Validator.isValidEvent(eCopy)) {
						return eCopy.getEventCode();
					} else {
						String chrListStringWithoutSemicolon = getChrListString(eCopy.getEventCode()).replaceAll(";", "");
						return divideChrStringIntoList(eCopy, chrListStringWithoutSemicolon, numOfBreakpoints / 2);
					}
					
				} else {
					return "";
				}
				
			} else {
				
				if (Validator.isValidEvent(eCopy)) {
					return "";
				} else {
					List<String> chrList = eCopy.getChrList();									
					if (chrList.size() <= numOfBreakpoints) {
						String chrListStringWithoutSemicolon = getChrListString(eCopy.getEventCode()).replaceAll(";", "");
						return divideChrStringIntoList(eCopy, chrListStringWithoutSemicolon, numOfBreakpoints / 2);
					} else {
						return "";
					}
				}
				
			}
			
		}
	}
	
	private static String fixRSubevent(Event e, String derChr) {
		Event eCopy = new Event(e);
		String breakpointsString = getBreakpointsString(eCopy.getEventCode());
		int numOfBreakpoints = countBreakpoints(breakpointsString);
		
		if (numOfBreakpoints % 2 != 0) {
			return "";
		} else {
			if (!isValidBreakpointsWithFixedNumBreakpointsInEachChr(eCopy.getBreakpoints(), 2)) {
				List<String> chrList = eCopy.getChrList();				
				if (chrList.size() <= numOfBreakpoints) {
					
					int offsetSize = numOfBreakpoints / 2 - 1;
					List<Integer> offsetList = new ArrayList<>();
					int offset = 0;
					for (int i = 0; i < offsetSize; i++) {
						offset += 2;
						offsetList.add(offset);
					}
					List<String> breakpointList = getAllBreakpointsFromBreakpointsString(breakpointsString);
					List<List<String>> breakpoints = new ArrayList<>();
					breakpoints.add(new ArrayList<String>());
					breakpoints.get(0).add(breakpointList.get(0));
					breakpoints.get(0).add(breakpointList.get(1));
					for (int i = 1; i <= offsetSize; i++) {
						breakpoints.add(new ArrayList<String>());
						breakpoints.get(i).add(breakpointList.get(2 * i));
						breakpoints.get(i).add(breakpointList.get(2 * i + 1));
					}
					eCopy.setBreakpoints(breakpoints);
					
					Collections.reverse(offsetList);
					String breakpointsStringNoSemicolon = breakpointsString.replaceAll(";", "");		
					List<Integer> armIndexes = findArmIndexesInBreakpointsString(breakpointsStringNoSemicolon);
					StringBuilder breakpointsStringRevised = new StringBuilder(breakpointsStringNoSemicolon); 
					for (int i = 0; i < offsetSize; i++) {
						breakpointsStringRevised.insert(armIndexes.get(offsetList.get(i)), ";");
					}
					String eventCode = eCopy.getEventCode();
					int offset0 = eventCode.indexOf(")");
					int breakpointsLeftParentheses = eventCode.indexOf("(", offset0 + 1);
					int breakpointsRightParentheses = eventCode.indexOf(")", offset0 + 1);
					eCopy.setEventCode(new StringBuilder(eventCode).replace(breakpointsLeftParentheses + 1, breakpointsRightParentheses, breakpointsStringRevised.toString()).toString());
					
					if (Validator.isValidRSubevent(eCopy, derChr)) {
						return eCopy.getEventCode();
					} else {
						String chrListStringWithoutSemicolon = getChrListString(eCopy.getEventCode()).replaceAll(";", "");
						return divideRSubeventChrStringIntoList(eCopy, chrListStringWithoutSemicolon, numOfBreakpoints / 2, derChr);
					}
					
				} else {
					return "";
				}
				
			} else {
				
				if (Validator.isValidRSubevent(eCopy, derChr)) {
					return "";
				} else {
					List<String> chrList = eCopy.getChrList();					
					if (chrList.size() <= numOfBreakpoints) {
						String chrListStringWithoutSemicolon = getChrListString(eCopy.getEventCode()).replaceAll(";", "");
						return divideRSubeventChrStringIntoList(eCopy, chrListStringWithoutSemicolon, numOfBreakpoints / 2, derChr);
					} else {
						return "";
					}
				}
				
			}
			
		}
	}
	
	private static String fixTas(Event e) {
		Event eCopy = new Event(e);
		String breakpointsString = getBreakpointsString(eCopy.getEventCode());
		int numOfBreakpoints = countBreakpoints(breakpointsString);
		if (numOfBreakpoints % 2 != 0) {
			return "";
		} else {
			if (!isValidBreakpointsWithVariedNumBreakpointsInEachChr(eCopy.getBreakpoints(), 1, 2)) {
				int offsetSize = numOfBreakpoints / 2;
				List<Integer> offsetList = new ArrayList<>();
				int offset = 1;
				offsetList.add(offset);
				for (int i = 1; i < offsetSize; i++) {
					offset += 2;
					offsetList.add(offset);
				}
				List<String> breakpointList = getAllBreakpointsFromBreakpointsString(breakpointsString);
				List<List<String>> breakpoints = new ArrayList<>();
				breakpoints.add(new ArrayList<String>());
				breakpoints.get(0).add(breakpointList.get(0));
				for (int i = 1; i < offsetSize; i++) {
					breakpoints.add(new ArrayList<String>());
					breakpoints.get(i).add(breakpointList.get(2 * i - 1));
					breakpoints.get(i).add(breakpointList.get(2 * i));
				}
				breakpoints.add(new ArrayList<String>());
				breakpoints.get(offsetSize).add(breakpointList.get(numOfBreakpoints - 1));
				eCopy.setBreakpoints(breakpoints);
				
				Collections.reverse(offsetList);
				String breakpointsStringNoSemicolon = breakpointsString.replaceAll(";", "");		
				List<Integer> armIndexes = findArmIndexesInBreakpointsString(breakpointsStringNoSemicolon);
				StringBuilder breakpointsStringRevised = new StringBuilder(breakpointsStringNoSemicolon); 
				for (int i = 0; i < offsetSize; i++) {
					breakpointsStringRevised.insert(armIndexes.get(offsetList.get(i)), ";");
				}
				String eventCode = eCopy.getEventCode();
				int offset0 = eventCode.indexOf(")");
				int breakpointsLeftParentheses = eventCode.indexOf("(", offset0 + 1);
				int breakpointsRightParentheses = eventCode.indexOf(")", offset0 + 1);
				eCopy.setEventCode(new StringBuilder(eventCode).replace(breakpointsLeftParentheses + 1, breakpointsRightParentheses, breakpointsStringRevised.toString()).toString());
			}
			List<String> chrList = eCopy.getChrList();
			int targetChrListSize = numOfBreakpoints / 2 + 1;
			if (chrList.size() == targetChrListSize) {
				if (Validator.isValidEvent(eCopy)) {
					if (!eCopy.getEventCode().equals(e.getEventCode())) {
						return eCopy.getEventCode();
					} else {
						return "";
					}					
				} else {
					String chrListStringWithoutSemicolon = getChrListString(eCopy.getEventCode()).replaceAll(";", "");
					return divideChrStringIntoList(eCopy, chrListStringWithoutSemicolon, targetChrListSize);
				}
			} else if (chrList.size() <= targetChrListSize * 2) {
				String chrListStringWithoutSemicolon = getChrListString(eCopy.getEventCode()).replaceAll(";", "");
				return divideChrStringIntoList(eCopy, chrListStringWithoutSemicolon, targetChrListSize);
			} else {
				return "";
			}			
		}		
	}
	
	private static String fixTrc(Event e) {
		Event eCopy = new Event(e);
		String breakpointsString = getBreakpointsString(eCopy.getEventCode());
		int numOfBreakpoints = countBreakpoints(breakpointsString);
		if (numOfBreakpoints != 4) {
			return "";
		} else {
			if (!isValidBreakpointsWithVariedNumBreakpointsInEachChr(eCopy.getBreakpoints(), 1, 2)) {
				List<Integer> offsetList = new ArrayList<>();
				offsetList.add(1);
				offsetList.add(3);
				List<String> breakpointList = getAllBreakpointsFromBreakpointsString(breakpointsString);
				List<List<String>> breakpoints = new ArrayList<>();
				breakpoints.add(new ArrayList<String>());
				breakpoints.get(0).add(breakpointList.get(0));				
				breakpoints.add(new ArrayList<String>());
				breakpoints.get(1).add(breakpointList.get(1));
				breakpoints.get(1).add(breakpointList.get(2));
				breakpoints.add(new ArrayList<String>());
				breakpoints.get(2).add(breakpointList.get(3));
				eCopy.setBreakpoints(breakpoints);
				
				Collections.reverse(offsetList);
				String breakpointsStringNoSemicolon = breakpointsString.replaceAll(";", "");		
				List<Integer> armIndexes = findArmIndexesInBreakpointsString(breakpointsStringNoSemicolon);
				StringBuilder breakpointsStringRevised = new StringBuilder(breakpointsStringNoSemicolon); 
				for (int i = 0; i < offsetList.size(); i++) {
					breakpointsStringRevised.insert(armIndexes.get(offsetList.get(i)), ";");
				}
				String eventCode = eCopy.getEventCode();
				int offset0 = eventCode.indexOf(")");
				int breakpointsLeftParentheses = eventCode.indexOf("(", offset0 + 1);
				int breakpointsRightParentheses = eventCode.indexOf(")", offset0 + 1);
				eCopy.setEventCode(new StringBuilder(eventCode).replace(breakpointsLeftParentheses + 1, breakpointsRightParentheses, breakpointsStringRevised.toString()).toString());
			} 
			List<String> chrList = eCopy.getChrList();
			int targetChrListSize = 3;
			if (chrList.size() == targetChrListSize) {
				if (Validator.isValidEvent(eCopy)) {
					if (!eCopy.getEventCode().equals(e.getEventCode())) {
						return eCopy.getEventCode();
					} else {
						return "";
					}					
				} else {
					String chrListStringWithoutSemicolon = getChrListString(eCopy.getEventCode()).replaceAll(";", "");
					return divideChrStringIntoList(eCopy, chrListStringWithoutSemicolon, targetChrListSize);
				}
			} else if (chrList.size() <= targetChrListSize * 2) {
				String chrListStringWithoutSemicolon = getChrListString(eCopy.getEventCode()).replaceAll(";", "");
				return divideChrStringIntoList(eCopy, chrListStringWithoutSemicolon, targetChrListSize);
			} else {
				return "";
			}
		}
	}
	
	private static String fixT(Event e) {
		Event eCopy = new Event(e);
		String breakpointsString = getBreakpointsString(eCopy.getEventCode());
		int numOfBreakpoints = countBreakpoints(breakpointsString);
		if (numOfBreakpoints % 2 != 0) {
			
			if (!isValidBreakpointsWithFixedNumBreakpointsInEachChr(eCopy.getBreakpoints(), 1)) {
				
				List<String> chrList = eCopy.getChrList();
				String chrListStringWithoutSemicolon = getChrListString(eCopy.getEventCode()).replaceAll(";", "");
				if (chrList.size() <= numOfBreakpoints * 2) {
					
					int offsetSize = numOfBreakpoints - 1;
					List<Integer> offsetList = new ArrayList<>();
					int offset = 0;
					for (int i = 0; i < offsetSize; i++) {
						offset += 1;
						offsetList.add(offset);
					}
					List<String> breakpointList = getAllBreakpointsFromBreakpointsString(breakpointsString);
					List<List<String>> breakpoints = new ArrayList<>();
					breakpoints.add(new ArrayList<String>());
					breakpoints.get(0).add(breakpointList.get(0));
					for (int i = 1; i <= offsetSize; i++) {
						breakpoints.add(new ArrayList<String>());
						breakpoints.get(i).add(breakpointList.get(i));
					}
					eCopy.setBreakpoints(breakpoints);
					
					Collections.reverse(offsetList);
					String breakpointsStringNoSemicolon = breakpointsString.replaceAll(";", "");		
					List<Integer> armIndexes = findArmIndexesInBreakpointsString(breakpointsStringNoSemicolon);
					StringBuilder breakpointsStringRevised = new StringBuilder(breakpointsStringNoSemicolon); 
					for (int i = 0; i < offsetSize; i++) {
						breakpointsStringRevised.insert(armIndexes.get(offsetList.get(i)), ";");
					}
					String eventCode = eCopy.getEventCode();
					int offset0 = eventCode.indexOf(")");
					int breakpointsLeftParentheses = eventCode.indexOf("(", offset0 + 1);
					int breakpointsRightParentheses = eventCode.indexOf(")", offset0 + 1);
					eCopy.setEventCode(new StringBuilder(eventCode).replace(breakpointsLeftParentheses + 1, breakpointsRightParentheses, breakpointsStringRevised.toString()).toString());
					
					if (Validator.isValidEvent(eCopy)) {
						return eCopy.getEventCode();
					} else {
						return divideChrStringIntoList(eCopy, chrListStringWithoutSemicolon, numOfBreakpoints);
					}
					
				} else {
					return "";
				}
				
			} else {
				
				if (Validator.isValidEvent(eCopy)) {
					return "";
				} else {
					List<String> chrList = eCopy.getChrList();
					String chrListStringWithoutSemicolon = getChrListString(eCopy.getEventCode()).replaceAll(";", "");
					if (chrList.size() <= numOfBreakpoints * 2) {
						return divideChrStringIntoList(eCopy, chrListStringWithoutSemicolon, numOfBreakpoints);
					} else {
						return "";
					}
				} 
				
			}
			
			
		} else {
			if (!isValidBreakpointsWithFixedNumBreakpointsInEachChr(eCopy.getBreakpoints(), 1) && !isValidBreakpointsWithFixedNumBreakpointsInEachChr(eCopy.getBreakpoints(), 2)) {
				
				List<String> chrList = eCopy.getChrList();
				String chrListStringWithoutSemicolon = getChrListString(eCopy.getEventCode()).replaceAll(";", "");
				
				if (chrList.size() <= numOfBreakpoints * 2) {
					
					int offsetSize = numOfBreakpoints - 1;
					List<Integer> offsetList = new ArrayList<>();
					int offset = 0;
					for (int i = 0; i < offsetSize; i++) {
						offset += 1;
						offsetList.add(offset);
					}
					List<String> breakpointList = getAllBreakpointsFromBreakpointsString(breakpointsString);
					List<List<String>> breakpoints = new ArrayList<>();
					breakpoints.add(new ArrayList<String>());
					breakpoints.get(0).add(breakpointList.get(0));
					for (int i = 1; i <= offsetSize; i++) {
						breakpoints.add(new ArrayList<String>());
						breakpoints.get(i).add(breakpointList.get(i));
					}
					eCopy.setBreakpoints(breakpoints);
					
					Collections.reverse(offsetList);
					String breakpointsStringNoSemicolon = breakpointsString.replaceAll(";", "");		
					List<Integer> armIndexes = findArmIndexesInBreakpointsString(breakpointsStringNoSemicolon);
					StringBuilder breakpointsStringRevised = new StringBuilder(breakpointsStringNoSemicolon); 
					for (int i = 0; i < offsetSize; i++) {
						breakpointsStringRevised.insert(armIndexes.get(offsetList.get(i)), ";");
					}
					String eventCode = eCopy.getEventCode();
					int offset0 = eventCode.indexOf(")");
					int breakpointsLeftParentheses = eventCode.indexOf("(", offset0 + 1);
					int breakpointsRightParentheses = eventCode.indexOf(")", offset0 + 1);
					eCopy.setEventCode(new StringBuilder(eventCode).replace(breakpointsLeftParentheses + 1, breakpointsRightParentheses, breakpointsStringRevised.toString()).toString());
					
					if (Validator.isValidEvent(eCopy)) {
						return eCopy.getEventCode();
					} else {
						String fixMsg =  divideChrStringIntoList(eCopy, chrListStringWithoutSemicolon, numOfBreakpoints);
						if (!fixMsg.isEmpty()) {
							return fixMsg;
						} else if (numOfBreakpoints >= 4) {
							int offsetSizeSecond = numOfBreakpoints / 2 - 1;
							List<Integer> offsetListSecond = new ArrayList<>();
							int offsetSecond = 0;
							for (int i = 0; i < offsetSizeSecond; i++) {
								offsetSecond += 2;
								offsetListSecond.add(offsetSecond);
							}

							List<List<String>> breakpointsSecond = new ArrayList<>();
							breakpointsSecond.add(new ArrayList<String>());
							breakpointsSecond.get(0).add(breakpointList.get(0));
							breakpointsSecond.get(0).add(breakpointList.get(1));
							for (int i = 1; i <= offsetSizeSecond; i++) {
								breakpointsSecond.add(new ArrayList<String>());
								breakpointsSecond.get(i).add(breakpointList.get(2 * i));
								breakpointsSecond.get(i).add(breakpointList.get(2 * i + 1));
							}
							eCopy.setBreakpoints(breakpointsSecond);
							
							Collections.reverse(offsetListSecond);								
							StringBuilder breakpointsStringRevisedSecond = new StringBuilder(breakpointsStringNoSemicolon); 
							for (int i = 0; i < offsetSizeSecond; i++) {
								breakpointsStringRevisedSecond.insert(armIndexes.get(offsetListSecond.get(i)), ";");
							}
							eCopy.setEventCode(new StringBuilder(eventCode).replace(breakpointsLeftParentheses + 1, breakpointsRightParentheses, breakpointsStringRevisedSecond.toString()).toString());
							
							if (Validator.isValidEvent(eCopy)) {
								return eCopy.getEventCode();
							} else {
								return divideChrStringIntoList(eCopy, chrListStringWithoutSemicolon, numOfBreakpoints / 2);
							}
						} else {
							return "";
						}
					}
					
				} else {
					return "";
				}
				
				
			} else if (isValidBreakpointsWithFixedNumBreakpointsInEachChr(eCopy.getBreakpoints(), 1)) {
				if (Validator.isValidEvent(eCopy)) {
					return "";
				} else {
					List<String> chrList = eCopy.getChrList();
					String chrListStringWithoutSemicolon = getChrListString(eCopy.getEventCode()).replaceAll(";", "");
					if (chrList.size() <= numOfBreakpoints) {
						String fixMsg = divideChrStringIntoList(eCopy, chrListStringWithoutSemicolon, numOfBreakpoints);
						if (!fixMsg.isEmpty()) {
							return fixMsg;
						} else if (numOfBreakpoints >= 4) {
							int offsetSize = numOfBreakpoints / 2 - 1;
							List<Integer> offsetList = new ArrayList<>();
							int offset = 0;
							for (int i = 0; i < offsetSize; i++) {
								offset += 2;
								offsetList.add(offset);
							}
							List<String> breakpointList = getAllBreakpointsFromBreakpointsString(breakpointsString);
							List<List<String>> breakpoints = new ArrayList<>();
							breakpoints.add(new ArrayList<String>());
							breakpoints.get(0).add(breakpointList.get(0));
							breakpoints.get(0).add(breakpointList.get(1));
							for (int i = 1; i <= offsetSize; i++) {
								breakpoints.add(new ArrayList<String>());
								breakpoints.get(i).add(breakpointList.get(2 * i));
								breakpoints.get(i).add(breakpointList.get(2 * i + 1));
							}
							eCopy.setBreakpoints(breakpoints);
							
							Collections.reverse(offsetList);
							String breakpointsStringNoSemicolon = breakpointsString.replaceAll(";", "");		
							List<Integer> armIndexes = findArmIndexesInBreakpointsString(breakpointsStringNoSemicolon);
							StringBuilder breakpointsStringRevised = new StringBuilder(breakpointsStringNoSemicolon); 
							for (int i = 0; i < offsetSize; i++) {
								breakpointsStringRevised.insert(armIndexes.get(offsetList.get(i)), ";");
							}
							String eventCode = eCopy.getEventCode();
							int offset0 = eventCode.indexOf(")");
							int breakpointsLeftParentheses = eventCode.indexOf("(", offset0 + 1);
							int breakpointsRightParentheses = eventCode.indexOf(")", offset0 + 1);
							eCopy.setEventCode(new StringBuilder(eventCode).replace(breakpointsLeftParentheses + 1, breakpointsRightParentheses, breakpointsStringRevised.toString()).toString());
							
							if (Validator.isValidEvent(eCopy)) {
								return eCopy.getEventCode();
							} else {
								return divideChrStringIntoList(eCopy, chrListStringWithoutSemicolon, numOfBreakpoints / 2);
							}
						} else {
							return "";
						}
					} else if (chrList.size() <= numOfBreakpoints * 2) {
						return divideChrStringIntoList(eCopy, chrListStringWithoutSemicolon, numOfBreakpoints);
					} else {
						return "";
					}
				}
			} else {
				if (Validator.isValidEvent(eCopy)) {
					return "";
				} else {
					List<String> chrList = eCopy.getChrList();
					String chrListStringWithoutSemicolon = getChrListString(eCopy.getEventCode()).replaceAll(";", "");
					if (numOfBreakpoints >= 4) {
						
						if (chrList.size() <= numOfBreakpoints) {
							String fixMsg = divideChrStringIntoList(eCopy, chrListStringWithoutSemicolon, numOfBreakpoints / 2);
							if (!fixMsg.isEmpty()) {
								return fixMsg;
							} else {
								int offsetSize = numOfBreakpoints - 1;
								List<Integer> offsetList = new ArrayList<>();
								int offset = 0;
								for (int i = 0; i < offsetSize; i++) {
									offset += 1;
									offsetList.add(offset);
								}
								List<String> breakpointList = getAllBreakpointsFromBreakpointsString(breakpointsString);
								List<List<String>> breakpoints = new ArrayList<>();
								breakpoints.add(new ArrayList<String>());
								breakpoints.get(0).add(breakpointList.get(0));
								for (int i = 1; i <= offsetSize; i++) {
									breakpoints.add(new ArrayList<String>());
									breakpoints.get(i).add(breakpointList.get(i));
								}
								eCopy.setBreakpoints(breakpoints);
								
								Collections.reverse(offsetList);
								String breakpointsStringNoSemicolon = breakpointsString.replaceAll(";", "");		
								List<Integer> armIndexes = findArmIndexesInBreakpointsString(breakpointsStringNoSemicolon);
								StringBuilder breakpointsStringRevised = new StringBuilder(breakpointsStringNoSemicolon); 
								for (int i = 0; i < offsetSize; i++) {
									breakpointsStringRevised.insert(armIndexes.get(offsetList.get(i)), ";");
								}
								String eventCode = eCopy.getEventCode();
								int offset0 = eventCode.indexOf(")");
								int breakpointsLeftParentheses = eventCode.indexOf("(", offset0 + 1);
								int breakpointsRightParentheses = eventCode.indexOf(")", offset0 + 1);
								eCopy.setEventCode(new StringBuilder(eventCode).replace(breakpointsLeftParentheses + 1, breakpointsRightParentheses, breakpointsStringRevised.toString()).toString());
								
								if (Validator.isValidEvent(eCopy)) {
									return eCopy.getEventCode();
								} else {
									return divideChrStringIntoList(eCopy, chrListStringWithoutSemicolon, numOfBreakpoints);
								}
							}
						} else if (chrList.size() <= numOfBreakpoints * 2) {
							
							int offsetSize = numOfBreakpoints - 1;
							List<Integer> offsetList = new ArrayList<>();
							int offset = 0;
							for (int i = 0; i < offsetSize; i++) {
								offset += 1;
								offsetList.add(offset);
							}
							List<String> breakpointList = getAllBreakpointsFromBreakpointsString(breakpointsString);
							List<List<String>> breakpoints = new ArrayList<>();
							breakpoints.add(new ArrayList<String>());
							breakpoints.get(0).add(breakpointList.get(0));
							for (int i = 1; i <= offsetSize; i++) {
								breakpoints.add(new ArrayList<String>());
								breakpoints.get(i).add(breakpointList.get(i));
							}
							eCopy.setBreakpoints(breakpoints);
							
							Collections.reverse(offsetList);
							String breakpointsStringNoSemicolon = breakpointsString.replaceAll(";", "");		
							List<Integer> armIndexes = findArmIndexesInBreakpointsString(breakpointsStringNoSemicolon);
							StringBuilder breakpointsStringRevised = new StringBuilder(breakpointsStringNoSemicolon); 
							for (int i = 0; i < offsetSize; i++) {
								breakpointsStringRevised.insert(armIndexes.get(offsetList.get(i)), ";");
							}
							String eventCode = eCopy.getEventCode();
							int offset0 = eventCode.indexOf(")");
							int breakpointsLeftParentheses = eventCode.indexOf("(", offset0 + 1);
							int breakpointsRightParentheses = eventCode.indexOf(")", offset0 + 1);
							eCopy.setEventCode(new StringBuilder(eventCode).replace(breakpointsLeftParentheses + 1, breakpointsRightParentheses, breakpointsStringRevised.toString()).toString());
							
							if (Validator.isValidEvent(eCopy)) {
								return eCopy.getEventCode();
							} else {
								return divideChrStringIntoList(eCopy, chrListStringWithoutSemicolon, numOfBreakpoints);
							}
							
						} else {
							return "";
						}
						
					} else {
						
						if (chrList.size() <= numOfBreakpoints * 2) {
							
							int offsetSize = numOfBreakpoints - 1;
							List<Integer> offsetList = new ArrayList<>();
							int offset = 0;
							for (int i = 0; i < offsetSize; i++) {
								offset += 1;
								offsetList.add(offset);
							}
							List<String> breakpointList = getAllBreakpointsFromBreakpointsString(breakpointsString);
							List<List<String>> breakpoints = new ArrayList<>();
							breakpoints.add(new ArrayList<String>());
							breakpoints.get(0).add(breakpointList.get(0));
							for (int i = 1; i <= offsetSize; i++) {
								breakpoints.add(new ArrayList<String>());
								breakpoints.get(i).add(breakpointList.get(i));
							}
							eCopy.setBreakpoints(breakpoints);
							
							Collections.reverse(offsetList);
							String breakpointsStringNoSemicolon = breakpointsString.replaceAll(";", "");		
							List<Integer> armIndexes = findArmIndexesInBreakpointsString(breakpointsStringNoSemicolon);
							StringBuilder breakpointsStringRevised = new StringBuilder(breakpointsStringNoSemicolon); 
							for (int i = 0; i < offsetSize; i++) {
								breakpointsStringRevised.insert(armIndexes.get(offsetList.get(i)), ";");
							}
							String eventCode = eCopy.getEventCode();
							int offset0 = eventCode.indexOf(")");
							int breakpointsLeftParentheses = eventCode.indexOf("(", offset0 + 1);
							int breakpointsRightParentheses = eventCode.indexOf(")", offset0 + 1);
							eCopy.setEventCode(new StringBuilder(eventCode).replace(breakpointsLeftParentheses + 1, breakpointsRightParentheses, breakpointsStringRevised.toString()).toString());
							
							if (Validator.isValidEvent(eCopy)) {
								return eCopy.getEventCode();
							} else {
								return divideChrStringIntoList(eCopy, chrListStringWithoutSemicolon, numOfBreakpoints);
							}
							
						} else {
							return "";
						}						
						
					} // This is the case where numOfBreakpoints == 2
					
				}
			} // This is the case of isValidBreakpointsWithFixedNumBreakpointsInEachChr(eCopy.getBreakpoints(), 2) 
		}
	}
	
	private static String dealWithChrListOfSizeThree(Event e) {
		String chr0 = e.getChrList().get(0);
		String chr1 = e.getChrList().get(1);
		String chr2 = e.getChrList().get(2);
		List<String> chrList = new ArrayList<>();
		String eventCode = e.getEventCode();
		if (isNumericChrOfLengthOne(chr1)) {
			if (isNumericChrOfLengthOne(chr2)) {
				chrList.add(chr0);
				chrList.add(chr1 + chr2);
				e.setChrList(chrList);						
				int offset0 = eventCode.indexOf(";");
				int offset1 = eventCode.indexOf(";", offset0 + 1);
				e.setEventCode(new StringBuilder(eventCode).deleteCharAt(offset1).toString());
				if (Validator.isValidEvent(e)) {
					return e.getEventCode();
				} else if (isNumericChrOfLengthOne(chr0)) {
					chrList.remove(1);
					chrList.remove(0);
					chrList.add(chr0 + chr1);
					chrList.add(chr2);
					e.setChrList(chrList);
					e.setEventCode(new StringBuilder(eventCode).deleteCharAt(offset0).toString());
					if (Validator.isValidEvent(e)) {
						return e.getEventCode();
					} else {
						return "";
					}
				} else {
					return "";
				}
			} else if (isNumericChrOfLengthOne(chr0)) {
				chrList.add(chr0 + chr1);
				chrList.add(chr2);
				e.setChrList(chrList);
				int offset0 = eventCode.indexOf(";");
				e.setEventCode(new StringBuilder(eventCode).deleteCharAt(offset0).toString());
				if (Validator.isValidEvent(e)) {
					return e.getEventCode();
				} else {
					return "";
				}
			} else {
				return "";
			}					
		} else {
			return "";
		}
	}
	
	private static void dealWithChrListOfSizeFour(Event e) {
		String chr0 = e.getChrList().get(0);
		String chr1 = e.getChrList().get(1);
		String chr2 = e.getChrList().get(2);
		String chr3 = e.getChrList().get(3);
		List<String> chrList = new ArrayList<>();
		chrList.add(chr0 + chr1);
		chrList.add(chr2 + chr3);
		e.setChrList(chrList);
		String eventCode = e.getEventCode();
		int offset0 = eventCode.indexOf(";");
		int offset1 = eventCode.indexOf(";", offset0 + 1);
		int offset2 = eventCode.indexOf(";", offset1 + 1);
		e.setEventCode(new StringBuilder(eventCode).deleteCharAt(offset2).deleteCharAt(offset0).toString());		
	}
	
	private static String divideChrStringIntoList(Event e, String incorrectChrS) {
		String eventCode = e.getEventCode();
		List<String> chrList = new ArrayList<>();
		if (incorrectChrS.length() == 2) {
			chrList.add(incorrectChrS.substring(0, 1));
			chrList.add(incorrectChrS.substring(1));
			e.setChrList(chrList);
			e.setEventCode(insertOneSemicolonToChrString(eventCode, incorrectChrS, 1));
			if (Validator.isValidEvent(e)) {
				return e.getEventCode();
			} else {
				return "";
			}
		} else if (incorrectChrS.length() == 4) {
			chrList.add(incorrectChrS.substring(0, 2));
			chrList.add(incorrectChrS.substring(2));
			e.setChrList(chrList);
			e.setEventCode(insertOneSemicolonToChrString(eventCode, incorrectChrS, 2));
			if (Validator.isValidEvent(e)) {
				return e.getEventCode();
			} else {
				return "";
			}
		} else if (incorrectChrS.length() == 3) {
        	chrList.add(incorrectChrS.substring(0, 1));
			chrList.add(incorrectChrS.substring(1));
			e.setChrList(chrList);
			e.setEventCode(insertOneSemicolonToChrString(eventCode, incorrectChrS, 1));
			if (Validator.isValidEvent(e)) {
				return e.getEventCode();
			} else {
				chrList.remove(1);
				chrList.remove(0);
				chrList.add(incorrectChrS.substring(0, 2));
				chrList.add(incorrectChrS.substring(2));
				e.setChrList(chrList);
				e.setEventCode(insertOneSemicolonToChrString(eventCode, incorrectChrS, 2));
				if (Validator.isValidEvent(e)) {
					return e.getEventCode();
				} else {
					return "";
				}
			}
        } else {
        	return "";
        }
	}
	
	private static String divideChrStringIntoList(Event e, String incorrectChrS, int size) {
		String eventCode = e.getEventCode();
		if (size > 1 && incorrectChrS.length() >= size && incorrectChrS.length() <= 2 * size) {
			for (List<Integer> divisionRule: divideSurplus(incorrectChrS.length() - size, size)) {
				dealWithDividingChrStringIntoListGeneral(e, divisionRule, eventCode, incorrectChrS);
				if (Validator.isValidEvent(e)) {
					return e.getEventCode();
				} 
			}
			return "";
		} else {
			return "";
		}
	}
	
	private static String divideRSubeventChrStringIntoList(Event e, String incorrectChrS, int size, String derChr) {
		String eventCode = e.getEventCode();
		if (size > 1 && incorrectChrS.length() >= size && incorrectChrS.length() <= 2 * size) {
			for (List<Integer> divisionRule: divideSurplus(incorrectChrS.length() - size, size)) {
				dealWithDividingChrStringIntoListGeneral(e, divisionRule, eventCode, incorrectChrS);
				if (Validator.isValidRSubevent(e, derChr)) {
					return e.getEventCode();
				} 
			}
			return "";
		} else {
			return "";
		}
	}
	
	private static String divideHsrTSubeventChrStringIntoList(Event e, String incorrectChrS, int size) {
		String eventCode = e.getEventCode();
		if (size > 1 && incorrectChrS.length() >= size && incorrectChrS.length() <= 2 * size) {
			for (List<Integer> divisionRule: divideSurplus(incorrectChrS.length() - size, size)) {
				dealWithDividingChrStringIntoListGeneral(e, divisionRule, eventCode, incorrectChrS);
				if (Validator.isValidGenericSubevent(e)) {
					return e.getEventCode();
				} 
			}
			return "";
		} else {
			return "";
		}
	}
	
	private static void dealWithDividingChrStringIntoListGeneral(Event e, List<Integer> divisionRule, String eventCode, String incorrectChrS) {
		List<Integer> offsetList = new ArrayList<>();
		int offset = 0;
		for (int i = 0; i < divisionRule.size() - 1; i++) {
			offset += divisionRule.get(i);
			offsetList.add(offset);
		}
		List<String> chrList = new ArrayList<>();
		chrList.add(incorrectChrS.substring(0, offsetList.get(0)));

		for (int i = 0; i < offsetList.size() - 1; i++) {
			chrList.add(incorrectChrS.substring(offsetList.get(i), offsetList.get(i + 1)));
		}

		chrList.add(incorrectChrS.substring(offsetList.get(offsetList.size() - 1)));
		e.setChrList(chrList);
		
		Collections.reverse(offsetList);
		e.setEventCode(insertSemicolonToChrString(eventCode, incorrectChrS, offsetList));
	}
	
	private static boolean isNumericChrOfLengthOneList(List<String> chrList) {
		for (String chr: chrList) {
			if (!isNumericChrOfLengthOne(chr)) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean isNumericChrOfLengthOne(String chr) {
		if (chr.length() != 1) {
			return false;
		} else if (!isValidChr(chr)) {
			return false;
		} else if (chr.equals("X") || chr.equals("Y")) {
			return false;
		} else {
			return true;
		}
	}
	
	private static String insertOneSemicolonToChrString(String eventCode, String incorrectChrS, int offset) {
		int chrListLeftParentheses = eventCode.indexOf("(");
		int chrListRightParentheses = eventCode.indexOf(")");
		return new StringBuilder(eventCode).replace(chrListLeftParentheses + 1, chrListRightParentheses, new StringBuilder(incorrectChrS).insert(offset, ";").toString()).toString();
	}
	
	private static String insertSemicolonToChrString(String eventCode, String chrListStringNoSemicolon, List<Integer> offsetListReversed) {
		int chrListLeftParentheses = eventCode.indexOf("(");
		int chrListRightParentheses = eventCode.indexOf(")");
		StringBuilder chrListStringRevised = new StringBuilder(chrListStringNoSemicolon);
		for (int i = 0; i < offsetListReversed.size(); i++) {
			chrListStringRevised.insert(offsetListReversed.get(i), ";");
		}
		return new StringBuilder(eventCode).replace(chrListLeftParentheses + 1, chrListRightParentheses, chrListStringRevised.toString()).toString();
	}
	
	private static String getChrListString(String eventCode) {
		int chrListLeftParentheses = eventCode.indexOf("(");
		int chrListRightParentheses = eventCode.indexOf(")");
		return eventCode.substring(chrListLeftParentheses + 1, chrListRightParentheses);
	} 
	
	private static String getBreakpointsString(String eventCode) {
		int offset = eventCode.indexOf(")");
		int breakpointsLeftParentheses = eventCode.indexOf("(", offset + 1);
		int breakpointsRightParentheses = eventCode.indexOf(")", offset + 1);
		return eventCode.substring(breakpointsLeftParentheses + 1, breakpointsRightParentheses); // No need to trim, as original white-space characters, if any, have already been filtered by the Antlr grammar.
	}
	
	private static void removeSemicolonInBreakpointsString(Event e) {
		String eventCode = e.getEventCode();
		String breakpointsString = getBreakpointsString(eventCode);
//		String[] breakpointArray = breakpointsString.split(";");
//		String breakpointsStringNoSemicolon = breakpointsString.replaceAll(";", "");
		List<String> breakpointList = getAllBreakpointsFromBreakpointsString(breakpointsString);
		List<List<String>> breakpoints = new ArrayList<>();	
		breakpoints.add(new ArrayList<String>());
		for (String breakpoint: breakpointList) {
			breakpoints.get(0).add(breakpoint);
		}
		e.setBreakpoints(breakpoints);		
		int offset0 = eventCode.indexOf(")");
		int breakpointsLeftParentheses = eventCode.indexOf("(", offset0 + 1);
		int breakpointsRightParentheses = eventCode.indexOf(")", offset0 + 1);
		e.setEventCode(new StringBuilder(eventCode).replace(breakpointsLeftParentheses + 1, breakpointsRightParentheses, breakpointsString.replaceAll(";", "")).toString());
	}
	
	private static void removeSecondSemicolonInBreakpointsString(Event e) {
		String breakpointsString = getBreakpointsString(e.getEventCode());
		List<List<String>> breakpoints = e.getBreakpoints();
		breakpoints.get(1).add(breakpoints.get(2).get(0));
		breakpoints.remove(2);
		
		int firstSemicolon = breakpointsString.indexOf(";");
		int secondSemicolon = breakpointsString.indexOf(";", firstSemicolon + 1);
		String eventCode = e.getEventCode();
		int offset0 = eventCode.indexOf(")");
		int breakpointsLeftParentheses = eventCode.indexOf("(", offset0 + 1);
		int breakpointsRightParentheses = eventCode.indexOf(")", offset0 + 1);
		e.setEventCode(new StringBuilder(eventCode).replace(breakpointsLeftParentheses + 1, breakpointsRightParentheses, new StringBuilder(breakpointsString).deleteCharAt(secondSemicolon).toString()).toString());
	}
	
	private static void exchangeBreakpoints(Event e) {
		String eventCode = e.getEventCode();
		String breakpointsString = getBreakpointsString(eventCode);
		e.getBreakpoints().add(0, e.getBreakpoints().remove(1));
		String[] breakpointArray = breakpointsString.split(";");
		int offset0 = eventCode.indexOf(")");
		int breakpointsLeftParentheses = eventCode.indexOf("(", offset0 + 1);
		int breakpointsRightParentheses = eventCode.indexOf(")", offset0 + 1);
		e.setEventCode(new StringBuilder(eventCode).replace(breakpointsLeftParentheses + 1, breakpointsRightParentheses, breakpointArray[1] + ";" + breakpointArray[0]).toString());
	}
	
	private static void exchangeChrs(Event e) {
		e.getChrList().add(0, e.getChrList().remove(1));
		String eventCode = e.getEventCode();
		int chrListLeftParentheses = eventCode.indexOf("(");
		int chrListRightParentheses = eventCode.indexOf(")");
		e.setEventCode(new StringBuilder(eventCode).replace(chrListLeftParentheses + 1, chrListRightParentheses, e.getChrList().get(0) + ";" + e.getChrList().get(1)).toString());
	}
	
	private static void moveSemicolonPositionInBreakpoints(Event e) {
		String eventCode = e.getEventCode();
		String breakpointsString = getBreakpointsString(eventCode);
		List<Integer> armIndexes = findArmIndexesInBreakpointsString(breakpointsString);
		List<List<String>> breakpoints = e.getBreakpoints();
		breakpoints.get(1).add(0, breakpoints.get(0).remove(1));
		int offset0 = eventCode.indexOf(")");
		int breakpointsLeftParentheses = eventCode.indexOf("(", offset0 + 1);
		int breakpointsRightParentheses = eventCode.indexOf(")", offset0 + 1);
		e.setEventCode(new StringBuilder(eventCode).replace(breakpointsLeftParentheses + 1, breakpointsRightParentheses, new StringBuilder(breakpointsString).deleteCharAt(armIndexes.get(2) - 1).insert(armIndexes.get(1), ";").toString()).toString());
	}
	
	private static void insertOneSemicolonToBreakpoints(Event e) {
		List<List<String>> breakpoints = new ArrayList<>();
		breakpoints.add(new ArrayList<String>());
		breakpoints.add(new ArrayList<String>());
		breakpoints.get(0).add(e.getBreakpoints().get(0).get(0));
		for (int i = 1; i < e.getBreakpoints().get(0).size(); i++) {
			breakpoints.get(1).add(e.getBreakpoints().get(0).get(i));
		}
		e.setBreakpoints(breakpoints);
		e.setEventCode(insertOneSemicolonToBreakpointsString(e));
	}
	
	private static String insertOneSemicolonToBreakpointsString(Event e) {
		String eventCode = e.getEventCode();
		String breakpointsString = getBreakpointsString(eventCode);
		List<Integer> armIndexes = findArmIndexesInBreakpointsString(breakpointsString);
		int offset0 = eventCode.indexOf(")");
		int breakpointsLeftParentheses = eventCode.indexOf("(", offset0 + 1);
		int breakpointsRightParentheses = eventCode.indexOf(")", offset0 + 1);
		return new StringBuilder(eventCode).replace(breakpointsLeftParentheses + 1, breakpointsRightParentheses, new StringBuilder(breakpointsString).insert(armIndexes.get(1), ";").toString()).toString();
	}
	
    private static int countBreakpoints(String breakpointsString) {
    	int count = 0;
    	for (char c: breakpointsString.toCharArray()) {
    		if (c == 'p' || c == 'q') {
    			count++;
    		}
    	}
    	return count;
    }
    
    private static boolean isListOfSizeOneLists(List<List<String>> breakpoints) {
    	for (List<String> pointList: breakpoints) {
    		if (pointList.size() != 1) {
    			return false;
    		}
    	}
    	return true;
    }
    
    private static List<Integer> findArmIndexesInBreakpointsString(String breakpointsString) {
    	List<Integer> armIndexes = new ArrayList<>();
    	Pattern p = Pattern.compile("[pq]\\d+(\\.\\d+)?");
    	Matcher m = p.matcher(breakpointsString);
    	while (m.find()) {
    		armIndexes.add(m.start());
    	}
    	return armIndexes;
    }
    
    private static List<String> getAllBreakpointsFromBreakpointsString(String breakpointsString) {
    	List<String> breakpointList = new ArrayList<>();
    	Pattern p = Pattern.compile("[pq]\\d+(\\.\\d+)?");
    	Matcher m = p.matcher(breakpointsString);
    	while (m.find()) {
    		breakpointList.add(m.group());
    	}
    	return breakpointList;
    }
    
    private static List<Integer> findSemicolonIndexes(String s) {
    	List<Integer> semicolonIndexes = new ArrayList<>();
    	for (int i = 0; i < s.length(); i++) {
    		if (s.charAt(i) == ';') {
    			semicolonIndexes.add(i);
    		}
    	}
    	return semicolonIndexes;
    }
    
    private static List<List<Integer>> divideSurplus(int surplus, int buckets) {
    	List<List<Integer>> combinations = new ArrayList<>();
    	if (surplus == 0) {
    		List<Integer> divisionRule = new ArrayList<>();
    		for (int i = 0; i < buckets; i++) {
    			divisionRule.add(1);
    		}
    		combinations.add(divisionRule);
    		return combinations;
    	} else if (surplus == buckets) {
    		List<Integer> divisionRule = new ArrayList<>();
    		for (int i = 0; i < buckets; i++) {
    			divisionRule.add(2);
    		}
    		combinations.add(divisionRule);
    		return combinations;
    	} else {
    		int[] arr = new int[buckets];
    		for (int i = 0; i< arr.length; i++) {
    			arr[i] = i;
    		}
    		Permutation.getCombination(arr, buckets, surplus, combinations);
            List<List<Integer>> divisionRules = getDivisionRules(combinations, buckets);
            Collections.sort(divisionRules, new ListComparator());
            return divisionRules;
    	}
    }
    
    private static List<List<Integer>> getDivisionRules(List<List<Integer>> indexOfChrOfLengthTwo, int buckets) {
		List<List<Integer>> divisionRules = new ArrayList<>();			
		for (List<Integer> indexes: indexOfChrOfLengthTwo) {
			List<Integer> divisionRule = new ArrayList<>();
			for (int i = 0; i < buckets; i++) {
				if (indexes.contains(i)) {
					divisionRule.add(2);
				} else {
					divisionRule.add(1);
				}				
			}			
			divisionRules.add(divisionRule);				
		}
		return divisionRules;
    }
    
    public static String getChr(String chrBreakpoint) {
		String chr;
		if (chrBreakpoint.contains("p")) {
			chr = chrBreakpoint.substring(0, chrBreakpoint.indexOf('p'));				
		} else {
			chr = chrBreakpoint.substring(0, chrBreakpoint.indexOf('q'));							
		}
		return chr;
	}
    
    public static String getBand(String chrBreakpoint) {
		String band;
		if (chrBreakpoint.contains("p")) {
			band = chrBreakpoint.substring(chrBreakpoint.indexOf('p') + 1);				
		} else {
			band = chrBreakpoint.substring(chrBreakpoint.indexOf('q') + 1);							
		}
		return band;
	}
    
    // Usually we cannot fix chrBreakpoint, but there is an exception. Sometimes, careless bench scientists forget the subband period when recording karyotype. This error is fixable!
    private static String fixChrBreakpoint(String chrBreakpoint) {
    	String chr = getChr(chrBreakpoint);
    	String incorrectBand = getBand(chrBreakpoint);
    	StringBuilder revisingBand = new StringBuilder(chrBreakpoint);
    	if (!incorrectBand.contains(".")) {
    		if (incorrectBand.length() == 3 || incorrectBand.length() == 4) {
    			String revisedChrBreakpoint = revisingBand.insert(chr.length() + 3, ".").toString();
    			if (isValidChrBreakpoint(revisedChrBreakpoint)) {
    				return revisedChrBreakpoint;
    			}
    		}	 
    	} else if (incorrectBand.indexOf(".") != 2) {
    		if (incorrectBand.length() == 4 || incorrectBand.length() == 5) {
    			String revisedChrBreakpoint = revisingBand.deleteCharAt(revisingBand.indexOf(".")).insert(chr.length() + 3, ".").toString();
    			if (isValidChrBreakpoint(revisedChrBreakpoint)) {
    				return revisedChrBreakpoint;
    			}
    		} 
    	}
    	return "";
    }  
    
    
    public static String getChrArm(String chrBreakpoint) {
		String chrArm;
		if (chrBreakpoint.contains("p")) {
			chrArm = chrBreakpoint.substring(0, chrBreakpoint.indexOf('p') + 1);				
		} else {
			chrArm = chrBreakpoint.substring(0, chrBreakpoint.indexOf('q') + 1);							
		}
		return chrArm;
	}
    
    private static boolean isUncerntainDer(DerEvent d) {
    	for (Event e: d.getSubevents()) {
    		if (e.isUncertainEvent()) {
    			return true;
    		}
    	}
    	return false;
    }
    
    private static boolean areUnbalancedResolution(String chrBreakpoint1, String chrBreakpoint2) {
    	if ((chrBreakpoint1.startsWith(chrBreakpoint2) || chrBreakpoint2.startsWith(chrBreakpoint1)) && !chrBreakpoint1.equals(chrBreakpoint2)) {
    		return true;
    	} else {
    		return false;
    	}
    }

}
