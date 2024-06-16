package org.acme.exception;

public class BadRequestException extends CustomException{

	public BadRequestException() {
		super();
	}
	
	public BadRequestException(String message) {
		super(message);
	}
	
	public BadRequestException(String message, Exception e) {
		super(message, e);
	}
	
}
