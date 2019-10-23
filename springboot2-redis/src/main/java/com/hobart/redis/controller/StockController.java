package com.hobart.redis.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 测试分布式锁
 * redis方案
 */
@RestController
public class StockController {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RedissonClient redissonClient;

    /**
     * JVM级别锁
     * @return
     */
    @GetMapping("/deduct-stock")
    public String deductStock(){
        //并发请求会出现并发问题
        //1.单实例 JVM级别加锁
        synchronized (this){
            int stock = Integer.parseInt(redisTemplate.opsForValue().get("stock"));
            if (stock > 0){
                int realStock = stock - 1;
                redisTemplate.opsForValue().set("stock", realStock+"");
                System.out.println("扣减成功，剩余库存："+realStock+"");
            }else {
                System.err.println("扣减库存失败，库存不足");
            }
        }
        //多实例还是存在并发问题

        return "end";
    }


    /**
     * redis分布式锁
     * setnx
     * @return
     */
    @GetMapping("/deduct-stock2")
    public String deductStock2(){
        //并发请求会出现并发问题
        //2.利用redis实现分布式锁
        String lockKey="product_001";
        String lockValue = UUID.randomUUID().toString();
        try {
            //Boolean result = redisTemplate.opsForValue().setIfAbsent(lockKey, "hobart");
            //对这个锁加个过期时间，防止kill程序，导致锁永远不释放
            //redisTemplate.expire(lockKey,10, TimeUnit.SECONDS);
            
            //上面2步操作替换一下操作 这2个操作为原子操作，要么执行都成功，要么都失败
            Boolean result = redisTemplate.opsForValue().setIfAbsent(lockKey,lockValue,30, TimeUnit.SECONDS);
            if (!result){
                System.err.println("已持有该锁");
                return "error";
            }
            int stock = Integer.parseInt(redisTemplate.opsForValue().get("stock"));
            if (stock > 0){
                int realStock = stock - 1;
                redisTemplate.opsForValue().set("stock", realStock+"");
                System.out.println("扣减成功，剩余库存："+realStock+"");
            }else {
                System.err.println("扣减库存失败，库存不足");
            }
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lockValue.equals(redisTemplate.opsForValue().get(lockKey))){
                redisTemplate.delete(lockKey);
            }
        }
        return lockValue;
    }

    /**
     * redisson 完美的分布式锁
     * @return
     */
    @GetMapping("/deduct-stock3")
    public String deductStock3(){
        //并发请求会出现并发问题
        //2.利用redis实现分布式锁
        String lockKey="product_001";
        String lockValue = UUID.randomUUID().toString();
        RLock lock = redissonClient.getLock(lockKey);
        try {
            lock.lock(30, TimeUnit.SECONDS);//会开启子线程对这个失效时间蓄力
            int stock = Integer.parseInt(redisTemplate.opsForValue().get("stock"));
            if (stock > 0){
                int realStock = stock - 1;
                redisTemplate.opsForValue().set("stock", realStock+"");
                System.out.println("扣减成功，剩余库存："+realStock+"");
            }else {
                System.err.println("扣减库存失败，库存不足");
            }
            //Thread.sleep(10000);
        } finally {
            lock.unlock();
        }
        return lockValue;
    }
   
}
