package com.hobart.cache.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.hobart.cache.model.Department;
import com.hobart.cache.model.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmpMapperTest {
	@Autowired
	private EmpMapper empMapper;
	@Autowired
	private DeptMapper deptMapper;
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;//K-V为对象的模版类
	@Autowired
	private StringRedisTemplate stringRedisTemplate;//K-V为字符串的模版类
	@Autowired
	private RedisTemplate<Object, Employee> empRedisTemplate;
	
	
	/**
	 * Redis常见五大数据类型
	 * String(字符串)、List(列表)、Set(集合)、Hash(散列)、ZSet(有序集合)
	 * stringRedisTemplate.opsForValue()[String(字符串)]
	 * stringRedisTemplate.opsForList()[List(列表)]
	 * stringRedisTemplate.opsForSet()[Set(集合)]
	 * stringRedisTemplate.opsForHash()[Hash(散列)]
	 * stringRedisTemplate.opsForZSet()[ZSet(有序集合)]
	 */
	@Test
	public void testReids(){
		//给redis保存数据
		//stringRedisTemplate.opsForValue().append("msg", "hello");
		String msg = stringRedisTemplate.opsForValue().get("msg");
		System.out.println(msg);
		
		//list
		stringRedisTemplate.opsForList().leftPush("mylist", "1");
		stringRedisTemplate.opsForList().leftPush("mylist", "2");
		
	}
	
	@Test
	public void testRedis2Obj(){
		Employee emp = empMapper.getEmp(1);
		//默认如果保存对象，使用jdk序列化机制，序列化后的数据保存到redis中
		//redisTemplate.opsForValue().set("emp-01", emp);
		//1、将数据以json的方式保存
		//(1) 自己将json的方式保存
		//(2) redisTemplate默认的序列化规则;改变默认的序列化规则
		empRedisTemplate.opsForValue().set("emp-01", emp);
	}
	
	
	
	@Test
	public void testGetEmp() {
		Employee emp = empMapper.getEmp(1);
		System.out.println(emp);
	}
	
	@Test
	public void testGetDept(){
		Department dept = deptMapper.getDept(1);
		System.out.println(dept);
	}
}
