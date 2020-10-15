package com.zelish.food.dto;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Data
public class FoodTruckInfoExcelData {
	private String locationId;
	private String applicant;
	private String facilityType;
	private String cnn;
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
	private String latitude;
	private String longitude;
	private String schedule;
	private String dayHours;
	private String noiSent;
	private String approved;
	private String received;
	private boolean priorPermit;
	private String expirationDate;
	private String location;
	private String firePrevention;
	private String policeDist;
	private String supervisor;
	private String zipCodes;
	private String neighbourHoods;
}
