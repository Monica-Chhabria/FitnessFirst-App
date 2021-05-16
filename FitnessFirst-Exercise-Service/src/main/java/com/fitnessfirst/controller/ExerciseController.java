package com.fitnessfirst.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fitnessfirst.model.Exercise;
import com.fitnessfirst.model.UserDetail;
import com.fitnessfirst.service.ExerciseDetailsService;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExerciseController {
	 @Autowired
	   RestTemplate restTemplate;
	 @Autowired
		private ExerciseDetailsService exerciseDetailsService;
		public ExerciseDetailsService getExerciseDetailsService() {
		return exerciseDetailsService;
	}
	public void setExerciseDetailsService(ExerciseDetailsService exerciseDetailsService) {
		this.exerciseDetailsService = exerciseDetailsService;
	}
		@Autowired
		private Environment env;
	  @PostMapping("/saveExercise")
	    public ResponseEntity<?> saveFoodDetails(@RequestBody Exercise exercise)
	    { 	 HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity <String> entity = new HttpEntity<String>(headers);
	    final String uri =  env.getProperty("userdetailsfetchuser.url");
	    UserDetail userDetail =  restTemplate.exchange(uri+exercise.getUsername(), HttpMethod.GET, entity, UserDetail.class).getBody();
	     if(null != userDetail){
	    exercise.setUserid(userDetail.getId());
	     }
	     Map<String, Object> json = new HashMap<String, Object>();
	        json.put("message", "failure");
	    Exercise exercisdedet= this.exerciseDetailsService.saveExercise(exercise);
	  
	    
	    if(exercisdedet != null){
	    	 json.put("message", "success");
	        return new ResponseEntity<Map<String,Object>>(json,HttpStatus.OK);
	
	    }	
	    return ResponseEntity.ok(json);
	    	
	    }
	  @GetMapping("/calBurntPerDay/{username}")
		public  ResponseEntity<?> findAllCalPerDay(@PathVariable("username")String email) throws JSONException
		{
	    	List<Exercise> exercisedetails =  this.exerciseDetailsService.findAllCalPerDay(email);
	List<Exercise>    exercisedet2 = 	exercisedetails.stream().map(exercisedetail -> {exercisedetail.setCalories(exercisedetail.getCalories().setScale(2, BigDecimal.ROUND_DOWN)); return exercisedetail;}).collect(Collectors.toList());
	
	    	//JSONObject jo = new JSONObject();
	    	 Map<String, Object> json = new HashMap<String, Object>();
	         json.put("message", "success");
	         json.put("calburnt", exercisedet2);
	    	/*jo.put("calpermeal", totalcal);*/
	    	
	    	//return new ResponseEntity<>(jo, HttpStatus.OK);
	        return new ResponseEntity<Map<String,Object>>(json,HttpStatus.OK);

	    	//return ResponseEntity.ok(jo) ;
		}
	  
	  
	  
	    @GetMapping("/totalCalBurntPerDay/{username}")
		public  ResponseEntity<?> getTotalCaloriesPerMeal(@PathVariable("username")String email) throws JSONException
		{
	    	BigDecimal totalcalpermeal =  this.exerciseDetailsService.findTotalCalPerDay(email);
	    	JSONObject jo = new JSONObject();

	        totalcalpermeal =totalcalpermeal.setScale(2,BigDecimal.ROUND_HALF_DOWN);
	    	 Map<String, Object> json = new HashMap<String, Object>();
	        // json.put("success", true);
	         json.put("message", "success");

	         //System.out.println("after"+totalcalpermeal);
	         json.put("totalcalburnt", totalcalpermeal);
	    	jo.put("totalcalburnt", totalcalpermeal);
	    	//return new ResponseEntity<>(jo, HttpStatus.OK);
	        return new ResponseEntity<Map<String,Object>>(json,HttpStatus.OK);

	    	//return ResponseEntity.ok(jo) ;
		}
}
