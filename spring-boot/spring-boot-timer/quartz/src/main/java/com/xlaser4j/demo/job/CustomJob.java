package com.xlaser4j.demo.job;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * @package: com.xlaser4j.demo.job
 * @author: Elijah.D
 * @time: 2020/2/6 19:20
 * @description:
 * @modified: Elijah.D
 */
@Component
public class CustomJob {
    /**
     * job方法
     */
    public void execute() {
        System.out.println(new Date() + ": 自定义一个类方法作为job,config配置注册为job,但是不能接收参数,不够灵活!");
    }
}
