package com.hubo.springboot.demo2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JdbcConfig {
	@Value("${url}")
	private String url;
	
	@Value("${driverClassName}")
	private String driverClassName;
	
	
	@Value("${username}")
	private String username;
	
	@Value("${password}")
	private String password;
	
	public void show(){
		System.out.println("=====jdbcConfig======");
		System.out.println("url :"+url);
		System.out.println("driverClassName :"+driverClassName);
		
		System.out.println("userName :"+username);
		System.out.println("password :"+password);
	}
}
