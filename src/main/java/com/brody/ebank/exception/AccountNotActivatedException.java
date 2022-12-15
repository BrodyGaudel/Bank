package com.brody.ebank.exception;

public class AccountNotActivatedException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public AccountNotActivatedException(String message) {
		super(message);
	}

}
