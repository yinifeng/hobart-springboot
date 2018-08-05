package com.hobart.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

/**
 * 注入Spring的组件
 * Aware的子接口
 * 底层原理实现都是Bean后置处理器实现这些功能的
 * @author hobart
 *
 */
public class Bank implements ApplicationContextAware,BeanNameAware,EmbeddedValueResolverAware{
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("传入的IOC容器:"+applicationContext);
		this.applicationContext=applicationContext;
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("当前Bean在容器中的名字:"+name);
	}

	@Override
	public void setEmbeddedValueResolver(StringValueResolver resolver) {
		//System.out.println("spEl#{}和获取上下文${}解析器");
		System.out.println("环境参数："+resolver.resolveStringValue("${os.name}"));
		System.out.println("spEl表达式："+resolver.resolveStringValue("#{20*10}"));
	}

}
