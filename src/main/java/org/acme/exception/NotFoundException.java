package org.acme.exception;

public class NotFoundException extends CustomException{

	public NotFoundException() {
		super();
	}
	
	public NotFoundException(String message) {
		super(message);
	}
	
	public NotFoundException(String message, Exception e) {
		super(message, e);
	}
	
}
