package com.zelish.food.exception.handler;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * This is used to format the exception message as part of exception information
 * 
 * @author ranjit
 *
 */
@AllArgsConstructor
@Setter
@Getter
public class ExceptionFormat {
	private Date date;
	private String message;
	private String description;
}
