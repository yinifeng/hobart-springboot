package com.hubo.spring4.demo3;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * spring容器初始化完成后
 * 向容器中注册bean
 * BeanDefinitionRegistryPostProcessor 可以拿到
 * ConfigurableListableBeanFactory，BeanDefinitionRegistry两个对象
 * BeanDefinitionRegistryPostProcessor可以动态注册bean
 * @author hubo
 *
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub

	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		// TODO Auto-generated method stub
		//注册bean
		for(int i=0;i<10;i++){
			BeanDefinitionBuilder bdb=BeanDefinitionBuilder.rootBeanDefinition(Person.class);
			//bean的属性设置
			bdb.addPropertyValue("name", "admin"+i);
			registry.registerBeanDefinition("person"+i, bdb.getBeanDefinition());
		}
	}

}
