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
public class DerEvent extends Event {
	
	private List<Event> subevents;
	
	public DerEvent() {
		super();
		subevents = new ArrayList<Event>();
	}

	public List<Event> getSubevents() {
		return subevents;
	}

	public void setSubevents(List<Event> subevents) {
		this.subevents = subevents;
	}

}
