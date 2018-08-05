package com.defu.meeting.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.defu.meeting.model.Customer;
import com.defu.meeting.vo.CustomerVo;

public interface CustomerDao {
	int insert(Customer customer);
	
	List<CustomerVo> list();
	
	CustomerVo getCustomerById(@Param("id") String id);
	
	int updateCustomer(@Param("customer") Customer customer);
	
	List<CustomerVo> getCustomerByTel(@Param("tel") String tel);
	
	int updateCustomerSiginStatus(@Param("siginStatus") String siginStatus,@Param("id") String id);
}
