package com.restapi.restwithspringbootudemy.exception;

public class UnsupportedMathOperationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnsupportedMathOperationException(String exception) {
		super(exception);
	}

}
