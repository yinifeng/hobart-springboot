package com.hubo.spring4.demo1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DogConfig {
	@Bean
	public Dog2 createDog(){
		return new Dog2();
	}
}
