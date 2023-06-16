package com.facens.cursosaf.application.exception;

import org.springframework.http.HttpStatus;
import lombok.Getter;

@Getter
public class BadRequestException extends GenericException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadRequestException(String message, HttpStatus status) {
        super(message, status);
    }
}
