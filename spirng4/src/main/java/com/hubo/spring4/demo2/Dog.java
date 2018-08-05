package com.hubo.spring4.demo2;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Dog implements SpringContextAware{
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext=applicationContext;
	}
	
	public void show(){
		System.out.println("Dog :"+applicationContext.getClass());
	}

}
