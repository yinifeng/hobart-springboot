package com.hubo.resource.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmpController {
	
	@ResponseBody
	@RequestMapping("/emps")
	public String list(){
		return "Hello emps";
	}
}
