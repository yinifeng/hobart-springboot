package com.hobart.boot.quartz;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.boot.system.EmbeddedServerPortFileWriter;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//@EnableAutoConfiguration
//@Configuration
//@PropertySource("classpath:scheduleJobs.properties")
//@ComponentScan(basePackageClasses = { QuartzApplication.class })
@SpringBootApplication
public class QuartzApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(QuartzApplication.class).web(true)
				.run(args);
		context.addApplicationListener(new ApplicationPidFileWriter());
		context.addApplicationListener(new EmbeddedServerPortFileWriter());
	}
}
