package com.xlaser4j.feign.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @package: com.xlaser4j.feign.api
 * @author: Elijah.D
 * @time: 2020 /3/26 14:10
 * @description: 通过feign的继承特性实现统一管理定义接口
 * @modified: Elijah.D
 */
public interface ICommonService {
    /**
     * Common get string.
     *
     * @return the string
     */
    @GetMapping("/g")
    String commonGet();

    /**
     * Common get with param string.
     *
     * @param key key
     * @return string
     */
    @GetMapping("/gp")
    String commonGetWithParam(@RequestParam("key") String key);

    /**
     * Common get with body string.
     *
     * @param keys keys
     * @return string
     */
    @PostMapping("/gb")
    String commonGetWithBody(@RequestBody String[] keys);
}
