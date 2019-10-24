package com.hobart.zookeeper.service;

import com.hobart.zookeeper.generator.IncrementIdGenerator;
import com.hobart.zookeeper.generator.UniqueIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseService implements IService{

    /**
     * The Logger.
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    protected long generateId() {
        return UniqueIdGenerator.getInstance(IncrementIdGenerator.getServiceId()).nextId();
    }
}
