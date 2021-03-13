package com.fitnessfirst.model;



import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;


/*@Entity*/
@Entity
@Audited
public class FoodDetails extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	public FoodDetails() {
		super();
		// TODO Auto-generated constructor stub
	}


	@NotNull
	private int userid;
	private String username;
	public String getUsername() {
		return username;
	}
	public FoodDetails( String username, String foodname, String meal, BigDecimal calories,
			float total_fat, float cholesterol, float sugars, float protein, float potassium,
			float total_carbohydrate) {
		//super();

		this.username = username;
		this.foodname = foodname;
		this.meal = meal;
		this.calories = calories;
		this.total_fat = total_fat;
		this.cholesterol = cholesterol;
		this.sugars = sugars;
		this.protein = protein;
		this.potassium = potassium;
		this.total_carbohydrate = total_carbohydrate;
	}
	public void setUsername(String username) {
		this.username = username;
	}


    private String foodname;
	public String getFoodname() {
		return foodname;
	}
	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}


	private String meal;
   public String getMeal() {
		return meal;
	}
	public void setMeal(String meal) {
		this.meal = meal;
	}

	
	
    private BigDecimal calories;

	
	
    public BigDecimal getCalories() {
		return calories;
	}
	public void setCalories(BigDecimal calories) {
		this.calories = calories;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
		public float getTotal_fat() {
		return total_fat;
	}
	public void setTotal_fat(float total_fat) {
		this.total_fat = total_fat;
	}
	public float getCholesterol() {
		return cholesterol;
	}
	public void setCholesterol(float cholesterol) {
		this.cholesterol = cholesterol;
	}
	public float getSugars() {
		return sugars;
	}
	public void setSugars(float sugars) {
		this.sugars = sugars;
	}
	public float getProtein() {
		return protein;
	}
	public void setProtein(float protein) {
		this.protein = protein;
	}
	public float getPotassium() {
		return potassium;
	}
	public void setPotassium(float potassium) {
		this.potassium = potassium;
	}
	public float getTotal_carbohydrate() {
		return total_carbohydrate;
	}
	public void setTotal_carbohydrate(float total_carbohydrate) {
		this.total_carbohydrate = total_carbohydrate;
	}



	private float total_fat;
	
	
    private float cholesterol;
	
	
    private float sugars;
	
	
	private float protein;
	
	
	private float potassium;
	
	
	private float total_carbohydrate;

}
