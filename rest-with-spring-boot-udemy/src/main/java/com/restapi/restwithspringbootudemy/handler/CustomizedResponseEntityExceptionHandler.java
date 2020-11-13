package com.restapi.restwithspringbootudemy.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.restapi.restwithspringbootudemy.exception.ExceptionResponse;
import com.restapi.restwithspringbootudemy.exception.InvalidJwtAuthenticationException;
import com.restapi.restwithspringbootudemy.exception.UnsupportedMathOperationException;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExecption(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UnsupportedMathOperationException.class)
	public final ExceptionResponse handleUnsupportedMathOperationExecption(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return response;
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidJwtAuthenticationException.class)
	public final ExceptionResponse handleInvalidJwtAuthenticationException(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return response;
	}
}
