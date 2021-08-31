package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lin Zhang
 * Programmer / Software Development Engineer
 * Institute for Informatics
 * Washington University School of Medicine in St. Louis
 * 
 * Date: August 30, 2021 
 */
public class Clone {
	
	private int cellNumber;
	private int relatedClone;
	private Map<String, String> relationship;
	private List<Event> cloneInput;
	private String cloneCode;
	
	public Clone() {
		cellNumber = 0;
		relatedClone = -1;
		relationship = new HashMap<>();
		cloneInput = new ArrayList<>();
		cloneCode = "";
	}

	public int getCellNumber() {
		return cellNumber;
	}

	public void setCellNumber(int cellNumber) {
		this.cellNumber = cellNumber;
	}

	public int getRelatedClone() {
		return relatedClone;
	}

	public void setRelatedClone(int relatedClone) {
		this.relatedClone = relatedClone;
	}

	public Map<String, String> getRelationship() {
		return relationship;
	}

	public void setRelationship(Map<String, String> relationship) {
		this.relationship = relationship;
	}

	public List<Event> getCloneInput() {
		return cloneInput;
	}

	public void setCloneInput(List<Event> cloneInput) {
		this.cloneInput = cloneInput;
	}

	public String getCloneCode() {
		return cloneCode;
	}

	public void setCloneCode(String cloneCode) {
		this.cloneCode = cloneCode;
	}
	
	

}
