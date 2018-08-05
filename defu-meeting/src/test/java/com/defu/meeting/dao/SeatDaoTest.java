package com.defu.meeting.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.defu.meeting.DefuMeetingApplication;
import com.defu.meeting.model.Seat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=DefuMeetingApplication.class)
public class SeatDaoTest {
	
	@Autowired
	private SeatDao seatDao;
	
	@Test
	public void testInsert() {
		Seat s=new Seat();
		s.setSeatName("5排-2座");
		s.setStatus("0");
		s.setCrt_time(new Date());
		s.setUp_time(new Date());
		s.setEnable_flg("1");
		long id = seatDao.insert(s);
		System.out.println(id);
		
		System.out.println("主键："+s.getId());
	}
	
	@Test
	public void testFindSeatById(){
		Seat seat = seatDao.findSeatById(1L);
		System.out.println(seat);
	}
	
	@Test
	public void testUpdateById(){
		Seat seat = seatDao.findSeatById(3L);
		System.out.println(seat);
		seat.setUp_time(new Date());
		seat.setSeatName("3排-1座");
		int i = seatDao.updateSeat(seat);
		System.out.println(i);
		System.out.println(seat);
	}
	
	@Test
	public void testFindAll(){
		List<Seat> lists = seatDao.findAll();
		System.out.println(lists);
	}

}
