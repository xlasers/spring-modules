package com.xlaser4j.jdbc;

import javax.annotation.PostConstruct;

import com.xlaser4j.jdbc.model.Student;
import com.xlaser4j.jdbc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @package: com.xlaser4j.jdbc
 * @author: Elijah.D
 * @time: 2019/12/10 14:15
 * @description:
 * @copyright: Copyright(c) 2019
 * @version: V1.0
 * @modified: Elijah.D
 */
@SpringBootApplication
public class JdbcApplication {
    @Autowired
    StudentService service;

    public static void main(String[] args) {
        SpringApplication.run(JdbcApplication.class, args);
    }

    @PostConstruct
    public void print() {
        service.saveStudent(new Student(1L, 2, "Deleted"));
        service.listStudentsByRead();
        service.saveStudent(new Student(2, "Linus-" + Math.random()));
        service.getOne();
        service.updateStudent(new Student(2L, 2, "Martin Flower-" + Math.random()));
        service.listStudentsByWrite();
        service.deleteStudent(1L);
        service.listStudentsByRowMapper();
    }
}
