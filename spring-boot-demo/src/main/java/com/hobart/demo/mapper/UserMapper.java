package com.hobart.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.hobart.demo.model.User;

@Mapper
public interface UserMapper {
	
	@Select("select * from user where id=#{id}")
	User getUserById(Integer id);
	
	@Select("select * from user")
	List<User> getAllUser();
}
