package com.hobart.spring.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * JSR250注解
 * @author hobart
 *
 */
public class Dog {
	
	public Dog() {
		System.out.println("dog...constructor...");
	}
	
	//对象创建完成和属性赋值完成调用
	@PostConstruct
	public void setUp(){
		System.out.println("dog...@PostConstruct...");
	}
	
	//容器移除对象之前
	@PreDestroy
	public void cleanUp(){
		System.out.println("dog...@PreDestroy...");
	}
}
