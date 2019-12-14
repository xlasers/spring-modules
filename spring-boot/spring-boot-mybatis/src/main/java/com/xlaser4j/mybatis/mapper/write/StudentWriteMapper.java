package com.xlaser4j.mybatis.mapper.write;

import java.util.List;

import com.xlaser4j.mybatis.model.Student;

/**
 * @package: com.xlaser4j.mybatis.mapper.write
 * @author: Elijah.D
 * @time: 2019/12/14 15:32
 * @description:
 * @copyright: Copyright(c) 2019
 * @version: V1.0
 * @modified: Elijah.D
 */
public interface StudentWriteMapper {
    /**
     * list
     *
     * @return
     */
    List<Student> listStudents();
}
