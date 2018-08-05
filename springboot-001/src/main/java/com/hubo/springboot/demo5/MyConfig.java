package com.hubo.springboot.demo5;

import org.springframework.context.annotation.Bean;

public class MyConfig {
	@Bean
	public Runnable createRunnable(){
		return () ->{};
	}
	
	@Bean
	public Runnable createRunnable2(){
		return () ->{};
	}
}
