package com.hobart.springmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hobart.springmvc.controller.MyFirstHandlerInterceptor;

/**
 * 
 * @author hobart
 * 子容器
 * useDefaultFilters=false 禁用默认的过滤定制规则
 */
@ComponentScan(value="com.hobart.springmvc",includeFilters={
		@Filter(type=FilterType.ANNOTATION,classes={Controller.class})
},useDefaultFilters=false)
@EnableWebMvc//开启mvc功能定制
public class AppConfig extends WebMvcConfigurerAdapter{
	
	//定制
	//视图解析器
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		//默认："/WEB-INF/", ".jsp"
		//例如：试图解析路径 /WEB-INF/index.jsp
		//registry.jsp();
		registry.jsp("/WEB-INF/views/",".jsp");
	}
	
	//静态资源访问
	//<mvc:default-servlet-handler/>
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub
		configurer.enable();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(new MyFirstHandlerInterceptor()).addPathPatterns("/**");
	}
}
