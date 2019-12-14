package com.xalser4j.aop;

import javax.annotation.PostConstruct;

import com.xalser4j.aop.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @package: com.xalser4j.aop
 * @author: Elijah.D
 * @time: 2019/12/14 14:39
 * @description:
 * @copyright: Copyright(c) 2019
 * @version: V1.0
 * @modified: Elijah.D
 */
@SpringBootApplication
public class AopApplication {
    @Autowired
    UserDao dao;

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);
    }

    @PostConstruct
    public void testAspect() {
        String user = dao.getUserById(1);
        dao.deleteUserById(1);
        System.out.println("\n\ntestAspect:====>> " + user + "\n\n");
    }
}
