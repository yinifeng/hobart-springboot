package com.hobart.boot.quartz.job;


import com.hobart.boot.quartz.config.JobProperties;

/**
 * 任务逻辑执行接口抽象
 * @author job
 *
 */
public interface IJobService {
	
	void execute(JobProperties.ScheduleJob scheduleJob) throws Exception;
}
