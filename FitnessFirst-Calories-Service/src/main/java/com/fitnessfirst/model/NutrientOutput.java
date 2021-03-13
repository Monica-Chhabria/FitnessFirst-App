package com.fitnessfirst.model;

public class NutrientOutput {

	Nutrient[] foods;
	public Nutrient[] getFoods() {
		return foods;
	}
	public void setFoods(Nutrient[] foods) {
		this.foods = foods;
	}
	public String getErrors() {
		return errors;
	}
	public void setErrors(String errors) {
		this.errors = errors;
	}
	private String errors;
}
