package com.hobart.sign.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@Controller
public class LoginController {
	
	@RequestMapping("/user/login")
	public String login(@RequestParam("username") String username,
			@RequestParam("password") String password){
		if(StringUtils.isNotBlank(username) && "123456".equals(password)){
			return "dashboard";
		}else{
			return "login";
		}
	}
}
