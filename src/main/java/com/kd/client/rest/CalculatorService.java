package com.kd.client.rest;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class CalculatorService {

	@Autowired
	@Lazy
	RestTemplate restTemplate;	
	
	public int doMathOperation() {
		
			return restTemplate.getForObject("http://localhost:8082/calculate/1/2", Integer.class);
		
		
	}
	
	
	public int methodWithDAOException() throws SQLException {
		
		throw new SQLException("Dummy Exception");
    }
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
