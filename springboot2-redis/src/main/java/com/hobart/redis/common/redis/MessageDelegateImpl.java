package com.hobart.redis.common.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Description:reids消息接收实现类
 * @Project:boot-sis
 * @File:MessageDelegateImpl.java
 * @Package:org.niugang.mq
 * @Date:2018年7月2日上午9:37:08
 * @author:niugang
 * @Copyright (c) 2018, 863263957@qq.com All Rights Reserved.
 */

public class MessageDelegateImpl implements MessageDelegate {
    private static final Logger logger = LoggerFactory.getLogger(MessageDelegateImpl.class);

    //@Autowired
    //private LogService logService;

    public void handleMessage(String message) {
        //if (StringUtils.isNotBlank(message)) {
        //    logger.info("log message :{}", message);
        //    LogParseBean parseObject = JSON.parseObject(message, LogParseBean.class);
        //    if(StringUtils.isNotBlank(parseObject.getType())) {
        //        LogEntity logEntity = new LogEntity();
        //        BeanUtils.copyProperties(parseObject, logEntity);
        //        logEntity.setId(UUID.randomUUID().toString().replaceAll("\\-", ""));
        //        logService.save(logEntity);
        //    }
    }

}
 
