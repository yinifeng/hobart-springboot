package com.hubo.spring4.demo4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
	
	@Bean
	public Dog dog(){
		return new Dog();
	}
}
