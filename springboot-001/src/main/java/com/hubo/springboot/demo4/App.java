package com.hubo.springboot.demo4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 根据条件装配Bean
 * @author hubo
 *
 */
@SpringBootApplication
public class App {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		System.out.println(context.getBeansOfType(EncodingConvert.class));
		System.out.println("====runnable.enable配置装配====");
		System.out.println(context.getBeansOfType(Runnable.class));
		context.close();
	}
}
