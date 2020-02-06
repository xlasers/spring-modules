package com.xlaser4j.demo.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @package: com.xlaser4j.demo.job
 * @author: Elijah.D
 * @time: 2020/2/6 19:22
 * @description:
 * @modified: Elijah.D
 */
public class CustomJobBean extends QuartzJobBean {
    /**
     * 自定义一个参数,quartz执行过程中,可以接收参数
     */
    private String param;

    public void setParam(String param) {
        this.param = param;
    }

    /**
     * job执行方法
     *
     * @param context
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext context) {
        System.out.println(new Date() + ": 通过实现jobBean方法,注册为一个job,可以注入参数param: " + param + " 同时方法名executeInternal固定,配置简单,灵活易用!");
    }
}
