package com.hobart.zookeeper.registry.base;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The class Register dto.
 *
 * @author paascloud.net @gmail.com
 */
@Data
@AllArgsConstructor
public class RegisterDto {

	private String app;

	private String host;

	private CoordinatorRegistryCenter coordinatorRegistryCenter;

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public CoordinatorRegistryCenter getCoordinatorRegistryCenter() {
		return coordinatorRegistryCenter;
	}

	public void setCoordinatorRegistryCenter(CoordinatorRegistryCenter coordinatorRegistryCenter) {
		this.coordinatorRegistryCenter = coordinatorRegistryCenter;
	}
	
	
}
