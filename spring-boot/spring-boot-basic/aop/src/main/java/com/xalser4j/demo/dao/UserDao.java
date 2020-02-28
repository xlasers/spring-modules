package com.xalser4j.demo.dao;

import org.springframework.stereotype.Component;

/**
 * @package: com.xalser4j.demo.dao
 * @author: Elijah.D
 * @time: 2019/12/14 14:41
 * @description:
 * @modified: Elijah.D
 */
@Component
public class UserDao {
    /**
     * test get
     *
     * @param id
     * @return
     */
    public String getUserById(Integer id) {
        System.out.println("...getUserById方法正在执行...");
        return "success";
    }

    /**
     * test delete
     *
     * @param id
     */
    public void deleteUserById(Integer id) {
        System.out.println("...deleteUserById方法正在执行...");
    }
}
