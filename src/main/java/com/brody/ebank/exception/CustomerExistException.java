package com.brody.ebank.exception;

public class CustomerExistException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public CustomerExistException(String message) {
		super(message);
	}

}
