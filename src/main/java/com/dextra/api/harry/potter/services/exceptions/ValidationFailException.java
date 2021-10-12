package com.dextra.api.harry.potter.services.exceptions;

public class ValidationFailException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ValidationFailException(String msg) {
		super(msg);
	}
}
