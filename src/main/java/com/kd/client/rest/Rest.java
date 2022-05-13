package com.kd.client.rest;

import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/client")
public class Rest {

    @Autowired
    RetryRegistry retryRegistry;
    
    @Autowired
    CalculatorService calculatorService;
	
	
	@GetMapping(value = "/getSum")
	@Retry(name = "retryService", fallbackMethod = "fallBackMethod")
	public int calculate() {
	
		return calculatorService.doMathOperation();
	}
	
	@GetMapping(value = "/getData")
	@Retry(name = "retryService", fallbackMethod = "fallBackMethod")
	public int calculateWithError() throws SQLException {
	
		return calculatorService.methodWithDAOException();
	}
	
	
	public int fallBackMethod(Exception e){
		
		
		return -1;
	}

	
	
	@PostConstruct
    public void postConstruct() {
        retryRegistry
            .retry("retryService")
            .getEventPublisher()
            .onRetry(System.out::println);
    }

}
