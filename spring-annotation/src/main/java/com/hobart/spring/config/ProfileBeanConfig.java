package com.hobart.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import com.hobart.spring.bean.Blank;
import com.hobart.spring.bean.DataSource;

/**
 * @Profile 注解根据不同的环境参数来注册组件
 * 
 * @author hobart
 *
 */
//@Profile("test") //修饰类满足这个环境参数类中的组件才注册
@PropertySource(value={"classpath:/datasource.properties"})
@Configuration
public class ProfileBeanConfig implements EmbeddedValueResolverAware{
	@Value("${jdbc.user}")
	private String userName;
	
	private String driverClass;
	
	//@Profile("default") //这个是spring默认的环境参数
	@Bean
	public Blank blank(){
		return new Blank();
	}
	
	@Profile("test")
	@Bean("testDataSource")
	public DataSource testDataSource(@Value("jdbc.pwd") String password){
		DataSource dataSource=new DataSource();
		//属性赋值
		dataSource.setUserName(userName);
		//方法参数
		dataSource.setPassword(password);
		//Aware子接口获取环境解析类
		dataSource.setDriverClass(driverClass);
		//测试环境
		dataSource.setUrl("jdbc:mysql://localhost:3306/test");
		return dataSource;
	}
	
	@Profile("dev")
	@Bean("devDataSource")
	public DataSource devDataSource(@Value("jdbc.pwd") String password){
		DataSource dataSource=new DataSource();
		dataSource.setUserName(userName);
		dataSource.setPassword(password);
		dataSource.setDriverClass(driverClass);
		dataSource.setUrl("jdbc:mysql://localhost:3306/dev");
		return dataSource;
	}
	
	@Profile("prod")
	@Bean("prodDataSource")
	public DataSource prodDataSource(@Value("jdbc.pwd") String password){
		DataSource dataSource=new DataSource();
		dataSource.setUserName(userName);
		dataSource.setPassword(password);
		dataSource.setDriverClass(driverClass);
		dataSource.setUrl("jdbc:mysql://localhost:3306/prod");
		return dataSource;
	}

	@Override
	public void setEmbeddedValueResolver(StringValueResolver resolver) {
		this.driverClass=resolver.resolveStringValue("${jdbc.driverClass}");
	}
}
