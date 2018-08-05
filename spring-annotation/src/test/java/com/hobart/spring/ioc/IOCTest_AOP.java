package com.hobart.spring.ioc;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hobart.spring.aop.MathCalculator;
import com.hobart.spring.config.AopBeanConfig;

public class IOCTest_AOP {
	
	@Test
	public void testAop(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopBeanConfig.class);
		MathCalculator mathCalculator = context.getBean(MathCalculator.class);
		mathCalculator.div(6, 3);
		context.close();
	}
}
