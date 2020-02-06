package com.xlaser4j.demo.timer;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @package: com.xlaser4j.demo.timer
 * @author: Elijah.D
 * @time: 2020/2/6 17:58
 * @description:
 * @modified: Elijah.D
 */
@Component
public class TimerComponent {
    /**
     * 延迟第一个任务开始2s,需要配合rate或者delay两种,不支持cron
     * Number of milliseconds to delay before the first execution fixedRate or fixedDelay() task.
     */
    public void initialDelay() {
    }

    /**
     * 一个任务结束直到下一个任务启动的间隔时间3s
     * Execute the annotated method with a fixed period in milliseconds between the end of the last
     * invocation and the start of the next.
     * <p>
     * 当任务执行时间大于3s,那么3s的时候第二次任务没有触发
     */
    @Scheduled(fixedDelay = 3000, initialDelay = 3000)
    public void fixedDelay() {
        System.out.println("fixedDelay:==== " + new Date());
    }

    /**
     * 启动两个任务的的间隔时间,无论第一个任务是否完成,2s后都会启动第二个任务
     * Execute the annotated method with a fixed period in milliseconds between invocations.
     * <p>
     * 无论任务执行时间是否大于3s,3s的时候已经触发了第二个任务
     */
    @Scheduled(fixedRate = 2000, initialDelay = 6000)
    public void fixedRate() {
        System.out.println("fixedRate:===== " + new Date());
    }

    /**
     * unix中的cron表达式
     * A cron-like expression, extending the usual UN*X definition to include triggers
     * on the second as well as minute, hour, day of month, month and day of week.
     * <p>
     * cron组成:秒/分/时/天/月/星期/年,其中年不是必填,星期和天有一个需要设置?表示互斥关系,表示每月的某一天,或第几周的某一天
     * <p>
     * 0/3 * * ? * *: 斜线表示每多长时间执行一次,这里就是每3秒执行一次,0是从0秒开始
     * * 3/20 * ? * *: 3/20表示每隔20分钟执行一次,“3”表示从第3分钟开始执行
     */
    @Scheduled(cron = "0/1 * * * * ?")
    public void cron() {
        System.out.println("cron:========== " + new Date());
    }
}
