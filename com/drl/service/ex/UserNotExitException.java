package com.drl.service.ex;


public class UserNotExitException extends ServiceException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4392999581795849778L;

	public UserNotExitException() {
		super();
	}

	public UserNotExitException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserNotExitException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotExitException(String message) {
		super(message);
	}

	public UserNotExitException(Throwable cause) {
		super(cause);
	}
	
}
