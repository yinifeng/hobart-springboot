package com.hobart.httpserver.controller;

import com.hobart.httpserver.model.Message;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MyController {


    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    public Map<String,String> submit(@RequestBody Message msg){
        System.out.println("======>"+msg);
        Map<String,String> map=new HashMap<>();
        map.put("code", "0");
        map.put("describe", "处理成功");
        return map;
    }
    
}
