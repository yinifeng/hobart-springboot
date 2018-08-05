package com.hobart.cache.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.hobart.cache.model.Department;

@Mapper
public interface DeptMapper {
	@Select("select * from dept where id=#{id}")
	Department getDept(Integer id);
}
