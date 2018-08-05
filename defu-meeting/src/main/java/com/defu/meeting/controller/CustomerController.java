package com.defu.meeting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.defu.meeting.service.CustomerService;
import com.defu.meeting.vo.CustomerVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customer/list")
	public String list(@RequestParam(value="pn",defaultValue="1") Integer pn,Model model){
		PageHelper.startPage(pn, 10);
		List<CustomerVo> lists = customerService.list();
		PageInfo<CustomerVo> pageInfo = new PageInfo<>(lists, 3);
		model.addAttribute("pageInfo", pageInfo);
		return "customer/list";
	}
	
	@GetMapping("/customer/list2")
	public String list2(@RequestParam(value="pn",defaultValue="1") Integer pn,Model model){
		
		System.out.println(">>>>"+pn);
		PageHelper.startPage(pn, 10);
		List<CustomerVo> lists = customerService.list();
		System.out.println(lists);
		PageInfo<CustomerVo> pageInfo = new PageInfo<>(lists, 3);//navigatePages连续显示的页数
		model.addAttribute("pageInfo", pageInfo);
		return "customer/list2";
	}
	
	
	@GetMapping("/customer/updateCustomer/{id}")
	public String toEditPage(@PathVariable("id") String id,Model model){
		CustomerVo customer = customerService.getCustomerById(id);
		model.addAttribute("customer",customer);
		return "customer/update";
	}
	
	@PutMapping("/customer/editCustomer")
	public String updateCustomer(CustomerVo customer){
		customerService.editCustomer(customer);
		return "redirect:/customer/list";
	}
	
    //来到员工添加页面
    @GetMapping("/customer/addCustomer")
    public String toAddPage(){
        return "customer/add";
    }
    
    @PostMapping("/customer/addCustomer")
    public String addCustomer(CustomerVo customer){
    	customerService.saveCustomer(customer);
    	return "redirect:/customer/list";
    }
}
