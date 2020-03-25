package com.xlaser4j.hystrix.annotation.collapse;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xlaser4j.hystrix.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @package: com.xlaser4j.hystrix.annotation.collapse
 * @author: Elijah.D
 * @time: 2020/3/25 16:35
 * @description: 通过注解实现请求合并简单易配置
 * @modified: Elijah.D
 */
@Service
public class UserServiceWithAnnotation {
    private final RestTemplate template;

    public UserServiceWithAnnotation(RestTemplate template) {
        this.template = template;
    }

    /**
     * 标记为command,也就是批处理方法
     *
     * @param ids id
     * @return list
     */
    @HystrixCommand
    public List<User> listUsers(List<Integer> ids) {
        User[] users = template.postForObject("http://hystrix-provider/rc", ids, User[].class);
        assert users != null;
        return Arrays.asList(users);
    }

    /**
     * 指定批处理方法即可: 注意参数timerDelayInMilliseconds的名字
     *
     * @param id 参数
     * @return user
     */
    @HystrixCollapser(batchMethod = "listUsers", collapserProperties = @HystrixProperty(name = "timerDelayInMilliseconds", value = "600"))
    public Future<User> getUserById(Integer id) {
        return null;
    }
}
