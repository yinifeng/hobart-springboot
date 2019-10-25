package com.hobart.zookeeper.controller;

import com.hobart.zookeeper.lock.DistributedLockByCurator;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Zookeeper 实现的分布式锁性能 比redis实现的分布式锁差一点
 * 但是 zookeeper实现的分布式锁可靠，稳定，不会出现死锁
 * 
 * redis的分布式锁可能会出现死锁，如果要追求高并发使用redis实现分布式锁
 */
@RestController
@RequestMapping("/lock")
public class LockController {
    @Autowired
    private DistributedLockByCurator lock;
    @Autowired
    private CuratorFramework curatorFramework;
    
    private final String path = "test";
    
    private final static String ROOT_LOCK="/hubo-lock/";
    
    @GetMapping("/lock1")
    public Boolean myLock1(){
        Boolean flag;
        lock.acquireLock(path);
        try {
            //TODO 业务代码
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            flag = lock.release(path);
        }
        flag = lock.release(path);
        return flag;
    }

    @GetMapping("/lock2")
    public Boolean myLock2(){
        Boolean flag;
        lock.acquireLock(path);
        try {
            //TODO 业务代码
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            flag = lock.release(path);
        }
        flag = lock.release(path);
        return flag;
    }

    @GetMapping("/clock1")
    public Boolean curatorLock1(){
        InterProcessMutex lock =new InterProcessMutex(curatorFramework,ROOT_LOCK+path);
        try {
            lock.acquire();
            System.out.println(">>>>>>>>>"+Thread.currentThread().getName()+"获取锁");
            try {
                //TODO 业务代码
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                lock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @GetMapping("/clock2")
    public Boolean curatorLock2(){
        InterProcessMutex lock =new InterProcessMutex(curatorFramework,ROOT_LOCK+path);
        try {
            lock.acquire();
            System.out.println(">>>>>>>>>"+Thread.currentThread().getName()+"获取锁");
            try {
                //TODO 业务代码
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                lock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    
    
}
