package org.acme.exception;

public class InternalServerErrorException extends CustomException{

	public InternalServerErrorException() {
		super();
	}
	
	public InternalServerErrorException(String message) {
		super(message);
	}
	
	public InternalServerErrorException(String message, Exception e) {
		super(message, e);
	}
	
}
