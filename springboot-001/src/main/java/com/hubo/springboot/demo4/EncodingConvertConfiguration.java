package com.hubo.springboot.demo4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 *  @Conditional 基于条件自动配置，一般配合Condition接口一起使用，只有接口实现类返回true，才装配，否则装配
 *  
 *  它可以用在方法上，返回true 装配，返回false 不装配
 *  
 * @author hubo
 *
 */
@Configuration
@Conditional(UTF8Condition.class)
public class EncodingConvertConfiguration {
	
	@Bean
	//@Conditional(GBKCondition.class)
	public GBKEncodingConvert createGBKEncodingConvert(){
		return new GBKEncodingConvert();
	}
	
	@Bean
	//@Conditional(UTF8Condition.class)
	public UTF8EncodingConvert createUTF8EncodingConvert(){
		return new UTF8EncodingConvert();
	}
}
