package com.hubo.springboot.demo5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Enable* 注解学习
 * 	启用配置 @EnableConfigurationProperties 和 @ConfigurationProperties 一起使用
 *  启用异步 @EnableAsync 和  @Async 一起使用
 * 
 * @author hubo
 *
 */
//@SpringBootApplication
@EnableConfigurationProperties
@EnableAsync
@ComponentScan
public class App {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		System.out.println(context.getBean(TomcatProperties.class));
		context.getBean(Runnable.class).run();
		System.out.println("=============Async End==============");
		context.close();
	}
}
