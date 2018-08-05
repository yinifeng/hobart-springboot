package com.hubo.spring4.demo2;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 逻辑参照ApplicationContextAwareProcessor
 * @author hubo
 *
 */
@Component
public class Book implements ApplicationContextAware{
	private ApplicationContext ApplicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.ApplicationContext=applicationContext;
	}
	
	public void show(){
		System.out.println("Book :"+ApplicationContext.getClass());
	}

}
