package com.hobart.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SpringbootEnventApplication {

	public static void main(String[] args) {
		//SpringApplication.run(SpringbootEnventApplication.class, args);
		
		
		//2.0
//		new SpringApplicationBuilder(SpringbootEnventApplication.class)
////				.web(WebApplicationType.NONE)
////				.run(args);

		new SpringApplicationBuilder(SpringbootEnventApplication.class)
				.web(false)
				.run(args);
	}

}

