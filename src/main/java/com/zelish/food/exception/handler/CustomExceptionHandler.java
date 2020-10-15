package com.zelish.food.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> noFoodTruckInfoAvailableException(Exception exception,
			WebRequest webRequest) {
		ExceptionFormat exceptionFormat = new ExceptionFormat(new Date(),exception.getMessage(),webRequest.getDescription(false));
		return new ResponseEntity(exceptionFormat,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
