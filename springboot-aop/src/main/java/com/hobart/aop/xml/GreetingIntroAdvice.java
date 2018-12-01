package com.hobart.aop.xml;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.stereotype.Component;

/**
 * 类的增强
 */
@Component("greetingIntroAdvice")
public class GreetingIntroAdvice extends DelegatingIntroductionInterceptor implements Apology{

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        return super.invoke(mi);
    }

    @Override
    public void saySorry(String name) {
        System.out.println("Sorry!"+name);
    }
}
