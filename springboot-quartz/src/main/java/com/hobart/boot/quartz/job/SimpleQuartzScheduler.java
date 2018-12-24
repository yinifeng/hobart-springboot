package com.hobart.boot.quartz.job;


import com.hobart.boot.quartz.config.JobProperties;
import com.hobart.boot.quartz.config.JobProperties.ScheduleJob;
import com.hobart.boot.quartz.enums.JobStaus;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 封装Quartz Scheduler
 * 
 * @author hubo
 */
public class SimpleQuartzScheduler implements InitializingBean,DisposableBean {
    private static final String GROUP_LINK_NAME=".";
    
    private final Scheduler scheduler;
    
    private final JobProperties jobProperties;
    
    private final ConcurrentHashMap<String,JobProperties.ScheduleJob> runningJobIdCahe=new ConcurrentHashMap<>();
    
    private final ConcurrentHashMap<String,ScheduleJob> stopJobIdCache=new ConcurrentHashMap<>();
    
    private final ConcurrentHashMap<String,ScheduleJob> runningJobCache=new ConcurrentHashMap<>();

    private final ConcurrentHashMap<String,ScheduleJob> stopJobCache=new ConcurrentHashMap<>();
    
    public SimpleQuartzScheduler(Scheduler scheduler, JobProperties jobProperties) {
        this.scheduler = scheduler;
        this.jobProperties = jobProperties;
    }
    
    
    public final String getJobCacheKey(String jobGroup,String jobName){
        return jobGroup+GROUP_LINK_NAME+jobName;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.buildScheduleJob();
        
        if(!scheduler.isStarted()){//未启动
            scheduler.start();
        }
    }
    
    private void buildScheduleJob() throws SchedulerException {
        List<ScheduleJob> jobs = this.jobProperties.getJobs();
        for (ScheduleJob job : jobs) {
            if (StringUtils.isBlank(job.getJobName()) || StringUtils.isBlank(job.getJobGroup())){
                continue;
            }
            //这个job任务不定时
            if (job.getJobStatus() == JobStaus.STOP){
                stopJobCache.put(getJobCacheKey(job.getJobGroup(), job.getJobName()), job);
                stopJobIdCache.put(job.getJobId(), job);
                continue;
            }
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (null == trigger) {
                JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
                        .withIdentity(job.getJobName(), job.getJobGroup()).withDescription(job.getDesc()).build();
                jobDetail.getJobDataMap().put("scheduleJob", job);
                jobDetail.isConcurrentExectionDisallowed();
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
                trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup())
                        .withSchedule(scheduleBuilder).build();
                scheduler.scheduleJob(jobDetail, trigger);
                runningJobCache.put(getJobCacheKey(job.getJobGroup(), job.getJobName()), job);
                runningJobIdCahe.put(job.getJobId(), job);
            } else {
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        }
    }

