package com.hobart.spring.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 类名选择器
 * @author hobart
 *
 */
public class MyImportSelector implements ImportSelector {
	
	/**
	 * 返回值就是导入到容器的组件全类名
	 * AnnotationMetadata：当前标注@Import注解类的所有注解信息
	 */
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		//自定义逻辑返回
		//注意：这地方不要返回空，否则会空指针
		return new String[]{"com.hobart.spring.bean.Blue","com.hobart.spring.bean.Blank"};
	}

}
