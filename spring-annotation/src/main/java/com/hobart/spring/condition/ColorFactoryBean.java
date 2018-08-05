package com.hobart.spring.condition;

import org.springframework.beans.factory.FactoryBean;

import com.hobart.spring.bean.Color;

public class ColorFactoryBean implements FactoryBean<Color> {
	
	//获取Bean的实例
	@Override
	public Color getObject() throws Exception {
		System.out.println("ColorFactoryBean...getObject");
		return new Color();
	}
	
	//获取Bean的类型
	@Override
	public Class<?> getObjectType() {
		return Color.class;
	}
	
	/**
	 * true：单实例，在容器中只创建一个实例对象
	 * false：多实例,每次获取都创建一个实例对象
	 */
	@Override
	public boolean isSingleton() {
		return true;
	}

}
