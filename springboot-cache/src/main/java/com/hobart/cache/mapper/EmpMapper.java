package com.hobart.cache.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hobart.cache.model.Employee;

@Mapper
public interface EmpMapper {
	@Select("select * from emp where id=#{id}")
	Employee getEmp(Integer id);
	
	@Update("update emp set name=#{name},email=#{email},age=#{age},sex=#{sex},did=#{dId} where id=#{id}")
	void updateEmp(Employee emp);
	
	@Select("select * from emp where name=#{name}")
	Employee getEmyByName(String name);
}
