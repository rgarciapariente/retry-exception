package com.example.retryexceptions;

import com.example.retryexceptions.service.RemoteCallService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class RetryExceptionsApplicationTests {


	@Autowired
	private RemoteCallService remoteCallService;

	@Test
	void contextLoads() {
	}

	@Test
	void methodOk() {
		assertEquals(remoteCallService.methodOk(),"OK");
	}

	@Test
	void methodError() {
		assertEquals(remoteCallService.methodError(),"OK Fallback");
	}

	@Test
	void methodFallbackError() {
		assertThrows(TestException.class, () -> remoteCallService.methodErrorInFallback());
	}

}
