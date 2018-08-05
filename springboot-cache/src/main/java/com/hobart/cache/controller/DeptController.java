package com.hobart.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hobart.cache.model.Department;
import com.hobart.cache.service.DeptService;

@RestController
public class DeptController {
	@Autowired
	private DeptService deptService;
	
	@GetMapping("/dept/{id}")
	public Department getDeptById(@PathVariable("id") Integer id){
		return deptService.getDeptById(id);
	}
}
