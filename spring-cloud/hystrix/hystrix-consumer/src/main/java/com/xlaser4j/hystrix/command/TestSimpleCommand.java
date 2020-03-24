package com.xlaser4j.hystrix.command;

import java.util.Random;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

/**
 * @package: com.xlaser4j.hystrix.annotation
 * @author: Elijah.D
 * @time: 2020/3/25 12:34
 * @description: 请求使用hystrix
 * @modified: Elijah.D
 */
public class TestSimpleCommand extends HystrixCommand<String> {
    private RestTemplate restTemplate;

    /**
     * 添加构造参数
     *
     * @param setter       父类参数,用于设置属性
     * @param restTemplate param
     */
    public TestSimpleCommand(Setter setter, RestTemplate restTemplate) {
        super(setter);
        this.restTemplate = restTemplate;
    }

    @Override
    protected String run() {
        int rand = new Random().nextInt(5);
        if (rand > 10) {
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException: 消费端异常也会进行降级,不抛出错误,而调用指定的fallback");
        }
        return restTemplate.getForObject("http://hystrix-provider/fb", String.class);
    }

    /**
     * command方式的容错
     * <p>
     * 通过{@link this#getExecutionException()}获取消费端的异常信息,似乎没有注解可以忽略的功能
     *
     * @return error
     */
    @Override
    protected String getFallback() {
        return "Command: provider异常(或者消费端异常),hystrix容错降级,调用; 异常信息(如果为null,则是provider异常问题): " + getExecutionException().getMessage();
    }

    // 继承的方式仍然可以实现缓存,但是略显麻烦,不在演示
    //@Override
    //protected String getCacheKey() {
    //    return super.getCacheKey();
    //}
}
