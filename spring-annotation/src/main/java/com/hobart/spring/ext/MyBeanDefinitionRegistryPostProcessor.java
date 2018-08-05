package com.hobart.spring.ext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

import com.hobart.spring.bean.Dog;

@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("MyBeanDefinitionRegistryPostProcessor...postProcessBeanFactory...");
	}
	
	/**
	 * 注册bean的定义信息 
	 * 在postProcessBeanFactory 之前执行
	 */
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println("MyBeanDefinitionRegistryPostProcessor...postProcessBeanDefinitionRegistry...");
		
		registry.registerBeanDefinition("dog", BeanDefinitionBuilder.rootBeanDefinition(Dog.class).getBeanDefinition());
	}

}
