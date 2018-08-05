package com.hubo.spring4.demo1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 扫描包
 * @author hubo
 *
 */
public class AppClient {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext("com.hubo.spring4.demo1");
		System.out.println(context.getBean("createUser"));
		System.out.println(context.getBeansOfType(User.class));
		System.out.println("<<<<<相关业务角色注解的bean>>>>>>>>>");
		System.out.println(context.getBean(UserDao.class));
		context.getBean(UserService.class).show();
		System.out.println(context.getBean(UserService.class).toString());
		System.out.println(context.getBean(UserController.class).toString());
		context.close();
	}
}
