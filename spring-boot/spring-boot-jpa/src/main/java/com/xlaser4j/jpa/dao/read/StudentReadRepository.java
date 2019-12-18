package com.xlaser4j.jpa.dao.read;

import java.util.List;

import com.xlaser4j.jpa.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @package: com.xlaser4j.jpa.dao.read
 * @author: Elijah.D
 * @time: 2019/12/18 22:52
 * @description:
 * @copyright: Copyright(c) 2019
 * @version: V1.0
 * @modified: Elijah.D
 */
public interface StudentReadRepository extends JpaRepository<StudentEntity, Long>, JpaSpecificationExecutor<StudentEntity> {
    /**
     * Find by name starting with and age less than student.
     *
     * @param name
     * @param age
     * @return
     */
    List<StudentEntity> findByNameStartingWithAndAgeLessThanEqualOrderByIdDesc(String name, int age);

    /**
     * 使用nativeQuery标识是原始sql语句查询出来是原始类型object
     *
     * @param id
     * @return
     */
    @Query(value = "SELECT s.name FROM student s WHERE s.id = ?1", nativeQuery = true)
    String findNameById(Long id);

    /**
     * Find by name order by id desc page.
     *
     * @param pageable
     * @param name
     * @return
     */
    Page<StudentEntity> findByNameStartingWithOrderByIdDesc(Pageable pageable, String name);
}
