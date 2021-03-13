package com.fitnessfirst.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fitnessfirst.model.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
	// public Exercise saveExercise(Exercise exercise);
	
	@Query(value = "SELECT SUM(calories) AS calories FROM EXERCISE WHERE username = ?1  and DATE_FORMAT(last_modified_date, \"%Y-%m-%d\") =?2", nativeQuery = true)
	  
	Optional<BigDecimal> findTotalCalBurntPerDay(String emailAddress,Date date);
	
	@Query(value = "SELECT * FROM EXERCISE WHERE username = ?1 and  DATE_FORMAT(last_modified_date, \"%Y-%m-%d\") =?2", nativeQuery = true)

	Optional<List<Exercise>>	findAllCalBurntPerDay(String emailAddress,Date date);


}
