package com.hobart.zookeeper.lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * zookeeper 实现的分布式锁
 * 
 * 
 * 这种监听节点下的子节点变化
 * 会产生羊群效应
 * 大量设置分布式锁 失效会有问题
 * 
 */
@Slf4j
@Component
public class DistributedLockByCurator implements InitializingBean {
    
    private static final String ROOT_PATH_LOCK = "mylock";
    
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    
    @Autowired
    private CuratorFramework curatorFramework;
    
    @Override
    public void afterPropertiesSet() throws Exception {
        this.curatorFramework = this.curatorFramework.usingNamespace("lock-namespace");
        String path = "/" + ROOT_PATH_LOCK;
        try {
            if (curatorFramework.checkExists().forPath(path) == null){
                curatorFramework.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath(path);
            }
            addWathcer(ROOT_PATH_LOCK);//wathcer 事件创建成功
        } catch (Exception e) {
            log.error("connect zookeeper fail,please check the log >> {}",e.getMessage(),e);
        }
    }

    /**
     * 创建事件监听
     * @param path
     * @throws Exception
     */
    private void addWathcer(String path) throws Exception {
        String keyPah;
        if (ROOT_PATH_LOCK.equals(path)){
            keyPah = "/" + path;
        }else{
            keyPah = "/" + ROOT_PATH_LOCK + "/" + path;
        }
        final PathChildrenCache cache = new PathChildrenCache(this.curatorFramework, keyPah, false);
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        cache.getListenable().addListener((client,event)->{
            if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED)){
                String oldPath = event.getData().getPath();
                log.info("上一个节点 [{}] 已经被断开",oldPath);
                if (oldPath.contains(path)){
                    //释放计数器，让当前的请求获取锁
                    countDownLatch.countDown();
                }
            }
        });
    }

    /**
     * 获取分布式锁
     * @param path
     */
    public void acquireLock(String path){
        String keyPath = "/"+ROOT_PATH_LOCK+"/"+path;
        while (true){
            try {
                curatorFramework.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.EPHEMERAL)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath(keyPath);
                log.info("success to acquire lock for path:{}",keyPath);
                break;
            } catch (Exception e) {
                log.info("failed to acquire lock for path:{}",keyPath);
                try {
                    if (countDownLatch.getCount() <= 0){
                        countDownLatch = new CountDownLatch(1);
                    }
                    countDownLatch.await();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public boolean release(String path){
        try {
            String keyPath = "/"+ROOT_PATH_LOCK+"/"+path;
            if (curatorFramework.checkExists().forPath(keyPath) != null){
                curatorFramework.delete().forPath(keyPath);
            }
        } catch (Exception e) {
            log.error("failed to release lock");
            return false;
        }
        return true;
    }
}
