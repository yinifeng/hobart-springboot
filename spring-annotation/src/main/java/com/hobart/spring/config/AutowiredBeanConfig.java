package com.hobart.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.hobart.spring.dao.UserDao;

/**
 * 自动装配
 * 
 * @author hobart
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.hobart.spring.controller", 
		"com.hobart.spring.service", "com.hobart.spring.dao" })
public class AutowiredBeanConfig {
	
	@Primary
	@Bean("userDao2")
	public UserDao userDao(){
		UserDao dao=new UserDao();
		dao.setLabe("2");
		return dao;
	}
}
