package com.hubo.springboot.demo2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 注入集合 数组配置
 * @author hubo
 *
 */
@Component
@ConfigurationProperties(prefix="zk")
public class ZookeeperProperties {
	private List<String> hosts=new ArrayList<String>();
	
	private String[] ports;

	public List<String> getHosts() {
		return hosts;
	}

	public void setHosts(List<String> hosts) {
		this.hosts = hosts;
	}

	public String[] getPorts() {
		return ports;
	}

	public void setPorts(String[] ports) {
		this.ports = ports;
	}

	@Override
	public String toString() {
		return "ZookeeperProperties [hosts=" + hosts + ", ports=" + Arrays.toString(ports) + "]";
	}
	
	
	
}
