package com.fitnessfirst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@EnableEurekaClient

@SpringBootApplication
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
