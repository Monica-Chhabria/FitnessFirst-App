package com.fitnessfirst.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "master_city")
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
/*	@Getter
	@Setter*/
private String code;
	public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
	public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
/*	@Getter
	@Setter*/
private String description;
/*	@Getter
	@Setter*/
private String country;
}
