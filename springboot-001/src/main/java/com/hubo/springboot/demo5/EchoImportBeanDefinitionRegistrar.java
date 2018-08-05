package com.hubo.springboot.demo5;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class EchoImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar{

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		// TODO Auto-generated method stub
		Map<String, Object> attrMap = importingClassMetadata.getAnnotationAttributes(EnableEcho.class.getName());
		String[] packArray = (String[]) attrMap.get("packages");
		List<String> packages=Arrays.asList(packArray);
		System.out.println("packages : "+packages);
		BeanDefinitionBuilder bdb=BeanDefinitionBuilder.rootBeanDefinition(EchoBeanPostProcessor.class);
		bdb.addPropertyValue("packages", packages);
		registry.registerBeanDefinition(EchoBeanPostProcessor.class.getName(), bdb.getBeanDefinition());
	}

}
