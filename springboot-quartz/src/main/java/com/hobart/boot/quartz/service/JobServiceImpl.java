package com.hobart.boot.quartz.service;

import com.hobart.boot.quartz.config.JobProperties;
import com.hobart.boot.quartz.job.IJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("physicalExceptionInfoService")
public class JobServiceImpl implements IJobService{
    
    private static final Logger logger= LoggerFactory.getLogger(JobServiceImpl.class);
    
    @Override
    public void execute(JobProperties.ScheduleJob scheduleJob) throws Exception {
        logger.info("==>>>>>>> job 执行调度开始======");
        Thread.sleep(30000);
        logger.info("##<<<<<< job 执行调度完成######");
    }
}
