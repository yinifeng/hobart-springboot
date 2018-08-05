package com.defu.meeting.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.defu.meeting.dao.CustomerDao;
import com.defu.meeting.dao.SeatDao;
import com.defu.meeting.model.Customer;
import com.defu.meeting.model.Seat;
import com.defu.meeting.service.CustomerService;
import com.defu.meeting.vo.CustomerVo;

@Service
public class CustomerSerivceImpl implements CustomerService {
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private SeatDao seatDao;

	@Override
	public List<CustomerVo> list() {
		return customerDao.list();
	}

	@Override
	public CustomerVo getCustomerById(String id) {
		return customerDao.getCustomerById(id);
	}

	@Transactional
	@Override
	public boolean editCustomer(CustomerVo customer) {
		Customer cust=new Customer();
		cust.setId(Long.valueOf(customer.getId()));
		cust.setName(customer.getName());
		cust.setTel(customer.getTel());
		cust.setSex(customer.getSex());
		cust.setCompany(customer.getCompany());
		cust.setSiginStatus(customer.getSiginStatus());
		cust.setSeatId(StringUtils.isBlank(customer.getSeatId()) ? null : Long.valueOf(customer.getSeatId()));
		cust.setUp_time(new Date());
		customerDao.updateCustomer(cust);
		return true;
	}
	
	@Transactional
	@Override
	public boolean saveCustomer(CustomerVo customer) {
		Customer cust=new Customer();
		cust.setName(customer.getName());
		cust.setTel(customer.getTel());
		cust.setSex(customer.getSex());
		cust.setCompany(customer.getCompany());
		cust.setSiginStatus(customer.getSiginStatus());
		Date curDate = new Date();
		cust.setCrt_time(curDate);
		cust.setUp_time(curDate);
		cust.setEnable_flg("1");
		List<Seat> seats = seatDao.findByStatus("0");
		if(seats.size()>0){
			Long seatId = seats.get(0).getId();
			Seat seat = seatDao.findSeatById(seatId);
			seat.setStatus("1");
			seat.setUp_time(curDate);
			seatDao.updateSeat(seat);
			cust.setSeatId(seatId);
		}
		customerDao.insert(cust);
		return true;
	}
	
	@Transactional
	@Override
	public CustomerVo signinByTel(String name, String tel) {
		List<CustomerVo> custs = customerDao.getCustomerByTel(tel.trim());
		CustomerVo cust=null;
		if(custs.size()==1){
			cust = custs.get(0);
		}else if(custs.size()>1){
			for(int i=0,len=custs.size();i<len; ++i){//有多个比较名字
				CustomerVo c=custs.get(i);
				if(c.getName() !=null && c.getName().equals(name)){
					cust=c;
					break;
				}
			}
		}
		if(cust == null)
			return null;
		if("0".equals(cust.getSiginStatus())){//未签到
			customerDao.updateCustomerSiginStatus("1", cust.getId());
			cust.setSiginStatus("1");
		}
		return cust;
	}
	
	public static void main(String[] args) {
		List<String> list=new ArrayList<>();
		list.add("AAA");
		list.add("BBB");
		list.add("CCC");
		list.add("DDD");
		list.add("EEE");
		
		int random = (int)(Math.random()*1);
		System.out.println(random);
		
		
	}

}
