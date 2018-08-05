package com.hobart.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import com.hobart.mybatis.mapper.BashMapper;

@ServletComponentScan
@SpringBootApplication
@MapperScan(basePackages={"com.hobart.mybatis.mapper"},markerInterface=BashMapper.class)
public class SpringbootMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMybatisApplication.class, args);
	}
}
