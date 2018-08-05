package com.hobart.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hobart.spring.condition.ColorFactoryBean;

/**
 * 工厂方式注册组件
 * @author hobart
 *
 */
@Configuration
public class FactoryBeanConfig {
	
	/**
	 * 工厂Bean获取的是调用getObject创建的对象
	 * @return
	 */
	@Bean
	public ColorFactoryBean colorFactoryBean(){
		return new ColorFactoryBean();
	}
}
