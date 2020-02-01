package com.xlaser4j.demo.repository;

import java.util.List;

import com.xlaser4j.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * @package: com.xlaser4j.demo.repository
 * @author: Elijah.D
 * @time: 2020/1/31 21:00
 * @description:
 * @modified: Elijah.D
 */
@RepositoryRestResource(path = "users")
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    /**
     * /users/search/query
     * <p>
     * 自定义方法额外路径/search,默认路径是方法名,通过path修改,rel是返回结果展示的字段,可以自定义修改
     * <p>
     * 通过/search方法查看所有自定义方法信息,http://localhost:8080/api/v1/users/search
     *
     * @param prefix
     * @return
     */
    @RestResource(path = "/query", rel = "listByName")
    List<UserEntity> findAllByNameStartingWith(String prefix);
}
