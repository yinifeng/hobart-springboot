package com.defu.meeting.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.defu.meeting.service.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService UserService;
	
	/**
	 * 管理页面
	 * @return
	 */
	@RequestMapping({"/admin","/admin.html"})
	public String admin(){
		return "admin";
	}

	@PostMapping("/admin/login")
	public String login(@RequestParam("username") String username, 
			@RequestParam("password") String password,
			Map<String,Object> map,HttpSession session) {
		//System.out.println(username+"<-------->"+password);
		if(UserService.login(username, password)){//登录成功
			session.setAttribute("userAdmin", username);
			/*秒为单位，1800= 60*30 即30分种*/
			session.setMaxInactiveInterval(1800);
			//登录成功防止表单重复提交可以重定向到主页
			return "redirect:/main.html";
		}else{//登录失败
			map.put("adminMsg", "用户名或密码错误");
			return "admin";
		}
	}
	
	@RequestMapping("/success")
	public String success(Map<String,Object> map){
		//Thymeleaf 默认到templates 目录下找页面
		map.put("msg", "hello");
		return "success";
	}
	
	@GetMapping("/admin/logout")
	public String logout(HttpSession session){
		session.removeAttribute("userAdmin");
		return "admin";
	}
}
