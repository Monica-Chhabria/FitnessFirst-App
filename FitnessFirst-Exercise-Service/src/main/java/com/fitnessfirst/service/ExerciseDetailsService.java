package com.fitnessfirst.service;

import java.math.BigDecimal;
import java.util.List;

import com.fitnessfirst.model.Exercise;

public interface ExerciseDetailsService {
	 public Exercise saveExercise(Exercise exercise);
	 BigDecimal findTotalCalPerDay(String emailAddress);
	 List<Exercise> findAllCalPerDay(String emailAddress);
}
