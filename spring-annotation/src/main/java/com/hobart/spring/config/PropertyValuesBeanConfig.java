package com.hobart.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.hobart.spring.bean.Student;

/**
 * 属性赋值
 * 
 * @author hobart
 *
 */
@Configuration
//导入配置文件到上下文
@PropertySources(value = { @PropertySource(
		value = { "classpath:/student.properties" }) })
//@PropertySource(value={ "classpath:/student.properties"})
public class PropertyValuesBeanConfig {

	@Bean
	public Student student() {
		return new Student();
	}
}
