package com.defu.meeting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.defu.meeting.interceptor.LoginInterceptor;

/**
 * 扩张springMVC 功能
 * 
 * 即保留springboot自动配置，又扩展springMVC
 * 
 * 不能标注@EnableWebMvc
 * @EnableWebMvc这个注解会全面接管springMVC，也就是springboot配置的springMVC全部失效
 * 
 * @author hobart
 *
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter{
	
	
	//默认页面到签到页面
	@Bean
	public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
		WebMvcConfigurerAdapter adapter=new WebMvcConfigurerAdapter(){
			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
				registry.addViewController("/").setViewName("signin");
				//registry.addViewController("/index").setViewName("signin");
				registry.addViewController("/index.html").setViewName("signin");
				registry.addViewController("/main.html").setViewName("dashboard");
			}
			
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				super.addInterceptors(registry);
				
				registry.addInterceptor(new LoginInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/index.html","/admin","/admin.html","/admin/login","/meeting/signin");
				
			}
		};
		return adapter;
	}
}
