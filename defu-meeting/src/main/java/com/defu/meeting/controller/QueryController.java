package com.defu.meeting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QueryController {
	
	@GetMapping("/customer/query")
	public String toQueryPage(){
		return "query/query";
	}
}
