package com.hubo.resource.spring;

import java.io.IOException;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class ResourceTest2 {
	public static void main(String[] args) throws IOException {
		DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
		//增加自定义资源解析器
		resourceLoader.addProtocolResolver(new MyProtocolResolver());
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(resourceLoader);
		Resource resource = resolver.getResource("my:spring.xml");
		System.out.println(resource.contentLength()+"<-->"+resource.getDescription());
	}
}
