package com.xlaser4j.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.demo.controller
 * @author: Elijah.D
 * @time: 2020/1/30 18:45
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class RedisController {
    private final StringRedisTemplate template;

    public RedisController(StringRedisTemplate template) {
        this.template = template;
    }

    /**
     * set redis
     */
    @GetMapping("/s")
    public void setRedis() {

        // VALUE
        ValueOperations<String, String> value = template.opsForValue();
        value.set("value", "valueValue");

        // lIST
        ListOperations<String, String> list = template.opsForList();
        List<String> data = new ArrayList<>();
        data.add("1");
        data.add("3");
        data.add("5");
        list.rightPushAll("list", data);

        // MAP
        HashOperations<String, Object, Object> map = template.opsForHash();
        map.put("map", "hashMap", "mapValue");
    }

    /**
     * get redis
     */
    @GetMapping("/g")
    public void getRedis() {
        System.out.println(template.opsForValue().get("value"));
        System.out.println(template.opsForList().range("list", 0, -1));
        System.out.println(template.opsForHash().get("map", "hashMap"));
    }
}
