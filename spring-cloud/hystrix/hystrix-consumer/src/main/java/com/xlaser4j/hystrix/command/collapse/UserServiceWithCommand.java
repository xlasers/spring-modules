package com.xlaser4j.hystrix.command.collapse;

import java.util.Arrays;
import java.util.List;

import com.xlaser4j.hystrix.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @package: com.xlaser4j.hystrix.command.collapse
 * @author: Elijah.D
 * @time: 2020/3/25 15:35
 * @description:
 * @modified: Elijah.D
 */
@Service
public class UserServiceWithCommand {
    private final RestTemplate template;

    public UserServiceWithCommand(RestTemplate template) {
        this.template = template;
    }

    /**
     * 消费端对接,服务端提供的是一次查询一个,我们把服务端接口改造成list接受,这里也对应的list调用
     *
     * @param ids id
     * @return list
     */
    public List<User> listUsers(List<Integer> ids) {
        User[] users = template.postForObject("http://hystrix-provider/rc", ids, User[].class);
        assert users != null;
        return Arrays.asList(users);
    }
}
