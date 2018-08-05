package com.hubo.springboot.demo3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
//@Profile("dev")
public class MyConfig {
	
	@Bean
	public Runnable createRunnable(){
		System.out.println("=====R1=======");
		return () ->{};
	}
	
	@Bean
	@Profile("dev")
	public Runnable createRunnable2(){
		System.out.println("=====R2=======");
		return () ->{};
	}
	
	
	@Bean
	@Profile("test")
	public Runnable createRunnable3(){
		System.out.println("=====R3=======");
		return () ->{};
	}
}
