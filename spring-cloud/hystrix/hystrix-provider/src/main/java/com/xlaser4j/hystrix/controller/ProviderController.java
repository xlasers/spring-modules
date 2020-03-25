package com.xlaser4j.hystrix.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.xlaser4j.hystrix.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.hystrix.controller
 * @author: Elijah.D
 * @time: 2020/3/25 14:29
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class ProviderController {
    /**
     * 测试hystrix fallback
     *
     * @return msg
     */
    @GetMapping("/fb")
    public String testConsumerFallBack() {
        return "Hystrix Provider";
    }

    /**
     * 测试hystrix缓存
     *
     * @param param1 param1
     * @param param2 param2
     * @return msg
     */
    @GetMapping("/c")
    public String testConsumerCache(String param1, String param2) {
        return param1 + param2;
    }

    /**
     * 测试hystrix请求合并: 注意控制台输出
     * <p>
     * 当第一个请求传1,第二个请求传2等参数不同的情况,hystrix可以根据设置的策略自动合并请求,并且分配结果
     * <p>
     * 本质上这是一个单查询方法,为了请求合并,我们要把这个方法改造成list数组传参
     *
     * @return msg
     */
    @PostMapping("/rc")
    public List<User> testRequestCollapse(@RequestBody Integer[] ids) {
        System.out.println("==================== 请求调用 ==================== 参数: " + Arrays.toString(ids));
        return Arrays.stream(ids).map(id -> new User(id, "name-" + id, true)).collect(Collectors.toList());
    }
}
