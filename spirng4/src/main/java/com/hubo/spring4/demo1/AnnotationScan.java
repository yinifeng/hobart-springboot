package com.hubo.spring4.demo1;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@ComponentScan(basePackages="com.hubo.spring4.demo1",excludeFilters=@Filter(type=FilterType.ASSIGNABLE_TYPE,classes=DogConfig.class))
@Configuration
public class AnnotationScan {

}
