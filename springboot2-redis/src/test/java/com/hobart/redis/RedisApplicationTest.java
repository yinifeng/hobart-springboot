package com.hobart.redis;

import com.hobart.redis.model.User;
import com.hobart.redis.util.RedisUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

public class RedisApplicationTest extends AbstractApplication{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtils redisUtils;
    
    @Test
    public void testStringSet(){
        this.stringRedisTemplate.opsForValue().set("aaa", "111");

    }

    @Test
    public void testStringGet(){

        System.out.println("===========>"+this.stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void testObjSet(){
        User user= new User(100222L,"hubo","123456","hubo@qq.com","hobart");
        //ClusterOperations clusterOperations = redisTemplate.opsForCluster();
        ValueOperations<String,User> operations = redisTemplate.opsForValue();
        operations.set("com:hobart", user);
        //过期设置key
        operations.set("com:hobart:f", user, 60,TimeUnit.SECONDS);
    }

    @Test
    public void testObjGet(){
        ValueOperations<String,User> operations = redisTemplate.opsForValue();
        User user = operations.get("com.hobart");
        System.out.println(user);
    }

    @Test
    public void testRedisUtilsStringSet(){
        User user= new User(1001L,"hubo","123456","hubo@qq.com","hobart");
        redisUtils.set("bbbbb", user);
    }
}
