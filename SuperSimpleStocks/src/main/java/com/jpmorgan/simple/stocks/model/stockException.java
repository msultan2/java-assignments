package com.jpmorgan.simple.stocks.model;

/** Exception Type in case wrong/invalids passed parameters 
 * or the process does't follow the agreed one 
 * 
 * @author msultan
 * 
 */

public class stockException extends Exception {

	public stockException() {
		// TODO Auto-generated constructor stub
	}

	public stockException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}