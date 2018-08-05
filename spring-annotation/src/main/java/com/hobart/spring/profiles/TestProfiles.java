package com.hobart.spring.profiles;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


@PropertySource(value={"classpath:/testpro.properties"})
@Configuration
public class TestProfiles implements EnvironmentAware,InitializingBean{
	
	private Environment env;
	@Value("${hobart.meta:111}")
	private String value;
	
	@Override
	public void setEnvironment(Environment environment) {
		// TODO Auto-generated method stub
		env=environment;
	}



	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("hubo_env", "hobart");
		System.out.println("ENV=====>"+env.getProperty("hobart_meta"));
		System.out.println("hobart.meta===>"+value);
		
		System.out.println("myEnv====>"+env.getProperty("hubo_env"));
		
		
	}

}
