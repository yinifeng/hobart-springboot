package com.hobart.zookeeper.config;

import com.hobart.zookeeper.properties.HobartProperties;
import com.hobart.zookeeper.properties.ZookeeperProperties;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class CuratorFrameworkConfig {
    @Resource
    private HobartProperties hobartProperties;
    
    @Bean(initMethod = "start",destroyMethod = "close")
    public CuratorFramework curatorFramework(){
        ZookeeperProperties zookeeperProperties = hobartProperties.getZk();
        return CuratorFrameworkFactory.newClient(zookeeperProperties.getZkAddressList(),
                zookeeperProperties.getSessionTimeoutMilliseconds(),
                zookeeperProperties.getConnectionTimeoutMilliseconds(),
                new ExponentialBackoffRetry(zookeeperProperties.getBaseSleepTimeMilliseconds(), zookeeperProperties.getMaxRetries(), zookeeperProperties.getMaxSleepTimeMilliseconds()));
                //new RetryNTimes(zookeeperProperties.getMaxRetries(),zookeeperProperties.getMaxSleepTimeMilliseconds()));
    }
}
