package com.example.retryexceptions.service;

import com.example.retryexceptions.TestException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RemoteCallService {

	@CircuitBreaker(name = "Test1", fallbackMethod = "fallbackMethod")
	public String methodOk() {
		return "OK";
	}

	@CircuitBreaker(name = "Test2", fallbackMethod = "fallbackMethod")
	public String methodError() {
		throw new TestException("Error in methodError");
	}

	public String fallbackMethod(TestException t) {
		log.error("Error captured", t);
		return "OK Fallback";
	}

	@CircuitBreaker(name = "Test3", fallbackMethod = "fallbackMethodWithError")
	public String methodErrorInFallback() {
		throw new TestException("Error in methodErrorInFallback");
	}
	public String fallbackMethodWithError(TestException t) {
		log.error("Error captured", t);
		throw new TestException("Error in fallbackMethodWithError");
	}

}
