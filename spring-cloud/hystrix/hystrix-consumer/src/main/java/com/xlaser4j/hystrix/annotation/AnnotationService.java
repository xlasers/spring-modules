package com.xlaser4j.hystrix.annotation;

import java.util.Random;
import java.util.concurrent.Future;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @package: com.xlaser4j.hystrix.annotation
 * @author: Elijah.D
 * @time: 2020/3/25 12:34
 * @description: 注解使用hystrix
 * @modified: Elijah.D
 */
@Service
public class AnnotationService {
    private final RestTemplate restTemplate;

    public AnnotationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 通过注解实现容错,当调用异常时,会自动查找fallback方法;
     * <p>
     * 注解: 同步执行
     *
     * @return msg
     */
    @HystrixCommand(fallbackMethod = "testFallback")
    public String sync() {
        return restTemplate.getForObject("http://hystrix-provider/fb", String.class);
    }

    /**
     * 通过注解实现容错,当调用异常时,会自动查找fallback方法;
     * <p>
     * 注解: 异步执行{@link AsyncResult}
     *
     * @return msg
     */
    @HystrixCommand(fallbackMethod = "testFallback")
    public Future<String> async() {
        return new AsyncResult<String>() {
            @Override
            public String invoke() {
                return restTemplate.getForObject("http://hystrix-provider/fb", String.class);
            }
        };
    }

    /**
     * 通过注解实现容错,当调用异常时,会自动查找fallback方法;
     * <p>
     * 注解: 模拟consumer端发生异常,fallback自动降级,并且可以获取异常信息;
     * <p>
     * 可以忽略某种异常,不进行降级,直接返回错误
     *
     * @return msg
     */
    @HystrixCommand(fallbackMethod = "testFallback", ignoreExceptions = ArithmeticException.class)
    public String error() {
        int rand = new Random().nextInt(10);
        if (rand < 4) {
            throw new ArithmeticException("ArithmeticException: 这种异常不进行降级,直接返回错误信息!");
        }
        if (rand > 6) {
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException: 这种异常会进行降级,不抛出错误,而调用指定的fallback");
        }

        return restTemplate.getForObject("http://hystrix-provider/fb", String.class);
    }

    /**
     * 这里继续添加注解,还可以继续容错降级,业务中可能就是先查真实,不行就降级为缓存,再不行降级为mock
     * <p>
     * 这里接收参数Throwable,可以获取consumer端的异常信息
     *
     * @return error
     */
    public String testFallback(Throwable e) {
        return "Annotation: provider异常(或者consumer发生异常),hystrix容错降级,调用; 异常信息(如果为null,则是provider异常问题): " + e.getMessage();
    }

    /**
     * 通过注解实现hystrix中的缓存调用,当参数是相同时就会缓存结果: 同时要两个注解,而且fallback方法要参数一致
     * <p>
     * 默认是所有参数的拼接
     *
     * @return msg
     */
    @HystrixCommand(fallbackMethod = "testFallbackWithCache")
    @CacheResult
    public String cache(String param1, String param2) {
        System.out.println("\n=========== cache: 执行,服务调用,并且缓存 ===========");
        return restTemplate.getForObject("http://hystrix-provider/c?param1={1}&param2={2}", String.class, param1, param2);
    }

    /**
     * 通过注解实现hystrix中的缓存调用,指定缓存的key是param1,即使第二个参数不一样也会缓存
     * <p>
     * 可以通过{@link com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey}指定某一个参数
     *
     * @return msg
     */
    @HystrixCommand(fallbackMethod = "testFallbackWithCache")
    @CacheResult
    public String cacheWithKey(@CacheKey String param1, String param2) {
        System.out.println("\n=========== cacheWithKey: 执行,服务调用,并且缓存 ===========");
        return restTemplate.getForObject("http://hystrix-provider/c?param1={1}&param2={2}", String.class, param1, param2);
    }

    /**
     * 通过注解实现hystrix中的缓存清除
     * <p>
     * 必须指定commandKey为缓存的方法名,用于定位删除的缓存位置
     *
     * @return msg
     */
    @HystrixCommand(fallbackMethod = "testFallbackWithCache")
    @CacheRemove(commandKey = "cache")
    public String cacheRemove(String param1, String param2) {
        System.out.println("\n=========== cacheRemove: 删除缓存,再次请求cache方法会触发调用 ===========");
        return restTemplate.getForObject("http://hystrix-provider/c?param1={1}&param2={2}", String.class, param1, param2);
    }

    /**
     * fallback的参数要与方法一致: 异常参数放到最后位置
     *
     * @param param1 1
     * @param param2 2
     * @param e      exception
     * @return msg
     */
    public String testFallbackWithCache(String param1, String param2, Throwable e) {
        return "Annotation: provider异常(或者consumer发生异常),hystrix容错降级,调用; 异常信息(如果为null,则是provider异常问题): " + e.getMessage();
    }
}
