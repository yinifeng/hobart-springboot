package com.defu.meeting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.defu.meeting.model.Seat;
import com.defu.meeting.service.SeatService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class SeatController {
	@Autowired
	private SeatService seatService;
	
	@GetMapping("/seat/list")
	public String list(@RequestParam(value="pn",defaultValue="1") Integer pn,Model model){
		PageHelper.startPage(pn, 10);//每页显示4条
		List<Seat> listSeat = seatService.listSeat();
		PageInfo<Seat> pageInfo = new PageInfo<>(listSeat, 3);//navigatePages连续显示的页数
		model.addAttribute("pageInfo", pageInfo);
		return "seat/list";
	}
	
	@GetMapping("/seat/addSeat")
	public String toAddPage(){
		return "seat/add";
	}
	
	@PostMapping("/seat/addSeat")
	public String addSeat(Seat seat){
		seatService.addSeat(seat);
		return "redirect:/seat/list";
	}
}
