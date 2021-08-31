package toolkit;

import java.util.Comparator;

/**
 * @author Lin Zhang
 * Programmer / Software Development Engineer
 * Institute for Informatics
 * Washington University School of Medicine in St. Louis
 * 
 * Date: August 30, 2021 
 */
public class ChrBreakpointComparator implements Comparator<String> {
	
	public int compare(String chrBreakpoint1, String chrBreakpoint2) {
		String chr1 = getChr(chrBreakpoint1);
		String chr2 = getChr(chrBreakpoint2);
		try {			
			int chrCompareNum = Integer.valueOf(chr1).compareTo(Integer.valueOf(chr2));
			return chrCompareNum != 0 ? chrCompareNum : chrBreakpoint1.compareTo(chrBreakpoint2); 
		} catch (NumberFormatException e) {
			if (chr1.matches("[\\d]+")) {
				return -1;
			} else if (chr2.matches("[\\d]+")) {
				return 1;
			} else {
				return chrBreakpoint1.compareTo(chrBreakpoint2);
			}
		}
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
}
