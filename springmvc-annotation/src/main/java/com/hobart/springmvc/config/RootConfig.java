package com.hobart.springmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

//父容器不包括 controller,spring的容器不扫描controller
@ComponentScan(value="com.hobart.springmvc",excludeFilters={
		@Filter(type=FilterType.ANNOTATION,classes={Controller.class})
})
public class RootConfig {

}
