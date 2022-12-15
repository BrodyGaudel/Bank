package com.brody.ebank.exception;

public class AccountSuspendedException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public AccountSuspendedException(String message) {
		super(message);
	}

}
