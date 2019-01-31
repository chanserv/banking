package com.sg.banking.exceptions;

/*
 * The exception is of Runtime type because I supposed that there is no way to recover from it
 */
public class OverdraftLimitException extends RuntimeException {

	private static final long serialVersionUID = 328463994164525420L;
	
	public OverdraftLimitException(String message) {
		super(message);
	}
}
