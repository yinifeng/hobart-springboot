package com.hubo.spring4.demo1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


/**
 * 配置类
 * @author hubo
 *
 */
@Configuration
public class MyConfig {
	
	@Bean(name="myBean")
	@Scope("prototype")//非单例
	public MyBean createMyBean(){
		return new MyBean();
	}
	
	@Bean
	//@Scope("prototype")//bean工厂
	public RunnableFactoryBean createRunnableFactoryBean(){
		return new RunnableFactoryBean();
	}
	
	@Bean
	public CarFactory createCarFactory(){
		return new CarFactory();
	}
	
	@Bean
	public Car createCar(CarFactory factory){
		return factory.create();
	}
	
	@Bean
	public Cat createCat(){
		return new Cat();
	}
	
	@Bean(initMethod="init",destroyMethod="destroy")
	public Dog createDog(){
		return new Dog();
	}
	
	@Bean
	public Animal createAnimal(){
		return new Animal();
	}
	
	@Bean
	public User createUser(){
		return new User();
	}
}
