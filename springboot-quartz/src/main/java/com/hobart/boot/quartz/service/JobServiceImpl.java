package com.hobart.boot.quartz.service;

import com.hobart.boot.quartz.config.JobProperties;
import com.hobart.boot.quartz.job.IJobService;
import com.hobart.boot.quartz.job.IResetJobCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service("physicalExceptionInfoService")
public class JobServiceImpl implements IJobService,IResetJobCounter{
    
    private static final Logger logger= LoggerFactory.getLogger(JobServiceImpl.class);
    
    private final AtomicInteger counter=new AtomicInteger(0);
    
    private final Object lock=new Object();
    
    @Autowired
    private QuartzJobManagerService quartzJobManagerService;
    
    @Override
    public void execute(JobProperties.ScheduleJob scheduleJob) throws Exception {
        synchronized (this.lock) {
            logger.info("==>>>>>>> job 执行调度开始======");
            Thread.sleep(1000);

            //counter获取数据不是线程安全的会脏读
            if(counter.get() < 3){
                logger.info("第"+(counter.get()+1)+"发送钉钉消息.....");
                counter.incrementAndGet();
            }else{
                logger.info("发送了"+(counter.get())+"次钉钉消息，任务停止.....");
                //quartzJobManagerService.pauseJob(scheduleJob.getJobName(), scheduleJob.getJobGroup());
                quartzJobManagerService.pauseTrigger(scheduleJob.getJobName(), scheduleJob.getJobGroup());
            }

            logger.info("##<<<<<< job 执行调度完成######");
        }
    }

    @Override
    public boolean resetCounter(String jobName, String jobGroup) throws Exception {
        this.counter.set(0);
        //quartzJobManagerService.resumeJob(jobName, jobGroup);
        
        quartzJobManagerService.resumeTrigger(jobName, jobGroup);
        return true;
    }
}
