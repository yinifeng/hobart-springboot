package com.hubo.spring4.demo1;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	//@Autowired
	@Resource
	private UserDao userDao;
	@Inject
	private Car car;
	
	public void show(){
		userDao.show();
	}

	@Override
	public String toString() {
		return "UserService [userDao=" + userDao + ", car=" + car + "]";
	}
	
	
	
	
}
