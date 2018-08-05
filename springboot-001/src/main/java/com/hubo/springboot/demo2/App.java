package com.hubo.springboot.demo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


/**
 * 静态读取配置文件
 * @author hubo
 *
 */
@SpringBootApplication
public class App {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		//获取配置，springboot默认配置文件为application.properties
		//System.out.println(context.getEnvironment().getProperty("local.ip"));
		//注入配置
		context.getBean(UserConfig.class).show();
		context.getBean(JdbcConfig.class).show();
		context.getBean(DataSourceProperties.class).show();
		System.out.println(context.getBean(ZookeeperProperties.class).toString());
		
		context.close();
	}
}
