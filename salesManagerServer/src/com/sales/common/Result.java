/**
 * 
 */
package com.sales.common;

/**
 * @author apple
 *
 */
public class Result {
	public final static int SUCESSED = 1;
	public final static int FAILED = -1;
	
	int result;
	String message;
	
	
	
	public Result() {
		super();
	}

	public Result(int result, String message) {
		super();
		this.result = result;
		this.message = message;
	}

	/**
	 * @return the result
	 */
	public int getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(int result) {
		this.result = result;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
