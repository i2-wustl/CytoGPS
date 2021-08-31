package validation;
import java.util.List;

/**
 * @author Lin Zhang
 * Programmer / Software Development Engineer
 * Institute for Informatics
 * Washington University School of Medicine in St. Louis
 * 
 * Date: August 30, 2021 
 */
public class DerivativeValidationOutcome {
	
	private boolean validDerivativeChr;
	private List<List<String>> segments;
	private String detailedSystem;
	private List<List<Integer>> derKaryotypeLGF;
	
	public DerivativeValidationOutcome (boolean validDerivativeChr, List<List<String>> segments, String detailedSystem, List<List<Integer>> derKaryotypeLGF) {
		this.validDerivativeChr = validDerivativeChr;
		this.segments = segments;
		this.detailedSystem = detailedSystem;
		this.derKaryotypeLGF = derKaryotypeLGF;
	}
	
	public boolean isValidDerivativeChr() {
		return validDerivativeChr;
	}
	
	public void setValidDerivativeChr(boolean validDerivativeChr) {
		this.validDerivativeChr = validDerivativeChr;
	}

	public List<List<String>> getSegments() {
		return segments;
	}

	public void setSegments(List<List<String>> segments) {
		this.segments = segments;
	}

	public String getDetailedSystem() {
		return detailedSystem;
	}

	public void setDetailedSystem(String detailedSystem) {
		this.detailedSystem = detailedSystem;
	}

	public List<List<Integer>> getDerKaryotypeLGF() {
		return derKaryotypeLGF;
	}

	public void setDerKaryotypeLGF(List<List<Integer>> derKaryotypeLGF) {
		this.derKaryotypeLGF = derKaryotypeLGF;
	}
	

}
