package com.defu.meeting;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

@Configuration
@EnableApolloConfig
@EnableTransactionManagement
@MapperScan(basePackages={"com.defu.meeting.dao"})
@SpringBootApplication
public class DefuMeetingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DefuMeetingApplication.class, args);
	}
	
	@Bean
	public TestJavaConfigBean javaConfigBean() {  
	        return new TestJavaConfigBean();  
	 }
}
