package validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import business.Clone;
import business.DerEvent;
import business.Event;
import business.ParseEvent;

/**
 * @author Lin Zhang
 * Programmer / Software Development Engineer
 * Institute for Informatics
 * Washington University School of Medicine in St. Louis
 * 
 * Date: August 30, 2021 
 */
public class Validator {
	
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
	
	public static String getBand(String chrBreakpoint) {
		String band;
		if (chrBreakpoint.contains("p")) {
			band = chrBreakpoint.substring(chrBreakpoint.indexOf('p') + 1);				
		} else {
			band = chrBreakpoint.substring(chrBreakpoint.indexOf('q') + 1);							
		}
		return band;
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
	
	public static boolean isValidRowClones(List<Clone> rowClones) {
		for (Clone clone: rowClones) {    		
    		for (Event e: clone.getCloneInput()) {
    			if (!e.getNature().isEmpty() && !e.isUncertainEvent() && !isValidEvent(e)) {
    				return false;
    			} else if (e.getNature().isEmpty() && !e.isUncertainEvent()) {
					if (!isValidChrList(e.getGainChrs())) {
						return false;
					}    					
    				if (!isValidChrList(e.getLossChrs())) {
						return false;
					}    				
    			}
    		}
    	}
		return true;
	}
	

	public static boolean isValidEvent(Event e) {
		boolean isValid = true;
		List<String> chrList = e.getChrList();
		List<List<String>> breakpoints = e.getBreakpoints();
		List<List<String>> breakpointsFullName = new ArrayList<>();
		if (!(e instanceof DerEvent)) {
			if (chrList.size() != breakpoints.size()) {
				return false;
			} else {
				breakpointsFullName = e.getBreakpointsFullName(chrList, breakpoints); 
			}      		
    	} else {
    		breakpointsFullName = null;
    	}
		
		switch (e.getNature()) {
			case "add": {
				if (chrList.size() != 1) {
					isValid = false;
				} else if (!isValidChr(chrList.get(0))) {
					isValid = false;
				} else if (breakpoints.get(0).size() != 1) {
					isValid = false;
				} else if (!isValidChrBreakpoint(breakpointsFullName.get(0).get(0))) {
					isValid = false;
				} else {
					isValid = true;
				}
				break;
			}
			case "del": {
				if (chrList.size() != 1) {
					isValid = false;
				} else if (!isValidChr(chrList.get(0))) {
					isValid = false;
				} else if (breakpoints.get(0).size() != 1 && breakpoints.get(0).size() != 2) {
					isValid = false;
				} else if (breakpoints.get(0).size() == 1) {
					if (!isValidChrBreakpoint(breakpointsFullName.get(0).get(0))) {
						isValid = false;
					} else {
						isValid = true;
					}
				} else {
					ParseEvent p = new ParseEvent();
					String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
					String chrBreakpoint1 = breakpointsFullName.get(0).get(1); 
					if (!isValidChrBreakpoint(chrBreakpoint0) || !isValidChrBreakpoint(chrBreakpoint1)) {
						isValid = false;
					} else if (!p.getChrArm(chrBreakpoint0).equals(p.getChrArm(chrBreakpoint1))) {
						isValid = false;
					} else if (areUnbalancedResolution(chrBreakpoint0, chrBreakpoint1)) {
						isValid = false;
					} else if (chrBreakpoint0.compareTo(chrBreakpoint1) > 0) {
						isValid = false;
					} else {
						isValid = true;
					}
				}
				break;
			}
			case "dic": {
				if (chrList.size() != 2) {
					isValid = false;
				} else if (!isValidChr(chrList.get(0)) || !isValidChr(chrList.get(1))) {
					isValid = false;
				} else if (breakpoints.get(0).size() != 1 || breakpoints.get(1).size() != 1) {
					isValid = false;
				} else if (!isValidChrBreakpoint(breakpointsFullName.get(0).get(0)) || !isValidChrBreakpoint(breakpointsFullName.get(1).get(0))) {
					isValid = false;
				} else {
					isValid = true;
				}
				break;
			}
			case "idic": {
				if (chrList.size() != 1) {
					isValid = false;
				} else if (!isValidChr(chrList.get(0))) {
					isValid = false;
				} else if (breakpoints.get(0).size() != 1) {
					isValid = false;
				} else if (!isValidChrBreakpoint(breakpointsFullName.get(0).get(0))) {
					isValid = false;
				} else {
					isValid = true;
				}
				break;
			}
			case "dup": {
				if (chrList.size() != 1) {
					isValid = false;
				} else if (!isValidChr(chrList.get(0))) {
					isValid = false;
				} else if (breakpoints.get(0).size() != 2) {
					isValid = false;
				} else {
					ParseEvent p = new ParseEvent();
					String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
					String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
					if (!isValidChrBreakpoint(chrBreakpoint0) || !isValidChrBreakpoint(chrBreakpoint1)) {
						isValid = false;
					} else if (!p.getChrArm(chrBreakpoint0).equals(p.getChrArm(chrBreakpoint1))) {
						isValid = false;
					} else if (areUnbalancedResolution(chrBreakpoint0, chrBreakpoint1)) {
						isValid = false;
					} else {
						isValid = true;
					}					
				}
				break;
			}
			case "trp": {
				if (chrList.size() != 1) {
					isValid = false;
				} else if (!isValidChr(chrList.get(0))) {
					isValid = false;
				} else if (breakpoints.get(0).size() != 2) {
					isValid = false;
				} else {
					ParseEvent p = new ParseEvent();
					String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
					String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
					if (!isValidChrBreakpoint(chrBreakpoint0) || !isValidChrBreakpoint(chrBreakpoint1)) {
						isValid = false;
					} else if (!p.getChrArm(chrBreakpoint0).equals(p.getChrArm(chrBreakpoint1))) {
						isValid = false;
					} else if (areUnbalancedResolution(chrBreakpoint0, chrBreakpoint1)) {
						isValid = false;
					} else {
						isValid = true;
					}
				}
				break;
			}
			case "qdp": {
				if (chrList.size() != 1) {
					isValid = false;
				} else if (!isValidChr(chrList.get(0))) {
					isValid = false;
				} else if (breakpoints.get(0).size() != 2) {
					isValid = false;
				} else {
					ParseEvent p = new ParseEvent();
					String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
					String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
					if (!isValidChrBreakpoint(chrBreakpoint0) || !isValidChrBreakpoint(chrBreakpoint1)) {
						isValid = false;
					} else if (!p.getChrArm(chrBreakpoint0).equals(p.getChrArm(chrBreakpoint1))) {
						isValid = false;
					} else if (areUnbalancedResolution(chrBreakpoint0, chrBreakpoint1)) {
						isValid = false;
					} else {
						isValid = true;
					}
				}
				break;
			}
			case "hsr": {
				if (chrList.size() != 1) {
					isValid = false;
				} else if (!isValidChr(chrList.get(0))) {
					isValid = false;
				} else if (breakpoints.get(0).size() != 1) {
					isValid = false;
				} else if (!isValidChrBreakpoint(breakpointsFullName.get(0).get(0))) {
					isValid = false;
				} else {
					isValid = true;
				}
				break;
			}
			case "ins": {
				if (chrList.size() != 1 && chrList.size() != 2) {
					isValid = false;
				} else if (chrList.size() == 1) {
					if (!isValidChr(chrList.get(0))) {
						isValid = false;
					} else if (breakpoints.get(0).size() != 3) {
						isValid = false;
					} else if (!isValidChrBreakpointList(breakpointsFullName)) {
						isValid = false;
					} else {
						ParseEvent p = new ParseEvent();
						String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
						String chrBreakpoint2 = breakpointsFullName.get(0).get(2);
						if (!p.getChrArm(chrBreakpoint1).equals(p.getChrArm(chrBreakpoint2))) {
							isValid = false;
						} else if (areUnbalancedResolution(chrBreakpoint1, chrBreakpoint2)) {
							isValid = false;
						} else {
							isValid = true;
						}
					}
				} else {
					if (!isValidChr(chrList.get(0)) || !isValidChr(chrList.get(1))) {
						isValid = false;
					} else if (breakpoints.get(0).size() != 1 || breakpoints.get(1).size() != 2) {
						isValid = false;
					} else if (!isValidChrBreakpointList(breakpointsFullName)) {
						isValid = false;
					} else {
						ParseEvent p = new ParseEvent();
						String chrBreakpoint1 = breakpointsFullName.get(1).get(0);
						String chrBreakpoint2 = breakpointsFullName.get(1).get(1);
						if (!p.getChrArm(chrBreakpoint1).equals(p.getChrArm(chrBreakpoint2))) {
							isValid = false;
						} else if (areUnbalancedResolution(chrBreakpoint1, chrBreakpoint2)) {
							isValid = false;
						} else {
							isValid = true;
						}
					}
				}
				break;
			}
			case "inv": {
				if (chrList.size() != 1) {
					isValid = false;
				} else if (!isValidChr(chrList.get(0))) {
					isValid = false;
				} else if (breakpoints.get(0).size() != 2) {
					isValid = false;
				} else if (!isValidChrBreakpoint(breakpointsFullName.get(0).get(0)) || !isValidChrBreakpoint(breakpointsFullName.get(0).get(1))) {
					isValid = false;
				} else if (areUnbalancedResolution(breakpointsFullName.get(0).get(0), breakpointsFullName.get(0).get(1))) {
					isValid = false;
				} else if (breakpointsFullName.get(0).get(0).compareTo(breakpointsFullName.get(0).get(1)) > 0) {
					isValid = false;
				} else {
					isValid = true;
				}
				break;
			}
			case "i": {
				if (chrList.size() != 1) {
					isValid = false;
				} else if (!isValidChr(chrList.get(0))) {
					isValid = false;
				} else if (breakpoints.get(0).size() != 1) {
					isValid = false;
				} else if (!isValidCen(breakpointsFullName.get(0).get(0))) {
					isValid = false;
				} else {
					isValid = true;
				}
				break;
			}
			case "r": {
				if (chrList.size() == 0) {
					isValid = false;
				} else if (!isValidChrList(chrList)) {
					isValid = false;
				} else if (!isValidBreakpointsWithFixedNumBreakpointsInEachChr(breakpoints, 2)) {
					isValid = false;
				} else if (!isValidChrBreakpointList(breakpointsFullName)) {
					isValid = false;
				} else {
					ParseEvent p = new ParseEvent();
					for (List<String> chrBreakpoints: breakpointsFullName) {
						if (p.getChrArm(chrBreakpoints.get(0)).equals(p.getChrArm(chrBreakpoints.get(1)))) {
							isValid = false;
							break;
						}
					}
				}
				break;
			}
			case "tas": {
				if (chrList.size() < 2) {
					isValid = false;
				} else if (!isValidChrList(chrList)) {
					isValid = false;
				} else if (!isValidBreakpointsWithVariedNumBreakpointsInEachChr(breakpoints, 1, 2)) {
					isValid = false;
				} else if (!isValidTerminalList(breakpointsFullName)) {
					isValid = false;
				} else {
					ParseEvent p = new ParseEvent();
					for (List<String> chrBreakpoints: breakpointsFullName) {
						if (chrBreakpoints.size() == 2 && p.getChrArm(chrBreakpoints.get(0)).equals(p.getChrArm(chrBreakpoints.get(1)))) {
							isValid = false;
							break;
						}
					}
				}
				break;
			}
			case "t": {
				if (chrList.size() < 2) {
					isValid = false;
				} else if (!isValidChrList(chrList)) {
					isValid = false;
				} else if (!isValidBreakpointsWithFixedNumBreakpointsInEachChr(breakpoints, 1) && !isValidBreakpointsWithFixedNumBreakpointsInEachChr(breakpoints, 2)) {
					isValid = false;
				} else if (!isValidChrBreakpointList(breakpointsFullName)) {
					isValid = false;
				} else {
					if (isValidBreakpointsWithFixedNumBreakpointsInEachChr(breakpoints, 2)) {						
						ParseEvent p = new ParseEvent();
						for (List<String> chrBreakpoints: breakpointsFullName) {
							if (!p.getChrArm(chrBreakpoints.get(0)).equals(p.getChrArm(chrBreakpoints.get(1)))) {
								isValid = false;
								break;
							} else if (areUnbalancedResolution(chrBreakpoints.get(0), chrBreakpoints.get(1))) {
								isValid = false;
								break;
							}
						}
					}
				}
				break;
			}
			case "trc": {
				if (chrList.size() != 3) {
					isValid = false;
				} else if (!isValidChrList(chrList)) {
					isValid = false;
				} else if (!isValidBreakpointsWithVariedNumBreakpointsInEachChr(breakpoints, 1, 2)) {
					isValid = false;
				} else if (!isValidChrBreakpointList(breakpointsFullName)) {
					isValid = false;
				} else {
					ParseEvent p = new ParseEvent();
					if (p.getChrArm(breakpointsFullName.get(1).get(0)).equals(p.getChrArm(breakpointsFullName.get(1).get(1)))) {
						isValid = false;
					} else {
						isValid = true;
					}					
				}
				break;
			}
			case "rob": {
				if (chrList.size() != 2) {
					isValid = false;
				} else if (!isValidChrList(chrList)) {
					isValid = false;
				} else if (!isValidBreakpointsWithFixedNumBreakpointsInEachChr(breakpoints, 1)) {
					isValid = false;
				} else if (!isValidCenList(breakpointsFullName)) {
					isValid = false;
				} else {
					isValid = true;
				}
				break;
			}
			case "ider": {
				if (chrList.size() != 1) {
					isValid = false;
				} else if (!isValidChr(chrList.get(0))) {
					isValid = false;
				} else if (breakpoints.size() != 1) {
					isValid = false;
				} else if (breakpoints.get(0).size() != 1) {
					isValid = false;
				} else if (!isValidCen(e.getBreakpointsFullName(chrList, breakpoints).get(0).get(0))) {
					isValid = false;
//				} else if (((DerEvent)e).getSubevents().size() == 0) {
//					isValid = false;
				} else {
					for (Event subevent: ((DerEvent)e).getSubevents()) {
						if (!subevent.isUncertainEvent() && !isValidGenericSubevent(subevent)) {
							return false;
						}
					}
					if (!isUncerntainDer((DerEvent)e) && !new IderValidation((DerEvent)e).getDerivativeValidationOutcome().isValidDerivativeChr()) {
						return false;
					}
					isValid = true;
				}
				break;
			}
			case "der": {
				if (chrList.size() != 1 && chrList.size() != 2) {
					isValid = false;
				} else if (chrList.size() == 1) {
					if (!isValidChr(chrList.get(0))) {
						isValid = false;
					} else if (breakpoints.size() > 0) {
						isValid = false;
					} else if (((DerEvent)e).getSubevents().size() == 0) {
						isValid = false;
					} else {
						String derChr = e.getChrList().get(0);
						List<Event> subevents = ((DerEvent)e).getSubevents();
						for (Event subevent: subevents) {
							switch (subevent.getNature()) {
								case "r": {
									if (!subevent.isUncertainEvent() && !isValidRSubevent(subevent, derChr)) {
										return false;
									}
									break;
								}
								default: {
									if (!subevent.isUncertainEvent() && !isValidGenericSubevent(subevent)) {
										return false;
									}
								}
							}
						}
						if (!containDerChr(subevents, derChr)) {
							return false;
						} else {
							if (!isUncerntainDer((DerEvent)e) && !new DerivativeValidation((DerEvent)e).getDerivativeValidationOutcome().isValidDerivativeChr()) {
								return false;
							}
						}
						isValid = true;
					} 
				} else {
					if (!isValidChr(chrList.get(0)) || !isValidChr(chrList.get(1))) {
						isValid = false;
					} else if (breakpoints.size() > 0) {
						if (breakpoints.size() != 2) {
							isValid = false;
						} else if (breakpoints.get(0).size() != 1 || breakpoints.get(1).size() != 1) {
							isValid = false;
						} else if (!isValidCenList(e.getBreakpointsFullName(chrList, breakpoints))) {
							isValid = false;
						} else if (((DerEvent)e).getSubevents().size() > 0) {
							for (Event subevent: ((DerEvent)e).getSubevents()) {
								if (!subevent.isUncertainEvent() && !isValidGenericSubevent(subevent)) {
									return false;
								}
							}							
							if (!isUncerntainDer((DerEvent)e)) {
								if (!chrList.get(0).equals(chrList.get(1))) {
									if (!new DicDerivativeValidationDifferentDerChrWithBreakpoints((DerEvent)e).getDicDerivativeValidationOutcome().isValidDerivativeChr()) {
										return false;
									}
								} else {
									List<List<String>> derCens = e.getBreakpointsFullName(chrList, breakpoints);
									if (!getChrArm(derCens.get(0).get(0)).equals(getChrArm(derCens.get(1).get(0)))) {
										if (!new DicDerivativeValidationDifferentDerChrWithBreakpoints((DerEvent)e).getDicDerivativeValidationOutcome().isValidDerivativeChr()) {
											return false;
										}
									} else {
										if (!new DicDerivativeValidationSameDerChrSameArmWithBreakpoints((DerEvent)e).getDicDerivativeValidationOutcome().isValidDerivativeChr()) {
											return false;
										}
									}
								}
							}							
							isValid = true;
						} else {
							isValid = true; // e.g. der(1;7)(q10;p10)
						}

					} else {
						if (((DerEvent)e).getSubevents().size() == 0) {
							isValid = false;
						} else {
							for (Event subevent: ((DerEvent)e).getSubevents()) {
								if (!subevent.isUncertainEvent() && !isValidGenericSubevent(subevent)) {									
									return false;
								}
							}	
							if (!isUncerntainDer((DerEvent)e)) {
								if (!chrList.get(0).equals(chrList.get(1))) {
									if (!new DicDerivativeValidationDifferentDerChrNoBreakpoints((DerEvent)e).getDicDerivativeValidationOutcome().isValidDerivativeChr()) {
										return false;
									}
								} else {
									if (!new DicDerivativeValidationSameDerChrNoBreakpoints((DerEvent)e).getDicDerivativeValidationOutcome().isValidDerivativeChr()) {
										return false;
									}
								}
							}
							isValid = true;	
						}
					}					
				}
				break;
			}
			// We don't need to add "default" here, because we have listed all cases allowed by our grammar.
		}
		return isValid;
	}
	
	public static boolean isValidRSubevent(Event e, String derChr) {
		List<String> chrList = e.getChrList();
		List<List<String>> breakpoints = e.getBreakpoints();
		List<List<String>> breakpointsFullName = new ArrayList<>();
		if (chrList.size() != breakpoints.size()) {
			return false;
		} else {
			breakpointsFullName = e.getBreakpointsFullName(chrList, breakpoints); 
		}
		boolean isValid;
		if (!isValidChrList(chrList)) {
			isValid = false;
		} else if (!chrList.contains(derChr)) {
			isValid = false;
		} else if (!isValidBreakpointsWithFixedNumBreakpointsInEachChr(breakpoints, 2)) {
			isValid = false;
		} else if (!isValidChrBreakpointList(breakpointsFullName)) {
			isValid = false;
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
//				isValid = false;
//			} else {
				for (List<String> chrBreakpoints: breakpointsExceptDerChr) {
					if (!p.getChrArm(chrBreakpoints.get(0)).equals(p.getChrArm(chrBreakpoints.get(1)))) {
						return false;
					} else if (areUnbalancedResolution(chrBreakpoints.get(0), chrBreakpoints.get(1))) {
						return false;
					}
				}
				isValid = true;
//			} ring's derchr may have the same arm, e.g. der(1)inv(1)(p13q21)r(1)(p12p33) 
		}
		return isValid;
	}
	
	public static boolean isValidGenericSubevent(Event e) {
		boolean isValid = true;
		List<String> chrList = e.getChrList();
		List<List<String>> breakpoints = e.getBreakpoints();
		List<List<String>> breakpointsFullName = new ArrayList<>();
		
		if (chrList.size() != breakpoints.size()) {
			return false;
		} else {
			breakpointsFullName = e.getBreakpointsFullName(chrList, breakpoints); 
		} 
		
		switch (e.getNature()) {
		    case "add": {
		    	if (chrList.size() != 1) {
					isValid = false;
				} else if (!isValidChr(chrList.get(0))) {
					isValid = false;
				} else if (breakpoints.get(0).size() != 1) {
					isValid = false;
				} else if (!isValidChrBreakpoint(breakpointsFullName.get(0).get(0))) {
					isValid = false;
				} else {
					isValid = true;
				}
		    	break;
		    }
		    case "del": {
		    	if (chrList.size() != 1) {
					isValid = false;
				} else if (!isValidChr(chrList.get(0))) {
					isValid = false;
				} else if (breakpoints.get(0).size() != 1 && breakpoints.get(0).size() != 2) {
					isValid = false;
				} else if (breakpoints.get(0).size() == 1) {
					if (!isValidChrBreakpoint(breakpointsFullName.get(0).get(0))) {
						isValid = false;
					} else {
						isValid = true;
					}
				} else {
					ParseEvent p = new ParseEvent();
					String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
					String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
					if (!isValidChrBreakpoint(chrBreakpoint0) || !isValidChrBreakpoint(chrBreakpoint1)) {
						isValid = false;
					} else if (!p.getChrArm(chrBreakpoint0).equals(p.getChrArm(chrBreakpoint1))) {
						isValid = false;
					} else if (areUnbalancedResolution(chrBreakpoint0, chrBreakpoint1)) {
						isValid = false;
					} else if (chrBreakpoint0.compareTo(chrBreakpoint1) > 0) {
						isValid = false;
					} else {
						isValid = true;
					}
				}
		    	break;
		    }
			case "dup": {
				if (chrList.size() != 1) {
					isValid = false;
				} else if (!isValidChr(chrList.get(0))) {
					isValid = false;
				} else if (breakpoints.get(0).size() != 2) {
					isValid = false;
				} else {
					ParseEvent p = new ParseEvent();
					String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
					String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
					if (!isValidChrBreakpoint(chrBreakpoint0) || !isValidChrBreakpoint(chrBreakpoint1)) {
						isValid = false;
					} else if (!p.getChrArm(chrBreakpoint0).equals(p.getChrArm(chrBreakpoint1))) {
						isValid = false;
					} else if (areUnbalancedResolution(chrBreakpoint0, chrBreakpoint1)) {
						isValid = false;
					} else {
						isValid = true;
					}
				}
				break;
			}
			case "trp": {
				if (chrList.size() != 1) {
					isValid = false;
				} else if (!isValidChr(chrList.get(0))) {
					isValid = false;
				} else if (breakpoints.get(0).size() != 2) {
					isValid = false;
				} else {
					ParseEvent p = new ParseEvent();
					String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
					String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
					if (!isValidChrBreakpoint(chrBreakpoint0) || !isValidChrBreakpoint(chrBreakpoint1)) {
						isValid = false;
					} else if (!p.getChrArm(chrBreakpoint0).equals(p.getChrArm(chrBreakpoint1))) {
						isValid = false;
					} else if (areUnbalancedResolution(chrBreakpoint0, chrBreakpoint1)) {
						isValid = false;
					} else {
						isValid = true;
					}
				}
				break;
			}
			case "qdp": {
				if (chrList.size() != 1) {
					isValid = false;
				} else if (!isValidChr(chrList.get(0))) {
					isValid = false;
				} else if (breakpoints.get(0).size() != 2) {
					isValid = false;
				} else {
					ParseEvent p = new ParseEvent();
					String chrBreakpoint0 = breakpointsFullName.get(0).get(0);
					String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
					if (!isValidChrBreakpoint(chrBreakpoint0) || !isValidChrBreakpoint(chrBreakpoint1)) {
						isValid = false;
					} else if (!p.getChrArm(chrBreakpoint0).equals(p.getChrArm(chrBreakpoint1))) {
						isValid = false;
					} else if (areUnbalancedResolution(chrBreakpoint0, chrBreakpoint1)) {
						isValid = false;
					} else {
						isValid = true;
					}
				}
				break;
			}
			case "hsr": {
				if (chrList.size() > 2) {
					isValid = false;
				} else if (!isValidChrList(chrList)) {
					isValid = false;
				} else if (!isValidBreakpointsWithFixedNumBreakpointsInEachChr(breakpoints, 1)) {
					isValid = false;
				} else if (!isValidChrBreakpointList(breakpointsFullName)) {
					isValid = false;
				} else {
					isValid = true;
				}
				break;
			}
			case "ins": {
				if (chrList.size() != 1 && chrList.size() != 2) {
					isValid = false;
				} else if (chrList.size() == 1) {
					if (!isValidChr(chrList.get(0))) {
						isValid = false;
					} else if (breakpoints.get(0).size() != 3) {
						isValid = false;
					} else if (!isValidChrBreakpointList(breakpointsFullName)) {
						isValid = false;
					} else {
						ParseEvent p = new ParseEvent();
						String chrBreakpoint1 = breakpointsFullName.get(0).get(1);
						String chrBreakpoint2 = breakpointsFullName.get(0).get(2);
						if (!p.getChrArm(chrBreakpoint1).equals(p.getChrArm(chrBreakpoint2))) {
							isValid = false;
						} else if (areUnbalancedResolution(chrBreakpoint1, chrBreakpoint2)) {
							isValid = false;
						} else {
							isValid = true;
						}
					}
				} else {
					if (!isValidChr(chrList.get(0)) || !isValidChr(chrList.get(1))) {
						isValid = false;
					} else if (breakpoints.get(0).size() != 1 || breakpoints.get(1).size() != 2) {
						isValid = false;
					} else if (!isValidChrBreakpointList(breakpointsFullName)) {
						isValid = false;
					} else {
						ParseEvent p = new ParseEvent();
						String chrBreakpoint1 = breakpointsFullName.get(1).get(0);
						String chrBreakpoint2 = breakpointsFullName.get(1).get(1);
						if (!p.getChrArm(chrBreakpoint1).equals(p.getChrArm(chrBreakpoint2))) {
							isValid = false;
						} else if (areUnbalancedResolution(chrBreakpoint1, chrBreakpoint2)) {
							isValid = false;
						} else {
							isValid = true;
						}
					}
				}
				break;
			}
			case "inv": {
				if (chrList.size() != 1) {
					isValid = false;
				} else if (!isValidChr(chrList.get(0))) {
					isValid = false;
				} else if (breakpoints.get(0).size() != 2) {
					isValid = false;
				} else if (!isValidChrBreakpoint(breakpointsFullName.get(0).get(0)) || !isValidChrBreakpoint(breakpointsFullName.get(0).get(1))) {
					isValid = false;
				} else if (areUnbalancedResolution(breakpointsFullName.get(0).get(0), breakpointsFullName.get(0).get(1))) {
					isValid = false;
				} else if (breakpointsFullName.get(0).get(0).compareTo(breakpointsFullName.get(0).get(1)) > 0) {
					isValid = false;
				} else {
					isValid = true;
				}
				break;
			}	
			case "t": {
				if (chrList.size() != 2) {
					isValid = false;
				} else if (!isValidChrList(chrList)) {
					isValid = false;
				} else if (!isValidBreakpointsWithFixedNumBreakpointsInEachChr(breakpoints, 1)) {
					isValid = false;
				} else if (!isValidChrBreakpointList(breakpointsFullName)) {
					isValid = false;
				} else {
					isValid = true;
				}
				break;
			}
			default: isValid = false; // This is important, as "r" "tas" "idic" "i" "dic" "trc" cannot appear in derivative chromosomes, except "r" could appear inside der when der's chrList has only one chr.
		}
		return isValid;
	}
	
    public static boolean containDerChr(List<Event> subevents, String derChr) {
    	for (Event e: subevents) {
    		if (e.getChrList().contains(derChr)) {
    			return true;
    		}
    	}
    	return false;
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
