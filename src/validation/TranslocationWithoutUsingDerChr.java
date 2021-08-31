package validation;

/**
 * @author Lin Zhang
 * Programmer / Software Development Engineer
 * Institute for Informatics
 * Washington University School of Medicine in St. Louis
 * 
 * Date: August 30, 2021 
 */
public class TranslocationWithoutUsingDerChr {

	private boolean isWrong, isDI;
	private String markDeletion;
	private String chrBreakpointDeletion, chrBreakpointInsertion;
	
	public TranslocationWithoutUsingDerChr() {
		isWrong = false;
		isDI = false;
		markDeletion = null;
		chrBreakpointDeletion = null;
		chrBreakpointInsertion = null;
	}
	
//	public TranslocationWithoutDerChr(boolean isWrong, boolean isDI, String markDeletion, String chrBreakpointDeletion, String chrBreakpointInsertion) {
//		this.isWrong = isWrong;
//		this.isDI = isDI;
//		this.markDeletion = markDeletion;
//		this.chrBreakpointDeletion = chrBreakpointDeletion;
//		this.chrBreakpointInsertion = chrBreakpointInsertion;
//	}
	
	public boolean isWrong() {
		return isWrong;
	}
	public void setWrong(boolean isWrong) {
		this.isWrong = isWrong;
	}
	public boolean isDI() {
		return isDI;
	}
	public void setDI(boolean isDI) {
		this.isDI = isDI;
	}
	public String getMarkDeletion() {
		return markDeletion;
	}
	public void setMarkDeletion(String markDeletion) {
		this.markDeletion = markDeletion;
	}
	public String getChrBreakpointDeletion() {
		return chrBreakpointDeletion;
	}
	public void setChrBreakpointDeletion(String chrBreakpointDeletion) {
		this.chrBreakpointDeletion = chrBreakpointDeletion;
	}
	public String getChrBreakpointInsertion() {
		return chrBreakpointInsertion;
	}
	public void setChrBreakpointInsertion(String chrBreakpointInsertion) {
		this.chrBreakpointInsertion = chrBreakpointInsertion;
	}
	
}
