package com.hobart.httpserver.aspect;

import com.hobart.httpserver.annotation.MyLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

    @Pointcut("@annotation(com.hobart.httpserver.annotation.MyLog)")
    public void cut(){}

    @Around("cut()")
    public void advice(ProceedingJoinPoint joinPoint){
        System.out.println("环绕通知开始");
        try {
            //通过反射执行目标对象连接点处的方法
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("环绕通知结束");
    }


    //当想获得注解里面的属性，可以直接注入该注解
    @Before("cut()&&@annotation(myLog)")
    public void record(JoinPoint joinPoint, MyLog myLog){
        System.out.println(myLog.value());
    }

    //@After("recordLog()")
    @After("cut()")
    public void after(){
        System.out.println("已经记录下操作日志@After 方法执行后");
    }
}
