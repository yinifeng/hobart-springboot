package com.hobart.spring.ioc;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hobart.spring.tx.TxBeanConfig;
import com.hobart.spring.tx.UserService;

public class IOCTest_Tx {
	
	@Test
	public void testTx(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TxBeanConfig.class);
		UserService userService = context.getBean(UserService.class);
		userService.insert();
		context.close();
	}
}
