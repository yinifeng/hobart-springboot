package com.defu.meeting.service;

import java.util.List;

import com.defu.meeting.vo.CustomerVo;

public interface CustomerService {
	
	List<CustomerVo> list();
	
	CustomerVo getCustomerById(String id);
	
	boolean editCustomer(CustomerVo customer);
	
	boolean saveCustomer(CustomerVo customer);
	
	CustomerVo signinByTel(String name,String tel);
}
