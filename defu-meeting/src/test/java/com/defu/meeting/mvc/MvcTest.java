package com.defu.meeting.mvc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.defu.meeting.DefuMeetingApplication;
import com.defu.meeting.vo.CustomerVo;
import com.github.pagehelper.PageInfo;

//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@SpringBootTest(classes=DefuMeetingApplication.class)

@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class MvcTest {
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	@Before
	public void initMockMvc(){
		mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
		System.out.println("******************"+context);
	}
	
	@Test
	public void testPage() throws Exception{
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/customer/list2"))	
		.andReturn();
		
		//mockMvc.perform(MockMvcRequestBuilders.get("/customer/list2", uriVars))
		
		Thread.sleep(5000);
		MockHttpServletRequest request = result.getRequest();
		PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
		System.out.println(pageInfo);
//		System.out.println("当前页码："+pageInfo.getPageNum());
//		System.out.println("总页码："+pageInfo.getPages());
//		System.out.println("总记录数："+pageInfo.getTotal());
	}
}
