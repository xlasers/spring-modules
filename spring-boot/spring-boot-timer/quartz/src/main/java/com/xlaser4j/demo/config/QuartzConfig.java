package com.xlaser4j.demo.config;

import java.util.Date;
import java.util.Objects;

import com.xlaser4j.demo.job.CustomJobBean;
import org.quartz.JobDataMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.*;

/**
 * @package: com.xlaser4j.demo.config
 * @author: Elijah.D
 * @time: 2020/2/6 19:26
 * @description:
 * @modified: Elijah.D
 */
@Configuration
public class QuartzConfig {
    /**
     * 自定义类方法注册为jobDetail,需要指明类,方法
     *
     * @return
     */
    @Bean
    MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean() {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        jobDetail.setTargetBeanName("customJob");
        jobDetail.setTargetMethod("execute");

        return jobDetail;
    }

    /**
     * 继承QuartzBean注册为jobDetail,无需额外配置,可以接收参数
     *
     * @return
     */
    @Bean
    JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
        jobDetail.setJobClass(CustomJobBean.class);

        JobDataMap param = new JobDataMap();
        param.put("param", "custom value");
        jobDetail.setJobDataMap(param);
        return jobDetail;
    }

    /**
     * 触发器:简单触发器
     * <p>
     * repeatCount:设置为3重复三次,执行4次
     *
     * @return
     */
    @Bean
    SimpleTriggerFactoryBean simpleTriggerFactoryBean() {
        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(Objects.requireNonNull(methodInvokingJobDetailFactoryBean().getObject()));
        trigger.setStartTime(new Date());
        trigger.setStartDelay(5000);
        trigger.setRepeatCount(3);
        trigger.setRepeatInterval(3000);
        return trigger;
    }

    /**
     * 触发器: cron触发器,遵循cron表达式
     *
     * @return
     */
    @Bean
    CronTriggerFactoryBean cronTriggerFactoryBean() {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(Objects.requireNonNull(jobDetailFactoryBean().getObject()));
        trigger.setCronExpression("0/4 * * ? * *");
        trigger.setStartDelay(20000);
        return trigger;
    }

    /**
     * 创建定时任务,并注入触发器
     *
     * @return
     */
    @Bean
    SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setTriggers(simpleTriggerFactoryBean().getObject(), cronTriggerFactoryBean().getObject());
        return factoryBean;
    }
}
