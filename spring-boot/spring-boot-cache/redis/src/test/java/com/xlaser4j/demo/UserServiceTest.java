package com.xlaser4j.demo;

import com.xlaser4j.demo.entity.UserEntity;
import com.xlaser4j.demo.repository.UserRepository;
import com.xlaser4j.demo.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @package: com.xlaser4j.demo
 * @author: Elijah.D
 * @time: 2020/2/1 19:42
 * @description:
 * @modified: Elijah.D
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    UserService service;

    @Autowired
    UserRepository repo;

    @Before
    public void setup() {
        int age = (int)(Math.random() * 10);
        repo.save(new UserEntity("Elijah" + age, age));
        System.out.println(repo.save(new UserEntity("Elijah" + age, age)));
    }

    @Test
    public void testGet() {

        // 第一次使用缓存,将结果缓存,后续不会再打印service中的输出,直接从缓存中获取结果(可以等待60s等待缓存过期,会再次打印一次)
        System.out.println(service.getUserById(5));

        // 直接从缓存中获取
        System.out.println(service.getUserById(5));
    }

    @Test
    public void testUpdate() {
        System.out.println(service.getUserById(7));
        System.out.println(service.getUserById(7));

        int age = (int)(Math.random() * 100);
        UserEntity user = new UserEntity("Elijah" + age, age);
        user.setId(7);
        System.out.println(service.updateUser(user));

        System.out.println(service.getUserById(7));
        System.out.println(service.getUserById(7));
    }

    @Test
    public void testDelete() {
        Integer id = repo.getMaxId();
        System.out.println(service.getUserById(id));
        System.out.println(service.getUserById(id));
        service.deleteUserById(id, "Just Test Key Unused Param.");

        System.out.println("\n=====删除缓存成功,则控制台输出打印getUserById信息,失败则不打印控制台信息!====\n");
        UserEntity user = service.getUserById(id);
        if (user == null) {
            System.out.println("CacheEvict:缓存数据已经删除!");
        } else {
            System.out.println("CacheEvict:缓存数据删除失败,读取缓存脏数据: " + user);
        }
    }
}
