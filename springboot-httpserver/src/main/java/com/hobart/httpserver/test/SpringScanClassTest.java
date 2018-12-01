package com.hobart.httpserver.test;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.Arrays;

public class SpringScanClassTest {

    public static void main(String[] args) throws IOException {
        ResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:com/hobart/httpserver/**/*.class");
        Arrays.asList(resources).forEach(System.out::println);
        for(Resource r:resources){
        }
    }
}
