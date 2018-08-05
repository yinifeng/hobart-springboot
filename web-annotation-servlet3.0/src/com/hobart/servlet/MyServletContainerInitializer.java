package com.hobart.servlet;

import java.util.EnumSet;
import java.util.Set;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.HandlesTypes;

import com.hobart.service.HelloService;

/**
 * 容器启动时候会将@HandlesTypes指定的这个类型
 * 下面的子类(实现类，子类型接口)传递过来
 * @author hobart
 *
 */

//感兴趣的类型
@HandlesTypes(value={HelloService.class})
public class MyServletContainerInitializer implements ServletContainerInitializer {
	
	/**
	 * 应用启动的时候会运行
	 * Set<Class<?>>：感兴趣类型的所有子类型
	 * ServletContext :代表当前web应用的ServletContext
	 * 		
	 * 		ServletContext添加web组件
	 * 
	 */
	@Override
	public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("感兴趣的类型：");
		c.forEach(System.out::println);
		
		//注册Servlet组件
		ServletRegistration.Dynamic dynamicServlet = ctx.addServlet("userServlet", new UserServlet());
		//配置Servlet的映射信息
		dynamicServlet.addMapping("/user");
		
		//注册Listener组件
		ctx.addListener(UserListener.class);
		
		//注册Filter组件
		FilterRegistration.Dynamic dynamicFilter = ctx.addFilter("userFilter", UserFilter.class);
		dynamicFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
	}

}
