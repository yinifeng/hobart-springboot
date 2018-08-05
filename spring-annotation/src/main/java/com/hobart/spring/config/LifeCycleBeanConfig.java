package com.hobart.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hobart.spring.bean.Car;
import com.hobart.spring.bean.Cat;
import com.hobart.spring.bean.Dog;
import com.hobart.spring.bean.MyBeanPostProcessor;

/**
 * bean的生命周期
 *   bean的创建---初始化---销毁过程
 * @author hobart
 *
 */
@Configuration
public class LifeCycleBeanConfig {
	
	@Bean(initMethod="init",destroyMethod="destroy")
	public Car car(){
		return new Car();
	}
	
	@Bean
	public Cat cat(){
		return new Cat();
	}
	
	@Bean
	public Dog dog(){
		return new Dog();
	}
	
	@Bean
	public MyBeanPostProcessor beanPostProcessor(){
		return new MyBeanPostProcessor();
	}
}
