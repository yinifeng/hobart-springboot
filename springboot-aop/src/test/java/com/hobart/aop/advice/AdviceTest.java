package com.hobart.aop.advice;

import com.hobart.aop.api.Greeting;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;

import static org.junit.Assert.*;

public class AdviceTest {
    
    @Test
    public void testAdvice(){
        ProxyFactory proxyFactory=new ProxyFactory();//创建代理工厂
        //设置目标类对象
        proxyFactory.setTarget(new Greeting() {
            @Override
            public void sayHello(String name) {
                System.out.println("Spring Advice代理，"+name);
            }
        });
        //添加前置增强
        proxyFactory.addAdvice(new GreetingBeforeAdvice());
        //添加后置增强
        proxyFactory.addAdvice(new GreetingAfterAdvice());
        
        //从代理工厂中获取代理
        Greeting proxy = (Greeting) proxyFactory.getProxy();
        proxy.sayHello("hobart");

        System.out.println(proxy);
    }

}