package com.fitnessfirst.dao;


import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fitnessfirst.model.FoodDetails;
@CrossOrigin(origins = "*", allowedHeaders = "*")

public interface FoodDetailsRepository extends JpaRepository<FoodDetails, Integer> {
	  
	
	//@Query(value = "SELECT SUM(calories) AS calories FROM FOOD_DETAILS WHERE username = ?1 and meal =?2 and last_modified_date =?3", nativeQuery = true)
	@Query(value = "SELECT SUM(calories) AS calories FROM FOOD_DETAILS WHERE username = ?1 and meal =?2 and DATE_FORMAT(last_modified_date, \"%Y-%m-%d\") =?3", nativeQuery = true)
	Optional<BigDecimal> findTotalCalPerMeal(String emailAddress,String meal,Date date);
	
	@Query(value = "SELECT SUM(calories) AS calories FROM FOOD_DETAILS WHERE username = ?1  and DATE_FORMAT(last_modified_date, \"%Y-%m-%d\") =?2", nativeQuery = true)
	Optional<BigDecimal> findTotalCalPerDay(String emailAddress,Date date);
	
	@Query(value = "SELECT * FROM FOOD_DETAILS WHERE username = ?1  and DATE_FORMAT(last_modified_date, \"%Y-%m-%d\") =?2", nativeQuery = true)
	Optional<List<FoodDetails>>	findAllCalPerMealPerDay(String emailAddress,String meal,Date date);
}
