package com.wissam.messaging.exception;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(CustomizedResponseEntityExceptionHandler.class);

	
	@ExceptionHandler(value = { ConstraintViolationException.class })

	public ResponseEntity<Object> handleConstraintViolationException(Exception ex) {

		logger.error("Exception: ", ex.getMessage());

		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(value = { Exception.class })

	public ResponseEntity<Object> handleException(Exception ex) {

		logger.error("Exception: ", ex.getMessage());

		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	//
}