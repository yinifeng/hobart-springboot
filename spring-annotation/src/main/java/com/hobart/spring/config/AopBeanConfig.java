package com.hobart.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.hobart.spring.aop.LogAspct;
import com.hobart.spring.aop.MathCalculator;

/**
 * 基于注解aop
 * @author hobart
 *
 */
@EnableAspectJAutoProxy//启用注解切面表达
@Configuration
public class AopBeanConfig {
	
	@Bean
	public MathCalculator mathCalculator(){
		return new MathCalculator();
	}
	
	@Bean
	public LogAspct logAspct(){
		return new LogAspct();
	}
}
