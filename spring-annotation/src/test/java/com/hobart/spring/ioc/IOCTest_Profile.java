package com.hobart.spring.ioc;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hobart.spring.bean.Blank;
import com.hobart.spring.bean.DataSource;
import com.hobart.spring.config.ProfileBeanConfig;

public class IOCTest_Profile {
	
	//编码设置
	@Test
	public void testProfile2(){
		//创建容器类
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		//设置需要激活的环境
		context.getEnvironment().setActiveProfiles("dev");
		//注册容器
		context.register(ProfileBeanConfig.class);
		//刷新容器
		context.refresh();
		String[] beanNames = context.getBeanNamesForType(DataSource.class);
		for(String name:beanNames){
			System.out.println(name);
		}
		System.out.println(context.getBean(Blank.class));
		context.close();
	} 
	
	//1.执行传入参数：-Dspring.profiles.active=test
	@Test
	public void testProfile1(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProfileBeanConfig.class);
		String[] beanNames = context.getBeanNamesForType(DataSource.class);
		for(String name:beanNames){
			System.out.println(name);
		}
		System.out.println(context.getBean(Blank.class));
		context.close();
	}
}
