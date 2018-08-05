package com.hobart.spring.ioc;

import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hobart.spring.ext.ExtBeanConfig;

public class IOCTest_Ext {
	
	@Test
	public void testExt(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ExtBeanConfig.class);
		context.publishEvent(new ApplicationEvent(new String("自定义事件发布....")) {
			private static final long serialVersionUID = 1L;
		});
		
		context.close();
	}
}
