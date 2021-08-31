package main;

/**
 * @author Lin Zhang
 * Programmer / Software Development Engineer
 * Institute for Informatics
 * Washington University School of Medicine in St. Louis
 * 
 * Date: August 30, 2021 
 */
public class TokenError {
	private int offsetStart;
	private int length;
	private String message;
	
	public TokenError(int offsetStart, int length, String message) {			
		this.offsetStart = offsetStart;
		this.length = length;
		this.message = message;
	}
	
	public String toString(String input) {
		return input.substring(offsetStart, offsetStart + length) + ": " + message;
	}

	public int getOffsetStart() {
		return offsetStart;
	}

	public void setOffsetStart(int offsetStart) {
		this.offsetStart = offsetStart;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
