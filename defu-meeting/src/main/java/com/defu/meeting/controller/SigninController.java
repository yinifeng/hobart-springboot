package com.defu.meeting.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.defu.meeting.service.CustomerService;
import com.defu.meeting.vo.CustomerVo;

/**
 *  签到控制器
 * @author hobart
 *
 */
@Controller
public class SigninController {
	@Autowired
	private CustomerService customerService;
	
	/**
	 * 默认页面到签到页面
	 */
//	@RequestMapping({"/","index","index.html"})
//	public String index(){
//		return "index";
//	}
	@PostMapping("/meeting/signin")
	public String signin(@RequestParam("username") String name,
			@RequestParam("tel") String tel,Map<String, Object> map,Model model){
		//System.out.println(name+"<***************>"+tel);
		if(StringUtils.isBlank(tel)){
			map.put("signinMsg", "电话号码不能为空");
			return "signin";
		}
		//签到成功
		CustomerVo cust = customerService.signinByTel(name, tel);
		if(cust==null){
			map.put("signinMsg", "电话号码错误");
			return "signin";
		}
		//map.put("seatMsg",cust.getSeatName()==null ? "无座" : cust.getSeatName());
		model.addAttribute("customer", cust);
		return "signin";
	}
}
