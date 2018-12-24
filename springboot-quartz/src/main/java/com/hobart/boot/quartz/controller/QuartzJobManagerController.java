package com.hobart.boot.quartz.controller;


import com.hobart.boot.quartz.dto.QuartzJobDto;
import com.hobart.boot.quartz.job.IResetJobCounter;
import com.hobart.boot.quartz.model.WrapMapper;
import com.hobart.boot.quartz.model.Wrapper;
import com.hobart.boot.quartz.service.QuartzJobManagerService;
import com.hobart.boot.quartz.support.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class QuartzJobManagerController {

    @Autowired
    private QuartzJobManagerService quartzManagerService;
    
    
    private Wrapper checkJobNameAndJobGroup(String name,String group){
        if (StringUtils.isBlank(name) || StringUtils.isBlank(group)){
            return WrapMapper.error("任务名称或者任务组为空...");
        }else if(quartzManagerService.checkJobNameAndGroup(name, group)){
            return WrapMapper.error("任务名称或者任务组找不到任务...");
        }
        return null;
    }
    
    @ResponseBody
    @RequestMapping("/info")
    public Wrapper getQuartzJob(@RequestParam("name") String name, @RequestParam("group") String group) {
        Wrapper wrapper = checkJobNameAndJobGroup(name, group);
        if (wrapper !=null){
            return wrapper;
        }
        QuartzJobDto jobDto = quartzManagerService.getJobInfo(name, group);
        return WrapMapper.ok(jobDto);
    }

    @ResponseBody
    @RequestMapping("/modify")
    public Wrapper modifyQuartzJob(String name, String group, String time) {
        boolean flag = true;
        try {
            flag = quartzManagerService.modifyJob(name, group, time);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return WrapMapper.ok(flag);
    }

    @RequestMapping(value = "/pause")
    public void pauseQuartzJob(String name, String group) {
        try {
            quartzManagerService.pauseJob(name, group);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/pauseAll")
    public void pauseAllQuartzJob() {
        try {
            quartzManagerService.pauseAllJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/delete")
    public void deleteJob(String name, String group) {
        try {
            quartzManagerService.deleteJob(name, group);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/resetJob")
    public Wrapper re(String name, String group){
        //TODO
        String bean = quartzManagerService.getInterfaceName(name,group);
        if (StringUtils.isBlank(bean)){
            return WrapMapper.ok(String.format("[%s,%s]定时任务未找到",name,group));
        }
        IResetJobCounter resetJobCounter = SpringContextHolder.getBean(bean, IResetJobCounter.class);
        try {
            boolean flag = resetJobCounter.resetCounter(name, group);
            if(flag){
                return WrapMapper.ok("任务重置成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WrapMapper.ok(String.format("[%s,%s]定时任务重置失败",name,group));
    }
}
