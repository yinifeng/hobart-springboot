package com.hobart.spring.ioc;

import java.util.Arrays;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import com.hobart.spring.bean.Blue;
import com.hobart.spring.bean.Person;
import com.hobart.spring.config.BeanConfig;
import com.hobart.spring.config.ConditionBeanConfig;
import com.hobart.spring.config.FactoryBeanConfig;
import com.hobart.spring.config.ImportBeanConfig;
import com.hobart.spring.config.ScopeBeanConfig;

public class IOCTest {
	
	@Test
	public void testFactoryBean(){
		ApplicationContext context = new AnnotationConfigApplicationContext(FactoryBeanConfig.class);
		System.out.println("IOC容器初始化完成......");
		consoleBean(context);
		
		//实际上是获取的 Bean工厂getObject返回的对象
		//而且是懒加载的方式
		Object bean = context.getBean("colorFactoryBean");
		Object bean2 = context.getBean("colorFactoryBean");
		System.out.println(bean == bean2);
		System.out.println("Bean的类型："+bean.getClass());
		
		//若要从容器中获取Bean工厂的组件 前面加个&
		Object beanFactory = context.getBean("&colorFactoryBean");
		System.out.println(beanFactory);
		
	}
	
	@Test
	public void testImport(){
		ApplicationContext context = new AnnotationConfigApplicationContext(ImportBeanConfig.class);
		consoleBean(context);
		Blue bean = context.getBean(Blue.class);
		System.out.println(bean);
	}
	
	private void consoleBean(ApplicationContext context){
		String[] beanDefinitionNames = context.getBeanDefinitionNames();
		for (String bean : beanDefinitionNames) {
			System.out.println("--=="+bean);
		}
	}
	
	@Test
	public void testConditional(){
		ApplicationContext context = new AnnotationConfigApplicationContext(ConditionBeanConfig.class);
		String[] beanNamesForType = context.getBeanNamesForType(Person.class);
		System.out.println(Arrays.toString(beanNamesForType));
		
		Environment environment = context.getEnvironment();
		System.out.println(environment.getProperty("os.name"));
		
		Map<String, Person> beansOfType = context.getBeansOfType(Person.class);
		for(Map.Entry<String, Person> entry:beansOfType.entrySet()){
			System.out.println(entry.getKey()+"="+entry.getValue());
		}
	}
	
	@Test
	public void testScope(){
		ApplicationContext context = new AnnotationConfigApplicationContext(ScopeBeanConfig.class);
		
		System.out.println("IOC容器创建完成....");
		Object bean = context.getBean("person");
		Object bean2 = context.getBean("person");
		System.out.println(bean == bean2);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void testIOC(){
		ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
		String[] definitionNames = context.getBeanDefinitionNames();
		for(String dn:definitionNames){
			System.out.println(dn);
		}
	}
}
