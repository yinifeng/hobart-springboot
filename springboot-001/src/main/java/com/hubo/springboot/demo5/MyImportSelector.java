package com.hubo.springboot.demo5;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;


/**
 * 放回的数组是 托管给spring容器管理的bean
 * selectImports放回值 必须是一个class全称
 * @author c_zhanghuazheng-001
 *
 */
public class MyImportSelector implements ImportSelector{

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		// TODO Auto-generated method stub
		
		/**
		 * 这里可以获取注解的详细信息，可以做一些特殊处理
		 */
		System.out.println(importingClassMetadata.getAllAnnotationAttributes(EnableLog.class.getName()));
		return new String[]{"com.hubo.springboot.demo5.User",Role.class.getName(),Jeep.class.getName()};
	}

}
