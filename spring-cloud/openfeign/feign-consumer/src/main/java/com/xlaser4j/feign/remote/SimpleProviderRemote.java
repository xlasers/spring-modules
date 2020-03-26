package com.xlaser4j.feign.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @package: com.xlaser4j.feign.remote
 * @author: Elijah.D
 * @time: 2020 /3/26 11:48
 * @description:
 * @modified: Elijah.D
 */
@FeignClient(value = "feign-provider")
public interface SimpleProviderRemote {
    /**
     * 测试feign调用,只需要注明client的服务名,同时保证返回值,参数路径与远端服务一致即可
     * <p>
     * 方法名无所谓,建议保持一致
     *
     * @return string msg
     */
    @GetMapping("/ps")
    String simpleTest();

    /**
     * 测试feign: k-v
     * <p>
     * 带参数: <b>key-value形式参数在feign调用端,必须加上@RequestParam绑定,否则传参无效</b>
     *
     * @param name the name
     * @return string msg
     */
    @GetMapping("/pp")
    String simpleWithParam(@RequestParam("name") String name);

    /**
     * 测试feign: body
     *
     * @param ids the ids
     * @return string msg
     */
    @PostMapping("/pb")
    String simpleWithBody(@RequestBody Integer[] ids);

    /**
     * 测试feign: header
     * <p>
     * 带参数: <b>通过其他http调用一样,header参数中文必须: 入参encode编码,接收方decode解码,否则乱码</b>
     *
     * @param head name
     * @return string msg
     */
    @GetMapping("/ph")
    String simpleWithHeader(@RequestHeader("head") String head);
}
