package com.fitnessfirst.model;

public class NutrientInput {
private String query;
public String getQuery() {
	return query;
}
public void setQuery(String query) {
	this.query = query;
}
public NutrientInput(String query, String timezone) {
	super();
	this.query = query;
	this.timezone = timezone;
}
public String getTimezone() {
	return timezone;
}
public void setTimezone(String timezone) {
	this.timezone = timezone;
}
private String timezone ;
}
