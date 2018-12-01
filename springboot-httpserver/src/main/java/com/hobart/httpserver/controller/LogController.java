package com.hobart.httpserver.controller;

import com.hobart.httpserver.annotation.MyLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    @MyLog
    @RequestMapping("log")
    public String getLog(){
        System.out.println("目标方法执行....");
        return "<h1>Hello World</h1>";
     }
}
