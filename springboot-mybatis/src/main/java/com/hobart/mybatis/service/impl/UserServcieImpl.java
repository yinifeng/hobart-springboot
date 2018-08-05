package com.hobart.mybatis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hobart.mybatis.mapper.user.UserMapper;
import com.hobart.mybatis.model.User;
import com.hobart.mybatis.service.UserService;

//@Service
public class UserServcieImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;

	@Override
	public List<User> listUser() {
		return userMapper.listUser();
	}
	
	
}
