package com.hobart.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hobart.cache.model.Employee;
import com.hobart.cache.service.EmpService;

@RestController
public class EmpController {
	@Autowired
	private EmpService empService;
	
	@GetMapping("/emp/{id}")
	public Employee getEmp(@PathVariable("id") Integer id){
		return empService.getEmp(id);
	}
	
	@GetMapping("/emp")
	public Employee update(Employee emp){
		return empService.updateEmp(emp);
	}
	
	@GetMapping("/delEmp/{id}")
	public String delete(@PathVariable("id") Integer id){
		empService.delEmp(id);
		return "success";
	}
	
	@GetMapping("/emp/name/{name}")
	public Employee getEmpByName(@PathVariable("name")String name){
		return empService.getEmpByName(name);
	}
}
