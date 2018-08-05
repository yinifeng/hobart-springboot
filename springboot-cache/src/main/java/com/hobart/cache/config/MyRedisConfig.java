package com.hobart.cache.config;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.hobart.cache.model.Department;
import com.hobart.cache.model.Employee;

@Configuration
public class MyRedisConfig {
	
	/**
	 * RedisAutoConfiguration
	 * @param redisConnectionFactory
	 * @return
	 * @throws UnknownHostException
	 */
	@Bean
	public RedisTemplate<Object, Employee> empRedisTemplate(
			RedisConnectionFactory redisConnectionFactory)
			throws UnknownHostException {
		RedisTemplate<Object, Employee> template = new RedisTemplate<Object, Employee>();
		template.setConnectionFactory(redisConnectionFactory);
		template.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(Employee.class));
		return template;
	}
	
	@Bean
	public RedisTemplate<Object, Department> deptRedisTemplate(
			RedisConnectionFactory redisConnectionFactory)
			throws UnknownHostException {
		RedisTemplate<Object, Department> template = new RedisTemplate<Object, Department>();
		template.setConnectionFactory(redisConnectionFactory);
		template.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(Department.class));
		return template;
	}
	
	
	/**
	 * RedisCacheConfiguration
	 * @param empRedisTemplate
	 * @return
	 */
	@Primary //两个RedisCacheManager 默认使用这个
	@Bean
	public RedisCacheManager empCacheManager(RedisTemplate<Object, Employee> empRedisTemplate) {
		RedisCacheManager cacheManager = new RedisCacheManager(empRedisTemplate);
		//设置前缀，默认会将CacheName作为key的前缀
		cacheManager.setUsePrefix(true);
		
		return cacheManager;
	}
	
	@Bean
	public RedisCacheManager deptCacheManager(RedisTemplate<Object, Department> deptRedisTemplate) {
		RedisCacheManager cacheManager = new RedisCacheManager(deptRedisTemplate);
		//设置前缀，默认会将CacheName作为key的前缀
		cacheManager.setUsePrefix(true);
		
		return cacheManager;
	}
}
