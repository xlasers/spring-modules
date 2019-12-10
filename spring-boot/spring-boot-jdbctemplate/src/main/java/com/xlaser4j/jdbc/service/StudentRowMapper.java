package com.xlaser4j.jdbc.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.xlaser4j.jdbc.model.Student;
import org.springframework.jdbc.core.RowMapper;

/**
 * @package: com.xlaser4j.jdbc.service
 * @author: Elijah.D
 * @time: 2019/12/10 14:44
 * @description:
 * @copyright: Copyright(c) 2019
 * @version: V1.0
 * @modified: Elijah.D
 */
public class StudentRowMapper implements RowMapper<Student> {
    /**
     * Implementations must implement this method to map each row of data in the ResultSet.
     * 自定义映射字段映射问题,如果字段名与属性名一直,则可以直接利用{@link org.springframework.jdbc.core.BeanPropertyRowMapper}
     *
     * @param rs     ResultSet
     * @param rowNum the number of the current row
     * @return the result object for the current row
     * @throws SQLException
     */
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("name");
        Integer age = rs.getInt("age");
        return new Student(id, age, name);
    }
}
