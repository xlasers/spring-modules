package com.xlaser4j.feign.controller;

import java.util.Arrays;

import com.xlaser4j.feign.api.ICommonService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.feign.controller
 * @author: Elijah.D
 * @time: 2020/3/26 14:27
 * @description: 通过feign的继承特性实现统一管理定义接口
 * @modified: Elijah.D
 */
@RestController
public class CommonControllerImpl implements ICommonService {
    @Override
    public String commonGet() {
        return "Extend Common Test";
    }

    @Override
    public String commonGetWithParam(String key) {
        return "Extend Common Test With Param: " + key;
    }

    @Override
    public String commonGetWithBody(String[] keys) {
        return "Extend Common Test With Body: " + Arrays.toString(keys);
    }
}
