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
public class ChrComparator implements Comparator<String> {

	public int compare(String chr1, String chr2) {
		try {			
			return Integer.valueOf(chr1).compareTo(Integer.valueOf(chr2));
		} catch (NumberFormatException e) {
			if (chr1.matches("[\\d]+")) {
				return 1;
			} else if (chr2.matches("[\\d]+")) {
				return -1;
			} else {
				return chr1.compareTo(chr2);
			}
		}
	}
	
}
