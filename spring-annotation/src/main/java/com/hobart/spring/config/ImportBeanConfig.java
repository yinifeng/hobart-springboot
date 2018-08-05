package com.hobart.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.hobart.spring.bean.Color;
import com.hobart.spring.bean.Person;
import com.hobart.spring.bean.Red;
import com.hobart.spring.condition.MyImportBeanDefinitionRegistrar;
import com.hobart.spring.condition.MyImportSelector;

/**
 * @Import 快速导入一个组件
 * @author hobart
 *
 */
@Configuration
@Import(value={Color.class,Red.class,MyImportSelector.class,MyImportBeanDefinitionRegistrar.class})//导入组件 id为全类名
public class ImportBeanConfig {
	
	@Bean
	public Person person(){
		return new Person("Import", 28);
	}
	
	/**
	 * 1、包扫描+组件标注注解（@Controller，@Service，@Repository，@Component）主要应用自己写的类
     *
	 * 2、@Bean 导入第三方包的组件
     *
	 * 3、@Import 快速给容器中导入一个组件
     *
	 */
	
	
}
