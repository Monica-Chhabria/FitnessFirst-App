package com.fitnessfirst.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fitnessfirst.dao.UserRepository;
import com.fitnessfirst.model.City;
import com.fitnessfirst.model.User;
import com.fitnessfirst.service.MasterService;
import com.fitnessfirst.service.impl.UserServiceImpl;

import lombok.Getter;
import lombok.Setter;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	

	public BCryptPasswordEncoder getbCryptPasswordEncoder() {
		return bCryptPasswordEncoder;
	}

	public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	@Autowired
    @Getter
    @Setter
    private MasterService masterService;
	@Autowired
	private UserServiceImpl userservice;
	public UserServiceImpl getUserservice() {
		return userservice;
	}

	public void setUserservice(UserServiceImpl userservice) {
		this.userservice = userservice;
	}
	@Autowired
	private UserRepository userepo;
	
	public UserRepository getUserepo() {
		return userepo;
	}

	public void setUserepo(UserRepository userepo) {
		this.userepo = userepo;
	}
	@GetMapping("/fetchUser/{id}")
	public ResponseEntity<User> fetchUser(@PathVariable("id") int id)
	{
		User user = this.userservice.fetchUser(id);
		return ResponseEntity.ok(user);
		
		
	}
	
	
	//rest end point t
	@GetMapping("/fetchUserByEmail/{email}")
	public ResponseEntity<User> fetchUserByEmail(@PathVariable("email") String  email)
	{
		User user = this.userservice.fetchUserByEmail(email);
		return ResponseEntity.ok(user);
		
		
	}
	@PostMapping("/saveUser")
	public ResponseEntity<User> saveUser(@RequestBody User u)
	{
		u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
		User user = this.userservice.saveUser(u);
		return ResponseEntity.ok(user);

		
	}
	
	
	
	
	@GetMapping("/fetchCity")
	public ResponseEntity<List<City>> fetchCity()
	{
		List<City> city = this.masterService.fetchCity();
		System.out.println(city.size());
	/*	for (City cit : city) {
		    System.out.println(cit.get);
		}*/
		//return ResponseEntity.ok(city);
		return ResponseEntity.of(Optional.of(city)); 

		
	}
}
