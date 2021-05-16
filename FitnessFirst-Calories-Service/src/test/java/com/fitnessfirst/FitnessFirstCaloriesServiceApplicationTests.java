package com.fitnessfirst;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 https://github.com/Java-Techie-jt/spring-boot-mockito
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fitnessfirst.dao.FoodDetailsRepository;
import com.fitnessfirst.model.FoodDetails;
import com.fitnessfirst.service.FoodDetailsService;
import com.fitnessfirst.service.impl.FoodDetailsServiceImpl;
/*@RunWith(SpringRunner.class)
@SpringBootTest*/

@RunWith(SpringRunner.class)
@SpringBootTest
public  class FitnessFirstCaloriesServiceApplicationTests {

  @Autowired
  private FoodDetailsService foodservice;



  public FoodDetailsService getFoodservice() {
	return foodservice;
}

public void setFoodservice(FoodDetailsService foodservice) {
	this.foodservice = foodservice;
}

public FoodDetailsRepository getRepository() {
    return repository;
  }

  public void setRepository(FoodDetailsRepository repository) {
    this.repository = repository;
  }

  @MockBean
  private FoodDetailsRepository repository;
  
  @Test
	public void testAllCalPerMealPerDay() {
	/*  FoodDetailsRepository localMockRepository = Mockito.mock(FoodDetailsRepository.class);*/
	   // Mockito.when(localMockRepository.count()).thenReturn(111L);
    String emailAddress = "foo";
    String meal = "Breakfast";
	Date date = new Date();
    java.sql.Date sDate = new java.sql.Date(date.getTime());
	
	 // findAllCalPerMealPerDay(String emailAddress,String meal,Date date)
/*		when(repository.findAllCalPerMealPerDay(emailAddress,meal,sDate)).thenReturn(Optional.of(Stream
				.of(new FoodDetails(), new FoodDetails()).
				collect(Collectors.toList())));*/
/*		 when(repository.findAllCalPerMealPerDay(emailAddress,meal,sDate)).thenReturn(Optional.of(Stream
					.of(new FoodDetails(), new FoodDetails()).
					collect(Collectors.toList()))); 
		assertEquals(2, foodservice.findAllCalPerMealPerDay(emailAddress, meal).size());*/
		// findTotalCalPerDay( emailAddress, date);
		// anyString(), anyInt(), any(Object.class)
		 //Mockito.anyString()
		 //java.sql.Date.class
		 Mockito.when(repository.findAllCalPerMealPerDay(Mockito.anyString(),Mockito.anyString(), Mockito.any())).thenReturn(Optional.of(Stream.of(new FoodDetails("foo", "mushroom", "Breakfast", new BigDecimal("200"),
					10, 10, 10, 10, 10,
					10)).collect(Collectors.toList())));

		 //Mockito.when(repository.findTotalCalPerDay(emailAddress,sDate)).thenReturn(Optional.of(new BigDecimal(200)));
		 assertEquals(1, foodservice.findAllCalPerMealPerDay(emailAddress,meal).size());
       //verify(repository).findTotalCalPerDay(emailAddress,sDate);
		 verify(repository).findAllCalPerMealPerDay(Mockito.anyString(),Mockito.anyString(), Mockito.any());
  }
  

  @Test
  public void testTotalCalPerDay() {
	/*  FoodDetailsRepository localMockRepository = Mockito.mock(FoodDetailsRepository.class);*/
	   // Mockito.when(localMockRepository.count()).thenReturn(111L);
    String emailAddress = "foo";
    //String meal = "Breakfast";
	Date date = new Date();
    java.sql.Date sDate = new java.sql.Date(date.getTime());
	
	 // findAllCalPerMealPerDay(String emailAddress,String meal,Date date)
/*		when(repository.findAllCalPerMealPerDay(emailAddress,meal,sDate)).thenReturn(Optional.of(Stream
				.of(new FoodDetails(), new FoodDetails()).
				collect(Collectors.toList())));*/
/*		 when(repository.findAllCalPerMealPerDay(emailAddress,meal,sDate)).thenReturn(Optional.of(Stream
					.of(new FoodDetails(), new FoodDetails()).
					collect(Collectors.toList()))); 
		assertEquals(2, foodservice.findAllCalPerMealPerDay(emailAddress, meal).size());*/
		// findTotalCalPerDay( emailAddress, date);
		// anyString(), anyInt(), any(Object.class)
		 //Mockito.anyString()
		 //java.sql.Date.class
		 Mockito.when(repository.findTotalCalPerDay(Mockito.anyString(), Mockito.any())).thenReturn(Optional.of(new BigDecimal(200)));

		 //Mockito.when(repository.findTotalCalPerDay(emailAddress,sDate)).thenReturn(Optional.of(new BigDecimal(200)));
		 assertEquals(new BigDecimal(200), foodservice.findTotalCalPerDay(emailAddress));
       //verify(repository).findTotalCalPerDay(emailAddress,sDate);
		 verify(repository).findTotalCalPerDay(Mockito.anyString(), Mockito.any());
  }
  @Test
	public void testSaveFood() {

  FoodDetails food = new FoodDetails("foo", "mushroom", "Breakfast", new BigDecimal("200"),
			10, 10, 10, 10, 10,
			10);
  when(repository.save(food)).thenReturn(food);
 
   }
  
  @Test
 	public void testTotalCalPerMeal() {
 	/*  FoodDetailsRepository localMockRepository = Mockito.mock(FoodDetailsRepository.class);*/
 	   // Mockito.when(localMockRepository.count()).thenReturn(111L);
     String emailAddress = "foo";
     String meal = "Breakfast";
 	Date date = new Date();
     java.sql.Date sDate = new java.sql.Date(date.getTime());
 	
 	 // findAllCalPerMealPerDay(String emailAddress,String meal,Date date)
 /*		when(repository.findAllCalPerMealPerDay(emailAddress,meal,sDate)).thenReturn(Optional.of(Stream
 				.of(new FoodDetails(), new FoodDetails()).
 				collect(Collectors.toList())));*/
 /*		 when(repository.findAllCalPerMealPerDay(emailAddress,meal,sDate)).thenReturn(Optional.of(Stream
 					.of(new FoodDetails(), new FoodDetails()).
 					collect(Collectors.toList()))); 
 		assertEquals(2, foodservice.findAllCalPerMealPerDay(emailAddress, meal).size());*/
 		// findTotalCalPerDay( emailAddress, date);
 		// anyString(), anyInt(), any(Object.class)
 		 //Mockito.anyString()
 		 //java.sql.Date.class
 		 Mockito.when(repository.findTotalCalPerMeal(Mockito.anyString(),Mockito.anyString() ,Mockito.any())).thenReturn(Optional.of(new BigDecimal(200)));

 		 //Mockito.when(repository.findTotalCalPerDay(emailAddress,sDate)).thenReturn(Optional.of(new BigDecimal(200)));
 		 assertEquals(new BigDecimal(200), foodservice.findTotalCalPerMeal(emailAddress,meal));
        //verify(repository).findTotalCalPerDay(emailAddress,sDate);
 		 verify(repository).findTotalCalPerMeal(Mockito.anyString(), Mockito.anyString(),Mockito.any());
   }

}
