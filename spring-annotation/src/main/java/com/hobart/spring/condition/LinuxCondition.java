package com.hobart.spring.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 按linux系统条件注册bean
 * @author hobart
 *
 */
public class LinuxCondition implements Condition {
	
	/**
	 * ConditionContext：判断条件能使用的上下文（环境）
	 * AnnotatedTypeMetadata：注释信息
	 */
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		// TODO 是否时linux系统
		//1.能获取到ioc容器使用的beanFactory
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
		//2.获取类加载器
		ClassLoader classLoader = context.getClassLoader();
		//3.获取当前环境的信息
		Environment environment = context.getEnvironment();
		//4.获取Bean定义注册类
		BeanDefinitionRegistry registry = context.getRegistry();
		//是否包含某个bean
		boolean containsBeanDefinition = registry.containsBeanDefinition("person");
		String property = environment.getProperty("os.name");
		if(property.contains("linux")){
			return true;
		}
		return false;
	}

}
