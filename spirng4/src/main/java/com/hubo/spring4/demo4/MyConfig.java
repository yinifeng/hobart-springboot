package com.hubo.spring4.demo4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan(basePackages={"com.hubo.spring4.demo4"})
public class MyConfig {
	@Bean
	public Cat cat(){
		return new Cat();
	}
}
