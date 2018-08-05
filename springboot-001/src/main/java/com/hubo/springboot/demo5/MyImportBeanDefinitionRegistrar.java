package com.hubo.springboot.demo5;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;


/**
 * 方法的参数有一个BeanDefinitionRegistry 可以往容器中动态的注册bean
 * @author hubo
 *
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar{

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		// TODO Auto-generated method stub
		BeanDefinitionBuilder bdb=BeanDefinitionBuilder.rootBeanDefinition(User.class);
		//beanDefinition
		registry.registerBeanDefinition("user", bdb.getBeanDefinition());
		
		BeanDefinitionBuilder bdb2=BeanDefinitionBuilder.rootBeanDefinition(Role.class);
		//beanDefinition
		registry.registerBeanDefinition("role", bdb2.getBeanDefinition());
		
		BeanDefinitionBuilder bdb3=BeanDefinitionBuilder.rootBeanDefinition(MyConfig.class);
		//beanDefinition
		registry.registerBeanDefinition(MyConfig.class.getName(), bdb3.getBeanDefinition());
	}

}
