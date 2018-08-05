package com.defu.meeting.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.defu.meeting.DefuMeetingApplication;
import com.defu.meeting.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=DefuMeetingApplication.class)
public class UserDaoTest {
	@Autowired
	private UserDao UserDao;
	
	@Test
	public void testFindUserByNameAndPwd() {
		User user = UserDao.findUserByNameAndPwd(null, "123456");
//		System.out.println(user.getEnable_flg());
//		System.out.println(user.getCrt_time());
//		System.out.println(user.getUp_time());
		System.out.println(user);
	}

}
