package com.hobart.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.hobart.spring.bean.Boss;
import com.hobart.spring.bean.Car;
import com.hobart.spring.bean.Color;

/**
 * @Autowired  的方法 构造 参数注入
 * @author hobart
 *
 */
@Configuration
@Import(value={Car.class,Boss.class})
public class AutowiredBeanConfig2 {
	
	//这个参数的@Autowired注解可以省略的
	//方法的参数是从容器中获取组件
	@Bean
	public Color color(Car car){
		Color color=new Color();
		color.setCar(car);
		return color;
	}
}
