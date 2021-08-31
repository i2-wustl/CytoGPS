package business;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lin Zhang
 * Programmer / Software Development Engineer
 * Institute for Informatics
 * Washington University School of Medicine in St. Louis
 * 
 * Date: August 30, 2021 
 */
public class Event {
	
	private String nature;
	private List<String> chrList;
	private List<List<String>> breakpoints;
	private List<String> gainChrs;
	private List<String> lossChrs;
	private int copies;
	private boolean uncertainEvent;
	private boolean validEvent;
	private String eventCode;
	
	public Event() {
		nature = "";
		chrList = new ArrayList<String>();
		breakpoints = new ArrayList<List<String>>();
		gainChrs = new ArrayList<String>();
		lossChrs = new ArrayList<String>();
		copies = 1;
		uncertainEvent = false;
		eventCode = "";
	}
	
	public Event(Event e) {
		nature = e.getNature();
		chrList = new ArrayList<String>(e.getChrList());
		breakpoints = new ArrayList<List<String>>(e.getBreakpoints());
		gainChrs = new ArrayList<String>(e.getGainChrs());
		lossChrs = new ArrayList<String>(e.getLossChrs());
		copies = e.getCopies();
		uncertainEvent = e.isUncertainEvent();
		eventCode = e.getEventCode();
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public List<String> getChrList() {
		return chrList;
	}

	public void setChrList(List<String> chrList) {
		this.chrList = chrList;
	}

	public List<List<String>> getBreakpoints() {
		return breakpoints;
	}

	public void setBreakpoints(List<List<String>> breakpoints) {
		this.breakpoints = breakpoints;
	}
	
	public List<String> getGainChrs() {
		return gainChrs;
	}

	public void setGainChrs(List<String> gainChrs) {
		this.gainChrs = gainChrs;
	}

	public List<String> getLossChrs() {
		return lossChrs;
	}

	public void setLossChrs(List<String> lossChrs) {
		this.lossChrs = lossChrs;
	}

	public int getCopies() {
		return copies;
	}

	public void setCopies(int copies) {
		this.copies = copies;
	}

	public boolean isUncertainEvent() {
		return uncertainEvent;
	}

	public void setUncertainEvent(boolean uncertainEvent) {
		this.uncertainEvent = uncertainEvent;
	}

	public boolean isValidEvent() {
		return validEvent;
	}

	public void setValidEvent(boolean validEvent) {
		this.validEvent = validEvent;
	}

	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

	public List<List<String>> getBreakpointsFullName(List<String> chrList, List<List<String>> breakpoints) {
		List<List<String>> breakpointsFullName = new ArrayList<>();
		if (chrList.size() != breakpoints.size()) {
			return null;
		} else {
			int i = 0;
			for (List<String> pointList: breakpoints) {
				List<String> chrBreakpoints = new ArrayList<>();
				for (String point: pointList) {
					chrBreakpoints.add(chrList.get(i) + point);
				}				
				breakpointsFullName.add(chrBreakpoints);
				i++;
			}
		}
		return breakpointsFullName;
	}	
	
	public String getCompleteEventCode(List<List<String>> breakpoints) {
		StringBuilder eventCode = new StringBuilder(this.eventCode);
		int index = eventCode.indexOf(")");
		String breakpointsCode = "(";
		for (List<String> pointList: breakpoints) {
			boolean beginning = true;
			for (String point: pointList) {
				if (beginning && point.startsWith("or")) {
					breakpointsCode += point.substring(2);
				} else {
					breakpointsCode += point;
				}
				beginning = false;
			}
			breakpointsCode += ";";
		}
		breakpointsCode = breakpointsCode.substring(0, breakpointsCode.length() - 1) + ")";
		eventCode.insert(index + 1, breakpointsCode);
		return eventCode.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Event && ((Event)o).getNature().equals(this.getNature()) && equalList(((Event)o).getChrList(), this.getChrList())) {
			return true;
    	} else {
    		return false;
    	}
	}
	
	public boolean equalList(List<String> chrList0, List<String> chrList1) {
    	if (chrList0.size() != chrList1.size()) {
    		return false;
    	} else {
    		for (int i = 0; i < chrList0.size(); i++) {
    			if (!chrList0.get(i).equals(chrList1.get(i))) {
    				return false;
    			}
    		}
    		return true;
    	}
    }
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += nature.hashCode();
		hash += chrList.size();
		return hash;
	}

}
