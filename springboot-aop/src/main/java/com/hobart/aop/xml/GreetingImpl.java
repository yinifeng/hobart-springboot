package com.hobart.aop.xml;

import com.hobart.aop.api.Greeting;
import org.springframework.stereotype.Component;

@Component
public class GreetingImpl implements Greeting {
    @Override
    public void sayHello(String name) {
        System.out.println("xml声明式配置代理，"+name);
        
        //测试异常环绕通知
        //throw new RuntimeException("Error");
    }
}
