package com.hobart.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import com.hobart.spring.bean.Person;
import com.hobart.spring.condition.LinuxCondition;
import com.hobart.spring.condition.WindowsCondition;

/**
 * 按条件注册组件
 * @author hobart
 *
 */

//加在类上满足这个条件，每个方法才能注册组件
@Conditional({WindowsCondition.class})
@Configuration
public class ConditionBeanConfig {
	
	/**
	 * @Conditional:按一定的条件进行判断，满足条件给容器中注册bean
	 * @return
	 */
	@Bean
	public Person person(){
		return new Person("张三",28);
	}
	
	//如果是windows环境注册bean
	@Conditional({WindowsCondition.class})
	@Bean("bill")
	public Person person1(){
		return new Person("Bill Gates", 62);
	}
	
	//如果是linux环境注册bean
	@Conditional(LinuxCondition.class)
	@Bean("linus")
	public Person person2(){
		return new Person("Linus", 48);
	}
}
