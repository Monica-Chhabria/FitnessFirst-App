package com.fitnessfirst;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.fitnessfirst.logging.CalorieLogger;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
@EnableEurekaClient
@SpringBootApplication
//this annotation is used to tell spring boot that encrypted properyies are present
@EnableEncryptableProperties
public class FitnessFirstCaloriesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitnessFirstCaloriesServiceApplication.class, args);
		CalorieLogger.LogMsg("inside spring boot application");
	}

   /* @LoadBalanced*/
	@Bean
	public RestTemplate getRestTemplate(RestTemplateBuilder builder)
	{
	    return builder
	            .setConnectTimeout(Duration.ofMillis(3000))
	            .setReadTimeout(Duration.ofMillis(3000))
	            .build();
	}
}
