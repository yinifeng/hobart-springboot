package com.defu.meeting.dao;

import org.apache.ibatis.annotations.Param;

import com.defu.meeting.model.User;

public interface UserDao {
	
	User findUserByNameAndPwd(@Param("username")String username,@Param("password")String password);
}
