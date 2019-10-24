package com.hobart.zookeeper.config;

import com.hobart.zookeeper.properties.HobartProperties;
import com.hobart.zookeeper.registry.RegistryCenterFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetAddress;

@Component
@Order
@Slf4j
public class ZookeeperInitRunner implements CommandLineRunner {
    @Resource
    private HobartProperties hobartProperties;
    
    @Value("${spring.application.name}")
    private String applicationName;
    
    @Override
    public void run(String... args) throws Exception {
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        log.info("###ZookeeperInitRunner，init. HostAddress={}, applicationName={}", hostAddress, applicationName);
        RegistryCenterFactory.startup(hobartProperties, hostAddress, applicationName);
        log.info("###ZookeeperInitRunner，finish<<<<<<<<<<<<<");
    }
}
