package com.zelish.food.model;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class FoodTruckInfo {
	private Long locationId;
	private String applicant;
	private String facilityType;
	private long cnn;
	private String locationDescription;
	private String address;
	private String blocklot;
	private String block;
	private String lot;
	private String permit;
	private String status;
	private String foodItems;
	private String x;
	private String y;
	private long latitude;
	private long longitude;
	private String schedule;
	private Date noiSent;
	private Date approved;
	private Date received;
	private boolean priorPermit;
	private Date expirationDate;
	private String location;
}
