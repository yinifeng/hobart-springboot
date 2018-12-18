package com.hobart.aop.xmlaop;

import com.hobart.aop.api.Greeting;
import org.springframework.stereotype.Component;

/**
 * 归纳一下，Advisor（切面）封装了 Advice（增强）与 Pointcut（切点 ）。
 * 当您理解了这句话后，就往下看吧
 */
@Component
public class GreetingImpl implements Greeting{
    @Override
    public void sayHello(String name) {
        System.out.println("Hello!"+name);
    }
    
    public void goodMorning(String name){
        System.out.println("Good Morning!"+name);
    }
    
    public void goodNight(String name){
        System.out.println("Godd Night!"+name);
    }
}
