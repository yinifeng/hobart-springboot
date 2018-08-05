package com.hobart.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hobart.demo.mapper.UserMapper;
import com.hobart.demo.model.User;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 整合swagger测试框架
 * @author hobart
 *
 */

@EnableSwagger2
@Controller
public class UserController {
	@Autowired
	public UserMapper userMapper;
	
	@ResponseBody
	@GetMapping(path="/user/{id}")
	public User getUserById(@PathVariable("id") Integer id){
		return userMapper.getUserById(id);
	}
	
	@ResponseBody
	@GetMapping("/listUser")
	@ApiOperation(value="查询所有用户接口",notes="获取用户的列表信息")
	public List<User> listUser(){
		return userMapper.getAllUser();
	}
}