    public void listJobInfo() throws SchedulerException {
        String name="";
        String group="";
        JobKey jobKey = new JobKey(name, group);

        JobDetail jobDetail = scheduler.getJobDetail(jobKey);


        boolean flag = jobDetail.requestsRecovery();

        scheduler.checkExists(jobKey);
        //

        ///////////////////////////////////////////////////////
        //TriggerKey triggerKey = new TriggerKey(name, group);
        TriggerKey triggerKey = TriggerKey.triggerKey(name, group);

        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);


        Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
        //PAUSED
    }

    /**
     * 获取运行的任务的元信息
     * @param jobGroup 任务组
     * @param jobName 任务名称
     * @return
     */
    public ScheduleJob getRunningJobByGroupAndName(String jobGroup,String jobName){
        return this.runningJobCache.get(getJobCacheKey(jobGroup, jobName));
    }

    /**
     * 获取未运行的任务的元信息
     * @param jobGroup 任务组
     * @param jobName 任务名称
     * @return
     */
    public ScheduleJob getStopJobByGroupAndName(String jobGroup,String jobName){
        return this.stopJobCache.get(getJobCacheKey(jobGroup, jobName));
    }

    /**
     * 获取运行的任务的元信息
     * @param jobId 任务id
     * @return
     */
    public ScheduleJob getRunnigJobById(String jobId){
        return this.runningJobIdCahe.get(jobId);
    }

    /**
     * 获取未运行的任务的元信息
     * @param jobId 任务id
     * @return
     */
    public ScheduleJob getStopJobById(String jobId){
        return this.stopJobIdCache.get(jobId);
    }
    

    /**
     * 删除某个任务
     *
     * @param name
     * @param group
     * @throws SchedulerException
     */
    public void deleteJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null)
            return;
        scheduler.deleteJob(jobKey);
    }


    /**
     * 恢复某个任务
     * 
     * 恢复这个任务的时候
     * 会立即并行执行队列中的任务数，
     * 并行的个数是配置的线程池的个数的空闲线程数
     * 
     *
     * @param name
     * @param group
     * @throws SchedulerException
     */
    public void resumeJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null)
            return;
        scheduler.resumeJob(jobKey);
    }
    
    /**
     * 恢复所有任务
     *
     * @throws SchedulerException
     */
    public void resumeAllJob() throws SchedulerException {
        scheduler.resumeAll();
    }


    /**
     * 获取Trigger状态
     * @param name job名称
     * @param group job组
     * @return
     * @throws SchedulerException
     */
    public Trigger.TriggerState getTriggerState(String name, String group) throws SchedulerException {
        TriggerKey triggerKey = new TriggerKey(name, group);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        if (cronTrigger == null){
            return null;
        }
        Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
        return triggerState;
    }

    /**
     * 停止调度器
     * 停止调度Job任务
     * @param name
     * @param group
     * @return
     * @throws SchedulerException
     */
    public void pauseTrigger(String name, String group) throws SchedulerException {

        TriggerKey triggerKey = TriggerKey.triggerKey(name, group);
        //CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        
        
        //TriggerKey triggerKey = new TriggerKey(name, group);
        //scheduler.unscheduleJob(triggerKey);
        this.scheduler.pauseTrigger(triggerKey);
    }

    /**
     * 
     * 恢复调度器
     * 停止调度Job任务
     * @param name
     * @param group
     * @return
     * @throws SchedulerException
     */
    public void resumeTrigger(String name, String group) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(name, group);
        //TriggerKey triggerKey = new TriggerKey(name, group);
        this.scheduler.resumeTrigger(triggerKey);
        
        //scheduler.rescheduleJob(triggerKey, trigger);
    }
    
    /**
     * 修改某个任务的执行时间
     *
     * @param name
     * @param group
     * @param time
     * @return
     * @throws SchedulerException
     */
    public boolean modifyJob(String name, String group, String time) throws SchedulerException {
        Date date = null;
        TriggerKey triggerKey = new TriggerKey(name, group);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        String oldTime = cronTrigger.getCronExpression();
        if (!oldTime.equalsIgnoreCase(time)) {
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(time);
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group)
                    .withSchedule(cronScheduleBuilder).build();
            date = scheduler.rescheduleJob(triggerKey, trigger);
        }
        return date != null;
    }

    /**
     * 暂停某个任务
     * 
     * 暂停的这个job并没有真正的暂停
     * trigger还是会按定时的表达式生成job任务到队列中
     * 待这个恢复的时候，会并行的执行这个队列中的任务
     * 每次并行的个数 都是根据你配置的线程池 的空闲线程数来并行的
     * 
     * 
     *
     * @param jobName
     * @param jobGroup
     * @throws SchedulerException
     */
    public void pauseJob(String jobName, String jobGroup) throws SchedulerException {
        JobKey jobKey = new JobKey(jobName, jobGroup);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null)
            return;
        scheduler.pauseJob(jobKey);
    }

    /**
     * 暂停所有任务
     * @throws SchedulerException
     */
    public void pauseAllJob() throws SchedulerException {
        scheduler.pauseAll();
    }

    /**
     * 判断某个任务是否存在
     * 
     * @param jobName job名称
     * @param jobGroup job组
     * @return
     * @throws SchedulerException
     */
    public boolean checkExists(String jobName,String jobGroup) throws SchedulerException {
        JobKey jobKey = new JobKey(jobName, jobGroup);
        return this.scheduler.checkExists(jobKey);
    }

    /**
     * 获取Trigger状态
     *
     * @param jobName
     * @param jobGroup
     * @return
     * @throws SchedulerException
     */
    public String getTriggerStateName(String jobName, String jobGroup) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        if (cronTrigger !=null){
            return scheduler.getTriggerState(triggerKey).name();
        }
        return null;
    }
    
    
    public Scheduler getScheduler() {
        return scheduler;
    }

    public JobProperties getJobProperties() {
        return jobProperties;
    }

    @Override
    public void destroy() throws Exception {
        if(this.scheduler.isStarted()){
            scheduler.shutdown();
        }
    }
}
