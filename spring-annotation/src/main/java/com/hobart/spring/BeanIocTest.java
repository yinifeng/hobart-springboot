package com.hobart.spring;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hobart.spring.bean.Person;
import com.hobart.spring.config.BeanConfig;

public class BeanIocTest {
	
	
	//配置类配置bean
	@Test
	public void testBeanConfig(){
		@SuppressWarnings("resource")
		ApplicationContext context=new AnnotationConfigApplicationContext(BeanConfig.class);
		Person person = context.getBean(Person.class);
		System.out.println(person);
		
		//bean的id
		String[] beanNames = context.getBeanNamesForType(Person.class);
		System.out.println(Arrays.toString(beanNames));
		
	}
	
	//xml配置bean
	@Test
	public void testBeanXML(){
		@SuppressWarnings("resource")
		ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
		Person person = (Person) context.getBean("person");
		System.out.println(person);
	}
}
