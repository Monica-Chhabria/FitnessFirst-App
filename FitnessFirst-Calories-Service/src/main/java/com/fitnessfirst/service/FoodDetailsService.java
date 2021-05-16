package com.fitnessfirst.service;

import com.fitnessfirst.model.FoodDetails;
import java.math.BigDecimal;
import java.util.List;




public interface FoodDetailsService {
  public FoodDetails saveFood(FoodDetails foodDetails);
  
  BigDecimal findTotalCalPerMeal(String emailAddress, String meal);
  
  List<FoodDetails> findAllCalPerMealPerDay(String emailAddress, String meal);
  
  BigDecimal findTotalCalPerDay(String emailAddress);
}
