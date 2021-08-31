package business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import main.TokenError;

/**
 * @author Lin Zhang
 * Programmer / Software Development Engineer
 * Institute for Informatics
 * Washington University School of Medicine in St. Louis
 * 
 * Date: August 30, 2021 
 */
public class FinalResult {
	
	private List<BiologicalOutcome> biologicalOutcomeList;	
	private List<String> biologicalInterpretationList;	
	private boolean containingLexerParserError;
	private String errorMessage;
	private boolean containingValidationError;
	private List<String> validationMessage;
	private String revisedKaryotype;
	private List<String> relationshipList;
	private List<Integer> cellNumList;
	private List<TokenError> tokenErrorList;
	private List<String> cloneCodeList;
	
	public FinalResult() {
		biologicalOutcomeList = new ArrayList<>();
		biologicalInterpretationList = new ArrayList<>();
		containingLexerParserError = false;
		errorMessage = "";
		containingValidationError = false;
		validationMessage = new ArrayList<>();
		revisedKaryotype = "";
		relationshipList = new ArrayList<>();
		cellNumList = new ArrayList<>();		
		tokenErrorList = new ArrayList<>();
		cloneCodeList = new ArrayList<>();		
	}

	public List<BiologicalOutcome> getBiologicalOutcomeList() {
		return biologicalOutcomeList;
	}

	public void setBiologicalOutcomeList(List<BiologicalOutcome> biologicalOutcomeList) {
		this.biologicalOutcomeList = biologicalOutcomeList;
	}

	public List<String> getBiologicalInterpretationList() {
		return biologicalInterpretationList;
	}

	public void setBiologicalInterpretationList(List<String> biologicalInterpretationList) {
		this.biologicalInterpretationList = biologicalInterpretationList;
	}

	public boolean isContainingLexerParserError() {
		return containingLexerParserError;
	}

	public void setContainingLexerParserError(boolean containingLexerParserError) {
		this.containingLexerParserError = containingLexerParserError;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isContainingValidationError() {
		return containingValidationError;
	}

	public void setContainingValidationError(boolean containingValidationError) {
		this.containingValidationError = containingValidationError;
	}

	public List<String> getValidationMessage() {
		return validationMessage;
	}

	public void setValidationMessage(List<String> validationMessage) {
		this.validationMessage = validationMessage;
	}
	
	public String getFormattedValidationMessage() {
		String errorMessage = "";
		for (String s: validationMessage) {
			errorMessage += s + "\r\n";
		}
		return errorMessage;
	}

	public String getRevisedKaryotype() {
		return revisedKaryotype;
	}

	public void setRevisedKaryotype(String revisedKaryotype) {
		this.revisedKaryotype = revisedKaryotype;
	}	
	
	public List<String> getRelationshipList() {
		return relationshipList;
	}

	public void setRelationshipList(List<String> relationshipList) {
		this.relationshipList = relationshipList;
	}
	
	public List<Integer> getCellNumList() {
		return cellNumList;
	}

	public void setCellNumList(List<Integer> cellNumList) {
		this.cellNumList = cellNumList;
	}

	public List<TokenError> getTokenErrorList() {
		return tokenErrorList;
	}

	public void setTokenErrorList(List<TokenError> tokenErrorList) {
		this.tokenErrorList = tokenErrorList;
	}
	
	public List<String> getCloneCodeList() {
		return cloneCodeList;
	}

	public void setCloneCodeList(List<String> cloneCodeList) {
		this.cloneCodeList = cloneCodeList;
	}
	
	// This will record the relationships between different clones
	public List<String> getRelationshipList(List<Clone> rowClones) {
		List<String> relationshipList = new ArrayList<>();
		for (Clone clone: rowClones) {
			Map<String, String> relationship = clone.getRelationship();
			if (relationship.size() == 0) {
				relationshipList.add(null);
			} else {
				String key = relationship.keySet().stream().findFirst().get(); // Using Java 8 tech; 
				String msg = "Note: \"" + key + "\" in this clone " + relationship.get(key) + " of \"" + rowClones.get(clone.getRelatedClone()).getCloneCode() + "\"";
				relationshipList.add(msg);
			}
		}
		return relationshipList;
	}
	
	// This will record cell numbers of all the clones
	public List<Integer> getCellNumList(List<Clone> rowClones) {
		List<Integer> cellNumList = new ArrayList<>();
		for (Clone clone: rowClones) {
			cellNumList.add(clone.getCellNumber());
		}
		return cellNumList;
	}
	
	// This will record clone strings of all the clones
	public List<String> getCloneCodeList(List<Clone> rowClones) {
		List<String> cloneCodeList = new ArrayList<>();
		for (Clone clone: rowClones) {
			cloneCodeList.add(clone.getCloneCode());
		}
		return cloneCodeList;
	}

}
