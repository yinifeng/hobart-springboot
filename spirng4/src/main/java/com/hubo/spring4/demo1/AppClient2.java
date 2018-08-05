package com.hubo.spring4.demo1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 扫描包，排除某个bean
 * 
 * @author c_zhanghuazheng-001
 *
 */
public class AppClient2 {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnotationScan.class);
		// 实现接口初始化销毁Bean
		System.out.println(context.getBean(Cat.class));
		//报错排序某个bean
		System.out.println(context.getBean(Dog2.class));
		System.out.println(context.getBean(Animal.class));
		context.close();
	}
}
