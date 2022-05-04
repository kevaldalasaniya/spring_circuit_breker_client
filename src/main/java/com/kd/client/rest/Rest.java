package com.kd.client.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/client")
public class Rest {

	@Autowired
	@Lazy
	RestTemplate restTemplate;
	private int attempt=1;
	@GetMapping
	@CircuitBreaker(name="kdBreaker" , fallbackMethod = "fallBackMethod")
	public int calculate() {
		 System.out.println("retry method called "+attempt++ +" times "+" at "+new Date());
		return restTemplate.getForObject("http://localhost:8082/calculate/1/2", Integer.class);

	}
	
	
	public int fallBackMethod(Exception e){
		
		
		return -1;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
