package com.hobart.aop;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AopApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context 
                = new SpringApplicationBuilder(AopApplication.class)
                        .web(true)
                        .build(args).run();

    }
}
