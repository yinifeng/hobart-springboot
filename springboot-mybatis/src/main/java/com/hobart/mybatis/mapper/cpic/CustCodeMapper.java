package com.hobart.mybatis.mapper.cpic;

import java.util.List;

import com.hobart.mybatis.mapper.BashMapper;
import com.hobart.mybatis.model.CustCode;

public interface CustCodeMapper extends BashMapper {
	List<CustCode> findAll();
}
