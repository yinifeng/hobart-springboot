package com.hubo.resource.spring;

import org.springframework.core.io.ProtocolResolver;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class MyProtocolResolver implements ProtocolResolver {
	private static final String RESOURCE_PREFIX="my";

	@Override
	public Resource resolve(String location, ResourceLoader resourceLoader) {
		if(location.startsWith(RESOURCE_PREFIX)){
			return resourceLoader.getResource(location.replace(RESOURCE_PREFIX, "classpath"));
		}
		return null;
	}

}
