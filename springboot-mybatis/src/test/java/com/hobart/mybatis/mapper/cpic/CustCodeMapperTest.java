package com.hobart.mybatis.mapper.cpic;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hobart.mybatis.SpringbootMybatisApplication;
import com.hobart.mybatis.model.CustCode;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=SpringbootMybatisApplication.class)
public class CustCodeMapperTest {
	@Autowired
	private CustCodeMapper custCodeMapper;

	@Test
	public void test() {
		List<CustCode> list = custCodeMapper.findAll();
		System.out.println("==>"+list);
	}

}
