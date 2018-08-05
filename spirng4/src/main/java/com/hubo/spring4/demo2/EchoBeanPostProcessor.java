package com.hubo.spring4.demo2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * BeanPostProcessor会在每个Bean初始化时候，调用一次
 * 两个方法不能返回null，否则，从容器中获取不到
 * 
 * @author hubo
 *
 */
//@Component
public class EchoBeanPostProcessor implements BeanPostProcessor{
	
	/**
	 * 在bean依赖属性装配设置之后触发
	 * 可以对bean 做些处理 ，对返回的bean做一下代理
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("=======postProcessBeforeInitialization========="+bean.getClass());
		if(bean instanceof User){
			return new LogUser();
		}
		return bean;
	}
	
	
	/**
	 * 是在bean init之后触发的
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("=======postProcessAfterInitialization========="+bean.getClass());
//		if(bean instanceof User){
//			return null;
//		}
		return bean;
	}

}
