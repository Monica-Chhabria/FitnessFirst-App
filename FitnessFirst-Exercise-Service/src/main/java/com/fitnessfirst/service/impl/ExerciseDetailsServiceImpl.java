package com.fitnessfirst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitnessfirst.dao.ExerciseRepository;
import com.fitnessfirst.model.Exercise;
import com.fitnessfirst.service.ExerciseDetailsService;
@Service
public class ExerciseDetailsServiceImpl implements ExerciseDetailsService {

	
	@Autowired
	private ExerciseRepository exerciseRepository;
	public ExerciseRepository getExerciseRepository() {
		return exerciseRepository;
	}

	public void setExerciseRepository(ExerciseRepository exerciseRepository) {
		this.exerciseRepository = exerciseRepository;
	}

	@Override
	public Exercise saveExercise(Exercise exercise) {
		// TODO Auto-generated method stub
		Exercise exercise2 =this.exerciseRepository.save(exercise);
		return exercise2;
	}

	@Override
	public BigDecimal findTotalCalPerDay(String emailAddress) {
		// TODO Auto-generated method stub
		//this.exerciseRepository.findTotalCalBurntPerDay(emailAddress, meal, date);
	/*	 Date date = new Date();
		 java.sql.Date sDate = new java.sql.Date(date.getTime());
	     //  System.out.println(sDate);
		 Optional<List<Exercise>> totalCal = this.exerciseRepository.findTotalCalBurntPerDay(emailAddress, meal,sDate);
		// float totalCalories =  totalCal.orElse(new Float(0));
		// System.out.println(totalCal.get().size());
		 List<Exercise> foodDetails = new ArrayList<FoodDetails>();
		 if(totalCal.isPresent())
		 {
			 return totalCal.get();
		 }
		 return foodDetails;
		
		
		return null;*/
		 Date date = new Date();
		 java.sql.Date sDate = new java.sql.Date(date.getTime());
	     //  System.out.println(sDate);
		 Optional<BigDecimal> totalCal = this.exerciseRepository.findTotalCalBurntPerDay(emailAddress,sDate);
		 BigDecimal totalCalories =  totalCal.orElse(new BigDecimal(0));
		 return totalCalories;
	}

	@Override
	public List<Exercise> findAllCalPerDay(String emailAddress) {
		// TODO Auto-generated method stub
		 Date date = new Date();
		 java.sql.Date sDate = new java.sql.Date(date.getTime());
	     //  System.out.println(sDate);
		 Optional<List<Exercise>> totalCal = this.exerciseRepository.findAllCalBurntPerDay(emailAddress,sDate);
		// float totalCalories =  totalCal.orElse(new Float(0));
		// System.out.println(totalCal.get().size());
		 List<Exercise> exDetails = new ArrayList<Exercise>();
		 if(totalCal.isPresent())
		 {
			 return totalCal.get();
		 }
		 return exDetails;
	}

	

}
