package com.restapi.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * class implements the exception that thrown during the person not found
 * 
 * @author amit chauhan
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PersonNotFoundException(String message) {
		super(message);
	}
}
