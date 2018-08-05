package com.hobart.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.hobart.spring.bean.Person;
import com.hobart.spring.filter.MyTypeFilter;
import com.hobart.spring.service.BookService;

/**
 * 配置类==配置文件beans.xml
 * @author hobart
 *
 */
@Configuration//告诉spring这是一个配置类
//@ComponentScan(value="com.hobart.spring",excludeFilters={
//		@Filter(type=FilterType.ANNOTATION,classes={Controller.class,Service.class})
//})
@ComponentScans({
	@ComponentScan(value="com.hobart.spring",includeFilters={
//			@Filter(type=FilterType.ANNOTATION,classes={Controller.class}),
//			@Filter(type=FilterType.ASSIGNABLE_TYPE,classes={BookService.class}),
			@Filter(type=FilterType.CUSTOM,classes={MyTypeFilter.class})
	},useDefaultFilters=false)
})

//@ComponentScan	value:指定要扫描的包
//excludeFilters = Filter[] :指定扫描的时候按照什么规则排除哪些组件
//includeFilters = Filter[] :指定扫描的时候只需要包含哪些组件(useDefaultFilters=false)关闭默认扫描方式
//FilterType.ANNOTATION ：按照注解
//FilterType.ASSIGNABLE_TYPE ：按照给定的类型
//FilterType.ASPECTJ ：使用Aspectj表达式
//FilterType.REGEX ：按照正则表达式
//FilterType.CUSTOM ：自定义Filter
public class BeanConfig {
	
	//给容器中注册一个Bean，类型为返回值的类型，id默认是用方法名作为id
	@Bean("person")
	public Person person001(){
		return new Person("marry", 20);
	}
}
