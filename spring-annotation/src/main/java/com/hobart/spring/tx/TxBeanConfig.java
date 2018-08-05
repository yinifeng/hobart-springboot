package com.hobart.spring.tx;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 注解事物
 * @author hobart
 *
 */
@EnableTransactionManagement
@ComponentScan("com.hobart.spring.tx")
@Configuration
public class TxBeanConfig {
	
	@Bean
	public DataSource dataSource() throws PropertyVetoException{
		ComboPooledDataSource dataSource=new ComboPooledDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("123456");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/hb_mybatis?useSSL=false");
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		return dataSource;
	}
	
	//这个配置类spring做过特殊的处理，调用相关方法获取还是从容器取
	@Bean
	public JdbcTemplate jdbcTemplate() throws PropertyVetoException{
		return new JdbcTemplate(dataSource());
	}
	
	@Bean("transactionManager")
	public PlatformTransactionManager transactionManager(DataSource dataSource){
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
		
		return transactionManager;
	}
	
}
