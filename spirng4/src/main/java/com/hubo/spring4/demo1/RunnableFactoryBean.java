package com.hubo.spring4.demo1;

import org.springframework.beans.factory.FactoryBean;

/**
 * 实现接口的bean工厂
 * @author hubo
 *
 */
public class RunnableFactoryBean implements FactoryBean<Jeep>{

	@Override
	public Jeep getObject() throws Exception {
		//return () -> {}; Lambda表达式
		return new Jeep();
	}

	@Override
	public Class<?> getObjectType() {
		//return Runnable.class;
		return Jeep.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
