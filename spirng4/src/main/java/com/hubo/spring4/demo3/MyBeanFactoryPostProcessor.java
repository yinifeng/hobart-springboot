package com.hubo.spring4.demo3;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;


/**
 * BeanFactoryPostProcessor 
 * 触发时机
 * spring容器初始化完成后触发，而且只触发一次
 * 触发时机比BeanPostProcessor早
 * @author hubo
 *
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor{
	
	
	/**
	 * 容器启动时立即执行，可以初始化容器
	 */
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		System.out.println("====postProcessBeanFactory===="+beanFactory.getBeanDefinitionCount());
	}

}
