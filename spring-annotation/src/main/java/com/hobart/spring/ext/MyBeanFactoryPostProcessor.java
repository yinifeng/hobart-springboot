package com.hobart.spring.ext;

import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	/**
	 * ConfigurableListableBeanFactory 获取Bean的定义信息
	 */
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("MyBeanFactoryPostProcessor...postProcessBeanFactory...");
		//获取容器bean定义信息记录数
		int count = beanFactory.getBeanDefinitionCount();
		String[] names = beanFactory.getBeanDefinitionNames();
		System.out.println("当前BeanFactory中有"+count+"个Bean");
		System.out.println(Arrays.asList(names));
	}

}
