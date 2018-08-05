package com.hubo.springboot.demo5;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="tomcat")
public class TomcatProperties {
	private String name;
	private String port;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	@Override
	public String toString() {
		return "TomcatProperties [name=" + name + ", port=" + port + "]";
	}
	
	
}
