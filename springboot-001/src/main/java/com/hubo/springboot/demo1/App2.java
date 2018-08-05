package com.hubo.springboot.demo1;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootConfiguration
//@SpringBootApplication
@ComponentScan
public class App2 {
	
	@Bean
	public Runnable createRunnable(){
		return () -> {System.out.println("spring boot is run");};
	}
	
}
