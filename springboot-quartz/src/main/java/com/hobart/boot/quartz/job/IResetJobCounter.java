package com.hobart.boot.quartz.job;

public interface IResetJobCounter {
    
    boolean resetCounter(String jobName, String jobGroup) throws Exception;
}
