package com.hubo.springboot.demo4;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import com.google.gson.Gson;


/**
 * springboot 提供很多条件装配bean的注解@ConditionalOnProperty
 * 
 * @author c_zhanghuazheng-001
 *
 */
@SpringBootConfiguration
public class UserConfiguration {
	
	/**
	 * 存在某个配置才装配
	 * matchIfMissing=true 配置不存在 但有某个值也装配
	 * @return
	 */
	@Bean
	@ConditionalOnProperty(name="runnable.enabled",havingValue="true",matchIfMissing=true)
	public Runnable createRunnable(){
		return () -> {};
	}
	
	/**
	 * classpath下存在某个class装配
	 * @return
	 */
	@Bean
	//@ConditionalOnClass(Gson.class)
	@ConditionalOnClass(name="com.google.gson.Gson")
	public Runnable createGsonRunnable(){
		return () -> {};
	}
	
	/**
	 * 容器当中存在某个Bean 而装配
	 * @return
	 */
	@Bean
	@ConditionalOnBean(User.class)
	public Runnable createBeanRunnable(){
		return () -> {};
	}
}
