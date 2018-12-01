package com.hobart.httpclient.config;

import com.alibaba.fastjson.JSON;
import com.hobart.httpclient.model.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApacheRestTemplateTest {

    @Autowired
    @Qualifier("apacheRestTemplate")
    private RestTemplate apacheRestTemplate;

    private static final String URL = "http://127.0.0.1:8888/submit";

    @Test
    public void post() {
//        Map<String,String> dataMap=new HashMap<>();
//        dataMap.put("code", "123");
//        dataMap.put("name", "你好");
//        dataMap.put("text", "sfsad");
        Message msg = new Message();
        msg.setCode("123");
        msg.setName("你好");
        msg.setText("sfsdf");
        //String result = apacheRestTemplate.postForObject(URL, new HttpEntity(JSON.toJSONString(msg)), String.class);
        ResponseEntity<String> result = apacheRestTemplate.postForEntity(URL, msg, String.class);
        System.out.println("--------------"+result.getBody());
    }
    
    @Test
    public void post2(){
        Map<String,String> dataMap=new HashMap<>();
        dataMap.put("code", "123");
        dataMap.put("name", "你好");
        dataMap.put("text", "sfsad");

        String result = apacheRestTemplate.postForObject(URL, JSON.toJSONString(dataMap), String.class);
        System.out.println("============>"+result);
    }
    
    @Test
    public void post3(){
        Map<String,String> dataMap=new HashMap<>();
        dataMap.put("code", "123");
        dataMap.put("name", "你好");
        dataMap.put("text", "sfsad");

        HttpHeaders headers = new HttpHeaders();

        MediaType type = MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.setContentType(type);
        //设置相应内容，相应内容将被转换为json格式返回  
        headers.setAcceptCharset(Collections.singletonList(Charset.forName("UTF-8")));
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> ent = new HttpEntity<String>(JSON.toJSONString(dataMap), headers);
        ResponseEntity<String> result = apacheRestTemplate.postForEntity(URL, ent, String.class);

        System.out.println(result.getBody());
    }
    
    @Test
    public void post4(){
        Message msg = new Message();
        msg.setCode("123");
        msg.setName("你好");
        msg.setText("sfsdf");

        HttpHeaders headers = new HttpHeaders();
        ////设置HTTP请求的请求头信息
        MediaType type = MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.setContentType(type);

        //设置相应内容，相应内容将被转换为json格式返回  
        headers.setAcceptCharset(Collections.singletonList(Charset.forName("UTF-8")));
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        //设置HttpEntity的Body类型为String，调用StringHttpMessageConverter转换报文体参数  
        HttpEntity<String> httpEntity = new HttpEntity(msg,headers);
        //发送post请求，并将返回的实体类型设置的IndexInfo  
        String result = apacheRestTemplate.postForObject(URL, httpEntity, String.class);
        System.out.println("=======>"+result);

    }
    
    
}
