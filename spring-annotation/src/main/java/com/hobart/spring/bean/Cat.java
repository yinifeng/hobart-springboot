package com.hobart.spring.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 属性赋值好后执行初始化方法
 * 
 * @author hobart
 *
 */
public class Cat implements InitializingBean, DisposableBean {

	public Cat() {
		System.out.println("cat...constructor...");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("cat...destroy...");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("cat...afterPropertiesSet...");
	}

}
