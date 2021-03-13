package com.fitnessfirst.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fitnessfirst.model.User;

@CrossOrigin(origins = "*", allowedHeaders = "*")

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	@Query("select u from User u where u.email = :email")
	public Optional<User> getUserFromEmail(@Param("email") String email);
}
