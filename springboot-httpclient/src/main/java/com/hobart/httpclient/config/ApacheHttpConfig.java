package com.hobart.httpclient.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * httpclient 带连接池实现
 */
@Configuration
public class ApacheHttpConfig {
    
    @Bean
    public PoolingHttpClientConnectionManager poolingConnectionManager(){
        PoolingHttpClientConnectionManager poolingConnectionManager = new PoolingHttpClientConnectionManager();
        //连接池的并发
        poolingConnectionManager.setMaxTotal(1000);
        //每个主机的并发
        poolingConnectionManager.setDefaultMaxPerRoute(1000);
        return poolingConnectionManager;
    }
    
    @Bean
    public HttpClientBuilder httpClientBuilder(){
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(poolingConnectionManager());
        httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(2,true));

        List<BasicHeader> headers=new ArrayList<>();
        headers.add(new BasicHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36"));
        headers.add(new BasicHeader("Accept-Encoding","gzip,deflate"));
        headers.add(new BasicHeader("Accept-Language","zh-CN"));
        
        httpClientBuilder.setDefaultHeaders(headers);
        
        return httpClientBuilder;
    }
    
    @Bean
    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory(){
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClientBuilder().build());
        //连接超时时间 毫秒
        clientHttpRequestFactory.setConnectTimeout(5000);
        //读写超时时间 毫秒
        clientHttpRequestFactory.setReadTimeout(10000);
        return clientHttpRequestFactory;
    }
    
    @Bean("apacheRestTemplate")
    public RestTemplate apacheRestTemplate(RestTemplateBuilder builder){
//        final List<HttpMessageConverter<?>> converters = Lists.newArrayList(
//                new ByteArrayHttpMessageConverter(), new StringHttpMessageConverter(),
//                new AllEncompassingFormHttpMessageConverter(),new FastJsonHttpMessageConverter());
//        final HttpMessageConverters httpMessageConverters = new HttpMessageConverters() {
//            @Override
//            public List<HttpMessageConverter<?>> getConverters() {
//                return converters;
//            }
//        };
        
        
//      RestTemplate apacheRestTemplate = builder.messageConverters(converters).build();
        RestTemplate apacheRestTemplate = builder.build();
        apacheRestTemplate.setRequestFactory(clientHttpRequestFactory());
        apacheRestTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        
        List<HttpMessageConverter<?>> converters=new ArrayList<>();
//        converters.add(new FormHttpMessageConverter());
//        converters.add(new MappingJackson2XmlHttpMessageConverter());
//        converters.add(new MappingJackson2HttpMessageConverter());
//        converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));

//        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
//        converters.add(new ResourceHttpMessageConverter());
//        converters.add(new AllEncompassingFormHttpMessageConverter());
        converters.add(new FastJsonHttpMessageConverter());
        converters.add(new MappingJackson2HttpMessageConverter());
        
        apacheRestTemplate.setMessageConverters(converters);


       
        return apacheRestTemplate;
    }
}
