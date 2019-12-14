package com.xlaser4j.mybatis.service;

import java.util.List;

import com.xlaser4j.mybatis.mapper.read.StudentReadMapper;
import com.xlaser4j.mybatis.mapper.write.StudentWriteMapper;
import com.xlaser4j.mybatis.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @package: com.xlaser4j.mybatis.service
 * @author: Elijah.D
 * @time: 2019/12/14 15:57
 * @description:
 * @copyright: Copyright(c) 2019
 * @version: V1.0
 * @modified: Elijah.D
 */
@Service
public class StudentService {
    private StudentReadMapper read;

    private StudentWriteMapper write;

    @Autowired
    public StudentService(StudentReadMapper read, StudentWriteMapper write) {
        this.read = read;
        this.write = write;
    }

    /**
     * list
     *
     * @return
     */
    public List<Student> listStudents() {
        List<Student> readList = read.listReadStudents();
        System.out.println("read=====>> " + readList);
        List<Student> writeList = write.listStudents();
        System.out.println("write=====>> " + writeList);
        writeList.addAll(readList);

        return writeList;
    }
}
