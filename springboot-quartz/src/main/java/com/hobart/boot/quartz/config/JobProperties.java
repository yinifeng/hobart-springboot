package com.hobart.boot.quartz.config;

import com.hobart.boot.quartz.enums.JobStaus;
import com.hobart.boot.quartz.model.BaseModel;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "jiayuan.schedule")
public class JobProperties extends BaseModel {

    private static final long serialVersionUID = -1191155855117916615L;

    private List<ScheduleJob> jobs = new ArrayList<ScheduleJob>();

    public List<ScheduleJob> getJobs() {
        return jobs;
    }

    public void setJobs(List<ScheduleJob> jobs) {
        this.jobs = jobs;
    }

    public static class ScheduleJob extends BaseModel {

        private static final long serialVersionUID = -3147139062250664870L;
        //job的编号
        private String jobId;
        //job名称
        private String jobName;
        //job所属组
        private String jobGroup;
        //job状态，1：运行，0：停止
        private JobStaus jobStatus;
        //cron表达式
        private String cronExpression;
        //描述
        private String desc;
        //接口名称的bean
        private String interfaceName;
        
        private String shellScript1="";
        
        private String shellScript2="";
        
        private String shellScript3="";

        public String getJobId() {
            return jobId;
        }

        public void setJobId(String jobId) {
            this.jobId = jobId;
        }

        public String getJobName() {
            return jobName;
        }

        public void setJobName(String jobName) {
            this.jobName = jobName;
        }

        public String getJobGroup() {
            return jobGroup;
        }

        public void setJobGroup(String jobGroup) {
            this.jobGroup = jobGroup;
        }

        public JobStaus getJobStatus() {
            return jobStatus;
        }

        public void setJobStatus(int jobStatus) {
            this.jobStatus = JobStaus.getJobStaus(jobStatus);
        }

        public String getCronExpression() {
            return cronExpression;
        }

        public void setCronExpression(String cronExpression) {
            this.cronExpression = cronExpression;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getInterfaceName() {
            return interfaceName;
        }

        public void setInterfaceName(String interfaceName) {
            this.interfaceName = interfaceName;
        }

        public String getShellScript1() {
            return shellScript1;
        }

        public void setShellScript1(String shellScript1) {
            this.shellScript1 = shellScript1;
        }

        public String getShellScript2() {
            return shellScript2;
        }

        public void setShellScript2(String shellScript2) {
            this.shellScript2 = shellScript2;
        }

        public String getShellScript3() {
            return shellScript3;
        }

        public void setShellScript3(String shellScript3) {
            this.shellScript3 = shellScript3;
        }
    }

}
