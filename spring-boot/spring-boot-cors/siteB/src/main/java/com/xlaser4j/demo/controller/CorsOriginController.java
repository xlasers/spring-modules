package com.xlaser4j.demo.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @package: com.xlaser4j.demo.controller
 * @author: Elijah.D
 * @time: 2020/1/23 21:47
 * @description:
 * @modified: Elijah.D
 */
@RestController
@RequestMapping("/annotation")
@CrossOrigin("http://localhost:8081")
public class CorsOriginController {
    /**
     * test get
     *
     * @return
     */
    @GetMapping("/get")
    public String get() {
        return "get请求,在配置跨域之后可以直接请求,浏览器network查看:Access-Control-Allow-Origin: http://localhost:8081!";
    }

    /**
     * test put
     * <p>
     * {@link org.springframework.web.servlet.config.annotation.CorsRegistration#maxAge(long)}
     *
     * @return
     */
    @PutMapping("/put")
    public String put() {
        return "put请求,在配置跨域之后在请求之前会先发出一个探测请求,如果支持才真正发出put请求,之后在设定的时间内,不在发出探测请求,浏览器network查看即可!";
    }
}
