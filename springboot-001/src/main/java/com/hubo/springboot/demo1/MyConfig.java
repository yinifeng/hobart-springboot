package com.hubo.springboot.demo1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
	@Bean
	public List<String> createList(){
		return new ArrayList<String>();
	}
}
