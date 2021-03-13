package com.fitnessfirst.service;

import com.fitnessfirst.model.User;

public interface UserService {
public  User saveUser(User user) ;
public User fetchUser(int id);
public User fetchUserByEmail(String email);
}
