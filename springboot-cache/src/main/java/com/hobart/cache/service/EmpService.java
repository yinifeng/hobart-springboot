package com.hobart.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.hobart.cache.mapper.EmpMapper;
import com.hobart.cache.model.Employee;

@CacheConfig(cacheNames="emp",cacheManager="empCacheManager") //抽取公共缓存
@Service
public class EmpService {
	@Autowired
	private EmpMapper empMapper;
	
	
	/**
	 * 
	 * 
	 *原理：
	 * 		1、自动配置类CacheAutoConfiguration
	 * 		2、缓存的配置类
	 * 		org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
			org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
			org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration
			org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration
			org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration
			org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration
			org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
			org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration
			org.springframework.boot.autoconfigure.cache.GuavaCacheConfiguration
			org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration
			org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration
			
			3、哪个配置类默认生效：SimpleCacheConfiguration
			
			4、给容器注册一个CacheManager：ConcurrentMapCacheManager
			5、CacheManager可以获取和创建ConcurrentMapCache类型的缓存组件，它的作用把数据缓存在ConcurrentMap<Object, Object> store属性中
	 * 
	 * 运行流程：
	 * 		1、方法运行之前，先去查询Cache(缓存组件)，按caheNames指定的名字获取；
	 * 			(CacheManager先获取相应的缓存)，第一次获取缓存如果没有Cache组件会自动创建。
	 * 		CacheAspectSupport
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 将方法的运行结果进行缓存；以后再要相同的数据，直接从缓存中取，不用调用方法；
	 * 
	 * cacheManager管理多个Cache组件的，对缓存真正CRUD操作在Cache组件中，每一个缓存组件有自己唯一一个名字
	 * @Cacheable几个属性：
	 * 		cacheNames/value：指定缓存组件的名字；将方法的返回结果放在数组中，是数组的方式，可以指定多个缓存；
	 * 		key：缓存数据使用的key；可以用它来指定。默认是使用方法参数的值 1-方法的返回值
	 * 			编写SpEL; #id;参数id的值 #a0	#p0	#root.args[0]
	 * 			getEmp[id]-->key="#root.methodName+'['+#id+']'"
	 * 		keyGenerator：key的生成器；可以自己指定key的生成器组件id
	 * 			key/keyGenerator：二选一使用
	 * 		cacheManager:指定缓存管理器，或者cacheResolver指定获取解析器
	 * 		condition：指定符合条件的情况下才缓存
	 * 			condition="#a0>1" 第一个参数的值大于1的时候才进行缓存
	 * 		unless：否定缓存；当unless指定的条件为true，方法的返回值就不会被缓存，可以获取到结果进行判断
	 * 					unless = "#result == null"
	 * 				unless="#a0==2":如果第一个参数的值等于2，那么结果不缓存了
	 * 		sync：是否使用异步模式
	 * 			异步模式unless不起作用
	 * 		
	 * @param id
	 * @return
	 */
	@Cacheable(cacheNames={"emp"}/*,keyGenerator="myKeyGenerator",condition="#a0>1"*/)
	public Employee getEmp(Integer id){
		System.out.println("查询"+id+"号员工");
		return empMapper.getEmp(id);
	}
	
	/**
	 * @CachePut:既调用方法，又更新缓存
	 * 修改数据库的某个数据，同时更新缓存；
	 * 运行时机：
	 * 	1、先调用目标方法
	 * 	2、将目标方法的结果缓存起来
	 * 
	 * 测试步骤：
	 * 	1、查询1号员工；查到的结果会放在缓存中；
	 * 		key：1 value：name：tom
	 * 	2、以后查询还是之前的结果
	 * 	3、更新1号员工：name=张三 sex=0 age=12
	 * 		将方法的返回值也放进缓存了；
	 * 		key：传入的emp对象	值：返回的emp对象；
	 * 	4、查询1号员工？
	 * 		应该是更新后的员工：
	 * 			key = "#employee.id"：使用传入参数的员工id；
	 * 			key = "#result.id"：使用返回后的id
	 * 			@Cacheable是不能用#result
	 * 		为什么是没有更新前的？【1号员工没有在缓存中更新】
	 * 
	 * @return
	 */
	@CachePut(value="emp",key = "#result.id")
	public Employee updateEmp(Employee emp){
		System.out.println("update "+emp);
		empMapper.updateEmp(emp);
		return emp;
	}
	
	/**
	 * @CacheEvict清除缓存
	 * key：指定清除的数据
	 * allEntries:指定清除这个缓存中的所有缓存
	 * beforeInvocation=false:缓存的清除是否在方法之前执行
	 * 		默认代表的是在方法之后执行；如果出现异常缓存就不会清除
	 * beforeInvocation=true
	 * 		代表清除缓存操作是在方法运行之前执行，无论方法是否出现异常，缓存都清除
	 * @param id
	 */
	@CacheEvict(/*value="emp",*/key="#id",allEntries=true,beforeInvocation=true)
	public void delEmp(Integer id){
		System.out.println("deleteEmp:"+id);
		int i=1/0;//报个错，使方法之后无法执行
	}
	
	
	/**
	 * 复杂的缓存
	 * @CachePut 目标方法每次都会执行
	 * @param name
	 * @return
	 */
	@Caching(cacheable={@Cacheable(/*value="emp",*/key="#name")},
				put={@CachePut(/*value="emp",*/key="#result.id"),
				 @CachePut(/*value="emp",*/key="#result.email")}
			)
	public Employee getEmpByName(String name){
		return empMapper.getEmyByName(name);
	}
}
