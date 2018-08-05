package com.hobart.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	@Transactional
	public void insert(){
		userDao.insert();
		System.out.println("数据插入完成.....");
		int i=1/0;
		System.out.println(i);
	}
}
