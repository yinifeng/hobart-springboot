package com.hobart.zookeeper.controller;

import com.hobart.zookeeper.service.ZKService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("zk")
public class ZKController {
    @Autowired
    private ZKService zkService;
    
    @GetMapping("/get-id")
    public Long getId(){
        return this.zkService.getId();
    }
}
