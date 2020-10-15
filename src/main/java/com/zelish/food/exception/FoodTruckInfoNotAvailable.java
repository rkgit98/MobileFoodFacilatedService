package com.zelish.food.exception;

public class FoodTruckInfoNotAvailable extends RuntimeException{

	public FoodTruckInfoNotAvailable() {
		super();
	}

	public FoodTruckInfoNotAvailable(String message, Throwable cause) {
		super(message, cause);
	}

	public FoodTruckInfoNotAvailable(String message) {
		super(message);
	}
	 
	
}
