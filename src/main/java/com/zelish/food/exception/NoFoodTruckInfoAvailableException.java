package com.zelish.food.exception;

public class NoFoodTruckInfoAvailableException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NoFoodTruckInfoAvailableException() {
	}

	public NoFoodTruckInfoAvailableException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoFoodTruckInfoAvailableException(String message) {
		super(message);
	}

}
