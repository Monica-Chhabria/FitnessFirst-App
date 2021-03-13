package com.fitnessfirst.service;

import java.math.BigDecimal;
import java.util.List;

import com.fitnessfirst.model.FoodDetails;

public interface FoodDetailsService {
 public FoodDetails saveFood(FoodDetails foodDetails);
 BigDecimal findTotalCalPerMeal(String emailAddress,String meal);
 List<FoodDetails> findAllCalPerMealPerDay(String emailAddress,String meal);
}
