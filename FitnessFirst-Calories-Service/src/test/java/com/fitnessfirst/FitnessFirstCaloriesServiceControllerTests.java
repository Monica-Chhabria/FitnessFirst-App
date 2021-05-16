package com.fitnessfirst;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitnessfirst.dao.FoodDetailsRepository;
import com.fitnessfirst.model.FoodDetails;
import com.fitnessfirst.service.FoodDetailsService;
import com.fitnessfirst.service.impl.FoodDetailsServiceImpl;
/*@RunWith(SpringRunner.class)
@SpringBootTest*/

@RunWith(SpringRunner.class)
@SpringBootTest
public  class FitnessFirstCaloriesServiceControllerTests {

  
  @Autowired
  private FoodDetailsServiceImpl foodservice;
  
  private MockMvc mockMvc;
  
  @Autowired
  private WebApplicationContext context;
  

  ObjectMapper om = new ObjectMapper();
  
  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }
  
/*  public FoodDetailsService getFoodservice() {
    return foodservice;
  }

  public void setFoodservice(FoodDetailsService foodservice) {
    this.foodservice = foodservice;
  }*/

/*  public FoodDetailsRepository getRepository() {
    return repository;
  }

  public void setRepository(FoodDetailsRepository repository) {
    this.repository = repository;
  }

  @MockBean
  private FoodDetailsRepository repository;
  
  
  public FoodDetailsServiceImpl getFoodservice() {
	return foodservice;
}

public void setFoodservice(FoodDetailsServiceImpl foodservice) {
	this.foodservice = foodservice;
}*/

/*@Test
	public void getUsersTest() {
	  FoodDetailsRepository localMockRepository = Mockito.mock(FoodDetailsRepository.class);
	   // Mockito.when(localMockRepository.count()).thenReturn(111L);
	  String emailAddress= "foo";
	  String meal = "Breakfast";
	  Date date = new Date();
	  java.sql.Date sDate = new java.sql.Date(date.getTime());
		 System.out.println("sdate "+sDate);
	 // findAllCalPerMealPerDay(String emailAddress,String meal,Date date)
		when(repository.findAllCalPerMealPerDay(emailAddress,meal,sDate)).thenReturn(Optional.of(Stream
				.of(new FoodDetails(), new FoodDetails()).
				collect(Collectors.toList())));
		 when(repository.findAllCalPerMealPerDay(emailAddress,meal,sDate)).thenReturn(Optional.of(Stream
					.of(new FoodDetails(), new FoodDetails()).
					collect(Collectors.toList()))); 
		assertEquals(2, foodservice.findAllCalPerMealPerDay(emailAddress, meal).size());
		// findTotalCalPerDay( emailAddress, date);
		// anyString(), anyInt(), any(Object.class)
		 //Mockito.anyString()
		 //java.sql.Date.class
		 Mockito.when(repository.findTotalCalPerDay(Mockito.anyString(), Mockito.any())).thenReturn(Optional.of(new BigDecimal(200)));

		 //Mockito.when(repository.findTotalCalPerDay(emailAddress,sDate)).thenReturn(Optional.of(new BigDecimal(200)));
		 assertEquals(new BigDecimal(200), foodservice.findTotalCalPerDay(emailAddress));
       //verify(repository).findTotalCalPerDay(emailAddress,sDate);
		 verify(repository).findTotalCalPerDay(Mockito.anyString(), Mockito.any());
  }*/


@Test
public void testAddFood() throws Exception {
	 //employee = new ();
	 FoodDetails food = new FoodDetails("foo", "mushroom", "Breakfast", new BigDecimal("200"),
				10, 10, 10, 10, 10,
				10);
	/*employee.setName("Basant");
	employee.setDept("IT");*/
	String jsonRequest = om.writeValueAsString(food);
	MvcResult result = mockMvc.perform(post("/api/saveFood").content(jsonRequest)
			.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();

	
	String resultContent = result.getResponse().getContentAsString();
	
	JsonFactory factory = om.getFactory();
    JsonParser parser = factory.createParser(resultContent);
    JsonNode actualObj = om.readTree(parser);
	
    Assert.assertEquals("success",actualObj.get("message").asText());
	System.out.println(resultContent);
	//Response response = om.readValue(resultContent, Response.class);
	//Assert.assertTrue(response.isStatus() == Boolean.TRUE);

}


@Test
public void testtotalCalPerMealPerDay() throws Exception {
	MvcResult result = mockMvc
			.perform(get("/api/totalCalPerMealPerDay/foo/BreakFast").content(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk()).andReturn();
	String resultContent = result.getResponse().getContentAsString();
	
	JsonFactory factory = om.getFactory();
    JsonParser parser = factory.createParser(resultContent);
    JsonNode actualObj = om.readTree(parser);
	
    Assert.assertEquals("success",actualObj.get("message").asText());
    //delta - the maximum delta between expected and actual for which both numbers are still considered equal.
    Assert.assertEquals(2000.00,actualObj.get("totalcalpermeal").asDouble(),2);

	//Assert.assertTrue(response.isStatus() == Boolean.TRUE);

}


}
