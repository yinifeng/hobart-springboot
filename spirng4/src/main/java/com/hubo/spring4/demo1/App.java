package com.hubo.spring4.demo1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(MyConfig.class,User.class,UserDao.class,UserService.class,UserController.class);
		
		System.out.println("<----------------------bean的配置类获取------------------------->");
		System.out.println(context.getBean(MyBean.class));
		//默认名字为方法的名字(createMyBean)
		System.out.println(context.getBean("myBean"));
		System.out.println("<------------------bean工厂创建bean----------------------------->");
		//System.out.println(context.getBean(Runnable.class));
		//bean工厂获取bean
		//类型获取
		System.out.println(context.getBean(Jeep.class));
		//名字获取
		System.out.println(context.getBean("createRunnableFactoryBean"));
		//获取工厂bean
		//名字获取
		System.out.println(context.getBean("&createRunnableFactoryBean"));
		//类型获取
		System.out.println(context.getBean(RunnableFactoryBean.class));
		System.out.println("<-------------------bean的初始化和销毁---------------------------->");
		//实现接口初始化销毁Bean
		System.out.println(context.getBean(Cat.class));
		System.out.println(context.getBean(Dog.class));
		System.out.println(context.getBean(Dog.class));
		
		System.out.println("<-------------------bean的装配---------------------------->");
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
