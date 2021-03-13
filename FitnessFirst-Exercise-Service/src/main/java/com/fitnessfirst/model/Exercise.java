package com.fitnessfirst.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;


@Entity
@Audited
public class Exercise extends Auditable<String>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private int userid;
	private String username;
	private String exercise;
	   private BigDecimal calories;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public BigDecimal getCalories() {
		return calories;
	}
	public void setCalories(BigDecimal calories) {
		this.calories = calories;
	}
/*	public String getExercisedetails() {
		return exercisedetails;
	}
	public void setExercisedetails(String exercisedetails) {
		this.exercisedetails = exercisedetails;
	}*/
	public String getUsername() {
		return username;
	}
	public String getExercise() {
		return exercise;
	}
	public void setExercise(String exercise) {
		this.exercise = exercise;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Exercise() {
		super();
		// TODO Auto-generated constructor stub
	}
}
