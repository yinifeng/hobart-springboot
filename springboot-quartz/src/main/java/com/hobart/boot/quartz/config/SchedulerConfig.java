package com.hobart.boot.quartz.config;


import com.hobart.boot.quartz.enums.JobStaus;
import com.hobart.boot.quartz.job.QuartzJobFactory;
import com.hobart.boot.quartz.support.AutowiringSpringBeanJobFactory;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import com.hobart.boot.quartz.config.JobProperties.ScheduleJob;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/*
 * hubo
 */
@Configuration
public class SchedulerConfig {
	
	@Autowired
	private JobProperties newJobProperties;
	
//	@Bean
//	@ConfigurationProperties(prefix = "jiayuan.schedule")
//	public JobProperties newJobProperties(){
//		System.out.println("---------------");
//		return new JobProperties();
//	}

	@Bean
	public JobFactory jobFactory(ApplicationContext applicationContext) {
		AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
		jobFactory.setApplicationContext(applicationContext);
		return jobFactory;
	}

	@Bean
	public Scheduler schedulerFactoryBean(/* DataSource dataSource, */ JobFactory jobFactory) throws Exception {
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
		// register all jobs
		// List<ScheduleJob> jobs = QuartzJobFactory.getInitAllJobs();
		List<JobProperties.ScheduleJob> jobs = this.newJobProperties.getJobs();
		for (ScheduleJob job : jobs) {
			if(StringUtils.isBlank(job.getJobName())){
				continue;
			}
			//这个job任务不定时
			if (job.getJobStatus() == JobStaus.STOP){
				continue;
			}
			TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			if (null == trigger) {
				JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
						.withIdentity(job.getJobName(), job.getJobGroup()).withDescription(job.getDesc()).build();
				jobDetail.getJobDataMap().put("scheduleJob", job);
				jobDetail.isConcurrentExectionDisallowed();
				jobDetail.requestsRecovery();
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
				trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup())
						.withSchedule(scheduleBuilder).build();
				scheduler.scheduleJob(jobDetail, trigger);
			} else {
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
				trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
				scheduler.rescheduleJob(triggerKey, trigger);
			}
		}

		scheduler.start();
		return scheduler;
	}

	@Bean
	public Properties quartzProperties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();
	}

}
