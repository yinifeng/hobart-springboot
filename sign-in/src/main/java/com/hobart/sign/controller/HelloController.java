package com.hobart.sign.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	@ResponseBody
	@RequestMapping("/hello")
	public String hello(){
		System.out.println("请求了！！！！！");
		return "Hello World!";
	}
	
	@RequestMapping("/")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/admin")
	public String admin(){
		return "admin";
	}
}
