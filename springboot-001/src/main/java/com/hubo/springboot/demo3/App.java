package com.hubo.springboot.demo3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 动态读取配置文件
 * 通过启动的参数来控制生效的profile
 * --spring.profiles.active=test,dev
 * 
 * 激活一个profile，默认的不激活（不影响配置文件）
 * --spring.profiles=test
 * 
 * 
 * @author hubo
 *
 */
@SpringBootApplication
public class App {
	public static void main(String[] args) {
//		SpringApplication app=new SpringApplication(App.class);
//		app.setAdditionalProfiles("test");
//		ConfigurableApplicationContext context = app.run(args);
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		System.out.println(context.getEnvironment().getProperty("springboot.name"));
		
		System.out.println(context.getEnvironment().getProperty("jdbc.url"));
		
		context.close();
	}
}
