package com.hobart.boot.quartz.service;


import com.hobart.boot.quartz.config.JobProperties;
import com.hobart.boot.quartz.dto.QuartzJobDto;
import com.hobart.boot.quartz.job.SimpleQuartzScheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * quartz 任务管理
 */
@Service
public class QuartzJobManagerService {
    
    @Autowired
    private SimpleQuartzScheduler quartzScheduler;


    /***
     * 校验任务名称和组 是否能匹配到配置的可运行任务
     * @param name
     * @param group
     * @return
     */
    public boolean checkJobNameAndGroup(String name,String group){
        return quartzScheduler.getRunningJobByGroupAndName(group, name) == null;
    }
    
    /**
     * 根据名称和组判断某个job是否存在
     * @param name job名称
     * @param group job组
     * @return
     */
    public boolean existJob(String name, String group) throws SchedulerException {
        return quartzScheduler.checkExists(name, group);
    }

    /**
     * 获取Trigger状态
     * @param name job名称
     * @param group job组
     * @return
     * @throws SchedulerException
     */
    public Trigger.TriggerState getTriggerState(String name, String group) throws SchedulerException {
        return this.quartzScheduler.getTriggerState(name, group);
    }

    /**
     * 获取Job信息
     *
     * @param name
     * @param group
     * @return
     * @throws SchedulerException
     */
    public QuartzJobDto getJobInfo(String name, String group) {
        
        try {
            JobProperties.ScheduleJob jobProperties = quartzScheduler.getRunningJobByGroupAndName(group, name);
            Trigger.TriggerState triggerState = quartzScheduler.getTriggerState(name, group);
            QuartzJobDto jobDto=new QuartzJobDto();
            jobDto.setJobId(jobProperties.getJobId());
            jobDto.setJobName(jobProperties.getJobName());
            jobDto.setJobGroup(jobProperties.getJobGroup());
            jobDto.setCronExpression(jobProperties.getCronExpression());
            jobDto.setJobDesc(jobProperties.getDesc());
            jobDto.setTriggerState(triggerState == null ? "不存在调度器" : triggerState.name());
            return jobDto;
        }catch (SchedulerException sex){
            
        }catch (Exception ex){
            
        }
        return null;
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
        return this.quartzScheduler.modifyJob(name, group, time);
    }

    /**
     * 暂停所有任务
     *
     * @throws SchedulerException
     */
    public void pauseAllJob() throws SchedulerException {
        this.quartzScheduler.pauseAllJob();
    }

    /**
     * 暂停某个任务
     *
     * @param name
     * @param group
     * @throws SchedulerException
     */
    public void pauseJob(String name, String group) throws SchedulerException {
        this.quartzScheduler.pauseJob(name, group);
    }

    /**
     * 暂停某个任务
     *
     * @param name
     * @param group
     * @throws SchedulerException
     */
    public void pauseTrigger(String name, String group) throws SchedulerException {
        this.quartzScheduler.pauseTrigger(name, group);
    }

    /**
     * 恢复所有任务
     *
     * @throws SchedulerException
     */
    public void resumeAllJob() throws SchedulerException {
        this.quartzScheduler.resumeAllJob();
    }

    /**
     * 恢复某个任务
     *
     * @param name
     * @param group
     * @throws SchedulerException
     */
    public void resumeJob(String name, String group) throws SchedulerException {
        this.quartzScheduler.resumeJob(name, group);
    }


    /**
     * 恢复某个任务
     *
     * @param name
     * @param group
     * @throws SchedulerException
     */
    public void resumeTrigger(String name, String group) throws SchedulerException {
        this.quartzScheduler.resumeTrigger(name, group);
    }

    /**
     * 删除某个任务
     *
     * @param name
     * @param group
     * @throws SchedulerException
     */
    public void deleteJob(String name, String group) throws SchedulerException {
        this.quartzScheduler.deleteJob(name, group);
    }
    
    public String getInterfaceName(String name,String group){
        JobProperties.ScheduleJob job = quartzScheduler.getRunningJobByGroupAndName(group, name);
        return job == null ? null : job.getInterfaceName();
    }
    
    
    
}
