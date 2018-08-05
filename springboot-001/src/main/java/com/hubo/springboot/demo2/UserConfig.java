package com.hubo.springboot.demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


/**
 * 获取配置的方法
 * @author hubo
 *
 *配置文件的默认的文件名字位application.properties
 *默认的位置在classpath根目录，或者classpath:/config, file:/,file:config/
 *
 *默认文件的配置名字可以在指定参数使用--spring.config.name来指定，只需要指定文件的名字，文件的扩展名可以省略
 * 只要指定文件名 如 app.properties
 * 
 *默认的配置文件路劲可以使用 --spring.config.location来指定，配置文件需要指定全路劲,包括目录和文件名字，还可以指定多个，多个用逗号隔开
 *--spring.config.location=classpath:conf/app.properties
 *
 *
 *--spring.config.location=classpath:conf/app.properties
 *
 *
 *--spring.config.location=classpath:conf/app.properties,file:D:/github/tmp/tomcat.properties
 *
 */
@Component
public class UserConfig {
	
	@Value("${local.port}")
	private String localPort;
	
	//自动类型转换
	@Value("${local.port}")
	private Integer localPort2;
	
	/**
	 * @value 默认必须要有配置，配置项可以为空，如果没有配置项，则可以给默认值
	 */
	@Value("${tomcat.port:9090}")
	private String tomcatPort;
	
	@Autowired
	private Environment env;
	
	public void show(){
		System.out.println("local.ip="+env.getProperty("local.ip"));
		System.out.println("local.port="+env.getProperty("local.port",Integer.class));
		System.out.println("local.port2="+localPort2);
		System.out.println("tomcat.port="+tomcatPort);
		System.out.println("name="+env.getProperty("name"));
		System.out.println("app.name="+env.getProperty("app.name"));
	}
	
}
