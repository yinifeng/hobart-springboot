package com.hobart.spring.condition;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import com.hobart.spring.bean.RainBow;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar{
	
	/**
	 * AnnotationMetadata:当前标注@Import注解类的所有注解信息
	 * BeanDefinitionRegistry:BeanDefinition的注册类；
	 * 	把所有需要添加到容器中的bean 调用
	 * 	BeanDefinitionRegistry.registerBeanDefinition手工注册
	 */
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		//根据Bean的id判断容器是否存在某个bean
		boolean hb1 = registry.containsBeanDefinition("com.hobart.spring.bean.Red");
		boolean hb2 = registry.containsBeanDefinition("com.hobart.spring.bean.Blue");
		if(hb1 && hb2){//如果存在哪么就注册
			//指定Bean的Id
			//指定Bean的定义信息(Bean的类型，Bean的作用域等等)
			BeanDefinition beanDefinition=new RootBeanDefinition(RainBow.class);
			registry.registerBeanDefinition("rainBow", beanDefinition);
		}
	}

}
