package com.hubo.springboot.demo5;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Import 导入一个类 或多个类 导入类用spring容器来托管，导入的配置类(配置类中的bean 可以spring管理)
 * @author hubo
 *
 */
//@Import({User.class,Role.class,MyConfig.class})
//@Import(MyImportSelector.class)
@EnableLog(name="myspringboot")
@EnableEcho(packages={"com.hubo.springboot.bean","com.hubo.springboot.vo"})
public class App2 {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(App2.class, args);
		System.out.println(context.getBean(User.class));
		System.out.println(context.getBean(Role.class));
		System.out.println(context.getBeansOfType(Runnable.class));
		
		context.close();
	}
}
