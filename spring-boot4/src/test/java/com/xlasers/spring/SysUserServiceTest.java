package com.xlasers.spring;

import com.xlasers.spring.model.SysUser;
import com.xlasers.spring.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * The type Sys user service test.
 *
 * @package: com.xlasers.spring
 * @author: Elijah.D
 * @time: CreateAt 2018/10/6 && 13:23
 * @description: service测试类
 * @copyright: Copyright © 2018 xlasers
 * @version: V1.0
 * @modified: Elijah.D
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserServiceTest {
    /**
     * The User service.
     */
    @Autowired
    ISysUserService userService;

    /**
     * Test get user by id of first db.
     */
    @Test
    public void testGetUserByIdOfFirstDb() {
        SysUser user = userService.getUserByIdOfFirstDb(1);
        log.info("【testUserOne】获取user用户信息, user: {}", user);
    }

    /**
     * Test get user by id of second db.
     */
    @Test
    public void testGetUserByIdOfSecondDb() {
        SysUser user = userService.getUserByIdOfSecondDb(1);
        log.info("【testUserTwo】获取user用户信息, user: {}", user);
    }

    /**
     * Test get user by id of first and second.
     */
    @Test
    public void testGetUserByIdOfFirstAndSecond() {
        userService.getUserByIdOfFirstAndSecond(1);
    }

    /**
     * Test transaction.
     * <p>
     * 事务开启后:多数据源动态切换失败 !
     * 一个事务对应一个数据源,数据源就不能再进行随意切换了
     * <p>
     * TODO 动态数据源事务控制失效
     */
    @Test
    public void testTransaction() {

        log.info("【开启事务测试】");

        SysUser user = userService.getUserByIdOfFirstDb(1);
        log.info("【testUserOne】获取user用户信息, user: {}", user);

        SysUser userTwo = userService.getUserByIdOfSecondDb(1);
        log.info("【testUserTwo】获取user用户信息, user: {}", userTwo);
    }
}
