package com.hobart.zookeeper.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "hobart")
public class HobartProperties {
    private AliyunProperties aliyun = new AliyunProperties();
    
    private ZookeeperProperties zk = new ZookeeperProperties();
}
