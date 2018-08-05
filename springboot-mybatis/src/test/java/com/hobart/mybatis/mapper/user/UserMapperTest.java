package com.hobart.mybatis.mapper.user;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hobart.mybatis.SpringbootMybatisApplication;
import com.hobart.mybatis.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=SpringbootMybatisApplication.class)
public class UserMapperTest {
	@Autowired
	UserMapper userMapper;
	@Autowired
	ApplicationContext context;
	
	@Test
	public void testListUser() {
		List<User> users = userMapper.listUser();
		System.out.println(users);
		System.out.println(context);
	}

}
