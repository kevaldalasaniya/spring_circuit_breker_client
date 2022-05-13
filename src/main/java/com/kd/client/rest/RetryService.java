package com.kd.client.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.resilience4j.retry.RetryRegistry;

@Service
public class RetryService {

    @Autowired
    Rest rest;
    
    @Autowired
    RetryRegistry retryRegistry;
    
}
