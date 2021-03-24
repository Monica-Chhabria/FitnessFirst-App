package com.fitnessfirst.service.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.fitnessfirst.service.FoodDetailsService;
import org.springframework.stereotype.Service;

import com.fitnessfirst.dao.FoodDetailsRepository;
import com.fitnessfirst.model.FoodDetails;


@Service

public class FoodDetailsServiceImpl implements FoodDetailsService {

	@Autowired
	private FoodDetailsRepository foodDetailsRepository;
	public FoodDetailsRepository getFoodDetailsRepository() {
		return foodDetailsRepository;
	}
	public void setFoodDetailsRepository(FoodDetailsRepository foodDetailsRepository) {
		this.foodDetailsRepository = foodDetailsRepository;
	}
	@Override
	public FoodDetails saveFood(FoodDetails foodDetails) {
		// TODO Auto-generated method stub
		FoodDetails fd = this.foodDetailsRepository.save(foodDetails);
		return fd;
	}
	//function to get total cal per meal for current dsy
	 public BigDecimal findTotalCalPerMeal(String emailAddress,String meal){
		 Date date = new Date();
		 java.sql.Date sDate = new java.sql.Date(date.getTime());
	     //  System.out.println(sDate);
		 Optional<BigDecimal> totalCal = this.foodDetailsRepository.findTotalCalPerMeal(emailAddress, meal,sDate);
		 BigDecimal totalCalories =  totalCal.orElse(new BigDecimal(0));
		 
		 return totalCalories;
	 }
	 public List<FoodDetails> findAllCalPerMealPerDay(String emailAddress,String meal){
		 Date date = new Date();
		 java.sql.Date sDate = new java.sql.Date(date.getTime());
	     //  System.out.println(sDate);
		 Optional<List<FoodDetails>> totalCal =
		this.foodDetailsRepository.findAllCalPerMealPerDay(emailAddress, meal,sDate);
		// float totalCalories =  totalCal.orElse(new Float(0));
		// System.out.println(totalCal.get().size());
		 List<FoodDetails> foodDetails = new ArrayList<FoodDetails>();
		 if(totalCal.isPresent()){
			 return totalCal.get();
		 }
		 return foodDetails;
	 }
	@Override
	public BigDecimal findTotalCalPerDay(String emailAddress) {
		// TODO Auto-generated method stub
		 Date date = new Date();
		 java.sql.Date sDate = new java.sql.Date(date.getTime());
	     //  System.out.println(sDate);
		 Optional<BigDecimal> totalCal = this.foodDetailsRepository.findTotalCalPerDay(emailAddress,sDate);
		 BigDecimal totalCalories =  totalCal.orElse(new BigDecimal(0));
		 
		 return totalCalories;
		
	}
}
