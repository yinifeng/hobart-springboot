package com.hobart.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hobart.spring.dao.UserDao;

@Service
public class UserService {
	
	@Qualifier("userDao")
	@Autowired(required=false)
	private UserDao userDao2;
	
	public void showDao(){
		System.out.println(userDao2.getLabe());
	}
}
