package com.hobart.aop.cglib;

import com.hobart.aop.api.Greeting;
import org.junit.Test;

public class CGLibDynamicProxyTest {

    @Test
    public void testCGLibProxy(){
        Greeting proxy = CGLibDynamicProxy.getInstance().getProxy(GreetingImp.class);

        proxy.sayHello("marry");
        System.out.println("----------------------");
        System.out.println(proxy);
    }

    public static class GreetingImp implements Greeting{

        @Override
        public void sayHello(String name) {
            System.out.println("CGLib动态代理，"+name);
        }
    }

}
