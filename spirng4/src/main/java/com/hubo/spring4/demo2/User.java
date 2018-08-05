package com.hubo.spring4.demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class User {
	
	private ApplicationContext applicationContext;
	
	public void init(){
		System.out.println("User init");
	}
	
	public void show(){
		System.out.println("Uer :"+applicationContext);
	}
	
	@Autowired
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
		System.out.println("set applicationContext...");
	}
	
	
}
