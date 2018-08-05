package com.defu.meeting.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.defu.meeting.DefuMeetingApplication;
import com.defu.meeting.vo.CustomerVo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=DefuMeetingApplication.class)
public class CustomerDaoTest {
	@Autowired
	private CustomerDao dao;
	
	@Test
	public void testInsert() {
		List<CustomerVo> list = dao.list();
		list.forEach(System.out::println);
	}

}
