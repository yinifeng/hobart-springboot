package com.hubo.springboot.demo1;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class App {
	
	public static void main(String[] args) {
		//ConfigurableApplicationContext context = SpringApplication.run(App2.class, args);
		SpringApplication app=new SpringApplication(App2.class);
		//设置多个源
//		Set<Object> sets=new HashSet<Object>();
//		sets.add(App2.class);
//		app.setSources(sets);
		ConfigurableApplicationContext context = app.run(args);
		context.getBean(Runnable.class).run();
		System.out.println(context.getBean(User.class));
		System.out.println(context.getBean(List.class).getClass());
		context.close();
	}
}
