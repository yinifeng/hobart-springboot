package com.hobart.aop.xml;

import com.hobart.aop.api.Greeting;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:xml/spring-advice.xml"})
public class XMLProxyTest {
    @Autowired
    private ApplicationContext context;


    /**
     * 声明式配置 方法增强
     */
    @Test
    public void testXmlProxy(){
        Greeting proxy = (Greeting) context.getBean("greetingProxy1");
        proxy.sayHello("hobart");
     
    }

    /**
     * 类的增强
     */
    @Test
    public void testXmlProxyByClass(){
        //转型为目标类
        GreetingImpl proxy = (GreetingImpl) context.getBean("greetingProxy2");
        proxy.sayHello("jack");
        
        //将目标类强制向上转型为Apology接口，这是引入增强给我们带来的特性
        Apology apology= (Apology) proxy;
        apology.saySorry("jack");
    }
}