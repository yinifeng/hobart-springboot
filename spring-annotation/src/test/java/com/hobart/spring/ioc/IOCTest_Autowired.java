package com.hobart.spring.ioc;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hobart.spring.bean.Boss;
import com.hobart.spring.bean.Car;
import com.hobart.spring.bean.Color;
import com.hobart.spring.config.AutowiredBeanConfig;
import com.hobart.spring.config.AutowiredBeanConfig2;
import com.hobart.spring.config.AwareBeanConfig;
import com.hobart.spring.controller.UserController;
import com.hobart.spring.service.UserService;

public class IOCTest_Autowired {
	
	
	@Test
	public void testAware(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AwareBeanConfig.class);
		System.out.println("创建的IOC容器--=="+context);
		context.close();
	}
	
	@Test
	public void testAutowired2(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutowiredBeanConfig2.class);
		Boss boss = context.getBean(Boss.class);
		System.out.println(boss);
		Car car = context.getBean(Car.class);
		System.out.println(car);
		Color color = context.getBean(Color.class);
		System.out.println(color.getCar());
		context.close();
	}
	
	@Test
	public void testAutowired(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutowiredBeanConfig.class);
		UserController userController = context.getBean(UserController.class);
		userController.show();
		UserService userService = context.getBean(UserService.class);
		System.out.println(userService);
		userService.showDao();
		context.close();
	}
}
