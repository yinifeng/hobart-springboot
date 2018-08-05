package com.hubo.spring4.demo4;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
		String[] beanDefinitionNames = context.getBeanDefinitionNames();
		Arrays.asList(beanDefinitionNames).forEach(System.out::println);

		context.close();
	}
}
