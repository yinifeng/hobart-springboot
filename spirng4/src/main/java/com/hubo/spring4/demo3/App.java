package com.hubo.spring4.demo3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext("com.hubo.spring4.demo3");
		System.out.println(context.getBean("createUser"));
		System.out.println(context.getBeansOfType(Person.class).values());
		
		//注册bean
		//context.registerBeanDefinition(beanName, beanDefinition);
		
		context.close();
	}
}
