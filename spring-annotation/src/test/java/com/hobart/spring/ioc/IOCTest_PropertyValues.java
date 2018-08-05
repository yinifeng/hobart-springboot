package com.hobart.spring.ioc;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import com.hobart.spring.bean.Student;
import com.hobart.spring.config.PropertyValuesBeanConfig;

public class IOCTest_PropertyValues {
	
	
	@Test
	public void testPropertyValue(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PropertyValuesBeanConfig.class);
		console(context);
		Student student = (Student) context.getBean("student");
		System.out.println("--==>"+student);
		//导入的配置文件也从容器中获取
		ConfigurableEnvironment environment = context.getEnvironment();
		System.out.println(environment.getProperty("student.nickName"));
		context.close();
	}
	
	private void console(AnnotationConfigApplicationContext context){
		String[] names = context.getBeanDefinitionNames();
		for(String n:names){
			System.out.println(n);
		}
	}
}
