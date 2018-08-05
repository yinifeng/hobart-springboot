package com.hobart.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hobart.cache.exception.ServiceException;



/**
 * 快速体验缓存
 * 	1、开启基于注解的缓存 @EnableCaching
 * 	2、标注缓存注解即可
 * 		@Cacheable
 * 		@CacheEvict
 * 		@CachePut
 * 默认使用的ConcurrentMapCacheManager==ConcurrentMapCache将数据保存在ConcurrentMap的store中
 * 开发中使用缓存中间件：redis、memcached
 * 
 * 整合redis作为缓存中间件
 * 	1、docker安装redis
 * 	2、导入redis启动器
 * 	3、配置redis连接
 * 	4、测试缓存
 * 		原理：CacheManager==>Cache 缓存组件来实际给缓存中存取数据
 * 		1)、引入redis的starter，容器中保存的是 RedisCacheManager
 * 		2)、RedisCacheManager 帮我们创建RedisCache来作为缓存组件；RedisCache通过操作redis缓存数据
 * 		3)、默认保存的数据k-v都是Object；利用序列化保存，如何保存json；
 * 			1、引入redis的starter，cacheManager变为RedisCacheManager
 * 			2、默认创建的RedisCacheManager操作redis的时候使用的是RedisTemplate<Object,Object>
 * 			3、RedisTemplate<Object,Object>是默认使用jdk的序列化机制
 * 		4)、自定义cacheManager
 * 
 * @author hobart
 *
 */
@SpringBootApplication
@RestController
@MapperScan(basePackages={"com.hobart.cache.mapper"})
@EnableCaching
public class CacheApplication {
	
	@GetMapping("/ex/{id}")
	public void ex1(@PathVariable("id") Integer id) throws Exception{
		if(id == 1){
			throw new Exception("数据库连接失败");
		}else{
			throw new ServiceException("用户不存在!");
		}
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(CacheApplication.class, args);
	}
}
