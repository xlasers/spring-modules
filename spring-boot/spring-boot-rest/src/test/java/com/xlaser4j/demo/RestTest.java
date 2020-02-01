package com.xlaser4j.demo;

import com.xlaser4j.demo.entity.UserEntity;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

/**
 * @package: com.xlaser4j.demo.controller
 * @author: Elijah.D
 * @time: 2020/1/31 21:52
 * @description:
 * @modified: Elijah.D
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestTest extends RestApplicationTest {
    private static final String URL = "http://localhost:8080/api/v1/users/";

    @Autowired
    private TestRestTemplate template;

    /**
     * base路径可以在config或者application.yml中设置{@link com.xlaser4j.demo.config.RestConfig}
     * <p>
     * /users等路径,可以在repository注解中设置{@link RepositoryRestResource#path()}
     */
    @Test
    public void testASave() {
        int age = (int)(Math.random() * 10);
        HttpEntity<UserEntity> request = new HttpEntity<>(new UserEntity("Elijah" + age, age));
        UserEntity user = template.exchange(URL, HttpMethod.POST, request, UserEntity.class).getBody();
        System.out.println(user);
    }

    @Test
    public void testBGet() {
        UserEntity user = template.getForEntity(URL + "/3", UserEntity.class).getBody();
        System.out.println(user);
    }

    /**
     * {@code UserEntity[] users = template.getForObject(URL, UserEntity[].class)}
     * <p>
     * 暂时不能自动反序列化??
     * Cannot deserialize instance of `com.xlaser4j.demo.entity.UserEntity[]` out of START_OBJECT token
     */
    @Test
    public void testCList() {
        Object userInfos = template.getForObject(URL, Object.class);
        System.out.println(userInfos);
    }

    @Test
    public void testDDelete() {
        template.delete(URL + "/1");
    }

    @Test
    public void testEPut() {
        UserEntity source = template.getForEntity(URL + "/3", UserEntity.class).getBody();
        System.out.println(source);
        assert source != null;
        source.setAge(source.getAge() + 1);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserEntity> request = new HttpEntity<>(source, headers);
        UserEntity target = template.exchange(URL + "/3", HttpMethod.PUT, request, UserEntity.class).getBody();
        System.out.println(target);
    }

    @Test
    public void testFCustom() {
        Object userInfos = template.getForObject(URL + "/search/query?prefix=E", Object.class);
        System.out.println(userInfos);
    }
}
