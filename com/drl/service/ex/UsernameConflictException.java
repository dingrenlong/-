package com.drl.service.ex;

import java.io.Serializable;

public class UsernameConflictException extends ServiceException implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5561119804831166572L;

	public UsernameConflictException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsernameConflictException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UsernameConflictException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UsernameConflictException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UsernameConflictException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
