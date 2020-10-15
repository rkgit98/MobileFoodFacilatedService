package com.zelish.food.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

@Data
@Entity
@Table(name = "food_truck_info")
public class FoodTruckInfoEntity implements Serializable {
	@Id
	@Column(name = "location_id", insertable = false)
	protected Long locationId;
	protected String applicant;
	@Column(name = "facility_type")
	protected String facilityType;
	@ColumnDefault(value = "0")
	protected long cnn;
	@Column(name = "location_description")
	protected String locationDescription;
	protected String address;
	protected String blocklot;
	protected String block;
	protected String lot;
	protected String permit;
	protected String status;
	@Column(name = "food_items",length = 500)
	protected String foodItems;
	protected String x;
	protected String y;
	@ColumnDefault(value = "0")
	protected long latitude;
	@ColumnDefault(value = "0")
	protected long longitude;
	protected String schedule;
	@Column(name = "noi_sent")
	protected Date noiSent;
	protected Date approved;
	protected Date received;
	@Column(name = "prior_permit")
	@ColumnDefault(value = "true")
	protected boolean priorPermit;
	@Column(name = "expiration_date")
	protected Date expirationDate;
	protected String location;
}
