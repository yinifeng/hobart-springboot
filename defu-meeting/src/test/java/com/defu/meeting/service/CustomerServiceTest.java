package com.defu.meeting.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.defu.meeting.DefuMeetingApplication;
import com.defu.meeting.vo.CustomerVo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=DefuMeetingApplication.class)
public class CustomerServiceTest {
	@Autowired
	private CustomerService custService;
	
	@Test
	public void testEditCustomer() {
		CustomerVo customer = custService.getCustomerById("3");
		customer.setSiginStatus("2");
		System.out.println(customer);
		boolean editCustomer = custService.editCustomer(customer);
	}

}
