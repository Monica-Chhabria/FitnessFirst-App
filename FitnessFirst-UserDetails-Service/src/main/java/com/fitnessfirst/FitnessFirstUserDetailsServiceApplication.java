package com.fitnessfirst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
@EnableEurekaClient

@SpringBootApplication
//this annotation is used to tell spring boot that encrypted properyies are present
@EnableEncryptableProperties
public class FitnessFirstUserDetailsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitnessFirstUserDetailsServiceApplication.class, args);
	}
@Bean
public BCryptPasswordEncoder getBcryptPasswordEncoder()
{
	return new BCryptPasswordEncoder();
}
}
