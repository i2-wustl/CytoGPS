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
public class CenComparator implements Comparator<String> {
	
	public int compare(String cen1, String cen2) {
		String chr1 = getChr(cen1);
		String chr2 = getChr(cen2);
		int chrCompare = new ChrComparator().compare(chr1, chr2);
		if (chrCompare != 0) {
			return chrCompare;
		}
		return cen1.compareTo(cen2);
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
