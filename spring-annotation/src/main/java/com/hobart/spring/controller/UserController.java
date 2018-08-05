package com.hobart.spring.controller;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hobart.spring.service.UserService;

@Controller
public class UserController {
	//@Autowired
	//@Inject
	@Resource
	private UserService userService;
	
	public void show(){
		System.out.println(userService);
	}
}
