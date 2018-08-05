package com.hobart.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

import com.hobart.cache.mapper.DeptMapper;
import com.hobart.cache.model.Department;


@Service
public class DeptService {
	@Autowired
	private DeptMapper deptMapper;
	
	@Qualifier("deptCacheManager")
	@Autowired
	private RedisCacheManager deptCacheManager;
	
	/**
	 * 序列到redis能变成json
	 * 但是反序列化异常，因为是使用的RedisTemplate<Object, Employee>作的反序列化
	 * 所以要增加配置RedisTemplate<Object, Department>
	 * 
	 * 注解方式
	 * @param id
	 * @return
	 */
//	@Cacheable(value="dept",cacheManager="deptCacheManager")
//	public Department getDeptById(Integer id) {
//		System.out.println("查询dept："+id);
//		return deptMapper.getDept(id);
//	}
	
	public Department getDeptById(Integer id) {
		System.out.println("查询dept："+id);
		Department dept = deptMapper.getDept(id);
		//获取某个缓存
		Cache cache = deptCacheManager.getCache("dept");
		cache.put("dept:"+id,dept);
		
		return dept;
	}
	

}
