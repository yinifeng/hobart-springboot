package com.hubo.spring4.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	public void show(){
		userService.show();
	}

	@Override
	public String toString() {
		return "UserController [userService=" + userService + "]";
	}
	
	
}
