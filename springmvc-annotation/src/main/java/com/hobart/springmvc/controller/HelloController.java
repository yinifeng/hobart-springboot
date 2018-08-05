package com.hobart.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hobart.springmvc.service.HelloService;

@Controller
public class HelloController {
	@Autowired
	private HelloService helloService;
	
	@ResponseBody
	@RequestMapping("/hello")
	public String hello(){
		String hello = helloService.sayHello("tomcate...");
		return hello;
	}
	
	@RequestMapping("/suc")
	public String success(){
		return "success";
	}
}
