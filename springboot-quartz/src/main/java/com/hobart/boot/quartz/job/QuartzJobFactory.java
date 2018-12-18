package com.hobart.boot.quartz.job;


import com.hobart.boot.quartz.config.JobProperties;
import com.hobart.boot.quartz.config.JobProperties.ScheduleJob;
import com.hobart.boot.quartz.support.SpringContextHolder;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 任务处理
 *
 * @author hubo
 */
//@PersistJobDataAfterExecution
//@DisallowConcurrentExecution  //不允许并发执行
public class QuartzJobFactory implements Job {
    private static final Logger logger = LoggerFactory.getLogger(QuartzJobFactory.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) jobExecutionContext.getMergedJobDataMap().get("scheduleJob");

        IJobService jobService = SpringContextHolder.getBean(scheduleJob.getInterfaceName(), IJobService.class);
        try {
            jobService.execute(scheduleJob);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}
