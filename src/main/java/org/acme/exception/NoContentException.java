package org.acme.exception;

public class NoContentException extends CustomException{

	public NoContentException() {
		super();
	}
	
	public NoContentException(String message) {
		super(message);
	}
	
	public NoContentException(String message, Exception e) {
		super(message, e);
	}
	
}
