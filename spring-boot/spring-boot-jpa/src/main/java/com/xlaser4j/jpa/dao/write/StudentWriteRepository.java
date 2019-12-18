package com.xlaser4j.jpa.dao.write;

import javax.transaction.Transactional;

import com.xlaser4j.jpa.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @package: com.xlaser4j.jpa.dao.read
 * @author: Elijah.D
 * @time: 2019/12/18 22:52
 * @description:
 * @copyright: Copyright(c) 2019
 * @version: V1.0
 * @modified: Elijah.D
 */
public interface StudentWriteRepository extends JpaRepository<StudentEntity, Long>, JpaSpecificationExecutor<StudentEntity> {
    /**
     * update
     *
     * @param name
     * @param id
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE student SET NAME = :name WHERE ID = :id", nativeQuery = true)
    Integer updateNameById(@Param("name") String name, @Param("id") Long id);

    /**
     * save
     *
     * @param name
     * @param age
     * @return
     */
    @Query(value = "INSERT INTO student(name,age) VALUES(?1,?2)", nativeQuery = true)
    @Modifying
    @Transactional
    Integer saveStudent(String name, Integer age);

    /**
     * delete
     *
     * @param name
     * @param age
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM student WHERE name = ?1 AND age = ?2", nativeQuery = true)
    void deleteByNameAndAge(String name, int age);
}
