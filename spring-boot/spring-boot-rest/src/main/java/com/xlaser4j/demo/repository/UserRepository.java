package com.xlaser4j.demo.repository;

import com.xlaser4j.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @package: com.xlaser4j.demo.repository
 * @author: Elijah.D
 * @time: 2020/1/31 21:00
 * @description:
 * @modified: Elijah.D
 */
@RepositoryRestResource(path = "users")
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
