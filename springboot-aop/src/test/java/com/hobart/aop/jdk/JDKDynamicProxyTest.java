package com.hobart.aop.jdk;

import com.hobart.aop.api.Greeting;
import org.junit.Test;

import static org.junit.Assert.*;

public class JDKDynamicProxyTest {
    @Test
    public void testJDKproxy() {
        Greeting proxy = new JDKDynamicProxy(new Greeting() {
            @Override
            public void sayHello(String name) {
                System.out.println("JDK动态代理," + name);
            }
        }).getProxy();

        proxy.sayHello("tom");
        System.out.println("------------------------------");
        System.out.println(proxy);
    }
}