package com.xlaser4j.demo.repository;

import com.xlaser4j.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @package: com.xlaser4j.demo.repository
 * @author: Elijah.D
 * @time: 2020/2/1 19:39
 * @description:
 * @modified: Elijah.D
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    /**
     * get max id
     *
     * @return
     */
    @Query(value = "select max(id) from user", nativeQuery = true)
    Integer getMaxId();
}
