package com.hobart.boot.quartz.config;


import com.hobart.boot.quartz.job.SimpleQuartzScheduler;
import com.hobart.boot.quartz.support.AutowiringSpringBeanJobFactory;
import org.quartz.*;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/*
 * hubo
 */
@Configuration
public class SchedulerConfig {
	
	
	@Bean
	@ConfigurationProperties(prefix = "jiayuan.schedule")
	public JobProperties newJobProperties(){
		System.out.println("---------------");
		return new JobProperties();
	}

	@Bean
	public JobFactory jobFactory(ApplicationContext applicationContext) {
		AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
		jobFactory.setApplicationContext(applicationContext);
		return jobFactory;
	}

	@Bean
	public SimpleQuartzScheduler schedulerFactoryBean(/* DataSource dataSource, */ JobFactory jobFactory) throws Exception {
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		// this allows to update triggers in DB when updating settings in config
		// file
		factory.setOverwriteExistingJobs(false);
		// factory.setDataSource(dataSource);
		// use specify jobFactory to create jobDetail
		factory.setJobFactory(jobFactory);
		//factory.setTaskExecutor(this.taskExecutor);

		factory.setQuartzProperties(quartzProperties());
		factory.afterPropertiesSet();

		Scheduler scheduler = factory.getScheduler();
		scheduler.setJobFactory(jobFactory);
		return new SimpleQuartzScheduler(scheduler,newJobProperties());
	}

	@Bean
	public Properties quartzProperties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();
	}

}
