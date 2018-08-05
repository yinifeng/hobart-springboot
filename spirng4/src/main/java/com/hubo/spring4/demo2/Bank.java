package com.hubo.spring4.demo2;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


/**
 * spring 4.3 的新特性 
 * 只能一个Bean 带applicationContext的构造函数
 * 默认优先无参构造对象
 * @author c_zhanghuazheng-001
 *
 */
@Component
public class Bank {
	private ApplicationContext applicationContext;
	
//	public Bank() {
//		// TODO Auto-generated constructor stub
//	}
	
	public Bank(ApplicationContext applicationContext) {
		this.applicationContext=applicationContext;
	}
	
	public void show(){
		System.out.println("Bank :" +applicationContext.getClass());
	}
}
