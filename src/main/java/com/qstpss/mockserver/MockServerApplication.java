package com.qstpss.mockserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MockServerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MockServerApplication.class, args);
	}

}
