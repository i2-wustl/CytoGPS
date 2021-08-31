package main;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
 * @author Lin Zhang
 * Programmer / Software Development Engineer
 * Institute for Informatics
 * Washington University School of Medicine in St. Louis
 * 
 * Date: August 30, 2021 
 */
public class ListErrorListener extends BaseErrorListener {
	
	private List<ErrorString> errorStrings = new ArrayList<>();
	
	public List<ErrorString> getErrorStrings() {
		return errorStrings;
	}
	
	public ListErrorListener() {
		errorStrings = new ArrayList<>();
	}
	
	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {		
		if (msg.startsWith("token recognition error")) {
			errorStrings.add(new ErrorString(charPositionInLine, 1, msg));
		} else {
			String[] lengthMessageArray = msg.split("\\|");
			errorStrings.add(new ErrorString(charPositionInLine, Integer.parseInt(lengthMessageArray[0]), lengthMessageArray[1]));
		}		
	}
	
	public boolean hasErrors() {
		return errorStrings.size() > 0;
	}
	
	public List<TokenError> getErrorList(String input, List<ErrorString> errorStrings) {
		List<TokenError> errorList = new ArrayList<>();
		errorStrings.forEach(
			            		errorString -> {
			            			if (errorString.message.startsWith("token recognition error")) {
			            				errorList.add(new TokenError(errorString.offset, 1, "Unrecognized token"));
			            			} else if (errorString.length == -1) {
			            				errorList.add(new TokenError(0, input.length(), errorString.message));
			            			} else {
			            				errorList.add(new TokenError(errorString.offset - errorString.length, errorString.length, errorString.message));
			            			}
			            		}
				            );
		return errorList;
	}
	
	
	class ErrorString {		
		int offset;
		int length;
		String message;
		ErrorString(int offset, int length, String message) {			
			this.offset = offset;
			this.length = length;
			this.message = message;
		}		
	}
	

}
