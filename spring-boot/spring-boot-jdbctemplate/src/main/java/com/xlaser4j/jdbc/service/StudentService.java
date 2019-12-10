package com.xlaser4j.jdbc.service;

import java.util.List;

import com.xlaser4j.jdbc.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @package: com.xlaser4j.jdbc.service
 * @author: Elijah.D
 * @time: 2019/12/10 14:37
 * @description:
 * @copyright: Copyright(c) 2019
 * @version: V1.0
 * @modified: Elijah.D
 */
@Service
public class StudentService {
    @Autowired
    @Qualifier("jdbcTemplateRead")
    JdbcTemplate readTemplate;

    @Autowired
    @Qualifier("jdbcTemplateWrite")
    JdbcTemplate writeTemplate;

    /**
     * list {@link BeanPropertyRowMapper}
     *
     * @return data
     */
    public void listStudentsByRead() {

        // BEAN-PROPERTY-ROW-MAPPER: READ
        List<Student> read = readTemplate.query("SELECT * FROM STUDENT", new BeanPropertyRowMapper<>(Student.class));
        System.out.println(read);
    }

    /**
     * save student
     *
     * @param student
     */
    public void saveStudent(Student student) {
        int update = writeTemplate.update("INSERT INTO STUDENT (ID, AGE, NAME) VALUES (?,?, ?)", student.getId(), student.getAge(), student.getName());
        String msg = update > 0 ? "ADD STUDENT SUCCESS" : "ADD STUDENT FAILED!";
        System.out.println(msg);
    }

    /**
     * get one
     */
    public void getOne() {
        Student query = readTemplate.queryForObject("SELECT * FROM STUDENT WHERE ID = ?", new BeanPropertyRowMapper<>(Student.class), 2);
        System.out.println(query);
    }

    /**
     * update student
     *
     * @param student
     */
    public void updateStudent(Student student) {
        int update = writeTemplate.update("UPDATE STUDENT SET NAME = ? WHERE ID = ?", student.getName(), student.getId());
        String msg = update > 0 ? "UPDATE STUDENT SUCCESS" : "UPDATE STUDENT FAILED!";
        System.out.println(msg);
    }

    /**
     * list {@link BeanPropertyRowMapper}
     *
     * @return data
     */
    public void listStudentsByWrite() {

        // BEAN-PROPERTY-ROW-MAPPER: WRITE
        List<Student> write = writeTemplate.query("SELECT * FROM STUDENT", new BeanPropertyRowMapper<>(Student.class));
        System.out.println(write);
    }

    /**
     * delete student
     *
     * @param id
     */
    public void deleteStudent(Long id) {
        int update = writeTemplate.update("DELETE FROM STUDENT WHERE ID = ?", id);
        String msg = update > 0 ? "DELETE STUDENT SUCCESS" : "DELETE STUDENT FAILED!";
        System.out.println(msg);
    }

    /**
     * list {@link StudentRowMapper}
     *
     * @return data
     */
    public void listStudentsByRowMapper() {

        // ROW-MAPPER
        List<Student> rowMapper = writeTemplate.query("SELECT * FROM STUDENT", new StudentRowMapper());
        System.out.println(rowMapper);
    }
}
