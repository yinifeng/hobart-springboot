package com.hubo.spring4.demo2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ContextBeanPostProcessor implements BeanPostProcessor{
	@Autowired
	private ApplicationContext applicationContext;
	

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		if(bean instanceof SpringContextAware){
			SpringContextAware sca=(SpringContextAware)bean;
			sca.setApplicationContext(applicationContext);
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		return bean;
	}

}
