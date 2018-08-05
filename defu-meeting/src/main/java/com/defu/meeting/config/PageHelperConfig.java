package com.defu.meeting.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.PageHelper;

@Configuration
public class PageHelperConfig {
	
	
	@Bean
	public PageHelper getPageHelper(){
		PageHelper pageHelper=new PageHelper();
		Properties properties=new Properties();
		properties.setProperty("helperDialect", "mysql");
		properties.setProperty("offsetAsPageNum", "true");
		properties.setProperty("rowBoundsWithCount", "true");
		properties.setProperty("pageSizeZero", "true");
		properties.setProperty("reasonable", "true");
		properties.setProperty("supportMethodsArguments", "true");
		pageHelper.setProperties(properties);
		return pageHelper;
	}
}
