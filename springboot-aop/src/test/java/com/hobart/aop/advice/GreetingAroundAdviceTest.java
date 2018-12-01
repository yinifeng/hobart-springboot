package com.hobart.aop.advice;

import com.hobart.aop.api.Greeting;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;

import static org.junit.Assert.*;

public class GreetingAroundAdviceTest {
    
    @Test
    public void testMethodInterceptor(){
        ProxyFactory proxyFactory=new ProxyFactory();//创建代理工厂
        proxyFactory.setTarget(new Greeting() {
            @Override
            public void sayHello(String name) {
                System.out.println("环绕增强，"+name);
            }
        });
        
        proxyFactory.addAdvice(new GreetingAroundAdvice());

        Greeting proxy = (Greeting) proxyFactory.getProxy();
        proxy.sayHello("mick");
    }
}