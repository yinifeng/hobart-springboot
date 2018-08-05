package com.defu.meeting.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.defu.meeting.dao.SeatDao;
import com.defu.meeting.model.Seat;
import com.defu.meeting.service.SeatService;

@Service
public class SeatServiceImpl implements SeatService {
	@Autowired
	private SeatDao seatDao;

	@Override
	public List<Seat> listSeat() {
		return seatDao.findAll();
	}
	
	@Transactional
	@Override
	public boolean addSeat(Seat seat) {
		Date curDate=new Date();
		seat.setCrt_time(curDate);
		seat.setUp_time(curDate);
		seat.setEnable_flg("1");
		seat.setStatus("0");
		seatDao.insert(seat);
		return true;
	}

}
