package com.fitnessfirst.service.impl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitnessfirst.dao.UserRepository;
import com.fitnessfirst.exception.UserNotFoundException;
import com.fitnessfirst.model.User;
import com.fitnessfirst.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
private UserRepository userrepo;

	public UserRepository getUserrepo() {
		return userrepo;
	}

	public void setUserrepo(UserRepository userrepo) {
		this.userrepo = userrepo;
	}
	
	//function for saving userdetails
	public User saveUser(User u)
	
	{
		
	User user =	this.userrepo.save(u);
	return user;	
		
	}
	
public User fetchUser(int id)
	
	{
		
	Optional<User> user =	this.userrepo.findById(id);
	return user.get();	
		
	}
//function to get user details from email
public User fetchUserByEmail(String email) throws UserNotFoundException 
{
	Optional<User> user;
//	System.out.println("email"+email);
	
	user =	this.userrepo.getUserFromEmail(email);
	if(user.isPresent())
	{
		return user.get();	
	}
	else
	{
		throw new UserNotFoundException();
	
	}
/*	catch(NoSuchElementException ex)
	{
		try {
			throw new UserNotFoundException();

	}*/
	/*if()
	{UserNotFoundException}
	else {
	throw new Exception("Incorrect UserName or Password");	
	}*/
	
	
}

}
