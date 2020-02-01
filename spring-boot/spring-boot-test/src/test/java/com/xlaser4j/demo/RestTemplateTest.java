package com.xlaser4j.demo;

import com.xlaser4j.demo.model.User;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

/**
 * @package: com.xlaser4j.demo
 * @author: Elijah.D
 * @time: 2020/2/1 17:18
 * @description:
 * @modified: Elijah.D
 */
public class RestTemplateTest extends ApplicationTest {
    @Autowired
    TestRestTemplate template;

    @Test
    public void testGet() {
        String name = template.getForObject("/a?name={1}", String.class, "Elijah");
        Assert.assertThat(name, Matchers.is("Test Elijah"));
        System.out.println(name);
    }

    @Test
    public void testPost() {
        HttpEntity<User> request = new HttpEntity<>(new User(1, "Martin", 18));
        ResponseEntity<User> response = template.postForEntity("/b", request, User.class);
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertNotNull(response.getBody());
        Assert.assertThat(18, Matchers.is(response.getBody().getAge()));

        System.out.println(response.getBody());
    }
}
