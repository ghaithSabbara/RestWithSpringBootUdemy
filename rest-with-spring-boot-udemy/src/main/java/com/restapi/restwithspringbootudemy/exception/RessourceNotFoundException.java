package com.restapi.restwithspringbootudemy.exception;

public class RessourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RessourceNotFoundException(String exception) {
		super(exception);
	}

}
