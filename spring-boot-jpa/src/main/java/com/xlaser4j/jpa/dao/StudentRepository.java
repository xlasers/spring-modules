package com.xlaser4j.mvc.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.xlaser4j.jpa.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * The interface Student repository.
 *
 * @package: com.xlaser4j.spring.dao
 * @author: Elijah.D
 * @time: 2018/10/6 14:54
 * @description: dao
 * @copyright: Copyright(c) 2018
 * @version: V1.0
 * @modified: Elijah.D
 */
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
    /**
     * Find by order by id desc list.
     *
     * @return the list
     */
    List<Student> findByOrderByIdDesc();

    /**
     * Find by name starting with and age less than student.
     *
     * @param name the name
     * @param i    the
     * @return the student
     */
    List<Student> findByNameStartingWithAndAgeLessThanEqualOrderByIdDesc(String name, int i);

    /**
     * Find name by id string.
     *
     * @param id the id
     * @return the string
     */
    @Query("SELECT s.name FROM Student s WHERE s.id = ?1")
    String findNameById(Long id);

    /**
     * Delete by name and age.
     *
     * @param name the name
     * @param age  the age
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM Student WHERE name = ?1 AND age = ?2")
    void deleteByNameAndAge(String name, int age);

    /**
     * Find by name order by id desc page.
     *
     * @param pageable the pageable
     * @param name     the name
     * @return the page
     */
    Page<Student> findByNameStartingWithOrderByIdDesc(Pageable pageable, String name);
}
