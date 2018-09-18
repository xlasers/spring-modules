package com.xlasers.spring.util;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * The type Redis util test.
 *
 * @package: com.xlasers.spring.util
 * @author: Elijah.D
 * @time: CreateAt 2018/9/28 && 14:47
 * @description: test
 * @copyright: Copyright © 2018 xlasers
 * @version: V1.0
 * @modified: Elijah.D
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilTest {
    /**
     * The redis-util.
     */
    @Autowired
    RedisUtil redis;

    /**
     * Test redis util.
     */
    @Test
    public void testSet() {

        redis.set("123", "oneTwoThree");
        log.info("【getOne】redis : {}", redis.get("123"));

        redis.set("abc", "ABC");
        log.info("【getTwo】redis : {}", redis.get("abc"));

        redis.set("list", Lists.newArrayList("1", "2", "a"));
        log.info("【getList】redis : {}", redis.get("list"));
    }

    /**
     * Test expire.
     */
    @Test
    public void testExpire() {

        redis.set("123", "oneTwoThree");
        redis.expire("123", 20);
        log.info("【testExpire】redis设置有效期 : {}", redis.getExpire("123"));

        redis.set("abc", "ABC");
        log.info("【testExpire】redis未设置有效期 : {}", redis.getExpire("abc"));
    }

    /**
     * Test del.
     */
    @Test
    public void testDel() throws InterruptedException {

        redis.set("a", "ABC");
        redis.expire("a", 20);
        log.info("【testDel】redis 是否存在key = a : {}", redis.hasKey("a"));

        Thread.sleep(5000);

        redis.del("a");
        log.info("【testDel】redis 删除key = a 是否成功 : {}", !redis.hasKey("a"));
    }
}
