package com.hubo.spring4.demo2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext("com.hubo.spring4.demo2");
		System.out.println("-------------------bean中注入Spring容器----------------------");
		System.out.println(context.getBean("user"));
		//System.out.println(context.getBean("createUser"));
		context.getBean("user",User.class).show();
		context.getBean(Book.class).show();
		context.getBean(Bank.class).show();
		System.out.println("--------------自定义ApplicationContextAware-------------------");
		context.getBean(Dog.class).show();
		context.close();
	}
}
