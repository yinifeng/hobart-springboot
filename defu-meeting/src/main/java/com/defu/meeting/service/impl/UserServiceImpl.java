package com.defu.meeting.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.defu.meeting.dao.UserDao;
import com.defu.meeting.model.User;
import com.defu.meeting.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	
	@Transactional
	@Override
	public boolean login(String username, String password) {
		User user = userDao.findUserByNameAndPwd(username, password);
		if(user ==null || StringUtils.isBlank(user.getUserName())){
			return false;
		}
		return true;
	}
	
	
}
