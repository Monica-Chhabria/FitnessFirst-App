package com.fitnessfirst;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class FitnessFirstAuthenticationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitnessFirstAuthenticationServiceApplication.class, args);
	}
	@Bean
	public RestTemplate getRestTemplate(RestTemplateBuilder builder)
	{
	    return builder
	            .setConnectTimeout(Duration.ofMillis(3000))
	            .setReadTimeout(Duration.ofMillis(3000))
	            .build();
	}
}
