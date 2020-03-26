package com.xlaser4j.feign.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;

import org.springframework.web.bind.annotation.*;

/**
 * @package: com.xlaser4j.feign.controller
 * @author: Elijah.D
 * @time: 2020/3/26 11:46
 * @description:
 * @modified: Elijah.D
 */
@RestController
public class SimpleController {
    /**
     * 测试feign
     *
     * @return msg
     */
    @GetMapping("/ps")
    public String simple() {
        return "Simple Test";
    }

    /**
     * 测试feign,带参数: key-value
     *
     * @return msg
     */
    @GetMapping("/pp")
    public String simpleWithParam(String name) {
        return "Simple Test With Param: " + name;
    }

    /**
     * 测试feign,带参数: body
     *
     * @return msg
     */
    @PostMapping("/pb")
    public String simpleWithBody(@RequestBody Integer[] ids) {
        return "Simple Test With Body: " + Arrays.toString(ids);
    }

    /**
     * 测试feign,带参数: header
     * <p>
     * 注意header参数的入参encode/接参decode
     *
     * @return msg
     */
    @GetMapping("/ph")
    public String simpleWithHeader(@RequestHeader("head") String head) throws UnsupportedEncodingException {
        return "Simple Test With Head: " + URLDecoder.decode(head, "UTF-8");
    }
}
