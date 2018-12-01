package com.hobart.httpclient.config;

import com.hobart.httpclient.model.Message;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTemplateTest {
    @Autowired
    private RestTemplate restTemplate;
    
    private static final String URL="http://127.0.0.1:8092/submit";
    
    @Before
    public void setUp() throws Exception {
    }
    
    @Test
    public void post(){
//        Map<String,String> dataMap=new HashMap<>();
//        dataMap.put("code", "123");
//        dataMap.put("name", "你好");
//        dataMap.put("text", "sfsad");
        Message msg=new Message();
        msg.setCode("123");
        msg.setName("你好");
        msg.setText("sfsdf");
//        HttpHeaders headers = new HttpHeaders();
//        
//        MediaType type = MediaType.parseMediaType(MediaType.TEXT_HTML_VALUE);
//        headers.setContentType(type);
//        
//        HttpEntity<String> ent = new HttpEntity<String>(JSON.toJSONString(msg), headers);
//        ResponseEntity<String> result = restTemplate.postForEntity(URL, ent, String.class);
        String s = restTemplate.postForObject(URL, msg, String.class);
//        String resultBody = result.getBody();
////        System.out.println(resultBody);
        System.out.println(s);
    }
    
    
    @After
    public void tearDown() throws Exception {
    }
}