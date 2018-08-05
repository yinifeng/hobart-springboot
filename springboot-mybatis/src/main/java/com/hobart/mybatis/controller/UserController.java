package com.hobart.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hobart.mybatis.model.User;
import com.hobart.mybatis.service.UserService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
//@RestController
public class UserController {
	@Autowired
	UserService UserService;
	
	@GetMapping("/list")
	public List<User> listUser(){
		return UserService.listUser();
	}
}
