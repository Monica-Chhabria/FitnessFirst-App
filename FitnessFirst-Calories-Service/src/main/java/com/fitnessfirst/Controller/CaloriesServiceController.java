package com.fitnessfirst.Controller;

import java.util.List;

import static org.mockito.Mockito.ignoreStubs;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.fitnessfirst.*;

import com.fitnessfirst.MyUserDetailsService;
import com.fitnessfirst.model.AuthenticationRequest;
import com.fitnessfirst.model.AuthenticationResponse;
import com.fitnessfirst.model.FoodDetails;
import com.fitnessfirst.model.Nutrient;
import com.fitnessfirst.model.NutrientInput;
import com.fitnessfirst.model.NutrientOutput;
import com.fitnessfirst.model.UserDetail;
import com.fitnessfirst.service.FoodDetailsService;
import com.fitnessfirst.util.JwtUtil;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class CaloriesServiceController {
	
	
/*
	@Autowired
	private AuthenticationManager authenticationManager;
*/
	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;
	@Autowired
	private Environment env;
	 @Autowired
	   RestTemplate restTemplate;
	@Autowired
	private FoodDetailsService foodDetailsService;


	/*@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
System.out.println("inside authenticate"+authenticationRequest.getUsername()+ " "+authenticationRequest.getPassword());
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);

		}

		catch (BadCredentialsException e) {

			throw new Exception("Incorrect username or password", e);

		}


		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}*/


public FoodDetailsService getFoodDetailsService() {
		return foodDetailsService;
	}

	public void setFoodDetailsService(FoodDetailsService foodDetailsService) {
		this.foodDetailsService = foodDetailsService;
	}

	/*
	   @RequestMapping(value = "/template/products", method = RequestMethod.POST)
	   public String createProducts(@RequestBody Product product) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Product> entity = new HttpEntity<Product>(product,headers);
	      
	      return restTemplate.exchange(
	         "http://localhost:8080/products", HttpMethod.POST, entity, String.class).getBody();
	      String fooResourceUrl
	      = "https://trackapi.nutritionix.com/v2/natural/nutrients";
	    ResponseEntity<String> response
	      = restTemplate.getForEntity(fooResourceUrl + "/1", String.class);
	    assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	   
	   }*/
 /*   @GetMapping("/")
    public void  getnutrients() {
		// TODO Auto-generated method stub
System.out.println("inside getnutrients");
	}
    */
    @PostMapping("/saveFood")
    public ResponseEntity<FoodDetails> saveFoodDetails(@RequestBody FoodDetails foodDetails)
    { 	 HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    HttpEntity <String> entity = new HttpEntity<String>(headers);
    final String uri =  env.getProperty("userdetailsfetchuser.url");
    UserDetail userDetail =  restTemplate.exchange(uri+foodDetails.getUsername(), HttpMethod.GET, entity, UserDetail.class).getBody();
	
    	foodDetails.setUserid(userDetail.getId());
    	FoodDetails foodDetails2 = this.foodDetailsService.saveFood(foodDetails);
    	return ResponseEntity.ok(foodDetails2);
    	
    }
    
    
    @GetMapping("/totalCalPerMealPerDay/{username}/{meal}")
	public  ResponseEntity<?> getTotalCaloriesPerMeal(@PathVariable("username")String email,@PathVariable("meal")String meal) throws JSONException
	{
    	BigDecimal totalcalpermeal =  this.foodDetailsService.findTotalCalPerMeal(email, meal);
    	JSONObject jo = new JSONObject();

        totalcalpermeal =totalcalpermeal.setScale(2,BigDecimal.ROUND_HALF_DOWN);
    	 Map<String, Object> json = new HashMap<String, Object>();
         json.put("success", true);
         //System.out.println("after"+totalcalpermeal);
         json.put("totalcalpermeal", totalcalpermeal);
    	jo.put("totalcalpermeal", totalcalpermeal);
    	//return new ResponseEntity<>(jo, HttpStatus.OK);
        return new ResponseEntity<Map<String,Object>>(json,HttpStatus.CREATED);

    	//return ResponseEntity.ok(jo) ;
	}
    @GetMapping("/calPerMealPerDay/{username}/{meal}")
	public  ResponseEntity<?> findAllCalPerMealPerDay(@PathVariable("username")String email,@PathVariable("meal")String meal) throws JSONException
	{
    	List<FoodDetails> fooddetails =  this.foodDetailsService.findAllCalPerMealPerDay(email, meal);
List<FoodDetails>    foodDetails2 = 	fooddetails.stream().map(fooddetail -> {fooddetail.setCalories(fooddetail.getCalories().setScale(2, BigDecimal.ROUND_DOWN)); return fooddetail;}).collect(Collectors.toList());
    	float totalcalpermeal = 0;
    	JSONObject jo = new JSONObject();
    	 Map<String, Object> json = new HashMap<String, Object>();
         json.put("success", true);
         json.put("calpermeal", foodDetails2);
    	jo.put("calpermeal", totalcalpermeal);
    	
    	//return new ResponseEntity<>(jo, HttpStatus.OK);
        return new ResponseEntity<Map<String,Object>>(json,HttpStatus.CREATED);

    	//return ResponseEntity.ok(jo) ;
	}
	@GetMapping("/nutrients/{food}")
	public ResponseEntity<NutrientOutput> getNutrientService(@PathVariable("food")String food)
	{
		// uri = "https://trackapi.nutritionix.com/v2/natural/nutrients";
		 final String  uri = env.getProperty("nutritionix.url");	
		//	String uri  = "";
		Nutrient nutrient = new Nutrient();
		NutrientInput nutrientInput = new NutrientInput(food, "US/Eastern");
	/*	I suggest using one of the exchange methods that accepts an HttpEntity for which you can also set the HttpHeaders. (You can also specify the HTTP method you want to use.)

		For example,

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
*
		HttpEntity<String> entity = new HttpEntity<>("body", headers);*/
		// set `content-type` header
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		headers.setContentType(MediaType.APPLICATION_JSON);

	    headers.set("x-app-id", env.getProperty("nutritionix.appid"));
	    headers.set("x-app-key",env.getProperty("nutritionix.appkey"));
	
		HttpEntity<NutrientInput> entity = new HttpEntity<>(nutrientInput, headers);
		// set `accept` header
		//headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		NutrientOutput result = restTemplate.postForObject( uri, entity, NutrientOutput.class);

	    return ResponseEntity.ok(result);
		//String result = restTemplate.getForObject(uri, String.class);
		 //restTemplate.getForEntity(uri, EmployeeListVO.class);
	}
}
