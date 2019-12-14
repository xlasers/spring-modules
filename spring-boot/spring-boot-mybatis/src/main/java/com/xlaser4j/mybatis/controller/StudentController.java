package com.xlaser4j.mybatis.controller;

import java.util.List;

import com.xlaser4j.mybatis.model.Student;
import com.xlaser4j.mybatis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.xlaser4j.mybatis.controller
 * @author: Elijah.D
 * @time: 2019/12/14 16:22
 * @description:
 * @copyright: Copyright(c) 2019
 * @version: V1.0
 * @modified: Elijah.D
 */
@RestController
public class StudentController {
    @Autowired
    StudentService service;

    /**
     * @return
     */
    @GetMapping("/students")
    public List<Student> listStudents() {
        List<Student> list = service.listStudents();
        System.out.println("testMybatis======> " + list);
        return list;
    }
}
