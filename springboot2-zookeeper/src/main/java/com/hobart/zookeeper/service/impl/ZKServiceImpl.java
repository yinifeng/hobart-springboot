package com.hobart.zookeeper.service.impl;

import com.hobart.zookeeper.service.BaseService;
import com.hobart.zookeeper.service.ZKService;
import org.springframework.stereotype.Service;

@Service
public class ZKServiceImpl extends BaseService implements ZKService {


    @Override
    public Long getId() {
        return this.generateId();
    }
}
