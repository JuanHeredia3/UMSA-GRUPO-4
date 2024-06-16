package org.acme.exception;

import java.io.Serializable;

public class CustomException extends Exception implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public CustomException() {
		super();
	}
	
	public CustomException(String message) {
		super(message);
	}
	
	public CustomException(String message, Exception e) {
		super(message, e);
	}
	
}
