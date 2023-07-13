package com.tracker.app.exception;

public class TrackerException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5154372896261714252L;
	private String message;

	public TrackerException(String message) {
		super();
		this.message = message;
	}

	public TrackerException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
