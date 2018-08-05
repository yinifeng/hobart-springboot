package com.hobart.spring.filter;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

public class MyTypeFilter implements TypeFilter {

	/**
	 * metadataReader:读取到的当前正在扫描类的信息
	 * metadataReaderFactory：可以获取其他任何类的信息
	 */
	@Override
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
			throws IOException {
		//获取当前类注解的信息
		AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
		annotationMetadata.getAnnotationTypes().forEach(System.out::println);
		
		//获取当前正在扫描类的类信息
		ClassMetadata classMetadata = metadataReader.getClassMetadata();
		
		//获取当前类资源（类的路径）
		Resource resource = metadataReader.getResource();
		
		String className = classMetadata.getClassName();
		System.out.println(">>>>>>"+className);
		//包含er的组件注册到容器当中
		if(className.contains("er")){
			return true;
		}
		return false;
	}

}
