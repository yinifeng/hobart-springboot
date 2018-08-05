package com.hubo.springboot.demo3;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.stereotype.Component;


/**
 * 动态读取配置到上下文中
 * @author hubo
 *
 */
@Component
public class MyEnvironmentPostProcessor implements EnvironmentPostProcessor{

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		// TODO Auto-generated method stub
		
		//也可以读取远程配置文件
		//便于配置文件集中管理
		try(InputStream input=new FileInputStream("D:/workspace/sourceCode/springboot/tmp/springboot.properties")) {
			Properties source=new Properties();
			source.load(input);
			PropertiesPropertySource propertySource = new PropertiesPropertySource("my", source);
			environment.getPropertySources().addLast(propertySource);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		}
	}

}
