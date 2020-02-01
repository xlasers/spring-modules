package com.xlaser4j.demo.service;

import com.xlaser4j.demo.entity.UserEntity;
import com.xlaser4j.demo.repository.UserRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @package: com.xlaser4j.demo.service
 * @author: Elijah.D
 * @time: 2020/2/1 19:25
 * @description:
 * @modified: Elijah.D
 */
@Service
@CacheConfig(cacheNames = "redisCache")
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    /**
     * 开启缓存,默认是key是参数id值
     * <p>
     * 自定义condition,这里设置不缓存null:"#result != null"
     * 也可以使用unless: "#result == null"
     *
     * @param id
     * @return
     */
    @Cacheable(unless = "#result == null")
    public UserEntity getUserById(Integer id) {
        System.out.println("getUserById: 从数据库中获取数据,如果没有打印,则是从缓存中获取. id = " + id);
        return repo.findById(id).orElse(null);
    }

    /**
     * 更新缓存,使用keyGenerator自定义key为id值
     *
     * @param user
     * @return
     */
    @CachePut(keyGenerator = "customKeyGenerator")
    public UserEntity updateUser(UserEntity user) {
        System.out.println("updateUser: 更新user时同步更新缓存. user: " + user);
        return repo.save(user);
    }

    /**
     * 删除缓存,无关name参数,强制制定key为id
     *
     * @param id
     * @param name
     * @return
     */
    @CacheEvict(key = "#id", beforeInvocation = true)
    public void deleteUserById(Integer id, String name) {
        System.out.println("deleteUserById: 删除user时清空缓存,beforeInvocation设置为true意为先删除缓存,防止缓存挂掉时没有正确删除缓存. id: " + id);
        repo.deleteById(id);
    }
}
