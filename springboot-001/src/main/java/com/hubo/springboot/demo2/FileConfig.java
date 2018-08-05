package com.hubo.springboot.demo2;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySource("classpath:jdbc.properties")
@PropertySource("file:d:/github/tmp/jdbc.properties")
@PropertySource("classpath:zookeeper.properties")
//@PropertySources({@PropertySource("classpath:jdbc.properties"),@PropertySource("file:d:/github/tmp/jdbc.properties")})
public class FileConfig {

}
