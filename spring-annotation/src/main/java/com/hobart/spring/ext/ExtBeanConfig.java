package com.hobart.spring.ext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.hobart.spring.bean.Cat;

/**
 * 扩展bean
 * @author hobart
 *
 */
@ComponentScan("com.hobart.spring.ext")
@Configuration
public class ExtBeanConfig {
	
	@Bean
	public Cat cat(){
		return new Cat();
	}
}
