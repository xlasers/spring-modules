package com.xlasers.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * The type Redis application test.
 *
 * @package: com.xlasers.spring
 * @author: Elijah.D
 * @time: CreateAt 2018/9/28 && 14:45
 * @description: test
 * @copyright: Copyright © 2018 xlasers
 * @version: V1.0
 * @modified: Elijah.D
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTest {
    /**
     * Test app.
     */
    @Test
    public void testApp() {
        String[] args = {};
        RedisApplication.main(args);
    }
}
