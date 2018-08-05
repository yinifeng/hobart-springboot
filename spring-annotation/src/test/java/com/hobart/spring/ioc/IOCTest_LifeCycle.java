package com.hobart.spring.ioc;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hobart.spring.bean.Car;
import com.hobart.spring.config.LifeCycleBeanConfig;

public class IOCTest_LifeCycle {
	
	@Test
	public void testBeanMethod(){
		//创建容器
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LifeCycleBeanConfig.class);
		//单实例Bean在容器创建时创建且初始化
		System.out.println("ioc 容器启动完成....");
		//多实例Bean获取时才创建且初始化
		Car car = context.getBean(Car.class);
		
		context.close();
	}
}

