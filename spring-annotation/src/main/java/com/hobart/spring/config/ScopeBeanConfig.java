package com.hobart.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import com.hobart.spring.bean.Person;

@Configuration
public class ScopeBeanConfig {
	
	
	/**
	 * @see ConfigurableBeanFactory#SCOPE_PROTOTYPE
	 * @see ConfigurableBeanFactory#SCOPE_SINGLETON
	 * @see org.springframework.web.context.WebApplicationContext#SCOPE_REQUEST
	 * @see org.springframework.web.context.WebApplicationContext#SCOPE_SESSION
	 * @return
	 * 
	 * prototype :多实例的，ioc容器启动不会调用方法创建对象放在容器中，每次获取的时候才会调用方法创建对象
     *
	 * singleton :单实例的（默认值）：ioc容器启动会调用方法创建对象到ioc容器中。以后每次获取都是从容器（map.get()）中拿
     *
	 * request :同一次请求创建一个实例 （web环境下）
     *
	 * session :同一个session创建一个实例 （web环境下）
     *
     *
     *
     *
	 */
//	@Scope("prototype")
	@Lazy
	@Bean
	public Person person(){
		System.out.println("创建Person...");
		return new Person("tom", 28);
	}
}
