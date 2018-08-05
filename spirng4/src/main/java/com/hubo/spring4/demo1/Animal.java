package com.hubo.spring4.demo1;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


public class Animal {
	
	@PostConstruct
	public void init(){
		System.out.println("============Animal init==========");
	}
	
	@PreDestroy
	public void destroy(){
		System.out.println("============Animal destroy==========");
	}
}
