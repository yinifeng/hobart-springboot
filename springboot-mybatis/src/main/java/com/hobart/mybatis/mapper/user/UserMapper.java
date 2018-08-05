package com.hobart.mybatis.mapper.user;

import java.util.List;

import com.hobart.mybatis.mapper.BashMapper;
import com.hobart.mybatis.model.User;


public interface UserMapper extends BashMapper{
	
	List<User> listUser();
}
