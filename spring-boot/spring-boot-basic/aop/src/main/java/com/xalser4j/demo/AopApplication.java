package com.xalser4j.demo;

import javax.annotation.PostConstruct;

import com.xalser4j.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @package: com.xalser4j.demo
 * @author: Elijah.D
 * @time: 2019/12/14 14:39
 * @description:
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
